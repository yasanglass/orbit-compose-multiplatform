import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    kotlin("plugin.compose")
    alias(libs.plugins.compose.multiplatform)
}

dependencies {
    implementation(projects.catalog.shared)
    implementation(compose.desktop.currentOs)
}

compose.desktop {
    application {
        mainClass = "glass.yasan.orbit.catalog.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Orbit Catalog"
            packageVersion = "1.0.0"
        }
    }
}
