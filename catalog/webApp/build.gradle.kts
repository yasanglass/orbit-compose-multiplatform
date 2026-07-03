import org.gradle.api.file.DuplicatesStrategy
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.tasks.IncrementalSyncTask

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.plugin.compose)
    alias(libs.plugins.compose.multiplatform)
}

kotlin {
    js {
        browser()
        binaries.executable()
        compilerOptions {
            sourceMap.set(false)
            sourceMapEmbedSources.unsetConvention()
        }
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(":catalog"))
            implementation(libs.compose.multiplatform.runtime)
            implementation(libs.compose.multiplatform.ui)
        }

        jsMain.dependencies {
            implementation(devNpm("tslib", "2.8.1"))
        }
    }
}

tasks.withType<IncrementalSyncTask>().configureEach {
    if (
        name.endsWith("TestDevelopmentExecutableCompileSync") ||
        name.endsWith("TestProductionExecutableCompileSync")
    ) {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }
}
