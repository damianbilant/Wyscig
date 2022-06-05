package com.example.demo.serwis.wyscig;

import com.example.demo.model.kierowca.Kierowca;
import com.example.demo.model.odcinek.Odcinek;
import com.example.demo.model.samochod.Samochod;
import com.example.demo.model.odcinek.TypOdcinka;
import com.example.demo.model.Uczestnik;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WyscigPrzejazdSerwis {

    private WyscigZycieSerwis wyscigZycieSerwis;

    public WyscigPrzejazdSerwis(WyscigZycieSerwis wyscigZycieSerwis) {
        this.wyscigZycieSerwis = wyscigZycieSerwis;
    }

    public void przejazd(Odcinek obecnyOdcinek, Kierowca kierowca, Samochod samochod, List<Uczestnik> listaUczestnikow) {
        switch (obecnyOdcinek.getTypOdcinka()) {
            case PITSTOP:
                przejazdPitstop(obecnyOdcinek, kierowca, samochod);
                break;
            case PROSTY:
                przejazdOdcinekProsty(obecnyOdcinek, kierowca, samochod, listaUczestnikow);
                break;
            case ZAKRET:
                przejazdZakret(obecnyOdcinek, kierowca, samochod, listaUczestnikow);
                break;
            case ZJAZD:
                przejazdZjazd(obecnyOdcinek, kierowca, samochod, listaUczestnikow);
                break;
            case PODJAZD:
                przejazdPodjazd(obecnyOdcinek, kierowca, samochod, listaUczestnikow);
                break;
            default:
                break;
        }
    }

    private void przejazdOdcinekProsty(Odcinek odcinek, Kierowca kierowca, Samochod samochod, List<Uczestnik> listaUczestnikow) {
        String nazwaOdcinka = odcinek.getNazwaOdcinka();
        if (odcinek.getTrudnoscOdcinka() >= 1 && kierowca.getZnajomoscTrasy() <= 5) {
            if (kierowca.getSzybkoscReakcji() <= 3 || kierowca.getRyzyko() >= 8) {
                wyscigZycieSerwis.zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka, samochod, kierowca, 0, 3, 0, 3, listaUczestnikow);
            } else if (kierowca.getSzybkoscReakcji() <= 5 || kierowca.getRyzyko() >= 10) {
                wyscigZycieSerwis.zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka, samochod, kierowca, 0, 2, 0, 2, listaUczestnikow);
            }
        } else if (odcinek.getTrudnoscOdcinka() == 0 && kierowca.getZnajomoscTrasy() <= 4) {
            if (kierowca.getSzybkoscReakcji() <= 4 || kierowca.getRyzyko() >= 14) {
                wyscigZycieSerwis.zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka, samochod, kierowca, 0, 1, 0, 1, listaUczestnikow);
            }
        }
    }

    private void przejazdZakret(Odcinek odcinek, Kierowca kierowca, Samochod samochod, List<Uczestnik> listaUczestnikow) {
        String nazwaOdcinka = odcinek.getNazwaOdcinka();
        if (odcinek.getTrudnoscOdcinka() >= 8 && kierowca.getSzybkoscReakcji() <= 6) {
            if (kierowca.getZnajomoscTrasy() <= 6 || kierowca.getRyzyko() >= 8) {
                wyscigZycieSerwis.zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka, samochod, kierowca, 0, 5, 0, 5, listaUczestnikow);
            } else if (kierowca.getZnajomoscTrasy() <= 5 || kierowca.getRyzyko() >= 10) {
                wyscigZycieSerwis.zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka, samochod, kierowca, 0, 4, 0, 4, listaUczestnikow);
            }
        } else if (odcinek.getTrudnoscOdcinka() >= 4 && kierowca.getSzybkoscReakcji() <= 5) {
            if (kierowca.getZnajomoscTrasy() <= 5 || kierowca.getRyzyko() >= 8) {
                wyscigZycieSerwis.zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka, samochod, kierowca, 0, 3, 0, 3, listaUczestnikow);
            } else if (kierowca.getZnajomoscTrasy() <= 4 || kierowca.getRyzyko() >= 10) {
                wyscigZycieSerwis.zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka, samochod, kierowca, 0, 2, 0, 2, listaUczestnikow);
            }
        } else if (odcinek.getTrudnoscOdcinka() < 4 && kierowca.getSzybkoscReakcji() <= 4) {
            if (kierowca.getZnajomoscTrasy() <= 4 || kierowca.getRyzyko() >= 12) {
                wyscigZycieSerwis.zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka, samochod, kierowca, 0, 1, 0, 1, listaUczestnikow);
            }
        }
    }

    private void przejazdZjazd(Odcinek odcinek, Kierowca kierowca, Samochod samochod, List<Uczestnik> listaUczestnikow) {
        String nazwaOdcinka = odcinek.getNazwaOdcinka();
        if (odcinek.getTrudnoscOdcinka() > 3) {
            if (samochod.getDrogaHamowania() < 80 || kierowca.getZnajomoscTrasy() >= 8) {
                wyscigZycieSerwis.zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka, samochod, kierowca, 0, 2, 0, 2, listaUczestnikow);
            } else if (samochod.getDrogaHamowania() >= 80 || kierowca.getZnajomoscTrasy() < 8) {
                wyscigZycieSerwis.zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka, samochod, kierowca, 0, 5, 0, 5, listaUczestnikow);
            }
        } else if (odcinek.getTrudnoscOdcinka() <= 3) {
            if (samochod.getDrogaHamowania() < 80 || kierowca.getZnajomoscTrasy() >= 8) {
                wyscigZycieSerwis.zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka, samochod, kierowca, 0, 1, 0, 1, listaUczestnikow);
            } else if (samochod.getDrogaHamowania() >= 80 || kierowca.getZnajomoscTrasy() < 8) {
                wyscigZycieSerwis.zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka, samochod, kierowca, 0, 2, 0, 2, listaUczestnikow);
            }
        }
    }

    private void przejazdPodjazd(Odcinek odcinek, Kierowca kierowca, Samochod samochod, List<Uczestnik> listaUczestnikow) {
        String nazwaOdcinka = odcinek.getNazwaOdcinka();
        if (odcinek.getTrudnoscOdcinka() > 6) {
            if (samochod.getSzybkosc() > 200 || kierowca.getZnajomoscTrasy() >= 8) {
                wyscigZycieSerwis.zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka, samochod, kierowca, 0, 2, 0, 2, listaUczestnikow);
            } else if (samochod.getSzybkosc() <= 200 || kierowca.getZnajomoscTrasy() < 8) {
                wyscigZycieSerwis.zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka, samochod, kierowca, 0, 5, 0, 5, listaUczestnikow);
            }
        } else if (odcinek.getTrudnoscOdcinka() <= 6) {
            if (samochod.getSzybkosc() > 200 || kierowca.getZnajomoscTrasy() >= 8) {
                wyscigZycieSerwis.zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka, samochod, kierowca, 0, 1, 0, 1, listaUczestnikow);
            } else if (samochod.getSzybkosc() <= 200 || kierowca.getZnajomoscTrasy() < 8) {
                wyscigZycieSerwis.zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(nazwaOdcinka, samochod, kierowca, 0, 2, 0, 2, listaUczestnikow);
            }
        }
    }

    private void przejazdPitstop(Odcinek obecnyOdcinek, Kierowca kierowca, Samochod samochod) {
        if (TypOdcinka.PITSTOP == obecnyOdcinek.getTypOdcinka()) {
            aktualizacjaKierowcaSamochodPitstop(kierowca, samochod);
        }
    }

    public void aktualizacjaKierowcaSamochodPitstop(Kierowca kierowca, Samochod samochod) {
        if (samochod.getWytrzymaloscSamochodu() <= 10) {
            samochod.aktualizacjaWytrzymalosciPitstop(samochod, kierowca);
            samochod.dodanieCzasuKara(samochod);
        }
        if (kierowca.getZycieKierowcy() <= 10) {
            kierowca.aktualizacjaZyciaPitsop(kierowca);
            samochod.dodanieCzasuKara(samochod);
        }
    }
}
