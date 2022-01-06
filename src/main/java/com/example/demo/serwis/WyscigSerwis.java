package com.example.demo.serwis;

import com.example.demo.model.*;
import com.example.demo.utils.Utils;

public class WyscigSerwis {

    public void tworzenieWyscigu() {
        System.out.println();
        Pogoda pogoda = PogodaSerwis.wylosujPogode();
        System.out.println();

        TrasaSerwis trasaSerwis = new TrasaSerwis();
        Trasa trasa = trasaSerwis.stworzTrase(pogoda, trasaSerwis.wylosujPoziomTrudnosci());


        KierowcaSerwis kierowcaSerwis = new KierowcaSerwis();
        System.out.println();
        Kierowca uber = kierowcaSerwis.stworzKierowce(TypKierowcy.UBER);
        uber.aktualizacjaReakcjiOdPogody(pogoda);
        Kierowca starydziad = kierowcaSerwis.stworzKierowce(TypKierowcy.DZIAD);
        starydziad.aktualizacjaReakcjiOdPogody(pogoda);
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

    public void nastepnyOdcinek(TypOdcinka obecnyOdcinek, Odcinek nastepnyOdcinek, Samochod samochod, Kierowca kierowca) {
        if (obecnyOdcinek == TypOdcinka.PROSTY) {
            switch (nastepnyOdcinek.getTypOdcinka()) {
                case PROSTY:
                    if (nastepnyOdcinek.getTrudnoscOdcinka() <= 1) {
                        if (kierowca.getZnajomoscTrasy() >= 6) {
                            Integer nowaSzybkosc = samochod.getSzybkosc() + 5;
                            samochod.limitZmianyPredkosciIzmianaPredkosci(nowaSzybkosc);
                        } else if (kierowca.getRyzyko() >= 10) {
                            Integer nowaSzybkosc = samochod.getSzybkosc() + 10;
                            samochod.limitZmianyPredkosciIzmianaPredkosci(nowaSzybkosc);

                        }
                    }
                    break;
                case ZAKRET:

                    break;
                case ZJAZD:

                    break;
                case PODJAZD:

                    break;
                default:
                    //meta bo ostatni odcinek
                    break;
            }
            //zwieksz predkosc o 5 jesli znajomosc trasy >=6 lub zwieksz predkosc o 10 jesli ryzyko >= 10
            // lub nie rob nic i metoda w samochodzie ktora panueje nad tym, 2x nie mozna zwiekszyc lub zmniejszyc predkosci po sobie

        }
        if (obecnyOdcinek == TypOdcinka.ZAKRET) {
            switch (nastepnyOdcinek.getTypOdcinka()) {
                case PROSTY:

                    break;
                case ZAKRET:

                    break;
                case ZJAZD:

                    break;
                case PODJAZD:

                    break;
                default:
//meta bo ostatni odcinek
                    break;
            }
        }
        if (obecnyOdcinek == TypOdcinka.ZJAZD) {
            switch (nastepnyOdcinek.getTypOdcinka()) {
                case PROSTY:

                    break;
                case ZAKRET:

                    break;
                case ZJAZD:

                    break;
                case PODJAZD:

                    break;
                default:
//meta bo ostatni odcinek
                    break;
            }
        }
        if (obecnyOdcinek == TypOdcinka.PODJAZD) {
            switch (nastepnyOdcinek.getTypOdcinka()) {
                case PROSTY:

                    break;
                case ZAKRET:

                    break;
                case ZJAZD:

                    break;
                case PODJAZD:

                    break;
                default:
//meta bo ostatni odcinek
                    break;
            }
        } else {
            //dopiero ruszyli więc potraktujmy jakby jechali prostym
        }
    }


    public void przejazdOdcinekProsty(Odcinek odcinek, Kierowca kierowca, Samochod samochod) {
        if (odcinek.getTrudnoscOdcinka() >= 1 && kierowca.getZnajomoscTrasy() <= 5) {
            if (kierowca.getSzybkoscReakcji() <= 3 || kierowca.getRyzyko() >= 8) {
                kierowca.setZycieKierowcy(kierowca.getZycieKierowcy() - Utils.losuj(0, 3));
                samochod.setWytrzymaloscSamochodu(samochod.getWytrzymaloscSamochodu() - Utils.losuj(0, 3));
            }
            if (kierowca.getSzybkoscReakcji() <= 5 || kierowca.getRyzyko() >= 10) {
                kierowca.setZycieKierowcy(kierowca.getZycieKierowcy() - Utils.losuj(0, 2));
                samochod.setWytrzymaloscSamochodu(samochod.getWytrzymaloscSamochodu() - Utils.losuj(0, 2));
            }
        } else if (odcinek.getTrudnoscOdcinka() == 0 && kierowca.getZnajomoscTrasy() <= 4) {
            if (kierowca.getSzybkoscReakcji() <= 4 || kierowca.getRyzyko() >= 14) {
                kierowca.setZycieKierowcy(kierowca.getZycieKierowcy() - Utils.losuj(0, 1));
                samochod.setWytrzymaloscSamochodu(samochod.getWytrzymaloscSamochodu() - Utils.losuj(0, 1));
            }
        }

        samochod.szybkoscPrzejazduOdcinka(odcinek);

    }
}





/*
Poprawka do drugiej części - wyścigSerwis - metoda przejazdOdcinekProsty. Trochę inaczej jednak będzie. Jeśli trudność odcinka >= 1 i
znajomość trasy < 4 to teraz dwa warianty:
        1. jeśli szybkość reakcji < 3 lub ryzyko > 15 to odejmij życie i stan auta o Utils.losuj(0,3).
        2. jeśli szybkość reakcji < 5 lub ryzyko > 10 to odejmij życie i stan auta o Utils.losuj(0,2).
        Natomiast jeśli trudność odcinka == 0 i znajomość trasy < 3 to jeden wariant:
        1. jeśli szybkość reakcji < 3 lub ryzyko > 15 to odejmij życie i stan auta o Utils.losuj(0,1).
        Dalej bez zmian czyli tutaj wywoał metodę liczącą szybkość przejazdu odcinka gdzie zapisany będzie czas i dystans.
        Dalej wywołamy metodę zmianaOdcinka (jej jeszcze nie mamy) i tabelę (tego też jeszcze nie mamy).*/