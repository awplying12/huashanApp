package com.karazam.huashanapp.my.mysettings.viewmodel.MysettingsViewModel;

import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.my.mysettings.model.databinding.MysettingsEntity;
import com.karazam.huashanapp.my.mysettings.view.MysettingsView;
import com.karazam.huashanapp.my.mysettings.view.activity.MysettingsActivity;
import com.karazam.huashanapp.my.mysettings.view.activity.MysettingsActivity2;
import com.karazam.huashanapp.my.realname.view.activity.UnauthorizedActivity;

/**
 * Created by Administrator on 2016/11/22.
 */

public class MysettingsViewModelImpl extends MysettingsViewModel {

    private MysettingsView mView;
    private MysettingsEntity mEntity;
    private Context context;
    private MysettingsActivity activity;


    public MysettingsViewModelImpl(MysettingsView mView, MysettingsEntity mEntity, Context context, MysettingsActivity activity) {
        this.mView = mView;
        this.mEntity = mEntity;
        this.context = context;
        this.activity = activity;


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
            mView.showToast("setupHeader");
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
        if(HuaShanApplication.userInformation.isStatus()){
            return;
        }
       mView.toOtherActivity(activity, UnauthorizedActivity.class);
    }


}
