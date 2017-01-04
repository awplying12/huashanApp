package com.karazam.huashanapp.my.realname.model.retrofit;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.main.retorfitMain.BaseDataSource;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.realname.model.databinding.RealnameBean;
import com.karazam.huashanapp.my.realname.model.databinding.RealnamePost;

import rx.Observable;

/**
 * Created by Administrator on 2017/1/3.
 */

public class RealnameDataSource extends BaseDataSource{

    RealnameApi service = retrofit1.create(RealnameApi.class);

    public Observable<BaseReturn<RealnameBean>> onRealname(String name, String idNo){
        RealnamePost post = new RealnamePost();
        post.setName(name);
        post.setIdNo(idNo);
//        post.setUserIdKey(HuaShanApplication.userKey);
        return service.onRealname(post,HuaShanApplication.token,"XMLHttpRequest");
    }
}
