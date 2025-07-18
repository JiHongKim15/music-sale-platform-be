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
        implementation(platform("org.springframework.boot:spring-boot-dependencies:3.3.1"))
        // Common dependencies for all subprojects if any
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

spotless {
    kotlin {
        target("**/*.kt", "**/*.kts")
        targetExclude("**/bin/**")
        ktlint()
    }
}
