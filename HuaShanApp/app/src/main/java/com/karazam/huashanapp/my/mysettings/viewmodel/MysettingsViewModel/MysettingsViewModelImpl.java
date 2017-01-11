package com.karazam.huashanapp.my.mysettings.viewmodel.MysettingsViewModel;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.mysettings.model.databinding.MysettingsEntity;
import com.karazam.huashanapp.my.mysettings.view.MysettingsView;
import com.karazam.huashanapp.my.mysettings.view.activity.MysettingsActivity;
import com.karazam.huashanapp.my.mysettings.view.activity.MysettingsActivity2;
import com.karazam.huashanapp.my.realname.view.activity.UnauthorizedActivity;
import com.karazam.huashanapp.my.setup.model.datanbinding.SetupBean;
import com.karazam.huashanapp.my.setup.model.datanbinding.SetupPost;
import com.karazam.huashanapp.my.setup.model.retrofit.SetupDataSource;

import java.util.concurrent.TimeUnit;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/22.
 */

public class MysettingsViewModelImpl extends MysettingsViewModel {

    private MysettingsView mView;
    private MysettingsEntity mEntity;
    private Context context;
    private MysettingsActivity activity;

    private SetupDataSource setupDataSource;


    public MysettingsViewModelImpl(MysettingsView mView, MysettingsEntity mEntity, Context context, MysettingsActivity activity) {
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
     * 修改头像
     * @param view
     */
    @Override
    public void setupHeader(View view) {
//            mView.showToast("setupHeader");
            mView.addPicturedialog();
    }

    /**
     * 修改用户名
     * @param view
     */
    @Override
    public void setupUserName(View view) {
        mView.toOtherActivity(activity, MysettingsActivity2.class);
    }

    /**
     * 实名认证
     * @param view
     */
    @Override
    public void toRealname(View view) {
        if(HuaShanApplication.certificationStatus){
            return;
        }
       mView.toOtherActivity(activity, UnauthorizedActivity.class);
    }

    /**
     * 修改头像接口
     * @param bitmapBase
     */
    @Override
    public void setHeader(String bitmapBase) {

        SetupPost post = new SetupPost();
        post.setAvatar(bitmapBase);
        setupDataSource.setUp(post)
                .throttleFirst(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<BaseReturn<SetupBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("setHeader"," e  :  "+e.toString());
                        mView.setHeaderError(e);
                    }

                    @Override
                    public void onNext(BaseReturn<SetupBean> setupBeanBaseReturn) {
                        if(setupBeanBaseReturn.isSuccess()){
                            SetupBean bean = setupBeanBaseReturn.getData();
                            HuaShanApplication.myInformation.setBaseInfo(bean.getBaseInfo());
                            HuaShanApplication.baseInfoBeanRX.set(bean.getBaseInfo());
                            mView.setHeaderSuccess();
                        } else {
                            mView.setHeaderFail(setupBeanBaseReturn.getMessage());
                        }
                    }
                });
    }


}
