package com.karazam.huashanapp.my.transactiondetails.investment.view.activity;

import android.databinding.DataBindingUtil;
import android.support.v4.widget.SwipeRefreshLayout;

import com.example.utils.base.BaseActivity;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityInvestmentBinding;
import com.karazam.huashanapp.my.transactiondetails.investment.model.databinding.InvestmentEntity;
import com.karazam.huashanapp.my.transactiondetails.investment.view.InvestmentView;
import com.karazam.huashanapp.my.transactiondetails.investment.viewmodel.InvestmentViewModel;
import com.karazam.huashanapp.my.transactiondetails.investment.viewmodel.InvestmentViewModelImpl;

/**
 * Created by Administrator on 2016/12/9.
 */

public class InvestmentActivity extends BaseActivity implements InvestmentView,SwipeRefreshLayout.OnRefreshListener{

    private ActivityInvestmentBinding binding;
    private InvestmentViewModel mModel;
    private InvestmentEntity entity = new InvestmentEntity();

    private SwipeRefreshLayout swipe_l;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_investment);
        mModel = new InvestmentViewModelImpl(this,entity,this,this);
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
