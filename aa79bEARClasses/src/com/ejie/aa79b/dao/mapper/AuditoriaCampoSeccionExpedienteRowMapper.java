package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.AuditoriaCampoSeccionExpediente;

public class AuditoriaCampoSeccionExpedienteRowMapper implements RowMapper<AuditoriaCampoSeccionExpediente> {

	@Override
	public AuditoriaCampoSeccionExpediente mapRow(ResultSet rs, int arg1) throws SQLException {
		AuditoriaCampoSeccionExpediente audiCampoSeccExp = new AuditoriaCampoSeccionExpediente();
		audiCampoSeccExp.setIdAuditoria(rs.getLong("IDAUDITORIA"));
		audiCampoSeccExp.setIdCampo(rs.getInt("IDCAMPO"));
		audiCampoSeccExp.setTipoCampo(rs.getInt("TIPOCAMPO"));
		audiCampoSeccExp.setNombreEu(rs.getNString("NOMBREEU"));
		audiCampoSeccExp.setIndObservaciones(rs.getString("INDOBSERVACIONES"));
		audiCampoSeccExp.setNotaOk(rs.getString("NOTAOK"));
		audiCampoSeccExp.setNotaNoOk(rs.getString("NOTANOOK"));
		audiCampoSeccExp.setPorNivel0(rs.getBigDecimal("PORNIVEL0"));
		audiCampoSeccExp.setPorNivel1(rs.getBigDecimal("PORNIVEL1"));
		audiCampoSeccExp.setPorNivel3(rs.getBigDecimal("PORNIVEL3"));
		audiCampoSeccExp.setPorNivel5(rs.getBigDecimal("PORNIVEL5"));
		audiCampoSeccExp.setIndObligatorio(rs.getString("INDOBLIGATORIO"));
		audiCampoSeccExp.setIdSeccionPadre(rs.getInt("IDSECCIONPADRE"));
		audiCampoSeccExp.setOrden(rs.getInt("ORDEN"));
		audiCampoSeccExp.setObserv(rs.getString("OBSERV"));
		audiCampoSeccExp.setNivelCalidad(rs.getInt("NIVELCALIDAD"));
		audiCampoSeccExp.setPorNivelCalidad(rs.getBigDecimal("PORNIVELCALIDAD"));
		audiCampoSeccExp.setIndMarcado(rs.getString("INDMARCADO"));
		return audiCampoSeccExp;
	}

}
