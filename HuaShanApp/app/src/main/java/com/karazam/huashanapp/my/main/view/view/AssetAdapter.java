package com.karazam.huashanapp.my.main.view.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/19.
 */

public class AssetAdapter extends PagerAdapter {


    private Context context;
    private ArrayList<View> list = new ArrayList<>();

    public AssetAdapter(Context context, ArrayList<View> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(list.get(position));


        return list.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }


}
