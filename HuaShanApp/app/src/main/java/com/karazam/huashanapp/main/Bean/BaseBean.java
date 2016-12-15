package com.karazam.huashanapp.main.Bean;

import com.google.gson.Gson;

/**
 * Created by Administrator on 2016/12/15.
 */

public abstract class BaseBean {

    public String toJsonString() {

        return new Gson().toJson(this);
    }
}
