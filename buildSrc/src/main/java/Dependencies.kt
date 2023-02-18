object Libs {
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val material = "com.google.android.material:material:1.7.0"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.4"

    const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1"
    const val activityKtx = "androidx.activity:activity-ktx:1.6.1"
    const val lifeCycleExt = "androidx.lifecycle:lifecycle-extensions:2.2.0"

    const val tensorLite = "org.tensorflow:tensorflow-lite:${Versions.tensor}"
    const val tensorLiteGpu = "org.tensorflow:tensorflow-lite-gpu:${Versions.tensor}"
    const val tensorLiteOps = "org.tensorflow:tensorflow-lite-select-tf-ops:${Versions.tensor}"
    const val tensorLiteSupport = "org.tensorflow:tensorflow-lite-support:0.4.2"
    const val openCv = "com.quickbirdstudios:opencv:4.5.3.0"
    const val exifinterface = "androidx.exifinterface:exifinterface:1.3.5"

    const val dexter = "com.karumi:dexter:6.2.3"
    const val imagePicker = "com.github.dhaval2404:imagepicker:2.1"

    const val hiltAndroid = "com.google.dagger:hilt-android:2.44.2"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:2.44.2"

    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.1"

    const val glide = "com.github.bumptech.glide:glide:4.11.0"

    const val ocr = "cz.adaptech.tesseract4android:tesseract4android-openmp:4.3.0"

    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
}

object LibsTest {
    const val jUnit = "junit:junit:4.13.2"
    const val extJUnit = "androidx.test.ext:junit:1.1.5"
    const val espressoCore = "androidx.test.espresso:espresso-core:3.5.1"
}

object Versions {
    const val tensor = "2.11.0"
    const val tensorSupport = " 0.4.3"
    const val coreKtx = "1.7.0"
    const val appCompat = "1.6.0"

    const val room = "2.4.0-rc01"
}