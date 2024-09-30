plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.dev.popular_films"
    compileSdk = 34

    buildFeatures{
        viewBinding = true
    }
}
dependencies {
    implementation(project(":app"))
    implementation(project(":popular_films_connector"))
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.fragment:fragment-ktx:1.8.3")

    //glide
    implementation("com.github.bumptech.glide:glide:4.16.0")

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.6")


}
