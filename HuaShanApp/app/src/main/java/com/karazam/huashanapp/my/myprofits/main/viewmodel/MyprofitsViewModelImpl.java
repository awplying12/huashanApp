package com.karazam.huashanapp.my.myprofits.main.viewmodel;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.main.Bean.MyAssets.MyAssetsBean;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.main.retrofit.user.MyAssetsDataSource;
import com.karazam.huashanapp.my.myprofits.main.model.databinding.MyprofitsEntity;
import com.karazam.huashanapp.my.myprofits.main.view.MyprofitsView;
import com.karazam.huashanapp.my.myprofits.main.view.activity.MyprofitsActivity;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/21.
 */

public class MyprofitsViewModelImpl extends MyprofitsViewModel {

    private MyprofitsView mView;
    private MyprofitsEntity mEntity;
    private Context context;
    private MyprofitsActivity activity;

    private MyAssetsDataSource assetsDataSource;

    public MyprofitsViewModelImpl(MyprofitsView mView, MyprofitsEntity mEntity, Context context, MyprofitsActivity activity) {
        this.mView = mView;
        this.mEntity = mEntity;
        this.context = context;
        this.activity = activity;

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

//        activity.showProgressDialog();

        assetsDataSource.getMyAssets().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new Subscriber<BaseReturn<MyAssetsBean>>() {
            @Override
            public void onCompleted() {
                mView.getMyAssetsFinish();
//                activity.dissmissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.i("getMyAssets","e : "+e.toString());
                mView.getMyAssetsFinish();
//                activity.dissmissProgressDialog();
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
