package com.karazam.huashanapp.manage.details_fragment.model.retrofit;

import com.karazam.huashanapp.main.retorfitMain.BaseDataSource;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.manage.details_fragment.model.databinding.ManageOpinionsbean;

import rx.Observable;

/**
 * Created by Administrator on 2016/12/29.
 */

public class ManageOpinionsDataSource extends BaseDataSource {

    ManageOpinionsApi service = retrofit1.create(ManageOpinionsApi.class);

    public Observable<BaseReturn<ManageOpinionsbean>> getManageOpinions(String projectId){
        return service.getManageOpinions(projectId,"XMLHttpRequest");
    }
}
