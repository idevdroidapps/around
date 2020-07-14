# around (Google Places POC)

## Built With
* [Kotlin](https://kotlinlang.org/)
* [Android Architecture Components](https://developer.android.com/topic/libraries/architecture)

## Author
* **James Campbell** - *Android Developer* -

### Instructions
This demo uses the Gradle build system.

Download the demo by cloning this repository.
In Android Studio, create a new project and choose the "Import Project" option.
Select the root directory that you downloaded with this repository.
If prompted for a gradle configuration, accept the default settings. Alternatively use the gradlew build command to build the project directly.
This demo app requires that you add your own Google Maps API key:

Get a Maps API key
Create a file in the root directory called secure.properties (this file should NOT be under version control to protect your API key)
Add a single line to secure.properties that looks like MAPS_API_KEY=YOUR_API_KEY, where YOUR_API_KEY is the API key you obtained in the first step
Build and run

### Issues Encountered
- Providing a method for 'injecting' the API KEY so the project could be shared publicly
- Use of the DataBinding Library can often require to rebuild the project in order to generate Implementation classes.

### Testing
An Instrumented test has been recorded to demonstrate the MainActivity functionality
Instrumented tests much be run on a physical device
