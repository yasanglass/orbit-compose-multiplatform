plugins {
    id("glass.yasan.orbit.buildlogic.library")
    id("glass.yasan.orbit.buildlogic.publish")
    alias(libs.plugins.compose.multiplatform)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
        }
        androidMain.dependencies {
            implementation(libs.androidx.core)
        }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "glass.yasan.orbit.icons.generated"
    generateResClass = always
}
