package com.karazam.huashanapp.my.message.main.model.retrofit;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.main.retorfitMain.BaseDataSource;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.message.main.model.databinding.MessagelistBean;

import rx.Observable;

/**
 * Created by Administrator on 2017/1/16.
 */

public class MessagelistDataSource extends BaseDataSource {

    MessagelistApi service = retrofit1.create(MessagelistApi.class);

    public Observable<BaseReturn<MessagelistBean>> getMessagelist(){
        return service.getMessagelist(HuaShanApplication.uuid,HuaShanApplication.token,"XMLHttpRequest");
    }
}
