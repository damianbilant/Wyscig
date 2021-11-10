package com.example.demo.model;

public abstract class Kierowca {
    private Integer znajomoscTrasy;
    private Integer stanTrzezwosci;
    private Integer szybkoscReakcji;

    public Kierowca(Integer znajomoscTrasy, Integer stanTrzezwosci, Integer szybkoscReakcji) {
        this.znajomoscTrasy = znajomoscTrasy;
        this.stanTrzezwosci = stanTrzezwosci;
        this.szybkoscReakcji = szybkoscReakcji;
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

    @Override
    public String toString(){
        return "znajomosc trasy: " + getZnajomoscTrasy() + "stan trzezwosci: " + getStanTrzezwosci() + "szybkosc reakcji: " + getSzybkoscReakcji();
    }
}
