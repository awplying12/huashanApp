package com.karazam.huashanapp.my.realname.view.activity;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;

import com.example.utils.base.BaseActivity;
import com.example.utils.utils.BitmapUtil;
import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityAuthenticatedBinding;
import com.karazam.huashanapp.main.BankLogo;
import com.karazam.huashanapp.main.Bean.UserInformation;
import com.karazam.huashanapp.my.realname.model.databinding.RealnameEntity;
import com.karazam.huashanapp.my.realname.view.AuthenticatedView;
import com.karazam.huashanapp.my.realname.viewmodel.AuthenticatedViewModel.AuthenticatedViewModel;
import com.karazam.huashanapp.my.realname.viewmodel.AuthenticatedViewModel.AuthenticatedViewModelImpl;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxView;

/**
 * Created by Administrator on 2016/11/24.
 */

public class AuthenticatedActivity extends BaseActivity implements AuthenticatedView{

    private ActivityAuthenticatedBinding binding;
    private AuthenticatedViewModel mModel;
    private RealnameEntity entity = new RealnameEntity();



    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_authenticated);
        mModel = new AuthenticatedViewModelImpl(this,entity,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
    }

    @Override
    public void dealLogicBeforeInitView() {

    }

    @Override
    public void initView() {


    }

    @Override
    public void dealLogicAfterInitView() {
        setBankCard();
    }

    /**
     * 银行哈卡
     */
    private void setBankCard() {

        RxView.findById(this,R.id.bankcard).bind(HuaShanApplication.userInformationR, new Rx.Action<View, UserInformation>() {
            @Override
            public void call(View target, UserInformation userInformation) {
                ImageView logo = (ImageView) target.findViewById(R.id.img_logo);

                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.zgyh_icon);

                logo.setImageBitmap(BitmapUtil.toRoundBitmap(BankLogo.setLogo(bitmap,AuthenticatedActivity.this)));
            }
        });
    }
}
