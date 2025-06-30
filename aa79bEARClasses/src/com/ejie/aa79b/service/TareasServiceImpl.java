package com.ejie.aa79b.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.dao.Aa79bTareaWsDao;
import com.ejie.aa79b.dao.AusenciasCalendarioPersonalDao;
import com.ejie.aa79b.dao.BitacoraExpedienteDao;
import com.ejie.aa79b.dao.ContactoFacturacionExpedienteDao;
import com.ejie.aa79b.dao.DatosContactoDao;
import com.ejie.aa79b.dao.DatosPersonaDao;
import com.ejie.aa79b.dao.DatosTareaTradosDao;
import com.ejie.aa79b.dao.DocumentosCorreccionDao;
import com.ejie.aa79b.dao.DocumentosGeneralDao;
import com.ejie.aa79b.dao.EjecucionTareasDao;
import com.ejie.aa79b.dao.EntidadDao;
import com.ejie.aa79b.dao.ExpedienteDao;
import com.ejie.aa79b.dao.ExpedienteTradRevDao;
import com.ejie.aa79b.dao.GenericoDao;
import com.ejie.aa79b.dao.GestorExpedienteDao;
import com.ejie.aa79b.dao.OidsAuxiliarDao;
import com.ejie.aa79b.dao.OrdenPreciosDao;
import com.ejie.aa79b.dao.RegistroAccionesDao;
import com.ejie.aa79b.dao.SolicitanteDao;
import com.ejie.aa79b.dao.SubsanacionDao;
import com.ejie.aa79b.dao.TareasDao;
import com.ejie.aa79b.dao.TareasGestionInterpretacionDao;
import com.ejie.aa79b.dao.TareasTrabajoDao;
import com.ejie.aa79b.dao.TiposTareaDao;
import com.ejie.aa79b.mail.MailService;
import com.ejie.aa79b.model.AusenciasCalendarioPersonal;
import com.ejie.aa79b.model.BitacoraExpediente;
import com.ejie.aa79b.model.BitacoraSolicitante;
import com.ejie.aa79b.model.ContactoFacturacionExpediente;
import com.ejie.aa79b.model.DatosContacto;
import com.ejie.aa79b.model.DatosEstimacion;
import com.ejie.aa79b.model.DatosPersona;
import com.ejie.aa79b.model.DocPresupuestoTraduccion;
import com.ejie.aa79b.model.DocumentoTareaAdjunto;
import com.ejie.aa79b.model.DocumentosCorreccion;
import com.ejie.aa79b.model.EjecucionTareas;
import com.ejie.aa79b.model.Entidad;
import com.ejie.aa79b.model.EntradaDatosSolicitud;
import com.ejie.aa79b.model.EstadosExpediente;
import com.ejie.aa79b.model.EstudioEstimado;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.FasesExpediente;
import com.ejie.aa79b.model.FechaHoraIniFin;
import com.ejie.aa79b.model.Fichero;
import com.ejie.aa79b.model.HorasEstimadas;
import com.ejie.aa79b.model.Lotes;
import com.ejie.aa79b.model.OrigenTareaNoConformidad;
import com.ejie.aa79b.model.ParametrosEmail;
import com.ejie.aa79b.model.Persona;
import com.ejie.aa79b.model.PersonalIZO;
import com.ejie.aa79b.model.ProcesoEmail;
import com.ejie.aa79b.model.RegistroAcciones;
import com.ejie.aa79b.model.Solicitante;
import com.ejie.aa79b.model.SubsanacionExpediente;
import com.ejie.aa79b.model.TareaIntPagoProveed;
import com.ejie.aa79b.model.Tareas;
import com.ejie.aa79b.model.TareasAsignado;
import com.ejie.aa79b.model.TiposRevision;
import com.ejie.aa79b.model.TiposTarea;
import com.ejie.aa79b.model.enums.AccionBitacoraEnum;
import com.ejie.aa79b.model.enums.EstadoAceptacionTareaEnum;
import com.ejie.aa79b.model.enums.EstadoEjecucionTareaEnum;
import com.ejie.aa79b.model.enums.EstadoExpedienteEnum;
import com.ejie.aa79b.model.enums.FaseExpedienteEnum;
import com.ejie.aa79b.model.enums.PuntosMenuEnum;
import com.ejie.aa79b.model.enums.TipoAvisoEnum;
import com.ejie.aa79b.model.enums.TipoJornadaEnum;
import com.ejie.aa79b.model.enums.TipoRecursoEnum;
import com.ejie.aa79b.model.enums.TipoTareaGestionAsociadaEnum;
import com.ejie.aa79b.security.Usuario;
import com.ejie.aa79b.utils.DateUtils;
import com.ejie.aa79b.utils.GeneralUtils;
import com.ejie.aa79b.utils.TareasServiceUtils;
import com.ejie.aa79b.utils.TareasUtils;
import com.ejie.aa79b.utils.Utils;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;

@Service(value = "tareas")
public class TareasServiceImpl extends GenericoServiceImpl<Tareas> implements TareasService {

	private static final String LABEL_TIPO_DE_TAREA = "comun.tipoDeTarea";
	private static final String LABEL_TAREAS_ELIMINADAS = "mensaje.tareasEliminadas";
	private static final String LABEL_TAREAS_CREADAS = "mensaje.tareasCreadas";
	private static final String LABEL_TAREAS_MODIFICADAS = "mensaje.tareasModificadas";
	private static final String LABEL_MOTIVO_OBS_NO_CONFORMIDAD = "label.motivoObsNoConformidad";
	private static final String LABEL_CREAR_TAREAS_DEFECTO = "mensaje.crearTareasDefecto";
	private static final String LABEL_TAREA = "label.tarea";
	private static final String ID = "id";
	private static final String ERROR_ENVIO_EMAIL = "Error en el envío de email";
	private static final String LABEL_REAPERTURA_TAREA = "mensaje.reaperturaTarea";
	private static final String LABEL_EJECUCION_TAREA = "mensaje.ejecucionTarea";

	@Autowired()
	private TareasDao tareasDao;

	@Autowired()
	private TiposTareaDao tiposTareasDao;

	@Autowired()
	private DatosTareaTradosDao datosTareaTradosDao;

	@Autowired()
	private DatosContactoDao datosContactoDao;

	@Autowired()
	private RegistroAccionesDao registroAccionesDao;

	@Autowired()
	private EjecucionTareasDao ejecucionTareasDao;

	@Autowired()
	private DocumentosGeneralDao documentosGeneralDao;

	@Autowired()
	private BitacoraExpedienteDao bitacoraExpedienteDao;

	@Autowired()
	private ExpedienteDao expedienteDao;

	@Autowired()
	private ReloadableResourceBundleMessageSource msg;

	@Autowired()
	private Properties appConfiguration;

	@Autowired()
	private MailService mailService;

	@Autowired()
	private PersonalIZOService personalIZOService;

	@Autowired()
	private ReloadableResourceBundleMessageSource appMessageSource;

	@Autowired()
	private SubsanacionDao subsanacionDao;
	@Autowired()
	private TareasGestionInterpretacionDao tareasGestionInterpretacionDao;
	@Autowired
	private ContactoFacturacionExpedienteDao contactoFacturacionExpedienteDao;
	@Autowired
	private EntidadDao entidadDao;
	@Autowired
	private SolicitanteDao solicitanteDao;
	@Autowired
	private DatosPersonaDao datosPersonaDao;
	@Autowired
	private GestorExpedienteDao gestorExpedienteDao;
	@Autowired
	private ExpedienteTradRevDao expedienteTradRevDao;
	@Autowired
	private OrdenPreciosDao ordenPreciosDao;
	@Autowired()
	private AusenciasCalendarioPersonalDao ausenciasCalendarioPersonalDao;
	@Autowired()
	private Aa79bTareaWsDao aa79bTareaWsDao;
	@Autowired()
	private OidsAuxiliarDao oidsAuxiliarDao;
	@Autowired()
	private DocumentosCorreccionDao documentosCorreccionDao;
	@Autowired
	private ProcesoEmailService procesoEmailService;
	@Autowired
	private TareasTrabajoDao tareasTrabajoDao;

	private static final String LABEL_TOTAL = "label.total";
	private static final String LABEL_TARIFA = "label.tarifa";
	private static final String LABEL_DOCPPTOINTERPPIE = "label.docPptoInterpPie";
	private static final String LABEL_PRESUPUESTO = "label.presupuesto";
	private static final String LABEL_IVA_INCLUIDO = "label.ivaIncluido2";

	private static final Logger LOGGER = LoggerFactory.getLogger(TareasServiceImpl.class);

	@Override
	protected GenericoDao<Tareas> getDao() {
		return this.tareasDao;
	}

	@Override
	public boolean isVisiblePptoInterpretacion(Expediente expediente) {
		return this.tareasDao.isVisiblePptoInterpretacion(expediente);
	}

	@Override()
	public JQGridResponseDto<Tareas> obtenerTareasConf(Tareas filter, JQGridRequestDto jqGridRequestDto,
			boolean startsWith) {
		List<Tareas> listaT = this.tareasDao.obtenerTareasConf(filter, jqGridRequestDto, false);
		Long recordNum = this.tareasDao.obtenerTareasConfCount(filter, false);

		return new JQGridResponseDto<Tareas>(jqGridRequestDto, recordNum, listaT);
	}

	@Override()
	public int reordenarTareas(List<Tareas> listTareas) {
		TareasServiceUtils tareasServiceUtils = new TareasServiceUtils();
		return tareasServiceUtils.reordenarTareas(listTareas);
	}

	@Override()
	@Transactional(rollbackFor = Throwable.class)
	public void removeTareas(List<String> listSelectedIds) {
		if (TareasServiceUtils.isNotEmptyListStr(listSelectedIds)) {
			List<Tareas> listTareas = this.tareasDao.findTareasAEliminar(listSelectedIds);
			List<Tareas> listTareasDependientes = this.tareasDao.findTareasDependientesAEliminar(listTareas);
			for (Tareas tareas : listTareas) {
				this.enviarEmailEliminacionTareas(tareas);
			}
			for (Tareas tareas : listTareasDependientes) {
				this.enviarEmailEliminacionTareas(tareas);
			}

			if (TareasServiceUtils.isNotEmptyListTareas(listTareas)) {
				// Comprobamos que la tarea "Crear proyecto trados" está entre
				// las tareas a eliminar y obtenemos sus datos
				Tareas tareaTrados = this.tareasDao.findTareaTrados(listTareas);

				eliminarAusenciaCalendario(listTareas);

				// Eliminamos las tareas dependientes de las tareas
				// seleccionadas por pantalla que estén pendientes de ejecución
				this.tareasDao.removeTareasDependientes(listTareas);
				// quitamos la relación de tarea de las tareas que eran relacionadas que se van
				// a quedar. Caso entrega revisión
				this.tareasDao.removeRelacionTareasDependientes(listTareas);
				// Eliminamos las tareas seleccionadas por pantalla
				this.tareasDao.removeTareas(listSelectedIds);
				this.registrarAccionTareasEliminadas(listTareas);

				// Actualizamos el estado/fase del expediente en caso de que
				// la tarea "Crear proyecto trados" se encuentre entre las
				// tareas a eliminar.
				updateExpIfDeleteTareaTrados(tareaTrados);
			}
		}

	}

	/**
	 * @param listTareas
	 */
	private void eliminarAusenciaCalendario(List<Tareas> listTareas) {
		TareasServiceUtils tareasServiceUtils = new TareasServiceUtils();
		tareasServiceUtils.eliminarAusenciaCalendario(listTareas);
	}

	/**
	 * @param tareaTrados
	 */
	private void updateExpIfDeleteTareaTrados(Tareas tareaTrados) {
		if (TareasServiceUtils.isTareaNoNula(tareaTrados)) {
			actualizarDatosExp(tareaTrados.getAnyo(), tareaTrados.getNumExp(),
					FaseExpedienteEnum.PDTE_EJECT_TAREAS.getValue(), null);
		}
	}

