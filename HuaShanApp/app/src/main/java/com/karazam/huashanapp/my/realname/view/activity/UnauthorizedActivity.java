package com.karazam.huashanapp.my.realname.view.activity;

import android.databinding.DataBindingUtil;
import android.graphics.Color;

import com.example.utils.base.BaseActivity;
import com.example.utils.custom.FloatLabeledEditText.FloatLabeledEditText;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityUnauthorizedBinding;
import com.karazam.huashanapp.my.realname.model.databinding.RealnameEntity;
import com.karazam.huashanapp.my.realname.view.UnauthorizedView;
import com.karazam.huashanapp.my.realname.viewmodel.UnauthorizedViewModel.UnauthorizedViewModel;
import com.karazam.huashanapp.my.realname.viewmodel.UnauthorizedViewModel.UnauthorizedViewModelImpl;

/**
 * Created by Administrator on 2016/11/24.
 */

public class UnauthorizedActivity extends BaseActivity implements UnauthorizedView{

    private ActivityUnauthorizedBinding binding;
    private RealnameEntity entity = new RealnameEntity();
    private UnauthorizedViewModel mModel;

    private FloatLabeledEditText fet_name;
    private FloatLabeledEditText fet_idnum;
    private FloatLabeledEditText fet_cardnum;
    private FloatLabeledEditText fet_bank;
    private FloatLabeledEditText fet_phonenum;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_unauthorized);
        mModel = new UnauthorizedViewModelImpl(this,entity,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
    }

    @Override
    public void dealLogicBeforeInitView() {

    }

    @Override
    public void initView() {
        fet_name = (FloatLabeledEditText) getView(R.id.fet_name);
        fet_name.setHintTextViewColor(Color.parseColor("#0894EC"));
        fet_idnum = (FloatLabeledEditText) getView(R.id.fet_idnum);
        fet_idnum.setHintTextViewColor(Color.parseColor("#0894EC"));
        fet_cardnum = (FloatLabeledEditText) getView(R.id.fet_cardnum);
        fet_cardnum.setHintTextViewColor(Color.parseColor("#0894EC"));
        fet_bank = (FloatLabeledEditText) getView(R.id.fet_bank);
        fet_bank.setHintTextViewColor(Color.parseColor("#0894EC"));
        fet_phonenum = (FloatLabeledEditText) getView(R.id.fet_phonenum);
        fet_phonenum.setHintTextViewColor(Color.parseColor("#0894EC"));
    }

    @Override
    public void dealLogicAfterInitView() {

    }
}
