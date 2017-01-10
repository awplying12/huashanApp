package com.karazam.huashanapp.manage.purchase.view;

import com.example.utils.base.BaseView;

/**
 * Created by Administrator on 2016/11/15.
 */

public interface PurchaseView extends BaseView{
    void showPasswordView();

    void addSMSView();


    void purchaseSuccess(String detailsId);

    void purchaseFail(String s);

    void purchaseError(Throwable e);
}
