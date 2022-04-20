package com.cis.poultry.Models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ManageRateDetailRequestObject {

    @SerializedName("FarmId")
    @Expose
    private Integer farmId;
    @SerializedName("date")
    @Expose
    private String date;

    public Integer getFarmId() {
        return farmId;
    }

    public void setFarmId(Integer farmId) {
        this.farmId = farmId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}