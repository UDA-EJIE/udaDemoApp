package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.webservices.Aa79bAuditoriaSeccionExpediente;

public class Aa79bAuditoriaSeccionExpedienteRowMapper implements RowMapper<Aa79bAuditoriaSeccionExpediente> {

	@Override
	public Aa79bAuditoriaSeccionExpediente mapRow(ResultSet rs, int arg1) throws SQLException {
		Aa79bAuditoriaSeccionExpediente audiSeccExp = new Aa79bAuditoriaSeccionExpediente();
		audiSeccExp.setIdAuditoria(rs.getLong("IDAUDITORIA"));
		audiSeccExp.setIdSeccion(rs.getInt("IDSECCION"));
		audiSeccExp.setTipoSeccion(rs.getInt("TIPOSECCION"));
		audiSeccExp.setIndRespuesta(rs.getString("INDRESPUESTA"));
		audiSeccExp.setNombre(rs.getString("NOMBREEU"));
		audiSeccExp.setIndObservaciones(rs.getString("INDOBSERVACIONES"));
		audiSeccExp.setOrden(rs.getInt("ORDEN"));
		audiSeccExp.setValorMinAprobado(rs.getInt("VALORMINAPROBADO"));
		audiSeccExp.setValorMinPeligro(rs.getInt("VALORMINPELIGRO"));
		audiSeccExp.setResulAuditoria(rs.getBigDecimal("RESULTADOAUDITORIA"));
		audiSeccExp.setNotaAuditoria(rs.getInt("NOTAAUDITORIA"));
		audiSeccExp.setObserv(rs.getString("OBSERV"));
		audiSeccExp.setCamposSeccionCount(rs.getLong("CAMPOSCOUNT"));
		return audiSeccExp;
	}

}
