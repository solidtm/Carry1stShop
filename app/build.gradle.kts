plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
//    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.solid.carry1stshop"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.solid.carry1stshop"
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
        debug{
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false
            android.buildFeatures.buildConfig = true
            buildConfigField("boolean", "DEBUG", "true")
//            buildConfigField(type = "String", name = "BASE_URL", value = "\"https://my-json-server.typicode.com\"")
        }
        release {
            isMinifyEnabled = true
            isDebuggable = false
            isShrinkResources = true
            buildConfigField("boolean", "DEBUG", "false")
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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

//    Android
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

//    Dependency Injection
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
    implementation(libs.koin.androidx.navigation)

//    Networking
    implementation(libs.retrofit)
    implementation(libs.moshi)
    implementation(libs.moshi.converter)
    implementation(libs.okhttp3)
    implementation(libs.logging)
//    implementation(libs.ktor.client.cio)
//    implementation(libs.ktor.client.core)
//    implementation(libs.ktor.android)
//    implementation(libs.ktor.serialization)
//    implementation(libs.ktor.client.content.negotiation)
//    implementation(libs.logback.classic)


//    Image loading
    implementation(libs.coil.compose)

//    Coroutines
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.navigation.compose)


//    Unit tests
    testImplementation(libs.junit)
    testImplementation(libs.junit.params)
    testRuntimeOnly(libs.junit.engine)
    testImplementation(libs.assertK)
    testImplementation(libs.mockK)
    testImplementation(libs.koin.test)
    testImplementation(libs.koin.test.junit)

//    UI tests
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}