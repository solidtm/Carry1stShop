# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Keep all the model classes marked with @Keep
-keep class * {
    @androidx.annotation.Keep *;
}

# Keep the application class
-keep class com.solid.carry1stshop.** {
    *;
}

# Keep Retrofit and OkHttp classes
-dontwarn okhttp3.**
-keep class okhttp3.** { *; }
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes RuntimeVisibleAnnotations
-keepattributes AnnotationDefault

# Keep Moshi classes
-keep class com.squareup.moshi.** { *; }
-dontwarn com.squareup.moshi.**

# Keep classes used by Koin
-keep class org.koin.** { *; }
-dontwarn org.koin.**

# Keep classes used by Coil
-keep class coil.** { *; }
-dontwarn coil.**

# Keep Jetpack Compose related classes
-keep class androidx.compose.** { *; }
-dontwarn androidx.compose.**

# Keep Kotlin coroutines and Flow
-keep class kotlinx.coroutines.** { *; }
-dontwarn kotlinx.coroutines.**

# Keep navigation component classes
-keep class androidx.navigation.** { *; }
-dontwarn androidx.navigation.**

## General Android classes
#-keepclassmembers class * extends android.app.Activity {
#    public void *(android.view.View);
#}
#
#-keepclassmembers class * extends androidx.fragment.app.Fragment {
#    public void *(android.view.View);
#}

# Keep ViewModel classes
-keep class androidx.lifecycle.ViewModel { *; }
-keep class androidx.lifecycle.LiveData { *; }
-keepclassmembers class androidx.lifecycle.** {
    *;
}

# Keep annotations
-keepattributes *Annotation*

# Keep Compose-related classes
-keep class androidx.compose.runtime.** { *; }
-keep class androidx.compose.ui.** { *; }
-keep class androidx.compose.foundation.** { *; }
-keep class androidx.compose.material.** { *; }

# Keep generated Jetpack Compose classes
-keep class androidx.compose.compiler.plugins.kotlin.** { *; }
-dontwarn androidx.compose.compiler.plugins.kotlin.**

# Keep rules for the Kotlin standard library
-keep class kotlin.** { *; }
-dontwarn kotlin.**

# Keep rules for JUnit
-keep class org.junit.** { *; }
-dontwarn org.junit.**

# Keep rules for MockK
-keep class io.mockk.** { *; }
-dontwarn io.mockk.**

# Keep rules for AssertK
-keep class assertk.** { *; }
-dontwarn assertk.**

# Keep rules for Truth
-keep class com.google.common.truth.** { *; }
-dontwarn com.google.common.truth.**

# Keep rules for Espresso
-keep class androidx.test.espresso.** { *; }
-dontwarn androidx.test.espresso.**
