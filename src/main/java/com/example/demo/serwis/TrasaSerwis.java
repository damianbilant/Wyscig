package com.example.demo.serwis;

import com.example.demo.model.*;
import com.example.demo.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service

public class TrasaSerwis {

    public Odcinek stworzOdcinek(TypOdcinka typOdcinka) {
        Odcinek odcinek;
        switch (typOdcinka) {
            case PODJAZD:
                odcinek = new OdcinekPodjazd();
                break;
            case PROSTY:
                odcinek = new OdcinekProsty();
                break;
            case ZAKRET:
                odcinek = new OdcinekZakret();
                break;
            case ZJAZD:
                odcinek = new OdcinekZjazd();
                break;
            default:
                odcinek = null;
                break;

        }
        return odcinek;
    }

    public Pogoda wylosujPogode() {
        int rodzajPogody = Utils.losuj(0, 100);
        Pogoda pogoda;
        if (rodzajPogody < 25) {
            pogoda = Pogoda.SUNNY;
        } else if (rodzajPogody < 50) {
            pogoda = Pogoda.CLOUDY;
        } else if (rodzajPogody < 75) {
            pogoda = Pogoda.RAINY;
        } else {
            pogoda = Pogoda.SNOWY;
        }
        return pogoda;
    }

    public Trasa stworzTrase(TrasaLevel poziomTrudnosci, Pogoda pogoda) {
        Integer iloscOdcinkowProstych = poziomTrudnosci.getIloscOdcinkowProstych();
        Integer iloscPodjazdow = poziomTrudnosci.getIloscPodjazdow();
        Integer iloscZakretow = poziomTrudnosci.getIloscZakretow();
        Integer iloscZjazdow = poziomTrudnosci.getIloscZjazdow();


        List<Odcinek> listaOdcinkow = new ArrayList<>();

        if (iloscOdcinkowProstych > 0) {
            for (int i = 0; i < iloscOdcinkowProstych; i++) {
                Odcinek odcinek = stworzOdcinek(TypOdcinka.PROSTY);
                listaOdcinkow.add(odcinek);
            }
        }
        if (iloscPodjazdow > 0) {
            for (int i = 0; i < iloscPodjazdow; i++) {
                Odcinek odcinek = stworzOdcinek(TypOdcinka.PODJAZD);
                listaOdcinkow.add(odcinek);
            }
        }
        if (iloscZakretow > 0) {
            for (int i = 0; i < iloscZakretow; i++) {
                Odcinek odcinek = stworzOdcinek(TypOdcinka.ZAKRET);
                listaOdcinkow.add(odcinek);
            }
        }
        if (iloscZjazdow > 0) {
            for (int i = 0; i < iloscZjazdow; i++) {
                Odcinek odcinek = stworzOdcinek(TypOdcinka.ZJAZD);
                listaOdcinkow.add(odcinek);
            }
        }


        Collections.shuffle(listaOdcinkow);

        Trasa trasa = new Trasa(pogoda, listaOdcinkow);

        for (Odcinek odcinek : listaOdcinkow) {
            System.out.println(odcinek.getNazwaOdcinka() + " " + odcinek.getDlugoscOdcinka() + " " + odcinek.getTrudnoscOdcinka());
        }
        System.out.println("Pogoda " + pogoda.getNazwaPogody());
        return trasa;
    }
}