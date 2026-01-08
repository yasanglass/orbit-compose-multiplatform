plugins {
    `kotlin-dsl`
}

group = "kiwi.orbit.compose.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.agp)
    implementation(libs.kotlin.gradle)
    implementation(libs.vannitktech.mavenPublish.gradle)

    // workaround for https://github.com/gradle/gradle/issues/15383
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

gradlePlugin {
    plugins {
        register("LibraryPlugin") {
            id = "kiwi.orbit.compose.buildlogic.library"
            implementationClass = "kiwi.orbit.compose.buildlogic.LibraryPlugin"
        }
        register("PublishPlugin") {
            id = "kiwi.orbit.compose.buildlogic.publish"
            implementationClass = "kiwi.orbit.compose.buildlogic.PublishPlugin"
        }
    }
}
