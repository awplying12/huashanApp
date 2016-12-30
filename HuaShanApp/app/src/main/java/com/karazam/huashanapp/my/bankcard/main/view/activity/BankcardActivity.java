package com.karazam.huashanapp.my.bankcard.main.view.activity;

import android.databinding.DataBindingUtil;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.utils.base.BaseActivity;
import com.example.utils.custom.RefreshRecyclerView;
import com.example.utils.custom.WrapContentLinearLayoutManager;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityBankcardBinding;
import com.karazam.huashanapp.main.adapter.TitleBarAdapter;
import com.karazam.huashanapp.manage.main.model.databinding.Project;
import com.karazam.huashanapp.manage.main.view.view.ContentAdapter;
import com.karazam.huashanapp.my.bankcard.main.model.databinding.BankcardEntity;
import com.karazam.huashanapp.my.bankcard.main.view.BankcardView;
import com.karazam.huashanapp.my.bankcard.main.view.view.BankcardAdapter;
import com.karazam.huashanapp.my.bankcard.main.view.view.BankcardBean;
import com.karazam.huashanapp.my.bankcard.main.view.view.WithdrawalscardAdapter;
import com.karazam.huashanapp.my.bankcard.main.viewmodel.BankcardViewModel;
import com.karazam.huashanapp.my.bankcard.main.viewmodel.BankcardViewModelImpl;

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


        content_rl.setOnRefreshListener(new RefreshRecyclerView.OnRefreshListener() {
            @Override
            public void onRefreshUp() {

            }
        });

    }

    @Override
    public void dealLogicAfterInitView() {
        setTitlebBar();
        setBankcardRecyclerView();

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

        WrapContentLinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false){
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 6000;
            }
        };
        content_rl.setLayoutManager(layoutManager);

        ArrayList<BankcardBean> list = new ArrayList<>();
        list.add(new BankcardBean());
        list.add(new BankcardBean());
        list.add(new BankcardBean());
        list.add(new BankcardBean());

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
            public void onBottomView(View view) {
//                showToast("onBottomView");
                mModel.toBindcard(null);
            }
        });


    }

    /**
     * 提现卡设置
     */
    private WithdrawalscardAdapter wadapter;
    private void setWithdrawalscardRecyclerView() {

        WrapContentLinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false){
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 6000;
            }
        };
        content_rl.setLayoutManager(layoutManager);

        ArrayList<BankcardBean> list = new ArrayList<>();
        list.add(new BankcardBean());

        wadapter = new WithdrawalscardAdapter(this,list);
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
                mModel.toBindcard(null);
            }
        });

    }


}
