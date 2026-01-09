package glass.yasan.orbit.buildlogic

import com.vanniktech.maven.publish.MavenPublishBaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class PublishPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        pluginManager.apply("com.vanniktech.maven.publish")

        extensions.configure<MavenPublishBaseExtension> {
            publishToMavenCentral(
                automaticRelease = true,
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
