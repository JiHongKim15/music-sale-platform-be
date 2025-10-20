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
    try {
        Dotenv.configure()
            .directory(System.getProperty("user.dir"))
            .filename(".env.local")
            .systemProperties()
            .load()
    } catch (e: Exception) {
        // .env.local 파일이 없어도 실행 가능하도록 예외 처리
        println("Warning: .env.local file not found, using default configuration")
    }

    runApplication<MusicSaleApplication>(*args)
}
