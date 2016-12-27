package com.karazam.huashanapp.manage.main.model.retrofit;

import com.karazam.huashanapp.main.retorfitMain.BaseDataSource;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.manage.main.model.databinding.ManagedataBean;

import rx.Observable;

/**
 * Created by Administrator on 2016/12/26.
 */

public class ManagedataDataSource extends BaseDataSource {

    ManagedataApi service = retrofit1.create(ManagedataApi.class);

    public Observable<BaseReturn<ManagedataBean>> getManagedata(String borrowingType, String currentPage){
        return service.getManagedata(borrowingType,currentPage,"XMLHttpRequest");
    }
}
