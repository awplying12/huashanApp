package com.example.administrator.rxbingdingdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxProperty;
import com.ogaclejapan.rx.binding.RxView;

public class MainActivity extends AppCompatActivity {


    private TextView textView;

    //属性对象，封装了要观察的数据
    private RxProperty<String> spContent = RxProperty.create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.texts);

        RxView.of(textView).bind(spContent, new Rx.Action<TextView, String>() {
            @Override
            public void call(TextView target, String s) {
                target.setText(s);
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spContent.set("wwww");
            }
        });

    }


}
