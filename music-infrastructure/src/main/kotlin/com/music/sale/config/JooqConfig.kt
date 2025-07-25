package com.music.sale.config

import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
open class JooqConfig(
    private val dataSource: DataSource,
) {
    @Bean
    open fun dslContext(): DSLContext {
        return DSL.using(dataSource, SQLDialect.MYSQL)
    }
} 
