package com.karazam.huashanapp.my.security.paymentpassword.viewmodel.PaymentpwViewModel;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;

import com.karazam.huashanapp.R;
import com.karazam.huashanapp.main.dialog.ActionSheetDialog;
import com.karazam.huashanapp.main.dialog.PaymentpasswrodView;
import com.karazam.huashanapp.manage.paymentmod.model.databinding.PaymentmodEntity;
import com.karazam.huashanapp.my.security.paymentpassword.model.databinding.PaymentpwEntity;
import com.karazam.huashanapp.my.security.paymentpassword.view.PaymentpwView;
import com.karazam.huashanapp.my.security.paymentpassword.view.activity.ForgetActivity;
import com.karazam.huashanapp.my.security.paymentpassword.view.activity.ModifyActivity;
import com.karazam.huashanapp.my.security.paymentpassword.view.activity.PaymentpwActivity;

import java.io.File;

/**
 * Created by Administrator on 2016/11/28.
 */

public class PaymentpwViewModelImpl extends PaymentpwViewModel {

    private PaymentpwView mView;
    private PaymentpwEntity mEntity;
    private Context context;
    private PaymentpwActivity activity;

    private PaymentpasswrodView paymentpasswrodView;

    public PaymentpwViewModelImpl(PaymentpwView mView, PaymentpwEntity mEntity, Context context, PaymentpwActivity activity) {
        this.mView = mView;
        this.mEntity = mEntity;
        this.context = context;
        this.activity = activity;

        setDialog();
    }

    @Override
    public void onBack(View view) {
        mView.FinishActivity(activity);
    }

    /**
     * 支付密码
     * @param view
     */
    @Override
    public void onPayment(View view) {
        mView.showToast("支付密码");
        paymentpasswrodView.show();
    }

    private void setDialog(){
        paymentpasswrodView = new PaymentpasswrodView(context);
        paymentpasswrodView.setView((ViewGroup) mView.getView(R.id.content_pl), new PaymentpasswrodView.OnPaymentpasswrodListener() {
            @Override
            public void onModify() {
                mView.toOtherActivity(activity, ModifyActivity.class);
                paymentpasswrodView.dismiss();
            }

            @Override
            public void onForget() {
                mView.toOtherActivity(activity, ForgetActivity.class);
                paymentpasswrodView.dismiss();
            }
        });

    }
}
