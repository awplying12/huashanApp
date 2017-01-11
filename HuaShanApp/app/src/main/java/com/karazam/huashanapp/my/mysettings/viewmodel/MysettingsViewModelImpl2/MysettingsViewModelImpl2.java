package com.karazam.huashanapp.my.mysettings.viewmodel.MysettingsViewModelImpl2;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.mysettings.model.databinding.MysettingsEntity;
import com.karazam.huashanapp.my.mysettings.view.MysettingsView2;
import com.karazam.huashanapp.my.mysettings.view.activity.MysettingsActivity2;
import com.karazam.huashanapp.my.setup.model.datanbinding.SetupBean;
import com.karazam.huashanapp.my.setup.model.datanbinding.SetupPost;
import com.karazam.huashanapp.my.setup.model.retrofit.SetupDataSource;

import java.util.concurrent.TimeUnit;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/23.
 */

public class MysettingsViewModelImpl2 extends MysettingsViewModel2 {

    private MysettingsView2 mView;
    private MysettingsEntity mEntity;
    private Context context;
    private MysettingsActivity2 activity;

    private SetupDataSource setupDataSource;

    public MysettingsViewModelImpl2(MysettingsView2 mView, MysettingsEntity mEntity, Context context, MysettingsActivity2 activity) {
        this.mView = mView;
        this.mEntity = mEntity;
        this.context = context;
        this.activity = activity;

        setupDataSource = new SetupDataSource();
    }

    @Override
    public void onBack(View view) {
        mView.FinishActivity(activity);
    }

    /**
     * 清除内容
     */
    @Override
    public void cleanContent(View view) {
        ed_nick.setText("");
    }

    /**
     * 保存内容
     * @param view
     */
    @Override
    public void saveData(View view) {

        String str = ed_nick.getText().toString();
        if(TextUtils.isEmpty(str)){
            mView.showToast(" 请输入昵称");
        }else {
            setUpName(str);
        }
        mView.FinishActivity(activity);
    }

    /**
     * 设置昵称
     */
    @Override
    public void setUpName(String nickName) {

        SetupPost post = new SetupPost();
        post.setNickName(nickName);

        setupDataSource.setUp(post)
                .throttleFirst(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<BaseReturn<SetupBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("setUpName"," e  :  "+e.toString());
                        mView.setUpNameError(e);
                    }

                    @Override
                    public void onNext(BaseReturn<SetupBean> setupBeanBaseReturn) {

                        if(setupBeanBaseReturn.isSuccess()){

                            SetupBean bean = setupBeanBaseReturn.getData();
                            HuaShanApplication.myInformation.setBaseInfo(bean.getBaseInfo());
                            HuaShanApplication.baseInfoBeanRX.set(bean.getBaseInfo());
                            mView.setUpNameSuccess();
                        }else {
                            mView.setUpNameFail(setupBeanBaseReturn.getMessage());
                        }
                    }
                });
    }
}
