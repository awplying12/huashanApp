package com.karazam.huashanapp.my.help.main.view.activity;

import android.databinding.DataBindingUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.utils.base.BaseActivity;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityHelpBinding;
import com.karazam.huashanapp.my.help.main.model.databinding.HelpEntity;
import com.karazam.huashanapp.my.help.main.view.HelpView;
import com.karazam.huashanapp.my.help.main.viewmodel.HelpViewModel;
import com.karazam.huashanapp.my.help.main.viewmodel.HelpViewModelImpl;

/**
 * Created by Administrator on 2016/11/30.
 */

public class HelpActivity extends BaseActivity implements HelpView{

    private ActivityHelpBinding binding;
    private HelpViewModel mModel;
    private HelpEntity entity = new HelpEntity();

    private WebView webView;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this,R.layout.activity_help);
        mModel = new HelpViewModelImpl(this,entity,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
    }

    @Override
    public void dealLogicBeforeInitView() {

    }

    @Override
    public void initView() {
        webView = (WebView) getView(R.id.web);
    }

    @Override
    public void dealLogicAfterInitView() {
        mModel.getUrl();
    }

    /**
     * 设置WebView
     * @param url
     */
    @Override
    public void setWeb(String url) {
        webView.loadUrl(url);
        WebSettings webSettings =   webView .getSettings();
        webSettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放
        webSettings.setLoadWithOverviewMode(true);
    }
}
