|          |             |                |       |
| :---:    |    :----:   |          :---: | :---: |
| ![1](https://drive.google.com/uc?export=view&id=1Kq7lIlKh1Cmk7BYkme-6ONPoNYEJ4dQV) | ![2](https://drive.google.com/uc?export=view&id=1pVg36wDbxwvRn1E5gKFo0BS9ss1H7-2W) | ![3](https://drive.google.com/uc?export=view&id=1iyT0g6DUtdYBYbY9PheO2tGK-HWUChyn) |



Carry1stShop is an Android application designed to showcase a product catalog. It allows users to browse through a list of products and view more information about each product. The app is built entirely using Kotlin and Jetpack Compose for a modern, declarative UI approach.

## Architecture
I strive to keep my architecture "perfect" by putting software-design and code quality first. 

### High-level view:

- [MVVM (Model-View-ViewModel)](https://www.techtarget.com/whatis/definition/Model-View-ViewModel#:~:text=Model%2DView%2DViewModel%20(MVVM)%20is%20a%20software%20design,Ken%20Cooper%20and%20John%20Gossman.)
- [FP (Functional Programming)](https://www.toptal.com/android/functional-reactive-programming-part-1)

## Usage
- Home Screen: Displays a list of products fetched from a remote server.
- Detail Screen: Shows detailed information about a selected product, including its image, name, price, and description.
- Toolbar: Contains an action to refresh the product list.

## Assumptions
- Networking: Products are fetched from a RESTful API endpoint.
- Error Handling: Basic error dialogs are shown for network errors or failures.
- Navigation: Simple navigation flow with two primary screens (Product List and Product Details).

## Tech Stack - Choices Around Plugins and 3rd Party Libraries

### Core:
- 100% [Kotlin](https://kotlinlang.org/): Ensures modern language features and null safety.
- 100% [Jetpack Compose](https://developer.android.com/jetpack/compose): Leverages the latest UI toolkit for Android, offering a declarative approach to UI development.
- [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html): Simplifies asynchronous programming, making it easier to handle background tasks and manage concurrency.
- [Kotlin Flow](https://kotlinlang.org/docs/flow.html): Used for reactive programming, handling streams of data efficiently.

### Dependency Injection:
- [Koin](https://insert-koin.io/): A lightweight dependency injection framework for Kotlin. It simplifies the management of dependencies and allows for easy testing and configuration.

### Networking:
- [Retrofit](https://square.github.io/retrofit/): A type-safe HTTP client for Android. It simplifies the process of making network requests and parsing the responses.
- [OkHttp](https://square.github.io/okhttp/) (REST Client): A robust HTTP client that provides efficient HTTP requests.
- [Moshi](https://github.com/square/retrofit/blob/trunk/retrofit-converters/moshi/README.md): A modern JSON library for Android and Java, used for parsing JSON into Kotlin objects and vice versa.

### Image Loading:
- [Gradle KTS](https://docs.gradle.org/current/userguide/kotlin_dsl.html): An image loading library for Android backed by Kotlin Coroutines. It is designed for asynchronous image loading and provides a simple API for image handling in Jetpack Compose.
  
### CI/CD:
- [Gradle KTS](https://docs.gradle.org/current/userguide/kotlin_dsl.html): Uses Kotlin DSL for Gradle build scripts, offering better syntax highlighting, code completion, and type safety.

### Testing:
- [JUnit 5](https://junit.org/junit5/): The latest version of the popular testing framework for Java and Kotlin.
- [MockK](https://mockk.io/): A mocking library for Kotlin. It allows for creating mocks and stubs in a type-safe manner.
- [AssertK](https://github.com/willowtreeapps/assertk/blob/main/README.md): A fluent assertion library for Kotlin, providing a more readable and expressive way to write assertions.
- [Truth](https://truth.dev/): A library for testing, making assertions more readable and straightforward.
- [Espresso](https://developer.android.com/training/testing/espresso): A UI testing framework for Android, used for writing concise and reliable UI tests.
- [Koin test](https://insert-koin.io/docs/reference/koin-test/testing/): Extensions for testing Koin modules.
- [Compose test](https://developer.android.com/develop/ui/compose/testing): Testing utilities for Jetpack Compose.
- [Compose navigation test](https://developer.android.com/develop/ui/compose/navigation): Utilities for testing Jetpack Compose navigation flows.

## Performance Optimization Considerations

### Efficient Networking:
- OkHttp and Retrofit: These libraries are chosen for their efficiency in handling network requests. OkHttp supports connection pooling and HTTP/2, which reduces latency and saves resources.
- Caching: OkHttp’s caching mechanism is utilized to store responses and reuse them when possible, reducing the number of network calls.

### Image Loading:
- Coil: Chosen for its efficient handling of image loading, backed by Kotlin Coroutines. It ensures that images are loaded asynchronously, preventing UI blocking.
- Image Caching: Coil’s built-in caching mechanism is used to store loaded images, minimizing redundant network requests.

### Asynchronous Programming:
- Kotlin Coroutines and Flow: Used extensively to perform tasks asynchronously, ensuring that the main thread is not blocked, leading to a smoother user experience.

### Declarative UI with Jetpack Compose:
- Compose: Allows building UIs in a declarative manner, which helps in optimizing rendering performance. Compose efficiently updates the UI by only re-composing parts of the UI that have changed.
- LazyColumn: Utilized for displaying lists efficiently, only rendering items that are currently visible on the screen.

### Navigation:
- Jetpack Compose Navigation: Used to manage navigation efficiently within the app, reducing overhead and complexity compared to traditional navigation components.

### Dependency Injection:
- Koin: Simplifies dependency management, reducing the boilerplate code and improving the maintainability and performance of dependency resolution.


## Project Requirements
- Java 17+
- **Android Studio Electric Eel+** (for easy install
  use [JetBrains Toolbox](https://www.jetbrains.com/toolbox-app/))

## How to build?
1. Clone the repository
2. Open with Android Studio
3. Everything should sync and build automatically


