package com.music.sale.service

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.time.Duration

@Service
class RedisService(
    private val redisTemplate: RedisTemplate<String, Any>
) {

    /**
     * 키-값 저장
     */
    fun set(key: String, value: Any, timeout: Duration? = null) {
        if (timeout != null) {
            redisTemplate.opsForValue().set(key, value, timeout)
        } else {
            redisTemplate.opsForValue().set(key, value)
        }
    }

    /**
     * 값 조회
     */
    fun get(key: String): Any? {
        return redisTemplate.opsForValue().get(key)
    }

    /**
     * 키 존재 여부 확인
     */
    fun exists(key: String): Boolean {
        return redisTemplate.hasKey(key) ?: false
    }

    /**
     * 키 삭제
     */
    fun delete(key: String): Boolean {
        return redisTemplate.delete(key) ?: false
    }

    /**
     * 만료 시간 설정
     */
    fun expire(key: String, timeout: Duration): Boolean {
        return redisTemplate.expire(key, timeout) ?: false
    }

    /**
     * TTL 조회
     */
    fun getTtl(key: String): Duration? {
        return redisTemplate.getExpire(key)
    }

    /**
     * Hash 저장
     */
    fun hSet(key: String, hashKey: String, value: Any) {
        redisTemplate.opsForHash().put(key, hashKey, value)
    }

    /**
     * Hash 조회
     */
    fun hGet(key: String, hashKey: String): Any? {
        return redisTemplate.opsForHash().get(key, hashKey)
    }

    /**
     * Hash 전체 조회
     */
    fun hGetAll(key: String): Map<Any, Any>? {
        return redisTemplate.opsForHash().entries(key)
    }

    /**
     * Hash 삭제
     */
    fun hDelete(key: String, vararg hashKeys: String): Long {
        return redisTemplate.opsForHash().delete(key, *hashKeys) ?: 0
    }

    /**
     * List 왼쪽 삽입
     */
    fun lPush(key: String, vararg values: Any): Long {
        return redisTemplate.opsForList().leftPushAll(key, *values) ?: 0
    }

    /**
     * List 오른쪽 삽입
     */
    fun rPush(key: String, vararg values: Any): Long {
        return redisTemplate.opsForList().rightPushAll(key, *values) ?: 0
    }

    /**
     * List 왼쪽 팝
     */
    fun lPop(key: String): Any? {
        return redisTemplate.opsForList().leftPop(key)
    }

    /**
     * List 오른쪽 팝
     */
    fun rPop(key: String): Any? {
        return redisTemplate.opsForList().rightPop(key)
    }

    /**
     * List 범위 조회
     */
    fun lRange(key: String, start: Long, end: Long): List<Any>? {
        return redisTemplate.opsForList().range(key, start, end)
    }

    /**
     * Set 추가
     */
    fun sAdd(key: String, vararg values: Any): Long {
        return redisTemplate.opsForSet().add(key, *values) ?: 0
    }

    /**
     * Set 조회
     */
    fun sMembers(key: String): Set<Any>? {
        return redisTemplate.opsForSet().members(key)
    }

    /**
     * Set 삭제
     */
    fun sRemove(key: String, vararg values: Any): Long {
        return redisTemplate.opsForSet().remove(key, *values) ?: 0
    }

    /**
     * Set 포함 여부 확인
     */
    fun sIsMember(key: String, value: Any): Boolean {
        return redisTemplate.opsForSet().isMember(key, value) ?: false
    }
} 
