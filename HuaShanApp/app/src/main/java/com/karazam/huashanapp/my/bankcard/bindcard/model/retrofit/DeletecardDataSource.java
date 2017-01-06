package com.karazam.huashanapp.my.bankcard.bindcard.model.retrofit;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.main.retorfitMain.BaseDataSource;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.bankcard.bindcard.model.databinding.BindcardBean;
import com.karazam.huashanapp.my.bankcard.bindcard.model.databinding.DeletecardPost;

import rx.Observable;

/**
 * Created by Administrator on 2017/1/6.
 */

public class DeletecardDataSource extends BaseDataSource {

    DeletecardApi service = retrofit1.create(DeletecardApi.class);

    public Observable<BaseReturn<BindcardBean>> deletaCard(DeletecardPost post){
        return service.deletacard(post, HuaShanApplication.token,"XMLHttpRequest");
    }
}
