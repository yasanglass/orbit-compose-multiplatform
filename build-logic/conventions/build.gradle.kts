plugins {
    `kotlin-dsl`
}

group = "glass.yasan.orbit.buildlogic"

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
            id = "glass.yasan.orbit.buildlogic.library"
            implementationClass = "glass.yasan.orbit.buildlogic.LibraryPlugin"
        }
        register("PublishPlugin") {
            id = "glass.yasan.orbit.buildlogic.publish"
            implementationClass = "glass.yasan.orbit.buildlogic.PublishPlugin"
        }
    }
}
