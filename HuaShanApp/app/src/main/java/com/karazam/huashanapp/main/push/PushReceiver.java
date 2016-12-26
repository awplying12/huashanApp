package com.karazam.huashanapp.main.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2016/12/22.
 */

public class PushReceiver extends BroadcastReceiver {

    Bundle bundle;

    @Override
    public void onReceive(Context context, Intent intent) {
        bundle = intent.getExtras();

        Log.i("msg","EXTRA_TITLE    :     "+bundle.getString(JPushInterface.EXTRA_TITLE));
        Log.i("msg","EXTRA_MESSAGE    :     "+bundle.getString(JPushInterface.EXTRA_MESSAGE));
        Log.i("msg","EXTRA_EXTRA    :     "+bundle.getString(JPushInterface.EXTRA_EXTRA));
        Log.i("msg","EXTRA_CONTENT_TYPE    :     "+bundle.getString(JPushInterface.EXTRA_CONTENT_TYPE));

        Log.i("msg","EXTRA_ALERT    :     "+bundle.getString(JPushInterface.EXTRA_ALERT));
        Log.i("msg","EXTRA_NOTIFICATION_TITLE    :     "+bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE));
    }

}
