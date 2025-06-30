package com.ejie.aa79b.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.common.PropertiesConstants;
import com.ejie.aa79b.common.exceptions.AppRuntimeException;
import com.ejie.aa79b.dao.BitacoraExpedienteDao;
import com.ejie.aa79b.dao.DatosContactoDao;
import com.ejie.aa79b.dao.DatosPagoProveedoresDao;
import com.ejie.aa79b.dao.DatosPagoProveedoresIntDao;
import com.ejie.aa79b.dao.DatosTareaTradosDao;
import com.ejie.aa79b.dao.DocumentosGeneralDao;
import com.ejie.aa79b.dao.EjecucionTareasDao;
import com.ejie.aa79b.dao.ExpedienteDao;
import com.ejie.aa79b.dao.ExpedienteFacturacionDao;
import com.ejie.aa79b.dao.ExpedienteInterpretacionDao;
import com.ejie.aa79b.dao.ExpedienteTradRevDao;
import com.ejie.aa79b.dao.ObservacionesTareasDao;
import com.ejie.aa79b.dao.RegistroAccionesDao;
import com.ejie.aa79b.dao.TareasDao;
import com.ejie.aa79b.dao.TareasGestionInterpretacionDao;
import com.ejie.aa79b.mail.MailService;
import com.ejie.aa79b.model.BitacoraExpediente;
import com.ejie.aa79b.model.BitacoraSolicitante;
import com.ejie.aa79b.model.DatosContacto;
import com.ejie.aa79b.model.DatosPagoProveedores;
import com.ejie.aa79b.model.DatosPagoProveedoresInt;
import com.ejie.aa79b.model.DatosTareaTrados;
import com.ejie.aa79b.model.DocumentoTareaAdjunto;
import com.ejie.aa79b.model.EjecucionTareas;
import com.ejie.aa79b.model.EstadosExpediente;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.ExpedienteTradRev;
import com.ejie.aa79b.model.FasesExpediente;
import com.ejie.aa79b.model.FicheroDocExp;
import com.ejie.aa79b.model.ObservacionesTareas;
import com.ejie.aa79b.model.ParametrosEmail;
import com.ejie.aa79b.model.RegistroAcciones;
import com.ejie.aa79b.model.SubsanacionExpediente;
import com.ejie.aa79b.model.TareaReflection;
import com.ejie.aa79b.model.Tareas;
import com.ejie.aa79b.model.TareasGestionInterpretacion;
import com.ejie.aa79b.model.enums.AccionBitacoraEnum;
import com.ejie.aa79b.model.enums.ActivoEnum;
import com.ejie.aa79b.model.enums.AplicacionOrigenExpediente;
import com.ejie.aa79b.model.enums.EstadoEjecucionTareaEnum;
import com.ejie.aa79b.model.enums.EstadoExpedienteEnum;
import com.ejie.aa79b.model.enums.EstadoSubsanacionEnum;
import com.ejie.aa79b.model.enums.FaseExpedienteEnum;
import com.ejie.aa79b.model.enums.OrigenExpedienteEnum;
import com.ejie.aa79b.model.enums.PuntosMenuEnum;
import com.ejie.aa79b.model.enums.TipoAvisoEnum;
import com.ejie.aa79b.model.enums.TipoCierreEnum;
import com.ejie.aa79b.model.enums.TipoRequerimientoEnum;
import com.ejie.aa79b.o75.Batch;
import com.ejie.aa79b.service.TareasService;
import com.ejie.aa79b.webservices.EventosService;
import com.ejie.aa79b.webservices.PIDService;

/**
 * @author aresua
 *
 */
public class FinalizarTareaUtils extends SpringBeanAutowiringSupport {

	private static final Logger LOGGER = LoggerFactory.getLogger(FinalizarTareaUtils.class);

	private static final String LABEL_TIPO_DE_TAREA = "comun.tipoDeTarea";
	private static final String ID = "id";
	private static final String LABEL_EJECUCION_TAREA = "mensaje.ejecucionTarea";

	@Autowired()
	private TareasDao tareasDao;
	@Autowired()
	private TareasService tareasService;
	@Autowired()
	private EjecucionTareasDao ejecucionTareasDao;
	@Autowired()
	private ObservacionesTareasDao observacionesTareasDao;
	@Autowired()
	private DatosPagoProveedoresDao datosPagoProveedoresDao;
	@Autowired()
	private DatosPagoProveedoresIntDao datosPagoProveedoresIntDao;
	@Autowired()
	private BitacoraExpedienteDao bitacoraExpedienteDao;
	@Autowired()
	private ExpedienteDao expedienteDao;
	@Autowired()
	private ExpedienteFacturacionDao expedienteFacturacionDao;
	@Autowired()
	private ExpedienteTradRevDao expedienteTradRevDao;
	@Autowired()
	private ExpedienteInterpretacionDao expedienteInterpretacionDao;
	@Autowired()
	private TareasGestionInterpretacionDao tareasGestionInterpretacionDao;
	@Autowired()
	private Batch batch;
	@Autowired()
	private DatosTareaTradosDao datosTareaTradosDao;
	@Autowired()
	private ReloadableResourceBundleMessageSource messageSource;
	@Autowired()
	private Properties appConfiguration;
	@Autowired()
	private MailService mailService;
	@Autowired()
	private DatosContactoDao datosContactoDao;
	@Autowired()
	private DocumentosGeneralDao documentosGeneralDao;
	@Autowired()
	private RegistroAccionesDao registroAccionesDao;
	@Autowired()
	private PIDService pidService;
	@Autowired()
	private EventosService eventosService;
	private static final String WSDL = "webservice.evento.wsdl";
	private static final String NAME_SPACE_URI = "qname.evento.namespaceURI";
	private static final String LOCAL_PART = "qname.evento.localpart";

	public FinalizarTareaUtils() {
		// Constructor vacío
	}

	/**
	 * Trados:
	 *
	 * @param tarea   Tareas
	 * @param request HttpServletRequest
	 */
	public void finalizarTarea1(TareaReflection tareaReflection) {
		FinalizarTareaUtils.LOGGER.debug("Finaliza la tarea TRADOS");

		final Tareas tarea = tareaReflection.getTarea();
		final HttpServletRequest request = tareaReflection.getRequest();
		this.finalizarTareaGeneral(tarea, true);
		this.registrarAccionEjecucionTarea(tarea);

		final Locale locale = new Locale(Constants.LANG_EUSKERA);

		/*
		 * Si se requeriere presupuesto y/o se ha modificado la fecha de entrega:
		 */
		ExpedienteTradRev expedienteTradRev = new ExpedienteTradRev(tarea.getAnyo(), tarea.getNumExp());
		expedienteTradRev = this.expedienteTradRevDao.find(expedienteTradRev);

		final Date fechaEntregaTarea = DateUtils.obtFechaHoraFormateada(tarea.getFechaEntrega(),
				tarea.getHoraEntrega());
		final Date fechaEntregaEnExp = DateUtils.obtFechaHoraFormateada(expedienteTradRev.getFechaFinalIzo(),
				expedienteTradRev.getHoraFinalIzo());
		boolean modifFechaEntrega = this.esModifFechaEntrega(fechaEntregaTarea, fechaEntregaEnExp);

		if (expedienteTradRev != null && Constants.SI.equals(expedienteTradRev.getIndPresupuesto())) {

			Long tipoRequerimiento = Constants.CEROLONG;
			String infoAdicional = "";
			int accionBitacoraSol = Constants.CERO;
			if (Constants.SI.equals(expedienteTradRev.getIndPresupuesto()) && !modifFechaEntrega) {
				tipoRequerimiento = Long.valueOf(TipoRequerimientoEnum.ACEPTACION_PRESUPUESTO.getValue());
				infoAdicional = this.messageSource.getMessage("label.aceptacionPresupuesto", null, locale);
				accionBitacoraSol = AccionBitacoraEnum.REQ_ACEPT_RECHAZ_PRESUP.getValue();
			} else if (Constants.SI.equals(expedienteTradRev.getIndPresupuesto()) && modifFechaEntrega) {
				tipoRequerimiento = Long.valueOf(TipoRequerimientoEnum.ACEPTACION_PRESUPUESTO_FECHA_FIN.getValue());
				infoAdicional = this.messageSource.getMessage("label.aceptacionPresupuestoYFecha", null, locale);
				accionBitacoraSol = AccionBitacoraEnum.REQ_ACEPT_RECHAZ_PRESUP_FECHA_PROP_IZO.getValue();
			}

			// Nuevo requerimiento en la 64
			Long idSub = this.expedienteDao.getNextVal("AA79B64Q00");
			final SubsanacionExpediente subsanacionExpediente = new SubsanacionExpediente(idSub);
			subsanacionExpediente.setTipoRequerimiento(tipoRequerimiento);
			subsanacionExpediente.setFechaLimite(DateUtils.obtFechaHoraFormateada(
					tarea.getSubsanacion().getFechaLimite(), tarea.getSubsanacion().getHoraLimite()));
			subsanacionExpediente.setFechaEntrega(fechaEntregaTarea);
			subsanacionExpediente.setPresupuesto(tarea.getSubsanacion().getPresupuesto());
			this.expedienteDao.registrarSubsanacionTrados(subsanacionExpediente);
			tarea.setIdRequerimiento(idSub);

			// Registro en la 59
			final BitacoraExpediente bitacoraExpediente = new BitacoraExpediente();
			bitacoraExpediente.setAnyo(tarea.getAnyo());
			bitacoraExpediente.setNumExp(tarea.getNumExp());
			EstadosExpediente estadosExpediente = new EstadosExpediente();
			estadosExpediente.setId(Long.valueOf(EstadoExpedienteEnum.EN_CURSO.getValue()));
			bitacoraExpediente.setEstadoExp(estadosExpediente);
			FasesExpediente fasesExpediente = new FasesExpediente();
			fasesExpediente.setId(Long.valueOf(FaseExpedienteEnum.PDTE_TRAM_CLIENTE.getValue()));
			bitacoraExpediente.setFaseExp(fasesExpediente);
			bitacoraExpediente.setInfoAdic(infoAdicional);
			bitacoraExpediente.setSubsanacionExp(subsanacionExpediente);

			final BitacoraExpediente bitExp = this.bitacoraExpedienteDao.add(bitacoraExpediente);

			// Actualizar idRequerimiento en 81
			this.tareasDao.updateIdRequerimiento(tarea);

			// Actualizar la 51 con el último estado guardado
			final Expediente exp = new Expediente();
			exp.setAnyo(tarea.getAnyo());
			exp.setNumExp(tarea.getNumExp());
			exp.setBitacoraExpediente(bitExp);
			this.expedienteDao.modificarEstado(exp);

			// Actualizar bitácora solicitante en la 79
			final BitacoraSolicitante bitacoraSolicitante = new BitacoraSolicitante();
			bitacoraSolicitante.setAnyo(tarea.getAnyo());
			bitacoraSolicitante.setNumExp(tarea.getNumExp());
			bitacoraSolicitante.setUsuario(Constants.IZO);

			this.bitacoraExpedienteDao.addBitacoraSolicitante(bitacoraSolicitante, accionBitacoraSol);
		} else {
			// actualizar bitacora en curso - pendiente de ejecucion tareas
			final BitacoraExpediente bitacoraExpediente = new BitacoraExpediente();
			bitacoraExpediente.setAnyo(tarea.getAnyo());
			bitacoraExpediente.setNumExp(tarea.getNumExp());
			EstadosExpediente estadosExpediente = new EstadosExpediente();
			estadosExpediente.setId(Long.valueOf(EstadoExpedienteEnum.EN_CURSO.getValue()));
			bitacoraExpediente.setEstadoExp(estadosExpediente);
			FasesExpediente fasesExpediente = new FasesExpediente();
			fasesExpediente.setId(Long.valueOf(FaseExpedienteEnum.PDTE_EJECT_TAREAS.getValue()));
			bitacoraExpediente.setFaseExp(fasesExpediente);

			final BitacoraExpediente bitExp2 = this.bitacoraExpedienteDao.add(bitacoraExpediente);
			// Actualizar la 51 con el ultimo estado guardado
			final Expediente exp = new Expediente();
			exp.setAnyo(tarea.getAnyo());
			exp.setNumExp(tarea.getNumExp());
			exp.setBitacoraExpediente(bitExp2);
			this.expedienteDao.modificarEstado(exp);
		}

		/* Para todos los casos */
		DatosTareaTrados datosTareaTrados = new DatosTareaTrados();
		datosTareaTrados.setIdTarea(tarea.getIdTarea());
		datosTareaTrados = this.datosTareaTradosDao.find(datosTareaTrados);
		datosTareaTrados.setFechaEntrega(fechaEntregaTarea);
		this.checkSubsanacion(tarea, datosTareaTrados);
		datosTareaTrados.setIndVisible(tarea.getIndVisible());
		this.datosTareaTradosDao.update(datosTareaTrados);

		this.comprobarFinalizacionExpediente(tarea);
		// Envío de mails
		this.procesoBatchEnvioMails(tarea, request);
	}

