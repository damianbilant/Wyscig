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

//TODO:wrzucić do samochódserwis test, powinno wylosować i stworzyć kierowcę test metoda,
// zrobić pogoda serwis test: chcemy sprawdzić czy jest możliwa każda pogoda do stworzenia po losowaniu,
// uczestnik serwis test: czy się tworzy uczestnik (może mock?) można zrobić zamiast scannera losowanie ilości uczestników i otestować,
//  która ma podane w argumencie ilość uczestników i teraz w zależnośći od tego czy przychodzi null to ma się wylosować ilość uczestników
//  a jak przychodzi jakaś wartość uczestników to ma się tyle stworzyć, a jak dam więcej lub mniej niż można to też coś ma się zrobić,
//  trasa serwis test czy się robić wszystko odcinki, czy wszystkie poziomy trudności się tworzą, czy odcinków jest tyle ile mamy w poziomie trudności zakodowane,
//  czy tyle sie tworzy tych odcinków, czy odcinki się nie powtarzają jeden za drugim i czy pierwszy odcinek jest prosty






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