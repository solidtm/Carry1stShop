|          |             |                |       |
| :---:    |    :----:   |          :---: | :---: |
| ![1](https://drive.google.com/uc?export=view&id=1Kq7lIlKh1Cmk7BYkme-6ONPoNYEJ4dQV) | ![2](https://drive.google.com/uc?export=view&id=1pVg36wDbxwvRn1E5gKFo0BS9ss1H7-2W) | ![3](https://drive.google.com/uc?export=view&id=1iyT0g6DUtdYBYbY9PheO2tGK-HWUChyn) |



Carry1stShop is a **simple e-commerce android app** written using 100% Jetpack Compose and Kotlin. It's designed to display the list of products with their name and prices and to display the details of each product in the list.

## Architecture
I strive to keep my architecture "perfect" by putting software-design and code quality first. 

### High-level view:

- [Modular architecture](https://android-developers.googleblog.com/2022/09/announcing-new-guide-to-android-app-modularization.html)
- [FP (Functional Programming)](https://www.toptal.com/android/functional-reactive-programming-part-1)
- [MVVM (Model-View-ViewModel)](https://www.techtarget.com/whatis/definition/Model-View-ViewModel#:~:text=Model%2DView%2DViewModel%20(MVVM)%20is%20a%20software%20design,Ken%20Cooper%20and%20John%20Gossman.)

## Tech Stack

### Core

- 100% [Kotlin](https://kotlinlang.org/)
- 100% [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
- [Kotlin Flow](https://kotlinlang.org/docs/flow.html)
- [Koin](https://insert-koin.io/) (DI)
- [Jetpack Compose Navigation](https://developer.android.com/jetpack/compose/navigation)
  

### Networking
- [Retrofit](https://ktor.io/docs/getting-started-ktor-client.html) (Client)
- [OkHttp Client](https://square.github.io/okhttp/) (REST Client)
- [Moshi](https://github.com/square/retrofit/blob/trunk/retrofit-converters/moshi/README.md) (JSON serialization)

### Other
- [Coil](https://coil-kt.github.io/coil/getting_started/) (Image loading)

### CI/CD
- [Gradle KTS](https://docs.gradle.org/current/userguide/kotlin_dsl.html)

### Testing
- [JUnit 5](https://junit.org/junit5/)
- [MockK](https://mockk.io/)
- [AssertK](https://github.com/willowtreeapps/assertk/blob/main/README.md)
- [Truth](https://truth.dev/)
- [Espresso](https://developer.android.com/training/testing/espresso)
- [Koin test](https://insert-koin.io/docs/reference/koin-test/testing/)
- [Compose test](https://developer.android.com/develop/ui/compose/testing)
- [Compose navigation test](https://developer.android.com/develop/ui/compose/navigation)


## Project Requirements
- Java 17+
- **Android Studio Electric Eel+** (for easy install
  use [JetBrains Toolbox](https://www.jetbrains.com/toolbox-app/))

## How to build?
1. Clone the repository
2. Open with Android Studio
3. Everything should sync and build automatically


