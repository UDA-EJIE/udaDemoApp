package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.webservices.Aa79bSalidaConsultaFacturas;

/**
 * Aa79bSalidaConsultaFacturasRowMapper
 * 
 * @author mrodriguez
 */
public class Aa79bSalidaConsultaFacturasRowMapper implements RowMapper<Aa79bSalidaConsultaFacturas> {

	@Override
	public Aa79bSalidaConsultaFacturas mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Aa79bSalidaConsultaFacturas aa79bSalidaConsultaFacturas = new Aa79bSalidaConsultaFacturas();

		aa79bSalidaConsultaFacturas.setIdFactura(resultSet.getBigDecimal(DBConstants.IDFACTURA));
		aa79bSalidaConsultaFacturas.setCodConcatenado(resultSet.getBigDecimal("CODCONCATENADO"));
		final Date fechaEmision = resultSet.getTimestamp(DBConstants.FECHAEMISION);
		if (fechaEmision != null) {
			aa79bSalidaConsultaFacturas.setFechaEmision(fechaEmision.getTime());
			aa79bSalidaConsultaFacturas.setFechaEmisionDate(fechaEmision);
			aa79bSalidaConsultaFacturas.setHoraEmision(resultSet.getString(DBConstants.HORAEMISION));
		}
		aa79bSalidaConsultaFacturas.setIdEstadoFactura(resultSet.getLong(DBConstants.ESTADOFACTURA));
		aa79bSalidaConsultaFacturas.setImporteTotal(resultSet.getBigDecimal(DBConstants.IMPORTETOTAL));
		aa79bSalidaConsultaFacturas.setImporteBase(resultSet.getBigDecimal(DBConstants.IMPORTEBASE));
		aa79bSalidaConsultaFacturas.setImporteIva(resultSet.getBigDecimal(DBConstants.IMPORTEIVA));
		aa79bSalidaConsultaFacturas.setTipoIva(resultSet.getBigDecimal(DBConstants.TIPOIVA));
		aa79bSalidaConsultaFacturas.setDescEstadoFactura(resultSet.getString(DBConstants.DESCESTADOFACTURA));

		return aa79bSalidaConsultaFacturas;
	}

}
