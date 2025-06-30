package com.ejie.aa79b.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.TradosExp;

public class TradosRowMapper implements RowMapper<TradosExp> {

    @Override()
    public TradosExp mapRow(ResultSet resultSet, int rowNum) throws SQLException {
    	TradosExp tradosExp = new TradosExp();
    	tradosExp.setId(resultSet.getLong("ID_TAREA_091"));
    	tradosExp.setIdFicheroTrados(resultSet.getLong("ID_DOC_ORIG_091"));
    	tradosExp.setNumTotalPal(resultSet.getInt("NUM_TOTAL_PAL_091"));
    	tradosExp.setNumPalConcor084090(resultSet.getInt("NUM_PAL_CONCOR_0_84_091"));
    	tradosExp.setNumPalConcor8594090(resultSet.getInt("NUM_PAL_CONCOR_85_94_091"));
    	tradosExp.setNumPalConcor95100090(resultSet.getInt("NUM_PAL_CONCOR_95_100_091"));
        return tradosExp;
    }

}
