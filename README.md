# around
Google Places demo

## Specifications / Requirements
As a result from a Places search, the app should:
- Display 10 places from the search area.
- Default the search to a 10 miles area around the users current location.
- Allow the user to extend the search radius.
- For each row in the list should present: name, image, rating
- Persist the search results in the app
- Allow future visits see previous searches and their results.

## Built With
* [Kotlin](https://kotlinlang.org/)
* [Android Architecture Components](https://developer.android.com/topic/libraries/architecture)
* [Google Maps Platform - Places Api](https://github.com/googlemaps/google-maps-services-java)

## Instructions
This demo uses the Gradle build system.

1. Download the demo by cloning this repository.
2. In Android Studio, create a new project and choose the "Import Project" option.
3. Select the root directory that you downloaded with this repository.
4. If prompted for a gradle configuration, accept the default settings. 
- Alternatively use the gradlew build command to build the project directly.

***This demo app requires that you add your own Google Maps API key***:

### Get a Maps API key
1. Create a file in the root directory called secure.properties (this file should NOT be under version control to protect your API key)
2. Add a single line to secure.properties that looks like MAPS_API_KEY=YOUR_API_KEY, where YOUR_API_KEY is the API key you obtained in the first step
3. Build and run

## Issues Encountered
- Providing a method for 'injecting' the API KEY so the project could be shared publicly
- Use of the DataBinding Library can often require to rebuild the project in order to generate Implementation classes.

## Testing
An Instrumented test has been recorded to demonstrate the MainActivity functionality
Instrumented tests much be run on a physical device

## Author
* **James Campbell** - *Android Developer* -
