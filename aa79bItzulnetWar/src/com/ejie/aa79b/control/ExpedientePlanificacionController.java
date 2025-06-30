package com.ejie.aa79b.control;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.model.CalendarioIcsModel;
import com.ejie.aa79b.model.ConfigCalculoPresupuesto;
import com.ejie.aa79b.model.DatosEstimacion;
import com.ejie.aa79b.model.DatosPlanificacion;
import com.ejie.aa79b.model.DocumentosExpediente;
import com.ejie.aa79b.model.EstudioEstimado;
import com.ejie.aa79b.model.ExpTareasResumen;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.ExpedientePlanificacion;
import com.ejie.aa79b.model.ExpedienteTareas;
import com.ejie.aa79b.model.ExpedienteTradRev;
import com.ejie.aa79b.model.Persona;
import com.ejie.aa79b.model.Propiedad;
import com.ejie.aa79b.model.RechazoExpediente;
import com.ejie.aa79b.model.ResumenGraficas;
import com.ejie.aa79b.model.SubsanacionExpediente;
import com.ejie.aa79b.model.Tareas;
import com.ejie.aa79b.model.TareasTrabajo;
import com.ejie.aa79b.model.TiposTarea;
import com.ejie.aa79b.model.enums.EstadoExpedienteEnum;
import com.ejie.aa79b.model.enums.FaseExpedienteEnum;
import com.ejie.aa79b.model.enums.TipoExpedienteEnum;
import com.ejie.aa79b.model.excel.ExcelModel;
import com.ejie.aa79b.security.Usuario;
import com.ejie.aa79b.service.ConfigCalculoPresupuestoService;
import com.ejie.aa79b.service.DocumentosExpedienteService;
import com.ejie.aa79b.service.ExcelReportService;
import com.ejie.aa79b.service.ExpedientePlanificacionReportService;
import com.ejie.aa79b.service.ExpedientePlanificacionService;
import com.ejie.aa79b.service.ExpedienteService;
import com.ejie.aa79b.service.PropiedadService;
import com.ejie.aa79b.service.SubsanacionService;
import com.ejie.aa79b.service.TareasService;
import com.ejie.aa79b.service.TareasTrabajoService;
import com.ejie.aa79b.service.TiposTareaService;
import com.ejie.aa79b.utils.DateUtils;
import com.ejie.x38.control.bind.annotation.RequestJsonBody;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;
import com.ejie.x38.rup.jqgrid.filter.model.Filter;
import com.ejie.x38.rup.jqgrid.filter.service.FilterService;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;

