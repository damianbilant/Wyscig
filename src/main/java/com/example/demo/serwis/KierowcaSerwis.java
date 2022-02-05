package com.example.demo.serwis;

import com.example.demo.model.*;
import com.example.demo.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service

public class KierowcaSerwis {

    public Kierowca stworzKierowce(TypKierowcy typKierowcy) {
        Kierowca kierowca;
        switch (typKierowcy) {
            case UBER:
                kierowca = new KierowcaUber();
                break;
            case DZIAD:
                kierowca = new KierowcaDziad();
                break;
            case BOR:
                kierowca = new KierowcaBOR();
                break;
            default:
                kierowca = null;
                break;
        }

        kierowca.nietrzezwoscZmniejszenieReakcji();
        kierowca.ustawienieRyzyka();
        System.out.println("Nowy kierowca to: " + kierowca.toString());
        return kierowca;
    }

    public Kierowca losowoStworzKierowce() {
        ArrayList<TypKierowcy> listaTypowKierowcy = new ArrayList<>(Arrays.asList(TypKierowcy.values()));
        Kierowca kierowca;

        int wylosowanyIndex = Utils.losuj(0, listaTypowKierowcy.size() - 1);

        TypKierowcy wylosowanyTypKierowcy = listaTypowKierowcy.get(wylosowanyIndex);
        kierowca = stworzKierowce(wylosowanyTypKierowcy);

        return kierowca;
    }

/*    public Kierowca stworzKierowce2(TypKierowcy typKierowcy) {
        int rodzajKierowcy = Utils.losuj(0,99);
        Kierowca kierowca = null;


            if(rodzajKierowcy<33) {

                    kierowca = new KierowcaUber();


                }


                else if(rodzajKierowcy<66) {
                    kierowca = new KierowcaDziad();


                }


                else if(rodzajKierowcy<99 && rodzajKierowcy >66) {
                    kierowca = new KierowcaBOR();


                }



        kierowca.nietrzezwoscZmniejszenieReakcji();
        kierowca.ustawienieRyzyka();
        System.out.println("Nowy kierowca to: " + kierowca.toString());
        return kierowca;
    }*/
}




