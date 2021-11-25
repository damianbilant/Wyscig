package com.example.demo.model;

import com.example.demo.serwis.SamochodSerwis;

public class CityCar extends Samochod{

        public CityCar (){
            super(TypSamochodu.HATCHBACK, SamochodSerwis.losuj(1000,1600), SamochodSerwis.losuj(400,700), SamochodSerwis.losuj(140,160));
        }
}
