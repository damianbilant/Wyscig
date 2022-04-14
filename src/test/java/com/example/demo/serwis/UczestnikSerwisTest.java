package com.example.demo.serwis;

import com.example.demo.model.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)

public class UczestnikSerwisTest {

    //TODO:uczestnik serwis test: czy się tworzy uczestnik, można zrobić zamiast scannera losowanie ilości uczestników i otestować,
    // która ma podane w argumencie ilość uczestników i teraz w zależnośći od tego czy przychodzi null to ma się wylosować ilość uczestników
    // a jak przychodzi jakaś wartość uczestników to ma się tyle stworzyć, a jak dam więcej lub mniej niż można to też coś ma się zrobić,



//do update po usunięciu scannera
    @Test
    public void powinnoWylosowacUczestnika(){
        //given
        UczestnikSerwis uczestnikSerwis = new UczestnikSerwis();
        KierowcaSerwis kierowcaSerwis = new KierowcaSerwis();
        SamochodSerwis samochodSerwis = new SamochodSerwis();
        //when
        List<Uczestnik> uczestnikList = uczestnikSerwis.stworzUczestnikow(kierowcaSerwis, samochodSerwis);
        //then
       sprawdzenieUczestnika(uczestnikList);
        Assert.assertFalse(uczestnikList.isEmpty());
        Assert.assertTrue(uczestnikList.size() >= 2 && uczestnikList.size() <= 6);
    }
    private void sprawdzenieUczestnika(List<Uczestnik> uczestnikList){
    for (Uczestnik uczestnik: uczestnikList) {
        Assert.assertNotNull(uczestnik);
    }
}

}
