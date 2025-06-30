package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.ExpedienteTradRev;
import com.ejie.aa79b.model.PuntosMenuAplicacion;
import com.ejie.aa79b.model.RegistroAcciones;

public class RegistroAccionesRwMapper implements RowMapper<RegistroAcciones> {
	@Override()
	public RegistroAcciones mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		RegistroAcciones bean = new RegistroAcciones();
		bean.setIdPuntoMenu(resultSet.getLong("IDPUNTOMENU043"));

		PuntosMenuAplicacion menuAplicacion = new PuntosMenuAplicacion();
		menuAplicacion.setDescEs(resultSet.getString("IDPUNTOMENUDESCES"));
		menuAplicacion.setDescEu(resultSet.getString("IDPUNTOMENUDESCEU"));
		bean.setPuntosMenuAplicacion(menuAplicacion);

		bean.setIdRegistroAccion(resultSet.getLong("IDREGISTROACCION043"));
		bean.setIdAccion(resultSet.getLong("IDACCION043"));
		bean.setIp(resultSet.getString("IP043"));
		bean.setFechaRegistro(resultSet.getDate("FECHAREGISTRO043"));
		bean.setHoraRegistro(resultSet.getString("HORAREGISTRO043"));
		bean.setUsuarioRegistro(resultSet.getString("USUARIOREGISTRO043"));
		bean.setAnyo(resultSet.getLong("ANYOEXP043"));
		bean.setNumExp(resultSet.getInt("NUMEXP043"));
		bean.setAnyoNumExpConcatenado(resultSet.getString("ANYONUMEXPCONCATENADO"));
		bean.setObserv(resultSet.getString("OBSERV043"));
		bean.setIdEstadoBitacora(resultSet.getLong("IDESTADOBITACORA043"));
		bean.setFechaHoraRegistro(resultSet.getString("FECHAHORAREGISTRO"));
		bean.setAccionDescEu(resultSet.getString("ACCIONDESCEU"));
		bean.setAccionDescEs(resultSet.getString("ACCIONDESCES"));

		ExpedienteTradRev expedienteTradRev = new ExpedienteTradRev();
		expedienteTradRev.setIndConfidencialDescEs(resultSet.getString("INDCONFIDENCIALDESCES"));
		expedienteTradRev.setIndConfidencialDescEu(resultSet.getString("INDCONFIDENCIALDESCEU"));
		bean.setExpedienteTradRev(expedienteTradRev);

		return bean;
	}
}