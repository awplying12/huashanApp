package com.karazam.huashanapp;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.telephony.TelephonyManager;
import android.text.format.Time;

import com.example.utils.base.BaseActivity;
import com.example.utils.utils.PathUtil;
import com.karazam.huashanapp.main.UserInformation;
import com.karazam.huashanapp.main.financialproject.FinancialInformation;
import com.karazam.huashanapp.main.financialproject.FinancialProject;
import com.karazam.huashanapp.main.financialproject.ReturnRecords;
import com.karazam.huashanapp.main.financialproject.ReturnRecordsItem;
import com.ogaclejapan.rx.binding.RxProperty;

import java.util.ArrayList;

import util.changhongit.com.cacheutils.Cache_RxBitmap.RxImageLoader;

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

    public static boolean certificationStatus = true;

    public static RxProperty<String> day = RxProperty.create();

    public static RxProperty<UserInformation> userInformationR = RxProperty.create();

    public static ArrayList<BaseActivity> securitysPayment = new ArrayList<>();
    public static ArrayList<BaseActivity> securitysGesture = new ArrayList<>();

    public static FinancialProject project1;
    public static FinancialProject project2;
    public static FinancialProject project3;
    public static FinancialProject project4;

    public static ArrayList<ReturnRecords> returnRecordses;

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

        RxImageLoader.init(getinstance());
        PathUtil.createDir();

        //IMEI（imei）
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE); //手机的唯一标识
        imei = tm.getDeviceId();

        Time time = new Time("GMT+8");
        time.setToNow();

        day.set(time.monthDay+"");

        userInformation = new UserInformation();
        userInformation.setBankCard("中国银行(尾号7634)");
        userInformation.setCardInformation("单笔限额5万,单日限额50万");
        userInformation.setUserbalance("6214.47");
        userInformation.setPaymentmod("bankCard");
        userInformation.setHeaderImg("http://tx.haiqq.com/uploads/allimg/150325/12215B540-0.jpg");
        userInformation.setUserName("王蕙");
        userInformation.setNickname("chayewala");
        userInformation.setStatus(certificationStatus);
        userInformation.setPhonenum("130*****017");
//        paymentmod = userInformation.getPaymentmod();
        userInformationR.set(userInformation);


        project1= new FinancialProject();
        ArrayList<FinancialInformation> informations1 = new ArrayList<>();
        informations1.add(new FinancialInformation());
        informations1.add(new FinancialInformation());
        informations1.add(new FinancialInformation());
        project1.setInformations(informations1);

        project2= new FinancialProject();
        ArrayList<FinancialInformation> informations2 = new ArrayList<>();
        informations2.add(new FinancialInformation());
        informations2.add(new FinancialInformation());
        informations2.add(new FinancialInformation());
        informations2.add(new FinancialInformation());
        project2.setInformations(informations2);

        project3= new FinancialProject();
        ArrayList<FinancialInformation> informations3 = new ArrayList<>();
        informations3.add(new FinancialInformation());
        informations3.add(new FinancialInformation());
        project3.setInformations(informations3);

        project4= new FinancialProject();
        ArrayList<FinancialInformation> informations4 = new ArrayList<>();
        informations4.add(new FinancialInformation());
        informations4.add(new FinancialInformation());
        informations4.add(new FinancialInformation());
        informations4.add(new FinancialInformation());
        project4.setInformations(informations4);

        returnRecordses = new ArrayList<>();
        ArrayList<ReturnRecordsItem> item1 = new ArrayList<>();
        item1.add(new ReturnRecordsItem());
        item1.add(new ReturnRecordsItem());
        item1.add(new ReturnRecordsItem());
        item1.add(new ReturnRecordsItem());
        item1.add(new ReturnRecordsItem());

        ArrayList<ReturnRecordsItem> item2 = new ArrayList<>();
        item2.add(new ReturnRecordsItem());
        item2.add(new ReturnRecordsItem());
        item2.add(new ReturnRecordsItem());
        item2.add(new ReturnRecordsItem());

        ArrayList<ReturnRecordsItem> item3 = new ArrayList<>();
        item3.add(new ReturnRecordsItem());
        item3.add(new ReturnRecordsItem());
        item3.add(new ReturnRecordsItem());
        item3.add(new ReturnRecordsItem());
        item3.add(new ReturnRecordsItem());
        item3.add(new ReturnRecordsItem());
        item3.add(new ReturnRecordsItem());
        item3.add(new ReturnRecordsItem());


        returnRecordses.add(new ReturnRecords(item1));
        returnRecordses.add(new ReturnRecords(item2));
        returnRecordses.add(new ReturnRecords(item3));


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
