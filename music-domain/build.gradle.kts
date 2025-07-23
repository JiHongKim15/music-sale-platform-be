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
    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

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
