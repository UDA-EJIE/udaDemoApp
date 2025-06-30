package com.ejie.aa79b.control;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.mail.MailService;
import com.ejie.aa79b.model.AusenciasCalendarioPersonal;
import com.ejie.aa79b.model.DatosContacto;
import com.ejie.aa79b.model.DocumentoTarea;
import com.ejie.aa79b.model.DocumentosExpediente;
import com.ejie.aa79b.model.EntradaDocumentosTarea;
import com.ejie.aa79b.model.ExpTareasResumen;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.FicheroDocExp;
import com.ejie.aa79b.model.Gantt;
import com.ejie.aa79b.model.ParametrosEmail;
import com.ejie.aa79b.model.PersonalIZO;
import com.ejie.aa79b.model.RegistroAcciones;
import com.ejie.aa79b.model.Tareas;
import com.ejie.aa79b.model.TareasTrabajo;
import com.ejie.aa79b.model.TiposTarea;
import com.ejie.aa79b.model.enums.AccionesEnum;
import com.ejie.aa79b.model.enums.EstadoAceptacionTareaEnum;
import com.ejie.aa79b.model.enums.EstadoEjecucionTareaEnum;
import com.ejie.aa79b.model.enums.PuntosMenuEnum;
import com.ejie.aa79b.model.enums.TipoAvisoEnum;
import com.ejie.aa79b.model.enums.TipoExpedienteEnum;
import com.ejie.aa79b.model.enums.TipoJornadaEnum;
import com.ejie.aa79b.model.enums.TipoTareaGestionAsociadaEnum;
import com.ejie.aa79b.security.Usuario;
import com.ejie.aa79b.service.AusenciasCalendarioPersonalService;
import com.ejie.aa79b.service.CargaTrabajoService;
import com.ejie.aa79b.service.DatosContactoService;
import com.ejie.aa79b.service.ExpedienteService;
import com.ejie.aa79b.service.PersonalIZOService;
import com.ejie.aa79b.service.RegistroAccionesService;
import com.ejie.aa79b.service.TareasGeneralService;
import com.ejie.aa79b.service.TareasService;
import com.ejie.aa79b.service.TareasTrabajoService;
import com.ejie.aa79b.service.TiposTareaService;
import com.ejie.aa79b.utils.DateUtils;
import com.ejie.aa79b.utils.TareasServiceUtils;
import com.ejie.aa79b.utils.Utils;
import com.ejie.aa79b.webservices.PIDService;
import com.ejie.x38.control.bind.annotation.RequestJsonBody;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;

@Controller
@RequestMapping(value = "/tramitacionexpedientes/planificacionCarga/cargatrabajo")
public class CargaTrabajoController {

	@Autowired()
	private ReloadableResourceBundleMessageSource appMessageSource;
	@Autowired()
	private PIDService pidService;
	@Autowired()
	private Properties appConfiguration;
	@Autowired()
	private CargaTrabajoService cargaTrabajoService;
	@Autowired()
	private RegistroAccionesService registroAccionesService;
	@Autowired()
	private ExpedienteService expedienteService;
	@Autowired()
	private TareasService tareasService;
	@Autowired()
	private TiposTareaService tiposTareaService;
	@Autowired()
	private PersonalIZOService personalIZOService;
	@Autowired()
	private TareasGeneralService tareasGeneralService;
	@Autowired()
	private DatosContactoService datosContactoService;
	@Autowired()
	private MailService mailService;
	@Autowired()
	private AusenciasCalendarioPersonalService ausenciasCalendarioPersonalService;
	@Autowired()
	private TareasTrabajoService tareasTrabajoService;
	private static final Logger LOGGER = LoggerFactory.getLogger(CargaTrabajoController.class);

	/**
	 *
	 * @return String
	 */
	@RequestMapping(value = "/maint", method = RequestMethod.GET)
	public String irCargaTrabajo() {
		CargaTrabajoController.LOGGER.info("[GET - View] : irCargaTrabajo");
		return "cargaTrabajo";
	}

	/**
	 *
	 * @return String
	 */
	@RequestMapping(value = "/expediente/maint", method = RequestMethod.GET)
	public ModelAndView irCargaTrabajoExpediente() {
		CargaTrabajoController.LOGGER.info("[GET - View] : irCargaTrabajoExpediente");
		return new ModelAndView("cargaTrabajoExpediente");
	}

	/**
	 *
	 * @return String
	 */
	@RequestMapping(value = "/recurso/maint", method = RequestMethod.GET)
	public ModelAndView irCargaTrabajoRecurso() {
		CargaTrabajoController.LOGGER.info("[GET - View] : irCargaTrabajoRecurso");
		return new ModelAndView("cargaTrabajoRecurso");
	}

	/**
	 *
	 * @return Integer
	 */
	@RequestMapping(value = "/tareas/getEstadoTareasDependientes/{anyo}/{numExp}/{idTarea}", method = RequestMethod.GET)
	public Integer getListado(@PathVariable Long anyo, @PathVariable Integer numExp, @PathVariable BigDecimal idTarea) {
		CargaTrabajoController.LOGGER.info("[POST - filter] : Obtener getEstadoTareasDependientes");
		return this.cargaTrabajoService.procComprobarEstadoTareasDependientes(idTarea, anyo, numExp);
	}

	/**
	 *
	 * @return String
	 */
	@RequestMapping(value = "/tareasDetalle/maint", method = RequestMethod.GET)
	public String irCargaTrabajoDetalleTarea() {
		CargaTrabajoController.LOGGER.info("[GET - View] : irCargaTrabajoDetalleTarea");
		return "cargaTrabajoDetalleTarea";
	}

	/**
	 *
	 * @return String
	 */
	@RequestMapping(value = "/tareasTrabajoDetalle/maint/{idtarea}", method = RequestMethod.GET)
	public String irCargaTrabajoDetalleTareaTrabajo(@PathVariable BigDecimal idtarea, Model model) {
		CargaTrabajoController.LOGGER.info("[GET - View] : irCargaTrabajoDetalleTareaTrabajo");
		TareasTrabajo tareaTrabajo = new TareasTrabajo();
		tareaTrabajo.setIdTarea(idtarea);
		tareaTrabajo = this.tareasTrabajoService.getDatosTarea(tareaTrabajo);
		model.addAttribute("tareaTrabajo", tareaTrabajo);
		return "cargaTrabajoDetalleTareaTrabajo";
	}

