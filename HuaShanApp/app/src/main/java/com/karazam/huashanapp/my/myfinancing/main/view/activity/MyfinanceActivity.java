package com.karazam.huashanapp.my.myfinancing.main.view.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.utils.base.BaseActivity;
import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityMyfinanceBinding;
import com.karazam.huashanapp.main.adapter.TitleBarAdapter;
import com.karazam.huashanapp.main.financialproject.FinancialProject;
import com.karazam.huashanapp.my.myfinancing.main.model.databinding.MyfinanceEntity;
import com.karazam.huashanapp.my.myfinancing.main.view.MyfinanceView;
import com.karazam.huashanapp.my.myfinancing.main.view.view.ContentAdapter;
import com.karazam.huashanapp.my.myfinancing.main.view.view.NofinanceView;
import com.karazam.huashanapp.my.myfinancing.main.viewmodel.MyfinanceViewModel;
import com.karazam.huashanapp.my.myfinancing.main.viewmodel.MyfinanceViewModelImpl;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/5.
 */

public class MyfinanceActivity extends BaseActivity implements MyfinanceView {

    private ActivityMyfinanceBinding binding;
    private MyfinanceViewModel mModel;
    private MyfinanceEntity entity = new MyfinanceEntity();

    private RecyclerView title_rl;
    private TitleBarAdapter titlebarAdapter;

    private TextView btn_finance;

    private RecyclerView content_rl;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_myfinance);
        mModel = new MyfinanceViewModelImpl(entity,this,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
    }

    @Override
    public void dealLogicBeforeInitView() {

    }

    @Override
    public void initView() {
        title_rl = (RecyclerView) getView(R.id.title_rl);
        LinearLayoutManager lm = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        title_rl.setLayoutManager(lm);

        btn_finance = (TextView) getView(R.id.btn_finance);

        content_rl = (RecyclerView) getView(R.id.content_rl);
        LinearLayoutManager lm1 = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        content_rl.setLayoutManager(lm1);
    }

    @Override
    public void dealLogicAfterInitView() {
            setTileRl();
            setLayout();
    }

    /**
     * 设置标题栏
     */
    private void setTileRl() {

        ArrayList<String> list = new ArrayList<>();
        list.add("投标中");
        list.add("已持有");
        list.add("已完成");

        titlebarAdapter = new TitleBarAdapter(list,this,15);
        title_rl.setAdapter(titlebarAdapter);

        titlebarAdapter.setmOnItemClickListener(new TitleBarAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position){
                    case 0:     //投标中
                        break;
                    case 1:     //已持有
                        break;
                    case 2:     //已完成
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * 设置布局内容
     */
    private void setLayout() {

        RxView.findById(this,R.id.label_pl).bind(HuaShanApplication.financialProjectR, new Rx.Action<View, FinancialProject>() {
            @Override
            public void call(View target, FinancialProject financialProject) {
                if(financialProject.getInformations().size() == 0){ // 没有标
                    NofinanceView view = new NofinanceView(MyfinanceActivity.this);
                    ViewGroup g = (ViewGroup) target;
                    g.addView(view.setView((ViewGroup) target));
                    btn_finance.setText("立即前往购买");
                }else {

                    setContentRL(financialProject);
                    btn_finance.setText("买入");
                }
            }
        });
    }

    /**
     * 理财内容
     */
    private void setContentRL(final FinancialProject financialProject){



        content_rl.setAdapter(new ContentAdapter(MyfinanceActivity.this,financialProject.getInformations()));
    }
}
