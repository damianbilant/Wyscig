package com.example.demo.serwis;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Zadania {


//    Dodatkowe:
//    1. Wypisz liczby od 5 do 15
//    2. Wypisz liczby od 1 do 10, ale dla co drugiej dodaj krótki tekst "co druga liczba"
//    3. Zrob listę imion (minimum 5) i wypisz wszystkie imiona (dwa maja sie zaczynac na litere "A"), a do każdego dodaj to samo nazwisko
//    4. Wypisz imiona z powyzszej listy, a jeśli imię zaczyna się od litery “A” to nie dodawaj nazwiska
//    Oczywiscie wypisywanie wszystkiego powyzej chcialbym, zeby bylo zrobione za pomoca pętli (4 rozne pętle).


//    Wyscig:
//    1. Stworz uczestnikSerwis i niech tam bedzie mozliwosc tworzenia uczestnika (uczestnik tj. kierowca i jego auto).
//    Mozesz tam zrobi, zeby sie losowo wszystko dobieralo, albo wg okreslonych typow.
//    Najwazniejsza metoda tworzaca liste uczestnikow ma przyjmowac w argumentach liczbe uczestnikow, tak
//    zebysmy mogli decydowac ilu kierowcow ma wziac udzial w wyscigu.
//    2. W klasie samochod stworz metode losowoStworzSamochod.
//    3. W klasach samochod i kierowca mamy metody obslugujace zmniejszanie zycia i wytrzymalosc samochodu jednak ich
//    nie uzylismy. Zastosowalismy bardzo podobna logike w metodach zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd itd.
//    Chcialbym, zebys to ujednolicil tak, zeby jednak te metody byly uzywane i po kazdym zmniejszeniu, zeby byl sprawdzany
//    stan zycia i jesli jest ponizej lub rowne 0 to dalej taki uczestnik ma juz nie brac udzialu w wyscigu.
//    4. Czas o przejezdzie odcinka powinien byc w minutach.
//    5. Zobacz czy teraz bylbys w stanie zrobic starcie. W klasie samochod po kazdym odcinku masz czas tego odcinka (czasPRzejazduOdcinka), ale takze
//    total czas (czasPrzejazdu). Metode dodajCzasPrzejazduOdcinka jak i dodajPrzejechanyDystans gdzies w wyscigSerwis nalezy
//    wywolac i tym samym bedziesz mial po kazdym odcinku info ile uczestnik przejechal km i w jakim czasie, a ten czas potrzebny
//    Ci jest do starcia. Pamietaj, ze starcie chcemy sprawdzac po przejechaniu przez wszystkich danego odcinka i wtedy
//    sprawdzamy czy na tym odcinku byl wypadek. Jak bedzie za duzo wypadkow to trzeba bedzie cos pozmieniac, zeby
//    nie gineli za szybko :)
    //stream
    //endpoint mapping
    //postman zainstalować

    //5. for po wszystkich uczesnikach , kolejny for

    //TODO: Damian Stwórz klase Pokoj (nazwa pokoju) i Gosc (imie, nazwisko i wiek). Zrob liste gosci i dwa pokoje. Za pomocą streama zrób selekcje gosci.
    //Goscie majacy imie na inna litere niz "A" i wiek powyzej 30 lat ida do jenego pokoju, a reszta do drugiego. Potem sprawdz
    //ile jest osob w pokojach i czy w sumie jest tyle samo co na liscie gosci.
    //Optionale -> obejrzyj ten materiał.

    public static void main(String[] args) {


        //    1. Wypisz liczby od 5 do 15

/*        for(int i = 5; i < 16; i++){
            System.out.println(i);
        }


        int licznik = 4;

        while(licznik<15){
            licznik++;
            System.out.println(licznik);
        }*/

        //    2. Wypisz liczby od 1 do 10, ale dla co drugiej dodaj krótki tekst "co druga liczba"


 /*       int[] tablica = new int[11];
        for(int i = 1; i <= 10; i++){
            tablica[i] = i;

            boolean x = i % 2 == 0;
            if (x) {
                System.out.println(tablica[i] + " co druga liczba");
            } else {
                System.out.println(i);
            }


        }
        }*/


        //    3. Zrob listę imion (minimum 5) i wypisz wszystkie imiona (dwa maja sie zaczynac na litere "A"), a do każdego dodaj to samo nazwisko

/*        List<String> listaImion = new ArrayList<>();
        listaImion.add("Ala");
        listaImion.add("Ula");
        listaImion.add("Rita");
        listaImion.add("Aleksander");
        listaImion.add("Ela");

        for (int i = 0; i < listaImion.size(); i++) {
            listaImion.set(i, listaImion.get(i).concat(" Nowak"));

             }*/
   /*         for (String imieNazwisko : listaImion) {
                System.out.println(imieNazwisko);
            }*/


        //    4. Wypisz imiona z powyzszej listy, a jeśli imię zaczyna się od litery “A” to nie dodawaj nazwiska


/*            for (int i = 0; i < listaImion.size(); i++) {
                if (listaImion.get(i).startsWith("A")) {
                    String imieNazwisko = listaImion.get(i);
                    int spacja = imieNazwisko.indexOf(" ");
                    listaImion.set(i, listaImion.get(i).substring(0, spacja));

                }


            }
            for (String imieNazwisko : listaImion) {
                System.out.println(imieNazwisko);
            }*/
        //1. Zrob jedną listę z imionami i nazwiskami, a potem metodę, która zamieni kolejność imię nazwisko na nazwisko imię
        // w przypadku kiedy nazwisko ma więcej niż 6 znaków. Wypisz na tym etapie. Potem zmień dla wszystkich wielkość liter
        // na WIELKIE LITERY. Wypisz listę i przy każdej osobie zrób liczbę porządkową: 1. Ala Nowak 2. Ktoś tam itd.

        List<String> listaImionNazwisk = new ArrayList<String>();

        listaImionNazwisk.add("Mateusz Baranowski");
        listaImionNazwisk.add("Aleksandra Szulc");
        listaImionNazwisk.add("Krzysztof Urbański");
        listaImionNazwisk.add("Bartek Malinowski");
        listaImionNazwisk.add("Michalina Janicka");
        listaImionNazwisk.add("Klara Zając");
        listaImionNazwisk.add("Amelia Król");
        listaImionNazwisk.add("Paulina Domagała");
        listaImionNazwisk.add("Maksymilian Kamiński");
        listaImionNazwisk.add("Zofia Sobolewska");
        listaImionNazwisk.add("Jakub Wasilewski");
        listaImionNazwisk.add("Mateusz Łukasik");

        for (int i = 0; i < listaImionNazwisk.size(); i++) {
            String imieNazwisko = listaImionNazwisk.get(i);
            int spacja = imieNazwisko.indexOf(" ");
            int koniecNazwiska = imieNazwisko.length();
            if (listaImionNazwisk.get(i).substring(spacja, koniecNazwiska).length() > 6) {
                //listaImionNazwisk.set(i, listaImionNazwisk.get(i).trim());
                listaImionNazwisk.set(i, listaImionNazwisk.get(i).substring(spacja+1, koniecNazwiska)+ " " + listaImionNazwisk.get(i).substring(0, spacja));

            }


        }
        for (String imionaNazwiska : listaImionNazwisk) {
            System.out.println(imionaNazwiska);

        }
        System.out.println();
        for (int i = 0; i < listaImionNazwisk.size(); i++) {
            listaImionNazwisk.set(i, listaImionNazwisk.get(i).toUpperCase());
        }
        for (String imionaNazwiska : listaImionNazwisk) {
            System.out.println(imionaNazwiska);
        }
        System.out.println();


        //Wypisz listę i przy każdej osobie zrób liczbę porządkową: 1. Ala Nowak 2. Ktoś tam itd.
        for (int i=1; i < listaImionNazwisk.size()+1; i++){
            String liczbaPorzadkowa = String.valueOf(i);
            listaImionNazwisk.set(i-1, liczbaPorzadkowa.concat(". " + listaImionNazwisk.get(i-1)));
        }
        for (String imionaNazwiska: listaImionNazwisk){
            System.out.println(imionaNazwiska);
        }
              {

        }




            // 2. Zrob listę liczb. Przetasuj ją. Znajdź najwieksza i najmniejsza wartość. Wypisz jakie to liczby.
            // Potem ułóż malejąco i wypisz. Potem zrób sumowanie wszystkich liczb (możesz użyć rekurencji czyli metoda sumującą
            // będzie wywoływać sama siebie jeśli na liście są jeszcze jakieś liczby które trzeba zsumowac). Wypisz ile wynosi suma.

            List<Integer> listaLiczb = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                listaLiczb.add(i + 1);
            }
            Collections.shuffle(listaLiczb);
            System.out.println(listaLiczb);


            int najwiekszaLiczba = 0;
        for (int i=0; i < listaLiczb.size(); i++){
          try{
              int wiekszaLiczba = Math.max(listaLiczb.get(i), listaLiczb.get(i+1));
              if(wiekszaLiczba > najwiekszaLiczba){
                  najwiekszaLiczba = wiekszaLiczba;
                  System.out.println("Zwiększam największą do " + wiekszaLiczba);
          }

           }
          catch (IndexOutOfBoundsException e){
              //System.out.println("Wyłapał błąd");
          }
        }
        Collections.sort(listaLiczb);
        Collections.reverse(listaLiczb);
        System.out.println(listaLiczb);
        System.out.println();

        int suma = 0;
        for (int i = 0; i < listaLiczb.size(); i++){
            suma += listaLiczb.get(i);

        }
        System.out.println(suma);
        System.out.println();

        class Sumowanie{

             private Integer sumowanie(Integer a, Integer b){

               Integer c = a + b;
               if (c == 0){
                   sumowanie(a,b+1);
               }
             return c;
             }

        }
        Sumowanie sumowanie = new Sumowanie();
        int suma2 = 0;
        for (int i = 0; i < listaLiczb.size(); i++){
        suma2 = sumowanie.sumowanie(suma2,listaLiczb.get(i));
        }
        System.out.println(suma2);
        System.out.println();
        }
    }










