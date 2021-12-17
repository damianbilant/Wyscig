package com.example.demo.model;

public abstract class Samochod {
    private TypSamochodu typSamochodu;
    private Integer ciezar;
    private Integer skutecznoscHamowania;
    private Integer szybkosc;

    public Samochod(TypSamochodu typSamochodu, Integer ciezar, Integer skutecznoscHamowania, Integer szybkosc) {
        this.typSamochodu = typSamochodu;
        this.ciezar = ciezar;
        this.skutecznoscHamowania = skutecznoscHamowania;
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

    public Integer getSkutecznoscHamowania() {
        return skutecznoscHamowania;
    }

    public Integer getSzybkosc() {
        return szybkosc;
    }

    public void setSzybkosc(Integer szybkosc) {
        this.szybkosc = szybkosc;
    }


    public Integer zamianaJednostek (Samochod samochod){
        Double x = 1000.0 / 3600.0;
        Double zmienionaSzybkosc = samochod.getSzybkosc() * x;
        int zaokraglonaSzybkosc = zmienionaSzybkosc.intValue();
        return  zaokraglonaSzybkosc;
    }

    public Integer wyliczenieDrogiHamowania (Samochod samochod){
        Integer y = (samochod.getCiezar() * zamianaJednostek(samochod));
        Integer drogaHamowania = y / samochod.getSkutecznoscHamowania();
        setDrogaHamowania(drogaHamowania);
        System.out.println("Droga hamowania samochodu to: " + drogaHamowania);
        return drogaHamowania;
    }

    private   void aktualizacjaSzybkosciOdPogody(Pogoda pogoda){
        pogoda.getZmianaSzybkosciSamochod();
        getSzybkosc();
        Integer zmianaSzybkosci = getSzybkosc() + pogoda.getZmianaSzybkosciSamochod();
        setSzybkosc(zmianaSzybkosci);
        System.out.println("Ze względu na pogodę szybkość samochodu " + getTypSamochodu() + " to: " + zmianaSzybkosci);

    }


    private   void aktualizacjaHamowaniaOdPogody(Pogoda pogoda){
        pogoda.getZmianaHamowaniaSamochod();
        getDrogaHamowania();
        Integer zmianaHamowania = getDrogaHamowania() + pogoda.getZmianaHamowaniaSamochod();
        setDrogaHamowania(zmianaHamowania);
        if (getDrogaHamowania() < 30 ) {
            setDrogaHamowania(30);
        }
        System.out.println("Ze względu na pogodę droga hamowania samochodu " + getTypSamochodu() + " to: " + getDrogaHamowania());
    }

    public void aktualizacjaSzybkosciIhamowania(Pogoda pogoda){
        aktualizacjaHamowaniaOdPogody(pogoda);
        aktualizacjaSzybkosciOdPogody(pogoda);

    }

    @Override
    public String toString() {
    return "typ samochodu : " + getTypSamochodu() + ", ciężar samochodu : " + getCiezar() + " kg" + ", skuteczność hamowania samochodu : " + getSkutecznoscHamowania() + ", szybkość max samochodu : " + getSzybkosc() + " km/h";
    }
}