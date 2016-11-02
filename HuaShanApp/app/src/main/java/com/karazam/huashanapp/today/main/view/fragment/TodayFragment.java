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
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.FragmentTodayBinding;

import com.karazam.huashanapp.today.main.model.databinding.TodayEntity;
import com.karazam.huashanapp.today.main.view.TodayView;
import com.karazam.huashanapp.today.main.view.fragment.view.AutoScrollAdapter;
import com.karazam.huashanapp.today.main.viewmodel.TodayViewModel;
import com.karazam.huashanapp.today.main.viewmodel.TodayViewModelImpl;

import java.util.ArrayList;

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
    private ViewGroupIndicator indicator;
    private AutoScrollAdapter autoScrollAdapter;

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
        indicator = (ViewGroupIndicator) getView(R.id.today_scroll_pager_indicator,view);

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

        autoScrollAdapter = new AutoScrollAdapter(ids,getContext(),pager);
        pager.setTime(5000);
        pager.setAdapter(autoScrollAdapter);
        indicator.setParent(pager);

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

    @Override
    public void onStart() {
        super.onStart();
        Log.i("Activity-->", "onStart");
        indicator.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        //    if (isPowerOff()) {
        indicator.stop();
        //    }
        Log.i("Activity-->", "onStop");

    }
}
