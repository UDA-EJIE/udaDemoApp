package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.webservices.Aa79bAuditoria;
import com.ejie.aa79b.model.webservices.Aa79bLotes;
import com.ejie.aa79b.model.webservices.Aa79bPersona;
import com.ejie.aa79b.model.webservices.Aa79bTarea;

public class Aa79bAuditoriaDatosGeneralesRowMapper implements RowMapper<Aa79bAuditoria> {

	@Override
	public Aa79bAuditoria mapRow(ResultSet rs, int arg1) throws SQLException {
		Aa79bAuditoria auditoria = new Aa79bAuditoria();
		auditoria.setId(rs.getBigDecimal("IDAUDITORIA"));
		auditoria.setIdTareaAuditar(rs.getBigDecimal("IDTAREAAUDITAR"));
		auditoria.setEstado(rs.getInt("ESTADOAUDITORIA"));
		auditoria.setEstadoDescEu(rs.getString("ESTADOAUDITORIADESCEU"));
		auditoria.setEstadoDescEs(rs.getString("ESTADOAUDITORIADESCES"));

		final Date fechaEnvio = rs.getTimestamp("FECHAAUDITORIA");
		final Date fechaConfirmacion = rs.getTimestamp("FECHACONFIRMACIONAUDITORIA");

		if (fechaEnvio != null) {
			auditoria.setFechaEnvio(fechaEnvio.getTime());
		}

		if (fechaConfirmacion != null) {
			auditoria.setFechaConfirmacion(fechaConfirmacion.getTime());
		}

		auditoria.setIndEnviado(rs.getString("INDENVIADO"));

		Aa79bTarea tarea = new Aa79bTarea();
		tarea.setAnyo(rs.getLong("ANYO"));
		tarea.setNumExp(rs.getInt("NUMEXP"));

		// datos auditor
		Aa79bPersona auditor = new Aa79bPersona();
		auditor.setDni(rs.getString("DNIAUDITOR"));
		auditor.setPreDni(rs.getString("PREDNIAUDITOR"));
		auditor.setSufDni(rs.getString("SUFDNIAUDITOR"));
		auditor.setTipIden(rs.getInt("TIPIDENAUDITOR"));
		auditor.setNombre(rs.getString("NOMBREAUDITOR"));
		auditor.setApe1(rs.getString("APEL1AUDITOR"));
		auditor.setApe2(rs.getString("APEL2AUDITOR"));
		tarea.setPersonaAsignada(auditor);

		// datos Lote
		Aa79bLotes lote = new Aa79bLotes();
		lote.setIdLote(rs.getInt("IDLOTE"));
		lote.setDescLoteEs(rs.getString("DESCLOTEES"));
		lote.setDescLoteEu(rs.getString("DESCLOTEEU"));

		Aa79bPersona personaContacto = new Aa79bPersona();
		personaContacto.setDni(rs.getString("DNICONTACTOLOTE"));
		lote.setContacto(personaContacto);
		tarea.setLotes(lote);

		auditoria.setTarea(tarea);

		return auditoria;
	}

}