package com.example.demo.exceptions;

import com.example.demo.model.samochod.TypSamochodu;

import java.text.MessageFormat;

public class SamochodException extends RuntimeException{
    public SamochodException(TypSamochodu typSamochodu) {
            super(MessageFormat.format("Samochód {0} nie został stworzony ", typSamochodu));
    }



}
