buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:8.9.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.10")
    }
}

plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.24" apply false
    id("com.google.devtools.ksp") version "1.9.24-1.0.20" apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
