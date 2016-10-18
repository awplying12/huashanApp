package com.karazam.huashanapp.manage.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.utils.base.BaseFragment;
import com.example.utils.custom.WrapContentLinearLayoutManager;

import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.FragmentManageBinding;
import com.karazam.huashanapp.manage.model.databinding.ManageEntity;
import com.karazam.huashanapp.manage.model.databinding.Project;
import com.karazam.huashanapp.manage.view.ManageView;

import com.karazam.huashanapp.manage.view.view.ContentAdapter;
import com.karazam.huashanapp.manage.view.view.TitleBarAdapter;
import com.karazam.huashanapp.manage.viewmodel.ManageViewModel;
import com.karazam.huashanapp.manage.viewmodel.ManageViewModelImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

/**
 * Created by Administrator on 2016/10/11.
 */

public class ManageFragment extends BaseFragment implements ManageView,SwipeRefreshLayout.OnRefreshListener{


    private View view;

    private FragmentManageBinding binding;
    private ManageEntity entity = new ManageEntity();
    private ManageViewModel mModel;

    private RecyclerView titlebar_rl;
    private TitleBarAdapter titlebarAdapter;


    private SwipeRefreshLayout mSwipeLayout;
    private RecyclerView content_rl;
    private ContentAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_manage,container,false);
        view = binding.getRoot();
        mModel = new ManageViewModelImpl(entity,this,getContext(),getActivity());
        binding.setHandler(mModel);
        binding.setEntity(entity);

        initView();
        setTitlebBar();
        setRefreshRecyclerView();

        return view;
    }



    /**
     * 初始化View
     */
    private void initView() {
        titlebar_rl = (RecyclerView) getView(R.id.manage_titlebar_rl,view);
        LinearLayoutManager  lm = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        titlebar_rl.setLayoutManager(lm);

        mSwipeLayout = (SwipeRefreshLayout) getView(R.id.manage_sl,view);
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);

        content_rl = (RecyclerView) getView(R.id.content_rl,view);


    }

    /**
     * 设置标题栏
     */
    private void setTitlebBar() {
        ArrayList<String> list = new ArrayList<>();
        list.add("信用标");
        list.add("担保标");
        list.add("债务标");
        list.add("转让专区");

        titlebarAdapter = new TitleBarAdapter(list,getContext());
        titlebar_rl.setAdapter(titlebarAdapter);

        titlebarAdapter.setmOnItemClickListener(new TitleBarAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                switch (position){
                    case 0: //信用标
                        break;
                    case 1: //担保标
                        break;
                    case 2: //债务标
                        break;
                    case 3: //转让专区
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     *  SwipeRefreshLayout配合RecyclerView
     *  实现下拉刷新和上拉加载更多以及没有数据的显示
     *
     */
    private int lastVisibleItem;
    private void setRefreshRecyclerView() {


        WrapContentLinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false){
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 6000;
            }
        };
        content_rl.setLayoutManager(layoutManager);

        ArrayList<Project> list = new ArrayList<>();
        list.add(new Project());
        list.add(new Project());
        list.add(new Project());
        list.add(new Project());
        list.add(new Project());
        list.add(new Project());
        list.add(new Project());
        list.add(new Project());
        list.add(new Project());
        list.add(new Project());
        list.add(new Project());
        list.add(new Project());
        list.add(new Project());
        list.add(new Project());
        adapter = new ContentAdapter(getContext(),list);
        content_rl.setAdapter(adapter);


        if(content_rl.getLayoutManager() instanceof LinearLayoutManager) {
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) content_rl.getLayoutManager();
             lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();


            content_rl.setOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);


                    if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapter.getItemCount()) {
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                List<String> newDatas = new ArrayList<String>();
//                                for (int i = 0; i < 5; i++) {
//                                    int index = i + 1;
//                                    newDatas.add("more item" + index);
//                                }
//                                adapter.addMoreItem(newDatas);
//                            }
//                        }, 1000);

                        showToast("onRefresh up");
                    }
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                }
            });

        }

    }


    @Override
    public void onRefresh() {
        showToast("onRefresh Down");
        mSwipeLayout.setRefreshing(false);
    }
}
