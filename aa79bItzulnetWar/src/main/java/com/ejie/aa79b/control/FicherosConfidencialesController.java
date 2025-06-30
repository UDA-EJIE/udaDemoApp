package com.ejie.aa79b.control;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.DocumentosExpediente;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.Fichero;
import com.ejie.aa79b.security.Usuario;
import com.ejie.aa79b.service.DocumentosExpedienteService;
import com.ejie.aa79b.service.ExcelReportService;
import com.ejie.aa79b.service.ExpedientesConfidencialesService;
import com.ejie.aa79b.service.PdfReportService;
import com.ejie.aa79b.utils.AESCrypt;
import com.ejie.aa79b.webservices.PIDService;
import com.ejie.x38.control.bind.annotation.RequestJsonBody;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;

/**
 * FicherosConfidencialesController
 *
 * @author mrodriguez
 */

@Controller()
@RequestMapping(value = "/servicios/trataFicherosConfi/ficherosConfidenciales")
public class FicherosConfidencialesController {

	private static final Logger LOGGER = LoggerFactory.getLogger(FicherosConfidencialesController.class);
	private static final String TITULO_FICHEROS_CONFIDENCIALES = "label.titulo.ficherosConfidenciales";
	private static final String FICHEROFILE = "ficheroFile";
	private static final String RUTA_PIF = "rutaPifAa06a";

	@Autowired()
	private ExpedientesConfidencialesService expedientesConfidencialesService;
	@Autowired()
	private DocumentosExpedienteService documentosExpedienteService;
	@Autowired()
	private ExcelReportService aa79bExcelReportService;
	@Autowired()
	private PdfReportService aa79bPdfReportService;
	@Autowired()
	private ReloadableResourceBundleMessageSource appMessageSource;
	@Autowired()
	private PIDService pidService;
	 @Autowired()
	private Properties appConfiguration;

	/**
	 * @return String
	 */
	@RequestMapping(value = "/maint", method = RequestMethod.GET)
	public String getFicherosConf() {
		FicherosConfidencialesController.LOGGER.info("maint");
		return "ficherosConfidenciales";
	}

	/**
	 * Devuelve una lista de expedientes confidenciales
	 *
	 * @return List<Expediente> Lista de objetos correspondientes a la busqueda
	 *         realizada.
	 */
	@RequestMapping(value = "/busquedaexpconf/filter", method = RequestMethod.POST)
	public @ResponseBody JQGridResponseDto<Expediente> getExpedientesConfidenciales(
			@RequestJsonBody(param = "filter") Expediente filterExpediente,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto) {

		FicherosConfidencialesController.LOGGER
				.info("[GET - findBy_PK] : Obtener expedientes confidenciales por filtro");
		Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication().getCredentials();

		return this.expedientesConfidencialesService.busquedaexpconf(filterExpediente, credentials.getNif(),
				jqGridRequestDto, false);
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
		FicherosConfidencialesController.LOGGER
				.info("[POST - subm]: xlsxTabla ========================================");
		Locale locale = LocaleContextHolder.getLocale();
		Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication().getCredentials();
		JQGridRequestDto jqGridRequestDtoReport = new JQGridRequestDto(null, null, jqGridRequestDto.getSidx(),
				jqGridRequestDto.getSord());
		JQGridResponseDto<Expediente> respuesta = this.expedientesConfidencialesService
				.busquedaexpconf(filterExpediente, credentials.getNif(), jqGridRequestDtoReport, false);

		final String label = this.appMessageSource.getMessage(TITULO_FICHEROS_CONFIDENCIALES, new Object[] {}, locale);

		this.aa79bExcelReportService.generarExcelJson("printExcel", response, criterios, columns, respuesta.getRows(),
				locale, TITULO_FICHEROS_CONFIDENCIALES, label);
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
		FicherosConfidencialesController.LOGGER
				.info("[POST - subm]: pdfTabla ========================================");
		Locale locale = LocaleContextHolder.getLocale();
		Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication().getCredentials();
		JQGridRequestDto jqGridRequestDtoReport = new JQGridRequestDto(null, null, jqGridRequestDto.getSidx(),
				jqGridRequestDto.getSord());
		JQGridResponseDto<Expediente> respuesta = this.expedientesConfidencialesService
				.busquedaexpconf(filterExpediente, credentials.getNif(), jqGridRequestDtoReport, false);

		final String label = this.appMessageSource.getMessage(TITULO_FICHEROS_CONFIDENCIALES, new Object[] {}, locale);

		this.aa79bPdfReportService.generarPdfJson("defaultPDF", response, criterios, columns, respuesta.getRows(),
				TITULO_FICHEROS_CONFIDENCIALES, locale, label);
	}

