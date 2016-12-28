package com.karazam.huashanapp.manage.main.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.utils.base.BaseFragment;
import com.example.utils.custom.RefreshRecyclerView;
import com.example.utils.custom.WrapContentLinearLayoutManager;

import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.FragmentManageBinding;
import com.karazam.huashanapp.main.Bean.HotProjects;
import com.karazam.huashanapp.manage.main.model.databinding.ManageEntity;
import com.karazam.huashanapp.manage.main.model.databinding.Project;
import com.karazam.huashanapp.manage.main.view.ManageView;

import com.karazam.huashanapp.manage.main.view.view.ContentAdapter;
import com.karazam.huashanapp.main.adapter.TitleBarAdapter;
import com.karazam.huashanapp.manage.main.viewmodel.ManageViewModel;
import com.karazam.huashanapp.manage.main.viewmodel.ManageViewModelImpl;

import java.util.ArrayList;

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
    private RefreshRecyclerView content_rl;
    private ContentAdapter adapter;

    private String type = "guarantee";
    private int page = 1;

    private ImageView faile_img;

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


        Refresh();
        return view;
    }

    /**
     * 初始化View
     */
    private void initView() {

        titlebar_rl = (RecyclerView) getView(R.id.manage_titlebar_rl,view);
        LinearLayoutManager  lm = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        titlebar_rl.setLayoutManager(lm);

//        faile_img = (ImageView) getView(R.id.faile_img);

        mSwipeLayout = (SwipeRefreshLayout) getView(R.id.manage_sl,view);
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);

        content_rl = (RefreshRecyclerView) getView(R.id.content_rl,view);


    }

    /**
     * 设置标题栏
     */
    private void setTitlebBar() {
        ArrayList<String> list = new ArrayList<>();
        list.add("产融货");
        list.add("优企宝");
        list.add("保理贷");
//        list.add("债权转让");


        titlebarAdapter = new TitleBarAdapter(list,getContext(),17);
        titlebar_rl.setAdapter(titlebarAdapter);

        titlebarAdapter.setmOnItemClickListener(new TitleBarAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                switch (position){
                    case 0: //产融货
                        type = "guarantee";
                        break;
                    case 1: //优企宝
                        type = "mortgage";
                        break;
                    case 2: //保理贷
                        type = "credit";
                        break;
//                    case 3: //债权转让
//                        break;
                    default:
                        break;
                }

                Refresh();

            }
        });
    }

    public void setCurrentItem(int position){
        titlebarAdapter.setCurrentItem(position);
    }

    /**
     *  SwipeRefreshLayout配合RecyclerView
     *  实现下拉刷新和上拉加载更多以及没有数据的显示
     *
     */
    private void setRefreshRecyclerView() {
//        mSwipeLayout.addView();
        WrapContentLinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false){
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 6000;
            }
        };
        content_rl.setLayoutManager(layoutManager);


        ArrayList<HotProjects> list = new ArrayList<>();
//
//        list.add(new HotProjects());
//        list.add(new HotProjects());
//        list.add(new HotProjects());
//        list.add(new HotProjects());
//        list.add(new HotProjects());

        adapter = new ContentAdapter(getContext(),list);
        content_rl.setAdapter(adapter);

//        adapter.setmOnItemClickListener(new BidingAdapter.onItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                showToast("点击 Item"+position);
//            }
//        });

        content_rl.setOnRefreshListener(new RefreshRecyclerView.OnRefreshListener() {
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
     * 刷新数据
     */
    private void Refresh(){
        page = 1;
        mModel.getManageData(type,page);
    }

    /**
     * 添加数据
     */
    private void addData(){
        if(page >mModel.allpage){
            showToast("到最后一页了！");
            return;
        }

        mModel.getManageData(type,page);
    }


    /**
     * 获取数据成功
     */
    @Override
    public void getManageDataSuccess(ArrayList<HotProjects> datas) {

        mSwipeLayout.setRefreshing(false);



        if(content_rl.getVisibility() != View.VISIBLE){
            content_rl.setVisibility(View.VISIBLE);
        }


        if(page == 1){

            adapter.setmData(datas);
            adapter.notifyDataSetChanged();

        }else {

            ArrayList<HotProjects> data = adapter.getmData();
            data.addAll(datas);
            adapter.setmData(data);
            adapter.notifyDataSetChanged();

        }

//        if(page >= mModel.allpage){
//            return;
//        }
        page ++;

    }

    /**
     * 获取数据失败
     * @param e
     */
    @Override
    public void getManageDataFaile(String e) {
        mSwipeLayout.setRefreshing(false);
        if(content_rl.getVisibility() == View.VISIBLE ){
            content_rl.setVisibility(View.GONE);
        }
//        else {
//            showToast("获取数据失败");
//        }
    }

    /**
     * 获取数据故障
     * @param e
     */
    @Override
    public void getManageDataError(Throwable e) {
        mSwipeLayout.setRefreshing(false);
        if(content_rl.getVisibility() == View.VISIBLE ){
            content_rl.setVisibility(View.GONE);
        }
//        else {
//            showToast("获取数据失败");
//        }

    }

}
