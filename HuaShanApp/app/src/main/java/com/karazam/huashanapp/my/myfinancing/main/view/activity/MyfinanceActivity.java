package com.karazam.huashanapp.my.myfinancing.main.view.activity;

import android.databinding.DataBindingUtil;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.paymentpassword.PasswordView;
import com.example.utils.base.BaseActivity;
import com.example.utils.custom.RefreshRecyclerView;
import com.example.utils.custom.WrapContentLinearLayoutManager;
import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityMyfinanceBinding;
import com.karazam.huashanapp.main.Bean.MyAssets.MyAssetsBean;
import com.karazam.huashanapp.main.adapter.TitleBarAdapter;
import com.karazam.huashanapp.main.Bean.financialproject.FinancialProject;
import com.karazam.huashanapp.my.myfinancing.main.model.databinding.CompletedBean;
import com.karazam.huashanapp.my.myfinancing.main.model.databinding.InvestingBean;
import com.karazam.huashanapp.my.myfinancing.main.model.databinding.MyfinanceBean;
import com.karazam.huashanapp.my.myfinancing.main.model.databinding.MyfinanceEntity;
import com.karazam.huashanapp.my.myfinancing.main.model.databinding.RepayingBean;
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

public class MyfinanceActivity extends BaseActivity implements MyfinanceView,SwipeRefreshLayout.OnRefreshListener {

    private ActivityMyfinanceBinding binding;
    private MyfinanceViewModel mModel;
    private MyfinanceEntity entity = new MyfinanceEntity();

    private RecyclerView title_rl;
    private TitleBarAdapter titlebarAdapter;

    private TextView det_income;

    private TextView btn_finance;

    private RefreshRecyclerView content_rl;

    private AssignmentView assignmentView;

//    private PasswordView pwd_view;
    private SwipeRefreshLayout swl_pl;

    private static int page = 1;

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
        mModel.mProgress = "investing";
    }

    @Override
    public void initView() {
        title_rl = (RecyclerView) getView(R.id.title_rl);
        LinearLayoutManager lm = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        title_rl.setLayoutManager(lm);

        btn_finance = (TextView) getView(R.id.btn_finance);

        content_rl = (RefreshRecyclerView) getView(R.id.content_rl);
//        LinearLayoutManager lm1 = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        WrapContentLinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false){
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 6000;
            }
        };
        content_rl.setLayoutManager(layoutManager);

        assignmentView = new AssignmentView(this);

//        pwd_view = (PasswordView) getView(R.id.pwd_view);

        swl_pl = (SwipeRefreshLayout) getView(R.id.swl_pl);
        swl_pl.setOnRefreshListener(this);
        swl_pl.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
    }

    @Override
    public void dealLogicAfterInitView() {
            setTileRl();
            setLayout();

            Refresh();
    }

    /**
     * 刷新
     */
    private void Refresh() {
        page = 1;
        mModel.getMyfinanceData(mModel.mProgress,page);
    }

    /**
     * 添加数据
     */
    private void addData(){

        if(page >mModel.allpage){
            showToast("到最后一页了！");
            return;
        }

        mModel.getMyfinanceData(mModel.mProgress,page);
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
//                        BidindMode.set(HuaShanApplication.project1);
                        mModel.mProgress = "investing";
                        break;
                    case 1:     //已持有
//                        holdingMode.set(HuaShanApplication.project2);
                        mModel.mProgress = "repaying";
                        break;
                    case 2:     //已完成
//                        finishedMode.set(HuaShanApplication.project3);
                        mModel.mProgress = "completed";
                        break;
                    default:
                        break;
                }

                Refresh();
            }
        });

