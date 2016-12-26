package com.karazam.huashanapp.today.main.model.databinding;

/**
 * Created by Administrator on 2016/12/26.
 */

public class HotProjects {
    private String borrowingId;
    private String title;
    private String interestRate;
    private String residualAmount;
    private String periodUnit;

    public String getBorrowingId() {
        return borrowingId;
    }

    public void setBorrowingId(String borrowingId) {
        this.borrowingId = borrowingId;
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

    public String getResidualAmount() {
        return residualAmount;
    }

    public void setResidualAmount(String residualAmount) {
        this.residualAmount = residualAmount;
    }

    public String getPeriodUnit() {
        return periodUnit;
    }

    public void setPeriodUnit(String periodUnit) {
        this.periodUnit = periodUnit;
    }

    @Override
    public String toString() {
        return "HotProjects{" +
                "borrowingId='" + borrowingId + '\'' +
                ", title='" + title + '\'' +
                ", interestRate='" + interestRate + '\'' +
                ", residualAmount='" + residualAmount + '\'' +
                ", periodUnit='" + periodUnit + '\'' +
                '}';
    }
}
