package com.example.demo.model;

import com.example.demo.serwis.KierowcaSerwis;

public class OldDziadDriver extends Kierowca{

    public OldDziadDriver() {
        super(TypKierowcy.STARYDZIAD, KierowcaSerwis.losuj(2,8), KierowcaSerwis.losuj(0,5),KierowcaSerwis.losuj(0,6));
    }
}
