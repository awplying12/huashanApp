package com.karazam.huashanapp.manage.details_fragment.model.databinding;

/**
 * Created by Administrator on 2016/12/28.
 */

public class RecordsItem {

    private String investor;
    private String buyTime;
    private String amount;
    private String operationMethod;
    private String operationMethodStr;

    public String getInvestor() {
        return investor;
    }

    public void setInvestor(String investor) {
        this.investor = investor;
    }

    public String getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getOperationMethod() {
        return operationMethod;
    }

    public void setOperationMethod(String operationMethod) {
        this.operationMethod = operationMethod;
    }

    public String getOperationMethodStr() {
        return operationMethodStr;
    }

    public void setOperationMethodStr(String operationMethodStr) {
        this.operationMethodStr = operationMethodStr;
    }

    @Override
    public String toString() {
        return "RecordsItem{" +
                "investor='" + investor + '\'' +
                ", buyTime='" + buyTime + '\'' +
                ", amount='" + amount + '\'' +
                ", operationMethod='" + operationMethod + '\'' +
                ", operationMethodStr='" + operationMethodStr + '\'' +
                '}';
    }
}
