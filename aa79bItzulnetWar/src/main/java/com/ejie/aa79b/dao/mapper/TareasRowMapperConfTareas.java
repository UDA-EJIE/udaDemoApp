package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.Entidad;
import com.ejie.aa79b.model.Lotes;
import com.ejie.aa79b.model.PersonalIZO;
import com.ejie.aa79b.model.Tareas;
import com.ejie.aa79b.model.TiposRevision;
import com.ejie.aa79b.model.TiposTarea;

public class TareasRowMapperConfTareas implements RowMapper<Tareas> {

	@Override()
	public Tareas mapRow(ResultSet rs, int rowNum) throws SQLException {
		final Tareas tareas = new Tareas();
		tareas.setAnyo(rs.getLong(DBConstants.ANYO));
		tareas.setNumExp(rs.getInt(DBConstants.NUMEXP));
		tareas.setIdTarea(rs.getBigDecimal(DBConstants.IDTAREA));
		tareas.setIndMostrarNotasATrad(rs.getString(DBConstants.IND_MOSTRAR_NOTAS_A_TRAD));
		TiposTarea tiposTarea = new TiposTarea();
		tiposTarea.setId015(rs.getLong(DBConstants.IDTIPOTAREA));
		tiposTarea.setDescEs015(rs.getString(DBConstants.TIPOTAREAES));
		tiposTarea.setDescEu015(rs.getString(DBConstants.TIPOTAREAEU));
		tiposTarea.setIndReqCierre015(rs.getString("INDREQCIERRE"));
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
		personaAsignada.setNombre(rs.getString("NOMBRERECURSO"));
		personaAsignada.setApellido1(rs.getString("APELLIDO1RECURSO"));
		personaAsignada.setApellido2(rs.getString("APELLIDO2RECURSO"));
		Entidad entidad = new Entidad();
		entidad.setCodigo(rs.getInt(DBConstants.IDENTIDAD));
		entidad.setTipo(rs.getString(DBConstants.TIPOENTIDAD));
		entidad.setTipoDesc(rs.getString(DBConstants.TIPOENTIDADDESC));
		personaAsignada.setEntidad(entidad);
		tareas.setPersonaAsignada(personaAsignada);
		Lotes lotes = new Lotes();
		lotes.setIdLote(rs.getInt(DBConstants.IDLOTE));
		lotes.setNombreLote(rs.getString(DBConstants.NOMBRELOTE));
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
		TiposRevision tipoRevision = new TiposRevision();
		tipoRevision.setId018(rs.getLong("IDTIPOREVISION"));
		tareas.setTipoRevision(tipoRevision);
		tareas.setIndReqRevision(rs.getString("INDREQREVISION_081"));

		Date fechaFinInterpretacion = rs.getDate("FECHAFININTER");
		if (fechaFinInterpretacion != null) {
			// Expediente interpretación
			tareas.setFechaEntrega(fechaFinInterpretacion);
			tareas.setHoraEntrega(rs.getString("HORAFININTER"));
		} else {
			// Expediente traducción / revisión
			tareas.setFechaEntrega(rs.getDate(DBConstants.FECHAFINALIZO));
			tareas.setHoraEntrega(rs.getString(DBConstants.HORAFINALIZO));
		}

		if (StringUtils.isEmpty(personaAsignada.getNombreCompleto())) {
			tareas.setRecursoAsignado(lotes.getNombreLote());
		} else {
			tareas.setRecursoAsignado(personaAsignada.getNombreCompleto());
		}
		tareas.setEsRecursoDisponible(rs.getString(DBConstants.ESRECURSODISPONIBLE));
		tareas.setTieneOtrasTareas(rs.getString("TIENEOTRASTAREAS"));
		tareas.setIndObligarXliff(rs.getString(DBConstants.IND_OBLIGAR_XLIFF));

		return tareas;
	}

}
