package com.example.demo.serwis;

import com.example.demo.model.*;
import com.example.demo.model.odcinek.TypOdcinka;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrasaSerwisTest {


    @Test

    public void czyTworzyTrase() {
        //given
        List<TrasaLevel> listaPoziomow = listaPoziomow();

        TrasaSerwis trasaSerwis = new TrasaSerwis();
        PogodaSerwis pogodaSerwis = new PogodaSerwis();
        Pogoda pogoda = pogodaSerwis.wylosujPogode();
        PitstopSerwis pitstopSerwis = new PitstopSerwis();
        List<TypOdcinka> listaTypowOdcinkow = new ArrayList<>(Arrays.asList(TypOdcinka.values()));
        //TrasaLevel trasaLevel = trasaSerwis.wylosujPoziomTrudnosci();
        for (int j = 0; j < listaPoziomow.size(); j++) {
            TrasaLevel level = listaPoziomow.get(j);

            //when
            Trasa trasa = trasaSerwis.stworzTrase(pogoda, level, pitstopSerwis);
            //then
            // test1: sumowanie ilości odcinków metoda i porównać do trasa.getListaOdcinkow().size();
            //Assert.assertTrue();
            Assert.assertTrue(trasa.getListaOdcinkow().get(0).getTypOdcinka().equals(TypOdcinka.PROSTY));
            Assert.assertFalse(trasa.getListaOdcinkow().get(0).getTypOdcinka().equals(TypOdcinka.ZAKRET));
            Assert.assertFalse(trasa.getListaOdcinkow().get(0).getTypOdcinka().equals(TypOdcinka.PODJAZD));
            Assert.assertFalse(trasa.getListaOdcinkow().get(0).getTypOdcinka().equals(TypOdcinka.ZJAZD));
            Assert.assertTrue(trasa.getListaOdcinkow().size() <= sumowanieOdcinkow(level,trasa));

            for (int i = 1; i < trasa.getListaOdcinkow().size(); i++) {
                if (trasa.getListaOdcinkow().get(i).getTrudnoscOdcinka() == trasa.getListaOdcinkow().get(i - 1).getTrudnoscOdcinka()) {
                    Assert.assertNotEquals(trasa.getListaOdcinkow().get(i).getTypOdcinka(), trasa.getListaOdcinkow().get(i - 1).getTypOdcinka());
                } else if (trasa.getListaOdcinkow().get(i).getTypOdcinka() == trasa.getListaOdcinkow().get(i - 1).getTypOdcinka()) {
                    Assert.assertNotEquals(trasa.getListaOdcinkow().get(i).getTrudnoscOdcinka(), trasa.getListaOdcinkow().get(i - 1).getTrudnoscOdcinka());
                }
            }
            for (int i = 0; i < trasa.getListaOdcinkow().size(); i++) {
                Assert.assertTrue((listaTypowOdcinkow).contains(trasa.getListaOdcinkow().get(i).getTypOdcinka()));
            }

        }
    }

    private List<TrasaLevel> listaPoziomow() {
        TrasaLevel trasaLevelEasy = TrasaLevel.EASY;
        TrasaLevel trasaLevelMedium = TrasaLevel.MEDIUM;
        TrasaLevel trasaLevelHard = TrasaLevel.HARD;
        List<TrasaLevel> listaPoziomowTrasy = new ArrayList<>();
        listaPoziomowTrasy.add(trasaLevelEasy);
        listaPoziomowTrasy.add(trasaLevelMedium);
        listaPoziomowTrasy.add(trasaLevelHard);

        return listaPoziomowTrasy;
    }

    private int sumowanieOdcinkow(TrasaLevel trasaLevel,Trasa trasa) {
        int suma = trasaLevel.getIloscOdcinkowProstych() + trasaLevel.getIloscPodjazdow() + trasaLevel.getIloscZakretow() + trasaLevel.getIloscZjazdow()
                + iloscPitstopow(trasa);

        return suma;
    }

    private int iloscPitstopow(Trasa trasa) {
        int licznik = 0;
        for (int i = 0; i < trasa.getListaOdcinkow().size(); i++) {
            if (trasa.getListaOdcinkow().get(i).getNazwaOdcinka().equals("Pitstop")) {
                licznik += 1;
            }
        }
        return licznik;

    }
}

//TODO:czy pitstopów jest co najmniej tyle ile jest w wylosuj czy w trasie rzeczywiście tyle powstaje ile powinno