package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.ExpTareasResumen;
import com.ejie.aa79b.model.Lotes;
import com.ejie.aa79b.model.PersonalIZO;
import com.ejie.aa79b.model.Tareas;
import com.ejie.aa79b.model.TiposTarea;

public class DependeTareaRowMapper implements RowMapper<ExpTareasResumen> {
	@Override()
	public ExpTareasResumen mapRow(ResultSet rs, int rowNum) throws SQLException {
		ExpTareasResumen expTarea = new ExpTareasResumen();
		Tareas tareas = new Tareas();
		TiposTarea tiposTarea = new TiposTarea();
		tiposTarea.setId015(rs.getLong(DBConstants.IDTAREA));
		tiposTarea.setDescEs015(rs.getString(DBConstants.TIPOTAREAES));
		tiposTarea.setDescEu015(rs.getString(DBConstants.TIPOTAREAEU));
		tareas.setTipoTarea(tiposTarea);
		tareas.setRecursoAsignacion(rs.getString("RECURSOASIGNACION"));
		tareas.setFechaFin(rs.getDate(DBConstants.FECHAFIN));
		tareas.setHoraFin(rs.getString(DBConstants.HORAFIN));
		tareas.setEstadoEjecucionDesc(rs.getString(DBConstants.ESTADOEJECUCIONDESC));
		Lotes lotes = new Lotes();
		lotes.setIdLote(rs.getInt("LOTEID"));
		lotes.setNombreLote(rs.getString("NOMBRELOTE"));
		tareas.setLotes(lotes);
		expTarea.setTarea(tareas);

		PersonalIZO personaAsignada = new PersonalIZO();
		personaAsignada.setNombre(rs.getString(DBConstants.NOMBRERECURSO));
		personaAsignada.setApellido1(rs.getString(DBConstants.APEL1RECURSO));
		personaAsignada.setApellido2(rs.getString(DBConstants.APEL2RECURSO));
		tareas.setPersonaAsignada(personaAsignada);

		expTarea.setTitulo(rs.getString(DBConstants.TITULO));
		return expTarea;
	}
}