package util.changhongit.com.cacheutils.Cache_RxBitmap;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.LruCache;

/**
 * Created by MuLuming on 2016/8/31.
 */
@SuppressLint("NewApi")
public class MemoryCache<Data> {

    private int mSize;
    private LruCache<String,Bitmap> mLruCache;



    public MemoryCache(int size) {

        this.mSize =size;
        this.mLruCache = new LruCache<String, Bitmap>(size){
            int size =0;
            @Override
            protected int sizeOf(String key, Bitmap value) {
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB_MR1)
                {

                    size=value.getByteCount();
                    return  size;
                }
                size= value.getRowBytes()*value.getHeight();

                return size;
            }
        };

    }

    /**
     * 保存数据
     * @param url
     * @param mbitmap
     */
    public void putData(String url, Bitmap mbitmap)
    {
        mLruCache.put(url,mbitmap);

    }

    /**
     * 获取数据
     * @param route
     * @return
     */
    public Bitmap getData(String route)
    {
       return mLruCache.get(route);


    }
}
