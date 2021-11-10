package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WyscigApplication {



	public static void main(String[] args) {
		SpringApplication.run(WyscigApplication.class, args);

	}

//Hej, trzeba w końcu zabrać się za tworzenie aplikacji. Ustaliliśmy, że będzie to jakiś wyścig. Zastanów się tak ogólnie jak byś widział taką grę/symulację wyścigu. Gra to może dużo powiedziane, bo finalnie będziemy odpalać wyścig i patrzeć kto wygrał...
//Możemy zrobić coś takiego, że na samym początku każdy uczestnik dostaje pule powiedzmy 20 punktów, z których może skonfigurować sobie auto lub konfiguracja dokona się losowo jeśli uczestnik tego nie zrobi sam. Po drodze można coś śmiesznego powymyślać, tak żeby może ścigali się po Mordorze i inne duperele :) Taka druga odsłona mojej apki Mordor-NFS :D
//Zastanów się co potrzebujemy mieć - auto (jakieś parametry), kierowce (jakieś parametry), może pogodę z warunkami drogowymi (ogólnie pogoda czy kilka zmiennych w pogodzie), trasa (tutaj też będziemy chcieli jakieś parametry, dzięki którym potem zbudujemy trasę, a z niej będą korzystać uczestnicy). Czyli potrzebujemy kilku klas z polami i pewnie ze dwa serwisy gdzie będziemy to wszystko tworzyć. W jednym z serwisów możesz spróbować zawrzeć już logikę tworzenia auta czy kierowcy.
//W sumie nad całością trzeba się zastanowić jak to widzimy, żeby potem połowy kodu nie zmienić... choć w pracy refactoring to chleb powszedni także nie obawiaj się pisać, a potem to kasować - za to potem płacą :D Jakby coś pisz, dzwoń :) ... jeśli za mało do zrobienia.


}
