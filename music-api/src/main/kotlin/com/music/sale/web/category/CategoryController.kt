package com.music.sale.web.category

import com.music.sale.application.category.port.inport.CategoryUseCase
import com.music.sale.domain.category.CategoryType
import com.music.sale.web.category.mapper.CategoryWebMapper
import com.music.sale.web.category.response.CategoryResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/categories")
class CategoryController(
    private val categoryUseCase: CategoryUseCase,
    private val categoryWebMapper: CategoryWebMapper,
) {
    @GetMapping("/{id}")
    fun getCategoryById(
        @PathVariable id: Long,
    ): ResponseEntity<CategoryResponse> {
        val category = categoryUseCase.getCategoryById(id)
        return ResponseEntity.ok(categoryWebMapper.toResponse(category))
    }

    @GetMapping("/all")
    fun getAllCategories(): ResponseEntity<List<CategoryResponse>> {
        val categories = categoryUseCase.getAllCategories()
        return ResponseEntity.ok(categories.map { categoryWebMapper.toResponse(it) })
    }

    @GetMapping("/type")
    fun getCategoriesByType(
        @RequestParam type: CategoryType,
    ): ResponseEntity<List<CategoryResponse>> {
        val categories = categoryUseCase.getCategoriesByType(type)
        return ResponseEntity.ok(categories.map { categoryWebMapper.toResponse(it) })
    }

    @GetMapping("/root")
    fun getRootCategories(): ResponseEntity<List<CategoryResponse>> {
        val categories = categoryUseCase.getRootCategories()
        return ResponseEntity.ok(categories.map { categoryWebMapper.toResponse(it) })
    }

    @GetMapping("/parent/{parentId}")
    fun getCategoriesByParentId(
        @PathVariable parentId: Long,
    ): ResponseEntity<List<CategoryResponse>> {
        val categories = categoryUseCase.getCategoriesByParentId(parentId)
        return ResponseEntity.ok(categories.map { categoryWebMapper.toResponse(it) })
    }
}
