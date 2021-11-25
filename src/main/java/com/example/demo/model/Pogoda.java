package com.example.demo.model;

import java.util.Random;

public enum Pogoda {
    SUNNY(0,24,30, -50,2),
    CLOUDY(25,49,0,-30,1),
    RAINY(50,74,-15,30,-1),
    SNOWY(75,100,-50,75,-2);

    private Integer minWartosc;
    private Integer maxWartosc;
    private Integer zmianaSzybkosci;
    private Integer zmianaHamowania;
    private Integer zmianaSzybkosciReakcji;


    Pogoda(Integer minWartosc, Integer maxWartosc, Integer zmianaSzybkosci, Integer zmianaHamowania, Integer zmianaSzybkosciReakcji) {
        this.minWartosc = minWartosc;
        this.maxWartosc = maxWartosc;
        this.zmianaSzybkosci = zmianaSzybkosci;
        this.zmianaHamowania = zmianaHamowania;
        this.zmianaSzybkosciReakcji = zmianaSzybkosciReakcji;
    }

    public Integer getMinWartosc() {
        return minWartosc;
    }

    public Integer getMaxWartosc() {
        return maxWartosc;
    }

    public Integer getZmianaSzybkosci() {
        return zmianaSzybkosci;
    }

    public Integer getZmianaHamowania() {
        return zmianaHamowania;
    }

    public Integer getZmianaSzybkosciReakcji() {
        return zmianaSzybkosciReakcji;
    }
}
