package com.example.demo.serwis;

import com.example.demo.model.*;

public class WyscigSerwis {

    public void tworzenieWyscigu() {
        System.out.println();
        PogodaSerwis pogodaSerwis = new PogodaSerwis();
        Pogoda pogoda = pogodaSerwis.wylosujPogode();
        System.out.println();

        TrasaSerwis trasaSerwis = new TrasaSerwis();
        Trasa trasa = trasaSerwis.stworzTrase(pogoda, trasaSerwis.wylosujPoziomTrudnosci());


        KierowcaSerwis kierowcaSerwis = new KierowcaSerwis();
        System.out.println();
        Kierowca uber = kierowcaSerwis.stworzKierowce(TypKierowcy.UBER);
        uber.aktualizacjaReakcjiOdPogody(pogoda);
        Kierowca starydziad = kierowcaSerwis.stworzKierowce(TypKierowcy.DZIAD);
        starydziad.aktualizacjaReakcjiOdPogody(pogoda);
        System.out.println();
        Kierowca bor = kierowcaSerwis.stworzKierowce(TypKierowcy.BOR);
        bor.aktualizacjaReakcjiOdPogody(pogoda);
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

        Odcinek odcinek = trasa.getListaOdcinkow().get(0);
        suv.dodajCzasPrzejazduOdcinka(suv.szybkoscPrzejazduOdcinka(odcinek));
        Odcinek odcinek2 = trasa.getListaOdcinkow().get(1);
        suv.dodajCzasPrzejazduOdcinka(suv.szybkoscPrzejazduOdcinka(odcinek2));
        Odcinek odcinek3 = trasa.getListaOdcinkow().get(2);
        suv.dodajCzasPrzejazduOdcinka(suv.szybkoscPrzejazduOdcinka(odcinek3));


        suv.dodajPrzejechanyDystans(odcinek);

        suv.dodajPrzejechanyDystans(odcinek2);

        suv.dodajPrzejechanyDystans(odcinek3);
    }


}