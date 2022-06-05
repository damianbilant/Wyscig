package com.example.demo.serwis;

import com.example.demo.model.samochod.Samochod;
import com.example.demo.model.samochod.TypSamochodu;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class SamochodSerwisTest {

    //:wrzucić do samochódserwis test, powinno wylosować i stworzyć samochód test metoda,


    @Test
    public void powinnoStworzycSamochod() {
        //given
        SamochodSerwis samochodSerwis = new SamochodSerwis();
        TypSamochodu typSamochodu = TypSamochodu.COUPE;
        //when
        Samochod samochod = samochodSerwis.stworzSamochod(typSamochodu);
        //then
        Assert.assertNotNull(samochod);
        Assert.assertEquals(TypSamochodu.COUPE, samochod.getTypSamochodu());
        Assert.assertNotNull(samochod.getCzasPrzejazdu());

    }

    @Test
    public  void powinnoWylosowacIstworzycSamochod(){
        //given
        SamochodSerwis samochodSerwis = new SamochodSerwis();
        ArrayList<TypSamochodu> listaTypowSamochodu = new ArrayList<>(Arrays.asList(TypSamochodu.values()));
        //when
        Samochod samochod = samochodSerwis.losowoStworzSamochod();
        //then
        Assert.assertNotNull(samochod);
        Assert.assertTrue(listaTypowSamochodu.contains(samochod.getTypSamochodu()));

    }


}
