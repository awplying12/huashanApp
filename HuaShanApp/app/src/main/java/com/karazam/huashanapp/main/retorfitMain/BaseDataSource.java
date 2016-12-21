package com.karazam.huashanapp.main.retorfitMain;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/4/12.
 */
public class BaseDataSource {

    /**
     * 基础 Retrofit 网络请求库
     */
    public Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.URL).client(new OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();

    public Retrofit retrofit1 = new Retrofit.Builder()
            .baseUrl(Constants.URL1).client(new OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();


}
