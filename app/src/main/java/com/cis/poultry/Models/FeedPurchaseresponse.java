package com.cis.poultry.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

        import java.util.List;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class FeedPurchaseresponse {

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

    @SerializedName("feedTypes")
    @Expose
    private List<FeedType> feedTypes = null;
    @SerializedName("feedPurchaseDetails")
    @Expose
    private List<FeedPurchaseDetail> feedPurchaseDetails = null;

    public List<FeedType> getFeedTypes() {
        return feedTypes;
    }

    public void setFeedTypes(List<FeedType> feedTypes) {
        this.feedTypes = feedTypes;
    }

    public List<FeedPurchaseDetail> getFeedPurchaseDetails() {
        return feedPurchaseDetails;
    }

    public void setFeedPurchaseDetails(List<FeedPurchaseDetail> feedPurchaseDetails) {
        this.feedPurchaseDetails = feedPurchaseDetails;
    }


    public class FeedPurchaseDetail {

        @SerializedName("Id")
        @Expose
        private Integer id;
        @SerializedName("Date")
        @Expose
        private String date;
        @SerializedName("BrokerName")
        @Expose
        private String brokerName;
        @SerializedName("Cost")
        @Expose
        private Double cost;
        @SerializedName("Weight")
        @Expose
        private Double weight;
        @SerializedName("Name")
        @Expose
        private String name;
        @SerializedName("Town")
        @Expose
        private String town;
        @SerializedName("BillDate")
        @Expose
        private String billDate;
        @SerializedName("LorryNumber")
        @Expose
        private String lorryNumber;
        @SerializedName("Freight")
        @Expose
        private Double freight;
        @SerializedName("BillAmount")
        @Expose
        private Double billAmount;
        @SerializedName("NetAmount")
        @Expose
        private Double netAmount;
        @SerializedName("ChequeNumber")
        @Expose
        private Object chequeNumber;
        @SerializedName("ChequeDate")
        @Expose
        private Object chequeDate;
        @SerializedName("BrokerId")
        @Expose
        private Integer brokerId;
        @SerializedName("FeedTypeId")
        @Expose
        private Integer feedTypeId;
        @SerializedName("IsActive")
        @Expose
        private Boolean isActive;
        @SerializedName("FeedType")
        @Expose
        private String feedType;
        @SerializedName("CreatedByUserId")
        @Expose
        private Integer createdByUserId;
        @SerializedName("CreatedDate")
        @Expose
        private String createdDate;
        @SerializedName("PaymentTypeId")
        @Expose
        private Integer paymentTypeId;
        @SerializedName("PaymentType")
        @Expose
        private String paymentType;
        @SerializedName("Remarks")
        @Expose
        private Object remarks;
        @SerializedName("TotalAmounts")
        @Expose
        private Double totalAmounts;
        @SerializedName("BillAmounts")
        @Expose
        private Double billAmounts;
        @SerializedName("Receiveddate")
        @Expose
        private String receiveddate;
        @SerializedName("InvoiceNumber")
        @Expose
        private Object invoiceNumber;
        @SerializedName("PaymentStatusId")
        @Expose
        private Integer paymentStatusId;
        @SerializedName("PaymentStatus")
        @Expose
        private String paymentStatus;
        @SerializedName("AdditionalAxpenses")
        @Expose
        private Object additionalAxpenses;
        @SerializedName("ConsiderFreight")
        @Expose
        private Boolean considerFreight;
        @SerializedName("UOMTypeId")
        @Expose
        private Integer uOMTypeId;
        @SerializedName("UOMType")
        @Expose
        private String uOMType;
        @SerializedName("DifferenceAmount")
        @Expose
        private Object differenceAmount;
        @SerializedName("FinalAmount")
        @Expose
        private Double finalAmount;
        @SerializedName("GST")
        @Expose
        private Object gST;
        @SerializedName("DueDate")
        @Expose
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

    }

    public class FeedType {

        @SerializedName("Id")
        @Expose
        private Integer id;
        @SerializedName("LookUpTypeId")
        @Expose
        private Integer lookUpTypeId;
        @SerializedName("Name")
        @Expose
        private String name;
        @SerializedName("Remarks")
        @Expose
        private String remarks;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getLookUpTypeId() {
            return lookUpTypeId;
        }

        public void setLookUpTypeId(Integer lookUpTypeId) {
            this.lookUpTypeId = lookUpTypeId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }
    }}}