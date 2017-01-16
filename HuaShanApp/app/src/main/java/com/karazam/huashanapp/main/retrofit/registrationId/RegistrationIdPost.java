package com.karazam.huashanapp.main.retrofit.registrationId;

/**
 * Created by Administrator on 2017/1/16.
 */

public class RegistrationIdPost {

//    userId
//    registrationId	设备标识
//    osType	设备系统（android/ios）

    private String userId,
            registrationId,
            osType = "android";

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    @Override
    public String toString() {
        return "RegistrationIdPost{" +
                "userId='" + userId + '\'' +
                ", registrationId='" + registrationId + '\'' +
                ", osType='" + osType + '\'' +
                '}';
    }
}
