package com.karazam.huashanapp.manage.details_fragment.model.databinding;

/**
 * Created by Administrator on 2016/12/29.
 */

public class ManageOpinionsbean {

    private String progress;
    private Opinions opinions;

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public Opinions getOpinions() {
        return opinions;
    }

    public void setOpinions(Opinions opinions) {
        this.opinions = opinions;
    }

    @Override
    public String toString() {
        return "ManageOpinionsbean{" +
                "progress='" + progress + '\'' +
                ", opinions=" + opinions +
                '}';
    }
}
