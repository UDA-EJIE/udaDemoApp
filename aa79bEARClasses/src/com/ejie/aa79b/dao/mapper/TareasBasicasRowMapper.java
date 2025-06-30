package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.EjecucionTareas;
import com.ejie.aa79b.model.ObservacionesTareas;
import com.ejie.aa79b.model.Tareas;
import com.ejie.aa79b.model.TiposTarea;

public class TareasBasicasRowMapper implements RowMapper<Tareas> {

	@Override
	public Tareas mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Tareas tareas = new Tareas();
		tareas.setIdTarea(resultSet.getBigDecimal("IDTAREA"));
		TiposTarea tipoTarea = new TiposTarea();
		tipoTarea.setId015(resultSet.getLong("IDTIPOTAREA"));
		tareas.setTipoTarea(tipoTarea);

		tareas.setIndNoConformidad(resultSet.getString("INDNOCONF"));

		tareas.setObservaciones(resultSet.getString("OBSERV"));

		if (StringUtils.isNotEmpty(resultSet.getString("OBSVEJEC"))) {
			ObservacionesTareas observacionesTareas = new ObservacionesTareas();
			observacionesTareas.setObsvEjecucion(resultSet.getString("OBSVEJEC"));
			tareas.setObservacionesTareas(observacionesTareas);
		}

		EjecucionTareas ejecucionTareas = new EjecucionTareas();
		ejecucionTareas.setHorasTarea(resultSet.getString("HORASTAREA"));
		ejecucionTareas.setIndRealizada(resultSet.getString("INDREALIZADA"));
		ejecucionTareas.setPorUsoEuskera(resultSet.getLong("PORUSOEUSKERA"));
		ejecucionTareas.setIndObservaciones(resultSet.getString(DBConstants.INDOBSERVACIONES));

		tareas.setEjecucionTareas(ejecucionTareas);

		return tareas;
	}

}
