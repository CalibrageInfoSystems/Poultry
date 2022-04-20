package com.cis.poultry.Models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EggSaleresponse {

    @SerializedName("ListResult")
    @Expose
    private Object listResult;
    @SerializedName("Result")
    @Expose
    private Result result;
    @SerializedName("IsSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("AffectedRecords")
    @Expose
    private Integer affectedRecords;
    @SerializedName("TotalRecords")
    @Expose
    private Integer totalRecords;
    @SerializedName("EndUserMessage")
    @Expose
    private String endUserMessage;
    @SerializedName("ValidationErrors")
    @Expose
    private List<Object> validationErrors = null;
    @SerializedName("Exception")
    @Expose
    private Object exception;

    public Object getListResult() {
        return listResult;
    }

    public void setListResult(Object listResult) {
        this.listResult = listResult;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Integer getAffectedRecords() {
        return affectedRecords;
    }

    public void setAffectedRecords(Integer affectedRecords) {
        this.affectedRecords = affectedRecords;
    }

    public Integer getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Integer totalRecords) {
        this.totalRecords = totalRecords;
    }

    public String getEndUserMessage() {
        return endUserMessage;
    }

    public void setEndUserMessage(String endUserMessage) {
        this.endUserMessage = endUserMessage;
    }

    public List<Object> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(List<Object> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public Object getException() {
        return exception;
    }

    public void setException(Object exception) {
        this.exception = exception;
    }


public class Result {

    @SerializedName("saleTransactions")
    @Expose
    private List<SaleTransaction> saleTransactions = null;
    @SerializedName("SaleRegisterDetails")
    @Expose
    private List<SaleRegisterDetail> saleRegisterDetails = null;
    @SerializedName("Traders")
    @Expose
    private List<Trader> traders = null;

    public List<SaleTransaction> getSaleTransactions() {
        return saleTransactions;
    }

    public void setSaleTransactions(List<SaleTransaction> saleTransactions) {
        this.saleTransactions = saleTransactions;
    }

    public List<SaleRegisterDetail> getSaleRegisterDetails() {
        return saleRegisterDetails;
    }

    public void setSaleRegisterDetails(List<SaleRegisterDetail> saleRegisterDetails) {
        this.saleRegisterDetails = saleRegisterDetails;
    }

    public List<Trader> getTraders() {
        return traders;
    }

    public void setTraders(List<Trader> traders) {
        this.traders = traders;
    }



public class SaleRegisterDetail {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("TraderId")
    @Expose
    private Integer traderId;
    @SerializedName("Date")
    @Expose
    private String date;
    @SerializedName("LorryNumber")
    @Expose
    private String lorryNumber;
    @SerializedName("NumberofBoxes")
    @Expose
    private Double numberofBoxes;
    @SerializedName("NECCRate")
    @Expose
    private Double nECCRate;
    @SerializedName("BillRate")
    @Expose
    private Double billRate;
    @SerializedName("BillAmount")
    @Expose
    private Double billAmount;
    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;
    @SerializedName("CreatedByUserId")
    @Expose
    private Integer createdByUserId;
    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;
    @SerializedName("UpdatedByUserId")
    @Expose
    private Integer updatedByUserId;
    @SerializedName("UpdatedDate")
    @Expose
    private String updatedDate;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Location")
    @Expose
    private String location;
    @SerializedName("MobileNumber")
    @Expose
    private String mobileNumber;
    @SerializedName("CummulativeBillAmount")
    @Expose
    private Double cummulativeBillAmount;
    @SerializedName("Remarks")
    @Expose
    private Object remarks;
    private boolean isExpanded;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTraderId() {
        return traderId;
    }

    public void setTraderId(Integer traderId) {
        this.traderId = traderId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLorryNumber() {
        return lorryNumber;
    }

    public void setLorryNumber(String lorryNumber) {
        this.lorryNumber = lorryNumber;
    }

    public Double getNumberofBoxes() {
        return numberofBoxes;
    }

    public void setNumberofBoxes(Double numberofBoxes) {
        this.numberofBoxes = numberofBoxes;
    }

    public Double getNECCRate() {
        return nECCRate;
    }

    public void setNECCRate(Double nECCRate) {
        this.nECCRate = nECCRate;
    }

    public Double getBillRate() {
        return billRate;
    }

    public void setBillRate(Double billRate) {
        this.billRate = billRate;
    }

    public Double getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(Double billAmount) {
        this.billAmount = billAmount;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
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

    public Integer getUpdatedByUserId() {
        return updatedByUserId;
    }

    public void setUpdatedByUserId(Integer updatedByUserId) {
        this.updatedByUserId = updatedByUserId;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Double getCummulativeBillAmount() {
        return cummulativeBillAmount;
    }

    public void setCummulativeBillAmount(Double cummulativeBillAmount) {
        this.cummulativeBillAmount = cummulativeBillAmount;
    }

    public Object getRemarks() {
        return remarks;
    }

    public void setRemarks(Object remarks) {
        this.remarks = remarks;
    }


    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }
}

public class SaleTransaction {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("TraderId")
    @Expose
    private Integer traderId;
    @SerializedName("TransactionDate")
    @Expose
    private String transactionDate;
    @SerializedName("ReceivedAmount")
    @Expose
    private Double receivedAmount;
    @SerializedName("PaymentTypeId")
    @Expose
    private Integer paymentTypeId;
    @SerializedName("PaymentMode")
    @Expose
    private String paymentMode;
    @SerializedName("CreatedByUserId")
    @Expose
    private Integer createdByUserId;
    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;
    @SerializedName("UpdatedByUserId")
    @Expose
    private Integer updatedByUserId;
    @SerializedName("UpdatedDate")
    @Expose
    private String updatedDate;
    @SerializedName("TraderName")
    @Expose
    private String traderName;
    @SerializedName("CheckNumber")
    @Expose
    private Object checkNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTraderId() {
        return traderId;
    }

    public void setTraderId(Integer traderId) {
        this.traderId = traderId;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Double getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(Double receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public Integer getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(Integer paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
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

    public Integer getUpdatedByUserId() {
        return updatedByUserId;
    }

    public void setUpdatedByUserId(Integer updatedByUserId) {
        this.updatedByUserId = updatedByUserId;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getTraderName() {
        return traderName;
    }

    public void setTraderName(String traderName) {
        this.traderName = traderName;
    }

    public Object getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(Object checkNumber) {
        this.checkNumber = checkNumber;
    }

}


public class Trader {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("ReceivedAmount")
    @Expose
    private Double receivedAmount;
    @SerializedName("BillingAmount")
    @Expose
    private Double billingAmount;
    @SerializedName("DueAmount")
    @Expose
    private Double dueAmount;
    @SerializedName("NumberofBoxes")
    @Expose
    private Double numberofBoxes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(Double receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public Double getBillingAmount() {
        return billingAmount;
    }

    public void setBillingAmount(Double billingAmount) {
        this.billingAmount = billingAmount;
    }

    public Double getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(Double dueAmount) {
        this.dueAmount = dueAmount;
    }

    public Double getNumberofBoxes() {
        return numberofBoxes;
    }

    public void setNumberofBoxes(Double numberofBoxes) {
        this.numberofBoxes = numberofBoxes;
    }

}}}