	/**
	 *
	 * @param tarea            Tareas
	 * @param datosTareaTrados DatosTareaTrados
	 */
	private void checkSubsanacion(Tareas tarea, DatosTareaTrados datosTareaTrados) {
		if (tarea.getSubsanacion() != null && tarea.getSubsanacion().getPresupuesto() != null) {
			datosTareaTrados.setPresupuesto(tarea.getSubsanacion().getPresupuesto());
		} else {
			datosTareaTrados.setPresupuesto(new BigDecimal(Constants.CERO));
		}
	}

	/**
	 *
	 * @param fechaEntregaTarea Date
	 * @param fechaEntregaEnExp Date
	 * @return boolean
	 */
	private boolean esModifFechaEntrega(Date fechaEntregaTarea, Date fechaEntregaEnExp) {
		boolean modifFechaEntrega = false;
		if (fechaEntregaTarea != null && fechaEntregaEnExp != null
				&& fechaEntregaTarea.getTime() != fechaEntregaEnExp.getTime()) {
			modifFechaEntrega = true;
		}
		return modifFechaEntrega;
	}

	/**
	 * Traducir:
	 *
	 * @param tarea   Tareas
	 * @param request HttpServletRequest
	 */
	public void finalizarTarea2(TareaReflection tareaReflection) {
		FinalizarTareaUtils.LOGGER.debug("Finaliza la tarea Traducir");
		final Tareas tarea = tareaReflection.getTarea();
		final HttpServletRequest request = tareaReflection.getRequest();
		this.finalizarTareaGeneral(tarea, true);
		this.registrarAccionEjecucionTarea(tarea);
		this.comprobarGuardarObservacionesAa06a(tarea);
		this.comprobarFinalizacionExpediente(tarea);
		this.procesoBatchEnvioMails(tarea, request);
	}

	/**
	 * Corredactar:
	 *
	 * @param tarea   Tareas
	 * @param request HttpServletRequest
	 */
	public void finalizarTarea3(TareaReflection tareaReflection) {
		FinalizarTareaUtils.LOGGER.debug("Finaliza la tarea Corredactar");
		final Tareas tarea = tareaReflection.getTarea();
		final HttpServletRequest request = tareaReflection.getRequest();
		this.finalizarTareaGeneral(tarea, true);
		this.registrarAccionEjecucionTarea(tarea);

		// ponemos los documentos finales como visibles al usuario final
		DocumentoTareaAdjunto documentoTareaAdjunto = new DocumentoTareaAdjunto();
		documentoTareaAdjunto.setIdTarea(tarea.getIdTarea());
		documentoTareaAdjunto.setIndVisible(Constants.SI);
		this.documentosGeneralDao.updateIndVisible92(documentoTareaAdjunto);

		BitacoraExpediente bitacoraExpediente = new BitacoraExpediente();
		ExpedienteTradRev expedienteTradRev = new ExpedienteTradRev(tarea.getAnyo(), tarea.getNumExp());
		expedienteTradRev = this.expedienteTradRevDao.find(expedienteTradRev);
		this.expedienteTradRevDao.updateFechaEntregaReal(expedienteTradRev);
		if (Constants.SI.equals(expedienteTradRev.getIndFacturable()) && comprobarExpedienteNoFacturado(tarea)) {
			/* Si el expediente es facturable */
			// Nuevo registro en la 59
			bitacoraExpediente.setAnyo(tarea.getAnyo());
			bitacoraExpediente.setNumExp(tarea.getNumExp());
			EstadosExpediente estadosExpediente = new EstadosExpediente();
			estadosExpediente.setId(Long.valueOf(EstadoExpedienteEnum.CERRADO.getValue()));
			bitacoraExpediente.setEstadoExp(estadosExpediente);
			FasesExpediente fasesExpediente = new FasesExpediente();
			fasesExpediente.setId(Long.valueOf(FaseExpedienteEnum.PDTE_REV_FACTURACION.getValue()));
			bitacoraExpediente.setFaseExp(fasesExpediente);

			bitacoraExpediente = this.bitacoraExpedienteDao.add(bitacoraExpediente);
		} else {
			/* Si el expediente no es facturable */
			// Nuevo registro en la 59
			bitacoraExpediente.setAnyo(tarea.getAnyo());
			bitacoraExpediente.setNumExp(tarea.getNumExp());
			EstadosExpediente estadosExpediente = new EstadosExpediente();
			estadosExpediente.setId(Long.valueOf(EstadoExpedienteEnum.CERRADO.getValue()));
			bitacoraExpediente.setEstadoExp(estadosExpediente);
			FasesExpediente fasesExpediente = new FasesExpediente();
			fasesExpediente.setId(Long.valueOf(FaseExpedienteEnum.CERRADO.getValue()));
			bitacoraExpediente.setFaseExp(fasesExpediente);

			bitacoraExpediente = this.bitacoraExpedienteDao.add(bitacoraExpediente);
		}

		// Se asocia la bitácora en la tabla 51
		final Expediente exp = new Expediente();
		exp.setAnyo(tarea.getAnyo());
		exp.setNumExp(tarea.getNumExp());
		exp.setEstadoBitacora(bitacoraExpediente.getIdEstadoBitacora());
		this.expedienteDao.updateIdEstadoBitacora(exp);

		final BitacoraSolicitante bitacoraSolicitante = new BitacoraSolicitante();
		bitacoraSolicitante.setAnyo(tarea.getAnyo());
		bitacoraSolicitante.setNumExp(tarea.getNumExp());
		bitacoraSolicitante.setUsuario(Constants.IZO);
		this.bitacoraExpedienteDao.addBitacoraSolicitante(bitacoraSolicitante,
				AccionBitacoraEnum.TRABAJO_FINAL_ENTREGADO_CLIENTE.getValue());

		this.bitacoraExpedienteDao.addBitacoraSolicitante(bitacoraSolicitante,
				AccionBitacoraEnum.EXP_CERRADO.getValue());

		this.comprobarFinalizacionExpediente(tarea);
		this.procesoBatchEnvioMails(tarea, request);
	}

	/**
	 * Revisar:
	 *
	 * @param tarea   Tareas
	 * @param request HttpServletRequest
	 */
	public void finalizarTarea4(TareaReflection tareaReflection) {
		FinalizarTareaUtils.LOGGER.debug("Finaliza la tarea Revisar");
		final Tareas tarea = tareaReflection.getTarea();
		final HttpServletRequest request = tareaReflection.getRequest();
		this.finalizarTareaGeneral(tarea, true);
		this.registrarAccionEjecucionTarea(tarea);
		this.comprobarGuardarObservacionesAa06a(tarea);
		this.comprobarFinalizacionExpediente(tarea);
		this.procesoBatchEnvioMails(tarea, request);
	}

	/**
	 * No conformidad con el trabajo entregado por el proveedor:
	 *
	 * @param tarea   Tareas
	 * @param request HttpServletRequest
	 */
	public void finalizarTarea5(TareaReflection tareaReflection) {
		FinalizarTareaUtils.LOGGER.debug("Finaliza la tarea No conformidad con el trabajo entregado por el proveedor");
		final Tareas tarea = tareaReflection.getTarea();
		final HttpServletRequest request = tareaReflection.getRequest();
		this.finalizarTareaGeneral(tarea, true);
		this.registrarAccionEjecucionTarea(tarea);
		this.comprobarGuardarObservacionesAa06a(tarea);
		this.comprobarFinalizacionExpediente(tarea);
		this.procesoBatchEnvioMails(tarea, request);
	}

