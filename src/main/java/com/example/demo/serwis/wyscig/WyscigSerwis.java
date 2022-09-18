package com.example.demo.serwis.wyscig;

import com.example.demo.model.*;
import com.example.demo.model.kierowca.Kierowca;
import com.example.demo.model.odcinek.Odcinek;
import com.example.demo.model.samochod.Samochod;
import com.example.demo.model.samochod.TypSamochodu;
import com.example.demo.serwis.KierowcaSerwis;
import com.example.demo.serwis.PitstopSerwis;
import com.example.demo.serwis.PogodaSerwis;
import com.example.demo.serwis.SamochodSerwis;
import com.example.demo.serwis.TrasaSerwis;
import com.example.demo.serwis.UczestnikSerwis;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
@RequiredArgsConstructor
public class WyscigSerwis {

    private final WyscigPrzejazdSerwis wyscigPrzejazdSerwis;
    private final WyscigStarcieSerwis wyscigStarcieSerwis;
    private final WyscigNastepnySerwis wyscigNastepnySerwis;
    private final WyscigZycieSerwis wyscigZycieSerwis;
    private final UczestnikSerwis uczestnikSerwis;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Uczestnik> tworzenieWyscigu(int liczba) {
        System.out.println();
        Pogoda pogoda = PogodaSerwis.wylosujPogode();
        System.out.println();

        TrasaSerwis trasaSerwis = new TrasaSerwis();
        PitstopSerwis pitstopSerwis = new PitstopSerwis();
        Trasa trasa = trasaSerwis.stworzTrase(pogoda, trasaSerwis.wylosujPoziomTrudnosci(), pitstopSerwis);

        KierowcaSerwis kierowcaSerwis = new KierowcaSerwis();
        SamochodSerwis samochodSerwis = new SamochodSerwis();
        UczestnikSerwis uczestnikSerwis = new UczestnikSerwis();
        List<Uczestnik> uczestnikList = uczestnikSerwis.stworzUczestnikow(kierowcaSerwis, samochodSerwis, liczba);
        uczestnikSerwis.wypisanieUczestnikow(uczestnikList);

        return  wyscig(uczestnikList, trasa);

    }

    public List<Uczestnik> tworzenieWyscigu(List<Uczestnik> listaUczestnikow) {
        resetUczestnikow(listaUczestnikow);
        System.out.println();
        Pogoda pogoda = PogodaSerwis.wylosujPogode();
        System.out.println();

        TrasaSerwis trasaSerwis = new TrasaSerwis();
        PitstopSerwis pitstopSerwis = new PitstopSerwis();
        Trasa trasa = trasaSerwis.stworzTrase(pogoda, trasaSerwis.wylosujPoziomTrudnosci(), pitstopSerwis);

        UczestnikSerwis uczestnikSerwis = new UczestnikSerwis();
        uczestnikSerwis.wypisanieUczestnikow(listaUczestnikow);

        List<Uczestnik> list = wyscig(listaUczestnikow, trasa);
        aktualizacjaListy(list);
        if (!list.isEmpty()) {
            zapiszZwyciezce(list);
        }
        return list;
    }

    private void aktualizacjaListy (List<Uczestnik> listaUczestnikow){
        for(Uczestnik uczestnik : uczestnikSerwis.listaUczestnikow)   {
            if(!listaUczestnikow.contains(uczestnik)){
                uczestnikSerwis.listaUczestnikow.remove(uczestnik);
            }
        }
    }

    private void resetUczestnikow(List<Uczestnik> listaUczestnikow){
        for(Uczestnik uczestnik : listaUczestnikow)   {
            uczestnik.getKierowca().resetKierowca();
            uczestnik.getSamochod().resetSamochod();
        }
    }

