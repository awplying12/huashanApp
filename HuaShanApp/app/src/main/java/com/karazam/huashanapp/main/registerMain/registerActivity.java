package com.karazam.huashanapp.main.registerMain;

import com.example.utils.base.BaseActivity;

import java.util.ArrayList;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Administrator on 2016/10/24.
 */

public class registerActivity {

    public static ArrayList<BaseActivity> allRegisterActivity = new ArrayList<>();

    public static void finishAll(){
        Observable.from(allRegisterActivity)
                .filter(new Func1<BaseActivity, Boolean>() {
                    @Override
                    public Boolean call(BaseActivity baseActivity) {
                        return baseActivity != null;
                    }
                })
                .subscribe(new Action1<BaseActivity>() {
                    @Override
                    public void call(BaseActivity baseActivity) {
                        baseActivity.FinishActivity(baseActivity);
                    }
                });
    }
}
