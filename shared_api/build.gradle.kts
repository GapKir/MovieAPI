plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.dev.shared_api"
    compileSdk = 34

    kotlinOptions {
        jvmTarget = "1.8"
    }
}
dependencies {
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
}