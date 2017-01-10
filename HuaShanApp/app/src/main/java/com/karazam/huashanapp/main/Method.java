package com.karazam.huashanapp.main;

import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.manage.details.model.databinding.Project;


import java.math.BigDecimal;

/**
 * Created by Administrator on 2016/11/16.
 */

public class Method {

    public static String calculateIncome(int money, Project project){
        double result = 0;
        double interestRate = Double.parseDouble(StringUtil.interrupt(project.getInterestRate(),0,"0"))/100;
        double period = Double.parseDouble(StringUtil.interrupt(project.getPeriod(),0,"0"));
        if(StringUtil.interrupt(project.getPeriodUnitDes(),0,"").equals("天")){
            result = money*(interestRate/365)*period;
        }else {
            result = money*(interestRate/12)*period;
        }

        BigDecimal bd = new BigDecimal(result);
        bd = bd.setScale(2,BigDecimal.ROUND_HALF_UP);
        return bd+"";

    }
}
