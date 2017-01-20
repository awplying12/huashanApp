package com.karazam.huashanapp.my.myreturn.main.view.activity;

import android.databinding.DataBindingUtil;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.utils.base.BaseActivity;
import com.example.utils.custom.RefreshRecyclerView;
import com.example.utils.custom.WrapContentLinearLayoutManager;
import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityMyreturnBinding;
import com.karazam.huashanapp.main.Bean.MyAssets.MyAssetsBean;

import com.karazam.huashanapp.my.myfinancing.main.view.view.NofinanceView;
import com.karazam.huashanapp.my.myreturn.main.model.databinding.MyReturnBean;
import com.karazam.huashanapp.my.myreturn.main.model.databinding.MyReturnEntity;
import com.karazam.huashanapp.my.myreturn.main.model.databinding.ReturnRecords;
import com.karazam.huashanapp.my.myreturn.main.view.MyReturnView;
import com.karazam.huashanapp.my.myreturn.main.view.view.RecordsAdapter;
import com.karazam.huashanapp.my.myreturn.main.viewmodel.MyReturnViewModel;
import com.karazam.huashanapp.my.myreturn.main.viewmodel.MyReturnViewModelImpl;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxProperty;
import com.ogaclejapan.rx.binding.RxView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/7.
 */

public class MyReturnActivity extends BaseActivity implements MyReturnView,SwipeRefreshLayout.OnRefreshListener {

    private ActivityMyreturnBinding binding;
    private MyReturnEntity entity = new MyReturnEntity();
    private MyReturnViewModel mModel;

    private TextView btn_finance;
    private SwipeRefreshLayout swl_pl;

    private RefreshRecyclerView content_rl;

    private static int page = 1;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_myreturn);
        mModel = new MyReturnViewModelImpl(entity,this,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
        activities.add(this);
    }

    @Override
    public void dealLogicBeforeInitView() {


//        RecordsMode.set(HuaShanApplication.returnRecordses);

    }

    @Override
    public void initView() {
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


        swl_pl = (SwipeRefreshLayout) getView(R.id.swl_pl);
        swl_pl.setOnRefreshListener(this);
        swl_pl.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        content_rl.setSwl_pl(swl_pl);

    }

    @Override
    public void dealLogicAfterInitView() {
        setLayout();

        Refresh();

    }

    /**
     * 设置布局内容
     */
//    private RxProperty<ArrayList<ReturnRecords>> RecordsMode = RxProperty.create();

    private RxProperty<MyReturnBean> RecordsMode = RxProperty.create();
    private View view;
    private void setLayout() {
        NofinanceView nofinanceView = new NofinanceView(MyReturnActivity.this);
        view = nofinanceView.setView();

//        RxView.findById(this,R.id.label_pl).bind(RecordsMode, new Rx.Action<View, ReturnRecords>() {  //投标中
//            @Override
//            public void call(View target, ReturnRecords financialProject) {
//                ViewGroup g = (ViewGroup) target;
//                if(financialProject.getInformations().size() == 0){ // 没有标
//                    g.addView(view);
//                    btn_finance.setText("立即前往购买");
//                    mModel.isEmpty = true;
//                }else {
//                    g.removeView(view);
//                    setBidindContent(financialProject);
//                    btn_finance.setText("买入");
//                    mModel.isEmpty = false;
//                }
//            }
//        });

        RxView.findById(this,R.id.pf_path_1).bind(HuaShanApplication.myAssetsBeanRX, new Rx.Action<View, MyAssetsBean>() {  //当前理财金额
            @Override
            public void call(View target, MyAssetsBean myAssetsBean) {

                TextView det_income = (TextView) target.findViewById(R.id.det_income);

                String financingMon = myAssetsBean.getWatingCapital();
                financingMon = StringUtil.reservedDecimal(StringUtil.interrupt(financingMon,0,"0"),2);

                det_income.setText(financingMon);

            }
        });



        RxView.findById(this,R.id.label_pl).bind(RecordsMode, new Rx.Action<View, MyReturnBean>() {
            @Override
            public void call(View target, MyReturnBean myReturnBean) {
                ViewGroup g = (ViewGroup) target;
                if(myReturnBean.getRows().size() == 0){ // 没有标
                    g.addView(view);
                    btn_finance.setText("立即前往购买");
                    mModel.isEmpty = true;
                }else {
                    g.removeView(view);
                    setRecordsContent(myReturnBean);
                    btn_finance.setText("买入");
                    mModel.isEmpty = false;
                }
            }
        });


        content_rl.setOnScrollListener(new RecyclerView.OnScrollListener(){ //解决swiperefreshlayout 和 RecyclerView 的滑动冲突
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int topRowVerticalPosition = (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
                swl_pl.setEnabled(topRowVerticalPosition >= 0);
//                content_rl.setEnabled();

            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

    }

    /**
     * 回款记录
     */
    private RecordsAdapter recordsAdapter;
    private ArrayList<ReturnRecords> returnRecordses;
    private void setRecordsContent(MyReturnBean myReturnBean) {

        if(page == 1){
            returnRecordses = myReturnBean.getRows();
        } else {
            returnRecordses.addAll(myReturnBean.getRows());
        }
        recordsAdapter = new RecordsAdapter(MyReturnActivity.this ,returnRecordses,content_rl);
        content_rl.setAdapter(recordsAdapter);

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
    public void onRefresh() {   //下拉刷新
            Refresh();
    }

    /**
     * 刷新
     */
    private void Refresh() {
        page = 1;
        mModel.getMyReturnData(page);
    }

    /**
     * 添加数据
     */
    private void addData(){

        if(page >mModel.allpage){
            showToast("到最后一页了！");
            return;
        }

        mModel.getMyReturnData(page);
    }

    /**
     * 获取我的回款数据成功
     */
    @Override
    public void myReturnSuccess(MyReturnBean bean) {

        swl_pl.setRefreshing(false);

        RecordsMode.set(bean);
    }

    /**
     * 获取我的回款数据失败
     */
    @Override
    public void myReturnFail(String s) {
        swl_pl.setRefreshing(false);
    }

    /**
     * 获取我的回款数据错误
     */
    @Override
    public void myReturnError(Throwable e) {
        swl_pl.setRefreshing(false);
    }


}
