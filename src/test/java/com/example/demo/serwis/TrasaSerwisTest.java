package com.example.demo.serwis;

import com.example.demo.model.*;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrasaSerwisTest {

    //TODO:// trasa serwis test czy się robią wszystkie odcinki, czy wszystkie poziomy trudności się tworzą, czy odcinków jest tyle ile mamy w poziomie trudności zakodowane,
    // czy tyle sie tworzy tych odcinków, czy odcinki się nie powtarzają jeden za drugim i czy pierwszy odcinek jest prosty

    @Test
    public void czyTworzyTrase() {
        //given
        TrasaSerwis trasaSerwis = new TrasaSerwis();
        PogodaSerwis pogodaSerwis = new PogodaSerwis();
        Pogoda pogoda = pogodaSerwis.wylosujPogode();
        List<TypOdcinka> listaTypowOdcinkow = new ArrayList<>(Arrays.asList(TypOdcinka.values()));
        TrasaLevel trasaLevel = trasaSerwis.wylosujPoziomTrudnosci();

        //when
        Trasa trasa = trasaSerwis.stworzTrase(pogoda, trasaLevel);
        //then
        // test1: sumowanie ilości odcinków metoda i porównać do trasa.getListaOdcinkow().size();
        //Assert.assertTrue();
        Assert.assertTrue(trasa.getListaOdcinkow().get(0).getTypOdcinka().equals(TypOdcinka.PROSTY));
        for (int i = 1; i < trasa.getListaOdcinkow().size(); i++) {
            Assert.assertNotEquals(trasa.getListaOdcinkow().get(i).getTypOdcinka(), trasa.getListaOdcinkow().get(i - 1).getTypOdcinka());
        }
        for (int i=0; i < trasa.getListaOdcinkow().size(); i++) {
            Assert.assertTrue(trasa.getListaOdcinkow().contains(listaTypowOdcinkow.get(i)));
        }
    }
        //sumowanie ilości odcinków metoda
}

//TODO:test żeby w parametrze testu było easy,medium,hard i żeby test odpalił się test z każdym tym poziomem trudności,
// czy pitstop się tworzy i czy w dobrych miejscach