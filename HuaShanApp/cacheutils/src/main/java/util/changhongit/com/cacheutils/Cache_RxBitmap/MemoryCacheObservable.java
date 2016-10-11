package util.changhongit.com.cacheutils.Cache_RxBitmap;


import android.text.TextUtils;
import android.util.Log;

import rx.Observable;

/**
 * Created by MuLuming on 2016/8/31.
 */

public class MemoryCacheObservable extends CacheObservable{

    public static final int DEFAULT_CACHE_SIZE=(24*1024*1024);
    MemoryCache mCache = new MemoryCache(DEFAULT_CACHE_SIZE);
    @Override
    public Observable<Data> getObservable(String url) {
        return Observable.create(subscriber -> {
            if (!subscriber.isUnsubscribed()) {
                subscriber.onNext(new Data(mCache.getData(url), url));
                subscriber.onCompleted();
            }
        });
    }

    public void putData(Data data)
    {
        if (TextUtils.isEmpty(data.url)||data.bitmap==null)
        {
            Log.i("url","null");
            return;
        }
        mCache.putData(data.url,data.bitmap);


    }


    }







