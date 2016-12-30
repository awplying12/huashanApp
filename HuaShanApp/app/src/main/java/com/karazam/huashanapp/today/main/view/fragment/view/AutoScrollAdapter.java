package com.karazam.huashanapp.today.main.view.fragment.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.utils.custom.views.AutoScrollViewPager;
import com.karazam.huashanapp.R;

import java.util.ArrayList;
import java.util.List;

import github.chenupt.multiplemodel.viewpager.ModelPagerAdapter;
import github.chenupt.multiplemodel.viewpager.PagerModelManager;
import rx.Subscriber;
import util.changhongit.com.cacheutils.Cache_RxBitmap.Data;
import util.changhongit.com.cacheutils.Cache_RxBitmap.RxImageLoader;

import static android.R.attr.id;

/**
 * Created by Administrator on 2016/10/13.
 */

public class AutoScrollAdapter extends ModelPagerAdapter {
//    private List<Integer> ids;
    private ArrayList<String> urls = new ArrayList<>();
    private ArrayList<Bitmap> bitmaps = new ArrayList<>();
    private Context context;
    private AutoScrollViewPager pager;

//    public AutoScrollAdapter(FragmentManager fm, PagerModelManager pagerModelManager, List<Integer> ids, Context context, AutoScrollViewPager pager) {
//        super(fm, pagerModelManager);
//        this.ids = ids;
//        this.context = context;
//        this.pager = pager;
//    }

    public AutoScrollAdapter(FragmentManager fm, PagerModelManager pagerModelManager, ArrayList<String> urls, Context context, AutoScrollViewPager pager) {
        super(fm, pagerModelManager);
//        this.ids = ids;
        this.urls = urls;
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
        return urls.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        final ImageView iv = new ImageView(container.getContext());
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        iv.setImageDrawable(context.getResources().getDrawable(ids[position]));

//        int id = ids.get(position);
        RxImageLoader.getLoaderObservable(iv,urls.get(position))
                .subscribe(new Subscriber<Data>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("bbb","e  :  "+e.toString());
                    }

                    @Override
                    public void onNext(Data data) {

                    }
                });

        iv.setBackgroundResource(R.drawable.def_img);

        container.addView(iv);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                pager.StopAutoScroll();
                mAutoScrollPagerClickListener.onClick(v,position);
            }
        });
//
//        iv.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
////                pager.StopAutoScroll();
//                return mAutoScrollPagerClickListener.onLongClick(view,position);
//            }
//        });

        pager.setmDuration(500);
        return iv;
    }



    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


    public ArrayList<String> getUrls() {
        return urls;
    }

    public void setUrls(ArrayList<String> urls) {
        this.urls = urls;
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
