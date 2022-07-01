plugins {
    id(GradlePluginId.ANDROID_APPLICATION)
    id(GradlePluginId.KOTLIN_ANDROID)
    id(GradlePluginId.KOTLIN_KAPT)
    id(GradlePluginId.ANDROID_JUNIT_5)
}

android {
    compileSdkVersion(AndroidConfig.COMPILE_SDK_VERSION)
    defaultConfig {
        applicationId = AndroidConfig.ID
        minSdkVersion(AndroidConfig.MIN_SDK_VERSION)
        targetSdkVersion(AndroidConfig.TARGET_SDK_VERSION)
//        buildToolsVersion(AndroidConfig.BUILD_TOOLS_VERSION)

        versionCode = AndroidConfig.VERSION_CODE
        versionName = AndroidConfig.VERSION_NAME
        testInstrumentationRunner = AndroidConfig.TEST_INSTRUMENTATION_RUNNER

        val gradlePropertyName = "tmdbApiKey"
        val propertyValue = project.properties[gradlePropertyName] as? String
        checkNotNull(propertyValue) { "Gradle property $gradlePropertyName is null" }

        val androidResourceName = gradlePropertyName.toSnakeCase().toUpperCase()
        buildConfigField("String", androidResourceName, propertyValue)
//        buildConfigFieldFromGradleProperty("tmdbApiKey")
    }

    buildTypes {
        getByName(BuildType.RELEASE) {
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            proguardFiles("proguard-android.txt", "proguard-rules.pro")
        }

        getByName(BuildType.DEBUG) {
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
        }

        testOptions {
            unitTests.isReturnDefaultValues = TestOptions.IS_RETURN_DEFAULT_VALUES
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
    dynamicFeatures += setOf(":feature_movie")
}

dependencies {
//    implementation(project(":feature_movies"))
    api(libs.bundles.kotlin)
    api(libs.bundles.stetho)
    api(libs.bundles.retrofit)
    api(libs.bundles.okhttp)
    api(libs.play.core)
    api(libs.bundles.ktx)
    api(libs.bundles.navigation)
    api(libs.bundles.lifecycle)
    api(libs.timber)
    api(libs.constraintLayout)
    api(libs.coordinatorLayout)
    api(libs.appcompat)
    api(libs.recyclerview)
    api(libs.material)
    api(libs.coroutines)
    api(libs.bundles.reactivex)
    kapt(libs.room.compiler)

    api(libs.koinandroid)

    testImplementation(project(ModuleDependency.TEST_UTIL_LIBRARY))
    testImplementation(libs.bundles.test)

    testRuntimeOnly(libs.junit.jupiter.engine)
}

fun String.toSnakeCase() = this.split(Regex("(?=[A-Z])")).joinToString("_") { it.toLowerCase() }
