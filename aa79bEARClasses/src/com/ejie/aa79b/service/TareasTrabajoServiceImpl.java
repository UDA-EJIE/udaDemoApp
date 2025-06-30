package com.ejie.aa79b.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.dao.DatosContactoDao;
import com.ejie.aa79b.dao.GenericoDao;
import com.ejie.aa79b.dao.RegistroAccionesDao;
import com.ejie.aa79b.dao.TareasTrabajoDao;
import com.ejie.aa79b.dao.TiposTareaDao;
import com.ejie.aa79b.mail.MailService;
import com.ejie.aa79b.model.DatosContacto;
import com.ejie.aa79b.model.DatosPersona;
import com.ejie.aa79b.model.DocumentoTareaAdjunto;
import com.ejie.aa79b.model.OtrosTrabajos;
import com.ejie.aa79b.model.ParametrosEmail;
import com.ejie.aa79b.model.Persona;
import com.ejie.aa79b.model.PersonalIZO;
import com.ejie.aa79b.model.RegistroAcciones;
import com.ejie.aa79b.model.Tareas;
import com.ejie.aa79b.model.TareasTrabajo;
import com.ejie.aa79b.model.TiposTarea;
import com.ejie.aa79b.model.enums.EstadoAceptacionTareaEnum;
import com.ejie.aa79b.model.enums.PuntosMenuEnum;
import com.ejie.aa79b.model.enums.TipoAvisoEnum;
import com.ejie.aa79b.security.Usuario;
import com.ejie.aa79b.utils.GeneralUtils;
import com.ejie.aa79b.utils.TareasServiceUtils;
import com.ejie.aa79b.utils.TareasUtils;
import com.ejie.aa79b.utils.Utils;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;

@Service(value = "tareasTrabajoService")
public class TareasTrabajoServiceImpl extends GenericoServiceImpl<TareasTrabajo> implements TareasTrabajoService {

	@Autowired
	private TareasTrabajoDao tareasTrabajoDao;

	@Autowired
	private OtrosTrabajosService otrosTrabajosService;

	@Autowired()
	private DatosContactoDao datosContactoDao;

	@Override
	protected GenericoDao<TareasTrabajo> getDao(){
		return this.tareasTrabajoDao;
	}

	 @Autowired()
	private DatosPersonaService datosPersonaService;

    @Autowired()
	private PersonalIZOService personalIZOService;

    @Autowired()
	private TiposTareaDao tiposTareasDao;

    @Autowired()
	private ReloadableResourceBundleMessageSource msg;

	@Autowired()
	private Properties appConfiguration;

	@Autowired()
	private MailService mailService;

	@Autowired
	private DocumentosGeneralService documentosGeneralService;
	@Autowired()
	private RegistroAccionesDao registroAccionesDao;

	private static final String LABEL_TAREA = "label.tarea";
	private static final String ERROR_ENVIO_EMAIL = "Error en el envío de email";

	private static final Logger LOGGER = LoggerFactory.getLogger(TareasTrabajoServiceImpl.class);

	@Override()
	@Transactional(rollbackFor = Throwable.class)
	public int crearTarea(TareasTrabajo tarea) {
		int rst = 0;
		if (tarea!= null) {
				rst = crearActualizarTarea(tarea);
		}
		return rst;
	}


	private void asignarDatosAsignacionTarea(TareasTrabajo tarea) {
		PersonalIZO personaAsignador = new PersonalIZO();
		if (StringUtils.isNotEmpty(tarea.getPersonaAsignada().getDni())) {
			tarea.setFechaAsignacion(new Date());
			final Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication()
					.getCredentials();
			personaAsignador.setDni(credentials.getNif());
		} else {
			tarea.setFechaAsignacion(null);
			personaAsignador.setDni(null);
		}
		tarea.setPersonaAsignador(personaAsignador);
	}

	private void asignarDatosAceptacionTarea(TareasTrabajo tarea) {
		final Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication()
				.getCredentials();
		if (StringUtils.isNotEmpty(tarea.getPersonaAsignada().getDni()) && credentials.getNif().equals(tarea.getPersonaAsignada().getDni())) {
			tarea.setFechaAceptacion(new Date());
			tarea.setEstadoAsignado(EstadoAceptacionTareaEnum.ACEPTADA.getValue());
		} else if(StringUtils.isNotEmpty(tarea.getPersonaAsignada().getDni())){
			tarea.setFechaAceptacion(null);
			tarea.setEstadoAsignado(EstadoAceptacionTareaEnum.PENDIENTE_ACEPTACION.getValue());
		}
	}

