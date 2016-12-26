package com.karazam.huashanapp.my.mysettings.view.activity;

import android.content.res.ColorStateList;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.utils.base.BaseActivity;
import com.example.utils.base.BaseView;
import com.example.utils.custom.FloatLabeledEditText.FloatLabeledEditText;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityMysettings2Binding;
import com.karazam.huashanapp.my.mysettings.model.databinding.MysettingsEntity;
import com.karazam.huashanapp.my.mysettings.view.MysettingsView2;
import com.karazam.huashanapp.my.mysettings.viewmodel.MysettingsViewModelImpl2.MysettingsViewModel2;
import com.karazam.huashanapp.my.mysettings.viewmodel.MysettingsViewModelImpl2.MysettingsViewModelImpl2;

import java.util.concurrent.TimeUnit;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Administrator on 2016/11/23.
 */

public class MysettingsActivity2 extends BaseActivity implements MysettingsView2 {

    private ActivityMysettings2Binding binding;
    private MysettingsViewModel2 mModel;
    private MysettingsEntity entity = new MysettingsEntity();

    private FloatLabeledEditText fet;


    private ImageView clean;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mysettings2);
        mModel = new MysettingsViewModelImpl2(this,entity,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
        activities.add(this);
    }

    @Override
    public void dealLogicBeforeInitView() {

    }

    @Override
    public void initView() {
        fet = (FloatLabeledEditText) getView(R.id.fet);
        fet.setHintTextViewColor(Color.parseColor("#0894EC"));

        mModel.ed_nick = (EditText) getView(R.id.nickname_ed);

        clean = (ImageView) getView(R.id.clean);
    }

    @Override
    public void dealLogicAfterInitView() {
        checkEditText();
    }

    /**
     * 检查EditText
     */
    private void checkEditText() {
        RxTextView.textChangeEvents(mModel.ed_nick)
                .debounce(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TextViewTextChangeEvent>() {
                    @Override
                    public void call(TextViewTextChangeEvent textViewTextChangeEvent) {
                        String str = textViewTextChangeEvent.text().toString().trim();

                        if(TextUtils.isEmpty(str)){
                            clean.setVisibility(View.GONE);
                        }else {
                            clean.setVisibility(View.VISIBLE);
                        }
                    }
                });

    }


}
