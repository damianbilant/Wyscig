package com.example.demo.serwis;

import com.example.demo.model.*;
import com.example.demo.utils.Utils;

import java.util.List;
import java.util.Map;

public class WyscigSerwis {

    public void tworzenieWyscigu() {
        System.out.println();
        Pogoda pogoda = PogodaSerwis.wylosujPogode();
        System.out.println();

        TrasaSerwis trasaSerwis = new TrasaSerwis();
        Trasa trasa = trasaSerwis.stworzTrase(pogoda, trasaSerwis.wylosujPoziomTrudnosci());


        KierowcaSerwis kierowcaSerwis = new KierowcaSerwis();
       /* System.out.println();
        Kierowca uber = kierowcaSerwis.stworzKierowce(TypKierowcy.UBER);
        uber.aktualizacjaReakcjiOdPogody(pogoda);
        Kierowca starydziad = kierowcaSerwis.stworzKierowce(TypKierowcy.DZIAD);
        starydziad.aktualizacjaReakcjiOdPogody(pogoda);
        Kierowca bor = kierowcaSerwis.stworzKierowce(TypKierowcy.BOR);
        bor.aktualizacjaReakcjiOdPogody(pogoda);
        System.out.println();*/


        SamochodSerwis samochodSerwis = new SamochodSerwis();
        /*Samochod suv = samochodSerwis.stworzSamochod(TypSamochodu.SUV);
        suv.aktualizacjaSzybkosciIhamowania(pogoda);
        System.out.println();
        Samochod coupe = samochodSerwis.stworzSamochod(TypSamochodu.COUPE);
        coupe.aktualizacjaSzybkosciIhamowania(pogoda);
        System.out.println();
        Samochod hatchback = samochodSerwis.stworzSamochod(TypSamochodu.HATCHBACK);
        hatchback.aktualizacjaSzybkosciIhamowania(pogoda);
        System.out.println();*/
        obslugaWszystkichOdcinkow(samochodSerwis.stworzSamochod(TypSamochodu.HATCHBACK), kierowcaSerwis.losowoStworzKierowce(), trasa);

    }

    public void obslugaPojedynczegoOdcinka(Odcinek obecnyOdcinek, Odcinek nastepnyOdcinek, Samochod samochod, Kierowca kierowca) {
        przejazd(obecnyOdcinek, kierowca, samochod);
        System.out.println("Kierowca " + kierowca.getTypKierowcy() + " przejechał odcinek " + obecnyOdcinek.getNazwaOdcinka() +
                " w czasie: " +  samochod.szybkoscPrzejazduOdcinka(obecnyOdcinek));
        // starcie
        //starcie(samochod,samochod,kierowca,obecnyOdcinek);
        nastepnyOdcinek(obecnyOdcinek.getTypOdcinka(), nastepnyOdcinek, samochod, kierowca);
        System.out.println("Samochód " + samochod.getTypSamochodu() + " prowadzony przez kierowcę " + kierowca.getTypKierowcy() +
                " osiąga szybkość: " + samochod.getSzybkosc());
        //obecna predkosc i adnotacje do każdego następnego odcinka
        //tabela wyników
    }
    //zastanowić się nad pętlą, która będzie szła po wszystkich odcinkach i obsługiwać wszystkie odcinki

    private void nastepnyOdcinek(TypOdcinka obecnyOdcinek, Odcinek nastepnyOdcinek, Samochod samochod, Kierowca kierowca) {
        if (obecnyOdcinek == TypOdcinka.PROSTY) {
            obecnyProsty(nastepnyOdcinek, samochod, kierowca);


        }
        if (obecnyOdcinek == TypOdcinka.ZAKRET) {
            obecnyZakret(nastepnyOdcinek, samochod, kierowca);

        }
        if (obecnyOdcinek == TypOdcinka.ZJAZD) {
            obecnyZjazd(nastepnyOdcinek, samochod, kierowca);

        }
        if (obecnyOdcinek == TypOdcinka.PODJAZD) {
            obecnyPodjazd(nastepnyOdcinek, samochod, kierowca);
        } else {
            obecnyProsty(nastepnyOdcinek, samochod, kierowca);
            //dopiero ruszyli więc potraktujmy jakby jechali prostym
        }
    }


