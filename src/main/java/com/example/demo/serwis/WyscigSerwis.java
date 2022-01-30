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

    public void obslugaPojedynczegoOdcinka (Odcinek obecnyOdcinek, Odcinek nastepnyOdcinek, Samochod samochod, Kierowca kierowca ){
        przejazd(obecnyOdcinek,kierowca,samochod);
        samochod.szybkoscPrzejazduOdcinka(obecnyOdcinek);
        // starcie
        nastepnyOdcinek(obecnyOdcinek.getTypOdcinka(),nastepnyOdcinek,samochod,kierowca);
        //obecna predkosc
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
                        kierowca.setZycieKierowcy(kierowca.getZycieKierowcy() - Utils.losuj(0, 2));
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - Utils.losuj(0, 2));
                    }

                    if ((kierowca.getZnajomoscTrasy() + kierowca.getSzybkoscReakcji()) > 10 && kierowca.getRyzyko() > 8) {
                        kierowca.setZycieKierowcy(kierowca.getZycieKierowcy() - Utils.losuj(2, 5));
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 5);
                    }
                    if ((kierowca.getZnajomoscTrasy() + kierowca.getSzybkoscReakcji() <= 10) && kierowca.getRyzyko() <= 8) {
                        kierowca.setZycieKierowcy(kierowca.getZycieKierowcy() - Utils.losuj(5, 10));
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 10);
                    }
                    if ((kierowca.getZnajomoscTrasy() + kierowca.getSzybkoscReakcji()) <= 10 && kierowca.getRyzyko() > 8) {
                        kierowca.setZycieKierowcy(kierowca.getZycieKierowcy() - Utils.losuj(10, 15));
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
                            zmniejszanieZyciaKierowcyIwytrzymalosciSamochodu(samochod, kierowca);
                        }
                        if ((samochod.getSzybkosc() / samochod.getDrogaHamowania()) <= 2.5 && kierowca.getRyzyko() > 8) {
                            if (kierowca.getZnajomoscTrasy() <= 4 && kierowca.getSzybkoscReakcji() <= 6) {
                                kierowca.setZycieKierowcy(kierowca.getZycieKierowcy() - Utils.losuj(2, 5));
                                samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 5);
                            }
                        }
                        if ((samochod.getSzybkosc() / samochod.getDrogaHamowania()) > 2.5 && kierowca.getRyzyko() > 8) {
                            if (kierowca.getZnajomoscTrasy() <= 3 && kierowca.getSzybkoscReakcji() <= 4) {
                                kierowca.setZycieKierowcy(kierowca.getZycieKierowcy() - Utils.losuj(5, 10));
                                samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 10);
                            }
                        }
                        if ((samochod.getSzybkosc() / samochod.getDrogaHamowania()) > 2.5 && kierowca.getSzybkoscReakcji() <= 8) {
                            if (kierowca.getZnajomoscTrasy() <= 2 && kierowca.getSzybkoscReakcji() <= 3) {
                                kierowca.setZycieKierowcy(kierowca.getZycieKierowcy() - Utils.losuj(10, 15));
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
                            zmniejszanieZyciaKierowcyIwytrzymalosciSamochodu(samochod, kierowca,0,2,0,2);
                        }
                        if ((samochod.getSzybkosc() / samochod.getDrogaHamowania()) <= 2.5 && kierowca.getRyzyko() > 8) {
                            if (kierowca.getZnajomoscTrasy() <= 4 && kierowca.getSzybkoscReakcji() <= 6) {
                                kierowca.setZycieKierowcy(kierowca.getZycieKierowcy() - Utils.losuj(2, 5));
                                samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 5);
                            }
                        }
                        if ((samochod.getSzybkosc() / samochod.getDrogaHamowania()) > 2.5 && kierowca.getRyzyko() > 8) {
                            if (kierowca.getZnajomoscTrasy() <= 3 && kierowca.getSzybkoscReakcji() <= 4) {
                                kierowca.setZycieKierowcy(kierowca.getZycieKierowcy() - Utils.losuj(5, 10));
                                samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 10);
                            }
                        }
                        if ((samochod.getSzybkosc() / samochod.getDrogaHamowania()) > 2.5 && kierowca.getSzybkoscReakcji() <= 8) {
                            if (kierowca.getZnajomoscTrasy() <= 2 && kierowca.getSzybkoscReakcji() <= 3) {
                                kierowca.setZycieKierowcy(kierowca.getZycieKierowcy() - Utils.losuj(10, 15));
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
                            zmniejszanieZyciaKierowcyIwytrzymalosciSamochodu(samochod, kierowca, 0,2,0,2);
                        }
                        if ((samochod.getSzybkosc() / samochod.getDrogaHamowania()) <= 2.5 && kierowca.getRyzyko() > 8) {
                            if (kierowca.getZnajomoscTrasy() <= 4 && kierowca.getSzybkoscReakcji() <= 6) {
                                zmniejszanieZyciaKierowcyIwytrzymalosciSamochodu(samochod,kierowca,2,5,2,5);
                                samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 5);
                            }
                        }
                        if ((samochod.getSzybkosc() / samochod.getDrogaHamowania()) > 2.5 && kierowca.getRyzyko() > 8) {
                            if (kierowca.getZnajomoscTrasy() <= 3 && kierowca.getSzybkoscReakcji() <= 4) {
                                zmniejszanieZyciaKierowcyIwytrzymalosciSamochodu(samochod,kierowca,5,10,5,10
                                samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 10));
                            }
                        }
                        if ((samochod.getSzybkosc() / samochod.getDrogaHamowania()) > 2.5 && kierowca.getSzybkoscReakcji() <= 8) {
                            if (kierowca.getZnajomoscTrasy() <= 2 && kierowca.getSzybkoscReakcji() <= 3) {
                                /////////////////////////
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

    private void zmniejszanieZyciaKierowcyIwytrzymalosciSamochodu(Samochod samochod, Kierowca kierowca, int minZycieKierowca, int maxZycieKierowca, int minWytrzymaloscSamochodu, int maxWytrzymaloscSamochodu ) {
        kierowca.setZycieKierowcy(kierowca.getZycieKierowcy() - Utils.losuj(minZycieKierowca, maxZycieKierowca));
        samochod.setWytrzymaloscSamochodu(samochod.getWytrzymaloscSamochodu() - Utils.losuj(minWytrzymaloscSamochodu, maxWytrzymaloscSamochodu));
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



    }

    public void przejazdZakret(Odcinek odcinek, Kierowca kierowca, Samochod samochod) {
        if (odcinek.getTrudnoscOdcinka() >= 8 && kierowca.getSzybkoscReakcji() <= 6) {
            if (kierowca.getZnajomoscTrasy() <= 6 || kierowca.getRyzyko() >= 8) {
                kierowca.setZycieKierowcy(kierowca.getZycieKierowcy() - Utils.losuj(0, 5));
                samochod.setWytrzymaloscSamochodu(samochod.getWytrzymaloscSamochodu() - Utils.losuj(0, 5));
            }
            if (kierowca.getZnajomoscTrasy() <= 5 || kierowca.getRyzyko() >= 10) {
                kierowca.setZycieKierowcy(kierowca.getZycieKierowcy() - Utils.losuj(0, 4));
                samochod.setWytrzymaloscSamochodu(samochod.getWytrzymaloscSamochodu() - Utils.losuj(0, 4));
            } else if (odcinek.getTrudnoscOdcinka() >= 4 && kierowca.getSzybkoscReakcji() <= 5) {
                if (kierowca.getZnajomoscTrasy() <= 5 || kierowca.getRyzyko() >= 8) {
                    kierowca.setZycieKierowcy(kierowca.getZycieKierowcy() - Utils.losuj(0, 3));
                    samochod.setWytrzymaloscSamochodu(samochod.getWytrzymaloscSamochodu() - Utils.losuj(0, 3));
                }
                if (kierowca.getZnajomoscTrasy() <= 4 || kierowca.getRyzyko() >= 10) {
                    kierowca.setZycieKierowcy(kierowca.getZycieKierowcy() - Utils.losuj(0, 2));
                    samochod.setWytrzymaloscSamochodu(samochod.getWytrzymaloscSamochodu() - Utils.losuj(0, 2));
                }
            } else if (odcinek.getTrudnoscOdcinka() < 4 && kierowca.getSzybkoscReakcji() <= 4) {
                if (kierowca.getZnajomoscTrasy() <= 4 || kierowca.getRyzyko() >= 12) {
                    kierowca.setZycieKierowcy(kierowca.getZycieKierowcy() - Utils.losuj(0, 1));
                    samochod.setWytrzymaloscSamochodu(samochod.getWytrzymaloscSamochodu() - Utils.losuj(0, 1));
                }
            }
        }

    }

    public void przejazdZjazd(Odcinek odcinek, Kierowca kierowca, Samochod samochod) {
        if (odcinek.getTrudnoscOdcinka() > 3) {
            if (samochod.getDrogaHamowania() < 80 || kierowca.getZnajomoscTrasy() >= 8) {
                kierowca.setZycieKierowcy(kierowca.getZycieKierowcy() - Utils.losuj(0, 2));
                samochod.setWytrzymaloscSamochodu(samochod.getWytrzymaloscSamochodu() - Utils.losuj(0, 2));
            }

            if (samochod.getDrogaHamowania() >= 80 || kierowca.getZnajomoscTrasy() < 8) {
                kierowca.setZycieKierowcy(kierowca.getZycieKierowcy() - Utils.losuj(0, 5));
                samochod.setWytrzymaloscSamochodu(samochod.getWytrzymaloscSamochodu() - Utils.losuj(0, 5));
            }
        }
        if (odcinek.getTrudnoscOdcinka() <= 3) {
            if (samochod.getDrogaHamowania() < 80 || kierowca.getZnajomoscTrasy() >= 8) {
                kierowca.setZycieKierowcy(kierowca.getZycieKierowcy() - Utils.losuj(0, 1));
                samochod.setWytrzymaloscSamochodu(samochod.getWytrzymaloscSamochodu() - Utils.losuj(0, 1));
            }
            if (samochod.getDrogaHamowania() >= 80 || kierowca.getZnajomoscTrasy() < 8) {
                kierowca.setZycieKierowcy(kierowca.getZycieKierowcy() - Utils.losuj(0, 2));
                samochod.setWytrzymaloscSamochodu(samochod.getWytrzymaloscSamochodu() - Utils.losuj(0, 2));
            }
        }

    }

    public void przejazdPodjazd(Odcinek odcinek, Kierowca kierowca, Samochod samochod) {
        if (odcinek.getTrudnoscOdcinka() > 6) {
            if (samochod.getSzybkosc() > 200 || kierowca.getZnajomoscTrasy() >= 8) {
                kierowca.setZycieKierowcy(kierowca.getZycieKierowcy() - Utils.losuj(0, 2));
                samochod.setWytrzymaloscSamochodu(samochod.getWytrzymaloscSamochodu() - Utils.losuj(0, 2));
            }
            if (samochod.getSzybkosc() <= 200 || kierowca.getZnajomoscTrasy() < 8) {
                kierowca.setZycieKierowcy(kierowca.getZycieKierowcy() - Utils.losuj(0, 5));
                samochod.setWytrzymaloscSamochodu(samochod.getWytrzymaloscSamochodu() - Utils.losuj(0, 5));
            }
        }
        if (odcinek.getTrudnoscOdcinka() <= 6) {
            if (samochod.getSzybkosc() > 200 || kierowca.getZnajomoscTrasy() >= 8) {
                kierowca.setZycieKierowcy(kierowca.getZycieKierowcy() - Utils.losuj(0, 1));
                samochod.setWytrzymaloscSamochodu(samochod.getWytrzymaloscSamochodu() - Utils.losuj(0, 1));
            }
            if (samochod.getSzybkosc() <= 200 || kierowca.getZnajomoscTrasy() < 8) {
                kierowca.setZycieKierowcy(kierowca.getZycieKierowcy() - Utils.losuj(0, 2));
                samochod.setWytrzymaloscSamochodu(samochod.getWytrzymaloscSamochodu() - Utils.losuj(0, 2));
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

}


