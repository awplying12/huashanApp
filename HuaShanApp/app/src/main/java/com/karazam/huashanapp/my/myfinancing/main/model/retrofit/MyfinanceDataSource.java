package com.karazam.huashanapp.my.myfinancing.main.model.retrofit;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.main.retorfitMain.BaseDataSource;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.myfinancing.main.model.databinding.MyfinanceBean;

import rx.Observable;

/**
 * Created by Administrator on 2017/1/11.
 */

public class MyfinanceDataSource extends BaseDataSource {

    MyfinanceApi service = retrofit1.create(MyfinanceApi.class);

    public Observable<BaseReturn<MyfinanceBean>> getMyfinance(String progress,String currentPage){
        return service.getMyfinance(progress,currentPage,HuaShanApplication.token,"XMLHttpRequest");
    }
}
