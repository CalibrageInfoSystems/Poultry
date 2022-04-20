package com.cis.poultry.Models;

public class productdata {
    private String shed;
    private String morality;
    private String availablebirds;
    private String eggs;
    private String products;

    public productdata(String shed, String morality, String availablebirds, String eggs, String products) {
        this.shed = shed;
        this.morality = morality;
        this.availablebirds = availablebirds;
        this.eggs = eggs;
        this.products = products;
    }

    public String getMorality() {
        return morality;
    }

    public void setMorality(String morality) {
        this.morality = morality;
    }

    public String getAvailablebirds() {
        return availablebirds;
    }

    public void setAvailablebirds(String availablebirds) {
        this.availablebirds = availablebirds;
    }

    public String getEggs() {
        return eggs;
    }

    public void setEggs(String eggs) {
        this.eggs = eggs;
    }

    public String getShed() {
        return shed;
    }

    public void setShed(String shed) {
        this.shed = shed;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }
}
