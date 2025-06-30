package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.DatosTareaTrados;
import com.ejie.aa79b.model.ExpTareasResumen;
import com.ejie.aa79b.model.ExpedienteInterpretacion;
import com.ejie.aa79b.model.ExpedienteTradRev;
import com.ejie.aa79b.model.PersonalIZO;
import com.ejie.aa79b.model.Tareas;
import com.ejie.aa79b.model.TiposTarea;

public class CargaTrabajoRowMapper implements RowMapper<ExpTareasResumen> {
	@Override()
	public ExpTareasResumen mapRow(ResultSet rs, int rowNum) throws SQLException {
		ExpTareasResumen expTareasResumen = new ExpTareasResumen();
		Tareas tareas = new Tareas();
		tareas.setAnyo(rs.getLong(DBConstants.ANYO));
		tareas.setNumExp(rs.getInt(DBConstants.NUMEXP));
		tareas.setIdTarea(rs.getBigDecimal(DBConstants.IDTAREA));
		tareas.setIdTareaRel(rs.getBigDecimal(DBConstants.IDTAREAREL));
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
		tareas.setEstadoEjecucion(rs.getInt(DBConstants.ESTADOEJECID));
		tareas.setEstadoAsignado(rs.getInt(DBConstants.ESTADOASIGID));
		tareas.setEstadoEjecucionDesc(rs.getString(DBConstants.ESTADOEJECUCIONDESC));
		tareas.setEstadoAsignadoDesc(rs.getString(DBConstants.ESTADOASIGNACIONDESC));
		tareas.setObservaciones(rs.getString(DBConstants.OBSERV));
		tareas.setOrden(rs.getInt(DBConstants.ORDEN));
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
		tareas.setFechaAsignacion(rs.getDate(DBConstants.FECHAASIGNACION));
		tareas.setHoraAsignacion(rs.getString(DBConstants.HORAASIGNACION));
		tareas.setFechaAceptacion(rs.getDate(DBConstants.FECHAACEPTACION));
		tareas.setRecursoAsignacion(rs.getString(DBConstants.RECURSOASIGNACION));
		tareas.setEstadoTareasDependientes(rs.getInt(DBConstants.ESTADOTAREASDEPENDIENTES));
		tareas.setEsRecursoDisponible(rs.getString(DBConstants.ESRECURSODISPONIBLE));

		expTareasResumen.setTarea(tareas);

		expTareasResumen.setAnyo(rs.getLong(DBConstants.ANYO));
		expTareasResumen.setNumExp(rs.getInt(DBConstants.NUMEXP));
		expTareasResumen.setTitulo(rs.getString(DBConstants.TITULO));
		expTareasResumen.setIdTipoExpediente(rs.getString(DBConstants.IDTIPOEXPEDIENTE));
		expTareasResumen.setTipoExpedienteDescEs(rs.getString(DBConstants.TIPOEXPEDIENTEDESCES));
		expTareasResumen.setTipoExpedienteDescEu(rs.getString(DBConstants.TIPOEXPEDIENTEDESCEU));
		expTareasResumen.setIndPrioritario(rs.getString(DBConstants.INDPRIORITARIO));
		expTareasResumen.setGrupoTrabajo(rs.getString("GRUPOTRABAJO"));

		ExpedienteTradRev expedienteTradRev = new ExpedienteTradRev();
		expedienteTradRev.setFechaFinalIzo(rs.getDate("FECHAFINALIZO"));
		expedienteTradRev.setHoraFinalIzo(rs.getString("HORAFINALIZO"));
		expedienteTradRev.setNumTotalPalIzo(rs.getInt("NUMTOTALPALIZO"));
		expedienteTradRev.setIndPublicarBopv(rs.getString("INDPUBLICARBOPV"));
		expedienteTradRev.setIndPrevistoBopv(rs.getString("INDPREVISTOBOPV"));

		DatosTareaTrados datosTareaTrados = new DatosTareaTrados();
		datosTareaTrados.setNumTotalPal(rs.getInt("NUMTOTALPAL"));
		datosTareaTrados.setNumPalConcor084(rs.getInt("NUMPALCONCOR084091"));
		datosTareaTrados.setNumPalConcor8594(rs.getInt("NUMPALCONCOR8594091"));
		datosTareaTrados.setNumPalConcor95100(rs.getInt("NUMPALCONCOR95100091"));
		datosTareaTrados.setNumPalConcor9599(rs.getInt("NUMPALCONCOR9599091"));
		datosTareaTrados.setNumPalConcor100(rs.getInt("NUMPALCONCOR100091"));
		expedienteTradRev.setTradosExp(datosTareaTrados);

		expTareasResumen.setExpedienteTradRev(expedienteTradRev);

		ExpedienteInterpretacion expedienteInterpretacion = new ExpedienteInterpretacion();
		expedienteInterpretacion.setFechaPrevistaInicio(rs.getDate(DBConstants.FECHAINICIOPREVISTA));
		expedienteInterpretacion.setHoraPrevistaInicio(rs.getString(DBConstants.HORAINICIOPREVISTA));
		expedienteInterpretacion.setFechaPrevistaEntrega(rs.getDate(DBConstants.FECHAFINPREVISTA));
		expedienteInterpretacion.setHoraPrevistaEntrega(rs.getString(DBConstants.HORAFINPREVISTA));
		expTareasResumen.setExpedienteInterpretacion(expedienteInterpretacion);

		return expTareasResumen;
	}
}