	/**
	 * Obtiene los documentos del expediente en caso de que el usuario conectado
	 * sea técnico y los documentos asociados a tareas en las que el usuario sea
	 * el recurso de asignado de la misma en caso de que el usuario conectado
	 * sea traductor.
	 *
	 * @param anyo
	 *            Long
	 * @param numExp
	 *            Integer.
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/detalleFichConfidenciales/documentos/{anyo}/{numExp}", method = RequestMethod.GET)
	public ModelAndView getDocumentos(@PathVariable Long anyo, @PathVariable Integer numExp) {
		FicherosConfidencialesController.LOGGER
				.info("[getDocumentos] : Obtener documentos asociados al detalle de ficheros confidenciales");

		Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication().getCredentials();
		List<DocumentosExpediente> listaDocumentosExpediente = new ArrayList<DocumentosExpediente>();

		// Se prepara el objeto con sus filtros
		final DocumentosExpediente documentosExpedienteFiltro = new DocumentosExpediente();
		documentosExpedienteFiltro.setAnyo(anyo);
		documentosExpedienteFiltro.setNumExp(numExp);

		JQGridRequestDto jqGridRequestDto = new JQGridRequestDto();
		jqGridRequestDto.setSord("ASC");
		jqGridRequestDto.setSidx(DBConstants.FECHAALTA);

		if (credentials.getTecnico()) {
			// El usuario conectado es tiene perfil de técnico
			listaDocumentosExpediente = this.documentosExpedienteService.findAllConIntermediosYFinales(documentosExpedienteFiltro,
					jqGridRequestDto);
		} else {
			// El usuario conectado tiene perfil de traductor
			listaDocumentosExpediente = this.documentosExpedienteService.findDocumentosTarea(documentosExpedienteFiltro,
					credentials.getNif(), jqGridRequestDto);
		}

		ModelMap map = new ModelMap();
		map.put("listaDocumentos", listaDocumentosExpediente);
		return new ModelAndView("detalleFichConfidenciales", map);
	}

	@RequestMapping(value = "/desencriptarFichero/{anyoExpediente}/{idExpediente}", method = RequestMethod.POST)
	public @ResponseBody() Fichero desencriptarFichero(
			@RequestJsonBody(required = false, param = "fichero") Fichero fichero, @PathVariable Long anyoExpediente,
			@PathVariable Integer idExpediente, HttpServletRequest request, HttpServletResponse response,
			Locale locale) {
		FicherosConfidencialesController.LOGGER.info("desencriptarFichero");
		final String passWd = fichero.getEncriptado();

		Fichero bean = new Fichero();
		try {

			AESCrypt aesCrypt = new AESCrypt(passWd);

        	ByteArrayOutputStream baos = new ByteArrayOutputStream();

			aesCrypt.decrypt(fichero.getTamano(), this.pidService.getDocumentInput(fichero.getOid()), baos);
			bean.setRutaPif(this.pidService.subidaPif(fichero.getNombre(), baos.toByteArray(), this.appConfiguration.getProperty(RUTA_PIF), false));
			bean.setNombre(fichero.getNombre());
			bean.setContentType(fichero.getContentType());

			this.expedientesConfidencialesService.registrarAccionDesencripFich(fichero.getCodigo(), anyoExpediente,
					idExpediente);

		} catch (UnsupportedEncodingException e) {
			FicherosConfidencialesController.LOGGER.info("Error UnsupportedEncodingException: " + e);
			bean.setError("ERROR UnsupportedEncodingException");
		} catch (GeneralSecurityException e) {
			FicherosConfidencialesController.LOGGER.info("Error GeneralSecurityException: " + e);
			bean.setError("ERROR GeneralSecurityException");
		} catch (IOException e) {
			FicherosConfidencialesController.LOGGER.info("Error IOException: " + e);
			if (e.getLocalizedMessage().contains("password incorrect")) {
				bean.setError(this.appMessageSource.getMessage("label.passwrdDescIncorrecta", null, locale));
			} else if (e.getLocalizedMessage().contains("header")) {
				bean.setError(this.appMessageSource.getMessage("label.noEncriptado", null, locale));
			} else {
				bean.setError(this.appMessageSource.getMessage("label.errorGenEncript", null, locale));
			}
		} catch (Exception e) {
			FicherosConfidencialesController.LOGGER.error("ERROR descargando documento del PID: ", e);
			bean.setError("ERROR descargando documento del PID");
		}
		request.getSession().setAttribute(FICHEROFILE, bean);
		return bean;
	}

	@RequestMapping(value = "/abrirFichero")
	public void cargarSolicitudes(HttpServletRequest request, HttpServletResponse response) {
		FicherosConfidencialesController.LOGGER.info("PRUEBAS: cargarSolicitudes");
		Fichero aux = (Fichero) request.getSession().getAttribute(FICHEROFILE);

		response.setContentType(aux.getContentType());
		response.setHeader("Content-disposition", "attachment;filename=\"" + aux.getNombre() + "\"");

		response.setHeader("Pragma", "cache");
		response.setHeader("Expires", "0");
		response.setHeader("Cache-Control", "private");

		Cookie cookie = new Cookie("fileDownload", "true");
		cookie.setPath("/");
		cookie.setSecure(true);
		response.addCookie(cookie);

		ByteArrayOutputStream baos = new ByteArrayOutputStream(aux.getBytes().length);
		baos.write(aux.getBytes(), 0, aux.getBytes().length);
		response.setContentLength(aux.getBytes().length);
		OutputStream os;
		try {
			os = response.getOutputStream();
			baos.writeTo(os);
			os.flush();
			os.close();
		} catch (IOException e) {
			FicherosConfidencialesController.LOGGER.info("Error IOException: " + e);
		}

		request.getSession().removeAttribute(FICHEROFILE);
	}

}
