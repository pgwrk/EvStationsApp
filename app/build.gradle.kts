plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.packageName
        minSdk = Config.minSDK
        targetSdk = Config.targetSDK
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.Compose.version
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // Android
    implementation(Dependencies.Android.coreKtx)

    // Compose
    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.material)
    implementation(Dependencies.Compose.tooling)

    // Lifecycle
    implementation(Dependencies.Lifecycle.lifecycleKtx)
    implementation(Dependencies.Lifecycle.activityCompose)

    // Test
    testImplementation(Dependencies.Test.jUnit)
    androidTestImplementation(Dependencies.Test.androidJUnit)
}