package com.karazam.huashanapp.manage.model.databinding;

/**
 * Created by Administrator on 2016/10/17.
 */

public class Project {
    private int status;
    private String status_tx;

    public Project(int status, String status_tx) {
        this.status = status;
        this.status_tx = status_tx;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatus_tx() {
        return status_tx;
    }

    public void setStatus_tx(String status_tx) {
        this.status_tx = status_tx;
    }

    @Override
    public String toString() {
        return "Project{" +
                "status=" + status +
                ", status_tx='" + status_tx + '\'' +
                '}';
    }
}
