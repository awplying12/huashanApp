package com.karazam.huashanapp.main.update.AppInfo;

/**
 * Created by Administrator on 2017/1/18.
 */

public class AppInfoBean {
//    "changeLog":"1.更新头像 2.新增功能 3.修复bug",
//            "appUrl":"http://192.168.2.13:8999/group1/M00/00/03/wKgCDVh_AWCANiK6AYk-b744u3I171.apk",
//            "packageName":"com.karazam",
//            "versionName":"1.0.2",
//            "version":2

    private String changeLog,
                    appUrl,
                    packageName,
                    versionName,
                    version;

    public String getChangeLog() {
        return changeLog;
    }

    public void setChangeLog(String changeLog) {
        this.changeLog = changeLog;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "AppInfoBean{" +
                "changeLog='" + changeLog + '\'' +
                ", appUrl='" + appUrl + '\'' +
                ", packageName='" + packageName + '\'' +
                ", versionName='" + versionName + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
