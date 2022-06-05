package com.example.demo.model.kierowca;

import com.example.demo.utils.Utils;

import java.util.UUID;

public class KierowcaDziad extends Kierowca{

    public KierowcaDziad(UUID uuid) {

        super(TypKierowcy.DZIAD, Utils.losuj(2,8), Utils.losuj(0,8),Utils.losuj(3,5),50, uuid);
    }
}
