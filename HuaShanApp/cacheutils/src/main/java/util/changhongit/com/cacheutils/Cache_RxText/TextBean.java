package util.changhongit.com.cacheutils.Cache_RxText;

import com.google.gson.Gson;

import io.realm.RealmObject;

/**
 * Created by Administrator on 2016/9/8.
 */

public abstract class TextBean{

    public String toJsonString() {

        return new Gson().toJson(this);
    }

}
