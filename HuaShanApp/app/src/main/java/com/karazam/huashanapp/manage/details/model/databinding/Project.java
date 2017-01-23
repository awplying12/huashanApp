package com.karazam.huashanapp.manage.details.model.databinding;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/28.
 */

//        period	期限
//        interestRate	利率
//        title	标题
//        publishDate	发布日期
//        repaymentMethodDes	还款方式
//        repaymentInquiry	还款来源
//        purpose	借款用途
//        amount	借款金额
//        description	借款描述
//        periodUnitDes	期限描述
//        investmentMinimum	起投金额
//        residualAmount	剩余可投金额
//        materials	项目材料
//        progress = investing


public class Project {
    private String period;
    private String interestRate;
    private String title;
    private String publishDate;
    private String repaymentMethodDes;
    private String repaymentInquiry;
    private String purpose;
    private String amount;
    private String description;
    private String periodUnitDes;
    private String investmentMinimum;
    private String residualAmount;
    private String progress;
    private String progressDes;
    private ArrayList<String> materials;

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getRepaymentMethodDes() {
        return repaymentMethodDes;
    }

    public void setRepaymentMethodDes(String repaymentMethodDes) {
        this.repaymentMethodDes = repaymentMethodDes;
    }

    public String getRepaymentInquiry() {
        return repaymentInquiry;
    }

    public void setRepaymentInquiry(String repaymentInquiry) {
        this.repaymentInquiry = repaymentInquiry;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPeriodUnitDes() {
        return periodUnitDes;
    }

    public void setPeriodUnitDes(String periodUnitDes) {
        this.periodUnitDes = periodUnitDes;
    }

    public String getInvestmentMinimum() {
        return investmentMinimum;
    }

    public void setInvestmentMinimum(String investmentMinimum) {
        this.investmentMinimum = investmentMinimum;
    }

    public String getResidualAmount() {
        return residualAmount;
    }

    public void setResidualAmount(String residualAmount) {
        this.residualAmount = residualAmount;
    }

    public ArrayList<String> getMaterials() {
        return materials;
    }

    public void setMaterials(ArrayList<String> materials) {
        this.materials = materials;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getProgressDes() {
        return progressDes;
    }

    public void setProgressDes(String progressDes) {
        this.progressDes = progressDes;
    }

    @Override
    public String toString() {
        return "Project{" +
                "period='" + period + '\'' +
                ", interestRate='" + interestRate + '\'' +
                ", title='" + title + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", repaymentMethodDes='" + repaymentMethodDes + '\'' +
                ", repaymentInquiry='" + repaymentInquiry + '\'' +
                ", purpose='" + purpose + '\'' +
                ", amount='" + amount + '\'' +
                ", description='" + description + '\'' +
                ", periodUnitDes='" + periodUnitDes + '\'' +
                ", investmentMinimum='" + investmentMinimum + '\'' +
                ", residualAmount='" + residualAmount + '\'' +
                ", progress='" + progress + '\'' +
                ", progressDes='" + progressDes + '\'' +
                ", materials=" + materials +
                '}';
    }
}
