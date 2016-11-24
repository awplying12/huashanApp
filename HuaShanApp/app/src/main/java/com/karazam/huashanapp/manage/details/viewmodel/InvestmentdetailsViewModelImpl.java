package com.karazam.huashanapp.manage.details.viewmodel;

import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.main.dialog.PromptDialog;
import com.karazam.huashanapp.manage.details.model.databinding.InvestmentdetailsEntity;

import com.karazam.huashanapp.manage.details.view.InvestmentdetailsView;
import com.karazam.huashanapp.manage.details.view.activity.InvestmentdetailsActivity;
import com.karazam.huashanapp.manage.purchase.view.activity.PurchaseActivity;
import com.karazam.huashanapp.my.realname.view.activity.UnauthorizedActivity;

/**
 * Created by Administrator on 2016/11/8.
 */

public class InvestmentdetailsViewModelImpl extends InvestmentdetailsViewModel {

    private InvestmentdetailsEntity mEntity;
    private InvestmentdetailsView mView;
    private Context context;
    private InvestmentdetailsActivity activity;

    private PromptDialog certificationDialog;

    public InvestmentdetailsViewModelImpl(InvestmentdetailsEntity mEntity, InvestmentdetailsView mView, Context context, InvestmentdetailsActivity activity) {
        this.mEntity = mEntity;
        this.mView = mView;
        this.context = context;
        this.activity = activity;

        setCertificationDialog();
    }

    @Override
    public void onBack(View view) {
        mView.FinishActivity(activity);
    }

    /**
     * 项目信息
     * @param view
     */
    @Override
    public void onInformation(View view) {
        mView.showToast("项目信息");
        mView.setTab(0);
    }

    /**
     * 购买记录
     * @param view
     */
    @Override
    public void onRecord(View view) {
        mView.showToast("购买记录");
        mView.setTab(1);
    }

    /**
     * 项目进度
     * @param view
     */
    @Override
    public void onSpeed(View view) {
        mView.showToast("项目进度");
        mView.setTab(2);
    }

    /**
     * 立即购买
     * @param view
     */
    @Override
    public void toPurchase(View view) {
        if(!HuaShanApplication.userInformation.isStatus()){

            if(certificationDialog != null){
                certificationDialog.show();
            }

            return;
        }
        mView.toOtherActivity(activity, PurchaseActivity.class);
    }

    private void setCertificationDialog(){
        certificationDialog = new PromptDialog(context);
        certificationDialog.setMod(PromptDialog.MOD1);
        certificationDialog.setPrompt("购买需要实名认证","您要前往实名认证吗？");

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
