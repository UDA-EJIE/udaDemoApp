package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.DatosFacturacionExpediente;

public class DatosFacturacionExpedienteImportesRowMapper implements RowMapper<DatosFacturacionExpediente> {

	@Override
	public DatosFacturacionExpediente mapRow(ResultSet resultSet, int arg1) throws SQLException {
		DatosFacturacionExpediente datosfact = new DatosFacturacionExpediente();
		datosfact.setImporteBase(resultSet.getBigDecimal("IMPORTEBASE"));
		datosfact.setImporteIva(resultSet.getBigDecimal("IMPORTEIVA"));
		datosfact.setImporteTotal(resultSet.getBigDecimal("IMPORTETOTAL"));
		return datosfact;
	}

}
