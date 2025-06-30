package com.ejie.aa79b.utils;

import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.dao.AusenciasCalendarioPersonalDao;
import com.ejie.aa79b.dao.ExpedienteDao;
import com.ejie.aa79b.dao.TareasDao;
import com.ejie.aa79b.dao.TiposTareaDao;
import com.ejie.aa79b.model.AusenciasCalendarioPersonal;
import com.ejie.aa79b.model.BitacoraExpediente;
import com.ejie.aa79b.model.DatosContacto;
import com.ejie.aa79b.model.Persona;
import com.ejie.aa79b.model.Tareas;
import com.ejie.aa79b.model.TareasTrabajo;
import com.ejie.aa79b.model.TiposTarea;
import com.ejie.aa79b.model.enums.EstadoAceptacionTareaEnum;
import com.ejie.aa79b.model.enums.EstadoEjecucionTareaEnum;
import com.ejie.aa79b.model.enums.EstadoExpedienteEnum;
import com.ejie.aa79b.model.enums.FaseExpedienteEnum;
import com.ejie.aa79b.model.enums.TipoAvisoEnum;
import com.ejie.aa79b.model.enums.TipoJornadaEnum;
import com.ejie.aa79b.model.enums.TipoRecursoEnum;
import com.ejie.aa79b.model.enums.TipoTareaGestionAsociadaEnum;

public class TareasServiceUtils extends SpringBeanAutowiringSupport {

	private static final Logger LOGGER = LoggerFactory.getLogger(TareasServiceUtils.class);

	@Autowired()
	private TareasDao tareasDao;
	@Autowired()
	private ExpedienteDao expedienteDao;
	@Autowired()
	private AusenciasCalendarioPersonalDao ausenciasCalendarioPersonalDao;
	@Autowired()
	private TiposTareaDao tiposTareaDao;

	public TareasServiceUtils() {
		// Constructor
	}

	/**
	 *
	 * @param listTareasCopy List<Tareas>
	 * @param tareas         Tareas
	 * @param rst            int
	 * @return int
	 */
	public static int compararTareaConListTareas(List<Tareas> listTareasCopy, Tareas tareas, int rst) {
		int rstValue = rst;
		for (Tareas tareasCopy : listTareasCopy) {

			if (isNotMismaTarea(tareas, tareasCopy)) {

				if (isTareaRelacionada(tareas, tareasCopy)) {
					rstValue = compararTareasRelacionadas(tareas, rstValue, tareasCopy);
				} else if (isNotValidTareaRevTradNoConfProvInt(tareas, tareasCopy)) {
					rstValue = 1;
				} else if (isNotValidTareaEntregaClteNotifProv(tareas, tareasCopy)) {
					rstValue = 1;
				} else if (isNotValidTareaEntregaClteRevisionPago(tareas, tareasCopy, listTareasCopy)) {
					rstValue = 1;
				} else if (esUltimaTareaGestionaAsociada(tareas.getTipoTarea())
						&& Constants.SI.equals(tareasCopy.getTipoTarea().getIndReqCierre015())
						&& tareas.getOrden() <= tareasCopy.getOrden()) {
					// En caso de que el orden no sea correcto
					rstValue = 1;
				}

			}

		}
		return rstValue;
	}

	/**
	 * @param tareas
	 * @param tareasCopy
	 * @return
	 */
	private static boolean isTareaRelacionada(Tareas tareas, Tareas tareasCopy) {
		return isNotNullIdTareaRel(tareas) && tareas.getIdTareaRel().compareTo(tareasCopy.getIdTarea()) == 0;
	}

	/**
	 * @param tareas
	 * @param tareasCopy
	 * @return
	 */
	private static boolean isNotMismaTarea(Tareas tareas, Tareas tareasCopy) {
		return tareas.getIdTarea().compareTo(tareasCopy.getIdTarea()) != 0;
	}

	/**
	 * @param tareas
	 * @param tareasCopy
	 * @return
	 */
	private static boolean isNotValidTareaRevTradNoConfProvInt(Tareas tareas, Tareas tareasCopy) {
		return isMismoIdTareaRel(tareas, tareasCopy) && isTareaNoConformidadProvInt(tareas)
				&& isTareaRevTrad(tareasCopy) && tareas.getOrden() >= tareasCopy.getOrden();
	}

	/**
	 * @param tareas
	 * @param tareasCopy
	 * @return boolean
	 */
	private static boolean isValidTareaEntregaClteNotifProv(Tareas tareas, Tareas tareasCopy) {
		return isTareaEntregaClteRev(tareas) && isTareaNotifCorreccionesProv(tareasCopy)
				&& tareas.getIdTarea().compareTo(tareasCopy.getIdTareaRel()) == 0
				&& tareas.getOrden() == tareasCopy.getOrden();
	}

	/**
	 * @param tareas
	 * @param tareasCopy
	 * @return boolean
	 */
	private static boolean isNotValidTareaEntregaClteNotifProv(Tareas tareas, Tareas tareasCopy) {
		return isTareaEntregaClteRev(tareas) && isTareaNotifCorreccionesProv(tareasCopy)
				&& tareas.getIdTarea().compareTo(tareasCopy.getIdTareaRel()) == 0
				&& tareas.getOrden() < tareasCopy.getOrden();
	}

