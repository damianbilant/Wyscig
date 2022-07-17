package com.example.demo.controller;

import com.example.demo.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
public class Controller {

    @Autowired
    JdbcTemplate jdbcTemplate;

    List<String> stringList = new ArrayList<>();

    @RequestMapping(method = RequestMethod.POST, path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void save(@RequestBody String data) {
        stringList.add(data);
        stringList.stream()
                .forEach(System.out::println);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/name", produces = MediaType.APPLICATION_JSON_VALUE)
    public String giveMeName(@RequestParam(value = "index") int index) {

        String name = null;
        if (stringList.size() >= 1) {
            name = stringList.get(index);

        } else {
            System.out.println("Brak imienia");
        }
        return name;
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/deletename")
    public void delete(@RequestParam(value = "index") int index) {

        String name = null;
        if (stringList.size() >= 1) {
            name = stringList.remove(index);

        } else {
            System.out.println("Lista pusta, nie ma nic do usunięcia");
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/stringlist", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> giveMeListOfNames() {
        if (!this.stringList.isEmpty()) {
            return stringList;
        } else {
            System.out.println("Lista jest pusta.");
        }
        return Collections.emptyList();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/zapiszUczestnika", produces = MediaType.APPLICATION_JSON_VALUE)
    public void saveUser(@RequestBody Integer life) {
        UUID uuid = Utils.stworzUUID();
        Uczestnik uczestnik = new Uczestnik(uuid, life);
        jdbcTemplate.update("insert into uczestnicyWyscigu (uuid, zycieKierowcy) values (?,?)", uczestnik.getUuid(), uczestnik.getLife());
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public class Uczestnik {
        private UUID uuid;
        public Integer life;
    }
}


//TODO: poptrzeć o json, o statusach w postman