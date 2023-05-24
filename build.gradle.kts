plugins {
    kotlin("jvm")
    id("org.graalvm.buildtools.native").version("0.9.12")
}

dependencies {

    implementation("com.wakaztahir:kate:1.0.33")
    implementation("com.wakaztahir.openapi:json-overlay:1.1.1")
    implementation("com.wakaztahir.openapi:parser:1.1.1")
    implementation("com.wakaztahir.openapi:validator:1.1.1")

    implementation("org.graalvm.sdk:graal-sdk:22.3.2")

    testImplementation("junit:junit:4.13.1")
    testImplementation(kotlin("test"))

}

graalvmNative {
    toolchainDetection.value(true)
}

nativeBuild {
    this.buildArgs.add("--enable-url-protocols=https")
    fallback.value(false)
    mainClass.value("com.wakaztahir.openapi.MainKt")
}

fun RepositoryHandler.githubPackages(){
    maven("https://maven.pkg.github.com/Qawaz/K-OpenApi-Parser") {
        name = "GithubPackages"
        try {
            credentials {
                username = (System.getenv("GPR_USER")).toString()
                password = (System.getenv("GPR_API_KEY")).toString()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

allprojects {
    group = "com.wakaztahir.openapi"
    version = property("version") as String
    repositories {
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
        mavenCentral()
        githubPackages()
    }
}