	/**
	 * @param tareas
	 * @param tareasCopy
	 * @return boolean
	 */
	private static boolean isNotValidTareaEntregaClteRevisionPago(Tareas tareas, Tareas tareasCopy,
			List<Tareas> listTareasCopy) {
		if (isTareaRevisionDatosPago(tareas) && esUltimaTareaGestionaAsociada(tareasCopy.getTipoTarea())) {
			// coprobamos si la tarea de entrega es la que esta despues de la que ha creado
			// la de revisión de datos
			boolean tareaRelEncontrada = false;
			for (Tareas tareaABuscar : listTareasCopy) {
				if (tareaABuscar.getIdTarea().equals(tareas.getIdTareaRel())) {
					tareaRelEncontrada = true;
				}
				if ((TipoTareaGestionAsociadaEnum.ENTREGA_CLIENTE_TRADUCCION.getValue() == tareaABuscar.getTipoTarea()
						.getId015()
						|| TipoTareaGestionAsociadaEnum.TRAD_ENTREGA_CLIENTE_TRADUCCION.getValue() == tareaABuscar
								.getTipoTarea().getId015()
						|| TipoTareaGestionAsociadaEnum.ENTREGA_CLIENTE_REVISION.getValue() == tareaABuscar
								.getTipoTarea().getId015()
						|| TipoTareaGestionAsociadaEnum.CORREDACTAR.getValue() == tareaABuscar.getTipoTarea()
								.getId015())
						&& tareaRelEncontrada) {
					if (tareaABuscar.getIdTarea().equals(tareasCopy.getIdTarea())) {
						return tareaABuscar.getOrden() >= tareas.getOrden();
					}
				}
			}
			return false;
		}

		return isTareaRevisionDatosPago(tareas) && esUltimaTareaGestionaAsociada(tareasCopy.getTipoTarea())
				&& tareas.getIdTarea().compareTo(tareasCopy.getIdTareaRel()) == 0
				&& tareas.getOrden() < tareasCopy.getOrden();
	}

	/**
	 * @param tareas
	 * @param rstValue
	 * @param tareasCopy
	 * @return
	 */
	private static int compararTareasRelacionadas(Tareas tareas, int rst, Tareas tareasCopy) {
		int rstValue = rst;
		if (isTareaNoConformidadProvInt(tareas) && tareas.getOrden() < tareasCopy.getOrden()) {
			rstValue = 1;
		} else if (isNotTareaNoConformidadProvInt(tareas) && tareas.getOrden() <= tareasCopy.getOrden()) {
			rstValue = 1;
		}
		return rstValue;
	}

	/**
	 * @param tareas
	 * @param tareasCopy
	 * @return boolean
	 */
	public static boolean isMismoIdTareaRel(Tareas tareas, Tareas tareasCopy) {
		return isNotNullIdTareaRel(tareas) && isNotNullIdTareaRel(tareasCopy)
				&& tareas.getIdTareaRel().compareTo(tareasCopy.getIdTareaRel()) == 0;
	}

	/**
	 *
	 * @param tiposTarea TiposTarea
	 * @return boolean
	 */
	public static boolean esUltimaTareaGestionaAsociada(TiposTarea tiposTarea) {
		return TipoTareaGestionAsociadaEnum.ENTREGA_CLIENTE_TRADUCCION.getValue() == tiposTarea.getId015()
				|| TipoTareaGestionAsociadaEnum.TRAD_ENTREGA_CLIENTE_TRADUCCION.getValue() == tiposTarea.getId015()
				|| TipoTareaGestionAsociadaEnum.ENTREGA_CLIENTE_REVISION.getValue() == tiposTarea.getId015()
				|| TipoTareaGestionAsociadaEnum.CORREDACTAR.getValue() == tiposTarea.getId015();
	}

	/**
	 *
	 * @param tiposTarea TiposTarea
	 * @return boolean
	 */
	public static boolean noEsUltimaTareaGestionAsociada(TiposTarea tiposTarea) {
		return TipoTareaGestionAsociadaEnum.PROYECTO_TRADOS.getValue() != tiposTarea.getId015()
				&& TipoTareaGestionAsociadaEnum.GESTION_INTERPRETACION.getValue() != tiposTarea.getId015()
				&& TipoTareaGestionAsociadaEnum.ESTUDIAR_SUBSANACION.getValue() != tiposTarea.getId015();
	}

	/**
	 *
	 * @param locale     Locale
	 * @param tiposTarea TiposTarea
	 * @return String
	 */
	public static String obtenerDescripcionTipoTareaBDAux(Locale locale, TiposTarea tiposTarea) {
		String descTipoDeTarea = StringUtils.EMPTY;
		if (tiposTarea != null) {
			if (Constants.LANG_EUSKERA.equals(locale.getLanguage())) {
				descTipoDeTarea = tiposTarea.getDescEu015();
			} else {
				descTipoDeTarea = tiposTarea.getDescEs015();
			}
		}
		return descTipoDeTarea;
	}