	/**
	 *
	 * @return String
	 */
	@RequestMapping(value = "/reasignarTareasCargaTrabajo/maint", method = RequestMethod.GET)
	public String irAReasignarTareasCargaTrabajo(Model model) {
		CargaTrabajoController.LOGGER.info("[GET - View] : irCargaTrabajoDetalleTarea");
		model.addAttribute("tareaTrabajo", 0);
		return "reasignartareascargatrabajo";
	}

	/**
	 *
	 * @return String
	 */
	@RequestMapping(value = "/reasignarTareasTrabajoCargaTrabajo/maint", method = RequestMethod.GET)
	public String irAReasignarTareasTrabajoCargaTrabajo(Model model) {
		CargaTrabajoController.LOGGER.info("[GET - View] : irAReasignarTareasTrabajoCargaTrabajo");
		model.addAttribute("tareaTrabajo", 1);
		return "reasignartareascargatrabajo";
	}

	/**
	 *
	 * @return ExpTareasResumen
	 */
	@RequestMapping(value = "/tareasDetalle/findTarea/{idTarea}", method = RequestMethod.GET)
	public @ResponseBody ExpTareasResumen findTarea(@PathVariable BigDecimal idTarea, Locale locale) {
		CargaTrabajoController.LOGGER.info("[POST - filter] : Obtener findTarea");
		String idioma = locale.getLanguage();
		return this.cargaTrabajoService.findTarea(idTarea, idioma);
	}

	/**
	 *
	 * @return ExpTareasResumen
	 */
	@RequestMapping(value = "/tareasDetalle/findTareaTrabajo/{idTarea}", method = RequestMethod.GET)
	public @ResponseBody TareasTrabajo findTareaTrabajo(@PathVariable BigDecimal idTarea, Locale locale) {
		CargaTrabajoController.LOGGER.info("[POST - filter] : Obtener findTarea");
		TareasTrabajo tareaTrabajo = new TareasTrabajo();
		tareaTrabajo.setIdTarea(idTarea);
		return this.tareasTrabajoService.getDatosTarea(tareaTrabajo);
	}

	@RequestMapping(value = "/tareasDetalle/comprobarEstadoTarea/{idTareasListString}", method = RequestMethod.GET)
	public @ResponseBody Boolean comprobarEstadoTarea(@PathVariable String idTareasListString, Locale locale) {
		CargaTrabajoController.LOGGER.info("[POST - filter] : Obtener comprobarEstadoTarea");
		Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication().getCredentials();
		Boolean result = true;
		String[] idTareaList = idTareasListString.split(",");

		for (int i = 0; i < idTareaList.length; i++) {
			BigDecimal idTarea = new BigDecimal(idTareaList[i]);
			Tareas tareasFilter = new Tareas();
			tareasFilter.setIdTarea(idTarea);
			Tareas tareas = this.tareasService.findConfTarea(tareasFilter);
			if (tareas.getEstadoAsignado() != EstadoAceptacionTareaEnum.PENDIENTE_ACEPTACION.getValue()
					|| !tareas.getPersonaAsignada().getDni().equals(credentials.getNif())) {
				result = false;
				break;
			}
		}

		return result;

	}

	@RequestMapping(value = "/tareasDetalle/comprobarEstadoEjecucionTarea/{idTareasListString}", method = RequestMethod.GET)
	public @ResponseBody Boolean comprobarEstadoEjecucionTarea(@PathVariable String idTareasListString, Locale locale) {
		CargaTrabajoController.LOGGER.info("[POST - filter] : Obtener comprobarEstadoTarea");
		Boolean resu = true;
		String[] idTareaList = idTareasListString.split(",");

		for (int i = 0; i < idTareaList.length; i++) {
			BigDecimal idTarea = new BigDecimal(idTareaList[i]);
			Tareas tareasFilter = new Tareas();
			tareasFilter.setIdTarea(idTarea);
			Tareas tareas = this.tareasService.findConfTarea(tareasFilter);
			if(!comprobarEstadoEjecucion(EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue(), tareas)
					&& !comprobarEstadoEjecucion(EstadoEjecucionTareaEnum.RETRASADA.getValue(), tareas)){
				resu = false;
				break;
			}
		}

		return resu;

	}

	@RequestMapping(value = "/tareasDetalle/comprobarEstadoEjecucionTareaTrabajo/{idTareasListString}", method = RequestMethod.GET)
	public @ResponseBody Boolean comprobarEstadoEjecucionTareaTrabajo(@PathVariable String idTareasListString, Locale locale) {
		CargaTrabajoController.LOGGER.info("[POST - filter] : Obtener comprobarEstadoTarea");
		Boolean resu = true;
		String[] idTareaList = idTareasListString.split(",");

		for (int i = 0; i < idTareaList.length; i++) {
			BigDecimal idTarea = new BigDecimal(idTareaList[i]);
			Tareas tareasFilter = new Tareas();
			tareasFilter.setIdTarea(idTarea);
			Tareas tareas = this.tareasTrabajoService.findConfTareaTrabajo(tareasFilter);
			if(!comprobarEstadoEjecucion(EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue(), tareas)
					&& !comprobarEstadoEjecucion(EstadoEjecucionTareaEnum.RETRASADA.getValue(), tareas)){
				resu = false;
				break;
			}
		}

		return resu;

	}

	/**
	 * funcion que devuelve si el estado pasado como parametro coincide con el estado de la tarea
	 * @param estadoEjecucionTarea Integer
	 * @param tareas Tareas
	 * @return Boolean
	 */
	private Boolean comprobarEstadoEjecucion(Integer estadoEjecucionTarea, Tareas tareas) {
		Boolean ret = true;
		if(estadoEjecucionTarea == EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue()
				&& (tareas.getEstadoEjecucion() != estadoEjecucionTarea)){
			//estado ejecucion - pendiente de ejecucion
				ret = false;
		}else if(estadoEjecucionTarea == EstadoEjecucionTareaEnum.RETRASADA.getValue()
				&& (tareas.getEstadoEjecucion() != estadoEjecucionTarea)){
			//estado ejecucion - retrasada
			ret = false;
		}else if(estadoEjecucionTarea == EstadoEjecucionTareaEnum.EJECUTADA.getValue()
				&& tareas.getEstadoEjecucion() != estadoEjecucionTarea){
			//estado ejecucion -  ejecutada
			ret = false;
		}
		return ret;
	}


