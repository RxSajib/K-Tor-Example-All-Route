plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    kotlin("plugin.serialization") version "2.1.0"
}

android {
    namespace = "com.example.networkktor"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.networkktor"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    implementation("io.ktor:ktor-client-core:3.0.2")
    implementation ("io.ktor:ktor-client-android:3.0.2")


    implementation ("io.ktor:ktor-client-content-negotiation:3.0.2")
    implementation ("io.ktor:ktor-serialization-kotlinx-json:3.0.2")

    implementation ("io.ktor:ktor-client-logging:3.0.2")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")


}