package com.cis.poultry.Models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class getDbPaymentsPackingAndProd {

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

    public class DailyStock {

        @SerializedName("Date")
        @Expose
        private String date;
        @SerializedName("Production")
        @Expose
        private double production;
        @SerializedName("OpeningStock")
        @Expose
        private Double openingStock;
        @SerializedName("Total")
        @Expose
        private Double total;
        @SerializedName("Packing")
        @Expose
        private Double Packing;
        @SerializedName("ClosingBalance")
        @Expose
        private Double ClosingBalance;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public double getProduction() {
            return production;
        }

        public void setProduction(double production) {
            this.production = production;
        }

        public Double getOpeningStock() {
            return openingStock;
        }

        public void setOpeningStock(Double openingStock) {
            this.openingStock = openingStock;
        }

        public Double getTotal() {
            return total;
        }

        public void setTotal(Double total) {
            this.total = total;
        }

        public Double getPacking() {
            return Packing;
        }

        public void setPacking(Double packing) {
            Packing = packing;
        }

        public Double getClosingBalance() {
            return ClosingBalance;
        }

        public void setClosingBalance(Double closingBalance) {
            ClosingBalance = closingBalance;
        }
    }

    public class InwardPayment {

        @SerializedName("InwardPayment")
        @Expose
        private Double inwardPayment;

        public Double getInwardPayment() {
            return inwardPayment;
        }

        public void setInwardPayment(Double inwardPayment) {
            this.inwardPayment = inwardPayment;
        }

    }

    public class OutwardPayment {

        @SerializedName("OutwardPayment")
        @Expose
        private Double outwardPayment;

        public Double getOutwardPayment() {
            return outwardPayment;
        }

        public void setOutwardPayment(Double outwardPayment) {
            this.outwardPayment = outwardPayment;
        }

    }


    public class Result {

        @SerializedName("dailyStock")
        @Expose
        private List<DailyStock> dailyStock = null;
        @SerializedName("sales")
        @Expose
        private List<Sale> sales = null;
        @SerializedName("OutwardPayment")
        @Expose
        private OutwardPayment outwardPayment;
        @SerializedName("InwardPayment")
        @Expose
        private InwardPayment inwardPayment;

        public List<DailyStock> getDailyStock() {
            return dailyStock;
        }

        public void setDailyStock(List<DailyStock> dailyStock) {
            this.dailyStock = dailyStock;
        }

        public List<Sale> getSales() {
            return sales;
        }

        public void setSales(List<Sale> sales) {
            this.sales = sales;
        }

        public OutwardPayment getOutwardPayment() {
            return outwardPayment;
        }

        public void setOutwardPayment(OutwardPayment outwardPayment) {
            this.outwardPayment = outwardPayment;
        }

        public InwardPayment getInwardPayment() {
            return inwardPayment;
        }

        public void setInwardPayment(InwardPayment inwardPayment) {
            this.inwardPayment = inwardPayment;
        }

    }

    public class Sale {

        @SerializedName("Name")
        @Expose
        private String name;
        @SerializedName("PackedBoxes")
        @Expose
        private Double packedBoxes;
        @SerializedName("PackedEggs")
        @Expose
        private Integer packedEggs;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getPackedBoxes() {
            return packedBoxes;
        }

        public void setPackedBoxes(Double packedBoxes) {
            this.packedBoxes = packedBoxes;
        }

        public Integer getPackedEggs() {
            return packedEggs;
        }

        public void setPackedEggs(Integer packedEggs) {
            this.packedEggs = packedEggs;
        }

    }

}
