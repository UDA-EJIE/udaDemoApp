package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.DatosTareaTrados;

public class DatosTareaTradosRowMapper implements RowMapper<DatosTareaTrados> {

	@Override()
	public DatosTareaTrados mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		final DatosTareaTrados datosTareaTrados = new DatosTareaTrados();
		datosTareaTrados.setIdTarea(resultSet.getBigDecimal("IDTAREA"));
		datosTareaTrados.setIdFicheroTrados(resultSet.getBigDecimal("IDFICHEROTRADOS"));
		datosTareaTrados.setIndVisible(resultSet.getString("INDVISIBLE"));
		datosTareaTrados.setPresupuesto(resultSet.getBigDecimal("PRESUPUESTO"));
		datosTareaTrados.setFechaEntrega(resultSet.getDate("FECHAENTREGA"));
		datosTareaTrados.setHoraEntrega(resultSet.getString("HORAENTREGA"));
		datosTareaTrados.setTarifaPal(resultSet.getBigDecimal("TARIFAPAL"));
		datosTareaTrados.setNumTotalPal(resultSet.getInt("NUMTOTALPAL"));
		datosTareaTrados.setNumPalConcor084(resultSet.getInt("NUMPALCONCOR084"));
		datosTareaTrados.setNumPalConcor8594(resultSet.getInt("NUMPALCONCOR8594"));
		datosTareaTrados.setNumPalConcor95100(resultSet.getInt("NUMPALCONCOR95100"));
		datosTareaTrados.setNumPalConcor9599(resultSet.getInt("NUMPALCONCOR9599"));
		datosTareaTrados.setNumPalConcor100(resultSet.getInt("NUMPALCONCOR100"));

		datosTareaTrados.setPorcentajeIva(resultSet.getLong("PORIVA"));
		datosTareaTrados.setImporteBase(resultSet.getBigDecimal("IMPORTEBASE"));
		datosTareaTrados.setImporteIva(resultSet.getBigDecimal("IMPORTEIVA"));

		datosTareaTrados.setNombreFicheroTrados(resultSet.getString("NOMBREPROYECTO"));
		datosTareaTrados.setFechaProyecto(resultSet.getDate("FECHAPROYECTO"));
		datosTareaTrados.setIdRequerimiento(resultSet.getBigDecimal("IDREQUERIMIENTO"));

		datosTareaTrados.setUrlOrdenPrecios(resultSet.getString("URLORDEN"));
		datosTareaTrados.setPrecioMinimo(resultSet.getBigDecimal("PRECIOMINIMO"));
		return datosTareaTrados;
	}

}
