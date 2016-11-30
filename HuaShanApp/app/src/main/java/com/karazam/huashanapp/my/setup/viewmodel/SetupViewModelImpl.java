package com.karazam.huashanapp.my.setup.viewmodel;

import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.my.about.main.view.activity.AboutActivity;
import com.karazam.huashanapp.my.help.main.view.activity.HelpActivity;
import com.karazam.huashanapp.my.mysettings.view.activity.MysettingsActivity;
import com.karazam.huashanapp.my.realname.view.activity.AuthenticatedActivity;
import com.karazam.huashanapp.my.realname.view.activity.UnauthorizedActivity;
import com.karazam.huashanapp.my.recommend.RecommendActivity;
import com.karazam.huashanapp.my.security.main.view.activity.SecurityActivity;
import com.karazam.huashanapp.my.setup.model.datanbinding.SetupEntity;
import com.karazam.huashanapp.my.setup.view.SetupView;
import com.karazam.huashanapp.my.setup.view.activity.SetupActivity;
import com.karazam.huashanapp.my.software.SoftwareActivity;

/**
 * Created by Administrator on 2016/11/22.
 */

public class SetupViewModelImpl extends SetupViewModel {

    private SetupView mView;
    private SetupEntity mEntity;
    private Context context;
    private SetupActivity activity;

    public SetupViewModelImpl(SetupView mView, SetupEntity mEntity, Context context, SetupActivity activity) {
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
     * 用户设置
     * @param view
     */
    @Override
    public void toMysettings(View view) {
        mView.toOtherActivity(activity, MysettingsActivity.class);
    }

    /**
     * 实名认证
     * @param view
     */
    @Override
    public void toRealname(View view) {

        if(HuaShanApplication.userInformation.isStatus()){
            mView.toOtherActivity(activity, AuthenticatedActivity.class);
        }else {
            mView.toOtherActivity(activity, UnauthorizedActivity.class);
        }

    }

    /**
     * 账号安全
     * @param view
     */
    @Override
    public void toSecurity(View view) {
        mView.toOtherActivity(activity, SecurityActivity.class);
    }

    /**
     * 软件设置
     * @param view
     */
    @Override
    public void toSoftwareSetting(View view) {
        mView.toOtherActivity(activity, SoftwareActivity.class);
    }

    /**
     * 推荐
     * @param view
     */
    @Override
    public void toRecommend(View view) {
        mView.toOtherActivity(activity, RecommendActivity.class);
    }

    /**
     * 关于
     * @param view
     */
    @Override
    public void toAbout(View view) {
        mView.toOtherActivity(activity, AboutActivity.class);
    }

    /**
     * 帮助中心
     * @param view
     */
    @Override
    public void toHelp(View view) {
        mView.toOtherActivity(activity, HelpActivity.class);
    }
}
