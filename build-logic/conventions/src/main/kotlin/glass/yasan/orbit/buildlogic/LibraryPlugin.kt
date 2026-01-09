package glass.yasan.orbit.buildlogic

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
import org.gradle.kotlin.dsl.creating
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.getting
import org.gradle.kotlin.dsl.the
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
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

            // Android
            androidTarget {
                compilations.all {
                    compileTaskProvider.configure {
                        compilerOptions {
                            jvmTarget.set(JvmTarget.JVM_11)
                            allWarningsAsErrors.set(true)
                            freeCompilerArgs.add("-Xskip-prerelease-check")
                            freeCompilerArgs.add("-Xexpect-actual-classes")
                        }
                    }
                }
            }

            // iOS
            iosX64()
            iosArm64()
            iosSimulatorArm64()

            // Desktop
            jvm("desktop") {
                compilations.all {
                    compileTaskProvider.configure {
                        compilerOptions {
                            jvmTarget.set(JvmTarget.JVM_11)
                            allWarningsAsErrors.set(true)
                            freeCompilerArgs.add("-Xexpect-actual-classes")
                        }
                    }
                }
            }

            // Web
            js {
                browser()
            }
            @OptIn(ExperimentalWasmDsl::class)
            wasmJs {
                browser()
            }

            applyDefaultHierarchyTemplate()

            // Custom source set for non-Android platforms
            sourceSets {
                val commonMain by getting
                val nonAndroidMain by creating {
                    dependsOn(commonMain)
                }
                val iosMain by getting { dependsOn(nonAndroidMain) }
                val desktopMain by getting { dependsOn(nonAndroidMain) }
                val jsMain by getting { dependsOn(nonAndroidMain) }
                val wasmJsMain by getting { dependsOn(nonAndroidMain) }
            }
        }

        extensions.configure<LibraryExtension> {
            namespace = "glass.yasan.orbit.${project.name}"

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
                sourceCompatibility = JavaVersion.VERSION_11
                targetCompatibility = JavaVersion.VERSION_11
            }

            lint {
                disable.add("ObsoleteLintCustomCheck")
                disable.add("UnusedResources")
                disable.add("VectorPath")
                disable.add("UnusedAttribute")
                disable.add("UnknownIssueId") // Some Compose lint checks may not be available in all configurations
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
