package kiwi.orbit.compose.buildlogic

import com.vanniktech.maven.publish.AndroidSingleVariantLibrary
import com.vanniktech.maven.publish.MavenPublishBaseExtension
import java.time.Year
import java.util.Properties
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.extra

class PublishPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        pluginManager.apply("com.vanniktech.maven.publish")

        val signingPropsFile = rootProject.file("release/signing.properties")
        if (signingPropsFile.exists()) {
            val localProperties = Properties()
            with(signingPropsFile.inputStream()) {
                localProperties.load(this)
            }
            localProperties.forEach { key, value ->
                if (key == "signing.secretKeyRingFile") {
                    project.extra.set(key as String, rootProject.file(value).absolutePath)
                } else {
                    project.extra.set(key as String, value)
                }
            }
        }

        @Suppress("UnstableApiUsage")
        extensions.configure<MavenPublishBaseExtension> {
            group = requireNotNull(project.findProperty("GROUP"))
            version = requireNotNull(project.findProperty("VERSION_NAME"))

            publishToMavenCentral()
            signAllPublications()
            configure(AndroidSingleVariantLibrary())
        }
    }
}
