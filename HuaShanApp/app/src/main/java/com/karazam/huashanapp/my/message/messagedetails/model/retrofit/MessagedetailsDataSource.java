package com.karazam.huashanapp.my.message.messagedetails.model.retrofit;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.main.retorfitMain.BaseDataSource;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.message.messagedetails.model.databinding.MessagedetailsBean;

import rx.Observable;

/**
 * Created by Administrator on 2017/1/16.
 */

public class MessagedetailsDataSource extends BaseDataSource {

    MessagedetailsApi service = retrofit1.create(MessagedetailsApi.class);

    public Observable<BaseReturn<MessagedetailsBean>> getMessagedetails(String type,String page){
        return service.getMessagedetails(HuaShanApplication.uuid,type,page,HuaShanApplication.token,"XMLHttpRequest");
    }

}
