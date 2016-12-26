package com.karazam.huashanapp.my.recommend;

import android.support.percent.PercentRelativeLayout;
import android.view.View;

import com.example.utils.base.BaseActivity;
import com.karazam.huashanapp.R;

/**
 * Created by Administrator on 2016/11/29.
 */

public class RecommendActivity extends BaseActivity implements View.OnClickListener{

    private PercentRelativeLayout title_back;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_recommend);
        activities.add(this);
    }

    @Override
    public void dealLogicBeforeInitView() {

    }

    @Override
    public void initView() {
        title_back = (PercentRelativeLayout) getView(R.id.title_back);
        title_back.setOnClickListener(this);
    }

    @Override
    public void dealLogicAfterInitView() {

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
