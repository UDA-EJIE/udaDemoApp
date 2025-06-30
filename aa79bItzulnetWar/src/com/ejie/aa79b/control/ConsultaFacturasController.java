package com.ejie.aa79b.control;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.model.Entidad;
import com.ejie.aa79b.model.EstadoFactura;
import com.ejie.aa79b.model.Facturas;
import com.ejie.aa79b.model.Persona;
import com.ejie.aa79b.service.ExcelReportService;
import com.ejie.aa79b.service.FacturasService;
import com.ejie.aa79b.service.PdfReportService;
import com.ejie.x38.control.bind.annotation.RequestJsonBody;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;

@Controller
@RequestMapping(value = "/tramitacionexpedientes/facturacionypagos/consultafacturas")
public class ConsultaFacturasController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConsultaFacturasController.class);
	@Autowired
	private FacturasService facturasService;
	@Autowired()
	private ReloadableResourceBundleMessageSource appMessageSource;
	@Autowired
	private ExcelReportService aa79bExcelReportService;
	@Autowired()
	private PdfReportService aa79bPdfReportService;

	private static final String LITERAL_LABEL = "menu.consultaFacturas";

	@RequestMapping(value = "/maint", method = RequestMethod.GET)
	public String getConsultaFacturas() {
		ConsultaFacturasController.LOGGER.info("[GET - View] : getConsultaFacturas");
		return "consultaFacturas";
	}

	@RequestMapping(value = "/suggestEntidadesFacturas", method = RequestMethod.GET)
	public @ResponseBody() List<Entidad> getEntidadesFacturas(@RequestParam() String q,
			@RequestParam(required = false) String tipo, Locale locale) {
		ConsultaFacturasController.LOGGER.info("[GET - find_ALL] : Obtener Entidad por filtro");

		final Entidad entidad = new Entidad();
		if (Constants.LANG_CASTELLANO.equals(locale.getLanguage())) {
			entidad.setDescAmpEs(q);
		} else {
			entidad.setDescAmpEu(q);
		}
		entidad.setTipo(tipo);

		return this.facturasService.findEntidadesFactura(entidad);
	}

	@RequestMapping(value = "/suggestContactoFacturas/{tipo}/{codigo}", method = RequestMethod.GET)
	public @ResponseBody List<Persona> findGestoresDeExpCEntidad(@RequestParam(required = false) String q,
			@PathVariable() String tipo, @PathVariable() Long codigo) {
		ConsultaFacturasController.LOGGER.info("[GET - find_ALL] : Obtener Persona por filtro");
		Facturas facturasFilter = new Facturas();
		Persona persona = new Persona();
		persona.setNombreFiltro(q);
		// si la entidad pasada es nula, vacia o un string con valor "-1", no lo
		// incluye en el filtro
		if (!StringUtils.isBlank(tipo) && !Constants.MINUS_UNO.toString().equals(tipo)) {
			facturasFilter.setTipoEntidadAsoc(tipo);
		}
		// si el codigo de entidad pasado es -1, no lo incluye en el filtro
		if (Constants.MINUS_UNO != codigo.longValue()) {
			facturasFilter.setIdEntidadAsoc(codigo);
		}
		facturasFilter.setPersona(persona);
		return this.facturasService.findContactoDeEntidadFaturas(facturasFilter);
	}

	@RequestMapping(value = "/filter", method = RequestMethod.POST)
	public @ResponseBody JQGridResponseDto<Facturas> getFacturas(
			@RequestJsonBody(param = "filter") Facturas filterFacturas,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto) {
		ConsultaFacturasController.LOGGER.info("[GET - findBy_PK] : Obtener Facturas por filtro");
		return this.facturasService.facturasFilter(filterFacturas, jqGridRequestDto, false);
	}
	
	@RequestMapping(value = "/estadosFactura/{facturaE}", method = RequestMethod.GET)
	public @ResponseBody() List<EstadoFactura> getEstadosFactura(@PathVariable() boolean facturaE) {
		ConsultaFacturasController.LOGGER.info("getEstadosFactura");
		return this.facturasService.estadosFactura(facturaE, false);
	}

	@RequestMapping(value = "/estadosFacturaSinBaja/{facturaE}", method = RequestMethod.GET)
	public @ResponseBody() List<EstadoFactura> getEstadosFacturaSinBaja(@PathVariable() boolean facturaE) {
		ConsultaFacturasController.LOGGER.info("getEstadosFactura");
		return this.facturasService.estadosFactura(facturaE, false);
	}

	@RequestMapping(value = "/xlsxReport", method = RequestMethod.POST)
	public void xlsxTabla(@RequestParam(value = "columns", required = false) String columns,
			@RequestParam(value = "criterios", required = false) String criterios,
			@ModelAttribute() JQGridRequestDto jqGridRequestDto, @ModelAttribute() Facturas filterFacturas,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		ConsultaFacturasController.LOGGER.info("[POST - subm]: xlsxTabla ========================================");
		Locale locale = LocaleContextHolder.getLocale();
		JQGridRequestDto jqGridRequestDtoReport = new JQGridRequestDto(null, null, jqGridRequestDto.getSidx(),
				jqGridRequestDto.getSord());
		JQGridResponseDto<Facturas> respuesta = this.facturasService.facturasFilter(filterFacturas,
				jqGridRequestDtoReport, false);

		final String label = this.appMessageSource.getMessage(LITERAL_LABEL, new Object[] {}, locale);

		this.aa79bExcelReportService.generarExcelJson("printExcel", response, criterios, columns, respuesta.getRows(),
				locale, LITERAL_LABEL, label);
	}

	@RequestMapping(value = "/pdfReport", method = RequestMethod.POST)
	public void pdfTabla(@RequestParam(value = "columns", required = false) String columns,
			@RequestParam(value = "criterios", required = false) String criterios, HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute() JQGridRequestDto jqGridRequestDto,
			@ModelAttribute() Facturas filterFacturas, Model model) {
		ConsultaFacturasController.LOGGER.info("[POST - subm]: pdfTabla ========================================");

		JQGridRequestDto jqGridRequestDtoReport = new JQGridRequestDto(null, null, jqGridRequestDto.getSidx(),
				jqGridRequestDto.getSord());

		JQGridResponseDto<Facturas> respuesta = this.facturasService.facturasFilter(filterFacturas,
				jqGridRequestDtoReport, false);

		Locale locale = LocaleContextHolder.getLocale();
		final String label = this.appMessageSource.getMessage(LITERAL_LABEL, new Object[] {}, locale);

		this.aa79bPdfReportService.generarPdfJson("defaultPDF", response, criterios, columns, respuesta.getRows(),
				LITERAL_LABEL, locale, label);
	}
	
	/**
	 * Operacion CRUD Delete. Borrado del registro correspondiente al
	 * identificador especificado.
	 *
	 * @param dni
	 *            String
	 * @param idAusencia
	 *            Integer Identificador del objeto que se desea eliminar.
	 * @return AusenciasCalendarioPersonal Bean eliminado.
	 */
	@RequestMapping(value = "/{idFactura}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody Facturas remove(@PathVariable Long idFactura) {
		Facturas factura = new Facturas();
		factura.setIdFactura(idFactura);
		this.facturasService.remove(factura);
		ConsultaFacturasController.LOGGER
				.info("[DELETE] : ConsultaFacturasController borrado correctamente");
		return factura;
	}
}
