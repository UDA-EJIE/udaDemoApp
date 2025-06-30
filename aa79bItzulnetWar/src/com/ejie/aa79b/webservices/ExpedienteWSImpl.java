package com.ejie.aa79b.webservices;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.JasperUtils;
import com.ejie.aa79b.mail.MailService;
import com.ejie.aa79b.model.BitacoraSolicitante;
import com.ejie.aa79b.model.BitacorasSolicitante;
import com.ejie.aa79b.model.CabeceraExpediente;
import com.ejie.aa79b.model.CamposSelecSub;
import com.ejie.aa79b.model.ClasificacionDocumentos;
import com.ejie.aa79b.model.ConfigCalculoPresupuesto;
import com.ejie.aa79b.model.ConfigPerfilSolicitante;
import com.ejie.aa79b.model.DatosConsultaExp;
import com.ejie.aa79b.model.DatosDocumentoSalida;
import com.ejie.aa79b.model.DatosExpediente;
import com.ejie.aa79b.model.DatosExpedientesRelacionados;
import com.ejie.aa79b.model.DatosInterpretacion;
import com.ejie.aa79b.model.DatosSalidaWS;
import com.ejie.aa79b.model.DireccionNora;
import com.ejie.aa79b.model.DocumentoTareaAdjunto;
import com.ejie.aa79b.model.DocumentosExpediente;
import com.ejie.aa79b.model.DocusSelecSub;
import com.ejie.aa79b.model.EntradaCabeceraExpediente;
import com.ejie.aa79b.model.EntradaDatosDocumentacion;
import com.ejie.aa79b.model.EntradaDatosDocumento;
import com.ejie.aa79b.model.EntradaDatosExpediente;
import com.ejie.aa79b.model.EntradaDatosSolicitud;
import com.ejie.aa79b.model.EntradaDocumentosExpediente;
import com.ejie.aa79b.model.EntradaExpedientesRelacionados;
import com.ejie.aa79b.model.EntradaFechaFinalizacion;
import com.ejie.aa79b.model.EntradaGestoresRepresentante;
import com.ejie.aa79b.model.EntradaInfoBitacora;
import com.ejie.aa79b.model.EntradaLibroRegistroSol;
import com.ejie.aa79b.model.EntradaNotasExpediente;
import com.ejie.aa79b.model.EntradaObtenerDetalleSub;
import com.ejie.aa79b.model.EntradaPlantilla;
import com.ejie.aa79b.model.EntradaReceptorAutorizado;
import com.ejie.aa79b.model.EntradaReceptoresAutorizados;
import com.ejie.aa79b.model.EntradaRegistrarSubsanacion;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.ExpedientesRelacionados;
import com.ejie.aa79b.model.FicheroWS;
import com.ejie.aa79b.model.FormatosFichero;
import com.ejie.aa79b.model.Gestor;
import com.ejie.aa79b.model.GestorExpediente;
import com.ejie.aa79b.model.GestoresRepresentante;
import com.ejie.aa79b.model.IdiomaDestino;
import com.ejie.aa79b.model.InfoBitacora;
import com.ejie.aa79b.model.LibroRegistro;
import com.ejie.aa79b.model.ListaExpedientesRelacionados;
import com.ejie.aa79b.model.ModosInterpretacion;
import com.ejie.aa79b.model.NotasExpediente;
import com.ejie.aa79b.model.ObservacionesExpediente;
import com.ejie.aa79b.model.ParametrosEmail;
import com.ejie.aa79b.model.ReceptorAutorizado;
import com.ejie.aa79b.model.SalidaDatosDocumentacion;
import com.ejie.aa79b.model.SalidaDatosTraduccionRevision;
import com.ejie.aa79b.model.SalidaFechaFinalizacion;
import com.ejie.aa79b.model.SalidaObtenerDetalleSub;
import com.ejie.aa79b.model.Solicitante;
import com.ejie.aa79b.model.SubsanacionExpediente;
import com.ejie.aa79b.model.TipoRelevancia;
import com.ejie.aa79b.model.TiposDocumento;
import com.ejie.aa79b.model.TiposInterpretacion;
import com.ejie.aa79b.model.enums.FormatoInformeEnum;
import com.ejie.aa79b.model.enums.TipoAvisoEnum;
import com.ejie.aa79b.model.enums.TipoExpedienteEnum;
import com.ejie.aa79b.model.excel.ExcelModel;
import com.ejie.aa79b.model.webservices.Aa79bDatosDeclaraciones;
import com.ejie.aa79b.model.webservices.Aa79bDireccionNora;
import com.ejie.aa79b.model.webservices.Aa79bDocumentoExpediente;
import com.ejie.aa79b.model.webservices.Aa79bEntradaEjecutarTarea;
import com.ejie.aa79b.model.webservices.Aa79bExpediente;
import com.ejie.aa79b.model.webservices.Aa79bExpedienteRelacionado;
import com.ejie.aa79b.model.webservices.Aa79bFicheroDocExp;
import com.ejie.aa79b.model.webservices.Aa79bGestorExpediente;
import com.ejie.aa79b.model.webservices.Aa79bInformeSolicitudes;
import com.ejie.aa79b.model.webservices.Aa79bInformes;
import com.ejie.aa79b.model.webservices.Aa79bJaxbList;
import com.ejie.aa79b.model.webservices.Aa79bPersona;
import com.ejie.aa79b.model.webservices.Aa79bReceptorAutorizadoConsulta;
import com.ejie.aa79b.model.webservices.Aa79bRegistroExpediente;
import com.ejie.aa79b.model.webservices.Aa79bSalidaDatosDescargaDocumentos;
import com.ejie.aa79b.model.webservices.Aa79bSalidaDatosPresupuestoFacturacion;
import com.ejie.aa79b.model.webservices.Aa79bSolaskides;
import com.ejie.aa79b.model.webservices.Aa79bSolicitud;
import com.ejie.aa79b.model.webservices.Aa79bTablaWebService;
import com.ejie.aa79b.service.Aa79bExpedienteWsService;
import com.ejie.aa79b.service.Aa79bSolicitudWsService;
import com.ejie.aa79b.service.Aa79bTareaWsService;
import com.ejie.aa79b.service.ClasificacionDocumentosService;
import com.ejie.aa79b.service.ConfigCalculoPresupuestoService;
import com.ejie.aa79b.service.ConfigLibroRegistroService;
import com.ejie.aa79b.service.ConfigPerfilSolicitanteService;
import com.ejie.aa79b.service.DatosPersonaService;
import com.ejie.aa79b.service.DireccionNoraService;
import com.ejie.aa79b.service.DocumentosExpedienteService;
import com.ejie.aa79b.service.DocumentosGeneralService;
import com.ejie.aa79b.service.ExcelReportService;
import com.ejie.aa79b.service.ExpedienteService;
import com.ejie.aa79b.service.ExpedientesRelacionadosService;
import com.ejie.aa79b.service.FormatosFicheroService;
import com.ejie.aa79b.service.IdiomaDestinoService;
import com.ejie.aa79b.service.ModosInterpretacionService;
import com.ejie.aa79b.service.ObservacionesExpedienteService;
import com.ejie.aa79b.service.OidsAuxiliarService;
import com.ejie.aa79b.service.PlantillasService;
import com.ejie.aa79b.service.ReceptorAutorizadoService;
import com.ejie.aa79b.service.SolicitanteService;
import com.ejie.aa79b.service.SubidaFicherosService;
import com.ejie.aa79b.service.TipoRelevanciaService;
import com.ejie.aa79b.service.TiposDocumentoService;
import com.ejie.aa79b.service.TiposInterpretacionService;
import com.ejie.aa79b.utils.DocumentoUtils;
import com.ejie.aa79b.utils.Utils;
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
@WebService(serviceName = "aa79bExpedienteWS", portName = "aa79bExpedienteWSPort", targetNamespace = "http://com.ejie.aa79b.webservices")
@SOAPBinding(parameterStyle = ParameterStyle.WRAPPED)
@HandlerChain(file = "server-handlers.xml")
public class ExpedienteWSImpl extends SpringBeanAutowiringSupport {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExpedienteWSImpl.class);

	@Autowired()
	private Properties appConfiguration;
	@Autowired()
	private ReloadableResourceBundleMessageSource appMessageSource;
	@Autowired()
	private ApplicationContext ctx;
	@Autowired()
	private MailService mailService;
	@Autowired
	private ConfigLibroRegistroService configLibroRegistroService;
	@Autowired
	private DocumentosExpedienteService documentosExpedienteService;
	@Autowired
	private Aa79bExpedienteWsService aa79bExpedienteWsService;
	@Autowired()
	private Aa79bSolicitudWsService aa79bSolicitudWsService;
	@Autowired
	private ConfigPerfilSolicitanteService configPerfilSolicitanteService;
	@Autowired
	private ConfigCalculoPresupuestoService configCalculoPresupuestoService;
	@Autowired
	private FormatosFicheroService formatosFicheroService;
	@Autowired
	private ClasificacionDocumentosService clasificacionDocumentosService;
	@Autowired
	private TiposDocumentoService tiposDocumentoService;
	@Autowired
	private IdiomaDestinoService idiomaDestinoService;
	@Autowired
	private ModosInterpretacionService modosInterpretacionService;
	@Autowired
	private TiposInterpretacionService tiposInterpretacionService;
	@Autowired
	private SolicitanteService solicitanteService;
	@Autowired()
	private ExpedienteService expedienteService;
	@Autowired()
	private ObservacionesExpedienteService observacionesExpedienteService;
	@Autowired()
	private ExpedientesRelacionadosService expedientesRelacionadosService;
	@Autowired()
	private ReceptorAutorizadoService receptorAutorizadoService;
	@Autowired()
	private DatosPersonaService datosPersonaService;
	@Autowired()
	private DireccionNoraService direccionNoraService;
	@Autowired
	private TipoRelevanciaService tipoRelevanciaService;
	@Autowired()
	private PIDService pidService;
	@Autowired()
	private Aa79bTareaWsService aa79bTareaWsService;
	@Autowired
	private DocumentosGeneralService documentosGeneralService;
	@Autowired
	private OidsAuxiliarService oidsAuxiliarService;
	@Autowired
	private SubidaFicherosService subidaFicherosService;
	@Autowired
	private PlantillasService plantillasService;
	@Autowired
	private ExcelReportService aa79bExcelReportService;

	public static final String MENSAJE_GESTOR_SIN_ACCESO = "mensaje.gestorSinAcceso";
	public static final String MENSAJE_SIN_PERMISOS_DE_USUARIO = "mensaje.gestorSinPermisos";
	public static final String MENSAJE_EXPEDIENTE_NO_ELIMINADO = "mensaje.expedienteNoEliminado";
	public static final String MENSAJE_SUBSANACION_REALIZADA = "mensaje.subsanacionRealizada";
	public static final String TITULO_JUSTIFICANTE_SOLICITUD = "titulo.justificanteSolicitud";
	public static final String MENSAJE_ERROR_RECEPTOR_AUTORIZADO_COUNT = "mensaje.errorReceptorAutorizadoCount";
	private static final String TABLA_REQUEST = "\ntablaRequest: ";
	private static final String MENSAJE_ERROR_OBTENER_DATOS_PRESUPUESTO_FACTURACION = "mensaje.errorObtenerDatosPresupFact";
	private static final String RUTA_PIF_AA06A = "rutaPifAa06a";

	/**
	 *
	 * @return String
	 */
	@WebMethod(operationName = "pruebaWS")
	public String prueba() {
		return "HolaMundo";
	}

	@WebMethod(operationName = "obtenerDatosInterpretacionWS")
	public DatosInterpretacion obtenerDatosInterpretacionWS() {

		DatosInterpretacion datosInterpretacion = new DatosInterpretacion();

		TiposInterpretacion tiposInterpretacionFilter = new TiposInterpretacion();
		tiposInterpretacionFilter.setEstado008(Constants.ALTA);

		List<TiposInterpretacion> tiposInterpretacion = this.tiposInterpretacionService
				.findAll(tiposInterpretacionFilter, null);

		ModosInterpretacion modosInterpretacionFilter = new ModosInterpretacion();
		modosInterpretacionFilter.setEstado(Constants.ALTA);

		List<ModosInterpretacion> modosInterpretacion = this.modosInterpretacionService
				.findAll(modosInterpretacionFilter, null);

		datosInterpretacion.setTiposInterpretacion(tiposInterpretacion);
		datosInterpretacion.setModosInterpretacion(modosInterpretacion);

		return datosInterpretacion;
	}

	@WebMethod(operationName = "obtenerDatosTraduccionRevisionWS")
	public SalidaDatosTraduccionRevision obtenerDatosTraduccionRevisionWS() {
		SalidaDatosTraduccionRevision salida = new SalidaDatosTraduccionRevision();

		IdiomaDestino idiomaDestino = new IdiomaDestino();
		idiomaDestino.setEstado(Constants.ALTA);
		salida.setIdiomaDestino(this.idiomaDestinoService.findAll(idiomaDestino, null));

		TiposDocumento tiposDocumentoFilter = new TiposDocumento();
		tiposDocumentoFilter.setEstado(Constants.ALTA);
		JQGridRequestDto jqGridRequestDto = new JQGridRequestDto();
		if ("ES".equalsIgnoreCase(LocaleContextHolder.getLocale().getLanguage())) {
			jqGridRequestDto.setSidx("ORDEN_042 ASC, DESC_ES_042");
		} else {
			jqGridRequestDto.setSidx("ORDEN_042 ASC, DESC_EU_042");
		}
		jqGridRequestDto.setSord("ASC");
		salida.setTiposDocumento(this.tiposDocumentoService.findAll(tiposDocumentoFilter, jqGridRequestDto));

		TipoRelevancia tipRelevancia = new TipoRelevancia();
		tipRelevancia.setEstado(Constants.ALTA);
		salida.setTipoRelevancia(this.tipoRelevanciaService.findAll(tipRelevancia, null));

		return salida;
	}

	@WebMethod(operationName = "obtenerGestoresRepresentanteWS")
	public GestoresRepresentante obtenerGestoresRepresentanteWS(EntradaGestoresRepresentante bean) {

		GestoresRepresentante gestoresRepresentante = new GestoresRepresentante();

		Solicitante solicitante = new Solicitante();
		solicitante.setDni(bean.getDni());

		List<Gestor> gestores = this.solicitanteService.findGestoresRespresentante(solicitante);

		gestoresRepresentante.setGestores(gestores);
		gestoresRepresentante.setEstado(Constants.ESTADO_WS_OK);

		return gestoresRepresentante;
	}

	/**
	 * @author javarona
	 *
	 * @param bean
	 *            EntradaFechaFinalizacion
	 * @return SalidaFechaFinalizacion
	 */
	@WebMethod(operationName = "obtenerDatosFechaFinalizacionWS")
	public SalidaFechaFinalizacion obtenerDatosFechaFinalizacionWS(EntradaFechaFinalizacion bean) {

		SalidaFechaFinalizacion salidaFechaFinalizacion;

		// Llamar a la funcion OBTENER_PLAZO_MINIMO pasandole los params de
		// EntradaFechaFinalizacion + solicitante.getGestorExpedientesVIP()
		salidaFechaFinalizacion = this.expedienteService.llamadaFncObtenerPlazoMinEntrega(bean);

		return salidaFechaFinalizacion;
	}

	@WebMethod(operationName = "obtenerNotasExpedienteWS")
	public NotasExpediente obtenerNotasExpedienteWS(EntradaNotasExpediente bean) {
		Locale locale = new Locale(bean.getIdioma());
		NotasExpediente notasExpediente = new NotasExpediente();
		DatosSalidaWS datosSalidaWS = this.tieneAccesoExpediente(bean.getDni(), bean.getAnyo(), bean.getNumExp(),
				locale);

		if (datosSalidaWS == null) {
			ObservacionesExpediente observaciones = this.obtenerObservaciones(bean);

			if (observaciones != null) {
				boolean existeFichero = observaciones.getOidFichero() != null;
				notasExpediente.setExisteFichero(existeFichero);
				notasExpediente.setObservaciones(observaciones.getObservaciones());
			}

			notasExpediente.setEstado(Constants.ESTADO_WS_OK);
		} else {
			notasExpediente.setEstado(datosSalidaWS.getEstado());
			notasExpediente.setDescripcion(datosSalidaWS.getDescripcion());
		}

		return notasExpediente;
	}

	@WebMethod(operationName = "obtenerFicheroObservacionesWS")
	public FicheroWS obtenerFicheroObservacionesWS(EntradaNotasExpediente bean) {
		Locale locale = new Locale(bean.getIdioma());
		FicheroWS fichero = new FicheroWS();
		DatosSalidaWS datosSalidaWS = this.tieneAccesoExpediente(bean.getDni(), bean.getAnyo(), bean.getNumExp(),
				locale);

		if (datosSalidaWS == null) {
			ObservacionesExpediente observaciones = this.obtenerObservaciones(bean);

			if (observaciones != null && observaciones.getOidFichero() != null) {
				fichero.setOid(observaciones.getOidFichero());
				fichero.setNombre(observaciones.getNombre());
				fichero.setContentType(observaciones.getContentType());
				fichero.setExtension(observaciones.getExtension());
				fichero.setTamano(observaciones.getTamano());
			}

			fichero.setEstado(Constants.ESTADO_WS_OK);
		} else {
			fichero.setEstado(datosSalidaWS.getEstado());
			fichero.setDescripcion(datosSalidaWS.getDescripcion());
		}

		return fichero;
	}

	@WebMethod(operationName = "obtenerDatosDocumentacionWS")
	public SalidaDatosDocumentacion obtenerDatosDocumentacionWS(EntradaDatosDocumentacion entradaDatosDocumentacion) {

		SalidaDatosDocumentacion salidaDatosDocumentacion = new SalidaDatosDocumentacion();

		TiposDocumento tiposDocumentoFilter = new TiposDocumento();
		tiposDocumentoFilter.setEstado(Constants.ALTA);
		JQGridRequestDto jqGridRequestDto = new JQGridRequestDto();
		if ("ES".equalsIgnoreCase(LocaleContextHolder.getLocale().getLanguage())) {
			jqGridRequestDto.setSidx("ORDEN_042 ASC, DESC_ES_042");
		} else {
			jqGridRequestDto.setSidx("ORDEN_042 ASC, DESC_EU_042");
		}
		jqGridRequestDto.setSord("ASC");
		salidaDatosDocumentacion.setTiposDocumento(this.tiposDocumentoService.findAll(tiposDocumentoFilter, jqGridRequestDto));

		ClasificacionDocumentos clasificacionDocumentosFilter = new ClasificacionDocumentos();
		clasificacionDocumentosFilter.setEstado075(Constants.ALTA);
		clasificacionDocumentosFilter.setTiposExpediente075(entradaDatosDocumentacion.getTipoExpediente());

		salidaDatosDocumentacion.setClasificacionDocumentos(
				this.clasificacionDocumentosService.findAllSinTrabajo(clasificacionDocumentosFilter));

		FormatosFichero formatosFicheroFilter = new FormatosFichero();
		formatosFicheroFilter.setEstado011(Constants.ALTA);

		salidaDatosDocumentacion.setFormatosFichero(this.formatosFicheroService.findAll(formatosFicheroFilter, null));

		ConfigCalculoPresupuesto configCalculoPresupuestoFilter = new ConfigCalculoPresupuesto();
		configCalculoPresupuestoFilter.setId(Constants.CONFIG_DEFAULT_ID);

		salidaDatosDocumentacion.setConfigCalculoPresupuesto(
				this.configCalculoPresupuestoService.findAll(configCalculoPresupuestoFilter, null));

		ConfigPerfilSolicitante configPerfilSolicitanteFilter = new ConfigPerfilSolicitante();
		configPerfilSolicitanteFilter.setId(Constants.CONFIG_DEFAULT_ID);

		salidaDatosDocumentacion.setConfigPerfilSolicitante(
				this.configPerfilSolicitanteService.findAll(configPerfilSolicitanteFilter, null));

		return salidaDatosDocumentacion;
	}

	@WebMethod(operationName = "obtenerDatosDeclaracionesWS")
	public Aa79bDatosDeclaraciones obtenerDatosDeclaracionesWS() {
		Aa79bDatosDeclaraciones aa79bDatosDeclaraciones = new Aa79bDatosDeclaraciones();

		ConfigPerfilSolicitante configPerfilSolicitanteFilter = new ConfigPerfilSolicitante();
		configPerfilSolicitanteFilter.setId(Constants.CONFIG_DEFAULT_ID);

		ConfigPerfilSolicitante configPerfilSolicitante = this.configPerfilSolicitanteService
				.find(configPerfilSolicitanteFilter);

		aa79bDatosDeclaraciones.setConfigPerfilSolicitante(configPerfilSolicitante);
		aa79bDatosDeclaraciones.setEstado(Constants.ESTADO_WS_OK);

		return aa79bDatosDeclaraciones;
	}

	@WebMethod(operationName = "obtenerDatosConsultaExpedientesWS")
	public DatosConsultaExp obtenerDatosConsultaExpedientesWS(EntradaGestoresRepresentante bean) {

		DatosConsultaExp datosConsultaExp = new DatosConsultaExp();

		Solicitante soli = new Solicitante();
		soli.setDni(bean.getDni());

		datosConsultaExp.setGestores(this.solicitanteService.findGestoresConsulta(soli));
		Date fecha = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		bean.setAnyo(Long.valueOf(String.valueOf(calendar.get(Calendar.YEAR)).substring(2)));

		datosConsultaExp.setTitulosExpediente(this.expedienteService.getTitulosExpediente(bean));

		datosConsultaExp.setCountGestorExpPdteTramCliente(this.expedienteService.countGestorExpPdteTramCliente(bean));

		return datosConsultaExp;
	}

	@WebMethod(operationName = "obtenerDetalleSubsanacionDatosWS")
	public SalidaObtenerDetalleSub obtenerDetalleSubsanacionDatosWS(EntradaObtenerDetalleSub bean) {
		Locale locale = new Locale(bean.getIdioma());
		SalidaObtenerDetalleSub salida = new SalidaObtenerDetalleSub();
		DatosSalidaWS datosSalidaWS = this.tieneAccesoExpediente(bean.getDni(), bean.getAnyo(), bean.getNumExp(),
				locale);

		if (datosSalidaWS == null) {
			Expediente exp = new Expediente();
			exp.setAnyo(bean.getAnyo());
			exp.setNumExp(bean.getNumExp());

			Expediente expediente = this.expedienteService.find(exp);
			if (Constants.NO.equals(expediente.getBitacoraExpediente().getSubsanacionExp().getIndSubsanado())) {

				SubsanacionExpediente detalleSub = this.expedienteService.getDetalleSubsanacion(expediente);
				List<CamposSelecSub> camposSub = this.expedienteService.getCampoSubsanacion(detalleSub);
				salida.setCamposSelecSub(camposSub);
				List<DocusSelecSub> docuSub = this.expedienteService.getDocuSubsanacion(detalleSub);
				salida.setDocusSelecSub(docuSub);
				salida.setDetalle(detalleSub.getDetalle());
				salida.setIndDocNuevos(detalleSub.getIndDocNuevos());
				salida.setFechaLimite(detalleSub.getFechaLimite().getTime());
				salida.setEstado(Constants.ESTADO_WS_OK);

			} else {
				salida.setEstado(Constants.ESTADO_WS_ERROR);
				salida.setDescripcion(this.appMessageSource.getMessage(MENSAJE_SUBSANACION_REALIZADA, null, locale));
			}
		} else {
			salida.setEstado(datosSalidaWS.getEstado());
			salida.setDescripcion(datosSalidaWS.getDescripcion());
		}

		return salida;
	}

	@WebMethod(operationName = "buscadorPersonasGIPWS")
	public Aa79bTablaWebService<Aa79bPersona> buscadorPersonasGIPWS(String dni, Aa79bPersona bean,
			Aa79bTablaWebService<Aa79bPersona> aa79bTablaWebService) {
		ExpedienteWSImpl.LOGGER.info("WS_METHOD buscadorPersonasGIPWS BEGIN\nfilter: " + dni + " - " + bean
				+ TABLA_REQUEST + aa79bTablaWebService);
		Locale locale = new Locale(bean.getIdioma());
		Aa79bTablaWebService<Aa79bPersona> tabla = new Aa79bTablaWebService<Aa79bPersona>();
		try {
			JQGridRequestDto jqGridRequestDto = null;
			if (aa79bTablaWebService != null && Utils.isValidAa79bTablaWebService(aa79bTablaWebService)) {
				jqGridRequestDto = new JQGridRequestDto(aa79bTablaWebService.getRows(), aa79bTablaWebService.getPage(),
						aa79bTablaWebService.getSidx(), aa79bTablaWebService.getSord());
			}

			final JQGridResponseDto<Aa79bPersona> jqGrid = this.datosPersonaService.findPersonasGIP(dni, bean,
					jqGridRequestDto, false);
			tabla.setEstado(Constants.ESTADO_WS_OK);
			@SuppressWarnings("unchecked")
			final Aa79bJaxbList<Aa79bPersona> lista = new Aa79bJaxbList<Aa79bPersona>(
					(List<Aa79bPersona>) jqGrid.getRows());
			tabla.setLista(lista);
			tabla.setRecordNum(Long.valueOf(jqGrid.getRecords()));
			ExpedienteWSImpl.LOGGER.info("WS_METHOD buscadorPersonasGIPWS END\nreturn" + tabla);
		} catch (Exception e) {
			ExpedienteWSImpl.LOGGER.error("[POST - subm]: buscadorPersonasGIPWS ", e);
			tabla.setSidx("-1");
			tabla.setSord(e.getMessage());
			tabla.setEstado(Constants.ESTADO_WS_ERROR);
			tabla.setDescripcion(this.appMessageSource.getMessage(MENSAJE_SIN_PERMISOS_DE_USUARIO, null, locale));
		}
		return tabla;

		// return
	}

	@WebMethod(operationName = "obtenerReceptoresAutorizadosWS")
	public Aa79bTablaWebService<ReceptorAutorizado> obtenerReceptoresAutorizadosWS(EntradaNotasExpediente bean,
			Aa79bTablaWebService<ReceptorAutorizado> aa79bTablaWebService) {
		ExpedienteWSImpl.LOGGER.info("obtenerReceptoresAutorizadosWS - tablaRequest: " + aa79bTablaWebService);
		Locale locale = new Locale(bean.getIdioma());
		Aa79bTablaWebService<ReceptorAutorizado> tabla = new Aa79bTablaWebService<ReceptorAutorizado>();
		try {
			JQGridRequestDto jqGridRequestDto = null;
			if (aa79bTablaWebService != null && Utils.isValidAa79bTablaWebService(aa79bTablaWebService)) {
				jqGridRequestDto = new JQGridRequestDto(aa79bTablaWebService.getRows(), aa79bTablaWebService.getPage(),
						aa79bTablaWebService.getSidx(), aa79bTablaWebService.getSord());

			}

			Solicitante solicitante = new Solicitante();
			solicitante.setDni(bean.getDni());
			Boolean permisoUsuario = this.receptorAutorizadoService.getPermisosUsuario(solicitante);
			GestorExpediente gestor = new GestorExpediente();
			gestor.setSolicitante(solicitante);

			Expediente exp = new Expediente();
			exp.setAnyo(bean.getAnyo());
			exp.setNumExp(bean.getNumExp());
			exp.setGestorExpediente(gestor);

			if (permisoUsuario) {
				Boolean accesoExpediente = this.receptorAutorizadoService.getAccesoExpediente(exp);
				if (accesoExpediente) {
					final JQGridResponseDto<ReceptorAutorizado> jqGrid = this.receptorAutorizadoService
							.getReceptoresAutorizados(exp, jqGridRequestDto, false);
					tabla.setEstado(Constants.ESTADO_WS_OK);
					@SuppressWarnings("unchecked")
					final Aa79bJaxbList<ReceptorAutorizado> lista = new Aa79bJaxbList<ReceptorAutorizado>(
							(List<ReceptorAutorizado>) jqGrid.getRows());
					tabla.setLista(lista);
					tabla.setRecordNum(Long.valueOf(jqGrid.getRecords()));
				} else {
					tabla.setEstado(Constants.ESTADO_WS_ERROR);
					tabla.setDescripcion(this.appMessageSource.getMessage(MENSAJE_GESTOR_SIN_ACCESO, null, locale));
				}
			} else {
				tabla.setEstado(Constants.ESTADO_WS_ERROR);
				tabla.setDescripcion(this.appMessageSource.getMessage(MENSAJE_SIN_PERMISOS_DE_USUARIO, null, locale));
			}

			ExpedienteWSImpl.LOGGER.info("obtenerReceptoresAutorizadosWS END\nreturn" + tabla);
		} catch (Exception e) {
			ExpedienteWSImpl.LOGGER.error("[POST - subm]: obtenerReceptoresAutorizadosWS ", e);
			tabla.setSidx("-1");
			tabla.setSord(e.getMessage());
			tabla.setEstado(Constants.ESTADO_WS_ERROR);
			tabla.setDescripcion(this.appMessageSource.getMessage(MENSAJE_SIN_PERMISOS_DE_USUARIO, null, locale));
		}

		return tabla;
	}

	@WebMethod(operationName = "obtenerExpedientesWS")
	public Aa79bTablaWebService<Aa79bExpediente> obtenerExpedientesWS(String dni, Aa79bExpediente bean,
			Aa79bTablaWebService<Aa79bExpediente> aa79bTablaWebService) {
		ExpedienteWSImpl.LOGGER.info("WS_METHOD obtenerExpedientesWS BEGIN\nfilter: " + dni + " - " + bean
				+ TABLA_REQUEST + aa79bTablaWebService);
		Locale locale = new Locale(bean.getIdioma());
		Solicitante solicitante = new Solicitante();
		// Comprobamos si el usuario tiene permisos y si es gestor y/o
		// coordinador
		solicitante = this.obtenerGestorCoordinador(dni);
		Aa79bTablaWebService<Aa79bExpediente> tabla = new Aa79bTablaWebService<Aa79bExpediente>();

		if (solicitante != null) {
			try {

				JQGridRequestDto jqGridRequestDto = null;
				if (aa79bTablaWebService != null && Utils.isValidAa79bTablaWebService(aa79bTablaWebService)) {
					jqGridRequestDto = new JQGridRequestDto(aa79bTablaWebService.getRows(),
							aa79bTablaWebService.getPage(), aa79bTablaWebService.getSidx(),
							aa79bTablaWebService.getSord());

				}

				final JQGridResponseDto<Aa79bExpediente> jqGrid = this.aa79bExpedienteWsService
						.obtenerExpedientes(solicitante, bean, jqGridRequestDto, false);
				tabla.setEstado(Constants.ESTADO_WS_OK);
				@SuppressWarnings(value = "unchecked")
				final Aa79bJaxbList<Aa79bExpediente> lista = new Aa79bJaxbList<Aa79bExpediente>(
						(List<Aa79bExpediente>) jqGrid.getRows());
				tabla.setLista(lista);
				tabla.setRecordNum(Long.valueOf(jqGrid.getRecords()));

				ExpedienteWSImpl.LOGGER.info("WS_METHOD obtenerExpedientesWS END\nreturn" + tabla);

			} catch (Exception e) {
				ExpedienteWSImpl.LOGGER.error("[POST - subm]: obtenerExpedientesWS ", e);
				tabla.setSidx("-1");
				tabla.setSord(e.getMessage());
				tabla.setEstado(Constants.ESTADO_WS_ERROR);
				tabla.setDescripcion(this.appMessageSource.getMessage(MENSAJE_SIN_PERMISOS_DE_USUARIO, null, locale));
			}
		} else {
			tabla.setEstado(Constants.ESTADO_WS_ERROR);
			tabla.setDescripcion(this.appMessageSource.getMessage(MENSAJE_SIN_PERMISOS_DE_USUARIO, null, locale));
		}

		return tabla;

	}

	@WebMethod(operationName = "consultarExpReceptoresWS")
	public Aa79bTablaWebService<Aa79bExpediente> consultarExpReceptoresWS(String dni, Aa79bExpediente bean,
			Aa79bTablaWebService<Aa79bExpediente> aa79bTablaWebService) {
		ExpedienteWSImpl.LOGGER.info("WS_METHOD consultarExpReceptoresWS BEGIN\nfilter: " + dni + " - " + bean
				+ TABLA_REQUEST + aa79bTablaWebService);
		Locale locale = new Locale(bean.getIdioma());
		Solicitante solicitante = new Solicitante();
		// Comprobamos si el usuario tiene permisos y si es gestor y/o
		// coordinador
		solicitante.setDni(dni);
		Aa79bTablaWebService<Aa79bExpediente> tabla = new Aa79bTablaWebService<Aa79bExpediente>();

		try {

			JQGridRequestDto jqGridRequestDto = null;
			if (aa79bTablaWebService != null && Utils.isValidAa79bTablaWebService(aa79bTablaWebService)) {
				jqGridRequestDto = new JQGridRequestDto(aa79bTablaWebService.getRows(), aa79bTablaWebService.getPage(),
						aa79bTablaWebService.getSidx(), aa79bTablaWebService.getSord());

			}

			final JQGridResponseDto<Aa79bExpediente> jqGrid = this.aa79bExpedienteWsService
					.consultarExpReceptores(solicitante, bean, jqGridRequestDto, false);
			tabla.setEstado(Constants.ESTADO_WS_OK);
			@SuppressWarnings(value = "unchecked")
			final Aa79bJaxbList<Aa79bExpediente> lista = new Aa79bJaxbList<Aa79bExpediente>(
					(List<Aa79bExpediente>) jqGrid.getRows());
			tabla.setLista(lista);
			tabla.setRecordNum(Long.valueOf(jqGrid.getRecords()));

			ExpedienteWSImpl.LOGGER.info("WS_METHOD consultarExpReceptoresWS END\nreturn" + tabla);

		} catch (Exception e) {
			ExpedienteWSImpl.LOGGER.error("[POST - subm]: consultarExpReceptoresWS ", e);
			tabla.setSidx("-1");
			tabla.setSord(e.getMessage());
			tabla.setEstado(Constants.ESTADO_WS_ERROR);
			tabla.setDescripcion(this.appMessageSource.getMessage(MENSAJE_SIN_PERMISOS_DE_USUARIO, null, locale));
		}

		return tabla;

	}

	@WebMethod(operationName = "eliminarExpedienteWS")
	public DatosSalidaWS eliminarExpedienteWS(String dni, Aa79bExpediente bean) {
		ExpedienteWSImpl.LOGGER.info("WS_METHOD eliminarExpedienteWS BEGIN\nfilter: " + dni + " - " + bean);
		Locale locale = new Locale(bean.getIdioma());
		DatosSalidaWS datosSalidaWs = new DatosSalidaWS();

		// Comprobamos si el usuario tiene permisos y que el usuario tiene
		// acceso al expediente
		datosSalidaWs = this.tieneAccesoExpediente(dni, bean.getAnyo(), bean.getNumExp(), locale);
		if (datosSalidaWs == null) {
			// eliminamos el expediente
			Integer eliminado = this.aa79bExpedienteWsService.eliminarExpediente(bean);
			datosSalidaWs = new DatosSalidaWS();
			// Comprobamos que lo hemos eliminado
			if (eliminado > Constants.CERO) {
				datosSalidaWs.setEstado(Constants.ESTADO_WS_OK);
				ExpedienteWSImpl.LOGGER.info("WS_METHOD obtenerExpedientesWS END\nreturn" + datosSalidaWs);
			} else {
				datosSalidaWs.setEstado(Constants.ESTADO_WS_ERROR);
				datosSalidaWs.setDescripcion(
						this.appMessageSource.getMessage(MENSAJE_EXPEDIENTE_NO_ELIMINADO, null, locale));
			}
		}

		return datosSalidaWs;

	}

	@WebMethod(operationName = "buscarExpedientesRelacionadosWS")
	public Aa79bTablaWebService<Aa79bExpediente> buscarExpedientesRelacionadosWS(String dni, Aa79bExpediente bean,
			Aa79bTablaWebService<Aa79bExpediente> aa79bTablaWebService) {
		ExpedienteWSImpl.LOGGER.info("WS_METHOD buscarExpedientesRelacionadosWS BEGIN\nfilter: " + dni + " - " + bean
				+ TABLA_REQUEST + aa79bTablaWebService);
		Locale locale = new Locale(bean.getIdioma());
		Solicitante solicitante = new Solicitante();
		// Comprobamos si el usuario tiene permisos y si es gestor y/o
		// coordinador
		solicitante = this.obtenerGestorCoordinador(dni);
		// Comprobamos si el usuario tiene permisos y que el usuario tiene
		// acceso al expediente
		Aa79bTablaWebService<Aa79bExpediente> tabla = new Aa79bTablaWebService<Aa79bExpediente>();
		if (solicitante != null) {
			try {

				JQGridRequestDto jqGridRequestDto = new JQGridRequestDto();
				if (aa79bTablaWebService != null && Utils.isValidAa79bTablaWebService(aa79bTablaWebService)) {
					jqGridRequestDto = new JQGridRequestDto(aa79bTablaWebService.getRows(),
							aa79bTablaWebService.getPage(), aa79bTablaWebService.getSidx(),
							aa79bTablaWebService.getSord());
				} else {
					jqGridRequestDto = null;
				}
				final JQGridResponseDto<Aa79bExpediente> jqGrid = this.aa79bExpedienteWsService
						.buscarExpedientesRelacionados(solicitante, bean, jqGridRequestDto, false);
				tabla.setEstado(Constants.ESTADO_WS_OK);
				@SuppressWarnings(value = "unchecked")
				final Aa79bJaxbList<Aa79bExpediente> lista = new Aa79bJaxbList<Aa79bExpediente>(
						(List<Aa79bExpediente>) jqGrid.getRows());
				tabla.setLista(lista);
				tabla.setRecordNum(Long.valueOf(jqGrid.getRecords()));
				ExpedienteWSImpl.LOGGER.info("WS_METHOD buscarExpedientesRelacionadosWS END\nreturn" + tabla);

			} catch (Exception e) {
				ExpedienteWSImpl.LOGGER.error("[POST - subm]: buscarExpedientesRelacionadosWS ", e);
				tabla.setSidx("-1");
				tabla.setSord(e.getMessage());

				tabla.setEstado(Constants.ESTADO_WS_ERROR);
				tabla.setDescripcion(this.appMessageSource.getMessage(MENSAJE_SIN_PERMISOS_DE_USUARIO, null, locale));

			}

		} else {
			tabla.setEstado(Constants.ESTADO_WS_ERROR);
			tabla.setDescripcion(this.appMessageSource.getMessage(MENSAJE_SIN_PERMISOS_DE_USUARIO, null, locale));
		}
		return tabla;
	}

	@WebMethod(operationName = "obtenerInfoBitacoraWS")
	public InfoBitacora obtenerInfoBitacoraWS(EntradaInfoBitacora bean) {
		Locale locale = new Locale(bean.getIdioma());
		InfoBitacora infoBitacora = new InfoBitacora();
		DatosSalidaWS datosSalidaWS = this.tieneAccesoExpediente(bean.getDni(), bean.getAnyo(), bean.getNumExp(),
				locale);

		if (datosSalidaWS == null) {
			BitacorasSolicitante bitacorasSolicitante = new BitacorasSolicitante();
			Expediente expediente = new Expediente();
			expediente.setAnyo(bean.getAnyo());
			expediente.setNumExp(bean.getNumExp());

			List<BitacoraSolicitante> listaBitacoraSolicitante = this.aa79bExpedienteWsService
					.findInfoBitacora(expediente);
			bitacorasSolicitante.setBitacoraSolicitante(listaBitacoraSolicitante);

			infoBitacora.setBitacorasSolicitante(bitacorasSolicitante);
			infoBitacora.setEstado(Constants.ESTADO_WS_OK);
		} else {
			infoBitacora.setEstado(datosSalidaWS.getEstado());
			infoBitacora.setDescripcion(datosSalidaWS.getDescripcion());
		}

		return infoBitacora;
	}

	@WebMethod(operationName = "obtenerCabeceraExpedienteWS")
	public CabeceraExpediente obtenerCabeceraExpedienteWS(EntradaCabeceraExpediente bean) {
		CabeceraExpediente cabeceraExpediente = new CabeceraExpediente();
		Expediente expediente = new Expediente();
		expediente.setAnyo(bean.getAnyo());
		expediente.setNumExp(bean.getNumExp());

		cabeceraExpediente = this.aa79bExpedienteWsService.findCabeceraExpediente(expediente);

		cabeceraExpediente.setEstado(Constants.ESTADO_WS_OK);

		return cabeceraExpediente;
	}

	@WebMethod(operationName = "obtenerDatosExpedienteWS")
	public DatosExpediente obtenerDatosExpedienteWS(EntradaDatosExpediente bean) {
		Locale locale = new Locale(bean.getIdioma());
		DatosExpediente datosExpediente = new DatosExpediente();
		DatosSalidaWS datosSalidaWS;
		if (bean.getComprPermisos()) {
			datosSalidaWS = this.tieneAccesoExpediente(bean.getDni(), bean.getAnyo(), bean.getNumExp(), locale);
		} else {
			datosSalidaWS = null;
		}

		if (datosSalidaWS == null) {
			Expediente expediente = new Expediente();
			expediente.setAnyo(bean.getAnyo());
			expediente.setNumExp(bean.getNumExp());

			Aa79bExpediente expedienteRst = this.aa79bExpedienteWsService.findDatosExpediente(expediente);
			if (TipoExpedienteEnum.INTERPRETACION.getValue().equals(expedienteRst.getTipoExpediente().getCodigo())
					&& expedienteRst.getDatosInterpretacion() != null) {
				DireccionNora direccionNora = this.direccionNoraService
						.find(expedienteRst.getDatosInterpretacion().getDirNora().getDireccion());
				Aa79bDireccionNora direccion = new Aa79bDireccionNora();
				direccion.setDireccion(Utils.obtenerDireccion(direccionNora));
				expedienteRst.getDatosInterpretacion().setDirNora(direccion);
			}

			datosExpediente.setExpediente(expedienteRst);
			datosExpediente.setEstado(Constants.ESTADO_WS_OK);
		} else {
			datosExpediente.setEstado(datosSalidaWS.getEstado());
			datosExpediente.setDescripcion(datosSalidaWS.getDescripcion());
		}

		return datosExpediente;
	}

	@WebMethod(operationName = "obtenerExpedientesRelacionadosWS")
	public Aa79bTablaWebService<Aa79bExpedienteRelacionado> obtenerExpedientesRelacionadosWS(
			EntradaExpedientesRelacionados entradaExpedientesRelacionados,
			Aa79bTablaWebService<Aa79bExpediente> aa79bTablaWebService) {
		ExpedienteWSImpl.LOGGER.info("WS_METHOD obtenerExpedientesRelacionadosWS BEGIN\nfilter: "
				+ entradaExpedientesRelacionados.getDni() + " - " + entradaExpedientesRelacionados.getAnyo() + " - "
				+ entradaExpedientesRelacionados.getNumExp() + TABLA_REQUEST + aa79bTablaWebService);
		Locale locale = new Locale(entradaExpedientesRelacionados.getIdioma());

		Aa79bExpediente aa79bExpediente = new Aa79bExpediente();
		aa79bExpediente.setAnyo(entradaExpedientesRelacionados.getAnyo());
		aa79bExpediente.setNumExp(entradaExpedientesRelacionados.getNumExp());
		Aa79bGestorExpediente gestor = new Aa79bGestorExpediente();
		gestor.setDniGestor(entradaExpedientesRelacionados.getDni());
		aa79bExpediente.setGestor(gestor);

		DatosSalidaWS datosSalidaWS = this.tieneAccesoExpediente(entradaExpedientesRelacionados.getDni(),
				aa79bExpediente.getAnyo(), aa79bExpediente.getNumExp(), locale);
		Aa79bTablaWebService<Aa79bExpedienteRelacionado> tabla = new Aa79bTablaWebService<Aa79bExpedienteRelacionado>();

		if (datosSalidaWS == null) {
			try {
				JQGridRequestDto jqGridRequestDto = null;
				if (aa79bTablaWebService != null && Utils.isValidAa79bTablaWebService(aa79bTablaWebService)) {
					jqGridRequestDto = new JQGridRequestDto(aa79bTablaWebService.getRows(),
							aa79bTablaWebService.getPage(), aa79bTablaWebService.getSidx(),
							aa79bTablaWebService.getSord());

				} else {
					jqGridRequestDto = null;
				}
				final JQGridResponseDto<Aa79bExpedienteRelacionado> jqGrid = this.aa79bExpedienteWsService
						.findExpedientesRelacionados(aa79bExpediente, jqGridRequestDto, false);
				tabla.setEstado(Constants.ESTADO_WS_OK);
				@SuppressWarnings(value = "unchecked")
				final Aa79bJaxbList<Aa79bExpedienteRelacionado> lista = new Aa79bJaxbList<Aa79bExpedienteRelacionado>(
						(List<Aa79bExpedienteRelacionado>) jqGrid.getRows());
				tabla.setLista(lista);
				tabla.setRecordNum(Long.valueOf(jqGrid.getRecords()));
				ExpedienteWSImpl.LOGGER.info("WS_METHOD obtenerExpedientesRelacionadosWS END\nreturn" + tabla);

			} catch (Exception e) {
				ExpedienteWSImpl.LOGGER.error("[POST - subm]: obtenerExpedientesRelacionadosWS ", e);
				tabla.setSidx("-1");
				tabla.setSord(e.getMessage());

				tabla.setEstado(Constants.ESTADO_WS_ERROR);
				tabla.setDescripcion(this.appMessageSource.getMessage(MENSAJE_SIN_PERMISOS_DE_USUARIO, null, locale));
			}
		} else {
			ExpedienteWSImpl.LOGGER.error("[POST - subm]: obtenerExpedientesRelacionadosWS ", "mensaje error");
			tabla.setSidx("-1");
			tabla.setSord("mensaje error");

			tabla.setEstado(Constants.ESTADO_WS_ERROR);
			tabla.setDescripcion(this.appMessageSource.getMessage(MENSAJE_SIN_PERMISOS_DE_USUARIO, null, locale));
		}

		return tabla;
	}

	@WebMethod(operationName = "obtenerExpedientesSeleccionadosWS")
	public DatosExpedientesRelacionados obtenerExpedientesSeleccionadosWS(String dni, String selectedIds) {
		DatosExpedientesRelacionados datosExpedientesRel = new DatosExpedientesRelacionados();
		DatosSalidaWS datosSalidaWS = new DatosSalidaWS();
		Solicitante solicitante = new Solicitante();
		solicitante = this.obtenerGestorCoordinador(dni);

		ListaExpedientesRelacionados lstExpedientesRel = new ListaExpedientesRelacionados();

		List<Aa79bExpedienteRelacionado> rstExpedientesRel = this.aa79bExpedienteWsService
				.obtenerExpedientesSeleccionados(solicitante, selectedIds);
		if (rstExpedientesRel != null && !rstExpedientesRel.isEmpty()) {
			lstExpedientesRel.setExpedienteRelacionado(rstExpedientesRel);

			datosExpedientesRel.setExpedientesRelacionados(lstExpedientesRel);

			datosExpedientesRel.setEstado(Constants.ESTADO_WS_OK);
		} else {
			datosExpedientesRel.setEstado(Constants.ESTADO_WS_ERROR);
			datosExpedientesRel.setDescripcion(datosSalidaWS.getDescripcion());
		}

		return datosExpedientesRel;
	}

	@WebMethod(operationName = "obtenerJustificanteSolicitudWS")
	public FicheroWS obtenerJustificanteSolicitudWS(EntradaDatosSolicitud bean) {

		ModelMap modelMap = new ModelMap();

		Expediente filterExpediente = new Expediente();
		String anyoSubstring = bean.getAnyo().toString().substring(Constants.DOS);
		filterExpediente.setAnyo(new Long(anyoSubstring));
		filterExpediente.setNumExp(bean.getNumExp());

		List<Expediente> exp = this.expedienteService.getJustificanteSolicitud(filterExpediente);
		filterExpediente.setAnyo(bean.getAnyo());

		int numExpDigits = String.valueOf((int) exp.get(Constants.CERO).getNumExp()).length();
		StringBuilder numExpFormated = this.obtenerNumExpConCeros(numExpDigits);
		exp.get(Constants.CERO).setNumExpFormated(exp.get(Constants.CERO).getAnyo().toString().substring(Constants.DOS)
				+ "/" + numExpFormated + exp.get(Constants.CERO).getNumExp());

		if (TipoExpedienteEnum.INTERPRETACION.getValue().equals(exp.get(Constants.CERO).getIdTipoExpediente())) {
			DireccionNora dirNora = exp.get(Constants.CERO).getExpedienteInterpretacion().getDirNora();
			if (dirNora != null) {
				dirNora = this.direccionNoraService.find(dirNora);
				exp.get(Constants.CERO).getExpedienteInterpretacion().setDirNora(dirNora);
				exp.get(Constants.CERO).getExpedienteInterpretacion().getDirNora()
						.setTxtDirec(Utils.obtenerDireccion(dirNora).getTxtDirec());
			}
		} else {
			List<ExpedientesRelacionados> expRelacionados = this.expedientesRelacionadosService
					.selectExpRelacionadosList(filterExpediente);
			if (!expRelacionados.isEmpty()) {
				for (int i = Constants.CERO; i < expRelacionados.size(); i++) {
					int numExpRelDigits = String.valueOf((int) expRelacionados.get(i).getNumExpRel()).length();
					StringBuilder numExpRelFormated = this.obtenerNumExpConCeros(numExpRelDigits);
					expRelacionados.get(i).setNumExpFormated(
							expRelacionados.get(i).getAnyoExpRel().toString().substring(Constants.DOS) + "/"
									+ numExpRelFormated + expRelacionados.get(i).getNumExpRel());
				}
			}
			exp.get(Constants.CERO).setListaExpedientesRelacionados(expRelacionados);
		}

		ConfigPerfilSolicitante configPerfilSolicitanteFilter = new ConfigPerfilSolicitante();
		configPerfilSolicitanteFilter.setId(Constants.CONFIG_DEFAULT_ID);

		ConfigPerfilSolicitante configPerfilSolicitante = this.configPerfilSolicitanteService
				.find(configPerfilSolicitanteFilter);

		EntradaLibroRegistroSol entradaLibroRegistroSol = new EntradaLibroRegistroSol();
		entradaLibroRegistroSol.setAnyo(bean.getAnyo());
		entradaLibroRegistroSol.setNumExp(bean.getNumExp());
		List<LibroRegistro> libroRegistro = this.configLibroRegistroService
				.getlibroRegistroSol(entradaLibroRegistroSol);

		Locale locale = new Locale(bean.getIdioma());

		Locale localEu = new Locale("eu");
		Locale localEs = new Locale("es");

		this.mapearValoresSolicitud(modelMap, configPerfilSolicitante, libroRegistro, locale, localEu, localEs);

		final List<Expediente> rdo2 = generarListadoDocsJustificante(bean, exp);
		modelMap.put("lDatos", rdo2);

		JasperPrint jasperPrint = null;
		byte[] bytesInforme = null;
		try {
			Resource mainReport;
			if (TipoExpedienteEnum.INTERPRETACION.getValue().equals(exp.get(Constants.CERO).getIdTipoExpediente())) {
				mainReport = this.ctx.getResource(Constants.RUTA_JASPER_JUSTIFICANTE_INTERPRETACION);
			} else {
				mainReport = this.ctx.getResource(Constants.RUTA_JASPER_JUSTIFICANTE_TRADREV);
			}

			JasperReport jasperReport = new JasperUtils().loadReport(mainReport);
			jasperPrint = JasperFillManager.fillReport(jasperReport, modelMap, new JRBeanCollectionDataSource(rdo2));
			bytesInforme = JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (JRException e) {
			ExpedienteWSImpl.LOGGER.error("Error pdfSolicitudes " + e);
		}

		FicheroWS ficheroBean = new FicheroWS();

		ficheroBean.setNombre(this.appMessageSource.getMessage(TITULO_JUSTIFICANTE_SOLICITUD, null, locale));
		ficheroBean.setExtension("." + Constants.FILE_PDF);
		ficheroBean.setContentType(Constants.CONTENT_TYPE_PDF);

		try {
			ficheroBean.setRutaPif(this.pidService.subidaPif(ficheroBean.getNombre() + ficheroBean.getExtension(),
					bytesInforme, this.appConfiguration.getProperty("rutaPifAa06a"), false));
		} catch (UnsupportedEncodingException e) {
			ExpedienteWSImpl.LOGGER.info("Error UnsupportedEncodingException: " + e);
			ficheroBean.setEstado(Constants.ESTADO_WS_ERROR);
			ficheroBean.setDescripcion("ERROR UnsupportedEncodingException");

		} catch (Exception e) {
			ExpedienteWSImpl.LOGGER.error("pruebapid: JOSE - subidaFichero Error: ", e);
			ficheroBean.setEstado(Constants.ESTADO_WS_ERROR);
			ficheroBean.setDescripcion(this.appMessageSource.getMessage("mensaje.fichero.errorPIF", null, locale));
		}

		return ficheroBean;
	}

	/**
	 * @param bean
	 * @param exp
	 * @return
	 */
	private List<Expediente> generarListadoDocsJustificante(EntradaDatosSolicitud bean, List<Expediente> exp) {
		List<List<Expediente>> datos = Utils.separarLista(exp, "numExp");
		final List<Expediente> rdo2 = new ArrayList<Expediente>();
		for (List<Expediente> list : datos) {
			for (Expediente docusList : list) {
				DocumentosExpediente filterDocumentosExpediente = new DocumentosExpediente();
				filterDocumentosExpediente.setAnyo(bean.getAnyo());
				filterDocumentosExpediente.setNumExp(bean.getNumExp());
				List<DocumentosExpediente> listDocu = this.documentosExpedienteService
						.findAll(filterDocumentosExpediente, null);
				docusList.setDocumentosExpediente(listDocu);
				rdo2.add(docusList);
			}

		}
		return rdo2;
	}

	private void mapearValoresSolicitud(ModelMap modelMap, ConfigPerfilSolicitante configPerfilSolicitante,
			List<LibroRegistro> libroRegistro, Locale locale, Locale localEu, Locale localEs) {
		// Nombre fichero
		modelMap.put("fileName", this.appMessageSource.getMessage(TITULO_JUSTIFICANTE_SOLICITUD, null, locale));
		// En línea (no descarga fichero) ?
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

	@WebMethod(operationName = "realizarSubsanacionDatos")
	public DatosSalidaWS realizarSubsanacionDatos(EntradaRegistrarSubsanacion bean) {

		Expediente exp = new Expediente();
		exp.setAnyo(bean.getExpediente().getAnyo());
		exp.setNumExp(bean.getExpediente().getNumExp());

		Locale locale = new Locale(bean.getExpediente().getIdioma());

		DatosSalidaWS salida = new DatosSalidaWS();
		DatosSalidaWS datosSalidaWS = this.tieneAccesoExpediente(bean.getDni(), exp.getAnyo(), exp.getNumExp(), locale);

		if (datosSalidaWS == null) {
			SubsanacionExpediente detalleSub = this.expedienteService.getDetalleSubsanacion(exp);
			this.expedienteService.updateIndSubsanacion(detalleSub);
			salida.setEstado(Constants.ESTADO_WS_OK);

			GestorExpediente gestor = this.expedienteService.getDatosContacto(exp);

			final List<String> listaDestinatarios = new ArrayList<String>();

			if (this.appConfiguration.getProperty(Constants.APP_ENTORNO) != null) {
				if (Constants.LOCAL.equals(this.appConfiguration.getProperty(Constants.APP_ENTORNO))) {
					listaDestinatarios.add(Constants.DESTINATARIO_EMAIL_LOCAL);
				} else {
					listaDestinatarios.add(gestor.getSolicitante().getDatosContacto().getEmail031());
				}

				ParametrosEmail parametrosEmail = new ParametrosEmail();
				this.mailService.sendMailWithParameters(TipoAvisoEnum.REQ_SUBSANACION.getValue(), listaDestinatarios, parametrosEmail);
			}

		} else {
			salida.setEstado(datosSalidaWS.getEstado());
			salida.setDescripcion(datosSalidaWS.getDescripcion());
		}
		return salida;
	}

	@WebMethod(operationName = "eliminarReceptorAutorizadoWS")
	public DatosSalidaWS eliminarReceptorAutorizadoWS(EntradaReceptorAutorizado entradaReceptorAutorizado) {
		ExpedienteWSImpl.LOGGER.info("eliminarReceptorAutorizadoWS");
		Locale locale = new Locale(entradaReceptorAutorizado.getIdioma());

		ReceptorAutorizado receptorAutorizado = new ReceptorAutorizado();
		receptorAutorizado.setAnyo(entradaReceptorAutorizado.getAnyo());
		receptorAutorizado.setNumExp(entradaReceptorAutorizado.getNumExp());
		receptorAutorizado.setDni(entradaReceptorAutorizado.getDniReceptor());

		// Comprobamos si el usuario tiene permisos y que el usuario tiene
		// acceso al expediente
		DatosSalidaWS datosSalida = this.tieneAccesoExpediente(entradaReceptorAutorizado.getDni(),
				receptorAutorizado.getAnyo(), receptorAutorizado.getNumExp(), locale);
		if (datosSalida == null) {
			this.receptorAutorizadoService.remove(receptorAutorizado);

			datosSalida = new DatosSalidaWS();
			datosSalida.setEstado(Constants.ESTADO_WS_OK);
		} else {
			datosSalida.setEstado(Constants.ESTADO_WS_ERROR);
			datosSalida.setDescripcion(this.appMessageSource.getMessage(MENSAJE_SIN_PERMISOS_DE_USUARIO, null, locale));
		}

		return datosSalida;

	}

	@WebMethod(operationName = "guardarReceptoresAutorizadosWS")
	public DatosSalidaWS guardarReceptoresAutorizadosWS(EntradaReceptoresAutorizados entradaReceptoresAutorizados) {
		ExpedienteWSImpl.LOGGER.info("guardarReceptoresAutorizadosWS");
		Locale locale = new Locale(entradaReceptoresAutorizados.getIdioma());

		// Comprobamos si el usuario tiene permisos y que el usuario tiene
		// acceso al expediente
		DatosSalidaWS datosSalida = this.tieneAccesoExpediente(entradaReceptoresAutorizados.getDni(),
				entradaReceptoresAutorizados.getAnyo(), entradaReceptoresAutorizados.getNumExp(), locale);

		if (datosSalida == null) {
			this.receptorAutorizadoService
					.guardarReceptoresAutorizados(entradaReceptoresAutorizados.getListaReceptoresAutorizados());

			datosSalida = new DatosSalidaWS();
			datosSalida.setEstado(Constants.ESTADO_WS_OK);
		} else {
			datosSalida.setEstado(Constants.ESTADO_WS_ERROR);
			datosSalida.setDescripcion(this.appMessageSource.getMessage(MENSAJE_SIN_PERMISOS_DE_USUARIO, null, locale));
		}
		return datosSalida;
	}

	/**
	 *
	 * @param Aa79bSolicitud
	 *            solicitud
	 * @return Aa79bRegistroExpediente
	 */
	@WebMethod(operationName = "registrarSolicitudWS")
	public Aa79bRegistroExpediente registrarSolicitudWS(Aa79bSolicitud solicitud) {
		ExpedienteWSImpl.LOGGER.info("registrarSolicitudWS");
		Locale locale = new Locale(solicitud.getIdioma());

		Aa79bRegistroExpediente registroExpediente = new Aa79bRegistroExpediente();
		try {

			registroExpediente = this.aa79bSolicitudWsService.altaSolicitud(solicitud);

			ExpedienteWSImpl.LOGGER.info("registrarSolicitudWS - procesoBatchSubidaPif - inicio ");

			ExpedienteWSImpl.LOGGER.info("registrarSolicitudWS - procesoBatchSubidaPif - fin ");
		} catch (Exception e) {
			ExpedienteWSImpl.LOGGER.error("pruebapid: JOSE - Error alta solicitud: ", e);
			registroExpediente.setEstado(Constants.ESTADO_WS_ERROR);
			registroExpediente
					.setDescripcion(this.appMessageSource.getMessage("mensaje.errorAltaSolicitud", null, locale));
		}
		return registroExpediente;
	}

	/**
	 *
	 * @param Aa79bSolicitud
	 *            solicitud
	 * @return Aa79bRegistroExpediente
	 */
	@WebMethod(operationName = "modificarSolicitudWS")
	public Aa79bRegistroExpediente modificarSolicitudWS(Aa79bSolicitud solicitud) {
		ExpedienteWSImpl.LOGGER.info("modificarSolicitudWS");
		Locale locale = new Locale(solicitud.getIdioma());
		Aa79bRegistroExpediente registroExpediente = new Aa79bRegistroExpediente();
		DatosSalidaWS datosSalidaWS = this.tieneAccesoExpediente(solicitud.getSolicitante().getDni(),
				solicitud.getAnyo(), solicitud.getNumExp(), locale);
		if (datosSalidaWS == null) {

			try {
				registroExpediente = this.aa79bSolicitudWsService.modificarSolicitud(solicitud);
			} catch (Exception e) {
				ExpedienteWSImpl.LOGGER.error("modificarSolicitud - Error modificación solicitud: ", e);
				registroExpediente.setEstado(Constants.ESTADO_WS_ERROR);
				registroExpediente.setDescripcion(
						this.appMessageSource.getMessage("mensaje.errorModificarSolicitud", null, locale));
			}
		} else {
			registroExpediente.setEstado(datosSalidaWS.getEstado());
			registroExpediente.setDescripcion(datosSalidaWS.getDescripcion());
		}
		return registroExpediente;
	}

	@WebMethod(operationName = "obtenerDocumentoExpedienteWS")
	public DatosDocumentoSalida obtenerDocumentoExpedienteWS(EntradaDatosDocumento entradaDatosDocumento)
			throws Exception {
		ExpedienteWSImpl.LOGGER.info("obtenerDocumentoExpedienteWS");
		Locale locale = new Locale(entradaDatosDocumento.getIdioma());
		// Comprobamos si el usuario tiene permisos y que el usuario tiene
		// acceso al expediente
		DatosSalidaWS datosSalida = this.tieneAccesoExpediente(entradaDatosDocumento.getDni(),
				entradaDatosDocumento.getAnyo(), entradaDatosDocumento.getNumExp(), locale);
		DatosDocumentoSalida datosDocumentoSalida = new DatosDocumentoSalida();

		if (datosSalida == null) {
			DocumentoUtils documentoUtils = new DocumentoUtils();
			datosDocumentoSalida = documentoUtils.obtenerDatosDocumentoSalida(entradaDatosDocumento);
			datosDocumentoSalida.setEstado(Constants.ESTADO_WS_OK);
		} else {
			datosDocumentoSalida.setEstado(Constants.ESTADO_WS_ERROR);
			datosDocumentoSalida
					.setDescripcion(this.appMessageSource.getMessage(MENSAJE_SIN_PERMISOS_DE_USUARIO, null, locale));
		}
		return datosDocumentoSalida;
	}

	@WebMethod(operationName = "obtenerDocumentosExpedienteWS")
	public Aa79bExpediente obtenerDocumentosExpedienteWS(EntradaDocumentosExpediente entradaDocumentosExpediente) {
		ExpedienteWSImpl.LOGGER.info("obtenerDocumentosExpedienteWS");
		Locale locale = new Locale(entradaDocumentosExpediente.getExpediente().getIdioma());

		// Comprobamos si el usuario tiene permisos y que el usuario tiene
		// acceso al expediente
		DatosSalidaWS datosSalida;
		if (entradaDocumentosExpediente.getComprPermisos()) {
			datosSalida = this.tieneAccesoExpediente(entradaDocumentosExpediente.getDni(),

					entradaDocumentosExpediente.getExpediente().getAnyo(),
					entradaDocumentosExpediente.getExpediente().getNumExp(), locale);
		} else {
			datosSalida = null;
		}
		Aa79bExpediente expediente = new Aa79bExpediente();

		if (datosSalida == null) {
			Aa79bDocumentoExpediente docuExp = new Aa79bDocumentoExpediente();
			docuExp.setAnyo(entradaDocumentosExpediente.getExpediente().getAnyo());
			docuExp.setNumExp(entradaDocumentosExpediente.getExpediente().getNumExp());
			expediente.setDocumentosExpediente(
					this.documentosExpedienteService.getAa79bDocumentosExpediente(docuExp, locale));

			datosSalida = new DatosSalidaWS();
			datosSalida.setEstado(Constants.ESTADO_WS_OK);
		} else {
			datosSalida.setEstado(Constants.ESTADO_WS_ERROR);
			datosSalida.setDescripcion(this.appMessageSource.getMessage(MENSAJE_SIN_PERMISOS_DE_USUARIO, null, locale));
		}
		return expediente;
	}

	@WebMethod(operationName = "descargarFicheroWS")
	public Aa79bSalidaDatosDescargaDocumentos descargarFicheroWS(EntradaDatosDocumento entradaDatosDocumento) {
		ExpedienteWSImpl.LOGGER.info("descargarFicheroWS");
		Locale locale = new Locale(entradaDatosDocumento.getIdioma());
		Boolean accesoTarea = true;
		Boolean accesoFicheroTarea = true;
		// Comprobamos si el usuario tiene permisos y que el usuario tiene
		// acceso al expediente
		DatosSalidaWS datosSalida = null;
		// devolver objeto EntradaDatosDocumento
		Aa79bSalidaDatosDescargaDocumentos salidaDatosDescargaDocumentos = new Aa79bSalidaDatosDescargaDocumentos();
		// comprobamos el acceso al expediente si no venimos de descarga de
		// fichero de tarea
		if (entradaDatosDocumento.getIdTarea() == null) {
			datosSalida = this.tieneAccesoExpediente(entradaDatosDocumento.getDni(), entradaDatosDocumento.getAnyo(),
					entradaDatosDocumento.getNumExp(), locale);
		} else {
			// comprobar acceso tarea de usuario
			final Aa79bEntradaEjecutarTarea beanEntradaEjecTarea = new Aa79bEntradaEjecutarTarea();
			beanEntradaEjecTarea.setDni(entradaDatosDocumento.getDni());
			beanEntradaEjecTarea.setIdTarea(entradaDatosDocumento.getIdTarea());
			accesoTarea = this.aa79bTareaWsService.getAccesoTarea(beanEntradaEjecTarea);
			// si venimos de auditoria, no necesitamos comprobar el acceso al fichero por tarea
			// comprobar acceso fichero tarea de usuario en caso de tareas
			if (!entradaDatosDocumento.isAuditoria()) {
				accesoFicheroTarea = this.aa79bTareaWsService.getAccesoFicheroTarea(entradaDatosDocumento);
			}
		}

		if (datosSalida == null && accesoTarea && accesoFicheroTarea) {
			// Leo la información del fichero para sacar su oid (tabla 88)
			DocumentoTareaAdjunto documentoTareaAdjunto = new DocumentoTareaAdjunto();
			documentoTareaAdjunto.setIdFichero(new BigDecimal(entradaDatosDocumento.getIdsFichero()));
			documentoTareaAdjunto = this.documentosGeneralService.find(documentoTareaAdjunto);

			Aa79bFicheroDocExp fichero = new Aa79bFicheroDocExp();
			fichero.setOid(documentoTareaAdjunto.getOid());
			fichero.setContentType(documentoTareaAdjunto.getContentType());
			fichero.setNombre(documentoTareaAdjunto.getNombre());
			fichero.setTamano(documentoTareaAdjunto.getTamano());
			List<Aa79bFicheroDocExp> listaFicheros = new ArrayList<Aa79bFicheroDocExp>();
			listaFicheros.add(fichero);
			salidaDatosDescargaDocumentos.setListaFicheros(listaFicheros);

			salidaDatosDescargaDocumentos.setEstado(Constants.ESTADO_WS_OK);
		} else {
			salidaDatosDescargaDocumentos.setEstado(Constants.ESTADO_WS_ERROR);
			salidaDatosDescargaDocumentos
					.setDescripcion(this.appMessageSource.getMessage(MENSAJE_SIN_PERMISOS_DE_USUARIO, null, locale));
		}
		// devolvemos el oid, contentType y nombre para que se descarge en aa06a
		return salidaDatosDescargaDocumentos;
	}

	@WebMethod(operationName = "insertaOidTablaAuxiliarWS")
	public DatosSalidaWS insertaOidTablaAuxiliarWS(String nuevoOID) {
		ExpedienteWSImpl.LOGGER.info("insertaOidTablaAuxiliarWS");
		DatosSalidaWS datosSalida = new DatosSalidaWS();
		try {
			this.oidsAuxiliarService.add(nuevoOID);
			datosSalida.setEstado(Constants.ESTADO_WS_OK);
		} catch (Exception e) {
			datosSalida.setEstado(Constants.ESTADO_WS_ERROR);
			ExpedienteWSImpl.LOGGER.info("Error en insertaOidTablaAuxiliarWS", e);
		}

		return datosSalida;
	}

	@WebMethod(operationName = "findInfoFicheroDescargaWS")
	public Aa79bSalidaDatosDescargaDocumentos findInfoFicheroDescargaWS(int tipoDescarga,
			List<BigDecimal> listaIdsFicheros) {
		ExpedienteWSImpl.LOGGER.info("findInfoFicheroDescargaWS");
		Aa79bSalidaDatosDescargaDocumentos aa79bSalidaDatosDescargaDocumentos = new Aa79bSalidaDatosDescargaDocumentos();
		try {
			aa79bSalidaDatosDescargaDocumentos = this.subidaFicherosService.findWS(tipoDescarga, listaIdsFicheros);
			aa79bSalidaDatosDescargaDocumentos.setEstado(Constants.ESTADO_WS_OK);
		} catch (Exception e) {
			aa79bSalidaDatosDescargaDocumentos.setEstado(Constants.ESTADO_WS_ERROR);
			ExpedienteWSImpl.LOGGER.info("Error en findInfoFicheroDescargaWS", e);
		}

		return aa79bSalidaDatosDescargaDocumentos;
	}

	@WebMethod(operationName = "setlibroRegistroSol")
	public DatosSalidaWS setlibroRegistroSol(EntradaLibroRegistroSol entradaLibroRegistroSol) {
		ExpedienteWSImpl.LOGGER.info("obtenerDocumentosExpedienteWS");
		Locale locale = new Locale(entradaLibroRegistroSol.getIdioma());

		// Comprobamos si el usuario tiene permisos y que el usuario tiene
		// acceso al expediente
		DatosSalidaWS datosSalida = this.tieneAccesoExpediente(entradaLibroRegistroSol.getDni(),
				entradaLibroRegistroSol.getAnyo(), entradaLibroRegistroSol.getNumExp(), locale);

		if (datosSalida == null) {
			this.configLibroRegistroService.setlibroRegistroSol(entradaLibroRegistroSol);

			datosSalida = new DatosSalidaWS();
			datosSalida.setEstado(Constants.ESTADO_WS_OK);
		} else {
			datosSalida.setEstado(Constants.ESTADO_WS_ERROR);
			datosSalida.setDescripcion(this.appMessageSource.getMessage(MENSAJE_SIN_PERMISOS_DE_USUARIO, null, locale));
		}
		return datosSalida;
	}

	@WebMethod(operationName = "esReceptorAutorizadoWS")
	public Aa79bReceptorAutorizadoConsulta esReceptorAutorizadoWS(
			EntradaReceptorAutorizado entradaReceptoresAutorizados) {
		ExpedienteWSImpl.LOGGER.info("esReceptorAutorizadoWS");
		Aa79bReceptorAutorizadoConsulta aa79bReceptorAutorizadoConsulta = new Aa79bReceptorAutorizadoConsulta();
		try {
			Integer countExpReceptor = this.receptorAutorizadoService
					.getReceptorAutorizadoCount(entradaReceptoresAutorizados.getDni());
			if (countExpReceptor > Constants.CERO) {
				aa79bReceptorAutorizadoConsulta.setEsReceptor(true);
			} else {
				aa79bReceptorAutorizadoConsulta.setEsReceptor(false);
			}
			aa79bReceptorAutorizadoConsulta.setCountExpReceptor(countExpReceptor);
			aa79bReceptorAutorizadoConsulta.setEstado(Constants.ESTADO_WS_OK);
		} catch (Exception e) {
			aa79bReceptorAutorizadoConsulta.setEstado(Constants.ESTADO_WS_ERROR);
			Locale locale;
			if (entradaReceptoresAutorizados.getIdioma() != null) {
				locale = new Locale(entradaReceptoresAutorizados.getIdioma());
			} else {
				locale = new Locale(Constants.LANG_EUSKERA);
			}
			aa79bReceptorAutorizadoConsulta.setDescripcion(
					this.appMessageSource.getMessage(MENSAJE_ERROR_RECEPTOR_AUTORIZADO_COUNT, null, locale));
			ExpedienteWSImpl.LOGGER.info("Error en esReceptorAutorizadoWS", e);
		}

		return aa79bReceptorAutorizadoConsulta;
	}

	@WebMethod(operationName = "obtenerDatosFacturacionInterpretacionWS")
	public Aa79bSalidaDatosPresupuestoFacturacion obtenerDatosFacturacionInterpretacionWS(
			EntradaDatosExpediente datosExpediente) {
		ExpedienteWSImpl.LOGGER.info("obtenerDatosFacturacionInterpretacionWS - INICIO");
		Aa79bSalidaDatosPresupuestoFacturacion salidaDatosPresupuestoFacturacion = new Aa79bSalidaDatosPresupuestoFacturacion();
		Locale locale = null;
		if (StringUtils.isNotEmpty(datosExpediente.getIdioma())) {
			locale = new Locale(datosExpediente.getIdioma());
		} else {
			locale = new Locale(Constants.LANG_EUSKERA);
		}
		try {
			/* obtener datosfacturacioninterpretacion */
			ExpedienteWSImpl.LOGGER
					.info("obtenerDatosFacturacionInterpretacionWS - obtener datosFacturacionInterpretacion - INICIO");
			salidaDatosPresupuestoFacturacion = this.aa79bExpedienteWsService
					.datosFacturacionInterpretacion(datosExpediente);
			ExpedienteWSImpl.LOGGER
					.info("obtenerDatosFacturacionInterpretacionWS - obtener datosFacturacionInterpretacion - FIN");
			/* obtener idRequerimiento */
			ExpedienteWSImpl.LOGGER.info("obtenerDatosFacturacionInterpretacionWS - obtener idRequerimiento - INICIO");
			salidaDatosPresupuestoFacturacion
					.setIdRequerimiento(this.aa79bExpedienteWsService.obtenerIdRequerimiento(datosExpediente));
			ExpedienteWSImpl.LOGGER.info("obtenerDatosFacturacionInterpretacionWS - obtener idRequerimiento - FIN");
			/* obtener datosentidadcontactofacturacion */
			ExpedienteWSImpl.LOGGER.info(
					"obtenerDatosFacturacionInterpretacionWS - obtener obtenerDatosEntidadContactoFacturacionInterpretacion - INICIO");
			salidaDatosPresupuestoFacturacion.setListaEntidadContacto(this.aa79bExpedienteWsService
					.obtenerDatosEntidadContactoFacturacionInterpretacion(datosExpediente));
			ExpedienteWSImpl.LOGGER.info(
					"obtenerDatosFacturacionInterpretacionWS - obtener obtenerDatosEntidadContactoFacturacionInterpretacion - FIN");
			salidaDatosPresupuestoFacturacion.setEstado(Constants.ESTADO_WS_OK);
		} catch (Exception e) {
			salidaDatosPresupuestoFacturacion.setEstado(Constants.ESTADO_WS_ERROR);
			salidaDatosPresupuestoFacturacion.setDescripcion(this.appMessageSource
					.getMessage(MENSAJE_ERROR_OBTENER_DATOS_PRESUPUESTO_FACTURACION, null, locale));
			ExpedienteWSImpl.LOGGER.info("obtenerDatosFacturacionInterpretacionWS - ERROR: " + e);
		}

		ExpedienteWSImpl.LOGGER.info("obtenerDatosFacturacionInterpretacionWS - FIN");
		return salidaDatosPresupuestoFacturacion;
	}

	@WebMethod(operationName = "obtenerDatosFacturacionTraduccionWS")
	public Aa79bSalidaDatosPresupuestoFacturacion obtenerDatosFacturacionTraduccionWS(
			EntradaDatosExpediente datosExpediente) {
		ExpedienteWSImpl.LOGGER.info("obtenerDatosFacturacionTraduccionWS - INICIO");
		Aa79bSalidaDatosPresupuestoFacturacion salidaDatosPresupuestoFacturacion = new Aa79bSalidaDatosPresupuestoFacturacion();
		Locale locale = null;
		if (StringUtils.isNotEmpty(datosExpediente.getIdioma())) {
			locale = new Locale(datosExpediente.getIdioma());
		} else {
			locale = new Locale(Constants.LANG_EUSKERA);
		}
		try {
			/* obtener datosfacturaciontraduccion */
			ExpedienteWSImpl.LOGGER
					.info("obtenerDatosFacturacionTraduccionWS - obtener datosfacturaciontraduccion - INICIO");
			salidaDatosPresupuestoFacturacion = this.aa79bExpedienteWsService
					.datosFacturacionTraduccion(datosExpediente);
			ExpedienteWSImpl.LOGGER
					.info("obtenerDatosFacturacionTraduccionWS - obtener datosfacturaciontraduccion - FIN");
			/* obtener idRequerimiento */
			ExpedienteWSImpl.LOGGER.info("obtenerDatosFacturacionTraduccionWS - obtener idRequerimiento - INICIO");
			salidaDatosPresupuestoFacturacion
					.setIdRequerimiento(this.aa79bExpedienteWsService.obtenerIdRequerimiento(datosExpediente));
			ExpedienteWSImpl.LOGGER.info("obtenerDatosFacturacionTraduccionWS - obtener idRequerimiento - FIN");
			/* obtener datosentidadcontactofacturacion */
			ExpedienteWSImpl.LOGGER
					.info("obtenerDatosFacturacionTraduccionWS - obtener datosEntidadContactoFacturacion - INICIO");
			salidaDatosPresupuestoFacturacion.setListaEntidadContacto(
					this.aa79bExpedienteWsService.obtenerDatosEntidadContactoFacturacionTraduccion(datosExpediente));
			ExpedienteWSImpl.LOGGER
					.info("obtenerDatosFacturacionTraduccionWS - obtener datosEntidadContactoFacturacion - FIN");
			salidaDatosPresupuestoFacturacion.setEstado(Constants.ESTADO_WS_OK);
		} catch (Exception e) {
			salidaDatosPresupuestoFacturacion.setEstado(Constants.ESTADO_WS_ERROR);
			salidaDatosPresupuestoFacturacion.setDescripcion(this.appMessageSource
					.getMessage(MENSAJE_ERROR_OBTENER_DATOS_PRESUPUESTO_FACTURACION, null, locale));
			ExpedienteWSImpl.LOGGER.info("obtenerDatosFacturacionTraduccionWS - ERROR: " + e);
		}

		ExpedienteWSImpl.LOGGER.info("obtenerDatosFacturacionTraduccionWS - FIN");
		return salidaDatosPresupuestoFacturacion;
	}

	@WebMethod(operationName = "obtenerTitulosExpedientesWS")
	public DatosConsultaExp obtenerTitulosExpedientesWS(EntradaGestoresRepresentante bean) {

		DatosConsultaExp datosConsultaExp = new DatosConsultaExp();
		try {
			datosConsultaExp.setTitulosExpediente(this.expedienteService.getTitulosExpediente(bean));
			datosConsultaExp.setEstado(Constants.ESTADO_WS_OK);

		} catch (Exception e) {
			datosConsultaExp.setEstado(Constants.ESTADO_WS_ERROR);
			ExpedienteWSImpl.LOGGER.info("obtenerTitulosExpedientesWS - ERROR: " + e);
		}


		return datosConsultaExp;
	}

	/**
	 * Obtiene los datos del solicitante en caso de que sea gestor y/o
	 * coordinador
	 *
	 * @param dni
	 * @return Solicitante
	 */
	private Solicitante obtenerGestorCoordinador(String dni) {
		Solicitante solicitante = new Solicitante();
		solicitante.setDni(dni);

		return this.solicitanteService.findGestorCoordinador(solicitante);
	}

	/**
	 * @Method que indica si el usuario tiene acceso al expediente
	 *
	 * @param dni
	 *            String
	 * @param anyo
	 *            Long
	 * @param numExp
	 *            Integer
	 * @return DatosSalidaWS
	 */
	private DatosSalidaWS tieneAccesoExpediente(String dni, Long anyo, Integer numExp, Locale locale) {

		DatosSalidaWS datosSalidaWS = new DatosSalidaWS();
		Expediente expediente = new Expediente();
		Gestor gestor = new Gestor();

		Solicitante solicitante = this.obtenerGestorCoordinador(dni);

		if (solicitante != null) {
			gestor.setSolicitante(solicitante);

			expediente.setAnyo(anyo);
			expediente.setNumExp(numExp);
			expediente.setGestorExpediente(gestor);

			if (this.aa79bExpedienteWsService.tieneAccesoExpediente(expediente, Constants.TABLA_GESTOR_EXPEDIENTE)) {
				datosSalidaWS = null;
			} else if(this.receptorAutorizadoService.esReceptorAutorizadoYAccesoAExpediente(dni, anyo, numExp)){
				datosSalidaWS = null;
			}else{
				datosSalidaWS.setEstado(Constants.ESTADO_WS_ERROR);
				datosSalidaWS.setDescripcion(this.appMessageSource.getMessage(MENSAJE_GESTOR_SIN_ACCESO, null, locale));
			}

		} else if(this.receptorAutorizadoService.esReceptorAutorizadoYAccesoAExpediente(dni, anyo, numExp)){
			datosSalidaWS = null;
		}else{
			datosSalidaWS.setEstado(Constants.ESTADO_WS_ERROR);
			datosSalidaWS
					.setDescripcion(this.appMessageSource.getMessage(MENSAJE_SIN_PERMISOS_DE_USUARIO, null, locale));
		}

		return datosSalidaWS;
	}

	/**
	 * Obtiene las observaciones del expediente
	 *
	 * @param bean
	 *            EntradaNotasExpediente
	 * @return ObservacionesExpediente
	 */
	private ObservacionesExpediente obtenerObservaciones(EntradaNotasExpediente bean) {

		Expediente expediente = new Expediente();
		expediente.setAnyo(bean.getAnyo());
		expediente.setNumExp(bean.getNumExp());

		return this.observacionesExpedienteService.observacionesFind(expediente);
	}

	private StringBuilder obtenerNumExpConCeros(Integer numExpRelDigits) {
		StringBuilder numExpFormated = new StringBuilder();
		Integer numExpRelFinal;
		if (numExpRelDigits <= Constants.SEIS) {
			numExpRelFinal = Constants.SEIS - numExpRelDigits;
			numExpFormated.setLength(Constants.CERO);
			for (int j = Constants.CERO; j < numExpRelFinal; j++) {
				numExpFormated.append("0");
			}
		}
		return numExpFormated;
	}

	/**
	 *
	 * @param bean EntradaPlantilla
	 * @return FicheroWS
	 */
	@WebMethod(operationName = "obtenerDatosDocPlantillaWS")
	public FicheroWS obtenerDatosDocPlantillaWS(EntradaPlantilla bean) {
		FicheroWS fichero = this.plantillasService.obtenerDatosDocPlantilla(bean);

			if (fichero != null && fichero.getOid() != null) {
				fichero.setEstado(Constants.ESTADO_WS_OK);
			}else {
				fichero = new FicheroWS();
				fichero.setEstado(Constants.ESTADO_WS_ERROR);
		}

		return fichero;
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
	@WebMethod(operationName = "informeSolaskideWS")
	public FicheroWS informeSolaskideWS(Aa79bInformes aa79bInformes) {
		ExpedienteWSImpl.LOGGER.debug("WS_METHOD informeSolaskideWS - BEGIN\nfilter: " + aa79bInformes);
		Locale locale;
		if (StringUtils.isNotBlank(aa79bInformes.getIdioma())) {
			locale = new Locale(aa79bInformes.getIdioma());
		} else {
			locale = new Locale(Constants.LANG_EUSKERA);
		}
		final String expedienteLabel = "titulo.solaskide";
		final String label = this.appMessageSource.getMessage(expedienteLabel, new Object[] {}, locale);

		final List<Aa79bSolaskides> solaskides = this.aa79bExpedienteWsService.findSolaskides(aa79bInformes, locale);
		FicheroWS ficheroBean = new FicheroWS();
		byte[] bytesInforme = generarInformeExcelSolaskides(locale, expedienteLabel,
					label, solaskides, ficheroBean);

		try {
			ficheroBean.setRutaPif(this.pidService.subidaPif(ficheroBean.getNombre(),
					bytesInforme, this.appConfiguration.getProperty(RUTA_PIF_AA06A), false));
		} catch (UnsupportedEncodingException e) {
			ExpedienteWSImpl.LOGGER.info("UnsupportedEncodingException - subidaPif: " + e);
			ficheroBean.setEstado(Constants.ESTADO_WS_ERROR);
			ficheroBean.setDescripcion("ERROR subidaPif");

		} catch (Exception e) {
			ExpedienteWSImpl.LOGGER.error("informeSolaskideWS - subidaFichero Error: ", e);
			ficheroBean.setEstado(Constants.ESTADO_WS_ERROR);
			ficheroBean.setDescripcion(this.appMessageSource.getMessage("mensaje.fichero.errorPIF", null, locale));
		}

		ExpedienteWSImpl.LOGGER.debug("WS_METHOD informeSolaskideWS - FIN: " + aa79bInformes);
		return ficheroBean;
	}

	private byte[] generarInformeExcelSolaskides(Locale locale, final String expedienteLabel, final String label,
			final List<Aa79bSolaskides> listaSolaskides, FicheroWS ficheroBean) {

		StringBuilder columns = new StringBuilder("[[\"contacto.dni\",\"label.dni\",150,\"right\"],"
				+ "[\"contacto.nombre\",\"label.nombre\",150,\"left\"]," + "[\"contacto.apellidos\",\"label.apellidos\",150,\"left\"],"
				+ "[\"entidad.tipo\",\"label.entidadODepart\",150,\"left\"]");

		if (locale.getLanguage().equals(Constants.LANG_CASTELLANO)) {
			columns.append(",[\"entidad.entidadDescAmpEs\",\"label.nomEntidadODepart\",150,\"left\"]]");
		} else {
			columns.append(",[\"entidad.entidadDescAmpEu\",\"label.nomEntidadODepart\",150,\"left\"]]");
		}

		ExcelModel excelModel = this.aa79bExcelReportService.getExcelModelJson(null,
				columns.toString(), listaSolaskides, locale, expedienteLabel, label);
		byte[] bytesInforme = null;
		ficheroBean.setEstado(Constants.ESTADO_WS_OK);
		try {
			bytesInforme = this.aa79bExcelReportService.generarExcel(FormatoInformeEnum.EXCEL.getTemplate(),
					excelModel);
		} catch (Exception e1) {
			ExpedienteWSImpl.LOGGER.info("ERROR informeSolaskideWS, Error: " + e1);
			ficheroBean.setEstado(Constants.ESTADO_WS_ERROR);
			ficheroBean.setDescripcion("ERROR informeSolaskideWS");
		}

		ficheroBean.setNombre(this.appMessageSource.getMessage("titulo.informeSolaskides", null, locale) + FormatoInformeEnum.EXCEL.getExtension());
		ficheroBean.setExtension(FormatoInformeEnum.EXCEL.getExtension());
		ficheroBean.setContentType(FormatoInformeEnum.EXCEL.getContentType());
		ficheroBean.setTamano((long) bytesInforme.length);
		return bytesInforme;
	}

	@WebMethod(operationName = "informeSolicitudesWS")
	public FicheroWS informeSolicitudesWS(Aa79bInformes aa79bInformes) {
		ExpedienteWSImpl.LOGGER.debug("WS_METHOD informeSolaskideWS - BEGIN\nfilter: " + aa79bInformes);
		Locale locale;
		if (StringUtils.isNotBlank(aa79bInformes.getIdioma())) {
			locale = new Locale(aa79bInformes.getIdioma());
		} else {
			locale = new Locale(Constants.LANG_EUSKERA);
		}
		final String expedienteLabel = "titulo.solicitudes";
		final String label = this.appMessageSource.getMessage(expedienteLabel, new Object[] {}, locale);

		final List<Aa79bInformeSolicitudes> solicitudes = this.aa79bExpedienteWsService.findInformeSolicitudes(aa79bInformes, locale);
		FicheroWS ficheroBean = new FicheroWS();
		byte[] bytesInforme = generarInformeSolicitudes(locale, expedienteLabel,
					label, solicitudes, ficheroBean);

		try {
			ficheroBean.setRutaPif(this.pidService.subidaPif(ficheroBean.getNombre(),
					bytesInforme, this.appConfiguration.getProperty(RUTA_PIF_AA06A), false));
		} catch (UnsupportedEncodingException e) {
			ExpedienteWSImpl.LOGGER.info("UnsupportedEncodingException - subidaPif: " + e);
			ficheroBean.setEstado(Constants.ESTADO_WS_ERROR);
			ficheroBean.setDescripcion("ERROR subidaPif");

		} catch (Exception e) {
			ExpedienteWSImpl.LOGGER.error("informeSolicitudesWS - subidaFichero Error: ", e);
			ficheroBean.setEstado(Constants.ESTADO_WS_ERROR);
			ficheroBean.setDescripcion(this.appMessageSource.getMessage("mensaje.fichero.errorPIF", null, locale));
		}

		ExpedienteWSImpl.LOGGER.debug("WS_METHOD informeSolicitudesWS - FIN: " + aa79bInformes);
		return ficheroBean;
	}

	private byte[] generarInformeSolicitudes(Locale locale, final String expedienteLabel, final String label,
			final List<Aa79bInformeSolicitudes> solicitudes, FicheroWS ficheroBean) {

		StringBuilder columns = new StringBuilder("[[\"numExp\",\"label.numExp\",150,\"right\",true,false],"
				+ "[\"anyo\",\"label.anyo\",150,\"left\",true,false]," + "[\"anyoNumExp\",\"label.expediente\",150,\"left\",false,false],"
				+ "[\"bopvDesc\",\"label.bopv\",150,\"left\",false,false]," + "[\"tipoExpDesc\",\"label.tipoExpediente\",150,\"left\",false,false],"
				+ "[\"solicitante\",\"label.solicitante\",150,\"left\",false,false]," + "[\"numPalIzo\",\"label.numPalabraIZO\",150,\"left\",true,false],"
				+ "[\"tarifaPalEur\",\"label.tarifaPalabras\",150,\"left\",true,false]," + "[\"finalizado\",\"label.terminado\",150,\"left\",false,false],"
				+ "[\"tipoDocumento\",\"label.tipoDocumento\",150,\"left\",false,false]," + "[\"tipoTraduccion\",\"label.tipoTraduccion\",150,\"left\",false,false],"
				+ "[\"importeTotalEur\",\"label.importeFactura\",150,\"left\",true,false]," + "[\"descripcion\",\"comun.descripcion\",150,\"left\",false,false],"
				+ "[\"solaskidea\",\"label.solaskide\",150,\"left\",false,false]," + "[\"idioma\",\"label.idiomaDestino\",150,\"left\",false,false],"
				+ "[\"fechaAlta\",\"label.fechaHoraSolicitud\",150,\"left\",false,false]," + "[\"fechaFinIzo\",\"label.fechaHoraEntregaIZO\",150,\"left\",false,false],"
				+ "[\"fechaEntregaReal\",\"label.fechaHoraEntregaReal\",150,\"left\",false,false]," + "[\"lIdFactura\",\"comun.numFactura\",150,\"left\",false,false]]");

		ExcelModel excelModel = this.aa79bExcelReportService.getExcelModelJson(null,
				columns.toString(), solicitudes, locale, expedienteLabel, label);
		byte[] bytesInforme = null;
		ficheroBean.setEstado(Constants.ESTADO_WS_OK);
		try {
			bytesInforme = this.aa79bExcelReportService.generarExcel(FormatoInformeEnum.EXCEL.getTemplate(),
					excelModel);
		} catch (Exception e1) {
			ExpedienteWSImpl.LOGGER.info("ERROR informeSolicitudesWS, Error: " + e1);
			ficheroBean.setEstado(Constants.ESTADO_WS_ERROR);
			ficheroBean.setDescripcion("ERROR informeSolicitudesWS");
		}

		ficheroBean.setNombre(this.appMessageSource.getMessage("titulo.informeSolicitudes", null, locale) + FormatoInformeEnum.EXCEL.getExtension());
		ficheroBean.setExtension(FormatoInformeEnum.EXCEL.getExtension());
		ficheroBean.setContentType(FormatoInformeEnum.EXCEL.getContentType());
		ficheroBean.setTamano((long) bytesInforme.length);
		return bytesInforme;
	}

}