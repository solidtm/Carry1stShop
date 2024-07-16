plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.solid.carry1stshop"

/*   This was added to fix errors:
     1. Test results were not received
     2. MockK could not self-attach a jvmti agent to the current VM
*/

    testOptions {
        unitTests {
            all {
                it.useJUnitPlatform()
            }
        }
        packaging {
            jniLibs {
                useLegacyPackaging = true
            }
        }
    }
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
            buildConfigField(type = "String", name = "BASE_URL", value = "\"https://my-json-server.typicode.com\"")
        }
        release {
            isMinifyEnabled = true
            isDebuggable = false
            isShrinkResources = true
            buildConfigField("boolean", "DEBUG", "false")
            buildConfigField(type = "String", name = "BASE_URL", value = "\"https://my-json-server.typicode.com\"")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    configurations {
        androidTestImplementation {
            this.exclude(group = "io.mockk", module = "mockk-agent-jvm")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
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

//    Koin
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
    implementation(libs.koin.androidx.navigation)

//    Retrofit
    implementation(libs.retrofit)
    implementation(libs.moshi)
    implementation(libs.moshi.converter)
    implementation(libs.okhttp3)
    implementation(libs.logging)

//    Coil
    implementation(libs.coil.compose)

//    Coroutines
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.navigation.compose)


//    Testing
    testImplementation(libs.junit)
    testImplementation(libs.junit.params)
    testRuntimeOnly(libs.junit.engine)
    testImplementation(libs.assertK)
    testImplementation(libs.mockK)
    testImplementation(libs.koin.test)
    testImplementation(libs.koin.test.junit)
    testImplementation(libs.kotlinx.coroutines.test)

    androidTestImplementation(libs.mockK)
    androidTestImplementation(libs.truth)
    androidTestImplementation(libs.mock.android)
    androidTestImplementation(libs.navigation.testing)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}