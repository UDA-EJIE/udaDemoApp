package com.ejie.aa79b.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.dao.ConfigCalculoPresupuestoDao;
import com.ejie.aa79b.model.ConfigCalculoPresupuesto;
import com.ejie.aa79b.model.ContactoFacturacionExpediente;
import com.ejie.aa79b.model.DetalleDatosFacturacion;
import com.ejie.aa79b.model.EntidadesGruposTrabajo;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.ExpedienteFacturacion;
import com.ejie.aa79b.model.ExpedienteFacturar;
import com.ejie.aa79b.model.Solicitante;
import com.ejie.aa79b.service.ContactoFacturacionExpedienteService;
import com.ejie.aa79b.service.EntidadesGruposTrabajoService;
import com.ejie.aa79b.service.ExcelReportService;
import com.ejie.aa79b.service.ExpedienteFacturacionService;
import com.ejie.aa79b.service.PdfReportService;
import com.ejie.aa79b.service.SolicitanteService;
import com.ejie.x38.control.bind.annotation.RequestJsonBody;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;

@Controller
@RequestMapping(value = "/tramitacionexpedientes/facturacionypagos/revisiondatosfacturacion")
public class RevisionDatosFacturacionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RevisionDatosFacturacionController.class);
	@Autowired()
	private PdfReportService aa79bPdfReportService;
	@Autowired()
	private ExcelReportService aa79bExcelReportService;
	@Autowired
	private ExpedienteFacturacionService expedienteFacturacionService;
	@Autowired
	private SolicitanteService solicitanteService;
	@Autowired
	private EntidadesGruposTrabajoService entidadesGruposTrabajoService;
	@Autowired()
	private ReloadableResourceBundleMessageSource appMessageSource;
	@Autowired
	ContactoFacturacionExpedienteService contactoFacturacionExpedienteService;
	@Autowired()
	private ConfigCalculoPresupuestoDao configCalculoPresupuestoDao;

	private static final String LITERAL_LABEL = "label.revisionDatosFact";

	/**
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/busquedarevision/maint", method = RequestMethod.GET)
	public String getPestanasPlanificacion() {
		RevisionDatosFacturacionController.LOGGER.info("[GET - View] : getConsultaFacturacionExpedientes");
		return "consultarevisiondatosfacturacion";
	}

	@RequestMapping(value = "/remove/{id058}", method = RequestMethod.POST)
	public @ResponseBody ContactoFacturacionExpediente remove(@PathVariable Long id058) {
		ContactoFacturacionExpediente contactoFacturacionExpediente = new ContactoFacturacionExpediente();
		contactoFacturacionExpediente.setId058(id058);
		this.contactoFacturacionExpedienteService.remove(contactoFacturacionExpediente);
		RevisionDatosFacturacionController.LOGGER
				.info("[DELETE] : ContactoFacturacionExpediente borrado correctamente");
		return contactoFacturacionExpediente;
	}

	@RequestMapping(value = "/newtable/filter", method = RequestMethod.POST)
	public @ResponseBody JQGridResponseDto<ContactoFacturacionExpediente> getFilter(
			@RequestJsonBody(param = "filter") ContactoFacturacionExpediente filter,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto) {
		RevisionDatosFacturacionController.LOGGER.info("[POST - filter] : rup_table vacia");
		return new JQGridResponseDto<ContactoFacturacionExpediente>();

	}

	@RequestMapping(value = "/busquedarevision/filter", method = RequestMethod.POST)
	public @ResponseBody JQGridResponseDto<ExpedienteFacturacion> getFilter(
			@RequestJsonBody(param = "filter") ExpedienteFacturacion filter,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto) {
		RevisionDatosFacturacionController.LOGGER
				.info("[POST - filter] : Obtener Expedientes: buscador de revisión de datos de facturación");
		return this.expedienteFacturacionService.revisionFacturacionExpedientesFilter(filter, jqGridRequestDto);

	}
	
	/**
	 * Operacion de obtencion de ids de elementos seleccionados de RUP_TABLE.
	 * 
	 * @param tableObjects
	 *           Map<String, Object>
	 * @return List<ExpedienteConsulta>
	 */
	@RequestMapping(value = "/busquedarevision/getSelectedIds", method = RequestMethod.POST)
	public @ResponseBody List<ExpedienteFacturacion> getSelectedIds(
			@RequestJsonBody(param = "filterObject") ExpedienteFacturacion filterExpedienteFacturacion,
			@RequestJsonBody(param = "tableData") JQGridRequestDto tableData){
		RevisionDatosFacturacionController.LOGGER.info("[POST] : busquedarevision - getSelectedIds");
		return this.expedienteFacturacionService.revisionFacturacionExpedientesFilterGetSelectedIds(filterExpedienteFacturacion,tableData);
	}

	@RequestMapping(value = "/entidadcontactofacturacion/maint", method = RequestMethod.GET)
	public String getEntidadContactoFacturacion(Model model) {
		RevisionDatosFacturacionController.LOGGER.info("[GET - View] : getEntidadContactoFacturacion");
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			json = mapper.writeValueAsString(new ArrayList<ContactoFacturacionExpediente>());
		} catch (Exception e) {
			RevisionDatosFacturacionController.LOGGER.error("Error mapeando listaDocumentos aa79b", e);
		}

		model.addAttribute("contactoFactuList", json);
		return "entidadcontactofacturacion";
	}

	@RequestMapping(value = "/detallecontactofacturacion/maint", method = RequestMethod.GET)
	public String getDetalleContactoFacturacion(Model model) {
		RevisionDatosFacturacionController.LOGGER.info("[GET - View] : getDetalleContactoFacturacion");
		ConfigCalculoPresupuesto configCalculoPresupuesto = this.configCalculoPresupuestoDao
				.find(new ConfigCalculoPresupuesto(Constants.ID_DATOS_BASICOS));
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			json = mapper.writeValueAsString(configCalculoPresupuesto);

		} catch (Exception e) {
			RevisionDatosFacturacionController.LOGGER.error("Error mapeando datosBasicos", e);
		}

		model.addAttribute("datosBasicos", json);
		return "detallerevisiondatosfacturacion";
	}

	@RequestMapping(value = "/detallecontactofacturacion/finRevisionFactura", method = RequestMethod.POST)
	public @ResponseBody void finRevisionFactura(@RequestBody DetalleDatosFacturacion detalleDatosFacturacionFilter,
			Locale locale) {
		RevisionDatosFacturacionController.LOGGER.info("[POST - filter] : finRevisionFactura");
		this.expedienteFacturacionService.finRevisionFactura(detalleDatosFacturacionFilter);
	}

	@RequestMapping(value = "/detallecontactofacturacion/finRevisionContactosEntidades", method = RequestMethod.POST)
	public @ResponseBody void finRevisionContactosEntidades(
			@RequestJsonBody(required = false, param = "listaExpedientes") String listContactoFactuExpStr,
			Locale locale) {
		RevisionDatosFacturacionController.LOGGER.info("[POST - filter] : finRevisionFactura");

		ObjectMapper mapper = new ObjectMapper();
		List<ContactoFacturacionExpediente> contactoFactuExpList = new ArrayList<ContactoFacturacionExpediente>();
		try {
			contactoFactuExpList = mapper.readValue(listContactoFactuExpStr,
					new TypeReference<List<ContactoFacturacionExpediente>>() {
					});
		} catch (Exception e) {
			RevisionDatosFacturacionController.LOGGER.info("Exception listExpedientesStr", e);
		}

		this.expedienteFacturacionService.finRevisionContactosEntidades(contactoFactuExpList);
	}

	@RequestMapping(value = "/detallecontactofacturacion/getdatosdetalle/{anyo}/{numExp}", method = RequestMethod.POST)
	public @ResponseBody() DetalleDatosFacturacion getDatosDetalle(@PathVariable() Long anyo,
			@PathVariable() int numExp) {
		RevisionDatosFacturacionController.LOGGER
				.info("[POST - add] : Borrar he insertar las entidades a los expedientes seleccionados");
		Expediente expediente = new Expediente();
		expediente.setAnyo(anyo);
		expediente.setNumExp(numExp);
		return this.expedienteFacturacionService.getDatosDetalle(expediente);
	}

	@RequestMapping(value = "/entidadcontactofacturacion/filter", method = RequestMethod.GET)
	public JQGridResponseDto<ContactoFacturacionExpediente> getEntidadContactoFacturacionFilter(Model model) {
		RevisionDatosFacturacionController.LOGGER.info("[POST - filter] : Obtener Entidades: filter vacio");
		return new JQGridResponseDto<ContactoFacturacionExpediente>();
	}

	@RequestMapping(value = "/entidadcontactofacturacion/guardarCambios", method = RequestMethod.POST)
	public @ResponseBody void guardarCambios(@RequestParam(value = "listaExpedientes") String listExpedientesStr,
			@RequestParam(required = false, value = "listaEntidadFactu") String listaEntidadFactuStr) {
		RevisionDatosFacturacionController.LOGGER
				.info("[POST - add] : Borrar he insertar las entidades a los expedientes seleccionados");

		ObjectMapper mapper = new ObjectMapper();
		List<Expediente> listExpedientes = new ArrayList<Expediente>();
		List<ExpedienteFacturar> listContactoFacturacionExpediente = new ArrayList<ExpedienteFacturar>();
		try {
			listExpedientes = mapper.readValue(listExpedientesStr, new TypeReference<List<Expediente>>() {
			});

			listContactoFacturacionExpediente = mapper.readValue(listaEntidadFactuStr,
					new TypeReference<List<ExpedienteFacturar>>() {
					});
		} catch (Exception e) {
			RevisionDatosFacturacionController.LOGGER.info("Exception listExpedientesStr", e);
		}

		for (Expediente exp : listExpedientes) {
			ContactoFacturacionExpediente contactoFactuExpFilter = new ContactoFacturacionExpediente();
			contactoFactuExpFilter.setAnyo(exp.getAnyo());
			contactoFactuExpFilter.setNumExp(exp.getNumExp());
			List<ContactoFacturacionExpediente> contactoFactuList = this.contactoFacturacionExpedienteService
					.findAll(contactoFactuExpFilter, new JQGridRequestDto());
			this.borrarEntidadContactoFacturacion(contactoFactuList, exp);
			this.registrarEntidadContactoFactu(exp.getAnyo(), exp.getNumExp(), listContactoFacturacionExpediente);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ContactoFacturacionExpediente add(
			@RequestBody ContactoFacturacionExpediente contactoFacturacionExpediente) {
		RevisionDatosFacturacionController.LOGGER
				.info("[POST - add] : Obtener datos formulario: No accede a base de datos");

		Solicitante solicitante = new Solicitante();
		solicitante.setDni(contactoFacturacionExpediente.getDniContacto058());
		Solicitante soliFind = this.solicitanteService.find(solicitante);

		EntidadesGruposTrabajo entidades = new EntidadesGruposTrabajo();
		entidades.setCodigo(contactoFacturacionExpediente.getIdEntidadAsoc058());
		entidades.setTipo(contactoFacturacionExpediente.getTipoEntidadAsoc058());
		EntidadesGruposTrabajo entiFind = this.entidadesGruposTrabajoService.findEntidad(entidades);

		contactoFacturacionExpediente.setContacto(soliFind);
		contactoFacturacionExpediente.setEntidadGruposTrabajo(entiFind);

		return contactoFacturacionExpediente;
	}

	@RequestMapping(value = "/datosEntidadesGrupoTrabajo", method = RequestMethod.POST)
	public @ResponseBody ContactoFacturacionExpediente datosEntidadesGrupoTrabajo(
			@RequestJsonBody ContactoFacturacionExpediente contactoFacturacionExpediente) {

		RevisionDatosFacturacionController.LOGGER
				.info("[POST - add] : Obtener datos formulario: No accede a base de datos");

		Solicitante solicitante = new Solicitante();
		solicitante.setDni(contactoFacturacionExpediente.getDniContacto058());
		Solicitante soliFind = this.solicitanteService.find(solicitante);

		EntidadesGruposTrabajo entidades = new EntidadesGruposTrabajo();
		entidades.setCodigo(contactoFacturacionExpediente.getIdEntidadAsoc058());
		entidades.setTipo(contactoFacturacionExpediente.getTipoEntidadAsoc058());
		EntidadesGruposTrabajo entiFind = this.entidadesGruposTrabajoService.findEntidad(entidades);

		contactoFacturacionExpediente.setContacto(soliFind);
		contactoFacturacionExpediente.setEntidadGruposTrabajo(entiFind);

		return contactoFacturacionExpediente;

	}

	@RequestMapping(value = "/xlsxReport", method = RequestMethod.POST)
	public void xlsxTabla(@RequestParam(value = "columns", required = false) String columns,
			@RequestParam(value = "criterios", required = false) String criterios,
			@ModelAttribute() JQGridRequestDto jqGridRequestDto,
			@ModelAttribute() ExpedienteFacturacion filterExpedienteFacturacion, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		RevisionDatosFacturacionController.LOGGER
				.info("[POST - subm]: xlsxTabla =======================================  =");
		Locale locale = LocaleContextHolder.getLocale();
		JQGridRequestDto jqGridRequestDtoReport = new JQGridRequestDto(null, null, jqGridRequestDto.getSidx(),
				jqGridRequestDto.getSord());
		JQGridResponseDto<ExpedienteFacturacion> respuesta = this.expedienteFacturacionService
				.revisionFacturacionExpedientesFilter(filterExpedienteFacturacion, jqGridRequestDtoReport);

		final String expedienteLabel = LITERAL_LABEL;
		final String label = this.appMessageSource.getMessage(expedienteLabel, new Object[] {}, locale);

		if (!columns.isEmpty()) {
			this.aa79bExcelReportService.generarExcelJson("printExcel", response, criterios, columns, respuesta.getRows(),
					locale, expedienteLabel, label);
		} else {
			this.aa79bExcelReportService.generarExcelJson("printExcel", response, criterios, columns, respuesta.getRows(),
					locale, expedienteLabel, label);
		}
	}

	@RequestMapping(value = "/pdfReport", method = RequestMethod.POST)
	public void pdfTabla(@RequestParam(value = "columns", required = false) String columns,
			@RequestParam(value = "criterios", required = false) String criterios, HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute() JQGridRequestDto jqGridRequestDto,
			@ModelAttribute() ExpedienteFacturacion filterExpedienteFacturacion, Model model) {
		RevisionDatosFacturacionController.LOGGER
				.info("[POST - subm]: pdfTabla ========================================");

		JQGridRequestDto jqGridRequestDtoReport = new JQGridRequestDto(null, null, jqGridRequestDto.getSidx(),
				jqGridRequestDto.getSord());

		JQGridResponseDto<ExpedienteFacturacion> respuesta = this.expedienteFacturacionService
				.revisionFacturacionExpedientesFilter(filterExpedienteFacturacion, jqGridRequestDtoReport);

		Locale locale = LocaleContextHolder.getLocale();
		final String label = this.appMessageSource.getMessage(LITERAL_LABEL, new Object[] {}, locale);

		this.aa79bPdfReportService.generarPdfJson("defaultPDF", response, criterios, columns, respuesta.getRows(),
				LITERAL_LABEL, locale, label);
	}

	@RequestMapping(value = "/detalledatosfacturacioninter/finRevDatosFact", method = RequestMethod.POST)
	public @ResponseBody void finRevDatosFact(@RequestBody DetalleDatosFacturacion detalleDatosFacturacionFilter,
			Locale locale) {
		RevisionDatosFacturacionController.LOGGER.info("[POST - filter] : finRevisionFactura");
		this.expedienteFacturacionService.finRevDatosFact(detalleDatosFacturacionFilter);
	}

	@RequestMapping(value = "/detallecontactofacturacion/finRevContactosEntidadesExpInter", method = RequestMethod.POST)
	public @ResponseBody void finRevContactosEntidadesExpInter(
			@RequestJsonBody(required = false, param = "listaExpedientes") String listContactoFactuExpStr,
			Locale locale) {
		RevisionDatosFacturacionController.LOGGER.info("[POST - filter] : finRevisionFactura");

		ObjectMapper mapper = new ObjectMapper();
		List<ContactoFacturacionExpediente> contactoFactuExpList = new ArrayList<ContactoFacturacionExpediente>();
		try {
			contactoFactuExpList = mapper.readValue(listContactoFactuExpStr,
					new TypeReference<List<ContactoFacturacionExpediente>>() {
					});
		} catch (Exception e) {
			RevisionDatosFacturacionController.LOGGER.info("Exception listExpedientesStr", e);
		}

		this.expedienteFacturacionService.finRevContactosEntidadesExpInter(contactoFactuExpList);
	}
	
	@RequestMapping(value = "/comprobarEstadoPresupuestos/{estado}", method = RequestMethod.POST)
	public @ResponseBody Boolean getComprobarEstadoExpedientesEnCurso(
			@RequestBody() List<Expediente> expedientesSeleccionados, @PathVariable() Integer estado) {
		RevisionDatosFacturacionController.LOGGER.info("[POST] : comprobarEstadoPresupuestos");
		
		return this.expedienteFacturacionService.comprobarEstadoPresupuestos(estado, expedientesSeleccionados);
	}

	public void registrarEntidadContactoFactu(Long anyo, int numExp,
			List<ExpedienteFacturar> listaEntidadFactu) {
		ContactoFacturacionExpediente contactoInsertar;
		for (ExpedienteFacturar entidadFactu : listaEntidadFactu) {
			contactoInsertar = new ContactoFacturacionExpediente();
			contactoInsertar.setAnyo(anyo);
			contactoInsertar.setNumExp(numExp);
			contactoInsertar.setTipoEntidadAsoc058(entidadFactu.getTipoEntidadAsoc());
			contactoInsertar.setIdEntidadAsoc058(entidadFactu.getIdEntidadAsoc());
			contactoInsertar.setDniContacto058(entidadFactu.getDniContacto());
			contactoInsertar.setPorFactura058(entidadFactu.getPorFactura());
			this.contactoFacturacionExpedienteService.add(contactoInsertar);
		}
	}

	private void borrarEntidadContactoFacturacion(List<ContactoFacturacionExpediente> contactoFactuList,
			Expediente exp) {
		for (ContactoFacturacionExpediente contactoFactuExpABorrar : contactoFactuList) {
			this.contactoFacturacionExpedienteService
					.borraTodosContactoEntidadPorId(contactoFactuExpABorrar.getId058());
			this.contactoFacturacionExpedienteService.borraTodosPorIdExcepcion(contactoFactuExpABorrar.getId058(),
					exp.getAnyo(), exp.getNumExp());
		}
	}
}
