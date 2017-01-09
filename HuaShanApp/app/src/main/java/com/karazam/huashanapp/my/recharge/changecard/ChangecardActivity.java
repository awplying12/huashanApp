package com.karazam.huashanapp.my.recharge.changecard;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.example.utils.base.BaseActivity;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.main.Bean.MyInformation.CardBean;
import com.karazam.huashanapp.my.recharge.main.view.activity.RechargeActivity;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/1/9.
 */

public class ChangecardActivity extends BaseActivity implements View.OnClickListener{

    private RecyclerView card_rl;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_changecard);
    }

    @Override
    public void dealLogicBeforeInitView() {

    }

    @Override
    public void initView() {
        card_rl = (RecyclerView) getView(R.id.card_rl);
        LinearLayoutManager lm = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        card_rl.setLayoutManager(lm);

        getView(R.id.title_back).setOnClickListener(this);
    }

    @Override
    public void dealLogicAfterInitView() {
        setRecyclerView();
    }

    /**
     * 设置银行卡RecyclerView
     */
    private void setRecyclerView() {



        RxView.of(card_rl).bind(HuaShanApplication.quickCardsRX, new Rx.Action<RecyclerView, ArrayList<CardBean>>() {
            @Override
            public void call(RecyclerView target, ArrayList<CardBean> cardBeen) {

                final CardAdapter adapter = new CardAdapter(cardBeen,ChangecardActivity.this);
                target.setAdapter(adapter);

                adapter.setmOnItemClickListener(new CardAdapter.OnItemClickListener() {
                    @Override
                    public void onItem(View view, int position) {
                        showToast(position+"");
                        CardBean bean = adapter.getList().get(position);

                        RechargeActivity.cardBeanRx.set(bean);

                        ChangecardActivity.this.finish();

                    }
                });
            }
        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.title_back:
                this.finish();
                break;

        }

    }
}