	/**
	 *
	 * @param tareas Tareas
	 * @return int
	 */
	public static int obtenerTipoAvisoEliminacionTareas(Tareas tareas) {
		int tipoAviso;
		if (TipoRecursoEnum.EXTERNO.getValue().equals(tareas.getRecursoAsignacion())) {
			tipoAviso = TipoAvisoEnum.ELIMINAR_TAREAS_PROVEEDOR.getValue();
		} else {
			tipoAviso = TipoAvisoEnum.ELIMINAR_TAREAS.getValue();
		}
		return tipoAviso;
	}

	/**
	 *
	 * @param tareas Tareas
	 * @return int
	 */
	public static int obtenerTipoAvisoAsignacionTareas(Tareas tareas) {
		int tipoAviso;
		if (TipoRecursoEnum.INTERNO.getValue().equals(tareas.getRecursoAsignacion())) {
			tipoAviso = TipoAvisoEnum.ASIGNAR_TAREA.getValue();
		} else {
			tipoAviso = TipoAvisoEnum.ASIGNAR_TAREA_PROVEEDOR.getValue();
		}
		return tipoAviso;
	}

	/**
	 *
	 * @param tareas Tareas
	 * @return int
	 */
	public static int obtenerTipoAvisoModificacionDocTarea(Tareas tareas) {
		int tipoAviso;
		if (TipoRecursoEnum.EXTERNO.getValue().equals(tareas.getRecursoAsignacion())) {
			tipoAviso = TipoAvisoEnum.MODIFICAR_DATOS_TAREA_EXTERNA.getValue();
		} else {
			tipoAviso = TipoAvisoEnum.MODIFICAR_DATOS_TAREA.getValue();
		}
		return tipoAviso;
	}

	/**
	 *
	 * @param tareas Tareas
	 * @return int
	 */
	public static int obtenerTipoAvisoReaperturaTarea(Tareas tareas) {
		int tipoAviso;
		if (TipoRecursoEnum.INTERNO.getValue().equals(tareas.getRecursoAsignacion())) {
			tipoAviso = TipoAvisoEnum.REAPERTURA_TAREA.getValue();
		} else {
			tipoAviso = TipoAvisoEnum.REAPERTURA_TAREA_EXTERNA.getValue();
		}
		return tipoAviso;
	}

	/**
	 *
	 * @param locale Locale
	 * @param tareas Tareas
	 * @return String
	 */
	public static String obtenerDescripcionTipoTarea(Locale locale, Tareas tareas) {
		String descTipoTarea;
		if (Constants.LANG_EUSKERA.equals(locale.getLanguage())) {
			descTipoTarea = tareas.getTipoTarea().getDescEu015();
		} else {
			descTipoTarea = tareas.getTipoTarea().getDescEs015();
		}

		return descTipoTarea;
	}

	/**
	 *
	 * @param bitacora BitacoraExpediente
	 * @return boolean
	 */
	public static boolean isExpEnCursoPdteProyTrados(BitacoraExpediente bitacora) {
		return isEstadoFaseExpNoNulo(bitacora)
				&& EstadoExpedienteEnum.EN_CURSO.getValue() == bitacora.getEstadoExp().getId()
				&& FaseExpedienteEnum.PDTE_PROYECTO_TRADOS.getValue() == bitacora.getFaseExp().getId();
	}

	/**
	 *
	 * @param bitacora BitacoraExpediente
	 * @return boolean
	 */
	public static boolean isExpEnCursoPdteTramClte(BitacoraExpediente bitacora) {
		return isEstadoFaseExpNoNulo(bitacora)
				&& EstadoExpedienteEnum.EN_CURSO.getValue() == bitacora.getEstadoExp().getId().intValue()
				&& FaseExpedienteEnum.PDTE_TRAM_CLIENTE.getValue() == bitacora.getFaseExp().getId().intValue();
	}

	/**
	 *
	 * @param bitacora BitacoraExpediente
	 * @return boolean
	 */
	public static boolean isEstadoFaseExpNoNulo(BitacoraExpediente bitacora) {
		return isEstadoExpNoNulo(bitacora) && bitacora.getFaseExp() != null;
	}

	/**
	 * @param bitacora
	 * @return
	 */
	private static boolean isEstadoExpNoNulo(BitacoraExpediente bitacora) {
		return bitacora != null && bitacora.getEstadoExp() != null;
	}

	/**
	 *
	 * @param tarea    Tareas
	 * @param original Tareas
	 * @return boolean
	 */
	public static boolean isCambioRecursoAsignacion(Tareas tarea, Tareas original) {
		return !tarea.getRecursoAsignacion().equals(original.getRecursoAsignacion())
				|| !tarea.getPersonaAsignada().getDni().equals(
						original.getPersonaAsignada().getDni() != null ? original.getPersonaAsignada().getDni() : "")
				|| tarea.getLotes().getIdLote() != original.getLotes().getIdLote();
	}

	/**
	 *
	 * @param tarea    Tareas
	 * @param original Tareas
	 * @return boolean
	 */
	public static boolean isCambioIndFacturable(Tareas tarea, Tareas original) {
		return !tarea.getIndFacturacion().equals(original.getIndFacturacion());
	}

