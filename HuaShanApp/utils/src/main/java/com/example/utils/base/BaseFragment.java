package com.example.utils.base;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;


/**
 * Created by Administrator on 2016/3/30.
 */
public class BaseFragment extends Fragment implements BaseView {





    public Context getContext(){
        return this.getActivity().getBaseContext();
    }

    @Override
    public void toOtherActivity(Activity activity, Class<?> cls) {
        Intent intent = new Intent(activity,cls);
        activity.startActivity(intent);
    }

    @Override
    public void exitKeyboard() {

    }

    @Override
    public void exitKeyboard(Activity activity) {
        InputMethodManager im = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(activity.getCurrentFocus().getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    public void FinishActivity(Activity act) {
        act.finish();
    }

    /**
     * BaseMainView接口方法实现
     */
    @Override
    public View getView(int id,View view) {
        return view. findViewById(id);
    }

    @Override
    public View getView(int id) {
        return null;
    }

    @Override
    public void showToast(String msg) {
        if(this.getActivity() == null){
            return;
        }
        Toast.makeText(this.getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}
