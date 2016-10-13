package com.karazam.huashanapp.main;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.karazam.huashanapp.R;

import huashanapp.karazam.com.gesture_lock.GestureEditActivity;
import huashanapp.karazam.com.gesture_lock.GestureUtil;
import huashanapp.karazam.com.gesture_lock.GestureVerifyActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnSetLock;
    private Button mBtnVerifyLock;

    private String Password = "";
    private TextView mPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpView();
        setUpListener();
    }

    private void setUpView() {
        mBtnSetLock = (Button) findViewById(R.id.btn_set_lockpattern);
        mBtnVerifyLock = (Button) findViewById(R.id.btn_verify_lockpattern);

        mPassword = (TextView) findViewById(R.id.password);
    }

    private void setUpListener() {
        mBtnSetLock.setOnClickListener(this);
        mBtnVerifyLock.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_set_lockpattern:
                startSetLockPattern();
                break;
            case R.id.btn_verify_lockpattern:
                startVerifyLockPattern();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == GestureUtil.GESTURELOCK_REQUESTCODE){
            switch (resultCode){
                case GestureUtil.GESTURELOCK_EDIT_RESULTCODE: //创建手势密码返回值
                    Password = data.getStringExtra(GestureUtil.Password);
                    mPassword.setText(Password);
                    Toast.makeText(MainActivity.this,data.getStringExtra(GestureUtil.Password),Toast.LENGTH_SHORT).show();

                    break;
                case GestureUtil.GESTURELOCK_VERIFY_RESULTCODE: //校检手势密码返回值

                    Toast.makeText(MainActivity.this,"qweer",Toast.LENGTH_SHORT).show();

                    break;
                default:
                    break;
            }
        }
    }

    private void startSetLockPattern() {
        Intent intent = new Intent(MainActivity.this, GestureEditActivity.class);
        startActivityForResult(intent, GestureUtil.GESTURELOCK_REQUESTCODE);
    }

    private void startVerifyLockPattern() {
        Intent intent = new Intent(MainActivity.this, GestureVerifyActivity.class);
        intent.putExtra(GestureUtil.Password,Password);
        startActivityForResult(intent, GestureUtil.GESTURELOCK_REQUESTCODE);
    }
}
