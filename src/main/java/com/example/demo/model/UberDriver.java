package com.example.demo.model;

import com.example.demo.serwis.KierowcaSerwis;

public class UberDriver extends Kierowca    {

    public UberDriver() {
        super(TypKierowcy.UBER, KierowcaSerwis.losuj(0,10), KierowcaSerwis.losuj(2,10), KierowcaSerwis.losuj(7,10));
    }


}
