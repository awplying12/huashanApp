package com.karazam.huashanapp.manage.experience.viewmodel;

import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.manage.experience.model.databinding.ExperienceEntity;
import com.karazam.huashanapp.manage.experience.view.ExperienceView;
import com.karazam.huashanapp.manage.experience.view.activity.ExperienceActivity;

/**
 * Created by Administrator on 2016/11/15.
 */

public class ExperienceViewModelImpl extends ExperienceViewModel {

    private ExperienceEntity mEntity;
    private ExperienceView mView;
    private Context context;
    private ExperienceActivity activity;

    public ExperienceViewModelImpl(ExperienceEntity mEntity, ExperienceView mView, Context context, ExperienceActivity activity) {
        this.mEntity = mEntity;
        this.mView = mView;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void onBack(View view) {
        mView.FinishActivity(activity);
    }
}
