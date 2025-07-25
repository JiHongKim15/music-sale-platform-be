plugins {
    id("org.springframework.boot") version "3.3.1"
    id("io.spring.dependency-management")
    kotlin("plugin.allopen") version "1.9.25"
}

tasks.getByName("bootJar") {
    enabled = false
}

tasks.getByName("jar") {
    enabled = true
}

dependencies {
    implementation(project(":music-domain"))

    // Spring Core (비즈니스 로직에 필요한 것만)
    implementation("org.springframework:spring-context")
    implementation("org.springframework:spring-tx")

    // Spring Data JPA (Page 인터페이스 사용)
    implementation("org.springframework.data:spring-data-commons")

    // Spring Security (PasswordEncoder 사용)
    implementation("org.springframework.security:spring-security-crypto")

    // Spring Messaging (WebSocket 메시징)
    implementation("org.springframework:spring-messaging")

    // Validation
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // Jackson (DTO 직렬화/역직렬화)
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

    // JWT (인증 서비스에서 사용)
    implementation("org.springframework.security:spring-security-jwt:1.1.1.RELEASE")

    // Lombok
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
}

kotlin {
    jvmToolchain(21)
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

sourceSets["main"].java.srcDirs("src/main/kotlin")
