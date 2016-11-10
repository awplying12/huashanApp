package com.karazam.huashanapp.manage.details.view.view;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/10.
 */

public class VerticalAdapter extends PagerAdapter {

    ArrayList<Integer> list = new ArrayList<>();

    public VerticalAdapter(ArrayList<Integer> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {


        ImageView iv = new ImageView(container.getContext());
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        iv.setImageDrawable(context.getResources().getDrawable(ids[position]));

        int id = list.get(position);
        iv.setBackgroundResource(id);
        container.addView(iv);

        return iv;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
