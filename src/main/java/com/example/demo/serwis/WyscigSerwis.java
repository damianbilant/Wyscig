package com.example.demo.serwis;

import com.example.demo.model.*;

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
        Kierowca uber = kierowcaSerwis.stworzKierowce(TypKierowcy.UBER);
        uber.aktualizacjaReakcjiOdPogody(pogoda);
        Kierowca starydziad = kierowcaSerwis.stworzKierowce(TypKierowcy.STARYDZIAD);
        starydziad.aktualizacjaReakcjiOdPogody(pogoda);
        System.out.println();


        SamochodSerwis samochodSerwis = new SamochodSerwis();
        Samochod suv = samochodSerwis.stworzSamochod(TypSamochodu.SUV);
        suv.aktualizacjaSzybkosciIhamowania(pogoda);
        System.out.println();
        Samochod coupe = samochodSerwis.stworzSamochod(TypSamochodu.COUPE);
        coupe.aktualizacjaSzybkosciIhamowania(pogoda);
        System.out.println();
        Samochod hatchback = samochodSerwis.stworzSamochod(TypSamochodu.HATCHBACK);
        hatchback.aktualizacjaSzybkosciIhamowania(pogoda);
        System.out.println();
    }
}