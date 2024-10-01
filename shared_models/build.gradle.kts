plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.dev.shared_models"
    compileSdk = 34

    kotlinOptions {
        jvmTarget = "1.8"
    }
}