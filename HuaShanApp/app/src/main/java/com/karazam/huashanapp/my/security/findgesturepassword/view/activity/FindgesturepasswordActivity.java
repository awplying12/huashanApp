package com.karazam.huashanapp.my.security.findgesturepassword.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.print.PageRange;
import android.view.View;
import android.view.ViewGroup;

import com.example.utils.base.BaseActivity;
import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityFindgesturepasswordBinding;
import com.karazam.huashanapp.home.view.activity.HomeActivity;
import com.karazam.huashanapp.main.dialog.InputContentView;
import com.karazam.huashanapp.my.security.findgesturepassword.model.databinding.FindgesturepasswordEntity;
import com.karazam.huashanapp.my.security.findgesturepassword.view.FindgesturepasswordView;
import com.karazam.huashanapp.my.security.findgesturepassword.viewmodel.FindgesturepasswordViewModel;
import com.karazam.huashanapp.my.security.findgesturepassword.viewmodel.FindgesturepasswordViewModelImpl;

import huashanapp.karazam.com.gesture_lock.GestureEditActivity;
import huashanapp.karazam.com.gesture_lock.GestureUtil;

/**
 * Created by Administrator on 2016/12/15.
 */

public class FindgesturepasswordActivity extends BaseActivity implements FindgesturepasswordView {

    private ActivityFindgesturepasswordBinding binding;
    private FindgesturepasswordEntity entity = new FindgesturepasswordEntity();
    private FindgesturepasswordViewModel mModel;

    private InputContentView inputView;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_findgesturepassword);
        mModel = new FindgesturepasswordViewModelImpl(this,entity,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
        activities.add(this);
    }

    @Override
    public void dealLogicBeforeInitView() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void dealLogicAfterInitView() {
        Intent intent = new Intent(this, GestureEditActivity.class);
        intent.putExtra("verification","verification");
        startActivityForResult(intent, GestureUtil.GESTURELOCK_REQUESTCODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == GestureUtil.GESTURELOCK_REQUESTCODE){

            Intent intent = new Intent(FindgesturepasswordActivity.this, HomeActivity.class);

            switch (resultCode){
                case GestureUtil.GESTURELOCK_EDIT_RESULTCODE:
                    String key = data.getStringExtra(GestureUtil.Password);
                    HuaShanApplication.editor.putString("gesture_lock",key).commit();

                    startActivity(intent);
                    finish();
                    break;
                case GestureUtil.GESTURELOCK_VERIFY_RESULTCODE:

                    startActivity(intent);
                    finish();

                    break;
                default:
                    finish();
                    break;
            }


        }

    }
}
