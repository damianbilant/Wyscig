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

import java.util.List;
import java.util.Optional;
import java.util.UUID;


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

    @RequestMapping(method = RequestMethod.POST, path = "/wyscig/zapiszuczestnika")
    private Uczestnik zapiszUczestnika(){
        Uczestnik uczestnik = uczestnikSerwis.stworzUczestnika(kierowcaSerwis, samochodSerwis);
        return uczestnik;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/wyscig/pobierzuczestnikow" )
    private List<Uczestnik> pobierzUczestnikow(){
        return uczestnikSerwis.listaUczestnikow;
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/wyscig/usunuczestnikow")
    private List<Uczestnik> usunUczestnikow(@RequestParam(value = "uuid") UUID uuid){
        List<Uczestnik> listaUczestnikow = uczestnikSerwis.listaUczestnikow;
        Optional<Uczestnik> uczestnik = listaUczestnikow.stream()
                .filter(u -> u.getKierowca().getUuid().equals(uuid))
                .findAny();
        if(uczestnik.isPresent()){
            listaUczestnikow.remove(uczestnik.get());
        }
        return listaUczestnikow;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/wyscig/start")
    private List<Uczestnik> startWyscigu(){
    return wyscigSerwis.tworzenieWyscigu(uczestnikSerwis.listaUczestnikow);
    }


}

//TODO:sprawdzam po skończonym wyścigu ci którzy nie dojechali to ich usunąć z listy (uczestnikSerwis.listaUczestnikow) i na liście żeby zostali tylko ci co
// dojechali i zrobić im reset parametrów; zwycięzca żeby został zapisany w bazie; może być też w kolumnie data (timestamp) w momencie kiedy kończy się wyścig
// podpowiedź tworzenieWyscigu(w wyscig serwis) i tworzenieWyscigu2(w wyscig serwis), druga lista w uczestnikserwis lista inmemory którą zasilamy przez postmana
// i te dwie porównujemy np. za pomocą foreach tą z uczestnikserwis do tej z wyscigu serwis i jak nie ma to usuwam z tej inmemory, reeset przejść przez każdego
// uczestnika i wywołać metodę resetującą parametry ( po wyścigu )


