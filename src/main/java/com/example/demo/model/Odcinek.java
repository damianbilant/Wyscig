package com.example.demo.model;

public abstract class Odcinek {
    private TypOdcinka typOdcinka;
    private Integer dlugoscOdcinka;
    private Integer trudnoscOdcinka;
    private String nazwaOdcinka;

    public Odcinek(TypOdcinka typOdcinka, Integer dlugoscOdcinka, Integer trudnoscOdcinka, String nazwaOdcinka) {
        this.typOdcinka = typOdcinka;
        this.dlugoscOdcinka = dlugoscOdcinka;
        this.trudnoscOdcinka = trudnoscOdcinka;
        this.nazwaOdcinka = nazwaOdcinka;
    }

    public void setDlugoscOdcinka(Integer dlugoscOdcinka) {
        this.dlugoscOdcinka = dlugoscOdcinka;
    }

    public TypOdcinka getTypOdcinka() {
        return typOdcinka;
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

