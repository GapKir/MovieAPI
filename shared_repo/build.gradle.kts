plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
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

    implementation("com.google.dagger:dagger:2.48.1")
    ksp("com.google.dagger:dagger-compiler:2.48.1")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
}
