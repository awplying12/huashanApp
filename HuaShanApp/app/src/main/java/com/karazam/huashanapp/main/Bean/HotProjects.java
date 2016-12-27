package com.karazam.huashanapp.main.Bean;

/**
 * Created by Administrator on 2016/12/26.
 */

public class HotProjects {

    private String borrowingId;
    private String interestRateStr;
    private String period;
    private String periodUnitStr;
    private String residualAmountStr;
    private String title;
    private String interestRate;
    private double residualAmount;
    private String periodUnit;
    private String progress;
    private String progressDes;

    public String getBorrowingId() {
        return borrowingId;
    }

    public void setBorrowingId(String borrowingId) {
        this.borrowingId = borrowingId;
    }

    public String getInterestRateStr() {
        return interestRateStr;
    }

    public void setInterestRateStr(String interestRateStr) {
        this.interestRateStr = interestRateStr;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getPeriodUnitStr() {
        return periodUnitStr;
    }

    public void setPeriodUnitStr(String periodUnitStr) {
        this.periodUnitStr = periodUnitStr;
    }

    public String getResidualAmountStr() {
        return residualAmountStr;
    }

    public void setResidualAmountStr(String residualAmountStr) {
        this.residualAmountStr = residualAmountStr;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate;
    }

    public double getResidualAmount() {
        return residualAmount;
    }

    public void setResidualAmount(double residualAmount) {
        this.residualAmount = residualAmount;
    }

    public String getPeriodUnit() {
        return periodUnit;
    }

    public void setPeriodUnit(String periodUnit) {
        this.periodUnit = periodUnit;
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
        return "HotProjects{" +
                "borrowingId='" + borrowingId + '\'' +
                ", interestRateStr='" + interestRateStr + '\'' +
                ", period='" + period + '\'' +
                ", periodUnitStr='" + periodUnitStr + '\'' +
                ", residualAmountStr='" + residualAmountStr + '\'' +
                ", title='" + title + '\'' +
                ", interestRate='" + interestRate + '\'' +
                ", residualAmount=" + residualAmount +
                ", periodUnit='" + periodUnit + '\'' +
                ", progress='" + progress + '\'' +
                ", progressDes='" + progressDes + '\'' +
                '}';
    }
}
