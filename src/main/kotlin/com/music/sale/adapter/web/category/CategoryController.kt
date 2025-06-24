// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.web.category

import com.music.sale.adapter.web.category.mapper.CategoryWebMapper
import com.music.sale.adapter.web.category.response.CategoryResponse
import com.music.sale.adapter.web.common.ApiResponse
import com.music.sale.application.category.port.inport.CategoryUseCase
import com.music.sale.domain.category.CategoryType
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/category")
@Tag(name = "Category", description = "카테고리 관리 API")
class CategoryController(
    private val categoryUseCase: CategoryUseCase,
    private val categoryWebMapper: CategoryWebMapper,
) {
    @GetMapping
    @Operation(summary = "전체 카테고리 조회", description = "시스템의 모든 카테고리를 조회합니다")
    fun getAllCategories(): ResponseEntity<ApiResponse<List<CategoryResponse>>> {
        val categories = categoryUseCase.getAllCategories()
        val response = categories.map { categoryWebMapper.toResponse(it) }
        return ResponseEntity.ok(ApiResponse.success(data = response))
    }

    @GetMapping("/{id}")
    @Operation(summary = "카테고리 ID로 조회", description = "카테고리 ID를 통해 특정 카테고리를 조회합니다")
    fun getCategoryById(
        @Parameter(description = "카테고리 ID", required = true)
        @PathVariable id: Long,
    ): ResponseEntity<ApiResponse<CategoryResponse>> {
        val category = categoryUseCase.getCategoryById(id)
        val response = categoryWebMapper.toResponse(category)
        return ResponseEntity.ok(ApiResponse.success(data = response))
    }

    @GetMapping("/type/{type}")
    @Operation(summary = "카테고리 타입별 조회", description = "카테고리 타입에 따라 카테고리를 조회합니다")
    fun getCategoriesByType(
        @Parameter(description = "카테고리 타입 (PRODUCT, STORE, BOARD, SEARCH, STATISTICS)", required = true)
        @PathVariable type: CategoryType,
    ): ResponseEntity<ApiResponse<List<CategoryResponse>>> {
        val categories = categoryUseCase.getCategoriesByType(type)
        val response = categories.map { categoryWebMapper.toResponse(it) }
        return ResponseEntity.ok(ApiResponse.success(data = response))
    }

    @GetMapping("/root")
    @Operation(summary = "루트 카테고리 조회", description = "최상위 카테고리들을 조회합니다")
    fun getRootCategories(): ResponseEntity<ApiResponse<List<CategoryResponse>>> {
        val categories = categoryUseCase.getRootCategories()
        val response = categories.map { categoryWebMapper.toResponse(it) }
        return ResponseEntity.ok(ApiResponse.success(data = response))
    }

    @GetMapping("/parent/{parentId}")
    @Operation(summary = "하위 카테고리 조회", description = "특정 부모 카테고리의 하위 카테고리들을 조회합니다")
    fun getCategoriesByParentId(
        @Parameter(description = "부모 카테고리 ID", required = true)
        @PathVariable parentId: Long,
    ): ResponseEntity<ApiResponse<List<CategoryResponse>>> {
        val categories = categoryUseCase.getCategoriesByParentId(parentId)
        val response = categories.map { categoryWebMapper.toResponse(it) }
        return ResponseEntity.ok(ApiResponse.success(data = response))
    }
} 
