package com.karazam.huashanapp.home.viewmodel;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.home.model.databinding.CheckloginReturn;
import com.karazam.huashanapp.home.model.databinding.HomeEntity;
import com.karazam.huashanapp.home.model.retrofit.CheckloginDataSource;
import com.karazam.huashanapp.home.view.HomeView;
import com.karazam.huashanapp.home.view.activity.HomeActivity;
import com.karazam.huashanapp.main.Bean.MyAssets.MyAssetsBean;
import com.karazam.huashanapp.main.Bean.MyInformation.MyInformationBean;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.main.retrofit.user.MyAssetsDataSource;
import com.karazam.huashanapp.main.retrofit.user.MyInformationDataSource;
import com.karazam.huashanapp.user.login.model.databinding.TokenData;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/11.
 */

public class HomeViewModelImpl extends HomeViewModel {

    private HomeView mView;
    private HomeEntity mEntity;
    private HomeActivity activity;
    private Context context;

    private CheckloginDataSource dataSource;
    private MyInformationDataSource informationDataSource;
    private MyAssetsDataSource assetsDataSource;

    public HomeViewModelImpl(HomeView mView, HomeEntity mEntity, HomeActivity activity, Context context) {
        this.mView = mView;
        this.mEntity = mEntity;
        this.activity = activity;
        this.context = context;

        dataSource = new CheckloginDataSource();
        informationDataSource = new MyInformationDataSource();
        assetsDataSource = new MyAssetsDataSource();
    }

    @Override
    public void onBack(View view) {
        mView.FinishActivity(activity);
    }

    /**
     * 金融Fragmetn
     * @param view
     */
    @Override
    public void toFinanec(View view) {
//        mView.setViewPagerCurrentItem(0,"金融");
    }


    /**
     * 今日Fragmetn
     * @param view
     */
    @Override
    public void toToday(View view) {
        mView.setViewPagerCurrentItem(0,"今日");
    }

    /**
     * 理财Fragment
     * @param view
     */
    @Override
    public void toManage(View view) {
        mView.setViewPagerCurrentItem(1,"理财");
    }

    /**
     * 积分Fragment
     * @param view
     */
    @Override
    public void toApply(View view) {
//        mView.setViewPagerCurrentItem(2,"申请");
    }

    /**
     * 我的Fragment
     * @param view
     */
    @Override
    public void toMy(View view) {
        switch (HuaShanApplication.loginStatus){
            case 1:
                mView.setViewPagerCurrentItem(2,"我的");
                break;
            default:
                mView.toLoginActivity();
                break;
        }


    }

    @Override
    public void setUp(View view) {
        HuaShanApplication.editor.putInt("loginStatus",2).commit();
        HuaShanApplication.loginStatus = 2;
        HuaShanApplication.loginStatusRx.set(HuaShanApplication.loginStatus);
        mView.setViewPagerCurrentItem(0,"今日");
    }

    /**
     * 同步登录状态
     */
    @Override
    public void onChecklogin() {

        dataSource.checkLoginStatus().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new Subscriber<BaseReturn<CheckloginReturn>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.i("onChecklogin","e : "+e.toString());
                mView.CheckloginError(e);
            }

            @Override
            public void onNext(BaseReturn<CheckloginReturn> checkloginReturnBaseReturn) {



                if(checkloginReturnBaseReturn.isSuccess()){
                    CheckloginReturn checkloginReturn = checkloginReturnBaseReturn.getData();

                    Log.i("onChecklogin",checkloginReturn.toString());

                    HuaShanApplication.editor.putString("token",checkloginReturn.getSid()).commit();
                    HuaShanApplication.token = checkloginReturn.getSid();

                    HuaShanApplication.editor.putString("uuid",checkloginReturn.getUserId()).commit();
                    HuaShanApplication.uuid = checkloginReturn.getUserId();

                    HuaShanApplication.editor.putString("userKey",checkloginReturn.getUserKey()).commit();
                    HuaShanApplication.userKey = checkloginReturn.getUserKey();

                    mView.CheckloginSuccess(checkloginReturn);

                }else {
                    mView.CheckloginFaile(checkloginReturnBaseReturn.getMessage());
                }

            }
        });

    }

    /**
     * 获取我的信息
     */
    @Override
    public void getMyInformation() {

        informationDataSource.getMyInformation().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new Subscriber<BaseReturn<MyInformationBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.i("getMyInformation","e : "+e.toString());
            }

            @Override
            public void onNext(BaseReturn<MyInformationBean> myInformationBeanBaseReturn) {

                if(myInformationBeanBaseReturn.isSuccess()){
                    HuaShanApplication.setMyInformation(myInformationBeanBaseReturn.getData());
                    Log.i("getMyInformation",HuaShanApplication.myInformation.toString());


                }else {
                    mView.showToast(myInformationBeanBaseReturn.getMessage());
                }

            }
        });
    }

    /**
     * 获取我的资产
     */
    @Override
    public void getMyAssets(){

        assetsDataSource.getMyAssets().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new Subscriber<BaseReturn<MyAssetsBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.i("getMyAssets","e : "+e.toString());
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
