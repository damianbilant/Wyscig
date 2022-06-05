package com.example.demo.model.kierowca;

import com.example.demo.utils.Utils;

import java.util.UUID;

public class KierowcaBOR extends Kierowca {

    public KierowcaBOR(UUID uuid) {
        super(TypKierowcy.BOR, Utils.losuj(5,10),Utils.losuj(0,5),Utils.losuj(5,10), 100, uuid);
    }


}


