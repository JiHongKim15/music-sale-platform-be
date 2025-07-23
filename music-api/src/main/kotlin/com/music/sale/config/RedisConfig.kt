package com.music.sale.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600) // 1시간 세션 유지
class RedisConfig {

    @Bean
    fun redisTemplate(connectionFactory: RedisConnectionFactory): RedisTemplate<String, Any> {
        val template = RedisTemplate<String, Any>()
        template.connectionFactory = connectionFactory
        
        // Key 직렬화 설정
        template.keySerializer = StringRedisSerializer()
        template.hashKeySerializer = StringRedisSerializer()
        
        // Value 직렬화 설정
        template.valueSerializer = GenericJackson2JsonRedisSerializer()
        template.hashValueSerializer = GenericJackson2JsonRedisSerializer()
        
        template.afterPropertiesSet()
        return template
    }
} 
