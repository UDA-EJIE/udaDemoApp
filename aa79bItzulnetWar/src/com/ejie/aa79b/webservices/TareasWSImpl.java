package com.ejie.aa79b.webservices;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.DaoConstants;
import com.ejie.aa79b.common.JasperUtils;
import com.ejie.aa79b.mail.MailService;
import com.ejie.aa79b.model.Auditoria;
import com.ejie.aa79b.model.BitacoraExpediente;
import com.ejie.aa79b.model.BitacoraSolicitante;
import com.ejie.aa79b.model.DatosContacto;
import com.ejie.aa79b.model.DatosSalidaWS;
import com.ejie.aa79b.model.DatosTareaTrados;
import com.ejie.aa79b.model.DocPresupuestoTraduccion;
import com.ejie.aa79b.model.DocumentoTarea;
import com.ejie.aa79b.model.DocumentoTareaAdjunto;
import com.ejie.aa79b.model.DocumentosCorreccion;
import com.ejie.aa79b.model.DocumentosExpediente;
import com.ejie.aa79b.model.EntradaDatosSolicitud;
import com.ejie.aa79b.model.EstadosExpediente;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.FasesExpediente;
import com.ejie.aa79b.model.FicheroDocExp;
import com.ejie.aa79b.model.FicheroWS;
import com.ejie.aa79b.model.FormatosFichero;
import com.ejie.aa79b.model.MotivosAnulacion;
import com.ejie.aa79b.model.ObservacionesExpediente;
import com.ejie.aa79b.model.ParametrosEmail;
import com.ejie.aa79b.model.SubsanacionExpediente;
import com.ejie.aa79b.model.Tareas;
import com.ejie.aa79b.model.TiposRevision;
import com.ejie.aa79b.model.TiposTarea;
import com.ejie.aa79b.model.enums.AccionBitacoraEnum;
import com.ejie.aa79b.model.enums.ActivoEnum;
import com.ejie.aa79b.model.enums.AuditoriaTipoSeccionEnum;
import com.ejie.aa79b.model.enums.EstadoAceptacionTareaEnum;
import com.ejie.aa79b.model.enums.EstadoEjecucionTareaEnum;
import com.ejie.aa79b.model.enums.EstadoEnvioEmailEnum;
import com.ejie.aa79b.model.enums.EstadoExpedienteEnum;
import com.ejie.aa79b.model.enums.EstadoRequerimientoEnum;
import com.ejie.aa79b.model.enums.FaseExpedienteEnum;
import com.ejie.aa79b.model.enums.FormatoInformeEnum;
import com.ejie.aa79b.model.enums.MotivosAnulacionEnum;
import com.ejie.aa79b.model.enums.TipoAvisoEnum;
import com.ejie.aa79b.model.enums.TipoExpedienteEnum;
import com.ejie.aa79b.model.enums.TipoFicheroAdjuntoEnum;
import com.ejie.aa79b.model.enums.TipoRequerimientoEnum;
import com.ejie.aa79b.model.enums.TipoTareaGestionAsociadaEnum;
import com.ejie.aa79b.model.excel.ExcelModel;
import com.ejie.aa79b.model.pdf.PdfModel;
import com.ejie.aa79b.model.webservices.Aa79bAuditoria;
import com.ejie.aa79b.model.webservices.Aa79bAuditoriaCampoSeccionExpediente;
import com.ejie.aa79b.model.webservices.Aa79bAuditoriaDocumentoSeccionExpediente;
import com.ejie.aa79b.model.webservices.Aa79bAuditoriaSeccionExpediente;
import com.ejie.aa79b.model.webservices.Aa79bConsultaTareasReport;
import com.ejie.aa79b.model.webservices.Aa79bDatosContacto;
import com.ejie.aa79b.model.webservices.Aa79bDatosTareaTrados;
import com.ejie.aa79b.model.webservices.Aa79bDocPresupuestoTraduccionInfoExped;
import com.ejie.aa79b.model.webservices.Aa79bDocumentoTarea;
import com.ejie.aa79b.model.webservices.Aa79bEntradaAuditoria;
import com.ejie.aa79b.model.webservices.Aa79bEntradaCamposSeccion;
import com.ejie.aa79b.model.webservices.Aa79bEntradaConfirmarAuditoria;
import com.ejie.aa79b.model.webservices.Aa79bEntradaConsultaTarea;
import com.ejie.aa79b.model.webservices.Aa79bEntradaDetalleAuditoria;
import com.ejie.aa79b.model.webservices.Aa79bEntradaDocumentosTarea;
import com.ejie.aa79b.model.webservices.Aa79bEntradaEjecutarTarea;
import com.ejie.aa79b.model.webservices.Aa79bEntradaEliminarFichero;
import com.ejie.aa79b.model.webservices.Aa79bEntradaTarea;
import com.ejie.aa79b.model.webservices.Aa79bEntradaValidarDocumentosTarea;
import com.ejie.aa79b.model.webservices.Aa79bFicheroDocExp;
import com.ejie.aa79b.model.webservices.Aa79bInformeTareasProveedor;
import com.ejie.aa79b.model.webservices.Aa79bJaxbList;
import com.ejie.aa79b.model.webservices.Aa79bListLoteCombo;
import com.ejie.aa79b.model.webservices.Aa79bNotasTarea;
import com.ejie.aa79b.model.webservices.Aa79bSalidaConfirmarAuditoria;
import com.ejie.aa79b.model.webservices.Aa79bSalidaConsultaTarea;
import com.ejie.aa79b.model.webservices.Aa79bSalidaDatosDescargaDocumentos;
import com.ejie.aa79b.model.webservices.Aa79bSalidaDetalleAuditoria;
import com.ejie.aa79b.model.webservices.Aa79bSalidaDocPresupuestoTraduccion;
import com.ejie.aa79b.model.webservices.Aa79bSalidaEliminarFichero;
import com.ejie.aa79b.model.webservices.Aa79bSalidaOrigenNoConformidad;
import com.ejie.aa79b.model.webservices.Aa79bSalidaTarea;
import com.ejie.aa79b.model.webservices.Aa79bSalidaValidarDocumentosTarea;
import com.ejie.aa79b.model.webservices.Aa79bTablaWebService;
import com.ejie.aa79b.model.webservices.Aa79bTareasGestionInterpretacion;
import com.ejie.aa79b.model.webservices.Aa79bTiposTarea;
import com.ejie.aa79b.model.webservices.EntradaAdjuntarFicheroWS;
import com.ejie.aa79b.model.webservices.SalidaAdjuntarFicheroWs;
import com.ejie.aa79b.service.Aa79bSolicitudWsService;
import com.ejie.aa79b.service.Aa79bTareaWsService;
import com.ejie.aa79b.service.AnulacionesService;
import com.ejie.aa79b.service.AuditoriaService;
import com.ejie.aa79b.service.BitacoraExpedienteService;
import com.ejie.aa79b.service.DatosContactoService;
import com.ejie.aa79b.service.DocumentosCorreccionService;
import com.ejie.aa79b.service.DocumentosExpedienteService;
import com.ejie.aa79b.service.DocumentosGeneralService;
import com.ejie.aa79b.service.ExcelReportService;
import com.ejie.aa79b.service.ExpedienteService;
import com.ejie.aa79b.service.FormatosFicheroService;
import com.ejie.aa79b.service.MotivosAnulacionService;
import com.ejie.aa79b.service.ObservacionesExpedienteService;
import com.ejie.aa79b.service.OidsAuxiliarService;
import com.ejie.aa79b.service.PdfReportService;
import com.ejie.aa79b.service.SubsanacionService;
import com.ejie.aa79b.service.TareasGeneralService;
import com.ejie.aa79b.service.TareasService;
import com.ejie.aa79b.service.TiposTareaService;
import com.ejie.aa79b.utils.FinalizarTareaUtils;
import com.ejie.aa79b.utils.TareasServiceUtils;
import com.ejie.aa79b.utils.Utils;
import com.ejie.aa79b.utils.WSUtils;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author aresua
 */
@WebService(serviceName = "aa79bTareasWS", portName = "aa79bTareasWSPort", targetNamespace = "http://com.ejie.aa79b.webservices")
@SOAPBinding(parameterStyle = ParameterStyle.WRAPPED)
@HandlerChain(file = "server-handlers.xml")
public class TareasWSImpl extends SpringBeanAutowiringSupport {

	private static final Logger LOGGER = LoggerFactory.getLogger(TareasWSImpl.class);

	@Autowired
	private DocumentosGeneralService documentosGeneralService;
	@Autowired
	private PIDService pidServiceImpl;
	@Autowired()
	private AuditoriaService auditoriaService;
	@Autowired()
	private TareasGeneralService tareasGeneralService;
	@Autowired()
	private Aa79bTareaWsService aa79bTareaWsService;
	@Autowired()
	private Aa79bSolicitudWsService aa79bSolicitudWsService;
	@Autowired()
	private ReloadableResourceBundleMessageSource appMessageSource;
	@Autowired()
	private ApplicationContext ctx;
	@Autowired()
	private Properties appConfiguration;
	@Autowired()
	private TareasService tareasService;
	@Autowired()
	private SubsanacionService subsanacionService;
	@Autowired()
	private BitacoraExpedienteService bitacoraExpedienteService;
	@Autowired()
	private ExpedienteService expedienteService;
	@Autowired()
	private MotivosAnulacionService motivosAnulacionService;
	@Autowired()
	private DatosContactoService datosContactoService;
	@Autowired()
	private MailService mailService;
	@Autowired
	private TiposTareaService tiposTareaService;
	@Autowired()
	private DocumentosExpedienteService documentosExpedienteService;
	@Autowired()
	private AnulacionesService anulacionesService;
	@Autowired()
	private OidsAuxiliarService oidsAuxiliarService;
	@Autowired()
	private ObservacionesExpedienteService observacionesExpedienteService;
	@Autowired
	private ExcelReportService aa79bExcelReportService;
	@Autowired
	private PdfReportService aa79bPdfReportService;
	@Autowired
	private FormatosFicheroService formatosFicheroService;
	@Autowired
	private DocumentosCorreccionService documentosCorreccionService;


	public static final String MENSAJE_GESTOR_SIN_ACCESO = "mensaje.gestorSinAcceso";
	public static final String MENSAJE_SIN_PERMISOS_DE_USUARIO = "mensaje.gestorSinPermisos";
	public static final String MENSAJE_EXPEDIENTE_NO_ELIMINADO = "mensaje.expedienteNoEliminado";
	public static final String MENSAJE_SUBSANACION_REALIZADA = "mensaje.subsanacionRealizada";
	private static final String MENSAJE_PROVEEDOR_SIN_ACCESO_A_TAREA = "mensaje.proveedorSinAccesoATarea";
	private static final String ERROR_OBTENIENDO_INFORMACION = "mensaje.errorObteniendoInformacionTarea";
	private static final String MENSAJE_FECHA_LIM_REQ_EXPIRADA = "mensaje.fechaLimReqPasada";
	private static final String TABLA_REQUEST = "\ntablaRequest: ";

	private static final String RUTA_PIF_AA06A = "rutaPifAa06a";

	/**
	 *
	 * @return String
	 */
	@WebMethod(operationName = "pruebaWS")
	public String prueba() {
		return "HolaMundo";
	}

	/**
	 * @author aresua
	 *
	 * @param bean
	 *            EntradaFechaFinalizacion
	 * @return SalidaFechaFinalizacion
	 */
	@WebMethod(operationName = "finalizarTareaWS")
	public DatosSalidaWS finalizarTareaWS(Aa79bEntradaTarea bean) {
		TareasWSImpl.LOGGER.debug("finalizarTareaWS - INICIO");

		TareasWSImpl.LOGGER.debug("finalizarTareaWS - Se parsea la tarea de entrada");
		Locale locale;
		if (StringUtils.isNotBlank(bean.getIdioma())) {
			locale = new Locale(bean.getIdioma());
		} else {
			locale = new Locale("eu");
		}
		DatosSalidaWS datosSalida = new DatosSalidaWS();
		try {
			Tareas tarea = WSUtils.parsearAFormatoAa79b(bean);

			TareasWSImpl.LOGGER.debug("finalizarTareaWS - Se llama al ejecutar tarea");
			this.tareasGeneralService.finalizarTarea(tarea, null);

			TareasWSImpl.LOGGER.debug("finalizarTareaWS - Se parsea la tarea de salida");

			datosSalida.setEstado(Constants.ESTADO_WS_OK);
			TareasWSImpl.LOGGER.debug("finalizarTareaWS - FIN");
		} catch (Exception e) {
			datosSalida.setEstado(Constants.ESTADO_WS_ERROR);
			datosSalida.setDescripcion(this.appMessageSource.getMessage("mensaje error", null, locale));
			TareasWSImpl.LOGGER.debug("finalizarTareaWS - ERROR WSUtils.parsearAFormatoAa79b. " + e);
		}

		return datosSalida;
	}

	/**
	 *
	 * @param dni
	 *            String
	 * @return Aa79bListLoteCombo
	 */
	@WebMethod(operationName = "obtenerBuscadorProveedorWS")
	public Aa79bListLoteCombo obtenerBuscadorProveedorWS(String dni, String idioma) {
		TareasWSImpl.LOGGER.debug("obtenerBuscadorProveedorWS - INICIO");
		TareasWSImpl.LOGGER.debug("obtenerBuscadorProveedorWS - Se llama a la funcion para obtener los lotes");
		Aa79bListLoteCombo listalotes = new Aa79bListLoteCombo();
		Locale locale;
		if (StringUtils.isNotBlank(idioma)) {
			locale = new Locale(idioma);
		} else {
			locale = new Locale("eu");
		}
		try {
			listalotes = this.aa79bTareaWsService.obtenerBuscadorProveedor(dni);
			listalotes.setEstado(Constants.ESTADO_WS_OK);
		} catch (Exception e) {
			listalotes.setEstado(Constants.ESTADO_WS_ERROR);
			listalotes.setDescripcion(this.appMessageSource.getMessage(e.getMessage(), null, locale));
			TareasWSImpl.LOGGER
					.debug("finalizarTareaWS - ERROR this.aa79bTareaWsService.obtenerBuscadorProveedor. " + e);
		}

		TareasWSImpl.LOGGER.debug("obtenerBuscadorProveedorWS - FIN");

		return listalotes;
	}

