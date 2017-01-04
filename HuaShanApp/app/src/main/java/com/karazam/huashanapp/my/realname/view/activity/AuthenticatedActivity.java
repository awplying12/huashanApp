package com.karazam.huashanapp.my.realname.view.activity;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.utils.base.BaseActivity;
import com.example.utils.utils.BitmapUtil;
import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityAuthenticatedBinding;
import com.karazam.huashanapp.main.BankLogo;
import com.karazam.huashanapp.main.Bean.MyInformation.BaseInfoBean;
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

    private TextView tx_name;
    private TextView tx_id;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_authenticated);
        mModel = new AuthenticatedViewModelImpl(this,entity,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
        activities.add(this);
    }

    @Override
    public void dealLogicBeforeInitView() {

    }

    @Override
    public void initView() {
        tx_name = (TextView) getView(R.id.tx_name);
        tx_id = (TextView) getView(R.id.tx_id);

    }

    @Override
    public void dealLogicAfterInitView() {
        setLayout();
    }

    /**
     * 设置界面
     */
    private void setLayout() {
        RxView.findById(this,R.id.content_pl).bind(HuaShanApplication.baseInfoBeanRX, new Rx.Action<View, BaseInfoBean>() {
            @Override
            public void call(View target, BaseInfoBean baseInfoBean) {

                String name = baseInfoBean.getRealname();
                tx_name.setText(StringUtil.interrupt(name,0,""));

                String id = baseInfoBean.getIdno();
                id = id.substring(0,1)+"****************"+id.substring(id.length()-1);
                tx_id.setText(StringUtil.interrupt(id,0,""));
            }
        });
    }


}
