package com.example.demo.serwis;

import com.example.demo.model.*;
import org.springframework.stereotype.Service;

@Service

public class SamochodSerwis {

    public Samochod stworzSamochod(TypSamochodu typSamochodu) {
        Samochod samochod;
        switch (typSamochodu) {
            case SUV:
                samochod = new BigCar();
                break;
            case COUPE:
                samochod = new FastCar();
                break;
            case HATCHBACK:
                samochod = new CityCar();
                break;
            default:
                samochod = null;
                break;
        }
        System.out.println("Nowy samochód to: " + samochod.toString());
        samochod.wyliczenieDrogiHamowania(samochod);
        return samochod;
    }


    }




