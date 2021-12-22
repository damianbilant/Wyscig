package com.example.demo.model;



public abstract class Kierowca {
    private TypKierowcy typKierowcy;
    private Integer znajomoscTrasy;
    private Integer stanTrzezwosci;
    private Integer szybkoscReakcji;
    private Integer zycieKierowcy;


    public Kierowca(TypKierowcy typKierowcy, Integer znajomoscTrasy, Integer stanTrzezwosci, Integer szybkoscReakcji, Integer zycieKierowcy) {
        this.typKierowcy = typKierowcy;
        this.znajomoscTrasy = znajomoscTrasy;
        this.stanTrzezwosci = stanTrzezwosci;
        this.szybkoscReakcji = szybkoscReakcji;
        this.zycieKierowcy = zycieKierowcy;
    }

    public TypKierowcy getTypKierowcy() {
        return typKierowcy;
    }

    public Integer getZnajomoscTrasy() {
        return znajomoscTrasy;
    }

    public Integer getStanTrzezwosci() {
        return stanTrzezwosci;
    }

    public Integer getSzybkoscReakcji() {
        return szybkoscReakcji;
    }

    public Integer getZycieKierowcy() {
        return  zycieKierowcy; }

    public void setZycieKierowcy(Integer zycieKierowcy) {
        this.zycieKierowcy = zycieKierowcy;
    }

    public void setSzybkoscReakcji(Integer szybkoscReakcji) {
        this.szybkoscReakcji = szybkoscReakcji;
    }

    public boolean czyTrzezwy() {
        boolean trzezwosc = true;
        if (getStanTrzezwosci() >= 4) {
            trzezwosc = false;
            zmniejszenieReakcji();
        }
        return trzezwosc;
    }

    private void zmniejszenieReakcji() {
        if (getSzybkoscReakcji() <= 2) {
            setSzybkoscReakcji(0);
        }
        setSzybkoscReakcji(getSzybkoscReakcji() - 3);
        System.out.println("Kierowca " + getTypKierowcy() + " jest nietrzeźwy więc ma wolniejszą reakcję równą " + getSzybkoscReakcji());
    }

    public void aktualizacjaReakcjiOdPogody( Pogoda pogoda) {
        Integer zmianaSzybkosciReakcji = getSzybkoscReakcji() + pogoda.getZmianaSzybkosciReakcjiKierowcy();
        setSzybkoscReakcji(zmianaSzybkosciReakcji);
        if (getSzybkoscReakcji() < 0){
            setSzybkoscReakcji(0);
        }
        System.out.println("Ze względu na pogodę reakcja kierowcy " + getTypKierowcy() + " to: " + getSzybkoscReakcji());
    }

    public void aktualizacjaZycia (Integer punktyZyciaDoZmniejszenia) {
        Integer zmniejszeniePunktowZycia = getZycieKierowcy() - punktyZyciaDoZmniejszenia;
        setZycieKierowcy(zmniejszeniePunktowZycia);
        if (getZycieKierowcy() <= 0){
            System.out.println("Kierowca " + getTypKierowcy() + " nie wytrzymał trudów wyścigu i muszą się nim zająć służby medyczne.");
            System.out.println("GAME OVER");
        }
    }

    public void znajomoscTrasyPredkosc (Samochod samochod){
        if(getZnajomoscTrasy() >= 9){
            samochod.setSzybkosc(samochod.getSzybkosc() + 15);
            System.out.println("Ze względu na bardzo dobrą znajomość trasy kierowcy " + getTypKierowcy() +
                    " prędkość na tym odcinku (prostym) zostaje zwiększona o 15 km/h i wynosi " + samochod.getSzybkosc());
        } else if ( getZnajomoscTrasy() >= 7 ){
            samochod.setSzybkosc(samochod.getSzybkosc() + 5);
            System.out.println("Ze względu na dobrą znajomość trasy kierowcy " + getTypKierowcy() +
                    " prędkość na tym odcinku (prostym) zostaje zwiększona o 5 km/h i wynosi " + samochod.getSzybkosc());
        }
    }
    public  void znajomoscTrasyPredkoscReset (Samochod samochod){
        if(getZnajomoscTrasy() >= 9) {
            samochod.setSzybkosc(samochod.getSzybkosc() - 15);
        } else if ( getZnajomoscTrasy() >= 7) {
            samochod.setSzybkosc(samochod.getSzybkosc() - 5);
        }
    }

    @Override
    public String toString() {
        return getTypKierowcy() + " znajomosc trasy: " + getZnajomoscTrasy() + " stan trzezwosci: " + getStanTrzezwosci() + " szybkosc reakcji: " + getSzybkoscReakcji();
    }
}

