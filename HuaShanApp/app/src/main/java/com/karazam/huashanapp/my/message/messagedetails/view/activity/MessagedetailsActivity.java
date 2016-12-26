package com.karazam.huashanapp.my.message.messagedetails.view.activity;

import android.databinding.DataBindingUtil;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.utils.base.BaseActivity;
import com.example.utils.custom.RefreshRecyclerView;
import com.example.utils.custom.WrapContentLinearLayoutManager;
import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityMessagedetailsBinding;
import com.karazam.huashanapp.my.message.messagedetails.model.databinding.MessagedetailsEntity;
import com.karazam.huashanapp.my.message.messagedetails.view.MessagedetailsView;
import com.karazam.huashanapp.my.message.messagedetails.view.view.DetailsBean;
import com.karazam.huashanapp.my.message.messagedetails.view.view.MessagedetailsAdapter;
import com.karazam.huashanapp.my.message.messagedetails.viewmodel.MessagedetailsViewModel;
import com.karazam.huashanapp.my.message.messagedetails.viewmodel.MessagedetailsViewModelImpl;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/21.
 */

public class MessagedetailsActivity extends BaseActivity implements MessagedetailsView,SwipeRefreshLayout.OnRefreshListener {

    private ActivityMessagedetailsBinding binding;
    private MessagedetailsEntity entity = new MessagedetailsEntity();
    private MessagedetailsViewModel mModel;

    private SwipeRefreshLayout swipe_rf;

    private RefreshRecyclerView rl_messagedetails;

    private TextView title_text;

    private String title;
    private int position;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_messagedetails);
        mModel = new MessagedetailsViewModelImpl(this,entity,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
        activities.add(this);
    }

    @Override
    public void dealLogicBeforeInitView() {
        title = getIntent().getStringExtra("title");
        position = getIntent().getIntExtra("position",-1);
    }

    @Override
    public void initView() {

        title_text = (TextView) getView(R.id.title_text);

        swipe_rf = (SwipeRefreshLayout) getView(R.id.swipe_rf);
        swipe_rf.setOnRefreshListener(this);
        swipe_rf.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);

        rl_messagedetails = (RefreshRecyclerView) getView(R.id.rl_messagedetails);


    }

    @Override
    public void dealLogicAfterInitView() {


        setLayout();

        setMessageRecyclerView();
    }

    /**
     * 设置界面
     */
    private void setLayout() {
        title_text.setText(StringUtil.interrupt(title,0,"未知"));

        switch (position){
            case 0:     //新标通知
                break;
            case 1:     //还款通知
                break;
            case 2:     //系统通知
                break;
            case 3:     //我的消息
                break;
            default:
                break;
        }
    }


    /**
     * 设置MessageRecyclerView
     */
    private void setMessageRecyclerView() {

        WrapContentLinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false){
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 6000;
            }
        };
        rl_messagedetails.setLayoutManager(layoutManager);


        ArrayList<DetailsBean> list = new ArrayList<>();
        list.add(new DetailsBean());
        list.add(new DetailsBean());
        list.add(new DetailsBean());

        rl_messagedetails.setAdapter(new MessagedetailsAdapter(MessagedetailsActivity.this,list));


        rl_messagedetails.setOnRefreshListener(new RefreshRecyclerView.OnRefreshListener() {
            @Override
            public void onRefreshUp() {
                showToast("onRefresh up");
            }
        });
    }

    @Override
    public void onRefresh() {
        showToast("onRefresh Down");
        swipe_rf.setRefreshing(false);
    }
}
