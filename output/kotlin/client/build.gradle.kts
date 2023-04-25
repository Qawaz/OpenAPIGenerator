plugins {
    kotlin("multiplatform")
    id("maven-publish")
    id("com.android.library")
}

group = "com.wakaztahir"
version = findProperty("version") as String

kotlin {
    android {
        publishLibraryVariants("release")
    }
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }
    js(IR) {
        browser()
        binaries.executable()
    }
    sourceSets {
        val commonMain by getting {
            dependencies {

            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val commonJvmMain by creating {
            dependsOn(commonMain)
        }
        val androidMain by getting {
            dependsOn(commonJvmMain)
        }
        val androidInstrumentedTest by getting {
            dependencies {
                implementation("androidx.test:runner:1.5.2")
                implementation("androidx.test:core-ktx:1.5.0")
            }
        }
        val jvmMain by getting {
            dependsOn(commonJvmMain)
        }
        val jvmTest by getting {

        }
        val jsMain by getting
    }
}

android {
    compileSdk = 32
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}