package com.example.demo.serwis;

import com.example.demo.model.Pogoda;
import com.example.demo.model.TypKierowcy;
import com.example.demo.model.TypSamochodu;

public class WyscigSerwis {

    public void tworzenieWyscigu() {
        System.out.println();
        PogodaSerwis pogodaSerwis = new PogodaSerwis();
        Pogoda pogoda = pogodaSerwis.wylosujPogode();
        System.out.println();

        TrasaSerwis trasaSerwis = new TrasaSerwis();
        trasaSerwis.stworzTrase(trasaSerwis.wylosujPoziomTrudnosci());

        KierowcaSerwis kierowcaSerwis = new KierowcaSerwis();
        System.out.println();
        kierowcaSerwis.stworzKierowce(TypKierowcy.UBER);
        kierowcaSerwis.stworzKierowce(TypKierowcy.STARYDZIAD);
        System.out.println();

        SamochodSerwis samochodSerwis = new SamochodSerwis();
        samochodSerwis.stworzSamochod(TypSamochodu.SUV);
        samochodSerwis.stworzSamochod(TypSamochodu.COUPE);
        samochodSerwis.stworzSamochod(TypSamochodu.HATCHBACK);
        System.out.println();
    }
}