package com.example.demo.model;


import com.example.demo.utils.Utils;

public abstract class Kierowca {
    private TypKierowcy typKierowcy;
    private Integer znajomoscTrasy;
    private Integer stanTrzezwosci;
    private Integer szybkoscReakcji;
    private Integer zycieKierowcy;



    public Kierowca(TypKierowcy typKierowcy, Integer znajomoscTrasy, Integer stanTrzezwosci, Integer szybkoscReakcji, Integer zycieKierowcy) {
        this.typKierowcy = typKierowcy;
        this.znajomoscTrasy = znajomoscTrasy;
        this.stanTrzezwosci = stanTrzezwosci;
        this.szybkoscReakcji = szybkoscReakcji;
        this.zycieKierowcy = zycieKierowcy;
    }

    public TypKierowcy getTypKierowcy() {

        return typKierowcy;
    }

    public Integer getZnajomoscTrasy() {

        return znajomoscTrasy;
    }

    public Integer getStanTrzezwosci() {

        return stanTrzezwosci;
    }

    public Integer getSzybkoscReakcji() {

        return szybkoscReakcji;
    }

    public Integer getZycieKierowcy() {
        return  zycieKierowcy; }

    public void setZycieKierowcy(Integer zycieKierowcy) {

        this.zycieKierowcy = zycieKierowcy;
    }

    public void setSzybkoscReakcji(Integer szybkoscReakcji) {

        this.szybkoscReakcji = szybkoscReakcji;
    }

    private Integer ryzyko;

    public Integer getRyzyko() {
        return ryzyko;
    }

    public void setRyzyko(Integer ryzyko) {
        this.ryzyko = ryzyko;
    }



    public void nietrzezwoscZmniejszenieReakcji() {
        if (!czyTrzezwy()) {
            if (getSzybkoscReakcji() <= 1) {
                setSzybkoscReakcji(0);
            } else {
                Integer nowaSzybkoscReakcji = getSzybkoscReakcji() - 1;
                setSzybkoscReakcji(nowaSzybkoscReakcji);
            }
        }
    }

    private boolean czyTrzezwy() {
        boolean trzezwosc = true;
        if (getStanTrzezwosci() >= 4) {
            trzezwosc = false;
        }
        return trzezwosc;
    }
    //TODO:
    public void aktualizacjaReakcjiOdPogody( Pogoda pogoda) {
        Integer zmianaSzybkosciReakcji = getSzybkoscReakcji() + pogoda.getZmianaSzybkosciReakcjiKierowcy();
        setSzybkoscReakcji(zmianaSzybkosciReakcji);
        if (getSzybkoscReakcji() < 0){
            setSzybkoscReakcji(0);
        }
        System.out.println("Ze względu na pogodę reakcja kierowcy " + getTypKierowcy() + " to: " + getSzybkoscReakcji());
    }

    public void aktualizacjaZycia (int minZycieKierowca, int maxZycieKierowca) {
        Integer punktyZyciaDoZmniejszenia = Utils.losuj(minZycieKierowca, maxZycieKierowca);
        Integer zmniejszeniePunktowZycia = getZycieKierowcy() - punktyZyciaDoZmniejszenia;
        setZycieKierowcy(zmniejszeniePunktowZycia);
        if (getZycieKierowcy() <= 0){
            System.out.println("Kierowca " + getTypKierowcy() + " nie wytrzymał trudów wyścigu i muszą się nim zająć służby medyczne.");
            System.out.println("GAME OVER");
        }
    }
    //TODO: MICHAŁ
    public void znajomoscTrasyPredkosc (Samochod samochod){
        if(getZnajomoscTrasy() >= 9){
            samochod.setSzybkosc(samochod.getSzybkosc() + 15);
            System.out.println("Ze względu na bardzo dobrą znajomość trasy kierowcy " + getTypKierowcy() +
                    " prędkość na tym odcinku (prostym) zostaje zwiększona o 15 km/h i wynosi " + samochod.getSzybkosc());
        } else if ( getZnajomoscTrasy() >= 7 ){
            samochod.setSzybkosc(samochod.getSzybkosc() + 5);
            System.out.println("Ze względu na dobrą znajomość trasy kierowcy " + getTypKierowcy() +
                    " prędkość na tym odcinku (prostym) zostaje zwiększona o 5 km/h i wynosi " + samochod.getSzybkosc());
        }
    }
    public  void znajomoscTrasyPredkoscReset (Samochod samochod){
        if(getZnajomoscTrasy() >= 9) {
            samochod.setSzybkosc(samochod.getSzybkosc() - 15);
        } else if ( getZnajomoscTrasy() >= 7) {
            samochod.setSzybkosc(samochod.getSzybkosc() - 5);
        }
    }

