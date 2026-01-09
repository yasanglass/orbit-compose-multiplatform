plugins {
    kotlin("jvm") version "2.3.0" apply false
    kotlin("android") version "2.3.0" apply false
    kotlin("multiplatform") version "2.3.0" apply false
    kotlin("plugin.serialization") version "2.3.0" apply false
    kotlin("plugin.compose") version "2.3.0" apply false
    id("org.jetbrains.compose") version "1.9.3" apply false
    alias(libs.plugins.compose.hot.reload) apply false
    id("com.android.library") version "8.12.0" apply false
    id("com.vanniktech.maven.publish") version "0.35.0" apply false
}
