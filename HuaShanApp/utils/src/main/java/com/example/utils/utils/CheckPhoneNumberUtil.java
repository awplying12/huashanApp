package com.example.utils.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by YC.Zhu on 2016/5/20.
 */
public class CheckPhoneNumberUtil {

    private static CheckPhoneNumberUtil instance;

    public static CheckPhoneNumberUtil getInstance() {
        if (instance == null) {
            instance = new CheckPhoneNumberUtil();
        }
        return instance;
    }

    public boolean CheckPhoneNumber(String phone) {
        if (phone.length() == 11 && isNumeric(phone)) {
            int head = Integer.parseInt(phone.substring(0, 3));
            if (isYidong(head) || isDianxin(head) || isLiantong(head) || isVirtuaPhoneNumber(head))
                return true;
            else
                return false;
        } else
            return false;
    }

    private boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    private boolean isYidong(int head) {
        if (134 <= head && head <= 139)
            return true;
        else if (150 <= head && head <= 152)
            return true;
        else if (157 <= head && head <= 159)
            return true;
        else if (182 <= head && head <= 184)
            return true;
        else if (187 <= head && head <= 188)
            return true;
        else if (head == 178 || head == 147)
            return true;
        else return false;
    }

    private boolean isDianxin(int head) {
        if (head == 133 || head == 153 || head == 177 || head == 180 || head == 181 || head == 189)
            return true;
        else return false;
    }

    private boolean isLiantong(int head) {
        if (130 <= head && head <= 132)
            return true;
        else if (head == 145 || head == 176)
            return true;
        else if (155 <= head && head <= 156)
            return true;
        else if (185 <= head && head <= 186)
            return true;
        else return false;
    }

    private boolean isVirtuaPhoneNumber(int head) {
        if (head == 170)
            return true;
        else return false;
    }

}
