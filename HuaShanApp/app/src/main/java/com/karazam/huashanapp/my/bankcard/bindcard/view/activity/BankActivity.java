package com.karazam.huashanapp.my.bankcard.bindcard.view.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.utils.base.BaseActivity;
import com.example.utils.custom.RefreshRecyclerView;
import com.example.utils.custom.WrapContentLinearLayoutManager;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.bankcard.bindcard.model.databinding.BankBean;
import com.karazam.huashanapp.my.bankcard.bindcard.model.databinding.BankPost;
import com.karazam.huashanapp.my.bankcard.bindcard.model.retrofit.GetBankDataSouce;
import com.karazam.huashanapp.my.bankcard.bindcard.view.view.BankAdapter;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxProperty;
import com.ogaclejapan.rx.binding.RxView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/1/4.
 */

public class BankActivity extends BaseActivity implements View.OnClickListener{

    private RefreshRecyclerView rv_bank;

    private GetBankDataSouce dataSource;

    private int mPage = 1;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_bank);
    }

    @Override
    public void dealLogicBeforeInitView() {
        dataSource = new GetBankDataSouce();

        getData(mPage);
    }

    @Override
    public void initView() {
        rv_bank = (RefreshRecyclerView) getView(R.id.rv_bank);
        WrapContentLinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false){
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 6000;
            }
        };
        rv_bank.setLayoutManager(layoutManager);

        getView(R.id.title_back).setOnClickListener(this);


    }

    @Override
    public void dealLogicAfterInitView() {
        setBankRecyclerView();
    }

    /**
     * 设置银行RecyclerView
     */
    private BankAdapter adapter;
    private RxProperty<BankPost> BankPostRX = RxProperty.create();
    private void setBankRecyclerView() {

        ArrayList<BankBean> list = new ArrayList<>();


        adapter = new BankAdapter(this,list);
        rv_bank.setAdapter(adapter);

        adapter.setmOnItemClickListener(new BankAdapter.OnItemClickListener() {
            @Override
            public void isHeaderView(View view) {

            }

            @Override
            public void onItem(View view, int position) {

                BankBean bean = adapter.getList().get(position);

                BindcardActivity.BankBeanRX.set(bean);
                BankActivity.this.finish();
            }

            @Override
            public void onBottomView(View view) {

            }
        });

        rv_bank.setOnRefreshListener(new RefreshRecyclerView.OnRefreshListener() {
            @Override
            public void onRefreshUp() {
                getData(mPage);
            }
        });

        RxView.of(rv_bank).bind(BankPostRX, new Rx.Action<RefreshRecyclerView, BankPost>() {
            @Override
            public void call(RefreshRecyclerView target, BankPost bankPost) {

//                ArrayList<BankBean> list = bankPost.getRows();

                ArrayList<BankBean> list = adapter.getList();
                list.addAll(bankPost.getRows());
                adapter.setList(list);
                adapter.notifyDataSetChanged();

            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.title_back:
                this.finish();
                break;
            default:
                break;
        }
    }

    private boolean isShow = false;
    private void getData(final int page){

        if(isShow){
            return;
        }
        isShow = true;

        dataSource.getBankData(page+"")
                .throttleFirst(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<BaseReturn<BankPost>>() {
                    @Override
                    public void onCompleted() {
                        isShow = false;
                    }

                    @Override
                    public void onError(Throwable e) {
                        showToast("网络故障！");
                        isShow = false;
                    }

                    @Override
                    public void onNext(BaseReturn<BankPost> bankPostBaseReturn) {
                        if(bankPostBaseReturn.isSuccess()){
                            BankPost bean = bankPostBaseReturn.getData();
                            BankPostRX.set(bean);
                            Log.i("bankdata",bean.toString());
                            mPage++;
                        } else {
                            showToast(bankPostBaseReturn.getMessage());
                        }
                    }
                });
    }
}
