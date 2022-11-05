plugins {
    id("com.android.application") version "7.2.0" apply false
    id("com.android.library") version "7.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.7.10" apply false
    id ("com.google.dagger.hilt.android") version Dependencies.Hilt.version apply false
}

tasks.register<Task>(name="clean") {
    delete(rootProject.buildDir)
}