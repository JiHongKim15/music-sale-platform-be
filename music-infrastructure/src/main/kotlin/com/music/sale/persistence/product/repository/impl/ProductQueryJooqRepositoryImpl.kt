package com.music.sale.persistence.product.repository.impl

import com.music.sale.application.product.dto.SearchProductCondition
import com.music.sale.domain.category.Category
import com.music.sale.domain.category.CategoryType
import com.music.sale.domain.store.Store
import com.music.sale.domain.user.User
import com.music.sale.persistence.product.dto.ProductCatalogQueryResult
import com.music.sale.persistence.product.dto.ProductQueryResult
import com.music.sale.persistence.product.repository.ProductQueryJooqRepository
import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Repository

@Repository
open class ProductQueryJooqRepositoryImpl(
    private val dslContext: DSLContext,
) : ProductQueryJooqRepository {
    override fun findAll(pageable: PageRequest): Page<ProductQueryResult> {
        val query =
            dslContext
                .select(
                    DSL.field("id").cast(Long::class.java),
                    DSL.field("custom_name").cast(String::class.java),
                    DSL.field("price").cast(Int::class.java),
                    DSL.field("stock_quantity").cast(Int::class.java),
                    DSL.field("condition").cast(String::class.java),
                    DSL.field("condition_grade").cast(String::class.java),
                    DSL.field("status").cast(String::class.java),
                    DSL.field("catalog_id").cast(Long::class.java),
                    DSL.field("catalog_name").cast(String::class.java),
                    DSL.field("catalog_brand").cast(String::class.java),
                    DSL.field("category_id").cast(Long::class.java),
                    DSL.field("category_name").cast(String::class.java),
                    DSL.field("category_type").cast(String::class.java),
                    DSL.field("category_path").cast(String::class.java),
                    DSL.field("category_depth").cast(Int::class.java),
                    DSL.field("seller_id").cast(Long::class.java),
                    DSL.field("seller_name").cast(String::class.java),
                    DSL.field("store_id").cast(Long::class.java),
                    DSL.field("custom_attributes").cast(String::class.java),
                )
                .from("product_items")
                .limit(pageable.pageSize)
                .offset(pageable.offset.toInt())

        val results =
            query.fetch().map { record ->
                ProductQueryResult(
                    id = record.get(0, Long::class.java),
                    name = record.get(1, String::class.java) ?: "",
                    catalog = ProductCatalogQueryResult(
                        id = record.get(7, Long::class.java),
                        name = record.get(8, String::class.java),
                        category = Category(
                            id = record.get(10, Long::class.java),
                            name = record.get(11, String::class.java),
                            type = CategoryType.valueOf(record.get(12, String::class.java)),
                            parent = null,
                            path = record.get(13, String::class.java),
                            depth = record.get(14, Int::class.java),
                        ),
                        brand = record.get(9, String::class.java) ?: "",
                        attribute = emptyMap(),
                    ),
                    price = record.get(2, Int::class.java),
                    seller = User(
                        id = record.get(15, Long::class.java),
                        name = User.Name(record.get(16, String::class.java)),
                    ),
                    store = Store(record.get(17, Long::class.java)),
                    condition = com.music.sale.domain.product.enum.ProductCondition.valueOf(
                        record.get(4, String::class.java),
                    ),
                    conditionGrade = record.get(5, String::class.java)?.let {
                        com.music.sale.domain.product.enum.ProductConditionGrade.valueOf(it)
                    },
                    stockQuantity = record.get(3, Int::class.java),
                    status = com.music.sale.domain.product.enum.ProductStatus.valueOf(
                        record.get(6, String::class.java),
                    ),
                    attributes = null,
                    images = null,
                )
            }

        val totalCount = dslContext.selectCount().from("product_items").fetchOne(0, Long::class.java) ?: 0L
        return org.springframework.data.domain.PageImpl(results, pageable, totalCount)
    }

    override fun findById(id: Long): ProductQueryResult? {
        val query =
            dslContext
                .select(
                    DSL.field("id").cast(Long::class.java),
                    DSL.field("custom_name").cast(String::class.java),
                    DSL.field("price").cast(Int::class.java),
                    DSL.field("stock_quantity").cast(Int::class.java),
                    DSL.field("condition").cast(String::class.java),
                    DSL.field("condition_grade").cast(String::class.java),
                    DSL.field("status").cast(String::class.java),
                    DSL.field("catalog_id").cast(Long::class.java),
                    DSL.field("catalog_name").cast(String::class.java),
                    DSL.field("catalog_brand").cast(String::class.java),
                    DSL.field("category_id").cast(Long::class.java),
                    DSL.field("category_name").cast(String::class.java),
                    DSL.field("category_type").cast(String::class.java),
                    DSL.field("category_path").cast(String::class.java),
                    DSL.field("category_depth").cast(Int::class.java),
                    DSL.field("seller_id").cast(Long::class.java),
                    DSL.field("seller_name").cast(String::class.java),
                    DSL.field("store_id").cast(Long::class.java),
                    DSL.field("custom_attributes").cast(String::class.java),
                )
                .from("product_items")
                .where(DSL.field("id").eq(id))

        return query.fetchOne()?.let { record ->
            ProductQueryResult(
                id = record.get(0, Long::class.java),
                name = record.get(1, String::class.java) ?: "",
                catalog = ProductCatalogQueryResult(
                    id = record.get(7, Long::class.java),
                    name = record.get(8, String::class.java),
                    category = Category(
                        id = record.get(10, Long::class.java),
                        name = record.get(11, String::class.java),
                        type = CategoryType.valueOf(record.get(12, String::class.java)),
                        parent = null,
                        path = record.get(13, String::class.java),
                        depth = record.get(14, Int::class.java),
                    ),
                    brand = record.get(9, String::class.java) ?: "",
                    attribute = emptyMap(), // JSON 파싱 필요시 구현
                ),
                price = record.get(2, Int::class.java),
                seller = User(
                    id = record.get(15, Long::class.java),
                    name = User.Name(record.get(16, String::class.java)),
                ),
                store = Store(record.get(17, Long::class.java)),
                condition = com.music.sale.domain.product.enum.ProductCondition.valueOf(
                    record.get(4, String::class.java),
                ),
                conditionGrade = record.get(5, String::class.java)?.let {
                    com.music.sale.domain.product.enum.ProductConditionGrade.valueOf(it)
                },
                stockQuantity = record.get(3, Int::class.java),
                status = com.music.sale.domain.product.enum.ProductStatus.valueOf(
                    record.get(6, String::class.java),
                ),
                attributes = null, // JSON 파싱 필요시 구현
                images = null, // 별도 조인 필요시 구현
            )
        }
    }

    override fun searchProducts(
        searchCondition: SearchProductCondition,
        pageable: PageRequest,
    ): Page<ProductQueryResult> {
        return findAll(pageable)
    }
}