package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.CentroOrganico;
import com.ejie.aa79b.model.DatosFacturacionExpedienteInterpretacion;
import com.ejie.aa79b.model.Entidad;
import com.ejie.aa79b.model.EstadoErrorFactura;
import com.ejie.aa79b.model.EstadoFactura;
import com.ejie.aa79b.model.EstadoFacturaE;
import com.ejie.aa79b.model.EstadoIncidenciaFactura;
import com.ejie.aa79b.model.Facturas;
import com.ejie.aa79b.model.Persona;

public class ObtenerFacturasRowMapper implements RowMapper<Facturas> {
	@Override
	public Facturas mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Facturas facturas = new Facturas();
		facturas.setIdFactura(resultSet.getLong("IDFACTURA"));
		facturas.setIdLiquidacion(resultSet.getString("IDLIQUIDACION"));
		facturas.setCodConcatenado(resultSet.getLong("CODCONCATENADO"));
		facturas.setFechaEmision(resultSet.getTimestamp("FECHAEMISION"));

		Entidad entidad = new Entidad();
		entidad.setCodigo(resultSet.getInt("CODIGO"));
		entidad.setDescAmpEs(resultSet.getString("DESCAMPES"));
		entidad.setDescAmpEu(resultSet.getString("DESCAMPEU"));

		CentroOrganico centroOrganico = new CentroOrganico();
		centroOrganico.setCodigo(resultSet.getString("CENTROORGANICO"));
		centroOrganico.setDescAmpEs(resultSet.getString("CENTROORGANICODESCES"));
		centroOrganico.setDescAmpEu(resultSet.getString("CENTROORGANICODESCEU"));

		Persona persona = new Persona();
		persona.setNombre(resultSet.getString("NOMBRE"));
		persona.setApellido1(resultSet.getString("APEL1"));
		persona.setApellido2(resultSet.getString("APEL2"));
		persona.setEntidad(entidad);
		persona.setCentroOrganico(centroOrganico);
		facturas.setPersona(persona);

		DatosFacturacionExpedienteInterpretacion datosFacExpInt = new DatosFacturacionExpedienteInterpretacion();
		datosFacExpInt.setImporteTotal(resultSet.getBigDecimal("IMPORTETOTAL"));
		datosFacExpInt.setFechaPago(resultSet.getDate("FECHAPAGO"));

		EstadoFactura estadoFactura = new EstadoFactura();
		estadoFactura.setIndEstadoFactura(resultSet.getLong("IDESTADOFACTURA"));
		estadoFactura.setEstadoFacturaDescEs(resultSet.getString("ESTADOFACTURADESCES"));
		estadoFactura.setEstadoFacturaDescEu(resultSet.getString("ESTADOFACTURADESCEU"));
		datosFacExpInt.setEstadoFactura(estadoFactura);

		EstadoFacturaE estadoFacturaE = new EstadoFacturaE();
		estadoFacturaE.setIndEstadoEFactura(resultSet.getInt("IDESTADOEFACTURA"));
		estadoFacturaE.setEstadoEFacturaDescEs(resultSet.getString("ESTADOEFACTURADESCES"));
		estadoFacturaE.setEstadoEFacturaDescEu(resultSet.getString("ESTADOEFACTURADESCEU"));
		datosFacExpInt.setEstadoFacturaE(estadoFacturaE);

		EstadoIncidenciaFactura estadoIncidenciaFactura = new EstadoIncidenciaFactura();
		estadoIncidenciaFactura.setIndEstadoIncidencia(resultSet.getInt("IDESTADOINCIDENCIA"));
		estadoIncidenciaFactura.setEstadoIncidenciaDescEs(resultSet.getString("ESTADOINCIDENCIADESCES"));
		estadoIncidenciaFactura.setEstadoIncidenciaDescEu(resultSet.getString("ESTADOINCIDENCIADESCEU"));
		datosFacExpInt.setEstadoIncidenciaFactura(estadoIncidenciaFactura);

		EstadoErrorFactura estadoErrorFactura = new EstadoErrorFactura();
		estadoErrorFactura.setIndEstadoError(resultSet.getInt("IDESTADOERROR"));
		estadoErrorFactura.setEstadoErrorDescEs(resultSet.getString("ESTADOERRORDESCES"));
		estadoErrorFactura.setEstadoErrorDescEu(resultSet.getString("ESTADOERRORDESCEU"));
		datosFacExpInt.setEstadoErrorFactura(estadoErrorFactura);
		facturas.setDatosFacturacionInterpretacion(datosFacExpInt);

		return facturas;
	}

}
