package com.karazam.huashanapp.manage.purchase.model.databinding;

/**
 * Created by Administrator on 2017/1/10.
 */

public class PurchasSMSPost {
//    projectId	项目ID
//    bankCardId	快捷卡ID
//    amount	投资金额
    private String projectId,bankCardId,amount;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getBankCardId() {
        return bankCardId;
    }

    public void setBankCardId(String bankCardId) {
        this.bankCardId = bankCardId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "PurchasSMSPost{" +
                "projectId='" + projectId + '\'' +
                ", bankCardId='" + bankCardId + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
