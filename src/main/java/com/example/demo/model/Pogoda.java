package com.example.demo.model;

public enum Pogoda {
    SUNNY(0,24),
    SNOWY(25,49),
    RAINY(50,74),
    CLOUDY(75,100);

    private Integer minWartosc;
    private Integer maxWartosc;

    Pogoda(Integer minWartosc, Integer maxWartosc) {
        this.minWartosc = minWartosc;
        this.maxWartosc = maxWartosc;
    }

    public Integer getMinWartosc() {
        return minWartosc;
    }

    public Integer getMaxWartosc() {
        return maxWartosc;
    }
}
