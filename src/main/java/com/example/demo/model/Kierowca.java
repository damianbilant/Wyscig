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

    public boolean czyTrzezwy(Kierowca kierowca) {
        boolean trzezwosc = true;
        if (getStanTrzezwosci() >= 4) {
            trzezwosc = false;
            zmniejszenieReakcji(kierowca);
        }
        return trzezwosc;
    }

    public void zmniejszenieReakcji(Kierowca kierowca) {
        if (getSzybkoscReakcji() <= 2) {
            setSzybkoscReakcji(0);
        }
        kierowca.setSzybkoscReakcji(getSzybkoscReakcji() - 3);
        System.out.println("Kierowca " + getTypKierowcy() + " jest nietrzeźwy więc ma wolniejszą reakcję równą " + getSzybkoscReakcji());
    }

    @Override
    public String toString() {
        return getTypKierowcy() + " znajomosc trasy: " + getZnajomoscTrasy() + " stan trzezwosci: " + getStanTrzezwosci() + " szybkosc reakcji: " + getSzybkoscReakcji();
    }
}

