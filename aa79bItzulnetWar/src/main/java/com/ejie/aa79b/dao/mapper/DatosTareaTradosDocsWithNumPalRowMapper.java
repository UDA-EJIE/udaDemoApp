package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.DatosTareaTradosDocs;

public class DatosTareaTradosDocsWithNumPalRowMapper implements RowMapper<DatosTareaTradosDocs> {

	@Override
	public DatosTareaTradosDocs mapRow(ResultSet resultSet, int arg1) throws SQLException {
		final DatosTareaTradosDocs datosTareaTradosDocs = new DatosTareaTradosDocs();
		datosTareaTradosDocs.setIdTarea(resultSet.getBigDecimal("IDTAREA"));
		datosTareaTradosDocs.setIdDocOrig(resultSet.getBigDecimal("IDDOCORIG"));
		datosTareaTradosDocs.setNumTotalPal(resultSet.getInt("NUMTOTALPAL"));
		datosTareaTradosDocs.setNumPalConcor084(resultSet.getInt("NUMPALCONCOR084"));
		datosTareaTradosDocs.setNumPalConcor8594(resultSet.getInt("NUMPALCONCOR8594"));
		datosTareaTradosDocs.setNumPalConcor95100(resultSet.getInt("NUMPALCONCOR95100"));
		datosTareaTradosDocs.setNumPalConcor9599(resultSet.getInt("NUMPALCONCOR9599"));
		datosTareaTradosDocs.setNumPalConcor100(resultSet.getInt("NUMPALCONCOR100"));

		datosTareaTradosDocs.setNumPalSolic(resultSet.getInt(DBConstants.NUMPALSOLIC));
		datosTareaTradosDocs.setNumPalIzo(resultSet.getInt(DBConstants.NUMPALIZO));
		datosTareaTradosDocs.setNombreFichero(resultSet.getString(DBConstants.NOMBREFICHERO));

		return datosTareaTradosDocs;
	}

}
