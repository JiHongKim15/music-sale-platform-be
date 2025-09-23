package com.music.sale.persistence.product.repository.impl

import com.music.sale.domain.category.Category
import com.music.sale.domain.category.CategoryType
import com.music.sale.persistence.product.dto.ProductCatalogQueryResult
import com.music.sale.persistence.product.repository.ProductCatalogQueryJooqRepository
import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
open class ProductCatalogQueryJooqRepositoryImpl(
    private val dslContext: DSLContext,
) : ProductCatalogQueryJooqRepository {
    override fun findAll(pageable: Pageable): Page<ProductCatalogQueryResult> {
        val query =
            dslContext
                .select(
                    DSL.field("id").cast(Long::class.java),
                    DSL.field("name").cast(String::class.java),
                    DSL.field("category_id").cast(Long::class.java),
                    DSL.field("category_name").cast(String::class.java),
                    DSL.field("category_type").cast(String::class.java),
                    DSL.field("category_path").cast(String::class.java),
                    DSL.field("category_depth").cast(Int::class.java),
                    DSL.field("brand").cast(String::class.java),
                )
                .from("product_catalogs")
                .orderBy(DSL.field("created_at").desc())
                .limit(pageable.pageSize)
                .offset(pageable.offset)

        val results =
            query.fetch().map { record ->
                ProductCatalogQueryResult(
                    id = record.get(0, Long::class.java),
                    name = record.get(1, String::class.java),
                    category = Category(
                        id = record.get(2, Long::class.java),
                        name = record.get(3, String::class.java),
                        type = CategoryType.valueOf(record.get(4, String::class.java)),
                        parent = null,
                        path = record.get(5, String::class.java),
                        depth = record.get(6, Int::class.java),
                    ),
                    brand = record.get(7, String::class.java) ?: "",
                    attribute = emptyMap(),
                )
            }

        return PageImpl(results, pageable, results.size.toLong())
    }

    override fun findById(id: Long): ProductCatalogQueryResult? {
        val query =
            dslContext
                .select(
                    DSL.field("id").cast(Long::class.java),
                    DSL.field("name").cast(String::class.java),
                    DSL.field("category_id").cast(Long::class.java),
                    DSL.field("category_name").cast(String::class.java),
                    DSL.field("category_type").cast(String::class.java),
                    DSL.field("category_path").cast(String::class.java),
                    DSL.field("category_depth").cast(Int::class.java),
                    DSL.field("brand").cast(String::class.java),
                )
                .from("product_catalogs")
                .where(DSL.field("id").eq(id))

        return query.fetchOne()?.let { record ->
            ProductCatalogQueryResult(
                id = record.get(0, Long::class.java),
                name = record.get(1, String::class.java),
                category = Category(
                    id = record.get(2, Long::class.java),
                    name = record.get(3, String::class.java),
                    type = CategoryType.valueOf(record.get(4, String::class.java)),
                    parent = null,
                    path = record.get(5, String::class.java),
                    depth = record.get(6, Int::class.java),
                ),
                brand = record.get(7, String::class.java) ?: "",
                attribute = emptyMap(),
            )
        }
    }

    override fun findByCategoryId(
        categoryId: Long,
        pageable: Pageable,
    ): Page<ProductCatalogQueryResult> {
        val query =
            dslContext
                .select(
                    DSL.field("id").cast(Long::class.java),
                    DSL.field("name").cast(String::class.java),
                    DSL.field("category_id").cast(Long::class.java),
                    DSL.field("category_name").cast(String::class.java),
                    DSL.field("category_type").cast(String::class.java),
                    DSL.field("category_path").cast(String::class.java),
                    DSL.field("category_depth").cast(Int::class.java),
                    DSL.field("brand").cast(String::class.java),
                )
                .from("product_catalogs")
                .where(DSL.field("category_id").eq(categoryId))
                .orderBy(DSL.field("created_at").desc())
                .limit(pageable.pageSize)
                .offset(pageable.offset)

        val results =
            query.fetch().map { record ->
                ProductCatalogQueryResult(
                    id = record.get(0, Long::class.java),
                    name = record.get(1, String::class.java),
                    category = Category(
                        id = record.get(2, Long::class.java),
                        name = record.get(3, String::class.java),
                        type = CategoryType.valueOf(record.get(4, String::class.java)),
                        parent = null,
                        path = record.get(5, String::class.java),
                        depth = record.get(6, Int::class.java),
                    ),
                    brand = record.get(7, String::class.java) ?: "",
                    attribute = emptyMap(),
                )
            }

        return PageImpl(results, pageable, results.size.toLong())
    }

    override fun findByNameContaining(
        name: String,
        pageable: Pageable,
    ): Page<ProductCatalogQueryResult> {
        val query =
            dslContext
                .select(
                    DSL.field("id").cast(Long::class.java),
                    DSL.field("name").cast(String::class.java),
                    DSL.field("category_id").cast(Long::class.java),
                    DSL.field("category_name").cast(String::class.java),
                    DSL.field("category_type").cast(String::class.java),
                    DSL.field("category_path").cast(String::class.java),
                    DSL.field("category_depth").cast(Int::class.java),
                    DSL.field("brand").cast(String::class.java),
                )
                .from("product_catalogs")
                .where(DSL.field("name").like("%$name%"))
                .orderBy(DSL.field("created_at").desc())
                .limit(pageable.pageSize)
                .offset(pageable.offset)

        val results =
            query.fetch().map { record ->
                ProductCatalogQueryResult(
                    id = record.get(0, Long::class.java),
                    name = record.get(1, String::class.java),
                    category = Category(
                        id = record.get(2, Long::class.java),
                        name = record.get(3, String::class.java),
                        type = CategoryType.valueOf(record.get(4, String::class.java)),
                        parent = null,
                        path = record.get(5, String::class.java),
                        depth = record.get(6, Int::class.java),
                    ),
                    brand = record.get(7, String::class.java) ?: "",
                    attribute = emptyMap(),
                )
            }

        return PageImpl(results, pageable, results.size.toLong())
    }
}