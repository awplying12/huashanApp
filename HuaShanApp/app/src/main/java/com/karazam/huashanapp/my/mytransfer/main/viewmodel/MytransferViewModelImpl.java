package com.karazam.huashanapp.my.mytransfer.main.viewmodel;

import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.my.mytransfer.main.model.databinding.MytransferEntity;
import com.karazam.huashanapp.my.mytransfer.main.view.MytransferView;
import com.karazam.huashanapp.my.mytransfer.main.view.activity.MytransferActivity;

/**
 * Created by Administrator on 2016/12/7.
 */

public class MytransferViewModelImpl extends MytransferViewModel {

    private MytransferView mView;
    private MytransferEntity mEntity;
    private Context context;
    private MytransferActivity activity;

    public MytransferViewModelImpl(MytransferView mView, MytransferEntity mEntity, Context context, MytransferActivity activity) {
        this.mView = mView;
        this.mEntity = mEntity;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void onBack(View view) {
        mView.FinishActivity(activity);
    }

    @Override
    public void Finanec(View view) {

        if(isEmpty){
            mView.showToast("立即前往购买");
        }else {
            mView.showToast("买入");
        }
    }
}
