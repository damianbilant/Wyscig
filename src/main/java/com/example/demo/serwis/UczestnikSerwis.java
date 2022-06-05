package com.example.demo.serwis;

import com.example.demo.model.Uczestnik;
import com.example.demo.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Service
public class UczestnikSerwis {

    public List<Uczestnik> stworzUczestnikow(KierowcaSerwis kierowcaSerwis, SamochodSerwis samochodSerwis) {
        List<Uczestnik> listaUczestnikow = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Ilu uczestników ma brać udział w wyścigu?");
                System.out.println("Wprowadź liczbę od 2 do 6 lub naciśnij ENTER aby zakończyć:");
                int liczbaUczestnikow = sc.nextInt();
                    if (liczbaUczestnikow < 2 || liczbaUczestnikow > 6) {
                        System.out.println("Liczba uczestników jest mniejsza niż 2 lub jest większa niż maksymalna liczba uczestników, czyli 6, liczba uczestników zostanie wylosowana.");
                      listaUczestnikow = losowoStworzIloscUczestnikow(kierowcaSerwis, samochodSerwis);
                    } else {
                        for (int i = 0; i < liczbaUczestnikow; i++) {
                            Uczestnik uczestnik = new Uczestnik(kierowcaSerwis.losowoStworzKierowce(), samochodSerwis.losowoStworzSamochod());
                            listaUczestnikow.add(uczestnik);
                        }
                    }
        } catch (InputMismatchException e) {
            System.out.println("Błędny format liczby, liczba uczestników zostanie wylosowana.");
            listaUczestnikow = losowoStworzIloscUczestnikow(kierowcaSerwis, samochodSerwis);
            sc.nextLine();
        } catch (Exception e) {
            System.out.println("Jakiś bląd " + e + " liczba uczestników zostanie wylosowana.");
            listaUczestnikow = losowoStworzIloscUczestnikow(kierowcaSerwis, samochodSerwis);
        }
        System.out.println();
        return listaUczestnikow;
    }

    private List<Uczestnik> losowoStworzIloscUczestnikow(KierowcaSerwis kierowcaSerwis, SamochodSerwis samochodSerwis){
        List<Uczestnik> listaUczestnikow = new ArrayList<>();
        int liczbaUczestnikow = Utils.losuj(2,6);
        for (int i = 0; i < liczbaUczestnikow; i++) {
            Uczestnik uczestnik = new Uczestnik(kierowcaSerwis.losowoStworzKierowce(), samochodSerwis.losowoStworzSamochod());
            listaUczestnikow.add(uczestnik);
        }
        return listaUczestnikow;
    }

    public void wypisanieUczestnikow(List<Uczestnik> listaUczestnikow) {
        System.out.println("Uczestnikami wyścigu są:");
        for (Uczestnik uczestnicy : listaUczestnikow) {
            System.out.println(uczestnicy.getKierowca().getTypKierowcy() + " " + uczestnicy.getSamochod().getTypSamochodu());
        }
    }
}
