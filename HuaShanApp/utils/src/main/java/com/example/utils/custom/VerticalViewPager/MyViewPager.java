package com.example.utils.custom.VerticalViewPager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.AbsListView;

public class MyViewPager extends ViewPager  {

	public MyViewPager(Context context) {
		super(context);
	}

	public MyViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
//	@Override
//	public boolean onInterceptTouchEvent(MotionEvent arg0) {
//		return true;
//	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (listener != null) {
				listener.onTouchDown();
			}
			break;
		case MotionEvent.ACTION_UP:
			if (listener != null) {
				listener.onTouchUp();
			}
			break;
		default:
			break;
		}
		return super.dispatchTouchEvent(ev);
	}
	
	private OnViewPagerTouchEvent listener;
	


	public interface OnViewPagerTouchEvent{
		void onTouchDown();
		void onTouchUp();
	}




}
