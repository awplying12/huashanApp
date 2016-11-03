package com.karazam.huashanapp.today.main.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.utils.base.BaseFragment;
import com.example.utils.custom.views.AutoScrollViewPager;
import com.example.utils.custom.views.ViewGroupIndicator;
import com.google.common.collect.Lists;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.FragmentTodayBinding;

import com.karazam.huashanapp.today.main.model.databinding.TodayEntity;
import com.karazam.huashanapp.today.main.view.TodayView;
import com.karazam.huashanapp.today.main.view.fragment.view.AutoScrollAdapter;
import com.karazam.huashanapp.today.main.viewmodel.TodayViewModel;
import com.karazam.huashanapp.today.main.viewmodel.TodayViewModelImpl;

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

    private SwipeRefreshLayout swl;

    private AutoScrollViewPager pager;
//    private ViewGroupIndicator indicator;
    private AutoScrollAdapter autoScrollAdapter;
    private SpringIndicator springIndicator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_today,container,false);
        view = binding.getRoot();
        mModel = new TodayViewModelImpl(entity,this,getContext(),getActivity());
        binding.setEntity(entity);
        binding.setHandler(mModel);

        initView();

        initSwipeRefreshLayout();
        AutoScrollViewPager();
        return view;
    }

    /**
     * 初始化
     */
    private void initSwipeRefreshLayout() {

    }


    /**
     * 初始化View
     */
    private void initView() {

        swl = (SwipeRefreshLayout) getView(R.id.today_swipe_ly,view);
        swl.setOnRefreshListener(this);
        swl.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);

        pager = (AutoScrollViewPager) getView(R.id.today_scroll_pager,view);
//        indicator = (ViewGroupIndicator) getView(R.id.today_scroll_pager_indicator,view);
        springIndicator = (SpringIndicator) getView(R.id.indicator,view);

    }

    @Override
    public void onRefresh() {
        swl.setRefreshing(false);
    }

    /**
     * 设置滚动ViewPager
     */
    private ArrayList<Integer> ids;
    private void AutoScrollViewPager(){

        ids = new ArrayList<>();
        ids.add(R.drawable.image1);
        ids.add(R.drawable.image2);
        ids.add(R.drawable.image3);
        ids.add(R.drawable.image4);
        ids.add(R.drawable.image5);

        PagerModelManager manager = new PagerModelManager();
        manager.addCommonFragment(GuideFragment.class, getBgRes(), getTitles());
//        ModelPagerAdapter adapter = new ModelPagerAdapter(getActivity().getSupportFragmentManager(), manager);

        autoScrollAdapter = new AutoScrollAdapter(getActivity().getSupportFragmentManager(), manager,ids,getContext(),pager);
        pager.setTime(5000);
        pager.setAdapter(autoScrollAdapter);
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

    private List<String> getTitles(){
        return Lists.newArrayList("1", "2", "3", "4","5");
    }

    private List<Integer> getBgRes(){
        return Lists.newArrayList(R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4,R.drawable.image5);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("Activity-->", "onStart");
//        indicator.start();
        pager.startAutoScroll();
    }

    @Override
    public void onStop() {
        super.onStop();
        //    if (isPowerOff()) {
//        indicator.stop();
        pager.stopAutoScroll();
        //    }
        Log.i("Activity-->", "onStop");

    }
}