    private void obecnyPodjazd(Odcinek nastepnyOdcinek, Samochod samochod, Kierowca kierowca) {
        TypOdcinka podjazd = TypOdcinka.PODJAZD;
        TypOdcinka nastepnyOdcinekTypOdcinka = nastepnyOdcinek.getTypOdcinka();
        switch (nastepnyOdcinek.getTypOdcinka()) {
            case PROSTY:
                if (nastepnyOdcinek.getTrudnoscOdcinka() <= 1) {
                    if (kierowca.getZnajomoscTrasy() >= 4) {
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() + 5);
                    }
                }
                if (kierowca.getZnajomoscTrasy() < 4) {
                    samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 4);
                }
                break;
            case ZAKRET:
                if (nastepnyOdcinek.getTrudnoscOdcinka() <= 6) {
                    if ((kierowca.getZnajomoscTrasy() + kierowca.getSzybkoscReakcji()) > 10 && kierowca.getRyzyko() <= 8) {
                        zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(podjazd,nastepnyOdcinekTypOdcinka,samochod,kierowca,0,2,0,2);
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - Utils.losuj(0, 2));
                    }

                    if ((kierowca.getZnajomoscTrasy() + kierowca.getSzybkoscReakcji()) > 10 && kierowca.getRyzyko() > 8) {
                        zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(podjazd,nastepnyOdcinekTypOdcinka,samochod,kierowca,2,5,2,5);
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 5);
                    }
                    if ((kierowca.getZnajomoscTrasy() + kierowca.getSzybkoscReakcji() <= 10) && kierowca.getRyzyko() <= 8) {
                        zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(podjazd,nastepnyOdcinekTypOdcinka,samochod,kierowca,5,10,5,10);
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 10);
                    }
                    if ((kierowca.getZnajomoscTrasy() + kierowca.getSzybkoscReakcji()) <= 10 && kierowca.getRyzyko() > 8) {
                        zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(podjazd,nastepnyOdcinekTypOdcinka,samochod,kierowca,10,15,10,15);
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 15);
                    }
                }

                break;
            case ZJAZD:
                if (nastepnyOdcinek.getTrudnoscOdcinka() <= 7) {
                    if (kierowca.getRyzyko() <= 8) {
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() + 5);
                    }
                    if (kierowca.getRyzyko() > 8) {
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() + 10);
                    }
                }
                break;
            case PODJAZD:
                if (nastepnyOdcinek.getTrudnoscOdcinka() <= 5) {
                    if (samochod.getSzybkosc() <= 200 || samochod.getCiezar() >= 1400) {
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 10);
                    }
                }
                if (nastepnyOdcinek.getTrudnoscOdcinka() > 5) {
                    if (samochod.getSzybkosc() <= 180 || samochod.getCiezar() >= 1200) {
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 20);
                    }
                }

                break;

            default:
