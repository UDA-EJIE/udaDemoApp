package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.ResumenGraficas;

/**
 * 
 * @author aresua
 *
 */
public class ResumenGraficasRowMapper implements RowMapper<ResumenGraficas> {

    @Override()
    public ResumenGraficas mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        ResumenGraficas resumenGraficas = new ResumenGraficas();

        resumenGraficas.setTotal(resultSet.getLong("TOTAL"));
        resumenGraficas.setMisExp(resultSet.getLong("MISEXP"));
        return resumenGraficas;
    }

}
