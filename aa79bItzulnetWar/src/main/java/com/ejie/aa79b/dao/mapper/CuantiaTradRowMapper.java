package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.CuantiaTrad;
import com.ejie.aa79b.model.TipoRelevancia;

/**
 * 
 * @author aresua
 *
 */
public class CuantiaTradRowMapper implements RowMapper<CuantiaTrad> {

    @Override()
    public CuantiaTrad mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        CuantiaTrad cuantiaTrad = new CuantiaTrad();
        cuantiaTrad.setIdTipoRelevancia(resultSet.getLong("IDTIPORELEVANCIA010"));
        cuantiaTrad.setTarifaEsEu(resultSet.getBigDecimal("TARIFAESEU033"));
        cuantiaTrad.setTarifaEuEs(resultSet.getBigDecimal("TARIFAEUES033"));
        cuantiaTrad.setIdOrden(resultSet.getLong("ID030"));

        TipoRelevancia tipoRelevancia = new TipoRelevancia();
        tipoRelevancia.setDescRelevanciaEs(resultSet.getString("DESCRELEVANCIAES010"));
        tipoRelevancia.setDescRelevanciaEu(resultSet.getString("DESCRELEVANCIAEU010"));

        cuantiaTrad.setTipoRelevancia(tipoRelevancia);

        return cuantiaTrad;
    }

}
