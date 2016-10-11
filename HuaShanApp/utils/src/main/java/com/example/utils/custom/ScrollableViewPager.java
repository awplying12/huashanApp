package com.example.utils.custom;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by YC.Zhu on 2016/3/31.
 */
public class ScrollableViewPager extends ViewPager {


    private boolean scrollAble = true;// 是否允许滑动

    public ScrollableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollableViewPager(Context context) {
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (scrollAble)
            return super.onTouchEvent(event);

        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (scrollAble)
            return super.onInterceptTouchEvent(event);

        return false;
    }

    public boolean isScrollAble() {
        return scrollAble;
    }

    public void setScrollAble(boolean scrollAble) {
        this.scrollAble = scrollAble;
    }
}
