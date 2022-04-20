package com.cis.poultry.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VisitLogobject {

    @SerializedName("visitedDate")
    @Expose
    private String visitedDate;
    @SerializedName("FarmId")
    @Expose
    private Integer farmId;

    public String getVisitedDate() {
        return visitedDate;
    }

    public void setVisitedDate(String visitedDate) {
        this.visitedDate = visitedDate;
    }

    public Integer getFarmId() {
        return farmId;
    }

    public void setFarmId(Integer farmId) {
        this.farmId = farmId;
    }

}