package com.karazam.huashanapp.my.recommend;

import android.content.Context;
import android.support.percent.PercentRelativeLayout;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.utils.base.BaseActivity;
import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.main.retorfitMain.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/29.
 */

public class RecommendActivity extends BaseActivity implements View.OnClickListener{

    private PercentRelativeLayout title_back;

    private String url;
    private WebView webView;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_recommend);
        activities.add(this);
    }

    @Override
    public void dealLogicBeforeInitView() {
        url = Constants.URL1 +"/mobile/uc/toActivityPage";
    }

    @Override
    public void initView() {
        title_back = (PercentRelativeLayout) getView(R.id.title_back);
        title_back.setOnClickListener(this);

        webView = (WebView) getView(R.id.web);
        WebSettings webSettings =   webView .getSettings();
        webSettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放
        webSettings.setLoadWithOverviewMode(true);

        webView.getSettings().setBuiltInZoomControls(false);
        webView.getSettings().setJavaScriptEnabled(false);
    }

    private Map<String,String> extraHeaders;
    @Override
    public void dealLogicAfterInitView() {

        extraHeaders = new HashMap<>();
        extraHeaders.put("sid", HuaShanApplication.token);
        extraHeaders.put("X-Requested-With","XMLHttpRequest");

        synCookies(this,url);

        webView.loadUrl(url,extraHeaders);

        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);
//                return super.shouldOverrideUrlLoading(view, url);
                return true;

            }
        });
//        webView.loadUrl(s);
    }


    /**
     * 同步一下cookie
     */
    public static void synCookies(Context context, String url) {
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeSessionCookie();//移除
//        cookieManager.setCookie(url, extraHeaders);//指定要修改的cookies

        CookieSyncManager.getInstance().sync();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.title_back:
                finish();
                break;
        }
    }
}
