package com.example.demo.serwis;

import com.example.demo.model.*;
import com.example.demo.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class WyscigSerwis {

    public void tworzenieWyscigu() {
        System.out.println();
        Pogoda pogoda = PogodaSerwis.wylosujPogode();
        System.out.println();

        TrasaSerwis trasaSerwis = new TrasaSerwis();
        Trasa trasa = trasaSerwis.stworzTrase(pogoda, trasaSerwis.wylosujPoziomTrudnosci());


        KierowcaSerwis kierowcaSerwis = new KierowcaSerwis();
        SamochodSerwis samochodSerwis = new SamochodSerwis();
        UczestnikSerwis uczestnikSerwis = new UczestnikSerwis();
        List<Uczestnik> uczestnikList = uczestnikSerwis.stworzUczestnikow(kierowcaSerwis, samochodSerwis);
        //?????? czy tutaj
        uczestnikSerwis.wypisanieUczestnikow(uczestnikList);

        wyscig(uczestnikList, trasa);

    }

    public void wyscig(List<Uczestnik> listaUczestnikow, Trasa trasa) {


        System.out.println("Wpływ pogody na samochód:");
        for (Uczestnik uczestnik : listaUczestnikow) {
            System.out.println("Samochód uczestnika " + uczestnik.getKierowca() + " to: " + uczestnik.getSamochod());
            uczestnik.getSamochod().aktualizacjaSzybkosciIhamowania(trasa.getPogoda());

        }
        System.out.println();
        System.out.println("Wszyscy na starcie");
        for (Uczestnik uczestnik : listaUczestnikow) {
            System.out.println();
            obslugaPojedynczegoOdcinka(null, trasa.getListaOdcinkow().get(0), uczestnik.getSamochod(), uczestnik.getKierowca()); //start
        }

        for (int i = 0; i < trasa.getListaOdcinkow().size(); i++) {
            Integer nrOdcinka = i + 1;
            System.out.println();
            System.out.println("Przejazd odcinka nr " + nrOdcinka + " z " + trasa.getListaOdcinkow().size() + " (" + trasa.getListaOdcinkow().get(i).getNazwaOdcinka() + ")");

            Odcinek obecnyOdcinek = trasa.getListaOdcinkow().get(i);

            for (Uczestnik uczestnik : listaUczestnikow) {
                System.out.println();
                try {
                    Odcinek nastepnyOdcinek = trasa.getListaOdcinkow().get(i + 1);
                    obslugaPojedynczegoOdcinka(obecnyOdcinek, nastepnyOdcinek, uczestnik.getSamochod(), uczestnik.getKierowca());
                } catch (Exception e) {
                    obslugaPojedynczegoOdcinka(obecnyOdcinek, null, uczestnik.getSamochod(), uczestnik.getKierowca());
                }
            }
            starcie(listaUczestnikow, obecnyOdcinek);
            //TODO: tabela wyników
        }
    }


    private void obslugaPojedynczegoOdcinka(Odcinek obecnyOdcinek, Odcinek nastepnyOdcinek, Samochod samochod, Kierowca kierowca) {

        //start
        if (obecnyOdcinek == null) {
            System.out.println("Kierowca " + kierowca.getTypKierowcy() + " przekroczył linię startu i rozpoczął zmagania w jakże trudnym wyścigu");
            nastepnyOdcinek(obecnyOdcinek.getTypOdcinka(), nastepnyOdcinek, samochod, kierowca);
        }

        //wyscig
        if (obecnyOdcinek != null && nastepnyOdcinek != null) {
            przejazd(obecnyOdcinek, kierowca, samochod);
            samochod.dodajCzasPrzejazduOdcinka(samochod.szybkoscPrzejazduOdcinka(obecnyOdcinek));
            samochod.dodajPrzejechanyDystans(obecnyOdcinek);
            System.out.println("Kierowca " + kierowca.getTypKierowcy() + " przejechał odcinek " + obecnyOdcinek.getNazwaOdcinka() +
                    " w czasie " + samochod.szybkoscPrzejazduOdcinka(obecnyOdcinek) + " minuty");




            nastepnyOdcinek(obecnyOdcinek.getTypOdcinka(), nastepnyOdcinek, samochod, kierowca);


        }

        //meta - ostatni odcinek
        if (nastepnyOdcinek == null) {
            System.out.println("Już widać metę! Jeszcze tylko jeden odcinek pozostal " + kierowca.getTypKierowcy() + " do pokonania");
            przejazd(obecnyOdcinek, kierowca, samochod);
            samochod.dodajCzasPrzejazduOdcinka(samochod.szybkoscPrzejazduOdcinka(obecnyOdcinek));
            samochod.dodajPrzejechanyDystans(obecnyOdcinek);
            System.out.println("Kierowca " + kierowca.getTypKierowcy() + " przejechał odcinek " + obecnyOdcinek.getNazwaOdcinka() +
                    " w czasie " + samochod.szybkoscPrzejazduOdcinka(obecnyOdcinek) + " minuty");

        }
    }

    private void przejazd(Odcinek obecnyOdcinek, Kierowca kierowca, Samochod samochod) {
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


    private void przejazdOdcinekProsty(Odcinek odcinek, Kierowca kierowca, Samochod samochod) {
        String nazwaOdcinka = odcinek.getNazwaOdcinka();
        if (odcinek.getTrudnoscOdcinka() >= 1 && kierowca.getZnajomoscTrasy() <= 5) {
            if (kierowca.getSzybkoscReakcji() <= 3 || kierowca.getRyzyko() >= 8) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka, samochod, kierowca, 0, 3, 0, 3);
            }
            if (kierowca.getSzybkoscReakcji() <= 5 || kierowca.getRyzyko() >= 10) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka, samochod, kierowca, 0, 2, 0, 2);
            }
        } else if (odcinek.getTrudnoscOdcinka() == 0 && kierowca.getZnajomoscTrasy() <= 4) {
            if (kierowca.getSzybkoscReakcji() <= 4 || kierowca.getRyzyko() >= 14) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka, samochod, kierowca, 0, 1, 0, 1);
            }
        }
    }

    private void przejazdZakret(Odcinek odcinek, Kierowca kierowca, Samochod samochod) {
        String nazwaOdcinka = odcinek.getNazwaOdcinka();
        if (odcinek.getTrudnoscOdcinka() >= 8 && kierowca.getSzybkoscReakcji() <= 6) {
            if (kierowca.getZnajomoscTrasy() <= 6 || kierowca.getRyzyko() >= 8) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka, samochod, kierowca, 0, 5, 0, 5);
            }
            if (kierowca.getZnajomoscTrasy() <= 5 || kierowca.getRyzyko() >= 10) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka, samochod, kierowca, 0, 4, 0, 4);
            }
        } else if (odcinek.getTrudnoscOdcinka() >= 4 && kierowca.getSzybkoscReakcji() <= 5) {
            if (kierowca.getZnajomoscTrasy() <= 5 || kierowca.getRyzyko() >= 8) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka, samochod, kierowca, 0, 3, 0, 3);
            }
            if (kierowca.getZnajomoscTrasy() <= 4 || kierowca.getRyzyko() >= 10) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka, samochod, kierowca, 0, 2, 0, 2);
            }
        } else if (odcinek.getTrudnoscOdcinka() < 4 && kierowca.getSzybkoscReakcji() <= 4) {
            if (kierowca.getZnajomoscTrasy() <= 4 || kierowca.getRyzyko() >= 12) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka, samochod, kierowca, 0, 1, 0, 1);
            }
        }
    }

    private void przejazdZjazd(Odcinek odcinek, Kierowca kierowca, Samochod samochod) {
        String nazwaOdcinka = odcinek.getNazwaOdcinka();
        if (odcinek.getTrudnoscOdcinka() > 3) {
            if (samochod.getDrogaHamowania() < 80 || kierowca.getZnajomoscTrasy() >= 8) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka, samochod, kierowca, 0, 2, 0, 2);
            }
            if (samochod.getDrogaHamowania() >= 80 || kierowca.getZnajomoscTrasy() < 8) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka, samochod, kierowca, 0, 5, 0, 5);
            }
        }
        if (odcinek.getTrudnoscOdcinka() <= 3) {
            if (samochod.getDrogaHamowania() < 80 || kierowca.getZnajomoscTrasy() >= 8) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka, samochod, kierowca, 0, 1, 0, 1);
            }
            if (samochod.getDrogaHamowania() >= 80 || kierowca.getZnajomoscTrasy() < 8) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka, samochod, kierowca, 0, 2, 0, 2);
            }
        }

    }

    private void przejazdPodjazd(Odcinek odcinek, Kierowca kierowca, Samochod samochod) {
        String nazwaOdcinka = odcinek.getNazwaOdcinka();
        if (odcinek.getTrudnoscOdcinka() > 6) {
            if (samochod.getSzybkosc() > 200 || kierowca.getZnajomoscTrasy() >= 8) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka, samochod, kierowca, 0, 2, 0, 2);
            }
            if (samochod.getSzybkosc() <= 200 || kierowca.getZnajomoscTrasy() < 8) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka, samochod, kierowca, 0, 5, 0, 5);
            }
        }
        if (odcinek.getTrudnoscOdcinka() <= 6) {
            if (samochod.getSzybkosc() > 200 || kierowca.getZnajomoscTrasy() >= 8) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka, samochod, kierowca, 0, 1, 0, 1);
            }
            if (samochod.getSzybkosc() <= 200 || kierowca.getZnajomoscTrasy() < 8) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka, samochod, kierowca, 0, 2, 0, 2);
            }
        }
    }

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

    private void obecnyProsty(Odcinek nastepnyOdcinek, Samochod samochod, Kierowca kierowca) {
        TypOdcinka nastepnyOdcinekTypOdcinka = nastepnyOdcinek.getTypOdcinka();
        TypOdcinka prosty = TypOdcinka.PROSTY;

        switch (nastepnyOdcinek.getTypOdcinka()) {
            case PROSTY:
                if (nastepnyOdcinek.getTrudnoscOdcinka() <= 1) {
                    if (kierowca.getZnajomoscTrasy() >= 6) {
                        Integer nowaSzybkosc = samochod.getSzybkosc() + 5;
                        samochod.limitZmianyPredkosciIzmianaPredkosci(nowaSzybkosc, prosty, kierowca);
                    } else if (kierowca.getRyzyko() >= 10) {
                        Integer nowaSzybkosc = samochod.getSzybkosc() + 10;
                        samochod.limitZmianyPredkosciIzmianaPredkosci(nowaSzybkosc, prosty, kierowca);
                    }
                }
                break;
            case ZAKRET:
                if (nastepnyOdcinek.getTrudnoscOdcinka() >= 5) {
                    if ((samochod.getSzybkosc() / samochod.getDrogaHamowania()) <= 2.5 && kierowca.getRyzyko() <= 8) {
                        if (kierowca.getZnajomoscTrasy() <= 5 && kierowca.getSzybkoscReakcji() <= 8) {
                            zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(prosty, nastepnyOdcinekTypOdcinka, samochod, kierowca, 0, 2, 0, 2);
                        }
                    }
                    if ((samochod.getSzybkosc() / samochod.getDrogaHamowania()) <= 2.5 && kierowca.getRyzyko() > 8) {
                        if (kierowca.getZnajomoscTrasy() <= 4 && kierowca.getSzybkoscReakcji() <= 6) {
                            zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(prosty, nastepnyOdcinekTypOdcinka, samochod, kierowca, 2, 5, 2, 5);
                            samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 5, prosty, kierowca);
                        }
                    }
                    if ((samochod.getSzybkosc() / samochod.getDrogaHamowania()) > 2.5 && kierowca.getRyzyko() > 8) {
                        if (kierowca.getZnajomoscTrasy() <= 3 && kierowca.getSzybkoscReakcji() <= 4) {
                            zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(prosty, nastepnyOdcinekTypOdcinka, samochod, kierowca, 5, 10, 5, 10);
                            samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 10, prosty, kierowca);
                        }
                    }
                    if ((samochod.getSzybkosc() / samochod.getDrogaHamowania()) > 2.5 && kierowca.getSzybkoscReakcji() <= 8) {
                        if (kierowca.getZnajomoscTrasy() <= 2 && kierowca.getSzybkoscReakcji() <= 3) {
                            zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(prosty, nastepnyOdcinekTypOdcinka, samochod, kierowca, 10, 15, 10, 15);
                            samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 15, prosty, kierowca);
                        }

                    }
                }
                break;
            case ZJAZD:
                if (nastepnyOdcinek.getTrudnoscOdcinka() <= 5) {
                    if (kierowca.getRyzyko() <= 10) {
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() + 5, prosty, kierowca);
                    } else if (kierowca.getRyzyko() > 10) {
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() + 10, prosty, kierowca);
                    }
                }
                break;
            case PODJAZD:
                if (nastepnyOdcinek.getTrudnoscOdcinka() >= 6) {
                    if (samochod.getSzybkosc() <= 150 || samochod.getCiezar() >= 1600) {
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 10, prosty, kierowca);
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
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() + 5, zakret, kierowca);
                    } else if (kierowca.getRyzyko() >= 15) {
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() + 5, zakret, kierowca);
                    }
                }
                break;
            case ZAKRET:
                if (nastepnyOdcinek.getTrudnoscOdcinka() >= 6) {
                    if ((samochod.getSzybkosc() / samochod.getDrogaHamowania()) <= 2.5 && kierowca.getRyzyko() <= 8) {
                        if (kierowca.getZnajomoscTrasy() <= 5 && kierowca.getSzybkoscReakcji() <= 8) {
                            zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(zakret, nastepnyOdcinekTypOdcinka, samochod, kierowca, 0, 2, 0, 2);
                        }
                    }
                    if ((samochod.getSzybkosc() / samochod.getDrogaHamowania()) <= 2.5 && kierowca.getRyzyko() > 8) {
                        if (kierowca.getZnajomoscTrasy() <= 4 && kierowca.getSzybkoscReakcji() <= 6) {
                            zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(zakret, nastepnyOdcinekTypOdcinka, samochod, kierowca, 2, 5, 2, 5);
                            samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 5, zakret, kierowca);
                        }
                    }
                    if ((samochod.getSzybkosc() / samochod.getDrogaHamowania()) > 2.5 && kierowca.getRyzyko() > 8) {
                        if (kierowca.getZnajomoscTrasy() <= 3 && kierowca.getSzybkoscReakcji() <= 4) {
                            zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(zakret, nastepnyOdcinekTypOdcinka, samochod, kierowca, 5, 10, 5, 10);
                            samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 10, zakret, kierowca);
                        }
                    }
                    if ((samochod.getSzybkosc() / samochod.getDrogaHamowania()) > 2.5 && kierowca.getSzybkoscReakcji() <= 8) {
                        if (kierowca.getZnajomoscTrasy() <= 2 && kierowca.getSzybkoscReakcji() <= 3) {
                            zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(zakret, nastepnyOdcinekTypOdcinka, samochod, kierowca, 10, 15, 10, 15);
                            samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 15, zakret, kierowca);
                        }
                    }
                }
                if (nastepnyOdcinek.getTrudnoscOdcinka() < 6) {
                    if (kierowca.getRyzyko() <= 10) {
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 5, zakret, kierowca);
                    }
                }
                break;
            case ZJAZD:
                if (nastepnyOdcinek.getTrudnoscOdcinka() <= 7) {
                    if (kierowca.getRyzyko() <= 8) {
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() + 5, zakret, kierowca);
                    }
                    if (kierowca.getRyzyko() > 8) {
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() + 10, zakret, kierowca);
                    }
                }
                break;
            case PODJAZD:
                if (nastepnyOdcinek.getTrudnoscOdcinka() >= 4) {
                    if (samochod.getSzybkosc() <= 170 || samochod.getCiezar() >= 1200) {
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 10, zakret, kierowca);
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
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() + 5, zjazd, kierowca);
                    }
                }
                break;
            case ZAKRET:
                if (nastepnyOdcinek.getTrudnoscOdcinka() >= 2) {
                    if ((samochod.getSzybkosc() / samochod.getDrogaHamowania()) <= 2.5 && kierowca.getRyzyko() <= 8) {
                        if (kierowca.getZnajomoscTrasy() <= 5 && kierowca.getSzybkoscReakcji() <= 8) {
                            zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(zjazd, nastepnyOdcinekTypOdcinka, samochod, kierowca, 0, 2, 0, 2);
                        }
                    }
                    if ((samochod.getSzybkosc() / samochod.getDrogaHamowania()) <= 2.5 && kierowca.getRyzyko() > 8) {
                        if (kierowca.getZnajomoscTrasy() <= 4 && kierowca.getSzybkoscReakcji() <= 6) {
                            zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(zjazd, nastepnyOdcinekTypOdcinka, samochod, kierowca, 2, 5, 2, 5);
                            samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 5, zjazd, kierowca);
                        }
                    }
                    if ((samochod.getSzybkosc() / samochod.getDrogaHamowania()) > 2.5 && kierowca.getRyzyko() > 8) {
                        if (kierowca.getZnajomoscTrasy() <= 3 && kierowca.getSzybkoscReakcji() <= 4) {
                            zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(zjazd, nastepnyOdcinekTypOdcinka, samochod, kierowca, 5, 10, 5, 10);
                            samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 10, zjazd, kierowca);
                        }
                    }
                    if ((samochod.getSzybkosc() / samochod.getDrogaHamowania()) > 2.5 && kierowca.getSzybkoscReakcji() <= 8) {
                        if (kierowca.getZnajomoscTrasy() <= 2 && kierowca.getSzybkoscReakcji() <= 3) {
                            zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(zjazd, nastepnyOdcinekTypOdcinka, samochod, kierowca, 10, 15, 10, 15);
                            samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 15, zjazd, kierowca);
                        }
                    }
                }
                break;
            case ZJAZD:
                if (nastepnyOdcinek.getTrudnoscOdcinka() <= 4) {
                    if (kierowca.getRyzyko() <= 8) {
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() + 5, zjazd, kierowca);
                    }
                    if (kierowca.getRyzyko() > 8) {
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() + 10, zjazd, kierowca);
                    }
                }
                if (nastepnyOdcinek.getTrudnoscOdcinka() > 4) {
                    if (kierowca.getRyzyko() <= 8) {
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 10, zjazd, kierowca);
                    }
                }
                break;
            case PODJAZD:
                if (nastepnyOdcinek.getTrudnoscOdcinka() >= 5) {
                    if (samochod.getSzybkosc() <= 170 || samochod.getCiezar() >= 1600) {
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 10, zjazd, kierowca);
                    }
                }
                break;
            default:
                //meta bo ostatni odcinek
                break;
        }
    }

    private void obecnyPodjazd(Odcinek nastepnyOdcinek, Samochod samochod, Kierowca kierowca) {
        TypOdcinka podjazd = TypOdcinka.PODJAZD;
        TypOdcinka nastepnyOdcinekTypOdcinka = nastepnyOdcinek.getTypOdcinka();

        switch (nastepnyOdcinek.getTypOdcinka()) {
            case PROSTY:
                if (nastepnyOdcinek.getTrudnoscOdcinka() <= 1) {
                    if (kierowca.getZnajomoscTrasy() >= 4) {
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() + 5, podjazd, kierowca);
                    }
                    if (kierowca.getZnajomoscTrasy() < 4) {
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 4, podjazd, kierowca);
                    }
                }
                break;
            case ZAKRET:
                if (nastepnyOdcinek.getTrudnoscOdcinka() <= 6) {
                    if ((kierowca.getZnajomoscTrasy() + kierowca.getSzybkoscReakcji()) > 10 && kierowca.getRyzyko() <= 8) {
                        zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(podjazd, nastepnyOdcinekTypOdcinka, samochod, kierowca, 0, 2, 0, 2);
                    }
                    if ((kierowca.getZnajomoscTrasy() + kierowca.getSzybkoscReakcji()) > 10 && kierowca.getRyzyko() > 8) {
                        zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(podjazd, nastepnyOdcinekTypOdcinka, samochod, kierowca, 2, 5, 2, 5);
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 5, podjazd, kierowca);
                    }
                    if ((kierowca.getZnajomoscTrasy() + kierowca.getSzybkoscReakcji() <= 10) && kierowca.getRyzyko() <= 8) {
                        zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(podjazd, nastepnyOdcinekTypOdcinka, samochod, kierowca, 5, 10, 5, 10);
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 10, podjazd, kierowca);
                    }
                    if ((kierowca.getZnajomoscTrasy() + kierowca.getSzybkoscReakcji()) <= 10 && kierowca.getRyzyko() > 8) {
                        zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(podjazd, nastepnyOdcinekTypOdcinka, samochod, kierowca, 10, 15, 10, 15);
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 15, podjazd, kierowca);
                    }
                }
                break;
            case ZJAZD:
                if (nastepnyOdcinek.getTrudnoscOdcinka() <= 7) {
                    if (kierowca.getRyzyko() <= 8) {
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() + 5, podjazd, kierowca);
                    }
                    if (kierowca.getRyzyko() > 8) {
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() + 10, podjazd, kierowca);
                    }
                }
                break;
            case PODJAZD:
                if (nastepnyOdcinek.getTrudnoscOdcinka() <= 5) {
                    if (samochod.getSzybkosc() <= 200 || samochod.getCiezar() >= 1400) {
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 10, podjazd, kierowca);
                    }
                }
                if (nastepnyOdcinek.getTrudnoscOdcinka() > 5) {
                    if (samochod.getSzybkosc() <= 180 || samochod.getCiezar() >= 1200) {
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 20, podjazd, kierowca);
                    }
                }
                break;
            default:
                //meta bo ostatni odcinek
                break;
        }
    }

    //TODO: metoda która usuwa z listyuczestników jak życie lub samochód mniejsze niż 0
    private void zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(String nazwaOdcinka, Samochod samochod, Kierowca kierowca,
                                                                          int minZycieKierowca, int maxZycieKierowca, int minWytrzymaloscSamochodu,
                                                                          int maxWytrzymaloscSamochodu,List<Uczestnik> listaUczestnikow) {
        kierowca.aktualizacjaZycia(minZycieKierowca, maxZycieKierowca);
        samochod.aktualizacjaWytrzymalosci(minWytrzymaloscSamochodu, maxWytrzymaloscSamochodu);
        System.out.println("Kierowca " + kierowca.getTypKierowcy() + " jadący samochodem " + samochod.getTypSamochodu() + " pokonując odcinek " +
                nazwaOdcinka + " odniósł obrażenia i teraz jego życie wynosi " + kierowca.getZycieKierowcy() + ", " +
                "natomiast samochód uległ uszkodzeniu i jego wytrzymałość wynosi " + samochod.getWytrzymaloscSamochodu());
    }

    //TODO: dla tej metody zmienić aktualizacja życia i wytrzymałości

    private void zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduStarcie(String nazwaZdarzenia, String nazwaOdcinka, Uczestnik uczestnik, Uczestnik innyUczestnik,
                                                                         int minZycieKierowca, int maxZycieKierowca, int minWytrzymaloscSamochodu, int maxWytrzymaloscSamochodu) {

        uczestnik.getKierowca().setZycieKierowcy(uczestnik.getKierowca().getZycieKierowcy() - Utils.losuj(minZycieKierowca, maxZycieKierowca));
        innyUczestnik.getKierowca().setZycieKierowcy(innyUczestnik.getKierowca().getZycieKierowcy() - Utils.losuj(minZycieKierowca, maxZycieKierowca));
        uczestnik.getSamochod().setWytrzymaloscSamochodu(uczestnik.getSamochod().getWytrzymaloscSamochodu() - Utils.losuj(minWytrzymaloscSamochodu, maxWytrzymaloscSamochodu));
        innyUczestnik.getSamochod().setWytrzymaloscSamochodu(innyUczestnik.getSamochod().getWytrzymaloscSamochodu() - Utils.losuj(minWytrzymaloscSamochodu, maxWytrzymaloscSamochodu));
        System.out.println("Kierowca " + uczestnik.getKierowca().getTypKierowcy() + " jadący samochodem " + uczestnik.getSamochod().getTypSamochodu() +
                " pokonujący odcinek " + nazwaOdcinka + " miał " + nazwaZdarzenia + " z " + innyUczestnik.getKierowca().getTypKierowcy() + " jadącym samochodem "
                + innyUczestnik.getSamochod().getTypSamochodu() + ".");
        skutkiStarcia(uczestnik, innyUczestnik);
    }

    private void zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(TypOdcinka obecnyOdcinek, TypOdcinka nastepnyOdcinek, Samochod samochod, Kierowca kierowca, int minZycieKierowca, int maxZycieKierowca, int minWytrzymaloscSamochodu, int maxWytrzymaloscSamochodu) {
        kierowca.aktualizacjaZycia(minZycieKierowca,maxZycieKierowca);
        samochod.aktualizacjaWytrzymalosci(minWytrzymaloscSamochodu, maxWytrzymaloscSamochodu);
        System.out.println("Kierowca " + kierowca.getTypKierowcy() + " jadący samochodem " + samochod.getTypSamochodu() + " pokonując odcinek " +
                obecnyOdcinek + " nie dostosował swoich umiejętności i prędkości samochodu do zbliżającego się odcinka " + nastepnyOdcinek +
                " w wyniku czego odniósł obrażenia. Teraz jego życie wynosi " + kierowca.getZycieKierowcy() +
                ", a samochód uległ uszkodzeniu i jego wytrzymałość wynosi " + samochod.getWytrzymaloscSamochodu());
    }

    public void starcie(List<Uczestnik> listaUczestnikow, Odcinek odcinek) {
        if (Utils.losuj(1, 4) == 1) {
            for (Uczestnik uczestnik :
                    listaUczestnikow) {
                for (Uczestnik zawodnik :
                        listaUczestnikow) {
                    if (!uczestnik.equals(zawodnik)) {
                        aktualizacjaKierowcaSamochodStarcie(uczestnik, zawodnik, odcinek);
                        System.out.println(uczestnik.getKierowca().getTypKierowcy() + " " + zawodnik.getKierowca().getTypKierowcy());

                    }

                }
            }
        }
    }

    private void skutkiStarcia(Uczestnik uczestnik, Uczestnik innyUczestnik) {
        if (uczestnik.getKierowca().getZycieKierowcy() > 0 && innyUczestnik.getKierowca().getZycieKierowcy() > 0
                && uczestnik.getSamochod().getWytrzymaloscSamochodu() > 0 && innyUczestnik.getSamochod().getWytrzymaloscSamochodu() > 0) {
            System.out.println("Obaj odnieśli obrażenia i teraz ich życie wynosi " + uczestnik.getKierowca().getZycieKierowcy() + " " + innyUczestnik.getKierowca().getZycieKierowcy() +
                    " natomiast samochody uległy uszkodzeniu i ich wytrzymałości wynoszą: " + uczestnik.getSamochod().getWytrzymaloscSamochodu() + " " + innyUczestnik.getSamochod().getWytrzymaloscSamochodu() + ".");
        } else if (uczestnik.getKierowca().getZycieKierowcy() <= 0 && innyUczestnik.getKierowca().getZycieKierowcy() > 0
                && uczestnik.getSamochod().getWytrzymaloscSamochodu() > 0 && innyUczestnik.getSamochod().getWytrzymaloscSamochodu() > 0) {
            System.out.println("Kierowca " + uczestnik.getKierowca().getTypKierowcy() + " nie wytrzymał trudów wyścigu i muszą się nim zająć służby medyczne. Kierowca " + innyUczestnik.getKierowca().getTypKierowcy() +
                    " odniósł obrażenia i teraz jego życie wynosi " + innyUczestnik.getKierowca().getZycieKierowcy() + " natomiast samochody uległy uszkodzeniu i ich wytrzymałości wynoszą: " +
                    uczestnik.getSamochod().getWytrzymaloscSamochodu() + " " + innyUczestnik.getSamochod().getWytrzymaloscSamochodu() + ".");
        } else if (uczestnik.getKierowca().getZycieKierowcy() > 0 && innyUczestnik.getKierowca().getZycieKierowcy() <= 0
                && uczestnik.getSamochod().getWytrzymaloscSamochodu() > 0 && innyUczestnik.getSamochod().getWytrzymaloscSamochodu() > 0) {
            System.out.println("Kierowca " + innyUczestnik.getKierowca().getTypKierowcy() + " nie wytrzymał trudów wyścigu i muszą się nim zająć służby medyczne. Kierowca " + uczestnik.getKierowca().getTypKierowcy() +
                    " odniósł obrażenia i teraz jego życie wynosi " + innyUczestnik.getKierowca().getZycieKierowcy() + " natomiast samochody uległy uszkodzeniu i ich wytrzymałości wynoszą: " +
                    uczestnik.getSamochod().getWytrzymaloscSamochodu() + " " + innyUczestnik.getSamochod().getWytrzymaloscSamochodu() + ".");
        } else if (uczestnik.getKierowca().getZycieKierowcy() > 0 && innyUczestnik.getKierowca().getZycieKierowcy() > 0
                && uczestnik.getSamochod().getWytrzymaloscSamochodu() <= 0 && innyUczestnik.getSamochod().getWytrzymaloscSamochodu() > 0) {
            System.out.println("Obaj odnieśli obrażenia i teraz ich życie wynosi " + uczestnik.getKierowca().getZycieKierowcy() + " " + innyUczestnik.getKierowca().getZycieKierowcy() +
                    " natomiast samochód " + uczestnik.getSamochod().getTypSamochodu() + " nie wytrzymał trudów wyścigu i nadaje się już tylko na złom, a samochód " + innyUczestnik.getSamochod().getTypSamochodu() + " uległ uszkodzeniu i jego wytrzymałość wynosi: " + innyUczestnik.getSamochod().getWytrzymaloscSamochodu() + ".");
        } else if (uczestnik.getKierowca().getZycieKierowcy() > 0 && innyUczestnik.getKierowca().getZycieKierowcy() > 0
                && uczestnik.getSamochod().getWytrzymaloscSamochodu() > 0 && innyUczestnik.getSamochod().getWytrzymaloscSamochodu() <= 0) {
            System.out.println("Obaj odnieśli obrażenia i teraz ich życie wynosi " + uczestnik.getKierowca().getZycieKierowcy() + " " + innyUczestnik.getKierowca().getZycieKierowcy() +
                    " natomiast samochód " + innyUczestnik.getSamochod().getTypSamochodu() + " nie wytrzymał trudów wyścigu i nadaje się już tylko na złom, a samochód " + uczestnik.getSamochod().getTypSamochodu() + " uległ uszkodzeniu i jego wytrzymałość wynosi: " + uczestnik.getSamochod().getWytrzymaloscSamochodu() + ".");
        } else if (uczestnik.getKierowca().getZycieKierowcy() <= 0 && innyUczestnik.getKierowca().getZycieKierowcy() <= 0
                && uczestnik.getSamochod().getWytrzymaloscSamochodu() > 0 && innyUczestnik.getSamochod().getWytrzymaloscSamochodu() > 0) {
            System.out.println("Obaj kierowcy nie wytrzymali trudów wyścigu i muszą się nimi zająć służby medyczne. Natomiast samochody uległy uszkodzeniu i ich wytrzymałości wynoszą: " +
                    uczestnik.getSamochod().getWytrzymaloscSamochodu() + " " + innyUczestnik.getSamochod().getWytrzymaloscSamochodu() + ".");
        } else if (uczestnik.getKierowca().getZycieKierowcy() > 0 && innyUczestnik.getKierowca().getZycieKierowcy() > 0
                && uczestnik.getSamochod().getWytrzymaloscSamochodu() <= 0 && innyUczestnik.getSamochod().getWytrzymaloscSamochodu() <= 0) {
            System.out.println("Obaj odnieśli obrażenia i teraz ich życie wynosi " + uczestnik.getKierowca().getZycieKierowcy() + " " + innyUczestnik.getKierowca().getZycieKierowcy() +
                    " .Natomiast samochody nie wytrzymały trudów wyścigu i nadają się już tylko na złom.");
        } else if (uczestnik.getKierowca().getZycieKierowcy() <= 0 && innyUczestnik.getKierowca().getZycieKierowcy() > 0
                && uczestnik.getSamochod().getWytrzymaloscSamochodu() <= 0 && innyUczestnik.getSamochod().getWytrzymaloscSamochodu() > 0) {
            System.out.println("Kierowca " + uczestnik.getKierowca().getTypKierowcy() + " nie wytrzymał trudów wyścigu i muszą się nim zająć służby medyczne, a jego samochód " + uczestnik.getSamochod().getTypSamochodu() + " nadaje się już tylko na złom. Natomiast kierowca " + innyUczestnik.getKierowca().getTypKierowcy() +
                    " odniósł obrażenia i teraz jego życie wynosi " + innyUczestnik.getKierowca().getZycieKierowcy() + " a jego samochód uległ uszkodzeniu i jego wytrzymałość wynosi: " + innyUczestnik.getSamochod().getWytrzymaloscSamochodu() + ".");
        } else if (uczestnik.getKierowca().getZycieKierowcy() > 0 && innyUczestnik.getKierowca().getZycieKierowcy() <= 0
                && uczestnik.getSamochod().getWytrzymaloscSamochodu() > 0 && innyUczestnik.getSamochod().getWytrzymaloscSamochodu() <= 0) {
            System.out.println("Kierowca " + innyUczestnik.getKierowca().getTypKierowcy() + " nie wytrzymał trudów wyścigu i muszą się nim zająć służby medyczne, a jego samochód " + innyUczestnik.getSamochod().getTypSamochodu() + " nadaje się już tylko na złom. Natomiast kierowca " + uczestnik.getKierowca().getTypKierowcy() +
                    " odniósł obrażenia i teraz jego życie wynosi " + uczestnik.getKierowca().getZycieKierowcy() + " a jego samochód uległ uszkodzeniu i jego wytrzymałość wynosi: " + uczestnik.getSamochod().getWytrzymaloscSamochodu() + ".");
        } else if (uczestnik.getKierowca().getZycieKierowcy() <= 0 && innyUczestnik.getKierowca().getZycieKierowcy() > 0
                && uczestnik.getSamochod().getWytrzymaloscSamochodu() > 0 && innyUczestnik.getSamochod().getWytrzymaloscSamochodu() <= 0) {
            System.out.println("Kierowca " + uczestnik.getKierowca().getTypKierowcy() + " nie wytrzymał trudów wyścigu i muszą się nim zająć służby medyczne, a jego samochód uległ uszkodzeniu i jego wytrzymałość wynosi: " + uczestnik.getSamochod().getWytrzymaloscSamochodu() +
                    "Natomiast kierowca " + innyUczestnik.getKierowca().getTypKierowcy() + " odniósł obrażenia i teraz jego życie wynosi " + innyUczestnik.getKierowca().getZycieKierowcy() + " ,ale jego samochód " + innyUczestnik.getSamochod().getTypSamochodu() + " nadaje się już tylko na złom.");
        } else if (uczestnik.getKierowca().getZycieKierowcy() > 0 && innyUczestnik.getKierowca().getZycieKierowcy() <= 0
                && uczestnik.getSamochod().getWytrzymaloscSamochodu() <= 0 && innyUczestnik.getSamochod().getWytrzymaloscSamochodu() > 0) {
            System.out.println("Kierowca " + innyUczestnik.getKierowca().getTypKierowcy() + " nie wytrzymał trudów wyścigu i muszą się nim zająć służby medyczne, a jego samochód uległ uszkodzeniu i jego wytrzymałość wynosi: " + innyUczestnik.getSamochod().getWytrzymaloscSamochodu() +
                    "Natomiast kierowca " + uczestnik.getKierowca().getTypKierowcy() + " odniósł obrażenia i teraz jego życie wynosi " + uczestnik.getKierowca().getZycieKierowcy() + " ,ale jego samochód " + uczestnik.getSamochod().getTypSamochodu() + " nadaje się już tylko na złom.");
        }
    }

    private void aktualizacjaKierowcaSamochodStarcie(Uczestnik uczestnik, Uczestnik innyUczestnik, Odcinek obecnyOdcinek) {

        String nazwaOdcinka = obecnyOdcinek.getNazwaOdcinka();
        if ((uczestnik.getSamochod().getCzasPrzejazdu() - innyUczestnik.getSamochod().getCzasPrzejazdu()) > 2.0 && (uczestnik.getSamochod().getCzasPrzejazdu() - innyUczestnik.getSamochod().getCzasPrzejazdu() <= 5.0)) {
            String nazwaZdarzenia = "wypadek";
            System.out.println("Wypadek!");
            if (obecnyOdcinek.getTypOdcinka() == TypOdcinka.ZAKRET) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduStarcie(nazwaZdarzenia, nazwaOdcinka, uczestnik, innyUczestnik, 25, 50, 25, 50);
            }
            if (obecnyOdcinek.getTypOdcinka() == TypOdcinka.ZJAZD) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduStarcie(nazwaZdarzenia, nazwaOdcinka, uczestnik, innyUczestnik, 15, 30, 15, 30);
            }
            if (obecnyOdcinek.getTypOdcinka() == TypOdcinka.PODJAZD) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduStarcie(nazwaZdarzenia, nazwaOdcinka, uczestnik, innyUczestnik, 5, 15, 5, 15);
            }
            if (obecnyOdcinek.getTypOdcinka() == TypOdcinka.PROSTY) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduStarcie(nazwaZdarzenia, nazwaOdcinka, uczestnik, innyUczestnik, 0, 5, 0, 5);
            }
        } else if ((uczestnik.getSamochod().getCzasPrzejazdu() - innyUczestnik.getSamochod().getCzasPrzejazdu()) > 1.0 && (uczestnik.getSamochod().getCzasPrzejazdu() - innyUczestnik.getSamochod().getCzasPrzejazdu() <= 2.0)) {
            String nazwaZdarzenia = "stłuczkę";
            System.out.println("Stłuczka!");
            if (obecnyOdcinek.getTypOdcinka() == TypOdcinka.ZAKRET) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduStarcie(nazwaZdarzenia, nazwaOdcinka, uczestnik, innyUczestnik, 15, 30, 15, 30);
            }
            if (obecnyOdcinek.getTypOdcinka() == TypOdcinka.ZJAZD) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduStarcie(nazwaZdarzenia, nazwaOdcinka, uczestnik, innyUczestnik, 10, 15, 10, 15);
            }
            if (obecnyOdcinek.getTypOdcinka() == TypOdcinka.PODJAZD) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduStarcie(nazwaZdarzenia, nazwaOdcinka, uczestnik, innyUczestnik, 5, 10, 5, 10);
            }
            if (obecnyOdcinek.getTypOdcinka() == TypOdcinka.PROSTY) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduStarcie(nazwaZdarzenia, nazwaOdcinka, uczestnik, innyUczestnik, 2, 5, 2, 5);
            }

        } else if ((uczestnik.getSamochod().getCzasPrzejazdu() - innyUczestnik.getSamochod().getCzasPrzejazdu()) <= 1.0) {
            String nazwaZdarzenia = "obtarcie";
            System.out.println("Obtarcie!");
            if (obecnyOdcinek.getTypOdcinka() == TypOdcinka.ZAKRET) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduStarcie(nazwaZdarzenia, nazwaOdcinka, uczestnik, innyUczestnik, 10, 15, 10, 15);
            }
            if (obecnyOdcinek.getTypOdcinka() == TypOdcinka.ZJAZD) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduStarcie(nazwaZdarzenia, nazwaOdcinka, uczestnik, innyUczestnik, 5, 10, 5, 10);
            }
            if (obecnyOdcinek.getTypOdcinka() == TypOdcinka.PODJAZD) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduStarcie(nazwaZdarzenia, nazwaOdcinka, uczestnik, innyUczestnik, 2, 5, 2, 5);
            }
            if (obecnyOdcinek.getTypOdcinka() == TypOdcinka.PROSTY) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduStarcie(nazwaZdarzenia, nazwaOdcinka, uczestnik, innyUczestnik, 0, 2, 0, 2);
            }
        }
    }




}

