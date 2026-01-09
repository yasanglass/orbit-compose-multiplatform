plugins {
    id("kiwi.orbit.compose.buildlogic.library")
    id("kiwi.orbit.compose.buildlogic.publish")
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.hot.reload)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.icons)
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.coil)
            implementation(libs.stately)
        }
        androidMain.dependencies {
            implementation(libs.androidx.core)
            implementation(libs.compose.animationGraphics)
            implementation(libs.compose.toolingPreview)
            implementation(libs.compose.uiUtil)
        }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "kiwi.orbit.compose.ui.generated"
    generateResClass = always
}

dependencies {
    debugImplementation(libs.compose.tooling)
    debugImplementation(libs.androidx.activityCompose)
    debugImplementation(libs.androidx.customView)
    debugImplementation(libs.androidx.customViewPoolingContainer)
}
