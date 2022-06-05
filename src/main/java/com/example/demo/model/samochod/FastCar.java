package com.example.demo.model.samochod;

import com.example.demo.utils.Utils;

public class FastCar extends Samochod{
    public FastCar (){
        super(TypSamochodu.COUPE, Utils.losuj(800,1200), Utils.losuj(450,650), Utils.losuj(170,200),100);
    }
}
