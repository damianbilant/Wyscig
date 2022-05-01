package com.example.demo.serwis;

import com.example.demo.model.Pogoda;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// zrobić pogoda serwis test: chcemy sprawdzić czy jest możliwa każda pogoda do stworzenia po losowaniu,

@RunWith(SpringRunner.class)
public class PogodaSerwisTest {

    @Test
    @Repeat(value = 20)
    public void powinnoWylosowacIstworzycPogode(){
        //given
        PogodaSerwis pogodaSerwis = new PogodaSerwis();
        List<Pogoda> listaTypowPogody = new ArrayList<>(Arrays.asList(Pogoda.values()));
        //when
        Pogoda pogoda = pogodaSerwis.wylosujPogode();
        //then
        Assert.assertTrue(listaTypowPogody.contains(pogoda));

    }

}
