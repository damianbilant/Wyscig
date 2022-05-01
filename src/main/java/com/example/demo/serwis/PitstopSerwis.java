package com.example.demo.serwis;

import com.example.demo.model.*;
import com.example.demo.utils.Utils;
import org.springframework.stereotype.Service;

@Service

public class PitstopSerwis {


    public Integer wylosujIloscPitstopow(Pogoda pogoda, Trasa trasa, TrasaLevel trasaLevel) {
        int iloscPitstopow = 0;
        if (trasa.getPogoda() == Pogoda.SUNNY) {
            if (trasaLevel == TrasaLevel.EASY) {
                iloscPitstopow = Utils.losuj(0, 1);
            } else if (trasaLevel == TrasaLevel.MEDIUM) {
                iloscPitstopow = Utils.losuj(1, 2);
            } else if (trasaLevel == TrasaLevel.HARD) {
                iloscPitstopow = Utils.losuj(1, 3);
            }
            if (iloscPitstopow > 0) {
                System.out.println("Pogoda " + pogoda.getNazwaPogody() + ", możliwa ilość pitstopów: " + iloscPitstopow + ".");
            }
        } else if (trasa.getPogoda() == Pogoda.CLOUDY) {
            if (trasaLevel == TrasaLevel.EASY) {
                iloscPitstopow = Utils.losuj(1, 2);
            } else if (trasaLevel == TrasaLevel.MEDIUM) {
                iloscPitstopow = Utils.losuj(1, 3);
            } else if (trasaLevel == TrasaLevel.HARD) {
                iloscPitstopow = Utils.losuj(2, 3);
            }
            System.out.println("Pogoda " + pogoda.getNazwaPogody() + ", możliwa ilość pitstopów: " + iloscPitstopow + ".");
        } else if (trasa.getPogoda() == Pogoda.RAINY) {
            if (trasaLevel == TrasaLevel.EASY) {
                iloscPitstopow = Utils.losuj(1, 3);
            } else if (trasaLevel == TrasaLevel.MEDIUM) {
                iloscPitstopow = Utils.losuj(2, 3);
            } else if (trasaLevel == TrasaLevel.HARD) {
                iloscPitstopow = Utils.losuj(2, 4);
            }
            System.out.println("Pogoda " + pogoda.getNazwaPogody() + ", możliwa ilość pitstopów: " + iloscPitstopow + ".");
        } else if (trasa.getPogoda() == Pogoda.SNOWY) {
            if (trasaLevel == TrasaLevel.EASY) {
                iloscPitstopow = Utils.losuj(2, 3);
            } else if (trasaLevel == TrasaLevel.MEDIUM) {
                iloscPitstopow = Utils.losuj(2, 4);
            } else if (trasaLevel == TrasaLevel.HARD) {
                iloscPitstopow = Utils.losuj(3, 4);
            }
            System.out.println("Pogoda " + pogoda.getNazwaPogody() + ", możliwa ilość pitstopów: " + iloscPitstopow + ".");
        }
        return iloscPitstopow;

    }

    //TODO:


//wyścigserwis
    public void pitstop(Odcinek obecnyOdcinek, Samochod samochod, Kierowca kierowca) {
        if(obecnyOdcinek.getTypOdcinka() == TypOdcinka.PITSTOP){
            aktualizacjaKierowcaSamochodPitstop(samochod, kierowca);
        }
    }
//wyścigserwis
    public void aktualizacjaKierowcaSamochodPitstop(Samochod samochod, Kierowca kierowca){
        //zrobić warunki jeśli życie/wytrzymałość mniejsza niż to dopiero się to odpalać ma
        aktualizacjaZyciaPitsop(kierowca);
        aktualizacjaWytrzymalosciPitstop(samochod);
    }
//samochód
    public int aktualizacjaWytrzymalosciPitstop(Samochod samochod) {
        int zwiekszenieWytrzymalosciSamochodu = samochod.getWytrzymaloscSamochodu() + 10;
        samochod.setWytrzymaloscSamochodu(zwiekszenieWytrzymalosciSamochodu);
        //dopisać sout
        //jeśli skorzysta z którejś metody to dodać im czas
        return zwiekszenieWytrzymalosciSamochodu;
    }
//kierowca
    public int aktualizacjaZyciaPitsop(Kierowca kierowca){
        int zwiekszenieZyciaKierowcy = kierowca.getZycieKierowcy() + 10;
        kierowca.setZycieKierowcy(zwiekszenieZyciaKierowcy);
        //dopisać sout
        //jeśli skorzysta z którejś metody to dodać im czas
        return zwiekszenieZyciaKierowcy;
    }
}
