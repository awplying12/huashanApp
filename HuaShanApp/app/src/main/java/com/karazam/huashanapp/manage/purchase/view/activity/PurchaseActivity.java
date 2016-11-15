package com.karazam.huashanapp.manage.purchase.view.activity;

import android.databinding.DataBindingUtil;

import com.example.utils.base.BaseActivity;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityPurchaseBinding;
import com.karazam.huashanapp.manage.purchase.model.databinding.PurchaseEntity;
import com.karazam.huashanapp.manage.purchase.view.PurchaseView;
import com.karazam.huashanapp.manage.purchase.viewmodel.PurchaseViewModel;
import com.karazam.huashanapp.manage.purchase.viewmodel.PurchaseViewModelImpl;

/**
 * Created by Administrator on 2016/11/15.
 */

public class PurchaseActivity extends BaseActivity implements PurchaseView{

    private ActivityPurchaseBinding binding;
    private PurchaseEntity entity = new PurchaseEntity();
    private PurchaseViewModel mModel;


    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_purchase);
        mModel = new PurchaseViewModelImpl(entity,this,this,this);
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
