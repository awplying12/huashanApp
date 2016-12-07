package com.karazam.huashanapp.my.myreturn.main.view.activity;

import android.databinding.DataBindingUtil;

import com.example.utils.base.BaseActivity;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityMyreturnBinding;
import com.karazam.huashanapp.my.myreturn.main.model.databinding.MyReturnEntity;
import com.karazam.huashanapp.my.myreturn.main.view.MyReturnView;
import com.karazam.huashanapp.my.myreturn.main.viewmodel.MyReturnViewModel;
import com.karazam.huashanapp.my.myreturn.main.viewmodel.MyReturnViewModelImpl;

/**
 * Created by Administrator on 2016/12/7.
 */

public class MyReturnActivity extends BaseActivity implements MyReturnView {

    private ActivityMyreturnBinding binding;
    private MyReturnEntity entity = new MyReturnEntity();
    private MyReturnViewModel mModel;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_myreturn);
        mModel = new MyReturnViewModelImpl(entity,this,this,this);
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
