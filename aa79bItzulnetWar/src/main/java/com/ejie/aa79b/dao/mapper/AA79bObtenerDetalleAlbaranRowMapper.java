package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.webservices.Aa79bDatosPagoProveedores;
import com.ejie.aa79b.model.webservices.Aa79bSalidaTarea;
import com.ejie.aa79b.model.webservices.Aa79bTiposTarea;

public class AA79bObtenerDetalleAlbaranRowMapper implements RowMapper<Aa79bSalidaTarea> {
	@Override
	public Aa79bSalidaTarea mapRow(ResultSet resultSet, int rowNum) throws SQLException {

		Aa79bSalidaTarea aa79bSalidaTarea = new Aa79bSalidaTarea();
		aa79bSalidaTarea.setAnyo(resultSet.getLong(DBConstants.ANYO));
		aa79bSalidaTarea.setNumExp(resultSet.getInt(DBConstants.NUMEXP));

		Aa79bTiposTarea tiposTarea = new Aa79bTiposTarea();
		tiposTarea.setId(resultSet.getLong(DBConstants.TIPOTAREAID));
		tiposTarea.setDescEs(resultSet.getString(DBConstants.TIPOTAREADESCES));
		tiposTarea.setDescEu(resultSet.getString(DBConstants.TIPOTAREADESCEU));
		aa79bSalidaTarea.setTipoTarea(tiposTarea);

		Aa79bDatosPagoProveedores datosPagoProveedores = new Aa79bDatosPagoProveedores();
		datosPagoProveedores.setNumTotalPal(resultSet.getInt(DBConstants.NUMTOTALPAL));
		datosPagoProveedores.setNumPalConcor084(resultSet.getInt(DBConstants.NUMPALCONCOR084));
		datosPagoProveedores.setNumPalConcor8594(resultSet.getInt(DBConstants.NUMPALCONCOR8594));
		datosPagoProveedores.setNumPalConcor95100(resultSet.getInt(DBConstants.NUMPALCONCOR95100));
		datosPagoProveedores.setIndRecargoFormato(resultSet.getString(DBConstants.INDRECARGOFORMATO));
		datosPagoProveedores.setNumPalRecargoFormato(resultSet.getInt(DBConstants.NUMPALRECARGOFORMATO));
		datosPagoProveedores.setIndRecargoApremio(resultSet.getString(DBConstants.INDRECARGOAPREMIO));
		datosPagoProveedores.setPorRecargoApremio(resultSet.getLong(DBConstants.PORRECARGOAPREMIO));
		datosPagoProveedores.setIndPenalizacion(resultSet.getString(DBConstants.INDPENALIZACION));
		datosPagoProveedores.setPorPenalizacion(resultSet.getLong(DBConstants.PORPENALIZACION));
		datosPagoProveedores.setIvaAplic(resultSet.getInt(DBConstants.IVAAPLIC));
		datosPagoProveedores.setImportePalAplic(resultSet.getBigDecimal(DBConstants.IMPORTEPALAPLIC));
		datosPagoProveedores.setPorRevisionAplic(resultSet.getLong(DBConstants.PORREVISIONAPLIC));
		datosPagoProveedores.setImporteTarea(resultSet.getBigDecimal(DBConstants.IMPORTETAREA));
		datosPagoProveedores.setImporteRecargoFormato(resultSet.getBigDecimal(DBConstants.IMPORTERECARGOFORMATO));
		datosPagoProveedores.setImporteRecargoApremio(resultSet.getBigDecimal(DBConstants.IMPORTERECARGOAPREMIO));
		datosPagoProveedores.setImportePenalizacion(resultSet.getBigDecimal(DBConstants.IMPORTEPENALIZACION));
		datosPagoProveedores.setImportePenalCalidad(resultSet.getBigDecimal("IMPORTEPENALCALIDAD"));
		datosPagoProveedores.setImporteBase(resultSet.getBigDecimal(DBConstants.IMPORTEBASE));
		datosPagoProveedores.setImporteIva(resultSet.getBigDecimal(DBConstants.IMPORTEIVA));
		datosPagoProveedores.setImporteTotal(resultSet.getBigDecimal(DBConstants.IMPORTETOTAL));

		aa79bSalidaTarea.setDatosPagoProveedor(datosPagoProveedores);

		return aa79bSalidaTarea;
	}

}