	/**
	 * Gestionar un trabajo de interpretación:
	 *
	 * @param tarea   Tareas
	 * @param request HttpServletRequest
	 */
	public void finalizarTarea6(TareaReflection tareaReflection) {
		FinalizarTareaUtils.LOGGER.debug("Finaliza la tarea Gestionar un trabajo de interpretación");
		final Tareas tarea = tareaReflection.getTarea();
		final HttpServletRequest request = tareaReflection.getRequest();
		this.finalizarTareaGeneral(tarea, true);
		this.registrarAccionEjecucionTarea(tarea);

		final Locale locale = new Locale(Constants.LANG_EUSKERA);

		// Actualizar la tabla 89
		final TareasGestionInterpretacion gestionInterpretacion = tarea.getGestionInterpretacion();
		gestionInterpretacion.setIdTarea(tarea.getIdTarea());

		int modificado = this.tareasGestionInterpretacionDao.updateInt(gestionInterpretacion);
		if (modificado == Constants.CERO) {
			this.tareasGestionInterpretacionDao.add(gestionInterpretacion);
		}

		Expediente expediente = new Expediente(tarea.getAnyo(), tarea.getNumExp());
		expediente = this.expedienteInterpretacionDao.find(expediente);
		if (expediente != null && expediente.getExpedienteInterpretacion() != null
				&& Constants.SI.equals(expediente.getExpedienteInterpretacion().getIndPresupuesto())) {
			/*
			 * Si en el expediente se ha especificado que se requiere presupuesto
			 */

			// Nuevo requerimiento en la 64
			Long idSub = this.expedienteDao.getNextVal("AA79B64Q00");
			final SubsanacionExpediente subsanacionExpediente = new SubsanacionExpediente(idSub);
			subsanacionExpediente
					.setTipoRequerimiento(Long.valueOf(TipoRequerimientoEnum.ACEPTACION_PRESUPUESTO.getValue()));
			subsanacionExpediente.setFechaLimite(DateUtils.obtFechaHoraFormateada(
					tarea.getSubsanacion().getFechaLimite(), tarea.getSubsanacion().getHoraLimite()));
			this.expedienteDao.registrarSubsanacion(subsanacionExpediente);

			// Actualizar en la tabla 81 el id del requerimiento que acabamos de
			// insertar
			this.tareasDao.updateIdSubsanacionTareaEjecutada(tarea.getIdTarea(), idSub);

			// Nuevo registro en la 59.
			final BitacoraExpediente bitacoraExpediente = new BitacoraExpediente();
			bitacoraExpediente.setAnyo(tarea.getAnyo());
			bitacoraExpediente.setNumExp(tarea.getNumExp());
			EstadosExpediente estadosExpediente = new EstadosExpediente();
			estadosExpediente.setId(Long.valueOf(EstadoExpedienteEnum.EN_CURSO.getValue()));
			bitacoraExpediente.setEstadoExp(estadosExpediente);
			FasesExpediente fasesExpediente = new FasesExpediente();
			fasesExpediente.setId(Long.valueOf(FaseExpedienteEnum.PDTE_TRAM_CLIENTE.getValue()));
			bitacoraExpediente.setFaseExp(fasesExpediente);
			bitacoraExpediente.setInfoAdic(this.messageSource.getMessage("label.aceptacionPresupuesto", null, locale));
			bitacoraExpediente.setSubsanacionExp(subsanacionExpediente);

			final BitacoraExpediente bitExp = this.bitacoraExpedienteDao.add(bitacoraExpediente);

			// Actualizar la 51 con el último estado guardado
			final Expediente exp = new Expediente();
			exp.setAnyo(tarea.getAnyo());
			exp.setNumExp(tarea.getNumExp());
			exp.setEstadoBitacora(bitExp.getIdEstadoBitacora());
			this.expedienteDao.updateIdEstadoBitacora(exp);

			// Actualizar bitácora solicitante en la 79
			final BitacoraSolicitante bitacoraSolicitante = new BitacoraSolicitante();
			bitacoraSolicitante.setAnyo(tarea.getAnyo());
			bitacoraSolicitante.setNumExp(tarea.getNumExp());
			bitacoraSolicitante.setUsuario(Constants.IZO);

			this.bitacoraExpedienteDao.addBitacoraSolicitante(bitacoraSolicitante,
					AccionBitacoraEnum.REQ_ACEPT_RECHAZ_PRESUP.getValue());

		}
		/* Si no se ha solicitado presupuesto */
		this.comprobarFinalizacionExpediente(tarea);
		// Envío de mails general
		this.procesoBatchEnvioMails(tarea, request);

	}

	/**
	 * Preparar interpretación:
	 *
	 * @param tarea   Tareas
	 * @param request HttpServletRequest
	 */
	public void finalizarTarea7(TareaReflection tareaReflection) {
		FinalizarTareaUtils.LOGGER.debug("Finaliza la tarea Preparar interpretación");
		final Tareas tarea = tareaReflection.getTarea();
		final HttpServletRequest request = tareaReflection.getRequest();
		this.finalizarTareaGeneral(tarea, true);
		this.registrarAccionEjecucionTarea(tarea);
		this.comprobarFinalizacionExpediente(tarea);
		this.procesoBatchEnvioMails(tarea, request);
	}

	/**
	 * Interpretación:
	 *
	 * @param tarea   Tareas
	 * @param request HttpServletRequest
	 */
	public void finalizarTarea8(TareaReflection tareaReflection) {
		FinalizarTareaUtils.LOGGER.debug("Finaliza la tarea Interpretación");
		final Tareas tarea = tareaReflection.getTarea();
		final HttpServletRequest request = tareaReflection.getRequest();
		this.finalizarTareaInterpretacion(tarea);
		this.comprobarGuardarObservacionesAa06a(tarea);
		this.comprobarFinalizacionExpediente(tarea);
		this.procesoBatchEnvioMails(tarea, request);
	}

	/**
	 * Gestionar una no conformidad por parte del cliente:
	 *
	 * @param tarea   Tareas
	 * @param request HttpServletRequest
	 */
	public void finalizarTarea9(TareaReflection tareaReflection) {
		FinalizarTareaUtils.LOGGER.debug("Finaliza la tarea Gestionar una no conformidad por parte del cliente");
		final Tareas tarea = tareaReflection.getTarea();
		final HttpServletRequest request = tareaReflection.getRequest();
		// La marco como realizada o no en función del ind_conformidad
		// y luego se mostrará el switch en función de ind_realizada de la tarea
		// de no conformidad
		// en lugar de fijarnos en el ind_no_conformidad de la tarea relacionada
		this.finalizarTareaGeneral(tarea, Constants.SI.equals(tarea.getIndNoConformidad()));
		this.registrarAccionEjecucionTarea(tarea);

		final Locale locale = new Locale(Constants.LANG_EUSKERA);

		// Observaciones en la 85
		this.guardarObservacionesAuditables(tarea);
		Tareas tareaRel = new Tareas();
		tareaRel.setIdTarea(this.tareasDao.findTareaRelacionada(tarea));
		tareaRel.setIndNoConformidad(tarea.getIndNoConformidad());
		// Guardar la conformidad del cliente
		this.tareasDao.updateConformidad(tareaRel);

		if (Constants.SI.equals(tarea.getIndNoConformidad())) {
			// Si se acepta la no conformidad -> Registro en la 59
			final BitacoraExpediente bitacoraExpediente = new BitacoraExpediente();
			bitacoraExpediente.setAnyo(tarea.getAnyo());
			bitacoraExpediente.setNumExp(tarea.getNumExp());
			EstadosExpediente estadosExpediente = new EstadosExpediente();
			estadosExpediente.setId(Long.valueOf(EstadoExpedienteEnum.EN_CURSO.getValue()));
			bitacoraExpediente.setEstadoExp(estadosExpediente);
			FasesExpediente fasesExpediente = new FasesExpediente();
			fasesExpediente.setId(Long.valueOf(FaseExpedienteEnum.PDTE_EJECT_TAREAS.getValue()));
			String infoAdicional = this.messageSource.getMessage("label.noConformidadAceptada", null, locale);
			bitacoraExpediente.setInfoAdic(infoAdicional);
			bitacoraExpediente.setFaseExp(fasesExpediente);

			final BitacoraExpediente bitExp = this.bitacoraExpedienteDao.add(bitacoraExpediente);

			// Actualizar la 51 con el último estado guardado
			final Expediente exp = new Expediente();
			exp.setAnyo(tarea.getAnyo());
			exp.setNumExp(tarea.getNumExp());
			exp.setEstadoBitacora(bitExp.getIdEstadoBitacora());
			this.expedienteDao.updateIdEstadoBitacora(exp);

			// Actualizar bitácora solicitante en la 79
			final BitacoraSolicitante bitacoraSolicitante = new BitacoraSolicitante();
			bitacoraSolicitante.setAnyo(tarea.getAnyo());
			bitacoraSolicitante.setNumExp(tarea.getNumExp());
			bitacoraSolicitante.setUsuario(Constants.IZO);

			this.bitacoraExpedienteDao.addBitacoraSolicitante(bitacoraSolicitante,
					AccionBitacoraEnum.EXP_EN_CURSO.getValue());

		} else {

			// hacer lo mismo que se hace al finalizar la tarea tipo 9, con sus
			// comprobaciones...

			BitacoraExpediente bitacoraExpediente = new BitacoraExpediente();
			ExpedienteTradRev expedienteTradRev = new ExpedienteTradRev(tarea.getAnyo(), tarea.getNumExp());
			expedienteTradRev = this.expedienteTradRevDao.find(expedienteTradRev);

			bitacoraExpediente.setAnyo(tarea.getAnyo());
			bitacoraExpediente.setNumExp(tarea.getNumExp());
			EstadosExpediente estadosExpediente = new EstadosExpediente();
			estadosExpediente.setId(Long.valueOf(EstadoExpedienteEnum.EN_CURSO.getValue()));
			bitacoraExpediente.setEstadoExp(estadosExpediente);
			FasesExpediente fasesExpediente = new FasesExpediente();
			fasesExpediente.setId(Long.valueOf(FaseExpedienteEnum.PDTE_EJECT_TAREAS.getValue()));
			String infoAdicional = this.messageSource.getMessage("label.noConformidadRechazada", null, locale);
			bitacoraExpediente.setInfoAdic(infoAdicional);
			bitacoraExpediente.setFaseExp(fasesExpediente);

			this.bitacoraExpedienteDao.add(bitacoraExpediente);

			bitacoraExpediente = new BitacoraExpediente();

			if (Constants.SI.equals(expedienteTradRev.getIndFacturable()) && comprobarExpedienteNoFacturado(tarea)) {
				/* Si el expediente es facturable */
				// Nuevo registro en la 59
				bitacoraExpediente.setAnyo(tarea.getAnyo());
				bitacoraExpediente.setNumExp(tarea.getNumExp());
				estadosExpediente = new EstadosExpediente();
				estadosExpediente.setId(Long.valueOf(EstadoExpedienteEnum.CERRADO.getValue()));
				bitacoraExpediente.setEstadoExp(estadosExpediente);
				fasesExpediente = new FasesExpediente();
				fasesExpediente.setId(Long.valueOf(FaseExpedienteEnum.PDTE_REV_FACTURACION.getValue()));
				bitacoraExpediente.setFaseExp(fasesExpediente);

				bitacoraExpediente = this.bitacoraExpedienteDao.add(bitacoraExpediente);
			} else {
				/* Si el expediente no es facturable */
				// Nuevo registro en la 59
				bitacoraExpediente.setAnyo(tarea.getAnyo());
				bitacoraExpediente.setNumExp(tarea.getNumExp());
				estadosExpediente = new EstadosExpediente();
				estadosExpediente.setId(Long.valueOf(EstadoExpedienteEnum.CERRADO.getValue()));
				bitacoraExpediente.setEstadoExp(estadosExpediente);
				fasesExpediente = new FasesExpediente();
				fasesExpediente.setId(Long.valueOf(FaseExpedienteEnum.CERRADO.getValue()));
				bitacoraExpediente.setFaseExp(fasesExpediente);

				bitacoraExpediente = this.bitacoraExpedienteDao.add(bitacoraExpediente);
			}

			// Se asocia la bitácora en la tabla 51
			final Expediente exp = new Expediente();
			exp.setAnyo(tarea.getAnyo());
			exp.setNumExp(tarea.getNumExp());
			exp.setEstadoBitacora(bitacoraExpediente.getIdEstadoBitacora());
			this.expedienteDao.updateIdEstadoBitacora(exp);
		}

		this.comprobarFinalizacionExpediente(tarea);
		this.procesoBatchEnvioMails(tarea, request);
	}

