package com.example.demo.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UczestnikDto {
        private String uuid;
        public Integer life;

    public UczestnikDto(String uuid, Integer life) {
        this.uuid = uuid;
        this.life = life;
    }

    public UczestnikDto() {
    }
}



