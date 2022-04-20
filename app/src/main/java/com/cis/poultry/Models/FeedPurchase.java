package com.cis.poultry.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeedPurchase {

        private Integer id;
        private String date;
        private String brokerName;
        private Double cost;
        private String name;
        private String town;
    private Double weight;
        private String billDate;
        private String lorryNumber;
        private Double freight;
        private Double billAmount;
        private Double netAmount;
        private Object chequeNumber;
        private Object chequeDate;
        private Integer brokerId;
        private Integer feedTypeId;
        private Boolean isActive;
        private String feedType;
        private Integer createdByUserId;
        private String createdDate;
        private Integer paymentTypeId;
        private String paymentType;
        private Object remarks;
    private boolean expanded;

    public FeedPurchase(Integer id, String date, String brokerName, Double cost, String name, String town, Double weight, String billDate, String lorryNumber, Double freight, Double billAmount, Double netAmount, Object chequeNumber, Object chequeDate, Integer brokerId, Integer feedTypeId, Boolean isActive, String feedType, Integer createdByUserId, String createdDate, Integer paymentTypeId, String paymentType, Object remarks, Double totalAmounts, Double billAmounts, String receiveddate, Object invoiceNumber, Integer paymentStatusId, String paymentStatus, Object additionalAxpenses, Boolean considerFreight, Integer uOMTypeId, String uOMType, Object differenceAmount, Double finalAmount, Object gST, String dueDate) {
        this.id = id;
        this.date = date;
        this.brokerName = brokerName;
        this.cost = cost;
        this.name = name;
        this.town = town;
        this.weight = weight;
        this.billDate = billDate;
        this.lorryNumber = lorryNumber;
        this.freight = freight;
        this.billAmount = billAmount;
        this.netAmount = netAmount;
        this.chequeNumber = chequeNumber;
        this.chequeDate = chequeDate;
        this.brokerId = brokerId;
        this.feedTypeId = feedTypeId;
        this.isActive = isActive;
        this.feedType = feedType;
        this.createdByUserId = createdByUserId;
        this.createdDate = createdDate;
        this.paymentTypeId = paymentTypeId;
        this.paymentType = paymentType;
        this.remarks = remarks;
        this.totalAmounts = totalAmounts;
        this.billAmounts = billAmounts;
        this.receiveddate = receiveddate;
        this.invoiceNumber = invoiceNumber;
        this.paymentStatusId = paymentStatusId;
        this.paymentStatus = paymentStatus;
        this.additionalAxpenses = additionalAxpenses;
        this.considerFreight = considerFreight;
        this.uOMTypeId = uOMTypeId;
        this.uOMType = uOMType;
        this.differenceAmount = differenceAmount;
        this.finalAmount = finalAmount;
        this.gST = gST;
        this.dueDate = dueDate;
    }

    private Double totalAmounts;
        private Double billAmounts;
        private String receiveddate;
        private Object invoiceNumber;
        private Integer paymentStatusId;
        private String paymentStatus;
        private Object additionalAxpenses;
        private Boolean considerFreight;
        private Integer uOMTypeId;
        private String uOMType;
        private Object differenceAmount;
        private Double finalAmount;
        private Object gST;
        private String dueDate;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getBrokerName() {
            return brokerName;
        }

        public void setBrokerName(String brokerName) {
            this.brokerName = brokerName;
        }

        public Double getCost() {
            return cost;
        }

        public void setCost(Double cost) {
            this.cost = cost;
        }

        public Double getWeight() {
            return weight;
        }

        public void setWeight(Double weight) {
            this.weight = weight;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTown() {
            return town;
        }

        public void setTown(String town) {
            this.town = town;
        }

        public String getBillDate() {
            return billDate;
        }

        public void setBillDate(String billDate) {
            this.billDate = billDate;
        }

        public String getLorryNumber() {
            return lorryNumber;
        }

        public void setLorryNumber(String lorryNumber) {
            this.lorryNumber = lorryNumber;
        }

        public Double getFreight() {
            return freight;
        }

        public void setFreight(Double freight) {
            this.freight = freight;
        }

        public Double getBillAmount() {
            return billAmount;
        }

        public void setBillAmount(Double billAmount) {
            this.billAmount = billAmount;
        }

        public Double getNetAmount() {
            return netAmount;
        }

        public void setNetAmount(Double netAmount) {
            this.netAmount = netAmount;
        }

        public Object getChequeNumber() {
            return chequeNumber;
        }

        public void setChequeNumber(Object chequeNumber) {
            this.chequeNumber = chequeNumber;
        }

        public Object getChequeDate() {
            return chequeDate;
        }

        public void setChequeDate(Object chequeDate) {
            this.chequeDate = chequeDate;
        }

        public Integer getBrokerId() {
            return brokerId;
        }

        public void setBrokerId(Integer brokerId) {
            this.brokerId = brokerId;
        }

        public Integer getFeedTypeId() {
            return feedTypeId;
        }

        public void setFeedTypeId(Integer feedTypeId) {
            this.feedTypeId = feedTypeId;
        }

        public Boolean getIsActive() {
            return isActive;
        }

        public void setIsActive(Boolean isActive) {
            this.isActive = isActive;
        }

        public String getFeedType() {
            return feedType;
        }

        public void setFeedType(String feedType) {
            this.feedType = feedType;
        }

        public Integer getCreatedByUserId() {
            return createdByUserId;
        }

        public void setCreatedByUserId(Integer createdByUserId) {
            this.createdByUserId = createdByUserId;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public Integer getPaymentTypeId() {
            return paymentTypeId;
        }

        public void setPaymentTypeId(Integer paymentTypeId) {
            this.paymentTypeId = paymentTypeId;
        }

        public String getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(String paymentType) {
            this.paymentType = paymentType;
        }

        public Object getRemarks() {
            return remarks;
        }

        public void setRemarks(Object remarks) {
            this.remarks = remarks;
        }

        public Double getTotalAmounts() {
            return totalAmounts;
        }

        public void setTotalAmounts(Double totalAmounts) {
            this.totalAmounts = totalAmounts;
        }

        public Double getBillAmounts() {
            return billAmounts;
        }

        public void setBillAmounts(Double billAmounts) {
            this.billAmounts = billAmounts;
        }

        public String getReceiveddate() {
            return receiveddate;
        }

        public void setReceiveddate(String receiveddate) {
            this.receiveddate = receiveddate;
        }

        public Object getInvoiceNumber() {
            return invoiceNumber;
        }

        public void setInvoiceNumber(Object invoiceNumber) {
            this.invoiceNumber = invoiceNumber;
        }

        public Integer getPaymentStatusId() {
            return paymentStatusId;
        }

        public void setPaymentStatusId(Integer paymentStatusId) {
            this.paymentStatusId = paymentStatusId;
        }

        public String getPaymentStatus() {
            return paymentStatus;
        }

        public void setPaymentStatus(String paymentStatus) {
            this.paymentStatus = paymentStatus;
        }

        public Object getAdditionalAxpenses() {
            return additionalAxpenses;
        }

        public void setAdditionalAxpenses(Object additionalAxpenses) {
            this.additionalAxpenses = additionalAxpenses;
        }

        public Boolean getConsiderFreight() {
            return considerFreight;
        }

        public void setConsiderFreight(Boolean considerFreight) {
            this.considerFreight = considerFreight;
        }

        public Integer getUOMTypeId() {
            return uOMTypeId;
        }

        public void setUOMTypeId(Integer uOMTypeId) {
            this.uOMTypeId = uOMTypeId;
        }

        public String getUOMType() {
            return uOMType;
        }

        public void setUOMType(String uOMType) {
            this.uOMType = uOMType;
        }

        public Object getDifferenceAmount() {
            return differenceAmount;
        }

        public void setDifferenceAmount(Object differenceAmount) {
            this.differenceAmount = differenceAmount;
        }

        public Double getFinalAmount() {
            return finalAmount;
        }

        public void setFinalAmount(Double finalAmount) {
            this.finalAmount = finalAmount;
        }

        public Object getGST() {
            return gST;
        }

        public void setGST(Object gST) {
            this.gST = gST;
        }

        public String getDueDate() {
            return dueDate;
        }

        public void setDueDate(String dueDate) {
            this.dueDate = dueDate;
        }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}


