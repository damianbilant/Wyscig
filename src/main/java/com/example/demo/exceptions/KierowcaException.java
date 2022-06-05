package com.example.demo.exceptions;

import com.example.demo.model.kierowca.TypKierowcy;

import java.text.MessageFormat;

public class KierowcaException extends RuntimeException {
    public KierowcaException(TypKierowcy typKierowcy){
        super(MessageFormat.format("Kierowca {0} nie został stworzony ",  typKierowcy));
    }
}





