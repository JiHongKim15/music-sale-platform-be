package com.music.sale.adapter.persistence.category.entity

import com.music.sale.adapter.persistence.common.BaseEntity
import com.music.sale.domain.category.CategoryType
import jakarta.persistence.*

@Entity
@Table(name = "category")
class CategoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    val depth: Int
) : BaseEntity() 