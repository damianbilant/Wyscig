package com.example.demo.model;

import com.example.demo.serwis.KierowcaSerwis;

public class KierowcaDziad extends Kierowca{

    public KierowcaDziad() {
        super(TypKierowcy.STARYDZIAD, KierowcaSerwis.losuj(2,8), KierowcaSerwis.losuj(0,5),KierowcaSerwis.losuj(0,6));
    }
}
