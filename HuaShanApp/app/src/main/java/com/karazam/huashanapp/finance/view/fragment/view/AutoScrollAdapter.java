package com.karazam.huashanapp.finance.view.fragment.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/10/13.
 */

public class AutoScrollAdapter extends PagerAdapter {
    private int[] ids;
    private Context context;

    public AutoScrollAdapter(int[] ids, Context context) {
        this.ids = ids;
        this.context = context;
    }

    @Override
    public int getCount() {
        return ids.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView iv = new ImageView(container.getContext());
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        iv.setImageDrawable(context.getResources().getDrawable(ids[position]));

        iv.setBackgroundResource(ids[position]);
        container.addView(iv);

                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"点击 : "+position,Toast.LENGTH_SHORT).show();
                    }
                });
        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
