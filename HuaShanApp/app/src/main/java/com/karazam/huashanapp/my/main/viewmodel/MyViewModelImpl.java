package com.karazam.huashanapp.my.main.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.main.dialog.PromptDialog;
import com.karazam.huashanapp.my.bankcard.main.view.activity.BankcardActivity;
import com.karazam.huashanapp.my.main.model.databinding.MyEntity;
import com.karazam.huashanapp.my.main.view.MyView;
import com.karazam.huashanapp.my.message.main.view.activity.MessageActivity;
import com.karazam.huashanapp.my.myfinancing.main.model.databinding.MyfinanceEntity;
import com.karazam.huashanapp.my.myfinancing.main.view.activity.MyfinanceActivity;
import com.karazam.huashanapp.my.myreturn.main.view.activity.MyReturnActivity;
import com.karazam.huashanapp.my.mytransfer.main.view.activity.MytransferActivity;
import com.karazam.huashanapp.my.realname.view.activity.AuthenticatedActivity;
import com.karazam.huashanapp.my.realname.view.activity.UnauthorizedActivity;
import com.karazam.huashanapp.my.recharge.main.view.activity.RechargeActivity;
import com.karazam.huashanapp.my.rechargecash.view.activity.RechargecashActivity;
import com.karazam.huashanapp.my.setup.view.activity.SetupActivity;
import com.karazam.huashanapp.my.transactiondetails.main.view.activity.TransactionActivity;
import com.karazam.huashanapp.my.withdrawals.main.view.activity.WithdrawalsActivity;

/**
 * Created by Administrator on 2016/10/12.
 */

public class MyViewModelImpl extends MyViewModel {

    private MyEntity mEntity;
    private MyView mView;
    private Context context;
    private Activity activity;

    private PromptDialog certificationDialog;;

    public MyViewModelImpl(MyEntity mEntity, MyView mView, Context context, Activity activity) {
        this.mEntity = mEntity;
        this.mView = mView;
        this.context = context;
        this.activity = activity;


    }

    @Override
    public void onBack(View view) {
        mView.FinishActivity(activity);
    }


    /**
     * 设置
     * @param view
     */
    @Override
    public void setUp(View view) {
            mView.toOtherActivity(activity, SetupActivity.class);
    }

    /**
     * 充值/提现 我的余额
     * @param view
     */
    @Override
    public void Rechargecash(View view) {
        mView.toOtherActivity(activity, RechargecashActivity.class);
    }

    /**
     * 我的理财
     * @param view
     */
    @Override
    public void Myfinance(View view) {

        Intent intent = new Intent(activity,MyfinanceActivity.class);
        activity.startActivityForResult(intent,67);
    }

    /**
     * 我的转让
     * @param view
     */
    @Override
    public void Mytransfer(View view) {
        Intent intent = new Intent(activity,MytransferActivity.class);
        activity.startActivityForResult(intent,67);
    }

    /**
     * 我的回款
     * @param view
     */
    @Override
    public void MyReturn(View view) {
        Intent intent = new Intent(activity,MyReturnActivity.class);
        activity.startActivityForResult(intent,67);
    }

    /**
     * 银行卡
     * @param view
     */
    @Override
    public void BankCard(View view) {

//        if(HuaShanApplication.userInformation.isStatus()){
//            mView.toOtherActivity(activity, AuthenticatedActivity.class);
//        }else {
//            mView.toOtherActivity(activity, UnauthorizedActivity.class);
//        }

        mView.toOtherActivity(activity, BankcardActivity.class);

    }

    /**
     * 交易记录
     * @param view
     */
    @Override
    public void Transaction(View view) {
        mView.toOtherActivity(activity, TransactionActivity.class);
    }

    /**
     * 信息
     * @param view
     */
    @Override
    public void Message(View view) {
        mView.toOtherActivity(activity, MessageActivity.class);
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
