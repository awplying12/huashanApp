package com.karazam.huashanapp.manage.details_fragment.model.databinding;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/29.
 */

public class Opinions {

    private OpinionsItem inquiry;
    private OpinionsItem approval;
    private OpinionsItem buying;
    private OpinionsItem lend;
    private OpinionsItem repay;

    public OpinionsItem getInquiry() {
        return inquiry;
    }

    public void setInquiry(OpinionsItem inquiry) {
        this.inquiry = inquiry;
    }

    public OpinionsItem getApproval() {
        return approval;
    }

    public void setApproval(OpinionsItem approval) {
        this.approval = approval;
    }

    public OpinionsItem getBuying() {
        return buying;
    }

    public void setBuying(OpinionsItem buying) {
        this.buying = buying;
    }

    public OpinionsItem getLend() {
        return lend;
    }

    public void setLend(OpinionsItem lend) {
        this.lend = lend;
    }

    public OpinionsItem getRepay() {
        return repay;
    }

    public void setRepay(OpinionsItem repay) {
        this.repay = repay;
    }


    @Override
    public String toString() {
        return "Opinions{" +
                "inquiry=" + inquiry +
                ", approval=" + approval +
                ", buying=" + buying +
                ", lend=" + lend +
                ", repay=" + repay +
                '}';
    }
}
