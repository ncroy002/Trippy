package com.trippy.back.entities;

public class FlightPriceResult {
    int lowestPrice;
    String url;

    public FlightPriceResult(int lowestPrice, String url) {
        this.lowestPrice = lowestPrice;
        this.url = url;
    }

    public int getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(int lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
