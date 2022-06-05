package com.example.demo.serwis.wyscig;

import com.example.demo.model.kierowca.Kierowca;
import com.example.demo.model.odcinek.Odcinek;
import com.example.demo.model.samochod.Samochod;
import com.example.demo.model.odcinek.TypOdcinka;
import com.example.demo.model.Uczestnik;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WyscigNastepnySerwis {

    private final WyscigZycieSerwis wyscigZycieSerwis;

    public void nastepnyOdcinek(TypOdcinka obecnyOdcinek, Odcinek nastepnyOdcinek, Samochod samochod, Kierowca kierowca, List<Uczestnik> listaUczestnikow) {
        if (obecnyOdcinek == TypOdcinka.PROSTY) {
            obecnyProsty(nastepnyOdcinek, samochod, kierowca, listaUczestnikow);
        }
        if (obecnyOdcinek == TypOdcinka.ZAKRET) {
            obecnyZakret(nastepnyOdcinek, samochod, kierowca, listaUczestnikow);
        }
        if (obecnyOdcinek == TypOdcinka.ZJAZD) {
            obecnyZjazd(nastepnyOdcinek, samochod, kierowca, listaUczestnikow);
        }
        if (obecnyOdcinek == TypOdcinka.PODJAZD) {
            obecnyPodjazd(nastepnyOdcinek, samochod, kierowca, listaUczestnikow);
        }

    }

    private void obecnyProsty(Odcinek nastepnyOdcinek, Samochod samochod, Kierowca kierowca, List<Uczestnik> listaUczestnikow) {
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
                            wyscigZycieSerwis.zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(prosty, nastepnyOdcinekTypOdcinka, samochod, kierowca, 0, 2, 0, 2, listaUczestnikow);
                        }
                    }
                    if ((samochod.getSzybkosc() / samochod.getDrogaHamowania()) <= 2.5 && kierowca.getRyzyko() > 8) {
                        if (kierowca.getZnajomoscTrasy() <= 4 && kierowca.getSzybkoscReakcji() <= 6) {
                            wyscigZycieSerwis.zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(prosty, nastepnyOdcinekTypOdcinka, samochod, kierowca, 2, 5, 2, 5, listaUczestnikow);
                            samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 5, prosty, kierowca);
                        }
                    }
                    if ((samochod.getSzybkosc() / samochod.getDrogaHamowania()) > 2.5 && kierowca.getRyzyko() > 8) {
                        if (kierowca.getZnajomoscTrasy() <= 3 && kierowca.getSzybkoscReakcji() <= 4) {
                            wyscigZycieSerwis.zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(prosty, nastepnyOdcinekTypOdcinka, samochod, kierowca, 5, 10, 5, 10, listaUczestnikow);
                            samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 10, prosty, kierowca);
                        }
                    }
                    if ((samochod.getSzybkosc() / samochod.getDrogaHamowania()) > 2.5 && kierowca.getSzybkoscReakcji() <= 8) {
                        if (kierowca.getZnajomoscTrasy() <= 2 && kierowca.getSzybkoscReakcji() <= 3) {
                            wyscigZycieSerwis.zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(prosty, nastepnyOdcinekTypOdcinka, samochod, kierowca, 10, 15, 10, 15, listaUczestnikow);
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

    private void obecnyZakret(Odcinek nastepnyOdcinek, Samochod samochod, Kierowca kierowca, List<Uczestnik> listaUczestnikow) {
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
                            wyscigZycieSerwis.zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(zakret, nastepnyOdcinekTypOdcinka, samochod, kierowca, 0, 2, 0, 2, listaUczestnikow);
                        }
                    }
                    if ((samochod.getSzybkosc() / samochod.getDrogaHamowania()) <= 2.5 && kierowca.getRyzyko() > 8) {
                        if (kierowca.getZnajomoscTrasy() <= 4 && kierowca.getSzybkoscReakcji() <= 6) {
                            wyscigZycieSerwis.zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(zakret, nastepnyOdcinekTypOdcinka, samochod, kierowca, 2, 5, 2, 5, listaUczestnikow);
                            samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 5, zakret, kierowca);
                        }
                    }
                    if ((samochod.getSzybkosc() / samochod.getDrogaHamowania()) > 2.5 && kierowca.getRyzyko() > 8) {
                        if (kierowca.getZnajomoscTrasy() <= 3 && kierowca.getSzybkoscReakcji() <= 4) {
                            wyscigZycieSerwis.zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(zakret, nastepnyOdcinekTypOdcinka, samochod, kierowca, 5, 10, 5, 10, listaUczestnikow);
                            samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 10, zakret, kierowca);
                        }
                    }
                    if ((samochod.getSzybkosc() / samochod.getDrogaHamowania()) > 2.5 && kierowca.getSzybkoscReakcji() <= 8) {
                        if (kierowca.getZnajomoscTrasy() <= 2 && kierowca.getSzybkoscReakcji() <= 3) {
                            wyscigZycieSerwis.zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(zakret, nastepnyOdcinekTypOdcinka, samochod, kierowca, 10, 15, 10, 15, listaUczestnikow);
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

    private void obecnyZjazd(Odcinek nastepnyOdcinek, Samochod samochod, Kierowca kierowca, List<Uczestnik> listaUczestnikow) {
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
                            wyscigZycieSerwis.zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(zjazd, nastepnyOdcinekTypOdcinka, samochod, kierowca, 0, 2, 0, 2, listaUczestnikow);
                        }
                    }
                    if ((samochod.getSzybkosc() / samochod.getDrogaHamowania()) <= 2.5 && kierowca.getRyzyko() > 8) {
                        if (kierowca.getZnajomoscTrasy() <= 4 && kierowca.getSzybkoscReakcji() <= 6) {
                            wyscigZycieSerwis.zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(zjazd, nastepnyOdcinekTypOdcinka, samochod, kierowca, 2, 5, 2, 5, listaUczestnikow);
                            samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 5, zjazd, kierowca);
                        }
                    }
                    if ((samochod.getSzybkosc() / samochod.getDrogaHamowania()) > 2.5 && kierowca.getRyzyko() > 8) {
                        if (kierowca.getZnajomoscTrasy() <= 3 && kierowca.getSzybkoscReakcji() <= 4) {
                            wyscigZycieSerwis.zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(zjazd, nastepnyOdcinekTypOdcinka, samochod, kierowca, 5, 10, 5, 10, listaUczestnikow);
                            samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 10, zjazd, kierowca);
                        }
                    }
                    if ((samochod.getSzybkosc() / samochod.getDrogaHamowania()) > 2.5 && kierowca.getSzybkoscReakcji() <= 8) {
                        if (kierowca.getZnajomoscTrasy() <= 2 && kierowca.getSzybkoscReakcji() <= 3) {
                            wyscigZycieSerwis.zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(zjazd, nastepnyOdcinekTypOdcinka, samochod, kierowca, 10, 15, 10, 15, listaUczestnikow);
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

    private void obecnyPodjazd(Odcinek nastepnyOdcinek, Samochod samochod, Kierowca kierowca, List<Uczestnik> listaUczestnikow) {
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
                        wyscigZycieSerwis.zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(podjazd, nastepnyOdcinekTypOdcinka, samochod, kierowca, 0, 2, 0, 2, listaUczestnikow);
                    }
                    if ((kierowca.getZnajomoscTrasy() + kierowca.getSzybkoscReakcji()) > 10 && kierowca.getRyzyko() > 8) {
                        wyscigZycieSerwis.zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(podjazd, nastepnyOdcinekTypOdcinka, samochod, kierowca, 2, 5, 2, 5, listaUczestnikow);
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 5, podjazd, kierowca);
                    }
                    if ((kierowca.getZnajomoscTrasy() + kierowca.getSzybkoscReakcji() <= 10) && kierowca.getRyzyko() <= 8) {
                        wyscigZycieSerwis.zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(podjazd, nastepnyOdcinekTypOdcinka, samochod, kierowca, 5, 10, 5, 10, listaUczestnikow);
                        samochod.limitZmianyPredkosciIzmianaPredkosci(samochod.getSzybkosc() - 10, podjazd, kierowca);
                    }
                    if ((kierowca.getZnajomoscTrasy() + kierowca.getSzybkoscReakcji()) <= 10 && kierowca.getRyzyko() > 8) {
                        wyscigZycieSerwis.zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(podjazd, nastepnyOdcinekTypOdcinka, samochod, kierowca, 10, 15, 10, 15, listaUczestnikow);
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
}
