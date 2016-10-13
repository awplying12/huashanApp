package com.example.utils.custom.views;

/**
 * Created by liukun on 15/2/9.
 */
public interface IndicatorParentImbl {

    public void setIndicatorListener(IndicatorListener indicatorListener);

    public int getIndicatorCount();

    public void startAutoScroll();

    public void stopAutoScroll();

    public void onReDraw(int index);
}
