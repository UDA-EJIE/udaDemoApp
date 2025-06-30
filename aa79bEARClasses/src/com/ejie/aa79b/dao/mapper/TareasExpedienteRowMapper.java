package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.ExpTareasResumen;
import com.ejie.aa79b.model.OtrosTrabajos;
import com.ejie.aa79b.model.PersonalIZO;
import com.ejie.aa79b.model.Tareas;
import com.ejie.aa79b.model.TiposTarea;

public class TareasExpedienteRowMapper implements RowMapper<ExpTareasResumen> {
	@Override()
	public ExpTareasResumen mapRow(ResultSet rs, int rowNum) throws SQLException {

		ExpTareasResumen expTarea = new ExpTareasResumen();
		Tareas tarea = new Tareas();
		tarea.setAnyo(rs.getLong(DBConstants.ANYO));
		tarea.setNumExp(rs.getInt(DBConstants.NUMEXP));
		tarea.setIdTarea(rs.getBigDecimal(DBConstants.IDTAREA));
		tarea.setFechaIni(rs.getDate(DBConstants.FECHAINI));
		tarea.setFechaFin(rs.getDate(DBConstants.FECHAFIN));
		tarea.setEstadoAsignado(rs.getInt(DBConstants.ESTADOASIGID));
		tarea.setEstadoEjecucion(rs.getInt(DBConstants.ESTADOEJECID));

		PersonalIZO personaAsignada = new PersonalIZO();
		personaAsignada.setDni(rs.getString(DBConstants.DNIRECURSO));
		personaAsignada.setNombre(rs.getString(DBConstants.NOMBRE));
		personaAsignada.setApellido1(rs.getString(DBConstants.APE1));
		if (null != rs.getString(DBConstants.APE2)) {
			personaAsignada.setApellido2(rs.getString(DBConstants.APE2));
		}
		tarea.setPersonaAsignada(personaAsignada);

		TiposTarea tipoTarea = new TiposTarea();
		tipoTarea.setId015(rs.getLong(DBConstants.IDTIPOTAREA));
		tipoTarea.setDescEs015(rs.getString(DBConstants.DESCES));
		tipoTarea.setDescEu015(rs.getString(DBConstants.DESCEU));
		tarea.setTipoTarea(tipoTarea);
		expTarea.setTarea(tarea);

		OtrosTrabajos trabajo = new OtrosTrabajos();
		trabajo.setIdTrabajo(rs.getBigDecimal("IDTRABAJO"));
		expTarea.setOtrosTrabajos(trabajo);
		Tareas tareaTrabajo = new Tareas();
		tareaTrabajo.setIdTarea(rs.getBigDecimal("IDTAREATRABAJO"));
		tareaTrabajo.setEstadoAsignado(rs.getInt("ESTADOASIGIDTRABAJO"));
		tareaTrabajo.setEstadoEjecucion(rs.getInt("ESTADOEJECIDTRABAJO"));
		tareaTrabajo.setFechaIni(rs.getDate("FECHAINITRABAJO"));
		tareaTrabajo.setFechaFin(rs.getDate("FECHAFINTRABAJO"));
		PersonalIZO personaAsignadaTrabajo = new PersonalIZO();
		personaAsignadaTrabajo.setDni(rs.getString("DNIRECURSOTRABAJO"));
		personaAsignadaTrabajo.setNombre(rs.getString("NOMBRETRABAJO"));
		personaAsignadaTrabajo.setApellido1(rs.getString("APE1TRABAJO"));
		if (null != rs.getString("APE2TRABAJO")) {
			personaAsignadaTrabajo.setApellido2(rs.getString("APE2TRABAJO"));
		}
		tareaTrabajo.setPersonaAsignada(personaAsignadaTrabajo);
		TiposTarea tipoTareaTrabajo = new TiposTarea();
		tipoTareaTrabajo.setId015(rs.getLong("IDTIPOTAREATRABAJO"));
		tipoTareaTrabajo.setDescEs015(rs.getString("DESCESTRABAJO"));
		tipoTareaTrabajo.setDescEu015(rs.getString("DESCEUTRABAJO"));
		tareaTrabajo.setTipoTarea(tipoTareaTrabajo);

		expTarea.setTareaTrabajo(tareaTrabajo);
		return expTarea;
	}
}