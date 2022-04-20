package com.cis.poultry.Models;

public class purchasedetails {

    private String date;
    private String name;
    private String netamount;
    private String duedate;
    private String status;

    public purchasedetails(String date, String name, String netamount, String duedate, String status) {
        this.date = date;
        this.name = name;
        this.netamount = netamount;
        this.duedate = duedate;
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getNetamount() {
        return netamount;
    }

    public String getDuedate() {
        return duedate;
    }

    public String getStatus() {
        return status;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNetamount(String netamount) {
        this.netamount = netamount;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
