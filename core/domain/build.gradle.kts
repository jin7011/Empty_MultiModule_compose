import kr.mojise.setNamespace

plugins {
    alias(libs.plugins.app.android.library)
    alias(libs.plugins.app.android.library.jacoco)
    id("com.google.devtools.ksp")
}

android {
    setNamespace("core.domain")
}

dependencies {
    api(projects.core.data)
    api(projects.core.model)

    implementation(libs.javax.inject)
}