package com.example.demo.model;

import com.example.demo.utils.Utils;

public class OdcinekPitstop extends Odcinek{
    public OdcinekPitstop() { super(TypOdcinka.PITSTOP, Utils.losuj(1,3),Utils.losuj(0,1),"Pitstop");}

}
