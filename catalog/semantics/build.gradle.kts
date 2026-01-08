import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("android")
    id("com.android.library")
}

android {
    namespace = "kiwi.orbit.compose.catalog.semantics"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    lint {
        abortOnError = true
        warningsAsErrors = true
    }

}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_1_8)
    }
}