//meta bo ostatni odcinek
                break;
        }
    }

    private void obecnyZjazd(Odcinek nastepnyOdcinek, Samochod samochod, Kierowca kierowca) {
        TypOdcinka zjazd = TypOdcinka.ZJAZD;
        TypOdcinka nastepnyOdcinekTypOdcinka = nastepnyOdcinek.getTypOdcinka();
        switch (nastepnyOdcinek.getTypOdcinka()) {
            case PROSTY:
                if (nastepnyOdcinek.getTrudnoscOdcinka() <= 1) {
                    if (kierowca.getRyzyko() > 15) {
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() + 5);
                    }
                }
                break;
            case ZAKRET:
                if (nastepnyOdcinek.getTrudnoscOdcinka() >= 2) {
                    if ((samochod.getSzybkosc() / samochod.getDrogaHamowania()) <= 2.5 && kierowca.getRyzyko() <= 8) {
                        if (kierowca.getZnajomoscTrasy() <= 5 && kierowca.getSzybkoscReakcji() <= 8) {
                            zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(zjazd,nastepnyOdcinekTypOdcinka,samochod, kierowca, 0, 2, 0, 2);
                        }
                        if ((samochod.getSzybkosc() / samochod.getDrogaHamowania()) <= 2.5 && kierowca.getRyzyko() > 8) {
                            if (kierowca.getZnajomoscTrasy() <= 4 && kierowca.getSzybkoscReakcji() <= 6) {
                                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(zjazd,nastepnyOdcinekTypOdcinka,samochod,kierowca,2,5,2,5);
                                samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 5);
                            }
                        }
                        if ((samochod.getSzybkosc() / samochod.getDrogaHamowania()) > 2.5 && kierowca.getRyzyko() > 8) {
                            if (kierowca.getZnajomoscTrasy() <= 3 && kierowca.getSzybkoscReakcji() <= 4) {
                                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(zjazd,nastepnyOdcinekTypOdcinka,samochod,kierowca,5,10,5,10);
                                samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 10);
                            }
                        }
                        if ((samochod.getSzybkosc() / samochod.getDrogaHamowania()) > 2.5 && kierowca.getSzybkoscReakcji() <= 8) {
                            if (kierowca.getZnajomoscTrasy() <= 2 && kierowca.getSzybkoscReakcji() <= 3) {
                                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(zjazd,nastepnyOdcinekTypOdcinka,samochod,kierowca,10,15,10,15);
                                samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 15);
                            }

                        }
                    }
                }

                break;
            case ZJAZD:
                if (nastepnyOdcinek.getTrudnoscOdcinka() <= 4) {
                    if (kierowca.getRyzyko() <= 8) {
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() + 5);
                    }
                    if (kierowca.getRyzyko() > 8) {
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() + 10);
                    }
                }
                if (nastepnyOdcinek.getTrudnoscOdcinka() > 4) {
                    if (kierowca.getRyzyko() <= 8) {
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 10);
                    }
                }

                break;
            case PODJAZD:
                if (nastepnyOdcinek.getTrudnoscOdcinka() >= 5) {
                    if (samochod.getSzybkosc() <= 170 || samochod.getCiezar() >= 1600) {
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 10);
                    }
                }

                break;
            default:
//meta bo ostatni odcinek
                break;
        }
    }

    private void obecnyZakret(Odcinek nastepnyOdcinek, Samochod samochod, Kierowca kierowca) {
        TypOdcinka zakret = TypOdcinka.ZAKRET;
        TypOdcinka nastepnyOdcinekTypOdcinka = nastepnyOdcinek.getTypOdcinka();
        switch (nastepnyOdcinek.getTypOdcinka()) {
            case PROSTY:
                if (nastepnyOdcinek.getTrudnoscOdcinka() == 1) {
                    if (kierowca.getZnajomoscTrasy() >= 8) {
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() + 5);
                    } else if (kierowca.getRyzyko() >= 15) {
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() + 5);
                    }
                }
                break;
            case ZAKRET:
                if (nastepnyOdcinek.getTrudnoscOdcinka() >= 6) {
                    if ((samochod.getSzybkosc() / samochod.getDrogaHamowania()) <= 2.5 && kierowca.getRyzyko() <= 8) {
                        if (kierowca.getZnajomoscTrasy() <= 5 && kierowca.getSzybkoscReakcji() <= 8) {
                            zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(zakret,nastepnyOdcinekTypOdcinka,samochod, kierowca, 0, 2, 0, 2);
                        }
                        if ((samochod.getSzybkosc() / samochod.getDrogaHamowania()) <= 2.5 && kierowca.getRyzyko() > 8) {
                            if (kierowca.getZnajomoscTrasy() <= 4 && kierowca.getSzybkoscReakcji() <= 6) {
                                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(zakret,nastepnyOdcinekTypOdcinka,samochod, kierowca, 2, 5, 2, 5);
                                samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 5);
                            }
                        }
                        if ((samochod.getSzybkosc() / samochod.getDrogaHamowania()) > 2.5 && kierowca.getRyzyko() > 8) {
                            if (kierowca.getZnajomoscTrasy() <= 3 && kierowca.getSzybkoscReakcji() <= 4) {
                                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(zakret,nastepnyOdcinekTypOdcinka,samochod, kierowca, 5, 10, 5, 10);
                                samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 10);
                            }
                        }
                        if ((samochod.getSzybkosc() / samochod.getDrogaHamowania()) > 2.5 && kierowca.getSzybkoscReakcji() <= 8) {
                            if (kierowca.getZnajomoscTrasy() <= 2 && kierowca.getSzybkoscReakcji() <= 3) {
                                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(zakret,nastepnyOdcinekTypOdcinka,samochod, kierowca, 10, 15, 10, 15);
                                samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 15);
                            }

                        }
                    }
                }
                if (nastepnyOdcinek.getTrudnoscOdcinka() < 6) {
                    if (kierowca.getRyzyko() <= 10) {
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 5);
                    }
                }

                break;
            case ZJAZD:
                if (nastepnyOdcinek.getTrudnoscOdcinka() <= 7) {
                    if (kierowca.getRyzyko() <= 8) {
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() + 5);
                    }
                }
                if (kierowca.getRyzyko() > 8) {
                    samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() + 10);
                }

                break;
            case PODJAZD:
                if (nastepnyOdcinek.getTrudnoscOdcinka() >= 4) {
                    if (samochod.getSzybkosc() <= 170 || samochod.getCiezar() >= 1200) {
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 10);
                    }
                }
                break;

            default:
