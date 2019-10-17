# MapsExample

This app was built as an example on how to fetch data from an api and display them on a map (Google Maps). It was built in a multi modular approach based on features, following the MVVM architecture pattern with libraries like:

* `Dagger 2 for Android`
* `Kotlin`
* `AndroidX`
* `Gson`
* `Google Maps`
* `RxJava 2`
* `Timber`
* `Retrofit 2`

## How to run it?
In order to make the application work you need to provide your own Google Maps API key. The correct place to provide the key is the string `google_maps_key` in the file `google_maps_api.xml`.

## What next?
* write UI tests using espresso
* add refresh button
* add proper loading state
* make error message more explicit



