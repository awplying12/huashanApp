package com.karazam.huashanapp.user.findpassword.main.viewmodel;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.karazam.huashanapp.R;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.main.retrofit.smsverification.SendSmsDataSource;
import com.karazam.huashanapp.main.retrofit.smsverification.VerifySmsDataSource;
import com.karazam.huashanapp.user.findpassword.main.model.databinding.FindpasswordEntity;
import com.karazam.huashanapp.user.findpassword.main.model.retrofit.FindpasswordDataSource;
import com.karazam.huashanapp.user.findpassword.main.view.FindpasswordView;
import com.karazam.huashanapp.user.findpassword.main.view.activity.FindpasswordActivity;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/31.
 */

public class FindpasswordViewModelImpl extends FindpasswordViewModel {

    private FindpasswordEntity mEntity;
    private FindpasswordView mView;
    private Context context;
    private FindpasswordActivity activity;

    private FindpasswordDataSource dataSource;

    private SendSmsDataSource sendSmsDataSource;
    private VerifySmsDataSource verifySmsDataSource;

    public FindpasswordViewModelImpl(FindpasswordEntity mEntity, FindpasswordView mView, Context context, FindpasswordActivity activity) {
        this.mEntity = mEntity;
        this.mView = mView;
        this.context = context;
        this.activity = activity;
        dataSource = new FindpasswordDataSource();

        sendSmsDataSource = new SendSmsDataSource();
        verifySmsDataSource = new VerifySmsDataSource();

        checkText();
//        reacQuire(null);
    }

    @Override
    public void onBack(View view) {
        activity.FinishActivity(activity);
    }

    /**
     * 检查信息
     */
    public void checkText(){
        tv_time = (TextView)mView.getView(R.id.time);

        RxView.of(tv_time).bind(time, new Rx.Action<View, Integer>() {
            @Override
            public void call(View target, Integer integer) {
                TextView view = (TextView) target;


                if(integer == 0 ){
                    view.setText("重新发送");
//                    view.setTextColor(Color.parseColor("#ffffff"));
                    target.setClickable(true);
                }else {
                    String time = integer >=100? integer+"" : (integer >= 10? "0"+integer: "00"+integer);
                    view.setText("请稍后 "+"("+time+")");
//                    view.setTextColor(Color.parseColor("#d0d0d0"));
                    target.setClickable(false);
                }

            }
        });

        com.jakewharton.rxbinding.view.RxView.clicks(tv_time)
                .throttleFirst(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        reacQuire(null);
                    }
                });
    }

    /**
     * 获取验证码
     */
    int s = 120;
    @Override
    public void reacQuire(View view){

        s = 120;
        final Timer timer = new Timer();
        TimerTask tk = new TimerTask() {
            @Override
            public void run() {
                s--;
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        time.set(s);
                    }
                });

                if(s == 0){
                    timer.cancel();

                }

            }
        };
        timer.schedule(tk,1000,1000);

        sendSms();
    }

    /**
     * 下一步
     * @param view
     */
    @Override
    public void onNextStep(View view) {

        verifySms();
        activity.showProgressDialog();
    }


    /**
     * 找回密码
     */
    @Override
    public void Findpassword() {

        String phonenum = et_phonenum.getText().toString();
        String code = ed_code.getText().toString();
        String password = ed_password.getText().toString();

        dataSource.findPassword(phonenum,password,code)
                .throttleFirst(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<BaseReturn>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("Findpassword","e   :  "+e.toString());
                        mView.FindpasswordFaile(e.toString());
                        activity.dissmissProgressDialog();

                    }

                    @Override
                    public void onNext(BaseReturn baseReturn) {

                        if(baseReturn.isSuccess()){
                            mView.FindpasswordSuccess(baseReturn.toString());
                            activity.dissmissProgressDialog();
                        }else {
                            mView.FindpasswordFaile(baseReturn.toString());
                            activity.dissmissProgressDialog();
                        }
                    }
                });

    }

    /**
     * 发送短信验证
     */
    private String smsType = "USER_FIND_PASSWORD_CODE";
    @Override
    public void sendSms() {

        String phonenum = et_phonenum.getText().toString();

        sendSmsDataSource.sendSms(phonenum,smsType)
                .throttleFirst(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<BaseReturn>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseReturn baseReturn) {

                    }
                });
    }

    /**
     * 验证短信验证
     */
    @Override
    public void verifySms() {

        String phonenum = et_phonenum.getText().toString();
        String code = ed_code.getText().toString();
        verifySmsDataSource.verifySms(phonenum,code,smsType)
                .throttleFirst(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<BaseReturn>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("Findpassword","e   :  "+e.toString());

                        mView.showToast("短信验证失败");
                        activity.dissmissProgressDialog();
                    }

                    @Override
                    public void onNext(BaseReturn baseReturn) {

                        if(baseReturn.isSuccess()){
                            Findpassword();
                        }else {
                            mView.showToast(baseReturn.getMessage());
                            activity.dissmissProgressDialog();
                        }

                    }
                });
    }
}
