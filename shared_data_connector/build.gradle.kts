plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.dev.shared_data_connector"
    compileSdk = 34

    kotlinOptions {
        jvmTarget = "1.8"
    }
}
dependencies {
    implementation("com.google.dagger:dagger:2.48.1")
    ksp("com.google.dagger:dagger-compiler:2.48.1")

    implementation(project(":shared_repo"))
    implementation(project(":feature_popular_films_connector"))
    implementation(project(":shared_models"))
}