	/**
	 *
	 * @param tarea    Tareas
	 * @param original Tareas
	 * @return boolean
	 */
	public static boolean isCambioRecursoAsignacionIndFact(Tareas tarea, Tareas original) {
		return (isCambioRecursoAsignacion(tarea, original)
				&& tarea.getRecursoAsignacion().equals(TipoRecursoEnum.INTERNO.getValue()))
				|| (isCambioIndFacturable(tarea, original) && tarea.getIndFacturacion().equals(Constants.NO));
	}

	/**
	 *
	 * @param tarea    Tareas
	 * @param original Tareas
	 * @return boolean
	 */
	public static boolean comprobarRecursoAsignacionEIndFact(Tareas tarea, Tareas original) {
		return isRecursoAsignacionTareaNulo(tarea, original) || isRecursoAsignacionOriginalNulo(tarea, original)
				|| (isRecursosAsignacionDistintos(tarea, original) || isIndFacturacionDistintos(tarea, original));
	}

	/**
	 *
	 * @param tarea    Tareas
	 * @param original Tareas
	 * @return boolean
	 */
	public static boolean comprobarRecursoAsignacion(Tareas tarea, Tareas original) {
		return isRecursoAsignacionTareaNulo(tarea, original) || isRecursoAsignacionOriginalNulo(tarea, original)
				|| (isRecursosAsignacionDistintos(tarea, original));
	}

	/**
	 *
	 * @param tarea    Tareas
	 * @param original Tareas
	 * @return boolean
	 */
	public static boolean isRecursoAsignacionTareaNulo(Tareas tarea, Tareas original) {
		return original.getRecursoAsignacion() != null && tarea.getRecursoAsignacion() == null;
	}

	/**
	 *
	 * @param tarea    Tareas
	 * @param original Tareas
	 * @return boolean
	 */
	public static boolean isRecursoAsignacionOriginalNulo(Tareas tarea, Tareas original) {
		return original.getRecursoAsignacion() == null && tarea.getRecursoAsignacion() != null;
	}

	/**
	 *
	 * @param tarea    Tareas
	 * @param original Tareas
	 * @return boolean
	 */
	public static boolean isRecursosAsignacionDistintos(Tareas tarea, Tareas original) {
		return original.getRecursoAsignacion() != null && tarea.getRecursoAsignacion() != null
				&& !original.getRecursoAsignacion().equals(tarea.getRecursoAsignacion());
	}

	/**
	 *
	 * @param tarea    Tareas
	 * @param original Tareas
	 * @return boolean
	 */
	public static boolean isIndFacturacionDistintos(Tareas tarea, Tareas original) {
		return !original.getIndFacturacion().equals(tarea.getIndFacturacion());
	}

	/**
	 * @param tarea
	 * @param original
	 * @return boolean
	 */
	public static boolean isRecursoLote(Tareas tarea, Tareas original) {
		return tarea.getRecursoAsignacion().equals(original.getRecursoAsignacion())
				&& original.getLotes().getIdLote() != 0 && tarea.getLotes().getIdLote() != null
				&& !original.getLotes().getIdLote().equals(tarea.getLotes().getIdLote());
	}

	/**
	 * @param tarea
	 * @param original
	 * @return boolean
	 */
	public static boolean isRecursoPersona(Tareas tarea, Tareas original) {
		return tarea.getRecursoAsignacion().equals(original.getRecursoAsignacion())
				&& StringUtils.isNotEmpty(tarea.getPersonaAsignada().getDni())
				&& original.getPersonaAsignada().getDni() != null
				&& !tarea.getPersonaAsignada().getDni().equals(original.getPersonaAsignada().getDni());
	}

	/**
	 * @param tarea
	 * @param original
	 * @return boolean
	 */
	public static boolean isRecursoAsignado(Tareas tarea, Tareas original) {
		return isRecursoAsignacion(tarea) && !tarea.getRecursoAsignacion().equals(original.getRecursoAsignacion());
	}

	/**
	 * @param tarea
	 * @return boolean
	 */
	public static boolean isNotTareaNula(Tareas tarea) {
		return isTareaNoNula(tarea) && tarea.getIdTarea() != null;
	}

	/**
	 * @param tarea
	 * @return boolean
	 */
	public static boolean isIdTareaNulo(Tareas tarea) {
		return tarea.getIdTarea() == null;
	}

	/**
	 * @param tarea
	 * @return
	 */
	public static boolean isTareaInterpretacionInterna(Tareas tarea) {
		return isNotNullTipoTarea(tarea) && isTareaInterpretacion(tarea) && isRecursoInterno(tarea);
	}

	public static boolean isTareaInterpretacion(Tareas tarea) {
		return TipoTareaGestionAsociadaEnum.INTERPRETAR.getValue() == tarea.getTipoTarea().getId015();
	}

	/**
	 * @param listTareas
	 * @return
	 */
	public static boolean isNotEmptyListTareas(List<Tareas> listTareas) {
		return listTareas != null && !listTareas.isEmpty();
	}
	/**
	 * @param listTareas
	 * @return
	 */
	public static boolean isNotEmptyListTareasTrabajo(List<TareasTrabajo> listTareas) {
		return listTareas != null && !listTareas.isEmpty();
	}

