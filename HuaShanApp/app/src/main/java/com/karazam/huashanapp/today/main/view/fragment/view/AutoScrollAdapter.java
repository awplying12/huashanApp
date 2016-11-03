package com.karazam.huashanapp.today.main.view.fragment.view;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.utils.custom.views.AutoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import github.chenupt.multiplemodel.viewpager.ModelPagerAdapter;
import github.chenupt.multiplemodel.viewpager.PagerModelManager;

/**
 * Created by Administrator on 2016/10/13.
 */

public class AutoScrollAdapter extends ModelPagerAdapter {
    private List<Integer> ids;
    private Context context;
    private AutoScrollViewPager pager;

    public AutoScrollAdapter(FragmentManager fm, PagerModelManager pagerModelManager, List<Integer> ids, Context context, AutoScrollViewPager pager) {
        super(fm, pagerModelManager);
        this.ids = ids;
        this.context = context;
        this.pager = pager;
    }

//    public AutoScrollAdapter(ArrayList<Integer> ids, Context context, AutoScrollViewPager pager) {
//        super();
//        this.ids = ids;
//        this.context = context;
//        this.pager = pager;
//    }

    @Override
    public int getCount() {
        return ids.size();
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

        int id = ids.get(position);
        iv.setBackgroundResource(id);
        container.addView(iv);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                pager.StopAutoScroll();
                mAutoScrollPagerClickListener.onClick(v,position);
            }
        });

        iv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
//                pager.StopAutoScroll();
                return mAutoScrollPagerClickListener.onLongClick(view,position);
            }
        });
        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


    /**
     * 点击触发事件
     */
    public interface OnAutoScrollPagerClickListener{
         void onClick(View view, int position);

        boolean onLongClick(View view, int position);
    }

    private  OnAutoScrollPagerClickListener mAutoScrollPagerClickListener;

    public  void setOnAutoScrollPagerClickListener(OnAutoScrollPagerClickListener onAutoScrollPagerClickListener){
        mAutoScrollPagerClickListener = onAutoScrollPagerClickListener;
    }
}