	/**
	 * Post-Traducción BOE:
	 *
	 * @param tarea   Tareas
	 * @param request HttpServletRequest
	 */
	public void finalizarTarea10(TareaReflection tareaReflection) {
		FinalizarTareaUtils.LOGGER.debug("Finaliza la tarea Post-Traducción BOE");
		final Tareas tarea = tareaReflection.getTarea();
		final HttpServletRequest request = tareaReflection.getRequest();
		this.finalizarTareaGeneral(tarea, true);
		this.registrarAccionEjecucionTarea(tarea);
		this.comprobarFinalizacionExpediente(tarea);
		this.procesoBatchEnvioMails(tarea, request);

		// Guardar documentos en la 88 y 86
	}

	/**
	 * Revisión de datos de pago a proveedor:
	 *
	 * @param tarea   Tareas
	 * @param request HttpServletRequest
	 */
	public void finalizarTarea11(TareaReflection tareaReflection) {
		FinalizarTareaUtils.LOGGER.debug("Finaliza la tarea Revisión de datos de pago a proveedor");
		final Tareas tarea = tareaReflection.getTarea();
		final HttpServletRequest request = tareaReflection.getRequest();
		this.finalizarTareaGeneral(tarea, true);
		this.registrarAccionEjecucionTarea(tarea);
		if (ActivoEnum.SI.getValue().equals(tarea.getIndFacturacion())) {
			// Datos de pago en la 94
			this.guardarDatosPagoProveedores(tarea, request);
		} else {
			this.tareasDao.eliminar(tarea.getIdTarea(), DBConstants.INTVALUE_TABLA_94);
		}

		this.tareasDao.updateIndFacturableRelacionada(tarea.getIdTarea(), tarea.getIndFacturacion());
		this.comprobarFinalizacionExpediente(tarea);
		this.procesoBatchEnvioMails(tarea, request);
	}

	/**
	 * Introducción de datos de pago a proveedor para los expedientes de
	 * interpretación asignados a empresas proveedoras:
	 *
	 * @param tarea   Tareas
	 * @param request HttpServletRequest
	 */
	public void finalizarTarea12(TareaReflection tareaReflection) {
		FinalizarTareaUtils.LOGGER.debug(
				"Finaliza la tarea Introducción de datos de pago a proveedor para los expedientes de interpretación asignados a empresas proveedoras");
		final Tareas tarea = tareaReflection.getTarea();
		final HttpServletRequest request = tareaReflection.getRequest();
		// Es una tarea que se ejecuta despues de una interpretacion con la que
		// esta relacionada
		// por lo que puede que sea ella la de cierre.
		this.finalizarTareaInterpretacion(tarea);
		// Importes en la 95
		this.guardarDatosPagoProveedoresInt(tarea, request);
		this.comprobarFinalizacionExpediente(tarea);
		this.procesoBatchEnvioMails(tarea, request);
	}

	/**
	 * Notificar correcciones al proveedor:
	 *
	 * @param tarea   Tareas
	 * @param request HttpServletRequest
	 */
	public void finalizarTarea13(TareaReflection tareaReflection) {
		FinalizarTareaUtils.LOGGER.debug("Finaliza la tarea Notificar correcciones al proveedor");
		final Tareas tarea = tareaReflection.getTarea();
		final HttpServletRequest request = tareaReflection.getRequest();
		this.finalizarTareaGeneral(tarea, true);
		this.registrarAccionEjecucionTarea(tarea);
		this.comprobarFinalizacionExpediente(tarea);
		this.procesoBatchEnvioMails(tarea, request);

	}

	/**
	 * Estudiar una subsanación de documentación:
	 *
	 * @param tarea   Tareas
	 * @param request HttpServletRequest
	 */
	public void finalizarTarea14(TareaReflection tareaReflection) {
		FinalizarTareaUtils.LOGGER.debug("Finaliza la tarea Estudiar una subsanación de documentación");
		final Tareas tarea = tareaReflection.getTarea();
		final HttpServletRequest request = tareaReflection.getRequest();
		this.finalizarTareaGeneral(tarea, true);
		this.registrarAccionEjecucionTarea(tarea);

		final SubsanacionExpediente subsanacion = tarea.getSubsanacion();
		// Actualizar la 64
		this.expedienteDao.updateIndAndFechaSubsanacion(subsanacion);

		/* Si subsanación correcta */
		final Locale locale = new Locale(Constants.LANG_EUSKERA);

		BitacoraExpediente bitacoraExpediente = new BitacoraExpediente();
		bitacoraExpediente.setAnyo(tarea.getAnyo());
		bitacoraExpediente.setNumExp(tarea.getNumExp());
		EstadosExpediente estadosExpediente = new EstadosExpediente();
		FasesExpediente fasesExpediente = new FasesExpediente();

		BitacoraSolicitante bitacoraSolicitante = new BitacoraSolicitante();
		bitacoraSolicitante.setAnyo(tarea.getAnyo());
		bitacoraSolicitante.setNumExp(tarea.getNumExp());
		// Usuario del IZO
		bitacoraSolicitante.setUsuario(Constants.IZO);

		if (EstadoSubsanacionEnum.ACEPTADO.getValue() == subsanacion.getEstado()) {
			// subsanacion correcta
			// Nuevo registro en la 59
			estadosExpediente.setId(Long.valueOf(EstadoExpedienteEnum.EN_CURSO.getValue()));
			bitacoraExpediente.setEstadoExp(estadosExpediente);
			fasesExpediente.setId(Long.valueOf(FaseExpedienteEnum.PDTE_TRAM_CLIENTE.getValue()));
			bitacoraExpediente.setFaseExp(fasesExpediente);
			bitacoraExpediente.setInfoAdic(this.messageSource.getMessage("label.subsanacion.correcta", null, locale));

			bitacoraExpediente = this.bitacoraExpedienteDao.add(bitacoraExpediente);

			// Nuevo registro en la bitacora del solicitante en la 79
			this.bitacoraExpedienteDao.addBitacoraSolicitante(bitacoraSolicitante,
					AccionBitacoraEnum.SUBS_ACEPT_IZO.getValue());
		} else {
			/* Si subsanacion no correcta */
			// Nuevo registro en la 59
			estadosExpediente.setId(Long.valueOf(EstadoExpedienteEnum.EN_CURSO.getValue()));
			bitacoraExpediente.setEstadoExp(estadosExpediente);
			fasesExpediente.setId(Long.valueOf(FaseExpedienteEnum.PDTE_TRAM_CLIENTE.getValue()));
			bitacoraExpediente.setFaseExp(fasesExpediente);
			bitacoraExpediente.setInfoAdic(this.messageSource.getMessage("label.subsanacion.incorrecta", null, locale));

			bitacoraExpediente = this.bitacoraExpedienteDao.add(bitacoraExpediente);

			// Nuevo registro en la bitácora del solicitante en la 79
			this.bitacoraExpedienteDao.addBitacoraSolicitante(bitacoraSolicitante,
					AccionBitacoraEnum.SUBS_RECHAZ_IZO.getValue());
		}

		/* Para todos los casos */

		// Nuevo registro en la 59
		estadosExpediente.setId(Long.valueOf(EstadoExpedienteEnum.EN_CURSO.getValue()));
		bitacoraExpediente.setEstadoExp(estadosExpediente);
		fasesExpediente.setId(Long.valueOf(FaseExpedienteEnum.PDTE_EJECT_TAREAS.getValue()));
		bitacoraExpediente.setFaseExp(fasesExpediente);
		bitacoraExpediente.setInfoAdic("");

		bitacoraExpediente = this.bitacoraExpedienteDao.add(bitacoraExpediente);

		// Actualizar estado en la 51
		final Expediente exp = new Expediente();
		exp.setAnyo(tarea.getAnyo());
		exp.setNumExp(tarea.getNumExp());
		exp.setEstadoBitacora(bitacoraExpediente.getIdEstadoBitacora());

		this.expedienteDao.updateIdEstadoBitacora(exp);

		this.comprobarDocumentacionTarea(tarea, subsanacion.getId());
		this.comprobarFinalizacionExpediente(tarea);
		this.procesoBatchEnvioMails(tarea, request);
	}

	/**
	 * Alinear:
	 *
	 * @param tarea   Tareas
	 * @param request HttpServletRequest
	 */
	public void finalizarTarea15(TareaReflection tareaReflection) {
		FinalizarTareaUtils.LOGGER.debug("Finaliza la tarea 15");
		this.finalizarTareaSinGestionAsociada(tareaReflection);
	}

