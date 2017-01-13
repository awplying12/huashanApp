package com.karazam.huashanapp.my.transactiondetails.myreturn.view.activity;

import android.databinding.DataBindingUtil;

import com.example.utils.base.BaseActivity;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityMyreturndetailsBinding;
import com.karazam.huashanapp.my.transactiondetails.myreturn.mode.databinding.MyreturndetailsEntity;
import com.karazam.huashanapp.my.transactiondetails.myreturn.view.MyreturndetailsView;
import com.karazam.huashanapp.my.transactiondetails.myreturn.viewmodel.MyreturndetailsViewModel;
import com.karazam.huashanapp.my.transactiondetails.myreturn.viewmodel.MyreturndetailsViewModelImpl;

/**
 * Created by Administrator on 2017/1/12.
 */

public class MyreturndetailsActivity extends BaseActivity implements MyreturndetailsView {

    private ActivityMyreturndetailsBinding binding;
    private MyreturndetailsEntity entity = new MyreturndetailsEntity();
    private MyreturndetailsViewModel mModel;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_myreturndetails);
        mModel = new MyreturndetailsViewModelImpl(this,entity,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
    }

    @Override
    public void dealLogicBeforeInitView() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void dealLogicAfterInitView() {

    }
}
