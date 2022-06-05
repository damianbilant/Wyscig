package com.example.demo.serwis.wyscig;

import com.example.demo.model.kierowca.Kierowca;
import com.example.demo.model.odcinek.Odcinek;
import com.example.demo.model.samochod.Samochod;
import com.example.demo.model.odcinek.TypOdcinka;
import com.example.demo.model.Uczestnik;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WyscigZycieSerwis {

    public void zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduStarcie(String nazwaZdarzenia, String nazwaOdcinka, Uczestnik uczestnik,
                                                                        Uczestnik innyUczestnik, int minZycieKierowca, int maxZycieKierowca,
                                                                        int minWytrzymaloscSamochodu, int maxWytrzymaloscSamochodu, List<Uczestnik> listaUczestnikow) {

        uczestnik.getKierowca().aktualizacjaZycia(minZycieKierowca, maxZycieKierowca);
        innyUczestnik.getKierowca().aktualizacjaZycia(minZycieKierowca, maxZycieKierowca);
        uczestnik.getSamochod().aktualizacjaWytrzymalosci(minWytrzymaloscSamochodu, maxWytrzymaloscSamochodu);
        innyUczestnik.getSamochod().aktualizacjaWytrzymalosci(minWytrzymaloscSamochodu, maxWytrzymaloscSamochodu);

        System.out.println("Kierowca " + uczestnik.getKierowca().getTypKierowcy() + " jadący samochodem " + uczestnik.getSamochod().getTypSamochodu() +
                " pokonujący odcinek " + nazwaOdcinka + " miał " + nazwaZdarzenia + " z " + innyUczestnik.getKierowca().getTypKierowcy() + " jadącym samochodem "
                + innyUczestnik.getSamochod().getTypSamochodu() + ".");
        skutkiStarcia(uczestnik, innyUczestnik);
    }

    private void skutkiStarcia(Uczestnik uczestnik, Uczestnik innyUczestnik) {
        if (uczestnik.getKierowca().getZycieKierowcy() > 0 && innyUczestnik.getKierowca().getZycieKierowcy() > 0
                && uczestnik.getSamochod().getWytrzymaloscSamochodu() > 0 && innyUczestnik.getSamochod().getWytrzymaloscSamochodu() > 0) {

            System.out.println("Obaj odnieśli obrażenia i teraz ich życie wynosi " + uczestnik.getKierowca().getZycieKierowcy() + " " + innyUczestnik.getKierowca().getZycieKierowcy() +
                    " natomiast samochody uległy uszkodzeniu i ich wytrzymałości wynoszą: " + uczestnik.getSamochod().getWytrzymaloscSamochodu() + " " + innyUczestnik.getSamochod().getWytrzymaloscSamochodu() + ".");

        } else if (uczestnik.getKierowca().getZycieKierowcy() <= 0 && innyUczestnik.getKierowca().getZycieKierowcy() > 0
                && uczestnik.getSamochod().getWytrzymaloscSamochodu() > 0 && innyUczestnik.getSamochod().getWytrzymaloscSamochodu() > 0) {

            System.out.println("Kierowca " + uczestnik.getKierowca().getTypKierowcy() + " nie wytrzymał trudów wyścigu i muszą się nim zająć służby medyczne. Kierowca " + innyUczestnik.getKierowca().getTypKierowcy() +
                    " odniósł obrażenia i teraz jego życie wynosi " + innyUczestnik.getKierowca().getZycieKierowcy() + " natomiast samochody uległy uszkodzeniu i ich wytrzymałości wynoszą: " +
                    uczestnik.getSamochod().getWytrzymaloscSamochodu() + " " + innyUczestnik.getSamochod().getWytrzymaloscSamochodu() + ".");

        } else if (uczestnik.getKierowca().getZycieKierowcy() > 0 && innyUczestnik.getKierowca().getZycieKierowcy() <= 0
                && uczestnik.getSamochod().getWytrzymaloscSamochodu() > 0 && innyUczestnik.getSamochod().getWytrzymaloscSamochodu() > 0) {

            System.out.println("Kierowca " + innyUczestnik.getKierowca().getTypKierowcy() + " nie wytrzymał trudów wyścigu i muszą się nim zająć służby medyczne. Kierowca " + uczestnik.getKierowca().getTypKierowcy() +
                    " odniósł obrażenia i teraz jego życie wynosi " + innyUczestnik.getKierowca().getZycieKierowcy() + " natomiast samochody uległy uszkodzeniu i ich wytrzymałości wynoszą: " +
                    uczestnik.getSamochod().getWytrzymaloscSamochodu() + " " + innyUczestnik.getSamochod().getWytrzymaloscSamochodu() + ".");

        } else if (uczestnik.getKierowca().getZycieKierowcy() > 0 && innyUczestnik.getKierowca().getZycieKierowcy() > 0
                && uczestnik.getSamochod().getWytrzymaloscSamochodu() <= 0 && innyUczestnik.getSamochod().getWytrzymaloscSamochodu() > 0) {

            System.out.println("Obaj odnieśli obrażenia i teraz ich życie wynosi " + uczestnik.getKierowca().getZycieKierowcy() + " " + innyUczestnik.getKierowca().getZycieKierowcy() +
                    " natomiast samochód " + uczestnik.getSamochod().getTypSamochodu() + " nie wytrzymał trudów wyścigu i nadaje się już tylko na złom, a samochód " + innyUczestnik.getSamochod().getTypSamochodu() + " uległ uszkodzeniu i jego wytrzymałość wynosi: " + innyUczestnik.getSamochod().getWytrzymaloscSamochodu() + ".");

        } else if (uczestnik.getKierowca().getZycieKierowcy() > 0 && innyUczestnik.getKierowca().getZycieKierowcy() > 0
                && uczestnik.getSamochod().getWytrzymaloscSamochodu() > 0 && innyUczestnik.getSamochod().getWytrzymaloscSamochodu() <= 0) {

            System.out.println("Obaj odnieśli obrażenia i teraz ich życie wynosi " + uczestnik.getKierowca().getZycieKierowcy() + " " + innyUczestnik.getKierowca().getZycieKierowcy() +
                    " natomiast samochód " + innyUczestnik.getSamochod().getTypSamochodu() + " nie wytrzymał trudów wyścigu i nadaje się już tylko na złom, a samochód " + uczestnik.getSamochod().getTypSamochodu() + " uległ uszkodzeniu i jego wytrzymałość wynosi: " + uczestnik.getSamochod().getWytrzymaloscSamochodu() + ".");

        } else if (uczestnik.getKierowca().getZycieKierowcy() <= 0 && innyUczestnik.getKierowca().getZycieKierowcy() <= 0
                && uczestnik.getSamochod().getWytrzymaloscSamochodu() > 0 && innyUczestnik.getSamochod().getWytrzymaloscSamochodu() > 0) {

            System.out.println("Obaj kierowcy nie wytrzymali trudów wyścigu i muszą się nimi zająć służby medyczne. Natomiast samochody uległy uszkodzeniu i ich wytrzymałości wynoszą: " +
                    uczestnik.getSamochod().getWytrzymaloscSamochodu() + " " + innyUczestnik.getSamochod().getWytrzymaloscSamochodu() + ".");

        } else if (uczestnik.getKierowca().getZycieKierowcy() > 0 && innyUczestnik.getKierowca().getZycieKierowcy() > 0
                && uczestnik.getSamochod().getWytrzymaloscSamochodu() <= 0 && innyUczestnik.getSamochod().getWytrzymaloscSamochodu() <= 0) {

            System.out.println("Obaj odnieśli obrażenia i teraz ich życie wynosi " + uczestnik.getKierowca().getZycieKierowcy() + " " + innyUczestnik.getKierowca().getZycieKierowcy() +
                    " .Natomiast samochody nie wytrzymały trudów wyścigu i nadają się już tylko na złom.");

        } else if (uczestnik.getKierowca().getZycieKierowcy() <= 0 && innyUczestnik.getKierowca().getZycieKierowcy() > 0
                && uczestnik.getSamochod().getWytrzymaloscSamochodu() <= 0 && innyUczestnik.getSamochod().getWytrzymaloscSamochodu() > 0) {

            System.out.println("Kierowca " + uczestnik.getKierowca().getTypKierowcy() + " nie wytrzymał trudów wyścigu i muszą się nim zająć służby medyczne, a jego samochód " + uczestnik.getSamochod().getTypSamochodu() + " nadaje się już tylko na złom. Natomiast kierowca " + innyUczestnik.getKierowca().getTypKierowcy() +
                    " odniósł obrażenia i teraz jego życie wynosi " + innyUczestnik.getKierowca().getZycieKierowcy() + " a jego samochód uległ uszkodzeniu i jego wytrzymałość wynosi: " + innyUczestnik.getSamochod().getWytrzymaloscSamochodu() + ".");

        } else if (uczestnik.getKierowca().getZycieKierowcy() > 0 && innyUczestnik.getKierowca().getZycieKierowcy() <= 0
                && uczestnik.getSamochod().getWytrzymaloscSamochodu() > 0 && innyUczestnik.getSamochod().getWytrzymaloscSamochodu() <= 0) {

            System.out.println("Kierowca " + innyUczestnik.getKierowca().getTypKierowcy() + " nie wytrzymał trudów wyścigu i muszą się nim zająć służby medyczne, a jego samochód " + innyUczestnik.getSamochod().getTypSamochodu() + " nadaje się już tylko na złom. Natomiast kierowca " + uczestnik.getKierowca().getTypKierowcy() +
                    " odniósł obrażenia i teraz jego życie wynosi " + uczestnik.getKierowca().getZycieKierowcy() + " a jego samochód uległ uszkodzeniu i jego wytrzymałość wynosi: " + uczestnik.getSamochod().getWytrzymaloscSamochodu() + ".");

        } else if (uczestnik.getKierowca().getZycieKierowcy() <= 0 && innyUczestnik.getKierowca().getZycieKierowcy() > 0
                && uczestnik.getSamochod().getWytrzymaloscSamochodu() > 0 && innyUczestnik.getSamochod().getWytrzymaloscSamochodu() <= 0) {

            System.out.println("Kierowca " + uczestnik.getKierowca().getTypKierowcy() + " nie wytrzymał trudów wyścigu i muszą się nim zająć służby medyczne, a jego samochód uległ uszkodzeniu i jego wytrzymałość wynosi: " + uczestnik.getSamochod().getWytrzymaloscSamochodu() +
                    "Natomiast kierowca " + innyUczestnik.getKierowca().getTypKierowcy() + " odniósł obrażenia i teraz jego życie wynosi " + innyUczestnik.getKierowca().getZycieKierowcy() + " ,ale jego samochód " + innyUczestnik.getSamochod().getTypSamochodu() + " nadaje się już tylko na złom.");

        } else if (uczestnik.getKierowca().getZycieKierowcy() > 0 && innyUczestnik.getKierowca().getZycieKierowcy() <= 0
                && uczestnik.getSamochod().getWytrzymaloscSamochodu() <= 0 && innyUczestnik.getSamochod().getWytrzymaloscSamochodu() > 0) {

            System.out.println("Kierowca " + innyUczestnik.getKierowca().getTypKierowcy() + " nie wytrzymał trudów wyścigu i muszą się nim zająć służby medyczne, a jego samochód uległ uszkodzeniu i jego wytrzymałość wynosi: " + innyUczestnik.getSamochod().getWytrzymaloscSamochodu() +
                    "Natomiast kierowca " + uczestnik.getKierowca().getTypKierowcy() + " odniósł obrażenia i teraz jego życie wynosi " + uczestnik.getKierowca().getZycieKierowcy() + " ,ale jego samochód " + uczestnik.getSamochod().getTypSamochodu() + " nadaje się już tylko na złom.");
        }
    }

    public void aktualizacjaKierowcaSamochodStarcie(Uczestnik uczestnik, Uczestnik innyUczestnik, Odcinek obecnyOdcinek, List<Uczestnik> listaUczestnikow) {

        String nazwaOdcinka = obecnyOdcinek.getNazwaOdcinka();
        if ((uczestnik.getSamochod().getCzasPrzejazdu() - innyUczestnik.getSamochod().getCzasPrzejazdu()) > 2.0 && (uczestnik.getSamochod().getCzasPrzejazdu() - innyUczestnik.getSamochod().getCzasPrzejazdu() <= 5.0)) {
            String nazwaZdarzenia = "wypadek";
            System.out.println();
            System.out.println("Wypadek!");
            if (obecnyOdcinek.getTypOdcinka() == TypOdcinka.ZAKRET) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduStarcie(nazwaZdarzenia, nazwaOdcinka, uczestnik, innyUczestnik, 25, 50, 25, 50, listaUczestnikow);
            }
            if (obecnyOdcinek.getTypOdcinka() == TypOdcinka.ZJAZD) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduStarcie(nazwaZdarzenia, nazwaOdcinka, uczestnik, innyUczestnik, 15, 30, 15, 30, listaUczestnikow);
            }
            if (obecnyOdcinek.getTypOdcinka() == TypOdcinka.PODJAZD) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduStarcie(nazwaZdarzenia, nazwaOdcinka, uczestnik, innyUczestnik, 5, 15, 5, 15, listaUczestnikow);
            }
            if (obecnyOdcinek.getTypOdcinka() == TypOdcinka.PROSTY) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduStarcie(nazwaZdarzenia, nazwaOdcinka, uczestnik, innyUczestnik, 0, 5, 0, 5, listaUczestnikow);
            }
        } else if ((uczestnik.getSamochod().getCzasPrzejazdu() - innyUczestnik.getSamochod().getCzasPrzejazdu()) > 1.0 && (uczestnik.getSamochod().getCzasPrzejazdu() - innyUczestnik.getSamochod().getCzasPrzejazdu() <= 2.0)) {
            String nazwaZdarzenia = "stłuczka";
            System.out.println();
            System.out.println("Stłuczka!");
            if (obecnyOdcinek.getTypOdcinka() == TypOdcinka.ZAKRET) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduStarcie(nazwaZdarzenia, nazwaOdcinka, uczestnik, innyUczestnik, 15, 30, 15, 30, listaUczestnikow);
            }
            if (obecnyOdcinek.getTypOdcinka() == TypOdcinka.ZJAZD) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduStarcie(nazwaZdarzenia, nazwaOdcinka, uczestnik, innyUczestnik, 10, 15, 10, 15, listaUczestnikow);
            }
            if (obecnyOdcinek.getTypOdcinka() == TypOdcinka.PODJAZD) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduStarcie(nazwaZdarzenia, nazwaOdcinka, uczestnik, innyUczestnik, 5, 10, 5, 10, listaUczestnikow);
            }
            if (obecnyOdcinek.getTypOdcinka() == TypOdcinka.PROSTY) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduStarcie(nazwaZdarzenia, nazwaOdcinka, uczestnik, innyUczestnik, 2, 5, 2, 5, listaUczestnikow);
            }

        } else if ((uczestnik.getSamochod().getCzasPrzejazdu() - innyUczestnik.getSamochod().getCzasPrzejazdu()) <= 1.0) {
            String nazwaZdarzenia = "obtarcie";
            System.out.println();
            System.out.println("Obtarcie!");
            if (obecnyOdcinek.getTypOdcinka() == TypOdcinka.ZAKRET) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduStarcie(nazwaZdarzenia, nazwaOdcinka, uczestnik, innyUczestnik, 10, 15, 10, 15, listaUczestnikow);
            }
            if (obecnyOdcinek.getTypOdcinka() == TypOdcinka.ZJAZD) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduStarcie(nazwaZdarzenia, nazwaOdcinka, uczestnik, innyUczestnik, 5, 10, 5, 10, listaUczestnikow);
            }
            if (obecnyOdcinek.getTypOdcinka() == TypOdcinka.PODJAZD) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduStarcie(nazwaZdarzenia, nazwaOdcinka, uczestnik, innyUczestnik, 2, 5, 2, 5, listaUczestnikow);
            }
            if (obecnyOdcinek.getTypOdcinka() == TypOdcinka.PROSTY) {
                zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduStarcie(nazwaZdarzenia, nazwaOdcinka, uczestnik, innyUczestnik, 0, 2, 0, 2, listaUczestnikow);
            }
        }
    }

    public void zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduNastepnyOdcinek(TypOdcinka obecnyOdcinek, TypOdcinka nastepnyOdcinek, Samochod samochod, Kierowca kierowca, int minZycieKierowca,
                                                                                int maxZycieKierowca, int minWytrzymaloscSamochodu, int maxWytrzymaloscSamochodu, List<Uczestnik> listaUczestnikow) {
        int aktualizacjaZycia = kierowca.aktualizacjaZycia(minZycieKierowca, maxZycieKierowca);
        int aktualizacjaWytrzymalosci = samochod.aktualizacjaWytrzymalosci(minWytrzymaloscSamochodu, maxWytrzymaloscSamochodu);

        if ((aktualizacjaZycia != 0 && (aktualizacjaWytrzymalosci != 0))) {

            if (kierowca.getZycieKierowcy() > 0 && samochod.getWytrzymaloscSamochodu() > 0) {

                System.out.println("Kierowca " + kierowca.getTypKierowcy() + " jadący samochodem " + samochod.getTypSamochodu() + " pokonując odcinek " +
                        obecnyOdcinek + " nie dostosował swoich umiejętności i prędkości samochodu do zbliżającego się odcinka " + nastepnyOdcinek +
                        " w wyniku czego odniósł obrażenia. Teraz jego życie wynosi " + kierowca.getZycieKierowcy() +
                        ", a samochód uległ uszkodzeniu i jego wytrzymałość wynosi " + samochod.getWytrzymaloscSamochodu() + ".");

            } else if (kierowca.getZycieKierowcy() <= 0 && samochod.getWytrzymaloscSamochodu() > 0) {

                System.out.println("Kierowca " + kierowca.getTypKierowcy() + " jadący samochodem " + samochod.getTypSamochodu() + " pokonując odcinek " +
                        obecnyOdcinek + " nie dostosował swoich umiejętności i prędkości samochodu do zbliżającego się odcinka " + nastepnyOdcinek +
                        " w wyniku czego odniósł bardzo ciężkie obrażenia i nie może kontynuować wyścigu.");

            } else if (kierowca.getZycieKierowcy() > 0 && samochod.getWytrzymaloscSamochodu() <= 0) {

                System.out.println("Kierowca " + kierowca.getTypKierowcy() + " jadący samochodem " + samochod.getTypSamochodu() + " pokonując odcinek " +
                        obecnyOdcinek + " nie dostosował swoich umiejętności i prędkości samochodu do zbliżającego się odcinka " + nastepnyOdcinek +
                        " w wyniku czego odniósł obrażenia. Teraz jego życie wynosi " + kierowca.getZycieKierowcy() +
                        ", ale samochód uległ bardzo poważnym uszkodzeniom i nie może kontynuować wyścigu.");

            } else if (kierowca.getZycieKierowcy() < 0 && samochod.getWytrzymaloscSamochodu() < 0) {

                System.out.println("Kierowca " + kierowca.getTypKierowcy() + " jadący samochodem " + samochod.getTypSamochodu() + " pokonując odcinek " +
                        obecnyOdcinek + " nie dostosował swoich umiejętności i prędkości samochodu do zbliżającego się odcinka " + nastepnyOdcinek +
                        " w wyniku czego odniósł bardzo ciężkie obrażenia, a jego samochód został kompletnie zniszczony i nie może kontynuować wyścigu.");
            }

        } else if ((aktualizacjaZycia != 0 && (aktualizacjaWytrzymalosci == 0))) {

            if (kierowca.getZycieKierowcy() > 0) {

                System.out.println("Kierowca " + kierowca.getTypKierowcy() + " jadący samochodem " + samochod.getTypSamochodu() + " pokonując odcinek " +
                        obecnyOdcinek + " nie dostosował swoich umiejętności i prędkości samochodu do zbliżającego się odcinka " + nastepnyOdcinek +
                        " w wyniku czego odniósł obrażenia. Teraz jego życie wynosi " + kierowca.getZycieKierowcy() +
                        ", natomiast samochód nie uległ uszkodzeniu.");

            } else if (kierowca.getZycieKierowcy() <= 0) {

                System.out.println("Kierowca " + kierowca.getTypKierowcy() + " jadący samochodem " + samochod.getTypSamochodu() + " pokonując odcinek " +
                        obecnyOdcinek + " nie dostosował swoich umiejętności i prędkości samochodu do zbliżającego się odcinka " + nastepnyOdcinek +
                        " w wyniku czego odniósł bardzo ciężkie obrażenia i nie może kontynuować wyścigu.");
            }

        } else if ((aktualizacjaZycia == 0 && (aktualizacjaWytrzymalosci != 0))) {

            if (samochod.getWytrzymaloscSamochodu() > 0) {

                System.out.println("Kierowca " + kierowca.getTypKierowcy() + " jadący samochodem " + samochod.getTypSamochodu() + " pokonując odcinek " +
                        obecnyOdcinek + " nie dostosował swoich umiejętności i prędkości samochodu do zbliżającego się odcinka " + nastepnyOdcinek +
                        " w wyniku czego nie odniósł obrażeń , ale samochód uległ uszkodzeniu i jego wytrzymałość wynosi " + samochod.getWytrzymaloscSamochodu() + ".");

            } else if (samochod.getWytrzymaloscSamochodu() <= 0) {

                System.out.println("Kierowca " + kierowca.getTypKierowcy() + " jadący samochodem " + samochod.getTypSamochodu() + " pokonując odcinek " +
                        obecnyOdcinek + " nie dostosował swoich umiejętności i prędkości samochodu do zbliżającego się odcinka " + nastepnyOdcinek +
                        " w wyniku czego jego samochód uległ bardzo poważnym uszkodzeniom i nie może kontynuować wyścigu.");
            }
        } else {
            //wszystko 0 i nic nie powinno się wypisywać
        }
    }

    public void zmniejszanieZyciaKierowcyIwytrzymalosciSamochoduPrzejazd(String nazwaOdcinka, Samochod samochod, Kierowca kierowca,
                                                                         int minZycieKierowca, int maxZycieKierowca, int minWytrzymaloscSamochodu,
                                                                         int maxWytrzymaloscSamochodu, List<Uczestnik> listaUczestnikow) {
        int aktualizacjaZycia = kierowca.aktualizacjaZycia(minZycieKierowca, maxZycieKierowca);
        int aktualizacjaWytrzymalosci = samochod.aktualizacjaWytrzymalosci(minWytrzymaloscSamochodu, maxWytrzymaloscSamochodu);

        if ((aktualizacjaZycia != 0 && (aktualizacjaWytrzymalosci != 0))) {

            if (kierowca.getZycieKierowcy() > 0 && samochod.getWytrzymaloscSamochodu() > 0) {

                System.out.println("Kierowca " + kierowca.getTypKierowcy() + " jadący samochodem " + samochod.getTypSamochodu() + "  nie dostosował swoich " +
                        "umiejętności i prędkości samochodu podczas przejazdu przez odcinek " + nazwaOdcinka +
                        " w wyniku czego odniósł obrażenia. Teraz jego życie wynosi " + kierowca.getZycieKierowcy() +
                        ", a samochód uległ uszkodzeniu i jego wytrzymałość wynosi " + samochod.getWytrzymaloscSamochodu() + ".");

            } else if (kierowca.getZycieKierowcy() <= 0 && samochod.getWytrzymaloscSamochodu() > 0) {

                System.out.println("Kierowca " + kierowca.getTypKierowcy() + " jadący samochodem " + samochod.getTypSamochodu() + " nie dostosował swoich" +
                        " umiejętności i prędkości samochodu podczas przejazdu przez odcinek " + nazwaOdcinka +
                        " w wyniku czego odniósł bardzo ciężkie obrażenia i nie może kontynuować wyścigu.");

            } else if (kierowca.getZycieKierowcy() > 0 && samochod.getWytrzymaloscSamochodu() <= 0) {

                System.out.println("Kierowca " + kierowca.getTypKierowcy() + " jadący samochodem " + samochod.getTypSamochodu() + " nie dostosował swoich" +
                        " umiejętności i prędkości samochodu podczas przejazdu przez odcinek " + nazwaOdcinka +
                        " w wyniku czego odniósł obrażenia. Teraz jego życie wynosi " + kierowca.getZycieKierowcy() +
                        ", ale jego samochód uległ bardzo poważnym uszkodzeniom i nie może kontynuować wyścigu.");

            } else if (kierowca.getZycieKierowcy() < 0 && samochod.getWytrzymaloscSamochodu() < 0) {

                System.out.println("Kierowca " + kierowca.getTypKierowcy() + " jadący samochodem " + samochod.getTypSamochodu() + " nie dostosował swoich" +
                        " umiejętności i prędkości samochodu podczas przejazdu przez odcinek " + nazwaOdcinka +
                        " w wyniku czego odniósł bardzo ciężkie obrażenia, a jego samochód został kompletnie zniszczony i nie może kontynuować wyścigu.");
            }

        } else if ((aktualizacjaZycia != 0 && (aktualizacjaWytrzymalosci == 0))) {


            if (kierowca.getZycieKierowcy() > 0) {

                System.out.println("Kierowca " + kierowca.getTypKierowcy() + " jadący samochodem " + samochod.getTypSamochodu() + " nie dostosował swoich" +
                        " umiejętności i prędkości samochodu podczas przejazdu przez odcinek " + nazwaOdcinka +
                        " w wyniku czego odniósł obrażenia. Teraz jego życie wynosi " + kierowca.getZycieKierowcy() +
                        ", natomiast samochód nie uległ uszkodzeniu.");

            } else if (kierowca.getZycieKierowcy() <= 0) {

                System.out.println("Kierowca " + kierowca.getTypKierowcy() + " jadący samochodem " + samochod.getTypSamochodu() + " nie dostosował swoich" +
                        " umiejętności i prędkości samochodu podczas przejazdu przez odcinek " + nazwaOdcinka +
                        " w wyniku czego odniósł bardzo ciężkie obrażenia i nie może kontynuować wyścigu.");
            }

        } else if ((aktualizacjaZycia == 0 && (aktualizacjaWytrzymalosci != 0))) {


            if (samochod.getWytrzymaloscSamochodu() > 0) {

                System.out.println("Kierowca " + kierowca.getTypKierowcy() + " jadący samochodem " + samochod.getTypSamochodu() + " nie dostosował swoich" +
                        " umiejętności i prędkości samochodu podczas przejazdu przez odcinek " + nazwaOdcinka +
                        " w wyniku czego nie odniósł obrażeń , ale samochód uległ uszkodzeniu i jego wytrzymałość wynosi " + samochod.getWytrzymaloscSamochodu() + ".");

            } else if (samochod.getWytrzymaloscSamochodu() <= 0) {

                System.out.println("Kierowca " + kierowca.getTypKierowcy() + " jadący samochodem " + samochod.getTypSamochodu() + " nie dostosował swoich" +
                        " umiejętności i prędkości samochodu podczas przejazdu przez odcinek " + nazwaOdcinka +
                        " w wyniku czego jego samochód uległ bardzo poważnym uszkodzeniom i nie może kontynuować wyścigu.");
            }

        } else {
            //wszystko 0 i nic nie powinno się wypisywać
        }
    }

    public void usuwanieUczestnikaLubSamochodu(List<Uczestnik> listaUczestnikow) {
        listaUczestnikow.removeIf(uczestnik -> uczestnik.getKierowca().getZycieKierowcy() <= 0
                || uczestnik.getSamochod().getWytrzymaloscSamochodu() <= 0);
    }

}
