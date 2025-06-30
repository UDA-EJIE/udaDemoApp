package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.Entidad;
import com.ejie.aa79b.model.Lotes;
import com.ejie.aa79b.model.PersonalIZO;
import com.ejie.aa79b.model.Tareas;
import com.ejie.aa79b.model.TiposTarea;

public class TareasRowMapperTareasAEliminar implements RowMapper<Tareas> {

	@Override()
	public Tareas mapRow(ResultSet rs, int rowNum) throws SQLException {
		final Tareas tareas = new Tareas();
		tareas.setAnyo(rs.getLong(DBConstants.ANYO));
		tareas.setNumExp(rs.getInt(DBConstants.NUMEXP));
		tareas.setIdTarea(rs.getBigDecimal(DBConstants.IDTAREA));
		TiposTarea tiposTarea = new TiposTarea();
		tiposTarea.setId015(rs.getLong(DBConstants.IDTIPOTAREA));
		tiposTarea.setDescEs015(rs.getString(DBConstants.TIPOTAREAES));
		tiposTarea.setDescEu015(rs.getString(DBConstants.TIPOTAREAEU));
		tareas.setTipoTarea(tiposTarea);
		tareas.setIndFacturacion(rs.getString(DBConstants.INDFACTURABLE));
		tareas.setFechaIni(rs.getDate(DBConstants.FECHAINI));
		tareas.setHoraIni(rs.getString(DBConstants.HORAINI));
		tareas.setFechaFin(rs.getDate(DBConstants.FECHAFIN));
		tareas.setHoraFin(rs.getString(DBConstants.HORAFIN));
		tareas.setHorasPrevistas(rs.getString(DBConstants.HORASPREVISTAS));
		tareas.setRecursoAsignacion(rs.getString(DBConstants.RECURSOASIGNACION));
		PersonalIZO personaAsignada = new PersonalIZO();
		personaAsignada.setDni(rs.getString(DBConstants.DNIRECURSO));
		Entidad entidad = new Entidad();
		entidad.setCodigo(rs.getInt(DBConstants.IDENTIDAD));
		entidad.setTipo(rs.getString(DBConstants.TIPOENTIDAD));
		entidad.setTipoDesc(rs.getString(DBConstants.TIPOENTIDADDESC));
		personaAsignada.setEntidad(entidad);
		tareas.setPersonaAsignada(personaAsignada);
		Lotes lotes = new Lotes();
		lotes.setIdLote(rs.getInt(DBConstants.IDLOTE));
		tareas.setLotes(lotes);
		tareas.setEstadoAsignado(rs.getInt(DBConstants.ESTADOASIGID));
		tareas.setEstadoAsignadoDesc(rs.getString(DBConstants.ESTADOASIGNACIONDESC));
		tareas.setEstadoEjecucion(rs.getInt(DBConstants.ESTADOEJECID));
		tareas.setEstadoEjecucionDesc(rs.getString(DBConstants.ESTADOEJECUCIONDESC));
		tareas.setObservaciones(rs.getString(DBConstants.OBSERV));
		tareas.setOrden(rs.getInt(DBConstants.ORDEN));
		PersonalIZO personaAsignador = new PersonalIZO();
		personaAsignador.setDni(rs.getString(DBConstants.DNIASIGNADOR));
		tareas.setPersonaAsignador(personaAsignador);
		tareas.setFechaAsignacion(rs.getDate(DBConstants.FECHAASIGNACION));
		tareas.setHoraAsignacion(rs.getString(DBConstants.HORAASIGNACION));
		tareas.setFechaAceptacion(rs.getDate(DBConstants.FECHAACEPTACION));
		tareas.setIdTareaRel(rs.getBigDecimal(DBConstants.IDTAREAREL));
		tareas.setIdRequerimiento(rs.getLong(DBConstants.IDREQUERIMIENTO));

		return tareas;
	}

}
