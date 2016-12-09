package com.karazam.huashanapp.my.transactiondetails.main.view.activity.view;

import com.example.utils.utils.DataUtil;

import java.text.ParseException;

/**
 * Created by Administrator on 2016/12/9.
 */

public class TransactionBean {

    private long data;
    private String weekDay;

    public TransactionBean() {
    }

    public TransactionBean(long data) {
        this.data = data;
    }

    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }

    public String getWeekDay() {

        try {
            if(DataUtil.IsToday(data)){
                weekDay = "今天";
            }else if(DataUtil.IsYesterday(data)){
                weekDay = "昨天";
            }else {
                weekDay = DataUtil.getWeekDay(data);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    @Override
    public String toString() {
        return "TransactionBean{" +
                "data=" + data +
                ", weekDay='" + weekDay + '\'' +
                '}';
    }
}
