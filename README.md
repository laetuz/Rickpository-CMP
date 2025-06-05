<img src="https://github.com/user-attachments/assets/4f37bb6c-ff51-440b-a756-0b408701d49e" alt="Rickpository Screenshot Android" height="450"/>
<img src="https://github.com/user-attachments/assets/43f3c333-7d2a-49d8-a4be-8ce50dc953e9" alt="Rickpository Screenshot iOS" height="450"/>

# Rickpository Compose Multi Platform 👽🚀

A simple Android application built with Kotlin and Jetpack Compose to explore characters from the Rick and Morty universe using the [Rick and Morty API](https://rickandmortyapi.com/documentation).

## ✨ Features

* **Browse Characters:** View a paginated list of characters from the show.
* **Character Details:** Tap on a character to see more detailed information (implementation dependent).
* **Pagination:** Loads characters page by page as you scroll.
* **Modern UI:** Built entirely with Jetpack Compose, featuring an edge-to-edge display.
* **Image Loading:** Asynchronously loads character images.

## 🛠️ Tech Stack & Architecture

This project utilizes a modern Android development stack:

* **Language:** [Kotlin](https://kotlinlang.org/) (100%)
* **UI Toolkit:** [Jetpack Compose](https://developer.android.com/jetpack/compose) for declarative UI.
* **Architecture:** MVVM (Model-View-ViewModel)
    * **ViewModel:** `androidx.lifecycle.ViewModel` to hold and manage UI-related data.
    * **Repository:** To abstract data sources (network, local database).
* **Asynchronous Programming:** [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) and [Flow](https://kotlinlang.org/docs/flow.html) for managing background tasks and data streams.
* **Networking:** [Ktor Client](https://ktor.io/docs/client-introduction.html) for making HTTP requests to the Rick and Morty API.
* **Dependency Injection:** [Koin](https://insert-koin.io/) (as indicated by `koinViewModel()`).
* **Image Loading:** [Coil 3](https://coil-kt.github.io/coil/compose/) for loading and displaying images.
* **Logging:** Uses a logging library (as indicated by `com.diamondedge.logging.logging` or `io.ktor.util.logging.KtorSimpleLogger`).

## 🚀 Getting Started

### Prerequisites

* Android Studio (latest stable version recommended, e.g., Koala or newer for latest Compose features)
* JDK 17 or higher

### Installation & Setup

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/laetuz/Rickpository-CMP.git](https://github.com/laetuz/Rickpository-CMP.git)
    cd Rickpository-CMP
    ```
2.  **Open in Android Studio:**
    Open the cloned project using Android Studio.
3.  **Build the project:**
    Android Studio should automatically sync Gradle and download dependencies. You can then build the project using `Build > Make Project` or by running it on an emulator or physical device.

The Rick and Morty API is public and does not require an API key.

## 🏗️ Project Structure (Simplified)

* `app/src/main/java/id/neotica/rickpository/`: Root package
    * `data/`: Contains data layer components.
        * `CharacterRepositoryImpl.kt`: Implementation of the repository pattern.
        * `local/`: Room database components (Entities, DAOs, Database class).
        * `remote/`: Ktor client setup and API service definitions.
    * `domain/`: Contains domain layer components.
        * `model/`: Data models (e.g., `Character`, `RickAndMortyResponse`, `ApiResult`).
        * `repository/`: Repository interface (if defined).
    * `presentation/`: Contains UI layer components (Jetpack Compose).
        * `characters/`: Composables and ViewModel for the characters list screen.
        * `character_detail/`: (If implemented) Composables and ViewModel for the character detail screen.
    * `di/`: Koin modules for dependency injection.
    * `navigation/`: Jetpack Compose Navigation setup.

## 📄 API Reference

This project uses the public [Rick and Morty API](https://rickandmortyapi.com/documentation). All data is fetched directly from this API.


## 📝 License

This project is licensed under the **MIT License** (or choose another like Apache 2.0).
