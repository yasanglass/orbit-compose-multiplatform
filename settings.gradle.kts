@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }
    buildscript {
        repositories {
            google()
        }
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "orbit-compose-multiplatform"

includeBuild("./build-logic")

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

include(":catalog")
include(":catalog:androidApp")
include(":catalog:desktopApp")
include(":catalog:webApp")
include(":icons")
include(":illustrations")
include(":ui")
