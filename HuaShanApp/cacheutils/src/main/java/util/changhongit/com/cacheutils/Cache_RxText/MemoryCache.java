package util.changhongit.com.cacheutils.Cache_RxText;

import android.text.TextUtils;
import android.util.LruCache;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

import io.realm.RealmObject;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/9/7.
 */

public class MemoryCache implements ICache {

    LruCache<String,String> mCache;

    public MemoryCache() {
        //获取系统分配给每个应用程序的最大内存，每个应用系统分配32M
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int mCacheSize = maxMemory / 8;
        //给LruCache分配1/8 4M
        mCache = new LruCache<String,String>(mCacheSize){
            @Override
            protected int sizeOf(String key, String value) {
                try {
                    return value.getBytes("UTF-8").length;
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    return value.getBytes().length;
                }
            }
        };
    }

    @Override
    public <T extends TextBean> Observable<T> get(String type, Class<T> cls) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                StringBuffer name =new StringBuffer();
                name.append("Type").append("_").append(type);

                String result = mCache.get(name.toString());
                if(subscriber.isUnsubscribed() || TextUtils.isEmpty(result)){
                    subscriber.onNext(null);
                }else {
                    T t = new Gson().fromJson(result,cls);
                    subscriber.onNext(t);
                }
                subscriber.onCompleted();
            }
        });
    }

    @Override
    public <T extends TextBean> void put(String type, T t) {
        if (null != t) {
            StringBuffer name =new StringBuffer();
            name.append("Type").append("_").append(type);
            mCache.put(name.toString(), t.toJsonString());
        }
    }
}
