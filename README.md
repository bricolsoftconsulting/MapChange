MAPCHANGE
=========

What is MapChange?
---------------------
MapChange is an Android app that shows how to extend Google Maps' MapView class to get notifications when the map has been panned, zoomed or both.

Installation
------------
To setup this project:

1. Download a copy of this code.
1. Edit the `map.xml` file in `/res/layout` to add your Google Maps API Key on this line:

    android:apiKey="ENTER_GOOGLE_MAPS_API_KEY_HERE"

Usage
-----
Pan, zoom or pinch the map. The app will tell you what you just did (panned, zoomed or both).

Credits
-------
Thanks to [Dave Smith](https://github.com/devunwired) for suggesting the use of View.postDelayed instead of a timer so the change events are not launched on the timer's background thread. His wonderful suggestion has been incorporated in this code.

Copyright
---------
Copyright 2011 Bricolsoft Consulting

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.