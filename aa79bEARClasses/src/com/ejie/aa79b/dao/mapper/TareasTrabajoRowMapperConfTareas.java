package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.Tareas;

/**
 *
 * @author eaguirresarobe
 *
 */
public class TareasTrabajoRowMapperConfTareas implements RowMapper<Tareas> {

	@Override
	public Tareas mapRow(ResultSet rs, int arg1) throws SQLException {
		final Tareas tareas = new Tareas();
		tareas.setEstadoEjecucion(rs.getInt(DBConstants.ESTADOEJECID));
		return tareas;
	}

}
