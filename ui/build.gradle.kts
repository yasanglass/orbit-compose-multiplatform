plugins {
    id("kiwi.orbit.compose.buildlogic.library")
    id("kiwi.orbit.compose.buildlogic.publish")
}

dependencies {
    implementation(platform(libs.compose.bom))

    implementation(projects.icons)

    implementation(libs.androidx.core)
    implementation(libs.coil)
    implementation(libs.compose.animationGraphics)
    implementation(libs.compose.runtime)
    implementation(libs.compose.foundation)
    implementation(libs.compose.materialRipple)
    implementation(libs.compose.material3)
    implementation(libs.compose.toolingPreview)
    implementation(libs.compose.uiUtil)
    implementation(libs.kotlin.stdlib)

    debugImplementation(libs.compose.tooling)
    debugImplementation(libs.androidx.activityCompose)
    debugImplementation(libs.androidx.customView)
    debugImplementation(libs.androidx.customViewPoolingContainer)
}