	/**
	 * @param listStr
	 * @return
	 */
	public static boolean isNotEmptyListStr(List<String> listStr) {
		return listStr != null && !listStr.isEmpty();
	}

	/**
	 * @param tarea
	 * @return
	 */
	public static boolean isNotLoteNulo(Tareas tarea) {
		return isLoteTareaNoNulo(tarea) && tarea.getLotes().getIdLote() != null;
	}

	/**
	 * @param tarea
	 * @return
	 */
	public static boolean isRecursoExterno(Tareas tarea) {
		return isTipoRecursoExt(tarea) && isNotLoteNulo(tarea);
	}

	/**
	 * @param tarea
	 * @return
	 */
	public static boolean isTareaNoNula(Tareas tarea) {
		return tarea != null;
	}

	/**
	 * @param listaDestinatarios
	 * @return
	 */
	public static boolean isNotEmptyLstDestinatarios(List<String> listaDestinatarios) {
		return !listaDestinatarios.isEmpty() && listaDestinatarios.get(0) != null;
	}

	/**
	 * @param tareas
	 * @return
	 */
	public static boolean isRecursoAsignado(Tareas tareas) {
		return tareas.getPersonaAsignada() != null && StringUtils.isNotBlank(tareas.getPersonaAsignada().getDni());
	}

	/**
	 * @param datosContacto
	 * @return
	 */
	public static boolean isNotNullDatosContacto(DatosContacto datosContacto) {
		return datosContacto != null;
	}

	/**
	 * @param tarea
	 * @return
	 */
	public static boolean isNotNullTipoTarea(Tareas tarea) {
		return tarea.getTipoTarea() != null;
	}

	/**
	 * @param tarea
	 * @return
	 */
	public static boolean isTareaNotificarCorreccionesProv(Tareas tarea) {
		return isNotNullTipoTarea(tarea) && TipoTareaGestionAsociadaEnum.NOTIFICAR_CORRECCIONES_PROVEEDOR
				.getValue() == tarea.getTipoTarea().getId015();
	}

	/**
	 * @param tarea
	 * @return
	 */
	public static boolean isRecursoInterno(Tareas tarea) {
		return TipoRecursoEnum.INTERNO.getValue().equals(tarea.getRecursoAsignacion());
	}

	/**
	 * @param tarea
	 * @return
	 */
	public static boolean isTipoRecursoExt(Tareas tarea) {
		return TipoRecursoEnum.EXTERNO.getValue().equals(tarea.getRecursoAsignacion());
	}

	/**
	 * @param tarea
	 * @return
	 */
	public static boolean isTareaTraduccion(Tareas tarea) {
		return TipoTareaGestionAsociadaEnum.TRADUCIR.getValue() == tarea.getTipoTarea().getId015();
	}

	/**
	 * @param tarea
	 * @return
	 */
	public static boolean isTareaRevision(Tareas tarea) {
		return TipoTareaGestionAsociadaEnum.REVISAR.getValue() == tarea.getTipoTarea().getId015();
	}

	/**
	 * @param tarea
	 * @return
	 */
	public static boolean isTareaTradRev(Tareas tarea) {
		return TareasServiceUtils.isTareaTraduccion(tarea) || TareasServiceUtils.isTareaRevision(tarea);
	}

	/**
	 * @param tarea
	 * @return
	 */
	public static boolean isTareaEstudiarSubDoc(Tareas tarea) {
		return TipoTareaGestionAsociadaEnum.ESTUDIAR_SUBSANACION.getValue() == tarea.getTipoTarea().getId015();
	}

	/**
	 * @param tarea
	 * @return boolean
	 */
	public static boolean isTareaCrearProyTrados(Tareas tarea) {
		return TipoTareaGestionAsociadaEnum.PROYECTO_TRADOS.getValue() == tarea.getTipoTarea().getId015();
	}

	/**
	 * @param tarea
	 * @return boolean
	 */
	public static boolean isRecursoAsignacion(Tareas tarea) {
		return StringUtils.isNotEmpty(tarea.getRecursoAsignacion());
	}

	/**
	 * @param tarea
	 * @return boolean
	 */
	public static boolean isNotRecursoAsignacion(Tareas tarea) {
		return StringUtils.isEmpty(tarea.getRecursoAsignacion());
	}

	/**
	 * @param tarea
	 * @return boolean
	 */
	public static boolean isTareaSinEjecutar(Tareas tarea) {
		return EstadoEjecucionTareaEnum.EJECUTADA.getValue() != tarea.getEstadoEjecucion();
	}

	/**
	 * @param tarea
	 * @return boolean
	 */
	public static boolean isTareaReqCierre(Tareas tarea) {
		return Constants.SI.equals(tarea.getTipoTarea().getIndReqCierre015());
	}

	/**
	 * @param tarea
	 * @return boolean
	 */
	public static boolean isTareaReordenable(Tareas tarea) {
		return TareasServiceUtils.isTareaSinEjecutar(tarea)
				&& TareasServiceUtils.noEsUltimaTareaGestionAsociada(tarea.getTipoTarea());
	}

	/**
	 * @param persona
	 * @return boolean
	 */
	public static boolean isDniNotNull(Persona persona) {
		return persona != null && persona.getDni() != null;
	}

