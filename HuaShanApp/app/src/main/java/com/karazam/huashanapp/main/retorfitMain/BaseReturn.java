package com.karazam.huashanapp.main.retorfitMain;

/**
 * Created by Administrator on 2016/10/20.
 */

public class BaseReturn<Data> {

    private String code;
    private Data data;
    private String msg;
    private String status;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BaseReturn{" +
                "code='" + code + '\'' +
                ", data='" + data + '\'' +
                ", msg='" + msg + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