	/**
	 *
	 * @param bean
	 *            Aa79bEntradaConsultaTarea
	 * @param aa79bTablaWebService
	 *            Aa79bTablaWebService<Aa79bSalidaConsultaTarea>
	 * @param salida
	 *            Aa79bSalidaConsultaTarea
	 * @return Aa79bTablaWebService<Aa79bSalidaConsultaTarea>
	 */
	@WebMethod(operationName = "consultaTareasProveedorWS")
	public Aa79bTablaWebService<Aa79bSalidaConsultaTarea> consultaTareasProveedorWS(Aa79bEntradaConsultaTarea bean,
			Aa79bTablaWebService<Aa79bSalidaConsultaTarea> aa79bTablaWebService, Aa79bSalidaConsultaTarea salida) {
		TareasWSImpl.LOGGER.debug(
				"WS_METHOD consultaTareasProveedorWS - BEGIN\nfilter: " + bean + TABLA_REQUEST + aa79bTablaWebService);
		Locale locale;
		if (StringUtils.isNotBlank(bean.getIdioma())) {
			locale = new Locale(bean.getIdioma());
		} else {
			locale = new Locale("eu");
		}
		Aa79bTablaWebService<Aa79bSalidaConsultaTarea> tabla = new Aa79bTablaWebService<Aa79bSalidaConsultaTarea>();
		try {

			JQGridRequestDto jqGridRequestDto = null;
			if (aa79bTablaWebService != null && Utils.isValidAa79bTablaWebService(aa79bTablaWebService)) {
				jqGridRequestDto = new JQGridRequestDto(aa79bTablaWebService.getRows(), aa79bTablaWebService.getPage(),
						aa79bTablaWebService.getSidx(), aa79bTablaWebService.getSord());

			}

			final JQGridResponseDto<Aa79bSalidaConsultaTarea> jqGrid = this.aa79bTareaWsService
					.consultaTareasProveedor(bean, jqGridRequestDto, false);
			tabla.setEstado(Constants.ESTADO_WS_OK);
			@SuppressWarnings(value = "unchecked")
			final Aa79bJaxbList<Aa79bSalidaConsultaTarea> lista = new Aa79bJaxbList<Aa79bSalidaConsultaTarea>(
					(List<Aa79bSalidaConsultaTarea>) jqGrid.getRows());
			tabla.setLista(lista);
			tabla.setRecordNum(Long.valueOf(jqGrid.getRecords()));

			TareasWSImpl.LOGGER.info("WS_METHOD consultaTareasProveedorWS END\nreturn" + tabla);

		} catch (Exception e) {
			TareasWSImpl.LOGGER.error("[POST - subm]: consultaTareasProveedorWS ", e);
			tabla.setSidx("-1");
			tabla.setSord(e.getMessage());
			tabla.setEstado(Constants.ESTADO_WS_ERROR);
			tabla.setDescripcion(this.appMessageSource.getMessage("mensaje error", null, locale));
		}

		TareasWSImpl.LOGGER.debug("consultaTareasProveedorWS - FIN");
		return tabla;
	}

	/**
	 *
	 * @param bean
	 *            Aa79bEntradaConsultaTarea
	 * @param aa79bTablaWebService
	 *            Aa79bTablaWebService<Aa79bSalidaConsultaTarea>
	 * @param salida
	 *            Aa79bSalidaConsultaTarea
	 * @return FicheroWS
	 */
	@WebMethod(operationName = "informeTareasProveedorWS")
	public FicheroWS informeTareasProveedorWS(Aa79bInformeTareasProveedor aa79bInformeTareasProveedor) {
		TareasWSImpl.LOGGER.debug("WS_METHOD informeTareasProveedorWS - BEGIN\nfilter: " + aa79bInformeTareasProveedor);
		Locale locale;
		Aa79bEntradaConsultaTarea bean = aa79bInformeTareasProveedor.getAa79bEntradaConsultaTarea();
		if (StringUtils.isNotBlank(bean.getIdioma())) {
			locale = new Locale(bean.getIdioma());
		} else {
			locale = new Locale(Constants.LANG_EUSKERA);
		}
		final String expedienteLabel = "titulo.tarea";
		final String label = this.appMessageSource.getMessage(expedienteLabel, new Object[] {}, locale);
		JQGridRequestDto jqGridRequestDto = new JQGridRequestDto(null, null, aa79bInformeTareasProveedor.getSidx(),
				aa79bInformeTareasProveedor.getSord());

		final JQGridResponseDto<Aa79bConsultaTareasReport> jqGrid = this.aa79bTareaWsService
				.consultaTareasInformeProveedor(bean, jqGridRequestDto, false);
		byte[] bytesInforme = null;
		FicheroWS ficheroBean = new FicheroWS();
		if (FormatoInformeEnum.EXCEL.getValue() == aa79bInformeTareasProveedor.getDocumentTypeLongValue()) {
			bytesInforme = generarInformeExcelTareasProveedor(aa79bInformeTareasProveedor, locale, expedienteLabel,
					label, jqGrid, ficheroBean);
		} else if (FormatoInformeEnum.PDF.getValue() == aa79bInformeTareasProveedor.getDocumentTypeLongValue()) {
			bytesInforme = generarInformePdfTareasProveedor(aa79bInformeTareasProveedor, locale, expedienteLabel, label,
					jqGrid, ficheroBean);
		}

		try {
			ficheroBean.setRutaPif(this.pidServiceImpl.subidaPif(ficheroBean.getNombre() + ficheroBean.getExtension(),
					bytesInforme, this.appConfiguration.getProperty(RUTA_PIF_AA06A), false));
		} catch (UnsupportedEncodingException e) {
			TareasWSImpl.LOGGER.info("UnsupportedEncodingException - subidaPif: " + e);
			ficheroBean.setEstado(Constants.ESTADO_WS_ERROR);
			ficheroBean.setDescripcion("ERROR subidaPif");

		} catch (Exception e) {
			TareasWSImpl.LOGGER.error(" informeTareasProveedorWS - subidaFichero Error: ", e);
			ficheroBean.setEstado(Constants.ESTADO_WS_ERROR);
			ficheroBean.setDescripcion(this.appMessageSource.getMessage("mensaje.fichero.errorPIF", null, locale));
		}

		TareasWSImpl.LOGGER.debug("WS_METHOD informeTareasProveedorWS - FIN: " + bean);
		return ficheroBean;
	}

	private byte[] generarInformeExcelTareasProveedor(Aa79bInformeTareasProveedor aa79bInformeTareasProveedor,
			Locale locale, final String expedienteLabel, final String label,
			final JQGridResponseDto<Aa79bConsultaTareasReport> jqGrid, FicheroWS ficheroBean) {
		ExcelModel excelModel = this.aa79bExcelReportService.getExcelModelJson(aa79bInformeTareasProveedor.getCriterios(),
				aa79bInformeTareasProveedor.getColumns(), jqGrid.getRows(), locale, expedienteLabel, label);
		byte[] bytesInforme = null;
		ficheroBean.setEstado(Constants.ESTADO_WS_OK);
		try {
			bytesInforme = this.aa79bExcelReportService.generarExcel(FormatoInformeEnum.EXCEL.getTemplate(),
					excelModel);
		} catch (Exception e1) {
			TareasWSImpl.LOGGER.info("ERROR informeTareasProveedorWS, Error: " + e1);
			ficheroBean.setEstado(Constants.ESTADO_WS_ERROR);
			ficheroBean.setDescripcion("ERROR informeExcelTareasProveedorWS");
		}

		ficheroBean.setNombre(this.appMessageSource.getMessage("titulo.informeTareasProveedor", null, locale));
		ficheroBean.setExtension(FormatoInformeEnum.EXCEL.getExtension());
		ficheroBean.setContentType(FormatoInformeEnum.EXCEL.getContentType());
		ficheroBean.setTamano((long) bytesInforme.length);
		return bytesInforme;
	}

	private byte[] generarInformePdfTareasProveedor(Aa79bInformeTareasProveedor aa79bInformeTareasProveedor,
			Locale locale, String expedienteLabel, String label, JQGridResponseDto<Aa79bConsultaTareasReport> jqGrid,
			FicheroWS ficheroBean) {
		PdfModel pdfModel = this.aa79bPdfReportService.getPdfModelJson(aa79bInformeTareasProveedor.getCriterios(),
				aa79bInformeTareasProveedor.getColumns(), jqGrid.getRows(), locale, label);
		byte[] bytesInforme = null;
		ficheroBean.setEstado(Constants.ESTADO_WS_OK);
		try {
			bytesInforme = this.aa79bPdfReportService.generarPdf(FormatoInformeEnum.PDF.getTemplate(), pdfModel, locale);
		} catch (Exception e1) {
			TareasWSImpl.LOGGER.info("ERROR informePdfTareasProveedorWS, Error: " + e1);
			ficheroBean.setEstado(Constants.ESTADO_WS_ERROR);
			ficheroBean.setDescripcion("ERROR informePdfTareasProveedorWS");
		}

		ficheroBean.setNombre(this.appMessageSource.getMessage("titulo.informeTareasProveedor", null, locale));
		ficheroBean.setExtension(FormatoInformeEnum.PDF.getExtension());
		ficheroBean.setContentType(FormatoInformeEnum.PDF.getContentType());
		ficheroBean.setTamano((long) bytesInforme.length);
		return bytesInforme;
	}


	@WebMethod(operationName = "obtenerDocumentosTraduccionWS")
	public Aa79bTablaWebService<Aa79bDocumentoTarea> obtenerDocumentosTraduccionWS(Aa79bEntradaEjecutarTarea bean,
			Aa79bTablaWebService<Aa79bDocumentoTarea> aa79bTablaWebService, Aa79bDocumentoTarea salida) {
		TareasWSImpl.LOGGER.debug("WS_METHOD obtenerDocumentosTraduccionWS - BEGIN\nfilter: " + bean + TABLA_REQUEST
				+ aa79bTablaWebService);

		Locale locale;
		if (StringUtils.isNotBlank(bean.getIdioma())) {
			locale = new Locale(bean.getIdioma());
		} else {
			locale = new Locale("eu");
		}

		Aa79bTablaWebService<Aa79bDocumentoTarea> tabla = new Aa79bTablaWebService<Aa79bDocumentoTarea>();
		try {

			JQGridRequestDto jqGridRequestDto = null;
			if (aa79bTablaWebService != null && Utils.isValidAa79bTablaWebService(aa79bTablaWebService)) {
				jqGridRequestDto = new JQGridRequestDto(aa79bTablaWebService.getRows(), aa79bTablaWebService.getPage(),
						aa79bTablaWebService.getSidx(), aa79bTablaWebService.getSord());

			}
			// comprobar acceso tarea de usuario
			Boolean accesoTarea = this.aa79bTareaWsService.getAccesoTarea(bean);
			if (accesoTarea) {
				final JQGridResponseDto<Aa79bDocumentoTarea> jqGrid = this.aa79bTareaWsService
						.obtenerDocumentosTraduccion(bean, jqGridRequestDto, false);
				tabla.setEstado(Constants.ESTADO_WS_OK);
				@SuppressWarnings(value = "unchecked")
				final Aa79bJaxbList<Aa79bDocumentoTarea> lista = new Aa79bJaxbList<Aa79bDocumentoTarea>(
						(List<Aa79bDocumentoTarea>) jqGrid.getRows());
				tabla.setLista(lista);
				tabla.setRecordNum(Long.valueOf(jqGrid.getRecords()));

				TareasWSImpl.LOGGER.info("WS_METHOD obtenerDocumentosTraduccionWS END\nreturn" + tabla);
			} else {
				tabla.setEstado(Constants.ESTADO_WS_ERROR);
				tabla.setDescripcion(
						this.appMessageSource.getMessage(MENSAJE_PROVEEDOR_SIN_ACCESO_A_TAREA, null, locale));
			}

		} catch (Exception e) {
			TareasWSImpl.LOGGER.error("[POST - subm]: obtenerDocumentosTraduccionWS ", e);
			tabla.setSidx("-1");
			tabla.setSord(e.getMessage());
			tabla.setEstado(Constants.ESTADO_WS_ERROR);
			tabla.setDescripcion(this.appMessageSource.getMessage(ERROR_OBTENIENDO_INFORMACION, null, locale));
		}

		TareasWSImpl.LOGGER.debug("obtenerDocumentosTraduccionWS - FIN");
		return tabla;
	}

	@WebMethod(operationName = "obtenerDocumentosXliffWS")
	public Aa79bTablaWebService<Aa79bDocumentoTarea> obtenerDocumentosXliffWS(Aa79bEntradaEjecutarTarea bean,
			Aa79bTablaWebService<Aa79bDocumentoTarea> aa79bTablaWebService, Aa79bDocumentoTarea salida) {
		TareasWSImpl.LOGGER.debug(
				"WS_METHOD obtenerDocumentosXliffWS - BEGIN\nfilter: " + bean + TABLA_REQUEST + aa79bTablaWebService);

		Locale locale;
		if (StringUtils.isNotBlank(bean.getIdioma())) {
			locale = new Locale(bean.getIdioma());
		} else {
			locale = new Locale("eu");
		}

		Aa79bTablaWebService<Aa79bDocumentoTarea> tabla = new Aa79bTablaWebService<Aa79bDocumentoTarea>();
		try {

			JQGridRequestDto jqGridRequestDto = null;
			if (aa79bTablaWebService != null && Utils.isValidAa79bTablaWebService(aa79bTablaWebService)) {
				jqGridRequestDto = new JQGridRequestDto(aa79bTablaWebService.getRows(), aa79bTablaWebService.getPage(),
						aa79bTablaWebService.getSidx(), aa79bTablaWebService.getSord());

			}
			// comprobar acceso tarea de usuario
			Boolean accesoTarea = this.aa79bTareaWsService.getAccesoTarea(bean);
			if (accesoTarea) {
				final JQGridResponseDto<Aa79bDocumentoTarea> jqGrid = this.aa79bTareaWsService
						.obtenerDocumentosXliffWS(bean, jqGridRequestDto, false);
				tabla.setEstado(Constants.ESTADO_WS_OK);
				@SuppressWarnings(value = "unchecked")
				final Aa79bJaxbList<Aa79bDocumentoTarea> lista = new Aa79bJaxbList<Aa79bDocumentoTarea>(
						(List<Aa79bDocumentoTarea>) jqGrid.getRows());
				tabla.setLista(lista);
				tabla.setRecordNum(Long.valueOf(jqGrid.getRecords()));

				TareasWSImpl.LOGGER.info("WS_METHOD obtenerDocumentosXliffWS END\nreturn" + tabla);
			} else {
				tabla.setEstado(Constants.ESTADO_WS_ERROR);
				tabla.setDescripcion(
						this.appMessageSource.getMessage(MENSAJE_PROVEEDOR_SIN_ACCESO_A_TAREA, null, locale));
			}

		} catch (Exception e) {
			TareasWSImpl.LOGGER.error("[POST - subm]: obtenerDocumentosXliffWS ", e);
			tabla.setSidx("-1");
			tabla.setSord(e.getMessage());
			tabla.setEstado(Constants.ESTADO_WS_ERROR);
			tabla.setDescripcion(this.appMessageSource.getMessage(ERROR_OBTENIENDO_INFORMACION, null, locale));
		}

		TareasWSImpl.LOGGER.debug("obtenerDocumentosXliffWS - FIN");
		return tabla;
	}

