plugins {
    kotlin("jvm") version "2.4.0" apply false
    kotlin("android") version "2.4.0" apply false
    kotlin("multiplatform") version "2.4.0" apply false
    kotlin("plugin.serialization") version "2.4.0" apply false
    kotlin("plugin.compose") version "2.4.0" apply false
    id("org.jetbrains.compose") version "1.11.1" apply false
    id("com.android.library") version "9.2.0" apply false
    id("com.android.application") version "9.2.0" apply false
    alias(libs.plugins.android.kotlin.multiplatform.library) apply false
    id("com.vanniktech.maven.publish") version "0.35.0" apply false
}