	@Override()
	@Transactional(rollbackFor = Throwable.class)
	public int reasignarTareas(PersonalIZO persona, List<String> listSelectedIds) {
		int rst = 0;
		if (TareasServiceUtils.isNotEmptyListStr(listSelectedIds)) {
			List<Tareas> listTareas = this.tareasDao.findTareasPendientesDeEjecutar(listSelectedIds);
			if (TareasServiceUtils.isNotEmptyListTareas(listTareas)) {
				Tareas tarea = new Tareas();
				tarea.setPersonaAsignada(persona);
				TareasUtils.asignarDatosAceptacionRecInterno(tarea);

				this.tareasDao.reasignarTareas(tarea, listSelectedIds);
				this.tareasDao.removeObsrvRechazo(listTareas);
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

				for (Tareas tareaOriginal : listTareas) {
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

	@Override()
	public int enviarEmailsAsignadoresModifCalendario(Tareas tareaFilter, int tipoTarea) {

		int rst = 0;

		List<Tareas> listaTareas = this.tareasDao.findTareasRangoFechas(tareaFilter, tipoTarea);

		if (!listaTareas.isEmpty()) {

			for (Tareas tareaX : listaTareas) {
				rst += this.enviarEmailAsignador(tareaX, tipoTarea);
			}
		}
		return rst;
	}

	/**
	 * Registra o actualiza las ausencias en el calendario personal al reasignar una
	 * tarea a un recurso interno
	 *
	 * @param listSelectedIds List<String>
	 * @return int
	 */
	private int actualizarTareas(List<String> listSelectedIds) {
		List<Tareas> listTareasInter = this.tareasDao.findTareas(listSelectedIds);
		int rst = 0;

		for (Tareas tarea : listTareasInter) {

			if (TareasServiceUtils.isTareaInterpretacion(tarea)) {
				rst = gestionarAusenciaCalendarioTareaInter(tarea);
			}

			if (TareasServiceUtils.isTareaTradRev(tarea)) {
				List<Tareas> listTareas = new ArrayList<Tareas>();
				listTareas.add(tarea);
				List<Tareas> listTareasDependientes = this.tareasDao.findTareasDependientesAEliminar(listTareas);
				for (Tareas tareasDept : listTareasDependientes) {

					this.enviarEmailEliminacionTareas(tareasDept);
				}
			}

			this.tareasDao.procCrearTareasRelacionadasPL(tarea.getIdTarea());

		}

		return rst;

	}

	/**
	 * @param tarea
	 * @return int
	 */
	private int gestionarAusenciaCalendarioTareaInter(Tareas tarea) {
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
					.updateDatosReasignacionTarea(ausenciasCalendarioPersonal);

			if (numFilasActualizadas == 0) {
				rst = addAusenciaCalendario(ausenciasCalendarioPersonal);
			}
		} else {
			this.ausenciasCalendarioPersonalDao.removeDatosTarea(tarea.getIdTarea());
		}
		return rst;
	}

	/**
	 * @param tareas
	 * @return int
	 */
	private int enviarEmailEliminacionTareas(Tareas tareas) {
		final Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication().getCredentials();

		int rst = 0;
		if ((tareas.getRecursoAsignacion() != null
				&& TipoRecursoEnum.EXTERNO.getValue().equals(tareas.getRecursoAsignacion()))
				|| (tareas.getPersonaAsignada().getDni() != null
						&& !credentials.getNif().equals(tareas.getPersonaAsignada().getDni()))) {
			rst = enviarEmail(tareas, TareasServiceUtils.obtenerTipoAvisoEliminacionTareas(tareas));
		}
		return rst;
	}

	/**
	 * @param tareas
	 * @param object
	 * @return int
	 */
	private int enviarEmailAsignacionTarea(Tareas tareas, Tareas tareaOriginal) {
		// comprobar que tiene tarea original y con recurso asignacion
		if (tareaOriginal != null && TareasServiceUtils.isRecursoAsignacion(tareaOriginal)) {
			// enviar email de eliminacion de tarea a asignado anterior
			this.enviarEmailEliminacionTareas(tareaOriginal);
		}
		TiposTarea tiposTarea = this.tiposTareasDao.find(tareas.getTipoTarea());
		tareas.setTipoTarea(tiposTarea);

		int rst = 0;
		if (!tareas.getPersonaAsignada().getDniCompleto().equals(tareas.getPersonaAsignador().getDniCompleto())
				&& TareasServiceUtils.isRecursoAsignacion(tareas)) {

			rst = enviarEmail(tareas, TareasServiceUtils.obtenerTipoAvisoAsignacionTareas(tareas));
		}
		return rst;
	}

	/**
	 * @param tareas
	 * @return int
	 */
	private int enviarEmailModificacionDocTarea(Tareas tareas) {

		TiposTarea tiposTarea = this.tiposTareasDao.find(tareas.getTipoTarea());
		tareas.setTipoTarea(tiposTarea);

		return enviarEmail(tareas, TareasServiceUtils.obtenerTipoAvisoModificacionDocTarea(tareas));
	}

	/**
	 * @param tareas
	 * @return int
	 */
	private int enviarEmailGenerarNoConformidad(Tareas tareas) {

		TiposTarea tiposTarea = this.tiposTareasDao.find(tareas.getTipoTarea());
		tareas.setTipoTarea(tiposTarea);

		return enviarEmail(tareas, TipoAvisoEnum.NO_CONFORMIDAD_PROVEEDOR.getValue());
	}

	/**
	 * @param tareas
	 * @return int
	 */
	@Override
	public int enviarEmailReaperturaTarea(Tareas tareas) {
		TiposTarea tiposTarea = this.tiposTareasDao.find(tareas.getTipoTarea());
		tareas.setTipoTarea(tiposTarea);
		return enviarEmail(tareas, TareasServiceUtils.obtenerTipoAvisoReaperturaTarea(tareas));
	}

	/**
	 * @param tareas
	 * @return int
	 */
	private int enviarEmailNotifCorreccionProv(Tareas tareas) {

		TiposTarea tiposTarea = this.tiposTareasDao.find(tareas.getTipoTarea());
		tareas.setTipoTarea(tiposTarea);

		return enviarEmail(tareas, TipoAvisoEnum.NOTIFICACION_CORRECCION_PROVEEDOR.getValue());
	}

	/**
	 * @param listaDestinatarios
	 * @param tareas
	 * @return int, Devuelve (0/-1/-2): 0 - Si el email se ha enviado correctamente;
	 *         -1 - Si el usuario no dispone de dirección de email; -2 - Si el envío
	 *         de email ha fallado.
	 */
	private int enviarEmail(Tareas tarea, int idTipoAviso) {
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
						+ Constants.GUION_CON_ESPACIOS + TareasServiceUtils.obtenerDescripcionTipoTarea(localeEu, tarea)
						+ Constants.ABRIR_PARENTESIS + this.msg.getMessage(Constants.LABEL_NUM_EXP, null, localeEu)
						+ tarea.getAnyoNumExpConcatenado() + Constants.CERRAR_PARENTESIS);
				infoEs.put(this.msg.getMessage(LABEL_TAREA, null, localeEs), tarea.getIdTarea()
						+ Constants.GUION_CON_ESPACIOS + TareasServiceUtils.obtenerDescripcionTipoTarea(localeEs, tarea)
						+ Constants.ABRIR_PARENTESIS + this.msg.getMessage(Constants.LABEL_NUM_EXP, null, localeEs)
						+ tarea.getAnyoNumExpConcatenado() + Constants.CERRAR_PARENTESIS);

				if (TipoAvisoEnum.NO_CONFORMIDAD_PROVEEDOR.getValue() == idTipoAviso) {
					infoEu.put(this.msg.getMessage(LABEL_MOTIVO_OBS_NO_CONFORMIDAD, null, localeEu),
							tarea.getObservaciones());
					infoEs.put(this.msg.getMessage(LABEL_MOTIVO_OBS_NO_CONFORMIDAD, null, localeEs),
							tarea.getObservaciones());
				}

				parametrosEmail.setInfoEu(infoEu);
				parametrosEmail.setInfoEs(infoEs);

				parametrosEmail.setAnyoNumExpediente(Utils.getNumExpedienteParameter(tarea));

				try {
					this.mailService.sendMailWithParameters(idTipoAviso, listaDestinatarios, parametrosEmail);
				} catch (Exception e) {
					TareasServiceImpl.LOGGER.info(ERROR_ENVIO_EMAIL, e);
					rst = -2;
				}
			} else {
				TareasServiceImpl.LOGGER.info("La lista de destinatarios esta vacia");
				rst = -1;
			}
		}

		return rst;
	}

	/**
	 * @param tareas Tareas
	 * @return List<String>
	 */
	@Override()
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
	 * @param listTareas
	 */
	private void registrarAccionTareasEliminadas(List<Tareas> listTareas) {
		this.registrarAccionTareas(listTareas, Constants.ACCION_MODIFICACION, LABEL_TAREAS_ELIMINADAS, false);
	}

	/**
	 * @param listTareas
	 */
	private void registrarAccionTareasCreadas(List<Tareas> listTareas) {
		this.registrarAccionTareas(listTareas, Constants.ACCION_ALTA, LABEL_TAREAS_CREADAS, true);
	}

	/**
	 * @param listTareas
	 * @param idAccion
	 * @param msgLabel
	 * @param getDescFromBD
	 */
	public void registrarAccionTareas(List<Tareas> listTareas, Long idAccion, String msgLabel, boolean getDescFromBD) {
		RegistroAcciones reg = new RegistroAcciones();
		reg.setIdPuntoMenu(PuntosMenuEnum.PLANIFICACION_EXPEDIENTES.getValue());
		reg.setIdAccion(idAccion);

		Locale locale = new Locale(Constants.LANG_EUSKERA);
		StringBuilder observ = new StringBuilder();
		observ.append(this.msg.getMessage(msgLabel, null, locale)).append(" \n");
		Long anyo = null;
		Integer numExpediente = null;

		for (Tareas tareas : listTareas) {

			if (TareasServiceUtils.isNumExpNulo(numExpediente)) {
				anyo = tareas.getAnyo();
				numExpediente = tareas.getNumExp();
			}

			observ.append(ID).append("=").append(tareas.getIdTarea());
			if (TareasServiceUtils.isNotNullTipoTarea(tareas)) {
				observ.append(", ");
				observ.append(this.msg.getMessage(LABEL_TIPO_DE_TAREA, null, locale)).append("=");

				String descTipoTarea;
				if (getDescFromBD) {
					TareasServiceUtils tareasServiceUtils = new TareasServiceUtils();
					descTipoTarea = tareasServiceUtils.obtenerDescripcionTipoTareaBD(locale, tareas);
				} else {
					descTipoTarea = TareasServiceUtils.obtenerDescripcionTipoTarea(locale, tareas);
				}

				observ.append(descTipoTarea);
			}
			observ.append(" \n");

		}

		reg.setObserv(observ.toString());
		reg.setAnyo(anyo);
		reg.setNumExp(numExpediente);
		reg.setIdEstadoBitacora(this.expedienteDao.findEstadoBitacoraExp(anyo, numExpediente));

		this.registroAccionesDao.add(reg);
	}