	public void asignarDatosAceptacion(TareasTrabajo tarea, TareasTrabajo original) {
		if (!tarea.getPersonaAsignada().getDni().equals(
				StringUtils.isNotEmpty(original.getPersonaAsignada().getDni()) ? original.getPersonaAsignada().getDni() : "")) {
			asignarDatosAceptacionTarea(tarea);
			// Se eliminan las observaciones de rechazo para la tarea
			// correspondiente
			this.tareasTrabajoDao.removeObsrvRechazo(tarea.getIdTarea());
		} else {
			tarea.setEstadoAsignado(original.getEstadoAsignado());
		}
	}



	/**
	 * @param tareaAux
	 * @return int
	 */
	private int envioEmailAsignacionTarea(TareasTrabajo tareaAux) {
		return this.enviarEmailAsignacionTarea(tareaAux, null);
	}

	/**
	 * @param tarea
	 * @param original
	 * @return int
	 */
	private int envioEmailAsignacionTarea(TareasTrabajo tarea, TareasTrabajo original) {
		return this.enviarEmailAsignacionTarea(tarea, original);
	}


	/**
	 * @param tareas
	 * @param object
	 * @return int
	 */
	private int enviarEmailAsignacionTarea(TareasTrabajo tareas, TareasTrabajo tareaOriginal) {
		// comprobar que tiene tarea original y con recurso asignacion
		int rst = 0;

		if ((tareaOriginal != null && StringUtils.isNotEmpty(tareaOriginal.getPersonaAsignada().getDni())) && (StringUtils.isEmpty(tareas.getPersonaAsignada().getDni())
				|| !tareas.getPersonaAsignada().getDniCompleto().equals(tareaOriginal.getPersonaAsignada().getDniCompleto()))) {
			// enviar email de eliminacion de tarea a asignado anterior
			tareaOriginal.setTipoTarea(this.tiposTareasDao.find(tareaOriginal.getTipoTarea()));
			tareaOriginal.setTrabajo(this.otrosTrabajosService.find(tareaOriginal.getTrabajo()));

			this.enviarEmailEliminacionTareas(tareaOriginal);
		}

		if (!tareas.getPersonaAsignada().getDniCompleto().equals(tareas.getPersonaAsignador().getDniCompleto())
				&& StringUtils.isNotEmpty(tareas.getPersonaAsignada().getDni())) {
			if (tareaOriginal == null
					|| tareaOriginal != null &&  StringUtils.isNotEmpty(tareaOriginal.getPersonaAsignada().getDni())
							&& !tareas.getPersonaAsignada().getDniCompleto().equals(tareaOriginal.getPersonaAsignada().getDniCompleto())
					|| StringUtils.isEmpty(tareaOriginal.getPersonaAsignada().getDni())) {
				rst = enviarEmail(tareas, TipoAvisoEnum.ASIGNAR_TAREA.getValue());
			}
		}
		return rst;
	}

	@Override()
	public int enviarEmailEliminacionTareas(TareasTrabajo tareas) {
		final Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication().getCredentials();

		int rst = 0;
		if ((tareas.getPersonaAsignada().getDni() != null
						&& !credentials.getNif().equals(tareas.getPersonaAsignada().getDni()))) {
			rst = enviarEmail(tareas, TipoAvisoEnum.ELIMINAR_TAREAS.getValue());
		}
		return rst;
	}

