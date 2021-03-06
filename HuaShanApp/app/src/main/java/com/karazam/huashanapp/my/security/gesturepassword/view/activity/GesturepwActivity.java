package com.karazam.huashanapp.my.security.gesturepassword.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.widget.Toast;

import com.example.utils.base.BaseActivity;
import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityGesturepwBinding;
import com.karazam.huashanapp.main.MainActivity;
import com.karazam.huashanapp.my.security.gesturepassword.model.databinding.GespwReturn;
import com.karazam.huashanapp.my.security.gesturepassword.model.databinding.GesturepwEntity;
import com.karazam.huashanapp.my.security.gesturepassword.view.GesturepwView;
import com.karazam.huashanapp.my.security.gesturepassword.viewmodel.GesturepwViewModel.GesturepwViewModel;
import com.karazam.huashanapp.my.security.gesturepassword.viewmodel.GesturepwViewModel.GesturepwViewModelImpl;

import huashanapp.karazam.com.gesture_lock.GestureEditActivity;
import huashanapp.karazam.com.gesture_lock.GestureUtil;

import static com.karazam.huashanapp.HuaShanApplication.securitysGesture;

/**
 * Created by Administrator on 2016/11/29.
 */

public class GesturepwActivity extends BaseActivity implements GesturepwView{

    private ActivityGesturepwBinding binding;
    private GesturepwViewModel mModel;
    private GesturepwEntity entity = new GesturepwEntity();

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_gesturepw);
        mModel = new GesturepwViewModelImpl(this,entity,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
        activities.add(this);
    }

    @Override
    public void dealLogicBeforeInitView() {
//        securitysGesture.add(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void dealLogicAfterInitView() {

    }


    @Override
    public void toGestureEdit() {
        Intent intent = new Intent(GesturepwActivity.this, GestureEditActivity.class);
        intent.putExtra("uuid",HuaShanApplication.uuid);
        intent.putExtra("account",HuaShanApplication.account);
        intent.putExtra("token",HuaShanApplication.token);
        startActivityForResult(intent, GestureUtil.GESTURELOCK_REQUESTCODE);
    }

    /**
     * 同步成功
     */
    @Override
    public void setGesPasswordSuccess(GespwReturn gespwReturn) {
//        showToast("同步成功");
        String str = gespwReturn.getGesPassword();
        HuaShanApplication.editor.putString("gesture_lock", StringUtil.interrupt(str,0,"-1")).commit();
        HuaShanApplication.editor.putBoolean("isGesture_lock",true).commit();
        finish();
    }

    /**
     * 同步失败
     */
    @Override
    public void setGesPasswordFaile(Throwable e) {
        showToast("网络故障！");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == GestureUtil.GESTURELOCK_REQUESTCODE){
            switch (resultCode){
                case GestureUtil.GESTURELOCK_EDIT_RESULTCODE: //创建手势密码返回值

                    String str = data.getStringExtra(GestureUtil.Password);

//                    HuaShanApplication.editor.putString("gesture_lock", StringUtil.interrupt(str,0,"-1")).commit();
//                    mModel.setGesPassword(StringUtil.interrupt(str,0,"-1"));

                    break;
                case GestureUtil.GESTURELOCK_VERIFY_RESULTCODE: //校检手势密码返回值

//                    Toast.makeText(GesturepwActivity.this,"qweer",Toast.LENGTH_SHORT).show();

                    break;
                default:
                    break;
            }
        }
    }
}
