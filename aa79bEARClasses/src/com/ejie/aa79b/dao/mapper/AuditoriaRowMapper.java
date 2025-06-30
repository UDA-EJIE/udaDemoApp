package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.Auditoria;
import com.ejie.aa79b.model.Lotes;
import com.ejie.aa79b.model.PersonalIZO;
import com.ejie.aa79b.model.TiposTarea;

public class AuditoriaRowMapper implements RowMapper<Auditoria> {
	@Override
	public Auditoria mapRow(ResultSet resultSet, int rowNum) throws SQLException {

		Auditoria auditoria = new Auditoria();
		auditoria.setIdTarea(resultSet.getBigDecimal("ID_TAREA"));
		auditoria.setEstado(resultSet.getInt("ESTADO_AUDITORIA"));
		auditoria.setIdTareaAuditar(resultSet.getBigDecimal("IDTAREAAUDITAR"));

		TiposTarea tipoTarea = new TiposTarea();
		tipoTarea.setDescEs015(resultSet.getString("TIPO_TAREA_ES"));
		tipoTarea.setDescEu015(resultSet.getString("TIPO_TAREA_EU"));
		auditoria.setTipoTarea(tipoTarea);

		auditoria.setAnyo(resultSet.getLong("ANYO"));
		auditoria.setNumExp(resultSet.getInt("NUM_EXP"));

		PersonalIZO auditor = new PersonalIZO();
		auditor.setDni(resultSet.getString("DNI_AUDITOR"));
		auditor.setSufDni(resultSet.getString("SUFDNI_AUDITOR"));
		auditor.setNombre(resultSet.getString("NOMBRE_AUDITOR"));
		auditor.setApellido1(resultSet.getString("APEL1_AUDITOR"));
		auditor.setApellido2(resultSet.getString("APEL2_AUDITOR"));
		auditoria.setAuditorAsignado(auditor);

		Lotes lotes = new Lotes();
		lotes.setDescLoteEs(resultSet.getString("DESC_LOTE_ES"));
		lotes.setDescLoteEu(resultSet.getString("DESC_LOTE_EU"));
		auditoria.setLotes(lotes);

		auditoria.setFechaEnvio(resultSet.getTimestamp("FECHA_ENVIO"));
		auditoria.setFechaConfirmacion(resultSet.getTimestamp("FECHA_CONFIRMACION"));

		return auditoria;
	}

}
