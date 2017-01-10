package com.karazam.huashanapp.main.Bean.MyInformation;

/**
 * Created by Administrator on 2016/12/29.
 */

public class CardBean {
//    "mobile":"13258382898",
//            "cardNo":"6222****4910",
//            "bankCardId":1,
//            "bankName":"交通银行",
//            "bankLogo":"/upload/image/201404/f1c9a7c5-849d-401b-bd3e-05059dc26224.jpg"
//             quickPayMemo 卡描述
    private String mobile;
    private String cardNo;
    private String bankCardId;
    private String bankName;
    private String bankLogo;
    private String quickPayMemo;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getBankCardId() {
        return bankCardId;
    }

    public void setBankCardId(String bankCardId) {
        this.bankCardId = bankCardId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankLogo() {
        return bankLogo;
    }

    public void setBankLogo(String bankLogo) {
        this.bankLogo = bankLogo;
    }

    public String getQuickPayMemo() {
        return quickPayMemo;
    }

    public void setQuickPayMemo(String quickPayMemo) {
        this.quickPayMemo = quickPayMemo;
    }

    @Override
    public String toString() {
        return "CardBean{" +
                "mobile='" + mobile + '\'' +
                ", cardNo='" + cardNo + '\'' +
                ", bankCardId='" + bankCardId + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankLogo='" + bankLogo + '\'' +
                ", quickPayMemo='" + quickPayMemo + '\'' +
                '}';
    }
}
