package com.music.sale.persistence.product.repository.impl

import com.music.sale.application.product.dto.SearchProductCondition
import com.music.sale.domain.category.CategoryType
import com.music.sale.persistence.category.entity.CategoryEntity
import com.music.sale.persistence.product.dto.ProductCatalogQueryResult
import com.music.sale.persistence.product.dto.ProductQueryResult
import com.music.sale.persistence.product.entity.ProductCatalogEntity
import com.music.sale.persistence.product.entity.ProductImageEntity
import com.music.sale.persistence.product.entity.ProductItemEntity
import com.music.sale.persistence.product.repository.ProductQueryJooqRepository
import org.jooq.*
import org.jooq.impl.DSL
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Repository

@Repository
open class ProductQueryJooqRepositoryImpl(
    private val dslContext: DSLContext,
) : ProductQueryJooqRepository {
    
    override fun findAll(pageable: PageRequest): Page<ProductQueryResult> {
        val baseQuery = buildProductQuery()
        val conditionedQuery = applySearchConditions(baseQuery, SearchProductCondition())
        val orderedQuery = applySorting(conditionedQuery, pageable.sort)
        
        val results = orderedQuery
            .limit(pageable.pageSize)
            .offset(pageable.offset)
            .fetch()
            .map { record -> mapToProductQueryResult(record) }

        val totalCount = countAllProducts()
        return PageImpl(results, pageable, totalCount)
    }

    override fun findById(id: Long): ProductQueryResult? {
        val baseQuery = buildProductQuery()
        val query = baseQuery.where(DSL.field("pi.id").eq(id))

        return query.fetchOne()?.let { record ->
            mapToProductQueryResult(record)
        }
    }

    override fun searchProducts(
        searchCondition: SearchProductCondition,
        pageable: PageRequest,
    ): Page<ProductQueryResult> {
        val baseQuery = buildProductQuery()
        val conditionedQuery = applySearchConditions(baseQuery, searchCondition)
        val orderedQuery = applySorting(conditionedQuery, pageable.sort)
        
        val results = orderedQuery
            .limit(pageable.pageSize)
            .offset(pageable.offset)
            .fetch()
            .map { record -> mapToProductQueryResult(record) }

        val totalCount = countProductsWithConditions(searchCondition)
        return PageImpl(results, pageable, totalCount)
    }

    @Suppress("UNCHECKED_CAST")
    private fun buildProductQuery(): Select<Record> {
        return dslContext
            .select(
                // ProductItem 필드들
                DSL.field("pi.id").`as`("product_id"),
                DSL.field("pi.custom_name").`as`("product_name"),
                DSL.field("pi.price"),
                DSL.field("pi.stock_quantity"),
                DSL.field("pi.condition"),
                DSL.field("pi.condition_grade"),
                DSL.field("pi.status"),
                DSL.field("pi.custom_attributes"),
                DSL.field("pi.seller_id"),
                DSL.field("pi.store_id"),
                
                // ProductCatalog 필드들
                DSL.field("pc.id").`as`("catalog_id"),
                DSL.field("pc.name").`as`("catalog_name"),
                DSL.field("pc.brand"),
                DSL.field("pc.attributes").`as`("catalog_attributes"),
                
                // Category 필드들
                DSL.field("c.id").`as`("category_id"),
                DSL.field("c.name").`as`("category_name"),
                DSL.field("c.type").`as`("category_type"),
                DSL.field("c.path").`as`("category_path"),
                DSL.field("c.depth").`as`("category_depth")
            )
            .from(DSL.table("product_item").`as`("pi"))
            .innerJoin(DSL.table("product_catalog").`as`("pc"))
            .on(DSL.field("pi.catalog_id").eq(DSL.field("pc.id")))
            .innerJoin(DSL.table("category").`as`("c"))
            .on(DSL.field("pc.category_id").eq(DSL.field("c.id"))) as Select<Record>
    }

    @Suppress("UNCHECKED_CAST")
    private fun applySorting(query: Select<Record>, sort: Sort): Select<Record> {
        if (sort.isUnsorted) {
            return query.orderBy(DSL.field("pi.created_at").desc()) as Select<Record>
        }

        val orderFields = sort.map { order ->
            val field = when (order.property.lowercase()) {
                "id" -> DSL.field("pi.id")
                "price" -> DSL.field("pi.price")
                "name" -> DSL.field("pi.custom_name")
                "catalog" -> DSL.field("pc.name")
                "condition" -> DSL.field("pi.condition")
                "conditiongrade" -> DSL.field("pi.condition_grade")
                "stockquantity" -> DSL.field("pi.stock_quantity")
                "status" -> DSL.field("pi.status")
                "createdat" -> DSL.field("pi.created_at")
                "updatedat" -> DSL.field("pi.updated_at")
                else -> DSL.field("pi.created_at")
            }
            
            when (order.direction) {
                Sort.Direction.ASC -> field.asc()
                Sort.Direction.DESC -> field.desc()
            }
        }.toList()

        return query.orderBy(orderFields) as Select<Record>
    }

    @Suppress("UNCHECKED_CAST")
    private fun applySearchConditions(
        query: Select<Record>,
        searchCondition: SearchProductCondition
    ): Select<Record> {
        var conditions = DSL.trueCondition()
        
        searchCondition.keyword?.let { keyword ->
            conditions = conditions.and(
                DSL.field("pc.name").likeIgnoreCase("%$keyword%")
                    .or(DSL.field("pi.custom_name").likeIgnoreCase("%$keyword%"))
                    .or(DSL.field("pc.brand").likeIgnoreCase("%$keyword%"))
            )
        }

        searchCondition.categoryId?.let { categoryId ->
            conditions = conditions.and(DSL.field("c.id").eq(categoryId))
        }

        searchCondition.minPrice?.let { minPrice ->
            conditions = conditions.and(DSL.field("pi.price").ge(minPrice))
        }

        searchCondition.maxPrice?.let { maxPrice ->
            conditions = conditions.and(DSL.field("pi.price").le(maxPrice))
        }

        searchCondition.condition?.let { condition ->
            conditions = conditions.and(DSL.field("pi.condition").eq(condition.name))
        }

        return query.where(conditions) as Select<Record>
    }

    private fun countAllProducts(): Long {
        return dslContext
            .selectCount()
            .from(DSL.table("product_item").`as`("pi"))
            .innerJoin(DSL.table("product_catalog").`as`("pc"))
            .on(DSL.field("pi.catalog_id").eq(DSL.field("pc.id")))
            .fetchOne(0, Long::class.java) ?: 0L
    }

    private fun countProductsWithConditions(searchCondition: SearchProductCondition): Long {
        var conditions = DSL.trueCondition()
        
        searchCondition.keyword?.let { keyword ->
            conditions = conditions.and(
                DSL.field("pc.name").likeIgnoreCase("%$keyword%")
                    .or(DSL.field("pi.custom_name").likeIgnoreCase("%$keyword%"))
                    .or(DSL.field("pc.brand").likeIgnoreCase("%$keyword%"))
            )
        }

        searchCondition.categoryId?.let { categoryId ->
            conditions = conditions.and(DSL.field("c.id").eq(categoryId))
        }

        searchCondition.minPrice?.let { minPrice ->
            conditions = conditions.and(DSL.field("pi.price").ge(minPrice))
        }

        searchCondition.maxPrice?.let { maxPrice ->
            conditions = conditions.and(DSL.field("pi.price").le(maxPrice))
        }

        searchCondition.condition?.let { condition ->
            conditions = conditions.and(DSL.field("pi.condition").eq(condition.name))
        }

        return dslContext
            .selectCount()
            .from(DSL.table("product_item").`as`("pi"))
            .innerJoin(DSL.table("product_catalog").`as`("pc"))
            .on(DSL.field("pi.catalog_id").eq(DSL.field("pc.id")))
            .innerJoin(DSL.table("category").`as`("c"))
            .on(DSL.field("pc.category_id").eq(DSL.field("c.id")))
            .where(conditions)
            .fetchOne(0, Long::class.java) ?: 0L
    }

    private fun mapToProductQueryResult(record: Record): ProductQueryResult {
        val catalog = ProductCatalogQueryResult(
            id = record.get("catalog_id", Long::class.java),
            name = record.get("catalog_name", String::class.java),
            categoryId = record.get("category_id", Long::class.java) ?: 0L,
            brand = record.get("brand", String::class.java) ?: "",
            attribute = parseAttributesToStringMap(record.get("catalog_attributes", String::class.java))
        )

        val productId = record.get("product_id", Long::class.java)
        val images = getProductImageEntities(productId)

        return ProductQueryResult(
            id = productId,
            name = record.get("product_name", String::class.java) ?: record.get("catalog_name", String::class.java),
            catalog = catalog,
            price = record.get("price", Int::class.java),
            sellerId = record.get("seller_id", Long::class.java),
            storeId = record.get("store_id", Long::class.java),
            condition = com.music.sale.domain.product.enum.ProductCondition.valueOf(
                record.get("condition", String::class.java)
            ),
            conditionGrade = record.get("condition_grade", String::class.java)?.let {
                com.music.sale.domain.product.enum.ProductConditionGrade.valueOf(it)
            },
            stockQuantity = record.get("stock_quantity", Int::class.java),
            status = com.music.sale.domain.product.enum.ProductStatus.valueOf(
                record.get("status", String::class.java)
            ),
            attributes = parseCustomAttributes(record.get("custom_attributes", String::class.java)),
            images = images
        )
    }

    private fun getProductImageEntities(productItemId: Long): List<ProductImageEntity> {
        return dslContext
            .select(
                DSL.field("id"),
                DSL.field("image_url"),
                DSL.field("is_thumbnail"),
                DSL.field("image_order"),
                DSL.field("alt_text")
            )
            .from("product_image")
            .where(DSL.field("product_item_id").eq(productItemId))
            .orderBy(DSL.field("image_order").asc())
            .fetch()
            .map { record ->
                val productItemProxy = ProductItemEntity(
                    id = productItemId,
                    catalog = ProductCatalogEntity(
                        id = 0L,
                        name = "",
                        category = CategoryEntity(
                            id = 0L,
                            name = "",
                            type = CategoryType.DIGITAL,
                            parent = null,
                            path = "",
                            depth = 0,
                            isActive = true
                        )
                    ),
                    price = 0,
                    condition = com.music.sale.domain.product.enum.ProductCondition.NEW,
                    stockQuantity = 0,
                    status = com.music.sale.domain.product.enum.ProductStatus.ACTIVE
                )
                
                ProductImageEntity(
                    id = record.get("id", Long::class.java),
                    productItem = productItemProxy,
                    imageUrl = record.get("image_url", String::class.java),
                    isThumbnail = record.get("is_thumbnail", Boolean::class.java),
                    imageOrder = record.get("image_order", Int::class.java),
                    altText = record.get("alt_text", String::class.java)
                )
            }
    }

    private fun parseAttributesToStringMap(attributesJson: String?): Map<String, String> {
        // TODO: JSON 파싱 로직 구현 - Map<String, String>으로 변환
        return if (attributesJson.isNullOrBlank()) emptyMap() else emptyMap()
    }

    private fun parseCustomAttributes(attributesJson: String?): Map<String, Any>? {
        // TODO: JSON 파싱 로직 구현 - Map<String, Any>로 변환
        return if (attributesJson.isNullOrBlank()) null else emptyMap()
    }
} 
