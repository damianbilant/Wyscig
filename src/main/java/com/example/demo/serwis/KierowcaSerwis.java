package com.example.demo.serwis;

import com.example.demo.exceptions.KierowcaException;
import com.example.demo.model.*;
import com.example.demo.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service

public class KierowcaSerwis {

    public Kierowca stworzKierowce(TypKierowcy typKierowcy) {
        Kierowca kierowca;
        try {
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
        } catch (RuntimeException exception){
            throw new KierowcaException(typKierowcy);
        }
    }

    public Kierowca losowoStworzKierowce() {
        ArrayList<TypKierowcy> listaTypowKierowcy = new ArrayList<>(Arrays.asList(TypKierowcy.values()));
        Kierowca kierowca;

        int wylosowanyIndex = Utils.losuj(0, listaTypowKierowcy.size() - 1);

        TypKierowcy wylosowanyTypKierowcy = listaTypowKierowcy.get(wylosowanyIndex);
        kierowca = stworzKierowce(wylosowanyTypKierowcy);

        return kierowca;
    }

}




