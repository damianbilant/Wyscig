package com.example.demo.serwis;

import com.example.demo.model.*;
import org.springframework.stereotype.Service;

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
        System.out.println("Nowy kierowca to: " + kierowca.toString());
        kierowca.czyTrzezwy();
        return kierowca;
    }



}


