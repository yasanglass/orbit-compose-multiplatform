@file:Suppress("UnstableApiUsage")

rootProject.name = "orbit-compose-multiplatform"

includeBuild("./build-logic")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    resolutionStrategy {
        eachPlugin {
            when (requested.id.name) {
                "com.android.library" -> useModule("com.android.tools.build:gradle")
            }
        }
    }
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

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

include(":catalog")
include(":catalog:semantics")
include(":icons")
include(":illustrations")
include(":ui")
