import kr.mojise.setNamespace

plugins {
    alias(libs.plugins.app.android.library)
    alias(libs.plugins.app.android.library.compose)
    alias(libs.plugins.app.android.library.jacoco)
}

android {
    setNamespace("core.ui")
}

dependencies {
    api(libs.androidx.metrics)
    api(projects.core.designsystem)
    api(projects.core.model)

    implementation(libs.androidx.browser)
    implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)
}
