package com.karazam.huashanapp.my.transactiondetails.main.view.activity;

import android.databinding.DataBindingUtil;
import android.util.Log;

import com.example.stickylistview_library.ExpandableStickyListHeadersListView;
import com.example.utils.base.BaseActivity;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityTransactionBinding;
import com.karazam.huashanapp.my.transactiondetails.main.model.databinding.TransactionEntity;
import com.karazam.huashanapp.my.transactiondetails.main.view.TransactionView;
import com.karazam.huashanapp.my.transactiondetails.main.view.activity.view.TransactionAdapter;
import com.karazam.huashanapp.my.transactiondetails.main.view.activity.view.TransactionBean;
import com.karazam.huashanapp.my.transactiondetails.main.viewmodel.TransactionViewModel;
import com.karazam.huashanapp.my.transactiondetails.main.viewmodel.TransactionViewModelImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/12/8.
 */

public class TransactionActivity extends BaseActivity implements TransactionView{

    private ActivityTransactionBinding binding;
    private TransactionViewModel mModel;
    private TransactionEntity entity = new TransactionEntity();

    private ExpandableStickyListHeadersListView mListView;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_transaction);
        mModel = new TransactionViewModelImpl(this,entity,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
    }

    @Override
    public void dealLogicBeforeInitView() {

    }

    @Override
    public void initView() {
        mListView = (ExpandableStickyListHeadersListView) getView(R.id.transaction_lv);
    }

    @Override
    public void dealLogicAfterInitView() {
        setList();
    }

    /**
     * 设置记录List
     */
    private void setList() {
        ArrayList<TransactionBean> beans = new ArrayList();
        beans.addAll(createTempData("2016-12-09"));
        beans.addAll(createTempData("2016-12-08"));
        beans.addAll(createTempData("2016-5-09"));
        beans.addAll(createTempData("2016-03-20"));
        beans.addAll(createTempData("2016-03-10"));
        beans.addAll(createTempData("2016-02-20"));
        beans.addAll(createTempData("2016-01-20"));
        beans.addAll(createTempData("2015-12-20"));
        beans.addAll(createTempData("2015-11-20"));
        beans.addAll(createTempData("2015-10-20"));
        beans.addAll(createTempData("2014-09-20"));



//        for(TransactionBean week : beans){
//            Log.i("msg",week.getWeekDay());
//        }
        TransactionAdapter adapter = new TransactionAdapter(this,beans);
        mListView.setAdapter(adapter);

    }

    /**
     * 造数据
     *            日期时间
     * @return
     */
    private List<TransactionBean> createTempData(String dateStr) {
        List<TransactionBean> list = new ArrayList();
        TransactionBean bean;
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 5; i++) {
            bean = new TransactionBean();

//            shop.setCheckedTime(date);
            Date date = null;
            try {
                date = sdf.parse(dateStr);
                bean.setData(date.getTime());
                list.add(bean);
                bean = null;
            } catch (ParseException e) {
                e.printStackTrace();
            }


        }
        return list;
    }

}
