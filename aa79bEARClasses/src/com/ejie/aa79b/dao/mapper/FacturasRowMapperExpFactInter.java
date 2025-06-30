package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.webservices.Aa79bDatosFacturacionExpInter;
import com.ejie.aa79b.model.webservices.Aa79bDatosInterpretacion;
import com.ejie.aa79b.model.webservices.Aa79bDescripcionIdioma;
import com.ejie.aa79b.model.webservices.Aa79bExpediente;
import com.ejie.aa79b.model.webservices.Aa79bGestorExpediente;

public class FacturasRowMapperExpFactInter implements RowMapper<Aa79bExpediente> {

	@Override
	public Aa79bExpediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Aa79bExpediente expediente = new Aa79bExpediente();

		// Expediente
		expediente.setAnyo(resultSet.getLong(DBConstants.ANYO));
		expediente.setNumExp(resultSet.getInt(DBConstants.NUMEXP));
		final Date fechaAlta = resultSet.getTimestamp(DBConstants.FECHAALTA);
		if (fechaAlta != null) {
			expediente.setFechaSolicitud(fechaAlta.getTime());
			expediente.setFechaSolicitudDate(fechaAlta);
		}
		expediente.setHoraSolicitud(resultSet.getString(DBConstants.HORAALTA));
		Aa79bDescripcionIdioma tipoExpediente = new Aa79bDescripcionIdioma();
		tipoExpediente.setCodigo(resultSet.getString(DBConstants.IDTIPOEXPEDIENTE));
		tipoExpediente.setDescEs(resultSet.getString(DBConstants.TIPOEXPEDIENTEDESCES));
		tipoExpediente.setDescEu(resultSet.getString(DBConstants.TIPOEXPEDIENTEDESCEU));
		expediente.setTipoExpediente(tipoExpediente);

		// Expediente de interpretación
		Aa79bDatosInterpretacion datosInterpretacion = new Aa79bDatosInterpretacion();
		Aa79bDescripcionIdioma tipoActo = new Aa79bDescripcionIdioma();
		tipoActo.setDescEs(resultSet.getString(DBConstants.TIPOACTODESCES));
		tipoActo.setDescEu(resultSet.getString(DBConstants.TIPOACTODESCEU));
		datosInterpretacion.setTipoActo(tipoActo);

		// Datos facturación del expediente
		Aa79bDatosFacturacionExpInter datosFactExpInter = new Aa79bDatosFacturacionExpInter();
		datosFactExpInter.setNumInterpretes(resultSet.getInt(DBConstants.NUMINTERPRETES));
		datosFactExpInter.setHorasInterpretacion(resultSet.getString(DBConstants.HORASINTERPRETACION));
		datosFactExpInter.setImporteBase(resultSet.getBigDecimal(DBConstants.IMPORTEBASE));
		datosFactExpInter.setImporteIva(resultSet.getBigDecimal(DBConstants.IMPORTEIVA));
		datosFactExpInter.setImporteTotal(resultSet.getBigDecimal(DBConstants.IMPORTETOTAL));
		datosInterpretacion.setDatosFacturacion(datosFactExpInter);
		expediente.setDatosInterpretacion(datosInterpretacion);

		// Gestor del expediente
		Aa79bGestorExpediente gestor = new Aa79bGestorExpediente();
		gestor.setNombreRepresentante(resultSet.getString(DBConstants.NOMBRESOLICITANTE));
		gestor.setApellido1Representante(resultSet.getString(DBConstants.APEL1SOLICITANTE));
		gestor.setApellido2Representante(resultSet.getString(DBConstants.APEL2SOLICITANTE));
		expediente.setGestor(gestor);

		return expediente;
	}

}
