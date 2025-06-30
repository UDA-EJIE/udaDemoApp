package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.CuantiaInter;

/**
 * 
 * @author aresua
 *
 */
public class CuantiaInterRowMapper implements RowMapper<CuantiaInter> {

    @Override()
    public CuantiaInter mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        CuantiaInter cuantiaInter = new CuantiaInter();
        cuantiaInter.setIdOrden(resultSet.getLong("IDORDEN035"));
        cuantiaInter.setJornCompHorasDesde(resultSet.getInt("JORNCOMPHORASDESDE035"));
        cuantiaInter.setJornCompHorasHasta(resultSet.getInt("JORNCOMPHORASHASTA035"));
        cuantiaInter.setJornMediaHorasDesde(resultSet.getInt("JORNMEDIAHORASDESDE035"));
        cuantiaInter.setJornMediaHorasHasta(resultSet.getInt("JORNMEDIAHORASHASTA035"));
        cuantiaInter.setPrecMinCae(resultSet.getBigDecimal("PRECMINCAE035"));
        cuantiaInter.setPrecMinNoCae(resultSet.getBigDecimal("PRECMINNOCAE035"));
        cuantiaInter.setPrecHoraReunionCae(resultSet.getBigDecimal("PRECHORAREUNIONCAE035"));
        cuantiaInter.setPrecHoraReunionNoCae(resultSet.getBigDecimal("PRECHORAREUNIONNOCAE035"));
        cuantiaInter.setPrecJornCongresoEu(resultSet.getBigDecimal("PRECJORNCONGRESOEU035"));
        cuantiaInter.setPrecMediaCongresoEu(resultSet.getBigDecimal("PRECMEDIACONGRESOEU035"));
        cuantiaInter.setPrecHoraCongresoEu(resultSet.getBigDecimal("PRECHORACONGRESOEU035"));
        cuantiaInter.setPrecJornCongresoNoEu(resultSet.getBigDecimal("PRECJORNCONGRESONOEU035"));
        cuantiaInter.setPrecMediaCongresoNoEu(resultSet.getBigDecimal("PRECMEDIACONGRESONOEU035"));
        cuantiaInter.setPrecHoraCongresoNoEu(resultSet.getBigDecimal("PRECHORACONGRESONOEU035"));
        cuantiaInter.setExisteReg(true);
        return cuantiaInter;
    }

}
