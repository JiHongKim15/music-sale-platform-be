package com.music.sale.persistence.product.repository.impl

import com.music.sale.application.product.dto.SearchProductCondition
import com.music.sale.domain.category.CategoryType
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
    }

    override fun findById(id: Long): ProductQueryResult? {
        val query =
            dslContext
                .select(
                    DSL.field("id").cast(Long::class.java),
                    DSL.field("name").cast(String::class.java),
                    DSL.field("price").cast(Int::class.java),
                    DSL.field("stock_quantity").cast(Int::class.java),
                    DSL.field("condition").cast(String::class.java),
                    DSL.field("condition_grade").cast(String::class.java),
                    DSL.field("status").cast(String::class.java),
                    DSL.field("catalog_id").cast(Long::class.java),
                    DSL.field("catalog_name").cast(String::class.java),
                    DSL.field("category_id").cast(Long::class.java),
                    DSL.field("category_name").cast(String::class.java),
                    DSL.field("category_type").cast(String::class.java),
                    DSL.field("category_path").cast(String::class.java),
                    DSL.field("category_depth").cast(Int::class.java),
                    DSL.field("seller_id").cast(Long::class.java),
                    DSL.field("seller_name").cast(String::class.java),
                    DSL.field("store_id").cast(Long::class.java),
                    DSL.field("store_name").cast(String::class.java),
                    DSL.field("custom_name").cast(String::class.java),
                    DSL.field("custom_attributes").cast(String::class.java),
                    DSL.field("created_at").cast(java.time.LocalDateTime::class.java),
                    DSL.field("updated_at").cast(java.time.LocalDateTime::class.java),
                )
                .from("product_items")
                .where(DSL.field("id").eq(id))

        return query.fetchOne()?.let { record ->
            ProductQueryResult(
                id = record.get(0, Long::class.java),
                name = record.get(1, String::class.java),
                price = record.get(2, Int::class.java),
                stockQuantity = record.get(3, Int::class.java),
                condition =
                    com.music.sale.domain.product.enum.ProductCondition.valueOf(
                        record.get(
                            4,
                            String::class.java,
                        ),
                    ),
                conditionGrade =
                    com.music.sale.domain.product.enum.ProductConditionGrade.valueOf(
                        record.get(
                            5,
                            String::class.java,
                        ),
                    ),
                status = com.music.sale.domain.product.enum.ProductStatus.valueOf(record.get(6, String::class.java)),
                catalogId = record.get(7, Long::class.java),
                catalogName = record.get(8, String::class.java),
                categoryId = record.get(9, Long::class.java),
                categoryName = record.get(10, String::class.java),
                categoryType = CategoryType.valueOf(record.get(11, String::class.java)),
                categoryPath = record.get(12, String::class.java),
                categoryDepth = record.get(13, Int::class.java),
                sellerId = record.get(14, Long::class.java),
                sellerName = record.get(15, String::class.java),
                storeId = record.get(16, Long::class.java),
                storeName = record.get(17, String::class.java),
                customName = record.get(18, String::class.java),
                // TODO: JSON 파싱 로직 추가
                customAttributes = null,
                createdAt = record.get(20, java.time.LocalDateTime::class.java),
                updatedAt = record.get(21, java.time.LocalDateTime::class.java),
            )
        }
    }

    override fun searchProducts(
        searchCondition: SearchProductCondition,
        pageable: PageRequest,
    ): Page<ProductQueryResult> {
        // TODO: 복잡한 검색 로직은 나중에 구현
        // 현재는 기본 findAll과 동일하게 구현
        return findAll(pageable)
    }
} 
