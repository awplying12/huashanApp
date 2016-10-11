package util.changhongit.com.cacheutils.Cache_RxBitmap;

import android.content.Context;
import android.widget.ImageView;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import rx.Observable;

/**
 * Created by Administrator on 2016/9/1.
 */

public class RxImageLoader {
    static Sources sources;
    public static void init(Context context){
        sources = new Sources(context);
    }
    private static final Map<Integer,String>  cachekeysmap
            = Collections.synchronizedMap(new HashMap<>());

    public static Observable<Data> getLoaderObservable(ImageView imageView,String url){
        if(imageView!=null){
            cachekeysmap.put(imageView.hashCode(),url);
        }
        Observable<Data> source = Observable.concat(sources.memory(url),
                sources.disk(url),sources.network(url)).first(data -> data!=null&&data.isAvailiable()
        &&url.equals(data.url));
        return source.doOnNext(data -> {
            if(imageView!=null&&url.equals(cachekeysmap.get(imageView.hashCode()))){
                imageView.setImageBitmap(data.bitmap);
            }
        });


    }
}
