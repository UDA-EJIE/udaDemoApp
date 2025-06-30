package com.ejie.aa79b.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.dao.AnulacionExpedienteDao;
import com.ejie.aa79b.dao.AnulacionesDao;
import com.ejie.aa79b.dao.BitacoraExpedienteDao;
import com.ejie.aa79b.dao.ExpedienteDao;
import com.ejie.aa79b.dao.GenericoDao;
import com.ejie.aa79b.dao.MotivosAnulacionDao;
import com.ejie.aa79b.dao.ObservacionesAnulExpDao;
import com.ejie.aa79b.dao.RegistroAccionesDao;
import com.ejie.aa79b.dao.TareasDao;
import com.ejie.aa79b.mail.MailService;
import com.ejie.aa79b.model.AnulacionExpediente;
import com.ejie.aa79b.model.BitacoraExpediente;
import com.ejie.aa79b.model.BitacoraSolicitante;
import com.ejie.aa79b.model.EstadosExpediente;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.FasesExpediente;
import com.ejie.aa79b.model.MotivosAnulacion;
import com.ejie.aa79b.model.ObservacionesAnulExp;
import com.ejie.aa79b.model.ParametrosEmail;
import com.ejie.aa79b.model.RegistroAcciones;
import com.ejie.aa79b.model.Tareas;
import com.ejie.aa79b.model.enums.AccionBitacoraEnum;
import com.ejie.aa79b.model.enums.AccionesEnum;
import com.ejie.aa79b.model.enums.EstadoExpedienteEnum;
import com.ejie.aa79b.model.enums.FaseExpedienteEnum;
import com.ejie.aa79b.model.enums.MotivosAnulacionEnum;
import com.ejie.aa79b.model.enums.PuntosMenuEnum;
import com.ejie.aa79b.model.enums.TipoAvisoEnum;
import com.ejie.aa79b.model.enums.TipoCierreEnum;
import com.ejie.aa79b.model.enums.TipoRecursoEnum;
import com.ejie.aa79b.utils.ExpedienteUtils;
import com.ejie.aa79b.utils.TareasServiceUtils;
import com.ejie.aa79b.utils.Utils;
import com.ejie.aa79b.webservices.EventosService;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;

