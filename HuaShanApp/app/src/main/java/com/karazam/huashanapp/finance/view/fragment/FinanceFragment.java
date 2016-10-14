package com.karazam.huashanapp.finance.view.fragment;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.utils.base.BaseFragment;

import com.example.utils.custom.views.AutoScrollViewPager;
import com.example.utils.custom.views.PercentLemon;
import com.example.utils.custom.views.ViewGroupIndicator;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.FragmentFinanceBinding;
import com.karazam.huashanapp.finance.model.databinding.FinanceEntity;
import com.karazam.huashanapp.finance.view.FinanceView;
import com.karazam.huashanapp.finance.view.fragment.view.AutoScrollAdapter;
import com.karazam.huashanapp.finance.viewmodel.FinanceViewModel;
import com.karazam.huashanapp.finance.viewmodel.FinanceViewModelImpl;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Administrator on 2016/10/11.
 */

public class FinanceFragment extends BaseFragment implements FinanceView,SwipeRefreshLayout.OnRefreshListener{

    private View view;

    private FragmentFinanceBinding binding;
    private FinanceEntity entity = new FinanceEntity();
    private FinanceViewModel mModel;

    private AutoScrollViewPager pager;
    private ViewGroupIndicator indicator;
    private AutoScrollAdapter autoScrollAdapter;
    private ArrayList<Integer> ids;

    private SwipeRefreshLayout mSwipeLayout;

    private PercentLemon percentLemon;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_finance,container,false);
        view = binding.getRoot();
        mModel = new FinanceViewModelImpl(entity,this,getContext(),getActivity());
        binding.setHandler(mModel);
        binding.setEntity(entity);

        initView();

        //顶部滚动广告条
        AutoScrollViewPager();



        return view;
    }


    /**
     * 初始化View
     */
    private void initView() {
        pager = (AutoScrollViewPager) getView(R.id.scroll_pager,view);
        indicator = (ViewGroupIndicator) getView(R.id.scroll_pager_indicator,view);


        mSwipeLayout = (SwipeRefreshLayout) getView(R.id.id_swipe_ly,view);
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);

        percentLemon = (PercentLemon) getView(R.id.percentLemon,view);
        percentLemon.setTextColor(Color.parseColor("#000000"));

    }

    /**
     * 设置滚动ViewPager
     */
    private void AutoScrollViewPager(){

        ids = new ArrayList<>();
        ids.add(R.drawable.image1);
        ids.add(R.drawable.image2);
        ids.add(R.drawable.image3);
        ids.add(R.drawable.image4);
        ids.add(R.drawable.image5);

        autoScrollAdapter = new AutoScrollAdapter(ids,getContext(),pager);
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
    private final Random random = new Random();
    @Override
    public void onRefresh() {
        showToast("Refresh Down");
        float f = random.nextFloat();
//        percentLemon.animatToPercent((float) 100);

        percentLemon.animatToPercent(f * 100);
        mSwipeLayout.setRefreshing(false);
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
