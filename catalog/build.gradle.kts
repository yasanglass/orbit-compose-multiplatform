@file:Suppress("UnstableApiUsage")

import glass.yasan.orbit.buildlogic.android
import glass.yasan.orbit.buildlogic.sourceSets
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.plugin.compose)
    alias(libs.plugins.kotlin.plugin.serialization)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.compose.multiplatform)
    id("glass.yasan.orbit.buildlogic.library") apply false
}

kotlin {
    android {
        namespace = "glass.yasan.orbit.catalog.shared"
        compileSdk = libs.versions.compileSdk.get().toInt()
        minSdk = libs.versions.minSdk.get().toInt()

        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }

        lint {
            abortOnError = true
            warningsAsErrors = true
        }
    }

    jvm("desktop")

    listOf(
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "catalog"
            binaryOption("bundleId", "glass.yasan.orbit.catalog")
            isStatic = true
        }
    }

    js {
        browser()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
    }

    applyDefaultHierarchyTemplate()

    sourceSets {
        commonMain.dependencies {
            implementation(project(":ui"))
            implementation(project(":icons"))
            implementation(project(":illustrations"))

            implementation(libs.compose.runtime)
            implementation(libs.compose.runtimeSaveable)
            implementation(libs.compose.multiplatform.foundation)
            implementation(libs.compose.multiplatform.material3)
            implementation(libs.compose.multiplatform.materialIconsExtended)
            implementation(libs.compose.multiplatform.components.resources)
            implementation(libs.compose.multiplatform.uiToolingPreview)

            implementation(libs.compose.navigation.multiplatform)
            implementation(libs.kotlin.datetime)
            implementation(libs.kotlin.serialization.core)
            implementation(libs.coil)
        }
    }
}
