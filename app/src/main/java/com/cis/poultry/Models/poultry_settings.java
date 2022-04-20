package com.cis.poultry.Models;

public class poultry_settings {
    String name;
    int image;
    public poultry_settings(String name, int image) {
        this.name = name;
        this.image=image;
    }

    public String getName() {
        return name;
    }

    public Integer getImage() {
        return image;
    }


}
