package com.music.sale.persistence.redis

import com.music.sale.domain.redis.RedisRepository
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository
import java.time.Duration

@Repository
class RedisRepositoryImpl(
    private val redisTemplate: RedisTemplate<String, Any>
) : RedisRepository {

    override fun set(key: String, value: Any, timeout: Duration?) {
        if (timeout != null) {
            redisTemplate.opsForValue().set(key, value, timeout)
        } else {
            redisTemplate.opsForValue().set(key, value)
        }
    }

    override fun get(key: String): Any? {
        return redisTemplate.opsForValue().get(key)
    }

    override fun exists(key: String): Boolean {
        return redisTemplate.hasKey(key) ?: false
    }

    override fun delete(key: String): Boolean {
        return redisTemplate.delete(key) ?: false
    }

    override fun expire(key: String, timeout: Duration): Boolean {
        return redisTemplate.expire(key, timeout) ?: false
    }

    override fun getTtl(key: String): Duration? {
        return redisTemplate.getExpire(key)
    }

    override fun hSet(key: String, hashKey: String, value: Any) {
        redisTemplate.opsForHash().put(key, hashKey, value)
    }

    override fun hGet(key: String, hashKey: String): Any? {
        return redisTemplate.opsForHash().get(key, hashKey)
    }

    override fun hGetAll(key: String): Map<Any, Any>? {
        return redisTemplate.opsForHash().entries(key)
    }

    override fun hDelete(key: String, vararg hashKeys: String): Long {
        return redisTemplate.opsForHash().delete(key, *hashKeys) ?: 0
    }

    override fun lPush(key: String, vararg values: Any): Long {
        return redisTemplate.opsForList().leftPushAll(key, *values) ?: 0
    }

    override fun rPush(key: String, vararg values: Any): Long {
        return redisTemplate.opsForList().rightPushAll(key, *values) ?: 0
    }

    override fun lPop(key: String): Any? {
        return redisTemplate.opsForList().leftPop(key)
    }

    override fun rPop(key: String): Any? {
        return redisTemplate.opsForList().rightPop(key)
    }

    override fun lRange(key: String, start: Long, end: Long): List<Any>? {
        return redisTemplate.opsForList().range(key, start, end)
    }

    override fun sAdd(key: String, vararg values: Any): Long {
        return redisTemplate.opsForSet().add(key, *values) ?: 0
    }

    override fun sMembers(key: String): Set<Any>? {
        return redisTemplate.opsForSet().members(key)
    }

    override fun sRemove(key: String, vararg values: Any): Long {
        return redisTemplate.opsForSet().remove(key, *values) ?: 0
    }

    override fun sIsMember(key: String, value: Any): Boolean {
        return redisTemplate.opsForSet().isMember(key, value) ?: false
    }
} 
