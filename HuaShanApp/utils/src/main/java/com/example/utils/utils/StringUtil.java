package com.example.utils.utils;

import android.text.TextUtils;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2016/4/27.
 */
public class StringUtil {

    /**
     * 退出登录
     *
     **/
    public final static String LOG_OUT = "log_out";

    /**
     * 刷新模式字段
     */
    public static final  int IS_FRIST =0;     //初次加载
    public static final int REFRESH_DOWN = 1; //下拉刷新
    public static final int REFRESH_UP = 2;   //上拉加载

    /**
     * 处理图片字段
     */
    public static final String IMAGE_FILE_NAME = "head.jpg";
    public static final int IMAGE_REQUEST_CODE = 0;
    public static final int CAMERA_REQUEST_CODE = 1;
    public static final int RESULT_REQUEST_CODE = 2;
    public static final int MESSAGESEDIT_REQUEST_CODE = 3;

    /**
     * 字符串截断
     * @param str
     * @param size
     * @return
     */
    public static String interrupt(String str,int size,String prompt){
        if(TextUtils.isEmpty(str)){ //先判空
//            Log.i("msg","字段是空的");

        }else {
            if(size != 0){
                if(str.length()> size){
                    StringBuffer sb = new StringBuffer();
                    sb.append(str.substring(0,size-1)).append("...");
                    return sb.toString();
                }else {
                   return str;
                }
            }else {
                return str;
            }

        }
        return prompt;
    }

    /**
     * 字符在num个字符换行
     * @param str
     * @param num
     * @return
     */
    public static String linefeed(String str,int num){
        StringBuffer sb = new StringBuffer();
        int num_x = num;
        if(TextUtils.isEmpty(str)){

        }else {
            char[] strs = str.toCharArray();
            for(int i = 0 ; i< strs.length;i++){
                sb.append(strs[i]);
                if(i == num_x){
                    sb.append("\n");
                    num_x = num_x + num;
                }
            }

        }
        return sb.toString();
    }

    /**
     * 保留小数点
     * @param data
     * @param num
     * @param <T>
     * @return
     */
    public static<T> String reservedDecimal(T data,int num){
        BigDecimal bd = new BigDecimal(String.valueOf(data));
        bd = bd.setScale(num,BigDecimal.ROUND_HALF_UP);
        return String.valueOf(bd);
    }

}
