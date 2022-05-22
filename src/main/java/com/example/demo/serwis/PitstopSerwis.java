package com.example.demo.serwis;

import com.example.demo.model.*;
import com.example.demo.utils.Utils;
import org.springframework.stereotype.Service;

@Service

public class PitstopSerwis {



    public Integer wylosujIloscPitstopow(Pogoda pogoda,TrasaLevel trasaLevel) {
        int iloscPitstopow = 0;
        if (pogoda == Pogoda.SUNNY) {
            if (trasaLevel == TrasaLevel.EASY) {
                iloscPitstopow = Utils.losuj(0, 1);
            } else if (trasaLevel == TrasaLevel.MEDIUM) {
                iloscPitstopow = Utils.losuj(1, 2);
            } else if (trasaLevel == TrasaLevel.HARD) {
                iloscPitstopow = Utils.losuj(1, 3);
            }
            if (iloscPitstopow > 0) {
                System.out.println("Pogoda " + pogoda.getNazwaPogody() + ", możliwa ilość pitstopów: " + iloscPitstopow + ".");
            }
        } else if (pogoda == Pogoda.CLOUDY) {
            if (trasaLevel == TrasaLevel.EASY) {
                iloscPitstopow = Utils.losuj(1, 2);
            } else if (trasaLevel == TrasaLevel.MEDIUM) {
                iloscPitstopow = Utils.losuj(1, 3);
            } else if (trasaLevel == TrasaLevel.HARD) {
                iloscPitstopow = Utils.losuj(2, 3);
            }
            System.out.println("Pogoda " + pogoda.getNazwaPogody() + ", możliwa ilość pitstopów: " + iloscPitstopow + ".");
        } else if (pogoda == Pogoda.RAINY) {
            if (trasaLevel == TrasaLevel.EASY) {
                iloscPitstopow = Utils.losuj(1, 3);
            } else if (trasaLevel == TrasaLevel.MEDIUM) {
                iloscPitstopow = Utils.losuj(2, 3);
            } else if (trasaLevel == TrasaLevel.HARD) {
                iloscPitstopow = Utils.losuj(2, 4);
            }
            System.out.println("Pogoda " + pogoda.getNazwaPogody() + ", możliwa ilość pitstopów: " + iloscPitstopow + ".");
        } else if (pogoda == Pogoda.SNOWY) {
            if (trasaLevel == TrasaLevel.EASY) {
                iloscPitstopow = Utils.losuj(2, 3);
            } else if (trasaLevel == TrasaLevel.MEDIUM) {
                iloscPitstopow = Utils.losuj(2, 4);
            } else if (trasaLevel == TrasaLevel.HARD) {
                iloscPitstopow = Utils.losuj(3, 4);
            }
            System.out.println("Pogoda " + pogoda.getNazwaPogody() + ", możliwa ilość pitstopów: " + iloscPitstopow + ".");
        }
        return iloscPitstopow;

    }





}