	@RequestMapping(value = "/tareasDetalle/aceptarAsignacion/{idTareasListString}/{estadoAceptacion}", method = RequestMethod.GET)
	public @ResponseBody ExpTareasResumen aceptarAsignacion(@PathVariable String idTareasListString,
			@PathVariable Integer estadoAceptacion, Locale locale) {
		CargaTrabajoController.LOGGER.info("[POST - filter] : Obtener aceptarAsignacion");
		Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication().getCredentials();
		ExpTareasResumen expTareasResumen = new ExpTareasResumen();
		String[] idTareaList = idTareasListString.split(",");
		for (int i = 0; i < idTareaList.length; i++) {
			BigDecimal idTarea = new BigDecimal(idTareaList[i]);
			Tareas tareas = new Tareas();
			tareas.setIdTarea(idTarea);
			tareas.setEstadoAsignado(estadoAceptacion);
			expTareasResumen.setTarea(tareas);
			tareas = this.tareasService.findConfTarea(tareas);
			expTareasResumen.setAnyo(tareas.getAnyo());
			expTareasResumen.setNumExp(tareas.getNumExp());

			Expediente exp = new Expediente();
			exp.setAnyo(tareas.getAnyo());
			exp.setNumExp(tareas.getNumExp());
			exp = this.expedienteService.find(exp);

			RegistroAcciones registroAcciones = new RegistroAcciones();
			registroAcciones.setIdPuntoMenu(PuntosMenuEnum.CARGA_TRABAJO.getValue());
			registroAcciones.setIdAccion(AccionesEnum.MODIFICACION.getValue());
			registroAcciones.setAnyo(tareas.getAnyo());
			registroAcciones.setNumExp(tareas.getNumExp());
			StringBuilder observ = new StringBuilder();
			observ.append(this.appMessageSource.getMessage("label.regAcciones.aceptarAsig", null, locale));
			observ.append(": ");
			observ.append("id = ").append(idTarea);
			observ.append(" , " + this.appMessageSource.getMessage("comun.tipoDeTarea", null, locale)).append(Constants.IGUAL);
			if(Constants.LANG_EUSKERA.equals(locale.getLanguage())){
				observ.append(tareas.getTipoTarea().getDescEu015());
			}else if(Constants.LANG_CASTELLANO.equals(locale.getLanguage())){
				observ.append(tareas.getTipoTarea().getDescEs015());
			}
			registroAcciones.setObserv(observ.toString());
			registroAcciones.setIdEstadoBitacora(exp.getEstadoBitacora());
			this.registroAccionesService.add(registroAcciones);

			if (TipoTareaGestionAsociadaEnum.INTERPRETAR.getValue() == tareas.getTipoTarea().getId015()) {
				AusenciasCalendarioPersonal ausenciasCalendarioPersonal = new AusenciasCalendarioPersonal();
				ausenciasCalendarioPersonal.setDni(credentials.getNif());
				ausenciasCalendarioPersonal.setAnyo(tareas.getAnyo());
				ausenciasCalendarioPersonal.setFechaDesde(tareas.getFechaIni());
				ausenciasCalendarioPersonal.setFechaHasta(tareas.getFechaFin());
				ausenciasCalendarioPersonal.setTipoJornada(TipoJornadaEnum.INTERPRETACION.getValue());
				ausenciasCalendarioPersonal.setObservDia(TipoJornadaEnum.INTERPRETACION.getValue());
				ausenciasCalendarioPersonal.setIdTarea(idTarea);
				this.ausenciasCalendarioPersonalService.add(ausenciasCalendarioPersonal);
			}
			this.cargaTrabajoService.updateEstadoAceptacion(expTareasResumen);
		}
		return expTareasResumen;
	}

	@RequestMapping(value = "/tareasDetalle/comprobarConflictoFechas/{idTareasListString}", method = RequestMethod.POST)
	public @ResponseBody Long comprobarConflictoFechas(@PathVariable String idTareasListString, Locale locale) {
		CargaTrabajoController.LOGGER.info("[POST - filter] : comprobarConflictoFechas");
		String[] idTareaList = idTareasListString.split(",");
		return this.cargaTrabajoService.comprobarConflictoFechas(idTareaList);
	}

