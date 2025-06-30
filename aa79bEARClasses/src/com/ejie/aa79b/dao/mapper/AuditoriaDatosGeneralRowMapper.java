package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.Auditoria;
import com.ejie.aa79b.model.Lotes;
import com.ejie.aa79b.model.Persona;
import com.ejie.aa79b.model.PersonalIZO;

public class AuditoriaDatosGeneralRowMapper implements RowMapper<Auditoria> {

	@Override
	public Auditoria mapRow(ResultSet rs, int arg1) throws SQLException {
		Auditoria auditoria = new Auditoria();
		auditoria.setIdAuditoria(rs.getLong("IDAUDITORIA"));
		auditoria.setAnyo(rs.getLong("ANYO"));
		auditoria.setNumExp(rs.getInt("NUMEXP"));
		auditoria.setEstado(rs.getInt("ESTADOAUDITORIA"));
		auditoria.setEstadoDescEu(rs.getString("ESTADOAUDITORIADESCEU"));
		auditoria.setEstadoDescEs(rs.getString("ESTADOAUDITORIADESCES"));
		auditoria.setFechaEnvio(rs.getTimestamp("FECHAAUDITORIA"));
		auditoria.setFechaConfirmacion(rs.getTimestamp("FECHACONFIRMACIONAUDITORIA"));
		auditoria.setIndEnviado(rs.getString("INDENVIADO"));

		auditoria.setIdTarea(rs.getBigDecimal("IDTAREAREVISION"));
		auditoria.setIdTareaAuditar(rs.getBigDecimal("IDTAREAAUDITAR"));
		// datos auditor
		PersonalIZO auditor = new PersonalIZO();
		auditor.setDni(rs.getString("DNIAUDITOR"));
		auditor.setPreDni(rs.getString("PREDNIAUDITOR"));
		auditor.setSufDni(rs.getString("SUFDNIAUDITOR"));
		auditor.setTipIden(rs.getInt("TIPIDENAUDITOR"));
		auditor.setNombre(rs.getString("NOMBREAUDITOR"));
		auditor.setApellido1(rs.getString("APEL1AUDITOR"));
		auditor.setApellido2(rs.getString("APEL2AUDITOR"));
		auditoria.setAuditorAsignado(auditor);
		// datos Lote
		Lotes lote = new Lotes();
		lote.setIdLote(rs.getInt("IDLOTE"));
		lote.setDescLoteEs(rs.getString("DESCLOTEES"));
		lote.setDescLoteEu(rs.getString("DESCLOTEEU"));
		Persona personaContacto = new Persona();
		personaContacto.setDni(rs.getString("DNICONTACTOLOTE"));
		lote.setContacto(personaContacto);
		auditoria.setLote(lote);

		return auditoria;
	}

}
