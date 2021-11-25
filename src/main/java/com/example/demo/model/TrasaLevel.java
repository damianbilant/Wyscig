package com.example.demo.model;

public enum TrasaLevel {
    EASY(10,3,2,2),
    MEDIUM(8,7,4,5),
    HARD(20,15,11,12);

    private Integer iloscOdcinkowProstych;
    private Integer iloscZakretow;
    private Integer iloscPodjazdow;
    private Integer iloscZjazdow;

    TrasaLevel(Integer iloscOdcinkowProstych, Integer iloscZakretow, Integer iloscPodjazdow, Integer iloscZjazdow) {
        this.iloscOdcinkowProstych = iloscOdcinkowProstych;
        this.iloscZakretow = iloscZakretow;
        this.iloscPodjazdow = iloscPodjazdow;
        this.iloscZjazdow = iloscZjazdow;

    }

    public Integer getIloscOdcinkowProstych() {
        return iloscOdcinkowProstych;
    }

    public Integer getIloscZakretow() {
        return iloscZakretow;
    }

    public Integer getIloscPodjazdow() {
        return iloscPodjazdow;
    }

    public Integer getIloscZjazdow() {
        return iloscZjazdow;
    }
}
