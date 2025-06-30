package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.DatosFacturacionExpedienteInterpretacion;
import com.ejie.aa79b.model.EstadoFactura;
import com.ejie.aa79b.model.Facturas;

/**
 * FacturasRowMapperConsulta
 * 
 * @author mrodriguez
 */
public class FacturasRowMapperConsulta implements RowMapper<Facturas> {

	@Override
	public Facturas mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Facturas facturas = new Facturas();

		facturas.setIdFactura(resultSet.getLong(DBConstants.IDFACTURA));
		facturas.setCodConcatenado(resultSet.getLong("CODCONCATENADO"));
		facturas.setIdEntidadAsoc(resultSet.getLong(DBConstants.IDENTIDADASOCIADA));
		facturas.setTipoEntidadAsoc(resultSet.getString(DBConstants.TIPOENTIDADASOCIADA));
		facturas.setDniContacto(resultSet.getString(DBConstants.DNICONTACTO));
		facturas.setFechaEmision(resultSet.getTimestamp(DBConstants.FECHAEMISION));
		facturas.setTipoExpFact(resultSet.getString(DBConstants.TIPOEXPFACTURA));

		DatosFacturacionExpedienteInterpretacion datosFacturacionInterpretacion = new DatosFacturacionExpedienteInterpretacion();
		EstadoFactura estadoFactura = new EstadoFactura();
		estadoFactura.setIndEstadoFactura(resultSet.getLong(DBConstants.ESTADOFACTURA));
		datosFacturacionInterpretacion.setEstadoFactura(estadoFactura);
		datosFacturacionInterpretacion.setImporteTotal(resultSet.getBigDecimal(DBConstants.IMPORTETOTAL));
		datosFacturacionInterpretacion.setImporteBase(resultSet.getBigDecimal(DBConstants.IMPORTEBASE));
		datosFacturacionInterpretacion.setImporteIva(resultSet.getBigDecimal(DBConstants.IMPORTEIVA));
		datosFacturacionInterpretacion.setTipoIva(resultSet.getBigDecimal(DBConstants.TIPOIVA));
		facturas.setDatosFacturacionInterpretacion(datosFacturacionInterpretacion);

		return facturas;
	}

}
