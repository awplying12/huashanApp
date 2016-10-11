package util.changhongit.com.cacheutils.Cache_RxBitmap;

import android.content.Context;
import android.util.Log;

import rx.Observable;

/**
 * Created by Administrator on 2016/9/1.
 */

public class Sources {

    Context mContext;
    MemoryCacheObservable mMemoryCacheObservable;
    DiskCacheObservable mDiskCacheObservable;
    NetCacheObservable mNetCacheObservable;
    public Sources(Context mContext){
        this.mContext = mContext;
        mMemoryCacheObservable = new MemoryCacheObservable();
        mDiskCacheObservable = new DiskCacheObservable(mContext);
        mNetCacheObservable = new NetCacheObservable();

    }
    public Observable<Data> memory(String url)
    {
        Log.i("msg","memory");
        return mMemoryCacheObservable.getObservable(url)

                .compose(logSource("Memory"));


    }
    public Observable<Data> disk(String url){
        Log.i("msg","disk");
        return mDiskCacheObservable.getObservable(url)
                .filter(data -> data!=null)
                .doOnNext(data -> {
                    mMemoryCacheObservable.putData(data);
                })
                .compose(logSource("Disk"));
    }

    Data mData;
    public Observable<Data> network(String url){
        Log.i("msg","network");
        return mNetCacheObservable.getObservable(url).doOnNext(data -> {
            mMemoryCacheObservable.putData(data);
            mDiskCacheObservable.putData(data);
        }).compose(logSource("Network"));


    }

    Observable.Transformer<Data, Data> logSource(final String source) {
        return dataObservable -> dataObservable.doOnNext(data -> {
                if (data != null && data.bitmap != null) {
                    Log.i("datasource",source + " has the data you are looking for!");
                } else {
                    Log.i("datasource",source + " not has the data!");
                }
            });

    }
}
