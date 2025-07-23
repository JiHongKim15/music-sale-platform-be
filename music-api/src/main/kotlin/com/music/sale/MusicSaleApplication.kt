// Copyright (C) 2024 Your Name or Company
package com.music.sale

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["com.music.sale"])
open class MusicSaleApplication

fun main(args: Array<String>) {
    runApplication<MusicSaleApplication>(*args)
}
