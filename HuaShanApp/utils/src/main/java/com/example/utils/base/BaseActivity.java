package com.example.utils.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.utils.custom.dialog.loadingDialog;

import java.util.ArrayList;

/**
 * Created by YC.Zhu on 2016/7/5.
 */
public abstract class BaseActivity extends FragmentActivity implements BaseView{


    public static int ScreeW; //屏幕的宽
    public static int ScreeH; //屏幕的高
    private loadingDialog progressDialog;

    public static ArrayList<BaseActivity> activities = new ArrayList<>();

    public static void finishAll(){
        for(int i = 0; i < activities.size(); i++){
            activities.get(i).finish();
        }
    }

//    public void addActvity(BaseActivity activity){
//        activities.add(activity);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Display m = this.getWindowManager().getDefaultDisplay();
        ScreeW = m.getWidth();
        ScreeH = m.getHeight();
        progressDialog = new loadingDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        setContentLayout();
        dealLogicBeforeInitView();
        initView();
        dealLogicAfterInitView();
    }

    /**
     * 设置ContentView
     */
    public abstract void setContentLayout();

    /**
     * 设置initView之前的操作
     */
    public abstract void dealLogicBeforeInitView();

    /**
     * 设置initView
     */
    public abstract void initView();

    /**
     * 设置initView之后的操作
     */
    public abstract void dealLogicAfterInitView();

    /**
     * BaseMainView接口方法实现
     */
    public View getView(int id) {
        return findViewById(id);
    }

    public View getView(int id,View view) {
        return view.findViewById(id);
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public Context getContext(){return this;}

    public void FinishActivity(Activity act){
        act.finish();
    }

    public void showProgressDialog() {
        progressDialog.setMessage("加载中...");
        progressDialog.show();
    }

    public void showProgressDialog(String str) {
        progressDialog.setMessage(str);
        progressDialog.show();
    }

    public void showProgressDialog(int resId) {
        progressDialog.setMessage(getString(resId));
        progressDialog.show();
    }

    public void dissmissProgressDialog() {
        progressDialog.dismiss();
    }

    /**
     *  跳转页面
     * @param activity
     * @param cls
     */
    @Override
    public void toOtherActivity(Activity activity,Class<?> cls){
        Intent intent = new Intent(activity,cls);
        activity.startActivity(intent);
    }

    /**
     * 退出键盘
     */
    @Override
    public void exitKeyboard(){
        InputMethodManager im = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(getCurrentFocus().getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    public void exitKeyboard(Activity activity) {
        InputMethodManager im = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(getCurrentFocus().getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

}
