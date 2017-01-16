package com.karazam.huashanapp.my.myassets.main.viewmodel;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.main.Bean.MyAssets.MyAssetsBean;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.main.retrofit.user.MyAssetsDataSource;
import com.karazam.huashanapp.my.myassets.main.model.databinding.MyassetsEntity;
import com.karazam.huashanapp.my.myassets.main.view.MyassetsView;
import com.karazam.huashanapp.my.myassets.main.view.activity.MyassetsActivity;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/20.
 */

public class MyassetsViewModelImpl extends MyassetsViewModel {

    private MyassetsView mView;
    private MyassetsEntity mEntity;
    private MyassetsActivity activity;
    private Context context;

    private MyAssetsDataSource assetsDataSource;

    public MyassetsViewModelImpl(MyassetsView mView, MyassetsEntity mEntity, MyassetsActivity activity, Context context) {
        this.mView = mView;
        this.mEntity = mEntity;
        this.activity = activity;
        this.context = context;

        assetsDataSource = new MyAssetsDataSource();
    }

    @Override
    public void onBack(View view) {
            mView.FinishActivity(activity);
    }

    /**
     * 获取我的资产
     */
    @Override
    public void getMyAssets(){



        assetsDataSource.getMyAssets().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new Subscriber<BaseReturn<MyAssetsBean>>() {
            @Override
            public void onCompleted() {
                mView.getMyAssetsFinish();
            }

            @Override
            public void onError(Throwable e) {
                Log.i("getMyAssets","e : "+e.toString());
                mView.getMyAssetsFinish();
            }

            @Override
            public void onNext(BaseReturn<MyAssetsBean> myAssetsBeanBaseReturn) {

                if(myAssetsBeanBaseReturn.isSuccess()){
                    Log.i("getMyAssets",myAssetsBeanBaseReturn.getData().toString());
                    HuaShanApplication.setMyAssets(myAssetsBeanBaseReturn.getData());
                }else {
                    mView.showToast(myAssetsBeanBaseReturn.getMessage());
                }
            }
        });


    }

}
