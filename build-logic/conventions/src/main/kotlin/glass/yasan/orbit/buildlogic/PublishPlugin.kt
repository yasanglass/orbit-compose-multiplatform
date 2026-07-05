package glass.yasan.orbit.buildlogic

import com.vanniktech.maven.publish.MavenPublishBaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.gradle.plugins.signing.Sign

class PublishPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        pluginManager.apply("com.vanniktech.maven.publish")

        extensions.configure<PublishingExtension> {
            repositories.maven {
                name = "LocalMaven"
                url = rootProject.layout.buildDirectory.dir("localMaven").get().asFile.toURI()
            }
        }

        // Sign only when a key is configured (CI); local publishing skips signing.
        tasks.withType<Sign>().configureEach {
            isRequired = providers.gradleProperty("signingInMemoryKey").isPresent
        }

        extensions.configure<MavenPublishBaseExtension> {
            publishToMavenCentral(
                automaticRelease = true,
                validateDeployment = false,
            )
            signAllPublications()

            pom {
                name.set("Orbit Compose Multiplatform")
                description.set("Compose Multiplatform port of Kiwi.com's Orbit design system.")
                inceptionYear.set("2026")
                url.set("https://github.com/yasanglass/orbit-compose-multiplatform/")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://github.com/yasanglass/orbit-compose-multiplatform/blob/main/license.md")
                        distribution.set("https://github.com/yasanglass/orbit-compose-multiplatform/blob/main/license.md")
                    }
                }
                developers {
                    developer {
                        id.set("yasanglass")
                        name.set("Yasan Glass")
                        url.set("https://github.com/yasanglass/")
                    }
                    developer {
                        id.set("contributors")
                        name.set("All Contributors")
                        url.set("https://github.com/yasanglass/orbit-compose-multiplatform/graphs/contributors")
                    }
                }
                scm {
                    url.set("https://github.com/yasanglass/orbit-compose-multiplatform/")
                    connection.set("scm:git:git://github.com/yasanglass/orbit-compose-multiplatform.git")
                    developerConnection.set("scm:git:ssh://git@github.com/yasanglass/orbit-compose-multiplatform.git")
                }
            }
        }
    }
}
