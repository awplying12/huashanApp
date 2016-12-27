package com.karazam.huashanapp.manage.main.view;

import com.example.utils.base.BaseView;
import com.karazam.huashanapp.main.Bean.HotProjects;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/11.
 */

public interface ManageView extends BaseView {

    void getManageDataSuccess(ArrayList<HotProjects> datas);

    void getManageDataFaile(String e);
}
