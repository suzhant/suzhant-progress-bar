## Installation

To use this library in your Android project, follow these steps:

Step 1: Add the JitPack repository to your build file

```kotlin
dependencyResolutionManagement {
	repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
	repositories {
		mavenCentral()
		maven { url 'https://jitpack.io' }
	}
}
```

Step 2: Add the dependency

```kotlin
dependencies {
     implementation 'com.github.suzhant:suzhant-progress-bar:v1.0.4'
}
```

Step 3: Sync your project with gradle