	/**
	 * @param listaDestinatarios
	 * @param listTareas
	 * @return boolean
	 */
	public static boolean isLstDestinatariosAndTareasNotEmpty(List<String> listaDestinatarios,
			List<Tareas> listTareas) {
		return TareasServiceUtils.isNotEmptyLstDestinatarios(listaDestinatarios)
				&& TareasServiceUtils.isNotEmptyListTareas(listTareas);
	}

	/**
	 * @param tarea
	 * @param original
	 * @return boolean
	 */
	public static boolean isCambioFechasTarea(Tareas tarea, Tareas original) {
		return !tarea.getFechaHoraIni().equals(original.getFechaHoraIni())
				|| !tarea.getFechaHoraFin().equals(original.getFechaHoraFin());
	}

	/**
	 * @param documentosSelect
	 * @param idDocs
	 * @param tarea
	 * @param original
	 * @return boolean
	 */
	public static boolean isCambioDatosTarea(String documentosSelect, String idDocs, Tareas tarea, Tareas original) {
		return !documentosSelect.equals(idDocs) || TareasServiceUtils.isCambioFechasTarea(tarea, original);
	}

	/**
	 * @param numExpediente
	 * @return boolean
	 */
	public static boolean isNumExpNulo(Integer numExpediente) {
		return numExpediente == null;
	}

	/**
	 * @param anyo
	 * @param numExp
	 * @return boolean
	 */
	public static boolean isNotAnyoNumExpNulos(Long anyo, Integer numExp) {
		return anyo != null && numExp != null;
	}

	/**
	 * @param tarea
	 * @return boolean
	 */
	public static boolean isNotTipoTareaTrados(Tareas tarea) {
		return TipoTareaGestionAsociadaEnum.PROYECTO_TRADOS.getValue() != tarea.getTipoTarea().getId015();
	}

	/**
	 * @param bitacora
	 * @param tarea
	 * @return boolean
	 */
	public static boolean isExpPdteProyTradosAndTareaNotTrados(BitacoraExpediente bitacora, Tareas tarea) {
		return TareasServiceUtils.isExpEnCursoPdteProyTrados(bitacora)
				&& TareasServiceUtils.isNotTipoTareaTrados(tarea);
	}

	/**
	 * @param tarea
	 * @param nif
	 * @return boolean
	 */
	public static boolean isMismoDni(Tareas tarea, String nif) {
		return tarea.getPersonaAsignada() != null && isMismoDni(tarea.getPersonaAsignada().getDni(), nif);
	}

	/**
	 * @param tarea
	 * @return boolean
	 */
	public static boolean isTareaNoConformidadClte(Tareas tarea) {
		return TipoTareaGestionAsociadaEnum.NO_CONFORMIDAD_CLIENTE.getValue() == tarea.getTipoTarea().getId015();
	}

	/**
	 * @param tarea
	 * @param original
	 * @return boolean
	 */
	public static boolean comprobarRecursoAsignado(Tareas tarea, Tareas original) {
		return TareasServiceUtils.isRecursoAsignado(tarea, original)
				|| TareasServiceUtils.isRecursoPersona(tarea, original)
				|| TareasServiceUtils.isRecursoLote(tarea, original);
	}

	/**
	 * @param tarea
	 * @return boolean
	 */
	public static boolean isEstadoTareaAceptada(Tareas tarea) {
		return EstadoAceptacionTareaEnum.ACEPTADA.getValue() == tarea.getEstadoAsignado();
	}

	/**
	 * Reordena las tareas
	 *
	 * @param listTareas
	 * @return int
	 */
	public int reordenarTareas(List<Tareas> listTareas) {
		int rst = 0;
		if (TareasServiceUtils.isNotEmptyListTareas(listTareas)) {
			List<Tareas> listTareasCopy = listTareas;

			for (Tareas tareas : listTareas) {

				if (TareasServiceUtils.isTareaReordenable(tareas)) {
					rst = TareasServiceUtils.compararTareaConListTareas(listTareasCopy, tareas, rst);
				}
			}
			if (rst == 0) {
				this.tareasDao.reordenarTareas(listTareas);
			}
		}
		return rst;
	}

	/**
	 * Elimina las ausencias del calendario personal para las tareas indicadas
	 *
	 * @param listTareas
	 */
	public void eliminarAusenciaCalendario(List<Tareas> listTareas) {
		for (Tareas tarea : listTareas) {

			if (TareasServiceUtils.isTareaInterpretacion(tarea)) {
				this.ausenciasCalendarioPersonalDao.removeDatosTarea(tarea.getIdTarea());
				break;
			}

		}
	}

	/**
	 * Registra o actualiza la ausencia en el calendario personal en caso de que la
	 * tarea esté asociada a un recurso interno o se elimina el registro, en caso de
	 * que esté asociada a un recurso externo
	 *
	 * @param tarea Tareas
	 * @return int
	 */
	public int updateAusenciaCalendarioPersonal(Tareas tarea) {
		int rst = 0;
		if (TareasServiceUtils.isTipoTareaInterpretacion(tarea)) {
			if (TareasServiceUtils.isRecursoInterno(tarea)) {
				rst = gestionarAusenciaCalendario(tarea);
			} else {
				this.ausenciasCalendarioPersonalDao.removeDatosTarea(tarea.getIdTarea());
			}
		}
		return rst;
	}

