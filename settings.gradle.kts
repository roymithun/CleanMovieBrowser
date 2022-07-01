rootProject.buildFileName = "build.gradle.kts"

enableFeaturePreview("VERSION_CATALOGS")

// Set single lock file (gradle.lockfile)
// This preview feature should be enabled by default in Gradle 7
// More: https://docs.gradle.org/current/userguide/dependency_locking.html#single_lock_file_per_project
enableFeaturePreview("ONE_LOCKFILE_PER_PROJECT")

include(
    ":app",
    ":test_util_library"
)

// Gradle plugins are added via plugin management, not the classpath
pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    // Using the plugins  DSL allows generating type-safe accessors for Kotlin DSL
    plugins {
        val agpVersion: String by settings
        id("com.android.application") version agpVersion
        id("com.android.library") version "7.2.1"
        id("com.android.dynamic-feature") version "7.2.1"

        val kotlinVersion: String by settings
        id("org.jetbrains.kotlin.jvm") version kotlinVersion
        id("org.jetbrains.kotlin.android") version "1.7.0"

        val navigationVersion: String by settings
        id("androidx.navigation.safeargs.kotlin") version navigationVersion

        val androidJUnit5Version: String by settings
        id("de.mannodermaus.android-junit5") version androidJUnit5Version
    }

    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "com.android.application",
                "com.android.library",
                "com.android.dynamic-feature" -> {
                    val agpCoordinates: String by settings
                    useModule(agpCoordinates)
                }
                "androidx.navigation.safeargs.kotlin" -> {
                    val navigationCoordinates: String by settings
                    useModule(navigationCoordinates)
                }
                "de.mannodermaus.android-junit5" -> {
                    val androidJnit5Coordinates: String by settings
                    useModule(androidJnit5Coordinates) // navigationVersion
                }
            }
        }
    }
}

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {

            val kotlinVersion: String by settings
            version("kotlin", kotlinVersion)
            // Required by Android dynamic feature modules and SafeArgs
            alias("kotlin-reflect").to("org.jetbrains.kotlin", "kotlin-reflect")
                .versionRef("kotlin")
            version("coroutines", "1.+")
            alias("coroutines").to("org.jetbrains.kotlinx", "kotlinx-coroutines-android")
                .versionRef("coroutines")
            bundle("kotlin", listOf("kotlin-reflect", "coroutines"))

            version("retrofit", "2.+")
            alias("retrofit-core").to("com.squareup.retrofit2", "retrofit").versionRef("retrofit")
            alias("converter-moshi").to("com.squareup.retrofit2", "converter-moshi")
                .versionRef("retrofit")

            version("gson-version", "2.9.0")
            alias("gson").to("com.google.code.gson", "gson").versionRef("gson-version")

            // Android - retrofit 2 - canot resolve RxJava2CallAdapterFactory
            alias("adapter-rxjava2").to("com.squareup.retrofit2", "adapter-rxjava2")
                .versionRef("retrofit")
            // KotlinJsonAdapterFactory unresolved reference -- https://stackoverflow.com/a/70036501/2694480
            version("moshi-kotlin", "1.12.0")
            alias("moshi-kotlin").to("com.squareup.moshi", "moshi-kotlin")
                .versionRef("moshi-kotlin")
            bundle(
                "retrofit",
                listOf(
                    "retrofit-core",
                    "converter-moshi",
                    "moshi-kotlin",
                    "adapter-rxjava2"
                )
            )

            // Retrofit will use okhttp 4 (it has binary capability with okhttp 3)
            // See: https://square.github.io/okhttp/upgrading_to_okhttp_4/
            version("okhttp", "4.+")
            alias("okhttp-okhttp").to("com.squareup.okhttp3", "okhttp").versionRef("okhttp")
            alias("okhttp-interceptor").to("com.squareup.okhttp3", "logging-interceptor")
                .versionRef("okhttp")
            // bundle is basically an alias for several dependencies
            bundle("okhttp", listOf("okhttp-okhttp", "okhttp-interceptor"))

            version("stetho", "1.5.0")
            alias("stetho-core").to("com.facebook.stetho", "stetho").versionRef("stetho")
            alias("stetho-okhttp3").to("com.facebook.stetho", "stetho-okhttp3").versionRef("stetho")
            bundle("stetho", listOf("stetho-core", "stetho-okhttp3"))

            alias("timber").to("com.jakewharton.timber:timber:4.+")
            alias("constraintLayout").to("androidx.constraintlayout:constraintlayout:2.+")
            alias("coordinatorLayout").to("androidx.coordinatorlayout:coordinatorlayout:1.+")
            alias("appcompat").to("androidx.appcompat:appcompat:1.+")
            alias("recyclerview").to("androidx.recyclerview:recyclerview:1.+")
            alias("material").to("com.google.android.material:material:1.4.0")
            alias("play-core").to("com.google.android.play:core:1.+")

            alias("rxjava").to("io.reactivex.rxjava2:rxjava:2.+")
            alias("rxandroid").to("io.reactivex.rxjava2:rxandroid:2.+")

            bundle("reactivex", listOf("rxjava", "rxandroid"))

            alias("core-ktx").to("androidx.core:core-ktx:1.+")
            alias("fragment-ktx").to("androidx.fragment:fragment-ktx:1.+")
            bundle("ktx", listOf("core-ktx", "fragment-ktx"))

            version("lifecycle", "2.+")
            alias("viewmodel-ktx").to("androidx.lifecycle", "lifecycle-viewmodel-ktx")
                .versionRef("lifecycle")
            alias("livedata-ktx").to("androidx.lifecycle", "lifecycle-livedata-ktx")
                .versionRef("lifecycle")
            alias("lifecycle-common").to("androidx.lifecycle", "lifecycle-common-java8")
                .versionRef("lifecycle")
            bundle("lifecycle", listOf("viewmodel-ktx", "livedata-ktx", "lifecycle-common"))

            val navigationVersion: String by settings
            version("navigation", navigationVersion)
            alias("navigation-fragment").to("androidx.navigation", "navigation-fragment-ktx")
                .versionRef("navigation")
            alias("navigation-dynamic")
                .to("androidx.navigation", "navigation-dynamic-features-fragment")
                .versionRef("navigation")
            alias("navigation-ui-ktx").to("androidx.navigation", "navigation-ui-ktx")
                .versionRef("navigation")
            bundle(
                "navigation",
                listOf("navigation-fragment", "navigation-dynamic", "navigation-ui-ktx")
            )

            version("koin", "3.+")
            alias("koinandroid").to("io.insert-koin", "koin-android").versionRef("koin")

//            version("koin", "2.0.1")
//            alias("koinandroid").to("org.koin", "koin-android").versionRef("koin")

            version("room", "2.+")
            alias("room-ktx").to("androidx.room", "room-ktx").versionRef("room")
            alias("room-runtime").to("androidx.room", "room-runtime").versionRef("room")
            bundle("room", listOf("room-ktx", "room-runtime"))

            alias("room.compiler").to("androidx.room", "room-compiler").versionRef("room")

            // Test dependencies
            alias("test-coroutines").to("org.jetbrains.kotlinx", "kotlinx-coroutines-test")
                .versionRef("coroutines")

            version("glide-version", "4.+")
            alias("glide").to("com.github.bumptech.glide", "glide").versionRef("glide-version")
            alias("glidecompiler").to("com.github.bumptech.glide", "compiler")
                .versionRef("glide-version")

            version("swiperefreshlayout-version", "1.1.0")
            alias("swiperefreshlayout").to("androidx.swiperefreshlayout", "swiperefreshlayout")
                .versionRef("swiperefreshlayout-version")

            version("kluent", "1.+")
            alias("kluent-android").to("org.amshove.kluent", "kluent-android").versionRef("kluent")

            alias("test-runner").to("androidx.test:runner:1.+")
            alias("espresso").to("androidx.test.espresso:espresso-core:3.+")
            alias("mockk").to("io.mockk:mockk:1.+")
            alias("arch").to("androidx.arch.core:core-testing:2.+")
            alias("kointest").to("io.insert-koin", "koin-test").versionRef("koin")

            version("junit", "5.+")
            alias("junit-jupiter-api").to("org.junit.jupiter", "junit-jupiter-api")
                .versionRef("junit")

            bundle(
                "test",
                listOf(
                    "test-coroutines",
                    "kluent-android",
                    "test-runner",
                    "espresso",
                    "mockk",
                    "arch",
                    "kointest",
                    "junit-jupiter-api"
                )
            )

            alias("junit-jupiter-engine").to("org.junit.jupiter", "junit-jupiter-engine")
                .versionRef("junit")
        }
    }
}
include(":feature_movie")
