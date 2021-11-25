package com.example.demo.model;

import com.example.demo.serwis.SamochodSerwis;

public class FastCar extends Samochod{
    public FastCar (){
        super(TypSamochodu.COUPE, SamochodSerwis.losuj(800,1200), SamochodSerwis.losuj(100,400), SamochodSerwis.losuj(170,200));
    }
}