	/**
	 * @param tarea
	 * @return int
	 */
	public int gestionarAusenciaCalendario(Tareas tarea) {
		int rst = 0;
		if (TareasServiceUtils.isEstadoTareaAceptada(tarea)) {
			AusenciasCalendarioPersonal ausenciasCalendarioPersonal = new AusenciasCalendarioPersonal();
			ausenciasCalendarioPersonal.setIdTarea(tarea.getIdTarea());
			ausenciasCalendarioPersonal.setFechaDesde(tarea.getFechaIni());
			ausenciasCalendarioPersonal.setFechaHasta(tarea.getFechaFin());
			ausenciasCalendarioPersonal.setAnyo(tarea.getAnyo());
			ausenciasCalendarioPersonal.setDni(tarea.getPersonaAsignada().getDni());
			ausenciasCalendarioPersonal.setTipoJornada(TipoJornadaEnum.INTERPRETACION.getValue());

			int numFilasActualizadas = this.ausenciasCalendarioPersonalDao
					.updateDatosTarea(ausenciasCalendarioPersonal);

			if (numFilasActualizadas == 0) {
				rst = addAusenciaCalendario(ausenciasCalendarioPersonal);
			}
		} else {
			this.ausenciasCalendarioPersonalDao.removeDatosTarea(tarea.getIdTarea());
		}
		return rst;
	}

	/**
	 * @param ausenciasCalendarioPersonal
	 * @return int
	 */
	public int addAusenciaCalendario(AusenciasCalendarioPersonal ausenciasCalendarioPersonal) {
		int rst = 0;
		try {
			this.ausenciasCalendarioPersonalDao.add(ausenciasCalendarioPersonal);
		} catch (Exception e) {
			TareasServiceUtils.LOGGER.info("Error al registrar datos en el calendario personal ", e);
			rst = -3;
		}
		return rst;
	}

	/**
	 * @param tarea
	 * @return boolean
	 */
	public static boolean isTipoTareaInterpretacion(Tareas tarea) {
		return TareasServiceUtils.isNotNullTipoTarea(tarea) && TareasServiceUtils.isTareaInterpretacion(tarea);
	}

	/**
	 * @param tarea
	 * @return boolean
	 */
	public static boolean isIdLoteNulo(Tareas tarea) {
		return isLoteTareaNoNulo(tarea) && tarea.getLotes().getIdLote() == null;
	}

	/**
	 * @param tarea
	 * @return boolean
	 */
	public static boolean isLoteTareaNoNulo(Tareas tarea) {
		return tarea.getLotes() != null;
	}

	/**
	 * @param documentosSelect
	 * @param idDocs
	 * @param tarea
	 * @param original
	 * @return boolean
	 */
	public static boolean isEnvioEmailCambioDatosTarea(String documentosSelect, String idDocs, Tareas tarea,
			Tareas original) {
		return !TareasServiceUtils.comprobarRecursoAsignado(tarea, original)
				&& TareasServiceUtils.isCambioDatosTarea(documentosSelect, idDocs, tarea, original);
	}

	/**
	 * @param tareas
	 * @return boolean
	 */
	public boolean isSubDocPdte(Tareas tareas) {
		return TareasServiceUtils.isTareaEstudiarSubDoc(tareas)
				&& this.expedienteDao.isEstadoSubDocPdte(tareas.getAnyo(), tareas.getNumExp());
	}

	/**
	 * @param tarea
	 * @param original
	 * @return boolean
	 */
	public static boolean isCambioRecursoAsigTareaTrad(Tareas tarea, Tareas original) {
		return TareasServiceUtils.comprobarRecursoAsignacion(tarea, original)
				&& TareasServiceUtils.isTareaTraduccion(tarea);
	}

	/**
	 * @param tarea
	 * @return boolean
	 */
	public static boolean isTareaNoConformidadProvInt(Tareas tarea) {
		return TipoTareaGestionAsociadaEnum.NO_CONFORMIDAD_PROVEEDOR.getValue() == tarea.getTipoTarea().getId015()
				|| TipoTareaGestionAsociadaEnum.NO_CONFORMIDAD_TRADUCTOR.getValue() == tarea.getTipoTarea().getId015();
	}

	/**
	 * @param tarea
	 * @return boolean
	 */
	public static boolean isNotTareaNoConformidadProvInt(Tareas tarea) {
		return TipoTareaGestionAsociadaEnum.NO_CONFORMIDAD_PROVEEDOR.getValue() != tarea.getTipoTarea().getId015()
				&& TipoTareaGestionAsociadaEnum.NO_CONFORMIDAD_TRADUCTOR.getValue() != tarea.getTipoTarea().getId015();
	}

	/**
	 * @param tarea
	 * @return boolean
	 */
	public static boolean isTareaRevTrad(Tareas tarea) {
		return TipoTareaGestionAsociadaEnum.REVISAR_TRADUCCION.getValue() == tarea.getTipoTarea().getId015()
				|| TipoTareaGestionAsociadaEnum.REVISAR_TRADUCCION_INTERNA.getValue() == tarea.getTipoTarea()
						.getId015();
	}

