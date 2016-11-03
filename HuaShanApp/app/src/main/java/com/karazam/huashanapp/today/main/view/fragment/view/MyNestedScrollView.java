package com.karazam.huashanapp.today.main.view.fragment.view;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2016/11/3.
 */

public class MyNestedScrollView extends NestedScrollView {

    public MyNestedScrollView(Context context) {
        super(context);
    }

    public MyNestedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyNestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        mOnScrollInterface.onSChanged(l,t,oldl,oldt);
    }


    private onScrollInterface mOnScrollInterface;

    public  void setOnScrollInterface(onScrollInterface onScrollInterface){
        mOnScrollInterface = onScrollInterface;
    }

    public interface onScrollInterface{
        void onSChanged(int l, int t, int oldl, int oldt);
    }
}
