package com.example.demo.serwis;

import com.example.demo.model.Pogoda;
import com.example.demo.utils.Utils;
import org.springframework.stereotype.Service;

@Service
public class PogodaSerwis {

    public static Pogoda wylosujPogode() {
        int rodzajPogody = Utils.losuj(0, 100);
        Pogoda pogoda;
        if (rodzajPogody < 25) {
            pogoda = Pogoda.SUNNY;
            System.out.println("Pogoda " + pogoda.getNazwaPogody() + ", duże prawdopodobieństwo oślepienia kierowcy przez promienie słoneczne. " +
                    "Uczestnikom wyścigu z pewnością przydałyby się okulary przeciwsłoneczne z polaryzacją.");
        } else if (rodzajPogody < 50) {
            pogoda = Pogoda.CLOUDY;
            System.out.println("Pogoda " + pogoda.getNazwaPogody() + ", z powodu słabego światła dziennego widoczność na drodze może być znacznie pogorszona. " +
                    "W takich warunkach łatwo o stłuczkę, a nawet poważny wypadek.");
        } else if (rodzajPogody < 75) {
            pogoda = Pogoda.RAINY;
            System.out.println("Pogoda " + pogoda.getNazwaPogody() + ", może poważnie utrudnić życie każdemu kierowcy. " +
                    "Podstawowym  problemem jest większa podatność kół pojazdu na poślizg oraz wydłużona droga hamowania. W takich warunkach uczestnicy naszego wyścigu muszą zachować szczególną ostrożność.");
        } else {
            pogoda = Pogoda.SNOWY;
            System.out.println("Pogoda " + pogoda.getNazwaPogody() + ", jezdnia przy takich warunkach może stać się bardzo śliska. " +
                    "Szczególnie na ostrych zakrętach i niebezpiecznych zjazdach śmierć niejednokrotnie zajrzy dziś w oczy uczestnikom wyścigu.");
        }
        return pogoda;

    }

}
