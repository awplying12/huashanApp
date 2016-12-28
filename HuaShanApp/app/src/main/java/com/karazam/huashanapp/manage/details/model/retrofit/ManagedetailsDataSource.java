package com.karazam.huashanapp.manage.details.model.retrofit;

import com.karazam.huashanapp.main.retorfitMain.BaseDataSource;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.manage.details.model.databinding.ManagedetailsBean;

import rx.Observable;

/**
 * Created by Administrator on 2016/12/28.
 */

public class ManagedetailsDataSource extends BaseDataSource{

    ManagedetailsApi service = retrofit1.create(ManagedetailsApi.class);

    public Observable<BaseReturn<ManagedetailsBean>> getDetails(String projectId){
        return service.getDetails(projectId,"XMLHttpRequest");
    }

}
