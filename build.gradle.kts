// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
    dependencies {
        classpath(Libs.androidGradlePlugin)

        classpath(Libs.Kotlin.gradlePlugin)

        classpath(Libs.Hilt.androidGradlePlugin)

    }
}