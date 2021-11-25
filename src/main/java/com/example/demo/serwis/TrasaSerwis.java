package com.example.demo.serwis;

import com.example.demo.model.OdcinekProsty;
import com.example.demo.utils.Utils;

public class TrasaSerwis {
    public OdcinekProsty tworzOdcinekProsty(){
        return new OdcinekProsty(Utils.losuj(1,10),0,"Odcinek prosty");

    }
}
