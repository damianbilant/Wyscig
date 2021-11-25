package com.example.demo;

import com.example.demo.model.TypKierowcy;
import com.example.demo.model.TypSamochodu;
import com.example.demo.serwis.KierowcaSerwis;
import com.example.demo.serwis.SamochodSerwis;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WyscigApplication {



	public static void main(String[] args) {
		SpringApplication.run(WyscigApplication.class, args);

		KierowcaSerwis kierowcaSerwis = new KierowcaSerwis();
		System.out.println();
		kierowcaSerwis.stworzKierowce(TypKierowcy.UBER);
		kierowcaSerwis.stworzKierowce(TypKierowcy.STARYDZIAD);
		System.out.println();

		SamochodSerwis samochodSerwis = new SamochodSerwis();
		samochodSerwis.stworzSamochod(TypSamochodu.SUV);
		samochodSerwis.stworzSamochod((TypSamochodu.COUPE));
		samochodSerwis.stworzSamochod((TypSamochodu.HATCHBACK));


	}

}
