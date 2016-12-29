package com.karazam.huashanapp.manage.details_fragment.viewmodel.DetailsFragmentViewModel_2;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;

import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.manage.details_fragment.model.databinding.DetailsFragment2Entity;
import com.karazam.huashanapp.manage.details_fragment.model.databinding.ManageOpinionsbean;
import com.karazam.huashanapp.manage.details_fragment.model.databinding.ManageRecordsBean;
import com.karazam.huashanapp.manage.details_fragment.model.databinding.RecordsItem;
import com.karazam.huashanapp.manage.details_fragment.model.retrofit.ManageOpinionsDataSource;
import com.karazam.huashanapp.manage.details_fragment.model.retrofit.ManageRecordsDataSource;
import com.karazam.huashanapp.manage.details_fragment.view.DetailsFragment2View;
import com.karazam.huashanapp.manage.main.model.databinding.Project;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/10.
 */

public class DetailsFragment2ViewModelImpl extends DetailsFragment2ViewModel {

    private DetailsFragment2Entity mEntity;
    private DetailsFragment2View mView;
    private Context context;
    private Activity activity;

    private ManageRecordsDataSource recordsdataSource;
    private ManageOpinionsDataSource opinionsDataSource;


    public DetailsFragment2ViewModelImpl(DetailsFragment2Entity mEntity, DetailsFragment2View mView, Context context, Activity activity) {
        this.mEntity = mEntity;
        this.mView = mView;
        this.context = context;
        this.activity = activity;

        recordsdataSource = new ManageRecordsDataSource();
        opinionsDataSource = new ManageOpinionsDataSource();

    }

    @Override
    public void onBack(View view) {
        mView.FinishActivity(activity);
    }

    /**
     * 项目信息
     * @param view
     */
    @Override
    public void onInformation(View view) {
        mView.showToast("项目信息");
        mView.setCurrentItem(0);
    }

    /**
     * 购买记录
     * @param view
     */
    @Override
    public void onRecord(View view) {
        mView.showToast("购买记录");
        mView.setCurrentItem(1);
    }

    /**
     * 项目进度
     * @param view
     */
    @Override
    public void onSpeed(View view) {
        mView.showToast("项目进度");
        mView.setCurrentItem(2);
    }

    /**
     * 购买记录
     * @param projectId
     */
    @Override
    public void getManageRecords(String projectId,int page) {

        recordsdataSource.getDetails(projectId,page+"")
                .throttleFirst(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<BaseReturn<ManageRecordsBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("getManageRecords","  e   :  "+e.toString());
                        mView.getManageRecordsError(e);
                    }

                    @Override
                    public void onNext(BaseReturn<ManageRecordsBean> manageRecordsBeanBaseReturn) {

                        if(manageRecordsBeanBaseReturn.isSuccess()){
                            ManageRecordsBean bean = manageRecordsBeanBaseReturn.getData();
                            ArrayList<RecordsItem> records = bean.getRecords();
                            allpage = bean.getPages();
                            mView.getManageRecordsSuccess(records);
                        }else {
                            mView.getManageRecordsFaile(manageRecordsBeanBaseReturn.getMessage());
                        }
                    }
                });
    }

    /**
     * 项目进度
     * @param projectId
     */
    @Override
    public void getManageOpinions(String projectId) {

        opinionsDataSource.getManageOpinions(projectId)
                .throttleFirst(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<BaseReturn<ManageOpinionsbean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("getManageOpinions","  e   :  "+e.toString());
                        mView.getManageOpinionsError(e);
                    }

                    @Override
                    public void onNext(BaseReturn<ManageOpinionsbean> manageOpinionsbeanBaseReturn) {

                        if(manageOpinionsbeanBaseReturn.isSuccess()){
                            ManageOpinionsbean bean = manageOpinionsbeanBaseReturn.getData();
                            mView.getManageOpinionsSuccess(bean.getOpinions());
                        }else {
                            mView.getManageOpinionsFaile(manageOpinionsbeanBaseReturn.getMessage());
                        }
                    }
                });
    }


}
