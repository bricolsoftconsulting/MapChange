/*
Copyright 2013 Bricolsoft Consulting

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

import android.content.Context;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class MyOverlay extends Overlay
{
	private MapView mMapView = null;
	private GestureDetector mGestureDetector = null;
	private SimpleOnGestureListener mGestureListener = new SimpleOnGestureListener()
	{
		@Override
		public boolean onSingleTapConfirmed(MotionEvent e)
		{
			if (mTapListener != null)
			{
				GeoPoint p = (GeoPoint)mMapView.getProjection().fromPixels((int)e.getRawX(), (int)e.getRawY());
				mTapListener.onTap(p, mMapView);
			}
			return true;
		}

		public boolean onDoubleTap(MotionEvent e)
		{
			if (mTapListener != null)
			{
				GeoPoint p = (GeoPoint)mMapView.getProjection().fromPixels((int)e.getRawX(), (int)e.getRawY());
				mTapListener.onDoubleTap(p, mMapView);
			}
			return true;
		}
	};

	// ------------------------------------------------------------------------
	// CONSTRUCTOR
	// ------------------------------------------------------------------------

	public MyOverlay(Context context, MapView mapView)
	{
		mMapView = mapView;
		mGestureDetector = new GestureDetector(context, mGestureListener);
	}

	// ------------------------------------------------------------------------
	// LISTENER DEFINITIONS
	// ------------------------------------------------------------------------

	// Tap listener
	public interface OnTapListener
	{
		public void onTap(GeoPoint p, MapView mapView);
		public void onDoubleTap(GeoPoint p, MapView mapView);
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

	public boolean onTouchEvent(MotionEvent motionEvent, MapView mapView)
	{
		return mGestureDetector.onTouchEvent(motionEvent);
	}
}
