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

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;

public class MyMapView extends MapView
{	
	// ------------------------------------------------------------------------
	// LISTENER DEFINITIONS
	// ------------------------------------------------------------------------
	
	// Change listener
	public interface OnChangeListener
	{
		public void onChange(MapView view, GeoPoint newCenter, GeoPoint oldCenter, int newZoom, int oldZoom);
	}
	
	// ------------------------------------------------------------------------
	// MEMBERS
	// ------------------------------------------------------------------------
	
	private MyMapView mThis;
	private long mEventsTimeout = 250L; 	// Set this variable to your preferred timeout
	private boolean mIsTouched = false;
	private GeoPoint mLastCenterPosition;
	private int mLastZoomLevel;
	private Timer mChangeDelayTimer = new Timer();
	private MyMapView.OnChangeListener mChangeListener = null;
	
	// ------------------------------------------------------------------------
	// CONSTRUCTORS
	// ------------------------------------------------------------------------
	
	public MyMapView(Context context, String apiKey)
	{
		super(context, apiKey);
		init();
	}
	
	public MyMapView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		init();
	}
	
	public MyMapView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		init();
	}
	
	private void init()
	{
		mThis = this;
		mLastCenterPosition = this.getMapCenter();
		mLastZoomLevel = this.getZoomLevel();
	}
	
	// ------------------------------------------------------------------------
	// GETTERS / SETTERS
	// ------------------------------------------------------------------------
	
	public void setOnChangeListener(MyMapView.OnChangeListener l)
	{
		mChangeListener = l;
	}

	// ------------------------------------------------------------------------
	// EVENT HANDLERS
	// ------------------------------------------------------------------------
	
	@Override
	public boolean onTouchEvent(MotionEvent ev)
	{		
		// Set touch internal
		mIsTouched = (ev.getAction() != MotionEvent.ACTION_UP);

		return super.onTouchEvent(ev);
	}
	
	@Override
	public void computeScroll()
	{
		super.computeScroll();
		
		// Check for change
		if (isSpanChange() || isZoomChange())
		{
			// If computeScroll called before timer counts down we should drop it and 
			// start counter over again
			resetMapChangeTimer();
		}
	}

	// ------------------------------------------------------------------------
	// TIMER RESETS
	// ------------------------------------------------------------------------
	
	private void resetMapChangeTimer()
	{
		mChangeDelayTimer.cancel();
		mChangeDelayTimer = new Timer();
		mChangeDelayTimer.schedule(new TimerTask()
		{
			@Override
			public void run()
			{
				if (mChangeListener != null) mChangeListener.onChange(mThis, getMapCenter(), mLastCenterPosition, getZoomLevel(), mLastZoomLevel);
				mLastCenterPosition = getMapCenter();
				mLastZoomLevel = getZoomLevel();
			}
		}, mEventsTimeout);
	}
	
	// ------------------------------------------------------------------------
	// CHANGE FUNCTIONS
	// ------------------------------------------------------------------------
	
	private boolean isSpanChange()
	{
		return !mIsTouched && !getMapCenter().equals(mLastCenterPosition);
	}
	
	private boolean isZoomChange()
	{
		return (getZoomLevel() != mLastZoomLevel);
	}
	
}