package com.karazam.huashanapp.my.transactiondetails.main.view.activity;

import android.databinding.DataBindingUtil;

import com.example.utils.base.BaseActivity;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityTransactionBinding;
import com.karazam.huashanapp.my.transactiondetails.main.model.databinding.TransactionEntity;
import com.karazam.huashanapp.my.transactiondetails.main.view.TransactionView;
import com.karazam.huashanapp.my.transactiondetails.main.viewmodel.TransactionViewModel;
import com.karazam.huashanapp.my.transactiondetails.main.viewmodel.TransactionViewModelImpl;

/**
 * Created by Administrator on 2016/12/8.
 */

public class TransactionActivity extends BaseActivity implements TransactionView{

    private ActivityTransactionBinding binding;
    private TransactionViewModel mModel;
    private TransactionEntity entity = new TransactionEntity();

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_transaction);
        mModel = new TransactionViewModelImpl(this,entity,this,this);
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
