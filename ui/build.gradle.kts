plugins {
    id("glass.yasan.orbit.buildlogic.library")
    id("glass.yasan.orbit.buildlogic.publish")
    alias(libs.plugins.compose.multiplatform)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.icons)
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.toolingPreview)
            implementation(libs.coil)
            implementation(libs.stately)
        }
        androidMain.dependencies {
            implementation(libs.androidx.core)
            implementation(libs.compose.animationGraphics)
            implementation(libs.compose.toolingPreview)
            implementation(libs.compose.uiUtil)
            implementation(libs.compose.tooling)
            implementation(libs.androidx.activityCompose)
            implementation(libs.androidx.customView)
            implementation(libs.androidx.customViewPoolingContainer)
        }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "glass.yasan.orbit.ui.generated"
    generateResClass = always
}
