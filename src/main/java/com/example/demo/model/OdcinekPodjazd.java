package com.example.demo.model;

import com.example.demo.utils.Utils;

public class OdcinekPodjazd extends Odcinek {
    public OdcinekPodjazd() {
        super(TypOdcinka.PODJAZD, Utils.losuj(1,3), Utils.losuj(1,10), "Podjazd" );
    }
}
