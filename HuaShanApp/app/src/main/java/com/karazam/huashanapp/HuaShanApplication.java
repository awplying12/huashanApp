package com.karazam.huashanapp;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.telephony.TelephonyManager;
import android.text.format.Time;

import com.karazam.huashanapp.main.UserInformation;
import com.ogaclejapan.rx.binding.RxProperty;

/**
 * Created by Administrator on 2016/10/11.
 */

public class HuaShanApplication extends Application {

    private static HuaShanApplication instance;

    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;

    public static String account;

    public static String uuid;
    public static String token;
    public static String refresh_token;
    public static String client_id;

    public static int loginStatus;
    public static String imei;

    public static UserInformation userInformation;



    public static RxProperty<String> day = RxProperty.create();

    public static RxProperty<UserInformation> userInformationR = RxProperty.create();

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        sharedPreferences = getinstance().getSharedPreferences("huashan", 0);
        editor = sharedPreferences.edit();

        account = sharedPreferences.getString("account", "");
        uuid = sharedPreferences.getString("uuid", "-1");
        token = sharedPreferences.getString("token", "");
        refresh_token = sharedPreferences.getString("refresh_token", "");
        client_id = sharedPreferences.getString("client_id", "");

        loginStatus = sharedPreferences.getInt("loginStatus",-1);

//        paymentmod = sharedPreferences.getString("paymentmod","");

        //IMEI（imei）
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE); //手机的唯一标识
        imei = tm.getDeviceId();

        Time time = new Time("GMT+8");
        time.setToNow();

        day.set(time.monthDay+"");

        userInformation = new UserInformation();
        userInformation.setBankCard("中国银行(尾号7634)");
        userInformation.setCardInformation("单笔限额5万,单日限额50万");
        userInformation.setUserbalance("8,214.47");
        userInformation.setPaymentmod("bankCard");
//        paymentmod = userInformation.getPaymentmod();
        userInformationR.set(userInformation);

    }

    public static HuaShanApplication getinstance() {
        return instance;
    }

    public static boolean checkSecurityInformation(Context context){

        return !sharedPreferences.getString("gesture_lock", "-1") .equals("-1") ;
    }

    public static String getGesturePassword(){
        return sharedPreferences.getString("gesture_lock", "-1");
    }


}
