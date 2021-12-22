package com.example.demo.model;

import com.example.demo.serwis.SamochodSerwis;
import com.example.demo.utils.Utils;

public class CityCar extends Samochod{

        public CityCar (){
            super(TypSamochodu.HATCHBACK, Utils.losuj(1000,1600), Utils.losuj(450,650), Utils.losuj(140,160),100);
        }
}
