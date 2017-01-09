package com.karazam.huashanapp.my.bankcard.main.view;

import com.example.utils.base.BaseView;
import com.karazam.huashanapp.my.bankcard.bindcard.model.databinding.BindcardBean;

/**
 * Created by Administrator on 2016/12/26.
 */

public interface BankcardView extends BaseView{

    void unBundlingSuccess(BindcardBean bean);

    void unBundlingFail(String s);

    void unBundlingError(Throwable e);
}
