package com.example.demo.model;

import com.example.demo.serwis.KierowcaSerwis;
import com.example.demo.utils.Utils;

public class KierowcaDziad extends Kierowca{

    public KierowcaDziad() {
        super(TypKierowcy.STARYDZIAD, Utils.losuj(2,8), Utils.losuj(0,5),Utils.losuj(0,6));
    }
}
