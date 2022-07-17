package com.example.demo.serwis;

import com.example.demo.model.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.List;

@RunWith(JUnitParamsRunner.class)

public class UczestnikSerwisTest {



    @Test
    @Parameters({
            "1", "0", "4", "7"
    })
    public void powinnoWylosowacUczestnika(int liczba){
        //given
        UczestnikSerwis uczestnikSerwis = new UczestnikSerwis();
        KierowcaSerwis kierowcaSerwis = new KierowcaSerwis();
        SamochodSerwis samochodSerwis = new SamochodSerwis();
        //when
        List<Uczestnik> uczestnikList = uczestnikSerwis.stworzUczestnikow(kierowcaSerwis, samochodSerwis, liczba);
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