@Controller
@RequestMapping(value = "/tramitacionexpedientes/planificacion/planificacionexpediente")
public class ExpedientePlanificacionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExpedientePlanificacionController.class);

	@Autowired()
	private ExpedientePlanificacionService expedientePlanificacionService;

	@Autowired()
	private TareasService tareasService;

	@Autowired()
	private TiposTareaService tiposTareasService;

	@Autowired
	private ExpedienteService expedienteService;

	@Autowired()
	private ConfigCalculoPresupuestoService configCalculoPresupuestoService;

	@Autowired()
	private ReloadableResourceBundleMessageSource appMessageSource;
	@Autowired
	private ExcelReportService aa79bExcelReportService;

	@Autowired
	private DocumentosExpedienteService documentosExpedienteService;

	@Autowired
	private SubsanacionService subsanacionService;

	@Autowired
	private ExpedientePlanificacionReportService expedientePlanificacionReportService;

	@Autowired
	private PropiedadService propiedadService;

	@Autowired
	private FilterService filterService;

	@Autowired
	private TareasTrabajoService tareasTrabajoService;

	/**
	 *
	 * @return String
	 */
	@RequestMapping(value = "/general/maint", method = RequestMethod.GET)
	public String getPestanasPlanificacion() {
		ExpedientePlanificacionController.LOGGER.info("[GET - View] : getPestanasPlanificacion");
		return "expedienteplanificaciongeneral";
	}

	/**
	 *
	 * @return String
	 */
	@RequestMapping(value = "/generalconsulta/maint", method = RequestMethod.GET)
	public String getExpedientePlanificacionGeneralConsulta() {
		ExpedientePlanificacionController.LOGGER.info("[GET - View] : getExpedientePlanificacionGeneralConsulta");
		return "expedienteplanificaciongeneralconsulta";
	}

	/**
	 *
	 * @return String
	 */
	@RequestMapping(value = "/resumen/maint", method = RequestMethod.GET)
	public String getResumen() {
		ExpedientePlanificacionController.LOGGER.info("[GET - View] : getResumen");
		return "pestresumen";
	}

	/**
	 *
	 * @return String
	 */
	@RequestMapping(value = "/datos/maint", method = RequestMethod.GET)
	public String getDatos() {
		ExpedientePlanificacionController.LOGGER.info("[GET - View] : getDatos");
		return "pestdatos";
	}

	/**
	 *
	 * @return String
	 */
	@RequestMapping(value = "/busquedageneral/maint", method = RequestMethod.GET)
	public String getBusquedaGeneral() {
		ExpedientePlanificacionController.LOGGER.info("[GET - View] : pestbusqueda");
		return "pestbusqueda";
	}

	/**
	 *
	 * @return String
	 */
	@RequestMapping(value = "/reasignartecnicopestbusqueda/maint", method = RequestMethod.GET)
	public String getReasignartecnicopestbusqueda() {
		ExpedientePlanificacionController.LOGGER.info("[GET - View] : reasignartecnicopestbusqueda");
		return "reasignartecnicopestbusqueda";
	}

	/**
	 *
	 * @return String
	 */
	@RequestMapping(value = "/desglosetareas/maint", method = RequestMethod.GET)
	public String getDesgloseTareas() {
		ExpedientePlanificacionController.LOGGER.info("[GET - View] : pestbusqueda");
		return "desglosetareas";
	}

	/**
	 *
	 * @return String
	 */
	@RequestMapping(value = "/detalleexpediente/maint", method = RequestMethod.GET)
	public String getDetalleExpediente() {
		ExpedientePlanificacionController.LOGGER.info("[GET - View] : getDetalleExpediente");
		return "planifdetalleexpgeneral";
	}

	/**
	 *
	 * @return String
	 */
	@RequestMapping(value = "/expedientetareas/maint", method = RequestMethod.GET)
	public String getTareasExpediente() {
		ExpedientePlanificacionController.LOGGER.info("[GET - View] : getTareasExpediente");
		return "expedientetareas";
	}

	/**
	 *
	 * @return String
	 */
	@RequestMapping(value = "/estudioestimado/maint", method = RequestMethod.GET)
	public String getEstudioEstimado() {
		ExpedientePlanificacionController.LOGGER.info("[GET - View] : getEstudioEstimado");
		return "estudioestimado";
	}

	/**
	 *
	 * @return String
	 */
	@RequestMapping(value = "/crearconftareas/maint", method = RequestMethod.GET)
	public String crearconfigurartareas() {
		ExpedientePlanificacionController.LOGGER.info("[GET - View] : crearConfigurarTareas");
		return "crearconftareas";
	}

	/**
	 *
	 * @param idTipoTarea
	 *            String
	 * @return String
	 */
	@RequestMapping(value = "/ejecutarTarea/{idTipoTarea}", method = RequestMethod.GET)
	public String getEjecutarTareaJSP(@PathVariable Long idTipoTarea, ModelMap model) {
		ExpedientePlanificacionController.LOGGER.info("[GET - View] : getEjecutarTareaJSP");
		final TiposTarea tipoTarea = this.tiposTareasService.find(new TiposTarea(idTipoTarea));
		model.put("descripcionTarea", tipoTarea.getDescEu015());
		model.put("horasObligatorias", tipoTarea.getIndHorasEjecucion015());
		model.put("tipoTarea", tipoTarea.getId015());
		if (Constants.SI.equals(tipoTarea.getIndGestionAsoc015())) {
			return "ejecutartarea" + idTipoTarea;
		} else {
			return "ejecutartareasingestion";
		}
	}

	/**
	 *
	 * @return String
	 */
	@RequestMapping(value = "/resumen/getChartsData", method = RequestMethod.GET)
	public @ResponseBody List<List<ResumenGraficas>> getChartsData() {
		ExpedientePlanificacionController.LOGGER.info("[GET] : getChartsData");
		// Se recupera el dni del usuario
		final Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication().getCredentials();
		return this.expedientePlanificacionService.getChartsData(credentials.getNif());
	}

	/**
	 *
	 * @param filter
	 *            ExpedienteTareas
	 * @param jqGridRequestDto
	 *            JQGridRequestDto
	 * @return JQGridResponseDto<ExpedienteTareas>
	 */
	@RequestMapping(value = "/datos/busquedaGeneral/filter", method = RequestMethod.POST)
	public @ResponseBody JQGridResponseDto<ExpTareasResumen> getExpedientesTareas(
			@RequestJsonBody(param = "filter") ExpTareasResumen filter,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto) {
		ExpedientePlanificacionController.LOGGER.info("[POST - filter] : Obtener Expedientes");
		final String sidx = jqGridRequestDto.getSidx();
		jqGridRequestDto.setSidx(sidx.replaceAll("anyoNumExpConcatenado asc,", ""));
		return this.expedientePlanificacionService.filterResumen(filter, jqGridRequestDto, false);
	}

	@RequestMapping(value = "/busquedageneral/filter", method = RequestMethod.POST)
	public @ResponseBody JQGridResponseDto<ExpedientePlanificacion> getFilter(
			@RequestJsonBody(param = "filter") ExpedientePlanificacion filter,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto) {
		ExpedientePlanificacionController.LOGGER.info("[POST - filter] : Obtener Expedientes");
		if (filter != null && filter.getIndicesBopv() != null) {
			if (filter.getExpedienteTradRev() == null) {
				ExpedienteTradRev expTradRev = new ExpedienteTradRev();
				filter.setExpedienteTradRev(expTradRev);
			}
			filter.getExpedienteTradRev().setIndPrevistoBopv(filter.getIndicesBopv());
			filter.getExpedienteTradRev().setIndPublicarBopv(filter.getIndicesBopv());
		}
		return this.expedienteService.busquedageneral(filter, jqGridRequestDto, false);

	}

	//añade o actualiza un filtro
	@RequestMapping(value = "/busquedageneral/multiFilter/add", method = RequestMethod.POST)
	public @ResponseBody Filter filterAdd(@RequestJsonBody(param="filtro") Filter filtro){
		ExpedientePlanificacionController.LOGGER.info("[POST - jqGrid] : add filter");
		Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication().getCredentials();
		filtro.setFilterUser(credentials.getNif());
	    return this.filterService.insert(filtro);
	}

	//borra un filtro
	@RequestMapping(value = "/busquedageneral/multiFilter/delete", method = RequestMethod.POST)
	public @ResponseBody Filter filterDelete(@RequestJsonBody(param="filtro") Filter filtro) {
		ExpedientePlanificacionController.LOGGER.info("[POST - jqGrid] : delete filter");
		Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication().getCredentials();
		filtro.setFilterUser(credentials.getNif());
	    return this.filterService.delete(filtro);
	}

	//Obtien todos los filtros
	@RequestMapping(value = "/busquedageneral/multiFilter/getAll", method = RequestMethod.GET)
	public @ResponseBody List<Filter> filterGetAll(
	    @RequestParam(value = "filterSelector", required = true) String filterSelector,
	    @RequestParam(value = "user", required = true) String filterUser) {
		ExpedientePlanificacionController.LOGGER.info("[get - jqGrid] : GetAll filter");
		Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication().getCredentials();
		String filterUserAux = credentials.getNif();
	    return this.filterService.getAllFilters(filterSelector,filterUserAux);
	}

	// Obtención del filtro por defecto
	@RequestMapping(value = "/busquedageneral/multiFilter/getDefault", method = RequestMethod.GET)
	public @ResponseBody Filter filterGetDefault(
	    @RequestParam(value = "filterSelector", required = true) String filterSelector,
	    @RequestParam(value = "user", required = true) String filterUser) {
		ExpedientePlanificacionController.LOGGER.info("[get - jqGrid] : getDefault filter");
		Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication().getCredentials();
		String filterUserAux = credentials.getNif();
	    return this.filterService.getDefault(filterSelector, filterUserAux);
	}

	/**
	 * Operacion de obtencion de ids de elementos seleccionados de RUP_TABLE.
	 *
	 * @param tableObjects
	 *           Map<String, Object>
	 * @return List<ExpedientePlanificacion>
	 */
	@RequestMapping(value = "/busquedageneral/getSelectedIds", method = RequestMethod.POST)
	public @ResponseBody List<ExpedientePlanificacion> getSelectedIds(
			@RequestJsonBody(param = "filterObject") ExpedientePlanificacion filter,
			@RequestJsonBody(param = "tableData") JQGridRequestDto tableData){
		ExpedientePlanificacionController.LOGGER.info("[POST - filter] : getSelectedIds");
		if (Constants.STRING_CERO.equals(filter.getIdTipoExpediente())) {
			filter.setIdTipoExpediente(TipoExpedienteEnum.INTERPRETACION.getValue());
		}
		if (filter != null && filter.getIndicesBopv() != null) {
			if (filter.getExpedienteTradRev() == null) {
				ExpedienteTradRev expTradRev = new ExpedienteTradRev();
				filter.setExpedienteTradRev(expTradRev);
			}
			filter.getExpedienteTradRev().setIndPrevistoBopv(filter.getIndicesBopv());
			filter.getExpedienteTradRev().setIndPublicarBopv(filter.getIndicesBopv());
		}
		return this.expedienteService.busquedageneralGetSelectedIds(filter,tableData);
	}

	@RequestMapping(value = "/desgloseTareas", method = RequestMethod.GET)
	public @ResponseBody List<ExpedienteTareas> getAllDesgloseTareas(
			@ModelAttribute ExpedientePlanificacion filterExpediente) {
		ExpedientePlanificacionController.LOGGER.info("[GET - find_ALL] : Obtener Expediente por filtro");
		return this.cargarListaTareas();
	}

	@RequestMapping(value = "/desgloseTareas/{sIdsExpedientes}/filter", method = RequestMethod.POST)
	public @ResponseBody JQGridResponseDto<ExpTareasResumen> getAllDesgloseTareasFilter(
			@PathVariable() String sIdsExpedientes, @RequestJsonBody JQGridRequestDto jqGridRequestDto) {
		final String sidx = jqGridRequestDto.getSidx();
		jqGridRequestDto.setSidx(sidx.replaceAll("anyoNumExpConcatenado asc,", ""));
		ExpedientePlanificacionController.LOGGER.info("[GET - find_ALL] : Obtener Expediente por filtro");
		return this.expedientePlanificacionService.filterDesgloseTareasExpedientes(sIdsExpedientes, jqGridRequestDto,
				false);
	}

	@RequestMapping(value = "/comprobarEstadoExpedientesEnCurso", method = RequestMethod.POST)
	public @ResponseBody Boolean getComprobarEstadoExpedientesEnCurso(
			@RequestBody() List<String> expedientesSeleccionados) {
		ExpedientePlanificacionController.LOGGER.info("[POST] : comprobarEstadoExpedientesEnCurso");
		return this.expedientePlanificacionService.getComprobarEstadoExpedientesEnCurso(expedientesSeleccionados);
	}

	@RequestMapping(value = "/tareasExpedientesForm/filter", method = RequestMethod.POST)
	public @ResponseBody JQGridResponseDto<Tareas> getTareasExpedientesFormFilter(
			@RequestJsonBody(param = "filter") Tareas filter, @RequestJsonBody JQGridRequestDto jqGridRequestDto) {
		ExpedientePlanificacionController.LOGGER.info("[POST - filter] : Obtener tareas del expediente");
		if (jqGridRequestDto != null) {
			jqGridRequestDto.setSidx("ORDEN,IDTAREA");
			jqGridRequestDto.setSord("ASC");
		}
		return this.tareasService.obtenerTareasConf(filter, jqGridRequestDto, false);
	}

	@RequestMapping(value = "/estimado/filter", method = RequestMethod.POST)
	public @ResponseBody JQGridResponseDto<EstudioEstimado> getEstudioEstimadoFormFilter(
			@RequestJsonBody(param = "filter") DatosEstimacion filter,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto) {
		ExpedientePlanificacionController.LOGGER
				.info("[POST - filter] : Obtiene los datos para estudiar la estimacion");
		return this.tareasService.obtenerEstudio(filter, jqGridRequestDto);
	}

	/**
	 *
	 * @return String
	 */
	@RequestMapping(value = "/irCargaTrabajo", method = RequestMethod.GET)
	public ModelAndView irCargaTrabajo() {
		ExpedientePlanificacionController.LOGGER.info("[GET - View] : irCargaTrabajo");
		return new ModelAndView("cargaTrabajo");
	}

	@RequestMapping(value = "/datosplanificacion", method = RequestMethod.POST)
	public @ResponseBody DatosPlanificacion getDatosPlanificacion(@RequestJsonBody Expediente expediente) {
		ExpedientePlanificacionController.LOGGER
				.info("[GET - getDatosPlanificacion] : Obtener los datos de planificacion");

		DatosPlanificacion datosPlanificacion;

		Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication().getCredentials();
		Persona asignador = new Persona();
		asignador.setDni(credentials.getNif());
		expediente.setAsignador(asignador);

		if (TipoExpedienteEnum.INTERPRETACION.getValue().equals(expediente.getIdTipoExpediente())) {
			datosPlanificacion = this.expedientePlanificacionService
					.obtenerDatosConfTareasExpInterpretacion(expediente);
		} else {
			datosPlanificacion = this.expedientePlanificacionService.obtenerDatosConfTareasExpTradRev(expediente);
		}

		return datosPlanificacion;
	}

	private List<ExpedienteTareas> cargarListaTareas() {
		return new ArrayList<ExpedienteTareas>();
	}

	/**
	 * Devuelve resultado de si el usuario en sesion es responsable de grupos de
	 * trabajo de expedientes de tipo Interpretacion
	 *
	 * @return boolean
	 */
	@RequestMapping(value = "/cargaInicialDatosPestBusqueda", method = RequestMethod.GET)
	public @ResponseBody List<Integer> cargaInicialDatosPestBusqueda() {
		Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication().getCredentials();
		String dni = credentials.getNif();
		ExpedientePlanificacionController.LOGGER
				.info("[GET] : obtener num de exp de interpretacion y" + "palTrados");
		List<Integer> resul = new ArrayList<Integer>();
		Integer tieneGruposTrabajo = this.expedienteService.countGruposTrabajoInterpretacion(dni);
		ConfigCalculoPresupuesto configCalculoPresupuesto = this.configCalculoPresupuestoService
				.find(new ConfigCalculoPresupuesto(Constants.ID_DATOS_BASICOS));
		Propiedad propiedad = new Propiedad();
		propiedad.setId(Constants.UNO);
		propiedad = this.propiedadService.find(propiedad);
		resul.add(tieneGruposTrabajo);
		resul.add(configCalculoPresupuesto.getPalTrados());
		resul.add(new Integer(propiedad.getValor()));
		return resul;
	}

	/**
	 *
	 * @return String
	 */
	@RequestMapping(value = "/reasignartareas/maint", method = RequestMethod.GET)
	public String getReasignarTareas() {
		ExpedientePlanificacionController.LOGGER.info("[GET - View] : getReasignarTareas");
		return "reasignartareas";
	}

	@RequestMapping(value = "/negociarFechaEntrega/guardar", method = RequestMethod.POST)
	public @ResponseBody void guardarRequerimientoFechaNueva(@RequestBody ExpedientePlanificacion expedientePlanificacion) {
		ExpedientePlanificacionController.LOGGER.info("[GET - View] : guardarFechaNueva");
		this.expedientePlanificacionService.addRequerimiento(expedientePlanificacion);
	}

	@RequestMapping(value = "/modificarFechaEntrega/guardar", method = RequestMethod.POST)
	public @ResponseBody void guardarFechaNueva(@RequestBody ExpedientePlanificacion expedientePlanificacion) {
		ExpedientePlanificacionController.LOGGER.info("[GET - View] : guardarFechaNueva");
		this.expedientePlanificacionService.modificarFechaEntrega(expedientePlanificacion);
	}

	@RequestMapping(value = "/expedientesrelacionadosplanif/maint", method = RequestMethod.GET)
	public String getExpedientesrelacionadosplanif() {
		ExpedientePlanificacionController.LOGGER.info("[GET - View] : getExpedientesrelacionadosplanif");
		return "expedientesrelacionadosplanif";
	}

	@RequestMapping(value = "/receptoresautorizadosplanif/maint", method = RequestMethod.GET)
	public String getReceptoresAutorizadosPlanif() {
		ExpedientePlanificacionController.LOGGER.info("[GET - View] : getReceptoresAutorizadosPlanif");
		return "receptoresautorizadosplanif";
	}

	@RequestMapping(value = "/contactoFacturacion/maint", method = RequestMethod.GET)
	public String getContactoFacturacion() {
		ExpedientePlanificacionController.LOGGER.info("[GET - View] : getContactoFacturacion");
		return "contactoFacturacion";
	}

	@RequestMapping(value = "/categorizacionExpediente/maint", method = RequestMethod.GET)
	public String getCategorizacionExpediente() {
		ExpedientePlanificacionController.LOGGER.info("[GET - View] : getCategorizacionExpediente");
		return "categorizacionExpediente";
	}

	@RequestMapping(value = "/rechazarExp/{procedencia}", method = RequestMethod.POST)
	public @ResponseBody Integer rechazarExpediente(
			@RequestJsonBody(required = false, param = "expediente") Expediente expedientes,
			@RequestJsonBody(required = false, param = "rechazoExp") RechazoExpediente rechazoExp,
			@PathVariable() String procedencia) {
		ExpedientePlanificacionController.LOGGER.info("[POST] : Rechazar expediente");

		return this.expedienteService.rechazarExpediente(expedientes, rechazoExp, procedencia);
	}

	@RequestMapping(value = "/rechazarExpediente/maint", method = RequestMethod.GET)
	public String getRechazarExpediente() {
		ExpedientePlanificacionController.LOGGER.info("[GET - View] : getRechazarExpediente");
		return "rechazarExpediente";
	}

	@RequestMapping(value = "/negociarFechaEntrega/comprobarEstadoFase/{anyoExpediente}/{idExpediente}", method = RequestMethod.POST)
	public @ResponseBody Boolean comprobarEstadoFase(@PathVariable() Long anyoExpediente,
			@PathVariable() Integer idExpediente) {
		ExpedientePlanificacionController.LOGGER.info("[GET - View] : comprobarEstadoFase");

		Expediente expFilter = new Expediente();
		expFilter.setAnyo(anyoExpediente);
		expFilter.setNumExp(idExpediente);
		Expediente bean = this.expedienteService.find(expFilter);

		Boolean result = false;
		if (bean.getBitacoraExpediente().getEstadoExp().getId()
				.equals(new Long(EstadoExpedienteEnum.EN_CURSO.getValue()))
				&& bean.getBitacoraExpediente().getFaseExp().getId()
						.equals(new Long(FaseExpedienteEnum.PDTE_TRAM_CLIENTE.getValue()))) {

			result = true;
		} else {

			result = false;
		}

		return result;
	}

	@RequestMapping(value = "/negociarFechaEntrega/maint/{anyoExpediente}/{idExpediente}", method = RequestMethod.GET)
	public String getNegociarFechaEntrega(@PathVariable() Long anyoExpediente, @PathVariable() Integer idExpediente,
			Model model) {
		ExpedientePlanificacionController.LOGGER.info("[GET - View] : getNegociarFechaEntrega");

		Expediente expFilter = new Expediente();
		expFilter.setAnyo(anyoExpediente);
		expFilter.setNumExp(idExpediente);
		Expediente bean = this.expedienteService.find(expFilter);

		if (bean != null) {
			model.addAttribute("fechaHoraEntrega", bean.getExpedienteTradRev().getFechaHoraFinalIZO());
		} else {
			model.addAttribute("fechaHoraEntrega", "");
		}

		return "negociarFechaEntrega";
	}

	@RequestMapping(value = "/modificarFechaEntrega/maint/{anyoExpediente}/{idExpediente}", method = RequestMethod.GET)
	public String getModificarFechaEntrega(@PathVariable() Long anyoExpediente, @PathVariable() Integer idExpediente,
			Model model) {
		ExpedientePlanificacionController.LOGGER.info("[GET - View] : getModificarFechaEntrega");

		Expediente expFilter = new Expediente();
		expFilter.setAnyo(anyoExpediente);
		expFilter.setNumExp(idExpediente);
		Expediente bean = this.expedienteService.find(expFilter);

		if (bean != null) {
			model.addAttribute("fechaHoraEntrega", bean.getExpedienteTradRev().getFechaHoraFinalIZO());
		} else {
			model.addAttribute("fechaHoraEntrega", "");
		}

		return "modificarFechaEntrega";
	}

	/**
	 *
	 * @return String
	 */
	@RequestMapping(value = "/reordenartareas/maint", method = RequestMethod.GET)
	public String getReordenarTareas() {
		ExpedientePlanificacionController.LOGGER.info("[GET - View] : getReordenarTareas");
		return "reordenartareas";
	}

	@RequestMapping(value = "/reordenarTareasExpForm/filter", method = RequestMethod.POST)
	public @ResponseBody JQGridResponseDto<Tareas> getReordenarTareasExpFilter(
			@RequestJsonBody(param = "filter") Tareas filter, @RequestJsonBody JQGridRequestDto jqGridRequestDto) {
		ExpedientePlanificacionController.LOGGER
				.info("[POST - filter] : Obtener tareas del expediente para reordenación");
		if (jqGridRequestDto != null) {
			jqGridRequestDto.setSidx("ORDEN,IDTAREA");
			jqGridRequestDto.setSord("ASC");
		}
		return this.tareasService.obtenerTareasConf(filter, jqGridRequestDto, false);
	}

	/**
	 *
	 * @param idTipoTarea
	 *            String
	 * @return String
	 */
	@RequestMapping(value = "/cambiarPrioridadExpediente/{anyo}/{numExp}", method = RequestMethod.POST)
	public @ResponseBody String cambiarPrioridadExpediente(@PathVariable Long anyo, @PathVariable Integer numExp) {
		ExpedientePlanificacionController.LOGGER.info("[GET - View] : cambiarPrioridadExpediente");
		return this.expedientePlanificacionService.cambiarPrioridadExpediente(anyo, numExp);
	}

	/**
	 * @param columns
	 * @param criterios
	 * @param jqGridRequestDto
	 * @param filterExpediente
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping(value = "/xlsxReport", method = RequestMethod.POST)
	public void xlsxTabla(@RequestParam(value = "columns", required = false) String columns,
			@RequestParam(value = "criterios", required = false) String criterios,
			@ModelAttribute() JQGridRequestDto jqGridRequestDto,
			@ModelAttribute() ExpedientePlanificacion filterExpediente, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		ExpedientePlanificacionController.LOGGER
				.info("[POST - subm]: xlsxTabla =======================================  =");
		if (Constants.STRING_CERO.equals(filterExpediente.getIdTipoExpediente())) {
			filterExpediente.setIdTipoExpediente(TipoExpedienteEnum.INTERPRETACION.getValue());
		}
		if (filterExpediente != null && filterExpediente.getIndicesBopv() != null) {
			if (filterExpediente.getExpedienteTradRev() == null) {
				ExpedienteTradRev expTradRev = new ExpedienteTradRev();
				filterExpediente.setExpedienteTradRev(expTradRev);
			}
			filterExpediente.getExpedienteTradRev().setIndPrevistoBopv(filterExpediente.getIndicesBopv());
			filterExpediente.getExpedienteTradRev().setIndPublicarBopv(filterExpediente.getIndicesBopv());
		}

		ExcelModel excelModel = this.expedientePlanificacionReportService.getInformePlanificacion(filterExpediente,
				columns, jqGridRequestDto);
		this.aa79bExcelReportService.generarExcel("printExcel", excelModel, response);
	}

	@RequestMapping(value = "/obtenerDatosTareaEstudioSubsanacion/{anyo}/{numExp}/{idTarea}", method = RequestMethod.GET)
	public @ResponseBody Tareas obtenerDatosTareaEstudioSubsanacion(@PathVariable Long anyo,
			@PathVariable Integer numExp, @PathVariable Long idTarea) {
		ExpedientePlanificacionController.LOGGER.info("[GET - View] : obtenerDatosTareaEstudioSubsanacion");
		final DocumentosExpediente documentosExpedienteFiltro = new DocumentosExpediente();
		documentosExpedienteFiltro.setAnyo(anyo);
		documentosExpedienteFiltro.setNumExp(numExp);
		Tareas tareas = new Tareas();
		SubsanacionExpediente subsanacionExpediente = this.subsanacionService.getSubsanacionExpediente(anyo, numExp, idTarea);
		List<DocumentosExpediente> listaDocumentosExpedienteBien = this.documentosExpedienteService
				.findAllEstudioSubsanacion(anyo, numExp, idTarea);
		tareas.setDocumentosExpediente(listaDocumentosExpedienteBien);
		tareas.setSubsanacion(subsanacionExpediente);
		return tareas;
	}

	@RequestMapping(value = "/datosEstimacion", method = RequestMethod.POST)
	public @ResponseBody DatosEstimacion getDatosEstimacion(@RequestJsonBody Expediente expediente) {
		ExpedientePlanificacionController.LOGGER
				.info("[GET - getDatosEstimacion] : Obtener los datos para estudiar la estimación");

		return this.expedientePlanificacionService.obtenerDatosEstimacion(expediente);
	}

	/**
	 *
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 */
	@RequestMapping(value = "/descargarCalendarioICS/{expedientesSeleccionados}/{tipoExp}", method = RequestMethod.GET)
	public void descargarIcs(@PathVariable() String expedientesSeleccionados, @PathVariable String tipoExp,
			HttpServletRequest request, HttpServletResponse response) {
		// 0 interpretacion -- 1 tradRev
		Integer iTipoExp = Integer.parseInt(tipoExp);
		List<CalendarioIcsModel> lExpeCal = new ArrayList<CalendarioIcsModel>();
		if (Constants.CERO == iTipoExp || Constants.UNO == iTipoExp) {
			lExpeCal = this.expedientePlanificacionService.obtenerDatosExpedientes(expedientesSeleccionados, iTipoExp);
		}
		ICalendar ical = new ICalendar();
		Locale locale = LocaleContextHolder.getLocale();
		for (CalendarioIcsModel expeCal : lExpeCal) {
			VEvent event = new VEvent();
			event.setSummary(expeCal.getAnyoNumExpConcatenado());
			if (Constants.STRING_CERO.equalsIgnoreCase(tipoExp)) {
				// interpretacion
				event.setLocation(expeCal.getDirNora().getTxtDirec());
			}
			event.setDescription(expeCal.getTitulo());

			event.setDateStart(DateUtils.obtFechaHoraFormateada(expeCal.getFechaInicio(), expeCal.getHoraInicio()));
			event.setDateEnd(DateUtils.obtFechaHoraFormateada(expeCal.getFechaFin(), expeCal.getHoraFin()));
			ical.addEvent(event);
		}

		response.setContentType("text/calendar");

		response.setHeader("Content-disposition", "attachment;filename="
				+ this.appMessageSource.getMessage("label.tituloCalendarioIcs", new Object[] {}, locale) + ".ics");

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
			ExpedientePlanificacionController.LOGGER.error("Error al descargar	 ics: ", e);
		}
		response.setContentLength(baos.toByteArray().length);
		OutputStream os;
		try {
			os = response.getOutputStream();
			baos.writeTo(os);
			os.flush();
			os.close();
		} catch (IOException e) {
			ExpedientePlanificacionController.LOGGER.info("Error IOException: " + e);
		}

	}

	@RequestMapping(value = "/tareacorreccionesprov/maint", method = RequestMethod.GET)
	public String tareaCorreccionesProv() {
		ExpedientePlanificacionController.LOGGER.info("[GET - View] : tareaCorreccionesProv");
		return "tareacorreccionesprov";
	}



	@RequestMapping(value = "/comprobarNecesidadFirmaDocs/{anyo}/{numExp}", method = RequestMethod.GET)
	public @ResponseBody Integer get(@PathVariable Long anyo, @PathVariable Integer numExp) {
		ExpedientePlanificacionController.LOGGER.info("[GET] : comprobarNecesidadFirmaDocs");

		Expediente expediente = new Expediente();
		expediente.setAnyo(anyo);
		expediente.setNumExp(numExp);
		return this.expedientePlanificacionService.comprobarNecesidadFirmaDocs(expediente);
	}

	/**
	 *
	 * @param idTarea
	 *            BigDecimal
	 * @return String
	 */
	@RequestMapping(value = "/ejecutarTareaTrabajo/{idTarea}/{idTipoTarea}", method = RequestMethod.GET)
	public String getEjecutarTareaTrabajoJSP(@PathVariable BigDecimal idTarea, @PathVariable Long idTipoTarea, ModelMap model) {
		ExpedientePlanificacionController.LOGGER.info("[GET - View] : getEjecutarTareaJSP");
		final TareasTrabajo tarea = this.tareasTrabajoService.obtenerDatosEjecucionTarea(idTarea);
		model.addAttribute("datEjecTarea", tarea != null ? tarea : new TareasTrabajo());
		final TiposTarea tipoTarea = this.tiposTareasService.find(new TiposTarea(idTipoTarea));
		model.put("descripcionTarea", tipoTarea.getDescEu015());
		model.put("horasObligatorias", tipoTarea.getIndHorasEjecucion015());
		model.put("tipoTarea", tipoTarea.getId015());
		return "ejecutartareatrabajo";
	}

}