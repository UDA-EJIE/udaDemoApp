package com.ejie.aa79b.utils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.ejie.aa79b.dao.TareasDao;
import com.ejie.aa79b.model.Tareas;
import com.ejie.aa79b.model.TareasTrabajo;
import com.ejie.aa79b.model.enums.EstadoAceptacionTareaEnum;
import com.ejie.aa79b.model.enums.EstadoEjecucionTareaEnum;
import com.ejie.aa79b.security.Usuario;

public class TareasUtils extends SpringBeanAutowiringSupport {

	@Autowired()
	private TareasDao tareasDao;

	public TareasUtils() {
		// Constructor
	}

	/**
	 * @param tarea
	 */
	public void asignarDatosAceptacion(Tareas tarea, Tareas original) {
		boolean cambioTipoRecurso = TareasServiceUtils.isCambioRecursoAsignacion(tarea, original);
		if (cambioTipoRecurso) {
			asignarDatosAceptacionPorRecurso(tarea);
			this.tareasDao.updateFechaAceptacion(tarea, cambioTipoRecurso);
			// Se eliminan las observaciones de rechazo para la tarea
			// correspondiente
			this.tareasDao.removeObsrvRechazo(tarea.getIdTarea());
		} else {
			tarea.setEstadoAsignado(original.getEstadoAsignado());
		}
	}

	/**
	 * @param tarea
	 */
	private static void asignarDatosAceptacionPorRecurso(Tareas tarea) {
		if (TareasServiceUtils.isTipoRecursoExt(tarea)) {
			asignarDatosAceptacion(tarea);
		} else if (TareasServiceUtils.isRecursoInterno(tarea)) {
			asignarDatosAceptacionRecInterno(tarea);
		} else {
			asignarDatosAceptacionSinRecAsignado(tarea);
		}
	}

	/**
	 * @param tarea
	 */
	public static void asignarDatosAceptacion(Tareas tarea) {
		tarea.setFechaAceptacion(new Date());
		tarea.setEstadoAsignado(EstadoAceptacionTareaEnum.ACEPTADA.getValue());
	}

	/**
	 * @param tarea
	 */
	public static void asignarDatosAceptacionRecInterno(Tareas tarea) {
		final Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication().getCredentials();

		if (TareasServiceUtils.isMismoDni(tarea, credentials.getNif())) {
			asignarDatosAceptacion(tarea);
		} else {
			tarea.setFechaAceptacion(null);
			tarea.setEstadoAsignado(EstadoAceptacionTareaEnum.PENDIENTE_ACEPTACION.getValue());
		}
	}

	/**
	 * @param tarea
	 */
	public static void asignarDatosAceptacionSinRecAsignado(Tareas tarea) {
		tarea.setFechaAceptacion(null);
		tarea.setEstadoAsignado(EstadoAceptacionTareaEnum.PENDIENTE_ASIGNACION.getValue());
	}

	/**
	 * @param tarea
	 */
	public static void asignarDatosTareaProveedor(Tareas tarea) {
		if (TareasServiceUtils.isTipoRecursoExt(tarea)) {
			asignarDatosAceptacion(tarea);

			if (TareasServiceUtils.isTareaNotificarCorreccionesProv(tarea)) {
				tarea.setEstadoEjecucion(EstadoEjecucionTareaEnum.EJECUTADA.getValue());
			}
		} else if (TareasServiceUtils.isRecursoInterno(tarea)) {
			asignarDatosAceptacionRecInterno(tarea);
		} else {
			asignarDatosAceptacionSinRecAsignado(tarea);
		}
	}

	/**
	 * Comprobar que no exista una tarea de no conformidad con el proveedor sin
	 * ejecutar para los documentos seleccionados
	 *
	 * @param tarea            Tareas
	 * @param documentosSelect String
	 * @return boolean
	 */
	public boolean isNotTareaNoConfProvSinEjec(Tareas tarea, String documentosSelect) {
		return this.tareasDao.countTareasNoConfProvSinEjec(tarea, documentosSelect) == 0;
	}

	/**
	 *
	 * @param bean             Tareas
	 * @param documentosSelect String
	 * @return boolean
	 */
	public boolean isNotTareaNoConfRevInternaSinEjec(Tareas tarea, String documentosSelect) {
		return this.tareasDao.countTareasNoConfRevInternaSinEjec(tarea, documentosSelect) == 0;
	}

	/**
	 *
	 * @param tarea TareasTrabajo
	 */
	public static void asignarDatosAceptacionRecInternoTareaTrabajo(TareasTrabajo tarea) {
		final Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication().getCredentials();

		if (TareasServiceUtils.isMismoDni(tarea, credentials.getNif())) {
			asignarDatosAceptacion(tarea);
		} else {
			tarea.setFechaAceptacion(null);
			tarea.setEstadoAsignado(EstadoAceptacionTareaEnum.PENDIENTE_ACEPTACION.getValue());
		}
	}

	private static void asignarDatosAceptacion(TareasTrabajo tarea) {
		tarea.setFechaAceptacion(new Date());
		tarea.setEstadoAsignado(EstadoAceptacionTareaEnum.ACEPTADA.getValue());
	}

}
