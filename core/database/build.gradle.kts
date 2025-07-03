import kr.mojise.setNamespace

plugins {
    alias(libs.plugins.app.android.library)
    alias(libs.plugins.app.android.library.jacoco)
    alias(libs.plugins.app.android.room)
    alias(libs.plugins.app.hilt)
}

android {
    setNamespace("core.database")
}

dependencies {
    api(projects.core.model)

    implementation(libs.kotlinx.datetime)

    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.kotlinx.coroutines.test)
}