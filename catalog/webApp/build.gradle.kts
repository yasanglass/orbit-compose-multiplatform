plugins {
    kotlin("multiplatform")
    kotlin("plugin.compose")
    alias(libs.plugins.compose.multiplatform)
}

kotlin {
    js {
        browser()
        binaries.executable()
    }

    @OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.catalog.shared)
            implementation(libs.compose.ui)
        }
    }
}
