package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.DatosPagoProveedores;

/**
 * 
 * @author aresua
 *
 */
public class DatosPagoProveedoresRowMapper implements RowMapper<DatosPagoProveedores> {

	@Override()
	public DatosPagoProveedores mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		final DatosPagoProveedores datosPagoProveedores = new DatosPagoProveedores();
		datosPagoProveedores.setIdTarea(resultSet.getBigDecimal("IDTAREA"));
		datosPagoProveedores.setImporteBase(resultSet.getBigDecimal("IMPORTEBASE"));
		datosPagoProveedores.setImporteIva(resultSet.getBigDecimal("IMPORTEIVA"));
		datosPagoProveedores.setImportePenalizacion(resultSet.getBigDecimal("IMPORTEPENALIZACION"));
		datosPagoProveedores.setImporteRecargoApremio(resultSet.getBigDecimal("IMPORTERECARGOAPREMIO"));
		datosPagoProveedores.setImporteRecargoFormato(resultSet.getBigDecimal("IMPORTERECARGOFORMATO"));
		datosPagoProveedores.setImporteTarea(resultSet.getBigDecimal("IMPORTETAREA"));
		datosPagoProveedores.setImporteTotal(resultSet.getBigDecimal("IMPORTETOTAL"));
		datosPagoProveedores.setIndPenalizacion(resultSet.getString("INDPENALIZACION"));
		datosPagoProveedores.setIndRecargoApremio(resultSet.getString("INDRECARGOAPREMIO"));
		datosPagoProveedores.setIndRecargoFormato(resultSet.getString("INDRECARGOFORMATO"));
		datosPagoProveedores.setNumPalConcor084(resultSet.getInt("NUMPALCONCOR084"));
		datosPagoProveedores.setNumPalConcor8594(resultSet.getInt("NUMPALCONCOR8594"));
		datosPagoProveedores.setNumPalConcor95100(resultSet.getInt("NUMPALCONCOR95100"));
		datosPagoProveedores.setNumPalRecargoFormato(resultSet.getInt("NUMPALRECARGOFORMATO"));
		datosPagoProveedores.setNumTotalPal(resultSet.getInt("NUMTOTALPAL"));
		datosPagoProveedores.setPorPenalizacion(resultSet.getLong("PORPENALIZACION"));
		datosPagoProveedores.setPorRecargoApremio(resultSet.getLong("PORRECARGOAPREMIO"));
		datosPagoProveedores.setIndAlbaran(resultSet.getString("INDALBARAN"));
		datosPagoProveedores.setNivelCalidad(resultSet.getString("NIVELCALIDAD"));
		datosPagoProveedores.setImportePenalCalidad(resultSet.getBigDecimal("IMPORTEPENALCALIDAD"));
		datosPagoProveedores.setPorPenalizacionCalidad(resultSet.getLong("PORPENALCALIDAD"));
		return datosPagoProveedores;
	}

}
