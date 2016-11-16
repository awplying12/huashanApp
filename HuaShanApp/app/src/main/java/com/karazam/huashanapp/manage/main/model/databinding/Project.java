package com.karazam.huashanapp.manage.main.model.databinding;

/**
 * Created by Administrator on 2016/10/17.
 */

public class Project {
    private int status;
    private String status_tx;

    private String title;
    private String amount;
    private String period;
    private String periodUnit;
    private String interestRate;
    private String residualAmount;

    public Project() {
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getPeriodUnit() {
        return periodUnit;
    }

    public void setPeriodUnit(String periodUnit) {
        this.periodUnit = periodUnit;
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

    @Override
    public String toString() {
        return "Project{" +
                "status=" + status +
                ", status_tx='" + status_tx + '\'' +
                ", title='" + title + '\'' +
                ", amount='" + amount + '\'' +
                ", period='" + period + '\'' +
                ", periodUnit='" + periodUnit + '\'' +
                ", interestRate='" + interestRate + '\'' +
                ", residualAmount='" + residualAmount + '\'' +
                '}';
    }
}
