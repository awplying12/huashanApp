package com.karazam.huashanapp.my.message.messagedetails.view.activity;

import android.databinding.DataBindingUtil;
import android.support.percent.PercentFrameLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.utils.base.BaseActivity;
import com.example.utils.custom.RefreshRecyclerView;
import com.example.utils.custom.WrapContentLinearLayoutManager;
import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityMessagedetailsBinding;
import com.karazam.huashanapp.my.message.main.view.activity.MessageActivity;
import com.karazam.huashanapp.my.message.main.view.view.NoMessageView;
import com.karazam.huashanapp.my.message.messagedetails.model.databinding.MessagedetailsBean;
import com.karazam.huashanapp.my.message.messagedetails.model.databinding.MessagedetailsEntity;
import com.karazam.huashanapp.my.message.messagedetails.view.MessagedetailsView;
import com.karazam.huashanapp.my.message.messagedetails.view.view.DetailsBean;
import com.karazam.huashanapp.my.message.messagedetails.view.view.MessagedetailsAdapter;
import com.karazam.huashanapp.my.message.messagedetails.viewmodel.MessagedetailsViewModel;
import com.karazam.huashanapp.my.message.messagedetails.viewmodel.MessagedetailsViewModelImpl;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxProperty;
import com.ogaclejapan.rx.binding.RxView;

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

    private View view;
    private PercentFrameLayout content_pl;

    private static int page = 1;

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
        mModel.type = getIntent().getStringExtra("type");
    }

    @Override
    public void initView() {

        title_text = (TextView) getView(R.id.title_text);

        swipe_rf = (SwipeRefreshLayout) getView(R.id.swipe_rf);
        swipe_rf.setOnRefreshListener(this);
        swipe_rf.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);

        rl_messagedetails = (RefreshRecyclerView) getView(R.id.rl_messagedetails);

        content_pl = (PercentFrameLayout) getView(R.id.content_pl);

        NoMessageView noMessageView = new NoMessageView(MessagedetailsActivity.this);
        view = noMessageView.setView();
    }

    @Override
    public void dealLogicAfterInitView() {


        setLayout();

        setMessageRecyclerView();

        Refresh();
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
    private MessagedetailsAdapter adapter;
    private RxProperty<MessagedetailsBean> messagedetailsRx = RxProperty.create();
    private ArrayList<DetailsBean> list = new ArrayList<>();
    private void setMessageRecyclerView() {

        WrapContentLinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false){
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 6000;
            }
        };
        rl_messagedetails.setLayoutManager(layoutManager);


//        ArrayList<DetailsBean> list = new ArrayList<>();
//        list.add(new DetailsBean());
//        list.add(new DetailsBean());
//        list.add(new DetailsBean());

        adapter = new MessagedetailsAdapter(MessagedetailsActivity.this,list);
        rl_messagedetails.setAdapter(adapter);

        RxView.of(rl_messagedetails).bind(messagedetailsRx, new Rx.Action<RefreshRecyclerView, MessagedetailsBean>() {
            @Override
            public void call(RefreshRecyclerView target, MessagedetailsBean messagedetailsBean) {

                content_pl.removeView(view);
                if(page == 1){
                    list = messagedetailsBean.getRows();
                    if(list == null || list.size() == 0){

                        content_pl.addView(view);
                    }


                } else {
                    list.addAll(messagedetailsBean.getRows());
                }

                adapter.setList(list);
                adapter.notifyDataSetChanged();

                page++;

            }
        });

        rl_messagedetails.setOnRefreshListener(new RefreshRecyclerView.OnRefreshListener() {
            @Override
            public void onRefreshUp() {
//                showToast("onRefresh up");
                addData();
            }
        });
    }

    @Override
    public void onRefresh() {
//        showToast("onRefresh Down");
        Refresh();
    }

    /**
     * 刷新
     */
    private void Refresh() {
        page = 1;
        mModel.getMessagedetails(page);
    }

    /**
     * 添加数据
     */
    private void addData(){

        if(page >mModel.allpage){
            showToast("到最后一页了！");
            return;
        }

        mModel.getMessagedetails(page);
    }

    /**
     * 获取消息详情成功
     * @param bean
     */
    @Override
    public void getMessagedetailsSuccess(MessagedetailsBean bean) {
        swipe_rf.setRefreshing(false);
        messagedetailsRx.set(bean);
    }

    /**
     * 获取消息详情失败
     * @param s
     */
    @Override
    public void getMessagedetailsFail(String s) {
        swipe_rf.setRefreshing(false);
    }

    /**
     * 获取消息详情错误
     * @param e
     */
    @Override
    public void getMessagedetailsError(Throwable e) {
        swipe_rf.setRefreshing(false);
    }
}
