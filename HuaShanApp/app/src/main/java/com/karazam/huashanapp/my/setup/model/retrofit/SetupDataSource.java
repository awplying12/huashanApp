package com.karazam.huashanapp.my.setup.model.retrofit;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.main.retorfitMain.BaseDataSource;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.setup.model.datanbinding.SetupBean;
import com.karazam.huashanapp.my.setup.model.datanbinding.SetupPost;

import rx.Observable;

/**
 * Created by Administrator on 2017/1/10.
 */

public class SetupDataSource extends BaseDataSource{

    SetupApi service = retrofit1.create(SetupApi.class);

    public Observable<BaseReturn<SetupBean>> setUp(SetupPost post){
        return service.setup(post, HuaShanApplication.token,"XMLHttpRequest");
    }
}
