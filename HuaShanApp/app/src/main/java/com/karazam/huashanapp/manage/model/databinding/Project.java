package com.karazam.huashanapp.manage.model.databinding;

/**
 * Created by Administrator on 2016/10/17.
 */

public class Project {
    private int status;

    public Project(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Project{" +
                "status=" + status +
                '}';
    }
}
