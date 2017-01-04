package com.karazam.huashanapp.my.bankcard.bindcard.viewmodel;

import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.main.dialog.PromptDialog;
import com.karazam.huashanapp.my.bankcard.bindcard.model.databinding.BindcardEntity;
import com.karazam.huashanapp.my.bankcard.bindcard.view.BindcardView;
import com.karazam.huashanapp.my.bankcard.bindcard.view.activity.BindcardActivity;
import com.karazam.huashanapp.my.realname.view.activity.UnauthorizedActivity;

/**
 * Created by Administrator on 2016/12/30.
 */

public class BindcardViewModelImpl extends BindcardViewModel {

    private BindcardView mView;
    private BindcardEntity mEntity;
    private Context context;
    private BindcardActivity activity;




    public BindcardViewModelImpl(BindcardView mView, BindcardEntity mEntity, Context context, BindcardActivity activity) {
        this.mView = mView;
        this.mEntity = mEntity;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void onBack(View view) {
        mView.FinishActivity(activity);
    }


    @Override
    public void onNextstep(View view) {
        mView.showToast("onNextstep");
    }

    /**
     * 清除姓名
     * @param view
     */
    @Override
    public void cleanName(View view) {
//        use_name.setText("");
    }

    /**
     * 清除身份证号
     * @param view
     */
    @Override
    public void cleanIdnum(View view) {
//        id_num.setText("");
    }

    /**
     * 清除银行卡号
     * @param view
     */
    @Override
    public void cleanCardnum(View view) {
        card_num.setText("");
    }

    /**
     * 清除银行
     * @param view
     */
    @Override
    public void cleanBank(View view) {
//        bank.setText("");
    }

    /**
     * 清除电话号码
     * @param view
     */
    @Override
    public void cleanPhonenum(View view) {
        phone_num.setText("");
    }

    /**
     * 设置银行卡类型
     * @param view
     */
    @Override
    public void getBankData(View view) {
        mView.showToast("getBankData");
    }

}
