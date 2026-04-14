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

rootProject.name = "dance4life-android"
include(":app")
include(":libs:core-domain")
include(":libs:core-data")
include(":plugins:rl-inference")

project(":libs").projectDir = file("../../libs")
project(":libs:core-domain").projectDir = file("../../libs/core-domain")
project(":libs:core-data").projectDir = file("../../libs/core-data")
project(":plugins").projectDir = file("../../plugins")
project(":plugins:rl-inference").projectDir = file("../../plugins/rl-inference")
