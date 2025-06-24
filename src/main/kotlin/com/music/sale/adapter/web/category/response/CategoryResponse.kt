// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.web.category.response

import com.music.sale.domain.category.CategoryType
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "카테고리 응답 DTO")
data class CategoryResponse(
    @Schema(description = "카테고리 ID", example = "1")
    val id: Long,
    @Schema(description = "카테고리 이름", example = "기타")
    val name: String,
    @Schema(description = "카테고리 타입", example = "PRODUCT")
    val type: CategoryType,
    @Schema(description = "부모 카테고리 정보", nullable = true)
    val parent: CategoryParentResponse?,
    @Schema(description = "카테고리 경로", example = "/1")
    val path: String,
    @Schema(description = "카테고리 깊이", example = "0")
    val depth: Int,
    @Schema(description = "활성화 상태", example = "true")
    val isActive: Boolean,
    @Schema(description = "루트 카테고리 여부", example = "true")
    val isRoot: Boolean,
    @Schema(description = "말단 카테고리 여부", example = "true")
    val isLeaf: Boolean,
)

@Schema(description = "부모 카테고리 정보")
data class CategoryParentResponse(
    @Schema(description = "부모 카테고리 ID", example = "1")
    val id: Long,
    @Schema(description = "부모 카테고리 이름", example = "악기")
    val name: String,
    @Schema(description = "부모 카테고리 타입", example = "PRODUCT")
    val type: CategoryType,
) 
