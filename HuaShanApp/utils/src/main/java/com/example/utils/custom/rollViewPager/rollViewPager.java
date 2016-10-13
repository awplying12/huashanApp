package com.example.utils.custom.rollViewPager;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/10/13.
 */

public class rollViewPager extends ViewPager {


    private boolean isLoop = true;
    private boolean isLoop_s = true;
    private int count = 0;

    public rollViewPager(Context context) {
        super(context);
    }

    public rollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



    public void setAdapter(PagerAdapter adapter,int size) {
        count = size;
        int n = Integer.MAX_VALUE / 2 % adapter.getCount();
        int itemPosition = Integer.MAX_VALUE / 2 - n;
        setCurrentItem(itemPosition);

        super.setAdapter(adapter);
        setAutomatic();
    }

    Handler mainHandler=new Handler(){
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                setCurrentItem(getCurrentItem() + 1);
            }
        };
    };

    private void setAutomatic() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (isLoop) {
                    SystemClock.sleep(5000);
                    if (isLoop_s) {
                        mainHandler.sendEmptyMessage(0);
                    }

                }

            }
        }).start();

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                isLoop_s = false;
                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
                getParent().requestDisallowInterceptTouchEvent(false);
                isLoop_s = true;

                break;

            default:
                break;
        }

        return super.onTouchEvent(ev);
    }

    public void destroyViewPager(){
        isLoop = false;
    }


    public interface PageChangeListener{
        public void onPageSelected(int position);
    }

    private  PageChangeListener mPageChangeListener;

    public   void setPageChangeListener(final PageChangeListener pageChangeListener){
        mPageChangeListener = pageChangeListener;

        rollViewPager.this.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.i("msg",count+"");

                pageChangeListener.onPageSelected(position%count);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
