plugins {
    id("org.jetbrains.kotlin.jvm") version "1.6.0"
    application
}

allprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.gradle.application")

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0-RC")
    }

    application {
        mainClass.set("com.24i.adventofcode.AppKt")
    }
}