package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.AuditoriaSeccionExpediente;

public class AuditoriaSeccionExpRowMapper implements RowMapper<AuditoriaSeccionExpediente> {

	@Override
	public AuditoriaSeccionExpediente mapRow(ResultSet rs, int arg1) throws SQLException {
		AuditoriaSeccionExpediente audiSeccExp = new AuditoriaSeccionExpediente();
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