    private void zapiszZwyciezce(List<Uczestnik> listaUczestnikow){
        if (!listaUczestnikow.isEmpty()){
            Map<Uczestnik, Double> listaCzasuPrzejazdu = utworzenieTabeliWynikow(listaUczestnikow);
            Uczestnik uczestnik = listaCzasuPrzejazdu.entrySet().stream().min(Map.Entry.comparingByValue()).get().getKey();
            String czas = new Date().toString();
            String id = uczestnik.getKierowca().getTypKierowcy() + czas;
            jdbcTemplate.update("insert into ZWYCIEZCYWYSCIGU (id_zwyciezcyWyscigu,UUID,TYPSAMOCHODU,WYTRZYMAŁOŚĆSAMOCHODU,przejechanydystans,NAZWAKIEROWCY,ZYCIEKIEROWCY,CZASZAKONCZENIAWYSCIGU) values (?,?,?,?,?,?,?,?)",
                    id,uczestnik.getKierowca().getUuid(),uczestnik.getSamochod().getTypSamochodu().name(), uczestnik.getSamochod().getWytrzymaloscSamochodu(),uczestnik.getSamochod().getPrzejechanyDystans(),uczestnik.getKierowca().getTypKierowcy().name(),
                    uczestnik.getKierowca().getZycieKierowcy(),new Date());
        }
        }


    private List<Uczestnik> wyscig(List<Uczestnik> listaUczestnikow, Trasa trasa) {

        System.out.println();
        System.out.println("Wpływ pogody na samochód:");
        for (Uczestnik uczestnik : listaUczestnikow) {
            uczestnik.getSamochod().aktualizacjaSzybkosciIhamowania(trasa.getPogoda());

        }
        System.out.println();
        System.out.println("Wpływ pogody na kierowcę:");
        for (Uczestnik uczestnik : listaUczestnikow) {
            uczestnik.getKierowca().aktualizacjaReakcjiOdPogody(trasa.getPogoda());

        }
        System.out.println();
        System.out.println("Wszyscy na starcie");
        for (Uczestnik uczestnik : listaUczestnikow) {
            System.out.println();
            obslugaPojedynczegoOdcinka(null, trasa.getListaOdcinkow().get(0), listaUczestnikow, uczestnik); //start

        }
        for (int i = 0; i < trasa.getListaOdcinkow().size(); i++) {
            int nrOdcinka = i + 1;
            System.out.println();
            System.out.println("Przejazd odcinka nr " + nrOdcinka + " z " + trasa.getListaOdcinkow().size() + " (" + trasa.getListaOdcinkow().get(i).getNazwaOdcinka() + ")");

            Odcinek obecnyOdcinek = trasa.getListaOdcinkow().get(i);

            for (Uczestnik uczestnik : listaUczestnikow) {
                System.out.println();
                try {
                    Odcinek nastepnyOdcinek = trasa.getListaOdcinkow().get(i + 1);
                    obslugaPojedynczegoOdcinka(obecnyOdcinek, nastepnyOdcinek, listaUczestnikow, uczestnik);
                    if (listaUczestnikow.size() < 1) {
                        break;
                    }
                } catch (Exception e) {
                    obslugaPojedynczegoOdcinka(obecnyOdcinek, null, listaUczestnikow, uczestnik);
                    if (listaUczestnikow.size() < 1) {
                        break;
                    }
                }
            }
            wyscigStarcieSerwis.starcie(listaUczestnikow, obecnyOdcinek);
            if (listaUczestnikow.size() < 1) {
                System.out.println();
                System.out.println("Wyścig został zakończony - nikt nie dojechał do mety.");
                break;
            }
            System.out.println();
            System.out.println("Tabela wyników:");
            utworzenieTabeliWynikow(listaUczestnikow);
        }
        return listaUczestnikow;
    }

