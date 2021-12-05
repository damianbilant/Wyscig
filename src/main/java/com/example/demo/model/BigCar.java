package com.example.demo.model;

import com.example.demo.serwis.SamochodSerwis;
import com.example.demo.utils.Utils;

public class BigCar extends Samochod {

        public BigCar (){
            super(TypSamochodu.SUV, Utils.losuj(1600,2500), Utils.losuj(600,1000), Utils.losuj(160,190));
        }

}
