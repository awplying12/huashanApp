package util.changhongit.com.cacheutils.Cache_RxText;

import android.content.Context;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import rx.Observable;

/**
 * Created by Administrator on 2016/9/8.
 */

public class RxCacheManager {
    static Sources sources;
    public static void init(Context context){
        sources = new Sources(context);
    }

    private static final Map<Integer,String> cachekeysmap
            = Collections.synchronizedMap(new HashMap<>());

    public static<T extends TextBean> Observable<T> getLoaderObservable(String key, Class<T> cls, NetworkCache<T> networkCache) {

        Observable<T> source = Observable.concat(sources.memory(key, cls),
                sources.disk(key, cls), sources.network(key, cls, networkCache))
                .first(data -> data != null);
        return source;
    }


}
