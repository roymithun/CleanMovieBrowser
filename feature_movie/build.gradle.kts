import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id(GradlePluginId.ANDROID_DYNAMIC_FEATURE)
    id(GradlePluginId.KOTLIN_ANDROID)
    id(GradlePluginId.KOTLIN_PARCELIZE)
    id(GradlePluginId.SAFE_ARGS)
    id(GradlePluginId.KOTLIN_KAPT)
    id(GradlePluginId.ANDROID_JUNIT_5)
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 26
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    implementation(project(":app"))
    api(libs.bundles.ktx)
    api(libs.bundles.lifecycle)

    api(libs.bundles.room)
    kapt(libs.room.compiler)

    api(libs.glide)
    kapt(libs.glidecompiler)

    api(libs.gson)

    api(libs.swiperefreshlayout)

    testImplementation(project(ModuleDependency.TEST_UTIL_LIBRARY))

    testImplementation(libs.bundles.test)

    testRuntimeOnly(libs.junit.jupiter.engine)
}