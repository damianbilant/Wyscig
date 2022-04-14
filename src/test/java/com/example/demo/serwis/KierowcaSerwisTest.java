package com.example.demo.serwis;


import com.example.demo.model.Kierowca;
import com.example.demo.model.Samochod;
import com.example.demo.model.TypKierowcy;
import com.example.demo.model.TypSamochodu;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.InstanceOf;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;

@RunWith(JUnitParamsRunner.class)
public class KierowcaSerwisTest {


    @Before
    public void jestemPrzedTestem() {
        System.out.println("jestem przed testem");
    }

    @After
    public void jestemPoTescie() {
        System.out.println("jestem po teście");
    }

    /*    @Mock
        private KierowcaSerwis kierowcaSerwis;
        @InjectMocks
        private KierowcaSerwis kierowcaSerwis;*/
    @Test
    @Parameters({
            "5|jakis tekst", "54|cokolwiek"
    })
    public void sprawdzenieParametrow(Integer liczba, String tekst) {
        //when
        System.out.println(liczba);
        System.out.println(tekst);
        //then
        Assert.assertNotNull(liczba);
        Assert.assertNotNull(tekst);
    }


    @Test
    public void powinnoStworzycKierowce() {
        //given
        KierowcaSerwis kierowcaSerwis = new KierowcaSerwis();
        TypKierowcy typKierowcy = TypKierowcy.UBER;
        //when
        Kierowca kierowca = kierowcaSerwis.stworzKierowce(typKierowcy);
        //then
        Assert.assertNotNull(kierowca);
        Assert.assertEquals(TypKierowcy.UBER, kierowca.getTypKierowcy());
        Assert.assertNotNull(kierowca.getZnajomoscTrasy());

    }

    @Test
    public void powinnoStworzycKierowce2() {
        //given
        KierowcaSerwis kierowcaSerwis = new KierowcaSerwis();
        TypKierowcy typKierowcy = TypKierowcy.UBER;
        //when
        Kierowca kierowca = kierowcaSerwis.stworzKierowce(typKierowcy);
        //then
        Assert.assertNotNull(kierowca);
        Assert.assertEquals(TypKierowcy.UBER, kierowca.getTypKierowcy());
        Assert.assertNotNull(kierowca.getZnajomoscTrasy());

    }



    @Test
    public  void powinnoWylosowacIstworzycKierowce(){
        //given
        KierowcaSerwis kierowcaSerwis = new KierowcaSerwis();
        ArrayList<TypKierowcy> listaTypowKierowcy = new ArrayList<>(Arrays.asList(TypKierowcy.values()));
        //when
        Kierowca kierowca = kierowcaSerwis.losowoStworzKierowce();
        //then
        Assert.assertNotNull(kierowca);
        Assert.assertTrue(listaTypowKierowcy.contains(kierowca.getTypKierowcy()));
        Assert.assertTrue(kierowca instanceof Kierowca);
    }
}