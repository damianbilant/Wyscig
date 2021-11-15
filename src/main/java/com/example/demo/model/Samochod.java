package com.example.demo.model;

public class Samochod {
    private Integer ciezar;
    private Integer hamowanie;
    private Integer szybkosc;

    public Samochod(Integer ciezar, Integer hamowanie, Integer szybkosc) {
        this.ciezar = ciezar;
        this.hamowanie = hamowanie;
        this.szybkosc = szybkosc;
    }

    public Integer getCiezar() {
        return ciezar;
    }

    public Integer getHamowanie() {
        return hamowanie;
    }

    public Integer getSzybkosc() {
        return szybkosc;
    }


    @Override
    public String toString() {
    return "ciężar samochodu to: " + getCiezar() + "skuteczność hamowania samochodu to: " + getHamowanie() + "szybkość samochodu to: " + getSzybkosc();
    }
}