	/**
	 * Actualizar las memorias de traducción:
	 *
	 * @param tarea   Tareas
	 * @param request HttpServletRequest
	 */
	public void finalizarTarea16(TareaReflection tareaReflection) {
		FinalizarTareaUtils.LOGGER.debug("Finaliza la tarea Actualizar las memorias de traducción");
		this.finalizarTareaSinGestionAsociada(tareaReflection);
	}

	/**
	 * Actualizar IDABA:
	 *
	 * @param tarea   Tareas
	 * @param request HttpServletRequest
	 */
	public void finalizarTarea17(TareaReflection tareaReflection) {
		FinalizarTareaUtils.LOGGER.debug("Finaliza la tarea Actualizar IDABA");
		this.finalizarTareaSinGestionAsociada(tareaReflection);
	}

	/**
	 * Tareas de terminología:
	 *
	 * @param tarea   Tareas
	 * @param request HttpServletRequest
	 */
	public void finalizarTarea18(TareaReflection tareaReflection) {
		FinalizarTareaUtils.LOGGER.debug("Finaliza la tarea Tareas de terminología");
		this.finalizarTareaSinGestionAsociada(tareaReflection);
	}

	/**
	 * Entrega a cliente:
	 *
	 * @param tarea   Tareas
	 * @param request HttpServletRequest
	 */
	public void finalizarTarea19(TareaReflection tareaReflection) {
		FinalizarTareaUtils.LOGGER.debug("Finaliza la tarea Entrega a cliente");
		final Tareas tarea = tareaReflection.getTarea();
		final HttpServletRequest request = tareaReflection.getRequest();
		this.finalizarTareaGeneral(tarea, true);
		this.registrarAccionEjecucionTarea(tarea);

		// ponemos los documentos finales como visibles al usuario final
		DocumentoTareaAdjunto documentoTareaAdjunto = new DocumentoTareaAdjunto();
		documentoTareaAdjunto.setIdTarea(tarea.getIdTarea());
		documentoTareaAdjunto.setIndVisible(Constants.SI);
		this.documentosGeneralDao.updateIndVisible92(documentoTareaAdjunto);

		BitacoraExpediente bitacoraExpediente = new BitacoraExpediente();
		ExpedienteTradRev expedienteTradRev = new ExpedienteTradRev(tarea.getAnyo(), tarea.getNumExp());
		expedienteTradRev = this.expedienteTradRevDao.find(expedienteTradRev);
		this.expedienteTradRevDao.updateFechaEntregaReal(expedienteTradRev);

		if (expedienteTradRev.getIndPublicarBopv().equals(Constants.NO)) {
			documentoTareaAdjunto.setIdDocOriginalFinal(null);
			this.documentosGeneralDao.updateAllDocOriginalFinal(documentoTareaAdjunto);
		}

		if (Constants.SI.equals(expedienteTradRev.getIndFacturable()) && comprobarExpedienteNoFacturado(tarea)) {
			/* Si el expediente es facturable */
			// Nuevo registro en la 59

			// Comprobamos que no se haya revisado ya los datos de facturación del
			// expediente.

			bitacoraExpediente.setAnyo(tarea.getAnyo());
			bitacoraExpediente.setNumExp(tarea.getNumExp());
			EstadosExpediente estadosExpediente = new EstadosExpediente();
			estadosExpediente.setId(Long.valueOf(EstadoExpedienteEnum.CERRADO.getValue()));
			bitacoraExpediente.setEstadoExp(estadosExpediente);
			FasesExpediente fasesExpediente = new FasesExpediente();
			fasesExpediente.setId(Long.valueOf(FaseExpedienteEnum.PDTE_REV_FACTURACION.getValue()));
			bitacoraExpediente.setFaseExp(fasesExpediente);

			bitacoraExpediente = this.bitacoraExpedienteDao.add(bitacoraExpediente);
		} else {
			/* Si el expediente no es facturable */
			// Nuevo registro en la 59
			bitacoraExpediente.setAnyo(tarea.getAnyo());
			bitacoraExpediente.setNumExp(tarea.getNumExp());
			EstadosExpediente estadosExpediente = new EstadosExpediente();
			estadosExpediente.setId(Long.valueOf(EstadoExpedienteEnum.CERRADO.getValue()));
			bitacoraExpediente.setEstadoExp(estadosExpediente);
			FasesExpediente fasesExpediente = new FasesExpediente();
			fasesExpediente.setId(Long.valueOf(FaseExpedienteEnum.CERRADO.getValue()));
			bitacoraExpediente.setFaseExp(fasesExpediente);

			bitacoraExpediente = this.bitacoraExpedienteDao.add(bitacoraExpediente);
		}

		// Se asocia la bitácora en la tabla 51
		final Expediente exp = new Expediente();
		exp.setAnyo(tarea.getAnyo());
		exp.setNumExp(tarea.getNumExp());
		exp.setEstadoBitacora(bitacoraExpediente.getIdEstadoBitacora());
		this.expedienteDao.updateIdEstadoBitacora(exp);

		final BitacoraSolicitante bitacoraSolicitante = new BitacoraSolicitante();
		bitacoraSolicitante.setAnyo(tarea.getAnyo());
		bitacoraSolicitante.setNumExp(tarea.getNumExp());
		bitacoraSolicitante.setUsuario(Constants.IZO);
		this.bitacoraExpedienteDao.addBitacoraSolicitante(bitacoraSolicitante,
				AccionBitacoraEnum.TRABAJO_FINAL_ENTREGADO_CLIENTE.getValue());
		this.bitacoraExpedienteDao.addBitacoraSolicitante(bitacoraSolicitante,
				AccionBitacoraEnum.EXP_CERRADO.getValue());

		this.comprobarFinalizacionExpediente(tarea);
		this.procesoBatchEnvioMails(tarea, request);

		Expediente expediente = this.expedienteDao.getOrigenExpediente(exp);
		List<String> rutasPif = this.getPIF(tarea);
		if(StringUtils.isNotBlank(expediente.getOrigen()) && expediente.getOrigen().equals("W") && StringUtils.isNotBlank(expediente.getAplicacionOrigen())
				&& !AplicacionOrigenExpediente.P43.getValue().equalsIgnoreCase(expediente.getAplicacionOrigen())) {
			FinalizarTareaUtils.LOGGER.info("FinalizarTareaUtils.createCambioFecha");
			this.eventosService.createInvoiceCambioEstado(expediente, rutasPif, TipoCierreEnum.FINALIZADO.getValue());
		}
	}

	/**
	 * Revisión con entrega a cliente:
	 *
	 * @param tarea   Tareas
	 * @param request HttpServletRequest
	 */
	public void finalizarTarea20(TareaReflection tareaReflection) {
		FinalizarTareaUtils.LOGGER.debug("Finaliza la tarea Revisión con entrega a cliente");
		final Tareas tarea = tareaReflection.getTarea();
		final HttpServletRequest request = tareaReflection.getRequest();
		this.finalizarTareaGeneral(tarea, true);
		this.registrarAccionEjecucionTarea(tarea);

		// ponemos los documentos finales como visibles al usuario final
		DocumentoTareaAdjunto documentoTareaAdjunto = new DocumentoTareaAdjunto();
		documentoTareaAdjunto.setIdTarea(tarea.getIdTarea());
		documentoTareaAdjunto.setIndVisible(Constants.SI);
		this.documentosGeneralDao.updateIndVisible92(documentoTareaAdjunto);

		BitacoraExpediente bitacoraExpediente = new BitacoraExpediente();
		ExpedienteTradRev expedienteTradRev = new ExpedienteTradRev(tarea.getAnyo(), tarea.getNumExp());
		expedienteTradRev = this.expedienteTradRevDao.find(expedienteTradRev);
		this.expedienteTradRevDao.updateFechaEntregaReal(expedienteTradRev);

		if (expedienteTradRev.getIndPublicarBopv().equals(Constants.NO)) {
			documentoTareaAdjunto.setIdDocOriginalFinal(null);
			this.documentosGeneralDao.updateAllDocOriginalFinal(documentoTareaAdjunto);
		}

		if (Constants.SI.equals(expedienteTradRev.getIndFacturable()) && comprobarExpedienteNoFacturado(tarea)) {
			/* Si el expediente es facturable */
			// Nuevo registro en la 59
			bitacoraExpediente.setAnyo(tarea.getAnyo());
			bitacoraExpediente.setNumExp(tarea.getNumExp());
			EstadosExpediente estadosExpediente = new EstadosExpediente();
			estadosExpediente.setId(Long.valueOf(EstadoExpedienteEnum.CERRADO.getValue()));
			bitacoraExpediente.setEstadoExp(estadosExpediente);
			FasesExpediente fasesExpediente = new FasesExpediente();
			fasesExpediente.setId(Long.valueOf(FaseExpedienteEnum.PDTE_REV_FACTURACION.getValue()));
			bitacoraExpediente.setFaseExp(fasesExpediente);

			bitacoraExpediente = this.bitacoraExpedienteDao.add(bitacoraExpediente);
		} else {
			/* Si el expediente no es facturable */
			// Nuevo registro en la 59
			bitacoraExpediente.setAnyo(tarea.getAnyo());
			bitacoraExpediente.setNumExp(tarea.getNumExp());
			EstadosExpediente estadosExpediente = new EstadosExpediente();
			estadosExpediente.setId(Long.valueOf(EstadoExpedienteEnum.CERRADO.getValue()));
			bitacoraExpediente.setEstadoExp(estadosExpediente);
			FasesExpediente fasesExpediente = new FasesExpediente();
			fasesExpediente.setId(Long.valueOf(FaseExpedienteEnum.CERRADO.getValue()));
			bitacoraExpediente.setFaseExp(fasesExpediente);

			bitacoraExpediente = this.bitacoraExpedienteDao.add(bitacoraExpediente);
		}

		// Se asocia la bitácora en la tabla 51
		final Expediente exp = new Expediente();
		exp.setAnyo(tarea.getAnyo());
		exp.setNumExp(tarea.getNumExp());
		exp.setEstadoBitacora(bitacoraExpediente.getIdEstadoBitacora());
		this.expedienteDao.updateIdEstadoBitacora(exp);

		final BitacoraSolicitante bitacoraSolicitante = new BitacoraSolicitante();
		bitacoraSolicitante.setAnyo(tarea.getAnyo());
		bitacoraSolicitante.setNumExp(tarea.getNumExp());
		Long fechaAlta = new Date().getTime();
		bitacoraSolicitante.setFechaAlta(fechaAlta);
		bitacoraSolicitante.setUsuario(Constants.IZO);
		this.bitacoraExpedienteDao.addBitacoraSolicitante(bitacoraSolicitante,
				AccionBitacoraEnum.TRABAJO_FINAL_ENTREGADO_CLIENTE.getValue());
		this.bitacoraExpedienteDao.addBitacoraSolicitante(bitacoraSolicitante,
				AccionBitacoraEnum.EXP_CERRADO.getValue());
		if (tarea.getIndFacturable()) {
			tarea.getDatosPagoProveedores().setIdTarea(tarea.getIdTarea());
			this.tareasDao.procActDatosPagoProvPL(tarea.getDatosPagoProveedores());
		}


		this.comprobarFinalizacionExpediente(tarea);
		this.procesoBatchEnvioMails(tarea, request);

		Expediente expediente = this.expedienteDao.getOrigenExpediente(exp);
		List<String> rutasPif = this.getPIF(tarea);
		if(StringUtils.isNotBlank(expediente.getOrigen()) && expediente.getOrigen().equals("W") && StringUtils.isNotBlank(expediente.getAplicacionOrigen())
				&& !AplicacionOrigenExpediente.P43.getValue().equalsIgnoreCase(expediente.getAplicacionOrigen())) {
			FinalizarTareaUtils.LOGGER.info("FinalizarTareaUtils.createCambioFecha");
			this.eventosService.createInvoiceCambioEstado(expediente, rutasPif, TipoCierreEnum.FINALIZADO.getValue());
		}

	}