	/**
	 * @param listaDestinatarios
	 * @param tareas
	 * @return int, Devuelve (0/-1/-2): 0 - Si el email se ha enviado correctamente;
	 *         -1 - Si el usuario no dispone de dirección de email; -2 - Si el envío
	 *         de email ha fallado.
	 */
	private int enviarEmail(TareasTrabajo tarea, int idTipoAviso) {
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

				infoEu.put(this.msg.getMessage(LABEL_TAREA, null, localeEu), tarea.getIdTarea()
						+ Constants.GUION_CON_ESPACIOS + tarea.getTipoTarea().getDescEu015()
						+ Constants.ABRIR_PARENTESIS + this.msg.getMessage(Constants.LABEL_NUM_TRABAJO, null, localeEu)
						+ Constants.ESPACIO+ tarea.getTrabajo().getIdTrabajo() + Constants.GUION_CON_ESPACIOS+ tarea.getTrabajo().getDescTrabajo() + Constants.CERRAR_PARENTESIS);
				infoEs.put(this.msg.getMessage(LABEL_TAREA, null, localeEs), tarea.getIdTarea()
						+ Constants.GUION_CON_ESPACIOS + tarea.getTipoTarea().getDescEs015()
						+ Constants.ABRIR_PARENTESIS + this.msg.getMessage(Constants.LABEL_NUM_TRABAJO, null, localeEs)
						+ Constants.ESPACIO+ tarea.getTrabajo().getIdTrabajo()  + Constants.GUION_CON_ESPACIOS+ tarea.getTrabajo().getDescTrabajo() + Constants.CERRAR_PARENTESIS);

				parametrosEmail.setInfoEu(infoEu);
				parametrosEmail.setInfoEs(infoEs);

				parametrosEmail.setNumTrabajo(Utils.getNumTrabajoAsunto(tarea));

				try {
					this.mailService.sendMailWithParameters(idTipoAviso, listaDestinatarios, parametrosEmail);
				} catch (Exception e) {
					TareasTrabajoServiceImpl.LOGGER.info(ERROR_ENVIO_EMAIL, e);
					rst = -2;
				}
			} else {
				TareasTrabajoServiceImpl.LOGGER.info("La lista de destinatarios esta vacia");
				rst = -1;
			}
		}

		return rst;
	}


	@Override()
	public List<String> obtenerDestinatarios(TareasTrabajo tareas) {
		final List<String> listaDestinatarios = new ArrayList<String>();
		// La tarea tiene asignado dni de recurso. El recurso de asignación
		// puede ser tanto interno como externo
		if (StringUtils.isNotEmpty(tareas.getPersonaAsignada().getDni()) &&
				GeneralUtils.emailValido(this.obtenerEmailContacto(tareas.getPersonaAsignada().getDni()))) {
			listaDestinatarios.add(this.obtenerEmailContacto(tareas.getPersonaAsignada().getDni()));
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
	 * @param tarea
	 * @param documentosSelect
	 * @return int
	 */
	private int crearActualizarTarea(TareasTrabajo tarea) {
		int rst = 0;
		if (tarea.getIdTarea() == null) {
			// Creamos una nueva tarea
			//creamos los datos de asignacion
			asignarDatosAsignacionTarea(tarea);
			asignarDatosAceptacionTarea(tarea);
			TareasTrabajo tareaAux = addTarea(tarea);
			// Lista de tareas utilizada para el registro de acciones
			List<TareasTrabajo> listTareas = new ArrayList<TareasTrabajo>();
			if (tareaAux != null && tareaAux.getIdTarea()!= null) {
				listTareas.add(tareaAux);
			}
			tareaAux.setTipoTarea(this.tiposTareasDao.find(tareaAux.getTipoTarea()));
			tareaAux.setTrabajo(this.otrosTrabajosService.find(tareaAux.getTrabajo()));
			// Se envía mail de aviso si la tarea está asignada a un recurso
			rst = this.envioEmailAsignacionTarea(tareaAux);
		} else {
			// Modificamos la tarea
			rst = updateTarea(tarea);
		}

		// Insertamos al asignador de la tarea en la foto de personas por si no
		// esta.
		if (tarea.getPersonaAsignador().getDni() != null) {
			boolean esta = this.datosPersonaService.comprobarPersona(tarea.getPersonaAsignador().getDni());
			if (!esta) {
				PersonalIZO personalIZO = new PersonalIZO();
				personalIZO.setDni(tarea.getPersonaAsignador().getDni());
				PersonalIZO personalAInsertar = this.personalIZOService.find(personalIZO);
				DatosPersona personaFoto = new DatosPersona();
				personaFoto.setDni(tarea.getPersonaAsignador().getDni());
				personaFoto.setSufDni(personalAInsertar.getSufDni());
				personaFoto.setPreDni(personalAInsertar.getPreDni());
				personaFoto.setTipIden(personalAInsertar.getTipIden());
				personaFoto.setNombre(personalAInsertar.getNombre());
				personaFoto.setApellido1(personalAInsertar.getApellido1());
				personaFoto.setApellido2(personalAInsertar.getApellido2());
				this.datosPersonaService.add(personaFoto);
			}
		}

		return rst;
	}

	/**
	 * @param tarea
	 * @param documentosSelect
	 * @return
	 */
	private TareasTrabajo addTarea(TareasTrabajo tarea) {

		TareasTrabajo tareaAux = this.tareasTrabajoDao.add(tarea);
		if (tareaAux!= null && tareaAux.getIdTarea() != null) {
			BigDecimal idTarea = tareaAux.getIdTarea();
			//añadimos el documento de la tarea
			DocumentoTareaAdjunto documentoTarea = tarea.getDocumentoEntrada();
			documentoTarea.setIdTarea(idTarea);
			this.addDocumentosTarea(documentoTarea);

		}
		return tareaAux;
	}

	private void addDocumentosTarea(DocumentoTareaAdjunto documentoTareaAdjunto) {
		if (StringUtils.isNotEmpty(documentoTareaAdjunto.getOid())) {
			// crear 88 y tabla intermedia
			this.documentosGeneralService.addDocumentoTareaTrabajo(documentoTareaAdjunto, "C8");
		}
	}

	@Transactional(rollbackFor = Throwable.class)
	private int updateTarea(TareasTrabajo tarea) {
		int rst = 0;

		if (tarea != null) {
			TareasTrabajo original = this.tareasTrabajoDao.findConfTarea(tarea);
			asignarDatosAsignacionTarea(tarea);
			asignarDatosAceptacion(tarea, original);
			this.tareasTrabajoDao.update(tarea);

			BigDecimal idTarea = tarea.getIdTarea();
			//añadimos el documento de la tarea
			DocumentoTareaAdjunto documentoTarea = tarea.getDocumentoEntrada();
			documentoTarea.setIdTarea(idTarea);
			this.addDocumentosTarea(documentoTarea);

			tarea.setTipoTarea(this.tiposTareasDao.find(tarea.getTipoTarea()));
			tarea.setTrabajo(this.otrosTrabajosService.find(tarea.getTrabajo()));


			if( !tarea.getFechaHoraInicio().equals(original.getFechaHoraInicio())
			|| !tarea.getFechaHoraFin().equals(original.getFechaHoraFin())
			|| tarea.getDocumentoEntrada().getOid()!= null){
				// Se envía mail de aviso de modificación de la documentación de
				// la tarea
				rst = this.enviarEmailModificacionDocTarea(tarea, original);
			}


			// Se envía mail de aviso si la tarea está asignada a un recurso
			if (rst == 0) {
				rst = envioEmailAsignacionTarea(tarea, original);
			} else {
				// No se asigna el valor rst devuelto por la función
				// envioEmailAsignacionTarea, en caso de que hayamos
				// obtenido
				// un error previamente
				envioEmailAsignacionTarea(tarea, original);
			}
		}

		return rst;
	}

	/**
	 * @param tareas
	 * @return int
	 */
	private int enviarEmailModificacionDocTarea(TareasTrabajo tareas, TareasTrabajo original) {
		if (StringUtils.isNotEmpty(tareas.getPersonaAsignada().getDni()) && tareas.getPersonaAsignada().getDni().equals(
				original.getPersonaAsignada().getDni() != null ? original.getPersonaAsignada().getDni() : "")) {
			return enviarEmail(tareas, TipoAvisoEnum.MODIFICAR_DATOS_TAREA.getValue());
		}
		return 0;
	}



	@Override
	public TareasTrabajo findConfTarea(TareasTrabajo bean) {
		return this.tareasTrabajoDao.findConfTarea(bean);
	}

	@Override()
	public int reabrirTarea(BigDecimal idTarea) {
		TareasTrabajo tarea = new TareasTrabajo();
		tarea.setIdTarea(idTarea);
		TareasTrabajo tareaAux = this.tareasTrabajoDao.findConfTarea(tarea);
		this.tareasTrabajoDao.reabrirTarea(idTarea);
		//quitamos la fecha de fin del trabajo
		this.otrosTrabajosService.eliminarFechaFinOtroTrabajoTarea(idTarea);
		return this.enviarEmailReaperturaTarea(tareaAux);
	}

	private int enviarEmailReaperturaTarea(TareasTrabajo tareas) {
		TiposTarea tiposTarea = this.tiposTareasDao.find(tareas.getTipoTarea());
		tareas.setTipoTarea(tiposTarea);
		return enviarEmail(tareas, TipoAvisoEnum.REAPERTURA_TAREA.getValue());
	}

	@Override
	public int comprobarTareaAEliminar(BigDecimal idTarea) {
		return this.tareasTrabajoDao.comprobarTareaAEliminar(idTarea);
	}


	@Override
	public TareasTrabajo getDatosTarea(TareasTrabajo tareaTrabajo) {
		return this.tareasTrabajoDao.getDatosTarea(tareaTrabajo);
	}


	@SuppressWarnings("unchecked")
	@Override
	public JQGridResponseDto<TareasTrabajo> getTareasDependientes(TareasTrabajo tareaTrabajoFilter, JQGridRequestDto jqGridRequestDto) {
		List<TareasTrabajo> listaT = (List<TareasTrabajo>) this.tareasTrabajoDao.getTareasDependientes(tareaTrabajoFilter,
				jqGridRequestDto, false);

		Long recordNum = (Long) this.tareasTrabajoDao.getTareasDependientes(tareaTrabajoFilter,null, true);

		return new JQGridResponseDto<TareasTrabajo>(jqGridRequestDto, recordNum, listaT);
	}


	@Override
	public Tareas findConfTareaTrabajo(Tareas tareasFilter) {
		return this.tareasTrabajoDao.findConfTareaTrabajo(tareasFilter);
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public int reasignarTareasTrabajo(PersonalIZO persona, List<String> listSelectedIds) {
		int rst = 0;
		if (TareasServiceUtils.isNotEmptyListStr(listSelectedIds)) {
			List<TareasTrabajo> listTareas = this.tareasTrabajoDao.findTareasTrabajoPendientesDeEjecutar(listSelectedIds);
			if (TareasServiceUtils.isNotEmptyListTareasTrabajo(listTareas)) {
				TareasTrabajo tarea = new TareasTrabajo();
				tarea.setPersonaAsignada(persona);
				TareasUtils.asignarDatosAceptacionRecInternoTareaTrabajo(tarea);

				this.tareasTrabajoDao.reasignarTareas(tarea, listSelectedIds);
				this.tareasTrabajoDao.removeObsrvRechazo(listTareas);
				rst = this.actualizarTareas(listSelectedIds);
				final Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication()
						.getCredentials();

				if (!persona.getDni().equals(credentials.getNif())) {
					if (rst == 0) {
						rst = this.enviarEmail(persona, listTareas);
					} else {
						// No se asigna el valor rst devuelto por la función
						// enviarEmail, en caso de que hayamos obtenido
						// un error previamente
						this.enviarEmail(persona, listTareas);
					}
				}

				for (TareasTrabajo tareaOriginal : listTareas) {
					// comprobar que tiene tarea original y con recurso
					// asignacion
					if (tareaOriginal != null && TareasServiceUtils.isRecursoAsignacion(tareaOriginal)) {
						// enviar email de eliminacion de tarea a asignado
						// anterior
						this.enviarEmailEliminacionTareas(tareaOriginal);
					}
				}

			}
		}

		return rst;
	}

	private int enviarEmail(PersonalIZO persona, List<TareasTrabajo> listTareas) {
		List<String> listaDestinatarios = new ArrayList<String>();
		int rst = 0;

		if (this.appConfiguration.getProperty(Constants.APP_ENTORNO) != null) {
			if (Constants.LOCAL.equals(this.appConfiguration.getProperty(Constants.APP_ENTORNO))) {
				listaDestinatarios.add(Constants.DESTINATARIO_EMAIL_LOCAL);
			} else if (TareasServiceUtils.isDniNotNull(persona)) {
				String email = this.obtenerEmailContacto(persona.getDni());
				listaDestinatarios.add(email);
			}

			Locale localeEu = new Locale(Constants.LANG_EUSKERA);
			Map<String, String> infoEu = new LinkedHashMap<String, String>();
			Locale localeEs = new Locale(Constants.LANG_CASTELLANO);
			Map<String, String> infoEs = new LinkedHashMap<String, String>();

			infoEu.put(this.msg.getMessage("label.tareas", null, localeEu), "");
			infoEs.put(this.msg.getMessage("label.tareas", null, localeEs), "");

			int cont = 0;

			if (TareasServiceUtils.isLstDestinatariosAndTareasTrabajoNotEmpty(listaDestinatarios, listTareas)) {
				ParametrosEmail parametrosEmail = new ParametrosEmail();
				StringBuilder descTareasEs = new StringBuilder();
				StringBuilder descTareasEu = new StringBuilder();

				for (TareasTrabajo tareas : listTareas) {

					if (cont != 0) {
						descTareasEu.append(Constants.SALTO_DE_LINEA);
						descTareasEs.append(Constants.SALTO_DE_LINEA);
					}
					// Euskera
					descTareasEu.append(this.msg.getMessage(LABEL_TAREA, null, localeEu)).append(" ");
					descTareasEu.append(Constants.ESPACIO);
					descTareasEu.append(tareas.getIdTarea());
					descTareasEu.append(Constants.GUION_CON_ESPACIOS);
					descTareasEu.append(TareasServiceUtils.obtenerDescripcionTipoTarea(localeEu, tareas));
					descTareasEu.append(Constants.ABRIR_PARENTESIS)
							.append(this.msg.getMessage(Constants.LABEL_NUM_EXP, null, localeEu));
					descTareasEu.append(Constants.CERRAR_PARENTESIS);
					// Castellano
					descTareasEs.append(this.msg.getMessage(LABEL_TAREA, null, localeEs)).append(" ");
					descTareasEs.append(Constants.ESPACIO);
					descTareasEs.append(tareas.getIdTarea());
					descTareasEs.append(Constants.GUION_CON_ESPACIOS);
					descTareasEs.append(TareasServiceUtils.obtenerDescripcionTipoTarea(localeEs, tareas));
					descTareasEs.append(Constants.ABRIR_PARENTESIS)
							.append(this.msg.getMessage(Constants.LABEL_NUM_EXP, null, localeEs));
					descTareasEs.append(Constants.CERRAR_PARENTESIS);

					cont++;

				}

				infoEu.put("", descTareasEu.toString());
				infoEs.put("", descTareasEs.toString());

				parametrosEmail.setInfoEu(infoEu);
				parametrosEmail.setInfoEs(infoEs);

				try {
					this.mailService.sendMailWithParameters(TipoAvisoEnum.REASIGNAR_TAREAS.getValue(),
							listaDestinatarios, parametrosEmail);
				} catch (Exception e) {
					TareasTrabajoServiceImpl.LOGGER.info(ERROR_ENVIO_EMAIL, e);
					rst = -2;
				}
			} else {
				rst = -1;
			}
		}

		return rst;
	}

	/**
	 *
	 * @param listSelectedIds List<String>
	 * @return int
	 */
	private int actualizarTareas(List<String> listSelectedIds) {
		List<TareasTrabajo> listTareasInter = this.tareasTrabajoDao.findTareas(listSelectedIds);
		int rst = 0;

		for (TareasTrabajo tarea : listTareasInter) {

				List<TareasTrabajo> listTareas = new ArrayList<TareasTrabajo>();
				listTareas.add(tarea);
		}

		return rst;

	}

	/**
	 * @param listaDestinatarios
	 * @param tareas
	 * @return int, Devuelve (0/-1/-2): 0 - Si el email se ha enviado correctamente;
	 *         -1 - Si el usuario no dispone de dirección de email; -2 - Si el envío
	 *         de email ha fallado.
	 */
	public int enviarEmail(Persona persona, List<Tareas> listTareas) {
		List<String> listaDestinatarios = new ArrayList<String>();
		int rst = 0;

		if (this.appConfiguration.getProperty(Constants.APP_ENTORNO) != null) {
			if (Constants.LOCAL.equals(this.appConfiguration.getProperty(Constants.APP_ENTORNO))) {
				listaDestinatarios.add(Constants.DESTINATARIO_EMAIL_LOCAL);
			} else if (TareasServiceUtils.isDniNotNull(persona)) {
				String email = this.obtenerEmailContacto(persona.getDni());
				listaDestinatarios.add(email);
			}

			Locale localeEu = new Locale(Constants.LANG_EUSKERA);
			Map<String, String> infoEu = new LinkedHashMap<String, String>();
			Locale localeEs = new Locale(Constants.LANG_CASTELLANO);
			Map<String, String> infoEs = new LinkedHashMap<String, String>();

			infoEu.put(this.msg.getMessage("label.tareas", null, localeEu), "");
			infoEs.put(this.msg.getMessage("label.tareas", null, localeEs), "");

			int cont = 0;

			if (TareasServiceUtils.isLstDestinatariosAndTareasNotEmpty(listaDestinatarios, listTareas)) {
				ParametrosEmail parametrosEmail = new ParametrosEmail();
				StringBuilder descTareasEs = new StringBuilder();
				StringBuilder descTareasEu = new StringBuilder();

				for (Tareas tareas : listTareas) {

					if (cont != 0) {
						descTareasEu.append(Constants.SALTO_DE_LINEA);
						descTareasEs.append(Constants.SALTO_DE_LINEA);
					}
					// Euskera
					descTareasEu.append(this.msg.getMessage(LABEL_TAREA, null, localeEu)).append(" ");
					descTareasEu.append(Constants.ESPACIO);
					descTareasEu.append(tareas.getIdTarea());
					descTareasEu.append(Constants.GUION_CON_ESPACIOS);
					descTareasEu.append(TareasServiceUtils.obtenerDescripcionTipoTarea(localeEu, tareas));
					descTareasEu.append(Constants.ABRIR_PARENTESIS)
							.append(this.msg.getMessage(Constants.LABEL_NUM_EXP, null, localeEu));
					descTareasEu.append(tareas.getAnyoNumExpConcatenado());
					descTareasEu.append(Constants.CERRAR_PARENTESIS);
					// Castellano
					descTareasEs.append(this.msg.getMessage(LABEL_TAREA, null, localeEs)).append(" ");
					descTareasEs.append(Constants.ESPACIO);
					descTareasEs.append(tareas.getIdTarea());
					descTareasEs.append(Constants.GUION_CON_ESPACIOS);
					descTareasEs.append(TareasServiceUtils.obtenerDescripcionTipoTarea(localeEs, tareas));
					descTareasEs.append(Constants.ABRIR_PARENTESIS)
							.append(this.msg.getMessage(Constants.LABEL_NUM_EXP, null, localeEs));
					descTareasEs.append(tareas.getAnyoNumExpConcatenado());
					descTareasEs.append(Constants.CERRAR_PARENTESIS);

					cont++;

				}

				infoEu.put("", descTareasEu.toString());
				infoEs.put("", descTareasEs.toString());

				parametrosEmail.setInfoEu(infoEu);
				parametrosEmail.setInfoEs(infoEs);

				parametrosEmail.setAnyoNumExpediente(Utils.getNumExpedienteParameter(listTareas.get(0)));

				try {
					this.mailService.sendMailWithParameters(TipoAvisoEnum.REASIGNAR_TAREAS.getValue(),
							listaDestinatarios, parametrosEmail);
				} catch (Exception e) {
					TareasTrabajoServiceImpl.LOGGER.info(ERROR_ENVIO_EMAIL, e);
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
	public List<String> obtenerDestinatarios(Tareas tareas) {
		final List<String> listaDestinatarios = new ArrayList<String>();
		if (TareasServiceUtils.isRecursoAsignado(tareas)) {
			// La tarea tiene asignado dni de recurso. El recurso de asignación
			// puede ser tanto interno como externo
			if (GeneralUtils.emailValido(this.obtenerEmailContacto(tareas.getPersonaAsignada().getDni()))) {
				listaDestinatarios.add(this.obtenerEmailContacto(tareas.getPersonaAsignada().getDni()));
			}

		} else if (TareasServiceUtils.isRecursoExterno(tareas)) {
			// El recurso de asignación es un proveedor y la tarea tiene idLote
			// asignado
			final List<DatosContacto> mailsProveedoresPorIdTarea = this.datosContactoDao
					.getMailsProveedoresPorIdLote(tareas.getLotes());
			for (DatosContacto contacto : mailsProveedoresPorIdTarea) {
				// validamos el email
				if (GeneralUtils.emailValido(contacto.getEmail031())) {
					listaDestinatarios.add(contacto.getEmail031());
				}
			}

		}
		return listaDestinatarios;
	}


	@Override
	public TareasTrabajo obtenerEstadoEjecucionTarea(TareasTrabajo tarea) {
		return this.tareasTrabajoDao.comprobarEstadoEjecucionTarea(tarea);
	}

	@Override
	public int comprobarTareaTrabajoAsignador(TareasTrabajo tareas) {
		int rst = 0;
		rst = this.tareasTrabajoDao.comprobarTareaAsignador(tareas);
		return rst;
	}

	@Override
	public TareasTrabajo obtenerDatosEjecucionTarea(BigDecimal idTarea) {
		return this.tareasTrabajoDao.obtenerDatosEjecucionTarea(idTarea);
	}

	@Override
	public Long comprobarConflictoFechas(String[] idTareaList) {
		return this.tareasTrabajoDao.comprobarConflictoFechas(idTareaList);
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public TareasTrabajo finalizarTarea(TareasTrabajo tareaTrabajo) {
		this.tareasTrabajoDao.finalizarTarea(tareaTrabajo);
		// lanzamos un borrado previo por si ha sido ejecutada antes
		this.tareasTrabajoDao.borrarObservacionesEjecucionTarea(tareaTrabajo);
		if(tareaTrabajo.getObservacionesTareas()!= null) {
			this.tareasTrabajoDao.insertarObservacionesEjecucionTarea(tareaTrabajo);
		}
		this.registrarAccionEjecucionTarea(tareaTrabajo);
		if ( !this.tareasTrabajoDao.tieneTareasPendientesDeEjecutar(tareaTrabajo)) {
			this.tareasTrabajoDao.finalizaTrabajo(tareaTrabajo);
		}
		return tareaTrabajo;
	}

	/**
	 * @param tareaAux
	 */
	private void registrarAccionEjecucionTarea(TareasTrabajo tareaAux) {
		RegistroAcciones reg = new RegistroAcciones();
		reg.setIdPuntoMenu(PuntosMenuEnum.CARGA_TRABAJO.getValue());
		reg.setIdAccion(Constants.ACCION_MODIFICACION);

		Locale locale = new Locale(Constants.LANG_EUSKERA);
		StringBuilder observ = new StringBuilder();
		observ.append(this.msg.getMessage("mensaje.ejecucionTareaTrabajo", null, locale)).append(" \n");
		BigDecimal idTarea = tareaAux.getIdTarea();
		BigDecimal idTrabajo = tareaAux.getTrabajo().getIdTrabajo();

		observ.append("id").append(Constants.IGUAL).append(idTarea);
		if (tareaAux.getTipoTarea() != null && tareaAux.getTipoTarea().getId015() != null) {
			observ.append(Constants.COMA_ESPACIO);
			observ.append(this.msg.getMessage("comun.tipoDeTarea", null, locale)).append(Constants.IGUAL);
			TareasServiceUtils tareasServiceUtils = new TareasServiceUtils();
			String descTipoTarea = tareasServiceUtils.obtenerDescripcionTipoTareaBD(locale, tareaAux.getTipoTarea());
			observ.append(descTipoTarea);
		}
		observ.append(" \n").append(" ( " + this.msg.getMessage("label.idTrabajo", null, locale)).append(Constants.IGUAL).append(idTrabajo).append(" ) ");

		observ.append(" \n");

		reg.setObserv(observ.toString());

		if (SecurityContextHolder.getContext().getAuthentication() == null && tareaAux.getPersonaAsignada() != null) {
			// Procedencia: WS
			reg.setUsuarioRegistro(tareaAux.getPersonaAsignada().getDni());
			reg.setIp(null);
		}

		this.registroAccionesDao.add(reg);
	}

	@Override
	public List<TareasTrabajo> getTareasTrabajosExcelDetalle(OtrosTrabajos otrosTrabajos) {
		return this.tareasTrabajoDao.getTareasTrabajosExcelDetalle(otrosTrabajos);
	}

}
