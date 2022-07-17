package com.example.demo.controller;

import com.example.demo.serwis.wyscig.WyscigSerwis;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WyscigController {
    private WyscigSerwis wyscigSerwis;

    public WyscigController(WyscigSerwis wyscigSerwis){
        this.wyscigSerwis = wyscigSerwis;
    }
    @RequestMapping(method = RequestMethod.GET, path = "/wyscig/liczbauczestnikow", produces = MediaType.APPLICATION_JSON_VALUE)
    public void wyscigUczestnicy(@RequestParam (value = "liczba") int liczba){
    wyscigSerwis.tworzenieWyscigu(liczba);
    }
}
