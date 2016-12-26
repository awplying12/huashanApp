package com.karazam.huashanapp.my.myfinancing.main.view.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.paymentpassword.PasswordView;
import com.example.utils.base.BaseActivity;
import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityMyfinanceBinding;
import com.karazam.huashanapp.main.adapter.TitleBarAdapter;
import com.karazam.huashanapp.main.Bean.financialproject.FinancialProject;
import com.karazam.huashanapp.my.myfinancing.main.model.databinding.MyfinanceEntity;
import com.karazam.huashanapp.my.myfinancing.main.view.MyfinanceView;
import com.karazam.huashanapp.my.myfinancing.main.view.view.AssignmentView;
import com.karazam.huashanapp.my.myfinancing.main.view.view.BidingAdapter;
import com.karazam.huashanapp.my.myfinancing.main.view.view.FinishedAdapter;
import com.karazam.huashanapp.my.myfinancing.main.view.view.HoldingAdapter;
import com.karazam.huashanapp.my.myfinancing.main.view.view.NofinanceView;
import com.karazam.huashanapp.my.myfinancing.main.viewmodel.MyfinanceViewModel;
import com.karazam.huashanapp.my.myfinancing.main.viewmodel.MyfinanceViewModelImpl;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxProperty;
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

    private AssignmentView assignmentView;

    private PasswordView pwd_view;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_myfinance);
        mModel = new MyfinanceViewModelImpl(entity,this,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
        activities.add(this);
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

        assignmentView = new AssignmentView(this);

        pwd_view = (PasswordView) getView(R.id.pwd_view);
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
                        BidindMode.set(HuaShanApplication.project1);
                        break;
                    case 1:     //已持有
                        holdingMode.set(HuaShanApplication.project2);
                        break;
                    case 2:     //已完成
                        finishedMode.set(HuaShanApplication.project3);
                        break;
                    default:
                        break;
                }
            }
        });

        BidindMode.set(HuaShanApplication.project1);
    }

    /**
     * 设置布局内容
     */
    private RxProperty<FinancialProject> BidindMode = RxProperty.create();
    private RxProperty<FinancialProject> holdingMode = RxProperty.create();
    private RxProperty<FinancialProject> finishedMode = RxProperty.create();
    private View view;
    private void setLayout() {
        NofinanceView nofinanceView = new NofinanceView(MyfinanceActivity.this);
        view = nofinanceView.setView();

        RxView.findById(this,R.id.label_pl).bind(BidindMode, new Rx.Action<View, FinancialProject>() {  //投标中
            @Override
            public void call(View target, FinancialProject financialProject) {
                ViewGroup g = (ViewGroup) target;
                if(financialProject.getInformations().size() == 0){ // 没有标
                    g.addView(view);
                    btn_finance.setText("立即前往购买");
                    mModel.isEmpty = true;
                }else {
                    g.removeView(view);
                    setBidindContent(financialProject);
                    btn_finance.setText("买入");
                    mModel.isEmpty = false;
                }
            }
        });

        RxView.findById(this,R.id.label_pl).bind(holdingMode, new Rx.Action<View, FinancialProject>() {  //已持有
            @Override
            public void call(View target, FinancialProject financialProject) {
                ViewGroup g = (ViewGroup) target;
                if(financialProject.getInformations().size() == 0){ // 没有标
                    g.addView(view);
                    btn_finance.setText("立即前往购买");
                    mModel.isEmpty = true;
                }else {
                    g.removeView(view);
                    setHoldingContent(financialProject);
                    btn_finance.setText("买入");
                    mModel.isEmpty = false;
                }
            }
        });

        RxView.findById(this,R.id.label_pl).bind(finishedMode, new Rx.Action<View, FinancialProject>() {  //已完成
            @Override
            public void call(View target, FinancialProject financialProject) {
                ViewGroup g = (ViewGroup) target;
                if(financialProject.getInformations().size() == 0){ // 没有标

                    g.addView(view);
                    btn_finance.setText("立即前往购买");
                    mModel.isEmpty = true;
                }else {
                    g.removeView(view);
                    setFinishedContent(financialProject);
                    btn_finance.setText("买入");
                    mModel.isEmpty = false;
                }
            }
        });

        pwd_view.setOnPasswordViewListener(new PasswordView.OnPasswordViewListener() {
            @Override
            public void inputFinish() {
                showToast(pwd_view.getStrPassword());
                if(HuaShanApplication.project2.getInformations().get(mPosition).isState()){
                    HuaShanApplication.project2.getInformations().get(mPosition).setState(false);
                    holdingMode.set(HuaShanApplication.project2);
                }else {
                    HuaShanApplication.project2.getInformations().get(mPosition).setState(true);
                    holdingMode.set(HuaShanApplication.project2);
                }

                pwd_view.out();
            }

            @Override
            public void onBack(View v) {
                pwd_view.out();
            }

            @Override
            public void onForgetpassword(View v) {

            }
        });

        assignmentView.setView((ViewGroup) getView(R.id.content_pl), new AssignmentView.OnAssignmentViewListener() {
            @Override
            public void onTransfer() {
                showToast("转让");
                pwd_view.show();
                assignmentView.dismiss();
            }

            @Override
            public void onCancel() {
                assignmentView.dismiss();
            }
        });

    }

    /**
     * 投标中内容
     */
    private BidingAdapter bidingAdapter;
    private void setBidindContent(final FinancialProject financialProject){


        bidingAdapter = new BidingAdapter(MyfinanceActivity.this,financialProject.getInformations(),content_rl);
        content_rl.setAdapter(bidingAdapter);
    }

    /**
     * 已持有内容
     */
    private HoldingAdapter holdingAdapter;
    private int mPosition = 0;
    private void setHoldingContent(final FinancialProject financialProject){


        holdingAdapter = new HoldingAdapter(MyfinanceActivity.this,financialProject.getInformations(),content_rl);
        content_rl.setAdapter(holdingAdapter);

        holdingAdapter.setmOnItemClickListener(new HoldingAdapter.OnItemClickListener() {

            @Override
            public void onCheck(int position) {
                showToast("查看  "+position);
            }

            @Override
            public void onDownload(int position) {
                showToast("下载  "+position);
            }

            @Override
            public void onTransfer(int position) {
                showToast("转让  "+position);
                mPosition = position;
                assignmentView.setFinancialInformation(holdingAdapter.getList().get(position));
                assignmentView.show();
            }

        });

    }

    /**
     * 已完成内容
     */
    private FinishedAdapter finishedAdapter;
    private void setFinishedContent(final FinancialProject financialProject){


        finishedAdapter = new FinishedAdapter(MyfinanceActivity.this,financialProject.getInformations(),content_rl);
        content_rl.setAdapter(finishedAdapter);

        finishedAdapter.setmOnItemClickListener(new FinishedAdapter.OnItemClickListener() {

            @Override
            public void onCheck(int position) {
                showToast("查看  "+position);
            }

            @Override
            public void onDownload(int position) {
                showToast("下载  "+position);
            }

        });

    }
}
