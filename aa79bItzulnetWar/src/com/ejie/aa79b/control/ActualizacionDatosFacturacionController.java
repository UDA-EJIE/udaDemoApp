package com.ejie.aa79b.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.ExpedienteFacturacion;
import com.ejie.aa79b.model.ListaExpediente;
import com.ejie.aa79b.model.RegistroAcciones;
import com.ejie.aa79b.model.enums.AccionesEnum;
import com.ejie.aa79b.model.enums.PuntosMenuEnum;
import com.ejie.aa79b.service.ContactoFacturacionExpedienteService;
import com.ejie.aa79b.service.ExcelReportService;
import com.ejie.aa79b.service.ExpedienteFacturacionService;
import com.ejie.aa79b.service.PdfReportService;
import com.ejie.aa79b.service.RegistroAccionesService;
import com.ejie.x38.control.bind.annotation.RequestJsonBody;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * ActualizacionDatosFacturacionController
 * 
 * @author mrodriguez
 */

@Controller()
@RequestMapping(value = "/servicios/actDatosFacturacion")
public class ActualizacionDatosFacturacionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ActualizacionDatosFacturacionController.class);
	private static final String LITERAL_LABEL = "label.titulo.actualizacionDatosFacturacion";

	@Autowired()
	private ExpedienteFacturacionService expedienteFacturacionService;
	@Autowired()
	private ExcelReportService aa79bExcelReportService;
	@Autowired()
	private PdfReportService aa79bPdfReportService;
	@Autowired()
	ContactoFacturacionExpedienteService contactoFacturacionExpedienteService;
	@Autowired()
	private RegistroAccionesService registroAccionesService;
	@Autowired()
	private ReloadableResourceBundleMessageSource appMessageSource;

	/**
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/maint", method = RequestMethod.GET)
	public String getActualizacionDatosFacturacion() {
		ActualizacionDatosFacturacionController.LOGGER.info("[GET - View] : getActualizacionDatosFacturacion");
		return "actualizaciondatosfacturacion";
	}

	@RequestMapping(value = "/filter", method = RequestMethod.POST)
	public @ResponseBody JQGridResponseDto<ExpedienteFacturacion> getFilter(
			@RequestJsonBody(param = "filter") ExpedienteFacturacion filter,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto) {
		ActualizacionDatosFacturacionController.LOGGER
				.info("[POST - filter] : Actualización de los datos de facturación - Obtener Expedientes ");
		return this.expedienteFacturacionService.actDatosFacturacionExpedientesFilter(filter, jqGridRequestDto);
	}
	
	/**
	 * Operacion de obtencion de ids de elementos seleccionados de RUP_TABLE.
	 * 
	 * @param tableObjects
	 *           Map<String, Object>
	 * @return List<ExpedienteConsulta>
	 */
	@RequestMapping(value = "/getSelectedIds", method = RequestMethod.POST)
	public @ResponseBody List<ExpedienteFacturacion> getSelectedIds(
			@RequestJsonBody(param = "filterObject") ExpedienteFacturacion filterExpedienteFacturacion,
			@RequestJsonBody(param = "tableData") JQGridRequestDto tableData){
		ActualizacionDatosFacturacionController.LOGGER.info("[POST] : Actualización de los datos de facturacion - getSelectedIds");
		return this.expedienteFacturacionService.actDatosFacturacionExpedientesFilterGetSelectedIds(filterExpedienteFacturacion,tableData);
	}
	

	@RequestMapping(value = "/xlsxReport", method = RequestMethod.POST)
	public void xlsxTabla(@RequestParam(value = "columns", required = false) String columns,
			@RequestParam(value = "criterios", required = false) String criterios,
			@ModelAttribute() JQGridRequestDto jqGridRequestDto,
			@ModelAttribute() ExpedienteFacturacion filterExpedienteFacturacion, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		ActualizacionDatosFacturacionController.LOGGER
				.info("[POST - subm]: xlsxTabla =======================================  =");
		Locale locale = LocaleContextHolder.getLocale();
		JQGridRequestDto jqGridRequestDtoReport = new JQGridRequestDto(null, null, jqGridRequestDto.getSidx(),
				jqGridRequestDto.getSord());
		JQGridResponseDto<ExpedienteFacturacion> respuesta = this.expedienteFacturacionService
				.actDatosFacturacionExpedientesFilter(filterExpedienteFacturacion, jqGridRequestDtoReport);

		final String label = this.appMessageSource.getMessage(LITERAL_LABEL, new Object[] {}, locale);

		String columnsWithAdditionalInfo = columns.replaceAll("\\[\"gestorExpediente.solicitante.nombreCompleto.*?\\]",
				"[\"gestorExpediente.solicitante.dniCompleto\",\""
						+ this.appMessageSource.getMessage(Constants.LABEL_DNI, null, locale) + "\",50, center],"
						+ "[\"gestorExpediente.solicitante.nombreCompleto\",\""
						+ this.appMessageSource.getMessage(Constants.LABEL_NOMBREAPELLIDOS, null, locale)
						+ "\",100, center]," + "[\"gestorExpediente.solicitante.gestorExpedientesVIPDesc"
						+ WordUtils.capitalize(locale.getLanguage()) + "\",\""
						+ this.appMessageSource.getMessage(Constants.LABEL_GESTOR_VIP, null, locale)
						+ "\",100, center]," + "[\"gestorExpediente.entidad.desc"
						+ WordUtils.capitalize(locale.getLanguage()) + "\",\""
						+ this.appMessageSource.getMessage(Constants.LABEL_ENTIDAD, null, locale) + "\",100, center]");

		this.aa79bExcelReportService.generarExcelJson("printExcel", response, criterios, columnsWithAdditionalInfo,
				respuesta.getRows(), locale, LITERAL_LABEL, label);
	}

	@RequestMapping(value = "/pdfReport", method = RequestMethod.POST)
	public void pdfTabla(@RequestParam(value = "columns", required = false) String columns,
			@RequestParam(value = "criterios", required = false) String criterios, HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute() JQGridRequestDto jqGridRequestDto,
			@ModelAttribute() ExpedienteFacturacion filterExpedienteFacturacion, Model model) {
		ActualizacionDatosFacturacionController.LOGGER
				.info("[POST - subm]: pdfTabla ========================================");
		Locale locale = LocaleContextHolder.getLocale();
		JQGridRequestDto jqGridRequestDtoReport = new JQGridRequestDto(null, null, jqGridRequestDto.getSidx(),
				jqGridRequestDto.getSord());
		JQGridResponseDto<ExpedienteFacturacion> respuesta = this.expedienteFacturacionService
				.actDatosFacturacionExpedientesFilter(filterExpedienteFacturacion, jqGridRequestDtoReport);

		final String label = this.appMessageSource.getMessage(LITERAL_LABEL, new Object[] {}, locale);

		String columnsWithAdditionalInfo = columns.replaceAll("\\[\"gestorExpediente.solicitante.nombreCompleto.*?\\]",
				"[\"gestorExpediente.solicitante.dniCompleto\",\""
						+ this.appMessageSource.getMessage(Constants.LABEL_DNI, null, locale) + "\",50, center],"
						+ "[\"gestorExpediente.solicitante.nombreCompleto\",\""
						+ this.appMessageSource.getMessage(Constants.LABEL_NOMBREAPELLIDOS, null, locale)
						+ "\",100, center]," + "[\"gestorExpediente.solicitante.gestorExpedientesVIPDesc"
						+ WordUtils.capitalize(locale.getLanguage()) + "\",\""
						+ this.appMessageSource.getMessage(Constants.LABEL_GESTOR_VIP, null, locale)
						+ "\",100, center]," + "[\"gestorExpediente.entidad.desc"
						+ WordUtils.capitalize(locale.getLanguage()) + "\",\""
						+ this.appMessageSource.getMessage(Constants.LABEL_ENTIDAD, null, locale) + "\",100, center]");

		this.aa79bPdfReportService.generarPdfJson("defaultPDF", response, criterios, columnsWithAdditionalInfo,
				respuesta.getRows(), LITERAL_LABEL, locale, label);
	}

	@RequestMapping(value = "/detalleDatosFactInter", method = RequestMethod.GET)
	public String getDetalleDatosFactInter() {
		ActualizacionDatosFacturacionController.LOGGER.info("[GET - View] : getDetalleDatosFactInter");
		return "detalledatosfacturacioninter";
	}
	
	/**
	 * obtiene los datos de boe (url) del expediente
	 * 
	 * @param expediente
	 *            Expediente
	 * @return Boolean
	 */
	@RequestMapping(value = "/obtenerDatosBoeExp", method = RequestMethod.POST)
	public @ResponseBody Expediente obtenerDatosBoeExp(@RequestJsonBody Expediente expediente) {
		
		ActualizacionDatosFacturacionController.LOGGER.info("[POST - obtenerDatosBoeExp]");
		return this.expedienteFacturacionService.obtenerDatosBoeExp(expediente);
	}
	
	/**
	 * guarda los datos relacionados al BOE de un expediente (indBoe y url) o varios (solo indBoe)
	 * 
	 * @param expObjects
	 *            Map<String, Object>
	 * @return Integer
	 */
	@RequestMapping(value = "/guardarDatosBoe", method = RequestMethod.POST)
	public @ResponseBody Integer guardarDatosBoe(@RequestBody Map<String, Object> expObjects ) {
		ActualizacionDatosFacturacionController.LOGGER.info("[POST - guardarDatosBoe]");
		ObjectMapper mapper = new ObjectMapper();
		String urlBoe = mapper.convertValue( expObjects.get("urlBoe"), String.class);
		String indBoe = mapper.convertValue( expObjects.get("indBoe"), String.class);
		ListaExpediente listaExpediente = mapper.convertValue( expObjects.get("listaExpediente"), ListaExpediente.class);
		Integer iResul = Constants.CERO;
		if(listaExpediente.getListaExpediente()!= null && listaExpediente.getListaExpediente().size()>Constants.CERO){
			if(listaExpediente.getListaExpediente().size()== Constants.UNO){
				//guardar datos boe de un expediente (poner a si ( puede venir url) o a no (sin url))
				if(indBoe == null){
					//si entra aqui es porque se ha seleccionado un expediente y se ha clickado en el boton de la toolbar de publicar en BOE masivo
					//lo ponemos a si
					indBoe = Constants.SI;
				}
				iResul = this.expedienteFacturacionService.guardarDatosBoe(listaExpediente.getListaExpediente().get(Constants.CERO), urlBoe, indBoe);
			}else{
				//guardar datos boe de n expedientes (poner a si y no puede venir url)
				iResul = this.expedienteFacturacionService.guardarDatosBoe(listaExpediente);
			}
			if(iResul>Constants.CERO){
				//guardar en registro de acciones
				List<RegistroAcciones> lista = new ArrayList<RegistroAcciones>();
				RegistroAcciones reg = null;
				for(Expediente expediente : listaExpediente.getListaExpediente()){
					reg = new RegistroAcciones();
					reg.setIdPuntoMenu(PuntosMenuEnum.SERVICIO_ACT_DATOS_FACTURACION.getValue());
					reg.setIdAccion(AccionesEnum.MODIFICACION.getValue());
					Locale locale = new Locale(Constants.LANG_EUSKERA);
					StringBuilder observ = new StringBuilder();
					observ.append(this.appMessageSource.getMessage("mensaje.cambioBoe", null, locale));
					reg.setAnyo(expediente.getAnyo());
					reg.setNumExp(expediente.getNumExp());
					reg.setObserv(observ.toString());
					lista.add(reg);
				}
				
				this.registroAccionesService.addBatch(lista);
			}
		}
		return iResul;
	}
	
	/**
	 * comprueba si los expedientes seleccionados tienen el indboe a no
	 * 
	 * @param listaExpedientes
	 *            ListaExpediente
	 * @return Boolean
	 */
	@RequestMapping(value = "/comprobarExpSelBoeANo", method = RequestMethod.POST)
	public @ResponseBody Boolean comprobarExpSelBoeANo(@RequestJsonBody ListaExpediente listaExpedientes) {
		ActualizacionDatosFacturacionController.LOGGER.info("[POST - comprobarExpSelBoeANo]");
		Integer iResul = this.expedienteFacturacionService.comprobarExpSelBoeANo(listaExpedientes);
		Boolean bResul = true;
		if(iResul>Constants.CERO){
			bResul = false;
		}
		return bResul;
	
	}
	
}
