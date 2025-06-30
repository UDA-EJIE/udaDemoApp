package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.SubsanacionExpediente;

public class ReqExpRowMapper implements RowMapper<SubsanacionExpediente> {

	@Override
	public SubsanacionExpediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		SubsanacionExpediente subsanacionExpediente = new SubsanacionExpediente();
		subsanacionExpediente.setId(resultSet.getLong(DBConstants.ID));
		subsanacionExpediente.setTipoRequerimiento(resultSet.getLong(DBConstants.TIPOREQUERIMIENTO));
		subsanacionExpediente.setTipoRequerimientoDescEu(resultSet.getString(DBConstants.TIPOREQUERIMIENTODESCEU));
		subsanacionExpediente.setTipoRequerimientoDescEs(resultSet.getString(DBConstants.TIPOREQUERIMIENTODESCES));
		subsanacionExpediente.setDetalle(resultSet.getString(DBConstants.DETALLE));
		subsanacionExpediente.setFechaReq(resultSet.getDate(DBConstants.FECHAREQ));
		subsanacionExpediente.setHoraReq(resultSet.getString(DBConstants.HORAREQ));
		subsanacionExpediente.setFechaLimite(resultSet.getDate(DBConstants.FECHALIMITE));
		subsanacionExpediente.setHoraLimite(resultSet.getString(DBConstants.HORALIMITE));
		subsanacionExpediente.setIndSubsanado(resultSet.getString(DBConstants.INDSUBSANADO));
		subsanacionExpediente.setEstado(resultSet.getInt(DBConstants.ESTADOSUBSANACION));
		subsanacionExpediente.setEstadoDescEu(resultSet.getString(DBConstants.ESTADOSUBSANACIONDESCEU));
		subsanacionExpediente.setEstadoDescEs(resultSet.getString(DBConstants.ESTADOSUBSANACIONDESCES));
		subsanacionExpediente.setFechaAceptacion(resultSet.getDate(DBConstants.FECHAACEPTACION));
		subsanacionExpediente.setHoraAceptacion(resultSet.getString(DBConstants.HORAACEPTACION));
		subsanacionExpediente.setFechaSubsanacion(resultSet.getDate(DBConstants.FECHASUBSANACION));
		subsanacionExpediente.setHoraSubsanacion(resultSet.getString(DBConstants.HORASUBSANACION));
		subsanacionExpediente.setPresupuesto(resultSet.getBigDecimal(DBConstants.PRESUPUESTO));
		subsanacionExpediente.setFechaEntrega(resultSet.getDate(DBConstants.FECHAENTREGA));
		subsanacionExpediente.setHoraEntrega(resultSet.getString(DBConstants.HORAENTREGA));
		subsanacionExpediente.setObservRechazo(resultSet.getString(DBConstants.OBSRVRECHAZO));
		subsanacionExpediente.setDniRecurso(resultSet.getString(DBConstants.DNIRECURSO));
		return subsanacionExpediente;
	}

}
