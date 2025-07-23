package com.music.sale.application.redis

import com.music.sale.domain.redis.RedisRepository
import org.springframework.stereotype.Service
import java.time.Duration

/**
 * Redis 서비스
 * 애플리케이션 계층에서 Redis 관련 비즈니스 로직을 처리
 */
@Service
class RedisService(
    private val redisRepository: RedisRepository
) {

    /**
     * 사용자 세션 저장
     */
    fun saveUserSession(userId: String, sessionData: Map<String, Any>, timeout: Duration = Duration.ofHours(1)) {
        val key = "session:user:$userId"
        redisRepository.set(key, sessionData, timeout)
    }

    /**
     * 사용자 세션 조회
     */
    fun getUserSession(userId: String): Map<String, Any>? {
        val key = "session:user:$userId"
        return redisRepository.get(key) as? Map<String, Any>
    }

    /**
     * 사용자 세션 삭제
     */
    fun deleteUserSession(userId: String): Boolean {
        val key = "session:user:$userId"
        return redisRepository.delete(key)
    }

    /**
     * 상품 조회수 증가
     */
    fun incrementProductViewCount(productId: String): Long {
        val key = "product:view:$productId"
        val currentCount = redisRepository.get(key) as? Long ?: 0L
        val newCount = currentCount + 1
        redisRepository.set(key, newCount, Duration.ofDays(30))
        return newCount
    }

    /**
     * 상품 조회수 조회
     */
    fun getProductViewCount(productId: String): Long {
        val key = "product:view:$productId"
        return redisRepository.get(key) as? Long ?: 0L
    }

    /**
     * 사용자 장바구니 저장
     */
    fun saveUserCart(userId: String, cartItems: List<Map<String, Any>>) {
        val key = "cart:user:$userId"
        redisRepository.set(key, cartItems, Duration.ofDays(7))
    }

    /**
     * 사용자 장바구니 조회
     */
    fun getUserCart(userId: String): List<Map<String, Any>>? {
        val key = "cart:user:$userId"
        return redisRepository.get(key) as? List<Map<String, Any>>
    }

    /**
     * 사용자 장바구니 삭제
     */
    fun deleteUserCart(userId: String): Boolean {
        val key = "cart:user:$userId"
        return redisRepository.delete(key)
    }

    /**
     * 상품 검색 히스토리 저장
     */
    fun saveSearchHistory(userId: String, searchTerm: String) {
        val key = "search:history:$userId"
        redisRepository.lPush(key, searchTerm)
        // 최대 10개만 유지
        redisRepository.lRange(key, 0, 9)?.let { history ->
            if (history.size > 10) {
                redisRepository.delete(key)
                redisRepository.lPush(key, *history.take(10).toTypedArray())
            }
        }
        redisRepository.expire(key, Duration.ofDays(30))
    }

    /**
     * 상품 검색 히스토리 조회
     */
    fun getSearchHistory(userId: String): List<String>? {
        val key = "search:history:$userId"
        return redisRepository.lRange(key, 0, -1)?.map { it.toString() }
    }

    /**
     * 상품 좋아요 추가
     */
    fun addProductLike(userId: String, productId: String): Boolean {
        val key = "product:likes:$productId"
        val result = redisRepository.sAdd(key, userId)
        redisRepository.expire(key, Duration.ofDays(365))
        return result > 0
    }

    /**
     * 상품 좋아요 제거
     */
    fun removeProductLike(userId: String, productId: String): Boolean {
        val key = "product:likes:$productId"
        val result = redisRepository.sRemove(key, userId)
        return result > 0
    }

    /**
     * 상품 좋아요 여부 확인
     */
    fun isProductLiked(userId: String, productId: String): Boolean {
        val key = "product:likes:$productId"
        return redisRepository.sIsMember(key, userId)
    }

    /**
     * 상품 좋아요 수 조회
     */
    fun getProductLikeCount(productId: String): Long {
        val key = "product:likes:$productId"
        return redisRepository.sMembers(key)?.size?.toLong() ?: 0L
    }

    /**
     * 캐시된 상품 정보 저장
     */
    fun cacheProductInfo(productId: String, productInfo: Map<String, Any>) {
        val key = "cache:product:$productId"
        redisRepository.set(key, productInfo, Duration.ofHours(1))
    }

    /**
     * 캐시된 상품 정보 조회
     */
    fun getCachedProductInfo(productId: String): Map<String, Any>? {
        val key = "cache:product:$productId"
        return redisRepository.get(key) as? Map<String, Any>
    }

    /**
     * 캐시된 상품 정보 삭제
     */
    fun deleteCachedProductInfo(productId: String): Boolean {
        val key = "cache:product:$productId"
        return redisRepository.delete(key)
    }

    /**
     * 실시간 알림 저장
     */
    fun saveNotification(userId: String, notification: Map<String, Any>) {
        val key = "notification:$userId"
        redisRepository.lPush(key, notification)
        // 최대 50개만 유지
        redisRepository.lRange(key, 0, 49)?.let { notifications ->
            if (notifications.size > 50) {
                redisRepository.delete(key)
                redisRepository.lPush(key, *notifications.take(50).toTypedArray())
            }
        }
        redisRepository.expire(key, Duration.ofDays(7))
    }

    /**
     * 실시간 알림 조회
     */
    fun getNotifications(userId: String, limit: Long = 10): List<Map<String, Any>>? {
        val key = "notification:$userId"
        return redisRepository.lRange(key, 0, limit - 1)?.map { it as Map<String, Any> }
    }

    /**
     * 알림 읽음 처리
     */
    fun markNotificationAsRead(userId: String, notificationIndex: Long): Boolean {
        val key = "notification:$userId"
        val notifications = redisRepository.lRange(key, 0, -1)?.toMutableList()
        if (notifications != null && notificationIndex < notifications.size) {
            val notification = notifications[notificationIndex.toInt()] as MutableMap<String, Any>
            notification["read"] = true
            redisRepository.delete(key)
            redisRepository.lPush(key, *notifications.toTypedArray())
            return true
        }
        return false
    }
} 
