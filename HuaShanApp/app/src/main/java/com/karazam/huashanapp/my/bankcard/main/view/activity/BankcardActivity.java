package com.karazam.huashanapp.my.bankcard.main.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.paymentpassword.PasswordView;
import com.example.utils.base.BaseActivity;
import com.example.utils.custom.RefreshRecyclerView;
import com.example.utils.custom.WrapContentLinearLayoutManager;
import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityBankcardBinding;
import com.karazam.huashanapp.main.Bean.MyInformation.CardBean;
import com.karazam.huashanapp.main.adapter.TitleBarAdapter;
import com.karazam.huashanapp.manage.main.model.databinding.Project;
import com.karazam.huashanapp.manage.main.view.view.ContentAdapter;
import com.karazam.huashanapp.my.bankcard.bindcard.model.databinding.BindcardBean;
import com.karazam.huashanapp.my.bankcard.main.model.databinding.BankcardEntity;
import com.karazam.huashanapp.my.bankcard.main.view.BankcardView;
import com.karazam.huashanapp.my.bankcard.main.view.view.BankcardAdapter;
import com.karazam.huashanapp.my.bankcard.main.view.view.BankcardBean;
import com.karazam.huashanapp.my.bankcard.main.view.view.WithdrawalscardAdapter;
import com.karazam.huashanapp.my.bankcard.main.viewmodel.BankcardViewModel;
import com.karazam.huashanapp.my.bankcard.main.viewmodel.BankcardViewModelImpl;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/26.
 */

public class BankcardActivity extends BaseActivity implements BankcardView,SwipeRefreshLayout.OnRefreshListener{

    private ActivityBankcardBinding binding;
    private BankcardViewModel mModel;
    private BankcardEntity entity = new BankcardEntity();

    private RecyclerView titlebar_rl;

