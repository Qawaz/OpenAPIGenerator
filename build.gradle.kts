plugins {
    kotlin("jvm")
}

dependencies {
    implementation("com.wakaztahir.kate:1.0.2")
    implementation("com.wakaztahir.openapi:parser:1.0.1")
    implementation("com.wakaztahir.openapi:validator:1.0.1")
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