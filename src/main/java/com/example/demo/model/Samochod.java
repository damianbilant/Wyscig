package com.example.demo.model;


import com.example.demo.utils.Utils;

public abstract class Samochod {
    private TypSamochodu typSamochodu;
    private Integer ciezar;
    private Integer skutecznoscHamowania;
    private Integer szybkosc;
    private Integer wytrzymaloscSamochodu;

    public Samochod(TypSamochodu typSamochodu, Integer ciezar, Integer skutecznoscHamowania, Integer szybkosc, Integer wytrzymaloscSamochodu) {
        this.typSamochodu = typSamochodu;
        this.ciezar = ciezar;
        this.skutecznoscHamowania = skutecznoscHamowania;
        this.szybkosc = szybkosc;
        this.wytrzymaloscSamochodu = wytrzymaloscSamochodu;
    }

    private Integer drogaHamowania;
    private Double czasPrzejazdu = 0.0;
    private Integer przejechanyDystans = 0;
    private Integer licznikWzrostuPredkosci = 0;
    private Integer licznikSpadkuPredkosci = 0;


    public Integer getLicznikWzrostuPredkosci() {
        return licznikWzrostuPredkosci;
    }

    public void setLicznikWzrostuPredkosci(Integer licznikWzrostuPredkosci) {
        this.licznikWzrostuPredkosci = licznikWzrostuPredkosci;
    }

    public Integer getLicznikSpadkuPredkosci() {
        return licznikSpadkuPredkosci;
    }

    public void setLicznikSpadkuPredkosci(Integer licznikSpadkuPredkosci) {
        this.licznikSpadkuPredkosci = licznikSpadkuPredkosci;
    }

    public Double getCzasPrzejazdu() {
        return czasPrzejazdu;
    }

    public Integer getPrzejechanyDystans() {
        return przejechanyDystans;
    }

    public Integer getDrogaHamowania() {
        return drogaHamowania;
    }

    public void setDrogaHamowania(Integer drogaHamowania) {
        this.drogaHamowania = drogaHamowania;
    }

    public TypSamochodu getTypSamochodu() {
        return typSamochodu;
    }

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

    public Integer getWytrzymaloscSamochodu() {
        return wytrzymaloscSamochodu;
    }

    public void setWytrzymaloscSamochodu(Integer wytrzymaloscSamochodu) {
        this.wytrzymaloscSamochodu = wytrzymaloscSamochodu;
    }

    public Integer zamianaJednostek(Samochod samochod) {
        Double x = 1000.0 / 3600.0;
        Double zmienionaSzybkosc = samochod.getSzybkosc() * x;
        int zaokraglonaSzybkosc = zmienionaSzybkosc.intValue();
        return zaokraglonaSzybkosc;
    }

    public Integer wyliczenieDrogiHamowania(Samochod samochod) {
        Integer y = (samochod.getCiezar() * zamianaJednostek(samochod));
        Integer drogaHamowania = y / samochod.getSkutecznoscHamowania();
        setDrogaHamowania(drogaHamowania);
        System.out.println("Droga hamowania samochodu to: " + drogaHamowania);
        return drogaHamowania;
    }


    private void aktualizacjaSzybkosciOdPogody(Pogoda pogoda) {
        Integer zmianaSzybkosci = getSzybkosc() + pogoda.getZmianaSzybkosciSamochod();
        setSzybkosc(zmianaSzybkosci);
        System.out.println("Ze względu na pogodę szybkość samochodu " + getTypSamochodu() + " to: " + zmianaSzybkosci);

    }


    private void aktualizacjaHamowaniaOdPogody(Pogoda pogoda) {
        Integer zmianaHamowania = getDrogaHamowania() + pogoda.getZmianaHamowaniaSamochod();
        setDrogaHamowania(zmianaHamowania);
        if (getDrogaHamowania() < 30) {
            setDrogaHamowania(30);
        }
        System.out.println("Ze względu na pogodę droga hamowania samochodu " + getTypSamochodu() + " to: " + getDrogaHamowania());
    }

    public void aktualizacjaSzybkosciIhamowania(Pogoda pogoda) {
        aktualizacjaHamowaniaOdPogody(pogoda);
        aktualizacjaSzybkosciOdPogody(pogoda);

    }

