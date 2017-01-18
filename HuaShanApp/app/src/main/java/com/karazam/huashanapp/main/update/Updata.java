package com.karazam.huashanapp.main.update;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.utils.base.BaseActivity;
import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.main.dialog.PromptDialog;
import com.karazam.huashanapp.main.dialog.PromptDialog2;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.main.update.AppInfo.AppInfoBean;
import com.karazam.huashanapp.main.update.AppInfo.AppInfoDataSource;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import android.os.Handler;

/**
 * Created by Administrator on 2017/1/18.
 */

public class Updata {

    private Context context;
    private BaseActivity activity;
    private ProgressDialog m_progressDlg;

    private String m_appNameStr;
    private Handler m_mainHandler;

    public Updata(Context context, BaseActivity activity) {
        this.context = context;
        this.activity = activity;

        m_mainHandler = new Handler();
        m_progressDlg =  new ProgressDialog(activity);
        m_progressDlg.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // 设置ProgressDialog 的进度条是否不明确 false 就是不设置为不明确
        m_progressDlg.setIndeterminate(false);
        m_progressDlg.setCanceledOnTouchOutside(false);

        m_appNameStr = "HuashanApp.apk";
    }

    public void checkNewestVersion(){
        final int vercode = Version.getVerCode(HuaShanApplication.getinstance());


        AppInfoDataSource dataSource = new AppInfoDataSource();
        dataSource.getAppInfo().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new Subscriber<BaseReturn<AppInfoBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(context,"网络故障！",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(BaseReturn<AppInfoBean> appInfoBeanBaseReturn) {
                if(appInfoBeanBaseReturn.isSuccess()){
                    AppInfoBean bean = appInfoBeanBaseReturn.getData();
                    int  version = Integer.parseInt(StringUtil.interrupt(bean.getVersion(),0,"1"));

                    m_newVerName = StringUtil.interrupt(bean.getVersionName(),0,"0.0");
                    m_newVerCode = StringUtil.interrupt(bean.getVersion(),0,"1");

                    if(vercode < version){
                        doNewVersionUpdate(bean.getAppUrl()); // 更新新版本
                    } else {
                        notNewVersionDlgShow(); // 提示当前为最新版本
                    }

                } else {
                    Toast.makeText(context,appInfoBeanBaseReturn.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 提示更新新版本
     */
    private String m_newVerName;
    private String m_newVerCode;
    private void doNewVersionUpdate(final String url) {
        int verCode = Version.getVerCode(HuaShanApplication.getinstance());
        String verName = Version.getVerName(HuaShanApplication.getinstance());

        String str= "当前版本："+verName+" Code:"+verCode+" ,发现新版本："+m_newVerName+
                " Code:"+m_newVerCode+" ,是否更新？";

       final PromptDialog dialog = new PromptDialog(context);
        dialog.setMod(PromptDialog.MOD1);

        dialog.setPrompt("版本更新",str);
        dialog.setClick("暂不更新", "更新", new PromptDialog.OnDialogListener() {
            @Override
            public void onleft(View view) {
                // 点击"取消"按钮之后退出程序
//                                finish();
                dialog.dismiss();
            }

            @Override
            public void onRight(View view) {

                m_progressDlg.setTitle("正在下载");
                m_progressDlg.setMessage("请稍候...");
                downFile(url);  //开始下载

                dialog.dismiss();
            }
        });
        dialog.show();
//        Dialog dialog = new AlertDialog.Builder(activity).setTitle("软件更新").setMessage(str)
//                // 设置内容
//                .setPositiveButton("更新",// 设置确定按钮
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog,
//                                                int which) {
//                                m_progressDlg.setTitle("正在下载");
//                                m_progressDlg.setMessage("请稍候...");
//                                downFile(url);  //开始下载
//                            }
//                        })
//                .setNegativeButton("暂不更新",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog,
//                                                int whichButton) {
//                                // 点击"取消"按钮之后退出程序
////                                finish();
//                                dialog.dismiss();
//                            }
//                        }).create();// 创建
//        // 显示对话框
//        dialog.show();
    }

    /**
     *  提示当前为最新版本
     */
    private void notNewVersionDlgShow()
    {
        int verCode = Version.getVerCode(HuaShanApplication.getinstance());
        String verName = Version.getVerName(HuaShanApplication.getinstance());

        String str="当前版本:"+verName+" Code:"+verCode+"" +"\n"+
                        "已是最新版,无需更新!";

        final PromptDialog2 dialog = new PromptDialog2(context);
        dialog.setMod(PromptDialog.MOD1);

        dialog.setPrompt("版本更新",str);
        dialog.setClick("确定", new PromptDialog2.OnDialogListener() {
            @Override
            public void onButton(View view) {
//               finish();
                dialog.dismiss();
            }
        });
        // 显示对话框
        dialog.show();

//        Dialog dialog = new AlertDialog.Builder(activity).setTitle("软件更新")
//                .setMessage(str)// 设置内容
//                .setPositiveButton("确定",// 设置确定按钮
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog,
//                                                int which) {
////                                finish();
//                                dialog.dismiss();
//                            }
//                        }).create();// 创建
//        // 显示对话框
//        dialog.show();
    }

    /**
     * 下载安装包
     * @param url
     */
    private void downFile(final String url) {

        m_progressDlg.show();
        new Thread() {
            public void run() {
                if(TextUtils.isEmpty(url)){
//                    Toast.makeText(activity,"1111",Toast.LENGTH_SHORT).show();
                    return;
                }
                HttpClient client = new DefaultHttpClient();
                HttpGet get = new HttpGet(url);
                HttpResponse response;
                try {
                    response = client.execute(get);
                    HttpEntity entity = response.getEntity();
                    long length = entity.getContentLength();

                    m_progressDlg.setMax((int)length);//设置进度条的最大值

                    InputStream is = entity.getContent();
                    FileOutputStream fileOutputStream = null;
                    if (is != null) {
                        File file = new File(
                                Environment.getExternalStorageDirectory(),
                                m_appNameStr);
                        fileOutputStream = new FileOutputStream(file);
                        byte[] buf = new byte[1024];
                        int ch = -1;
                        int count = 0;
                        while ((ch = is.read(buf)) != -1) {
                            fileOutputStream.write(buf, 0, ch);
                            count += ch;
                            if (length > 0) {
                                m_progressDlg.setProgress(count);
                            }
                        }
                    }
                    fileOutputStream.flush();
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    down();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 告诉HANDER已经下载完成了，可以安装了
     */
    private void down() {
        m_mainHandler.post(new Runnable() {
            public void run() {
                m_progressDlg.cancel();
                update();
            }
        });
    }

    /**
     * 安装程序
     */
    void update() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(Environment
                        .getExternalStorageDirectory(), m_appNameStr)),
                "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

}
