package com.karazam.huashanapp.my.bankcard.bindcard.model.retrofit;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.main.retorfitMain.BaseDataSource;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.bankcard.bindcard.model.databinding.BindcardPost;
import com.karazam.huashanapp.my.bankcard.bindcard.model.databinding.BindcardBean;
import com.karazam.huashanapp.my.bankcard.bindcard.model.databinding.UpDatecardPost;

import rx.Observable;

/**
 * Created by Administrator on 2017/1/4.
 */

public class AddcardDataSource extends BaseDataSource{

    AddcardApi service = retrofit1.create(AddcardApi.class);

    public Observable<BaseReturn<BindcardBean>> onAddcard(BindcardPost post){
        return service.getAddcard(post, HuaShanApplication.token,"XMLHttpRequest");
    }


    public Observable<BaseReturn<BindcardBean>> upDatecard(UpDatecardPost post){
        return service.upDatecard(post, HuaShanApplication.token,"XMLHttpRequest");
    }

    public Observable<BaseReturn> sendSMS(BindcardPost post){
        return service.sendSMS(post, HuaShanApplication.token,"XMLHttpRequest");
    }
}
