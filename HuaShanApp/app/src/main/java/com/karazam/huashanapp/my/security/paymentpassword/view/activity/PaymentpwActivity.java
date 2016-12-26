package com.karazam.huashanapp.my.security.paymentpassword.view.activity;

import android.databinding.DataBindingUtil;

import com.example.utils.base.BaseActivity;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityPaymentpwBinding;
import com.karazam.huashanapp.manage.paymentmod.view.PaymentmodView;
import com.karazam.huashanapp.my.security.paymentpassword.model.databinding.PaymentpwEntity;
import com.karazam.huashanapp.my.security.paymentpassword.view.PaymentpwView;
import com.karazam.huashanapp.my.security.paymentpassword.viewmodel.PaymentpwViewModel.PaymentpwViewModel;
import com.karazam.huashanapp.my.security.paymentpassword.viewmodel.PaymentpwViewModel.PaymentpwViewModelImpl;

import static com.karazam.huashanapp.HuaShanApplication.securitysPayment;

/**
 * Created by Administrator on 2016/11/28.
 */

public class PaymentpwActivity extends BaseActivity implements PaymentpwView {

    private ActivityPaymentpwBinding binding;
    private PaymentpwEntity entity = new PaymentpwEntity();
    private PaymentpwViewModel mModel;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_paymentpw);
        mModel = new PaymentpwViewModelImpl(this,entity,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
        activities.add(this);
    }

    @Override
    public void dealLogicBeforeInitView() {
        securitysPayment.add(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void dealLogicAfterInitView() {

    }
}
