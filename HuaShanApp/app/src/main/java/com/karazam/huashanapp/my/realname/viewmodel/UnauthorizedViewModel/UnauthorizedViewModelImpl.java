package com.karazam.huashanapp.my.realname.viewmodel.UnauthorizedViewModel;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.my.realname.model.databinding.RealnameEntity;
import com.karazam.huashanapp.my.realname.view.UnauthorizedView;
import com.karazam.huashanapp.my.realname.view.activity.AuthenticatedActivity;
import com.karazam.huashanapp.my.realname.view.activity.UnauthorizedActivity;
import com.karazam.huashanapp.my.security.paymentpassword3.view.activity.SetpaypsActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/11/24.
 */

public class UnauthorizedViewModelImpl extends UnauthorizedViewModel {

    private UnauthorizedView mView;
    private RealnameEntity mEntity;
    private Context context;
    private UnauthorizedActivity activity;

    public UnauthorizedViewModelImpl(UnauthorizedView mView, RealnameEntity mEntity, Context context, UnauthorizedActivity activity) {
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
     * 下一步
     * @param view
     */
    @Override
    public void onNextstep(View view) {
//        mView.showToast("onNextstep");
        mView.addSMSView();
    }

    /**
     * 清除姓名
     * @param view
     */
    @Override
    public void cleanName(View view) {
        use_name.setText("");
    }

    /**
     * 清除身份证号
     * @param view
     */
    @Override
    public void cleanIdnum(View view) {
        id_num.setText("");
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
        bank.setText("");
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
     * 开始认证
     */
    private static boolean isshow = false;
    @Override
    public void onAuthentication() {

        if(isshow){
            return;
        }
        isshow = true;
        Timer timer = new Timer();
        TimerTask tk = new TimerTask() {
            @Override
            public void run() {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mView.showToast("绑定成功");
//                        HuaShanApplication.userInformation.setStatus(true);
//                        HuaShanApplication.userInformationR.set(HuaShanApplication.userInformation);

                        Intent intent = new Intent(activity, SetpaypsActivity.class);
                        intent.putExtra("isRealName","realName");
                        activity.startActivity(intent);
                        mView.FinishActivity(activity);
                        mView.disSMSView();
                        isshow = false;
                    }
                });
            }
        };
        timer.schedule(tk,3000);
    }
}
