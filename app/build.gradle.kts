import com.google.protobuf.gradle.*

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    // Added plugins
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.kapt)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.protobuf)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.gdgevents.gdgeventsapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.gdgevents.gdgeventsapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.13"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation (libs.play.services.maps)

    implementation ("com.google.android.gms:play-services-location:21.0.1")
    implementation ("com.google.maps.android:maps-compose:2.11.2")
    implementation("androidx.compose.ui:ui:1.5.3")
    implementation("androidx.compose.material:material:1.5.3") // Material Design 2
    implementation("androidx.compose.material3:material3:1.3.1") // Material Design 3

    implementation("androidx.compose.ui:ui-tooling-preview:1.5.3")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")

    // Hilt - dependency injection
    implementation(libs.hilt.android)
    // hilt kapt - annotation processor — hilt ksp is in alpha
    kapt(libs.hilt.compiler)

    // Retrofit - make type safe requests
    implementation(libs.retrofit)
    // okhttp - fire http calls
    implementation(libs.okhttp)
    // logging interceptor - log calls for debugging
    implementation(libs.logging.interceptor)
    // converter - make retrofit use serialization json
    implementation(libs.converter.kotlinx.serialization)
    // JSON - serialize Kotlin objects <-> JSON
    implementation(libs.kotlinx.serialization.json)

    // Coil - asynchronous image loading
    implementation(libs.coil.compose)

    // datastore - make type safe local storage
    implementation(libs.androidx.datastore)
    // protobuf - serialize Kotlin objects <-> protobuf schema
    implementation(libs.protobuf.kotlin.lite)

    // room - local database
    implementation(libs.androidx.room.runtime)
    // room ksp - annotation processor
    ksp(libs.androidx.room.compiler)
    // room - coroutine support
    implementation(libs.androidx.room.ktx)

    // mockk unit tests
    testImplementation(libs.mockk.android)
    // mockk instrumented tests
    androidTestImplementation(libs.mockk.android)
    // kotlin coroutines tests
    testImplementation(libs.kotlinx.coroutines.test)

    // collect as state with lifecycle
    implementation(libs.androidx.lifecycle.runtime.compose)

    // adaptive navigation - get window info
    implementation(libs.androidx.adaptive.navigation.android)
    // navigation suite scaffold - make adaptive navigation
    implementation(libs.androidx.material3.adaptive.navigation.suite)

    // compose nav host & type safe navigation
    implementation(libs.androidx.navigation.compose)
    // hilt view model injection
    implementation(libs.androidx.hilt.navigation.compose)

    // splash screen - backwards compatibility
    implementation(libs.androidx.core.splashscreen)

    // work manager
    implementation(libs.androidx.work.runtime.ktx)
    // hilt work manager injection
    implementation(libs.androidx.hilt.work)

    // hilt kapt work manager
    kapt(libs.androidx.hilt.compiler)
}

// protobuf plugin
protobuf {
    // proto compiler
    protoc {
        artifact = libs.protobuf.protoc.get().toString()
    }
    // plugin instructions
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                // make java files
                register("java") {
                    option("lite")
                }
                // make kotlin DSL on top of java files
                register("kotlin") {
                    option("lite")
                }
            }
        }
    }
}

// kapt plugin
kapt {
    // improve type inference accuracy
    correctErrorTypes = true
}
