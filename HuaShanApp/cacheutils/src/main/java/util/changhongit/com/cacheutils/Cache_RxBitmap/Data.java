package util.changhongit.com.cacheutils.Cache_RxBitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by MuLuming on 2016/8/31.
 */

public class Data {
    public Bitmap bitmap;
    public String url;
    private boolean isAvailiable;

    public Data(Bitmap bitmap, String url) {
        this.bitmap = bitmap;
        this.url = url;
    }

    public Data(File f, String url) {
        this.url = url;
        if (f != null && f.exists()) {
            try {

            bitmap = BitmapFactory.decodeStream(new FileInputStream(f));
            } catch (FileNotFoundException e) {
               e.printStackTrace();
            }


        }
    }


    public Boolean isAvailiable()
    {
        isAvailiable = url!=null&&bitmap!=null;
        return isAvailiable;


    }
}
