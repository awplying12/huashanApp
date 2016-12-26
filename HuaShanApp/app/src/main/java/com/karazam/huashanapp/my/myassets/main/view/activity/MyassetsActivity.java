package com.karazam.huashanapp.my.myassets.main.view.activity;

import android.databinding.DataBindingUtil;
import android.support.v4.widget.SwipeRefreshLayout;

import com.example.utils.base.BaseActivity;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityMyassetsBinding;
import com.karazam.huashanapp.my.myassets.main.model.databinding.MyassetsEntity;
import com.karazam.huashanapp.my.myassets.main.view.MyassetsView;
import com.karazam.huashanapp.my.myassets.main.viewmodel.MyassetsViewModel;
import com.karazam.huashanapp.my.myassets.main.viewmodel.MyassetsViewModelImpl;

/**
 * Created by Administrator on 2016/12/20.
 */

public class MyassetsActivity extends BaseActivity implements MyassetsView,SwipeRefreshLayout.OnRefreshListener {

    private ActivityMyassetsBinding binding;
    private MyassetsViewModel mModel;
    private MyassetsEntity entity = new MyassetsEntity();

    private SwipeRefreshLayout swl_pl;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_myassets);
        mModel = new MyassetsViewModelImpl(this,entity,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
        activities.add(this);
    }

    @Override
    public void dealLogicBeforeInitView() {

    }

    @Override
    public void initView() {
        swl_pl = (SwipeRefreshLayout) getView(R.id.swl_pl);
        swl_pl.setOnRefreshListener(this);
        swl_pl.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
    }

    @Override
    public void dealLogicAfterInitView() {

    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        swl_pl.setRefreshing(false);
    }
}
