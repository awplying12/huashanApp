package com.karazam.huashanapp.manage.details_fragment.viewmodel.DetailsFragmentViewModel_2;

import android.view.View;

import com.example.utils.base.BaseViewModel;

/**
 * Created by Administrator on 2016/11/10.
 */

public abstract class DetailsFragment2ViewModel extends BaseViewModel{

    public int allpage;

    public abstract void onInformation(View view);

    public abstract void onRecord(View view);

    public abstract void onSpeed(View view);

    public abstract void getManageRecords(String projectId,int page);

    public abstract void getManageOpinions(String projectId);
}
