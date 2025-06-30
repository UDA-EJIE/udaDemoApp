package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.DatosPagoProveedores;
import com.ejie.aa79b.model.TiposTarea;

public class TareasAlbaranRowMapper implements RowMapper<DatosPagoProveedores> {
	@Override
	public DatosPagoProveedores mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		DatosPagoProveedores datosPagoProveedores = new DatosPagoProveedores();
		datosPagoProveedores.setAnyo(resultSet.getLong("ANYO"));
		datosPagoProveedores.setNumExpediente(resultSet.getInt("NUMEXP"));
		datosPagoProveedores.setIdTarea(resultSet.getBigDecimal(DBConstants.IDTAREA));
		datosPagoProveedores.setNumTotalPal(resultSet.getInt(DBConstants.NUMTOTALPAL));
		datosPagoProveedores.setNumPalConcor084(resultSet.getInt(DBConstants.NUMPALCONCOR084));
		datosPagoProveedores.setNumPalConcor8594(resultSet.getInt(DBConstants.NUMPALCONCOR8594));
		datosPagoProveedores.setNumPalConcor95100(resultSet.getInt(DBConstants.NUMPALCONCOR95100));
		datosPagoProveedores.setIvaAplic(resultSet.getInt(DBConstants.IVAAPLIC));
		datosPagoProveedores.setImportePalAplic(resultSet.getBigDecimal(DBConstants.IMPORTEPALAPLIC));
		datosPagoProveedores.setImporteTarea(resultSet.getBigDecimal(DBConstants.IMPORTETAREA));
		datosPagoProveedores.setImporteRecargoFormato(resultSet.getBigDecimal(DBConstants.IMPORTERECARGOFORMATO));
		datosPagoProveedores.setImporteRecargoApremio(resultSet.getBigDecimal(DBConstants.IMPORTERECARGOAPREMIO));
		datosPagoProveedores.setImportePenalizacion(resultSet.getBigDecimal(DBConstants.IMPORTEPENALIZACION));
		datosPagoProveedores.setImportePenalCalidad(resultSet.getBigDecimal("IMPPENALCALIDAD"));
		datosPagoProveedores.setImporteTotal(resultSet.getBigDecimal(DBConstants.IMPORTETOTAL));
		datosPagoProveedores.setImporteBase(resultSet.getBigDecimal(DBConstants.IMPORTEBASE));
		datosPagoProveedores.setImporteIva(resultSet.getBigDecimal(DBConstants.IMPORTEIVA));
		datosPagoProveedores.setNumPalRecargoFormato(resultSet.getInt(DBConstants.NUMPALRECARGOFORMATO));
		datosPagoProveedores.setPorPenalizacion(resultSet.getLong(DBConstants.PORPENALIZACION));
		datosPagoProveedores.setPorPenalizacionCalidad(resultSet.getLong("PORPENALCALIDAD"));
		datosPagoProveedores.setPorRecargoApremio(resultSet.getLong(DBConstants.PORRECARGOAPREMIO));
		TiposTarea tiposTarea = new TiposTarea();
		tiposTarea.setDescEs015(resultSet.getString(DBConstants.TIPOTAREADESCES));
		tiposTarea.setDescEu015(resultSet.getString(DBConstants.TIPOTAREADESCEU));
		datosPagoProveedores.setTiposTarea(tiposTarea);
		return datosPagoProveedores;
	}
}