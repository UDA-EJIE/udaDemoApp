package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.PuntosMenuAplicacion;
import com.ejie.aa79b.model.RegistroAcciones;

public class RegistroAccionesExpedienteRwMapper implements RowMapper<RegistroAcciones> {
	@Override()
	public RegistroAcciones mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		RegistroAcciones bean = new RegistroAcciones();
		bean.setIdPuntoMenu(resultSet.getLong("IDPUNTOMENU043"));
		bean.setIdRegistroAccion(resultSet.getLong("IDREGISTROACCION043"));
		bean.setIdAccion(resultSet.getLong("IDACCION043"));
		bean.setAccionDescEu(resultSet.getString("ACCIONDESCEU"));
		bean.setAccionDescEs(resultSet.getString("ACCIONDESCES"));
		bean.setIp(resultSet.getString("IP043"));
		bean.setFechaRegistro(resultSet.getDate("FECHAREGISTRO043"));
		bean.setUsuarioRegistro(resultSet.getString("USUARIOREGISTRO043"));
		bean.setAnyo(resultSet.getLong("ANYOEXP043"));
		bean.setNumExp(resultSet.getInt("NUMEXP043"));
		bean.setObserv(resultSet.getString("OBSERV043"));
		bean.setIdEstadoBitacora(resultSet.getLong("IDESTADOBITACORA043"));
		bean.setFechaHoraRegistro(resultSet.getString("FECHAHORAREGISTRO"));

		PuntosMenuAplicacion puntosMenuAplicacion = new PuntosMenuAplicacion();
		puntosMenuAplicacion.setDescEs(resultSet.getString(DBConstants.DESCES));
		puntosMenuAplicacion.setDescEu(resultSet.getString(DBConstants.DESCEU));
		bean.setPuntosMenuAplicacion(puntosMenuAplicacion);

		return bean;
	}
}