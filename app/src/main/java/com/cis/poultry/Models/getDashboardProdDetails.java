package com.cis.poultry.Models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class getDashboardProdDetails {


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

        @SerializedName("LayerProduction")
        @Expose
        private Double layerProduction;
        @SerializedName("GrowerProduction")
        @Expose
        private Double growerProduction;
        @SerializedName("LayerChicks")
        @Expose
        private Integer layerChicks;
        @SerializedName("GrowerChicks")
        @Expose
        private Integer growerChicks;
        @SerializedName("ChickShedChicks")
        @Expose
        private Integer chickShedChicks;
        @SerializedName("LCPercentage")
        @Expose
        private Double lCPercentage;
        @SerializedName("GCPercentage")
        @Expose
        private Double gCPercentage;
        @SerializedName("LayerEggs")
        @Expose
        private Double layerEggs;
        @SerializedName("GrowerEggs")
        @Expose
        private Double growerEggs;
        @SerializedName("LayerMortality")
        @Expose
        private Integer layerMortality;
        @SerializedName("GrowerMortality")
        @Expose
        private Integer growerMortality;
        @SerializedName("ChickMortality")
        @Expose
        private Integer chickMortality;
        @SerializedName("Date")
        @Expose
        private String date;

        public Double getLayerProduction() {
            return layerProduction;
        }

        public void setLayerProduction(Double layerProduction) {
            this.layerProduction = layerProduction;
        }

        public Double getGrowerProduction() {
            return growerProduction;
        }

        public void setGrowerProduction(Double growerProduction) {
            this.growerProduction = growerProduction;
        }

        public Integer getLayerChicks() {
            return layerChicks;
        }

        public void setLayerChicks(Integer layerChicks) {
            this.layerChicks = layerChicks;
        }

        public Integer getGrowerChicks() {
            return growerChicks;
        }

        public void setGrowerChicks(Integer growerChicks) {
            this.growerChicks = growerChicks;
        }

        public Integer getChickShedChicks() {
            return chickShedChicks;
        }

        public void setChickShedChicks(Integer chickShedChicks) {
            this.chickShedChicks = chickShedChicks;
        }

        public Double getLCPercentage() {
            return lCPercentage;
        }

        public void setLCPercentage(Double lCPercentage) {
            this.lCPercentage = lCPercentage;
        }

        public Double getGCPercentage() {
            return gCPercentage;
        }

        public void setGCPercentage(Double gCPercentage) {
            this.gCPercentage = gCPercentage;
        }

        public Double getLayerEggs() {
            return layerEggs;
        }

        public void setLayerEggs(Double layerEggs) {
            this.layerEggs = layerEggs;
        }

        public Double getGrowerEggs() {
            return growerEggs;
        }

        public void setGrowerEggs(Double growerEggs) {
            this.growerEggs = growerEggs;
        }

        public Integer getLayerMortality() {
            return layerMortality;
        }

        public void setLayerMortality(Integer layerMortality) {
            this.layerMortality = layerMortality;
        }

        public Integer getGrowerMortality() {
            return growerMortality;
        }

        public void setGrowerMortality(Integer growerMortality) {
            this.growerMortality = growerMortality;
        }

        public Integer getChickMortality() {
            return chickMortality;
        }

        public void setChickMortality(Integer chickMortality) {
            this.chickMortality = chickMortality;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }


    }
}
