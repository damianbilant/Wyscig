package com.example.demo;

import com.example.demo.model.Pogoda;
import com.example.demo.model.TrasaLevel;
import com.example.demo.model.TypKierowcy;
import com.example.demo.model.TypSamochodu;
import com.example.demo.serwis.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WyscigApplication {



	public static void main(String[] args) {
		SpringApplication.run(WyscigApplication.class, args);

		WyscigSerwis wyscigSerwis = new WyscigSerwis();
		wyscigSerwis.tworzenieWyscigu();






	}

}
