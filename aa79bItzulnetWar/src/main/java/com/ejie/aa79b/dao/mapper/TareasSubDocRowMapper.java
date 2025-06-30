package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.Lotes;
import com.ejie.aa79b.model.PersonalIZO;
import com.ejie.aa79b.model.Tareas;
import com.ejie.aa79b.model.TiposTarea;

public class TareasSubDocRowMapper implements RowMapper<Tareas> {

	@Override
	public Tareas mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		final Tareas tarea = new Tareas();
		tarea.setIdTarea(resultSet.getBigDecimal(DBConstants.IDTAREA));
		tarea.setAnyo(resultSet.getLong(DBConstants.ANYO));
		tarea.setNumExp(resultSet.getInt(DBConstants.NUMEXP));
		TiposTarea tipoTarea = new TiposTarea();
		tipoTarea.setId015(resultSet.getLong(DBConstants.IDTIPOTAREA));
		tipoTarea.setDescEs015(resultSet.getString(DBConstants.TIPOTAREAES));
		tipoTarea.setDescEu015(resultSet.getString(DBConstants.TIPOTAREAEU));
		tarea.setTipoTarea(tipoTarea);
		tarea.setRecursoAsignacion(resultSet.getString(DBConstants.RECURSOASIGNACION));
		PersonalIZO personaAsignada = new PersonalIZO();
		personaAsignada.setDni(resultSet.getString(DBConstants.DNIRECURSO));
		tarea.setPersonaAsignada(personaAsignada);
		Lotes lote = new Lotes();
		lote.setIdLote(resultSet.getInt(DBConstants.IDLOTE));
		tarea.setLotes(lote);
		tarea.setEstadoEjecucion(resultSet.getInt(DBConstants.ESTADOEJECID));

		return tarea;
	}

}
