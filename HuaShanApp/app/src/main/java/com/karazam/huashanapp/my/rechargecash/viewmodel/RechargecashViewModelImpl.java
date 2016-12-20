package com.karazam.huashanapp.my.rechargecash.viewmodel;

import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.main.dialog.PromptDialog;
import com.karazam.huashanapp.my.realname.view.activity.UnauthorizedActivity;
import com.karazam.huashanapp.my.recharge.main.view.activity.RechargeActivity;
import com.karazam.huashanapp.my.rechargecash.model.databinding.RechargecashEntity;
import com.karazam.huashanapp.my.rechargecash.view.RechargecashView;
import com.karazam.huashanapp.my.rechargecash.view.activity.RechargecashActivity;
import com.karazam.huashanapp.my.transactiondetails.main.view.activity.TransactionActivity;
import com.karazam.huashanapp.my.withdrawals.main.view.activity.WithdrawalsActivity;

/**
 * Created by Administrator on 2016/12/19.
 */

public class RechargecashViewModelImpl extends RechargecashViewModel {

    private RechargecashView mView;
    private RechargecashEntity mEntity;
    private Context context;
    private RechargecashActivity activity;

    private PromptDialog certificationDialog;;

    public RechargecashViewModelImpl(RechargecashView mView, RechargecashEntity mEntity, Context context, RechargecashActivity activity) {
        this.mView = mView;
        this.mEntity = mEntity;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void onBack(View view) {
        mView.FinishActivity(activity);
    }


    /**
     * 提现
     * @param view
     */
    @Override
    public void Withdrawals(View view) {
        setCertificationDialog();
        if(!HuaShanApplication.userInformation.isStatus()){

            if(certificationDialog != null){
                certificationDialog.setPrompt("提现需要实名认证","您要前往实名认证吗？");
                certificationDialog.show();
            }

            return;
        }
        mView.toOtherActivity(activity, WithdrawalsActivity.class);
    }

    /**
     * 充值
     * @param view
     */
    @Override
    public void Recharge(View view) {
        setCertificationDialog();
        if(!HuaShanApplication.userInformation.isStatus()){

            if(certificationDialog != null){
                certificationDialog.setPrompt("充值需要实名认证","您要前往实名认证吗？");
                certificationDialog.show();
            }

            return;
        }
        mView.toOtherActivity(activity, RechargeActivity.class);
    }

    /**
     * 交易记录
     * @param view
     */
    @Override
    public void Transaction(View view) {
        mView.toOtherActivity(activity, TransactionActivity.class);
    }


    private void setCertificationDialog(){
        certificationDialog = new PromptDialog(activity);
        certificationDialog.setMod(PromptDialog.MOD1);


        certificationDialog.setClick("否", "是", new PromptDialog.OnDialogListener() {
            @Override
            public void onleft(View view) {
                if(certificationDialog != null){
                    certificationDialog.dismiss();
                }
            }

            @Override
            public void onRight(View view) {
                mView.toOtherActivity(activity, UnauthorizedActivity.class);
                if(certificationDialog != null){
                    certificationDialog.dismiss();
                }
            }
        });

    }


}