	/**
	 * @param tarea
	 * @param original
	 */
	private void registrarAccionTareaModificada(Tareas tarea, Tareas original) {
		RegistroAcciones reg = new RegistroAcciones();
		reg.setIdPuntoMenu(PuntosMenuEnum.PLANIFICACION_EXPEDIENTES.getValue());
		reg.setIdAccion(Constants.ACCION_MODIFICACION);

		Map<String, String> parametros = new LinkedHashMap<String, String>();
		parametros.put("tipoRevision.id018", "comun.tipoDeRevision");
		parametros.put("indFacturacion", "comun.facturablePorProveedor");
		parametros.put("fechaIni", "label.fechaIni");
		parametros.put("horaIni", "label.horaIni");
		parametros.put("fechaFin", "label.fechaFin");
		parametros.put("horaFin", "label.horaFin");
		parametros.put("horasPrevistas", "comun.horaPrevista");
		parametros.put("recursoAsignacion", "label.recursoAsignacion");
		parametros.put("personaAsignada.dni", "label.dniRecursoAsignado");
		parametros.put("personaAsignada.entidad.tipo", "label.tipoEntidadRecursoAsignado");
		parametros.put("personaAsignada.entidad.codigo", "label.codigoEntidadRecursoAsignado");
		parametros.put("lotes.idLote", "label.idLoteRecursoAsignado");
		parametros.put("orden", "label.ordenacion");
		parametros.put("personaAsignador.dni", "label.dniAsignador");

		Locale locale = new Locale(Constants.LANG_EUSKERA);
		String aux = Utils.observacionesRegistro(tarea, original, parametros, this.msg, locale);
		if (StringUtils.isNotBlank(aux)) {
			TareasServiceUtils tareasServiceUtils = new TareasServiceUtils();
			StringBuilder observ = new StringBuilder();
			observ.append(this.msg.getMessage(LABEL_TAREAS_MODIFICADAS, null, locale)).append(" \n");
			observ.append(ID).append("=").append(tarea.getIdTarea());
			if (TareasServiceUtils.isNotNullTipoTarea(tarea)) {
				observ.append(", ");
				observ.append(this.msg.getMessage(LABEL_TIPO_DE_TAREA, null, locale)).append("=");
				observ.append(tareasServiceUtils.obtenerDescripcionTipoTareaBD(locale, tarea));
			}
			observ.append(" \n");
			observ.append(aux);
			observ.append(this.obtenerMensajeObservaciones(tarea, original, locale));
			reg.setObserv(observ.toString());
			reg.setAnyo(tarea.getAnyo());
			reg.setNumExp(tarea.getNumExp());
			reg.setIdEstadoBitacora(this.expedienteDao.findEstadoBitacoraExp(tarea.getAnyo(), tarea.getNumExp()));

			this.registroAccionesDao.add(reg);
		}

	}

