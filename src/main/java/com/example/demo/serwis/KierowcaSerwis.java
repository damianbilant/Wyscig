package com.example.demo.serwis;

import com.example.demo.model.*;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service

public class KierowcaSerwis {

    public Kierowca stworzKierowce(TypKierowcy typKierowcy) {
        Kierowca kierowca;
        switch (typKierowcy) {
            case UBER:
                kierowca = new KierowcaUber();
                break;
            case STARYDZIAD:
                kierowca = new KierowcaDziad();
                break;
            default:
                kierowca = null;
                break;
        }
        System.out.println("Nowy kierowca to: " + kierowca.toString());
        kierowca.czyTrzezwy(kierowca);
        return kierowca;
    }

    public static int losuj(int minimum, int maximum) {
        Random rn = new Random();
        return rn.nextInt(maximum - minimum + 1) + minimum;
    }

}


