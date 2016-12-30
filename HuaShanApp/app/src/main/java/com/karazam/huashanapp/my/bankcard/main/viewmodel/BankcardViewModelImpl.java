package com.karazam.huashanapp.my.bankcard.main.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.main.dialog.PromptDialog;
import com.karazam.huashanapp.my.bankcard.bindcard.view.activity.BindcardActivity;
import com.karazam.huashanapp.my.bankcard.main.model.databinding.BankcardEntity;
import com.karazam.huashanapp.my.bankcard.main.view.BankcardView;
import com.karazam.huashanapp.my.bankcard.main.view.activity.BankcardActivity;
import com.karazam.huashanapp.my.realname.view.activity.UnauthorizedActivity;

/**
 * Created by Administrator on 2016/12/26.
 */

public class BankcardViewModelImpl extends BankcardViewModel{

    private BankcardView mView;
    private BankcardEntity mEntity;
    private Context context;
    private BankcardActivity activity;

    private PromptDialog certificationDialog;

    public BankcardViewModelImpl(BankcardView mView, BankcardEntity mEntity, Context context, BankcardActivity activity) {
        this.mView = mView;
        this.mEntity = mEntity;
        this.context = context;
        this.activity = activity;


        setCertificationDialog();
    }

    @Override
    public void onBack(View view) {
        mView.FinishActivity(activity);
    }

    /**
     * 绑定银行卡
     * @param view
     */
    @Override
    public void toBindcard(View view) {

        if(!HuaShanApplication.certificationStatus){

            if(certificationDialog != null){
                certificationDialog.show();
            }

            return;
        }

        Intent intent = new Intent(activity, BindcardActivity.class);
        intent.putExtra("flag",flag);
        activity.startActivity(intent);
    }



    private void setCertificationDialog(){
        certificationDialog = new PromptDialog(context);
        certificationDialog.setMod(PromptDialog.MOD1);
        certificationDialog.setPrompt("绑卡需要实名认证","您要前往实名认证吗？");

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
