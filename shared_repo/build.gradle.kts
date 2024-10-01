plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.dev.shared_repo"
    compileSdk = 34

    kotlinOptions {
        jvmTarget = "1.8"
    }
}
dependencies {
    implementation(project(":shared_models"))
    implementation(project(":shared_api"))
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
}
