package com.example.demo.model;

public enum TrasaLevel {
    EASY(10,3,2,2,"łatwy"),
    MEDIUM(8,7,4,5,"średni"),
    HARD(20,15,11,12,"trudny");

    private Integer iloscOdcinkowProstych;
    private Integer iloscZakretow;
    private Integer iloscPodjazdow;
    private Integer iloscZjazdow;
    private String nazwaPoziomuTrasy;

    TrasaLevel(Integer iloscOdcinkowProstych, Integer iloscZakretow, Integer iloscPodjazdow, Integer iloscZjazdow, String nazwaPoziomuTrasy) {
        this.iloscOdcinkowProstych = iloscOdcinkowProstych;
        this.iloscZakretow = iloscZakretow;
        this.iloscPodjazdow = iloscPodjazdow;
        this.iloscZjazdow = iloscZjazdow;
        this.nazwaPoziomuTrasy = nazwaPoziomuTrasy;
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

    public String getNazwaPoziomuTrasy() {
        return nazwaPoziomuTrasy;
    }
}
