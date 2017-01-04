package com.karazam.huashanapp.my.recharge.main.viewmodel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.main.dialog.SMSauthenticationView;
import com.karazam.huashanapp.my.recharge.main.model.databinding.RechargeEntity;
import com.karazam.huashanapp.my.recharge.main.view.RechargeView;
import com.karazam.huashanapp.my.recharge.main.view.activity.RechargeActivity;

/**
 * Created by Administrator on 2016/12/1.
 */

public class RechargeViewModelImpl extends RechargeViewModel {

    private RechargeView mView;
    private RechargeEntity mEntity;
    private Context context;
    private RechargeActivity activity;

    private SMSauthenticationView smsview;

    public RechargeViewModelImpl(RechargeView mView, RechargeEntity mEntity, Context context, RechargeActivity activity) {
        this.mView = mView;
        this.mEntity = mEntity;
        this.context = context;
        this.activity = activity;

        setSMSview();
    }



    @Override
    public void onBack(View view) {
        mView.FinishActivity(activity);
    }

    /**
     * 下一步
     * @param view
     */
    @Override
    public void nextStep(View view) {
        mView.showToast("nextStep");
        smsview.show();
    }

    /**
     * 充值
     */
    @Override
    public void Recharge() {
        mView.rechargeSuccess();
        smsview.dismiss();
    }

    /**
     * 设置短息验证
     */
    private void setSMSview() {
        smsview = new SMSauthenticationView(context);

        smsview.setView(HuaShanApplication.account,"",(ViewGroup) mView.getView(R.id.content_pl), new SMSauthenticationView.OnAuthenticationListener() {
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
                    Recharge();

                }
            }
        });
    }
}
