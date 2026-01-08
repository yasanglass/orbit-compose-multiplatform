plugins {
    id("kiwi.orbit.compose.buildlogic.library")
    id("kiwi.orbit.compose.buildlogic.publish")
}

dependencies {
    implementation(platform(libs.compose.bom))

    implementation(libs.kotlin.stdlib)
    implementation(libs.androidx.core)
    implementation(libs.compose.runtime)
    implementation(libs.compose.ui)
}
