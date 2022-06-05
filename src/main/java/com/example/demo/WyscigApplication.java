package com.example.demo;

import com.example.demo.serwis.wyscig.WyscigNastepnySerwis;
import com.example.demo.serwis.wyscig.WyscigPrzejazdSerwis;
import com.example.demo.serwis.wyscig.WyscigSerwis;
import com.example.demo.serwis.wyscig.WyscigStarcieSerwis;
import com.example.demo.serwis.wyscig.WyscigZycieSerwis;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WyscigApplication {

	public static void main(String[] args) {
		SpringApplication.run(WyscigApplication.class, args);

		WyscigZycieSerwis wyscigZycieSerwis = new WyscigZycieSerwis();
		WyscigPrzejazdSerwis wyscigPrzejazdSerwis = new WyscigPrzejazdSerwis(wyscigZycieSerwis);
		WyscigStarcieSerwis wyscigStarcieSerwis = new WyscigStarcieSerwis(wyscigZycieSerwis);
		WyscigNastepnySerwis wyscigNastepnySerwis = new WyscigNastepnySerwis(wyscigZycieSerwis);

		WyscigSerwis wyscigSerwis = new WyscigSerwis(wyscigPrzejazdSerwis, wyscigStarcieSerwis, wyscigNastepnySerwis, wyscigZycieSerwis);
		wyscigSerwis.tworzenieWyscigu();

	}

}