	@WebMethod(operationName = "adjuntarFicheroWS")
	public SalidaAdjuntarFicheroWs adjuntarFicheroWS(EntradaAdjuntarFicheroWS bean) {
		TareasWSImpl.LOGGER.debug("WS_METHOD adjuntarFicheroWS - BEGIN: " + bean);
		Locale locale;
		if (StringUtils.isNotBlank(bean.getIdioma())) {
			locale = new Locale(bean.getIdioma());
		} else {
			locale = new Locale("eu");
		}
		SalidaAdjuntarFicheroWs salidaAdjuntarFicheroWs = new SalidaAdjuntarFicheroWs();

		// comprobar acceso a tarea
		Aa79bEntradaEjecutarTarea aa79bEntradaEjecutarTarea = new Aa79bEntradaEjecutarTarea();
		aa79bEntradaEjecutarTarea.setDni(bean.getDni());
		aa79bEntradaEjecutarTarea.setIdTarea(bean.getIdTarea());
		try {
			Boolean accesoTarea = this.aa79bTareaWsService.getAccesoTarea(aa79bEntradaEjecutarTarea);
			if (accesoTarea) {
				// daba problemas al subirlo directamente. En registrar
				// solicitud se
				// hace asi
				DocumentoTarea filterDocumentoTarea = new DocumentoTarea();
				filterDocumentoTarea.setIdTarea(bean.getIdTarea());
				DocumentosExpediente docOriginal = new DocumentosExpediente();
				docOriginal.setIdDoc(bean.getDocumentoTareaAdjunto().getIdDocOriginal());
				filterDocumentoTarea.setDocumentoOriginal(docOriginal);

				DocumentoTarea docTarea = this.obtenerRegistroTarea(bean.getTipoFichero(), filterDocumentoTarea);

				bean.getDocumentoTareaAdjunto().setIdTarea(bean.getIdTarea());

				bean.getDocumentoTareaAdjunto().setOid(bean.getDocumentoTareaAdjunto().getOid());
				if (TipoFicheroAdjuntoEnum.TIPO_TRADUCCION_REVISION.getValue() == bean.getTipoFichero()) {
					bean.getDocumentoTareaAdjunto()
							.setTitulo(docTarea.getDocumentoOriginal().getTitulo() + "_" + bean.getIdiomaDest());
				} else if (TipoFicheroAdjuntoEnum.TIPO_XLIFF.getValue() == bean.getTipoFichero()) {
					bean.getDocumentoTareaAdjunto().setTitulo(bean.getDocumentoTareaAdjunto().getNombre());
				}

				// necesitamos el id fichero para insertarlo en la tabla
				// correspondiente 88
				bean.setDocumentoTareaAdjunto(this.documentosGeneralService.add(bean.getDocumentoTareaAdjunto()));
				// guardar en tablas intermedias
				this.documentosGeneralService.addTipoFichero(bean.getDocumentoTareaAdjunto(), bean.getTipoFichero());

				salidaAdjuntarFicheroWs.setIdFichero(bean.getDocumentoTareaAdjunto().getIdFichero());

				// eliminar oid de fichero guardado de la A0
				this.oidsAuxiliarService.remove(bean.getDocumentoTareaAdjunto().getOid());
				// si habia un fichero en la tarea lo eliminamos
				if (TipoFicheroAdjuntoEnum.TIPO_TRADUCCION_REVISION.getValue() == bean.getTipoFichero()
						&& docTarea.getDocumentoAdjunto() != null
						&& docTarea.getDocumentoAdjunto().getIdFichero() != null) {
					// eliminamos el fichero de la tabla 88
					this.documentosGeneralService.remove(docTarea.getDocumentoAdjunto());
					// anyadimos el oid a la tabla A0
					this.oidsAuxiliarService.add(docTarea.getDocumentoAdjunto().getOid());
				}

				salidaAdjuntarFicheroWs.setEstado(Constants.ESTADO_WS_OK);
			} else {
				salidaAdjuntarFicheroWs.setEstado(Constants.ESTADO_WS_ERROR);
				salidaAdjuntarFicheroWs.setDescripcion(
						this.appMessageSource.getMessage(MENSAJE_PROVEEDOR_SIN_ACCESO_A_TAREA, null, locale));
			}
		} catch (Exception e) {
			TareasWSImpl.LOGGER.error("WS_METHOD: adjuntarFicheroWS ", e);
			salidaAdjuntarFicheroWs.setEstado(Constants.ESTADO_WS_ERROR);
			salidaAdjuntarFicheroWs
					.setDescripcion(this.appMessageSource.getMessage(ERROR_OBTENIENDO_INFORMACION, null, locale));
		}
		TareasWSImpl.LOGGER.debug("WS_METHOD adjuntarFicheroWS - FIN: " + bean);
		return salidaAdjuntarFicheroWs;
	}

	private DocumentoTarea obtenerRegistroTarea(Integer tipoFichero, DocumentoTarea filterDocumentoTarea) {
		DocumentoTarea docTarea = null;
		switch (tipoFichero) {
		case 1:
			docTarea = this.tareasGeneralService.findRegTraduccionRevision(filterDocumentoTarea);
			break;
		case 2:
			docTarea = this.tareasGeneralService.findRegJustificante(filterDocumentoTarea);
			break;
		default:
			break;
		}
		return docTarea;
	}

