package com.example.demo.model;

import com.example.demo.utils.Utils;

public class OdcinekZjazd extends Odcinek {
    public OdcinekZjazd() {
        super(TypOdcinka.ZJAZD, Utils.losuj(4, 6), Utils.losuj(1,10), "Niebezpieczny zjazd");
    }
}