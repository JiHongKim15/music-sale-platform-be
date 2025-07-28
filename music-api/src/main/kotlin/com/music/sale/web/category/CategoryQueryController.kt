package com.music.sale.web.category

import com.music.sale.application.category.port.inport.CategoryQueryUseCase
import com.music.sale.domain.category.CategoryType
import com.music.sale.web.category.mapper.CategoryQueryWebMapper
import com.music.sale.web.category.response.CategoryQueryResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/categories")
class CategoryQueryController(
    private val categoryQueryUseCase: CategoryQueryUseCase,
    private val categoryQueryWebMapper: CategoryQueryWebMapper,
) {
    @GetMapping("/{id}")
    fun getCategoryById(
        @PathVariable id: Long,
    ): ResponseEntity<CategoryQueryResponse> {
        val category = categoryQueryUseCase.getCategoryById(id)
        return ResponseEntity.ok(categoryQueryWebMapper.toResponse(category))
    }

    @GetMapping("/all")
    fun getAllCategories(): ResponseEntity<List<CategoryQueryResponse>> {
        val categories = categoryQueryUseCase.getAllCategories()
        return ResponseEntity.ok(categories.map { categoryQueryWebMapper.toResponse(it) })
    }

    @GetMapping("/type")
    fun getCategoriesByType(
        @RequestParam type: CategoryType,
    ): ResponseEntity<List<CategoryQueryResponse>> {
        val categories = categoryQueryUseCase.getCategoriesByType(type)
        return ResponseEntity.ok(categories.map { categoryQueryWebMapper.toResponse(it) })
    }

    @GetMapping("/root")
    fun getRootCategories(): ResponseEntity<List<CategoryQueryResponse>> {
        val categories = categoryQueryUseCase.getRootCategories()
        return ResponseEntity.ok(categories.map { categoryQueryWebMapper.toResponse(it) })
    }

    @GetMapping("/parent/{parentId}")
    fun getCategoriesByParentId(
        @PathVariable parentId: Long,
    ): ResponseEntity<List<CategoryQueryResponse>> {
        val categories = categoryQueryUseCase.getCategoriesByParentId(parentId)
        return ResponseEntity.ok(categories.map { categoryQueryWebMapper.toResponse(it) })
    }
}
