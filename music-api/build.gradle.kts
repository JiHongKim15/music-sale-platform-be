plugins {
    id("org.springframework.boot") version "3.3.1"
    id("io.spring.dependency-management")
    kotlin("plugin.allopen") version "1.9.25"
}

dependencies {
    implementation(project(":music-application"))
    implementation(project(":music-domain"))
    implementation(project(":music-infrastructure"))

    // Spring Boot
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-websocket")

    // Kotlin
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // Database
    runtimeOnly("com.mysql:mysql-connector-j")

    // JWT
    implementation("io.jsonwebtoken:jjwt-api:0.12.5")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.5")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.5")

    // Swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")

    // Dotenv
    implementation("io.github.cdimascio:dotenv-kotlin:6.4.1")

    // Test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
}

kotlin {
    jvmToolchain(21)
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

sourceSets["main"].java.srcDirs(
    "src/main/kotlin",
    "build/generated/source/kapt/main",
)

tasks.withType<org.springframework.boot.gradle.tasks.bundling.BootJar> {
    mainClass.set("com.music.sale.MusicSaleApplicationKt")
}

// 환경별 실행 태스크
tasks.register("local") {
    group = "application"
    description = "로컬 환경으로 애플리케이션 실행"
    dependsOn("bootRun")
    doFirst {
        tasks.bootRun {
            args("--spring.profiles.active=local")
        }
    }
}

tasks.register("dev") {
    group = "application"
    description = "개발 환경으로 애플리케이션 실행"
    dependsOn("bootRun")
    doFirst {
        tasks.bootRun {
            args("--spring.profiles.active=dev")
        }
    }
}

tasks.register("prod") {
    group = "application"
    description = "운영 환경으로 애플리케이션 실행"
    dependsOn("bootRun")
    doFirst {
        tasks.bootRun {
            args("--spring.profiles.active=prod")
        }
    }
}

tasks.register("testEnv") {
    group = "application"
    description = "테스트 환경으로 애플리케이션 실행"
    dependsOn("bootRun")
    doFirst {
        tasks.bootRun {
            args("--spring.profiles.active=test")
        }
    }
}
