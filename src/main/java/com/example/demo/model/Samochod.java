package com.example.demo.model;

public abstract class Samochod {
    private TypSamochodu typSamochodu;
    private Integer ciezar;
    private Integer hamowanie;
    private Integer szybkosc;

    public Samochod(TypSamochodu typSamochodu, Integer ciezar, Integer hamowanie, Integer szybkosc) {
        this.typSamochodu = typSamochodu;
        this.ciezar = ciezar;
        this.hamowanie = hamowanie;
        this.szybkosc = szybkosc;
    }
    private Integer drogaHamowania;

    public Integer getDrogaHamowania() {
        return drogaHamowania;
    }

    public void setDrogaHamowania(Integer drogaHamowania) {
        this.drogaHamowania = drogaHamowania;
    }

    public TypSamochodu getTypSamochodu() { return  typSamochodu; }

    public Integer getCiezar() {
        return ciezar;
    }

    public Integer getHamowanie() {
        return hamowanie;
    }

    public Integer getSzybkosc() {
        return szybkosc;
    }



    public Integer zamianaJednostek (Samochod samochod){
        Double x = 1000.0 / 3600.0;
        Double zmienionaSzybkosc = samochod.getSzybkosc() * x;
        int zaokraglonaSzybkosc = zmienionaSzybkosc.intValue();
        return  zaokraglonaSzybkosc;
    }

    public Integer wyliczenieDrogiHamowania (Samochod samochod){
        Integer y = (samochod.getCiezar() * zamianaJednostek(samochod));
        Integer drogaHamowania = y / samochod.getHamowanie();
        setDrogaHamowania(drogaHamowania);
        System.out.println("Droga hamowania samochodu to: " + samochod.wyliczenieDrogiHamowania(samochod));
        return drogaHamowania;
    }


    @Override
    public String toString() {
    return "typ samochodu : " + getTypSamochodu() + ", ciężar samochodu : " + getCiezar() + " kg" + ", skuteczność hamowania samochodu : " + getHamowanie() + ", szybkość max samochodu : " + getSzybkosc() + " km/h";
    }
}