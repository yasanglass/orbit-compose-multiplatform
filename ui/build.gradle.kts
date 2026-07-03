plugins {
    id("glass.yasan.orbit.buildlogic.library")
    id("glass.yasan.orbit.buildlogic.publish")
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.hot.reload)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":icons"))
            implementation(libs.compose.multiplatform.runtime)
            implementation(libs.compose.multiplatform.foundation)
            implementation(libs.compose.multiplatform.material3)
            implementation(libs.compose.multiplatform.ui)
            implementation(libs.compose.multiplatform.components.resources)
            implementation(libs.compose.multiplatform.uiToolingPreview)
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
    packageOfResClass = "glass.yasan.orbit.ui.generated"
    generateResClass = always
}

dependencies {
    "androidRuntimeClasspath"(libs.compose.tooling)
    "androidRuntimeClasspath"(libs.androidx.activityCompose)
    "androidRuntimeClasspath"(libs.androidx.customView)
    "androidRuntimeClasspath"(libs.androidx.customViewPoolingContainer)
}
