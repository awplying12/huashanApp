package com.karazam.huashanapp.today.main.view.fragment;

import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.utils.base.BaseFragment;

import com.example.utils.custom.VpSwipeRefreshLayout;
import com.example.utils.custom.views.AutoScrollViewPager;
import com.example.utils.custom.views.ViewGroupIndicator;
import com.google.common.collect.Lists;
import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.FragmentTodayBinding;

import com.karazam.huashanapp.today.main.model.databinding.TodayEntity;
import com.karazam.huashanapp.today.main.view.TodayView;
import com.karazam.huashanapp.today.main.view.fragment.view.AutoScrollAdapter;
import com.karazam.huashanapp.today.main.view.fragment.view.CommodityAdapter;
import com.karazam.huashanapp.today.main.view.fragment.view.CommodityItem;
import com.karazam.huashanapp.today.main.view.fragment.view.MyNestedScrollView;

import com.karazam.huashanapp.today.main.viewmodel.TodayViewModel;
import com.karazam.huashanapp.today.main.viewmodel.TodayViewModelImpl;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxView;

import java.util.ArrayList;
import java.util.List;

import github.chenupt.multiplemodel.viewpager.ModelPagerAdapter;
import github.chenupt.multiplemodel.viewpager.PagerModelManager;
import github.chenupt.springindicator.GuideFragment;
import github.chenupt.springindicator.SpringIndicator;

/**
 * Created by Administrator on 2016/11/2.
 */

public class TodayFragment extends BaseFragment implements TodayView,SwipeRefreshLayout.OnRefreshListener {

    private View view;

    private FragmentTodayBinding binding;
    private TodayEntity entity = new TodayEntity();

    private TodayViewModel mModel;
    private TextView profit_num;

    private VpSwipeRefreshLayout swl;

    private AutoScrollViewPager pager;

    private AutoScrollAdapter autoScrollAdapter;
    private SpringIndicator springIndicator;

    private MyNestedScrollView scrollview;

    private RecyclerView commodity_rl;

    private ImageView line_1;
    private ImageView line_2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_today,container,false);
        view = binding.getRoot();
        mModel = new TodayViewModelImpl(entity,this,getContext(),getActivity());
        binding.setEntity(entity);
        binding.setHandler(mModel);

        initView();

        initTime();
        setScrollView();
        AutoScrollViewPager();
        setCommodity();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        pager.startAutoScroll();
    }

    @Override
    public void onStop() {
        super.onStop();
        pager.stopAutoScroll();

    }



    /**
     * 初始化日期
     */
    private void initTime() {
        RxView.findById(view,R.id.today_time).bind(HuaShanApplication.day, new Rx.Action<View, String>() {
            @Override
            public void call(View target, String s) {
                TextView textView = (TextView) target;
                textView.setText(s);
            }
        });
    }


    /**
     * 初始化View
     */
    private void initView() {

        swl = (VpSwipeRefreshLayout) getView(R.id.today_swipe_ly,view);
        swl.setOnRefreshListener(this);
        swl.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);

        pager = (AutoScrollViewPager) getView(R.id.today_scroll_pager,view);
        springIndicator = (SpringIndicator) getView(R.id.indicator,view);

        profit_num = (TextView) getView(R.id.profit_num,view);
        final Typeface font = Typeface.createFromAsset(getActivity().getAssets(),"FZXQJW.TTF");
        profit_num.setTypeface(font);

        scrollview = (MyNestedScrollView) getView(R.id.scrollview,view);

        line_1 = (ImageView) getView(R.id.td_line_1,view);
        line_2 = (ImageView) getView(R.id.td_line_2,view);

        commodity_rl = (RecyclerView) getView(R.id.commodity_rl,view);
        LinearLayoutManager lm = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        commodity_rl.setLayoutManager(lm);

    }

    @Override
    public void onRefresh() {
        swl.setRefreshing(false);
    }

    /**
     * 设置滚动ViewPager
     */
    private void AutoScrollViewPager(){

        PagerModelManager manager = new PagerModelManager();
        manager.addCommonFragment(GuideFragment.class, getBgRes(), getTitles());

        autoScrollAdapter = new AutoScrollAdapter(getActivity().getSupportFragmentManager(), manager,getBgRes(),getContext(),pager);
        pager.setTime(5000);
        pager.setAdapter(autoScrollAdapter);
        pager.setmDuration(500);
//        indicator.setParent(pager);

        springIndicator.setViewPager(pager);

        autoScrollAdapter.setOnAutoScrollPagerClickListener(new AutoScrollAdapter.OnAutoScrollPagerClickListener() {

            @Override
            public void onClick(View view, int position) {
                showToast(position+"");
            }

            @Override
            public boolean onLongClick(View view, int position) {
                showToast(position+"Long");
                return true;
            }
        });
    }

    /**
     * 设置广告标题
     * @return
     */
    private List<String> getTitles(){
        return Lists.newArrayList("1", "2", "3", "4","5");
    }

    /**
     * 设置广告图片
     * @return
     */
    private List<Integer> getBgRes(){
        return Lists.newArrayList(R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4,R.drawable.image5);
    }

    /**
     * 设置滑动效果
     */
    private void setScrollView() {

        scrollview.setOnScrollInterface(new MyNestedScrollView.onScrollInterface() {
            @Override
            public void onSChanged(int l, int t, int oldl, int oldt) {


                int[] location1 = new int[2];
                line_1.getLocationOnScreen(location1);
                final int x1 = location1[0];
                final int y1 = location1[1];

                int[] location2 = new int[2];
                line_2.getLocationOnScreen(location2);
                final int x2 = location2[0];
                final int y2 = location2[1];


                if((y1-y2) <= 0 ){
                    line_1.setBackgroundColor(getResources().getColor(R.color.transparent));
                    line_2.setBackgroundColor(getResources().getColor(R.color.line_color));
                }else {
                    line_2.setBackgroundColor(getResources().getColor(R.color.transparent));
                    line_1.setBackgroundColor(getResources().getColor(R.color.line_color));
                }
            }
        });

    }

    /**
     * 积分商城RecyclerView
     */
    private void setCommodity(){

        ArrayList<CommodityItem> items = new ArrayList<>();
        items.add(new CommodityItem("手机主题馆积分不够分期付","#66ffff"));
        items.add(new CommodityItem());
        items.add(new CommodityItem());
        items.add(new CommodityItem());
        items.add(new CommodityItem());

        CommodityAdapter adapter = new CommodityAdapter(items,commodity_rl);

        commodity_rl.setAdapter(adapter);

    }


}
