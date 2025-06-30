package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.TarifExpTradRev;

/**
 * 
 * @author aresua
 *
 */
public class TarifExpTradRevRowMapper implements RowMapper<TarifExpTradRev> {

    @Override()
    public TarifExpTradRev mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        final TarifExpTradRev tarifExpTradRev = new TarifExpTradRev();
        tarifExpTradRev.setIdOrden(resultSet.getLong("IDORDEN032"));
        tarifExpTradRev.setNumPalabrasTarifConcor(resultSet.getInt("NUMPALABRASTARIFCONCOR032"));
        tarifExpTradRev.setPorPalabraConcor084(resultSet.getLong("PORPALABRACONCOR084032"));
        tarifExpTradRev.setPorPalabraConcor8594(resultSet.getLong("PORPALABRACONCOR8594032"));
        tarifExpTradRev.setPorPalabraConcor95100(resultSet.getLong("PORPALABRACONCOR95100032"));
        tarifExpTradRev.setPorRecargoDif(resultSet.getLong("PORRECARGODIF032"));
        tarifExpTradRev.setPorRecargoUrg(resultSet.getLong("PORRECARGOURG032"));
        tarifExpTradRev.setPrecioMinimo(resultSet.getBigDecimal("PRECIOMINIMO032"));
        tarifExpTradRev.setExisteReg(true);
        return tarifExpTradRev;
    }

}
