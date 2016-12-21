package com.karazam.huashanapp.my.myprofits.main.viewmodel;

import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.my.myprofits.main.model.databinding.MyprofitsEntity;
import com.karazam.huashanapp.my.myprofits.main.view.MyprofitsView;
import com.karazam.huashanapp.my.myprofits.main.view.activity.MyprofitsActivity;

/**
 * Created by Administrator on 2016/12/21.
 */

public class MyprofitsViewModelImpl extends MyprofitsViewModel {

    private MyprofitsView mView;
    private MyprofitsEntity mEntity;
    private Context context;
    private MyprofitsActivity activity;

    public MyprofitsViewModelImpl(MyprofitsView mView, MyprofitsEntity mEntity, Context context, MyprofitsActivity activity) {
        this.mView = mView;
        this.mEntity = mEntity;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void onBack(View view) {
        mView.FinishActivity(activity);
    }
}