	/**
	 * Revisar traducción externa:
	 *
	 * @param tarea   Tareas
	 * @param request HttpServletRequest
	 */
	public void finalizarTarea21(TareaReflection tareaReflection) {
		FinalizarTareaUtils.LOGGER.debug("Finaliza la tarea Revisar traducción externa");
		final Tareas tarea = tareaReflection.getTarea();
		if (tarea.getIndFacturable()) {
			tarea.getDatosPagoProveedores().setIdTarea(tarea.getIdTarea());
			this.tareasDao.procActDatosPagoProvPL(tarea.getDatosPagoProveedores());
		}
		final HttpServletRequest request = tareaReflection.getRequest();
		this.finalizarTareaGeneral(tarea, true);
		this.registrarAccionEjecucionTarea(tarea);
		this.comprobarFinalizacionExpediente(tarea);
		this.procesoBatchEnvioMails(tarea, request);
	}

	/**
	 * Entrega a cliente:
	 *
	 * @param tarea   Tareas
	 * @param request HttpServletRequest
	 */
	public void finalizarTarea22(TareaReflection tareaReflection) {
		FinalizarTareaUtils.LOGGER.debug("Finaliza la tarea Entrega a cliente");
		final Tareas tarea = tareaReflection.getTarea();
		final HttpServletRequest request = tareaReflection.getRequest();
		this.finalizarTareaGeneral(tarea, true);
		this.registrarAccionEjecucionTarea(tarea);

		// ponemos los documentos finales como visibles al usuario final
		DocumentoTareaAdjunto documentoTareaAdjunto = new DocumentoTareaAdjunto();
		documentoTareaAdjunto.setIdTarea(tarea.getIdTarea());
		documentoTareaAdjunto.setIndVisible(Constants.SI);
		this.documentosGeneralDao.updateIndVisible92(documentoTareaAdjunto);

		BitacoraExpediente bitacoraExpediente = new BitacoraExpediente();
		ExpedienteTradRev expedienteTradRev = new ExpedienteTradRev(tarea.getAnyo(), tarea.getNumExp());
		expedienteTradRev = this.expedienteTradRevDao.find(expedienteTradRev);
		this.expedienteTradRevDao.updateFechaEntregaReal(expedienteTradRev);

		if (expedienteTradRev.getIndPublicarBopv().equals(Constants.NO)) {
			documentoTareaAdjunto.setIdDocOriginalFinal(null);
			this.documentosGeneralDao.updateAllDocOriginalFinal(documentoTareaAdjunto);
		}

		if (Constants.SI.equals(expedienteTradRev.getIndFacturable()) && comprobarExpedienteNoFacturado(tarea)) {
			/* Si el expediente es facturable */
			// Nuevo registro en la 59
			bitacoraExpediente.setAnyo(tarea.getAnyo());
			bitacoraExpediente.setNumExp(tarea.getNumExp());
			EstadosExpediente estadosExpediente = new EstadosExpediente();
			estadosExpediente.setId(Long.valueOf(EstadoExpedienteEnum.CERRADO.getValue()));
			bitacoraExpediente.setEstadoExp(estadosExpediente);
			FasesExpediente fasesExpediente = new FasesExpediente();
			fasesExpediente.setId(Long.valueOf(FaseExpedienteEnum.PDTE_REV_FACTURACION.getValue()));
			bitacoraExpediente.setFaseExp(fasesExpediente);

			bitacoraExpediente = this.bitacoraExpedienteDao.add(bitacoraExpediente);
		} else {
			/* Si el expediente no es facturable */
			// Nuevo registro en la 59
			bitacoraExpediente.setAnyo(tarea.getAnyo());
			bitacoraExpediente.setNumExp(tarea.getNumExp());
			EstadosExpediente estadosExpediente = new EstadosExpediente();
			estadosExpediente.setId(Long.valueOf(EstadoExpedienteEnum.CERRADO.getValue()));
			bitacoraExpediente.setEstadoExp(estadosExpediente);
			FasesExpediente fasesExpediente = new FasesExpediente();
			fasesExpediente.setId(Long.valueOf(FaseExpedienteEnum.CERRADO.getValue()));
			bitacoraExpediente.setFaseExp(fasesExpediente);

			bitacoraExpediente = this.bitacoraExpedienteDao.add(bitacoraExpediente);
		}

		// Se asocia la bitácora en la tabla 51
		final Expediente exp = new Expediente();
		exp.setAnyo(tarea.getAnyo());
		exp.setNumExp(tarea.getNumExp());
		exp.setEstadoBitacora(bitacoraExpediente.getIdEstadoBitacora());
		this.expedienteDao.updateIdEstadoBitacora(exp);

		final BitacoraSolicitante bitacoraSolicitante = new BitacoraSolicitante();
		bitacoraSolicitante.setAnyo(tarea.getAnyo());
		bitacoraSolicitante.setNumExp(tarea.getNumExp());
		bitacoraSolicitante.setUsuario(Constants.IZO);
		this.bitacoraExpedienteDao.addBitacoraSolicitante(bitacoraSolicitante,
				AccionBitacoraEnum.TRABAJO_FINAL_ENTREGADO_CLIENTE.getValue());
		this.bitacoraExpedienteDao.addBitacoraSolicitante(bitacoraSolicitante,
				AccionBitacoraEnum.EXP_CERRADO.getValue());

		this.comprobarFinalizacionExpediente(tarea);
		this.procesoBatchEnvioMails(tarea, request);

		Expediente expediente = this.expedienteDao.getOrigenExpediente(exp);
		List<String> rutasPif = this.getPIF(tarea);
		if(StringUtils.isNotBlank(expediente.getOrigen()) && expediente.getOrigen().equals("W") && StringUtils.isNotBlank(expediente.getAplicacionOrigen())
				&& !AplicacionOrigenExpediente.P43.getValue().equalsIgnoreCase(expediente.getAplicacionOrigen())) {
			FinalizarTareaUtils.LOGGER.info("FinalizarTareaUtils.createCambioFecha");
			this.eventosService.createInvoiceCambioEstado(expediente, rutasPif, TipoCierreEnum.FINALIZADO.getValue());
		}
	}

	/**
	 * No conformidad traductor
	 *
	 * @param tareaReflection TareaReflection
	 */
	public void finalizarTarea23(TareaReflection tareaReflection) {
		final Tareas tarea = tareaReflection.getTarea();
		final HttpServletRequest request = tareaReflection.getRequest();
		this.finalizarTareaGeneral(tarea, true);
		this.registrarAccionEjecucionTarea(tarea);
		this.comprobarFinalizacionExpediente(tarea);
		this.procesoBatchEnvioMails(tarea, request);
	}

	/**
	 * Revision interna traductor
	 *
	 * @param tareaReflection TareaReflection
	 */
	public void finalizarTarea24(TareaReflection tareaReflection) {
		final Tareas tarea = tareaReflection.getTarea();
		final HttpServletRequest request = tareaReflection.getRequest();
		this.finalizarTareaGeneral(tarea, true);
		this.registrarAccionEjecucionTarea(tarea);
		this.comprobarFinalizacionExpediente(tarea);
		this.procesoBatchEnvioMails(tarea, request);
	}

	/**
	 * Tareas post-entrega:
	 *
	 * @param tarea   Tareas
	 * @param request HttpServletRequest
	 */
	public void finalizarTarea28(TareaReflection tareaReflection) {
		FinalizarTareaUtils.LOGGER.debug("Finaliza la tarea 28");
		this.finalizarTareaSinGestionAsociada(tareaReflection);
	}

	/**
	 *
	 * @param tarea   Tareas
	 * @param request HttpServletRequest
	 */
	private void finalizarTareaGeneral(Tareas tarea, boolean esFinalizada) {
		// General: Actualizar tabla 82 con las horas reales y el indicador a S
		EjecucionTareas ejecucionTareas = new EjecucionTareas();
		ejecucionTareas.setIdTarea(tarea.getIdTarea());

		if (tarea.getEjecucionTareas() != null) {
			ejecucionTareas = tarea.getEjecucionTareas();
		}

		if (StringUtils.isEmpty(ejecucionTareas.getIndObservaciones())) {
			ejecucionTareas.setIndObservaciones(Constants.NO);
		}

		if (esFinalizada) {
			ejecucionTareas.setIndRealizada(Constants.SI);
		}

		EjecucionTareas ejecBD = new EjecucionTareas();
		ejecBD.setIdTarea(tarea.getIdTarea());
		ejecBD = this.ejecucionTareasDao.find(ejecBD);

		ejecucionTareas.setIdTarea(tarea.getIdTarea());

		if (ejecBD != null && ejecBD.getIdTarea() != null
				&& ejecBD.getIdTarea().compareTo(Constants.MAGIC_NUMBER_0) == 1) {
			this.ejecucionTareasDao.update(ejecucionTareas);
		} else {
			this.ejecucionTareasDao.add(ejecucionTareas);
		}

		// General: Actualizar el estado de ejecución "Ejecutada" en la tabla 81
		this.tareasDao.updateTareaEjecutada(tarea.getIdTarea());
	}

