package com.karazam.huashanapp.my.transactiondetails.withdrawals.view.activity;

import android.databinding.DataBindingUtil;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.example.utils.base.BaseActivity;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityWithdrawalsdetailsBinding;
import com.karazam.huashanapp.my.transactiondetails.withdrawals.model.databinding.WithdrawalsdetailsEntity;
import com.karazam.huashanapp.my.transactiondetails.withdrawals.view.WithdrawalsdetailsView;
import com.karazam.huashanapp.my.transactiondetails.withdrawals.view.view.StateAdapter;
import com.karazam.huashanapp.my.transactiondetails.withdrawals.view.view.StateitemBean;
import com.karazam.huashanapp.my.transactiondetails.withdrawals.viewmodel.WithdrawalsdetailsViewModel;
import com.karazam.huashanapp.my.transactiondetails.withdrawals.viewmodel.WithdrawalsdetailsViewModelImpl;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/1.
 */

public class WithdrawalsdetailsActivity extends BaseActivity implements WithdrawalsdetailsView,SwipeRefreshLayout.OnRefreshListener{

    private ActivityWithdrawalsdetailsBinding binding;
    private WithdrawalsdetailsViewModel mModel;
    private WithdrawalsdetailsEntity entity = new WithdrawalsdetailsEntity();

    private RecyclerView rl_state;

    private SwipeRefreshLayout swipe_l;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_withdrawalsdetails);
        mModel = new WithdrawalsdetailsViewModelImpl(this,entity,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
        activities.add(this);
    }

    @Override
    public void dealLogicBeforeInitView() {

    }

    @Override
    public void initView() {
        rl_state = (RecyclerView) getView(R.id.rl_state);
        LinearLayoutManager lm = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rl_state.setLayoutManager(lm);

        swipe_l = (SwipeRefreshLayout) getView(R.id.swipe_l);
        swipe_l.setOnRefreshListener(this);
        swipe_l.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
    }

    @Override
    public void dealLogicAfterInitView() {

        setRecyclerView();

    }

    /**
     * 设置RecyclerView
     */
    private void setRecyclerView() {

        ArrayList<StateitemBean> list = new ArrayList<>();
        list.add(new StateitemBean("提现申请","11-13 14:48"));
        list.add(new StateitemBean("审核通过",""));
        list.add(new StateitemBean("银行处理中",""));
        list.add(new StateitemBean("完成",""));


        rl_state.setAdapter(new StateAdapter(list,this,rl_state));


    }

    @Override
    public void onRefresh() {
        swipe_l.setRefreshing(false);
    }
}
