plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.safe.args)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kapt)
//    alias(libs.plugins.ksp)
    alias(libs.plugins.mapsplatform.secrets.gradle.plugin)
}

android {
    namespace = "com.akmaldev.tutorapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.akmaldev.tutorapp"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Dagger Hilt
    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.compiler)

    // Retrofit
    implementation(libs.retrofit2)
    implementation(libs.retrofit2.gson)

    // OkHttp
    implementation(libs.okhttp3)

    // Interceptor
    debugImplementation(libs.chucker)
    releaseImplementation(libs.chucker.no.op)

    // Circle Image View
    implementation(libs.circle.image.view)

    // Glide
    implementation(libs.glide)

    // Google Map
    implementation(libs.goole.map)
    implementation(libs.play.services.location)

    // Camera
    implementation(libs.camera.core)
    implementation(libs.camera.camera2)
    implementation(libs.camera.lifecycle)
    implementation(libs.camera.view)

    // Chart
    implementation(libs.pie.chart)

    // Pagination
    implementation(libs.pagination)
}