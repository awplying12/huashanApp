package com.karazam.huashanapp;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.format.Time;
import android.util.Log;
import android.view.View;

import com.example.utils.ACacheBase.ACache;
import com.example.utils.base.BaseActivity;
import com.example.utils.base.SimpleActivityLifecycle;
import com.example.utils.utils.PathUtil;

import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.main.Bean.MyAssets.MyAssetsBean;
import com.karazam.huashanapp.main.Bean.MyInformation.BaseInfoBean;
import com.karazam.huashanapp.main.Bean.MyInformation.CardBean;
import com.karazam.huashanapp.main.Bean.MyInformation.MyInformationBean;
import com.karazam.huashanapp.main.Bean.UserInformation;
import com.karazam.huashanapp.main.Bean.financialproject.FinancialInformation;
import com.karazam.huashanapp.main.Bean.financialproject.FinancialProject;


import com.karazam.huashanapp.main.retrofit.user.LogoutDataSource;
import com.karazam.huashanapp.manage.details.model.databinding.ManagedetailsBean;
import com.karazam.huashanapp.my.myreturn.main.model.databinding.ReturnRecords;
import com.karazam.huashanapp.my.myreturn.main.model.databinding.ReturnRecordsItem;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxProperty;
import com.ogaclejapan.rx.binding.RxView;

import java.util.ArrayList;

import cn.jpush.android.api.JPushInterface;
import util.changhongit.com.cacheutils.Cache_RxBitmap.RxImageLoader;

/**
 * Created by Administrator on 2016/10/11.
 */

public class HuaShanApplication extends Application {

    private static HuaShanApplication instance;

    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;
    public static ACache mACache;

    public static String account;

    public static String uuid;
    public static String userKey;
    public static String token;
    public static String refresh_token;
    public static String client_id;

    public static Boolean corp;


    public static int loginStatus;
    public static RxProperty<Integer> loginStatusRx = RxProperty.create();

    public static String imei;
    public static String mtype;
    public static String VERSION;


    public static String paymentMethod = "QUICK_PAY";

    public static boolean certificationStatus = true;

    public static RxProperty<Time> day = RxProperty.create();


    //个人信息
    public static MyInformationBean myInformation = new MyInformationBean();
    public static RxProperty<MyInformationBean> myInformationRX = RxProperty.create();
    public static RxProperty<BaseInfoBean> baseInfoBeanRX = RxProperty.create();
    public static RxProperty<CardBean> withdrawCarRx = RxProperty.create();
    public static RxProperty<ArrayList<CardBean>> quickCardsRX = RxProperty.create();

    public static String header = "";

    //个人资产
    public static MyAssetsBean myAssetsBean = new MyAssetsBean();
    public static RxProperty<MyAssetsBean> myAssetsBeanRX = RxProperty.create();

    public static ArrayList<BaseActivity> securitysPayment = new ArrayList<>();
    public static ArrayList<BaseActivity> securitysGesture = new ArrayList<>();

    //投资详情对象
    public static RxProperty<ManagedetailsBean> project = RxProperty.create();
    public static RxProperty<String> refreshManage = RxProperty.create();


    public static String RegistrationID;

    public static SimpleActivityLifecycle lifecycle;
    public static LogoutDataSource logoutDataSource;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        JPushInterface.setDebugMode(true); // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);  //推送初始化
//        RegistrationID =JPushInterface.getRegistrationID(this);
        getRegistrationID();

        sharedPreferences = getinstance().getSharedPreferences("huashan", 0);
        editor = sharedPreferences.edit();

        account = sharedPreferences.getString("account", "");
        header = sharedPreferences.getString("header","");
        uuid = sharedPreferences.getString("uuid", "-1");
        userKey = sharedPreferences.getString("userKey", "-1");
        token = sharedPreferences.getString("token", "");
        refresh_token = sharedPreferences.getString("refresh_token", "");
        client_id = sharedPreferences.getString("client_id", "");

        corp = sharedPreferences.getBoolean("corp",false);

        paymentMethod = sharedPreferences.getString("paymentMethod", "QUICK_PAY");

        loginStatus = sharedPreferences.getInt("loginStatus",-1);

//        loginStatus = 1;
//        loginStatusRx.set(loginStatus);

//        loginStatusRx.set(loginStatus);

//        paymentmod = sharedPreferences.getString("paymentmod","");

        mACache = ACache.get(this);
        RxImageLoader.init(getinstance());
        PathUtil.createDir();

