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

    public TrasaLevel wylosujPoziomTrudnosci() {
        int skalaTrudnosci = Utils.losuj(0, 99);
        TrasaLevel trasaLevel;
        if (skalaTrudnosci < 33) {
            trasaLevel = TrasaLevel.EASY;
        } else if (skalaTrudnosci < 66) {
            trasaLevel = TrasaLevel.MEDIUM;
        } else {
            trasaLevel = TrasaLevel.HARD;
        }
        return trasaLevel;
    }

    public Trasa stworzTrase(Pogoda pogoda, TrasaLevel poziomTrudnosci) {
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

        powtarzalnoscOdcinkow(listaOdcinkow);
        sumowanieDlugosciPowtarzalnychPoziomowOdcinkow(listaOdcinkow);

        Trasa trasa = new Trasa(pogoda, listaOdcinkow);

        System.out.println("Poziom trudności trasy: " + poziomTrudnosci.getNazwaPoziomuTrasy() + ", długość trasy: " + sumowanieTrasy(listaOdcinkow) + " km");
        System.out.println("Trasa składa się z odcinków:");
        System.out.println();
        for (Odcinek odcinek : listaOdcinkow) {
            System.out.println(odcinek.getNazwaOdcinka() + ", długość " + odcinek.getDlugoscOdcinka() + " km, " + "poziom trudności " + odcinek.getTrudnoscOdcinka());
        }
        System.out.println();
        return trasa;
    }

    private void powtarzalnoscOdcinkow(List<Odcinek> listaOdcinkow) {
        Integer licznikOdcinkow = 1;
        for (int i = 1; i < listaOdcinkow.size(); i++) {
            if (listaOdcinkow.get(i).getTypOdcinka().equals(listaOdcinkow.get(i - 1).getTypOdcinka())) {
                licznikOdcinkow += 1;
                if (licznikOdcinkow == 4) {
                    Collections.shuffle(listaOdcinkow);
                    powtarzalnoscOdcinkow(listaOdcinkow);
                }
            } else {
                licznikOdcinkow = 1;
            }
        }
    }

    private Integer sumowanieTrasy(List<Odcinek> listaOdcinkow) {
        int suma = 0;
        for (Odcinek odcinek : listaOdcinkow) {
            suma += odcinek.getDlugoscOdcinka();
        }
        return suma;
    }

    private void sumowanieDlugosciPowtarzalnychPoziomowOdcinkow(List<Odcinek> listaOdcinkow) {
        for (int i = 1; i < listaOdcinkow.size(); i++) {
            if (listaOdcinkow.get(i).getTypOdcinka().equals(listaOdcinkow.get(i - 1).getTypOdcinka())) {
                if (listaOdcinkow.get(i).getTrudnoscOdcinka().equals(listaOdcinkow.get(i - 1).getTrudnoscOdcinka())) {
                    int dlugoscOdcinka1 = listaOdcinkow.get(i).getDlugoscOdcinka();
                    int dlugoscOdcinka2 = listaOdcinkow.get(i - 1).getDlugoscOdcinka();
                    int suma = dlugoscOdcinka2 + dlugoscOdcinka1;
               /* System.out.println("zsumowało!!!!!!!!!!!!!!!!!!");
                System.out.println(dlugoscOdcinka1 + " długość 1 " + dlugoscOdcinka2 + " długość 2 " + listaOdcinkow.get(i).getTrudnoscOdcinka() +
                        " poziom1 " + listaOdcinkow.get(i-1).getTrudnoscOdcinka() + " poziom2");*/
                    listaOdcinkow.get(i - 1).setDlugoscOdcinka(suma);
                    listaOdcinkow.remove(i);
                    sumowanieDlugosciPowtarzalnychPoziomowOdcinkow(listaOdcinkow);
                }

            }
        }
    }
}
