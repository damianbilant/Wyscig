package com.example.demo.model;

public class Uczestnik {

    private Kierowca kierowca;
    private Samochod samochod;

    public Uczestnik(Kierowca kierowca, Samochod samochod) {
        this.kierowca = kierowca;
        this.samochod = samochod;
    }

    public Kierowca getKierowca() {
        return kierowca;
    }

    public Samochod getSamochod() {
        return samochod;
    }
}
