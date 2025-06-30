/**
 *
 */
package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.DocumentoTareaAdjunto;
import com.ejie.aa79b.model.ObservacionesTareas;
import com.ejie.aa79b.model.OtrosTrabajos;
import com.ejie.aa79b.model.PersonalIZO;
import com.ejie.aa79b.model.TareasTrabajo;
import com.ejie.aa79b.model.TiposTarea;

/**
 * @author eaguirresarobe
 *
 */
public class DatosTareaTrabajoRowMapper implements RowMapper<TareasTrabajo> {

	@Override
	public TareasTrabajo mapRow(ResultSet rs, int arg1) throws SQLException {
		TareasTrabajo tareaTrabajo = new TareasTrabajo();
		tareaTrabajo.setIdTarea(rs.getBigDecimal("IDTAREA"));
		OtrosTrabajos trabajo = new OtrosTrabajos();
		trabajo.setIdTrabajo(rs.getBigDecimal("IDTRABAJO"));
		trabajo.setDescTrabajo(rs.getString("DESCTRABAJO"));
		trabajo.setFechaFinPrevista(rs.getDate("FECHAFINPREVISTA"));
		trabajo.setHoraFinPrevista(rs.getString("HORAFINPREVISTA"));
		tareaTrabajo.setTrabajo(trabajo);
		TiposTarea tipoTarea = new TiposTarea();
		tipoTarea.setId015(rs.getLong("IDTIPOTAREA"));
		tipoTarea.setDescEu015(rs.getString("DESCTIPOTAREAEU"));
		tipoTarea.setDescEs015(rs.getString("DESCTIPOTAREAES"));
		tareaTrabajo.setTipoTarea(tipoTarea);
		tareaTrabajo.setFechaInicio(rs.getDate("FECHAINICIO"));
		tareaTrabajo.setHoraInicio(rs.getString("HORAINICIO"));
		tareaTrabajo.setFechaFin(rs.getDate("FECHAFIN"));
		tareaTrabajo.setHoraFin(rs.getString("HORAFIN"));
		PersonalIZO personaAsignada = new PersonalIZO();
		personaAsignada.setDni(rs.getString("DNIRECURSO"));
		personaAsignada.setNombre(rs.getString("NOMBRERECURSO"));
		personaAsignada.setApellido1(rs.getString("APELLIDO1RECURSO"));
		personaAsignada.setApellido2(rs.getString("APELLIDO2RECURSO"));
		tareaTrabajo.setPersonaAsignada(personaAsignada);

		tareaTrabajo.setEstadoAsignado(rs.getInt("ESTADOASIGID"));
		tareaTrabajo.setEstadoAsignadoDesc(rs.getString("ESTADOASIGNACIONDESC"));

		tareaTrabajo.setEstadoEjecucion(rs.getInt("ESTADOEJECID"));
		tareaTrabajo.setEstadoEjecucionDesc(rs.getString("ESTADOEJECUCIONDESC"));
		tareaTrabajo.setOrden(rs.getInt("ORDEN"));


		tareaTrabajo.setFechaAsignacion(rs.getDate("FECHAASIGNACION"));
		tareaTrabajo.setHoraAsignacion(rs.getString("HORAASIGNACION"));

		tareaTrabajo.setFechaAceptacion(rs.getDate("FECHAACEPTACION"));
		tareaTrabajo.setHoraAceptacion(rs.getString("HORAACEPTACION"));

		tareaTrabajo.setObservaciones(rs.getString("OBSERV"));
		PersonalIZO personaAsignador = new PersonalIZO();
		personaAsignador.setDni(rs.getString("DNIASIGNADOR"));
		personaAsignador.setNombre(rs.getString("NOMBREASIGNADOR"));
		personaAsignador.setApellido1(rs.getString("APELLIDO1ASIGNADOR"));
		personaAsignador.setApellido2(rs.getString("APELLIDO2ASIGNADOR"));
		tareaTrabajo.setPersonaAsignador(personaAsignador);
		tareaTrabajo.setRecursoDisponible(rs.getString(DBConstants.ESRECURSODISPONIBLE));
		tareaTrabajo.setTieneOtrasTareas(rs.getString("TIENEOTRASTAREAS"));
		// datos del documento de entrada
		DocumentoTareaAdjunto documentoEntrada = new DocumentoTareaAdjunto();
		documentoEntrada.setIdFichero(rs.getBigDecimal("IDFICHERO"));
		documentoEntrada.setTitulo(rs.getString("TITULOFICHERO"));
		documentoEntrada.setExtension(rs.getString("EXTENSIONFICHERO"));
		documentoEntrada.setContentType(rs.getString("CONTENTTYPEFICHERO"));
		documentoEntrada.setTamano(rs.getLong("TAMANOFICHERO"));
		documentoEntrada.setEncriptado(rs.getString("INDENCRIPTADO"));
		documentoEntrada.setRutaPif(rs.getString("RUTAPIF"));
		documentoEntrada.setOid(rs.getString("OIDFICHERO"));
		documentoEntrada.setNombre(rs.getString("NOMBREFICHERO"));
		tareaTrabajo.setDocumentoEntrada(documentoEntrada);
		tareaTrabajo.setObsvrechazo(rs.getString("OBSERVRECHAZO"));
		ObservacionesTareas observacionesEjecTarea = new ObservacionesTareas();
		observacionesEjecTarea.setObsvEjecucion(rs.getString("OBSERVJECUCION"));
		tareaTrabajo.setObservacionesTareas(observacionesEjecTarea);
		// datos del documento de salida
		DocumentoTareaAdjunto documentoSalida = new DocumentoTareaAdjunto();
		documentoSalida.setIdFichero(rs.getBigDecimal("IDFICHEROSALIDA"));
		documentoSalida.setTitulo(rs.getString("TITULOFICHEROSALIDA"));
		documentoSalida.setExtension(rs.getString("EXTENSIONFICHEROSALIDA"));
		documentoSalida.setContentType(rs.getString("CONTENTTYPEFICHEROSALIDA"));
		documentoSalida.setTamano(rs.getLong("TAMANOFICHEROSALIDA"));
		documentoSalida.setEncriptado(rs.getString("INDENCRIPTADOSALIDA"));
		documentoSalida.setRutaPif(rs.getString("RUTAPIFSALIDA"));
		documentoSalida.setOid(rs.getString("OIDFICHEROSALIDA"));
		documentoSalida.setNombre(rs.getString("NOMBREFICHEROSALIDA"));
		tareaTrabajo.setDocumentoSalida(documentoSalida);
		return tareaTrabajo;
	}

}
