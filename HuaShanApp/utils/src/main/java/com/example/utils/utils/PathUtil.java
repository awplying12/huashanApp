package com.example.utils.utils;

import android.os.Environment;

import java.io.File;

/**
 * Created by Administrator on 2016/4/21.
 */
public class PathUtil {

    //SD卡路径
    private static String pathname = Environment.getExternalStorageDirectory().getPath();
    private static String dirname = "Family_old_ImageFile";

    public static void createDir(){
        File filedir = new File(pathname+File.separator+dirname+File.separator);
        filedir.mkdir();
    }

    public static String getPath(){
        return pathname+File.separator+dirname;
    }

}
