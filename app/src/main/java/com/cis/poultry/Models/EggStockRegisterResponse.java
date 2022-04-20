package com.cis.poultry.Models;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EggStockRegisterResponse {

    @SerializedName("ListResult")
    @Expose
    private List<ListResult> listResult = null;
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

    public List<ListResult> getListResult() {
        return listResult;
    }

    public void setListResult(List<ListResult> listResult) {
        this.listResult = listResult;
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



public class ListResult {
        @SerializedName("Date")
        @Expose
        private String date;
        @SerializedName("OpeningStock")
        @Expose
        private Double openingStock;
        @SerializedName("DailyProduction")
        @Expose
        private Double dailyProduction;
        @SerializedName("Total")
        @Expose
        private Double total;
        @SerializedName("Packing")
        @Expose
        private Double packing;
        @SerializedName("Damage")
        @Expose
        private Double damage;
        @SerializedName("FreeIssue")
        @Expose
        private Double freeIssue;
        @SerializedName("ClosingStock")
        @Expose
        private Double closingStock;
        @SerializedName("NECCRate")
        @Expose
        private Double nECCRate;
        @SerializedName("PulpRate")
        @Expose
        private Double pulpRate;
        @SerializedName("CullRate")
        @Expose
        private Double cullRate;
        @SerializedName("BillRate")
        @Expose
        private Double billRate;
        @SerializedName("Remarks")
        @Expose
        private Object remarks;
    private boolean expanded;
        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Double getOpeningStock() {
            return openingStock;
        }

        public void setOpeningStock(Double openingStock) {
            this.openingStock = openingStock;
        }

        public Double getDailyProduction() {
            return dailyProduction;
        }

        public void setDailyProduction(Double dailyProduction) {
            this.dailyProduction = dailyProduction;
        }

        public Double getTotal() {
            return total;
        }

        public void setTotal(Double total) {
            this.total = total;
        }

        public Double getPacking() {
            return packing;
        }

        public void setPacking(Double packing) {
            this.packing = packing;
        }

        public Double getDamage() {
            return damage;
        }

        public void setDamage(Double damage) {
            this.damage = damage;
        }

        public Double getFreeIssue() {
            return freeIssue;
        }

        public void setFreeIssue(Double freeIssue) {
            this.freeIssue = freeIssue;
        }

        public Double getClosingStock() {
            return closingStock;
        }

        public void setClosingStock(Double closingStock) {
            this.closingStock = closingStock;
        }

        public Double getNECCRate() {
            return nECCRate;
        }

        public void setNECCRate(Double nECCRate) {
            this.nECCRate = nECCRate;
        }

        public Double getPulpRate() {
            return pulpRate;
        }

        public void setPulpRate(Double pulpRate) {
            this.pulpRate = pulpRate;
        }

        public Double getCullRate() {
            return cullRate;
        }

        public void setCullRate(Double cullRate) {
            this.cullRate = cullRate;
        }

        public Double getBillRate() {
            return billRate;
        }

        public void setBillRate(Double billRate) {
            this.billRate = billRate;
        }

        public Object getRemarks() {
            return remarks;
        }

        public void setRemarks(Object remarks) {
            this.remarks = remarks;
        }



    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}}