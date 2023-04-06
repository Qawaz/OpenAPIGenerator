pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
    }
    plugins {
        kotlin("jvm").version("1.8.0").apply(false)
        kotlin("multiplatform").version("1.8.0").apply(false)
    }
}

rootProject.name = "OpenAPIGenerator"

include("json-overlay")
include("parser")
include("validator")