    public void ustawienieRyzyka (){
        Integer ryzyko = 20 - (getZnajomoscTrasy() + getSzybkoscReakcji());
        if (ryzyko >= 4 && czyTrzezwy()) {
 /*           Integer zmniejszenieRyzyka = ryzyko - 4;
            ryzyko = zmniejszenieRyzyka;*/
            ryzyko -= 4;
            setRyzyko(ryzyko);
        } else if (ryzyko < 4 && czyTrzezwy()) {
            setRyzyko(0);
        } else if (!czyTrzezwy() && ryzyko <= 16) {
           ryzyko += 4;
           setRyzyko(ryzyko);
        } else {
            setRyzyko(20);
        }
    }

    @Override
    public String toString() {
        return getTypKierowcy() + " znajomosc trasy: " + getZnajomoscTrasy() + " poziom trzezwosci: " + getStanTrzezwosci() + " stan trzezwosci: "
                + czyTrzezwy() + " szybkosc reakcji: " + getSzybkoscReakcji() + " ryzyko: " + getRyzyko();
    }
}

   //W klasie Kierowcy utwórz pole ryzyko, do tego możesz dorzucić getter i setter bo będziemy tę wartość ustawiać i pobierać.
//Następnie utwórz metodę ustawienieRyzyka. Od sumy znajomości trasy i szybkości reakcji kierowcy będzie zależec ryzyko.
// Max ryzyka to 20 więc zrób różnicę pomiędzy 20, a sumą znajomości trasy i szybkości reakcji.
// I dalej jeśli ryzyko jest >= 4 i kierowca jest trzeźwy to obliczone wcześniej ryzyko zmniejszamy o 4.
// Jeśli ryzyko jest < 4 i kierowca jest trzeźwy to ustaw ryzyko na 0, a jeśli kierowca jest nietrzeźwy to ryzyko podnosimy o 4.
// Czyli mamy jakieś ryzyko na początku obliczone, a potem w zależności od tego czy kierowca jest trzeźwy dokonujemy jeszcze dodatkowej modyfikacji.


/*
Poprawka do drugiej części - wyścigSerwis - metoda przejazdOdcinekProsty. Trochę inaczej jednak będzie. Jeśli trudność odcinka >= 1 i
znajomość trasy < 4 to teraz dwa warianty:
        1. jeśli szybkość reakcji < 3 lub ryzyko > 15 to odejmij życie i stan auta o Utils.losuj(0,3).
        2. jeśli szybkość reakcji < 5 lub ryzyko > 10 to odejmij życie i stan auta o Utils.losuj(0,2).
        Natomiast jeśli trudność odcinka == 0 i znajomość trasy < 3 to jeden wariant:
        1. jeśli szybkość reakcji < 3 lub ryzyko > 15 to odejmij życie i stan auta o Utils.losuj(0,1).
        Dalej bez zmian czyli tutaj wywoał metodę liczącą szybkość przejazdu odcinka gdzie zapisany będzie czas i dystans.
        Dalej wywoałamy metodę zmianaOdcinka (jej jeszcze nie mamy) i tabelę (tego też jeszcze nie mamy).*/