	/**
	 *
	 * @param tarea   Tareas
	 * @param request HttpServletRequest
	 */
	private void finalizarTareaSinGestionAsociada(Tareas tarea, HttpServletRequest request) {
		this.finalizarTareaGeneral(tarea, false);
		this.registrarAccionEjecucionTarea(tarea);
		// Actualiza observaciones en la 85
		this.guardarObservacionesAuditables(tarea);
	}

	public void finalizarTareaSinGestionAsociada(TareaReflection tareaReflection) {
		FinalizarTareaUtils.LOGGER.debug("Finaliza la tarea finalizarTareaSinGestionAsociada");
		final Tareas tarea = tareaReflection.getTarea();
		final HttpServletRequest request = tareaReflection.getRequest();
		this.finalizarTareaSinGestionAsociada(tarea, request);
		this.comprobarFinalizacionExpediente(tarea);
		this.procesoBatchEnvioMails(tarea, request);

	}

	/**
	 *
	 * @param tarea Tareas
	 */
	private void guardarObservacionesAuditables(Tareas tarea) {
		if (tarea.getObservacionesTareas() != null
				&& StringUtils.isNotEmpty(tarea.getObservacionesTareas().getObsvEjecucion())) {
			final ObservacionesTareas observacionesTareas = new ObservacionesTareas();
			observacionesTareas.setIdTarea(tarea.getIdTarea());

			Long numReg = this.observacionesTareasDao.findAllCount(observacionesTareas);
			observacionesTareas.setObsvEjecucion(tarea.getObservacionesTareas().getObsvEjecucion());
			if (numReg > Constants.CERO) {
				this.observacionesTareasDao.update(observacionesTareas);
			} else {
				this.observacionesTareasDao.add(observacionesTareas);
			}

		}
	}

	/**
	 * Preparar interpretación / Interpretación:
	 *
	 * @param tarea   Tareas
	 * @param request HttpServletRequest
	 */
	private void finalizarTareaInterpretacion(Tareas tarea) {
		this.tareasDao.reiniciarCalculoInterpretacion(tarea);
		this.finalizarTareaGeneral(tarea, true);
		this.registrarAccionEjecucionTarea(tarea);
		/* Si todas las tareas de requeridas para el cierre están finalizadas */
		final Long countTareasIntNoFinalizadas = this.tareasDao.findAllCountInterpretacionFin(tarea);

		BitacoraExpediente bitacoraExpediente = new BitacoraExpediente();
		BitacoraExpediente bitExp = new BitacoraExpediente();
		if (countTareasIntNoFinalizadas == Long.valueOf(Constants.CERO)) {
			// Nuevo registro en la 59. -> No hay tareas de interpretación
			// sin finalizar.
			bitacoraExpediente.setAnyo(tarea.getAnyo());
			bitacoraExpediente.setNumExp(tarea.getNumExp());
			EstadosExpediente estadosExpediente = new EstadosExpediente();
			estadosExpediente.setId(Long.valueOf(EstadoExpedienteEnum.CERRADO.getValue()));
			bitacoraExpediente.setEstadoExp(estadosExpediente);
			FasesExpediente fasesExpediente = new FasesExpediente();
			fasesExpediente.setId(Long.valueOf(FaseExpedienteEnum.CERRADO.getValue()));
			bitacoraExpediente.setFaseExp(fasesExpediente);

			bitExp = this.bitacoraExpedienteDao.add(bitacoraExpediente);

			// Actualizar la 51 con el último estado guardado
			final Expediente exp = new Expediente();
			exp.setAnyo(tarea.getAnyo());
			exp.setNumExp(tarea.getNumExp());
			exp.setEstadoBitacora(bitExp.getIdEstadoBitacora());

			this.expedienteDao.updateIdEstadoBitacora(exp);

			// Actualizar bitácora solicitante en la 79
			final BitacoraSolicitante bitacoraSolicitante = new BitacoraSolicitante();
			bitacoraSolicitante.setAnyo(tarea.getAnyo());
			bitacoraSolicitante.setNumExp(tarea.getNumExp());
			bitacoraSolicitante.setUsuario(Constants.IZO);

			this.bitacoraExpedienteDao.addBitacoraSolicitante(bitacoraSolicitante,
					AccionBitacoraEnum.EXP_CERRADO.getValue());
		}
	}

	public void comprobarFinalizacionExpediente(Tareas tarea) {

		final BitacoraExpediente bitacoraExp = this.expedienteDao.findEstadoFaseExpediente(tarea.getAnyo(),
				tarea.getNumExp());

		// Comprobar si el estado del expediente es cerrado y fase cerrado...
		if (Long.valueOf(EstadoExpedienteEnum.CERRADO.getValue()) == bitacoraExp.getEstadoExp().getId()
				&& Long.valueOf(FaseExpedienteEnum.CERRADO.getValue()) == bitacoraExp.getFaseExp().getId()) {

			// Si no quedan tareas no requeridas para el cierre pendientes
			// de ejecutar -> Finalizar expediente... Nuevo registro 59 y
			// asociar al expediente
			final Long countTareasNoReqCierre = this.tareasDao.findAllCountNoReqCierreSinEjecutar(tarea);
			if (countTareasNoReqCierre == Long.valueOf(Constants.CERO)) {
				BitacoraExpediente bitacoraExpediente = new BitacoraExpediente();
				BitacoraExpediente bitExp = new BitacoraExpediente();
				bitacoraExpediente.setAnyo(tarea.getAnyo());
				bitacoraExpediente.setNumExp(tarea.getNumExp());
				EstadosExpediente estadosExpediente = new EstadosExpediente();
				estadosExpediente.setId(Long.valueOf(EstadoExpedienteEnum.FINALIZADO.getValue()));
				bitacoraExpediente.setEstadoExp(estadosExpediente);
				FasesExpediente fasesExpediente = new FasesExpediente();
				fasesExpediente.setId(Long.valueOf(FaseExpedienteEnum.FINALIZADO.getValue()));
				bitacoraExpediente.setFaseExp(fasesExpediente);

				bitExp = this.bitacoraExpedienteDao.add(bitacoraExpediente);

				// Actualizar la 51 con el último estado guardado
				final Expediente exp = new Expediente();
				exp.setAnyo(tarea.getAnyo());
				exp.setNumExp(tarea.getNumExp());
				exp.setEstadoBitacora(bitExp.getIdEstadoBitacora());

				this.expedienteDao.updateIdEstadoBitacora(exp);

			}
		}
	}

	public void comprobarDocumentacionTarea(Tareas tarea, Long idRequerimiento) {

		if (EstadoSubsanacionEnum.ACEPTADO.getValue() == tarea.getSubsanacion().getEstado()) {
			List<Tareas> listTareas = this.tareasDao.tareasAsociadasASubDoc(idRequerimiento);

			for (Tareas tareaAux : listTareas) {
				if (EstadoEjecucionTareaEnum.EJECUTADA.getValue() == tareaAux.getEstadoEjecucion()) {
					this.tareasDao.reabrirTarea(tareaAux.getIdTarea());
					this.tareasService.enviarEmailReaperturaTarea(tareaAux);
				} else {
					enviarEmailModificacionDocTarea(tareaAux);
				}
				this.actualizarDatosExpediente(tareaAux, tarea.getAnyo(), tarea.getNumExp());

			}
		}

	}

	/**
	 * @param tareaAux
	 */
	public void actualizarDatosExpediente(Tareas tarea, Long anyo, Integer numExp) {
		if (TareasServiceUtils.isTareaCrearProyTrados(tarea)) {
			BitacoraExpediente bitacora = new BitacoraExpediente();
			bitacora.setAnyo(anyo);
			bitacora.setNumExp(numExp);

			EstadosExpediente estadoExp = new EstadosExpediente();
			estadoExp.setId((long) EstadoExpedienteEnum.EN_CURSO.getValue());
			bitacora.setEstadoExp(estadoExp);

			FasesExpediente faseExp = new FasesExpediente();
			faseExp.setId((long) FaseExpedienteEnum.PDTE_PROYECTO_TRADOS.getValue());
			bitacora.setFaseExp(faseExp);

			BitacoraExpediente bitacoraAux = this.bitacoraExpedienteDao.add(bitacora);
			bitacoraAux.getIdEstadoBitacora();

			Expediente expediente = new Expediente();
			expediente.setAnyo(anyo);
			expediente.setNumExp(numExp);
			expediente.setEstadoBitacora(bitacoraAux.getIdEstadoBitacora());

			this.expedienteDao.updateIdEstadoBitacora(expediente);
		}
	}

	/**
	 * @param tarea
	 * @return
	 */
	private int enviarEmailModificacionDocTarea(Tareas tarea) {
		return this.enviarEmail(tarea, TipoAvisoEnum.DOCUMENTACION_TAREAS.getValue());
	}

	private boolean comprobarExpedienteNoFacturado(Tareas tarea) {
		return (this.expedienteFacturacionDao.comprobarExpedienteNoFacturado(tarea.getAnyo(), tarea.getNumExp()) == 0);
	}

