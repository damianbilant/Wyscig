package com.example.demo.model;

import com.example.demo.utils.Utils;

public class OdcinekZakret extends Odcinek {
    public OdcinekZakret() {
        super(TypOdcinka.ZAKRET, Utils.losuj(1, 3), Utils.losuj(1,10), "Zakręt");
    }
}