plugins {
    id("glass.yasan.orbit.buildlogic.library")
    id("glass.yasan.orbit.buildlogic.publish")
    alias(libs.plugins.compose.multiplatform)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.ui)
            implementation(compose.components.resources)
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
