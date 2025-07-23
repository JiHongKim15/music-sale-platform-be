package com.music.sale.web.redis

import com.music.sale.application.redis.RedisService
import com.music.sale.common.ApiResponse
import org.springframework.web.bind.annotation.*
import java.time.Duration

@RestController
@RequestMapping("/api/redis/business")
class RedisBusinessController(
    private val redisService: RedisService
) {

    // 사용자 세션 관리
    @PostMapping("/session/save")
    fun saveUserSession(
        @RequestParam userId: String,
        @RequestBody sessionData: Map<String, Any>
    ): ApiResponse<Boolean> {
        redisService.saveUserSession(userId, sessionData)
        return ApiResponse.success(true)
    }

    @GetMapping("/session/{userId}")
    fun getUserSession(@PathVariable userId: String): ApiResponse<Map<String, Any>?> {
        val session = redisService.getUserSession(userId)
        return ApiResponse.success(session)
    }

    @DeleteMapping("/session/{userId}")
    fun deleteUserSession(@PathVariable userId: String): ApiResponse<Boolean> {
        val result = redisService.deleteUserSession(userId)
        return ApiResponse.success(result)
    }

    // 상품 조회수 관리
    @PostMapping("/product/{productId}/view")
    fun incrementProductView(@PathVariable productId: String): ApiResponse<Long> {
        val count = redisService.incrementProductViewCount(productId)
        return ApiResponse.success(count)
    }

    @GetMapping("/product/{productId}/view")
    fun getProductViewCount(@PathVariable productId: String): ApiResponse<Long> {
        val count = redisService.getProductViewCount(productId)
        return ApiResponse.success(count)
    }

    // 장바구니 관리
    @PostMapping("/cart/{userId}")
    fun saveUserCart(
        @PathVariable userId: String,
        @RequestBody cartItems: List<Map<String, Any>>
    ): ApiResponse<Boolean> {
        redisService.saveUserCart(userId, cartItems)
        return ApiResponse.success(true)
    }

    @GetMapping("/cart/{userId}")
    fun getUserCart(@PathVariable userId: String): ApiResponse<List<Map<String, Any>>?> {
        val cart = redisService.getUserCart(userId)
        return ApiResponse.success(cart)
    }

    @DeleteMapping("/cart/{userId}")
    fun deleteUserCart(@PathVariable userId: String): ApiResponse<Boolean> {
        val result = redisService.deleteUserCart(userId)
        return ApiResponse.success(result)
    }

    // 검색 히스토리 관리
    @PostMapping("/search/{userId}")
    fun saveSearchHistory(
        @PathVariable userId: String,
        @RequestParam searchTerm: String
    ): ApiResponse<Boolean> {
        redisService.saveSearchHistory(userId, searchTerm)
        return ApiResponse.success(true)
    }

    @GetMapping("/search/{userId}")
    fun getSearchHistory(@PathVariable userId: String): ApiResponse<List<String>?> {
        val history = redisService.getSearchHistory(userId)
        return ApiResponse.success(history)
    }

    // 상품 좋아요 관리
    @PostMapping("/product/{productId}/like/{userId}")
    fun addProductLike(
        @PathVariable productId: String,
        @PathVariable userId: String
    ): ApiResponse<Boolean> {
        val result = redisService.addProductLike(userId, productId)
        return ApiResponse.success(result)
    }

    @DeleteMapping("/product/{productId}/like/{userId}")
    fun removeProductLike(
        @PathVariable productId: String,
        @PathVariable userId: String
    ): ApiResponse<Boolean> {
        val result = redisService.removeProductLike(userId, productId)
        return ApiResponse.success(result)
    }

    @GetMapping("/product/{productId}/like/{userId}")
    fun isProductLiked(
        @PathVariable productId: String,
        @PathVariable userId: String
    ): ApiResponse<Boolean> {
        val result = redisService.isProductLiked(userId, productId)
        return ApiResponse.success(result)
    }

    @GetMapping("/product/{productId}/like/count")
    fun getProductLikeCount(@PathVariable productId: String): ApiResponse<Long> {
        val count = redisService.getProductLikeCount(productId)
        return ApiResponse.success(count)
    }

    // 상품 캐시 관리
    @PostMapping("/cache/product/{productId}")
    fun cacheProductInfo(
        @PathVariable productId: String,
        @RequestBody productInfo: Map<String, Any>
    ): ApiResponse<Boolean> {
        redisService.cacheProductInfo(productId, productInfo)
        return ApiResponse.success(true)
    }

    @GetMapping("/cache/product/{productId}")
    fun getCachedProductInfo(@PathVariable productId: String): ApiResponse<Map<String, Any>?> {
        val productInfo = redisService.getCachedProductInfo(productId)
        return ApiResponse.success(productInfo)
    }

    @DeleteMapping("/cache/product/{productId}")
    fun deleteCachedProductInfo(@PathVariable productId: String): ApiResponse<Boolean> {
        val result = redisService.deleteCachedProductInfo(productId)
        return ApiResponse.success(result)
    }

    // 알림 관리
    @PostMapping("/notification/{userId}")
    fun saveNotification(
        @PathVariable userId: String,
        @RequestBody notification: Map<String, Any>
    ): ApiResponse<Boolean> {
        redisService.saveNotification(userId, notification)
        return ApiResponse.success(true)
    }

    @GetMapping("/notification/{userId}")
    fun getNotifications(
        @PathVariable userId: String,
        @RequestParam(required = false, defaultValue = "10") limit: Long
    ): ApiResponse<List<Map<String, Any>>?> {
        val notifications = redisService.getNotifications(userId, limit)
        return ApiResponse.success(notifications)
    }

    @PutMapping("/notification/{userId}/read/{index}")
    fun markNotificationAsRead(
        @PathVariable userId: String,
        @PathVariable index: Long
    ): ApiResponse<Boolean> {
        val result = redisService.markNotificationAsRead(userId, index)
        return ApiResponse.success(result)
    }
} 
