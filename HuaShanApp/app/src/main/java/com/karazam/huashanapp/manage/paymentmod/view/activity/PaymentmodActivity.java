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
import com.karazam.huashanapp.main.Bean.MyInformation.CardBean;
import com.karazam.huashanapp.main.Bean.UserInformation;
import com.karazam.huashanapp.manage.paymentmod.model.databinding.PaymentmodEntity;
import com.karazam.huashanapp.manage.paymentmod.view.PaymentmodView;
import com.karazam.huashanapp.manage.paymentmod.view.view.PaymentAdapter;
import com.karazam.huashanapp.manage.paymentmod.viewmodel.PaymentmodViewModel;
import com.karazam.huashanapp.manage.paymentmod.viewmodel.PaymentmodViewModelImpl;
import com.karazam.huashanapp.manage.purchase.model.databinding.PurchasBean;
import com.karazam.huashanapp.manage.purchase.view.activity.PurchaseActivity;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/16.
 */

public class PaymentmodActivity extends BaseActivity implements PaymentmodView{

    private ActivityPaymentmodBinding binding;
    private PaymentmodViewModel mModel;
    private PaymentmodEntity entity = new PaymentmodEntity();

    private RecyclerView payment_rl;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_paymentmod);
        mModel = new PaymentmodViewModelImpl(entity,this,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
        activities.add(this);
    }

    @Override
    public void dealLogicBeforeInitView() {

    }

    @Override
    public void initView() {
        payment_rl = (RecyclerView) getView(R.id.payment_rl);
        LinearLayoutManager lm = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        payment_rl.setLayoutManager(lm);


    }

    @Override
    public void dealLogicAfterInitView() {
        setRecyclerView();
    }

    /**
     * 设置付款方式
     */
    private PaymentAdapter adapter;
    private void setRecyclerView() {

        adapter = new PaymentAdapter(this,new ArrayList<CardBean>());
        payment_rl.setAdapter(adapter);

        adapter.setmOnItemClickListener(new PaymentAdapter.OnItemClickListener() {
            @Override
            public void isHeaderView(View view) {
//                showToast("账户余额");
                PurchasBean purchasBean = new PurchasBean();
                purchasBean.setPaymentMethod("BALANCE_PAY");
                purchasBean.setAssets(HuaShanApplication.myAssetsBean);
                PurchaseActivity.purchasBeanRx.set(purchasBean);
                finish();

            }

            @Override
            public void onItem(View view, int position) {
//                showToast("银行卡");

                CardBean cardbean = adapter.getList().get(position);

                PurchasBean purchasBean = new PurchasBean();
                purchasBean.setPaymentMethod("QUICK_PAY");
                purchasBean.setBean(cardbean);
                PurchaseActivity.purchasBeanRx.set(purchasBean);
                finish();
            }

            @Override
            public void onItemLong(View view, int position) {

            }

            @Override
            public void onBottomView(View view) {

            }
        });

        RxView.of(payment_rl).bind(HuaShanApplication.quickCardsRX, new Rx.Action<RecyclerView, ArrayList<CardBean>>() {
            @Override
            public void call(RecyclerView target, ArrayList<CardBean> cardBeen) {

                adapter.setList(cardBeen);
                adapter.notifyDataSetChanged();

            }
        });
    }


}
