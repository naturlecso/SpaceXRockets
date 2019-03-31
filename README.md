# SpaceX Rockets

## Architecture
Basically I used a mixed MVVM - [Flux](https://facebook.github.io/flux/docs/overview.html) approch, because it perfectly fits for thin-clients like this application and Room+RxJava works perfectly as the dispatcher.
The idea behind the structuring is that normally the domain and data packages would come in single modules and the main module (or the feature modules) would only see the domain layer.

## Functionalities
 * Data fetching happens at the welcome dialog and you can also refresh the data by swiping at the rocket list.
 * Rockets active state can be toggled by the filter icon on the rocket list screen

## Libraries
* [RxJava](https://github.com/ReactiveX/RxJava): for the reactive data flow
* [Koin](https://insert-koin.io/): dependency injection framework
* [Room](https://developer.android.com/topic/libraries/architecture/room): an SQLite wrapper with Rx support
* [Retrofit](https://square.github.io/retrofit/): HTTP client
* [Android databinding](https://developer.android.com/topic/libraries/data-binding): used as an interface between the View and the ViewModel
* [Timber](https://github.com/JakeWharton/timber): logging
* [Picasso](https://square.github.io/picasso/): async image loading
* [Kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization): serialize JSON response from the API
* [ThreeTenABP](https://github.com/JakeWharton/ThreeTenABP): dealing with dates
* [MPAndroidChart](https://github.com/PhilJay/MPAndroidChart): chart engine

## Places of improvement
* Better error handling
* Tests

---
*The application can be downloaded [here](https://drive.google.com/file/d/19g0dNdLBP5jypWGM6d9JvmN9ZHSjH7L2/view?usp=sharing).*
*(And also can be founded in this repository [SpacexRockets.apk](SpacexRockets.apk))*
