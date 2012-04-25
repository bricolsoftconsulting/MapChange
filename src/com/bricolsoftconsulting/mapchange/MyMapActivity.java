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
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class MyMapActivity extends MapActivity
{
	// Members
	MyMapView mMapView;
	MyOverlay mMapOverlay;
	Handler mHandler = new Handler();
	
	// Runnables
	Runnable mOnMapTap = new Runnable()
	{
		public void run()
		{
			// Notify
			Toast.makeText(MyMapActivity.this, "Map Tap", Toast.LENGTH_SHORT).show();
		}
	};
	
	Runnable mOnMapZoom = new Runnable()
	{
		public void run()
		{
			// Notify
			Toast.makeText(MyMapActivity.this, "Map Zoom", Toast.LENGTH_SHORT).show();
		}
	};
	
	Runnable mOnMapPan = new Runnable()
	{
		public void run()
		{
			// Notify
			Toast.makeText(MyMapActivity.this, "Map Pan", Toast.LENGTH_SHORT).show();
		}
	};
	
	Runnable mOnMapZoomPan = new Runnable()
	{
		public void run()
		{
			// Notify
			Toast.makeText(MyMapActivity.this, "Map Zoom + Pan", Toast.LENGTH_SHORT).show();
		}
	};
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);
    	
		// Populate the map member
		mMapView = (MyMapView) findViewById(R.id.theMap);
        
		// Add overlay
		mMapOverlay = new MyOverlay();
		mMapView.getOverlays().add(mMapOverlay);
        
		// Add zoom controls
		mMapView.setBuiltInZoomControls(true);
		
		// Add listeners
		mMapView.setOnChangeListener(new MapViewChangeListener());
		mMapOverlay.setOnTapListener(new MapViewTapListener());
	}
	
	private class MapViewChangeListener implements MyMapView.OnChangeListener
	{

		@Override
		public void onChange(MapView view, GeoPoint newCenter, GeoPoint oldCenter, int newZoom, int oldZoom)
		{
			// Check values
			if ((!newCenter.equals(oldCenter)) && (newZoom != oldZoom))
			{
				mHandler.post(mOnMapZoomPan);
			}
			else if (!newCenter.equals(oldCenter))
			{
				mHandler.post(mOnMapPan);
			}
			else if (newZoom != oldZoom)
			{
				mHandler.post(mOnMapZoom);
			}
		}	
	}
	
	private class MapViewTapListener implements MyOverlay.OnTapListener
	{
		@Override
		public void onTap(MapView view, GeoPoint geoPoint)
		{
			mHandler.post(mOnMapTap);
		}
	}

	@Override
	protected boolean isRouteDisplayed()
	{
		return false;
	}
}