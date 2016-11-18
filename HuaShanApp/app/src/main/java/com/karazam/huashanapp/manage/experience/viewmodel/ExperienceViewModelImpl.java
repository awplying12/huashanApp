package com.karazam.huashanapp.manage.experience.viewmodel;

import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.main.dialog.PromptDialog;
import com.karazam.huashanapp.manage.experience.model.databinding.ExperienceEntity;
import com.karazam.huashanapp.manage.experience.view.ExperienceView;
import com.karazam.huashanapp.manage.experience.view.activity.ExperienceActivity;
import com.karazam.huashanapp.manage.purchase.view.activity.PurchaseActivity;

/**
 * Created by Administrator on 2016/11/15.
 */

public class ExperienceViewModelImpl extends ExperienceViewModel {

    private ExperienceEntity mEntity;
    private ExperienceView mView;
    private Context context;
    private ExperienceActivity activity;

    private PromptDialog certificationDialog;

    public ExperienceViewModelImpl(ExperienceEntity mEntity, ExperienceView mView, Context context, ExperienceActivity activity) {
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

    @Override
    public void toPurchase(View view) {
        if(!HuaShanApplication.certificationStatus){

                if(certificationDialog != null){
                    certificationDialog.show();
                }

            return;
        }
        mView.toOtherActivity(activity, PurchaseActivity.class);
    }

    private void setCertificationDialog(){
        certificationDialog = new PromptDialog(context);

        certificationDialog.setPrompt("购买需要实名认证","您要前往实名认证吗？");

        certificationDialog.setClick("否", "是", new PromptDialog.OnDialogListener() {
            @Override
            public void onleft(View view) {
                mView.showToast("否");
                if(certificationDialog != null){
                    certificationDialog.dismiss();
                }
            }

            @Override
            public void onRight(View view) {
                mView.showToast("是");
            }
        });


    }
}