//meta bo ostatni odcinek
                break;
        }
    }

    private void obecnyProsty(Odcinek nastepnyOdcinek, Samochod samochod, Kierowca kierowca) {
        TypOdcinka nastepnyOdcinekTypOdcinka = nastepnyOdcinek.getTypOdcinka();
        TypOdcinka prosty = TypOdcinka.PROSTY;
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
                if (nastepnyOdcinek.getTrudnoscOdcinka() >= 5) {
                    if ((samochod.getSzybkosc() / samochod.getDrogaHamowania()) <= 2.5 && kierowca.getRyzyko() <= 8) {
                        if (kierowca.getZnajomoscTrasy() <= 5 && kierowca.getSzybkoscReakcji() <= 8) {
                            zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(prosty,nastepnyOdcinekTypOdcinka,samochod, kierowca, 0, 2, 0, 2);
                        }
                        if ((samochod.getSzybkosc() / samochod.getDrogaHamowania()) <= 2.5 && kierowca.getRyzyko() > 8) {
                            if (kierowca.getZnajomoscTrasy() <= 4 && kierowca.getSzybkoscReakcji() <= 6) {
                                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(prosty,nastepnyOdcinekTypOdcinka,samochod, kierowca, 2, 5, 2, 5);
                                samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 5);
                            }
                        }
                        if ((samochod.getSzybkosc() / samochod.getDrogaHamowania()) > 2.5 && kierowca.getRyzyko() > 8) {
                            if (kierowca.getZnajomoscTrasy() <= 3 && kierowca.getSzybkoscReakcji() <= 4) {
                                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(prosty,nastepnyOdcinekTypOdcinka,samochod, kierowca, 5, 10, 5, 10);
                                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 10);
                            }
                        }
                        if ((samochod.getSzybkosc() / samochod.getDrogaHamowania()) > 2.5 && kierowca.getSzybkoscReakcji() <= 8) {
                            if (kierowca.getZnajomoscTrasy() <= 2 && kierowca.getSzybkoscReakcji() <= 3) {
                                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(prosty,nastepnyOdcinekTypOdcinka,samochod, kierowca, 10, 15, 10, 15);
                                samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 15);
                            }

                        }

                    }
                }

                break;
            case ZJAZD:
                if (nastepnyOdcinek.getTrudnoscOdcinka() <= 5) {
                    if (kierowca.getRyzyko() <= 10) {
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() + 5);
                    }
                } else if (kierowca.getRyzyko() > 10) {
                    samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() + 10);
                } else {
                    samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc());
                }

                break;
            case PODJAZD:
                if (nastepnyOdcinek.getTrudnoscOdcinka() >= 6) {
                    if (samochod.getSzybkosc() <= 150 || samochod.getCiezar() >= 1600) {
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 10);
                    }
                }


                break;
            default:
                //meta bo ostatni odcinek
                break;
        }
    }


    private void zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(String nazwaOdcinka, Samochod samochod, Kierowca kierowca, int minZycieKierowca, int maxZycieKierowca, int minWytrzymaloscSamochodu, int maxWytrzymaloscSamochodu) {
        kierowca.setZycieKierowcy(kierowca.getZycieKierowcy() - Utils.losuj(minZycieKierowca, maxZycieKierowca));
        samochod.setWytrzymaloscSamochodu(samochod.getWytrzymaloscSamochodu() - Utils.losuj(minWytrzymaloscSamochodu, maxWytrzymaloscSamochodu));
        System.out.println("Kierowca " + kierowca.getTypKierowcy() + " jadący samochodem " + samochod.getTypSamochodu() + " pokonujący odcinek " +
                nazwaOdcinka + " odniósł obrażenia i teraz jego życie wynosi " + kierowca.getZycieKierowcy() + " natomiast samochód uległ uszkodzeniu i jego wytrzymałość wynosi: " + samochod.getWytrzymaloscSamochodu());
    }

    /*private void zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduStarcie(String nazwaOdcinka, Samochod samochod, Kierowca kierowca, int minZycieKierowca, int maxZycieKierowca, int minWytrzymaloscSamochodu, int maxWytrzymaloscSamochodu) {
        kierowca.setZycieKierowcy(kierowca.getZycieKierowcy() - Utils.losuj(minZycieKierowca, maxZycieKierowca));
        samochod.setWytrzymaloscSamochodu(samochod.getWytrzymaloscSamochodu() - Utils.losuj(minWytrzymaloscSamochodu, maxWytrzymaloscSamochodu));
        System.out.println("Kierowca " + kierowca.getTypKierowcy() + " jadący samochodem " + samochod.getTypSamochodu() + " pokonujący odcinek " +
                nazwaOdcinka + " odniósł obrażenia i teraz jego życie wynosi " + kierowca.getZycieKierowcy() +
                " natomiast samochód uległ uszkodzeniu i jego wytrzymałość wynosi: " + samochod.getWytrzymaloscSamochodu());
    }*/

    private void zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(TypOdcinka obecnyOdcinek, TypOdcinka nastepnyOdcinek, Samochod samochod, Kierowca kierowca, int minZycieKierowca, int maxZycieKierowca, int minWytrzymaloscSamochodu, int maxWytrzymaloscSamochodu) {
        kierowca.setZycieKierowcy(kierowca.getZycieKierowcy() - Utils.losuj(minZycieKierowca, maxZycieKierowca));
        samochod.setWytrzymaloscSamochodu(samochod.getWytrzymaloscSamochodu() - Utils.losuj(minWytrzymaloscSamochodu, maxWytrzymaloscSamochodu));
        System.out.println("Kierowca " + kierowca.getTypKierowcy() + " jadący samochodem " + samochod.getTypSamochodu() + " pokonujący odcinek " +
                obecnyOdcinek + " odniósł obrażenia i teraz jego życie wynosi " + kierowca.getZycieKierowcy() +
                " natomiast samochód uległ uszkodzeniu i jego wytrzymałość wynosi: " + samochod.getWytrzymaloscSamochodu());
    }

    public void przejazdOdcinekProsty(Odcinek odcinek, Kierowca kierowca, Samochod samochod) {
        String nazwaOdcinka = odcinek.getNazwaOdcinka();
        if (odcinek.getTrudnoscOdcinka() >= 1 && kierowca.getZnajomoscTrasy() <= 5) {
            if (kierowca.getSzybkoscReakcji() <= 3 || kierowca.getRyzyko() >= 8) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka,samochod, kierowca, 0, 3, 0, 3);
            }
            if (kierowca.getSzybkoscReakcji() <= 5 || kierowca.getRyzyko() >= 10) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka,samochod, kierowca, 0, 2, 0, 2);
            }
        } else if (odcinek.getTrudnoscOdcinka() == 0 && kierowca.getZnajomoscTrasy() <= 4) {
            if (kierowca.getSzybkoscReakcji() <= 4 || kierowca.getRyzyko() >= 14) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka, samochod, kierowca, 0, 1, 0, 1);
            }
        }

    }

    public void przejazdZakret(Odcinek odcinek, Kierowca kierowca, Samochod samochod) {
        String nazwaOdcinka = odcinek.getNazwaOdcinka();
        if (odcinek.getTrudnoscOdcinka() >= 8 && kierowca.getSzybkoscReakcji() <= 6) {
            if (kierowca.getZnajomoscTrasy() <= 6 || kierowca.getRyzyko() >= 8) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka,samochod, kierowca, 0, 5, 0, 5);
            }
            if (kierowca.getZnajomoscTrasy() <= 5 || kierowca.getRyzyko() >= 10) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka,samochod, kierowca, 0, 4, 0, 4);
            } else if (odcinek.getTrudnoscOdcinka() >= 4 && kierowca.getSzybkoscReakcji() <= 5) {
                if (kierowca.getZnajomoscTrasy() <= 5 || kierowca.getRyzyko() >= 8) {
                    zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka,samochod, kierowca, 0, 3, 0, 3);
                }
                if (kierowca.getZnajomoscTrasy() <= 4 || kierowca.getRyzyko() >= 10) {
                    zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka,samochod, kierowca, 0, 2, 0, 2);
                }
            } else if (odcinek.getTrudnoscOdcinka() < 4 && kierowca.getSzybkoscReakcji() <= 4) {
                if (kierowca.getZnajomoscTrasy() <= 4 || kierowca.getRyzyko() >= 12) {
                    zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka,samochod, kierowca, 0, 1, 0, 1);
                }
            }
        }

    }

    public void przejazdZjazd(Odcinek odcinek, Kierowca kierowca, Samochod samochod) {
        String nazwaOdcinka = odcinek.getNazwaOdcinka();
        if (odcinek.getTrudnoscOdcinka() > 3) {
            if (samochod.getDrogaHamowania() < 80 || kierowca.getZnajomoscTrasy() >= 8) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka,samochod, kierowca, 0, 2, 0, 2);
            }

            if (samochod.getDrogaHamowania() >= 80 || kierowca.getZnajomoscTrasy() < 8) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka,samochod, kierowca, 0, 5, 0, 5);
            }
        }
        if (odcinek.getTrudnoscOdcinka() <= 3) {
            if (samochod.getDrogaHamowania() < 80 || kierowca.getZnajomoscTrasy() >= 8) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka,samochod, kierowca, 0, 1, 0, 1);
            }
            if (samochod.getDrogaHamowania() >= 80 || kierowca.getZnajomoscTrasy() < 8) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka,samochod, kierowca, 0, 2, 0, 2);
            }
        }

    }

    public void przejazdPodjazd(Odcinek odcinek, Kierowca kierowca, Samochod samochod) {
        String nazwaOdcinka = odcinek.getNazwaOdcinka();
        if (odcinek.getTrudnoscOdcinka() > 6) {
            if (samochod.getSzybkosc() > 200 || kierowca.getZnajomoscTrasy() >= 8) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka,samochod, kierowca, 0, 2, 0, 2);
            }
            if (samochod.getSzybkosc() <= 200 || kierowca.getZnajomoscTrasy() < 8) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka,samochod, kierowca, 0, 5, 0, 5);
            }
        }
        if (odcinek.getTrudnoscOdcinka() <= 6) {
            if (samochod.getSzybkosc() > 200 || kierowca.getZnajomoscTrasy() >= 8) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka,samochod, kierowca, 0, 1, 0, 1);
            }
            if (samochod.getSzybkosc() <= 200 || kierowca.getZnajomoscTrasy() < 8) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka,samochod, kierowca, 0, 2, 0, 2);
            }
        }
    }

    public void przejazd(Odcinek obecnyOdcinek, Kierowca kierowca, Samochod samochod) {
        switch (obecnyOdcinek.getTypOdcinka()) {
            case PROSTY:
                przejazdOdcinekProsty(obecnyOdcinek, kierowca, samochod);
                break;
            case ZAKRET:
                przejazdZakret(obecnyOdcinek, kierowca, samochod);
                break;
            case ZJAZD:
                przejazdZjazd(obecnyOdcinek, kierowca, samochod);
                break;
            case PODJAZD:
                przejazdPodjazd(obecnyOdcinek, kierowca, samochod);
                break;
            default:
                break;
        }
    }

    public void obslugaWszystkichOdcinkow ( Samochod samochod, Kierowca kierowca, Trasa trasa){
        obslugaPojedynczegoOdcinka(null,trasa.getListaOdcinkow().get(0),samochod,kierowca); //start
        for(int i=0; i < trasa.getListaOdcinkow().size(); i++){
            Odcinek obecnyOdcinek = trasa.getListaOdcinkow().get(i);

            try {Odcinek nastepnyOdcinek = trasa.getListaOdcinkow().get(i + 1);
                obslugaPojedynczegoOdcinka(obecnyOdcinek, nastepnyOdcinek,samochod,kierowca);
            }
            catch (Exception e){
                obslugaPojedynczegoOdcinka(obecnyOdcinek,null,samochod,kierowca);
            }
        }
    }


