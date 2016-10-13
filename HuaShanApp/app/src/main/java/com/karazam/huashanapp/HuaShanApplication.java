package com.karazam.huashanapp;

import android.app.Application;
import android.content.SharedPreferences;

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
    }

    public static HuaShanApplication getinstance() {
        return instance;
    }
}
