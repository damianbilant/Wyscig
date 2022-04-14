package com.example.demo.model;

import com.example.demo.utils.Utils;

public class KierowcaBOR extends Kierowca {

    public KierowcaBOR() {
        super(TypKierowcy.BOR, Utils.losuj(5,10),Utils.losuj(0,5),Utils.losuj(5,10), 100);
    }


}


