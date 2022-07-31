package com.example.demo.controller;

import com.example.demo.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(method = RequestMethod.POST, path = "/zapiszUczestnika", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> saveUser(@RequestBody Uczestnik uczestnik) {
        jdbcTemplate.update("insert into uczestnicyWyscigu (uuid, zycieKierowcy) values (?,?)", uczestnik.getUuid(), uczestnik.getLife());
        return ResponseEntity.ok(HttpStatus.OK);


    }

    /*@RequestMapping(method = RequestMethod.PUT, path = "/aktualizujUczestnika")
    public ResponseEntity<HttpStatus> actualUser(@RequestParam(value = "uuid") String uuid, @RequestParam(value = "life") int life) {
        jdbcTemplate.update("update uczestnicyWyscigu set zycieKierowcy=? where uuid=?", life, uuid);
        return ResponseEntity.ok(HttpStatus.OK);
    }*/

    @RequestMapping(method = RequestMethod.PUT, path = "/aktualizujUczestnika/{uuid}")
    public ResponseEntity<HttpStatus> actualUser(@PathVariable String uuid, @RequestParam(value = "life") int life) {
        jdbcTemplate.update("update uczestnicyWyscigu set zycieKierowcy=? where uuid=?", life, uuid);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/pobierzUczestnikow")
    public List<UczestnikDto> getUsers() {
        try {
            List<UczestnikDto> uczestnicy = jdbcTemplate.query("SELECT * FROM UCZESTNICYWYSCIGU", new UczestnikRowMapper());
            return uczestnicy;
        } catch (EmptyResultDataAccessException exception){
            exception.getStackTrace();
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/usunUczestnika")
    public ResponseEntity<HttpStatus> deleteUser(@RequestParam(value = "uuid") String uuid) {
        jdbcTemplate.update("delete uczestnicyWyscigu where uuid=?", uuid);
        return ResponseEntity.ok(HttpStatus.OK);
    }



    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class Uczestnik {
        private String uuid;
        public Integer life;
    }
}


//TODO: poptrzeć o json, o statusach w postman