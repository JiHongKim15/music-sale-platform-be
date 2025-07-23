package com.music.sale.domain.redis

import java.time.Duration

/**
 * Redis 리포지토리 인터페이스
 * 도메인 계층에서 Redis 작업을 정의
 */
interface RedisRepository {
    
    // 기본 작업
    fun set(key: String, value: Any, timeout: Duration? = null)
    fun get(key: String): Any?
    fun exists(key: String): Boolean
    fun delete(key: String): Boolean
    fun expire(key: String, timeout: Duration): Boolean
    fun getTtl(key: String): Duration?
    
    // Hash 작업
    fun hSet(key: String, hashKey: String, value: Any)
    fun hGet(key: String, hashKey: String): Any?
    fun hGetAll(key: String): Map<Any, Any>?
    fun hDelete(key: String, vararg hashKeys: String): Long
    
    // List 작업
    fun lPush(key: String, vararg values: Any): Long
    fun rPush(key: String, vararg values: Any): Long
    fun lPop(key: String): Any?
    fun rPop(key: String): Any?
    fun lRange(key: String, start: Long, end: Long): List<Any>?
    
    // Set 작업
    fun sAdd(key: String, vararg values: Any): Long
    fun sMembers(key: String): Set<Any>?
    fun sRemove(key: String, vararg values: Any): Long
    fun sIsMember(key: String, value: Any): Boolean
} 
