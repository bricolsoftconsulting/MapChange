/*
Copyright 2011 Bricolsoft Consulting

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

package com.bricolsoftconsulting.mapchange;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class MyOverlay extends Overlay
{	
	// ------------------------------------------------------------------------
	// LISTENER DEFINITIONS
	// ------------------------------------------------------------------------
	
	// Tap listener
	public interface OnTapListener
	{
		public void onTap(MapView v, GeoPoint geoPoint);
	}
	
	// ------------------------------------------------------------------------
	// GETTERS / SETTERS
	// ------------------------------------------------------------------------
	
	// Setters
	public void setOnTapListener(OnTapListener listener)
	{
		mTapListener = listener;
	}

	// ------------------------------------------------------------------------
	// MEMBERS
	// ------------------------------------------------------------------------
	
	private OnTapListener mTapListener;
	
	// ------------------------------------------------------------------------
	// EVENT HANDLERS
	// ------------------------------------------------------------------------
	
	@Override
	public boolean onTap(GeoPoint geoPoint, MapView mapView)
	{
		mTapListener.onTap(mapView, geoPoint);
		return super.onTap(geoPoint, mapView);
	}
}