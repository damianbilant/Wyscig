package com.example.demo.model;

public enum Pogoda {
    SUNNY(30, -50,2, "słoneczna"),
    CLOUDY(0,-30,1, "pochmurna"),
    RAINY(-15,30,-1, "deszczowa"),
    SNOWY(-50,75,-2, "śnieżna");

    private Integer zmianaSzybkosciSamochod;
    private Integer zmianaHamowaniaSamochod;
    private Integer zmianaSzybkosciReakcjiKierowcy;
    private String nazwaPogody;

    Pogoda(Integer zmianaSzybkosciSamochod, Integer zmianaHamowaniaSamochod, Integer zmianaSzybkosciReakcjiKierowcy, String nazwaPogody) {
        this.zmianaSzybkosciSamochod = zmianaSzybkosciSamochod;
        this.zmianaHamowaniaSamochod = zmianaHamowaniaSamochod;
        this.zmianaSzybkosciReakcjiKierowcy = zmianaSzybkosciReakcjiKierowcy;
        this.nazwaPogody = nazwaPogody;
    }

    public Integer getZmianaSzybkosciSamochod() {
        return zmianaSzybkosciSamochod;
    }

    public Integer getZmianaHamowaniaSamochod() { return zmianaHamowaniaSamochod; }

    public Integer getZmianaSzybkosciReakcjiKierowcy() {
        return zmianaSzybkosciReakcjiKierowcy;
    }

    public String getNazwaPogody() {
        return nazwaPogody;
    }
}
