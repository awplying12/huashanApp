package com.karazam.huashanapp.manage.details.view;

import com.example.utils.base.BaseView;
import com.karazam.huashanapp.manage.details.model.databinding.ManagedetailsBean;

/**
 * Created by Administrator on 2016/11/8.
 */

public interface InvestmentdetailsView extends BaseView{

    void setTab(int num);

    void getManagedetailsDataSuccess(ManagedetailsBean data);

    void getManagedetailsDataFaile(String e);

    void getManagedetailsDataError(Throwable e);


}
