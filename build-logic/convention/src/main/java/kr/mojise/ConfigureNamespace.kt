package kr.mojise

import org.gradle.api.Project

fun Project.setNamespace(name: String) {
    androidExtension.apply {
        val basePackage = libs.findVersion("basePackage").get().toString()
        namespace = "$basePackage.$name"
    }
}