package com.example.demo.model;

import com.example.demo.serwis.KierowcaSerwis;
import com.example.demo.utils.Utils;

public class KierowcaUber extends Kierowca    {

    public KierowcaUber() {
        super(TypKierowcy.UBER, Utils.losuj(0,10), Utils.losuj(2,10), Utils.losuj(7,10));
    }


}
