package com.example.demo.controller;

import com.example.demo.model.Uczestnik;
import com.example.demo.serwis.KierowcaSerwis;
import com.example.demo.serwis.SamochodSerwis;
import com.example.demo.serwis.UczestnikSerwis;
import com.example.demo.serwis.wyscig.WyscigSerwis;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WyscigController {
    private WyscigSerwis wyscigSerwis;
    private UczestnikSerwis uczestnikSerwis;
    private KierowcaSerwis kierowcaSerwis;
    private SamochodSerwis samochodSerwis;

    public WyscigController(WyscigSerwis wyscigSerwis, UczestnikSerwis uczestnikSerwis, KierowcaSerwis kierowcaSerwis, SamochodSerwis samochodSerwis){
        this.wyscigSerwis = wyscigSerwis;
        this.uczestnikSerwis = uczestnikSerwis;
        this.kierowcaSerwis = kierowcaSerwis;
        this.samochodSerwis = samochodSerwis;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/wyscig/liczbauczestnikow", produces = MediaType.APPLICATION_JSON_VALUE)
    public void wyscigUczestnicy(@RequestParam (value = "liczba") int liczba){
        wyscigSerwis.tworzenieWyscigu(liczba);
    }


    private void zapiszUczestnika(){
        uczestnikSerwis.tutaj metoda stworz uczestnika;

    }






}
//TODO: do naszej wewnetrznej listy zapisywać pojedyńczych uczestników ale także pobierać i kasować poszczególnych uczestników z tej naszej wew listy i wszystkich usunąć,
// chcemy odpalić wyścig z listą uczestników których stworzyliśmy i tych którzy dojechali do mety zapisać w bazie i pobrać



