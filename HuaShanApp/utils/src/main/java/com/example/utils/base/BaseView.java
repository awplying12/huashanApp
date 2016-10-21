package com.example.utils.base;

import android.app.Activity;
import android.content.Context;
import android.view.View;

/**
 * Created by YC.Zhu on 2016/7/5.
 */
public interface BaseView {

    View getView(int id);
    View getView(int id, View view);
    void showToast(String msg);
    Context getContext();

    void toOtherActivity(Activity activity,Class<?> cls);

    void exitKeyboard();

    void exitKeyboard(Activity activity);

    void FinishActivity(Activity act);

}
