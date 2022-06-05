package com.example.demo.serwis.wyscig;

import com.example.demo.model.odcinek.Odcinek;
import com.example.demo.model.Uczestnik;
import com.example.demo.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WyscigStarcieSerwis {

    private final WyscigZycieSerwis wyscigZycieSerwis;

    public void starcie(List<Uczestnik> listaUczestnikow, Odcinek odcinek) {
        if (Utils.losuj(1, 4) == 1) {
            int pominDublaIndex = 0;
            for (Uczestnik uczestnik :
                    listaUczestnikow) {
                pominDublaIndex += 1;
                for (int i = pominDublaIndex; i < listaUczestnikow.size(); i++) {
                    if (!uczestnik.equals(listaUczestnikow.get(i))) {
                        wyscigZycieSerwis.aktualizacjaKierowcaSamochodStarcie(uczestnik, listaUczestnikow.get(i), odcinek, listaUczestnikow);
                        System.out.println(uczestnik.getKierowca().getUuid() + " " + uczestnik.getKierowca().getTypKierowcy() + " " + uczestnik.getSamochod().getTypSamochodu() + "  z  " + listaUczestnikow.get(i).getKierowca().getUuid() + " " + listaUczestnikow.get(i).getKierowca().getTypKierowcy() + " " + listaUczestnikow.get(i).getSamochod().getTypSamochodu());
                    }
                }
            }
        }
        wyscigZycieSerwis.usuwanieUczestnikaLubSamochodu(listaUczestnikow);
    }

}
