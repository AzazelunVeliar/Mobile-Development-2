plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "ru.mirea.khudyakovma.recyclerviewapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "ru.mirea.khudyakovma.recyclerviewapp"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.cardview:cardview:1.0.0")
}
