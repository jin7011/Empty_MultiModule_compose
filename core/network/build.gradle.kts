import kr.mojise.setNamespace

plugins {
    alias(libs.plugins.app.android.library)
    alias(libs.plugins.app.android.library.jacoco)
    alias(libs.plugins.app.hilt)
    id("kotlinx-serialization")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    buildFeatures {
        buildConfig = true
    }
    setNamespace("core.network")
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

secrets {
//    propertiesFileName = "secrets.properties" // 비공개 url
//    defaultPropertiesFileName = "secrets.defaults.properties" // 팀내 공개 가능 url
}

dependencies {
    api(libs.kotlinx.datetime)
    api(projects.core.common)
    api(projects.core.model)

    implementation(libs.coil.kt)
    implementation(libs.coil.kt.svg)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)

    testImplementation(libs.kotlinx.coroutines.test)
}