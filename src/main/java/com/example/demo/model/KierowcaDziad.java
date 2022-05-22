package com.example.demo.model;

import com.example.demo.utils.Utils;

public class KierowcaDziad extends Kierowca{

    public KierowcaDziad() {

        super(TypKierowcy.DZIAD, Utils.losuj(2,8), Utils.losuj(0,8),Utils.losuj(3,5),50);
    }
}