//        BidindMode.set(HuaShanApplication.project1);
    }

    /**
     * 设置布局内容
     */

    private RxProperty<MyfinanceBean> BidindMode = RxProperty.create();
    private RxProperty<MyfinanceBean> holdingMode = RxProperty.create();
    private RxProperty<MyfinanceBean> finishedMode = RxProperty.create();
    private View view;
    private void setLayout() {
        NofinanceView nofinanceView = new NofinanceView(MyfinanceActivity.this);
        view = nofinanceView.setView();

        RxView.findById(this,R.id.pf_path_1).bind(HuaShanApplication.myAssetsBeanRX, new Rx.Action<View, MyAssetsBean>() {  //当前理财金额
            @Override
            public void call(View target, MyAssetsBean myAssetsBean) {

                TextView det_income = (TextView) target.findViewById(R.id.det_income);

                String financingMon = myAssetsBean.getWatingCapital();
                financingMon = StringUtil.reservedDecimal(StringUtil.interrupt(financingMon,0,"0"),2);

                det_income.setText(financingMon);

            }
        });

        RxView.findById(this,R.id.label_pl).bind(BidindMode, new Rx.Action<View, MyfinanceBean>() {
            @Override
            public void call(View target, MyfinanceBean myfinanceBean) {
                ViewGroup g = (ViewGroup) target;
                if(myfinanceBean.getInvesting().size() == 0 && page == 1){ // 没有标
                    g.addView(view);
                    btn_finance.setText("立即前往购买");
                    mModel.isEmpty = true;
                }else {
                    g.removeView(view);
                    setBidindContent(myfinanceBean);
                    btn_finance.setText("买入");
                    mModel.isEmpty = false;
                }
            }
        });

        RxView.findById(this,R.id.label_pl).bind(holdingMode, new Rx.Action<View, MyfinanceBean>() {
            @Override
            public void call(View target, MyfinanceBean myfinanceBean) {
                    ViewGroup g = (ViewGroup) target;
                    if(myfinanceBean.getRepaying().size() == 0){ // 没有标
                        g.addView(view);
                        btn_finance.setText("立即前往购买");
                        mModel.isEmpty = true;
                    }else {
                        g.removeView(view);
                        setHoldingContent(myfinanceBean);
                        btn_finance.setText("买入");
                        mModel.isEmpty = false;
                    }
            }
        });


        RxView.findById(this,R.id.label_pl).bind(finishedMode, new Rx.Action<View, MyfinanceBean>() {
            @Override
            public void call(View target, MyfinanceBean myfinanceBean) {
                ViewGroup g = (ViewGroup) target;
                if(myfinanceBean.getCompleted().size() == 0){ // 没有标

                    g.addView(view);
                    btn_finance.setText("立即前往购买");
                    mModel.isEmpty = true;
                }else {
                    g.removeView(view);
                    setFinishedContent(myfinanceBean);
                    btn_finance.setText("买入");
                    mModel.isEmpty = false;
                }
            }
        });

//        pwd_view.setOnPasswordViewListener(new PasswordView.OnPasswordViewListener() {
//            @Override
//            public void inputFinish() {
//                showToast(pwd_view.getStrPassword());
//                if(HuaShanApplication.project2.getInformations().get(mPosition).isState()){
//                    HuaShanApplication.project2.getInformations().get(mPosition).setState(false);
//                    holdingMode.set(HuaShanApplication.project2);
//                }else {
//                    HuaShanApplication.project2.getInformations().get(mPosition).setState(true);
//                    holdingMode.set(HuaShanApplication.project2);
//                }
//
//                pwd_view.out();
//            }
//
//            @Override
//            public void onBack(View v) {
//                pwd_view.out();
//            }
//
//            @Override
//            public void onForgetpassword(View v) {
//
//            }
//        });

//        assignmentView.setView((ViewGroup) getView(R.id.content_pl), new AssignmentView.OnAssignmentViewListener() {
//            @Override
//            public void onTransfer() {
//                showToast("转让");
////                pwd_view.show();
//                assignmentView.dismiss();
//            }
//
//            @Override
//            public void onCancel() {
//                assignmentView.dismiss();
//            }
//        });

    }

    /**
     * 投标中内容
     */
    private BidingAdapter bidingAdapter;
    private ArrayList<InvestingBean> investing;
    private void setBidindContent(final MyfinanceBean myfinanceBean){


//        ArrayList<InvestingBean> investing = myfinanceBean.getInvesting();

        if(page == 1){
            investing = myfinanceBean.getInvesting();
        } else {
            investing.addAll(myfinanceBean.getInvesting());
        }

        bidingAdapter = new BidingAdapter(MyfinanceActivity.this,investing,content_rl);
        content_rl.setAdapter(bidingAdapter);

        page++;
    }

    /**
     * 已持有内容
     */
    private HoldingAdapter holdingAdapter;
    private ArrayList<RepayingBean> repaying;
    private int mPosition = 0;
    private void setHoldingContent(final MyfinanceBean myfinanceBean){

        if(page == 1){
            repaying = myfinanceBean.getRepaying();
        } else {
            repaying.addAll(myfinanceBean.getRepaying());
        }

        holdingAdapter = new HoldingAdapter(MyfinanceActivity.this,repaying,content_rl);
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
//                showToast("转让  "+position);
//                mPosition = position;
//                assignmentView.setFinancialInformation(holdingAdapter.getList().get(position));
//                assignmentView.show();
            }

        });

        page++;

    }

    /**
     * 已完成内容
     */
    private FinishedAdapter finishedAdapter;
    private ArrayList<CompletedBean> completed;

    private void setFinishedContent(final MyfinanceBean myfinanceBean){

        if(page == 1){
            completed = myfinanceBean.getCompleted();
        } else {
            completed.addAll(myfinanceBean.getCompleted());
        }

        finishedAdapter = new FinishedAdapter(MyfinanceActivity.this,completed,content_rl);
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

        page++;

    }

    @Override
    protected void onStart() {
        super.onStart();

        content_rl.setOnRefreshListener(new RefreshRecyclerView.OnRefreshListener() { //上拉加载
            @Override
            public void onRefreshUp() {
//                showToast("onRefreshUp");
                addData();
            }
        });
    }

    @Override
    public void onRefresh() { //下拉刷新

        Refresh();
    }

    /**
     * 获取我的理财数据成功
     */
    @Override
    public void myfinanceSuccess(MyfinanceBean bean) {

        swl_pl.setRefreshing(false);

        if(content_rl.getVisibility() != View.VISIBLE){
            content_rl.setVisibility(View.VISIBLE);
        }

        if (mModel.mProgress.equals("investing")){
            BidindMode.set(bean);
        } else if(mModel.mProgress.equals("repaying")){
            holdingMode.set(bean);
        } else if(mModel.mProgress.equals("completed")){
            finishedMode.set(bean);
        }



    }

    /**
     * 获取我的理财数据失败
     */
    @Override
    public void myfinanceFail(String s) {

        swl_pl.setRefreshing(false);

        showToast(s);

        if(content_rl.getVisibility() == View.VISIBLE){
            content_rl.setVisibility(View.GONE);
        }
    }

    /**
     * 获取我的理财数据错误
     */
    @Override
    public void myfinanceeError(Throwable e) {

        swl_pl.setRefreshing(false);

        showToast("网络故障！");

        if(content_rl.getVisibility() == View.VISIBLE){
            content_rl.setVisibility(View.GONE);
        }
    }


}
