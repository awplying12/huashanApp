package com.karazam.huashanapp.my.security.paymentpassword2.viewmodel.VerifyViewModel2;

import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.my.security.paymentpassword2.model.databinding.VerifyEntity;
import com.karazam.huashanapp.my.security.paymentpassword2.view.VerifyView2;
import com.karazam.huashanapp.my.security.paymentpassword2.view.activity.VerifyActivity;
import com.karazam.huashanapp.my.security.paymentpassword2.view.activity.VerifyActivity2;
import com.karazam.huashanapp.my.security.paymentpassword3.view.activity.SetpaypsActivity;

/**
 * Created by Administrator on 2016/11/28.
 */

public class VerifyViewModelImpl2 extends VerifyViewModel2 {

    private VerifyView2 mView;
    private VerifyEntity mEntity;
    private Context context;
    private VerifyActivity2 activity2;

    public VerifyViewModelImpl2(VerifyView2 mView, VerifyEntity mEntity, Context context, VerifyActivity2 activity2) {
        this.mView = mView;
        this.mEntity = mEntity;
        this.context = context;
        this.activity2 = activity2;
    }

    @Override
    public void onBack(View view) {
        mView.FinishActivity(activity2);
    }

    /**
     * 下一步
     * @param view
     */
    @Override
    public void onNextstep(View view) {
        mView.toOtherActivity(activity2, SetpaypsActivity.class);
    }

    /**
     * 清除卡号
     * @param view
     */
    @Override
    public void cleanCard(View view) {
        ed_card.setText("");
    }

    /**
     * 清除身份证
     * @param view
     */
    @Override
    public void cleanID(View view) {
        ed_id.setText("");
    }
}
