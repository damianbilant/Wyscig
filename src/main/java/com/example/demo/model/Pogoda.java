package com.example.demo.model;

import java.util.Random;

public enum Pogoda {
    SUNNY(0,24,30, -50,2, "Słoneczna"),
    CLOUDY(25,49,0,-30,1, "Pochmurna"),
    RAINY(50,74,-15,30,-1, "Deszczowa"),
    SNOWY(75,100,-50,75,-2, "Śnieżna");

    private Integer minWartosc;
    private Integer maxWartosc;
    private Integer zmianaSzybkosci;
    private Integer zmianaHamowania;
    private Integer zmianaSzybkosciReakcji;
    private  String nazwaPogody;


    Pogoda(Integer minWartosc, Integer maxWartosc, Integer zmianaSzybkosci, Integer zmianaHamowania, Integer zmianaSzybkosciReakcji, String nazwaPogody) {
        this.minWartosc = minWartosc;
        this.maxWartosc = maxWartosc;
        this.zmianaSzybkosci = zmianaSzybkosci;
        this.zmianaHamowania = zmianaHamowania;
        this.zmianaSzybkosciReakcji = zmianaSzybkosciReakcji;
        this.nazwaPogody = nazwaPogody;
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

    public String getNazwaPogody() {
        return nazwaPogody;
    }
}
