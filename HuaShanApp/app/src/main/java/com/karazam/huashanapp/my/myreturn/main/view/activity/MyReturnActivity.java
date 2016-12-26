package com.karazam.huashanapp.my.myreturn.main.view.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.utils.base.BaseActivity;
import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityMyreturnBinding;
import com.karazam.huashanapp.main.Bean.financialproject.ReturnRecords;
import com.karazam.huashanapp.my.myfinancing.main.view.view.NofinanceView;
import com.karazam.huashanapp.my.myreturn.main.model.databinding.MyReturnEntity;
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

public class MyReturnActivity extends BaseActivity implements MyReturnView {

    private ActivityMyreturnBinding binding;
    private MyReturnEntity entity = new MyReturnEntity();
    private MyReturnViewModel mModel;

    private TextView btn_finance;

    private RecyclerView content_rl;


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


        RecordsMode.set(HuaShanApplication.returnRecordses);

    }

    @Override
    public void initView() {
        btn_finance = (TextView) getView(R.id.btn_finance);

        content_rl = (RecyclerView) getView(R.id.content_rl);
        LinearLayoutManager lm1 = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        content_rl.setLayoutManager(lm1);
    }

    @Override
    public void dealLogicAfterInitView() {
        setLayout();

    }

    /**
     * 设置布局内容
     */
    private RxProperty<ArrayList<ReturnRecords>> RecordsMode = RxProperty.create();

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

        RxView.findById(this,R.id.label_pl).bind(RecordsMode, new Rx.Action<View, ArrayList<ReturnRecords>>() {
            @Override
            public void call(View target, ArrayList<ReturnRecords> returnRecordses) {
                                ViewGroup g = (ViewGroup) target;
                if(returnRecordses.size() == 0){ // 没有标
                    g.addView(view);
                    btn_finance.setText("立即前往购买");
                    mModel.isEmpty = true;
                }else {
                    g.removeView(view);
                    setRecordsContent(returnRecordses);
                    btn_finance.setText("买入");
                    mModel.isEmpty = false;
                }
            }
        });
    }

    /**
     * 回款记录
     */
    private RecordsAdapter recordsAdapter;
    private void setRecordsContent(ArrayList<ReturnRecords> returnRecordses) {
        recordsAdapter = new RecordsAdapter(MyReturnActivity.this ,returnRecordses,content_rl);
        content_rl.setAdapter(recordsAdapter);
    }
}