	/**
	 * @param listaDestinatarios
	 * @param tareas
	 * @return int, Devuelve (0/-1/-2): 0 - Si el email se ha enviado correctamente;
	 *         -1 - Si el usuario no dispone de dirección de email; -2 - Si el envío
	 *         de email ha fallado.
	 */
	public int enviarEmail(Tareas tarea, int idTipoAviso) {
		List<String> listaDestinatarios = new ArrayList<String>();
		int rst = 0;

		if (this.appConfiguration.getProperty(Constants.APP_ENTORNO) != null) {
			if (Constants.LOCAL.equals(this.appConfiguration.getProperty(Constants.APP_ENTORNO))) {
				listaDestinatarios.add(Constants.DESTINATARIO_EMAIL_LOCAL);
			} else {
				listaDestinatarios.addAll(this.obtenerDestinatarios(tarea));
			}

			if (TareasServiceUtils.isNotEmptyLstDestinatarios(listaDestinatarios)) {
				ParametrosEmail parametrosEmail = new ParametrosEmail();

				Locale localeEu = new Locale(Constants.LANG_EUSKERA);
				Map<String, String> infoEu = new LinkedHashMap<String, String>();
				Locale localeEs = new Locale(Constants.LANG_CASTELLANO);
				Map<String, String> infoEs = new LinkedHashMap<String, String>();

				infoEu.put(this.messageSource.getMessage(Constants.LABEL_NUM_EXP, null, localeEu),
						tarea.getAnyoNumExpConcatenado());
				infoEs.put(this.messageSource.getMessage(Constants.LABEL_NUM_EXP, null, localeEs),
						tarea.getAnyoNumExpConcatenado());

				infoEu.put(this.messageSource.getMessage(Constants.LABEL_MODIFICACION_DOC_TAREA, null, localeEu),
						tarea.getIdTarea() + Constants.GUION_CON_ESPACIOS
								+ TareasServiceUtils.obtenerDescripcionTipoTarea(localeEu, tarea));
				infoEs.put(this.messageSource.getMessage(Constants.LABEL_MODIFICACION_DOC_TAREA, null, localeEs),
						tarea.getIdTarea() + Constants.GUION_CON_ESPACIOS
								+ TareasServiceUtils.obtenerDescripcionTipoTarea(localeEs, tarea));

				parametrosEmail.setInfoEu(infoEu);
				parametrosEmail.setInfoEs(infoEs);

				parametrosEmail.setAnyoNumExpediente(Utils.getNumExpedienteParameter(tarea));

				try {
					this.mailService.sendMailWithParameters(idTipoAviso, listaDestinatarios, parametrosEmail);
				} catch (Exception e) {
					FinalizarTareaUtils.LOGGER.info("Error en el envío de email", e);
					rst = -2;
				}
			} else {
				rst = -1;
			}
		}

		return rst;
	}

	/**
	 * @param tareas Tareas
	 * @return List<String>
	 */
	private List<String> obtenerDestinatarios(Tareas tareas) {
		final List<String> listaDestinatarios = new ArrayList<String>();
		if (TareasServiceUtils.isRecursoAsignado(tareas)) {
			// La tarea tiene asignado dni de recurso. El recurso de asignación
			// puede ser tanto interno como externo
			listaDestinatarios.add(this.obtenerEmailContacto(tareas.getPersonaAsignada().getDni()));
		} else if (TareasServiceUtils.isRecursoExterno(tareas)) {
			// El recurso de asignación es un proveedor y la tarea tiene idLote
			// asignado
			final List<DatosContacto> mailsProveedoresPorIdTarea = this.datosContactoDao
					.getMailsProveedoresPorIdTarea(tareas.getIdTarea());
			for (DatosContacto contacto : mailsProveedoresPorIdTarea) {
				listaDestinatarios.add(contacto.getEmail031());
			}

		}
		return listaDestinatarios;
	}

	/**
	 * @param dni
	 * @return
	 */
	public String obtenerEmailContacto(String dni) {
		String email = null;
		DatosContacto datosContacto = new DatosContacto();
		datosContacto.setDni031(dni);
		DatosContacto datosContactoAux = this.datosContactoDao.find(datosContacto);
		if (TareasServiceUtils.isNotNullDatosContacto(datosContactoAux)) {
			email = datosContactoAux.getEmail031();
		}
		return email;
	}

	/**
	 *
	 * @param tarea   Tareas
	 * @param request HttpServletRequest
	 */
	private void guardarDatosPagoProveedores(Tareas tarea, HttpServletRequest request) {
		final DatosPagoProveedores datosPagoProveedores = tarea.getDatosPagoProveedores();
		datosPagoProveedores.setIdTarea(tarea.getIdTarea());
		int update = this.datosPagoProveedoresDao.updateReturnInt(datosPagoProveedores);
		if (update == Constants.CERO) {
			this.datosPagoProveedoresDao.add(datosPagoProveedores);
		}
	}

	/**
	 *
	 * @param tarea   Tareas
	 * @param request HttpServletRequest
	 */
	private void guardarDatosPagoProveedoresInt(Tareas tarea, HttpServletRequest request) {
		final DatosPagoProveedoresInt datosPagoProveedoresInt = tarea.getDatosPagoProveedoresInt();
		datosPagoProveedoresInt.setIdTarea(tarea.getIdTarea());
		this.datosPagoProveedoresIntDao.add(datosPagoProveedoresInt);
	}

	/**
	 *
	 * @param tarea   Tareas
	 * @param request HttpServletRequest
	 */
	public void procesoBatchEnvioMails(Tareas tarea, HttpServletRequest request) {
		String nomSh = "ejecTarea.sh";

		final Locale locale = LocaleContextHolder.getLocale();
		final StringBuilder str = new StringBuilder();
		str.append(Constants.CONSTANTE_APLICACION).append(Constants.ESPACIO)
				.append(this.messageSource.getMessage("ejecutar", null, locale));
		this.batch.peticionBatch(request, nomSh, String.valueOf(tarea.getIdTarea()), str.toString());
	}

	/**
	 *
	 * @param tarea Tareas
	 */
	private void comprobarGuardarObservacionesAa06a(Tareas tarea) {
		// En caso de que vengan observaciones (aa06a) se guardan.
		if (tarea.getObservacionesTareas() != null
				&& StringUtils.isNotBlank(tarea.getObservacionesTareas().getObsvEjecucion())) {
			this.guardarObservacionesAuditables(tarea);
		}
	}

	/**
	 * @param tareaAux
	 */
	private void registrarAccionEjecucionTarea(Tareas tareaAux) {
		RegistroAcciones reg = new RegistroAcciones();
		reg.setIdPuntoMenu(PuntosMenuEnum.PLANIFICACION_EXPEDIENTES.getValue());
		reg.setIdAccion(Constants.ACCION_MODIFICACION);

		Locale locale = new Locale(Constants.LANG_EUSKERA);
		StringBuilder observ = new StringBuilder();
		observ.append(this.messageSource.getMessage(LABEL_EJECUCION_TAREA, null, locale)).append(" \n");
		Long anyo = tareaAux.getAnyo();
		Integer numExpediente = tareaAux.getNumExp();

		observ.append(ID).append(Constants.IGUAL).append(tareaAux.getIdTarea());
		if (TareasServiceUtils.isNotNullTipoTarea(tareaAux)) {
			observ.append(Constants.COMA_ESPACIO);
			observ.append(this.messageSource.getMessage(LABEL_TIPO_DE_TAREA, null, locale)).append(Constants.IGUAL);
			TareasServiceUtils tareasServiceUtils = new TareasServiceUtils();
			String descTipoTarea = tareasServiceUtils.obtenerDescripcionTipoTareaBD(locale, tareaAux);
			observ.append(descTipoTarea);
		}
		observ.append(" \n");

		reg.setObserv(observ.toString());
		reg.setAnyo(anyo);
		reg.setNumExp(numExpediente);
		reg.setIdEstadoBitacora(this.expedienteDao.findEstadoBitacoraExp(anyo, numExpediente));

		if (SecurityContextHolder.getContext().getAuthentication() == null && tareaAux.getPersonaAsignada() != null) {
			// Procedencia: WS
			reg.setUsuarioRegistro(tareaAux.getPersonaAsignada().getDni());
			reg.setIp(null);
		}

		this.registroAccionesDao.add(reg);
	}



	private List<String> getPIF(Tareas tarea) {
		FinalizarTareaUtils.LOGGER.debug("getPIF - INICIO");
		List<String> rutasPif = new ArrayList<String>();
		try {
			Expediente exp = new Expediente();
			exp.setAnyo(tarea.getAnyo());
			exp.setNumExp(tarea.getNumExp());
			exp = this.expedienteDao.getOrigenExpediente(exp);
			if (OrigenExpedienteEnum.WEB_SERVICE.getValue().equals(exp.getOrigen())
					&& StringUtils.isNotBlank(exp.getAplicacionOrigen())
					&& !AplicacionOrigenExpediente.P43.getValue().equalsIgnoreCase(exp.getAplicacionOrigen())) {
				// guardar documentos firmados en el pif en la ruta /aa79b/cod_aplicación/final
				StringBuilder rutaPifAplicacion = new StringBuilder();
				rutaPifAplicacion.append("/").append(exp.getAplicacionOrigen().toLowerCase());
				rutaPifAplicacion.append(this.appConfiguration.getProperty(PropertiesConstants.RUTAPIFDOCFINALPARTE1));
				rutaPifAplicacion.append(this.appConfiguration.getProperty(PropertiesConstants.RUTAPIFDOCFINALPARTE2));
				// obtenemos los id de doc finales
				List<FicheroDocExp> docsFinales = new ArrayList<FicheroDocExp>();
				if (Constants.CONSTANTE_APLICACION_TRAMITAGUNE.equalsIgnoreCase(exp.getAplicacionOrigen())) {
					docsFinales = this.documentosGeneralDao.getIdDocumentosFinalesR02t(tarea.getIdTarea());
				}else {
					docsFinales = this.documentosGeneralDao.getIdDocumentosFinales(tarea.getIdTarea());
				}
				int cont = 0;
				for (FicheroDocExp docFinal : docsFinales) {
					// obtenemos datos necesarios de fichero
					if (Constants.CONSTANTE_APLICACION_TRAMITAGUNE.equalsIgnoreCase(exp.getAplicacionOrigen()) && cont == Constants.CERO) {
						docFinal = this.documentosGeneralDao.getDatosFicheroOriginal(docFinal);
						cont++;
					}else {
						docFinal = this.documentosGeneralDao.getDatosFichero(docFinal);
					}
					// obtenemos el cuerpo del fichero
					byte[] documentoPrueba = this.pidService.getDocument(docFinal.getOid());
					// subimos al pif el fichero
					StringBuilder nombreDocumento = new StringBuilder();
					nombreDocumento.append(String.valueOf(tarea.getAnyo()).substring(2,4)).append(String.format("%06d", tarea.getNumExp())).append("_").append(docFinal.getNombre());
					String rutaPif = this.pidService.subidaPif(nombreDocumento.toString(), documentoPrueba,
							rutaPifAplicacion.toString(), true);
					rutasPif.add(rutaPif);
				}
			}
		} catch (Exception e) {
			FinalizarTareaUtils.LOGGER.error("ERROR getPIF: ", e);
			throw new AppRuntimeException(e.getMessage());
		}
		FinalizarTareaUtils.LOGGER.debug("getPIF - FIN");
		return rutasPif;
	}
}
