package com.cis.poultry.Models;

public class transactionDetails {

    private String date;
    private String paymentmode;
    private String receivedamount;

    public transactionDetails(String date, String paymentmode, String receivedamount) {
        this.date = date;
        this.paymentmode = paymentmode;
        this.receivedamount = receivedamount;
    }

    public String getDate() {
        return date;
    }

    public String getPaymentmode() {
        return paymentmode;
    }

    public String getReceivedamount() {
        return receivedamount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPaymentmode(String paymentmode) {
        this.paymentmode = paymentmode;
    }

    public void setReceivedamount(String receivedamount) {
        this.receivedamount = receivedamount;
    }
}
