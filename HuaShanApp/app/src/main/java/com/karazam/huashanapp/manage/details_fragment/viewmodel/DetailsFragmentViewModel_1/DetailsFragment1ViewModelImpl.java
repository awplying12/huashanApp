package com.karazam.huashanapp.manage.details_fragment.viewmodel.DetailsFragmentViewModel_1;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.manage.details_fragment.model.databinding.DetailsFragment1Entity;
import com.karazam.huashanapp.manage.details_fragment.view.DetailsFragment1View;

/**
 * Created by Administrator on 2016/11/10.
 */

public class DetailsFragment1ViewModelImpl extends DetailsFragment1ViewModel {

    private DetailsFragment1View mView;
    private DetailsFragment1Entity mEntity;
    private Context context;
    private Activity activity;

    public DetailsFragment1ViewModelImpl(DetailsFragment1View mView, DetailsFragment1Entity mEntity, Context context, Activity activity) {
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
