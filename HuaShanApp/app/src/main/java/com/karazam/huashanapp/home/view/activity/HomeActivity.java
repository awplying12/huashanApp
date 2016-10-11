package com.karazam.huashanapp.home.view.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.utils.Adapter.PagerFragmentAdapter;
import com.example.utils.base.BaseActivity;
import com.example.utils.custom.ScrollableViewPager;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityHomeBinding;
import com.karazam.huashanapp.finance.view.fragment.FinanceFragment;
import com.karazam.huashanapp.home.model.databinding.HomeEntity;
import com.karazam.huashanapp.home.view.HomeView;
import com.karazam.huashanapp.home.viewmodel.HomeViewModel;
import com.karazam.huashanapp.home.viewmodel.HomeViewModelImpl;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/11.
 */

public class HomeActivity extends BaseActivity implements HomeView {

    private HomeEntity mEntity = new HomeEntity();
    private ActivityHomeBinding binding;
    private HomeViewModel mModel;

    private int limit = 3;
    private ScrollableViewPager viewPager; //主体viewPager

    private FinanceFragment financeFragment;



    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this,R.layout.activity_home);
        mModel = new  HomeViewModelImpl(this,mEntity,this,this);
        binding.setHandler(mModel);
        binding.setEntity(mEntity);
    }

    @Override
    public void dealLogicBeforeInitView() {

    }

    @Override
    public void initView() {
        viewPager = (ScrollableViewPager) getView(R.id.viewpager_home);
    }

    @Override
    public void dealLogicAfterInitView() {
        setViewPager();
    }

    /**
     * 设置主体ViewPage
     */
    private void setViewPager() {
        ArrayList<Fragment> list = new ArrayList<>();
        list.add(new FinanceFragment());
        list.add(new FinanceFragment());
        list.add(new FinanceFragment());
        list.add(new FinanceFragment());

        viewPager.setScrollAble(false);
        viewPager.setOffscreenPageLimit(limit); //设置viewpager缓存一侧Fragment的数量
        viewPager.setAdapter(new PagerFragmentAdapter(getSupportFragmentManager(), list));
        viewPager.setCurrentItem(0);

    }
}
