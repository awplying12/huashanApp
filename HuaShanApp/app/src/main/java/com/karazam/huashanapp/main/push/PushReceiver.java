package com.karazam.huashanapp.main.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.Log;

import com.example.utils.base.SystemUtils;
import com.karazam.huashanapp.home.view.activity.HomeActivity;
import com.karazam.huashanapp.my.message.main.view.activity.MessageActivity;

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


        if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {


//            //打开自定义的Activity
//            Intent i = new Intent(context, MessageActivity.class);
//            i.putExtras(bundle);
//            //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
//            context.startActivity(i);
           boolean flag = SystemUtils.isAppAlive(context,"com.karazam.huashanapp");
            Log.i("flag",flag+"");
            if(flag){

                Intent mainIntent = new Intent(context, HomeActivity.class);
                mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                //打开自定义的Activity
                Intent i = new Intent(context, MessageActivity.class);
                i.putExtras(bundle);

                Intent[] intents = {mainIntent, i};
                context.startActivities(intents);

            } else {
                Intent launchIntent = context.getPackageManager().
                        getLaunchIntentForPackage("com.karazam.huashanapp");
                launchIntent.setFlags(
                        Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);

                launchIntent.putExtra("HomeActivity", bundle);

                context.startActivity(launchIntent);
            }

        }
    }

}
