plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    kotlin("kapt")     ////for dagger hilt
    id("com.google.dagger.hilt.android") ////for dagger hilt

    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.urbandictionary"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.urbandictionary"
        minSdk = 24
        targetSdk = 33
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

    buildFeatures{
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {


    val daggerVersion = "2.50"
    val retrofitVersion = "2.9.0"
    val firebaseAuth = "22.3.0"
    val viewModelCoroutines = "2.7.0"

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    ////for dagger hilt
    implementation("com.google.dagger:hilt-android:$daggerVersion")
    kapt("com.google.dagger:hilt-android-compiler:$daggerVersion")

    ////retrofit
    implementation ("com.squareup.retrofit2:retrofit:$retrofitVersion")
    // JSON Parsing
    implementation ("com.google.code.gson:gson:$retrofitVersion")
    implementation ("com.squareup.retrofit2:converter-gson:$retrofitVersion")

    ////firebase auth
    implementation("com.google.firebase:firebase-auth:$firebaseAuth")

    ////////////view model coroutines
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$viewModelCoroutines")

//    implementation "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03"
//    kapt "androidx.hilt:hilt-compiler:1.0.0-alpha03"

}