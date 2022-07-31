package com.example.demo.controller;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UczestnikRowMapper implements RowMapper<UczestnikDto> {
    @Override
    public UczestnikDto mapRow(ResultSet rs, int rowNumber) throws SQLException {
        UczestnikDto uczestnikDto = new UczestnikDto();
        uczestnikDto.setUuid(rs.getString("uuid"));
        uczestnikDto.setLife(rs.getInt("zycieKierowcy"));
        return uczestnikDto;
    }
}
