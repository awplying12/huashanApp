package com.karazam.huashanapp.my.myprofits.main.view.activity;

import android.databinding.DataBindingUtil;

import com.example.utils.base.BaseActivity;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityMyprofitsBinding;
import com.karazam.huashanapp.my.myprofits.main.model.databinding.MyprofitsEntity;
import com.karazam.huashanapp.my.myprofits.main.view.MyprofitsView;
import com.karazam.huashanapp.my.myprofits.main.viewmodel.MyprofitsViewModel;
import com.karazam.huashanapp.my.myprofits.main.viewmodel.MyprofitsViewModelImpl;

/**
 * Created by Administrator on 2016/12/21.
 */

public class MyprofitsActivity extends BaseActivity implements MyprofitsView{

    private ActivityMyprofitsBinding binding;
    private MyprofitsViewModel mModel;
    private MyprofitsEntity entity = new MyprofitsEntity();

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_myprofits);
        mModel = new MyprofitsViewModelImpl(this,entity,this,this);
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
