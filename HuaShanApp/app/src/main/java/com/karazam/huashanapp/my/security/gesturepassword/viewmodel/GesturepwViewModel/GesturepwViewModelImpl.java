package com.karazam.huashanapp.my.security.gesturepassword.viewmodel.GesturepwViewModel;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.karazam.huashanapp.R;
import com.karazam.huashanapp.main.dialog.InputContentView;
import com.karazam.huashanapp.my.security.gesturepassword.model.databinding.GesturepwEntity;
import com.karazam.huashanapp.my.security.gesturepassword.view.GesturepwView;
import com.karazam.huashanapp.my.security.gesturepassword.view.activity.GesturepwActivity;

/**
 * Created by Administrator on 2016/11/29.
 */

public class GesturepwViewModelImpl extends GesturepwViewModel{

    private GesturepwView mView;
    private GesturepwEntity mEntity;
    private Context context;
    private GesturepwActivity activity;

    private InputContentView inputContentView;

    public GesturepwViewModelImpl(GesturepwView mView, GesturepwEntity mEntity, Context context, GesturepwActivity activity) {
        this.mView = mView;
        this.mEntity = mEntity;
        this.context = context;
        this.activity = activity;


        setInputContentView();
    }

    private void setInputContentView() {
        inputContentView = new InputContentView(context);
        inputContentView.setView((ViewGroup) mView.getView(R.id.content_pl), new InputContentView.OnInputContentListener() {
            @Override
            public void onLeft(View view) {
                inputContentView.dismiss();
            }

            @Override
            public void onRight(View view) {
                String str = inputContentView.getContent();
                if(TextUtils.isEmpty(str)){
                    mView.showToast("请输入密码");
                    return;
                }else {
                    nextStep();
                }

            }
        });
    }


    @Override
    public void onBack(View view) {
        mView.FinishActivity(activity);
    }


    @Override
    public void onGesture(View view) {
        inputContentView.show();
    }

    /**
     * 下一步
     */
    private void nextStep() {
        mView.toGestureEdit();
        inputContentView.dismiss();
    }
}
