package com.karazam.huashanapp.main.Bean.financialproject;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/5.
 */

public class FinancialProject {

    private ArrayList<FinancialInformation> informations;

    public FinancialProject() {
    }

    public FinancialProject(ArrayList<FinancialInformation> informations) {
        this.informations = informations;
    }

    public ArrayList<FinancialInformation> getInformations() {
        return informations;
    }

    public void setInformations(ArrayList<FinancialInformation> informations) {
        this.informations = informations;
    }

    @Override
    public String toString() {
        return "FinancialProject{" +
                "informations=" + informations +
                '}';
    }
}
