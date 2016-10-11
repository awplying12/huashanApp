package util.changhongit.com.cacheutils.Cache_RxText;

import android.content.Context;
import android.util.Log;

import io.realm.RealmObject;
import rx.Observable;
import rx.functions.Func1;
import util.changhongit.com.cacheutils.Cache_RxBitmap.Data;

/**
 * Created by Administrator on 2016/9/8.
 */

public class Sources {

    private ICache mMemoryCache, mDiskCache;
    private Context context;

    public Sources(Context mContext) {
        this.context = context;
        mMemoryCache = new MemoryCache();
        mDiskCache = new DiskCache(mContext);
    }

    public <T extends TextBean> Observable<T> memory(String type, Class<T> cls){
        return mMemoryCache.get(type,cls)

                .compose(logSource("load from memory:" + type));

    }

    public <T extends TextBean> Observable<T> disk(String type, Class<T> cls){
            return mDiskCache.get(type,cls)
                    .doOnNext(t ->{
                        if(t != null) {
                            mMemoryCache.put(type, t);
                        }
                    })
                    .compose(logSource("load from disk: " + type));
    }

    public <T extends TextBean> Observable<T> network(String type, Class<T> cls, NetworkCache<T> networkCache){
            return networkCache.get(type,cls)
                    .filter(t -> t!=null).doOnNext(t ->{
                mMemoryCache.put(type,t);
                mDiskCache.put(type,t);
            }) .compose(logSource("load from network: " + type));
    }

    <T > Observable.Transformer<T, T> logSource(final String source) {
        return dataObservable -> dataObservable.doOnNext(data -> {
            if (data != null) {
                Log.i("datasource",source + " has the data you are looking for!");
            } else {
                Log.i("datasource",source + " not has the data!");
            }
        });

    }
}
