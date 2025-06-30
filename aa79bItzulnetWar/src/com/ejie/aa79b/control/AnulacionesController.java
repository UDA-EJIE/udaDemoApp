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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejie.aa79b.model.AnulacionExpediente;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.service.AnulacionesService;
import com.ejie.aa79b.service.ExcelReportService;
import com.ejie.aa79b.service.PdfReportService;
import com.ejie.x38.control.bind.annotation.RequestJsonBody;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;

/**
 * AnulacionesController
 * 
 * @author mrodriguez
 */

@Controller
@RequestMapping(value = "/tramitacionexpedientes/gestionexpedientes/gestionanulaciones")
public class AnulacionesController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AnulacionesController.class);
	private static final String TITULO_GESTION_ANULACIONES = "titulo.gestionAnulaciones";

	@Autowired()
	private AnulacionesService anulacionesService;
	@Autowired()
	private ExcelReportService aa79bExcelReportService;
	@Autowired()
	private PdfReportService aa79bPdfReportService;
	@Autowired()
	private ReloadableResourceBundleMessageSource appMessageSource;

	/**
	 * Metodo de presentacion del RUP_TABLE.
	 * 
	 * @param model
	 *            Model
	 * @return String
	 */
	@RequestMapping(value = "/maint", method = RequestMethod.GET)
	public String getFormEdit() {
		AnulacionesController.LOGGER.info("[GET - View] : gestionanulaciones");
		return "gestionanulaciones";
	}

	@RequestMapping(value = "/enPestana", method = RequestMethod.GET)
	public String getAnulacionesPestana(Model model) {
		AnulacionesController.LOGGER.info("[GET - View] : getAnulacionesPestana");
		return "gestionanulacionespestana";
	}

	/**
	 * Devuelve una lista de expedientes susceptibles a ser anulados
	 * 
	 * @return List<Expediente> Lista de objetos correspondientes a la busqueda
	 *         realizada.
	 */
	@RequestMapping(value = "/busquedaexpaanular/filter", method = RequestMethod.POST)
	public @ResponseBody JQGridResponseDto<Expediente> getExpedientesAAnular(
			@RequestJsonBody(param = "filter") Expediente filterExpediente,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto) {

		AnulacionesController.LOGGER.info("[GET - findBy_PK] : Obtener expedientes a anular por filtro");
		return this.anulacionesService.busquedaexpaanular(filterExpediente, jqGridRequestDto, false);
	}

	/**
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/anularExpedientes/maint", method = RequestMethod.GET)
	public String getAnularExpedientes() {
		AnulacionesController.LOGGER.info("[GET - View] : getAnularExpedientes");
		return "anularexpedientes";
	}

	/**
	 * Se comprueba si el orden establecido para las tareas es correcto.
	 * 
	 * @param anulacionExpediente
	 *            AnulacionExpediente
	 * @param listExpedientesStr
	 *            String
	 */
	@RequestMapping(value = "/anulacionExpedientes", method = RequestMethod.POST)
	public @ResponseBody() void anularExpedientes(
			@RequestJsonBody(required = false, param = "anulacionExpediente") AnulacionExpediente anulacionExpediente,
			@RequestJsonBody(required = false, param = "listaExpedientes") String listExpedientesStr) {
		AnulacionesController.LOGGER.info("[POST] : Anular expedientes");

		ObjectMapper mapper = new ObjectMapper();
		List<Expediente> listExpedientes = new ArrayList<Expediente>();
		try {
			listExpedientes = mapper.readValue(listExpedientesStr, new TypeReference<List<Expediente>>() {
			});
		} catch (Exception e) {
			AnulacionesController.LOGGER.info("Exception listExpedientesStr", e);
		}

		anulacionesService.anularExpedientes(anulacionExpediente, listExpedientes);

	}

	/**
	 * @param columns
	 * @param criterios
	 * @param jqGridRequestDto
	 * @param filterMotivosAnulacion
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping(value = "/xlsxReport", method = RequestMethod.POST)
	public void xlsxTabla(@RequestParam(value = "columns", required = false) String columns,
			@RequestParam(value = "criterios", required = false) String criterios,
			@ModelAttribute() JQGridRequestDto jqGridRequestDto, @ModelAttribute() Expediente filterExpediente,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		AnulacionesController.LOGGER.info("[POST - subm]: xlsxTabla ========================================");
		Locale locale = LocaleContextHolder.getLocale();
		JQGridRequestDto jqGridRequestDtoReport = new JQGridRequestDto(null, null, jqGridRequestDto.getSidx(),
				jqGridRequestDto.getSord());
		JQGridResponseDto<Expediente> respuesta = this.anulacionesService.busquedaexpaanular(filterExpediente,
				jqGridRequestDtoReport, false);

		final String label = this.appMessageSource.getMessage(TITULO_GESTION_ANULACIONES, new Object[] {}, locale);

		this.aa79bExcelReportService.generarExcel("printExcel", response, criterios, columns, respuesta.getRows(),
				locale, TITULO_GESTION_ANULACIONES, label);
	}

	/**
	 * @param columns
	 * @param criterios
	 * @param request
	 * @param response
	 * @param jqGridRequestDto
	 * @param filter
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "/pdfReport", method = RequestMethod.POST)
	public void pdfTabla(@RequestParam(value = "columns", required = false) String columns,
			@RequestParam(value = "criterios", required = false) String criterios, HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute() JQGridRequestDto jqGridRequestDto,
			@ModelAttribute() Expediente filterExpediente, Model model) {
		AnulacionesController.LOGGER.info("[POST - subm]: pdfTabla ========================================");

		JQGridRequestDto jqGridRequestDtoReport = new JQGridRequestDto(null, null, jqGridRequestDto.getSidx(),
				jqGridRequestDto.getSord());

		JQGridResponseDto<Expediente> respuesta = this.anulacionesService.busquedaexpaanular(filterExpediente,
				jqGridRequestDtoReport, false);

		Locale locale = LocaleContextHolder.getLocale();
		final String label = this.appMessageSource.getMessage(TITULO_GESTION_ANULACIONES, new Object[] {}, locale);

		this.aa79bPdfReportService.generarPdf("defaultPDF", response, criterios, columns, respuesta.getRows(),
				TITULO_GESTION_ANULACIONES, locale, label);
	}

}
