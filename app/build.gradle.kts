plugins {
  id("com.android.application")
  id("kotlin-android")
}

android {
  namespace = "dev.juez.plugin"
  compileSdk = 34

  defaultConfig {
    applicationId = "dev.juez.plugin"
    minSdk = 21
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"

    vectorDrawables.useSupportLibrary = true
  }
  
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  buildTypes {
    release {
      isMinifyEnabled = true
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }

  buildFeatures {
    viewBinding = true
  }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
  kotlinOptions.jvmTarget = "17"
}

dependencies {
  implementation(project(":api"))
}