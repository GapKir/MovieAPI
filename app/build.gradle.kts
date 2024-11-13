plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.movieapi"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.movieapi"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        viewBinding = true
    }
}

dependencies {
    //standard
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")

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
    implementation(project(":shared_models"))
    implementation(project(":shared_data_connector"))
    implementation(project(":common"))
    implementation(project(":di"))
}