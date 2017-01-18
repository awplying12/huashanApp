package com.karazam.huashanapp.my.about.main.view.activity;

import android.databinding.DataBindingUtil;
import android.text.Html;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.utils.base.BaseActivity;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityAboutBinding;
import com.karazam.huashanapp.main.update.Updata;
import com.karazam.huashanapp.my.about.main.model.databinding.AboutEntity;
import com.karazam.huashanapp.my.about.main.view.AboutView;
import com.karazam.huashanapp.my.about.main.viewmodel.AboutViewModel.AboutViewModel;
import com.karazam.huashanapp.my.about.main.viewmodel.AboutViewModel.AboutViewModelImpl;

import rx.Subscriber;
import util.changhongit.com.cacheutils.Cache_RxBitmap.Data;
import util.changhongit.com.cacheutils.Cache_RxBitmap.RxImageLoader;

/**
 * Created by Administrator on 2016/11/29.
 */

public class AboutActivity extends BaseActivity implements AboutView {

    private ActivityAboutBinding binding;
    private AboutViewModel mModel;
    private AboutEntity entity = new AboutEntity();

    private TextView hint_text;



    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_about);
        mModel = new AboutViewModelImpl(this,entity,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
        activities.add(this);
    }

    @Override
    public void dealLogicBeforeInitView() {
        mModel.updata = new Updata(this,this);
    }

    @Override
    public void initView() {
        hint_text = (TextView) getView(R.id.hint_text);
    }

    @Override
    public void dealLogicAfterInitView() {

        hint_text.setText(Html.fromHtml("金融客服（9:00-21:00）<font color='#0894EC'>0971-8011979</font>"));

    }
}
