package com.okra.widget.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Filter implements Serializable {

    public Filter(){}

    public Filter(String industry_type, ArrayList<String> banks){
        this.industry_type = industry_type;
        this.banks = banks;
    }

    private String industry_type;
    private ArrayList<String> banks;

    public String getIndustry_type() {
        return industry_type;
    }

    public void setIndustry_type(String industry_type) {
        this.industry_type = industry_type;
    }

    public ArrayList<String> getBanks() {
        return banks;
    }

    public void setBanks(ArrayList<String> banks) {
        this.banks = banks;
    }
}

