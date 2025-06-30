package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.BitacoraExpediente;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.ExpedienteInterpretacion;
import com.ejie.aa79b.model.ExpedienteTradRev;
import com.ejie.aa79b.model.SubsanacionExpediente;

public class ExpedientesAAnularRowMapper implements RowMapper<Expediente> {

	@Override
	public Expediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Expediente expediente = new Expediente();
		expediente.setAnyo(resultSet.getLong(DBConstants.ANYO));
		expediente.setNumExp(resultSet.getInt(DBConstants.NUMEXP));
		expediente.setAnyoNumExpConcatenado(resultSet.getString(DBConstants.ANYONUMEXPCONCATENADO));
		expediente.setIdTipoExpediente(resultSet.getString(DBConstants.IDTIPOEXPEDIENTE));
		expediente.setTipoExpedienteDescEs(resultSet.getString(DBConstants.TIPOEXPEDIENTEDESCES));
		expediente.setTipoExpedienteDescEu(resultSet.getString(DBConstants.TIPOEXPEDIENTEDESCEU));
		expediente.setTitulo(resultSet.getString(DBConstants.TITULO));
		expediente.setFechaEntregaFormateada(resultSet.getString(DBConstants.FECHAENTREGAFORMATEADA));

		// ExpedienteInterpretacion
		ExpedienteInterpretacion expedienteInterpretacion = new ExpedienteInterpretacion();
		expedienteInterpretacion.setFechaIni(resultSet.getDate(DBConstants.FECHAINICIO));
		expedienteInterpretacion.setHoraIni(resultSet.getString(DBConstants.HORAINICIO));
		expedienteInterpretacion.setFechaFin(resultSet.getDate(DBConstants.FECHAFIN));
		expedienteInterpretacion.setHoraFin(resultSet.getString(DBConstants.HORAFIN));
		expediente.setExpedienteInterpretacion(expedienteInterpretacion);

		// ExpedienteTradRev
		ExpedienteTradRev expedienteTradRev = new ExpedienteTradRev();
		expedienteTradRev.setFechaFinalIzo(resultSet.getDate(DBConstants.FECHAFINALIZO));
		expedienteTradRev.setHoraFinalIzo(resultSet.getString(DBConstants.HORAFINALIZO));
		expedienteTradRev.setFechaFinalSolic(resultSet.getDate(DBConstants.FECHAFINALSOLIC));
		expedienteTradRev.setHoraFinalSolic(resultSet.getString(DBConstants.HORAFINALSOLIC));
		expediente.setExpedienteTradRev(expedienteTradRev);

		// BitacoraExpediente
		BitacoraExpediente bitacoraExpediente = new BitacoraExpediente();
		SubsanacionExpediente subsanacionExpediente = new SubsanacionExpediente();
		subsanacionExpediente.setFechaLimite(resultSet.getDate(DBConstants.FECHALIMITE));
		subsanacionExpediente.setHoraLimite(resultSet.getString(DBConstants.HORALIMITE));
		subsanacionExpediente.setEstado(resultSet.getInt(DBConstants.ESTADOSUBSANACION));
		subsanacionExpediente.setEstadoDescEs(resultSet.getString(DBConstants.ESTADOSUBSANACIONDESCES));
		subsanacionExpediente.setEstadoDescEu(resultSet.getString(DBConstants.ESTADOSUBSANACIONDESCEU));
		subsanacionExpediente.setTipoRequerimiento(resultSet.getLong(DBConstants.TIPOREQUERIMIENTO));
		subsanacionExpediente.setTipoRequerimientoDescEs(resultSet.getString(DBConstants.TIPOREQUERIMIENTODESCES));
		subsanacionExpediente.setTipoRequerimientoDescEu(resultSet.getString(DBConstants.TIPOREQUERIMIENTODESCEU));
		bitacoraExpediente.setSubsanacionExp(subsanacionExpediente);
		expediente.setBitacoraExpediente(bitacoraExpediente);

		return expediente;
	}

}
