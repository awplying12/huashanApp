package com.karazam.huashanapp.manage.details_fragment.view.fragment;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.recyclerviewpager.RecyclerViewPager.CarouselViewPager;
import com.example.utils.Adapter.PagerFragmentAdapter;
import com.example.utils.base.BaseFragment;
import com.example.utils.custom.RefreshRecyclerView;
import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.FragmentDetails2Binding;
import com.karazam.huashanapp.main.Bean.HotProjects;
import com.karazam.huashanapp.manage.details.model.databinding.ManagedetailsBean;
import com.karazam.huashanapp.manage.details.model.databinding.Project;
import com.karazam.huashanapp.manage.details.view.activity.InvestmentdetailsActivity;
import com.karazam.huashanapp.manage.details_fragment.model.databinding.DetailsFragment2Entity;
import com.karazam.huashanapp.manage.details_fragment.model.databinding.Opinions;
import com.karazam.huashanapp.manage.details_fragment.model.databinding.OpinionsItem;
import com.karazam.huashanapp.manage.details_fragment.model.databinding.RecordsItem;
import com.karazam.huashanapp.manage.details_fragment.view.DetailsFragment2View;
import com.karazam.huashanapp.manage.details_fragment.view.fragment.view.ConAdapter;
import com.karazam.huashanapp.manage.details_fragment.view.fragment.view.ImgAdapter;
import com.karazam.huashanapp.manage.details_fragment.viewmodel.DetailsFragmentViewModel_2.DetailsFragment2ViewModel;
import com.karazam.huashanapp.manage.details_fragment.viewmodel.DetailsFragmentViewModel_2.DetailsFragment2ViewModelImpl;

import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxProperty;
import com.ogaclejapan.rx.binding.RxView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/10.
 */

public class DetailsFragment2 extends BaseFragment implements DetailsFragment2View {

    private View view;

    private FragmentDetails2Binding binding;
    private DetailsFragment2ViewModel mModel;
    private DetailsFragment2Entity entity =new DetailsFragment2Entity();

    private LinearLayout content_ll;

    private View view1;
    private View view2;
    private View view3;

    private TextView information;
    private TextView record;
    private TextView speed;

