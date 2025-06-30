package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.ExpTareasResumen;
import com.ejie.aa79b.model.OtrosTrabajos;
import com.ejie.aa79b.model.Tareas;

public class GanttExpTrabajoRowMapper implements RowMapper<ExpTareasResumen> {
	@Override()
	public ExpTareasResumen mapRow(ResultSet rs, int rowNum) throws SQLException {
		ExpTareasResumen expTarea = new ExpTareasResumen();
		expTarea.setAnyo(rs.getLong(DBConstants.ANYO));
		expTarea.setNumExp(rs.getInt(DBConstants.NUMEXP));
		expTarea.setTitulo(rs.getString(DBConstants.TITULO));
		Tareas tarea = new Tareas();
		if (null == rs.getDate(DBConstants.FECHAINICIOTAREA)) {
			tarea.setFechaIni(rs.getDate(DBConstants.FECHAINICIO));
		} else {
			tarea.setFechaIni(rs.getDate(DBConstants.FECHAINICIOTAREA));
		}
		tarea.setFechaFin(rs.getDate(DBConstants.FECHAFIN));
		expTarea.setTarea(tarea);
		OtrosTrabajos trabajo = new OtrosTrabajos();
		trabajo.setIdTrabajo(rs.getBigDecimal("IDTRABAJO"));
		trabajo.setDescTrabajo(rs.getString("DESCTRABAJO"));
		Tareas tareaTrabajo = new Tareas();
		if (null == rs.getDate("FECHAINICIOTAREATRABAJO")) {
			tareaTrabajo.setFechaIni(rs.getDate("FECHAINICIOTRABAJO"));
		} else {
			tareaTrabajo.setFechaIni(rs.getDate("FECHAINICIOTAREATRABAJO"));
		}
		tareaTrabajo.setFechaFin(rs.getDate("FECHAFINTRABAJO"));
		expTarea.setTareaTrabajo(tareaTrabajo);
		expTarea.setOtrosTrabajos(trabajo);
		return expTarea;
	}
}