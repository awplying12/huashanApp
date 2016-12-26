package com.karazam.huashanapp.user.register.viewmodel.Register1ViewModel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.karazam.huashanapp.R;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.user.register.model.databinbing.Register1Entity;
import com.karazam.huashanapp.user.register.model.retrofit.CheckMobileDataSource;
import com.karazam.huashanapp.user.register.view.Register1View;
import com.karazam.huashanapp.user.register.view.activity.Register1Activity;
import com.karazam.huashanapp.user.register.view.activity.Register2Activity;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxView;

import java.util.concurrent.TimeUnit;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/20.
 */

public class Register1ViewModelImpl extends Register1ViewModel {

    private Register1Entity mEntity;
    private Register1View mView;
    private Context context;
    private Register1Activity activity;
    private CheckMobileDataSource dataSource;


    public Register1ViewModelImpl(Register1Entity mEntity, Register1View mView, Context context, Register1Activity activity) {
        this.mEntity = mEntity;
        this.mView = mView;
        this.context = context;
        this.activity = activity;
        dataSource = new CheckMobileDataSource();
        setPhoneNum();
    }

    @Override
    public void onBack(View view) {
        activity.FinishActivity(activity);
    }

    /**
     *设置电话号码
     */

    private void setPhoneNum() {
        RxView.findById(activity, R.id.phoneNum_register).bind(phoneNum, new Rx.Action<View, String>() {
            @Override
            public void call(View target, String s) {
                TextView view = (TextView) target;
                view.setText(s);
            }
        });

    }

    /**
     * 确定
     * @param view
     */
    @Override
    public void onSure(View view) {

//        mView.toOtherActivity(activity, Register2Activity.class);
        Intent intent = new Intent(activity,Register2Activity.class);
        intent.putExtra("PhoneNum",account_register.getText().toString());
        activity.startActivity(intent);
        mView.introduction();


    }



    /**
     * 下一步
     * @param view
     */
    @Override
    public void onNextStep(View view) {
        activity.showProgressDialog();
        checkMobile(account_register.getText().toString());
    }

    @Override
    public void onIntroduction(View view) {
        mView.introduction();
    }

    /**
     * 验证手机号唯一
     * @param mobile
     */
    @Override
    public void checkMobile(String mobile) {

        dataSource.checkMobile(mobile)
                .throttleFirst(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<BaseReturn>() {
                    @Override
                    public void onCompleted() {
                        activity.dissmissProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("checkMobile","e  :  "+e.toString());
                        mView.checkMobileFaile("网络出现故障！");
                        activity.dissmissProgressDialog();
                    }

                    @Override
                    public void onNext(BaseReturn baseReturn) {

                        if(baseReturn.isSuccess()){
                            mView.checkMobileSuccess(baseReturn.getMessage());
                        }else {
                            mView.checkMobileFaile(baseReturn.getMessage());
                        }
                    }
                });
    }

}
