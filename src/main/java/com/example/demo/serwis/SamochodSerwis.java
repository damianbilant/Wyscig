package com.example.demo.serwis;

import com.example.demo.exceptions.SamochodException;
import com.example.demo.model.*;
import com.example.demo.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service

public class SamochodSerwis {

    public Samochod stworzSamochod(TypSamochodu typSamochodu) {
        Samochod samochod;
        try {
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
        } catch (Exception exception) {
            throw new SamochodException(typSamochodu);
        }
    }

    public Samochod losowoStworzSamochod() {
        ArrayList<TypSamochodu> listaTypowSamochodu = new ArrayList<>(Arrays.asList(TypSamochodu.values()));
        Samochod samochod;

        int wylosowanyIndex = Utils.losuj(0, listaTypowSamochodu.size() - 1);
        TypSamochodu wylosowanyTypSamochodu = listaTypowSamochodu.get(wylosowanyIndex);
        samochod = stworzSamochod(wylosowanyTypSamochodu);

        return samochod;
    }


}




