package com.example.demo.model;

import java.util.List;

public class Trasa {
    private Pogoda pogoda;
    private List<Odcinek> listaOdcinkow;


    public Pogoda getPogoda() {

        return pogoda;
    }

    public List<Odcinek> getListaOdcinkow() {

        return listaOdcinkow;
    }

    public Trasa(Pogoda pogoda, List<Odcinek> listaOdcinkow) {
        this.pogoda = pogoda;
        this.listaOdcinkow = listaOdcinkow;


    }
}
