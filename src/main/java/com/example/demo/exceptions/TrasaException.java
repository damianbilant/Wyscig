package com.example.demo.exceptions;

import com.example.demo.model.TrasaLevel;

import java.text.MessageFormat;

public class TrasaException extends RuntimeException{
    public TrasaException (TrasaLevel trasaLevel){
        super(MessageFormat.format("Trasa {0} nie została stworzona ", trasaLevel));
    }
}





