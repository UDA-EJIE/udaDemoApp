package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.DatosTareaTradosDocs;

public class DatosTareaTradosDocsRowMapper implements RowMapper<DatosTareaTradosDocs> {

	@Override()
	public DatosTareaTradosDocs mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		final DatosTareaTradosDocs datosTareaTradosDocs = new DatosTareaTradosDocs();
		datosTareaTradosDocs.setIdTarea(resultSet.getBigDecimal("IDTAREA"));
		datosTareaTradosDocs.setIdDocOrig(resultSet.getBigDecimal("IDDOCORIG"));
		datosTareaTradosDocs.setNumTotalPal(resultSet.getInt("NUMTOTALPAL"));
		datosTareaTradosDocs.setNumPalConcor084(resultSet.getInt("NUMPALCONCOR084"));
		datosTareaTradosDocs.setNumPalConcor8594(resultSet.getInt("NUMPALCONCOR8594"));
		datosTareaTradosDocs.setNumPalConcor95100(resultSet.getInt("NUMPALCONCOR95100"));
		datosTareaTradosDocs.setNumPalConcor9599(resultSet.getInt("NUMPALCONCOR9599"));
		datosTareaTradosDocs.setNumPalConcor100(resultSet.getInt("NUMPALCONCOR100"));

		return datosTareaTradosDocs;
	}

}