	/**
	 * @param tarea
	 * @return boolean
	 */
	public static boolean isNotNullIdTareaRel(Tareas tarea) {
		return tarea.getIdTareaRel() != null;
	}

	/**
	 * @param tarea
	 * @return boolean
	 */
	public static boolean isTareaEntregaClteRev(Tareas tarea) {
		return TipoTareaGestionAsociadaEnum.ENTREGA_CLIENTE_REVISION.getValue() == tarea.getTipoTarea().getId015();
	}

	/**
	 * @param tarea
	 * @return boolean
	 */
	public static boolean isTareaNotifCorreccionesProv(Tareas tarea) {
		return TipoTareaGestionAsociadaEnum.NOTIFICAR_CORRECCIONES_PROVEEDOR.getValue() == tarea.getTipoTarea()
				.getId015();
	}

	/**
	 * @param tarea
	 * @return boolean
	 */
	public static boolean isTareaRevisionDatosPago(Tareas tarea) {
		return TipoTareaGestionAsociadaEnum.REVISION_PAGO_PROVEEDOR.getValue() == tarea.getTipoTarea().getId015();
	}

	/**
	 * @param idLote
	 * @return boolean
	 */
	public static boolean isIdLoteNoNulo(Integer idLote) {
		return idLote != null;
	}

	/**
	 * Indica si existe una tarea de revisión externa
	 *
	 * @param tarea Tareas
	 * @return boolean
	 */
	public boolean existeTareaRevisionExterna(Tareas tarea) {
		boolean existeTareaRevExt = false;
		if (isTipoRecursoExt(tarea) && this.tareasDao.findCountTareasRevExt(tarea) > 0) {
			existeTareaRevExt = true;
		}
		return existeTareaRevExt;
	}

	public static boolean isDesasignacionRecurso(Tareas tarea, Tareas original) {
		return isNotRecursoAsignacion(tarea) && isRecursoAsignacion(original);
	}

	/**
	 *
	 * @param bitacora BitacoraExpediente
	 * @return boolean
	 */
	public static boolean isExpCerrado(BitacoraExpediente bitacora) {
		return isEstadoFaseExpNoNulo(bitacora)
				&& EstadoExpedienteEnum.CERRADO.getValue() == bitacora.getEstadoExp().getId()
				&& FaseExpedienteEnum.CERRADO.getValue() == bitacora.getFaseExp().getId();
	}

	/**
	 *
	 * @param bitacora BitacoraExpediente
	 * @return boolean
	 */
	public static boolean isExpEnCurso(BitacoraExpediente bitacora) {
		return isEstadoExpNoNulo(bitacora)
				&& EstadoExpedienteEnum.EN_CURSO.getValue() == bitacora.getEstadoExp().getId();
	}

	/**
	 * @param tareas
	 * @return
	 */
	public String obtenerDescripcionTipoTareaBD(Locale locale, Tareas tareas) {
		return this.obtenerDescripcionTipoTareaBD(locale, tareas.getTipoTarea());
	}

	/**
	 * @param tareas
	 * @return
	 */
	public String obtenerDescripcionTipoTareaBD(Locale locale, TiposTarea tipoTarea) {
		String descTipoTarea = StringUtils.EMPTY;
		TiposTarea tiposTarea = this.tiposTareaDao.find(tipoTarea);
		descTipoTarea = obtenerDescripcionTipoTareaBDAux(locale, tiposTarea);
		return descTipoTarea;
	}


	/**
	 *
	 * @param tarea TareasTrabajo
	 * @param nif String
	 * @return boolean
	 */
	public static boolean isMismoDni(TareasTrabajo tarea, String nif) {
		return tarea.getPersonaAsignada() != null && isMismoDni(tarea.getPersonaAsignada().getDni(), nif);
	}



	/**
	 *
	 * @param tareaOriginal
	 * @return
	 */
	public static boolean isRecursoAsignacion(TareasTrabajo tareaOriginal) {
			return StringUtils.isNotEmpty(tareaOriginal.getRecursoAsignacion());
	}

	/**
	 *
	 * @param locale Locale
	 * @param tareas TareasTrabajo
	 * @return Object
	 */
	public static String obtenerDescripcionTipoTarea(Locale locale, TareasTrabajo tiposTarea) {
		String descTipoDeTarea = StringUtils.EMPTY;
		if (tiposTarea != null && tiposTarea.getTipoTarea() != null) {
			if (Constants.LANG_EUSKERA.equals(locale.getLanguage())) {
				descTipoDeTarea = tiposTarea.getTipoTarea().getDescEu015();
			} else {
				descTipoDeTarea = tiposTarea.getTipoTarea().getDescEs015();
			}
		}
		return descTipoDeTarea;
	}

	public static boolean isMismoDni(String dni, String nif) {
		return nif.equals(dni);
	}

	public static boolean isLstDestinatariosAndTareasTrabajoNotEmpty(List<String> listaDestinatarios,
			List<TareasTrabajo> listTareas) {
		return TareasServiceUtils.isNotEmptyLstDestinatarios(listaDestinatarios)
				&& TareasServiceUtils.isNotEmptyListTareasTrabajo(listTareas);
	}



}
