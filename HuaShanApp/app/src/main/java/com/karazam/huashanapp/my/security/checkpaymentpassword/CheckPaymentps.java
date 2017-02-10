package com.karazam.huashanapp.my.security.checkpaymentpassword;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.main.dialog.PromptDialog.PromptDialog;
import com.karazam.huashanapp.main.push.PushReceiver;
import com.karazam.huashanapp.main.refreshToken.RefreshToken;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.realname.view.activity.UnauthorizedActivity;
import com.karazam.huashanapp.my.security.checkpaymentpassword.retrofit.CheckPaymentpsDataSource;
import com.karazam.huashanapp.my.security.paymentpassword2.view.activity.VerifyActivity;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/2/10.
 */

public class CheckPaymentps {

    private CheckPaymentpsDataSource dataSource;

    private CheckPaymentpsInterface mCheckPaymentpsInterface;

    public interface CheckPaymentpsInterface{

        void onSuccess();

        void onFail(String s);

        void onError(Throwable e);
    }

    private PromptDialog promptDialog;

    public CheckPaymentps(Activity activity, CheckPaymentpsInterface checkPaymentpsInterface) {
        dataSource = new CheckPaymentpsDataSource();
        mCheckPaymentpsInterface = checkPaymentpsInterface;

        Check(true);
        setPromptDialog(activity);
    }

    public void Check(final boolean isfirst){

        dataSource.checkPaymentps()
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<BaseReturn>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mCheckPaymentpsInterface == null){
                            return;
                        }
                        mCheckPaymentpsInterface.onError(e);
                    }

                    @Override
                    public void onNext(BaseReturn baseReturn) {

                        if (baseReturn.isSuccess()){
                            if (mCheckPaymentpsInterface == null){
                                return;
                            }
                            mCheckPaymentpsInterface.onSuccess();
                        } else if(baseReturn.getCode().equals("666")){


                            if(!isfirst){ //token 过期处理
                                HuaShanApplication.safeExit();
                                return;
                            }
                            RefreshToken.refresh(new RefreshToken.RefreshTokenInterface() {
                                @Override
                                public void onSuccess(String s) {
                                    Check(false);
                                }

                                @Override
                                public void onFaile(String s) {
                                    HuaShanApplication.safeExit();
                                }

                                @Override
                                public void onError(Throwable e) {
                                    HuaShanApplication.safeExit();
                                }
                            });



                        } else {

                            if(promptDialog != null){
                                promptDialog.show();
                            }

                            if (mCheckPaymentpsInterface == null){
                                return;
                            }
                            mCheckPaymentpsInterface.onFail(baseReturn.getMessage());
                        }
                    }
                });
    }

    private void setPromptDialog(final Activity activity){

        promptDialog = new PromptDialog(activity);
        promptDialog.setMod(PromptDialog.MOD1);
        promptDialog.setPrompt("提示","您还没有设置支付密码！");

        promptDialog.setClick("暂不设置", "去设置", new PromptDialog.OnDialogListener() {
            @Override
            public void onleft(View view) {
                if(promptDialog != null){
                    promptDialog.dismiss();
                }
            }

            @Override
            public void onRight(View view) {
//                mView.toOtherActivity(activity, UnauthorizedActivity.class);

                Intent intent = new Intent(activity, VerifyActivity.class);
                activity.startActivity(intent);

                if(promptDialog != null){
                    promptDialog.dismiss();
                }
            }
        });
    }
}
