plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.dev.di"
    compileSdk = 34

    buildFeatures{
        viewBinding = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    //retrofit
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    //mvi
    implementation("com.airbnb.android:mavericks:3.0.3")

    //dagger2
    implementation("com.google.dagger:dagger:2.48.1")
    ksp("com.google.dagger:dagger-compiler:2.48.1")

    //modules
    implementation(project(":shared_api"))
    implementation(project(":shared_repo"))
    implementation(project(":feature_popular_films_connector"))
    implementation(project(":feature_popular_films"))
    implementation(project(":shared_data_connector"))
    implementation(project(":common"))
}