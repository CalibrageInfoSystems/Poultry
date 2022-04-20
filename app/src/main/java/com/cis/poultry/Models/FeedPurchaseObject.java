package com.cis.poultry.Models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeedPurchaseObject {

    @SerializedName("FeedTypeId")
    @Expose
    private Integer feedTypeId;
    @SerializedName("PaymentStatusId")
    @Expose
    private Integer paymentStatusId;
    @SerializedName("FromDate")
    @Expose
    private String fromDate;
    @SerializedName("ToDate")
    @Expose
    private String toDate;
    @SerializedName("FarmId")
    @Expose
    private Integer farmId;

    public Integer getFeedTypeId() {
        return feedTypeId;
    }

    public void setFeedTypeId(Integer feedTypeId) {
        this.feedTypeId = feedTypeId;
    }

    public Integer getPaymentStatusId() {
        return paymentStatusId;
    }

    public void setPaymentStatusId(Integer paymentStatusId) {
        this.paymentStatusId = paymentStatusId;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public Integer getFarmId() {
        return farmId;
    }

    public void setFarmId(Integer farmId) {
        this.farmId = farmId;
    }}