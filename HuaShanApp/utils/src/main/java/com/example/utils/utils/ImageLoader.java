package com.example.utils.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;




import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/4/18.
 */
@SuppressLint("NewApi")
public class ImageLoader {

    //线程池
    private ExecutorService mExecutorService;
    //用于缓存图片
    private LruCache<String,Bitmap> mLruCache;

    private StringBuffer namesb = new StringBuffer();

    private boolean online;

    public ImageLoader(){
        //最多三个线程执行，其他的排队等待
        mExecutorService = Executors.newFixedThreadPool(3);
        //设置缓存空间
        initLrcCache();

    }

    private boolean mIsBusy = false;
    public void setIsBusy(boolean IsBusy){
        this.mIsBusy = IsBusy;
    }

    private void initLrcCache() {
        //获取系统分配给每个应用程序的最大内存，每个应用系统分配64M
        int maxMemory = (int) Runtime.getRuntime().maxMemory();

        int mCacheSize = maxMemory / 8;
        mLruCache = new LruCache<String, Bitmap>(mCacheSize){
            int size = 0;

            @Override
            protected int sizeOf(String key, Bitmap value) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1){
                    size = value.getByteCount();
                    return size;
                }
                size =value.getRowBytes()*value.getHeight();
                return size;
            }
        };

    }

    /**
     * 在ImageView 上显示图片
     * 先在lrc缓存中拿图片，如果没有就向线程池中提交一个线程任务，从本地或网络获取
     * @param imgUrl
     * @param imageView
     */
    public void displayImg(String imgUrl,ImageView imageView,boolean online,String updataTime){
        imageView.setTag(imgUrl);

        setURL(imgUrl,updataTime);
        this.online = online;
        //先从lrc缓存中获取bitmap
        Bitmap bitmap = getBitmapFromCache(namesb.toString());
        if(bitmap != null){
            showbitmap(imageView,bitmap,imgUrl);
            Log.i("from",namesb.toString()+"  :  来自lru缓存");
            //来自lru缓存
            return;
        }
        //lru缓存没有图片
        if(!mIsBusy){
            LoadBitmapRunnable runnable = new LoadBitmapRunnable(imageView,imgUrl,updataTime);
            mExecutorService.submit(runnable); // 向线程池提交线程
        }
    }

    /**
     * 显示bitmap的线程
     */
    private class LoadBitmapRunnable implements Runnable{
        private ImageView imageView;
        private String imgUrl;
        private String updataTime;

        public LoadBitmapRunnable(ImageView view,String imgUrl,String updataTime){
            super();
            this.imageView = view;
            this.imgUrl = imgUrl;
            this.updataTime = updataTime;
        }

        @Override
        public void run() {
            //从本地获取Bitmap
            Bitmap bitmap = getBitmapFromLocal(namesb.toString());
            if(bitmap != null){
                showbitmap(imageView,bitmap,imgUrl);
                Log.i("from",namesb.toString()+"  :  来自本地");
                //来自本地
                return;
            }
            //本地没有图片
            bitmap = getBitmapFromNetwork(imgUrl,namesb.toString());
            if(bitmap != null){
                showbitmap(imageView,bitmap,imgUrl);
                Log.i("from",namesb.toString()+"  :  来自网络");
                //来自网络
            }
        }
    }

    /**
     * 从lrc缓存中获取bitmap
     * @param namesb
     * @return
     */
    private Bitmap getBitmapFromCache(String namesb){  //后改为time
        return mLruCache.get(namesb);
    }

    /**
     * 从本地获取bitmap
     * @param namesb
     * @return
     */
    private Bitmap getBitmapFromLocal(String namesb){ //后改为time
        Bitmap bitmap = null;
        //从本地获取bitmap
//        String[] strs = imgUrl.split("/");
        String localUrl = PathUtil.getPath()+ File.separator+namesb;
        if(BitmapUtil.isSDCardExist()){
           bitmap = BitmapUtil.getBitmapFromSDCard(localUrl);
        }

        if(bitmap != null){
            //添加到缓存中
            addBitmapToCache(bitmap,namesb);
        }
        return bitmap;
    }

    /**
     * 将bitmap放入lrc缓存中
     * @param bitmap
     * @param namesb
     */
    private void addBitmapToCache(Bitmap bitmap,String namesb){
        mLruCache.put(namesb,bitmap);
    }

    /**
     * 将bitmap放入本地文件
     * @param bitmap
     * @param namesb
     */
    private void addBitmapToLocal(Bitmap bitmap,String namesb){
//        String[] strs = imgUrl.split("/");
        String localUrl = PathUtil.getPath()+ File.separator+namesb;
        BitmapUtil.saveBitmap(localUrl,bitmap);
    }

    /**
     * 从网络拿图片
     * @param imgUrl
     * @return
     */
    private Bitmap getBitmapFromNetwork(String imgUrl,String namesb){
        Bitmap bitmap = null;
        try {
            bitmap = getImage(imgUrl);
            addBitmapToCache(bitmap,namesb);
            addBitmapToLocal(bitmap,namesb);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("form","e  :  "+e.toString());
        }
        return bitmap;
    }

    /**
     * 为ImageView显示Bitmap
     * @param view
     * @param bitmap
     * @param imgUrl
     */
    private void showbitmap(ImageView view,Bitmap bitmap,String imgUrl){
        //放错乱
        if(!imgUrl.equals(view.getTag().toString())){
            return;
        }
        DisplayImgRunnable runnable = new DisplayImgRunnable(view,bitmap);
        Activity aty = (Activity) view.getContext();
        aty.runOnUiThread(runnable);
    }

    private class DisplayImgRunnable implements Runnable{
        private ImageView imageView;
        private Bitmap bitmap;

        public DisplayImgRunnable(ImageView imageView,Bitmap bitmap){
            super();
            this.imageView = imageView;
            this.bitmap = bitmap;
        }
        @Override
        public void run() {
                    if(bitmap != null){
            bitmap = BitmapUtil.toRoundBitmap(bitmap);
            if(online){
                imageView.setImageBitmap(bitmap);
            }else {
               imageView.setImageBitmap(BitmapUtil.setGray(bitmap));
            }

        }
//        if(imageView != null){
//            imageView.setImageBitmap(bitmap);
//        }
//
        }
    }

    public static Bitmap getImage(String Url) throws Exception {

        try {

            URL url = new URL(Url);

            String responseCode = url.openConnection().getHeaderField(0);

//            if (responseCode.indexOf("200") < 0)
//                throw new Exception("图片文件不存在或路径错误，错误代码：" + responseCode);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setConnectTimeout(8 * 1000);
//            conn.setReadTimeout(8 * 1000);

//            conn.addRequestProperty("Content-Type","application/json");
//            conn.addRequestProperty("Accept","application/hal+json");


            InputStream in = conn.getInputStream();

            return BitmapFactory.decodeStream(in);

        } catch (IOException e) {

            // TODO Auto-generated catch block

            throw new Exception(e.getMessage());

        }

    }

    private void setURL(String imageUrl,String updataTime){
        String[] strs = imageUrl.split("/");
        namesb.append(strs[strs.length-1]).append("_").append(updataTime);
    }

    public Bitmap getBitmap(String imgUrl,String updataTime){

        setURL(imgUrl,updataTime);

        //从本地获取Bitmap
        Bitmap bitmap = getBitmapFromLocal(namesb.toString());
        if(bitmap != null){
            return bitmap;
        }

        //本地没有图片
        bitmap = getBitmapFromNetwork(imgUrl,namesb.toString());
        if(bitmap != null){
            return bitmap;
        }
        return null;
    }
}
