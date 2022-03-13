package com.example.demo.serwis;

import com.example.demo.model.Kierowca;
import com.example.demo.model.Samochod;
import com.example.demo.model.TypSamochodu;
import com.example.demo.model.Uczestnik;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UczestnikSerwis {


    public List<Uczestnik> stworzUczestnikow(KierowcaSerwis kierowcaSerwis, SamochodSerwis samochodSerwis) {
        List<Uczestnik> listaUczestnikow = new ArrayList<>();
        Scanner sc = new Scanner(System.in);


        try {
            System.out.println("Ilu uczestników ma brać udział w wyścigu?");
            System.out.println("Wprowadź liczbę lub naciśnij ENTER aby zakończyć:");
            int liczbaUczestnikow = sc.nextInt();
            if (liczbaUczestnikow == 0 || liczbaUczestnikow > 6) {
                System.out.println("Liczba uczestników jest równa 0 lub jest większa niż maksymalna liczba uczestników, czyli 6.");
            } else {
                for (int i = 0; i < liczbaUczestnikow; i++) {
                    Uczestnik uczestnik = new Uczestnik(kierowcaSerwis.losowoStworzKierowce(), samochodSerwis.losowoStworzSamochod());
                    listaUczestnikow.add(uczestnik);
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Błędny format liczby");
            sc.nextLine();
        } catch (Exception e) {
            System.out.println("Jakiś bląd " + e);
        }

        System.out.println();
        return listaUczestnikow;
    }

    public void wypisanieUczestnikow(List<Uczestnik> listaUczestnikow) {
        System.out.println("Uczestnikami wyścigu są:");
        for (Uczestnik uczestnicy :
                listaUczestnikow) {
            System.out.println(uczestnicy.getKierowca().getTypKierowcy() + " " + uczestnicy.getSamochod().getTypSamochodu());

        }


    }
}
