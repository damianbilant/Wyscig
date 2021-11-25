package com.example.demo.model;

import com.example.demo.serwis.SamochodSerwis;

public class BigCar extends Samochod {

        public BigCar (){
            super(TypSamochodu.SUV, SamochodSerwis.losuj(1600,2500), SamochodSerwis.losuj(600,1000), SamochodSerwis.losuj(160,190));
        }

}
