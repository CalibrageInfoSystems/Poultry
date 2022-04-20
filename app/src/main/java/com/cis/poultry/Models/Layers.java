package com.cis.poultry.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Layers {

    private Integer id;
    private Integer shedId;
    private String shedName;
    private Integer farmId;
    private String farmName;
    private String batchName;
    private Integer batchId;
    private Integer ageinWeeks;
    private Integer ageinDays;
    private Integer openingBirds;
    private Integer died;
    private Integer hurt;
    private Integer remainingBirds;
    private Integer feed;
    private Object damagedEggs;
    private Object numberofEggs;
    private Object totalEggs;
    private Object percentage;
    private String statusType;
    private Integer statusTypeId;
    private boolean expanded;

    public Layers(Integer id, Integer shedId, String shedName, Integer farmId, String farmName, String batchName, Integer batchId, Integer ageinWeeks, Integer ageinDays, Integer openingBirds, Integer died, Integer hurt, Integer remainingBirds, Integer feed, Object damagedEggs, Object numberofEggs, Object totalEggs, Object percentage, String statusType, Integer statusTypeId, Integer createdByUserId, String createdDate, Integer updatedByUserId, String updatedDate, Integer shedTypeId, String shedType, Object remarks) {
        this.id = id;
        this.shedId = shedId;
        this.shedName = shedName;
        this.farmId = farmId;
        this.farmName = farmName;
        this.batchName = batchName;
        this.batchId = batchId;
        this.ageinWeeks = ageinWeeks;
        this.ageinDays = ageinDays;
        this.openingBirds = openingBirds;
        this.died = died;
        this.hurt = hurt;
        this.remainingBirds = remainingBirds;
        this.feed = feed;
        this.damagedEggs = damagedEggs;
        this.numberofEggs = numberofEggs;
        this.totalEggs = totalEggs;
        this.percentage = percentage;
        this.statusType = statusType;
        this.statusTypeId = statusTypeId;
        this.createdByUserId = createdByUserId;
        this.createdDate = createdDate;
        this.updatedByUserId = updatedByUserId;
        this.updatedDate = updatedDate;
        this.shedTypeId = shedTypeId;
        this.shedType = shedType;
        this.remarks = remarks;
    }

    private Integer createdByUserId;
    private String createdDate;
    private Integer updatedByUserId;
    private String updatedDate;
    private Integer shedTypeId;
    private String shedType;
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

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public boolean isExpanded() {
        return expanded;
    }
}

