package com.karazam.huashanapp.today.main.model.retrofit;

import com.karazam.huashanapp.main.retorfitMain.BaseDataSource;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.today.main.model.databinding.TodayBean;

import rx.Observable;

/**
 * Created by Administrator on 2016/12/26.
 */

public class TaodayDataSource extends BaseDataSource {

    TodayApi service = retrofit1.create(TodayApi.class);

    public Observable<BaseReturn<TodayBean>> getTodayData(){
        return service.getTodayData();
    }

}
