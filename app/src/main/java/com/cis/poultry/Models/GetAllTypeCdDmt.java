package com.cis.poultry.Models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllTypeCdDmt {

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

        @SerializedName("TypeCdId")
        @Expose
        private Integer typeCdId;
        @SerializedName("ClassTypeId")
        @Expose
        private Integer classTypeId;
        @SerializedName("NAME")
        @Expose
        private String nAME;
        @SerializedName("Desc")
        @Expose
        private String desc;
        @SerializedName("TableName")
        @Expose
        private String tableName;
        @SerializedName("ColumnName")
        @Expose
        private String columnName;
        @SerializedName("SortOrder")
        @Expose
        private Integer sortOrder;
        @SerializedName("IsActive")
        @Expose
        private Boolean isActive;

        public Integer getTypeCdId() {
            return typeCdId;
        }

        public void setTypeCdId(Integer typeCdId) {
            this.typeCdId = typeCdId;
        }

        public Integer getClassTypeId() {
            return classTypeId;
        }

        public void setClassTypeId(Integer classTypeId) {
            this.classTypeId = classTypeId;
        }

        public String getNAME() {
            return nAME;
        }

        public void setNAME(String nAME) {
            this.nAME = nAME;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getTableName() {
            return tableName;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        public String getColumnName() {
            return columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }

        public Integer getSortOrder() {
            return sortOrder;
        }

        public void setSortOrder(Integer sortOrder) {
            this.sortOrder = sortOrder;
        }

        public Boolean getIsActive() {
            return isActive;
        }

        public void setIsActive(Boolean isActive) {
            this.isActive = isActive;
        }

    }
}