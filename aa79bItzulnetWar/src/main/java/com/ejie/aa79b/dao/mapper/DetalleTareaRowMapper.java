package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.DatosContacto;
import com.ejie.aa79b.model.DatosTareaTrados;
import com.ejie.aa79b.model.EjecucionTareas;
import com.ejie.aa79b.model.EmpresasProveedoras;
import com.ejie.aa79b.model.ExpTareasResumen;
import com.ejie.aa79b.model.ExpedienteInterpretacion;
import com.ejie.aa79b.model.ExpedienteTradRev;
import com.ejie.aa79b.model.Lotes;
import com.ejie.aa79b.model.Persona;
import com.ejie.aa79b.model.PersonalIZO;
import com.ejie.aa79b.model.Tareas;
import com.ejie.aa79b.model.TiposRevision;
import com.ejie.aa79b.model.TiposTarea;

public class DetalleTareaRowMapper implements RowMapper<ExpTareasResumen> {
	@Override()
	public ExpTareasResumen mapRow(ResultSet rs, int rowNum) throws SQLException {
		ExpTareasResumen expTarea = new ExpTareasResumen();
		Tareas tareas = new Tareas();
		tareas.setAnyo(rs.getLong(DBConstants.ANYO));
		tareas.setNumExp(rs.getInt(DBConstants.NUMEXP));
		tareas.setIdTarea(rs.getBigDecimal(DBConstants.IDTAREA));
		TiposTarea tiposTarea = new TiposTarea();
		tiposTarea.setId015(rs.getLong(DBConstants.IDTIPOTAREA));
		tiposTarea.setDescEs015(rs.getString(DBConstants.TIPOTAREAES));
		tiposTarea.setDescEu015(rs.getString(DBConstants.TIPOTAREAEU));
		tareas.setTipoTarea(tiposTarea);

		TiposRevision tipoRevision = new TiposRevision();
		tipoRevision.setId018(rs.getLong("IDTIPOREVISION"));
		tipoRevision.setDescEs018(rs.getString("TIPOREVISIONDESCES"));
		tipoRevision.setDescEu018(rs.getString("TIPOREVISIONDESCEU"));
		tareas.setTipoRevision(tipoRevision);

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
		tareas.setFechaAsignacion(rs.getDate(DBConstants.FECHAASIGNACION));
		tareas.setHoraAsignacion(rs.getString(DBConstants.HORAASIGNACION));
		tareas.setFechaAceptacion(rs.getDate(DBConstants.FECHAACEPTACION));
		tareas.setHoraAceptacion(rs.getString(DBConstants.HORAACEPTACION));
		tareas.setRecursoAsignacion(rs.getString(DBConstants.RECURSOASIGNACION));
		tareas.setObservacionesRechazo(rs.getString(DBConstants.OBSRVRECHAZO));
		tareas.setIndMostrarNotasATrad(rs.getString(DBConstants.IND_MOSTRAR_NOTAS_A_TRAD));
		tareas.setIndObligarXliff(rs.getString(DBConstants.IND_OBLIGAR_XLIFF));

		expTarea.setAnyo(rs.getLong(DBConstants.ANYO));
		expTarea.setNumExp(rs.getInt(DBConstants.NUMEXP));
		expTarea.setIdTipoExpediente(rs.getString(DBConstants.IDTIPOEXPEDIENTE));
		expTarea.setTipoExpedienteDescEs(rs.getString(DBConstants.TIPOEXPEDIENTEDESCES));
		expTarea.setTipoExpedienteDescEu(rs.getString(DBConstants.TIPOEXPEDIENTEDESCEU));

		Persona solicitante = new Persona();
		solicitante.setNombre(rs.getString("NOMBRESOLIC"));
		solicitante.setApellido1(rs.getString("APEL1SOLIC"));
		solicitante.setApellido2(rs.getString("APEL2SOLIC"));
		DatosContacto datosSolic = new DatosContacto();
		datosSolic.setEmail031(rs.getString("EMAILSOLIC"));
		datosSolic.setTelmovil031(rs.getString("TELSOLIC"));
		solicitante.setDatosContacto(datosSolic);
		expTarea.setSolicitante(solicitante);

		ExpedienteTradRev expedienteTradRev = new ExpedienteTradRev();
		expedienteTradRev.setIndPublicarBopv(rs.getString("BOPV"));
		expedienteTradRev.setFechaFinalIzo(rs.getDate(DBConstants.FECHAFINALIZO));
		expedienteTradRev.setNumTotalPalIzo(rs.getInt(DBConstants.NUMTOTALPALIZO));
		expedienteTradRev.setNumTotalPalSolic(rs.getInt(DBConstants.NUMTOTALPALSOLIC));
		DatosTareaTrados tradosExp = new DatosTareaTrados();
		tradosExp.setNumTotalPal(rs.getInt("NUMTOTALPAL"));
		tradosExp.setNumPalConcor084(rs.getInt("NUMPALCONCOR084091"));
		tradosExp.setNumPalConcor8594(rs.getInt("NUMPALCONCOR8594091"));
		tradosExp.setNumPalConcor95100(rs.getInt("NUMPALCONCOR95100091"));
		tradosExp.setNumPalConcor9599(rs.getInt("NUMPALCONCOR9599091"));
		tradosExp.setNumPalConcor100(rs.getInt("NUMPALCONCOR100091"));
		expedienteTradRev.setTradosExp(tradosExp);

		expedienteTradRev.setIdIdioma(rs.getLong(DBConstants.IDIDIOMA));
		expedienteTradRev.setIdIdiomaDescEs(rs.getString(DBConstants.DESCIDIOMAES));
		expedienteTradRev.setIdIdiomaDescEu(rs.getString(DBConstants.DESCIDIOMAEU));
		expTarea.setExpedienteTradRev(expedienteTradRev);

		ExpedienteInterpretacion expedienteInterpretacion = new ExpedienteInterpretacion();
		expedienteInterpretacion.setModoInterpretacion(rs.getLong("MODOINTERPRETACION"));
		expedienteInterpretacion.setModoInterpretacionDescEs(rs.getString("MODOINTERPRETACIONDESCES"));
		expedienteInterpretacion.setModoInterpretacionDescEu(rs.getString("MODOINTERPRETACIONDESCEU"));
		expTarea.setExpedienteInterpretacion(expedienteInterpretacion);

		EjecucionTareas ejecucionTareas = new EjecucionTareas();
		ejecucionTareas.setFechaEjecucion(rs.getDate(DBConstants.FECHAEJECUCION));
		ejecucionTareas.setHoraEjecucion(rs.getString("HORAEJECUCION"));
		tareas.setEjecucionTareas(ejecucionTareas);

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

		Lotes lotes = new Lotes();
		lotes.setIdLote(rs.getInt(DBConstants.IDLOTE));
		lotes.setNombreLote(rs.getString(DBConstants.NOMBRELOTE));
		lotes.setFechaInicio(rs.getDate(DBConstants.FECHAINICIO));
		lotes.setFechaFin(rs.getDate(DBConstants.FECHAFIN));
		lotes.setImportePalabra(rs.getBigDecimal(DBConstants.IMPORTEPALABRA));
		lotes.setPorPagoPalConcor8594(rs.getLong(DBConstants.PORPAGOPALCONCOR8594));
		lotes.setPorPagoPalConcor95100(rs.getLong(DBConstants.PORPAGOPALCONCOR95100));
		lotes.setPorRevision(rs.getLong(DBConstants.PORREVISION));
		lotes.setPorRecargoApremio(rs.getLong(DBConstants.PORRECARGOAPREMIO));
		lotes.setPorRecargoFormato(rs.getLong(DBConstants.PORRECARGOFORMATO));
		lotes.setPorPenalizacion(rs.getLong(DBConstants.PORPENALIZACION));
		lotes.setImporteAsignado(rs.getBigDecimal(DBConstants.IMPORTEASIGNADO));
		lotes.setImporteConsumido(rs.getBigDecimal(DBConstants.IMPORTECONSUMIDO));

		EmpresasProveedoras empresasProveedoras = new EmpresasProveedoras();
		empresasProveedoras.setCif(rs.getString(DBConstants.CIF));
		empresasProveedoras.setDescEs(rs.getString(DBConstants.DESCES));
		empresasProveedoras.setDescEu(rs.getString(DBConstants.DESCEU));
		lotes.setEmpresasProveedoras(empresasProveedoras);
		tareas.setLotes(lotes);

		Tareas tareaRel = new Tareas();
		tareaRel.setIdTarea(rs.getBigDecimal(DBConstants.IDTAREAREL));
		TiposTarea tipoTareaRel = new TiposTarea();
		tipoTareaRel.setId015(rs.getLong(DBConstants.IDTIPOTAREAREL));
		tipoTareaRel.setDescEs015(rs.getString(DBConstants.TIPOTAREAESREL));
		tipoTareaRel.setDescEu015(rs.getString(DBConstants.TIPOTAREAEUREL));
		tareaRel.setTipoTarea(tipoTareaRel);
		tareas.setTarea(tareaRel);
		expTarea.setTarea(tareas);
		expTarea.setGrupoTrabajo(rs.getString("GRUPOTRABAJO"));

		return expTarea;
	}
}