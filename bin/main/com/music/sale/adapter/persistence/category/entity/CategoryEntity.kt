package com.music.sale.adapter.persistence.category.entity

import com.music.sale.adapter.persistence.common.BaseEntity
import com.music.sale.domain.category.Category
import com.music.sale.domain.category.CategoryType
import jakarta.persistence.*

@Entity
@Table(name = "category")
class CategoryEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val name: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val type: CategoryType,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    val parent: CategoryEntity? = null,

    @Column(nullable = false)
    val path: String,

    @Column(nullable = false)
    val depth: Int = 0,

    @Column(nullable = false)
    val isActive: Boolean = true

) : BaseEntity() {
    fun toDomain(): Category {
        return Category(
            id = id ?: 0L,
            name = name,
            type = type,
            parent = parent?.toDomain(),
            path = path,
            depth = depth,
            isActive = isActive
        )
    }

    companion object {
        fun fromDomain(category: Category): CategoryEntity {
            return CategoryEntity(
                id = category.id,
                name = category.name,
                type = category.type,
                parent = category.parent?.let { fromDomain(it) },
                path = category.path,
                depth = category.depth,
                isActive = category.isActive
            )
        }
    }
} 