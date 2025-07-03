import kr.mojise.setNamespace

plugins {
    alias(libs.plugins.app.android.library)
    alias(libs.plugins.app.android.library.jacoco)
    alias(libs.plugins.app.hilt)
}

android {
    defaultConfig {
        consumerProguardFiles("consumer-proguard-rules.pro")
    }
    setNamespace("core.datastore")
}

dependencies {
    api(libs.androidx.dataStore)
    api(projects.core.datastoreProto)
    api(projects.core.model)

    implementation(projects.core.common)

    testImplementation(libs.kotlinx.coroutines.test)
}
