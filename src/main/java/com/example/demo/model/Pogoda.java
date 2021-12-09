package com.example.demo.model;

import java.util.Random;

public enum Pogoda {
    SUNNY(30, -50,2, "słoneczna"),
    CLOUDY(0,-30,1, "pochmurna"),
    RAINY(-15,30,-1, "deszczowa"),
    SNOWY(-50,75,-2, "śnieżna");


    private Integer zmianaSzybkosci;
    private Integer zmianaHamowania;
    private Integer zmianaSzybkosciReakcji;
    private String nazwaPogody;


    Pogoda(Integer zmianaSzybkosci, Integer zmianaHamowania, Integer zmianaSzybkosciReakcji, String nazwaPogody) {
        this.zmianaSzybkosci = zmianaSzybkosci;
        this.zmianaHamowania = zmianaHamowania;
        this.zmianaSzybkosciReakcji = zmianaSzybkosciReakcji;
        this.nazwaPogody = nazwaPogody;
    }



    public Integer getZmianaSzybkosci() {
        return zmianaSzybkosci;
    }

    public Integer getZmianaHamowania() { return zmianaHamowania; }

    public Integer getZmianaSzybkosciReakcji() {
        return zmianaSzybkosciReakcji;
    }

    public String getNazwaPogody() {
        return nazwaPogody;
    }
}
