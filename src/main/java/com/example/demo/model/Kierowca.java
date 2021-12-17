package com.example.demo.model;

import com.example.demo.serwis.KierowcaSerwis;

public abstract class Kierowca {
    private TypKierowcy typKierowcy;
    private Integer znajomoscTrasy;
    private Integer stanTrzezwosci;
    private Integer szybkoscReakcji;

    public Kierowca(TypKierowcy typKierowcy, Integer znajomoscTrasy, Integer stanTrzezwosci, Integer szybkoscReakcji) {
        this.typKierowcy = typKierowcy;
        this.znajomoscTrasy = znajomoscTrasy;
        this.stanTrzezwosci = stanTrzezwosci;
        this.szybkoscReakcji = szybkoscReakcji;
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



    @Override
    public String toString() {
        return getTypKierowcy() + " znajomosc trasy: " + getZnajomoscTrasy() + " stan trzezwosci: " + getStanTrzezwosci() + " szybkosc reakcji: " + getSzybkoscReakcji();
    }
}

