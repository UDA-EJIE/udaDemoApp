package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.CuantiaRev;
import com.ejie.aa79b.model.TipoRelevancia;

/**
 * 
 * @author aresua
 *
 */
public class CuantiaRevRowMapper implements RowMapper<CuantiaRev> {

    @Override()
    public CuantiaRev mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        CuantiaRev cuantiaRev = new CuantiaRev();
        cuantiaRev.setIdTipoRelevancia(resultSet.getLong("IDTIPORELEVANCIA010"));
        cuantiaRev.setTarifaEsEu(resultSet.getBigDecimal("TARIFAESEU034"));
        cuantiaRev.setTarifaEuEs(resultSet.getBigDecimal("TARIFAEUES034"));
        cuantiaRev.setIdOrden(resultSet.getLong("ID030"));

        TipoRelevancia tipoRelevancia = new TipoRelevancia();
        tipoRelevancia.setDescRelevanciaEs(resultSet.getString("DESCRELEVANCIAES010"));
        tipoRelevancia.setDescRelevanciaEu(resultSet.getString("DESCRELEVANCIAEU010"));

        cuantiaRev.setTipoRelevancia(tipoRelevancia);

        return cuantiaRev;
    }

}
