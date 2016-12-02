package com.karazam.huashanapp.my.transactiondetails.recharge.view.activity;

import android.databinding.DataBindingUtil;
import android.support.v4.widget.SwipeRefreshLayout;

import com.example.utils.base.BaseActivity;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityRechargedetailsBinding;
import com.karazam.huashanapp.my.transactiondetails.recharge.model.datatbinding.RechargedetailsEntity;
import com.karazam.huashanapp.my.transactiondetails.recharge.view.RechargedetailsView;
import com.karazam.huashanapp.my.transactiondetails.recharge.viewmodel.RechargedetailsViewModel;
import com.karazam.huashanapp.my.transactiondetails.recharge.viewmodel.RechargedetailsViewModelImpl;

/**
 * Created by Administrator on 2016/12/2.
 */

public class RechargedetailsActivity extends BaseActivity implements RechargedetailsView,SwipeRefreshLayout.OnRefreshListener {

    private ActivityRechargedetailsBinding binding;
    private RechargedetailsViewModel mModel;
    private RechargedetailsEntity entity = new RechargedetailsEntity();

    private SwipeRefreshLayout swipe_l;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rechargedetails);
        mModel = new RechargedetailsViewModelImpl(this,entity,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
    }

    @Override
    public void dealLogicBeforeInitView() {

    }

    @Override
    public void initView() {
        swipe_l = (SwipeRefreshLayout) getView(R.id.swipe_l);
        swipe_l.setOnRefreshListener(this);
        swipe_l.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
    }

    @Override
    public void dealLogicAfterInitView() {

    }

    @Override
    public void onRefresh() {
        swipe_l.setRefreshing(false);
    }
}
