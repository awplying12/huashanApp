package com.example.utils.custom.rollViewPager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2016/10/13.
 */

public class rollViewPagerAdapter extends PagerAdapter {

    private List<View> list;
    private Context con;

    public rollViewPagerAdapter(List<View> list, Context con) {
        this.list = list;
        this.con = con;
    }


    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(list.get(position%list.size()));

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View v = new View(con);
        v = list.get(position%list.size());
        container.addView(v);
        return v;
    }
}
