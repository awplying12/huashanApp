package com.karazam.huashanapp.my.myreturn.main.model.retrofit;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.main.retorfitMain.BaseDataSource;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.myreturn.main.model.databinding.MyReturnBean;

import rx.Observable;

/**
 * Created by Administrator on 2017/1/12.
 */

public class MyReturnDataSource extends BaseDataSource {

    MyReturnApi service = retrofit1.create(MyReturnApi.class);

    public Observable<BaseReturn<MyReturnBean>> getMyReturnData(String currentPage){
        return service.getMyReturnData(currentPage, HuaShanApplication.token,"XMLHttpRequest");
    }
}