	/**
	 * @param bean     Tareas
	 * @param original Tareas
	 * @param locale   Locale
	 * @param String
	 */
	private String obtenerMensajeObservaciones(Tareas bean, Tareas original, Locale locale) {
		StringBuilder strObservaciones = new StringBuilder();
		if (original != null) {
			String strActual = bean.getObservaciones();
			String strOriginal = original.getObservaciones();
			if (StringUtils.isNotBlank(strActual) && !StringUtils.equals(strOriginal, strActual)) {
				strObservaciones.append(this.msg.getMessage(Constants.LABEL_OBSERVACIONES_MODIFICADAS, null, locale));
			}
		}

		return strObservaciones.toString();
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
					TareasServiceImpl.LOGGER.info(ERROR_ENVIO_EMAIL, e);
					rst = -2;
				}
			} else {
				rst = -1;
			}
		}

		return rst;
	}

	@Override
	public JQGridResponseDto<TareasAsignado> filterAsignadoAproveedores(Tareas filter,
			JQGridRequestDto jqGridRequestDto, boolean b) {
		List<TareasAsignado> listaT = this.tareasDao.findTareasAsignadasAProveedor(filter, jqGridRequestDto, false);
		for (TareasAsignado tareaAsignado : listaT) {
			tareaAsignado.setMsg(this.msg);
		}
		Long recordNum = this.tareasDao.findTareasAsignadasAProveedorCount(filter);
		return new JQGridResponseDto<TareasAsignado>(jqGridRequestDto, recordNum, listaT);
	}

	@Override()
	public int comprobarTareaAsignador(Tareas tareas) {
		BitacoraExpediente bitacora = this.expedienteDao.findEstadoFaseExpediente(tareas.getAnyo(), tareas.getNumExp());
		int rst = 0;

		if (TareasServiceUtils.isExpEnCursoPdteTramClte(bitacora)) {
			TareasServiceUtils tareasServiceUtils = new TareasServiceUtils();
			if (tareasServiceUtils.isSubDocPdte(tareas)) {
				rst = this.tareasDao.comprobarTareaAsignador(tareas);
			} else {
				rst = 3;
			}
		} else {
			rst = this.tareasDao.comprobarTareaAsignador(tareas);
		}

		return rst;
	}

	@Override()
	@Transactional(rollbackFor = Throwable.class)
	public void procCrearTareasDefectoPL(Long anyo, Integer numExp) {
		this.tareasDao.procCrearTareasDefectoPL(anyo, numExp);
		this.registrarAccionesCreacionTareasDefecto(anyo, numExp);
	}

	/**
	 * @param anyo
	 * @param numExp
	 */
	private void registrarAccionesCreacionTareasDefecto(Long anyo, Integer numExp) {
		RegistroAcciones reg = new RegistroAcciones();
		reg.setIdPuntoMenu(PuntosMenuEnum.PLANIFICACION_EXPEDIENTES.getValue());
		reg.setIdAccion(Constants.ACCION_ALTA);

		Locale locale = new Locale(Constants.LANG_EUSKERA);
		StringBuilder observ = new StringBuilder();
		observ.append(this.msg.getMessage(LABEL_CREAR_TAREAS_DEFECTO, null, locale)).append(Constants.ESPACIO);
		if (TareasServiceUtils.isNotAnyoNumExpNulos(anyo, numExp)) {
			observ.append(String.valueOf(anyo).substring(2) + "/" + String.format("%06d", numExp));
		}
		reg.setAnyo(anyo);
		reg.setNumExp(numExp);
		reg.setIdEstadoBitacora(this.expedienteDao.findEstadoBitacoraExp(anyo, numExp));
		reg.setObserv(observ.toString());
		this.registroAccionesDao.add(reg);
	}

	@Override()
	@Transactional(rollbackFor = Throwable.class)
	public int crearTarea(Tareas tarea, String documentosSelect) {

		int rst = 0;

		if (TareasServiceUtils.isTareaNoNula(tarea)) {

			if (TareasServiceUtils.isRecursoAsignacion(tarea)) {

				BitacoraExpediente bitacora = this.expedienteDao.findEstadoFaseExpediente(tarea.getAnyo(),
						tarea.getNumExp());
				TareasServiceUtils tareasServiceUtils = new TareasServiceUtils();
				if (TareasServiceUtils.isExpPdteProyTradosAndTareaNotTrados(bitacora, tarea)) {
					rst = 1;
				} else if (TareasServiceUtils.isExpEnCursoPdteTramClte(bitacora)) {
					rst = 2;
				} else if (tareasServiceUtils.existeTareaRevisionExterna(tarea)) {
					rst = 3;
				} else {
					rst = crearActualizarTarea(tarea, documentosSelect);
				}

			} else {
				rst = crearActualizarTarea(tarea, documentosSelect);
			}

		}

		return rst;

	}

	/**
	 * @param tarea
	 * @param documentosSelect
	 * @return int
	 */
	private int crearActualizarTarea(Tareas tarea, String documentosSelect) {
		int rst = 0;
		if (TareasServiceUtils.isIdTareaNulo(tarea)) {
			// Creamos una nueva tarea
			asignarDatosAsignacionTarea(tarea);
			TareasUtils.asignarDatosTareaProveedor(tarea);

			Tareas tareaAux = addTarea(tarea, documentosSelect);
			// Lista de tareas utilizada para el registro de acciones
			List<Tareas> listTareas = new ArrayList<Tareas>();

			if (TareasServiceUtils.isNotTareaNula(tareaAux)) {
				this.actualizarDatosExpediente(tareaAux);
				gestionarTareaNoConformidadClte(tareaAux);
				Integer idProcesoMail = this.tareasDao.procCrearTareasRelacionadasPL(tareaAux.getIdTarea());
				if (tarea.getDatosPagoProveedores() != null) {
					tarea.getDatosPagoProveedores().setIdTarea(tarea.getIdTarea());
					this.tareasDao.procActDatosPagoProvPL(tarea.getDatosPagoProveedores());
				}
				if (0 != idProcesoMail) {
					ProcesoEmail bean = new ProcesoEmail();
					bean.setIdProceso(idProcesoMail);
					this.procesoEmailService.enviarMail(bean);
				}

				listTareas.add(tareaAux);

				// Se registra la ausencia en el calendario personal en caso
				// de que la tarea esté asociada a un recurso interno
				rst = addAusenciaCalendarioPersonal(tareaAux);
			}
			tareaAux.setTipoTarea(this.tiposTareasDao.find(tareaAux.getTipoTarea()));
			// Se envía mail de aviso si la tarea está asignada a un recurso
			if (rst == 0) {
				rst = this.envioEmailAsignacionTarea(tareaAux);
			} else {
				// No se asigna el valor rst devuelto por la función
				// enviarEmailAsignacionTarea, en caso de que hayamos obtenido
				// un error previamente
				envioEmailAsignacionTarea(tareaAux);
			}

			// Registro de acciones
			this.registrarAccionTareasCreadas(listTareas);
		} else {
			// Modificamos la tarea
			rst = this.update(tarea, documentosSelect);
		}
		// Insertamos al asignador de la tarea en la foto de personas por si no
		// esta.

		if (tarea.getPersonaAsignador().getDni() != null) {
			boolean esta = this.datosPersonaDao.comprobarPersona(tarea.getPersonaAsignador().getDni());
			if (!esta) {
				PersonalIZO personalIZO = new PersonalIZO();
				personalIZO.setDni(tarea.getPersonaAsignador().getDni());
				PersonalIZO personalAInsertar = this.personalIZOService.find(personalIZO);
				this.expedienteDao.guardarPersona(personalAInsertar);
			}
		}

		return rst;
	}

	/**
	 * @param tareaAux
	 */
	private void gestionarTareaNoConformidadClte(Tareas tareaAux) {
		if (TareasServiceUtils.isTareaNoConformidadClte(tareaAux)) {
			Locale locale = new Locale(Constants.LANG_EUSKERA);
			actualizarDatosExp(tareaAux.getAnyo(), tareaAux.getNumExp(),
					FaseExpedienteEnum.PDTE_EJECT_TAREAS.getValue(),
					this.msg.getMessage(Constants.LABEL_NO_CONFORMIDAD_CLIENTE, null, locale));
			addBitacoraSolicitanteNoConfClte(tareaAux);
		}
	}

	/**
	 * @param tareaAux
	 * @return int
	 */
	private int envioEmailAsignacionTarea(Tareas tareaAux) {
		return this.enviarEmailAsignacionTarea(tareaAux, null);
	}

	/**
	 * Registra la ausencia en el calendario personal en caso de que la tarea esté
	 * asociada a un recurso interno
	 *
	 * @param tarea Tareas
	 * @return int
	 */
	private int addAusenciaCalendarioPersonal(Tareas tarea) {
		int rst = 0;

		if (TareasServiceUtils.isTareaInterpretacionInterna(tarea)) {
			rst = gestionarAusenciaCalendarioPersonal(tarea);
		}

		return rst;

	}

	/**
	 * @param tarea
	 * @return int
	 */
	private int gestionarAusenciaCalendarioPersonal(Tareas tarea) {
		int rst = 0;
		if (TareasServiceUtils.isEstadoTareaAceptada(tarea)) {
			AusenciasCalendarioPersonal ausenciasCalendarioPersonal = new AusenciasCalendarioPersonal();
			ausenciasCalendarioPersonal.setIdTarea(tarea.getIdTarea());
			ausenciasCalendarioPersonal.setFechaDesde(tarea.getFechaIni());
			ausenciasCalendarioPersonal.setFechaHasta(tarea.getFechaFin());
			ausenciasCalendarioPersonal.setAnyo(tarea.getAnyo());
			ausenciasCalendarioPersonal.setDni(tarea.getPersonaAsignada().getDni());
			ausenciasCalendarioPersonal.setTipoJornada(TipoJornadaEnum.INTERPRETACION.getValue());

			rst = addAusenciaCalendario(ausenciasCalendarioPersonal);
		} else {
			this.ausenciasCalendarioPersonalDao.removeDatosTarea(tarea.getIdTarea());
		}
		return rst;
	}

	/**
	 * @param tarea
	 */
	private void asignarDatosAsignacionTarea(Tareas tarea) {
		PersonalIZO personaAsignador = new PersonalIZO();
		if (TareasServiceUtils.isRecursoAsignacion(tarea)) {
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

	/**
	 * @param tarea
	 * @param documentosSelect
	 * @return
	 */
	private Tareas addTarea(Tareas tarea, String documentosSelect) {

		relacionarConTareaEntregaClte(tarea);

		Tareas tareaAux = this.tareasDao.add(tarea);

		if (TareasServiceUtils.isNotTareaNula(tareaAux)) {
			BigDecimal idTarea = tareaAux.getIdTarea();
			EjecucionTareas ejecucionTareas = new EjecucionTareas();
			ejecucionTareas.setIdTarea(idTarea);

			this.ejecucionTareasDao.add(ejecucionTareas);

			this.addDocumentosTarea(idTarea, documentosSelect);

		}
		return tareaAux;
	}

	/**
	 * @param tarea
	 */
	private void relacionarConTareaEntregaClte(Tareas tarea) {
		if (TareasServiceUtils.isTareaNoConformidadClte(tarea)) {
			Tareas tareaEntrega = this.aa79bTareaWsDao.findTareaEntregaCliente(tarea);

			if (TareasServiceUtils.isNotTareaNula(tareaEntrega)) {
				tarea.setIdTareaRel(tareaEntrega.getIdTarea());
			}
		}
	}

	/**
	 * @param tarea
	 */
	private void addBitacoraSolicitanteNoConfClte(Tareas tarea) {
		BitacoraSolicitante bitacoraSolicitante = new BitacoraSolicitante();
		bitacoraSolicitante.setAnyo(tarea.getAnyo());
		bitacoraSolicitante.setNumExp(tarea.getNumExp());
		bitacoraSolicitante.setUsuario(Constants.IZO);
		this.bitacoraExpedienteDao.addBitacoraSolicitante(bitacoraSolicitante,
				AccionBitacoraEnum.NO_CONFORMIDAD_CLIENTE.getValue());
	}

	/**
	 * @param documentosSelect
	 * @param idTarea
	 */
	private void addDocumentosTarea(BigDecimal idTarea, String documentosSelect) {
		DocumentoTareaAdjunto documentoTareaAdjunto = new DocumentoTareaAdjunto();
		documentoTareaAdjunto.setIdTarea(idTarea);

		// Eliminamos duplicados
		Set<String> hsDocs = Utils.eliminarDuplicados(documentosSelect);

		for (String descDocu : hsDocs) {
			if (!descDocu.isEmpty()) {
				documentoTareaAdjunto.setIdDocOriginal(new BigDecimal(descDocu));

				this.documentosGeneralDao.add83(documentoTareaAdjunto);
			}
		}
	}

	/**
	 * @param tareaAux
	 */
	public void actualizarDatosExpediente(Tareas tareaAux) {
		if (TareasServiceUtils.isTareaCrearProyTrados(tareaAux)) {
			actualizarDatosExp(tareaAux.getAnyo(), tareaAux.getNumExp(),
					FaseExpedienteEnum.PDTE_PROYECTO_TRADOS.getValue(), null);
		}
	}

	/**
	 * @param anyo
	 * @param numExp
	 * @param faseExpediente
	 * @param infoAdicional
	 */
	public void actualizarDatosExp(Long anyo, Integer numExp, long faseExpediente, String infoAdicional) {
		BitacoraExpediente bitacora = new BitacoraExpediente();
		bitacora.setAnyo(anyo);
		bitacora.setNumExp(numExp);
		if (infoAdicional != null) {
			bitacora.setInfoAdic(infoAdicional);
		}

		EstadosExpediente estadoExp = new EstadosExpediente();
		estadoExp.setId((long) EstadoExpedienteEnum.EN_CURSO.getValue());
		bitacora.setEstadoExp(estadoExp);

		FasesExpediente faseExp = new FasesExpediente();
		faseExp.setId(faseExpediente);
		bitacora.setFaseExp(faseExp);

		BitacoraExpediente bitacoraAux = this.bitacoraExpedienteDao.add(bitacora);
		bitacoraAux.getIdEstadoBitacora();

		Expediente expediente = new Expediente();
		expediente.setAnyo(anyo);
		expediente.setNumExp(numExp);
		expediente.setEstadoBitacora(bitacoraAux.getIdEstadoBitacora());

		this.expedienteDao.updateIdEstadoBitacora(expediente);
	}

	@Transactional(rollbackFor = Throwable.class)
	private int update(Tareas tarea, String documentosSelect) {
		int rst = 0;

		if (TareasServiceUtils.isTareaNoNula(tarea)) {
			Tareas original = this.tareasDao.findConfTarea(tarea);

			asignarDatosAsignacionTarea(tarea);
			TareasUtils tareasUtils = new TareasUtils();
			tareasUtils.asignarDatosAceptacion(tarea, original);

			this.tareasDao.update(tarea);

			List<Tareas> listTareasDependientes = null;
			if (TareasServiceUtils.isTareaTradRev(tarea)
					&& TareasServiceUtils.isCambioRecursoAsignacionIndFact(tarea, original)) {
				List<Tareas> listTareas = new ArrayList<Tareas>();
				listTareas.add(tarea);
				listTareasDependientes = this.tareasDao.findTareasDependientesAEliminar(listTareas);
			}
			Integer idProcesoMail = this.tareasDao.procCrearTareasRelacionadasPL(tarea.getIdTarea());
			if (tarea.getDatosPagoProveedores() != null) {
				tarea.getDatosPagoProveedores().setIdTarea(tarea.getIdTarea());
				this.tareasDao.procActDatosPagoProvPL(tarea.getDatosPagoProveedores());
			}
			if (0 != idProcesoMail) {
				ProcesoEmail bean = new ProcesoEmail();
				bean.setIdProceso(idProcesoMail);
				this.procesoEmailService.enviarMail(bean);
			}
			String idDocs = this.documentosGeneralDao.getDocumentosTareaStr(tarea.getIdTarea());

			if (TareasServiceUtils.isEnvioEmailCambioDatosTarea(documentosSelect, idDocs, tarea, original)) {
				// Se envía mail de aviso de modificación de la documentación de
				// la tarea
				rst = this.enviarEmailModificacionDocTarea(tarea);
			}

			// Se eliminan todos los documentos asociados a la tarea
			this.documentosGeneralDao.removeDocumentosTarea(tarea.getIdTarea());

			// Se dan de alta todos los documentos asociados a la tarea
			this.addDocumentosTarea(tarea.getIdTarea(), documentosSelect);

			// Se registra o actualiza la ausencia en el calendario personal en
			// caso de que la tarea esté asociada a un recurso interno o se
			// elimina el registro, en caso de que esté asociada a un recurso
			// externo.
			rst = updateAusenciaCalendarioPersonal(tarea);

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
			if (listTareasDependientes != null) {
				for (Tareas tareasDept : listTareasDependientes) {
					if (!TareasServiceUtils.isCambioRecursoAsignacion(tarea, original)) {
						if (tareasDept.getTipoTarea().getId015()
								.intValue() == TipoTareaGestionAsociadaEnum.REVISION_PAGO_PROVEEDOR.getValue()) {
							this.enviarEmailEliminacionTareas(tareasDept);
						}
					} else {
						this.enviarEmailEliminacionTareas(tareasDept);
					}
				}
			}

			// Registro de acciones
			this.registrarAccionTareaModificada(tarea, original);
		}

		return rst;
	}

	/**
	 * @param tarea
	 * @param original
	 * @return int
	 */
	private int envioEmailAsignacionTarea(Tareas tarea, Tareas original) {

		return this.enviarEmailAsignacionTarea(tarea, original);
	}

	/**
	 * Registra o actualiza la ausencia en el calendario personal en caso de que la
	 * tarea esté asociada a un recurso interno o se elimina el registro, en caso de
	 * que esté asociada a un recurso externo
	 *
	 * @param tarea Tareas
	 * @return int
	 */
	private int updateAusenciaCalendarioPersonal(Tareas tarea) {
		TareasServiceUtils tareasServiceUtils = new TareasServiceUtils();
		return tareasServiceUtils.updateAusenciaCalendarioPersonal(tarea);
	}

	/**
	 * @param ausenciasCalendarioPersonal
	 * @return int
	 */
	private int addAusenciaCalendario(AusenciasCalendarioPersonal ausenciasCalendarioPersonal) {
		TareasServiceUtils tareasServiceUtils = new TareasServiceUtils();
		return tareasServiceUtils.addAusenciaCalendario(ausenciasCalendarioPersonal);
	}

	@Override
	public TareaIntPagoProveed getDatosTareaInt(Long idTarea) {
		return this.tareasDao.getDatosTareaInt(idTarea);
	}

	@Override
	public String getRecursoAsignado(Tareas tarea) {
		return this.tareasDao.getRecursoAsignado(tarea);
	}

	@Override()
	public HorasEstimadas calcularHorasPrevistasTradRev(Expediente expediente, String documentosSelect,
			BigDecimal idTipoTarea, Tareas tarea) {
		HorasEstimadas horasEstimadas = new HorasEstimadas();
		horasEstimadas.setHorasIZO(this.tareasDao.calcularHorasPrevistasTradRev(Constants.ROL_PERSONAL_IZO, expediente,
				documentosSelect, idTipoTarea, tarea));
		horasEstimadas.setHorasProveedor(this.tareasDao.calcularHorasPrevistasTradRev(Constants.ROL_PROVEEDOR_EXTERNO,
				expediente, documentosSelect, idTipoTarea, tarea));

		return horasEstimadas;
	}

	@Override()
	public String calcularHorasPrevistasInter(Expediente expediente) {
		return this.tareasDao.calcularHorasPrevistasInter(expediente);
	}

	@Override()
	public BigDecimal calcularImportePresupuestoInter(Tareas tarea) {
		return this.tareasDao.calcularImportePresupuestoInter(tarea);
	}

	@Override()
	public BigDecimal calcularImportePrevistoLote(BigDecimal idTarea, Integer idLote) {
		return this.tareasDao.calcularImportePrevistoLote(idTarea, idLote);
	}

	@Override()
	public int comprobarOrdenTareas(Tareas tarea) {
		return this.tareasDao.comprobarOrdenTareas(tarea);
	}

	@Override()
	public int comprobarTipoTarea(Tareas tarea) {
		return this.tareasDao.comprobarTipoTarea(tarea);
	}

	@Override()
	public JQGridResponseDto<Tareas> ultimasTareasInterpretacion(JQGridRequestDto jqGridRequestDto) {

		@SuppressWarnings("unchecked")
		List<Tareas> listaTareas = (List<Tareas>) this.tareasDao.ultimasTareasInterpretacion(jqGridRequestDto, false);

		Long recordNum = 0L;
		if (listaTareas != null && !listaTareas.isEmpty()) {
			recordNum = (Long) this.tareasDao.ultimasTareasInterpretacion(jqGridRequestDto, true);
		}

		if (jqGridRequestDto != null) {
			return new JQGridResponseDto<Tareas>(jqGridRequestDto, recordNum, listaTareas);
		} else {
			return new JQGridResponseDto<Tareas>(new JQGridRequestDto(), recordNum, listaTareas);
		}
	}

	@Override()
	public int comprobarFechaFinTarea(Tareas tarea) {
		return this.tareasDao.comprobarFechaFinTarea(tarea);
	}

	@Override
	public Tareas findConfTarea(Tareas bean) {
		return this.tareasDao.findConfTarea(bean);
	}

	@Override
	public JQGridResponseDto<EstudioEstimado> obtenerEstudio(DatosEstimacion datosEstimacion,
			JQGridRequestDto jqGridRequestDto) {

		List<EstudioEstimado> listaT = this.tareasDao.obtenerEstudio(datosEstimacion, jqGridRequestDto);
		Long recordNum = this.tareasDao.obtenerEstudioCount(datosEstimacion);

		return new JQGridResponseDto<EstudioEstimado>(jqGridRequestDto, recordNum, listaT);
	}

	@Override
	public int notificarCorreccionProveedor(Tareas tarea, Fichero fichero) {

		asignarDatosNotifCorrecProv(tarea);
		return ejecutarAccionesNotifCorrecProv(tarea, fichero);

	}

	@Override
	public int notificarCorreccionProvTradExt(Tareas tarea, Fichero fichero) {

		asignarDatosNotifCorrecProv(tarea);
		asignarLoteTareaTradExt(tarea);
		return ejecutarAccionesNotifCorrecProv(tarea, fichero);

	}

	/**
	 * @param tarea
	 */
	private void asignarDatosNotifCorrecProv(Tareas tarea) {
		Date fecha = new Date();
		String hora = DateUtils.obtHoraFormateada(fecha);

		tarea.setFechaIni(fecha);
		tarea.setHoraIni(hora);
		tarea.setFechaFin(fecha);
		tarea.setHoraFin(hora);
		tarea.setHorasPrevistas(Constants.HORA_MINUTOS_CERO);
		tarea.setEstadoAsignado(EstadoAceptacionTareaEnum.ACEPTADA.getValue());
		tarea.setEstadoEjecucion(EstadoEjecucionTareaEnum.EJECUTADA.getValue());
		tarea.setFechaAsignacion(fecha);
		tarea.setFechaAceptacion(fecha);
		tarea.setRecursoAsignacion(TipoRecursoEnum.EXTERNO.getValue());

		PersonalIZO personaAsignador = new PersonalIZO();
		Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication().getCredentials();
		personaAsignador.setDni(credentials.getNif());
		tarea.setPersonaAsignador(personaAsignador);

		TiposTarea tipoTarea = new TiposTarea();
		tipoTarea.setId015((long) TipoTareaGestionAsociadaEnum.NOTIFICAR_CORRECCIONES_PROVEEDOR.getValue());

		tarea.setTipoTarea(tipoTarea);
	}

	/**
	 * @param tarea
	 * @param documentosSelect
	 */
	private int ejecutarAccionesNotifCorrecProv(Tareas tarea, Fichero fichero) {
		int rst = 0;
		// Insertar registro en la tabla 81
		Tareas tareaAux = this.tareasDao.add(tarea);

		if (TareasServiceUtils.isNotTareaNula(tareaAux)) {
			BigDecimal idTarea = tareaAux.getIdTarea();
			EjecucionTareas ejecucionTareas = new EjecucionTareas();
			ejecucionTareas.setIdTarea(idTarea);
			ejecucionTareas.setHorasTarea(Constants.HORA_MINUTOS_CERO);
			ejecucionTareas.setIndRealizada(Constants.SI);

			// Insertar registro en la tabla 82
			this.ejecucionTareasDao.addConFechaEjec(ejecucionTareas);

			DocumentoTareaAdjunto bean = new DocumentoTareaAdjunto();
			bean.setNombre(fichero.getNombre());
			bean.setExtension(fichero.getExtension());
			bean.setEncriptado(fichero.getEncriptado());
			bean.setTamano(fichero.getTamano());
			bean.setContentType(fichero.getContentType());
			bean.setTitulo(fichero.getNombre());
			bean.setOid(fichero.getOid());
			// Insertar registro en la tabla 88
			DocumentoTareaAdjunto docuAdd = this.documentosGeneralDao.add(bean);
			// Borrar oid nuevo de la tabla A0
			this.oidsAuxiliarDao.remove(bean.getOid());

			DocumentosCorreccion documentosCorreccion = new DocumentosCorreccion();
			documentosCorreccion.setIdTarea(idTarea);
			documentosCorreccion.setIdFichero(docuAdd.getIdFichero());
			// Insertar registro en la tabla B1
			this.documentosCorreccionDao.add(documentosCorreccion);

			this.registrarAccionEjecucionTarea(tareaAux);

			rst = this.enviarEmailNotifCorreccionProv(tareaAux);
		}

		return rst;
	}

	/**
	 * @param tarea
	 */
	private void asignarLoteTareaTradExt(Tareas tarea) {
		if (TareasServiceUtils.isIdLoteNulo(tarea)) {
			// En caso de que el lote sea null, indica que la notificación de la
			// corrección al proveedor procede de la tarea de revisión de
			// traducción externa

			Tareas bean = new Tareas();
			bean.setAnyo(tarea.getAnyo());
			bean.setNumExp(tarea.getNumExp());
			bean.setOrden(tarea.getOrden());
			BigDecimal idTarea = this.tareasDao.findTareaTraduccionExtEjec(bean);
			bean.setIdTarea(idTarea);
			Integer idLote = this.tareasDao.findLoteTareaTradEjec(bean);

			if (TareasServiceUtils.isIdLoteNoNulo(idLote)) {
				Lotes lotes = new Lotes();
				lotes.setIdLote(idLote);
				tarea.setLotes(lotes);
			}
		}
	}

	@Override
	public int generarNoConformidad(Tareas tarea, String documentosSelect, BigDecimal idTareaRel, Fichero fichero) {
		int rst = 0;
		TareasUtils tareasUtils = new TareasUtils();
		asignarDatosGeneralesNoConformidad(tarea);

		Tareas bean = new Tareas();
		bean.setAnyo(tarea.getAnyo());
		bean.setNumExp(tarea.getNumExp());
		bean.setIdTareaRel(idTareaRel);

		// Comprobar que no exista una tarea de no conformidad con el proveedor
		// sin ejecutar para los documentos seleccionados
		if (tareasUtils.isNotTareaNoConfProvSinEjec(bean, documentosSelect)) {
			// Se obtiene el lote a partir de la tarea original: Tarea de
			// traduccion en estado ejecutada
			Lotes lotes = new Lotes();
			bean = new Tareas();
			bean.setIdTarea(idTareaRel);
			Integer idLote = this.tareasDao.findLoteTareaTradEjec(bean);

			if (TareasServiceUtils.isIdLoteNoNulo(idLote)) {
				lotes.setIdLote(idLote);
				tarea.setLotes(lotes);
				tarea.setIdTareaRel(idTareaRel);

				ejecutarAccionesNoConformidad(tarea, documentosSelect, fichero);
			}
		} else {
			rst = 1;
		}

		return rst;

	}

	@Override
	public int noConformidadRevEntregaClte(Tareas tarea, String documentosSelect, Integer orden, Fichero fichero) {
		int rst = 0;
		TareasUtils tareasUtils = new TareasUtils();
		asignarDatosGeneralesNoConformidad(tarea);

		// Se obtiene el id de la tarea relacionada
		Tareas bean = new Tareas();
		bean.setAnyo(tarea.getAnyo());
		bean.setNumExp(tarea.getNumExp());
		bean.setOrden(orden);
		tarea.setIdTareaRel(this.tareasDao.findTareaRevisionExtEjec(bean));

		// Comprobar que no exista una tarea de no conformidad con el proveedor
		// sin ejecutar para los documentos seleccionados
		if (tareasUtils.isNotTareaNoConfProvSinEjec(tarea, documentosSelect)) {
			ejecutarAccionesNoConformidad(tarea, documentosSelect, fichero);
		} else {
			rst = 1;
		}

		return rst;
	}

	/**
	 * @param tarea
	 */
	private void asignarDatosGeneralesNoConformidad(Tareas tarea) {
		TiposTarea tipoTarea = new TiposTarea();
		tipoTarea.setId015((long) TipoTareaGestionAsociadaEnum.NO_CONFORMIDAD_PROVEEDOR.getValue());
		tarea.setTipoTarea(tipoTarea);

		PersonalIZO personaAsignador = new PersonalIZO();
		Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication().getCredentials();
		personaAsignador.setDni(credentials.getNif());
		tarea.setPersonaAsignador(personaAsignador);

		tarea.setRecursoAsignacion(TipoRecursoEnum.EXTERNO.getValue());
		TareasUtils.asignarDatosAceptacion(tarea);
		tarea.setFechaAsignacion(new Date());
	}

	/**
	 * @param tarea            Tareas
	 * @param documentosSelect String
	 * @param fichero          Fichero
	 * @return int
	 */
	private int ejecutarAccionesNoConformidad(Tareas tarea, String documentosSelect, Fichero fichero) {
		Tareas tareaAux = addTarea(tarea, documentosSelect);
		// anyadir documento si lo han adjuntado
		if (fichero != null && StringUtils.isNotBlank(fichero.getOid())) {
			DocumentoTareaAdjunto bean = new DocumentoTareaAdjunto();
			bean.setNombre(fichero.getNombre());
			bean.setExtension(fichero.getExtension());
			bean.setEncriptado(fichero.getEncriptado());
			bean.setTamano(fichero.getTamano());
			bean.setContentType(fichero.getContentType());
			bean.setTitulo(fichero.getNombre());
			bean.setOid(fichero.getOid());
			// Insertar registro en la tabla 88
			DocumentoTareaAdjunto docuAdd = this.documentosGeneralDao.add(bean);
			// Borrar oid nuevo de la tabla A0
			this.oidsAuxiliarDao.remove(bean.getOid());

			DocumentosCorreccion documentosCorreccion = new DocumentosCorreccion();
			documentosCorreccion.setIdTarea(tareaAux.getIdTarea());
			documentosCorreccion.setIdFichero(docuAdd.getIdFichero());
			// Insertar registro en la tabla B1
			this.documentosCorreccionDao.add(documentosCorreccion);
		}

		return enviarEmailGenerarNoConformidad(tarea);
	}

	@Override
	public int findLoteTareaRevisionEjec(Tareas bean) {
		return this.tareasDao.findLoteTareaRevisionEjec(bean);
	}

	@Override()
	@Transactional(rollbackFor = Throwable.class)
	public Tareas getDatosDocumentoPptoInterpretacion(Long anyo, Integer numExp, Long idRequerimiento) {
		Tareas laTarea = new Tareas();

		if (!idRequerimiento.equals(Constants.MAGIC_NUMBER_MENOS_1_L)) {
			SubsanacionExpediente elRequerimientoFilter = new SubsanacionExpediente();
			elRequerimientoFilter.setId(idRequerimiento);

			laTarea.setSubsanacion(this.subsanacionDao.find(elRequerimientoFilter));

			laTarea.setGestionInterpretacion(
					this.tareasGestionInterpretacionDao.getInfoDocumentoPresupuestoInterpretacion(anyo, numExp));
		} else {

			laTarea.setGestionInterpretacion(this.tareasGestionInterpretacionDao
					.getInfoDocumentoPresupuestoInterpretacionSinEjecutar(anyo, numExp));
		}

		FechaHoraIniFin fechasInterpretacion = new FechaHoraIniFin();
		fechasInterpretacion = this.tareasDao.getDocumentoPresupuestoInterpretacionFechaIniFin(anyo, numExp);
		laTarea.setFechaIni(fechasInterpretacion.getFechaIni());
		laTarea.setHoraIni(fechasInterpretacion.getHoraIni());
		laTarea.setFechaFin(fechasInterpretacion.getFechaFin());
		laTarea.setHoraFin(fechasInterpretacion.getHoraFin());
		laTarea.setIdRequerimiento(idRequerimiento);

		PersonalIZO personalIZO = new PersonalIZO();
		DatosPersona datosPersona = new DatosPersona();
		Entidad laEntidadFiltro = new Entidad();
		Solicitante solicitante = new Solicitante();

		ContactoFacturacionExpediente contactoFacturacionExpedienteFilter = new ContactoFacturacionExpediente();
		contactoFacturacionExpedienteFilter.setAnyo(anyo);
		contactoFacturacionExpedienteFilter.setNumExp(numExp);
		List<ContactoFacturacionExpediente> listaContactoFacturacionExpediente = this.contactoFacturacionExpedienteDao
				.findAll(contactoFacturacionExpedienteFilter, null);
		if (!listaContactoFacturacionExpediente.isEmpty()) {
			if (listaContactoFacturacionExpediente.size() == Constants.UNO) {
				// contacto
				if (StringUtils
						.isNotEmpty(listaContactoFacturacionExpediente.get(Constants.CERO).getDniContacto058())) {

					laEntidadFiltro
							.setTipo(listaContactoFacturacionExpediente.get(Constants.CERO).getTipoEntidadAsoc058());
					laEntidadFiltro
							.setCodigo(listaContactoFacturacionExpediente.get(Constants.CERO).getIdEntidadAsoc058());
					personalIZO.setEntidad(this.entidadDao.find(laEntidadFiltro));

					solicitante.setDni(listaContactoFacturacionExpediente.get(Constants.CERO).getDniContacto058());
					solicitante = this.solicitanteDao.findSolicitanteConDatosDireccion(solicitante);
					personalIZO.setNombreCompleto(solicitante.getNombreCompleto());
					personalIZO.setNombre(solicitante.getNombre());
					personalIZO.setApellido1(solicitante.getApellido1());
					personalIZO.setApellido2(solicitante.getApellido2());

					personalIZO.getEntidad().setDireccion(solicitante.getEntidad().getDireccion());

				} else {
					// entidad: FALTA AÑADIR LA DIRECCION DE FACTURACION CUANDO
					// NOS
					// CREEN LA VISTA DE X54J
					laEntidadFiltro
							.setTipo(listaContactoFacturacionExpediente.get(Constants.CERO).getTipoEntidadAsoc058());
					laEntidadFiltro
							.setCodigo(listaContactoFacturacionExpediente.get(Constants.CERO).getIdEntidadAsoc058());
					personalIZO.setEntidad(this.entidadDao.find(laEntidadFiltro));
				}

			} else {
				// Consultar tabla 54 para obtener el dni del gestor (dni_solic)
				Expediente expAux = new Expediente();
				expAux.setAnyo(anyo);
				expAux.setNumExp(numExp);
				expAux = this.gestorExpedienteDao.find(expAux);

				datosPersona.setDni(expAux.getGestorExpediente().getSolicitante().getDni());
				datosPersona = this.datosPersonaDao.find(datosPersona);
				personalIZO.setNombreCompleto(datosPersona.getNombreCompleto());
				personalIZO.setNombre(datosPersona.getNombre());
				personalIZO.setApellido1(datosPersona.getApellido1());
				personalIZO.setApellido2(datosPersona.getApellido2());
				// entidad: FALTA AÑADIR LA DIRECCION DE FACTURACION CUANDO NOS
				// CREEN LA VISTA DE X54J
				laEntidadFiltro.setTipo(expAux.getGestorExpediente().getEntidad().getTipo());
				laEntidadFiltro.setCodigo(expAux.getGestorExpediente().getEntidad().getCodigo());
				personalIZO.setEntidad(this.entidadDao.find(laEntidadFiltro));

			}
			StringBuilder laDireccionCompuesta = new StringBuilder();
			laDireccionCompuesta.append(personalIZO.getEntidad().getDireccion().getDireccion());
			laDireccionCompuesta.append(Constants.COMA).append(Constants.ESPACIO)
					.append(personalIZO.getEntidad().getDireccion().getTxtLocalidad());
			laDireccionCompuesta.append(Constants.COMA).append(Constants.ESPACIO)
					.append(personalIZO.getEntidad().getDireccion().getTxtMunicipio());
			laDireccionCompuesta.append(Constants.PUNTO).append(Constants.ESPACIO)
					.append(personalIZO.getEntidad().getDireccion().getCodPostal());
			laDireccionCompuesta.append(Constants.ESPACIO).append(Constants.GUION).append(Constants.ESPACIO)
					.append(personalIZO.getEntidad().getDireccion().getTxtProvincia());
			personalIZO.getEntidad().getDireccion().setDireccion(laDireccionCompuesta.toString());

			laTarea.setPersonaAsignada(personalIZO);
		}

		return laTarea;
	}

	@Override()
	@Transactional(rollbackFor = Throwable.class)
	public DocPresupuestoTraduccion getDatosDocumentoPptoTraduccion(Long anyo, Integer numExp, Long idRequerimiento) {
		DocPresupuestoTraduccion docPresupuestoTraduccion = new DocPresupuestoTraduccion();

		if (!idRequerimiento.equals(Constants.MAGIC_NUMBER_MENOS_1_L)) {
			SubsanacionExpediente elRequerimientoFilter = new SubsanacionExpediente();
			elRequerimientoFilter.setId(idRequerimiento);

			docPresupuestoTraduccion.setSubsanacion(this.subsanacionDao.find(elRequerimientoFilter));
		}

		docPresupuestoTraduccion.setDatosTareaTrados(
				this.datosTareaTradosDao.getInfoDocumentoPresupuestoTraduccion(anyo, numExp, idRequerimiento));

		docPresupuestoTraduccion
				.setDatosExpediente(this.expedienteTradRevDao.getInfoDocumentoPresupuestoTraduccion(anyo, numExp));

		docPresupuestoTraduccion.setUrlOrden(this.ordenPreciosDao.getUrlOrden());

		docPresupuestoTraduccion.setIdRequerimiento(idRequerimiento);

		PersonalIZO personalIZO = new PersonalIZO();
		DatosPersona datosPersona = new DatosPersona();
		Entidad laEntidadFiltro = new Entidad();
		Solicitante solicitante = new Solicitante();

		ContactoFacturacionExpediente contactoFacturacionExpedienteFilter = new ContactoFacturacionExpediente();
		contactoFacturacionExpedienteFilter.setAnyo(anyo);
		contactoFacturacionExpedienteFilter.setNumExp(numExp);
		List<ContactoFacturacionExpediente> listaContactoFacturacionExpediente = this.contactoFacturacionExpedienteDao
				.findAll(contactoFacturacionExpedienteFilter, null);
		if (!listaContactoFacturacionExpediente.isEmpty()) {
			if (listaContactoFacturacionExpediente.size() == Constants.UNO) {
				// contacto
				if (StringUtils
						.isNotEmpty(listaContactoFacturacionExpediente.get(Constants.CERO).getDniContacto058())) {

					laEntidadFiltro
							.setTipo(listaContactoFacturacionExpediente.get(Constants.CERO).getTipoEntidadAsoc058());
					laEntidadFiltro
							.setCodigo(listaContactoFacturacionExpediente.get(Constants.CERO).getIdEntidadAsoc058());
					personalIZO.setEntidad(this.entidadDao.find(laEntidadFiltro));

					solicitante.setDni(listaContactoFacturacionExpediente.get(Constants.CERO).getDniContacto058());
					solicitante = this.solicitanteDao.findSolicitanteConDatosDireccion(solicitante);
					personalIZO.setNombreCompleto(solicitante.getNombreCompleto());
					personalIZO.setNombre(solicitante.getNombre());
					personalIZO.setApellido1(solicitante.getApellido1());
					personalIZO.setApellido2(solicitante.getApellido2());

					personalIZO.getEntidad().setDireccion(solicitante.getEntidad().getDireccion());

				} else {
					// entidad: FALTA AÑADIR LA DIRECCION DE FACTURACION CUANDO
					// NOS
					// CREEN LA VISTA DE X54J
					laEntidadFiltro
							.setTipo(listaContactoFacturacionExpediente.get(Constants.CERO).getTipoEntidadAsoc058());
					laEntidadFiltro
							.setCodigo(listaContactoFacturacionExpediente.get(Constants.CERO).getIdEntidadAsoc058());
					personalIZO.setEntidad(this.entidadDao.find(laEntidadFiltro));
				}

			} else {
				// Consultar tabla 54 para obtener el dni del gestor (dni_solic)
				Expediente expAux = new Expediente();
				expAux.setAnyo(anyo);
				expAux.setNumExp(numExp);
				expAux = this.gestorExpedienteDao.find(expAux);

				datosPersona.setDni(expAux.getGestorExpediente().getSolicitante().getDni());
				datosPersona = this.datosPersonaDao.find(datosPersona);
				personalIZO.setNombreCompleto(datosPersona.getNombreCompleto());
				personalIZO.setNombre(datosPersona.getNombre());
				personalIZO.setApellido1(datosPersona.getApellido1());
				personalIZO.setApellido2(datosPersona.getApellido2());
				// entidad: FALTA AÑADIR LA DIRECCION DE FACTURACION CUANDO NOS
				// CREEN LA VISTA DE X54J
				laEntidadFiltro.setTipo(expAux.getGestorExpediente().getEntidad().getTipo());
				laEntidadFiltro.setCodigo(expAux.getGestorExpediente().getEntidad().getCodigo());
				personalIZO.setEntidad(this.entidadDao.find(laEntidadFiltro));

			}

			StringBuilder laDireccionCompuesta = new StringBuilder();
			laDireccionCompuesta.append(personalIZO.getEntidad().getDireccion().getDireccion());
			laDireccionCompuesta.append(Constants.COMA).append(Constants.ESPACIO)
					.append(personalIZO.getEntidad().getDireccion().getTxtLocalidad());
			laDireccionCompuesta.append(Constants.COMA).append(Constants.ESPACIO)
					.append(personalIZO.getEntidad().getDireccion().getTxtMunicipio());
			laDireccionCompuesta.append(Constants.PUNTO).append(Constants.ESPACIO)
					.append(personalIZO.getEntidad().getDireccion().getCodPostal());
			laDireccionCompuesta.append(Constants.ESPACIO).append(Constants.GUION).append(Constants.ESPACIO)
					.append(personalIZO.getEntidad().getDireccion().getTxtProvincia());
			personalIZO.getEntidad().getDireccion().setDireccion(laDireccionCompuesta.toString());

			docPresupuestoTraduccion.setPersonaAsignada(personalIZO);
		}

		return docPresupuestoTraduccion;
	}

	@Override()
	@Transactional(rollbackFor = Throwable.class)
	public DocPresupuestoTraduccion getDatosDocumentoPptoTraduccionLang(Long anyo, Integer numExp, Long idRequerimiento,
			String idioma) {
		DocPresupuestoTraduccion docPresupuestoTraduccion = new DocPresupuestoTraduccion();

		docPresupuestoTraduccion.setDatosTareaTrados(
				this.datosTareaTradosDao.getInfoDocumentoPresupuestoTraduccion(anyo, numExp, idRequerimiento));

		docPresupuestoTraduccion.setDatosExpediente(
				this.expedienteTradRevDao.getInfoDocumentoPresupuestoTraduccionLang(anyo, numExp, idioma));

		return docPresupuestoTraduccion;
	}

	@Override()
	public Tareas findDetalleTarea(BigDecimal idTarea, String idioma) {
		return this.tareasDao.findDetalleTarea(idTarea, idioma);
	}

	@Override()
	public TiposRevision findTipoRevisionTareaRel(BigDecimal idTarea) {
		return this.tareasDao.findTipoRevisionTareaRel(idTarea);
	}

	@Override()
	public Tareas findTareaTrados(Long anyo, Integer numExp) {
		return this.tareasDao.findTareaTrados(anyo, numExp);
	}

	@Override
	public long comprobarTareasPendientes(Tareas tareas) {
		return this.tareasDao.comprobarTareasPendientes(tareas);
	}

	@Override()
	public int reabrirTarea(BigDecimal idTarea) {
		Tareas tarea = new Tareas();
		tarea.setIdTarea(idTarea);
		Tareas tareaAux = this.tareasDao.findConfTarea(tarea);
		this.tareasDao.reabrirTarea(idTarea);
		registrarAccionReaperturaTarea(tareaAux);
		this.actualizarDatosExpediente(tareaAux);
		return this.enviarEmailReaperturaTarea(tareaAux);
	}

	@Override()
	public int preReabrirTarea(BigDecimal idTarea) {
		return this.tareasDao.comprobarReaperturaTarea(idTarea);
	}

	/**
	 * @param tareas Tareas
	 */
	private void registrarAccionReaperturaTarea(Tareas tareas) {
		registrarAccionTarea(tareas, LABEL_REAPERTURA_TAREA, false);
	}

	@Override()
	public int findTareasAutomaticasCount(List<String> listSelectedIds, String tipoExpediente) {
		return this.tareasDao.findTareasAutomaticasCount(listSelectedIds, tipoExpediente);
	}

	@Override
	public Tareas findTareaRevisionExterna(Long anyo, Integer numExp) {
		return this.tareasDao.findTareaRevisionExterna(anyo, numExp);
	}

	@Override
	public Integer actualizarFechaFinalIzo(EntradaDatosSolicitud bean) {
		return this.tareasDao.actualizarFechaFinalIzo(bean);
	}

	@Override
	public List<Integer> findCountTareasRangoFechas(Tareas tareas) {

		List<Tareas> listTareasInterp = this.tareasDao.findTareasRangoFechas(tareas, Constants.UNO);
		List<Tareas> listTareasNOInterp = this.tareasDao.findTareasRangoFechas(tareas, Constants.DOS);

		List<Integer> numTareas = new ArrayList<Integer>();
		numTareas.add(listTareasInterp.size());
		numTareas.add(listTareasNOInterp.size());

		return numTareas;
	}

	@Override
	public List<Tareas> findTareasRangoFechas(Tareas tareas) {
		return this.tareasDao.findTareasRangoFechas(tareas);
	}

	@Override
	public String calcularHorasEntreFechas(Tareas tarea) {
		return this.tareasDao.calcularHorasEntreFechas(tarea);
	}

	private int enviarEmailAsignador(Tareas tarea, int tipoTarea) {
		List<String> listaDestinatarios = new ArrayList<String>();
		int rst = 0;

		if (this.appConfiguration.getProperty(Constants.APP_ENTORNO) != null) {
			if (Constants.LOCAL.equals(this.appConfiguration.getProperty(Constants.APP_ENTORNO))) {
				listaDestinatarios.add(Constants.DESTINATARIO_EMAIL_LOCAL);
			} else if (TareasServiceUtils.isDniNotNull(tarea.getPersonaAsignador())) {
				String email = this.obtenerEmailContacto(tarea.getPersonaAsignador().getDni());
				listaDestinatarios.add(email);
			}

			if (!listaDestinatarios.isEmpty()) {
				ParametrosEmail parametrosEmail = new ParametrosEmail();

				Locale localeEu = new Locale(Constants.LANG_EUSKERA);
				Map<String, String> infoEu = new LinkedHashMap<String, String>();
				Locale localeEs = new Locale(Constants.LANG_CASTELLANO);
				Map<String, String> infoEs = new LinkedHashMap<String, String>();

				infoEu.put(this.msg.getMessage(LABEL_TAREA, null, localeEu), tarea.getIdTarea()
						+ Constants.GUION_CON_ESPACIOS + TareasServiceUtils.obtenerDescripcionTipoTarea(localeEu, tarea)
						+ Constants.ABRIR_PARENTESIS + this.msg.getMessage(Constants.LABEL_NUM_EXP, null, localeEu)
						+ tarea.getAnyoNumExpConcatenado() + Constants.CERRAR_PARENTESIS);
				infoEs.put(this.msg.getMessage(LABEL_TAREA, null, localeEs), tarea.getIdTarea()
						+ Constants.GUION_CON_ESPACIOS + TareasServiceUtils.obtenerDescripcionTipoTarea(localeEs, tarea)
						+ Constants.ABRIR_PARENTESIS + this.msg.getMessage(Constants.LABEL_NUM_EXP, null, localeEs)
						+ tarea.getAnyoNumExpConcatenado() + Constants.CERRAR_PARENTESIS);

				parametrosEmail.setInfoEu(infoEu);
				parametrosEmail.setInfoEu(infoEs);

				parametrosEmail.setAnyoNumExpediente(Utils.getNumExpedienteParameter(tarea));

				int tipoAviso = TipoAvisoEnum.MODIF_CALENDARIO.getValue();
				if (tipoTarea == Constants.UNO) {
					tipoAviso = TipoAvisoEnum.PETICION_AUSENCIA.getValue();
				}
				try {

					this.mailService.sendMailWithParameters(tipoAviso, listaDestinatarios, parametrosEmail);
				} catch (Exception e) {
					TareasServiceImpl.LOGGER.info(ERROR_ENVIO_EMAIL, e);
					rst = -2;
				}
			} else {
				rst = -1;
			}
		}

		return rst;
	}

	@Override
	public long comprobarTareasAEliminar(List<String> listSelectedIds) {
		return this.tareasDao.comprobarTareasAEliminar(listSelectedIds);
	}

	@Override
	public Long findAllCountSinEjecutar(Tareas tareas) {
		return this.tareasDao.findAllCountSinEjecutar(tareas);
	}

	@Override
	public long findAllCountTareasAsignadas(Tareas tareas) {
		return this.tareasDao.findAllCountTareasAsignadas(tareas);
	}

	@Override
	public Tareas findDatosCorreccionesProv(Tareas bean) {
		return this.tareasDao.findDatosCorreccionesProv(bean);
	}

	@Override
	public void getmodelMapDocumentoTraduccion(Locale locale, ModelMap modelMap,
			List<DocPresupuestoTraduccion> lDatos) {
		Locale localEu = new Locale("eu");
		Locale localEs = new Locale("es");

		// Nombre fichero
		modelMap.put("fileName", this.appMessageSource.getMessage(LABEL_PRESUPUESTO, null, locale));
		// En línea (no descarga fichero) ?
		modelMap.put("isInline", false);
		// Cabeceras comunes y locale (parameter)
		modelMap.put("REPORT_LOCALE", locale);
		modelMap.put("STATICS", this.appConfiguration.getProperty("datos.path"));
		modelMap.put("RUTAWAR", this.appConfiguration.getProperty("war.path"));

		modelMap.put("TITULO_ES", " / " + this.appMessageSource.getMessage("label.docPptoTraducTitulo", null, localEs));
		modelMap.put("TITULO_EU", this.appMessageSource.getMessage("label.docPptoTraducTitulo", null, localEu));

		modelMap.put("TEMA_ES", " / " + this.appMessageSource.getMessage("label.tema", null, localEs));
		modelMap.put("TEMA_EU", this.appMessageSource.getMessage("label.tema", null, localEu));
		modelMap.put("NUM_TOTAL_PAL_ES", this.appMessageSource.getMessage("label.numTotalPalabras", null, localEs));
		modelMap.put("NUM_TOTAL_PAL_EU", this.appMessageSource.getMessage("label.numTotalPalabras", null, localEu));
		modelMap.put("TOTAL_ES", " / " + this.appMessageSource.getMessage(LABEL_TOTAL, null, localEs));
		modelMap.put("TOTAL_EU", this.appMessageSource.getMessage(LABEL_TOTAL, null, localEu));
		modelMap.put("IVA_INC_ES", " / " + this.appMessageSource.getMessage(LABEL_IVA_INCLUIDO, null, localEs));
		modelMap.put("IVA_INC_EU", this.appMessageSource.getMessage(LABEL_IVA_INCLUIDO, null, localEu));
		modelMap.put("FLIMITEACEPT_ES",
				this.appMessageSource.getMessage("label.fechaHoraLimiteAceptacion", null, localEs));
		modelMap.put("FLIMITEACEPT_EU",
				this.appMessageSource.getMessage("label.fechaHoraLimiteAceptacion", null, localEu));

		modelMap.put("INTRO_EU", this.appMessageSource.getMessage("label.docPptoTraducIntro", null, localEu));
		modelMap.put("INTRO_ES", this.appMessageSource.getMessage("label.docPptoTraducIntro", null, localEs));
		modelMap.put("CORDIALMENTE_EU", this.appMessageSource.getMessage("label.cordialmente", null, localEu));
		modelMap.put("TARIFA_ES", this.appMessageSource.getMessage(LABEL_TARIFA, null, localEs));
		modelMap.put("PIEDOC_EU", this.appMessageSource.getMessage(LABEL_DOCPPTOINTERPPIE, null, localEu));
		modelMap.put("PIEDOC_ES", this.appMessageSource.getMessage(LABEL_DOCPPTOINTERPPIE, null, localEs));

		modelMap.put("NOMBREEMP_ES", this.appMessageSource.getMessage("label.docPptoInterpNombreEmp", null, localEs));
		modelMap.put("PUESTOEMP_EU", this.appMessageSource.getMessage("label.docPptoInterpPuestoEmp", null, localEu));
		modelMap.put("LOCALIZEMP_EU", this.appMessageSource.getMessage("label.docPptoInterpLocalizEmp", null, localEu));
		/* PAGINA 2 */
		modelMap.put("ADJUNTO_EU", this.appMessageSource.getMessage("label.adjunto", null, localEu));
		String[] argsIntro2 = { lDatos.get(0).getDatosTareaTrados().getUrlOrdenPrecios() };
		modelMap.put("INTROPAG2_EU",
				this.appMessageSource.getMessage("label.docPptoTraducIntroPag2", argsIntro2, localEu));
		modelMap.put("INTROPAG2_ES",
				this.appMessageSource.getMessage("label.docPptoTraducIntroPag2", argsIntro2, localEs));
		/* tabla */
		modelMap.put("NUMEXP", this.appMessageSource.getMessage("label.numExp", null, localEu));
		modelMap.put("FECHASOLIC", this.appMessageSource.getMessage("label.fechaSolicitud", null, localEu));
		modelMap.put("TAREA", this.appMessageSource.getMessage("label.tarea", null, localEu));
		modelMap.put("IDIOMADEST", this.appMessageSource.getMessage("label.idiomaDestino", null, localEu));
		modelMap.put("TIPOTEXTO", this.appMessageSource.getMessage("label.tipoTexto", null, localEu));
		modelMap.put("BOPV", this.appMessageSource.getMessage("label.bopv", null, localEu));
		modelMap.put("TARIFA", this.appMessageSource.getMessage(LABEL_TARIFA, null, localEu));
		modelMap.put("NUMTOTPAL", this.appMessageSource.getMessage("label.documento.numTotalPal", null, localEu));
		modelMap.put("ENTRE084", this.appMessageSource.getMessage("label.entre084", null, localEu));
		modelMap.put("ENTRE8594", this.appMessageSource.getMessage("label.entre8594", null, localEu));
		modelMap.put("ENTRE95100", this.appMessageSource.getMessage("label.entre95100", null, localEu));
		modelMap.put("URGENCIA", this.appMessageSource.getMessage("label.urgencia", null, localEu));
		modelMap.put("DIFICULTAD", this.appMessageSource.getMessage("label.dificultad", null, localEu));
		modelMap.put("CERTIFICADO", this.appMessageSource.getMessage("label.certificado", null, localEu));
		modelMap.put("IMPORTEBASE", this.appMessageSource.getMessage("label.importeBase", null, localEu));
		modelMap.put("IVA", this.appMessageSource.getMessage("label.iva", null, localEu));
		modelMap.put("TOTAL", this.appMessageSource.getMessage(LABEL_TOTAL, null, localEu));

		modelMap.put("TRADUCCION_EU", this.appMessageSource.getMessage("label.traduccion", null, localEu));
		modelMap.put("TRADUCCION_ES", "/" + this.appMessageSource.getMessage("label.traduccion", null, localEs));
		modelMap.put("REVISION_EU", this.appMessageSource.getMessage("label.revision", null, localEu));
		modelMap.put("REVISION_ES", "/" + this.appMessageSource.getMessage("label.revision", null, localEs));
		modelMap.put("EUSKERA_EU", this.appMessageSource.getMessage("label.euskera", null, localEu));
		modelMap.put("EUSKERA_ES", "/" + this.appMessageSource.getMessage("label.euskera", null, localEs));
		modelMap.put("CASTELLANO_EU", this.appMessageSource.getMessage("label.castellano", null, localEu));
		modelMap.put("CASTELLANO_ES", "/" + this.appMessageSource.getMessage("label.castellano", null, localEs));
		modelMap.put("TRADRELEV_EU", this.appMessageSource.getMessage("label.traduccionesRelevantes", null, localEu));
		modelMap.put("TRADRELEV_ES",
				"/" + this.appMessageSource.getMessage("label.traduccionesRelevantes", null, localEs));
		modelMap.put("TEXTONORMAL_EU", this.appMessageSource.getMessage("label.textosNormales", null, localEu));
		modelMap.put("TEXTONORMAL_ES", "/" + this.appMessageSource.getMessage("label.textosNormales", null, localEs));
		modelMap.put("TEXTOSENCILLO_EU", this.appMessageSource.getMessage("label.textosSencillos", null, localEu));
		modelMap.put("TEXTOSENCILLO_ES",
				"/" + this.appMessageSource.getMessage("label.textosSencillos", null, localEs));
		modelMap.put("SI_EU", this.appMessageSource.getMessage("comun.si", null, localEu));
		modelMap.put("SI_ES", "/" + this.appMessageSource.getMessage("comun.si", null, localEs));
		modelMap.put("NO_EU", this.appMessageSource.getMessage("comun.no", null, localEu));
		modelMap.put("NO_ES", "/" + this.appMessageSource.getMessage("comun.no", null, localEs));
		modelMap.put("40PORC_EU", this.appMessageSource.getMessage("label.40Porc", null, localEu));
		modelMap.put("0PORC_EU", this.appMessageSource.getMessage("label.0Porc", null, localEu));
		modelMap.put("25PORC_EU", this.appMessageSource.getMessage("label.25Porc", null, localEu));

		String cuanMinAuxEs = new java.text.DecimalFormat("#,##0.00", new java.text.DecimalFormatSymbols(localEs))
				.format(lDatos.get(0).getDatosTareaTrados().getPrecioMinimo());
		String[] argsCuantiaMinEs = { cuanMinAuxEs };
		modelMap.put("CUANTIAMIN_EU",
				this.appMessageSource.getMessage("label.docPptoTraducCuantiaMin", argsCuantiaMinEs, localEu));
		modelMap.put("CUANTIAMIN_ES",
				this.appMessageSource.getMessage("label.docPptoTraducCuantiaMin", argsCuantiaMinEs, localEs));

		if (!lDatos.get(0).getIdRequerimiento().equals(Constants.MAGIC_NUMBER_MENOS_1_L)) {
			modelMap.put("ES_BORRADOR", Constants.NO);
		} else {
			modelMap.put("ES_BORRADOR", Constants.SI);
		}

		modelMap.put("lDatos", lDatos);
	}

	@Override
	public void getmodelMapDocumentoInterpretacion(Locale locale, ModelMap modelMap, List<Tareas> lDatos) {
		Locale localEu = new Locale("eu");
		Locale localEs = new Locale("es");

		modelMap.put("fileName", this.appMessageSource.getMessage(LABEL_PRESUPUESTO, null, locale));
		modelMap.put("isInline", false);
		modelMap.put("REPORT_LOCALE", locale);
		modelMap.put("STATICS", this.appConfiguration.getProperty("datos.path"));
		modelMap.put("RUTAWAR", this.appConfiguration.getProperty("war.path"));

		modelMap.put("TITULO_ES", " / " + this.appMessageSource.getMessage(LABEL_PRESUPUESTO, null, localEs));
		modelMap.put("TITULO_EU", this.appMessageSource.getMessage(LABEL_PRESUPUESTO, null, localEu));

		modelMap.put("FECHA_INI_ES", this.appMessageSource.getMessage("label.fechaHoraInicio", null, localEs));
		modelMap.put("FECHA_INI_EU", this.appMessageSource.getMessage("label.fechaHoraInicio", null, localEu));
		modelMap.put("FECHA_FIN_ES", this.appMessageSource.getMessage("label.fechaHoraFin", null, localEs));
		modelMap.put("FECHA_FIN_EU", this.appMessageSource.getMessage("label.fechaHoraFin", null, localEu));
		modelMap.put("NUM_INTERPRETES_ES", this.appMessageSource.getMessage("label.numInterpretes", null, localEs));
		modelMap.put("NUM_INTERPRETES_EU", this.appMessageSource.getMessage("label.numInterpretes", null, localEu));
		modelMap.put("TOTAL_ES", " / " + this.appMessageSource.getMessage(LABEL_TOTAL, null, localEs));
		modelMap.put("TOTAL_EU", this.appMessageSource.getMessage(LABEL_TOTAL, null, localEu));
		modelMap.put("IVA_INC_ES", " / " + this.appMessageSource.getMessage(LABEL_IVA_INCLUIDO, null, localEs));
		modelMap.put("IVA_INC_EU", this.appMessageSource.getMessage(LABEL_IVA_INCLUIDO, null, localEu));

		modelMap.put("INTRO_EU", this.appMessageSource.getMessage("label.docPptoInterpIntro", null, localEu));
		modelMap.put("INTRO_ES", this.appMessageSource.getMessage("label.docPptoInterpIntro", null, localEs));
		modelMap.put("INTRO2_EU", this.appMessageSource.getMessage("label.docPptoInterpIntro2", null, localEu));
		modelMap.put("INTRO2_ES", this.appMessageSource.getMessage("label.docPptoInterpIntro2", null, localEs));
		modelMap.put("CORDIALMENTE_EU", this.appMessageSource.getMessage("label.cordialmente", null, localEu));
		modelMap.put("TARIFA_ES", this.appMessageSource.getMessage(LABEL_TARIFA, null, localEs));

		String[] argsIntro2 = { lDatos.get(0).getGestionInterpretacion().getUrlOrdenPrecios() };
		modelMap.put("PIEDOC_EU", this.appMessageSource.getMessage(LABEL_DOCPPTOINTERPPIE, argsIntro2, localEu));
		modelMap.put("PIEDOC_ES", this.appMessageSource.getMessage(LABEL_DOCPPTOINTERPPIE, argsIntro2, localEs));

		modelMap.put("NOMBREEMP_ES", this.appMessageSource.getMessage("label.docPptoInterpNombreEmp", null, localEs));
		modelMap.put("PUESTOEMP_EU", this.appMessageSource.getMessage("label.docPptoInterpPuestoEmp", null, localEu));
		modelMap.put("LOCALIZEMP_EU", this.appMessageSource.getMessage("label.docPptoInterpLocalizEmp", null, localEu));

		if (!lDatos.get(0).getIdRequerimiento().equals(Constants.MAGIC_NUMBER_MENOS_1_L)) {
			modelMap.put("ES_BORRADOR", Constants.NO);
		} else {
			modelMap.put("ES_BORRADOR", Constants.SI);
		}

		modelMap.put("lDatos", lDatos);
	}

	/**
	 * @param tareas Tareas
	 */
	private void registrarAccionEjecucionTarea(Tareas tareas) {
		registrarAccionTarea(tareas, LABEL_EJECUCION_TAREA, true);
	}

	/**
	 * @param tareaAux
	 */
	private void registrarAccionTarea(Tareas tareas, String msgLabel, boolean getDescFromBD) {
		RegistroAcciones reg = new RegistroAcciones();
		reg.setIdPuntoMenu(PuntosMenuEnum.PLANIFICACION_EXPEDIENTES.getValue());
		reg.setIdAccion(Constants.ACCION_MODIFICACION);

		Locale locale = new Locale(Constants.LANG_EUSKERA);
		StringBuilder observ = new StringBuilder();
		observ.append(this.msg.getMessage(msgLabel, null, locale)).append(" \n");
		Long anyo = tareas.getAnyo();
		Integer numExpediente = tareas.getNumExp();

		observ.append(ID).append(Constants.IGUAL).append(tareas.getIdTarea());
		if (TareasServiceUtils.isNotNullTipoTarea(tareas)) {
			observ.append(Constants.COMA_ESPACIO);
			observ.append(this.msg.getMessage(LABEL_TIPO_DE_TAREA, null, locale)).append(Constants.IGUAL);
			String descTipoTarea;
			if (getDescFromBD) {
				TareasServiceUtils tareasServiceUtils = new TareasServiceUtils();
				descTipoTarea = tareasServiceUtils.obtenerDescripcionTipoTareaBD(locale, tareas);
			} else {
				descTipoTarea = TareasServiceUtils.obtenerDescripcionTipoTarea(locale, tareas);
			}

			observ.append(descTipoTarea);
		}
		observ.append(" \n");

		reg.setObserv(observ.toString());
		reg.setAnyo(anyo);
		reg.setNumExp(numExpediente);
		reg.setIdEstadoBitacora(this.expedienteDao.findEstadoBitacoraExp(anyo, numExpediente));

		this.registroAccionesDao.add(reg);
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public Long calcularFechaIni(Expediente expediente, String orden, BigDecimal idTarea) {
		return this.tareasDao.calcularFechaIni(expediente, orden, idTarea);
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public Long calcularFechaFinTarea(Tareas tarea) {
		return this.tareasDao.calcularFechaFinTarea(tarea);
	}

	@Override
	public Tareas findEjecTareasPrevInterpretacion(Tareas tarea) {
		return this.tareasGestionInterpretacionDao.findEjecTareasPrevInterpretacion(tarea);
	}

	@Override
	public Integer actualizarTareasPrevInterpretacion(Tareas tarea) {
		return this.tareasGestionInterpretacionDao.actualizarTareasPrevInterpretacion(tarea);
	}

	@Override
	public OrigenTareaNoConformidad obtenerOrigenNoConformidad(BigDecimal idTarea) {
		return this.tareasDao.obtenerOrigenNoConformidad(idTarea);
	}

	@Override
	public int generarNoConformidadRevInterna(Tareas tarea, String documentosSelect, BigDecimal idTareaRel) {
		int rst = 0;
		TareasUtils tareasUtils = new TareasUtils();
		asignarDatosGeneralesNoConformidadRevInterna(tarea);

		Tareas bean = new Tareas();
		bean.setAnyo(tarea.getAnyo());
		bean.setNumExp(tarea.getNumExp());
		bean.setIdTareaRel(idTareaRel);

		// Comprobar que no exista una tarea de no conformidad con el traductor
		// sin ejecutar para los documentos seleccionados
		if (tareasUtils.isNotTareaNoConfRevInternaSinEjec(bean, documentosSelect)) {
			// Se obtiene el traductor asignado a partir de la tarea original: Tarea de
			// traduccion en estado ejecutada
			PersonalIZO personaAsignada = new PersonalIZO();
			bean = new Tareas();
			bean.setIdTarea(idTareaRel);
			personaAsignada = this.tareasDao.findPersonaAsignadaTareaTradEjec(bean);

			if (personaAsignada.getDni() != null) {
				tarea.setPersonaAsignada(personaAsignada);
				tarea.setIdTareaRel(idTareaRel);

				ejecutarAccionesNoConformidad(tarea, documentosSelect, null);
			}
		} else {
			rst = 1;
		}

		return rst;
	}

	/**
	 *
	 * @param tarea Tareas
	 */
	private void asignarDatosGeneralesNoConformidadRevInterna(Tareas tarea) {
		TiposTarea tipoTarea = new TiposTarea();
		tipoTarea.setId015((long) TipoTareaGestionAsociadaEnum.NO_CONFORMIDAD_TRADUCTOR.getValue());
		tarea.setTipoTarea(tipoTarea);

		PersonalIZO personaAsignador = new PersonalIZO();
		Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication().getCredentials();
		personaAsignador.setDni(credentials.getNif());
		tarea.setPersonaAsignador(personaAsignador);

		tarea.setRecursoAsignacion(TipoRecursoEnum.INTERNO.getValue());
		TareasUtils.asignarDatosAceptacion(tarea);
		tarea.setFechaAsignacion(new Date());
	}

	@Override
	public Integer comprobarFechaFinTareasNoSuperaFechaFinExp(Expediente expediente) {
		return this.tareasDao.comprobarFechaFinTareasNoSuperaFechaFinExp(expediente);
	}

	@Override
	public Tareas obtenerEstadoEjecucionTarea(Tareas tarea) {
		Tareas est = null;
		// comprobamos que no tenga tareas previas pendientes de ejecutar
		long pendientes = this.tareasDao.comprobarTareasPendientes(tarea);
		if (pendientes == 0) {
			// no tiene tareas previas pendientes de ejecutar
			est = this.tareasDao.comprobarEstadoEjecucionTarea(tarea);
		}

		return est;
	}

	@Override
	public Tareas getAsignadoTareaRevisionAnterior(Tareas tarea, List<Integer> tiposTarea) {
		return this.tareasDao.getAsignadoTareaRevisionAnterior(tarea, tiposTarea);
	}


}
