@file:Suppress("UnstableApiUsage")

import com.android.build.gradle.internal.api.ApkVariantOutputImpl
import org.jetbrains.kotlin.konan.properties.loadProperties

plugins {
    id("com.android.application")
    kotlin("plugin.compose")
    alias(libs.plugins.compose.multiplatform)
}

android {
    namespace = "glass.yasan.orbit.catalog"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "glass.yasan.orbit.catalog"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionName = project.findProperty("VERSION_NAME").toString()

        val bits = versionName!!.split('.').map { it.toInt() }
        check(bits.size == 3)
        versionCode = bits[0] * 1_00_00 + bits[1] * 1_00 + bits[2]

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            val propertiesFile = File("$projectDir/../../release/signing.properties")
            if (!propertiesFile.exists()) return@create

            val properties = loadProperties(propertiesFile.absolutePath)
            storeFile = properties.getProperty("store.path")?.let { file(it) }
            storePassword = properties.getProperty("store.password")
            keyAlias = properties.getProperty("key.alias")
            keyPassword = properties.getProperty("key.password")
        }
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
            isPseudoLocalesEnabled = true
        }
        create("ci") {
            matchingFallbacks.add("release")
            applicationIdSuffix = ".ci"
            isMinifyEnabled = true
            isShrinkResources = true
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
        release {
            isPseudoLocalesEnabled = true
            isMinifyEnabled = true
            isShrinkResources = true
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        compose = true
        buildConfig = false
        aidl = false
        resValues = false
        shaders = false
    }

    experimentalProperties["android.experimental.r8.dex-startup-optimization"] = true

    lint {
        disable.add("ObsoleteLintCustomCheck")
        disable.add("GradleDependency")
        baseline = file("lint-baseline.xml")
        abortOnError = true
        warningsAsErrors = true
    }
}

androidComponents {
    onVariants(selector().all()) { variant ->
        if (variant.buildType == "ci") {
            val baseVersionName = project.findProperty("VERSION_NAME")?.toString() ?: "1.0.0"
            variant.outputs.forEach { output ->
                output.versionName.set("${baseVersionName}-${System.getenv("GITHUB_REF_NAME")}")
                output.versionCode.set(System.getenv("GITHUB_RUN_NUMBER")?.toInt() ?: 1)
            }
        }
    }
}

dependencies {
    implementation(projects.catalog.shared)
    implementation(libs.androidx.core)
    implementation(libs.androidx.appCompat)
    implementation(libs.androidx.activityCompose)
}
