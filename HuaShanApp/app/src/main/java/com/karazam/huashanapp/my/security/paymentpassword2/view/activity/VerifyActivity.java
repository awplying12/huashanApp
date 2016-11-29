package com.karazam.huashanapp.my.security.paymentpassword2.view.activity;

import android.databinding.DataBindingUtil;
import android.view.View;
import android.view.ViewGroup;

import com.example.utils.base.BaseActivity;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityVerifyBinding;
import com.karazam.huashanapp.main.dialog.SMSauthenticationView;
import com.karazam.huashanapp.my.security.paymentpassword2.model.databinding.VerifyEntity;
import com.karazam.huashanapp.my.security.paymentpassword2.view.VerifyView;
import com.karazam.huashanapp.my.security.paymentpassword2.viewmodel.VerifyViewModel.VerifyViewModel;
import com.karazam.huashanapp.my.security.paymentpassword2.viewmodel.VerifyViewModel.VerifyViewModelImpl;
import com.karazam.huashanapp.my.security.paymentpassword3.view.activity.SetpaypsActivity;

import static com.karazam.huashanapp.HuaShanApplication.securitysPayment;

/**
 * Created by Administrator on 2016/11/28.
 */

public class VerifyActivity extends BaseActivity implements VerifyView {

    private ActivityVerifyBinding binding;
    private VerifyViewModel mModel;
    private VerifyEntity entity = new VerifyEntity();

    private SMSauthenticationView smsview;
    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_verify);
        mModel = new VerifyViewModelImpl(this,entity,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
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
        setSMSView();
    }

    /**
     * 设置短息验证View
     */
    private void setSMSView() {
        smsview = new SMSauthenticationView(this);
        smsview.setView((ViewGroup) getView(R.id.content_pl), new SMSauthenticationView.OnAuthenticationListener() {
            @Override
            public void onLeft(View view) {
                smsview.dismiss();
            }

            @Override
            public void onRight(View view) {
                smsview.verification();
            }

            @Override
            public void onHelp(View view) {

            }

            @Override
            public void onResult(boolean result) {
                if(result){
                    toOtherActivity(VerifyActivity.this, SetpaypsActivity.class);
                    smsview.dismiss();
                }
            }
        });

        smsview.setText1("验证手机号");
        smsview.setText2("输入手机尾号5017接收到的验证码");
    }

    /**
     * 弹出手机验证
     */
    @Override
    public void addSMS() {
        smsview.show();
    }
}
