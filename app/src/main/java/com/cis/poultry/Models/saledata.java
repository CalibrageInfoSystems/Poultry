package com.cis.poultry.Models;

public class saledata {
    private String date;
    private String noofcartons;
    private String billrate;
    private String billamount;

    public saledata(String date, String noofcartons, String billrate, String billamount) {
        this.date = date;
        this.noofcartons = noofcartons;
        this.billrate = billrate;
        this.billamount = billamount;
    }

    public String getDate() {
        return date;
    }

    public String getNoofcartons() {
        return noofcartons;
    }

    public String getBillrate() {
        return billrate;
    }

    public String getBillamount() {
        return billamount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setNoofcartons(String noofcartons) {
        this.noofcartons = noofcartons;
    }

    public void setBillrate(String billrate) {
        this.billrate = billrate;
    }

    public void setBillamount(String billamount) {
        this.billamount = billamount;
    }
}
