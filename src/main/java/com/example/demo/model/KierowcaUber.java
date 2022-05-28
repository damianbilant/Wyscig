package com.example.demo.model;

import com.example.demo.utils.Utils;

import java.util.UUID;

public class KierowcaUber extends Kierowca    {

    public KierowcaUber(UUID uuid) {
        super(TypKierowcy.UBER, Utils.losuj(0,10), Utils.losuj(2,10), Utils.losuj(7,10), 100,uuid);
    }


}
