@file:Suppress("UnstableApiUsage")

import com.android.build.gradle.internal.api.ApkVariantOutputImpl
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.konan.properties.loadProperties

plugins {
    kotlin("multiplatform")
    kotlin("plugin.compose")
    kotlin("plugin.serialization")
    id("com.android.application")
    alias(libs.plugins.compose.multiplatform)
}

kotlin {
    androidTarget()

    jvm("desktop")

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "catalog"
            isStatic = true
        }
    }

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
            implementation(projects.ui)
            implementation(projects.icons)
            implementation(projects.illustrations)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            implementation(libs.compose.navigation.multiplatform)
            implementation(libs.kotlin.datetime)
            implementation(libs.kotlin.serialization.core)
            implementation(libs.coil)
        }

        androidMain.dependencies {
            implementation(libs.androidx.core)
            implementation(libs.androidx.appCompat)
            implementation(libs.androidx.activityCompose)
        }

        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }
    }
}

android {
    namespace = "kiwi.orbit.compose.catalog"

    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "kiwi.orbit.compose.catalog"
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
            val propertiesFile = File("$projectDir/../release/signing.properties")
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

    applicationVariants.configureEach {
        outputs.configureEach {
            if (buildType.name == "ci") {
                this as ApkVariantOutputImpl
                versionNameOverride = "${versionName}-${System.getenv("GITHUB_REF_NAME")}"
                versionCodeOverride = System.getenv("GITHUB_RUN_NUMBER")?.toInt() ?: 1
            }
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
        renderScript = false
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

compose.desktop {
    application {
        mainClass = "kiwi.orbit.compose.catalog.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Orbit Catalog"
            packageVersion = "1.0.0"
        }
    }
}
