package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.webservices.Aa79bAuditoria;
import com.ejie.aa79b.model.webservices.Aa79bLotes;
import com.ejie.aa79b.model.webservices.Aa79bPersona;
import com.ejie.aa79b.model.webservices.Aa79bTarea;
import com.ejie.aa79b.model.webservices.Aa79bTiposTarea;

public class Aa79bAuditoriaRowMapper implements RowMapper<Aa79bAuditoria> {
	@Override
	public Aa79bAuditoria mapRow(ResultSet resultSet, int rowNum) throws SQLException {

		Aa79bAuditoria auditoria = new Aa79bAuditoria();
		auditoria.setId(resultSet.getBigDecimal("ID_AUDITORIA"));
		auditoria.setEstado(resultSet.getInt("ESTADO_AUDITORIA"));
		auditoria.setIdTareaAuditar(resultSet.getBigDecimal("ID_TAREA_AUDITAR"));

		final Date fechaEnvio = resultSet.getTimestamp("FECHA_ENVIO");
		final Date fechaConfirmacion = resultSet.getTimestamp("FECHA_CONFIRMACION");

		if (fechaEnvio != null) {
			auditoria.setFechaEnvio(fechaEnvio.getTime());
		}

		if (fechaConfirmacion != null) {
			auditoria.setFechaConfirmacion(fechaConfirmacion.getTime());
		}

		Aa79bTarea tarea = new Aa79bTarea();
		tarea.setIdTarea(resultSet.getBigDecimal("ID_TAREA"));
		tarea.setAnyo(resultSet.getLong("ANYO"));
		tarea.setNumExp(resultSet.getInt("NUM_EXP"));

		Aa79bTiposTarea tipoTarea = new Aa79bTiposTarea();
		tipoTarea.setId(resultSet.getLong("ID_TIPO_TAREA"));
		tipoTarea.setDescEs(resultSet.getString("TIPO_TAREA_ES"));
		tipoTarea.setDescEu(resultSet.getString("TIPO_TAREA_EU"));
		tarea.setTipoTarea(tipoTarea);

		Aa79bPersona auditor = new Aa79bPersona();
		auditor.setDni(resultSet.getString("DNI_AUDITOR"));
		auditor.setSufDni(resultSet.getString("SUFDNI_AUDITOR"));
		auditor.setNombre(resultSet.getString("NOMBRE_AUDITOR"));
		auditor.setApe1(resultSet.getString("APEL1_AUDITOR"));
		auditor.setApe2(resultSet.getString("APEL2_AUDITOR"));
		tarea.setPersonaAsignada(auditor);

		Aa79bLotes lotes = new Aa79bLotes();
		lotes.setDescLoteEs(resultSet.getString("DESC_LOTE_ES"));
		lotes.setDescLoteEu(resultSet.getString("DESC_LOTE_EU"));
		tarea.setLotes(lotes);

		auditoria.setTarea(tarea);

		return auditoria;
	}

}
