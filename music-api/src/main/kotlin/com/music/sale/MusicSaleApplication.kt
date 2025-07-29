// Copyright (C) 2024 Your Name or Company
package com.music.sale

import io.github.cdimascio.dotenv.Dotenv
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
open class MusicSaleApplication

fun main(args: Array<String>) {
    Dotenv.configure()
        .directory(System.getProperty("user.dir"))
        .filename(".env.local")
        .systemProperties()
        .load()

    runApplication<MusicSaleApplication>(*args)
}
