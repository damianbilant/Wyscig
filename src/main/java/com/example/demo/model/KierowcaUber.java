package com.example.demo.model;

import com.example.demo.serwis.KierowcaSerwis;

public class KierowcaUber extends Kierowca    {

    public KierowcaUber() {
        super(TypKierowcy.UBER, KierowcaSerwis.losuj(0,10), KierowcaSerwis.losuj(2,10), KierowcaSerwis.losuj(7,10));
    }


}
