package util.changhongit.com.cacheutils.Cache_RxBitmap;


import rx.Observable;

/**
 * Created by MuLuming on 2016/8/31.
 */

public abstract class CacheObservable {

    public abstract Observable<Data> getObservable(String url);


}
