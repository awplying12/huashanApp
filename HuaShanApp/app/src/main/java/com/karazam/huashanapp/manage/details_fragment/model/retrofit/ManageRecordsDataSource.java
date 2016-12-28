package com.karazam.huashanapp.manage.details_fragment.model.retrofit;

import com.karazam.huashanapp.main.retorfitMain.BaseDataSource;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.manage.details_fragment.model.databinding.ManageRecordsBean;

import rx.Observable;

/**
 * Created by Administrator on 2016/12/28.
 */

public class ManageRecordsDataSource extends BaseDataSource {

    ManageRecordsApi service = retrofit1.create(ManageRecordsApi.class);

    public Observable<BaseReturn<ManageRecordsBean>> getDetails(String projectId){
        return service.getRecords(projectId,"XMLHttpRequest");
    }

}
