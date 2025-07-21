pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "music-sale-platform-be"

include(
    "music-api",
    "music-application",
    "music-infrastructure",
)
