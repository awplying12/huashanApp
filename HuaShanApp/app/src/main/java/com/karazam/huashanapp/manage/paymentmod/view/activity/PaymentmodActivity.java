package com.karazam.huashanapp.manage.paymentmod.view.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.utils.base.BaseActivity;
import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityPaymentmodBinding;
import com.karazam.huashanapp.main.UserInformation;
import com.karazam.huashanapp.manage.paymentmod.model.databinding.PaymentmodEntity;
import com.karazam.huashanapp.manage.paymentmod.view.PaymentmodView;
import com.karazam.huashanapp.manage.paymentmod.viewmodel.PaymentmodViewModel;
import com.karazam.huashanapp.manage.paymentmod.viewmodel.PaymentmodViewModelImpl;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxView;

/**
 * Created by Administrator on 2016/11/16.
 */

public class PaymentmodActivity extends BaseActivity implements PaymentmodView{

    private ActivityPaymentmodBinding binding;
    private PaymentmodViewModel mModel;
    private PaymentmodEntity entity = new PaymentmodEntity();

    private LinearLayout content_ll;

    private View view1;
    private View view2;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_paymentmod);
        mModel = new PaymentmodViewModelImpl(entity,this,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
    }

    @Override
    public void dealLogicBeforeInitView() {

    }

    @Override
    public void initView() {
        content_ll = (LinearLayout) getView(R.id.content_ll);
    }

    @Override
    public void dealLogicAfterInitView() {
        setPaymentmodView1();
        setPaymentmodView2();

        content_ll.addView(view1);
        content_ll.addView(view2);
    }


    /**
     * 设置付款方式VIEW1
     */
    private void setPaymentmodView1() {

        view1 = LayoutInflater.from(this).inflate(R.layout.view_paymentmod_item,null);
        RxView.of(view1).bind(HuaShanApplication.userInformationR, new Rx.Action<View, UserInformation>() {
            @Override
            public void call(View target, UserInformation userInformation) {
                TextView pay_method = (TextView) target.findViewById(R.id.pay_method);
                TextView pay_content = (TextView) target.findViewById(R.id.pay_content);
                ImageView pay_img = (ImageView) target.findViewById(R.id.pay_img);

                pay_img.setImageDrawable(getResources().getDrawable(R.drawable.zhye_icon));
                pay_method.setText("账户余额");

                String userbalance = StringUtil.interrupt(userInformation.getUserbalance(),0,"0.00");
                pay_content.setText("可用余额 "+userbalance);

            }
        });

        TextView bt_recharge = (TextView) view1.findViewById(R.id.bt_recharge);
        bt_recharge.setVisibility(View.VISIBLE);

        bt_recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("充值");
            }
        });

        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choicePaymentmod("");
                PaymentmodActivity.this.finish();
            }
        });
    }

    /**
     * 设置付款方式VIEW2
     */
    private void setPaymentmodView2() {

        view2 = LayoutInflater.from(this).inflate(R.layout.view_paymentmod_item,null);
        RxView.of(view2).bind(HuaShanApplication.userInformationR, new Rx.Action<View, UserInformation>() {
            @Override
            public void call(View target, UserInformation userInformation) {
                TextView pay_method = (TextView) target.findViewById(R.id.pay_method);
                TextView pay_content = (TextView) target.findViewById(R.id.pay_content);
                ImageView pay_img = (ImageView) target.findViewById(R.id.pay_img);

                pay_img.setImageDrawable(getResources().getDrawable(R.drawable.zgyh_icon));

                String bankCard = StringUtil.interrupt(userInformation.getBankCard(),0,"未知");
                pay_method.setText(bankCard);

                String cardInformation = StringUtil.interrupt(userInformation.getCardInformation(),0,"未知");
                pay_content.setText(cardInformation);
            }
        });

        view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choicePaymentmod("bankCard");
                PaymentmodActivity.this.finish();
            }
        });
    }

    private void choicePaymentmod(String mod){
        HuaShanApplication.userInformation.setPaymentmod(mod);
        HuaShanApplication.userInformationR.set(HuaShanApplication.userInformation);
    }

}