    @SneakyThrows
    private void obslugaPojedynczegoOdcinka(Odcinek obecnyOdcinek, Odcinek nastepnyOdcinek, List<Uczestnik> listaUczestnikow, Uczestnik uczestnik) {
        if (uczestnik == null || uczestnik.getKierowca() == null || uczestnik.getSamochod() == null) {
            throw new NullPointerException();
        }


        Kierowca kierowca = uczestnik.getKierowca();
        Samochod samochod = uczestnik.getSamochod();

        //start
        if (obecnyOdcinek == null) {
            System.out.println("Kierowca " + kierowca.getTypKierowcy() + " przekroczył linię startu i rozpoczął zmagania w jakże trudnym wyścigu");
            kierowca.znajomoscTrasyPredkosc(samochod);
        }

        //wyscig
        if (obecnyOdcinek != null && nastepnyOdcinek != null) {
            wyscigPrzejazdSerwis.przejazd(obecnyOdcinek, kierowca, samochod, listaUczestnikow);
            wyscigZycieSerwis.usuwanieUczestnikaLubSamochodu(listaUczestnikow);
            if (listaUczestnikow.isEmpty()) {
                System.out.println("Wyścig zostaje zakończony, ponieważ żaden z uczestników nie jest w stanie go dokończyć. Zobaczmy na końcową tabelę wyników:");
                utworzenieTabeliWynikow(listaUczestnikow);
            } else if (listaUczestnikow.contains(uczestnik)) {

                samochod.dodajCzasPrzejazduOdcinka(samochod.szybkoscPrzejazduOdcinka(obecnyOdcinek));
                samochod.dodajPrzejechanyDystans(obecnyOdcinek);
                System.out.println("Kierowca " + kierowca.getTypKierowcy() + " przejechał odcinek " + obecnyOdcinek.getNazwaOdcinka() +
                        " w czasie " + samochod.szybkoscPrzejazduOdcinka(obecnyOdcinek) + " minuty");

                wyscigNastepnySerwis.nastepnyOdcinek(obecnyOdcinek.getTypOdcinka(), nastepnyOdcinek, samochod, kierowca, listaUczestnikow);
                wyscigZycieSerwis.usuwanieUczestnikaLubSamochodu(listaUczestnikow);
                if (listaUczestnikow.isEmpty()) {
                    System.out.println("Wyścig zostaje zakończony, ponieważ żaden z uczestników nie jest w stanie go dokończyć. Zobaczmy na końcową tabelę wyników:");
                    utworzenieTabeliWynikow(listaUczestnikow);
                }
            }
        }
        //meta - ostatni odcinek
        if (nastepnyOdcinek == null) {
            System.out.println("Już widać metę! Jeszcze tylko jeden odcinek pozostał " + kierowca.getTypKierowcy() + " do pokonania");
            wyscigPrzejazdSerwis.przejazd(obecnyOdcinek, kierowca, samochod, listaUczestnikow);
            wyscigZycieSerwis.usuwanieUczestnikaLubSamochodu(listaUczestnikow);
            if (listaUczestnikow.isEmpty()) {
                System.out.println("Wyścig zostaje zakończony, ponieważ żaden z uczestników nie jest w stanie go dokończyć. Zobaczmy na końcową tabelę wyników:");
                utworzenieTabeliWynikow(listaUczestnikow);
            } else if (listaUczestnikow.contains(uczestnik)) {
                samochod.dodajCzasPrzejazduOdcinka(samochod.szybkoscPrzejazduOdcinka(obecnyOdcinek));
                samochod.dodajPrzejechanyDystans(obecnyOdcinek);
                System.out.println("Kierowca " + kierowca.getTypKierowcy() + " przejechał odcinek " + obecnyOdcinek.getNazwaOdcinka() +
                        " w czasie " + samochod.szybkoscPrzejazduOdcinka(obecnyOdcinek) + " minuty");

            }
            System.out.println();
            System.out.println("Wyścig dobiegł końca.");
        }
    }

    private Map<Uczestnik, Double> utworzenieTabeliWynikow(List<Uczestnik> listaUczestnikow) {
        Map<Uczestnik, Double> listaCzasuPrzejazdu = new HashMap<>();
        for (int i = 0; i < listaUczestnikow.size(); i++) {
            for (Uczestnik zawodnik : listaUczestnikow) {
                double czasPrzejazdu = zawodnik.getSamochod().getCzasPrzejazdu();
                czasPrzejazdu *= 10;
                czasPrzejazdu = Math.round(czasPrzejazdu);
                czasPrzejazdu /= 10;
                listaCzasuPrzejazdu.put(zawodnik, czasPrzejazdu);
            }
        }
        listaCzasuPrzejazdu.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(x -> System.out.println(x.getKey().getKierowca().getTypKierowcy() + " " + x.getValue()));
    return listaCzasuPrzejazdu;
    }

}


