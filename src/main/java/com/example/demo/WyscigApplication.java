package com.example.demo;

import com.example.demo.model.TypKierowcy;
import com.example.demo.serwis.KierowcaSerwis;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WyscigApplication {



	public static void main(String[] args) {
		SpringApplication.run(WyscigApplication.class, args);

		KierowcaSerwis kierowcaSerwis = new KierowcaSerwis();
		kierowcaSerwis.stworzKierowce(TypKierowcy.UBER);
		kierowcaSerwis.stworzKierowce(TypKierowcy.STARYDZIAD);

	}

}
