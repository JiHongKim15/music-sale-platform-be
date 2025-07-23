plugins {
    id("org.springframework.boot") version "3.3.1"
    id("io.spring.dependency-management")
    kotlin("plugin.allopen") version "1.9.25"
}

dependencies {
    implementation(project(":music-application"))
    implementation(project(":music-infrastructure"))

    // 실행 시점에 필요한 DB 드라이버를 명시적으로 추가
    runtimeOnly("com.mysql:mysql-connector-j")
    implementation(project(":music-domain"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-websocket")
    implementation("io.jsonwebtoken:jjwt-api:0.12.5")
    implementation("io.jsonwebtoken:jjwt-impl:0.12.5")
    implementation("io.jsonwebtoken:jjwt-jackson:0.12.5")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    
    // Redis 설정
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.session:spring-session-data-redis")

    annotationProcessor("org.projectlombok:lombok:1.18.30")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
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
