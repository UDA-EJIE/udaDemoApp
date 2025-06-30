package com.ejie.aa79b.control;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.JasperUtils;
import com.ejie.aa79b.model.ConfigPerfilSolicitante;
import com.ejie.aa79b.model.DatosPagoProveedores;
import com.ejie.aa79b.model.DetalleExpedienteVisionCliente;
import com.ejie.aa79b.model.DireccionNora;
import com.ejie.aa79b.model.DocumentosExpediente;
import com.ejie.aa79b.model.EntradaLibroRegistroSol;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.ExpedienteConsulta;
import com.ejie.aa79b.model.ExpedientesRelacionados;
import com.ejie.aa79b.model.FicheroWS;
import com.ejie.aa79b.model.LibroRegistro;
import com.ejie.aa79b.model.ListaExpediente;
import com.ejie.aa79b.model.enums.TipoExpedienteEnum;
import com.ejie.aa79b.service.ConfigLibroRegistroService;
import com.ejie.aa79b.service.ConfigPerfilSolicitanteService;
import com.ejie.aa79b.service.ConsultasService;
import com.ejie.aa79b.service.DireccionNoraService;
import com.ejie.aa79b.service.DocumentosExpedienteService;
import com.ejie.aa79b.service.ExcelReportService;
import com.ejie.aa79b.service.ExpedienteService;
import com.ejie.aa79b.service.ExpedientesRelacionadosService;
import com.ejie.aa79b.service.PdfReportService;
import com.ejie.aa79b.utils.Utils;
import com.ejie.aa79b.webservices.PIDService;
import com.ejie.x38.control.bind.annotation.RequestJsonBody;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping(value = "/consultas")
public class ConsultasController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConsultasController.class);

	@Autowired()
	private ConsultasService consultasService;
	@Autowired
	private ExcelReportService aa79bExcelReportService;
	@Autowired()
	private PdfReportService aa79bPdfReportService;
	@Autowired()
	private ExpedienteService expedienteService;
	@Autowired()
	private DireccionNoraService direccionNoraService;
	@Autowired()
	private ExpedientesRelacionadosService expedientesRelacionadosService;
	@Autowired
	private ConfigPerfilSolicitanteService configPerfilSolicitanteService;
	@Autowired
	private ConfigLibroRegistroService configLibroRegistroService;
	@Autowired()
	private Properties appConfiguration;
	@Autowired
	private DocumentosExpedienteService documentosExpedienteService;
	@Autowired()
	private ApplicationContext ctx;
	@Autowired()
	private PIDService pidService;

	@Autowired()
	private ReloadableResourceBundleMessageSource appMessageSource;

	public static final String TITULO_JUSTIFICANTE_SOLICITUD = "titulo.justificanteSolicitud";

	/**
	 *
	 * @return String
	 */
	@RequestMapping(value = "/consultaexpedientes/maint", method = RequestMethod.GET)
	public String getConsultaExpedientes() {
		ConsultasController.LOGGER.info("[GET - View] : getConsultasConsultaExpedientes");
		return "consultaexpedientes";
	}

	/**
	 *
	 * @return String
	 */
	@RequestMapping(value = "/consultaplanificacionexpedientes/maint", method = RequestMethod.GET)
	public String getConsultaPlanificacionExpedientes() {
		ConsultasController.LOGGER.info("[GET - View] : getConsultasPlanificacionExpedientes");
		return "consultaplanificacionexpedientes";
	}

	/**
	 *
	 * @return String
	 */
	@RequestMapping(value = "/consultaregistroacciones/maint", method = RequestMethod.GET)
	public String getConsultaRegistroAcciones() {
		ConsultasController.LOGGER.info("[GET - View] : getConsultaRegistroAcciones");
		return "consultaregistroacciones";
	}

	/********************************************************************************************
	 * CONSULTA EXPEDIENTES - INICIO
	 ********************************************************************************************/
	/**
	 *
	 * @return String
	 */
	@RequestMapping(value = "/metadatosbusquedaconsulta/maint", method = RequestMethod.GET)
	public String getMetadatosbusquedaconsulta() {
		ConsultasController.LOGGER.info("[GET - View] : getMetadatosbusquedaconsulta");
		return "metadatosbusquedaconsulta";
	}

	/**
	 *
	 * @return String
	 */
	@RequestMapping(value = "/reasignargestorconsulta/maint", method = RequestMethod.GET)
	public String getReasignargestorConsulta() {
		ConsultasController.LOGGER.info("[GET - View] : getReasignargestorconsulta");
		return "reasignargestorconsulta";
	}

	/**
	 *
	 * @return String
	 */
	@RequestMapping(value = "/datospagoaproveedoresconsulta/maint", method = RequestMethod.GET)
	public String getDatosPagoaProveedoresConsulta() {
		ConsultasController.LOGGER.info("[GET - View] : getDatosPagoaProveedoresConsulta");
		return "datospagoaproveedoresconsulta";
	}

	/**
	 *
	 * @return String
	 */
	@RequestMapping(value = "/detalleexpedientedesdeclienteconsulta/maint", method = RequestMethod.GET)
	public String getDetalleExpedienteDesdeClienteConsulta() {
		ConsultasController.LOGGER.info("[GET - View] : getDetalleExpedienteDesdeClienteConsulta");
		return "detalleexpedientedesdeclienteconsulta";
	}

	@RequestMapping(value = "/consultaexpedientes/filter", method = RequestMethod.POST)
	public @ResponseBody JQGridResponseDto<ExpedienteConsulta> getFilter(
			@RequestJsonBody(param = "filter") ExpedienteConsulta filter,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto) {
		ConsultasController.LOGGER.info("[POST - filter] : Consulta expedientes");

		return this.consultasService.consultaexpedientes(filter, jqGridRequestDto);

	}

	/**
	 * Operacion de obtencion de ids de elementos seleccionados de RUP_TABLE.
	 *
	 * @param tableObjects
	 *           Map<String, Object>
	 * @return List<ExpedienteConsulta>
	 */
	@RequestMapping(value = "/consultaexpedientes/getSelectedIds", method = RequestMethod.POST)
	public @ResponseBody List<ExpedienteConsulta> getSelectedIds(
			@RequestJsonBody(param = "filterObject") ExpedienteConsulta filterExpedienteConsulta,
			@RequestJsonBody(param = "tableData") JQGridRequestDto tableData
			/*@RequestBody Map<String, Object> tableObjects*/){
		ConsultasController.LOGGER.info("[POST] : Consulta expedientes - getSelectedIds");
		return this.consultasService.consultaexpedientesGetSelectedIds(filterExpedienteConsulta,tableData);
	}


	@RequestMapping(value = "/consultaExpedientesXlsxReport", method = RequestMethod.POST)
	public void xlsxTabla(@RequestParam(value = "columns", required = false) String columns,
			@RequestParam(value = "criterios", required = false) String criterios,
			@ModelAttribute() JQGridRequestDto jqGridRequestDto, @ModelAttribute() ExpedienteConsulta filter,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		ConsultasController.LOGGER.info("[POST - subm]: xlsxTabla =======================================  =");
		Locale locale = LocaleContextHolder.getLocale();
		JQGridRequestDto jqGridRequestDtoReport = new JQGridRequestDto(null, null, jqGridRequestDto.getSidx(),
				jqGridRequestDto.getSord());
		JQGridResponseDto<ExpedienteConsulta> respuesta = this.consultasService.consultaexpedientes(filter,
				jqGridRequestDtoReport);

		final String expedienteLabel = "titulo.expediente";
		final String label = this.appMessageSource.getMessage(expedienteLabel, new Object[] {}, locale);

		this.aa79bExcelReportService.generarExcelJson("printExcel", response, criterios, columns, respuesta.getRows(),
				locale, expedienteLabel, label);
	}

	@RequestMapping(value = "/consultaExpedientesPdfReport", method = RequestMethod.POST)
	public void pdfTabla(@RequestParam(value = "columns", required = false) String columns,
			@RequestParam(value = "criterios", required = false) String criterios, HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute() JQGridRequestDto jqGridRequestDto,
			@ModelAttribute() ExpedienteConsulta filter, Model model) {
		ConsultasController.LOGGER.info("[POST - subm]: pdfTabla ========================================");

		JQGridRequestDto jqGridRequestDtoReport = new JQGridRequestDto(null, null, jqGridRequestDto.getSidx(),
				jqGridRequestDto.getSord());

		JQGridResponseDto<ExpedienteConsulta> respuesta = this.consultasService.consultaexpedientes(filter,
				jqGridRequestDtoReport);

		Locale locale = LocaleContextHolder.getLocale();
		final String expedienteLabel = "titulo.expediente";
		final String label = this.appMessageSource.getMessage(expedienteLabel, new Object[] {}, locale);

		this.aa79bPdfReportService.generarPdfJson("defaultPDF", response, criterios, columns, respuesta.getRows(),
				expedienteLabel, locale, label);
	}

	@RequestMapping(value = "/asignarGestorAExpedientes", method = RequestMethod.POST)
	public @ResponseBody Integer asignarGestorAExpedientes(@RequestJsonBody ListaExpediente listaExpedientes) {

		ConsultasController.LOGGER.info("[POST] : asignarGestorAExpedientes");
		return this.consultasService.asignarGestorAExpedientes(listaExpedientes);
	}

	@RequestMapping(value = "/comprobarDatosPagoAProveedores", method = RequestMethod.POST)
	public @ResponseBody Integer comprobarDatosPagoAProveedores(@RequestJsonBody Expediente expediente) {

		ConsultasController.LOGGER.info("[POST] : comprobarDatosPagoAProveedores");
		return this.consultasService.comprobarDatosPagoAProveedores(expediente);
	}

	@RequestMapping(value = "/datosPagoProveedoresConsulta/filter", method = RequestMethod.POST)
	public @ResponseBody JQGridResponseDto<DatosPagoProveedores> datosPagoProveedoresConsultaFilter(
			@RequestJsonBody(param = "filter") Expediente filter, @RequestJsonBody JQGridRequestDto jqGridRequestDto) {
		ConsultasController.LOGGER.info("[POST - filter] : datosPagoProveedoresConsultaFilter");
		if (Constants.STRING_CERO.equals(filter.getIdTipoExpediente())) {
			filter.setIdTipoExpediente(TipoExpedienteEnum.INTERPRETACION.getValue());

		}
		return this.consultasService.datosPagoProveedoresConsulta(filter, jqGridRequestDto, false);
	}

	@RequestMapping(value = "/comprobarDatosFacturacionExpediente", method = RequestMethod.POST)
	public @ResponseBody Integer comprobarDatosFacturacionExpediente(@RequestJsonBody Expediente expediente) {

		ConsultasController.LOGGER.info("[POST] : comprobarDatosFacturacionExpediente");
		return this.consultasService.comprobarDatosFacturacionExpedienteConsulta(expediente);
	}

	@RequestMapping(value = "/comprobarEsSolicitud", method = RequestMethod.POST)
	public @ResponseBody Integer comprobarEsSolicitud(@RequestJsonBody Expediente expediente) {

		ConsultasController.LOGGER.info("[POST] : comprobarEsSolicitud");
		return this.consultasService.comprobarEsSolicitud(expediente);
	}

	@RequestMapping(value = "/obtenerDatosDetalleExpedienteDesdeClienteConsulta", method = RequestMethod.POST)
	public @ResponseBody DetalleExpedienteVisionCliente obtenerDatosDetalleExpedienteDesdeClienteConsulta(
			@RequestJsonBody Expediente expediente) {

		ConsultasController.LOGGER.info("[POST] : obtenerDatosDetalleExpedienteDesdeClienteConsulta");
		DetalleExpedienteVisionCliente expedienteRst = this.consultasService
				.findDatosDetalleExpedienteDesdeClienteConsulta(expediente);
		if (TipoExpedienteEnum.INTERPRETACION.getValue().equals(expedienteRst.getIdTipoExpediente())
				&& expedienteRst.getExpedienteInterpretacion() != null) {
			DireccionNora direccionNora = this.direccionNoraService
					.find(expedienteRst.getExpedienteInterpretacion().getDirNora());
			DireccionNora direccion = new DireccionNora();
			direccion = Utils.obtenerDireccion(direccionNora);
			expedienteRst.getExpedienteInterpretacion().setDirNora(direccion);
		}
		return expedienteRst;
	}

	@RequestMapping(value = "/detalle/pdfSolicitud", method = RequestMethod.GET)
	public void getPdfSolicitud(@RequestParam() Long anyo, @RequestParam() Integer numExp,
			HttpServletResponse response) {
		ConsultasController.LOGGER.info("[POST] : getPdfSolicitud - inicio");
		String anyoSubstring = anyo.toString().substring(Constants.DOS);
		Long anyoNuevo = new Long(anyoSubstring);
		Expediente expedienteFilter = new Expediente(anyoNuevo, numExp);
		List<Expediente> listaExp = this.expedienteService.getJustificanteSolicitud(expedienteFilter);
		Expediente expedienteDatos = new Expediente();
		expedienteDatos = cargarExpDatosPdf(expedienteFilter, listaExp, expedienteDatos);
		ConfigPerfilSolicitante configPerfilSolicitanteFilter = new ConfigPerfilSolicitante();
		configPerfilSolicitanteFilter.setId(Constants.CONFIG_DEFAULT_ID);

		ConfigPerfilSolicitante configPerfilSolicitante = this.configPerfilSolicitanteService
				.find(configPerfilSolicitanteFilter);

		EntradaLibroRegistroSol entradaLibroRegistroSol = new EntradaLibroRegistroSol();
		entradaLibroRegistroSol.setAnyo(expedienteFilter.getAnyo());
		entradaLibroRegistroSol.setNumExp(expedienteFilter.getNumExp());
		List<LibroRegistro> libroRegistro = this.configLibroRegistroService
				.getlibroRegistroSol(entradaLibroRegistroSol);

		Locale locale = LocaleContextHolder.getLocale();

		Locale localEu = new Locale("eu");
		Locale localEs = new Locale("es");

		ModelMap modelMapJustificanteSol = new ModelMap();

		this.mapearValoresJustificanteSolicitud(modelMapJustificanteSol, configPerfilSolicitante, libroRegistro, locale,
				localEu, localEs);

		List<List<Expediente>> datos = Utils.separarLista(listaExp, "numExp");
		final List<Expediente> rdo2 = new ArrayList<Expediente>();
		for (List<Expediente> list : datos) {
			for (Expediente docusList : list) {
				DocumentosExpediente filterDocumentosExpediente = new DocumentosExpediente();
				filterDocumentosExpediente.setAnyo(expedienteFilter.getAnyo());
				filterDocumentosExpediente.setNumExp(expedienteFilter.getNumExp());
				List<DocumentosExpediente> listDocu = this.documentosExpedienteService
						.findAll(filterDocumentosExpediente, null);
				docusList.setDocumentosExpediente(listDocu);
				rdo2.add(docusList);
			}

		}
		modelMapJustificanteSol.put("lDatos", rdo2);

		JasperPrint jasperPrint = null;
		byte[] bytesInforme = null;
		try {
			Resource mainReport;
			if (TipoExpedienteEnum.INTERPRETACION.getValue().equals(expedienteDatos.getIdTipoExpediente())) {
				mainReport = this.ctx.getResource(Constants.RUTA_JASPER_JUSTIFICANTE_INTERPRETACION);
			} else {
				mainReport = this.ctx.getResource(Constants.RUTA_JASPER_JUSTIFICANTE_TRADREV);
			}

			JasperReport jasperReport = new JasperUtils().loadReport(mainReport);
			jasperPrint = JasperFillManager.fillReport(jasperReport, modelMapJustificanteSol,
					new JRBeanCollectionDataSource(rdo2));
			bytesInforme = JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (JRException e) {
			ConsultasController.LOGGER.error("Error pdfSolicitudes " + e);
		}

		FicheroWS ficheroBean = new FicheroWS();

		ficheroBean.setNombre(this.appMessageSource.getMessage(TITULO_JUSTIFICANTE_SOLICITUD, null, locale));
		ficheroBean.setExtension("." + Constants.FILE_PDF);
		ficheroBean.setContentType(Constants.CONTENT_TYPE_PDF);

		try {
			ficheroBean.setRutaPif(this.pidService.subidaPif(ficheroBean.getNombre() + ficheroBean.getExtension(),
					bytesInforme, this.appConfiguration.getProperty("rutaPifAa06a"), false));
		} catch (UnsupportedEncodingException e) {
			ConsultasController.LOGGER.info("Error UnsupportedEncodingException: " + e);
			ficheroBean.setEstado(Constants.ESTADO_WS_ERROR);
			ficheroBean.setDescripcion("ERROR UnsupportedEncodingException");

		} catch (Exception e) {
			ConsultasController.LOGGER.error("pid - subidaFichero Error: ", e);
			ficheroBean.setEstado(Constants.ESTADO_WS_ERROR);
			ficheroBean.setDescripcion(this.appMessageSource.getMessage("mensaje.fichero.errorPIF", null, locale));
		}

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition",
				"attachment; filename=\"" + ficheroBean.getNombre() + ficheroBean.getExtension() + "\"");
		try {
			OutputStream out = response.getOutputStream();
			InputStream servicioGetDelPifInput = this.pidService.servicioGetDelPifInput(ficheroBean.getRutaPif());
			byte[] outputByte = new byte[4096];
			while (servicioGetDelPifInput.read(outputByte, 0, 4096) != -1) {
				out.write(outputByte, 0, 4096);
			}
			servicioGetDelPifInput.close();
			out.close();
			response.flushBuffer();
		} catch (Exception e) {
			ConsultasController.LOGGER.error("ERROR descargando documento del PIF: ", e);
		}

		ConsultasController.LOGGER.info("[POST] : getPdfSolicitud - fin");
	}

	/**
	 * @param expedienteFilter
	 * @param listaExp
	 * @param expedienteDatos
	 * @return
	 */
	private Expediente cargarExpDatosPdf(Expediente expedienteFilter, List<Expediente> listaExp,
			Expediente expedienteDatosIn) {
		Expediente expedienteDatos = expedienteDatosIn;
		if (!listaExp.isEmpty()) {
			expedienteDatos = listaExp.get(Constants.CERO);
			expedienteDatos.setNumExpFormated(
					Utils.getAnyoNumExpConcatenado(expedienteDatos.getAnyo(), expedienteDatos.getNumExp()));
			if (TipoExpedienteEnum.INTERPRETACION.getValue().equals(expedienteDatos.getIdTipoExpediente())) {
				DireccionNora dirNora = expedienteDatos.getExpedienteInterpretacion().getDirNora();
				if (dirNora != null) {
					dirNora = this.direccionNoraService.find(dirNora);
					expedienteDatos.getExpedienteInterpretacion().setDirNora(dirNora);
					expedienteDatos.getExpedienteInterpretacion().getDirNora()
							.setTxtDirec(Utils.obtenerDireccion(dirNora).getTxtDirec());
				}
			} else {
				List<ExpedientesRelacionados> expRelacionados = this.expedientesRelacionadosService
						.selectExpRelacionadosList(expedienteFilter);
				if (!expRelacionados.isEmpty()) {
					for (int i = Constants.CERO; i < expRelacionados.size(); i++) {
						StringBuilder numExpRelFormated = new StringBuilder();
						numExpRelFormated.append(Utils.getAnyoNumExpConcatenado(expRelacionados.get(i).getAnyo(),
								expRelacionados.get(i).getNumExp()));
						expRelacionados.get(i).setNumExpFormated(
								expRelacionados.get(i).getAnyoExpRel().toString().substring(Constants.DOS) + "/"
										+ numExpRelFormated + expRelacionados.get(i).getNumExpRel());
					}
				}
				expedienteDatos.setListaExpedientesRelacionados(expRelacionados);
			}
		}
		return expedienteDatos;
	}

	private void mapearValoresJustificanteSolicitud(ModelMap modelMap, ConfigPerfilSolicitante configPerfilSolicitante,
			List<LibroRegistro> libroRegistro, Locale locale, Locale localEu, Locale localEs) {
		// Nombre fichero
		modelMap.put("fileName", this.appMessageSource.getMessage(TITULO_JUSTIFICANTE_SOLICITUD, null, locale));
		// En linea (no descarga fichero) ?
		modelMap.put("isInline", false);
		// Cabeceras comunes y locale (parameter)
		modelMap.put("REPORT_LOCALE", locale);
		modelMap.put("STATICS", this.appConfiguration.getProperty("datos.path"));
		modelMap.put("RUTAWAR", this.appConfiguration.getProperty("war.path"));

		if (!libroRegistro.isEmpty()) {
			modelMap.put("LIBRO_REGISTRO_NUM", this.appMessageSource
					.getMessage(libroRegistro.get(Constants.CERO).getNumRegistro(), null, localEs));
		}

		modelMap.put("RESGUARDO_ES", this.appMessageSource.getMessage("label.pdf.resguardo", null, localEs));
		modelMap.put("LIBRO_REGISTRO_ES", this.appMessageSource.getMessage("label.libroRegistro", null, localEs));
		modelMap.put("NUMERO_ANOTACION_ES", this.appMessageSource.getMessage("label.numeroAnotacion", null, localEs));
		modelMap.put("DECLARACION_ES",
				this.appMessageSource.getMessage(configPerfilSolicitante.getDeclaracionResponsableEs(), null, localEs));
		modelMap.put("DECLARACION_RESPON_ES",
				this.appMessageSource.getMessage("label.pdf.declaracionRespon", null, localEs));
		modelMap.put("DATOS_PERSONAL_ES", this.appMessageSource.getMessage("label.datosPersonales", null, localEs));
		modelMap.put("TITULO_ES", this.appMessageSource.getMessage(TITULO_JUSTIFICANTE_SOLICITUD, null, localEs));
		modelMap.put("GESTOR_ES", this.appMessageSource.getMessage("label.gestor", null, localEs));
		modelMap.put("CIF_ES", this.appMessageSource.getMessage("label.cif", null, localEs));
		modelMap.put("NIF_ES", this.appMessageSource.getMessage("label.nif", null, localEs));
		modelMap.put("ENTIDAD_ES", this.appMessageSource.getMessage("label.entidad", null, localEs));
		modelMap.put("MAIL_ES", this.appMessageSource.getMessage("label.email", null, localEs));
		modelMap.put("NOM_APELLIDOS_ES", this.appMessageSource.getMessage("label.nombreapellidos", null, localEs));
		modelMap.put("TIPO_EXP_ES", this.appMessageSource.getMessage("label.tipoExpediente", null, localEs));
		modelMap.put("TITULO_EXP_ES", this.appMessageSource.getMessage("label.tituloJusti", null, localEs));
		modelMap.put("TITULO_EXP_REL_ES", this.appMessageSource.getMessage("label.titulo", null, localEs));
		modelMap.put("OBSERVACIONES_ES", this.appMessageSource.getMessage("label.ficheroObservaciones", null, localEs));
		modelMap.put("NOTAS_ES", this.appMessageSource.getMessage("label.notas", null, localEs));
		modelMap.put("NOM_FICHERO_ES", this.appMessageSource.getMessage("label.ficheroAdjunto", null, localEs));
		modelMap.put("DATOS_SOLICITUD_ES", this.appMessageSource.getMessage("label.datosSolicitud", null, localEs));
		modelMap.put("DATOS_REVISION_ES", this.appMessageSource.getMessage("label.datosRevision", null, localEs));
		modelMap.put("IDIOMA_ES", this.appMessageSource.getMessage("label.idiomaDestinoTrad", null, localEs));
		modelMap.put("REFERENCIA_TRAM_ES", this.appMessageSource.getMessage("label.refTramitagune", null, localEs));
		modelMap.put("BOPV_ES", this.appMessageSource.getMessage("label.publicarBOPV", null, localEs));
		modelMap.put("CONFIDENCIAL_ES", this.appMessageSource.getMessage("label.esConfidencial", null, localEs));
		modelMap.put("CORREDACCION_ES", this.appMessageSource.getMessage("label.esCorredaccion", null, localEs));
		modelMap.put("TEXT_ELABORAR_ES", this.appMessageSource.getMessage("label.texto", null, localEs));
		modelMap.put("PARTICIPANTES_ES", this.appMessageSource.getMessage("label.participantes", null, localEs));
		modelMap.put("NUM_EXP_ES", this.appMessageSource.getMessage("label.numeroExpediente", null, localEs));
		modelMap.put("FECHA_ALTA_ES", this.appMessageSource.getMessage("label.fechaAlta", null, localEs));
		modelMap.put("TIPO_REDACCION_ES",
				this.appMessageSource.getMessage("label.tipoRedaccionBilingue", null, localEs));
		modelMap.put("EXP_RELACIONADAS_ES",
				this.appMessageSource.getMessage("menu.expedientesRelacionados", null, localEs));
		modelMap.put("DATOS_INTERPRETACION_ES",
				this.appMessageSource.getMessage("label.datosInterpretacion", null, localEs));
		modelMap.put("LUGAR_ES", this.appMessageSource.getMessage("label.lugarInterpretacion", null, localEs));
		modelMap.put("INDICADOR_ES", this.appMessageSource.getMessage("label.indProgramada", null, localEs));
		modelMap.put("MODO_INTERPRETACION_ES",
				this.appMessageSource.getMessage("label.modoInterpretacion", null, localEs));
		modelMap.put("PRESUPUESTO_ES", this.appMessageSource.getMessage("label.presupuestoJusti", null, localEs));
		modelMap.put("TIPO_ACTO_ES", this.appMessageSource.getMessage("label.tipoActo", null, localEs));
		modelMap.put("TIPO_PETICIONARIO_ES", this.appMessageSource.getMessage("label.tipoPeticionario", null, localEs));
		modelMap.put("FECHA_INI_INTER_ES",
				this.appMessageSource.getMessage("label.fechaHoraIniInterpretacion", null, localEs));
		modelMap.put("FECHA_FIN_INTER_ES",
				this.appMessageSource.getMessage("label.fechaHoraFinInterpretacion", null, localEs));
		modelMap.put("FECHA_INI_ES", this.appMessageSource.getMessage("label.fechaIni", null, localEs));
		modelMap.put("FECHA_FIN_ES", this.appMessageSource.getMessage("label.fechaFin", null, localEs));
		modelMap.put("SOLIC_PRESUPUESTO_ES",
				this.appMessageSource.getMessage("label.solicitarPresupuesto", null, localEs));
		modelMap.put("CONTACTO_ES", this.appMessageSource.getMessage("label.contactoJusti", null, localEs));
		modelMap.put("TELEFONO_ES", this.appMessageSource.getMessage("label.telefono", null, localEs));
		modelMap.put("DOCUMENTACION_ES", this.appMessageSource.getMessage("label.documentacion", null, localEs));
		modelMap.put("PALABRAS_SOL_ES", this.appMessageSource.getMessage("label.documento.numPalabras", null, localEs));
		modelMap.put("GARRANTZIA_ES", this.appMessageSource.getMessage("label.relevancia", null, localEs));
		modelMap.put("EXP_FACTURABLE_ES", this.appMessageSource.getMessage("label.entidadFacturable", null, localEs));
		modelMap.put("URGENTE_ES", this.appMessageSource.getMessage("label.urgente", null, localEs));
		modelMap.put("REQUIERE_PRESUPUESTO_ES",
				this.appMessageSource.getMessage("label.requierePresupuesto", null, localEs));
		modelMap.put("FECHA_FIN_IZO_ES", this.appMessageSource.getMessage("label.fechaHoraFinalIzo", null, localEs));
		modelMap.put("FECHA_FIN_SOL_ES", this.appMessageSource.getMessage("label.fechaFinalSolic", null, localEs));
		modelMap.put("FECHA_FIN_PROPUESTA_ES", this.appMessageSource.getMessage("label.fechaFinProp", null, localEs));
		modelMap.put("CLASE_DOCU_ES", this.appMessageSource.getMessage("label.claseDocumento", null, localEs));
		modelMap.put("TIPO_DOCU_ES", this.appMessageSource.getMessage("label.tipo", null, localEs));
		modelMap.put("NUM_PALABRAS_IZO_ES",
				this.appMessageSource.getMessage("label.documento.numPalabrasIZO", null, localEs));
		modelMap.put("NUM_PALABRAS_SOLIC_ES",
				this.appMessageSource.getMessage("label.documento.numPalabrasSolicitante", null, localEs));

		modelMap.put("SI_ES", this.appMessageSource.getMessage("gestion.si", null, localEs));
		modelMap.put("NO_ES", this.appMessageSource.getMessage("gestion.no", null, localEs));

		modelMap.put("RESGUARDO_EU", this.appMessageSource.getMessage("label.pdf.resguardo", null, localEu));
		modelMap.put("LIBRO_REGISTRO_EU", this.appMessageSource.getMessage("label.libroRegistro", null, localEu));
		modelMap.put("NUMERO_ANOTACION_EU", this.appMessageSource.getMessage("label.numeroAnotacion", null, localEu));
		modelMap.put("DECLARACION_EU",
				this.appMessageSource.getMessage(configPerfilSolicitante.getDeclaracionResponsableEu(), null, localEu));
		modelMap.put("DECLARACION_RESPON_EU",
				this.appMessageSource.getMessage("label.pdf.declaracionRespon", null, localEu));
		modelMap.put("DATOS_PERSONAL_EU", this.appMessageSource.getMessage("label.datosPersonales", null, localEu));
		modelMap.put("TITULO_EU", this.appMessageSource.getMessage(TITULO_JUSTIFICANTE_SOLICITUD, null, localEu));
		modelMap.put("GESTOR_EU", this.appMessageSource.getMessage("label.gestor", null, localEu));
		modelMap.put("CIF_EU", this.appMessageSource.getMessage("label.cif", null, localEu));
		modelMap.put("NIF_EU", this.appMessageSource.getMessage("label.nif", null, localEu));
		modelMap.put("ENTIDAD_EU", this.appMessageSource.getMessage("label.entidad", null, localEu));
		modelMap.put("MAIL_EU", this.appMessageSource.getMessage("label.email", null, localEu));
		modelMap.put("NOM_APELLIDOS_EU", this.appMessageSource.getMessage("label.nombreapellidos", null, localEu));
		modelMap.put("TIPO_EXP_EU", this.appMessageSource.getMessage("label.tipoExpediente", null, localEu));
		modelMap.put("TITULO_EXP_EU", this.appMessageSource.getMessage("label.tituloJusti", null, localEu));
		modelMap.put("TITULO_EXP_REL_EU", this.appMessageSource.getMessage("label.titulo", null, localEu));
		modelMap.put("OBSERVACIONES_EU", this.appMessageSource.getMessage("label.ficheroObservaciones", null, localEu));
		modelMap.put("NOTAS_EU", this.appMessageSource.getMessage("label.notas", null, localEu));
		modelMap.put("NOM_FICHERO_EU", this.appMessageSource.getMessage("label.ficheroAdjunto", null, localEu));
		modelMap.put("DATOS_SOLICITUD_EU", this.appMessageSource.getMessage("label.datosSolicitud", null, localEu));
		modelMap.put("DATOS_REVISION_EU", this.appMessageSource.getMessage("label.datosRevision", null, localEu));
		modelMap.put("IDIOMA_EU", this.appMessageSource.getMessage("label.idiomaDestinoTrad", null, localEu));
		modelMap.put("REFERENCIA_TRAM_EU", this.appMessageSource.getMessage("label.refTramitagune", null, localEu));
		modelMap.put("BOPV_EU", this.appMessageSource.getMessage("label.publicarBOPV", null, localEu));
		modelMap.put("CONFIDENCIAL_EU", this.appMessageSource.getMessage("label.esConfidencial", null, localEu));
		modelMap.put("CORREDACCION_EU", this.appMessageSource.getMessage("label.esCorredaccion", null, localEu));
		modelMap.put("TEXT_ELABORAR_EU", this.appMessageSource.getMessage("label.texto", null, localEu));
		modelMap.put("PARTICIPANTES_EU", this.appMessageSource.getMessage("label.participantes", null, localEu));
		modelMap.put("NUM_EXP_EU", this.appMessageSource.getMessage("label.numeroExpediente", null, localEu));
		modelMap.put("FECHA_ALTA_EU", this.appMessageSource.getMessage("label.fechaAlta", null, localEu));
		modelMap.put("TIPO_REDACCION_EU",
				this.appMessageSource.getMessage("label.tipoRedaccionBilingue", null, localEu));
		modelMap.put("EXP_RELACIONADAS_EU",
				this.appMessageSource.getMessage("menu.expedientesRelacionados", null, localEu));
		modelMap.put("DATOS_INTERPRETACION_EU",
				this.appMessageSource.getMessage("label.datosInterpretacion", null, localEu));
		modelMap.put("LUGAR_EU", this.appMessageSource.getMessage("label.lugarInterpretacion", null, localEu));
		modelMap.put("INDICADOR_EU", this.appMessageSource.getMessage("label.indProgramada", null, localEu));
		modelMap.put("MODO_INTERPRETACION_EU",
				this.appMessageSource.getMessage("label.modoInterpretacion", null, localEu));
		modelMap.put("PRESUPUESTO_EU", this.appMessageSource.getMessage("label.presupuestoJusti", null, localEu));
		modelMap.put("TIPO_ACTO_EU", this.appMessageSource.getMessage("label.tipoActo", null, localEu));
		modelMap.put("TIPO_PETICIONARIO_EU", this.appMessageSource.getMessage("label.tipoPeticionario", null, localEu));
		modelMap.put("FECHA_INI_EU", this.appMessageSource.getMessage("label.fechaIni", null, localEu));
		modelMap.put("FECHA_FIN_EU", this.appMessageSource.getMessage("label.fechaFin", null, localEu));
		modelMap.put("SOLIC_PRESUPUESTO_EU",
				this.appMessageSource.getMessage("label.solicitarPresupuesto", null, localEu));
		modelMap.put("FECHA_INI_INTER_EU",
				this.appMessageSource.getMessage("label.fechaHoraIniInterpretacion", null, localEu));
		modelMap.put("FECHA_FIN_INTER_EU",
				this.appMessageSource.getMessage("label.fechaHoraFinInterpretacion", null, localEu));
		modelMap.put("CONTACTO_EU", this.appMessageSource.getMessage("label.contactoJusti", null, localEu));
		modelMap.put("TELEFONO_EU", this.appMessageSource.getMessage("label.telefono", null, localEu));
		modelMap.put("DOCUMENTACION_EU", this.appMessageSource.getMessage("label.documentacion", null, localEu));
		modelMap.put("PALABRAS_SOL_EU", this.appMessageSource.getMessage("label.documento.numPalabras", null, localEu));
		modelMap.put("GARRANTZIA_EU", this.appMessageSource.getMessage("label.relevancia", null, localEu));
		modelMap.put("EXP_FACTURABLE_EU", this.appMessageSource.getMessage("label.entidadFacturable", null, localEu));
		modelMap.put("URGENTE_EU", this.appMessageSource.getMessage("label.urgente", null, localEu));
		modelMap.put("REQUIERE_PRESUPUESTO_EU",
				this.appMessageSource.getMessage("label.requierePresupuesto", null, localEu));
		modelMap.put("FECHA_FIN_IZO_EU", this.appMessageSource.getMessage("label.fechaHoraFinalIzo", null, localEu));
		modelMap.put("FECHA_FIN_SOL_EU", this.appMessageSource.getMessage("label.fechaFinalSolic", null, localEu));
		modelMap.put("FECHA_FIN_PROPUESTA_EU", this.appMessageSource.getMessage("label.fechaFinProp", null, localEu));
		modelMap.put("CLASE_DOCU_EU", this.appMessageSource.getMessage("label.claseDocumento", null, localEu));
		modelMap.put("TIPO_DOCU_EU", this.appMessageSource.getMessage("label.tipo", null, localEu));
		modelMap.put("NUM_PALABRAS_IZO_EU",
				this.appMessageSource.getMessage("label.documento.numPalabrasIZO", null, localEu));
		modelMap.put("NUM_PALABRAS_SOLIC_EU",
				this.appMessageSource.getMessage("label.documento.numPalabrasSolicitante", null, localEu));
		modelMap.put("SI_EU", this.appMessageSource.getMessage("gestion.si", null, localEu));
		modelMap.put("NO_EU", this.appMessageSource.getMessage("gestion.no", null, localEu));

	}

	/********************************************************************************************
	 * CONSULTA EXPEDIENTES - FIN
	 ********************************************************************************************/

}
