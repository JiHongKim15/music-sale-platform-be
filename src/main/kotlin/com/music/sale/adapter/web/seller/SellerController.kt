// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.web.seller

import com.music.sale.adapter.web.common.ApiResponse
import com.music.sale.application.user.service.SellerVerificationService
import com.music.sale.domain.user.User
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/seller")
@Tag(name = "판매자 관리", description = "판매자 관련 API (임시 단순화 버전)")
class SellerController(
    private val sellerVerificationService: SellerVerificationService,
) {
    @GetMapping("/{userId}")
    @Operation(summary = "사용자 조회", description = "사용자 정보를 조회합니다")
    fun getUser(
        @PathVariable userId: Long,
    ): ResponseEntity<ApiResponse<UserResponse>> {
        val user = sellerVerificationService.getUserById(userId)

        return ResponseEntity.ok(
            ApiResponse.success(
                UserResponse.from(user),
            ),
        )
    }
}

// Response DTO
data class UserResponse(
    val id: Long,
    val email: String,
    val name: String,
    val nickname: String,
    val role: String,
) {
    companion object {
        fun from(user: User): UserResponse {
            return UserResponse(
                id = user.id ?: 0L,
                email = user.email?.value ?: "",
                name = user.name?.value ?: "",
                nickname = user.nickname?.value ?: "",
                role = user.role?.name ?: "",
            )
        }
    }
} 