        //IMEI（imei）
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE); //手机的唯一标识
        imei = tm.getDeviceId();
        //手机型号
        mtype = android.os.Build.MODEL;
        //系统版本
        VERSION = android.os.Build.VERSION.RELEASE;

        Time time = new Time("GMT+8");
        time.setToNow();

        day.set(time);

        logoutDataSource = new LogoutDataSource();

        ActivityLifecycle();
    }

    /**
     * 监听Application 生命周期
     */
    private void ActivityLifecycle() {
        if (Build.VERSION.SDK_INT >= 14) {
            lifecycle = new SimpleActivityLifecycle();
            registerActivityLifecycleCallbacks(lifecycle);
        }

    }


    public static HuaShanApplication getinstance() {
        return instance;
    }

    public static boolean checkSecurityInformation(Context context){

        return sharedPreferences.getBoolean("isGesture_lock", false);
    }

    public static String getGesturePassword(){
        return sharedPreferences.getString("gesture_lock", "-1");
    }

    /**
     * 设置用户信息
     */
    public static void setMyInformation(MyInformationBean bean){
        HuaShanApplication.myInformation = bean;


//        certificationStatus = bean.getBaseInfo().isAuthentication();
//        HuaShanApplication.header = bean.getBaseInfo().getAvatar();
//        HuaShanApplication.editor.putString("header",bean.getBaseInfo().getAvatar()).commit();
        HuaShanApplication.myInformationRX.set(HuaShanApplication.myInformation);
        HuaShanApplication.baseInfoBeanRX.set(HuaShanApplication.myInformation.getBaseInfo());
        HuaShanApplication.withdrawCarRx.set(HuaShanApplication.myInformation.getWithdrawCard());
        HuaShanApplication.quickCardsRX.set((HuaShanApplication.myInformation.getQuickCards()));

//        if(bean == null){
//            corp = "0";
//            HuaShanApplication.editor.putString("corp","0").commit();
//        } else {
//            corp = StringUtil.interrupt(bean.getCorp(),0,"0");
//            HuaShanApplication.editor.putString(corp,"0").commit();
//        }

        if(bean.getBaseInfo() == null){
            certificationStatus = false;
            HuaShanApplication.header = "";
            HuaShanApplication.editor.putString("header",HuaShanApplication.header).commit();
            HuaShanApplication.editor.putBoolean("isGesture_lock",false).commit();
            HuaShanApplication.editor.putString("gesture_lock","-1").commit();
        }else {
            certificationStatus = bean.getBaseInfo().isAuthentication();
            HuaShanApplication.header = bean.getBaseInfo().getAvatar();
            HuaShanApplication.editor.putString("header",bean.getBaseInfo().getAvatar()).commit();
            HuaShanApplication.editor.putBoolean("isGesture_lock",bean.getBaseInfo().isSetGesPassword()).commit();
            HuaShanApplication.editor.putString("gesture_lock",bean.getBaseInfo().getGesPassword()).commit();
        }

    }

    /**
     * 设置用户资产
     */
    public static void setMyAssets(MyAssetsBean bean){
        HuaShanApplication.myAssetsBean = bean;
        HuaShanApplication.myAssetsBeanRX.set(HuaShanApplication.myAssetsBean);
    }

    /**
     * 安全退出
     */
    public static void safeExit(){
        HuaShanApplication.loginStatus = -1;
        HuaShanApplication.editor.putInt("loginStatus",HuaShanApplication.loginStatus).commit();
        loginStatusRx.set(HuaShanApplication.loginStatus);

        HuaShanApplication.editor.putString("account","").commit();
//        HuaShanApplication.editor.putString("header","").commit();
        HuaShanApplication.editor.putString("uuid","-1").commit();
        HuaShanApplication.editor.putString("userKey","-1").commit();
        HuaShanApplication.editor.putString("token","").commit();
        HuaShanApplication.editor.putString("refresh_token","").commit();
        HuaShanApplication.editor.putString("client_id","").commit();

        HuaShanApplication.editor.putString("gesture_lock","-1").commit();

//        HuaShanApplication.editor.putString("corp","0").commit();
        setMyInformation(new MyInformationBean());
        setMyAssets(new MyAssetsBean());

        if(logoutDataSource == null){
            return;
        }
        logoutDataSource.Logout();
    }

    public static String getRegistrationID(){
        RegistrationID =JPushInterface.getRegistrationID(getinstance());
        return RegistrationID;
    }

}
