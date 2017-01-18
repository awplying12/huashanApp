package com.karazam.huashanapp.my.software;

import android.support.percent.PercentRelativeLayout;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.utils.base.BaseActivity;
import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.main.update.Updata;
import com.karazam.huashanapp.main.update.Version;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2016/11/29.
 */

public class SoftwareActivity extends BaseActivity implements View.OnClickListener{

    private PercentRelativeLayout title_back;

//    private ImageView set_sound;
//    private ImageView set_readsms;
    private ImageView set_push;
//    private ImageView set_event;
//    private ImageView set_remind;

    private TextView tv_updata;

    private Updata updata;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_software);
        activities.add(this);
    }

    @Override
    public void dealLogicBeforeInitView() {

        updata = new Updata(this,this);
    }

    @Override
    public void initView() {
        title_back = (PercentRelativeLayout) getView(R.id.title_back);
//        set_sound = (ImageView) getView(R.id.set_sound);
//        set_readsms = (ImageView) getView(R.id.set_readsms);
        set_push = (ImageView) getView(R.id.set_push);
        set_push.setSelected(HuaShanApplication.sharedPreferences.getBoolean("set_push",true));
//        set_event = (ImageView) getView(R.id.set_event);
//        set_remind = (ImageView) getView(R.id.set_remind);

        title_back.setOnClickListener(this);
//        set_sound.setOnClickListener(this);
//        set_readsms.setOnClickListener(this);
        set_push.setOnClickListener(this);
//        set_event.setOnClickListener(this);
//        set_remind.setOnClickListener(this);

        getView(R.id.pl_updata).setOnClickListener(this);

        tv_updata = (TextView) getView(R.id.tv_updata);


    }

    @Override
    public void dealLogicAfterInitView() {
        setLayout();
    }

    private void setLayout() {

        String verName = Version.getVerName(HuaShanApplication.getinstance());
        tv_updata.setText(Html.fromHtml("当前版本：<font color='#9e9e9e'>"+verName+"</font>"));

    }

//    /**
//     * 音效开启
//     */
//    private void setSound(){
//        setSwitchingEffect(set_sound);
//    }
//
//    /**
//     * 读取短息
//     */
//    private void setReadsms(){
//        setSwitchingEffect(set_readsms);
//    }

    /**
     * 推送消息
     */
    private void setPush(){
        setSwitchingEffect(set_push);
//        showToast(set_push.isSelected()+"");
        HuaShanApplication.editor.putBoolean("set_push",set_push.isSelected()).commit();
        if (set_push.isSelected()){
            JPushInterface.resumePush(HuaShanApplication.getinstance());
        } else {
            JPushInterface.stopPush(HuaShanApplication.getinstance());
        }
    }

//    /**
//     * 最新活动
//     */
//    private void setEvent(){
//        setSwitchingEffect(set_event);
//    }
//
//    /**
//     * 理财提醒
//     */
//    private void setRemind(){
//        setSwitchingEffect(set_remind);
//    }

    /**
     *开关效果
     */
    private void setSwitchingEffect(ImageView view){
        if(view.isSelected()){
            view.setSelected(false);
        }else {
            view.setSelected(true);
        }
    }

    /**
     * 版本升级
     */
    private void onUpdata() {
        updata.checkNewestVersion();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.title_back:
                finish();
                break;
//            case R.id.set_sound:
//                setSound();
//                break;
//            case R.id.set_readsms:
//                setReadsms();
//                break;
            case R.id.set_push:
                setPush();
                break;
//            case R.id.set_event:
//                setEvent();
//                break;
//            case R.id.set_remind:
//                setRemind();
//                break;
            case R.id.pl_updata:
                onUpdata();
                break;
            default:
                break;
        }
    }


}
