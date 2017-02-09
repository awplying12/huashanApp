package com.karazam.huashanapp.agreement.investmentagreement;

import android.support.percent.PercentRelativeLayout;
import android.view.View;
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
 * Created by Administrator on 2017/2/7.
 */

public class InvestmentagreementActivity extends BaseActivity implements View.OnClickListener{

    private PercentRelativeLayout title_back;

    private String url;
    private WebView webView;

    private String type;
    private String projectId;
    private String investmentId;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_investmentagreement);
        activities.add(this);
    }

    @Override
    public void dealLogicBeforeInitView() {

        type = getIntent().getStringExtra("type");

        if(type.equals("projectId")){ //投资详情
            projectId = getIntent().getStringExtra("projectId");
            url = Constants.URL1 +"/mobile/investment/agreement?"+"projectId="+projectId;
        } else if(type.equals("investmentId")){ // 我的理财
            investmentId = getIntent().getStringExtra("investmentId");
            url = Constants.URL1 +"/mobile/uc/investment/agreement?"+"investmentId="+investmentId;
        }


    }

    @Override
    public void initView() {
        title_back = (PercentRelativeLayout) getView(R.id.title_back);
        title_back.setOnClickListener(this);

        webView = (WebView) getView(R.id.web);
        WebSettings webSettings =   webView .getSettings();
        webSettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放
        webSettings.setLoadWithOverviewMode(true);
    }

    private Map<String,String> extraHeaders;
    @Override
    public void dealLogicAfterInitView() {

        extraHeaders = new HashMap<>();
        extraHeaders.put("sid", HuaShanApplication.token);
        extraHeaders.put("X-Requested-With","XMLHttpRequest");


        webView.loadUrl(url,extraHeaders);

        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);
//                return super.shouldOverrideUrlLoading(view, url);
                return true;

            }
        });
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
