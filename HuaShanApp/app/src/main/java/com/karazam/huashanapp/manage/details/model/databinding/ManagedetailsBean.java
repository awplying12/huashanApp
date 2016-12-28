package com.karazam.huashanapp.manage.details.model.databinding;

/**
 * Created by Administrator on 2016/12/28.
 */

public class ManagedetailsBean {

    private Project project;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "ManagedetailsBean{" +
                "project=" + project +
                '}';
    }

}