	@RequestMapping(value = "/tareasDetalle/rechazarAsignacion/{idTareasListString}/{estadoAceptacion}/{motivoRechazo}", method = RequestMethod.GET)
	public @ResponseBody ExpTareasResumen rechazarAsignacion(@PathVariable String idTareasListString,
			@PathVariable Integer estadoAceptacion, @PathVariable String motivoRechazo, Locale locale) {
		CargaTrabajoController.LOGGER.info("[POST - filter] : Obtener rechazarAsignacion");
		ExpTareasResumen expTareasResumen = new ExpTareasResumen();
		String[] idTareaList = idTareasListString.split(",");
		for (int i = 0; i < idTareaList.length; i++) {
			BigDecimal idTarea = new BigDecimal(idTareaList[i]);
			Tareas tareas = new Tareas();
			tareas.setIdTarea(idTarea);
			tareas.setEstadoAsignado(estadoAceptacion);
			expTareasResumen.setTarea(tareas);
			tareas = this.tareasService.findConfTarea(tareas);
			expTareasResumen.setAnyo(tareas.getAnyo());
			expTareasResumen.setNumExp(tareas.getNumExp());
			this.cargaTrabajoService.insertObsrvRechazo(idTarea, motivoRechazo);

			Expediente exp = new Expediente();
			exp.setAnyo(tareas.getAnyo());
			exp.setNumExp(tareas.getNumExp());
			exp = this.expedienteService.find(exp);

			RegistroAcciones registroAcciones = new RegistroAcciones();
			registroAcciones.setIdPuntoMenu(PuntosMenuEnum.CARGA_TRABAJO.getValue());
			registroAcciones.setIdAccion(AccionesEnum.MODIFICACION.getValue());
			registroAcciones.setAnyo(tareas.getAnyo());
			registroAcciones.setNumExp(tareas.getNumExp());
			StringBuilder observ = new StringBuilder();
			observ.append(this.appMessageSource.getMessage("label.regAcciones.rechazarAsig", null, locale));
			observ.append(": ");
			observ.append("id = ").append(idTarea);
			observ.append(" , " + this.appMessageSource.getMessage("comun.tipoDeTarea", null, locale)).append(Constants.IGUAL);
			if(Constants.LANG_EUSKERA.equals(locale.getLanguage())){
				observ.append(tareas.getTipoTarea().getDescEu015());
			}else if(Constants.LANG_CASTELLANO.equals(locale.getLanguage())){
				observ.append(tareas.getTipoTarea().getDescEs015());
			}
			observ.append(" , " + this.appMessageSource.getMessage("label.observaciones", null, locale) + "= ");
			observ.append(motivoRechazo);
			registroAcciones.setObserv(observ.toString());
			registroAcciones.setIdEstadoBitacora(exp.getEstadoBitacora());
			this.registroAccionesService.add(registroAcciones);

			this.cargaTrabajoService.updateEstadoAceptacion(expTareasResumen);

			DatosContacto datosContacto = new DatosContacto();
			datosContacto.setDni031(tareas.getPersonaAsignador().getDni());
			datosContacto = this.datosContactoService.findDatosContacto(datosContacto);

			final List<String> listaDestinatarios = new ArrayList<String>();
			if (this.appConfiguration.getProperty(Constants.APP_ENTORNO) != null) {
				if (Constants.LOCAL.equals(this.appConfiguration.getProperty(Constants.APP_ENTORNO))) {
					listaDestinatarios.add(Constants.DESTINATARIO_EMAIL_LOCAL);
				} else if (datosContacto != null) {
						listaDestinatarios.add(datosContacto.getEmail031());
				}

				if (TareasServiceUtils.isNotEmptyLstDestinatarios(listaDestinatarios)) {
					ParametrosEmail parametrosEmail = new ParametrosEmail();

					Locale localeEu = new Locale(Constants.LANG_EUSKERA);
					Locale localeEs = new Locale(Constants.LANG_CASTELLANO);
					Map<String, String> infoEu = new LinkedHashMap<String, String>();
					Map<String, String> infoEs = new LinkedHashMap<String, String>();

					StringBuilder info = new StringBuilder();
					info.append(tareas.getIdTarea() + Constants.GUION_CON_ESPACIOS
							+ TareasServiceUtils.obtenerDescripcionTipoTarea(localeEu, tareas)).append(" (");
					info.append(this.appMessageSource.getMessage("label.numExpMin", null, localeEu)).append(" ")
					.append(tareas.getAnyoNumExpConcatenado()).append(")");
					infoEu.put(this.appMessageSource.getMessage("label.tarea", null, localeEu), info.toString());

					info = new StringBuilder();
					info.append(tareas.getIdTarea() + Constants.GUION_CON_ESPACIOS
							+ TareasServiceUtils.obtenerDescripcionTipoTarea(localeEs, tareas)).append(" (");
					info.append(this.appMessageSource.getMessage("label.numExpMin", null, localeEs)).append(" ")
					.append(tareas.getAnyoNumExpConcatenado()).append(")");
					infoEs.put(this.appMessageSource.getMessage("label.tarea", null, localeEs), info.toString());

					if (!motivoRechazo.isEmpty()) {
						infoEu.put(this.appMessageSource.getMessage("label.observaciones", null, localeEu), motivoRechazo);
						infoEs.put(this.appMessageSource.getMessage("label.observaciones", null, localeEs), motivoRechazo);
					}
					parametrosEmail.setInfoEu(infoEu);
					parametrosEmail.setInfoEs(infoEs);

					parametrosEmail.setAnyoNumExpediente(Utils.getNumExpedienteParameter(tareas));


					try {
						this.mailService.sendMailWithParameters(TipoAvisoEnum.RECHAZO_ASIGNACION_TAREAS.getValue(),
								listaDestinatarios, parametrosEmail);
					} catch (Exception e) {
						CargaTrabajoController.LOGGER.info("Error en el envío de email", e);
					}
				} else {
					CargaTrabajoController.LOGGER.info("No hay destinatarios", listaDestinatarios);
				}
			}
		}
		return expTareasResumen;
	}

	@RequestMapping(value = "/tareasDetalle/descargaDocTareasZip", method = RequestMethod.POST)
	public @ResponseBody DocumentosExpediente getDescargaDocTareasZip(@RequestJsonBody EntradaDocumentosTarea bean,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto, Locale locale) {
		DocumentosExpediente documentosExpediente = new DocumentosExpediente();
		documentosExpediente.setAnyo(bean.getAnyo());
		documentosExpediente.setNumExp(bean.getNumExp());

		List<FicheroDocExp> listaFicheros;

		List<DocumentoTarea> documentos = this.tareasGeneralService.findDocsDetalleTarea(bean.getIdTarea(),
				locale.getLanguage());
		listaFicheros = this.parsearDocsTarea(documentos);

		documentosExpediente.setFicheros(listaFicheros);

		CargaTrabajoController.LOGGER
				.info("CargaTrabajoDetalle descargaDocTareasZip END\nreturn" + documentosExpediente);

		return documentosExpediente;
	}

