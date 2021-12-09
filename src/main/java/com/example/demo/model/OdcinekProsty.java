package com.example.demo.model;

import com.example.demo.utils.Utils;

public class OdcinekProsty extends Odcinek{
public OdcinekProsty(){
    super(TypOdcinka.PROSTY, Utils.losuj(2,10), Utils.losuj(0,1), "Odcinek prosty" );
}
}