	@WebMethod(operationName = "obtenerDocumentoPresupuestoInterpretacionWS")
	public FicheroWS obtenerDocumentoPresupuestoInterpretacionWS(EntradaDatosSolicitud bean) {

		ModelMap modelMap = new ModelMap();

		Tareas laTarea = this.tareasService.getDatosDocumentoPptoInterpretacion(bean.getAnyo(), bean.getNumExp(),
				bean.getIdRequerimiento());

		List<Tareas> lDatos = new ArrayList<Tareas>();
		lDatos.add(laTarea);

		Locale locale;
		if (StringUtils.isNotBlank(bean.getIdioma())) {
			locale = new Locale(bean.getIdioma());
		} else {
			locale = new Locale("eu");
		}

		this.tareasService.getmodelMapDocumentoInterpretacion(locale, modelMap, lDatos);

		JasperPrint jasperPrint = null;
		byte[] bytesInforme = null;
		try {
			Resource mainReport;

			mainReport = this.ctx.getResource(Constants.RUTA_JASPER_DOC_PRESUPUESTO_INTERPRETACION);

			JasperReport jasperReport = new JasperUtils().loadReport(mainReport);
			jasperPrint = JasperFillManager.fillReport(jasperReport, modelMap, new JRBeanCollectionDataSource(lDatos));
			bytesInforme = JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (JRException e) {
			TareasWSImpl.LOGGER.error("Error pdf documentoPresupuestoInterpretacion " + e);
		}

		FicheroWS ficheroBean = new FicheroWS();

		ficheroBean.setNombre(this.appMessageSource.getMessage("label.docPptoInterpNombreFich", null, locale) + "_"
				+ bean.getAnyo() + "_" + bean.getNumExp());
		ficheroBean.setExtension("." + Constants.FILE_PDF);
		ficheroBean.setContentType(Constants.CONTENT_TYPE_PDF);

		try {
			ficheroBean.setRutaPif(this.pidServiceImpl.subidaPif(ficheroBean.getNombre() + ficheroBean.getExtension(),
					bytesInforme, this.appConfiguration.getProperty(RUTA_PIF_AA06A), false));
		} catch (UnsupportedEncodingException e) {
			TareasWSImpl.LOGGER.info("Error UnsupportedEncodingException: " + e);
			ficheroBean.setEstado(Constants.ESTADO_WS_ERROR);
			ficheroBean.setDescripcion("ERROR UnsupportedEncodingException");

		} catch (Exception e) {
			TareasWSImpl.LOGGER.error("pruebapid: JOSE - subidaFichero Error: ", e);
			ficheroBean.setEstado(Constants.ESTADO_WS_ERROR);
			ficheroBean.setDescripcion(this.appMessageSource.getMessage("mensaje.fichero.errorPIF", null, locale));
		}

		return ficheroBean;
	}

	@WebMethod(operationName = "obtenerDocumentoPresupuestoTraduccionWS")
	public FicheroWS obtenerDocumentoPresupuestoTraduccionWS(EntradaDatosSolicitud bean) {

		ModelMap modelMap = new ModelMap();

		DocPresupuestoTraduccion docPresupuestoTraduccion = this.tareasService
				.getDatosDocumentoPptoTraduccion(bean.getAnyo(), bean.getNumExp(), bean.getIdRequerimiento());

		List<DocPresupuestoTraduccion> lDatos = new ArrayList<DocPresupuestoTraduccion>();

		lDatos.add(docPresupuestoTraduccion);

		Locale locale;
		if (StringUtils.isNotBlank(bean.getIdioma())) {
			locale = new Locale(bean.getIdioma());
		} else {
			locale = new Locale("eu");
		}

		this.tareasService.getmodelMapDocumentoTraduccion(locale, modelMap, lDatos);

		JasperPrint jasperPrint = null;
		byte[] bytesInforme = null;
		try {
			Resource mainReport;

			mainReport = this.ctx.getResource(Constants.RUTA_JASPER_DOC_PRESUPUESTO_TRADUCCION);

			JasperReport jasperReport = new JasperUtils().loadReport(mainReport);
			jasperPrint = JasperFillManager.fillReport(jasperReport, modelMap, new JRBeanCollectionDataSource(lDatos));
			bytesInforme = JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (JRException e) {
			TareasWSImpl.LOGGER.error("Error pdf documentoPresupuestoTraduccion " + e);
		}

		FicheroWS ficheroBean = new FicheroWS();

		ficheroBean.setNombre(this.appMessageSource.getMessage("label.docPptoTraducNombreFich", null, locale) + "_"
				+ bean.getAnyo() + "_" + bean.getNumExp());
		ficheroBean.setExtension("." + Constants.FILE_PDF);
		ficheroBean.setContentType(Constants.CONTENT_TYPE_PDF);

		try {
			ficheroBean.setRutaPif(this.pidServiceImpl.subidaPif(ficheroBean.getNombre() + ficheroBean.getExtension(),
					bytesInforme, this.appConfiguration.getProperty(RUTA_PIF_AA06A), false));
		} catch (UnsupportedEncodingException e) {
			TareasWSImpl.LOGGER.info("Error UnsupportedEncodingException: " + e);
			ficheroBean.setEstado(Constants.ESTADO_WS_ERROR);
			ficheroBean.setDescripcion("ERROR UnsupportedEncodingException");

		} catch (Exception e) {
			TareasWSImpl.LOGGER.error("pruebapid: JOSE - subidaFichero Error: ", e);
			ficheroBean.setEstado(Constants.ESTADO_WS_ERROR);
			ficheroBean.setDescripcion(this.appMessageSource.getMessage("mensaje.fichero.errorPIF", null, locale));
		}

		return ficheroBean;
	}

	@WebMethod(operationName = "eliminarFicheroWS")
	public Aa79bSalidaEliminarFichero eliminarFicheroWS(Aa79bEntradaEliminarFichero bean) {
		TareasWSImpl.LOGGER.debug("WS_METHOD eliminarFicheroWS - BEGIN: " + bean);
		// comprobar acceso a tarea
		Aa79bSalidaEliminarFichero salidaEliminarFichero = new Aa79bSalidaEliminarFichero();
		Locale locale;
		if (StringUtils.isNotBlank(bean.getIdioma())) {
			locale = new Locale(bean.getIdioma());
		} else {
			locale = new Locale("eu");
		}
		Aa79bEntradaEjecutarTarea aa79bEntradaEjecutarTarea = new Aa79bEntradaEjecutarTarea();
		aa79bEntradaEjecutarTarea.setDni(bean.getDni());
		aa79bEntradaEjecutarTarea.setIdTarea(bean.getIdTarea());
		try {
			Boolean accesoTarea = this.aa79bTareaWsService.getAccesoTarea(aa79bEntradaEjecutarTarea);
			if (accesoTarea) {
				// obtener el oid del documento a eliminar
				DocumentoTareaAdjunto documento = new DocumentoTareaAdjunto();
				documento.setIdFichero(bean.getIdDoc());
				DocumentoTareaAdjunto docAEliminar = this.documentosGeneralService.find(documento);

				Integer iEliminado = 0;
				if (bean.getTipoFichero() == TipoFicheroAdjuntoEnum.TIPO_XLIFF.getValue()) {
					iEliminado = this.documentosGeneralService.removeTipoFichero(bean);
				}
				// anyadimos a la tabla A0 el fichero a eliminar
				this.oidsAuxiliarService.add(docAEliminar.getOid());
				// eliminar de la tabla 88
				this.documentosGeneralService.remove(docAEliminar);

				salidaEliminarFichero.setEliminado(iEliminado > 0);

				salidaEliminarFichero.setEstado(Constants.ESTADO_WS_OK);
			} else {
				salidaEliminarFichero.setEstado(Constants.ESTADO_WS_ERROR);
				salidaEliminarFichero.setDescripcion(
						this.appMessageSource.getMessage(MENSAJE_PROVEEDOR_SIN_ACCESO_A_TAREA, null, locale));
			}
		} catch (Exception e) {
			TareasWSImpl.LOGGER.error("WS_METHOD: eliminarFicheroWS ", e);
			salidaEliminarFichero.setEstado(Constants.ESTADO_WS_ERROR);
			salidaEliminarFichero
					.setDescripcion(this.appMessageSource.getMessage(ERROR_OBTENIENDO_INFORMACION, null, locale));
		}

		TareasWSImpl.LOGGER.debug("WS_METHOD eliminarFicheroWS - FIN: " + bean);
		return salidaEliminarFichero;
	}

	@WebMethod(operationName = "obtenerDocumentosRevisionWS")
	public Aa79bTablaWebService<Aa79bDocumentoTarea> obtenerDocumentosRevisionWS(Aa79bEntradaEjecutarTarea bean,
			Aa79bTablaWebService<Aa79bDocumentoTarea> aa79bTablaWebService, Aa79bDocumentoTarea salida) {
		TareasWSImpl.LOGGER.debug("WS_METHOD obtenerDocumentosRevisionWS - BEGIN\nfilter: " + bean + TABLA_REQUEST
				+ aa79bTablaWebService);

		Locale locale;
		if (StringUtils.isNotBlank(bean.getIdioma())) {
			locale = new Locale(bean.getIdioma());
		} else {
			locale = new Locale("eu");
		}

		Aa79bTablaWebService<Aa79bDocumentoTarea> tabla = new Aa79bTablaWebService<Aa79bDocumentoTarea>();
		try {

			JQGridRequestDto jqGridRequestDto = null;
			if (aa79bTablaWebService != null && Utils.isValidAa79bTablaWebService(aa79bTablaWebService)) {
				jqGridRequestDto = new JQGridRequestDto(aa79bTablaWebService.getRows(), aa79bTablaWebService.getPage(),
						aa79bTablaWebService.getSidx(), aa79bTablaWebService.getSord());

			}
			// comprobar acceso tarea de usuario
			Boolean accesoTarea = this.aa79bTareaWsService.getAccesoTarea(bean);
			if (accesoTarea) {
				final JQGridResponseDto<Aa79bDocumentoTarea> jqGrid = this.aa79bTareaWsService
						.obtenerDocumentosRevision(bean, jqGridRequestDto, false);
				tabla.setEstado(Constants.ESTADO_WS_OK);
				@SuppressWarnings(value = "unchecked")
				final Aa79bJaxbList<Aa79bDocumentoTarea> lista = new Aa79bJaxbList<Aa79bDocumentoTarea>(
						(List<Aa79bDocumentoTarea>) jqGrid.getRows());
				tabla.setLista(lista);
				tabla.setRecordNum(Long.valueOf(jqGrid.getRecords()));

				TareasWSImpl.LOGGER.info("WS_METHOD obtenerDocumentosRevisionWS END\nreturn" + tabla);
			} else {
				tabla.setEstado(Constants.ESTADO_WS_ERROR);
				tabla.setDescripcion(
						this.appMessageSource.getMessage(MENSAJE_PROVEEDOR_SIN_ACCESO_A_TAREA, null, locale));
			}

		} catch (Exception e) {
			TareasWSImpl.LOGGER.error("[POST - subm]: obtenerDocumentosRevisionWS ", e);
			tabla.setSidx("-1");
			tabla.setSord(e.getMessage());
			tabla.setEstado(Constants.ESTADO_WS_ERROR);
			tabla.setDescripcion(this.appMessageSource.getMessage(ERROR_OBTENIENDO_INFORMACION, null, locale));
		}

		TareasWSImpl.LOGGER.debug("obtenerDocumentosRevisionWS - FIN");
		return tabla;
	}

	@WebMethod(operationName = "obtenerOrigenNoConformidadWS")
	public Aa79bSalidaOrigenNoConformidad obtenerOrigenNoConformidadWS(Aa79bEntradaEjecutarTarea bean) {
		TareasWSImpl.LOGGER.debug("WS_METHOD obtenerOrigenNoConformidadWS - BEGIN\nfilter: " + bean);

		Locale locale;
		if (StringUtils.isNotBlank(bean.getIdioma())) {
			locale = new Locale(bean.getIdioma());
		} else {
			locale = new Locale("eu");
		}
		Aa79bSalidaOrigenNoConformidad aa79bSalidaOrigenNoConformidad = new Aa79bSalidaOrigenNoConformidad();
		try {

			// comprobar acceso tarea de usuario
			Boolean accesoTarea = this.aa79bTareaWsService.getAccesoTarea(bean);
			if (accesoTarea) {
				aa79bSalidaOrigenNoConformidad = this.aa79bTareaWsService.obtenerOrigenNoConformidad(bean);
				aa79bSalidaOrigenNoConformidad.setEstado(Constants.ESTADO_WS_OK);

				TareasWSImpl.LOGGER.info("WS_METHOD obtenerOrigenNoConformidadWS END\nreturn");
			} else {
				aa79bSalidaOrigenNoConformidad.setEstado(Constants.ESTADO_WS_ERROR);
				aa79bSalidaOrigenNoConformidad.setDescripcion(
						this.appMessageSource.getMessage(MENSAJE_PROVEEDOR_SIN_ACCESO_A_TAREA, null, locale));
			}

		} catch (Exception e) {
			TareasWSImpl.LOGGER.error("[POST - subm]: obtenerOrigenNoConformidadWS ", e);
			aa79bSalidaOrigenNoConformidad.setEstado(Constants.ESTADO_WS_ERROR);
			aa79bSalidaOrigenNoConformidad
					.setDescripcion(this.appMessageSource.getMessage(ERROR_OBTENIENDO_INFORMACION, null, locale));
		}

		TareasWSImpl.LOGGER.debug("obtenerOrigenNoConformidadWS - FIN");
		return aa79bSalidaOrigenNoConformidad;
	}

	@WebMethod(operationName = "obtenerDocumentosTraduccionNoConformidadWS")
	public Aa79bTablaWebService<Aa79bDocumentoTarea> obtenerDocumentosTraduccionNoConformidadWS(
			Aa79bEntradaEjecutarTarea bean, Aa79bTablaWebService<Aa79bDocumentoTarea> aa79bTablaWebService,
			Aa79bDocumentoTarea salida) {
		TareasWSImpl.LOGGER.debug("WS_METHOD obtenerDocumentosTraduccionNoConformidadWS - BEGIN\nfilter: " + bean
				+ TABLA_REQUEST + aa79bTablaWebService);

		Locale locale;
		if (StringUtils.isNotBlank(bean.getIdioma())) {
			locale = new Locale(bean.getIdioma());
		} else {
			locale = new Locale("eu");
		}

		Aa79bTablaWebService<Aa79bDocumentoTarea> tabla = new Aa79bTablaWebService<Aa79bDocumentoTarea>();
		try {

			JQGridRequestDto jqGridRequestDto = null;
			if (aa79bTablaWebService != null && Utils.isValidAa79bTablaWebService(aa79bTablaWebService)) {
				jqGridRequestDto = new JQGridRequestDto(aa79bTablaWebService.getRows(), aa79bTablaWebService.getPage(),
						aa79bTablaWebService.getSidx(), aa79bTablaWebService.getSord());

			}
			// comprobar acceso tarea de usuario
			Boolean accesoTarea = this.aa79bTareaWsService.getAccesoTarea(bean);
			if (accesoTarea) {
				final JQGridResponseDto<Aa79bDocumentoTarea> jqGrid = this.aa79bTareaWsService
						.obtenerDocumentosTraduccionNoConformidad(bean, jqGridRequestDto, false);
				tabla.setEstado(Constants.ESTADO_WS_OK);
				@SuppressWarnings(value = "unchecked")
				final Aa79bJaxbList<Aa79bDocumentoTarea> lista = new Aa79bJaxbList<Aa79bDocumentoTarea>(
						(List<Aa79bDocumentoTarea>) jqGrid.getRows());
				tabla.setLista(lista);
				tabla.setRecordNum(Long.valueOf(jqGrid.getRecords()));

				TareasWSImpl.LOGGER.info("WS_METHOD obtenerDocumentosTraduccionNoConformidadWS END\nreturn" + tabla);
			} else {
				tabla.setEstado(Constants.ESTADO_WS_ERROR);
				tabla.setDescripcion(
						this.appMessageSource.getMessage(MENSAJE_PROVEEDOR_SIN_ACCESO_A_TAREA, null, locale));
			}

		} catch (Exception e) {
			TareasWSImpl.LOGGER.error("[POST - subm]: obtenerDocumentosTraduccionNoConformidadWS ", e);
			tabla.setSidx("-1");
			tabla.setSord(e.getMessage());
			tabla.setEstado(Constants.ESTADO_WS_ERROR);
			tabla.setDescripcion(this.appMessageSource.getMessage(ERROR_OBTENIENDO_INFORMACION, null, locale));
		}

		TareasWSImpl.LOGGER.debug("obtenerDocumentosTraduccionNoConformidadWS - FIN");
		return tabla;
	}

	@WebMethod(operationName = "obtenerDocumentosRevisionNoConformidadWS")
	public Aa79bTablaWebService<Aa79bDocumentoTarea> obtenerDocumentosRevisionNoConformidadWS(
			Aa79bEntradaEjecutarTarea bean, Aa79bTablaWebService<Aa79bDocumentoTarea> aa79bTablaWebService,
			Aa79bDocumentoTarea salida) {
		TareasWSImpl.LOGGER.debug("WS_METHOD obtenerDocumentosRevisionNoConformidadWS - BEGIN\nfilter: " + bean
				+ TABLA_REQUEST + aa79bTablaWebService);

		Locale locale;
		if (StringUtils.isNotBlank(bean.getIdioma())) {
			locale = new Locale(bean.getIdioma());
		} else {
			locale = new Locale("eu");
		}

		Aa79bTablaWebService<Aa79bDocumentoTarea> tabla = new Aa79bTablaWebService<Aa79bDocumentoTarea>();
		try {

			JQGridRequestDto jqGridRequestDto = null;
			if (aa79bTablaWebService != null && Utils.isValidAa79bTablaWebService(aa79bTablaWebService)) {
				jqGridRequestDto = new JQGridRequestDto(aa79bTablaWebService.getRows(), aa79bTablaWebService.getPage(),
						aa79bTablaWebService.getSidx(), aa79bTablaWebService.getSord());

			}
			// comprobar acceso tarea de usuario
			Boolean accesoTarea = this.aa79bTareaWsService.getAccesoTarea(bean);
			if (accesoTarea) {
				final JQGridResponseDto<Aa79bDocumentoTarea> jqGrid = this.aa79bTareaWsService
						.obtenerDocumentosRevisionNoConformidad(bean, jqGridRequestDto, false);
				tabla.setEstado(Constants.ESTADO_WS_OK);
				@SuppressWarnings(value = "unchecked")
				final Aa79bJaxbList<Aa79bDocumentoTarea> lista = new Aa79bJaxbList<Aa79bDocumentoTarea>(
						(List<Aa79bDocumentoTarea>) jqGrid.getRows());
				tabla.setLista(lista);
				tabla.setRecordNum(Long.valueOf(jqGrid.getRecords()));

				TareasWSImpl.LOGGER.info("WS_METHOD obtenerDocumentosRevisionNoConformidadWS END\nreturn" + tabla);
			} else {
				tabla.setEstado(Constants.ESTADO_WS_ERROR);
				tabla.setDescripcion(
						this.appMessageSource.getMessage(MENSAJE_PROVEEDOR_SIN_ACCESO_A_TAREA, null, locale));
			}
		} catch (Exception e) {
			TareasWSImpl.LOGGER.error("[POST - subm]: obtenerDocumentosRevisionNoConformidadWS ", e);
			tabla.setSidx("-1");
			tabla.setSord(e.getMessage());
			tabla.setEstado(Constants.ESTADO_WS_ERROR);
			tabla.setDescripcion(this.appMessageSource.getMessage(ERROR_OBTENIENDO_INFORMACION, null, locale));
		}

		TareasWSImpl.LOGGER.debug("obtenerDocumentosRevisionNoConformidadWS - FIN");
		return tabla;
	}

	@WebMethod(operationName = "validarDocumentosTareaFinalizarWS")
	public Aa79bSalidaValidarDocumentosTarea validarDocumentosTareaFinalizarWS(
			Aa79bEntradaValidarDocumentosTarea bean) {
		TareasWSImpl.LOGGER.debug("WS_METHOD validarDocumentosTareaFinalizarWS - BEGIN" + bean);
		Aa79bEntradaEjecutarTarea accesoModel = new Aa79bEntradaEjecutarTarea();
		accesoModel.setDni(bean.getDni());
		accesoModel.setIdTarea(bean.getIdTarea());
		Locale locale;
		if (StringUtils.isNotBlank(bean.getIdioma())) {
			locale = new Locale(bean.getIdioma());
		} else {
			locale = new Locale("eu");
		}
		Aa79bSalidaValidarDocumentosTarea aa79bSalidaValidarDocumentosTarea = new Aa79bSalidaValidarDocumentosTarea();
		try {

			// comprobar acceso tarea de usuario
			Boolean accesoTarea = this.aa79bTareaWsService.getAccesoTarea(accesoModel);
			if (accesoTarea) {
				aa79bSalidaValidarDocumentosTarea
						.setValido(this.aa79bTareaWsService.validarDocumentosTareaFinalizar(bean));
				aa79bSalidaValidarDocumentosTarea.setEstado(Constants.ESTADO_WS_OK);

				TareasWSImpl.LOGGER.info("WS_METHOD validarDocumentosTareaFinalizarWS END\nreturn" + bean);
			} else {
				aa79bSalidaValidarDocumentosTarea.setEstado(Constants.ESTADO_WS_ERROR);
				aa79bSalidaValidarDocumentosTarea.setDescripcion(
						this.appMessageSource.getMessage(MENSAJE_PROVEEDOR_SIN_ACCESO_A_TAREA, null, locale));
			}

		} catch (Exception e) {
			TareasWSImpl.LOGGER.error("[POST - subm]: validarDocumentosTareaFinalizarWS ", e);
			aa79bSalidaValidarDocumentosTarea.setEstado(Constants.ESTADO_WS_ERROR);
			aa79bSalidaValidarDocumentosTarea
					.setDescripcion(this.appMessageSource.getMessage(ERROR_OBTENIENDO_INFORMACION, null, locale));
		}

		TareasWSImpl.LOGGER.debug("validarDocumentosTareaFinalizarWS - FIN");
		return aa79bSalidaValidarDocumentosTarea;
	}

	@WebMethod(operationName = "aceptacionRechazoRequerimientoWS")
	public Aa79bSalidaTarea aceptacionRechazoRequerimientoWS(EntradaDatosSolicitud bean) {
		TareasWSImpl.LOGGER.info("aceptacionRechazoRequerimientoWS");
		int resultEmail = 0;
		Aa79bSalidaTarea aa79bSalidaTarea = new Aa79bSalidaTarea();
		Locale locale = new Locale(bean.getIdioma());
		Expediente exp = new Expediente();

		DatosContacto datosContacto = new DatosContacto();
		datosContacto.setDni031(bean.getDni());
		datosContacto = this.datosContactoService.findDatosContacto(datosContacto);

		DatosContacto datosContactoAsignador = new DatosContacto();
		if (!Constants.UNO.equals(bean.getAccionRequerimiento())) {

			BitacoraExpediente bExp = new BitacoraExpediente();
			bExp.setAnyo(bean.getAnyo());
			bExp.setNumExp(bean.getNumExp());

			EstadosExpediente estado = new EstadosExpediente();
			estado.setId(Long.valueOf(EstadoExpedienteEnum.EN_CURSO.getValue()));
			bExp.setEstadoExp(estado);

			FasesExpediente fases = new FasesExpediente();

			fases.setId(Long.valueOf(FaseExpedienteEnum.PDTE_TRAM_CLIENTE.getValue()));

			bExp.setFaseExp(fases);
			bExp.setDatoAdic(this.appMessageSource.getMessage("comun.rechazado", null, locale));

			updateRequerimiento(bean, locale);

			asignarInfoAdicional(bean, locale, bExp);

			SubsanacionExpediente subsanacionExpediente = new SubsanacionExpediente();
			subsanacionExpediente.setId(bean.getIdRequerimiento());
			bExp.setSubsanacionExp(subsanacionExpediente);

			this.bitacoraExpedienteService.add(bExp);

			exp.setAnyo(bean.getAnyo());
			exp.setNumExp(bean.getNumExp());
			exp = this.expedienteService.find(exp);
			exp.setBitacoraExpediente(bExp);
			this.expedienteService.modificarEstado(exp);

			if (isAceptacionPresupuestoFechaFin(bean)) {

				BitacoraSolicitante bitacoraSolicitante = new BitacoraSolicitante();
				asignarIdAccionBitacora(bean, bitacoraSolicitante);
				bitacoraSolicitante.setAnyo(bean.getAnyo());
				bitacoraSolicitante.setNumExp(bean.getNumExp());
				bitacoraSolicitante.setUsuario(datosContacto.getNombreApellidos());
				this.asignarAccionBitacora(bean, locale, bitacoraSolicitante);
				this.aa79bSolicitudWsService.addBitacoraSolicitante(bitacoraSolicitante);

				this.anulacionesService.anularExpAutomaticamente(bean.getAnyo(), bean.getNumExp(), bean.getMotivoRechazo());

				resultEmail = this.enviarEmailAceptRechazoReq(datosContacto, exp, locale,
						TipoAvisoEnum.ANULADO_EXP.getValue(), bean);
			}else if(TipoRequerimientoEnum.ACEPTACION_FECHA_FIN.getValue() == bean.getTipoRequerimiento() ){
				//si se rechaza la nueva fecha propuesta, poner el expediente en fase pendiente de ejecucion
				fases.setId(Long.valueOf(FaseExpedienteEnum.PDTE_EJECT_TAREAS.getValue()));
				bExp.setFaseExp(fases);
				bExp.setSubsanacionExp(null);
				bExp.setDatoAdic("");
				this.bitacoraExpedienteService.add(bExp);
				//modificar estado
				exp.setBitacoraExpediente(bExp);
				this.expedienteService.modificarEstado(exp);
//				//anyadir bitacora solicitante
				BitacoraSolicitante bitacoraSolicitante = new BitacoraSolicitante();
				asignarIdAccionBitacora(bean, bitacoraSolicitante);
				bitacoraSolicitante.setAnyo(bean.getAnyo());
				bitacoraSolicitante.setNumExp(bean.getNumExp());
				bitacoraSolicitante.setUsuario(datosContacto.getNombreApellidos());
				this.asignarAccionBitacora(bean, locale, bitacoraSolicitante);
				this.aa79bSolicitudWsService.addBitacoraSolicitante(bitacoraSolicitante);

			}

		} else {

			BitacoraExpediente bExp = new BitacoraExpediente();
			bExp.setAnyo(bean.getAnyo());
			bExp.setNumExp(bean.getNumExp());

			EstadosExpediente estado = new EstadosExpediente();
			estado.setId(Long.valueOf(EstadoExpedienteEnum.EN_CURSO.getValue()));
			bExp.setEstadoExp(estado);

			FasesExpediente fases = new FasesExpediente();
			fases.setId(Long.valueOf(FaseExpedienteEnum.PDTE_TRAM_CLIENTE.getValue()));
			bExp.setFaseExp(fases);
			bExp.setDatoAdic(this.appMessageSource.getMessage("comun.aceptado", null, locale));

			updateRequerimiento(bean, locale);

			asignarInfoAdicional(bean, locale, bExp);

			SubsanacionExpediente subsanacionExpediente = new SubsanacionExpediente();
			subsanacionExpediente.setId(bean.getIdRequerimiento());
			bExp.setSubsanacionExp(subsanacionExpediente);

			this.bitacoraExpedienteService.add(bExp);

			exp.setAnyo(bean.getAnyo());
			exp.setNumExp(bean.getNumExp());
			exp = this.expedienteService.find(exp);
			exp.setBitacoraExpediente(bExp);
			this.expedienteService.modificarEstado(exp);

			//cogemos el asignado de trados si lo hay.
			Tareas tareaTrados = new Tareas();
			tareaTrados.setAnyo(bean.getAnyo());
			tareaTrados.setNumExp(bean.getNumExp());
			tareaTrados.getTipoTarea().setId015(Long.valueOf(TipoTareaGestionAsociadaEnum.PROYECTO_TRADOS.getValue()));
			tareaTrados.setOrden(2);//La de trados siempre es orden 0, le ponemos orden 1 pq la consulta filtra por un orden menor al indicado.
			String recursoAsig = this.tareasService.getRecursoAsignado(tareaTrados);
			if (StringUtils.isNotBlank(recursoAsig)) {
				datosContactoAsignador.setDni031(recursoAsig);
			}else {
				datosContactoAsignador.setDni031(exp.getAsignador().getDni());
			}
			datosContactoAsignador = this.datosContactoService.findDatosContacto(datosContactoAsignador);

			fases.setId(Long.valueOf(FaseExpedienteEnum.PDTE_EJECT_TAREAS.getValue()));
			bExp.setFaseExp(fases);
			bExp.setIdMotivoRechazo(null);
			bExp.setDatoAdic("");
			bExp.setInfoAdic("");
			subsanacionExpediente = new SubsanacionExpediente();
			bExp.setSubsanacionExp(subsanacionExpediente);
			exp.setBitacoraExpediente(bExp);

			this.bitacoraExpedienteService.add(bExp);
			this.expedienteService.modificarEstado(exp);

			BitacoraSolicitante bitacoraSolicitante = new BitacoraSolicitante();
			asignarIdAccionBitacora(bean, bitacoraSolicitante);
			bitacoraSolicitante.setAnyo(bean.getAnyo());
			bitacoraSolicitante.setNumExp(bean.getNumExp());
			bitacoraSolicitante.setUsuario(datosContacto.getNombreApellidos());
			this.asignarAccionBitacora(bean, locale, bitacoraSolicitante);
			this.aa79bSolicitudWsService.addBitacoraSolicitante(bitacoraSolicitante);

			if (isAceptacionFechaFin(bean)) {
				// actualizar fecha final izo 53 con fecha entrega 90
				this.tareasService.actualizarFechaFinalIzo(bean);
			}
			// se lanza de nuevo la ejecución de tarea

			if (new Integer(TipoRequerimientoEnum.ACEPTACION_FECHA_FIN.getValue())
					.equals(bean.getTipoRequerimiento())) {
				BigDecimal elIdTarea = this.subsanacionService.getIdTareaConRequerimiento(bean.getIdRequerimiento());
				Tareas laTarea = new Tareas();
				laTarea.setIdTarea(elIdTarea);

				FinalizarTareaUtils finalizarTareaUtils = new FinalizarTareaUtils();
				finalizarTareaUtils.procesoBatchEnvioMails(laTarea, null);
			}

		}

		resultEmail = this.enviarEmailAceptRechazoReq(datosContactoAsignador, exp, locale,
				TipoAvisoEnum.REQUERIMIENTO_REALIZADO.getValue(), bean);
		aa79bSalidaTarea.setEstado(Constants.ESTADO_WS_OK);
		aa79bSalidaTarea.setDescripcion(String.valueOf(resultEmail));
		return aa79bSalidaTarea;
	}

	private boolean isAceptacionFechaFin(EntradaDatosSolicitud bean) {
		return TipoRequerimientoEnum.ACEPTACION_FECHA_FIN.getValue() == bean.getTipoRequerimiento()
				|| TipoRequerimientoEnum.ACEPTACION_PRESUPUESTO_FECHA_FIN.getValue() == bean.getTipoRequerimiento();
	}

	/**
	 * @param bean
	 * @param bitacoraSolicitante
	 */
	private void asignarIdAccionBitacora(EntradaDatosSolicitud bean, BitacoraSolicitante bitacoraSolicitante) {
		if (Constants.UNO.equals(bean.getAccionRequerimiento())) {
			asignarIdAccionBitacoraAcpt(bean, bitacoraSolicitante);
		} else {
			asignarIdAccionBitacoraRechazo(bean, bitacoraSolicitante);
		}
	}

	/**
	 * @param bean
	 * @param bitacoraSolicitante
	 */
	private void asignarIdAccionBitacoraRechazo(EntradaDatosSolicitud bean, BitacoraSolicitante bitacoraSolicitante) {
		if (new Long(TipoRequerimientoEnum.ACEPTACION_PRESUPUESTO.getValue()).equals(bean.getIdRequerimiento())) {
			bitacoraSolicitante.setIdAccionBitacora(new Long(AccionBitacoraEnum.RECHAZ_PRESUP.getValue()));
		} else if (new Long(TipoRequerimientoEnum.ACEPTACION_PRESUPUESTO_FECHA_FIN.getValue())
				.equals(bean.getIdRequerimiento())) {
			bitacoraSolicitante
					.setIdAccionBitacora(new Long(AccionBitacoraEnum.RECHAZ_PRESUP_FECHA_PROP_IZO.getValue()));
		} else if (new Long(TipoRequerimientoEnum.ACEPTACION_FECHA_FIN.getValue()).equals(bean.getIdRequerimiento())) {
			bitacoraSolicitante.setIdAccionBitacora(new Long(AccionBitacoraEnum.RECHAZ_FECHA_PROP_IZO.getValue()));
		}
	}

	/**
	 * @param bean
	 * @param bitacoraSolicitante
	 */
	private void asignarIdAccionBitacoraAcpt(EntradaDatosSolicitud bean, BitacoraSolicitante bitacoraSolicitante) {
		if (new Long(TipoRequerimientoEnum.ACEPTACION_PRESUPUESTO.getValue()).equals(bean.getIdRequerimiento())) {
			bitacoraSolicitante.setIdAccionBitacora(new Long(AccionBitacoraEnum.ACEPT_PRESUP.getValue()));
		} else if (new Long(TipoRequerimientoEnum.ACEPTACION_PRESUPUESTO_FECHA_FIN.getValue())
				.equals(bean.getIdRequerimiento())) {
			bitacoraSolicitante
					.setIdAccionBitacora(new Long(AccionBitacoraEnum.ACEPT_PRESUP_FECHA_PROP_IZO.getValue()));
		} else if (new Long(TipoRequerimientoEnum.ACEPTACION_FECHA_FIN.getValue()).equals(bean.getIdRequerimiento())) {
			bitacoraSolicitante.setIdAccionBitacora(new Long(AccionBitacoraEnum.ACEPT_FECHA_PROP_IZO.getValue()));
		}
	}

	/**
	 * @param bean
	 * @param tipoRequerimientoLabel
	 */
	private String asignarLabelTipoRequerimiento(EntradaDatosSolicitud bean) {
		if (new Long(TipoRequerimientoEnum.ACEPTACION_PRESUPUESTO.getValue()).equals(bean.getTipoRequerimiento())) {
			return TipoRequerimientoEnum.ACEPTACION_PRESUPUESTO.getLabel();
		} else if (new Long(TipoRequerimientoEnum.ACEPTACION_PRESUPUESTO_FECHA_FIN.getValue())
				.equals(bean.getIdRequerimiento())) {
			return TipoRequerimientoEnum.ACEPTACION_PRESUPUESTO_FECHA_FIN.getLabel();
		} else if (new Long(TipoRequerimientoEnum.ACEPTACION_FECHA_FIN.getValue())
				.equals(bean.getTipoRequerimiento())) {
			return TipoRequerimientoEnum.ACEPTACION_FECHA_FIN.getLabel();
		} else {
			return Constants.EMPTY;
		}
	}

	private int enviarEmailAuditoriaConfirmada(Aa79bAuditoria auditoria, Locale locale, int tipoAviso) {
		List<String> listaDestinatarios = new ArrayList<String>();
		int rst = EstadoEnvioEmailEnum.OK.getValue();

		List<DatosContacto> lDatosContacto = this.datosContactoService
				.getMailRecursoTareaAuditoria(auditoria.getId());
		if (lDatosContacto != null && !lDatosContacto.isEmpty()) {
			for (final DatosContacto contacto : lDatosContacto) {
				listaDestinatarios.add(contacto.getEmail031());
			}
		}
		//Obtenemos el detalle de la auditoria

		Auditoria auditoriaAux = this.auditoriaService.getDatosBasicosAuditoria(auditoria.getId());
		TiposTarea tarea = this.tiposTareaService.getTipoTareaPorIdTarea(auditoriaAux.getIdTarea());
		// cuerpo de mensaje numexp - titulo - tarea
		if (TareasServiceUtils.isNotEmptyLstDestinatarios(listaDestinatarios)) {
			TareasWSImpl.LOGGER
					.info("entorno: " + this.appConfiguration.getProperty(Constants.APP_ENTORNO));

			ParametrosEmail parametrosEmail = new ParametrosEmail();
			Locale localeEu = new Locale(Constants.LANG_EUSKERA);
			Map<String, String> infoEu = new LinkedHashMap<String, String>();
			Locale localeEs = new Locale(Constants.LANG_CASTELLANO);
			Map<String, String> infoEs = new LinkedHashMap<String, String>();
			parametrosEmail.setAnyoNumExpediente(auditoria.getTarea().getAnyoNumExpConcatenado());
			infoEu.put(this.appMessageSource.getMessage(Constants.LABEL_ID_TAREA, null, localeEu),
					auditoriaAux.getIdTarea()+ Constants.ESPACIO + Constants.GUION + Constants.ESPACIO
					+ tarea.getDescEu015() + Constants.ESPACIO + Constants.ABRIR_PARENTESIS
					+ this.appMessageSource.getMessage(Constants.LABEL_NUM_EXP, null, localeEu)
					+ Constants.ESPACIO + auditoria.getTarea().getAnyoNumExpConcatenado() + Constants.CERRAR_PARENTESIS
					);
			infoEs.put(this.appMessageSource.getMessage(Constants.LABEL_ID_TAREA, null, localeEs),
					auditoriaAux.getIdTarea()+ Constants.ESPACIO + Constants.GUION + Constants.ESPACIO
					+ tarea.getDescEs015() + Constants.ESPACIO + Constants.ABRIR_PARENTESIS
					+ this.appMessageSource.getMessage(Constants.LABEL_NUM_EXP, null, localeEs)
					+ Constants.ESPACIO + auditoria.getTarea().getAnyoNumExpConcatenado() + Constants.CERRAR_PARENTESIS
			);

			parametrosEmail.setInfoEu(infoEu);
			parametrosEmail.setInfoEs(infoEs);
			try {
				this.mailService.sendMailWithParameters(tipoAviso,
						listaDestinatarios, parametrosEmail);
			} catch (Exception e) {
				TareasWSImpl.LOGGER.info("Error en el envío de email", e);
				rst = EstadoEnvioEmailEnum.ERROR.getValue();
			}
		} else {
			TareasWSImpl.LOGGER.info("No hay destinatarios", listaDestinatarios);
			rst = EstadoEnvioEmailEnum.SIN_DESTINATARIOS.getValue();
		}
		return rst;
	}

	/**
	 * @param datosContacto
	 * @param tipoAviso
	 */
	private int enviarEmailAceptRechazoReq(DatosContacto datosContacto, Expediente exp, Locale locale, int tipoAviso,
			EntradaDatosSolicitud bean) {
		final List<String> listaDestinatarios = new ArrayList<String>();
		int rst = 0;

		obtenerDestinatariosEmailRequerim(datosContacto, listaDestinatarios);

		if (TareasServiceUtils.isNotEmptyLstDestinatarios(listaDestinatarios)) {
			ParametrosEmail parametrosEmail = new ParametrosEmail();

			Locale localeEu = new Locale(Constants.LANG_EUSKERA);
			Map<String, String> infoEu = new LinkedHashMap<String, String>();
			Locale localeEs = new Locale(Constants.LANG_CASTELLANO);
			Map<String, String> infoEs = new LinkedHashMap<String, String>();

			infoEu.put(this.appMessageSource.getMessage("label.numExpMin", null, localeEu), exp.getAnyoNumExpConcatenado() + DaoConstants.SIGNO_GUION + exp.getTitulo());
			infoEs.put(this.appMessageSource.getMessage("label.numExpMin", null, localeEs), exp.getAnyoNumExpConcatenado() + DaoConstants.SIGNO_GUION + exp.getTitulo());

			if (asignarLabelTipoRequerimiento(bean) != "") {
				infoEu.put(this.appMessageSource.getMessage("label.tipoRequerimiento", null, localeEu), this.appMessageSource.getMessage(asignarLabelTipoRequerimiento(bean), null, localeEu) + DaoConstants.SIGNO_PUNTO);
				infoEs.put(this.appMessageSource.getMessage("label.tipoRequerimiento", null, localeEs), this.appMessageSource.getMessage(asignarLabelTipoRequerimiento(bean), null, localeEs) + DaoConstants.SIGNO_PUNTO);
			}

			if (!Constants.UNO.equals(bean.getAccionRequerimiento())) {
				infoEu.put(this.appMessageSource.getMessage("label.accion", null, localeEu), this.appMessageSource.getMessage(EstadoRequerimientoEnum.RECHAZADA.getLabel(), null, localeEu));
				infoEs.put(this.appMessageSource.getMessage("label.accion", null, localeEs), this.appMessageSource.getMessage(EstadoRequerimientoEnum.RECHAZADA.getLabel(), null, localeEs));
			} else {
				infoEu.put(this.appMessageSource.getMessage("label.accion", null, localeEu), this.appMessageSource.getMessage(EstadoRequerimientoEnum.ACEPTADA.getLabel(), null, localeEu));
				infoEs.put(this.appMessageSource.getMessage("label.accion", null, localeEs), this.appMessageSource.getMessage(EstadoRequerimientoEnum.ACEPTADA.getLabel(), null, localeEs));
			}


			if (tipoAviso == TipoAvisoEnum.ANULADO_EXP.getValue()) {

				MotivosAnulacion motivoFilter = new MotivosAnulacion();
				motivoFilter.setId012((long) MotivosAnulacionEnum.PRESUPUESTO_RECHAZADO.getValue());
				MotivosAnulacion motivo = this.motivosAnulacionService.find(motivoFilter);

				infoEu.put(this.appMessageSource.getMessage("label.motivoRechazo", null, localeEu), motivo.getDescEu012());
				infoEs.put(this.appMessageSource.getMessage("label.motivoRechazo", null, localeEs), motivo.getDescEs012());
			} else if (tipoAviso == TipoAvisoEnum.REQUERIMIENTO_REALIZADO.getValue() && !bean.getMotivoRechazo().isEmpty()) {
					infoEu.put(this.appMessageSource.getMessage("label.motivoRechazo", null, localeEu), bean.getMotivoRechazo());
					infoEs.put(this.appMessageSource.getMessage("label.motivoRechazo", null, localeEs), bean.getMotivoRechazo());
			}

			parametrosEmail.setInfoEu(infoEu);
			parametrosEmail.setInfoEs(infoEs);

			parametrosEmail.setAnyoNumExpediente(Utils.getNumExpedienteParameter(exp));

			try {
				this.mailService.sendMailWithParameters(tipoAviso, listaDestinatarios, parametrosEmail);
			} catch (Exception e) {
				TareasWSImpl.LOGGER.info("Error en el envío de email", e);
				rst = -2;
			}
		} else {
			rst = -1;
		}

		return rst;
	}

	/**
	 * @param datosContacto
	 * @param listaDestinatarios
	 */
	private void obtenerDestinatariosEmailRequerim(DatosContacto datosContacto, final List<String> listaDestinatarios) {
		if (this.appConfiguration.getProperty(Constants.APP_ENTORNO) != null) {
			if (Constants.LOCAL.equals(this.appConfiguration.getProperty(Constants.APP_ENTORNO))) {
				listaDestinatarios.add(Constants.DESTINATARIO_EMAIL_LOCAL);
			} else {
				if (datosContacto.getEmail031() != null) {
					listaDestinatarios.add(datosContacto.getEmail031());
				}
			}
		}
	}

	/**
	 * @param bean
	 * @param locale
	 * @param bExp
	 */
	private void asignarInfoAdicional(EntradaDatosSolicitud bean, Locale locale, BitacoraExpediente bExp) {
		if (TipoRequerimientoEnum.ACEPTACION_PRESUPUESTO.equals(bean.getTipoRequerimiento())) {
			bExp.setInfoAdic(this.appMessageSource.getMessage("label.aceptacionPresupuesto", null, locale));
		} else if (TipoRequerimientoEnum.ACEPTACION_PRESUPUESTO_FECHA_FIN.equals(bean.getTipoRequerimiento())) {
			bExp.setInfoAdic(this.appMessageSource.getMessage("label.aceptacionPresupuestoYFecha", null, locale));
		} else if (TipoRequerimientoEnum.ACEPTACION_FECHA_FIN.equals(bean.getTipoRequerimiento())) {
			bExp.setInfoAdic(this.appMessageSource.getMessage("label.aceptacionFechaEntrega", null, locale));
		}
	}

	private void asignarAccionBitacora(EntradaDatosSolicitud bean, Locale locale, BitacoraSolicitante bSol) {

		// OJO: Estamos usando idTipoTarea para enviar el tipoRequerimiento

		if (bean.getAccionRequerimiento() == Constants.UNO) {
			if (TipoRequerimientoEnum.ACEPTACION_PRESUPUESTO.getValue() == bean.getTipoRequerimiento()) {
				bSol.setIdAccionBitacora(new Long(AccionBitacoraEnum.ACEPT_PRESUP.getValue()));
			} else if (TipoRequerimientoEnum.ACEPTACION_PRESUPUESTO_FECHA_FIN.getValue() == bean
					.getTipoRequerimiento()) {
				bSol.setIdAccionBitacora(new Long(AccionBitacoraEnum.ACEPT_PRESUP_FECHA_PROP_IZO.getValue()));
			} else if (TipoRequerimientoEnum.ACEPTACION_FECHA_FIN.getValue() == bean.getTipoRequerimiento()) {
				bSol.setIdAccionBitacora(new Long(AccionBitacoraEnum.ACEPT_FECHA_PROP_IZO.getValue()));
			}
		} else {
			if (TipoRequerimientoEnum.ACEPTACION_PRESUPUESTO.getValue() == bean.getTipoRequerimiento()) {
				bSol.setIdAccionBitacora(new Long(AccionBitacoraEnum.RECHAZ_PRESUP.getValue()));
			} else if (TipoRequerimientoEnum.ACEPTACION_PRESUPUESTO_FECHA_FIN.getValue() == bean
					.getTipoRequerimiento()) {
				bSol.setIdAccionBitacora(new Long(AccionBitacoraEnum.RECHAZ_PRESUP_FECHA_PROP_IZO.getValue()));
			} else if (TipoRequerimientoEnum.ACEPTACION_FECHA_FIN.getValue() == bean.getTipoRequerimiento()) {
				bSol.setIdAccionBitacora(new Long(AccionBitacoraEnum.RECHAZ_FECHA_PROP_IZO.getValue()));
			}
		}
	}

	/**
	 * @param bean
	 * @param locale
	 * @param bExp
	 */
	private void updateRequerimiento(EntradaDatosSolicitud bean, Locale locale) {
		SubsanacionExpediente sExp = new SubsanacionExpediente();
		sExp.setId(bean.getIdRequerimiento());
		if (!Constants.UNO.equals(bean.getAccionRequerimiento())) {
			sExp.setEstado(EstadoRequerimientoEnum.RECHAZADA.getValue());
			sExp.setObservRechazo(bean.getMotivoRechazo());
		} else {
			sExp.setEstado(EstadoRequerimientoEnum.ACEPTADA.getValue());
		}

		this.subsanacionService.actualizarSubsanacionConIndSubs(sExp);
	}

	/**
	 * @param bean
	 * @return
	 */
	private boolean isAceptacionPresupuestoFechaFin(EntradaDatosSolicitud bean) {

		// OJO: Estamos usando idTipoTarea para enviar el tipoRequerimiento

		return TipoRequerimientoEnum.ACEPTACION_PRESUPUESTO.getValue() == bean.getTipoRequerimiento()
				|| TipoRequerimientoEnum.ACEPTACION_PRESUPUESTO_FECHA_FIN.getValue() == bean.getTipoRequerimiento();
	}

	@WebMethod(operationName = "obtenerDatosTipoTareaWS")
	public Aa79bSalidaTarea obtenerDatosTipoTareaWS(EntradaDatosSolicitud bean) {
		TareasWSImpl.LOGGER.info("obtenerDatosTipoTareaWS");
		TiposTarea tiposTarea = new TiposTarea();
		tiposTarea.setId015(bean.getIdTipoTarea());
		tiposTarea = this.tiposTareaService.find(tiposTarea);

		FormatosFichero formatosFicheroFilter = new FormatosFichero();
		formatosFicheroFilter.setEstado011(Constants.ALTA);

		Aa79bSalidaTarea aa79bSalidaTarea = new Aa79bSalidaTarea();
		Aa79bTiposTarea aa79bTiposTarea = new Aa79bTiposTarea();
		aa79bTiposTarea.setId(tiposTarea.getId015());
		aa79bTiposTarea.setDescEs(tiposTarea.getDescEs015());
		aa79bTiposTarea.setDescEu(tiposTarea.getDescEu015());
		aa79bTiposTarea.setDescEstadoEu(tiposTarea.getDescEstadoEu());
		aa79bTiposTarea.setDescEstadoEs(tiposTarea.getDescEstadoEs());
		aa79bTiposTarea.setDescGestionEu(tiposTarea.getDescGestionEu());
		aa79bTiposTarea.setDescGestionEs(tiposTarea.getDescGestionEs());
		aa79bTiposTarea.setDescCierreEs(tiposTarea.getDescCierreEs());
		aa79bTiposTarea.setDescCierreEu(tiposTarea.getDescCierreEu());
		aa79bTiposTarea.setIndHorasEjecucion(tiposTarea.getIndHorasEjecucion015());
		aa79bTiposTarea.setIndFacturable(tiposTarea.getIndFacturable015());
		aa79bSalidaTarea.setTipoTarea(aa79bTiposTarea);

		Aa79bSalidaTarea datosTareaEjecutada = this.aa79bTareaWsService.getDatosTareaEjecutada(bean.getIdTarea());

		aa79bSalidaTarea.setObservaciones(datosTareaEjecutada.getObservaciones());
		aa79bSalidaTarea.setEjecucionTareas(datosTareaEjecutada.getEjecucionTareas());

		aa79bSalidaTarea.setFormatosFichero(this.formatosFicheroService.findAll(formatosFicheroFilter, null));

		return aa79bSalidaTarea;
	}

	@WebMethod(operationName = "obtenerDatosRequerimientoInterpretacionWS")
	public Aa79bSalidaTarea obtenerDatosRequerimientoInterpretacionWS(EntradaDatosSolicitud bean) {
		TareasWSImpl.LOGGER.info("obtenerDatosRequerimientoInterpretacionWS");

		Locale locale;
		if (StringUtils.isNotBlank(bean.getIdioma())) {
			locale = new Locale(bean.getIdioma());
		} else {
			locale = new Locale(Constants.LANG_EUSKERA);
		}

		Tareas laTarea = this.tareasService.getDatosDocumentoPptoInterpretacion(bean.getAnyo(), bean.getNumExp(),
				bean.getIdRequerimiento());

		Aa79bSalidaTarea aa79bSalidaTarea = new Aa79bSalidaTarea();
		if (laTarea.getGestionInterpretacion().getFechaHoraLimite().getTime() < new Date().getTime()) {
			aa79bSalidaTarea.setEstado(Constants.ESTADO_WS_ERROR);
			aa79bSalidaTarea
					.setDescripcion(this.appMessageSource.getMessage(MENSAJE_FECHA_LIM_REQ_EXPIRADA, null, locale));
		} else {
			aa79bSalidaTarea.setFechaIni(laTarea.getFechaIni().getTime());
			aa79bSalidaTarea.setFechaFin(laTarea.getFechaFin().getTime());
			aa79bSalidaTarea.setHoraIni(laTarea.getHoraIni());
			aa79bSalidaTarea.setHoraFin(laTarea.getHoraFin());
			Aa79bTareasGestionInterpretacion gestionInterpretacion = new Aa79bTareasGestionInterpretacion();
			gestionInterpretacion.setNumInterpretes(laTarea.getGestionInterpretacion().getNumInterpretes());
			gestionInterpretacion.setPresupuesto(laTarea.getGestionInterpretacion().getPresupuesto());
			aa79bSalidaTarea.setGestionInterpretacion(gestionInterpretacion);
		}

		return aa79bSalidaTarea;
	}

	@WebMethod(operationName = "obtenerDatosRequerimientoTraduccionWS")
	public Aa79bSalidaDocPresupuestoTraduccion obtenerDatosRequerimientoTraduccionWS(EntradaDatosSolicitud bean) {
		TareasWSImpl.LOGGER.info("obtenerDatosRequerimientoTraduccionWS");
		Locale locale;
		if (StringUtils.isNotBlank(bean.getIdioma())) {
			locale = new Locale(bean.getIdioma());
		} else {
			locale = new Locale("eu");
		}
		DocPresupuestoTraduccion docPresupuestoTraduccion = this.tareasService.getDatosDocumentoPptoTraduccionLang(
				bean.getAnyo(), bean.getNumExp(), bean.getIdRequerimiento(), bean.getIdioma());

		SubsanacionExpediente elRequerimientoFilter = new SubsanacionExpediente();
		elRequerimientoFilter.setId(bean.getIdRequerimiento());
		SubsanacionExpediente elRequerimiento = this.subsanacionService.find(elRequerimientoFilter);

		Aa79bSalidaDocPresupuestoTraduccion aa79bDocPresupuestoTraduccion = new Aa79bSalidaDocPresupuestoTraduccion();

		if (elRequerimiento.getFechaHoraLimiteDate().getTime() < new Date().getTime()) {
			aa79bDocPresupuestoTraduccion.setEstado(Constants.ESTADO_WS_ERROR);
			aa79bDocPresupuestoTraduccion
					.setDescripcion(this.appMessageSource.getMessage(MENSAJE_FECHA_LIM_REQ_EXPIRADA, null, locale));
		} else {
			if (docPresupuestoTraduccion.getDatosTareaTrados() != null) {
				Aa79bDatosTareaTrados datosTareaTrados = new Aa79bDatosTareaTrados();
				datosTareaTrados.setTarifaPal(docPresupuestoTraduccion.getDatosTareaTrados().getTarifaPal());
				datosTareaTrados.setImporteBase(docPresupuestoTraduccion.getDatosTareaTrados().getImporteBase());
				datosTareaTrados.setImporteIva(docPresupuestoTraduccion.getDatosTareaTrados().getImporteIva());
				datosTareaTrados.setPorcentajeIva(docPresupuestoTraduccion.getDatosTareaTrados().getPorcentajeIva());
				datosTareaTrados.setPresupuesto(docPresupuestoTraduccion.getDatosTareaTrados().getPresupuesto());
				datosTareaTrados.setNumTotalPal(docPresupuestoTraduccion.getDatosTareaTrados().getNumTotalPal());
				datosTareaTrados
						.setNumPalConcor084(docPresupuestoTraduccion.getDatosTareaTrados().getNumPalConcor084());
				datosTareaTrados
						.setNumPalConcor8594(docPresupuestoTraduccion.getDatosTareaTrados().getNumPalConcor8594());
				datosTareaTrados
						.setNumPalConcor95100(docPresupuestoTraduccion.getDatosTareaTrados().getNumPalConcor95100());

				if (elRequerimiento.getFechaEntrega() != null) {
					datosTareaTrados.setFechaEntrega(elRequerimiento.getFechaEntrega().getTime());
					datosTareaTrados.setHoraEntrega(elRequerimiento.getHoraEntrega());
				}
				aa79bDocPresupuestoTraduccion.setDatosTareaTrados(datosTareaTrados);
			}
			if (docPresupuestoTraduccion.getDatosExpediente() != null) {
				Aa79bDocPresupuestoTraduccionInfoExped docPresupuestoTraduccionInfoExped = new Aa79bDocPresupuestoTraduccionInfoExped();
				docPresupuestoTraduccionInfoExped
						.setIndDificil(docPresupuestoTraduccion.getDatosExpediente().getIndDificil());
				docPresupuestoTraduccionInfoExped.setTitulo(docPresupuestoTraduccion.getDatosExpediente().getTitulo());
				docPresupuestoTraduccionInfoExped
						.setDificilDesc(docPresupuestoTraduccion.getDatosExpediente().getDificilDesc());
				docPresupuestoTraduccionInfoExped
						.setIndUrgente(docPresupuestoTraduccion.getDatosExpediente().getIndUrgente());
				docPresupuestoTraduccionInfoExped
						.setUrgenteDesc(docPresupuestoTraduccion.getDatosExpediente().getUrgenteDesc());

				docPresupuestoTraduccionInfoExped.setAnyo(docPresupuestoTraduccion.getDatosExpediente().getAnyo());
				docPresupuestoTraduccionInfoExped.setNumExp(docPresupuestoTraduccion.getDatosExpediente().getNumExp());
				int numExpDigits = String.valueOf((int) docPresupuestoTraduccion.getDatosExpediente().getNumExp())
						.length();
				StringBuilder numExpFormated = WSUtils.obtenerNumExpConCeros(numExpDigits);
				docPresupuestoTraduccionInfoExped.setNumExpFormated(
						docPresupuestoTraduccion.getDatosExpediente().getAnyo().toString().substring(Constants.DOS)
								+ "/" + numExpFormated + docPresupuestoTraduccion.getDatosExpediente().getNumExp());

				docPresupuestoTraduccionInfoExped
						.setIdTipoExpediente(docPresupuestoTraduccion.getDatosExpediente().getIdTipoExpediente());
				docPresupuestoTraduccionInfoExped
						.setTipoExpedienteDesc(docPresupuestoTraduccion.getDatosExpediente().getTipoExpedienteDesc());
				docPresupuestoTraduccionInfoExped
						.setTipoExpDesc(docPresupuestoTraduccion.getDatosExpediente().getTipoExpDesc());
				docPresupuestoTraduccionInfoExped
						.setFechaAlta(docPresupuestoTraduccion.getDatosExpediente().getFechaAlta().getTime());
				docPresupuestoTraduccionInfoExped
						.setHoraAlta(docPresupuestoTraduccion.getDatosExpediente().getHoraAlta());

				if (elRequerimiento.getFechaEntrega() != null) {
					docPresupuestoTraduccionInfoExped.setFechaFinalIzo(elRequerimiento.getFechaEntrega().getTime());
					docPresupuestoTraduccionInfoExped.setHoraFinalIzo(elRequerimiento.getHoraEntrega());
				} else {
					docPresupuestoTraduccionInfoExped.setFechaFinalIzo(
							docPresupuestoTraduccion.getDatosExpediente().getFechaFinalIzo().getTime());
					docPresupuestoTraduccionInfoExped
							.setHoraFinalIzo(docPresupuestoTraduccion.getDatosExpediente().getHoraFinalIzo());
				}
				docPresupuestoTraduccionInfoExped
						.setIdIdioma(docPresupuestoTraduccion.getDatosExpediente().getIdIdioma());
				docPresupuestoTraduccionInfoExped
						.setIdiomaDescEs(docPresupuestoTraduccion.getDatosExpediente().getIdiomaDescEs());
				docPresupuestoTraduccionInfoExped
						.setIdiomaDescEu(docPresupuestoTraduccion.getDatosExpediente().getIdiomaDescEu());
				docPresupuestoTraduccionInfoExped
						.setIdRelevancia(docPresupuestoTraduccion.getDatosExpediente().getIdRelevancia());
				docPresupuestoTraduccionInfoExped
						.setRelevanciaDescEs(docPresupuestoTraduccion.getDatosExpediente().getRelevanciaDescEs());
				docPresupuestoTraduccionInfoExped
						.setRelevanciaDescEu(docPresupuestoTraduccion.getDatosExpediente().getRelevanciaDescEu());
				docPresupuestoTraduccionInfoExped
						.setIndPublicarBopv(docPresupuestoTraduccion.getDatosExpediente().getIndPublicarBopv());
				docPresupuestoTraduccionInfoExped
						.setPublicarBopvDesc(docPresupuestoTraduccion.getDatosExpediente().getPublicarBopvDesc());
				aa79bDocPresupuestoTraduccion.setDatosExpediente(docPresupuestoTraduccionInfoExped);
			}
		}
		return aa79bDocPresupuestoTraduccion;
	}

	@WebMethod(operationName = "registrarNoConformidadClienteWS")
	public Aa79bSalidaTarea registrarNoConformidadClienteWS(Aa79bEntradaTarea bean) {
		TareasWSImpl.LOGGER.info("registrarNoConformidadCliente");
		Locale locale;
		if (StringUtils.isNotBlank(bean.getIdioma())) {
			locale = new Locale(bean.getIdioma());
		} else {
			locale = new Locale("eu");
		}
		Tareas tarea = new Tareas();
		tarea.setAnyo(bean.getAnyo());
		tarea.setNumExp(bean.getNumExp());
		Tareas tareaEntrega = this.aa79bTareaWsService.findTareaEntregaCliente(tarea);

		tarea.setOrden(tareaEntrega.getOrden() + 1);
		tarea.setIdTareaRel(tareaEntrega.getIdTarea());
		TiposTarea tipTarea = new TiposTarea();
		tipTarea.setId015(new Long(TipoTareaGestionAsociadaEnum.NO_CONFORMIDAD_CLIENTE.getValue()));
		tarea.setTipoTarea(tipTarea);
		tarea.setObservaciones(bean.getObservacionesTareas().getObsvEjecucion());
		this.tareasService.add(tarea);

		BitacoraExpediente bExp = new BitacoraExpediente();
		bExp.setAnyo(bean.getAnyo());
		bExp.setNumExp(bean.getNumExp());
		bExp.setInfoAdic(this.appMessageSource.getMessage("label.noConformidadCliente", null, locale));

		EstadosExpediente estado = new EstadosExpediente();
		estado.setId(Long.valueOf(EstadoExpedienteEnum.EN_CURSO.getValue()));
		bExp.setEstadoExp(estado);

		FasesExpediente fases = new FasesExpediente();
		fases.setId(Long.valueOf(FaseExpedienteEnum.PDTE_EJECT_TAREAS.getValue()));
		bExp.setFaseExp(fases);

		this.bitacoraExpedienteService.add(bExp);

		Expediente exp = new Expediente();
		exp.setAnyo(bExp.getAnyo());
		exp.setNumExp(bExp.getNumExp());
		exp.setBitacoraExpediente(bExp);
		this.expedienteService.modificarEstado(exp);


		DatosContacto datosContacto = new DatosContacto();
		datosContacto.setDni031(bean.getDni());
		datosContacto = this.datosContactoService.findDatosContacto(datosContacto);

		BitacoraSolicitante bitacoraSolicitante = new BitacoraSolicitante();
		bitacoraSolicitante.setAnyo(bean.getAnyo());
		bitacoraSolicitante.setNumExp(bean.getNumExp());
		bitacoraSolicitante.setUsuario(datosContacto.getNombreApellidos());
		bitacoraSolicitante.setIdAccionBitacora(new Long(AccionBitacoraEnum.NO_CONFORMIDAD_CLIENTE.getValue()));
		this.aa79bSolicitudWsService.addBitacoraSolicitante(bitacoraSolicitante);

		Aa79bSalidaTarea aa79bSalidaTarea = new Aa79bSalidaTarea();
		aa79bSalidaTarea.setEstado(Constants.ESTADO_WS_OK);
		return aa79bSalidaTarea;
	}

	@WebMethod(operationName = "detalleTareaProveedorWS")
	public Aa79bSalidaTarea detalleTareaProveedorWS(Aa79bEntradaEjecutarTarea bean) {
		TareasWSImpl.LOGGER.debug("WS_METHOD detalleTareaProveedorWS - BEGIN\nidTarea: " + bean.getIdTarea());

		Locale locale;
		if (StringUtils.isNotBlank(bean.getIdioma())) {
			locale = new Locale(bean.getIdioma());
		} else {
			locale = new Locale(Constants.LANG_EUSKERA);
		}
		Aa79bSalidaTarea aa79bSalidaTarea = new Aa79bSalidaTarea();

		try {

			// comprobar acceso tarea de usuario
			Boolean accesoTarea = this.aa79bTareaWsService.getAccesoTarea(bean);
			if (accesoTarea) {

				BigDecimal idTarea = bean.getIdTarea();
				String idioma = bean.getIdioma();

				Aa79bDatosContacto aa79bDatosContacto = new Aa79bDatosContacto();

				DatosContacto datosContacto = new DatosContacto();
				datosContacto.setDni031(bean.getDni());

				DatosContacto rstDatosContacto = this.datosContactoService.findDatosContacto(datosContacto);

				aa79bDatosContacto = WSUtils.parsearDatosContactoToWS(rstDatosContacto);

				Tareas tarea = this.tareasService.findDetalleTarea(idTarea, idioma);

				if (tarea.getTipoTarea().getId015() == Long.valueOf(TipoTareaGestionAsociadaEnum.NO_CONFORMIDAD_PROVEEDOR.getValue())) {
					TiposRevision tipoRevision = this.tareasService.findTipoRevisionTareaRel(idTarea);
					tarea.setTipoRevision(tipoRevision);
				}

				DatosTareaTrados datosTareaTrados = obtenerDatosTrados(tarea);
				List<DocumentoTarea> documentos = new ArrayList<DocumentoTarea>();
				Tareas tareaAux = this.tareasService.findConfTarea(tarea);


				if (WSUtils.esTipoTarea(tareaAux, TipoTareaGestionAsociadaEnum.NOTIFICAR_CORRECCIONES_PROVEEDOR.getValue())) {
					documentos.add(getDocumentosTarea(tarea));
				} else if (WSUtils.esTipoTarea(tareaAux, TipoTareaGestionAsociadaEnum.NO_CONFORMIDAD_PROVEEDOR.getValue())) {
					documentos = this.tareasGeneralService.findDocsDetalleTarea(idTarea, idioma);
					getDocumentoTareaNoConformidad(tarea, documentos);
				} else {
					documentos = this.tareasGeneralService.findDocsDetalleTarea(idTarea, idioma);
				}

				aa79bSalidaTarea = WSUtils.parsearAFormatoWsAa79(tarea);
				aa79bSalidaTarea.setDatosContacto(aa79bDatosContacto);

				Aa79bDatosTareaTrados aa79bDatosTareaTrados = WSUtils.parsearDatosTareaTradosToWS(datosTareaTrados);
				aa79bSalidaTarea.setDatosTrados(aa79bDatosTareaTrados);

				List<Aa79bDocumentoTarea> documentosTarea = WSUtils.parsearDocumentosTareaToWS(documentos);
				aa79bSalidaTarea.setDocumentos(documentosTarea);

				aa79bSalidaTarea.setTareaEjecutable(comprobarTareaEjecutable(tarea));

				Long idIdioma = obtenerIdiomaDestino(tarea);

				if (idIdioma != null) {
					aa79bSalidaTarea.setIdIdiomaDestino(idIdioma);
				}

				aa79bSalidaTarea.setEstado(Constants.ESTADO_WS_OK);

				Expediente expediente = new Expediente();
				expediente.setAnyo(tarea.getAnyo());
				expediente.setNumExp(tarea.getNumExp());
				ObservacionesExpediente obsExp = this.observacionesExpedienteService.observacionesFind(expediente);

				Aa79bNotasTarea notasTarea = WSUtils.parsearNotasTareaToWs(obsExp);
				aa79bSalidaTarea.setNotasTarea(notasTarea);

				TareasWSImpl.LOGGER.info("WS_METHOD detalleTareaProveedorWS END\nreturn" + aa79bSalidaTarea);
			} else {
				aa79bSalidaTarea.setEstado(Constants.ESTADO_WS_ERROR);
				aa79bSalidaTarea.setDescripcion(
						this.appMessageSource.getMessage(MENSAJE_PROVEEDOR_SIN_ACCESO_A_TAREA, null, locale));
			}

		} catch (Exception e) {
			TareasWSImpl.LOGGER.error("[POST - subm]: detalleTareaProveedorWS ", e);
			aa79bSalidaTarea.setEstado(Constants.ESTADO_WS_ERROR);
			aa79bSalidaTarea
					.setDescripcion(this.appMessageSource.getMessage(ERROR_OBTENIENDO_INFORMACION, null, locale));
		}

		TareasWSImpl.LOGGER.debug("detalleTareaProveedorWS - FIN");
		return aa79bSalidaTarea;
	}

	/**
	 *
	 * @param tarea Tareas
	 * @param documentos List<DocumentoTarea>
	 */
	private void getDocumentoTareaNoConformidad(Tareas tarea, List<DocumentoTarea> documentos) {
		DocumentoTarea docTarea = getDocumentosTarea(tarea);
		if (docTarea != null
				&& docTarea.getDocumentoOriginal() != null
				&& docTarea.getDocumentoOriginal().getFicheros().size() == 1) {
			FicheroDocExp fichero = docTarea.getDocumentoOriginal().getFicheros().get(0);
			DocumentoTareaAdjunto docAdjunto = new DocumentoTareaAdjunto();
			docAdjunto.setIdFichero(fichero.getIdDocInsertado());
			docAdjunto.setContentType(fichero.getContentType());
			docAdjunto.setNombre(fichero.getNombre());
			DocumentoTarea docAuxTarea = new DocumentoTarea();
			docAuxTarea.setDocumentoAdjunto(docAdjunto);
			documentos.add(docAuxTarea);
		}
	}

	private DocumentoTarea getDocumentosTarea(Tareas tarea) {
		DocumentosCorreccion docCorreccion = new DocumentosCorreccion();
		docCorreccion.setIdTarea(tarea.getIdTarea());
		return this.documentosCorreccionService.getDocumentoCorreccion(docCorreccion);
	}


	/**
	 * @param tarea
	 * @return
	 */
	private Long obtenerIdiomaDestino(Tareas tarea) {
		Long idIdioma = null;

		Expediente expediente = new Expediente();
		expediente.setAnyo(tarea.getAnyo());
		expediente.setNumExp(tarea.getNumExp());

		Expediente exp = this.expedienteService.find(expediente);

		if (exp != null
				&& (TipoExpedienteEnum.REVISION.getValue().equals(exp.getIdTipoExpediente())
						|| TipoExpedienteEnum.TRADUCCION.getValue().equals(exp.getIdTipoExpediente()))
				&& exp.getExpedienteTradRev() != null) {
			idIdioma = exp.getExpedienteTradRev().getIdIdioma();
		}

		return idIdioma;
	}

	@WebMethod(operationName = "descargarDocumentosTareaWS")
	public Aa79bSalidaDatosDescargaDocumentos descargarDocumentosTareaWS(Aa79bEntradaDocumentosTarea bean) {
		TareasWSImpl.LOGGER.debug("WS_METHOD descargarDocumentosTareaWS - BEGIN\nidTarea: " + bean.getIdTarea());

		Locale locale;
		String idioma;
		if (StringUtils.isNotBlank(bean.getIdioma())) {
			idioma = bean.getIdioma();
			locale = new Locale(idioma);
		} else {
			idioma = Constants.LANG_EUSKERA;
			locale = new Locale(idioma);
		}
		Aa79bSalidaDatosDescargaDocumentos salida = new Aa79bSalidaDatosDescargaDocumentos();
		try {

			Aa79bEntradaEjecutarTarea entradaEjecTarea = new Aa79bEntradaEjecutarTarea();
			entradaEjecTarea.setDni(bean.getDni());

			BigDecimal idTarea = bean.getIdTarea();
			entradaEjecTarea.setIdTarea(idTarea);

			// comprobar acceso tarea de usuario
			Boolean accesoTarea = this.aa79bTareaWsService.getAccesoTarea(entradaEjecTarea);
			if (accesoTarea) {

				List<Aa79bFicheroDocExp> listaFicheros;
				BigDecimal idDoc = bean.getIdDoc();

				if (idDoc != null) {
					FicheroDocExp fichero = this.documentosExpedienteService.findDatosFichero(idDoc);

					listaFicheros = new ArrayList<Aa79bFicheroDocExp>();

					Aa79bFicheroDocExp aa79bFicheroDocExp = WSUtils.parsearFicheroDocExpToWs(fichero);

					listaFicheros.add(aa79bFicheroDocExp);
				} else {
					List<DocumentoTarea> documentos = this.tareasGeneralService.findDocsDetalleTarea(idTarea, idioma);
					listaFicheros = WSUtils.parsearDocsTareaToWS(documentos);
				}

				salida.setListaFicheros(listaFicheros);
				salida.setEstado(Constants.ESTADO_WS_OK);

				TareasWSImpl.LOGGER.info("WS_METHOD descargarDocumentosTareaWS END\nreturn" + salida);
			} else {
				salida.setEstado(Constants.ESTADO_WS_ERROR);
				salida.setDescripcion(
						this.appMessageSource.getMessage(MENSAJE_PROVEEDOR_SIN_ACCESO_A_TAREA, null, locale));
			}

		} catch (Exception e) {
			TareasWSImpl.LOGGER.error("[POST - subm]: descargarDocumentosTareaWS ", e);
			salida.setEstado(Constants.ESTADO_WS_ERROR);
			salida.setDescripcion(this.appMessageSource.getMessage(ERROR_OBTENIENDO_INFORMACION, null, locale));
		}

		TareasWSImpl.LOGGER.debug("descargarDocumentosTareaWS - FIN");
		return salida;
	}

	@WebMethod(operationName = "obtenerAuditoriasWS")
	public Aa79bTablaWebService<Aa79bAuditoria> obtenerAuditoriasWS(Aa79bEntradaAuditoria bean,
			Aa79bTablaWebService<Aa79bAuditoria> aa79bTablaWebService, Aa79bAuditoria salida) {
		TareasWSImpl.LOGGER.debug("WS_METHOD obtenerAuditoriasWS - BEGIN\nfilter: " + bean + TABLA_REQUEST
				+ aa79bTablaWebService);

		Locale locale;
		if (StringUtils.isNotBlank(bean.getIdioma())) {
			locale = new Locale(bean.getIdioma());
		} else {
			locale = new Locale("eu");
		}

		Aa79bTablaWebService<Aa79bAuditoria> tabla = new Aa79bTablaWebService<Aa79bAuditoria>();
		try {

			JQGridRequestDto jqGridRequestDto = null;
			if (aa79bTablaWebService != null && Utils.isValidAa79bTablaWebService(aa79bTablaWebService)) {
				jqGridRequestDto = new JQGridRequestDto(aa79bTablaWebService.getRows(), aa79bTablaWebService.getPage(),
						aa79bTablaWebService.getSidx(), aa79bTablaWebService.getSord());

			}

			final JQGridResponseDto<Aa79bAuditoria> jqGrid = this.aa79bTareaWsService
					.obtenerAuditorias(bean, jqGridRequestDto, false);
			tabla.setEstado(Constants.ESTADO_WS_OK);
			@SuppressWarnings(value = "unchecked")
			final Aa79bJaxbList<Aa79bAuditoria> lista = new Aa79bJaxbList<Aa79bAuditoria>(
					(List<Aa79bAuditoria>) jqGrid.getRows());
			tabla.setLista(lista);
			tabla.setRecordNum(Long.valueOf(jqGrid.getRecords()));

			TareasWSImpl.LOGGER.info("WS_METHOD obtenerAuditoriasWS END\nreturn" + tabla);

		} catch (Exception e) {
			TareasWSImpl.LOGGER.error("[POST - subm]: obtenerAuditoriasWS ", e);
			tabla.setSidx("-1");
			tabla.setSord(e.getMessage());
			tabla.setEstado(Constants.ESTADO_WS_ERROR);
			tabla.setDescripcion(this.appMessageSource.getMessage(ERROR_OBTENIENDO_INFORMACION, null, locale));
		}

		TareasWSImpl.LOGGER.debug("obtenerAuditoriasWS - FIN");
		return tabla;
	}

	@WebMethod(operationName = "detalleAuditoriaWS")
	public Aa79bSalidaDetalleAuditoria detalleAuditoriaWS(Aa79bEntradaDetalleAuditoria bean) {
		TareasWSImpl.LOGGER.debug("detalleAuditoriaWS - INICIO");
		Aa79bSalidaDetalleAuditoria aa79bSalidaAuditoria = new Aa79bSalidaDetalleAuditoria();
		Aa79bAuditoria aa79bAuditoria = new Aa79bAuditoria();

		Auditoria datosAuditoria = new Auditoria();
		datosAuditoria.setAnyo(bean.getAnyo());
		datosAuditoria.setNumExp(bean.getNumExp());
		datosAuditoria.setIdTarea(bean.getIdTarea());
		datosAuditoria.setIdTareaAuditar(bean.getIdTareaAuditar());

		Long idTablaC2 = this.auditoriaService.llamarPLCrearEstructuraAuditoria(datosAuditoria);
		if (idTablaC2 != null && idTablaC2 > 0) {
			datosAuditoria.setIdAuditoria(idTablaC2);
			// obtenemos datos generales de auditoria
			aa79bAuditoria = this.aa79bTareaWsService.getDatosGeneralesAuditoria(datosAuditoria);
			// obtenemos secciones de auditoria
			List<Aa79bAuditoriaSeccionExpediente> lAuditoriaSeccionExpediente = this.aa79bTareaWsService.getSeccionesExpedienteAuditoria(datosAuditoria);
			List<Aa79bAuditoriaSeccionExpediente> lAuditoriaSeccionExpedienteMostrar = new ArrayList<Aa79bAuditoriaSeccionExpediente>();
			if (lAuditoriaSeccionExpediente != null && !lAuditoriaSeccionExpediente.isEmpty()) {
				for(Aa79bAuditoriaSeccionExpediente auditSecExp : lAuditoriaSeccionExpediente) {
					// obtenemos count de campos de seccion
					if (ActivoEnum.SI.getValue().equalsIgnoreCase(auditSecExp.getIndObservaciones()) ||
							auditSecExp.getCamposSeccionCount() > 0 ||
							AuditoriaTipoSeccionEnum.LIBRE.getValue().equals(auditSecExp.getTipoSeccion())) {
						// si la seccion tienen indObservaciones a 'S' o tiene campos o es de tipo seccion Libre, hay que mostrarlo en pantalla
						lAuditoriaSeccionExpedienteMostrar.add(auditSecExp);
					}
				}
			}

			aa79bSalidaAuditoria.setlAuditoriaSeccionExpediente(lAuditoriaSeccionExpedienteMostrar);
		}

		aa79bSalidaAuditoria.setAuditoria(aa79bAuditoria);
		TareasWSImpl.LOGGER.debug("WS_METHOD detalleAuditoriaWS - FIN: " + aa79bSalidaAuditoria);
		return aa79bSalidaAuditoria;
	}

	@WebMethod(operationName = "filterCamposSeccionWS")
	public Aa79bTablaWebService<Aa79bAuditoriaCampoSeccionExpediente> filterCamposSeccionWS(Aa79bEntradaCamposSeccion bean,
			Aa79bTablaWebService<Aa79bAuditoriaCampoSeccionExpediente> aa79bTablaWebService, Aa79bAuditoriaCampoSeccionExpediente salida) {
		TareasWSImpl.LOGGER.debug("WS_METHOD filterCamposSeccionWS - BEGIN\nfilter: " + bean + TABLA_REQUEST
				+ aa79bTablaWebService);

		Locale locale;
		if (StringUtils.isNotBlank(bean.getIdioma())) {
			locale = new Locale(bean.getIdioma());
		} else {
			locale = new Locale("eu");
		}

		Aa79bTablaWebService<Aa79bAuditoriaCampoSeccionExpediente> tabla = new Aa79bTablaWebService<Aa79bAuditoriaCampoSeccionExpediente>();
		try {

			JQGridRequestDto jqGridRequestDto = null;
			if (aa79bTablaWebService != null && Utils.isValidAa79bTablaWebService(aa79bTablaWebService)) {
				jqGridRequestDto = new JQGridRequestDto(aa79bTablaWebService.getRows(), aa79bTablaWebService.getPage(),
						aa79bTablaWebService.getSidx(), aa79bTablaWebService.getSord());

			}

			final JQGridResponseDto<Aa79bAuditoriaCampoSeccionExpediente> jqGrid = this.aa79bTareaWsService
					.filterCamposSeccion(bean, jqGridRequestDto, false);
			tabla.setEstado(Constants.ESTADO_WS_OK);
			@SuppressWarnings(value = "unchecked")
			final Aa79bJaxbList<Aa79bAuditoriaCampoSeccionExpediente> lista = new Aa79bJaxbList<Aa79bAuditoriaCampoSeccionExpediente>(
					(List<Aa79bAuditoriaCampoSeccionExpediente>) jqGrid.getRows());
			tabla.setLista(lista);
			tabla.setRecordNum(Long.valueOf(jqGrid.getRecords()));

			TareasWSImpl.LOGGER.info("WS_METHOD filterCamposSeccionWS END\nreturn" + tabla);

		} catch (Exception e) {
			TareasWSImpl.LOGGER.error("[POST - subm]: filterCamposSeccionWS ", e);
			tabla.setSidx("-1");
			tabla.setSord(e.getMessage());
			tabla.setEstado(Constants.ESTADO_WS_ERROR);
			tabla.setDescripcion(this.appMessageSource.getMessage(ERROR_OBTENIENDO_INFORMACION, null, locale));
		}

		TareasWSImpl.LOGGER.debug("filterCamposSeccionWS - FIN");
		return tabla;
	}

	@WebMethod(operationName = "filterDocumentosSeccionWS")
	public Aa79bTablaWebService<Aa79bAuditoriaDocumentoSeccionExpediente> filterDocumentosSeccionWS(Aa79bEntradaCamposSeccion bean,
			Aa79bTablaWebService<Aa79bAuditoriaDocumentoSeccionExpediente> aa79bTablaWebService, Aa79bAuditoriaDocumentoSeccionExpediente salida) {
		TareasWSImpl.LOGGER.debug("WS_METHOD filterDocumentosSeccionWS - BEGIN\nfilter: " + bean + TABLA_REQUEST
				+ aa79bTablaWebService);

		Locale locale;
		if (StringUtils.isNotBlank(bean.getIdioma())) {
			locale = new Locale(bean.getIdioma());
		} else {
			locale = new Locale("eu");
		}

		Aa79bTablaWebService<Aa79bAuditoriaDocumentoSeccionExpediente> tabla = new Aa79bTablaWebService<Aa79bAuditoriaDocumentoSeccionExpediente>();
		try {

			JQGridRequestDto jqGridRequestDto = null;
			if (aa79bTablaWebService != null && Utils.isValidAa79bTablaWebService(aa79bTablaWebService)) {
				jqGridRequestDto = new JQGridRequestDto(aa79bTablaWebService.getRows(), aa79bTablaWebService.getPage(),
						aa79bTablaWebService.getSidx(), aa79bTablaWebService.getSord());

			}

			final JQGridResponseDto<Aa79bAuditoriaDocumentoSeccionExpediente> jqGrid = this.aa79bTareaWsService
					.filterDocumentosSeccion(bean, jqGridRequestDto, false);
			tabla.setEstado(Constants.ESTADO_WS_OK);
			@SuppressWarnings(value = "unchecked")
			final Aa79bJaxbList<Aa79bAuditoriaDocumentoSeccionExpediente> lista = new Aa79bJaxbList<Aa79bAuditoriaDocumentoSeccionExpediente>(
					(List<Aa79bAuditoriaDocumentoSeccionExpediente>) jqGrid.getRows());
			tabla.setLista(lista);
			tabla.setRecordNum(Long.valueOf(jqGrid.getRecords()));

			TareasWSImpl.LOGGER.info("WS_METHOD filterDocumentosSeccionWS END\nreturn" + tabla);

		} catch (Exception e) {
			TareasWSImpl.LOGGER.error("[POST - subm]: filterDocumentosSeccionWS ", e);
			tabla.setSidx("-1");
			tabla.setSord(e.getMessage());
			tabla.setEstado(Constants.ESTADO_WS_ERROR);
			tabla.setDescripcion(this.appMessageSource.getMessage(ERROR_OBTENIENDO_INFORMACION, null, locale));
		}

		TareasWSImpl.LOGGER.debug("filterDocumentosSeccionWS - FIN");
		return tabla;
	}

	@WebMethod(operationName = "guardarAuditoriaWS")
	public Aa79bSalidaConfirmarAuditoria guardarAuditoriaWS(Aa79bEntradaConfirmarAuditoria bean) {
		Locale locale = new Locale(bean.getIdioma());
		TareasWSImpl.LOGGER.debug("guardarAuditoriaWS - INICIO");
		Aa79bSalidaConfirmarAuditoria datosSalida = new Aa79bSalidaConfirmarAuditoria();
		TareasWSImpl.LOGGER.debug("guardarAuditoriaWS - Se llama al guardarDatosAuditoria");
		Aa79bAuditoria auditoria = this.aa79bTareaWsService.guardarDatosAuditoria(bean.getAuditoria());
		int rst = this.enviarEmailAuditoriaConfirmada(bean.getAuditoria(), locale, TipoAvisoEnum.AUDITORIA_CONFIRMADA.getValue());
		datosSalida.setEstado(Constants.ESTADO_WS_OK);
		datosSalida.setDescripcion(String.valueOf(rst));
		datosSalida.setFechaConfirmacion(auditoria.getFechaConfirmacion());
		TareasWSImpl.LOGGER.debug("guardarAuditoriaWS - FIN");
		return datosSalida;
	}

	/**
	 * Comprueba si la tarea es ejecutable o no.
	 *
	 * @param tarea
	 * @return String
	 */
	private String comprobarTareaEjecutable(Tareas tarea) {
		String tareaEjecutable = StringUtils.EMPTY;

		if (EstadoAceptacionTareaEnum.ACEPTADA.getValue() != tarea.getEstadoAsignado()
				|| EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue() != tarea.getEstadoEjecucion()) {
			tareaEjecutable = Constants.NO;
		} else {

			BitacoraExpediente bitacora = this.expedienteService.findEstadoFaseExpediente(tarea.getAnyo(),
					tarea.getNumExp());

			if (bitacora != null && bitacora.getEstadoExp() != null && bitacora.getFaseExp() != null) {

				if (EstadoExpedienteEnum.EN_CURSO.getValue() == bitacora.getEstadoExp().getId()
						&& FaseExpedienteEnum.PDTE_TRAM_CLIENTE.getValue() == bitacora.getFaseExp().getId()) {
					tareaEjecutable = Constants.NO;
				} else {
					tareaEjecutable = isTareaEjecutable(tarea);
				}

			}

		}

		return tareaEjecutable;
	}

	/**
	 * @param tarea
	 * @return
	 */
	private String isTareaEjecutable(Tareas tarea) {
		String tareaEjecutable;
		long numTareasPendientes = this.tareasService.comprobarTareasPendientes(tarea);

		if (numTareasPendientes > 0) {
			tareaEjecutable = Constants.NO;
		} else {
			tareaEjecutable = Constants.SI;
		}
		return tareaEjecutable;
	}

	/**
	 * Obtiene los datos de trados asociados a la tarea
	 *
	 * @param tarea
	 * @return DatosTareaTrados
	 */
	private DatosTareaTrados obtenerDatosTrados(Tareas tarea) {
		DatosTareaTrados datosTareaTrados = null;

		if (existeTareaTrados(tarea)) {
			Tareas tareaTrados = this.tareasService.findTareaTrados(tarea.getAnyo(), tarea.getNumExp());

			if (tareaTrados != null) {
				if (EstadoEjecucionTareaEnum.EJECUTADA.getValue() == tareaTrados.getEstadoEjecucion()) {
					datosTareaTrados = this.tareasGeneralService.findDatosTareaTradosEjecutada(tarea.getIdTarea(),
							tareaTrados.getIdTarea());
					if (datosTareaTrados.getNumTotalPal() == 0) {
						datosTareaTrados = this.tareasGeneralService.findDatosTareaTradosSinEjecutar(tarea.getIdTarea());
					}
				} else {
					datosTareaTrados = this.tareasGeneralService.findDatosTareaTradosSinEjecutar(tarea.getIdTarea());
				}
			} else {
				datosTareaTrados = this.tareasGeneralService.findDatosTareaTradosSinEjecutar(tarea.getIdTarea());
			}
		}

		return datosTareaTrados;
	}

	/**
	 * @param tarea
	 * @return
	 */
	private boolean existeTareaTrados(Tareas tarea) {
		return WSUtils.existenDatosTareaNulos(tarea)
				&& TipoTareaGestionAsociadaEnum.INTERPRETAR.getValue() != tarea.getTipoTarea().getId015();
	}

}