    public int aktualizacjaWytrzymalosci(int minWytrzymaloscSamochodu, int maxWytrzymaloscSamochodu) {
        Integer zmniejszeniePunktowWytrzymalosciSamochodu = Utils.losuj(minWytrzymaloscSamochodu, maxWytrzymaloscSamochodu);
        Integer zmniejszenieWytrzymalosciSamochodu = getWytrzymaloscSamochodu() - zmniejszeniePunktowWytrzymalosciSamochodu;
            setWytrzymaloscSamochodu(zmniejszenieWytrzymalosciSamochodu);
        return zmniejszeniePunktowWytrzymalosciSamochodu;
    }

    public Double szybkoscPrzejazduOdcinka(Odcinek odcinek) {
        double s = Double.valueOf(odcinek.getDlugoscOdcinka());
        double v = Double.valueOf(getSzybkosc());
        double czasPrzejazduOdcinka = (s / v)* 60;
        czasPrzejazduOdcinka *= 10; //czasPrzejazduOdcinka = czasPrzejazduOdcinka * 10;
        czasPrzejazduOdcinka = Math.round(czasPrzejazduOdcinka);
        czasPrzejazduOdcinka /= 10; //czasPrzejazduOdcinka = czasPrzejazduOdcinka / 10;
        return czasPrzejazduOdcinka;
    }

    public Double czasPrzejazduTotal(){
        double totalCzasPrzejazdu = getCzasPrzejazdu();
        totalCzasPrzejazdu *= 10;
        totalCzasPrzejazdu = Math.round(totalCzasPrzejazdu);
        totalCzasPrzejazdu /= 10;

        return totalCzasPrzejazdu;
    }

    public void dodajCzasPrzejazduOdcinka(Double czasPrzejazduOdcinka) {
        czasPrzejazdu += czasPrzejazduOdcinka;
        //czasPrzejazdu = czasPrzejazduOdcinka + czasPrzejazdu;
        System.out.println("Czas total " + czasPrzejazduTotal());
        System.out.println("Czas odcinka " + czasPrzejazduOdcinka);
    }

    public void resetCzasuPrzejazdu() {

    }

    public void dodajPrzejechanyDystans(Odcinek odcinek) {
        przejechanyDystans += odcinek.getDlugoscOdcinka();
        System.out.println("Dystans total " + getPrzejechanyDystans());
        System.out.println("Dystans odcinka " + odcinek.getDlugoscOdcinka());
    }

    public void resetPrzejechanegoDystansu() {

    }

    public void limitZmianyPredkosciIzmianaPredkosci(Integer nowaszybkosc, TypOdcinka obecnyTypOdcinka, Kierowca kierowca) {
        if (getLicznikWzrostuPredkosci() == 0 || getLicznikWzrostuPredkosci() == 1) {
            if (getSzybkosc() < nowaszybkosc) {
                setLicznikWzrostuPredkosci(getLicznikWzrostuPredkosci()+1);
                setSzybkosc(nowaszybkosc);
                System.out.println("Na odcinku " + obecnyTypOdcinka + " kierowca " + kierowca.getTypKierowcy() + " jadący "
                        + getTypSamochodu() + " przyspieszył do " + nowaszybkosc + " km/h");
                if (getLicznikSpadkuPredkosci() > 0) {
                    setLicznikSpadkuPredkosci(getLicznikSpadkuPredkosci()-1);
                }
            }
        }
        if (getLicznikSpadkuPredkosci() == 0 || getLicznikSpadkuPredkosci() == 1) {
            if (getSzybkosc() > nowaszybkosc) {
                setLicznikSpadkuPredkosci(getLicznikSpadkuPredkosci()+1);
                setSzybkosc(nowaszybkosc);
                System.out.println("Na odcinku " + obecnyTypOdcinka + " kierowca " + kierowca.getTypKierowcy() + " jadący "
                        + getTypSamochodu() + " zwolnil do " + nowaszybkosc + " km/h");
                if (getLicznikWzrostuPredkosci() > 0) {
                    setLicznikWzrostuPredkosci(getLicznikWzrostuPredkosci()-1);
                }
            }
        }
    }



  @Override
    public String toString() {
        return "typ samochodu : " + getTypSamochodu() + ", ciężar samochodu : " + getCiezar() + " kg" + ", skuteczność hamowania samochodu : " + getSkutecznoscHamowania() + ", szybkość max samochodu : " + getSzybkosc() + " km/h";
    }
}