/*    public void starcie (Map<Kierowca, Samochod> kierujacySamochodem, Odcinek obecnyOdcinek){
        //mapa gdzie będzie samochód i czas przejazdu, gdzie samochód jest kluczem a czas wartością, posortowane po czasie rosnąco
        // i porównywanie po czasie elementów w dół
        String nazwaOdcinka = obecnyOdcinek.getNazwaOdcinka();
        if((samochod.getCzasPrzejazdu() - innysamochod.getCzasPrzejazdu()) <= 1.0){
            System.out.println("Wypadek!");
            if(obecnyOdcinek.getTypOdcinka()==TypOdcinka.ZAKRET){
            zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduStarcie(nazwaOdcinka,samochod,kierowca,25,50,25,50);
        }
            if(obecnyOdcinek.getTypOdcinka()==TypOdcinka.ZJAZD){
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduStarcie(nazwaOdcinka,samochod,kierowca,15,30,15,30);
            }
            if(obecnyOdcinek.getTypOdcinka()==TypOdcinka.PODJAZD) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduStarcie(nazwaOdcinka,samochod, kierowca, 5, 15, 5, 15);
            }
            if(obecnyOdcinek.getTypOdcinka()==TypOdcinka.PROSTY){
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduStarcie(nazwaOdcinka,samochod,kierowca,0,5,0,5);
            }
        }
            else if((samochod.getCzasPrzejazdu() - innysamochod.getCzasPrzejazdu()) > 1.0 && (samochod.getCzasPrzejazdu() - innysamochod.getCzasPrzejazdu() <= 2.0)){
                System.out.println("Stłuczka!");
                if(obecnyOdcinek.getTypOdcinka()==TypOdcinka.ZAKRET){
                    zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduStarcie(nazwaOdcinka,samochod,kierowca,15,30,15,30);
                }
                if(obecnyOdcinek.getTypOdcinka()==TypOdcinka.ZJAZD){
                    zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduStarcie(nazwaOdcinka,samochod,kierowca,10,15,10,15);
                }
                if(obecnyOdcinek.getTypOdcinka()==TypOdcinka.PODJAZD){
                    zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduStarcie(nazwaOdcinka,samochod, kierowca,5,10,5,10);
                }
                if(obecnyOdcinek.getTypOdcinka()==TypOdcinka.PROSTY){
                    zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduStarcie(nazwaOdcinka,samochod,kierowca,2,5,2,5);
                }
            }
            else if((samochod.getCzasPrzejazdu() - innysamochod.getCzasPrzejazdu()) > 2.0 && (samochod.getCzasPrzejazdu() - innysamochod.getCzasPrzejazdu() <= 5.0)){
                System.out.println("Obtarcie!");
                if(obecnyOdcinek.getTypOdcinka()==TypOdcinka.ZAKRET) {
                    zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduStarcie(nazwaOdcinka,samochod, kierowca, 10, 15, 10, 15);
                }
                if(obecnyOdcinek.getTypOdcinka()==TypOdcinka.ZJAZD){
                    zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduStarcie(nazwaOdcinka,samochod,kierowca,5,10,5,10);
                }
                if(obecnyOdcinek.getTypOdcinka()==TypOdcinka.PODJAZD) {
                    zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduStarcie(nazwaOdcinka,samochod, kierowca, 2, 5, 2, 5);
                }
                if (obecnyOdcinek.getTypOdcinka()==TypOdcinka.PROSTY){
                    zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduStarcie(nazwaOdcinka,samochod,kierowca,0,2,0,2);
                }
            }
    }*/

    //jesli czas przejazdu odcinka rozni sie o <= 1 min mamy wypadek i znaczny spadek zycia kierowcy i spadek zycia auta
//jesli czas rozni sie o > 1 min && <= 2 min to mamy stłuczkę i spadki zycia
//jesli czas rozni sie o > 2 min && <= 5 min to mamy obtarcie i niewielkie spadki zycia
//spadki zycia i wytrzymalosci auta maja byc duze jesli starcie jest na zakrecie, spore jak jest na zjezdzie,
// a standardowe dla pozostaych typow odcinkow (podjazd, odcinek prosty).

}


