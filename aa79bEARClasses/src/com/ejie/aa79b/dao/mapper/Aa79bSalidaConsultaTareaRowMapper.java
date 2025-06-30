package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.enums.TipoTareaGestionAsociadaEnum;
import com.ejie.aa79b.model.webservices.Aa79bDescripcionIdioma;
import com.ejie.aa79b.model.webservices.Aa79bLoteCombo;
import com.ejie.aa79b.model.webservices.Aa79bSalidaConsultaTarea;

public class Aa79bSalidaConsultaTareaRowMapper implements RowMapper<Aa79bSalidaConsultaTarea> {
	@Override
	public Aa79bSalidaConsultaTarea mapRow(ResultSet resultSet, int arg1) throws SQLException {
		Aa79bSalidaConsultaTarea salidaConsultaTarea = new Aa79bSalidaConsultaTarea();

		salidaConsultaTarea.setIdTarea(resultSet.getBigDecimal(DBConstants.IDTAREA));
		Aa79bDescripcionIdioma tipoTarea = new Aa79bDescripcionIdioma();
		tipoTarea.setId(resultSet.getLong(DBConstants.IDTIPOTAREA));
		tipoTarea.setDescEs(resultSet.getString(DBConstants.TIPOTAREADESCES));
		tipoTarea.setDescEu(resultSet.getString(DBConstants.TIPOTAREADESCEU));
		salidaConsultaTarea.setTipoTarea(tipoTarea);
		Aa79bDescripcionIdioma estadoEjecucionTarea = new Aa79bDescripcionIdioma();
		estadoEjecucionTarea.setId(resultSet.getLong(DBConstants.ESTADOEJECID));
		estadoEjecucionTarea.setDescEs(resultSet.getString(DBConstants.ESTADOEJECUCIONDESCES));
		estadoEjecucionTarea.setDescEu(resultSet.getString(DBConstants.ESTADOEJECUCIONDESCEU));
		salidaConsultaTarea.setEstadoEjecucionTarea(estadoEjecucionTarea);
		salidaConsultaTarea.setAnyo(resultSet.getLong(DBConstants.ANYO));
		salidaConsultaTarea.setNumExp(resultSet.getInt(DBConstants.NUMEXP));
		if (TipoTareaGestionAsociadaEnum.INTERPRETAR.getValue() == salidaConsultaTarea.getTipoTarea().getId()) {
			// interpretacion
			final Date fechaAlta = resultSet.getTimestamp(DBConstants.FECHAINICIO);
			final Date fechaFin = resultSet.getTimestamp(DBConstants.FECHAFIN);
			if (fechaAlta != null) {
				salidaConsultaTarea.setFechaInicioInterpretacion(fechaAlta.getTime());
			}
			salidaConsultaTarea.setHoraInicioInterpretacion(resultSet.getString(DBConstants.HORAINICIO));
			if (fechaFin != null) {
				salidaConsultaTarea.setFechaFinInterpretacion(fechaFin.getTime());
			}
			salidaConsultaTarea.setHoraFinInterpretacion(resultSet.getString(DBConstants.HORAFIN));
		} else {
			// tradRev
			salidaConsultaTarea.setNumPalXml(resultSet.getInt(DBConstants.NUMTOTALPAL));
			salidaConsultaTarea.setNumPalConcor084(resultSet.getInt(DBConstants.NUMPALCONCOR084));
			salidaConsultaTarea.setNumPalConcor8594(resultSet.getInt(DBConstants.NUMPALCONCOR8594));
			salidaConsultaTarea.setNumPalConcor95100(resultSet.getInt(DBConstants.NUMPALCONCOR95100));
			salidaConsultaTarea.setNumPalConcor9599(resultSet.getInt(DBConstants.NUMPALCONCOR9599));
			salidaConsultaTarea.setNumPalConcor100(resultSet.getInt(DBConstants.NUMPALCONCOR100));
			salidaConsultaTarea.setNumPalIzo(resultSet.getInt(DBConstants.NUMPALIZO));
			final Date fechaPrevistaEjecucion = resultSet.getTimestamp(DBConstants.FECHAPREVISTAEJECUCION);
			final Date fechaRealEjecucion = resultSet.getTimestamp(DBConstants.FECHAREALEJECUCION);
			if (fechaPrevistaEjecucion != null) {
				salidaConsultaTarea.setFechaPrevista(fechaPrevistaEjecucion.getTime());
			}
			salidaConsultaTarea.setHoraPrevista(resultSet.getString(DBConstants.HORAPREVISTAEJECUCION));
			if (fechaRealEjecucion != null) {
				salidaConsultaTarea.setFechaReal(fechaRealEjecucion.getTime());
			}
			salidaConsultaTarea.setHoraReal(resultSet.getString(DBConstants.HORAREALEJECUCION));
			Aa79bLoteCombo lote = new Aa79bLoteCombo();
			lote.setId(resultSet.getInt(DBConstants.IDLOTE));
			lote.setNombreLote(resultSet.getString(DBConstants.NOMBRELOTE));
			salidaConsultaTarea.setLotes(lote);
		}

		return salidaConsultaTarea;
	}

}