@Service(value = "anulacionesService")
public class AnulacionesServiceImpl extends GenericoServiceImpl<Expediente> implements AnulacionesService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AnulacionesServiceImpl.class);

	private static final String LABEL_TAREA = "label.tarea";

	@Autowired()
	private AnulacionesDao anulacionesDao;
	@Autowired()
	private MotivosAnulacionDao motivosAnulacionDao;
	@Autowired()
	private AnulacionExpedienteDao anulacionExpedienteDao;
	@Autowired()
	private ObservacionesAnulExpDao observacionesAnulExpDao;
	@Autowired()
	private BitacoraExpedienteDao bitacoraExpedienteDao;
	@Autowired()
	private ExpedienteDao expedienteDao;
	@Autowired()
	private RegistroAccionesDao registroAccionesDao;
	@Autowired()
	private TareasDao tareasDao;
	@Autowired()
	private ReloadableResourceBundleMessageSource msg;
	@Autowired()
	private Properties appConfiguration;
	@Autowired()
	private MailService mailService;
	@Autowired()
	private TareasService tareasService;
	@Autowired
	private EventosService eventosService;

	@Override()
	protected GenericoDao<Expediente> getDao() {
		return this.anulacionesDao;
	}

	@Override()
	public JQGridResponseDto<Expediente> busquedaexpaanular(Expediente filter, JQGridRequestDto jqGridRequestDto,
			boolean isCount) {
		@SuppressWarnings("unchecked")
		List<Expediente> listaExpedientes = (List<Expediente>) this.anulacionesDao.busquedaexpaanular(filter,
				jqGridRequestDto, false, false);

		Long recordNum = 0L;

		if (listaExpedientes != null && !listaExpedientes.isEmpty()) {
			recordNum = (Long) this.anulacionesDao.busquedaexpaanular(filter, jqGridRequestDto, false, true);
		}

		if (jqGridRequestDto != null) {
			return new JQGridResponseDto<Expediente>(jqGridRequestDto, recordNum, listaExpedientes);
		} else {
			return new JQGridResponseDto<Expediente>(new JQGridRequestDto(), recordNum, listaExpedientes);
		}
	}

	@Override()
	public void anularExpedientes(AnulacionExpediente anulacionExpediente, List<Expediente> listExpedientes) {
		anularExpedientes(anulacionExpediente, listExpedientes, false);
	}

	@Override()
	public void anularExpediente(AnulacionExpediente anulacionExpediente, Expediente expediente) {
		List<Expediente> listExpedientes = new ArrayList<Expediente>();
		listExpedientes.add(expediente);

		anularExpedientes(anulacionExpediente, listExpedientes, true);
	}

	private void anularExpedientes(AnulacionExpediente anulacionExpediente, List<Expediente> listExpedientes,
			boolean isCambioEstadoExp) {

		MotivosAnulacion motivosAnulacion = new MotivosAnulacion();
		motivosAnulacion.setId012(anulacionExpediente.getIdMotivoAnulacion());
		motivosAnulacion = this.motivosAnulacionDao.find(motivosAnulacion);

		for (Expediente expediente : listExpedientes) {

			// Registro de acciones (Tabla 43)
			this.registrarAcciones(expediente, anulacionExpediente.getDescMotivoAnulacion(), isCambioEstadoExp);

			// Tabla A1
			AnulacionExpediente anulExp = this.anulacionExpedienteDao.add(anulacionExpediente);

			Long idAnulExp = anulExp.getId();

			ObservacionesAnulExp obsAnulExp = anulacionExpediente.getObservacionesAnulExp();

			if (anulExp.getObservacionesAnulExp() != null
					&& anulExp.getObservacionesAnulExp().getObsvAnulacion() != null) {
				// Tabla A2
				obsAnulExp.setId(idAnulExp);
				// introduzco el motivo de la anulacion en el expediente para
				// que vaya en el email
				expediente.setMotivosAnulacion(motivosAnulacion);
				this.observacionesAnulExpDao.add(obsAnulExp);
			}

			actualizarBitacoras(expediente, idAnulExp);

			expediente = this.expedienteDao.getOrigenExpediente(expediente);
			List<String> rutasPif = new ArrayList<String>();
			if(StringUtils.isNotBlank(expediente.getOrigen()) && expediente.getOrigen().equals("W") && StringUtils.isNotBlank(expediente.getAplicacionOrigen())) {
				AnulacionesServiceImpl.LOGGER.info("AnulacionesServiceImpl.createCambioFecha");
				expediente.setMotivosAnulacion(motivosAnulacion);
				this.eventosService.createInvoiceCambioEstado(expediente, rutasPif, TipoCierreEnum.ANULADO.getValue());
			}

			// Enviar email al gestor del expediente

			this.enviarEmail(expediente, obsAnulExp);



		}

	}

	/**
	 * Se registra una nueva accion en la tabla 43 (Registro de acciones)
	 *
	 * @param bean                Expediente
	 * @param descMotivoAnulacion String
	 */
	public void registrarAcciones(Expediente bean, String descMotivoAnulacion, boolean isCambioEstadoExp) {
		RegistroAcciones reg = new RegistroAcciones();
		Long idPuntoMenu;
		if (isCambioEstadoExp) {
			idPuntoMenu = PuntosMenuEnum.SERVICIO_CAMBIO_ESTADO_EXP.getValue();
		} else {
			idPuntoMenu = PuntosMenuEnum.PLANIFICACION_EXPEDIENTES.getValue();
		}
		reg.setIdPuntoMenu(idPuntoMenu);
		reg.setIdAccion(AccionesEnum.MODIFICACION.getValue());

		Locale locale = new Locale(Constants.LANG_EUSKERA);
		StringBuilder observ = new StringBuilder();
		observ.append(this.msg.getMessage("mensaje.expedienteAnulado", null, locale)).append(" ");
		observ.append(descMotivoAnulacion).append(" \n");
		reg.setAnyo(bean.getAnyo());
		reg.setNumExp(bean.getNumExp());
		reg.setIdEstadoBitacora(this.expedienteDao.findEstadoBitacoraExp(bean.getAnyo(), bean.getNumExp()));
		reg.setObserv(observ.toString());
		this.registroAccionesDao.add(reg);
	}

	/**
	 * Realiza el envío de email para un expediente anulado
	 *
	 * @param expediente Expediente
	 */
	private int enviarEmail(Expediente expediente, ObservacionesAnulExp obsAnulExp) {
		List<String> listaDestinatarios = new ArrayList<String>();
		int rst = 0;

		if (this.appConfiguration.getProperty(Constants.APP_ENTORNO) != null) {
			if (Constants.LOCAL.equals(this.appConfiguration.getProperty(Constants.APP_ENTORNO))) {
				listaDestinatarios.add(Constants.DESTINATARIO_EMAIL_LOCAL);
			} else {
				ExpedienteUtils expedienteUtils = new ExpedienteUtils();
				listaDestinatarios.add(expedienteUtils.obtenerEmailContacto(expediente));
			}

			if (TareasServiceUtils.isNotEmptyLstDestinatarios(listaDestinatarios)) {
				ParametrosEmail parametrosEmail = new ParametrosEmail();

				Locale localeEu = new Locale(Constants.LANG_EUSKERA);
				Map<String, String> infoEu = new LinkedHashMap<String, String>();
				Locale localeEs = new Locale(Constants.LANG_CASTELLANO);
				Map<String, String> infoEs = new LinkedHashMap<String, String>();

				infoEu.put(this.msg.getMessage(Constants.LABEL_NUM_EXP, null, localeEu),
						expediente.getAnyoNumExpConcatenado());
				infoEs.put(this.msg.getMessage(Constants.LABEL_NUM_EXP, null, localeEs),
						expediente.getAnyoNumExpConcatenado());

				if (expediente.getMotivosAnulacion() != null
						&& expediente.getMotivosAnulacion().getDescEstadoEu() != null) {
					// obtenemos el motivo y las observaciones de la anulacion
					StringBuilder infoMotivEu = new StringBuilder();
					StringBuilder infoMotivEs = new StringBuilder();

					infoMotivEu.append(expediente.getMotivosAnulacion().getDescEu012()).append(". ");
					infoMotivEs.append(expediente.getMotivosAnulacion().getDescEs012()).append(". ");
					if (obsAnulExp.getObsvAnulacion() != null) {
						infoMotivEu.append(obsAnulExp.getObsvAnulacion());
						infoMotivEs.append(obsAnulExp.getObsvAnulacion());
					}
					infoEu.put(this.msg.getMessage("label.motivosAnulacion", null, localeEu), infoMotivEu.toString());
					infoEs.put(this.msg.getMessage("label.motivosAnulacion", null, localeEs), infoMotivEs.toString());
				}

				parametrosEmail.setInfoEu(infoEu);
				parametrosEmail.setInfoEs(infoEs);

				parametrosEmail.setAnyoNumExpediente(Utils.getNumExpedienteParameter(expediente));

				try {
					this.mailService.sendMailWithParameters(TipoAvisoEnum.ANULADO_EXP.getValue(), listaDestinatarios,
							parametrosEmail);
				} catch (Exception e) {
					AnulacionesServiceImpl.LOGGER.info("Error en el envío de email", e);
					rst = -2;
				}
			} else {
				rst = -1;
			}
		}

		return rst;
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void anularExpAutomaticamente(Long anyo, Integer numExp, String obsvAnulacion) {

		Expediente expediente = new Expediente();
		expediente.setAnyo(anyo);
		expediente.setNumExp(numExp);

		// Tabla A1
		AnulacionExpediente anulacionExpediente = new AnulacionExpediente();
		anulacionExpediente.setIdMotivoAnulacion((long) MotivosAnulacionEnum.PRESUPUESTO_RECHAZADO.getValue());
		AnulacionExpediente anulExp = this.anulacionExpedienteDao.add(anulacionExpediente);
		Long idAnulExp = anulExp.getId();
		ObservacionesAnulExp observacionesanulexp = new ObservacionesAnulExp();
		observacionesanulexp.setId(idAnulExp);
		observacionesanulexp.setObsvAnulacion(obsvAnulacion);
		this.observacionesAnulExpDao.add(observacionesanulexp);

		actualizarBitacoras(expediente, idAnulExp);

		// Envio de emails con tareas aceptadas y pdtes de
		// ejecutar
		this.enviarEmailTareasAceptadasPptoRechazado(expediente);

		// Desasignar las tareas pendientes de ejecución, borrar datos de observ
		// (T84) eliminar del calendario personal
		this.tareasDao.desasignarTareasPdtesEjec(anyo, numExp);

	}

	/**
	 * Actualiza la bitacora del expediente y la bitacora del solicitante
	 *
	 * @param expediente Expediente
	 * @param idAnulExp  Long
	 */
	private void actualizarBitacoras(Expediente expediente, Long idAnulExp) {
		// Actualizar la bitacora (Tabla 59)
		BitacoraExpediente bitacoraExpediente = new BitacoraExpediente();
		bitacoraExpediente.setAnyo(expediente.getAnyo());
		bitacoraExpediente.setNumExp(expediente.getNumExp());
		EstadosExpediente estadosExpediente = new EstadosExpediente();
		estadosExpediente.setId((long) EstadoExpedienteEnum.ANULADO.getValue());
		bitacoraExpediente.setEstadoExp(estadosExpediente);
		FasesExpediente fasesExpediente = new FasesExpediente();
		fasesExpediente.setId((long) FaseExpedienteEnum.ANULADO.getValue());
		bitacoraExpediente.setFaseExp(fasesExpediente);
		bitacoraExpediente.setIdMotivoAnulacion(idAnulExp);

		BitacoraExpediente bitacoraExp = this.bitacoraExpedienteDao.add(bitacoraExpediente);
		expediente.setEstadoBitacora(bitacoraExp.getIdEstadoBitacora());

		// Actualizar el estado de la bitacora del expediente (Tabla 51)
		this.expedienteDao.updateIdEstadoBitacora(expediente);

		// Actualizar bitacora solicitante (Tabla 79)
		BitacoraSolicitante bitacoraSolicitante = new BitacoraSolicitante();
		bitacoraSolicitante.setAnyo(expediente.getAnyo());
		bitacoraSolicitante.setNumExp(expediente.getNumExp());
		bitacoraSolicitante.setUsuario(Constants.IZO);
		this.bitacoraExpedienteDao.addBitacoraSolicitante(bitacoraSolicitante,
				AccionBitacoraEnum.EXP_ANULADO.getValue());
	}

	/**
	 * Realiza el envío de email para las tareas aceptadas de un expediente anulado
	 *
	 * @param anyo
	 * @param numExp
	 * @return
	 */
	@Override
	public int enviarEmailTareasAceptadasPptoRechazado(Expediente expediente) {

		int rst = 0;

		// Primero recuperar la lista de tareas no ejec y asignadas
		List<Tareas> listaTareas = this.tareasDao.getTareasAsignadasNoEjecutadas(expediente.getAnyo(),
				expediente.getNumExp());
		List<String> listaDestinatarios = new ArrayList<String>();

		Locale localeEu = new Locale(Constants.LANG_EUSKERA);
		Locale localeEs = new Locale(Constants.LANG_CASTELLANO);

		// vamos a hacer un bucle con esas tareas
		for (Tareas unaTarea : listaTareas) {

			if (this.appConfiguration.getProperty(Constants.APP_ENTORNO) != null) {
				if (Constants.LOCAL.equals(this.appConfiguration.getProperty(Constants.APP_ENTORNO))) {
					listaDestinatarios.add(Constants.DESTINATARIO_EMAIL_LOCAL);
				} else {
					// por cada tarea del bucle, mandar 1 email por tarea y el
					// destinatario
					// lo sacaré de TareasService.obtenerDestinatarios( tarea)
					listaDestinatarios = this.tareasService.obtenerDestinatarios(unaTarea);
				}

				if (TareasServiceUtils.isNotEmptyLstDestinatarios(listaDestinatarios)) {
					ParametrosEmail parametrosEmail = new ParametrosEmail();

					Map<String, String> infoEu = new LinkedHashMap<String, String>();
					Map<String, String> infoEs = new LinkedHashMap<String, String>();

					infoEu.put(this.msg.getMessage(LABEL_TAREA, null, localeEu),
							unaTarea.getIdTarea() + Constants.GUION_CON_ESPACIOS
									+ TareasServiceUtils.obtenerDescripcionTipoTarea(localeEu, unaTarea)
									+ Constants.ABRIR_PARENTESIS
									+ this.msg.getMessage(Constants.LABEL_NUM_EXP, null, localeEu)
									+ expediente.getAnyoNumExpConcatenado() + Constants.CERRAR_PARENTESIS);
					infoEu.put(this.msg.getMessage(LABEL_TAREA, null, localeEs),
							unaTarea.getIdTarea() + Constants.GUION_CON_ESPACIOS
									+ TareasServiceUtils.obtenerDescripcionTipoTarea(localeEs, unaTarea)
									+ Constants.ABRIR_PARENTESIS
									+ this.msg.getMessage(Constants.LABEL_NUM_EXP, null, localeEs)
									+ expediente.getAnyoNumExpConcatenado() + Constants.CERRAR_PARENTESIS);

					parametrosEmail.setInfoEu(infoEu);
					parametrosEmail.setInfoEs(infoEs);

					parametrosEmail.setAnyoNumExpediente(Utils.getNumExpedienteParameter(expediente));

					rst = envioEmailTareasAceptadas(rst, listaDestinatarios, unaTarea, parametrosEmail);
				} else {
					rst = -1;
				}
			}
		}
		return rst;
	}

	/**
	 * @param rst
	 * @param listaDestinatarios
	 * @param unaTarea
	 * @param parametrosEmail
	 * @return
	 */
	private int envioEmailTareasAceptadas(int rst, List<String> listaDestinatarios, Tareas unaTarea,
			ParametrosEmail parametrosEmail) {
		int tipoAviso, rstAux = rst;
		if (TipoRecursoEnum.INTERNO.getValue().equals(unaTarea.getRecursoAsignacion())) {
			tipoAviso = TipoAvisoEnum.ELIMINAR_TAREAS.getValue();
		} else {
			tipoAviso = TipoAvisoEnum.ELIMINAR_TAREAS_PROVEEDOR.getValue();
		}
		try {
			this.mailService.sendMailWithParameters(tipoAviso, listaDestinatarios, parametrosEmail);

		} catch (Exception e) {
			AnulacionesServiceImpl.LOGGER.info("Error en el envío de email enviarEmailTareasAceptadasPptoRechazado", e);
			rstAux = -2;
		}
		return rstAux;
	}

}
