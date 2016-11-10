package com.karazam.huashanapp.manage.details_fragment.viewmodel.DetailsFragmentViewModel_2;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.manage.details_fragment.model.databinding.DetailsFragment2Entity;
import com.karazam.huashanapp.manage.details_fragment.view.DetailsFragment2View;
import com.karazam.huashanapp.manage.main.model.databinding.Project;

/**
 * Created by Administrator on 2016/11/10.
 */

public class DetailsFragment2ViewModelImpl extends DetailsFragment2ViewModel {

    private DetailsFragment2Entity mEntity;
    private DetailsFragment2View mView;
    private Context context;
    private Activity activity;

    public DetailsFragment2ViewModelImpl(DetailsFragment2Entity mEntity, DetailsFragment2View mView, Context context, Activity activity) {
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
