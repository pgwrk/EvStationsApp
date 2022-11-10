# EvStationsApp
## Issues and possible improvements:

### Common:
- Show/Hide password icon was not provided and was taken from standard icons pack
- UI paddings need to be moved to UI theme
- Need to add internet connection availability component

### Login screen:
- Need to add an email validation

### Stations screen:
- Location data source mocked and returns a coordinate for the Helsinki Cathedral 
- Caching and pagination can be added


## Features:
- Light and dark themes supported
- Android version 5+ 
- According to the assignment, /stations api call is mocked.
  To get real data from the backend, it is need to comment line 37 in ApiModule.kt 
- Settings screen
- Gradle kts
- Single module with ability to migrate to multi module


## Tech stack:
- Kotlin as a recommended programming language for Android development
- Compose as a modern UI framework developed and promoted by Google
- Retrofit as a standard library for http requests
- MVVM/MVI as one of the most popular and convenient approach for an application architecture