	@RequestMapping(value = "/tareasDetalle/descargaDocPIF", method = RequestMethod.GET)
	public void descargarDocPIF(@RequestParam(value = "laRuta", required = false) String laRuta,
			@RequestParam(value = "nombreFich", required = false) String nombreFich, HttpServletResponse response) {
		CargaTrabajoController.LOGGER.info("[GET - View]: descargarDocPIF");
		String nombreFichero;
		if (StringUtils.isNotEmpty(nombreFich)) {
			nombreFichero = nombreFich;
		} else {
			nombreFichero = laRuta.substring(laRuta.lastIndexOf("/") + 1, laRuta.length());
		}
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + nombreFichero + "\"");
		response.setHeader("Pragma", "cache");
		response.setHeader("Expires", "0");
		response.setHeader("Cache-Control", "private");
		try {
			byte[] bytes = this.pidService.getDocument(laRuta);
			ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length);
			baos.write(bytes, 0, bytes.length);
			response.setContentLength(bytes.length);
			OutputStream os;
			os = response.getOutputStream();
			baos.writeTo(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			CargaTrabajoController.LOGGER.error("ERROR descargando documento del PIF: ", e);
		}
	}

	@RequestMapping(value = "/tareasDependientes/filter", method = RequestMethod.POST)
	public @ResponseBody JQGridResponseDto<ExpTareasResumen> getFilterTareaDependiente(
			@RequestJsonBody(param = "filter") ExpTareasResumen expTareasResumenFilter,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto, Locale locale) {
		CargaTrabajoController.LOGGER.info("[POST - filter] : Obtener Tarea dependiente");
		String idioma = locale.getLanguage();
		ExpTareasResumen expTarea = this.cargaTrabajoService.findTarea(expTareasResumenFilter.getTarea().getIdTarea(),
				idioma);
		return this.cargaTrabajoService.filtroTareasDependiente(expTarea, jqGridRequestDto, false);
	}

	@RequestMapping(value = "/tareasDependientesTareaTrabajo/filter", method = RequestMethod.POST)
	public @ResponseBody JQGridResponseDto<TareasTrabajo> getFilterTareaDependienteTareaTrabajo(
			@RequestJsonBody(param = "filter") TareasTrabajo tareaTrabajoFilter,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto, Locale locale) {
		CargaTrabajoController.LOGGER.info("[POST - filter] : Obtener Tarea dependiente");
		return this.tareasTrabajoService.getTareasDependientes(tareaTrabajoFilter, jqGridRequestDto);
	}

	@RequestMapping(value = "/tareasTradRev/filter", method = RequestMethod.POST)
	public @ResponseBody JQGridResponseDto<ExpTareasResumen> getTareasTradRev(
			@RequestJsonBody(param = "filter") ExpTareasResumen expTareasResumenFilter,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto) {
		CargaTrabajoController.LOGGER.info("[POST - filter] : Obtener tareas traduccion y revision");
		if (!expTareasResumenFilter.getEsAsignador().booleanValue()) {
			Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication().getCredentials();
			expTareasResumenFilter.setFiltroDatos(new String[] { credentials.getNif() });
		}
		expTareasResumenFilter.setIdTipoExpediente(TipoExpedienteEnum.TRADUCCION.getValue());
		return this.cargaTrabajoService.filtroExpTareas(expTareasResumenFilter, jqGridRequestDto, false);
	}

	@RequestMapping(value = "/tareasInterpretacion/filter", method = RequestMethod.POST)
	public @ResponseBody JQGridResponseDto<ExpTareasResumen> getTareasInterpretacion(
			@RequestJsonBody(param = "filter") ExpTareasResumen expTareasResumenFilter,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto) {
		CargaTrabajoController.LOGGER.info("[POST - filter] : Obtener tareas interpretacion");
		if (!expTareasResumenFilter.getEsAsignador().booleanValue()) {
			Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication().getCredentials();
			expTareasResumenFilter.setFiltroDatos(new String[] { credentials.getNif() });
		}
		expTareasResumenFilter.setIdTipoExpediente(TipoExpedienteEnum.INTERPRETACION.getValue());
		return this.cargaTrabajoService.filtroExpTareas(expTareasResumenFilter, jqGridRequestDto, false);
	}

	@RequestMapping(value = "/tareasTrabajo/filter", method = RequestMethod.POST)
	public @ResponseBody JQGridResponseDto<ExpTareasResumen> getTareasTrabajo(
			@RequestJsonBody(param = "filter") ExpTareasResumen expTareasResumenFilter,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto) {
		CargaTrabajoController.LOGGER.info("[POST - filter] : Obtener tareas trabajo");
		if (!expTareasResumenFilter.getEsAsignador()) {
			Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication().getCredentials();
			expTareasResumenFilter.setFiltroDatos(new String[] { credentials.getNif() });
		}
		expTareasResumenFilter.setIdTipoExpediente(TipoExpedienteEnum.TRADUCCION.getValue());
		return this.cargaTrabajoService.filtroExpTareasTrabajo(expTareasResumenFilter, jqGridRequestDto, false);
	}

	@RequestMapping(value = "/comprobarTipoTarea/{tipoTareaFilter}", method = RequestMethod.GET)
	public @ResponseBody TiposTarea comprobarTipoTareaInterpretacion(@PathVariable Long tipoTareaFilter, Locale locale) {
		CargaTrabajoController.LOGGER.info("[POST - filter] : Obtener comprobarTipoTareaInterpretacion");

		TiposTarea bean = new TiposTarea();
		bean.setId015(tipoTareaFilter);

		return this.tiposTareaService.find(bean);
	}

	@RequestMapping(value = "/comprobarTareasPendientes", method = RequestMethod.GET)
	public @ResponseBody String comprobarTareasPendientesUsuario(Locale locale) {
		CargaTrabajoController.LOGGER.info("[GET - filter] : Obtener comprobarTareasPendientesUsuario");
		return this.cargaTrabajoService.tieneTareasPendientes();
	}

	@RequestMapping(value = "/comprobarTareasPendientesAsignador", method = RequestMethod.POST)
	public @ResponseBody String comprobarTareasPendientesUsuarioAsignador(@RequestBody ExpTareasResumen expTareasResumen) {
		CargaTrabajoController.LOGGER.info("[GET - filter] : Obtener comprobarTareasPendientesUsuarioAsignador");
		if (expTareasResumen.getFiltroDatos() == null) {
			return Constants.STRING_CERO;
		}else {
			return this.cargaTrabajoService.tieneTareasPendientesAsignador(expTareasResumen);
		}
	}


	/**
	 * Operacion de obtencion de ids de elementos seleccionados de RUP_TABLE.
	 *
	 * @param tableObjects
	 *           Map<String, Object>
	 * @return List<ExpedienteConsulta>
	 */
	@RequestMapping(value = "/tareasInterpretacion/getSelectedIds", method = RequestMethod.POST)
	public @ResponseBody List<ExpTareasResumen> getSelectedIdsInter(
			@RequestJsonBody(param = "filterObject") ExpTareasResumen expTareasResumenFilter,
			@RequestJsonBody(param = "tableData") JQGridRequestDto tableData){
		CargaTrabajoController.LOGGER.info("[POST] : busquedaGeneral carga trabajo - getSelectedIds");
		if (!expTareasResumenFilter.getEsAsignador()) {
			Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication().getCredentials();
			expTareasResumenFilter.setFiltroDatos(new String[] { credentials.getNif() });
		}
		expTareasResumenFilter.setIdTipoExpediente(TipoExpedienteEnum.INTERPRETACION.getValue());
		return this.cargaTrabajoService.filtroExpTareasGetSelectedIds(expTareasResumenFilter,tableData);
	}

	/**
	 * Operacion de obtencion de ids de elementos seleccionados de RUP_TABLE.
	 *
	 * @param tableObjects
	 *           Map<String, Object>
	 * @return List<ExpedienteConsulta>
	 */
	@RequestMapping(value = "/tareasTradRev/getSelectedIds", method = RequestMethod.POST)
	public @ResponseBody List<ExpTareasResumen> getSelectedIds(
			@RequestJsonBody(param = "filterObject") ExpTareasResumen expTareasResumenFilter,
			@RequestJsonBody(param = "tableData") JQGridRequestDto tableData){
		CargaTrabajoController.LOGGER.info("[POST] : busquedaGeneral carga trabajo - getSelectedIds");
		if (!expTareasResumenFilter.getEsAsignador()) {
			Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication().getCredentials();
			expTareasResumenFilter.setFiltroDatos(new String[] { credentials.getNif() });
		}
		expTareasResumenFilter.setIdTipoExpediente(TipoExpedienteEnum.TRADUCCION.getValue());
		return this.cargaTrabajoService.filtroExpTareasGetSelectedIds(expTareasResumenFilter,tableData);
	}

	/**
	 * Operacion de obtencion de ids de elementos seleccionados de RUP_TABLE.
	 *
	 * @param tableObjects
	 *           Map<String, Object>
	 * @return List<ExpedienteConsulta>
	 */
	@RequestMapping(value = "/tareasTrabajo/getSelectedIds", method = RequestMethod.POST)
	public @ResponseBody List<ExpTareasResumen> getTareasTrabajoSelectedIds(
			@RequestJsonBody(param = "filterObject") ExpTareasResumen expTareasResumenFilter,
			@RequestJsonBody(param = "tableData") JQGridRequestDto tableData){
		CargaTrabajoController.LOGGER.info("[POST] : getTareasTrabajoSelectedIds carga trabajo - getSelectedIds");
		if (!expTareasResumenFilter.getEsAsignador()) {
			Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication().getCredentials();
			expTareasResumenFilter.setFiltroDatos(new String[] { credentials.getNif() });
		}
		return this.cargaTrabajoService.filtroTareasTrabajoGetSelectedIds(expTareasResumenFilter,tableData);
	}

	@RequestMapping(value = "/getGanttRecurso", method = RequestMethod.POST)
	public @ResponseBody List<Gantt> getGanttRecurso(@RequestBody ExpTareasResumen expTareasResumenFilter,
			Locale locale) {
		CargaTrabajoController.LOGGER.info("[POST - filter] : getGanttRecurso");
		if (!expTareasResumenFilter.getEsAsignador()) {
			Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication().getCredentials();
			expTareasResumenFilter.setFiltroDatos(new String[] { credentials.getNif() });
		}
		return this.cargaTrabajoService.getExpTareasGantt(expTareasResumenFilter, locale);
	}

	@RequestMapping(value = "/getEsAsignador", method = RequestMethod.GET)
	public @ResponseBody Boolean getEsAsignador() {
		CargaTrabajoController.LOGGER.info("[POST - filter] : getEsAsignador");

		Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication().getCredentials();
		PersonalIZO filterPersonalIZO = new PersonalIZO();
		filterPersonalIZO.setAsignador(Constants.SI);
		List<PersonalIZO> personalIzoList = this.personalIZOService.findAsignadores(filterPersonalIZO, Constants.ALTA);

		Boolean result = false;
		for (PersonalIZO personalIzo : personalIzoList) {
			if (credentials.getNif().equals(personalIzo.getDni())) {
				result = true;
			}
		}

		return result;
	}

	@RequestMapping(value = "/getGanttExpediente/{tareas}", method = RequestMethod.POST)
	public @ResponseBody List<Gantt> getGanttExpediente(@RequestBody ExpTareasResumen expTareasResumenFilter,
			@PathVariable Boolean tareas, Locale locale) {
		CargaTrabajoController.LOGGER.info("[POST - filter] : getGanttRecurso");
		if (!expTareasResumenFilter.getEsAsignador()) {
			Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication().getCredentials();
			expTareasResumenFilter.setFiltroDatos(new String[] { credentials.getNif() });
		}
		return this.cargaTrabajoService.getExpGantt(expTareasResumenFilter, tareas, locale);
	}

	@RequestMapping(value = "/descargarCalendarioICS/{tareasSeleccionados}", method = RequestMethod.GET)
	public void descargarIcs(@PathVariable() String tareasSeleccionados, HttpServletRequest request,
			HttpServletResponse response, Locale locale) {
		List<Tareas> lTarCal = new ArrayList<Tareas>();
		lTarCal = this.cargaTrabajoService.obtenerDatosTareas(tareasSeleccionados);

		ICalendar ical = new ICalendar();

		for (Tareas tarCal : lTarCal) {
			VEvent event = new VEvent();
			event.setSummary(tarCal.getAnyoNumExpConcatenado());
			if (locale.getLanguage().equals(Constants.LANG_CASTELLANO)) {
				event.setDescription(tarCal.getTipoTarea().getDescEs015());
			} else {
				event.setDescription(tarCal.getTipoTarea().getDescEu015());
			}

			event.setDateStart(DateUtils.obtFechaHoraFormateada(tarCal.getFechaIni(), tarCal.getHoraIni()));
			event.setDateEnd(DateUtils.obtFechaHoraFormateada(tarCal.getFechaFin(), tarCal.getHoraFin()));
			ical.addEvent(event);
		}

		response.setContentType("text/calendar");
		response.setHeader("Content-disposition", "attachment;filename=myCalendar.ics");

		response.setHeader("Pragma", "cache");
		response.setHeader("Expires", "0");
		response.setHeader("Cache-Control", "private");

		Cookie cookie = new Cookie("fileDownload", "true");
		cookie.setPath("/");
		cookie.setSecure(true);
		response.addCookie(cookie);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			Biweekly.write(ical).go(baos);
		} catch (IOException e) {
			CargaTrabajoController.LOGGER.error("Error al descargar	 ics: ", e);
		}
		response.setContentLength(baos.toByteArray().length);
		OutputStream os;
		try {
			os = response.getOutputStream();
			baos.writeTo(os);
			os.flush();
			os.close();
		} catch (IOException e) {
			CargaTrabajoController.LOGGER.info("Error IOException: " + e);
		}

	}

	@RequestMapping(value = "/tareasDetalle/comprobarEstadoTareaTrabajo/{idTareasListString}", method = RequestMethod.GET)
	public @ResponseBody Boolean comprobarEstadoTareaTrabajo(@PathVariable String idTareasListString, Locale locale) {
		CargaTrabajoController.LOGGER.info("[POST - filter] : Obtener comprobarEstadoTarea");
		Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication().getCredentials();
		Boolean result = true;
		String[] idTareaList = idTareasListString.split(",");

		for (int i = 0; i < idTareaList.length; i++) {
			BigDecimal idTarea = new BigDecimal(idTareaList[i]);
			TareasTrabajo tareasFilter = new TareasTrabajo();
			tareasFilter.setIdTarea(idTarea);
			TareasTrabajo tareas = this.tareasTrabajoService.findConfTarea(tareasFilter);
			if (tareas.getEstadoAsignado() != EstadoAceptacionTareaEnum.PENDIENTE_ACEPTACION.getValue()
					|| !tareas.getPersonaAsignada().getDni().equals(credentials.getNif())) {
				result = false;
				break;
			}
		}
		return result;
	}

	@RequestMapping(value = "/tareasDetalle/comprobarConflictoFechasTareasTrabajo/{idTareasListString}", method = RequestMethod.POST)
	public @ResponseBody Long comprobarConflictoFechasTareasTrabajo(@PathVariable String idTareasListString, Locale locale) {
		CargaTrabajoController.LOGGER.info("[POST - filter] : comprobarConflictoFechasTareasTrabajo");
		String[] idTareaList = idTareasListString.split(",");
		return this.tareasTrabajoService.comprobarConflictoFechas(idTareaList);
	}

	@RequestMapping(value = "/tareasDetalle/aceptarAsignacionTareaTrabajo/{idTareasListString}/{estadoAceptacion}", method = RequestMethod.GET)
	public @ResponseBody Integer aceptarAsignacionTareaTrabajo(@PathVariable String idTareasListString,
			@PathVariable Integer estadoAceptacion, Locale locale) {
		CargaTrabajoController.LOGGER.info("[POST - filter] : Obtener aceptarAsignacionTareaTrabajo");
		String[] idTareaList = idTareasListString.split(",");
		Integer countUpdates = 0;
		for (int i = 0; i < idTareaList.length; i++) {
			BigDecimal idTarea = new BigDecimal(idTareaList[i]);
			TareasTrabajo tareas = new TareasTrabajo();
			tareas.setIdTarea(idTarea);
			tareas = this.tareasTrabajoService.findConfTarea(tareas);
			tareas.setEstadoAsignado(estadoAceptacion);

			RegistroAcciones registroAcciones = new RegistroAcciones();
			registroAcciones.setIdPuntoMenu(PuntosMenuEnum.CARGA_TRABAJO.getValue());
			registroAcciones.setIdAccion(AccionesEnum.MODIFICACION.getValue());
			StringBuilder observ = new StringBuilder();
			observ.append(this.appMessageSource.getMessage("label.regAcciones.aceptarAsig", null, locale));
			observ.append(": ");
			observ.append("id = ").append(idTarea);
			observ.append(" , " + this.appMessageSource.getMessage("comun.tipoDeTarea", null, locale)).append(Constants.IGUAL);
			if(Constants.LANG_EUSKERA.equals(locale.getLanguage())){
				observ.append(tareas.getTipoTarea().getDescEu015());
			}else if(Constants.LANG_CASTELLANO.equals(locale.getLanguage())){
				observ.append(tareas.getTipoTarea().getDescEs015());
			}
			registroAcciones.setObserv(observ.toString());
			this.registroAccionesService.add(registroAcciones);

			countUpdates += this.cargaTrabajoService.updateEstadoAceptacionTareaTrabajo(tareas);
		}
		return countUpdates;
	}

	@RequestMapping(value = "/tareasDetalle/rechazarAsignacionTareaTrabajo/{idTareasListString}/{estadoAceptacion}/{motivoRechazo}", method = RequestMethod.GET)
	public @ResponseBody ExpTareasResumen rechazarAsignacionTareaTrabajo(@PathVariable String idTareasListString,
			@PathVariable Integer estadoAceptacion, @PathVariable String motivoRechazo, Locale locale) {
		CargaTrabajoController.LOGGER.info("[GET] : Obtener rechazarAsignacionTareaTrabajo");
		ExpTareasResumen expTareasResumen = new ExpTareasResumen();
		String[] idTareaList = idTareasListString.split(",");
		for (int i = 0; i < idTareaList.length; i++) {
			BigDecimal idTarea = new BigDecimal(idTareaList[i]);
			TareasTrabajo tareas = new TareasTrabajo();
			tareas.setIdTarea(idTarea);
			tareas = this.tareasTrabajoService.findConfTarea(tareas);
			tareas.setEstadoAsignado(estadoAceptacion);
			this.cargaTrabajoService.insertObsrvRechazoTareaTrabajo(idTarea, motivoRechazo);

			RegistroAcciones registroAcciones = new RegistroAcciones();
			registroAcciones.setIdPuntoMenu(PuntosMenuEnum.CARGA_TRABAJO.getValue());
			registroAcciones.setIdAccion(AccionesEnum.MODIFICACION.getValue());
			StringBuilder observ = new StringBuilder();
			observ.append(this.appMessageSource.getMessage("label.regAcciones.rechazarAsig", null, locale));
			observ.append(": ");
			observ.append("id = ").append(idTarea);
			observ.append(" , " + this.appMessageSource.getMessage("comun.tipoDeTarea", null, locale)).append(Constants.IGUAL);
			if(Constants.LANG_EUSKERA.equals(locale.getLanguage())){
				observ.append(tareas.getTipoTarea().getDescEu015());
			}else if(Constants.LANG_CASTELLANO.equals(locale.getLanguage())){
				observ.append(tareas.getTipoTarea().getDescEs015());
			}
			observ.append(" , " + this.appMessageSource.getMessage("label.observaciones", null, locale) + "= ");
			observ.append(motivoRechazo);
			registroAcciones.setObserv(observ.toString());
			this.registroAccionesService.add(registroAcciones);

			this.cargaTrabajoService.updateEstadoAceptacionTareaTrabajo(tareas);

			DatosContacto datosContacto = new DatosContacto();
			datosContacto.setDni031(tareas.getPersonaAsignador().getDni());
			datosContacto = this.datosContactoService.findDatosContacto(datosContacto);

			final List<String> listaDestinatarios = new ArrayList<String>();
			if (this.appConfiguration.getProperty(Constants.APP_ENTORNO) != null) {
				if (Constants.LOCAL.equals(this.appConfiguration.getProperty(Constants.APP_ENTORNO))) {
					listaDestinatarios.add(Constants.DESTINATARIO_EMAIL_LOCAL);
				} else if (datosContacto != null) {
						listaDestinatarios.add(datosContacto.getEmail031());
				}

				if (TareasServiceUtils.isNotEmptyLstDestinatarios(listaDestinatarios)) {
					ParametrosEmail parametrosEmail = new ParametrosEmail();

					Locale localeEu = new Locale(Constants.LANG_EUSKERA);
					Locale localeEs = new Locale(Constants.LANG_CASTELLANO);
					Map<String, String> infoEu = new LinkedHashMap<String, String>();
					Map<String, String> infoEs = new LinkedHashMap<String, String>();

					StringBuilder info = new StringBuilder();
					info.append(tareas.getIdTarea() + Constants.GUION_CON_ESPACIOS
							+ TareasServiceUtils.obtenerDescripcionTipoTarea(localeEu, tareas)).append(" (");
					info.append(this.appMessageSource.getMessage("label.idTrabajo", null, localeEu)).append(" ")
					.append(tareas.getTrabajo().getIdTrabajo()).append(")");
					infoEu.put(this.appMessageSource.getMessage("label.tarea", null, localeEu), info.toString());

					info = new StringBuilder();
					info.append(tareas.getIdTarea() + Constants.GUION_CON_ESPACIOS
							+ TareasServiceUtils.obtenerDescripcionTipoTarea(localeEs, tareas)).append(" (");
					info.append(this.appMessageSource.getMessage("label.idTrabajo", null, localeEs)).append(" ")
					.append(tareas.getTrabajo().getIdTrabajo()).append(")");
					infoEs.put(this.appMessageSource.getMessage("label.tarea", null, localeEs), info.toString());

					if (!motivoRechazo.isEmpty()) {
						infoEu.put(this.appMessageSource.getMessage("label.observaciones", null, localeEu), motivoRechazo);
						infoEs.put(this.appMessageSource.getMessage("label.observaciones", null, localeEs), motivoRechazo);
					}
					parametrosEmail.setInfoEu(infoEu);
					parametrosEmail.setInfoEs(infoEs);


					try {
						this.mailService.sendMailWithParameters(TipoAvisoEnum.RECHAZO_ASIGNACION_TAREAS.getValue(),
								listaDestinatarios, parametrosEmail);
					} catch (Exception e) {
						CargaTrabajoController.LOGGER.info("Error en el envío de email", e);
					}
				} else {
					CargaTrabajoController.LOGGER.info("No hay destinatarios", listaDestinatarios);
				}
			}
		}
		return expTareasResumen;
	}

	@RequestMapping(value = "/descargarCalendarioICSTareasTrabajo/{tareasSeleccionados}", method = RequestMethod.GET)
	public void descargarCalendarioICSTareasTrabajo(@PathVariable() String tareasSeleccionados, HttpServletRequest request,
			HttpServletResponse response, Locale locale) {
		CargaTrabajoController.LOGGER.info("[GET] : descargarCalendarioICSTareasTrabajo");
		List<TareasTrabajo> lTarCal = new ArrayList<TareasTrabajo>();
		lTarCal = this.cargaTrabajoService.obtenerDatosTareasTrabajo(tareasSeleccionados);

		ICalendar ical = new ICalendar();

		for (TareasTrabajo tarCal : lTarCal) {
			VEvent event = new VEvent();
			event.setSummary(tarCal.getTrabajo().getIdTrabajo() + " - "  + tarCal.getIdTarea());
			if (locale.getLanguage().equals(Constants.LANG_CASTELLANO)) {
				event.setDescription(tarCal.getTipoTarea().getDescEs015());
			} else {
				event.setDescription(tarCal.getTipoTarea().getDescEu015());
			}

			event.setDateStart(DateUtils.obtFechaHoraFormateada(tarCal.getFechaInicio(), tarCal.getHoraInicio()));
			event.setDateEnd(DateUtils.obtFechaHoraFormateada(tarCal.getFechaFin(), tarCal.getHoraFin()));
			ical.addEvent(event);
		}

		response.setContentType("text/calendar");
		response.setHeader("Content-disposition", "attachment;filename=myCalendar.ics");

		response.setHeader("Pragma", "cache");
		response.setHeader("Expires", "0");
		response.setHeader("Cache-Control", "private");

		Cookie cookie = new Cookie("fileDownload", "true");
		cookie.setPath("/");
		cookie.setSecure(true);
		response.addCookie(cookie);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			Biweekly.write(ical).go(baos);
		} catch (IOException e) {
			CargaTrabajoController.LOGGER.error("Error al descargar	 ics: ", e);
		}
		response.setContentLength(baos.toByteArray().length);
		OutputStream os;
		try {
			os = response.getOutputStream();
			baos.writeTo(os);
			os.flush();
			os.close();
		} catch (IOException e) {
			CargaTrabajoController.LOGGER.info("Error IOException: " + e);
		}

	}

	private List<FicheroDocExp> parsearDocsTarea(List<DocumentoTarea> documentos) {
		List<FicheroDocExp> listaFicheros = null;

		if (documentos != null && !documentos.isEmpty()) {

			listaFicheros = new ArrayList<FicheroDocExp>();

			for (DocumentoTarea documentoTarea : documentos) {

				if (documentoTarea.getDocumentoOriginal() != null) {

					DocumentosExpediente documentosExpediente = documentoTarea.getDocumentoOriginal();
					this.parsearFicherosDocExp(listaFicheros, documentosExpediente.getFicheros());

				}

			}

		}

		return listaFicheros;
	}

	public void parsearFicherosDocExp(List<FicheroDocExp> listaFicheros, List<FicheroDocExp> ficheros) {
		if (ficheros != null && !ficheros.isEmpty()) {

			for (FicheroDocExp ficheroDoc : ficheros) {

				if (ficheroDoc.getIdDocInsertado() != null) {
					FicheroDocExp fichero = new FicheroDocExp();
					fichero.setOid(ficheroDoc.getOid());
					fichero.setNombre(ficheroDoc.getNombre());
					fichero.setContentType(ficheroDoc.getContentType());
					fichero.setTamano(ficheroDoc.getTamano());
					listaFicheros.add(fichero);
				}

			}

		}
	}
}
