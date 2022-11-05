object Dependencies {

    object Android {
        const val coreKtx = "androidx.core:core-ktx:1.7.0"
    }

    object Compose {
        const val version = "1.3.0"

        const val ui = "androidx.compose.ui:ui:$version"
        const val material = "androidx.compose.material:material:$version"
        const val tooling = "androidx.compose.ui:ui-tooling-preview:$version"
    }

    object Lifecycle {
        const val lifecycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:2.5.1"
        const val activityCompose = "androidx.activity:activity-compose:1.5.1"
    }

    object Hilt {
        const val version = "2.44"

        const val android = "com.google.dagger:hilt-android:$version"
        const val compiler = "com.google.dagger:hilt-compiler:$version"
    }

    object Test {
        const val jUnit = "junit:junit:4.13.2"
        const val androidJUnit = "androidx.test.ext:junit:1.1.2"
    }
}