    private RefreshRecyclerView content_rl;
    private SwipeRefreshLayout mSwipeLayout;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bankcard);
        mModel = new BankcardViewModelImpl(this,entity,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
        activities.add(this);
    }

    @Override
    public void dealLogicBeforeInitView() {

    }

    @Override
    public void initView() {
        titlebar_rl = (RecyclerView) getView(R.id.titlebar_rl);
        LinearLayoutManager lm = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        titlebar_rl.setLayoutManager(lm);


        mSwipeLayout = (SwipeRefreshLayout) getView(R.id.swp_sl);
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);


        content_rl = (RefreshRecyclerView) getView(R.id.content_rl);
        WrapContentLinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false){
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 6000;
            }
        };
        content_rl.setLayoutManager(layoutManager);


        content_rl.setOnRefreshListener(new RefreshRecyclerView.OnRefreshListener() {
            @Override
            public void onRefreshUp() {

            }
        });


        mModel.pwd_view = (PasswordView) getView(R.id.pwd_view);

    }

    @Override
    public void dealLogicAfterInitView() {
        setTitlebBar();
        setBankcardRecyclerView();

        setPasswordView();
//        setRecyclerView();
    }

    /**
     * 设置标题栏
     */
    private TitleBarAdapter titlebarAdapter;
    private void setTitlebBar() {



        ArrayList<String> list = new ArrayList<>();
        list.add("快捷卡");
        list.add("提现卡");


        titlebarAdapter = new TitleBarAdapter(list,getContext(),16);
        titlebar_rl.setAdapter(titlebarAdapter);

        titlebarAdapter.setmOnItemClickListener(new TitleBarAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                switch (position){
                    case 0: //快捷卡
                        setBankcardRecyclerView();
                        mModel.flag = 1;
                        break;
                    case 1: //提现卡
                        setWithdrawalscardRecyclerView();
                        mModel.flag = 2;
                        break;
                    default:
                        break;
                }

            }
        });
    }

    public void setCurrentItem(int position){
        titlebarAdapter.setCurrentItem(position);
    }

    @Override
    public void onRefresh() {
        mSwipeLayout.setRefreshing(false);
    }

    /**
     *  SwipeRefreshLayout配合RecyclerView
     *  实现下拉刷新和上拉加载更多以及没有数据的显示
     *
     */
    private BankcardAdapter adapter;
    private void setBankcardRecyclerView() {

        ArrayList<CardBean> list = new ArrayList<>();
        list.addAll(HuaShanApplication.myInformation.getQuickCards());

        adapter = new BankcardAdapter(this,list);
        content_rl.setAdapter(adapter);

        adapter.setmOnItemClickListener(new BankcardAdapter.OnItemClickListener() {
            @Override
            public void isHeaderView(View view) {

            }

            @Override
            public void onItem(View view, int position) {

            }

            @Override
            public void onItemLong(View view, int position) {
                showToast(position+"");
                mModel.id = adapter.getList().get(position).getBankCardId();
                mModel.pwd_view.show();
//                mModel.toUnbundling(1,"");
            }

            @Override
            public void onBottomView(View view) {
//                showToast("onBottomView");
                mModel.toBindcard(null);
            }
        });

        RxView.of(new View(this)).bind(HuaShanApplication.quickCardsRX, new Rx.Action<View, ArrayList<CardBean>>() {
            @Override
            public void call(View target, ArrayList<CardBean> cardBeen) {
    //                showToast("bindffffff");
                adapter.setList(cardBeen);
                adapter.notifyDataSetChanged();
            }
        });

    }

    /**
     * 提现卡设置
     */
    private WithdrawalscardAdapter wadapter;
    private void setWithdrawalscardRecyclerView() {

        ArrayList<CardBean> list = new ArrayList<>();
//        list.add(HuaShanApplication.myInformation.getWithdrawCardl());
//        list.add(new BankcardBean());

        wadapter = new WithdrawalscardAdapter(this,list);
//        wadapter.setList(list);
        content_rl.setAdapter(wadapter);


        wadapter.setmOnItemClickListener(new WithdrawalscardAdapter.OnItemClickListener() {
            @Override
            public void isHeaderView(View view) {

            }

            @Override
            public void onItem(View view, int position) {

            }

            @Override
            public void onBottomView(View view) {
//                showToast("onBottomView");
                if(mModel.flag == 2&& wadapter.getList().size() == 0){
                    mModel.flag = 2;
                } else if(mModel.flag == 2&& wadapter.getList().size() != 0){
                    mModel.flag = 3;
                }
                mModel.toBindcard(null);
            }
        });


            RxView.of(new View(this)).bind(HuaShanApplication.withdrawCarRx, new Rx.Action<View, CardBean>() {
                @Override
                public void call(View target, CardBean cardBean) {
                    if(cardBean == null || cardBean.getBankCardId() == null){
                        return;
                    }
                    ArrayList<CardBean> cardBeens = new ArrayList<CardBean>();
                    cardBeens.add(cardBean);
                    wadapter.setList(cardBeens);
                    wadapter.notifyDataSetChanged();
                }
            });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 101){
            switch (resultCode){
                case 1:
                    setBankcardRecyclerView();
                    break;
                case 2:
                    setWithdrawalscardRecyclerView();
                    break;
                case 3:
                    setWithdrawalscardRecyclerView();
                    break;
                default:
                    break;
            }
        }
    }


    /**
     * 设置支付密码控件PasswordView
     */
    private void setPasswordView(){

        mModel.pwd_view.setOnPasswordViewListener(new PasswordView.OnPasswordViewListener() {
            @Override
            public void inputFinish() {
//                showToast(mModel.pwd_view.getStrPassword());
                mModel.pwd_view.out();
//                dialog.show();
//                mModel.upDatecard(StringUtil.interrupt(mModel.pwd_view.getStrPassword(),0,""));
                mModel.toUnbundling(mModel.pwd_view.getStrPassword());
            }

            @Override
            public void onBack(View v) {
                mModel.pwd_view.out();
            }

            @Override
            public void onForgetpassword(View v) {

            }
        });
        mModel.pwd_view.setMoney("银行卡操作");
    }

    /**
     * 解绑成功
     */
    @Override
    public void unBundlingSuccess(BindcardBean bean) {
        adapter.setList(bean.getQuickCards());
        adapter.notifyDataSetChanged();
    }

    /**
     * 解绑失败
     * @param s
     */
    @Override
    public void unBundlingFail(String s) {
        showToast(s);
    }

    /**
     * 解绑错误
     * @param e
     */
    @Override
    public void unBundlingError(Throwable e) {

    }
}
