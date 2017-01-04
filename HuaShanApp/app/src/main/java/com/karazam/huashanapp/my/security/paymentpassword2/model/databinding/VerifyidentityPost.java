package com.karazam.huashanapp.my.security.paymentpassword2.model.databinding;

/**
 * Created by Administrator on 2017/1/4.
 */

public class VerifyidentityPost {
    private String idNo;
    private String cardNo;
    private String type;

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "VerifyidentityPost{" +
                "idNo='" + idNo + '\'' +
                ", cardNo='" + cardNo + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
