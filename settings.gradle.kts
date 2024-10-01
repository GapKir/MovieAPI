pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MovieAPI"
include(":app")
include(":popular_films")
include(":popular_films_connector")
include(":shared_models")
include(":shared_repo")
include(":shared_api")
include(":common")
