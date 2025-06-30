package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.enums.TipoTareaGestionAsociadaEnum;
import com.ejie.aa79b.model.webservices.Aa79bConsultaTareasReport;
import com.ejie.aa79b.model.webservices.Aa79bDescripcionIdioma;
import com.ejie.aa79b.model.webservices.Aa79bLoteCombo;

public class Aa79bConsultaTareasReportRowMapper implements RowMapper<Aa79bConsultaTareasReport> {

	@Override
	public Aa79bConsultaTareasReport mapRow(ResultSet resultSet, int arg1) throws SQLException {
		Aa79bConsultaTareasReport consultaTareasReport = new Aa79bConsultaTareasReport();

		consultaTareasReport.setIdTarea(resultSet.getBigDecimal(DBConstants.IDTAREA));
		Aa79bDescripcionIdioma tipoTarea = new Aa79bDescripcionIdioma();
		tipoTarea.setId(resultSet.getLong(DBConstants.IDTIPOTAREA));
		tipoTarea.setDescEs(resultSet.getString(DBConstants.TIPOTAREADESCES));
		tipoTarea.setDescEu(resultSet.getString(DBConstants.TIPOTAREADESCEU));
		consultaTareasReport.setTipoTarea(tipoTarea);
		Aa79bDescripcionIdioma estadoEjecucionTarea = new Aa79bDescripcionIdioma();
		estadoEjecucionTarea.setId(resultSet.getLong(DBConstants.ESTADOEJECID));
		estadoEjecucionTarea.setDescEs(resultSet.getString(DBConstants.ESTADOEJECUCIONDESCES));
		estadoEjecucionTarea.setDescEu(resultSet.getString(DBConstants.ESTADOEJECUCIONDESCEU));
		consultaTareasReport.setEstadoEjecucionTarea(estadoEjecucionTarea);
		consultaTareasReport.setAnyo(resultSet.getLong(DBConstants.ANYO));
		consultaTareasReport.setNumExp(resultSet.getInt(DBConstants.NUMEXP));
		if (TipoTareaGestionAsociadaEnum.INTERPRETAR.getValue() == consultaTareasReport.getTipoTarea().getId()) {
			// interpretacion
			consultaTareasReport.setFechaInicioInterpretacion(resultSet.getTimestamp(DBConstants.FECHAINICIO));
			consultaTareasReport.setFechaFinInterpretacion(resultSet.getTimestamp(DBConstants.FECHAFIN));
			consultaTareasReport.setHoraInicioInterpretacion(resultSet.getString(DBConstants.HORAINICIO));
			consultaTareasReport.setHoraFinInterpretacion(resultSet.getString(DBConstants.HORAFIN));
		} else {
			// tradRev
			consultaTareasReport.setNumPalXml(resultSet.getInt(DBConstants.NUMTOTALPAL));
			consultaTareasReport.setNumPalConcor084(resultSet.getInt(DBConstants.NUMPALCONCOR084));
			consultaTareasReport.setNumPalConcor8594(resultSet.getInt(DBConstants.NUMPALCONCOR8594));
			consultaTareasReport.setNumPalConcor95100(resultSet.getInt(DBConstants.NUMPALCONCOR95100));
			consultaTareasReport.setNumPalConcor9599(resultSet.getInt(DBConstants.NUMPALCONCOR9599));
			consultaTareasReport.setNumPalConcor100(resultSet.getInt(DBConstants.NUMPALCONCOR100));
			consultaTareasReport.setNumPalIzo(resultSet.getInt(DBConstants.NUMPALIZO));
			consultaTareasReport.setFechaPrevista(resultSet.getTimestamp(DBConstants.FECHAPREVISTAEJECUCION));
			consultaTareasReport.setHoraPrevista(resultSet.getString(DBConstants.HORAPREVISTAEJECUCION));
			consultaTareasReport.setFechaReal(resultSet.getTimestamp(DBConstants.FECHAREALEJECUCION));
			consultaTareasReport.setHoraReal(resultSet.getString(DBConstants.HORAREALEJECUCION));
			Aa79bLoteCombo lote = new Aa79bLoteCombo();
			lote.setId(resultSet.getInt(DBConstants.IDLOTE));
			lote.setNombreLote(resultSet.getString(DBConstants.NOMBRELOTE));
			consultaTareasReport.setLotes(lote);
		}

		return consultaTareasReport;
	}

}
