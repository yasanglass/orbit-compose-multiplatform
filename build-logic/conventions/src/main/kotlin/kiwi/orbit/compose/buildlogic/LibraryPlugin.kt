package kiwi.orbit.compose.buildlogic

import com.android.build.api.dsl.LibraryExtension
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Action
import org.gradle.api.JavaVersion
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.jvm.toolchain.JvmVendorSpec
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.getting
import org.gradle.kotlin.dsl.the
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinBaseExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

class LibraryPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        val libs = the<LibrariesForLibs>()

        pluginManager.apply("org.jetbrains.kotlin.multiplatform")
        pluginManager.apply("org.jetbrains.kotlin.plugin.compose")
        pluginManager.apply("com.android.library")

        configurations.all {
            resolutionStrategy {
                force(libs.compose.material3)
            }
        }

        extensions.getByType<KotlinBaseExtension>().apply {
            jvmToolchain {
                languageVersion.set(JavaLanguageVersion.of(17))
                vendor.set(JvmVendorSpec.AZUL)
            }
        }

        extensions.configure<KotlinMultiplatformExtension> {
            explicitApi()
            androidTarget {
                compilations.all {
                    compileTaskProvider.configure {
                        compilerOptions {
                            jvmTarget.set(JvmTarget.JVM_1_8)
                            allWarningsAsErrors.set(true)
                            freeCompilerArgs.add("-Xskip-prerelease-check")
                        }
                    }
                }
            }
            sourceSets {
                val androidMain by getting {}
            }
        }

        extensions.configure<LibraryExtension> {
            namespace = "kiwi.orbit.compose.${project.name}"

            compileSdk = libs.versions.compileSdk.get().toInt()

            defaultConfig {
                minSdk = libs.versions.minSdk.get().toInt()
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }

            buildFeatures {
                compose = true
                buildConfig = false
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }

            lint {
                disable.add("ObsoleteLintCustomCheck")
                disable.add("UnusedResources")
                disable.add("VectorPath")
                disable.add("UnusedAttribute")
                disable.add("ComposeUnstableCollections") // not suitable requirement for library, for now
                disable.add("ComposeCompositionLocalUsage") // theming uses this a lot
                abortOnError = true
                warningsAsErrors = true
            }

            packaging {
                resources.excludes.add("META-INF/AL2.0")
                resources.excludes.add("META-INF/LGPL2.1")
            }

            @Suppress("UnstableApiUsage")
            testOptions {
                unitTests {
                    isIncludeAndroidResources = true
                }
            }
        }

    }

    private fun KotlinMultiplatformExtension.sourceSets(configure: Action<NamedDomainObjectContainer<KotlinSourceSet>>): Unit =
        (this as org.gradle.api.plugins.ExtensionAware).extensions.configure("sourceSets", configure)
}
