package com.karazam.huashanapp.my.bankcard.bindcard.model.retrofit;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.main.retorfitMain.BaseDataSource;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.bankcard.bindcard.model.databinding.BindcarPost;
import com.karazam.huashanapp.my.bankcard.bindcard.model.databinding.BindcardBean;

import rx.Observable;

/**
 * Created by Administrator on 2017/1/4.
 */

public class AddcardDataSource extends BaseDataSource{

    AddcardApi service = retrofit1.create(AddcardApi.class);

    public Observable<BaseReturn<BindcardBean>> Addcard(BindcarPost post){
        return service.getAddcard(post, HuaShanApplication.token,"XMLHttpRequest");
    }
}
