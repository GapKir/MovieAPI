plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.dev.popular_films_connector"
    compileSdk = 34

    kotlinOptions {
        jvmTarget = "1.8"
    }
}
dependencies {
    implementation(project(":shared_models"))
    implementation(project(":shared_repo"))
}
