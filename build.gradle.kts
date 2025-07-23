plugins {
    kotlin("jvm") version "1.9.25"
    id("com.diffplug.spotless") version "6.25.0"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.music.sale"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "io.spring.dependency-management")

    repositories {
        mavenCentral()
    }

    dependencies {
        // Common dependencies for all subprojects if any
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

// 환경별 실행 태스크 (간단한 명령어)
tasks.register("local") {
    group = "application"
    description = "로컬 환경으로 애플리케이션 실행"
    dependsOn(":music-api:local")
}

tasks.register("dev") {
    group = "application"
    description = "개발 환경으로 애플리케이션 실행"
    dependsOn(":music-api:dev")
}

tasks.register("prod") {
    group = "application"
    description = "운영 환경으로 애플리케이션 실행"
    dependsOn(":music-api:prod")
}

tasks.register("testEnv") {
    group = "application"
    description = "테스트 환경으로 애플리케이션 실행"
    dependsOn(":music-api:testEnv")
}

spotless {
    kotlin {
        target("**/*.kt", "**/*.kts")
        targetExclude("**/bin/**")
        ktlint()
    }
}
