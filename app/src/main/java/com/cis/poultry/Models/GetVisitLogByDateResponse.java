package com.cis.poultry.Models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetVisitLogByDateResponse {

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

    @SerializedName("shedTypes")
    @Expose
    private List<ShedType> shedTypes = null;
    @SerializedName("visitLogDetails")
    @Expose
    private List<VisitLogDetail> visitLogDetails = null;
    @SerializedName("visitLogSummary")
    @Expose
    private List<VisitLogSummary> visitLogSummary = null;

    public List<ShedType> getShedTypes() {
        return shedTypes;
    }

    public void setShedTypes(List<ShedType> shedTypes) {
        this.shedTypes = shedTypes;
    }

    public List<VisitLogDetail> getVisitLogDetails() {
        return visitLogDetails;
    }

    public void setVisitLogDetails(List<VisitLogDetail> visitLogDetails) {
        this.visitLogDetails = visitLogDetails;
    }

    public List<VisitLogSummary> getVisitLogSummary() {
        return visitLogSummary;
    }

    public void setVisitLogSummary(List<VisitLogSummary> visitLogSummary) {
        this.visitLogSummary = visitLogSummary;
    }



public class ShedType {

    @SerializedName("ShedTypeId")
    @Expose
    private Integer shedTypeId;
    @SerializedName("ShedType")
    @Expose
    private String shedType;
    @SerializedName("LogDate")
    @Expose
    private String LogDate;

    public Integer getShedTypeId() {
        return shedTypeId;
    }

    public void setShedTypeId(Integer shedTypeId) {
        this.shedTypeId = shedTypeId;
    }

    public String getShedType() {
        return shedType;
    }

    public void setShedType(String shedType) {
        this.shedType = shedType;
    }

    public String getLogDate() {
        return LogDate;
    }

    public void setLogDate(String logDate) {
        LogDate = logDate;
    }
}

public class VisitLogDetail {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("ShedId")
    @Expose
    private Integer shedId;
    @SerializedName("ShedName")
    @Expose
    private String shedName;
    @SerializedName("FarmId")
    @Expose
    private Integer farmId;
    @SerializedName("FarmName")
    @Expose
    private String farmName;
    @SerializedName("BatchName")
    @Expose
    private String batchName;
    @SerializedName("BatchId")
    @Expose
    private Integer batchId;
    @SerializedName("AgeinWeeks")
    @Expose
    private Integer ageinWeeks;
    @SerializedName("AgeinDays")
    @Expose
    private Integer ageinDays;
    @SerializedName("OpeningBirds")
    @Expose
    private Integer openingBirds;
    @SerializedName("Died")
    @Expose
    private Integer died;
    @SerializedName("Hurt")
    @Expose
    private Integer hurt;
    @SerializedName("RemainingBirds")
    @Expose
    private Integer remainingBirds;
    @SerializedName("Feed")
    @Expose
    private Integer feed;
    @SerializedName("DamagedEggs")
    @Expose
    private Object damagedEggs;
    @SerializedName("NumberofEggs")
    @Expose
    private Object numberofEggs;
    @SerializedName("TotalEggs")
    @Expose
    private Object totalEggs;
    @SerializedName("Percentage")
    @Expose
    private Object percentage;
    @SerializedName("StatusType")
    @Expose
    private String statusType;
    @SerializedName("StatusTypeId")
    @Expose
    private Integer statusTypeId;
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
    @SerializedName("ShedTypeId")
    @Expose
    private Integer shedTypeId;
    @SerializedName("ShedType")
    @Expose
    private String shedType;
    @SerializedName("Remarks")
    @Expose
    private Object remarks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShedId() {
        return shedId;
    }

    public void setShedId(Integer shedId) {
        this.shedId = shedId;
    }

    public String getShedName() {
        return shedName;
    }

    public void setShedName(String shedName) {
        this.shedName = shedName;
    }

    public Integer getFarmId() {
        return farmId;
    }

    public void setFarmId(Integer farmId) {
        this.farmId = farmId;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public Integer getAgeinWeeks() {
        return ageinWeeks;
    }

    public void setAgeinWeeks(Integer ageinWeeks) {
        this.ageinWeeks = ageinWeeks;
    }

    public Integer getAgeinDays() {
        return ageinDays;
    }

    public void setAgeinDays(Integer ageinDays) {
        this.ageinDays = ageinDays;
    }

    public Integer getOpeningBirds() {
        return openingBirds;
    }

    public void setOpeningBirds(Integer openingBirds) {
        this.openingBirds = openingBirds;
    }

    public Integer getDied() {
        return died;
    }

    public void setDied(Integer died) {
        this.died = died;
    }

    public Integer getHurt() {
        return hurt;
    }

    public void setHurt(Integer hurt) {
        this.hurt = hurt;
    }

    public Integer getRemainingBirds() {
        return remainingBirds;
    }

    public void setRemainingBirds(Integer remainingBirds) {
        this.remainingBirds = remainingBirds;
    }

    public Integer getFeed() {
        return feed;
    }

    public void setFeed(Integer feed) {
        this.feed = feed;
    }

    public Object getDamagedEggs() {
        return damagedEggs;
    }

    public void setDamagedEggs(Object damagedEggs) {
        this.damagedEggs = damagedEggs;
    }

    public Object getNumberofEggs() {
        return numberofEggs;
    }

    public void setNumberofEggs(Object numberofEggs) {
        this.numberofEggs = numberofEggs;
    }

    public Object getTotalEggs() {
        return totalEggs;
    }

    public void setTotalEggs(Object totalEggs) {
        this.totalEggs = totalEggs;
    }

    public Object getPercentage() {
        return percentage;
    }

    public void setPercentage(Object percentage) {
        this.percentage = percentage;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public Integer getStatusTypeId() {
        return statusTypeId;
    }

    public void setStatusTypeId(Integer statusTypeId) {
        this.statusTypeId = statusTypeId;
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

    public Integer getShedTypeId() {
        return shedTypeId;
    }

    public void setShedTypeId(Integer shedTypeId) {
        this.shedTypeId = shedTypeId;
    }

    public String getShedType() {
        return shedType;
    }

    public void setShedType(String shedType) {
        this.shedType = shedType;
    }

    public Object getRemarks() {
        return remarks;
    }

    public void setRemarks(Object remarks) {
        this.remarks = remarks;
    }

}
public class VisitLogSummary {

    @SerializedName("ShedTypeId")
    @Expose
    private Integer shedTypeId;
    @SerializedName("StatusType")
    @Expose
    private String statusType;
    @SerializedName("OpeningBirds")
    @Expose
    private Integer openingBirds;
    @SerializedName("Died")
    @Expose
    private Integer died;
    @SerializedName("Hurt")
    @Expose
    private Integer hurt;
    @SerializedName("RemainingBirds")
    @Expose
    private Integer remainingBirds;
    @SerializedName("Feed")
    @Expose
    private Integer feed;
    @SerializedName("DamagedEggs")
    @Expose
    private Integer damagedEggs;
    @SerializedName("NumberofEggs")
    @Expose
    private Integer numberofEggs;
    @SerializedName("TotalEggs")
    @Expose
    private Integer totalEggs;
    @SerializedName("Percentage")
    @Expose
    private Double percentage;
    @SerializedName("Cartons")
    @Expose
    private Double Cartons;

    public Integer getShedTypeId() {
        return shedTypeId;
    }

    public void setShedTypeId(Integer shedTypeId) {
        this.shedTypeId = shedTypeId;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public Integer getOpeningBirds() {
        return openingBirds;
    }

    public void setOpeningBirds(Integer openingBirds) {
        this.openingBirds = openingBirds;
    }

    public Integer getDied() {
        return died;
    }

    public void setDied(Integer died) {
        this.died = died;
    }

    public Integer getHurt() {
        return hurt;
    }

    public void setHurt(Integer hurt) {
        this.hurt = hurt;
    }

    public Integer getRemainingBirds() {
        return remainingBirds;
    }

    public void setRemainingBirds(Integer remainingBirds) {
        this.remainingBirds = remainingBirds;
    }

    public Integer getFeed() {
        return feed;
    }

    public void setFeed(Integer feed) {
        this.feed = feed;
    }

    public Integer getDamagedEggs() {
        return damagedEggs;
    }

    public void setDamagedEggs(Integer damagedEggs) {
        this.damagedEggs = damagedEggs;
    }

    public Integer getNumberofEggs() {
        return numberofEggs;
    }

    public void setNumberofEggs(Integer numberofEggs) {
        this.numberofEggs = numberofEggs;
    }

    public Integer getTotalEggs() {
        return totalEggs;
    }

    public void setTotalEggs(Integer totalEggs) {
        this.totalEggs = totalEggs;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public Double getCartons() {
        return Cartons;
    }

    public void setCartons(Double cartons) {
        Cartons = cartons;
    }
}}}