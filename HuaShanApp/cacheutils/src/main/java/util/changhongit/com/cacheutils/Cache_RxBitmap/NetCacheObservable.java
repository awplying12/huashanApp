package util.changhongit.com.cacheutils.Cache_RxBitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/9/1.
 */

public class NetCacheObservable extends CacheObservable {

    @Override
    public Observable<Data> getObservable(String url) {
        Log.i("msg","1");
        return Observable.create(new Observable.OnSubscribe<Data>() {
            @Override
            public void call(Subscriber<? super Data> subscriber) {

                Log.i("msg","100");
                Data data ;
                Bitmap bitmap = null;
                InputStream inputStream = null;
                Log.i("msg","4");
                try {
                    final HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
                    Log.i("msg","2");
//                    conn.addRequestProperty("Content-Type","application/json");
//                    conn.addRequestProperty("Accept","application/hal+json");
//                    conn.addRequestProperty("X-Token", FamilyApplication.token);

                    inputStream = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(inputStream);
                    Log.i("msg","3");
                } catch (IOException e) {
                    Log.i("msg","5 e : "+e.toString());
                    e.printStackTrace();
                } finally {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                data = new Data(bitmap,url);

                Log.i("msg","2");

                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                }
            }

        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());


    }
}