    private String borrowingId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details_2,container,false);
        view = binding.getRoot();
        mModel = new DetailsFragment2ViewModelImpl(entity,this,getContext(),getActivity());
        binding.setHandler(mModel);
        binding.setEntity(entity);


        initView();

        setContent();
        return view;
    }



    /**
     * 初始化View
     */
    private void initView() {
        content_ll = (LinearLayout) getView(R.id.content_ll,view);

        view1 = LayoutInflater.from(getContext()).inflate(R.layout.view_details_1,null);
        view2 = LayoutInflater.from(getContext()).inflate(R.layout.view_details_2,null);
        view3 = LayoutInflater.from(getContext()).inflate(R.layout.view_details_3,null);

        information = (TextView) getView(R.id.bt_det_1,view);
        record = (TextView) getView(R.id.bt_det_2,view);
        speed = (TextView) getView(R.id.bt_det_3,view);


    }

    /**
     * 设置ViewPager
     */
    private void setContent() {

        onView1();
        onView2();
        onView3();

        content_ll.addView(view1);
    }

    @Override
    public void setCurrentItem(int position){
        content_ll.removeAllViews();
        initTab();
        switch (position){
            case 0:
                content_ll.addView(view1);
                information.setBackgroundColor(Color.parseColor("#00ffffff"));
                break;
            case 1:
                content_ll.addView(view2);
                record.setBackgroundColor(Color.parseColor("#00ffffff"));
                getManageRecords();
                break;
            case 2:
                content_ll.addView(view3);
                speed.setBackgroundColor(Color.parseColor("#00ffffff"));
                getManageOpinions();
                break;
            default:
                break;
        }
    }

    public void setBorrowingId(String borrowingId){
        this.borrowingId = borrowingId;
    }

    //购买记录
    /**
     * 购买记录获取数据成功
     */
    @Override
    public void getManageRecordsSuccess(ArrayList<RecordsItem> records) {

        recordRP.set(records);
        recordsPage ++;
    }

    /**
     * 购买记录获取数据失败
     */
    @Override
    public void getManageRecordsFaile(String e) {

    }

    /**
     * 购买记录获取数据故障
     */
    @Override
    public void getManageRecordsError(Throwable e) {

    }

    //项目进度
    /**
     * 项目进度获取数据成功
     */
    @Override
    public void getManageOpinionsSuccess(Opinions opinions) {
        Log.i("opinions",opinions.toString());
        opinionsRX.set(opinions);
    }

    /**
     * 项目进度获取数据失败
     */
    @Override
    public void getManageOpinionsFaile(String e) {

    }

    /**
     * 项目进度获取数据故障
     */
    @Override
    public void getManageOpinionsError(Throwable e) {

    }

    /**
     * 初始化tab
     */
    private void initTab(){
        information.setBackgroundColor(Color.parseColor("#f0f0f0"));
        record.setBackgroundColor(Color.parseColor("#f0f0f0"));
        speed.setBackgroundColor(Color.parseColor("#f0f0f0"));
    }

    private ImgAdapter adapter;
    private void onView1(){
        CarouselViewPager recyclerView = (CarouselViewPager) view1.findViewById(R.id.img_rl);
        LinearLayoutManager lm = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(lm);

        ArrayList<String> list = new ArrayList<>();
//        list.add("");
//        list.add("");
//        list.add("");


        adapter = new ImgAdapter(list,getContext());
        recyclerView.setAdapter(adapter);

        InvestmentdetailsActivity activity = (InvestmentdetailsActivity) getActivity();
//        RxView.of(recyclerView).bind(activity.project, new Rx.Action<CarouselViewPager, ManagedetailsBean>() {
//            @Override
//            public void call(CarouselViewPager target, ManagedetailsBean managedetailsBean) {
//
//            }
//        });

        RxView.of(view1).bind(activity.project, new Rx.Action<View, ManagedetailsBean>() {
            @Override
            public void call(View target, ManagedetailsBean managedetailsBean) {

                Project project = managedetailsBean.getProject();

                TextView tv_description = (TextView) target.findViewById(R.id.tv_description);
                String description = project.getDescription();  //项目描述
                tv_description.setText(StringUtil.interrupt(description,0,"无"));

                TextView tv_purpose = (TextView) target.findViewById(R.id.tv_purpose);
                String purpose = project.getPurpose();
                tv_purpose.setText(StringUtil.interrupt(purpose,0,"无"));

                TextView tv_repaymentInquiry = (TextView) target.findViewById(R.id.tv_repaymentInquiry);
                String repaymentInquiry = project.getRepaymentInquiry();
                tv_repaymentInquiry.setText(StringUtil.interrupt(repaymentInquiry,0,"无"));

                ArrayList<String> materials = project.getMaterials();
                adapter.setList(materials);
                adapter.notifyDataSetChanged();

            }
        });

    }

    private RxProperty<ArrayList<RecordsItem>> recordRP = RxProperty.create();
    private void onView2(){
        RefreshRecyclerView recyclerView = (RefreshRecyclerView) view2.findViewById(R.id.con_rl);

       final LinearLayoutManager lm = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(lm);
        ArrayList<RecordsItem> list = new ArrayList<>();

        final ConAdapter adapter = new ConAdapter(list,getContext());
        recyclerView.setAdapter(adapter);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_header_item,null);
        adapter.setHeaderView(view);

        recyclerView.setOnRefreshListener(new RefreshRecyclerView.OnRefreshListener() {
            @Override
            public void onRefreshUp() {
                addManageRecords();
            }
        });

        RxView.of(recyclerView).bind(recordRP, new Rx.Action<RefreshRecyclerView, ArrayList<RecordsItem>>() {
            @Override
            public void call(RefreshRecyclerView target, ArrayList<RecordsItem> recordsItems) {

                if(recordsPage == 1){

                    adapter.setList(recordsItems);
                    adapter.notifyDataSetChanged();

                }else {

                    ArrayList<RecordsItem> data = adapter.getList();
                    data.addAll(recordsItems);
                    adapter.setList(data);
                    adapter.notifyDataSetChanged();

                }

            }
        });


    }

    private RxProperty<Opinions> opinionsRX = RxProperty.create();
    private void onView3(){

        RxView.of(view3).bind(opinionsRX, new Rx.Action<View, Opinions>() {
            @Override
            public void call(View target, Opinions opinions) {

                OpinionsItem inquiry = opinions.getInquiry();
                    ImageView img1 = (ImageView) target.findViewById(R.id.img_1);
                    TextView tvImg1 = (TextView) target.findViewById(R.id.tv_img_1);
                    TextView tv1 = (TextView) target.findViewById(R.id.tv_1);

                    setImg(img1,inquiry.getFlag(),0);
                    tvImg1.setText(StringUtil.interrupt(inquiry.getT1(),2,"未知"));
                    setTv(tv1,inquiry);

                OpinionsItem approval = opinions.getApproval();
                    ImageView img2 = (ImageView) target.findViewById(R.id.img_2);
                    TextView tvImg2 = (TextView) target.findViewById(R.id.tv_img_2);
                    TextView tv2 = (TextView) target.findViewById(R.id.tv_2);

                    setImg(img2,approval.getFlag(),0);
                    tvImg2.setText(StringUtil.interrupt(approval.getT1(),2,"未知"));
                    setTv(tv2,approval);


                OpinionsItem buying = opinions.getBuying();
                    ImageView img3 = (ImageView) target.findViewById(R.id.img_3);
                    TextView tvImg3 = (TextView) target.findViewById(R.id.tv_img_3);
                    TextView tv3 = (TextView) target.findViewById(R.id.tv_3);

                    setImg(img3,buying.getFlag(),0);
                    tvImg3.setText(StringUtil.interrupt(buying.getT1(),2,"未知"));
                    setTv(tv3,buying);

                OpinionsItem lend = opinions.getLend();
                    ImageView img4 = (ImageView) target.findViewById(R.id.img_4);
                    TextView tvImg4 = (TextView) target.findViewById(R.id.tv_img_4);
                    TextView tv4 = (TextView) target.findViewById(R.id.tv_4);

                    setImg(img4,lend.getFlag(),0);
                    tvImg4.setText(StringUtil.interrupt(lend.getT1(),2,"未知"));
                    setTv(tv4,lend);


                OpinionsItem repay = opinions.getRepay();
                    ImageView img5 = (ImageView) target.findViewById(R.id.img_5);
                    TextView tvImg5= (TextView) target.findViewById(R.id.tv_img_5);
                    TextView tv5 = (TextView) target.findViewById(R.id.tv_5);

                    setImg(img5,repay.getFlag(),1);
                    tvImg5.setText(StringUtil.interrupt(repay.getT1(),2,"未知"));
                    setTv(tv5,repay);
            }
        });
    }

    private void setImg(ImageView img, int flag,int position){

        int imgId1 = 0;
        int imgId2 = 0;

        int imgId3 = R.drawable.lo;

        switch (position){
            case 0:
                imgId1 = R.drawable.oo;
                imgId2 = R.drawable.ol;
                break;
            case 1:
                imgId1 = R.drawable.l;
                imgId2 = R.drawable.o;
                break;
            default:
                imgId1 = R.drawable.oo;
                imgId2 = R.drawable.ol;
                break;
        }

        switch (flag){
            case 1:
                img.setBackgroundResource(imgId2);
                break;
            case 2:
                img.setBackgroundResource(imgId3);
                break;
            case 3:
                img.setBackgroundResource(imgId1);
                break;
            default:
                img.setBackgroundResource(imgId2);
                break;

        }
    }

    public void setTv(TextView tv,OpinionsItem item){

        tv.setText(StringUtil.interrupt(item.getT2(),0,"")+"   "+StringUtil.interrupt(item.getT3(),0,""));

        if(item.getFlag() == 2){
            tv.setTextColor(Color.parseColor("#ff8000"));
        }
    }

    private int recordsPage = 1;
        public void getManageRecords() {
        recordsPage = 1;
        mModel.getManageRecords(borrowingId,recordsPage);
    }

    public void addManageRecords() {
        if(recordsPage >mModel.allpage){
            showToast("到最后一页了！");
            return;
        }
        mModel.getManageRecords(borrowingId,recordsPage);
    }

    public void getManageOpinions(){
        mModel.getManageOpinions(borrowingId);
    }

}
