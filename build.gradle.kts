// Top-level build file where you can add configuration options common to all sub-projects/modules.


buildscript {
    dependencies {
        classpath ("com.android.tools.build:gradle:8.1.4")
//        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.40.5")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.48")

    }

    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://www.jitpack.io") }    }
}


plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
    id ("com.android.library") version "7.4.0-alpha08" apply false
    id ("com.google.devtools.ksp") version "1.9.21-1.0.15" apply false
    kotlin("jvm") version "1.6.0"
}