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

## License
```
Copyright 2019 Matthias Siegmund

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

