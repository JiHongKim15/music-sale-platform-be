package com.music.sale.web.redis

import com.music.sale.common.ApiResponse
import com.music.sale.application.redis.RedisService
import org.springframework.web.bind.annotation.*
import java.time.Duration

@RestController
@RequestMapping("/api/redis")
class RedisTestController(
    private val redisService: RedisService
) {

    @PostMapping("/set")
    fun setValue(
        @RequestParam key: String,
        @RequestParam value: String,
        @RequestParam(required = false) timeout: Long? = null
    ): ApiResponse<Boolean> {
        val duration = timeout?.let { Duration.ofSeconds(it) }
        redisService.set(key, value, duration)
        return ApiResponse.success(true)
    }

    @GetMapping("/get/{key}")
    fun getValue(@PathVariable key: String): ApiResponse<Any?> {
        val value = redisService.get(key)
        return ApiResponse.success(value)
    }

    @DeleteMapping("/delete/{key}")
    fun deleteValue(@PathVariable key: String): ApiResponse<Boolean> {
        val result = redisService.delete(key)
        return ApiResponse.success(result)
    }

    @GetMapping("/exists/{key}")
    fun existsKey(@PathVariable key: String): ApiResponse<Boolean> {
        val exists = redisService.exists(key)
        return ApiResponse.success(exists)
    }

    @PostMapping("/expire/{key}")
    fun expireKey(
        @PathVariable key: String,
        @RequestParam timeout: Long
    ): ApiResponse<Boolean> {
        val result = redisService.expire(key, Duration.ofSeconds(timeout))
        return ApiResponse.success(result)
    }

    @GetMapping("/ttl/{key}")
    fun getTtl(@PathVariable key: String): ApiResponse<Long?> {
        val ttl = redisService.getTtl(key)
        return ApiResponse.success(ttl?.seconds)
    }

    @PostMapping("/hash/set")
    fun setHash(
        @RequestParam key: String,
        @RequestParam hashKey: String,
        @RequestParam value: String
    ): ApiResponse<Unit> {
        redisService.hSet(key, hashKey, value)
        return ApiResponse.success(Unit)
    }

    @GetMapping("/hash/get")
    fun getHash(
        @RequestParam key: String,
        @RequestParam hashKey: String
    ): ApiResponse<Any?> {
        val value = redisService.hGet(key, hashKey)
        return ApiResponse.success(value)
    }

    @GetMapping("/hash/getAll/{key}")
    fun getAllHash(@PathVariable key: String): ApiResponse<Map<Any, Any>?> {
        val values = redisService.hGetAll(key)
        return ApiResponse.success(values)
    }

    @PostMapping("/list/push")
    fun pushToList(
        @RequestParam key: String,
        @RequestParam value: String,
        @RequestParam(required = false, defaultValue = "right") direction: String
    ): ApiResponse<Long> {
        val result = if (direction == "left") {
            redisService.lPush(key, value)
        } else {
            redisService.rPush(key, value)
        }
        return ApiResponse.success(result)
    }

    @GetMapping("/list/pop")
    fun popFromList(
        @RequestParam key: String,
        @RequestParam(required = false, defaultValue = "right") direction: String
    ): ApiResponse<Any?> {
        val result = if (direction == "left") {
            redisService.lPop(key)
        } else {
            redisService.rPop(key)
        }
        return ApiResponse.success(result)
    }

    @GetMapping("/list/range/{key}")
    fun getListRange(
        @PathVariable key: String,
        @RequestParam start: Long = 0,
        @RequestParam end: Long = -1
    ): ApiResponse<List<Any>?> {
        val values = redisService.lRange(key, start, end)
        return ApiResponse.success(values)
    }

    @PostMapping("/set/add")
    fun addToSet(
        @RequestParam key: String,
        @RequestParam values: List<String>
    ): ApiResponse<Long> {
        val result = redisService.sAdd(key, *values.toTypedArray())
        return ApiResponse.success(result)
    }

    @GetMapping("/set/members/{key}")
    fun getSetMembers(@PathVariable key: String): ApiResponse<Set<Any>?> {
        val members = redisService.sMembers(key)
        return ApiResponse.success(members)
    }

    @PostMapping("/set/remove")
    fun removeFromSet(
        @RequestParam key: String,
        @RequestParam values: List<String>
    ): ApiResponse<Long> {
        val result = redisService.sRemove(key, *values.toTypedArray())
        return ApiResponse.success(result)
    }

    @GetMapping("/set/contains")
    fun isSetMember(
        @RequestParam key: String,
        @RequestParam value: String
    ): ApiResponse<Boolean> {
        val isMember = redisService.sIsMember(key, value)
        return ApiResponse.success(isMember)
    }
} 
