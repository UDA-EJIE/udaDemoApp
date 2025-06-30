package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.TareasGestionInterpretacion;

public class TareasGestionInterpretacionRowMapper implements RowMapper<TareasGestionInterpretacion> {

	@Override
	public TareasGestionInterpretacion mapRow(ResultSet resultSet, int arg1) throws SQLException {
		TareasGestionInterpretacion tareas = new TareasGestionInterpretacion();
		tareas.setHorasPrevistasInterpretacion(resultSet.getString("HORASINTERPRETACION"));
		tareas.setIndVisible(resultSet.getString("INDVISIBLE"));
		tareas.setIndPresupuesto(resultSet.getString("INDPRESU"));
		tareas.setNumInterpretes(resultSet.getLong("NUMINTERPRETES"));
		tareas.setPresupuesto(resultSet.getBigDecimal("PRESUPUESTO"));
		tareas.setFechaIni(resultSet.getDate("FECHAINI52"));
		tareas.setFechaFin(resultSet.getDate("FECHAFIN52"));
		tareas.setHoraIni(resultSet.getString("HORAINI52"));
		tareas.setHoraFin(resultSet.getString("HORAFIN52"));
		tareas.setHorasPrevistasInterpretacion(resultSet.getString("HORASINTERPRETACION"));
		tareas.setIdRequerimiento(resultSet.getLong("IDREQUERIMIENTO"));
		tareas.setFechaLimite(resultSet.getDate("FECHALIMITE"));
		tareas.setHoraLimite(resultSet.getString("HORALIMITE"));
		tareas.setAnyo(resultSet.getLong("ANYO"));
		tareas.setNumExp(resultSet.getInt("NUMEXP"));

		return tareas;
	}
}
