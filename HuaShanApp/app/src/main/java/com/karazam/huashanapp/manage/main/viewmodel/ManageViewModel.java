package com.karazam.huashanapp.manage.main.viewmodel;

import com.example.utils.base.BaseViewModel;

/**
 * Created by Administrator on 2016/10/11.
 */

public abstract class ManageViewModel extends BaseViewModel{

    public static int allpage = 1;

    public abstract void getManageData(String type,int page);

}
