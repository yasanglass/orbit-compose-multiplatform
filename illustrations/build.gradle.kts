plugins {
    id("glass.yasan.orbit.buildlogic.library")
    id("glass.yasan.orbit.buildlogic.publish")
    alias(libs.plugins.compose.multiplatform)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.compose.multiplatform.runtime)
            implementation(libs.compose.multiplatform.ui)
            implementation(libs.compose.multiplatform.components.resources)
        }
        androidMain.dependencies {
            implementation(libs.androidx.core)
        }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "glass.yasan.orbit.illustrations.generated"
    generateResClass = always
}
