package com.karazam.huashanapp.my.message.main.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.utils.base.BaseActivity;
import com.example.utils.custom.RefreshRecyclerView;
import com.example.utils.custom.WrapContentLinearLayoutManager;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityMessageBinding;
import com.karazam.huashanapp.main.Bean.MessageBean;
import com.karazam.huashanapp.my.message.main.model.databinding.MessageEntity;
import com.karazam.huashanapp.my.message.main.view.MessageView;
import com.karazam.huashanapp.my.message.main.view.view.MessageAdapter;
import com.karazam.huashanapp.my.message.main.viewmodel.MessageViewModel;
import com.karazam.huashanapp.my.message.main.viewmodel.MessageViewModelImpl;
import com.karazam.huashanapp.my.message.messagedetails.view.activity.MessagedetailsActivity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/20.
 */

public class MessageActivity extends BaseActivity implements MessageView,SwipeRefreshLayout.OnRefreshListener {

    private ActivityMessageBinding binding;
    private MessageEntity entity = new MessageEntity();
    private MessageViewModel mModel;

    private SwipeRefreshLayout swl;

    private RefreshRecyclerView rl_message;


    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_message);
        mModel = new MessageViewModelImpl(this,entity,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
        activities.add(this);
    }

    @Override
    public void dealLogicBeforeInitView() {

    }

    @Override
    public void initView() {
        swl = (SwipeRefreshLayout) getView(R.id.swipe_rf);
        swl.setOnRefreshListener(this);
        swl.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);

        rl_message = (RefreshRecyclerView) getView(R.id.rl_message);

    }

    @Override
    public void dealLogicAfterInitView() {
        setMessageRecyclerView();
    }

    /**
     * 设置MessageRecyclerView
     */
    private MessageAdapter adapter;
    private void setMessageRecyclerView() {

        WrapContentLinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false){
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 6000;
            }
        };
        rl_message.setLayoutManager(layoutManager);

        ArrayList<MessageBean> list = new ArrayList<>();
        list.add(new MessageBean(R.drawable.msg_newpro,"新标通知"));
        list.add(new MessageBean(R.drawable.msg_repay,"还款通知"));
        list.add(new MessageBean(R.drawable.msg_system,"系统通知"));
        list.add(new MessageBean(R.drawable.msg_my,"我的消息"));


        adapter = new MessageAdapter(this,list);
        rl_message.setAdapter(adapter);

        adapter.setOnItemClickListener(new MessageAdapter.OnItemClickListener() {
            @Override
            public void onItem(View view, int position) {
                showToast(""+position);

//                toOtherActivity(MessageActivity.this,MessagedetailsActivity.class);
                Intent intent = new Intent(MessageActivity.this,MessagedetailsActivity.class);
                intent.putExtra("title",adapter.getList().get(position).getTitle());
                intent.putExtra("position",position);
                MessageActivity.this.startActivity(intent);


            }
        });

        rl_message.setOnRefreshListener(new RefreshRecyclerView.OnRefreshListener() {
            @Override
            public void onRefreshUp() {
                showToast("onRefresh up");
            }
        });
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        showToast("onRefresh Down");
        swl.setRefreshing(false);
    }
}
