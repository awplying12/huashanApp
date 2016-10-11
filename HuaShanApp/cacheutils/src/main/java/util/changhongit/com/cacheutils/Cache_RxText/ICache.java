package util.changhongit.com.cacheutils.Cache_RxText;

import io.realm.RealmObject;
import rx.Observable;

/**
 * Created by Administrator on 2016/9/7.
 */

public interface ICache {

    <T extends TextBean> Observable<T> get(String type, Class<T> cls);

    <T extends TextBean> void put(String type, T t);

}
