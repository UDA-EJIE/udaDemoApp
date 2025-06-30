/**
 *
 */
package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.ExpTareasResumen;
import com.ejie.aa79b.model.OtrosTrabajos;
import com.ejie.aa79b.model.PersonalIZO;
import com.ejie.aa79b.model.Tareas;
import com.ejie.aa79b.model.TiposTarea;

/**
 * @author eaguirresarobe
 *
 */
public class OtrosTrabajoRowMapper implements RowMapper<ExpTareasResumen> {

	@Override
	public ExpTareasResumen mapRow(ResultSet rs, int arg1) throws SQLException {
		ExpTareasResumen tarea = new ExpTareasResumen();
		OtrosTrabajos otrosTrabajos = new OtrosTrabajos();
		otrosTrabajos.setIdTrabajo(rs.getBigDecimal("IDTRABAJO"));
		otrosTrabajos.setDescTrabajo(rs.getString("DESCTRABAJO"));
		otrosTrabajos.setFechaInicio(rs.getDate("FECHAINICIO"));
		otrosTrabajos.setFechaFinPrevista(rs.getDate("FECHAFINPREVISTA"));
		otrosTrabajos.setFechaFin(rs.getDate("FECHAFIN"));
		tarea.setOtrosTrabajos(otrosTrabajos);
		Tareas tareas = new Tareas();
		tareas.setIdTarea(rs.getBigDecimal("IDTAREA"));
		TiposTarea tiposTarea = new TiposTarea();
		tiposTarea.setId015(rs.getLong("IDTIPOTAREA"));
		tiposTarea.setDescEs015(rs.getString("TIPOTAREAES"));
		tiposTarea.setDescEu015(rs.getString("TIPOTAREAEU"));
		tareas.setTipoTarea(tiposTarea);
		tareas.setFechaIni(rs.getDate("FECHAINICIOTAREA"));
		tareas.setHoraIni(rs.getString("HORAINICIOTAREA"));
		tareas.setFechaFin(rs.getDate("FECHAFINTAREA"));
		tareas.setHoraFin(rs.getString("HORAFINTAREA"));
		tareas.setEstadoEjecucion(rs.getInt("ESTADOEJECID"));
		tareas.setEstadoAsignado(rs.getInt("ESTADOASIGNACION"));
		tareas.setEstadoEjecucionDesc(rs.getString("ESTADOEJECUCIONDESC"));
		tareas.setEstadoAsignadoDesc(rs.getString("ESTADOASIGNACIONDESC"));
		tareas.setObservaciones(rs.getString("OBSERVACIONES"));
		tareas.setOrden(rs.getInt("ORDEN"));
		PersonalIZO personaAsignador = new PersonalIZO();
		personaAsignador.setDni(rs.getString("DNIASIGNADOR"));
		personaAsignador.setSufDni(rs.getString("SUFDNIASIGNADOR"));
		personaAsignador.setNombre(rs.getString("NOMBREASIGNADOR"));
		personaAsignador.setApellido1(rs.getString("APEL1ASIGNADOR"));
		personaAsignador.setApellido2(rs.getString("APEL2ASIGNADOR"));
		tareas.setPersonaAsignador(personaAsignador);
		PersonalIZO personaAsignada = new PersonalIZO();
		personaAsignada.setDni(rs.getString("DNIRECURSO"));
		personaAsignada.setSufDni(rs.getString("SUFDNIRECURSO"));
		personaAsignada.setNombre(rs.getString("NOMBRERECURSO"));
		personaAsignada.setApellido1(rs.getString("APEL1RECURSO"));
		personaAsignada.setApellido2(rs.getString("APEL2RECURSO"));
		tareas.setPersonaAsignada(personaAsignada);
		tareas.setFechaAsignacion(rs.getDate("FECHAASIGNACION"));
		tareas.setHoraAsignacion(rs.getString("HORAASIGNACION"));
		tareas.setFechaAceptacion(rs.getDate("FECHAACEPTACION"));
		tareas.setEsRecursoDisponible(rs.getString("ESRECURSODISPONIBLE"));
		// TODO RECURSOASIGNACION
		// TODO ESTADOTAREASDEPENDIENTES
		// TODO LEFT JOIN AA79B26S01 tg2 ON tg2.ID_026 = OBTENER_GRUPO_TRABAJO(e1.ANYO_051,e1.NUM_EXP_051

		tarea.setTarea(tareas);
		return tarea;
	}

}
