package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.enums.TipoExpedienteEnum;
import com.ejie.aa79b.model.webservices.Aa79bDatosTradRev;
import com.ejie.aa79b.model.webservices.Aa79bDescripcionIdioma;
import com.ejie.aa79b.model.webservices.Aa79bEstadoFaseExpediente;
import com.ejie.aa79b.model.webservices.Aa79bExpediente;
import com.ejie.aa79b.model.webservices.Aa79bGestorExpediente;

public class Aa79bExpedienteRowMapper implements RowMapper<Aa79bExpediente> {

	@Override
	public Aa79bExpediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Aa79bExpediente aa79bExpediente = new Aa79bExpediente();
		aa79bExpediente.setAnyo(resultSet.getLong(DBConstants.ANYO));
		aa79bExpediente.setNumExp(resultSet.getInt(DBConstants.NUMEXP));
		aa79bExpediente.setAnyoNumExp(resultSet.getString(DBConstants.ANYONUMEXPCONCATENADO));
		Aa79bDescripcionIdioma tipoExpediente = new Aa79bDescripcionIdioma();
		tipoExpediente.setCodigo(resultSet.getString(DBConstants.IDTIPOEXPEDIENTE));
		tipoExpediente.setDescEs(resultSet.getString(DBConstants.TIPOEXPEDIENTEDESCES));
		tipoExpediente.setDescEu(resultSet.getString(DBConstants.TIPOEXPEDIENTEDESCEU));
		aa79bExpediente.setTipoExpediente(tipoExpediente);
		aa79bExpediente.setTitulo(resultSet.getString(DBConstants.TITULO));
		if (!TipoExpedienteEnum.INTERPRETACION.getValue().equals(tipoExpediente.getCodigo())) {
			Aa79bDatosTradRev datosTradRev = new Aa79bDatosTradRev();
			datosTradRev.setRefTramitagune(resultSet.getString(DBConstants.REF_TRAMITAGUNE));
			aa79bExpediente.setDatosTradRev(datosTradRev);
		}

		Aa79bEstadoFaseExpediente aa79bEstadoFaseExpediente = new Aa79bEstadoFaseExpediente();
		aa79bEstadoFaseExpediente.setIdEstado(resultSet.getLong(DBConstants.IDESTADOEXP));
		aa79bEstadoFaseExpediente.setIdFase(resultSet.getLong(DBConstants.IDFASEEXPEDIENTE));
		aa79bExpediente.setEstadoFaseExpediente(aa79bEstadoFaseExpediente);
		Aa79bDescripcionIdioma estadoFase = new Aa79bDescripcionIdioma();
		estadoFase.setId(resultSet.getLong(DBConstants.ESTADOFASEAA06A));
		estadoFase.setDescEs(resultSet.getString(DBConstants.ESTADOFASEAA06ADESCES));
		estadoFase.setDescEu(resultSet.getString(DBConstants.ESTADOFASEAA06ADESCEU));
		aa79bExpediente.setEstadoFase(estadoFase);

		final Date fechaAlta = resultSet.getTimestamp(DBConstants.FECHAALTA);
		final Date fechaFinalIzo = resultSet.getTimestamp(DBConstants.FECHAFINALIZO);
		if (fechaAlta != null) {
			aa79bExpediente.setFechaSolicitud(fechaAlta.getTime());
		}
		aa79bExpediente.setHoraSolicitud(resultSet.getString(DBConstants.HORAALTA));
		if (fechaFinalIzo != null) {
			aa79bExpediente.setFechaEntrega(fechaFinalIzo.getTime());
		}
		aa79bExpediente.setHoraSolicitud(resultSet.getString(DBConstants.HORAFINALIZO));

		aa79bExpediente.setIndPublicarBOPV(resultSet.getString(DBConstants.INDPUBLICARBOPV));
		aa79bExpediente.setPublicarBOPVDescEs(resultSet.getString(DBConstants.PUBLICARBOPVDESCES));
		aa79bExpediente.setPublicarBOPVDescEu(resultSet.getString(DBConstants.PUBLICARBOPVDESCEU));
		aa79bExpediente.setIndFacturable(resultSet.getString(DBConstants.INDFACTURABLE));
		aa79bExpediente.setFacturableDescEs(resultSet.getString(DBConstants.FACTURABLEDESCES));
		aa79bExpediente.setFacturableDescEu(resultSet.getString(DBConstants.FACTURABLEDESCEU));
		aa79bExpediente.setIndPagado(resultSet.getString(DBConstants.INDPAGADO));

		Aa79bGestorExpediente gestor = new Aa79bGestorExpediente();
		gestor.setDniGestor(resultSet.getString(DBConstants.DNIGESTOR));
		gestor.setNombreGestor(resultSet.getString(DBConstants.NOMBREGESTOR));
		gestor.setApellido1Gestor(resultSet.getString(DBConstants.APEL1GESTOR));
		gestor.setApellido2Gestor(resultSet.getString(DBConstants.APEL2GESTOR));
		gestor.setTipoEntidadGestor(resultSet.getString(DBConstants.TIPOENTIDAD));
		gestor.setCodigoEntidadGestor(resultSet.getInt(DBConstants.IDENTIDAD));
		gestor.setIndVIPGestor(resultSet.getString(DBConstants.INDVIP));
		gestor.setDescEntidadEsGestor(resultSet.getString(DBConstants.DESCENTIDADES));
		gestor.setDescEntidadEuGestor(resultSet.getString(DBConstants.DESCENTIDADEU));
		gestor.setDescAmpEntidadEsGestor(resultSet.getString(DBConstants.DESCAMPENTIDADES));
		gestor.setDescAmpEntidadEuGestor(resultSet.getString(DBConstants.DESCAMPENTIDADEU));

		aa79bExpediente.setGestor(gestor);

		return aa79bExpediente;
	}

}
