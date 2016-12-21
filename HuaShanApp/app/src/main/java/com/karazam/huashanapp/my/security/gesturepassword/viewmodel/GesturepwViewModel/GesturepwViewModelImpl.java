package com.karazam.huashanapp.my.security.gesturepassword.viewmodel.GesturepwViewModel;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.karazam.huashanapp.R;
import com.karazam.huashanapp.main.dialog.InputContentView;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.security.gesturepassword.model.databinding.GespwReturn;
import com.karazam.huashanapp.my.security.gesturepassword.model.databinding.GesturepwEntity;
import com.karazam.huashanapp.my.security.gesturepassword.model.retrofit.GespasswordDataSource;
import com.karazam.huashanapp.my.security.gesturepassword.view.GesturepwView;
import com.karazam.huashanapp.my.security.gesturepassword.view.activity.GesturepwActivity;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/29.
 */

public class GesturepwViewModelImpl extends GesturepwViewModel{

    private GesturepwView mView;
    private GesturepwEntity mEntity;
    private Context context;
    private GesturepwActivity activity;

    private InputContentView inputContentView;

    private GespasswordDataSource dataSource;

    public GesturepwViewModelImpl(GesturepwView mView, GesturepwEntity mEntity, Context context, GesturepwActivity activity) {
        this.mView = mView;
        this.mEntity = mEntity;
        this.context = context;
        this.activity = activity;

        dataSource = new GespasswordDataSource();
        setInputContentView();
    }

    private void setInputContentView() {
        inputContentView = new InputContentView(context);
        inputContentView.setView((ViewGroup) mView.getView(R.id.content_pl), new InputContentView.OnInputContentListener() {
            @Override
            public void onLeft(View view) {
                inputContentView.dismiss();
            }

            @Override
            public void onRight(View view) {
                String str = inputContentView.getContent();
                if(TextUtils.isEmpty(str)){
                    mView.showToast("请输入密码");
                    return;
                }else {
                    nextStep();
                }

            }
        });
    }


    @Override
    public void onBack(View view) {
        mView.FinishActivity(activity);
    }


    @Override
    public void onGesture(View view) {
        inputContentView.show();
    }

    /**
     * 同步手势密码
     */
    @Override
    public void setGesPassword(String gesPassword) {

        dataSource.setGesPassword(gesPassword).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new Subscriber<BaseReturn<GespwReturn>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

                mView.setGesPasswordFaile(e);
                Log.i("GespwReturn","e  :  "+e.toString());
            }

            @Override
            public void onNext(BaseReturn<GespwReturn> gespwReturnBaseReturn) {
                GespwReturn gespwReturn = gespwReturnBaseReturn.getData();
                Log.i("GespwReturn",gespwReturn.toString());
                mView.setGesPasswordSuccess(gespwReturn);
            }
        });
    }

    /**
     * 下一步
     */
    private void nextStep() {
        mView.toGestureEdit();
        inputContentView.dismiss();
    }
}
