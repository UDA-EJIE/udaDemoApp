/**
 *
 */
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

/**
 * @author eaguirresarobe
 *
 */
public class GanttRecursoRowMapper implements RowMapper<ExpTareasResumen> {

	@Override
	public ExpTareasResumen mapRow(ResultSet rs, int arg1) throws SQLException {
		ExpTareasResumen expTareasResumen = new ExpTareasResumen();
		Tareas tareas = new Tareas();
		tareas.setAnyo(rs.getLong(DBConstants.ANYO));
		tareas.setNumExp(rs.getInt(DBConstants.NUMEXP));
		tareas.setIdTarea(rs.getBigDecimal(DBConstants.IDTAREA));
		TiposTarea tiposTarea = new TiposTarea();
		tiposTarea.setId015(rs.getLong(DBConstants.IDTIPOTAREA));
		tiposTarea.setDescEs015(rs.getString(DBConstants.TIPOTAREAES));
		tiposTarea.setDescEu015(rs.getString(DBConstants.TIPOTAREAEU));
		tareas.setTipoTarea(tiposTarea);
		tareas.setFechaIni(rs.getDate(DBConstants.FECHAINI));
		tareas.setHoraIni(rs.getString(DBConstants.HORAINI));
		tareas.setFechaFin(rs.getDate(DBConstants.FECHAFIN));
		tareas.setHoraFin(rs.getString(DBConstants.HORAFIN));
		tareas.setEstadoEjecucion(rs.getInt(DBConstants.ESTADOEJECID));
		tareas.setEstadoAsignado(rs.getInt(DBConstants.ESTADOASIGID));
		tareas.setEstadoEjecucionDesc(rs.getString(DBConstants.ESTADOEJECUCIONDESC));
		tareas.setEstadoAsignadoDesc(rs.getString(DBConstants.ESTADOASIGNACIONDESC));
		PersonalIZO personaAsignada = new PersonalIZO();
		personaAsignada.setDni(rs.getString(DBConstants.DNIRECURSO));
		personaAsignada.setSufDni(rs.getString(DBConstants.SUFDNIRECURSO));
		personaAsignada.setNombre(rs.getString(DBConstants.NOMBRERECURSO));
		personaAsignada.setApellido1(rs.getString(DBConstants.APEL1RECURSO));
		personaAsignada.setApellido2(rs.getString(DBConstants.APEL2RECURSO));
		tareas.setPersonaAsignada(personaAsignada);

		PersonalIZO personaAsignador = new PersonalIZO();
		personaAsignador.setDni(rs.getString(DBConstants.DNIASIGNADOR));
		personaAsignador.setSufDni(rs.getString(DBConstants.SUFDNIASIGNADOR));
		personaAsignador.setNombre(rs.getString(DBConstants.NOMBREASIGNADOR));
		personaAsignador.setApellido1(rs.getString(DBConstants.APEL1ASIGNADOR));
		personaAsignador.setApellido2(rs.getString(DBConstants.APEL2ASIGNADOR));
		tareas.setPersonaAsignador(personaAsignador);
		tareas.setRecursoAsignacion(rs.getString(DBConstants.RECURSOASIGNACION));

		expTareasResumen.setTarea(tareas);

		expTareasResumen.setAnyo(rs.getLong(DBConstants.ANYO));
		expTareasResumen.setNumExp(rs.getInt(DBConstants.NUMEXP));
		expTareasResumen.setTitulo(rs.getString(DBConstants.TITULO));
		expTareasResumen.setIdTipoExpediente(rs.getString(DBConstants.IDTIPOEXPEDIENTE));
		expTareasResumen.setTipoExpedienteDescEs(rs.getString(DBConstants.TIPOEXPEDIENTEDESCES));
		expTareasResumen.setTipoExpedienteDescEu(rs.getString(DBConstants.TIPOEXPEDIENTEDESCEU));
		expTareasResumen.setIndPrioritario(rs.getString(DBConstants.INDPRIORITARIO));
		expTareasResumen.setGrupoTrabajo(rs.getString("GRUPOTRABAJO"));

		OtrosTrabajos otrosTrabajos = new OtrosTrabajos();
		otrosTrabajos.setIdTrabajo(rs.getBigDecimal("IDTRABAJO"));
		otrosTrabajos.setDescTrabajo(rs.getString("DESCTRABAJO"));

		expTareasResumen.setOtrosTrabajos(otrosTrabajos);

		return expTareasResumen;
	}

}
