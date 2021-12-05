package com.example.demo.model;

import com.example.demo.serwis.SamochodSerwis;
import com.example.demo.utils.Utils;

public class FastCar extends Samochod{
    public FastCar (){
        super(TypSamochodu.COUPE, Utils.losuj(800,1200), Utils.losuj(100,400), Utils.losuj(170,200));
    }
}
