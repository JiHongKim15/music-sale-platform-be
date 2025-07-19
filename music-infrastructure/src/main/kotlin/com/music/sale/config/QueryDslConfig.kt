// Copyright (C) 2024 Your Name or Company
package com.music.sale.config

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class QueryDslConfig(
    private val entityManager: EntityManager,
) {
    @Bean
    open fun jpaQueryFactory(): JPAQueryFactory {
        return JPAQueryFactory(entityManager)
    }
}
