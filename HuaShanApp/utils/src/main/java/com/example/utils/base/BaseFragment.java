package com.example.utils.base;


import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;


/**
 * Created by Administrator on 2016/3/30.
 */
public class BaseFragment extends Fragment implements BaseView {





    public Context getContext(){
        return this.getActivity().getBaseContext();
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
        Toast.makeText(this.getActivity().getBaseContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
