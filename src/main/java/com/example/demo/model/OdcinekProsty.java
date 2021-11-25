package com.example.demo.model;

public class OdcinekProsty {
    private Integer dlugoscOdcinka;
    private Integer trudnoscOdcinka;
    private String nazwaOdcinka;

    public OdcinekProsty(Integer dlugoscOdcinka, Integer trudnoscOdcinka, String nazwaOdcinka) {
        this.dlugoscOdcinka = dlugoscOdcinka;
        this.trudnoscOdcinka = trudnoscOdcinka;
        this.nazwaOdcinka = nazwaOdcinka;
    }

    public Integer getDlugoscOdcinka() {
        return dlugoscOdcinka;
    }

    public Integer getTrudnoscOdcinka() {
        return trudnoscOdcinka;
    }

    public String getNazwaOdcinka() {
        return nazwaOdcinka;
    }
}
