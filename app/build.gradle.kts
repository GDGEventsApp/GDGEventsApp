plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

    // Added plugins
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.kapt)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.protobuf)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.gdgevents.gdgeventsapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.gdgevents.gdgeventsapp"
        minSdk = 25
        //noinspection EditedTargetSdkVersion
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String", "MAP_API_KEY", "\"AIzaSyCLzDsqjcZ6gcm7riPHj82vURPk23BQW-4\"")
        manifestPlaceholders["MAP_API_KEY"] = "AIzaSyCLzDsqjcZ6gcm7riPHj82vURPk23BQW-4"
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
        buildConfig = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            // fix bug 'failed to make module' - instrumented mockk produces duplicate files
            pickFirsts.addAll(setOf("META-INF/LICENSE.md", "META-INF/LICENSE-notice.md"))
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
    implementation(libs.play.services.location)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

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
    // Coil - image Fetcher
    implementation(libs.coil.network.okhttp)

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

    implementation(libs.maps.compose)
    implementation(libs.play.services.maps.v1810)
    implementation(libs.kotlinx.coroutines.play.services)

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
