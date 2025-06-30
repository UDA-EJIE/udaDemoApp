package com.ejie.aa79b.webservices;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
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
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.mail.MailService;
import com.ejie.aa79b.model.Albaran;
import com.ejie.aa79b.model.ConfigDireccionEmail;
import com.ejie.aa79b.model.DatosContacto;
import com.ejie.aa79b.model.DatosPagoProveedores;
import com.ejie.aa79b.model.Entidad;
import com.ejie.aa79b.model.EntradaAlbaranTareas;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.Facturas;
import com.ejie.aa79b.model.Fichero;
import com.ejie.aa79b.model.FicheroWS;
import com.ejie.aa79b.model.Lotes;
import com.ejie.aa79b.model.ParametrosEmail;
import com.ejie.aa79b.model.RegistroAcciones;
import com.ejie.aa79b.model.Tareas;
import com.ejie.aa79b.model.enums.AccionesEnum;
import com.ejie.aa79b.model.enums.EstadoAlbaranEnum;
import com.ejie.aa79b.model.enums.FormatoInformeEnum;
import com.ejie.aa79b.model.enums.PuntosMenuEnum;
import com.ejie.aa79b.model.enums.TipoAvisoEnum;
import com.ejie.aa79b.model.excel.ExcelModel;
import com.ejie.aa79b.model.pdf.PdfModel;
import com.ejie.aa79b.model.webservices.Aa79bAlbaran;
import com.ejie.aa79b.model.webservices.Aa79bDatosContacto;
import com.ejie.aa79b.model.webservices.Aa79bEmpresasProveedoras;
import com.ejie.aa79b.model.webservices.Aa79bEntradaConsultaFacturas;
import com.ejie.aa79b.model.webservices.Aa79bEntradaDatosConsulFact;
import com.ejie.aa79b.model.webservices.Aa79bEntradaEjecutarTarea;
import com.ejie.aa79b.model.webservices.Aa79bEntradaFactura;
import com.ejie.aa79b.model.webservices.Aa79bEntradaTarea;
import com.ejie.aa79b.model.webservices.Aa79bEntradaTareasAlbaran;
import com.ejie.aa79b.model.webservices.Aa79bExpediente;
import com.ejie.aa79b.model.webservices.Aa79bInformeExpedientesFactura;
import com.ejie.aa79b.model.webservices.Aa79bInformeFacturas;
import com.ejie.aa79b.model.webservices.Aa79bInformeTareasAlbaran;
import com.ejie.aa79b.model.webservices.Aa79bJaxbList;
import com.ejie.aa79b.model.webservices.Aa79bSalidaAsociacionAlbaran;
import com.ejie.aa79b.model.webservices.Aa79bSalidaConsultaFacturas;
import com.ejie.aa79b.model.webservices.Aa79bSalidaDatosConsulFact;
import com.ejie.aa79b.model.webservices.Aa79bSalidaDetalleFactura;
import com.ejie.aa79b.model.webservices.Aa79bSalidaDocumentoFactura;
import com.ejie.aa79b.model.webservices.Aa79bSalidaTarea;
import com.ejie.aa79b.model.webservices.Aa79bTablaWebService;
import com.ejie.aa79b.model.webservices.Aa79bTarea;
import com.ejie.aa79b.service.Aa79bAlbaranService;
import com.ejie.aa79b.service.Aa79bTareaWsService;
import com.ejie.aa79b.service.ConfigDireccionEmailService;
import com.ejie.aa79b.service.DatosContactoService;
import com.ejie.aa79b.service.EntidadService;
import com.ejie.aa79b.service.ExcelReportService;
import com.ejie.aa79b.service.ExpedienteService;
import com.ejie.aa79b.service.FacturasService;
import com.ejie.aa79b.service.LotesService;
import com.ejie.aa79b.service.PdfReportService;
import com.ejie.aa79b.service.RegistroAccionesService;
import com.ejie.aa79b.utils.GeneralUtils;
import com.ejie.aa79b.utils.TareasServiceUtils;
import com.ejie.aa79b.utils.Utils;
import com.ejie.aa79b.utils.WSUtils;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;

/**
 * @author aresua
 */
@WebService(serviceName = "aa79bFacturacionWS", portName = "aa79bFacturacionWSPort", targetNamespace = "http://com.ejie.aa79b.webservices")
@SOAPBinding(parameterStyle = ParameterStyle.WRAPPED)
@HandlerChain(file = "server-handlers.xml")
public class FacturacionWSImpl extends SpringBeanAutowiringSupport {

	@Autowired
	private Aa79bAlbaranService albaranService;
	@Autowired()
	private Aa79bTareaWsService aa79bTareaWsService;
	@Autowired
	private LotesService lotesService;
	@Autowired()
	private RegistroAccionesService registroAccionesService;
	@Autowired()
	private ConfigDireccionEmailService configDireccionEmailService;
	@Autowired()
	private MailService mailService;
	@Autowired()
	private ExpedienteService expedienteService;
	@Autowired
	private ExcelReportService excelReportService;
	@Autowired
	private PdfReportService pdfReportService;
	@Autowired
	private PdfReportService aa79bPdfReportService;
	@Autowired()
	private PIDService pidService;
	@Autowired()
	private FacturasService facturasService;
	@Autowired()
	private DatosContactoService datosContactoService;
	@Autowired()
	private EntidadService entidadService;
	@Autowired()
	private Properties appConfiguration;
	@Autowired()
	private ReloadableResourceBundleMessageSource appMessageSource;

	private static final Logger LOGGER = LoggerFactory.getLogger(FacturacionWSImpl.class);
	private static final String TABLA_REQUEST = "\ntablaRequest: ";
	private static final String MENSAJE_PROVEEDOR_SIN_ACCESO_A_TAREA = "mensaje.proveedorSinAccesoATarea";
	private static final String MENSAJE_REFERENCIA_EXISTE = "mensaje.referenciaExiste";
	private static final String ERROR_OBTENIENDO_INFORMACION = "mensaje.errorObteniendoInformacionTarea";
	private static final String ERROR_OBTENIENDO_INFORMACION_FACTURA = "mensaje.errorObteniendoInformacionFactura";
	private static final String MENSAJE_PROVEEDOR_SIN_ACCESO_A_FACTURA = "mensaje.proveedorSinAccesoAFactura";
	private static final String CAMPO_FECHA_EMISION = "fechaEmision";
	private static final String CAMPO_FECHA_EMISION_INFORME_EU = "fechaHoraEmisionEu";
	private static final String CAMPO_FECHA_EMISION_INFORME_ES = "fechaHoraEmisionEs";
	private static final String CAMPO_ESTADO_FACTURA = "idEstadoFactura";
	private static final String CAMPO_DESC_ESTADO_FACTURA = "descEstadoFactura";
	private static final String CAMPO_NUM_TOTAL_PAL_FACTURAR = "datosTradRev.datosFacturacion.numTotalPalFacturar";
	private static final String CAMPOS_NUM_TOTAL_PAL_FACTURAR_INFORME = "[\"datosTradRev.datosFacturacion.numTotalPalFacturar\",\"label.nPalabrasAFacturar\",150,\"right\"],"
			+ "[\"datosTradRev.datosFacturacion.numPalConcor084\",\"label.palconcor1\",150,\"right\"],"
			+ "[\"datosTradRev.datosFacturacion.numPalConcor8594\",\"label.palconcor2\",150,\"right\"],"
			+ "[\"datosTradRev.datosFacturacion.numPalConcor95100\",\"label.palconcor3\",150,\"right\"]";
	private static final String CAMPO_TIPO_EXPEDIENTE = "tipoExpediente.codigo";
	private static final String CAMPO_TIPO_EXPEDIENTE_INFORME_EU = "tipoExpediente.descEu";
	private static final String CAMPO_TIPO_EXPEDIENTE_INFORME_ES = "tipoExpediente.descEs";
	private static final String CAMPO_FECHA_SOLICITUD = "fechaSolicitud";
	private static final String CAMPO_FECHA_SOLICITUD_INFORME_EU = "fechaHoraSolicitudEu";
	private static final String CAMPO_FECHA_SOLICITUD_INFORME_ES = "fechaHoraSolicitudEs";

	/**
	 *
	 * @return String
	 */
	@WebMethod(operationName = "pruebaWS")
	public String prueba() {
		return "HolaMundo";
	}

	@WebMethod(operationName = "obtenerBuscadorAlbaranWS")
	public List<Aa79bAlbaran> obtenerBuscadorAlbaranWS(Aa79bAlbaran bean) {
		FacturacionWSImpl.LOGGER.debug("obtenerBuscadorAlbaranWS - INICIO");
		Albaran albaran = new Albaran();
		albaran.setIdLote(bean.getIdLote());
		albaran.setEstado(bean.getEstado());
		FacturacionWSImpl.LOGGER.debug(
				"obtenerBuscadorAlbaranWS - return this.albaranService.obtenerBuscadorAlbaran(Aa79bAlbaran) - FIN");
		return this.albaranService.obtenerBuscadorAlbaran(albaran);
	}

	@WebMethod(operationName = "consultaTareasAlbaranWS")
	public Aa79bTablaWebService<Aa79bSalidaTarea> consultaTareasAlbaranWS(Aa79bEntradaTarea bean,
			Aa79bTablaWebService<Aa79bSalidaTarea> aa79bTablaWebService, Aa79bSalidaTarea salida) {
		FacturacionWSImpl.LOGGER.debug(
				"WS_METHOD consultaTareasAlbaranWS - BEGIN\nfilter: " + bean + TABLA_REQUEST + aa79bTablaWebService);
		Locale locale;
		if (StringUtils.isNotBlank(bean.getIdioma())) {
			locale = new Locale(bean.getIdioma());
		} else {
			locale = new Locale("eu");
		}
		Aa79bTablaWebService<Aa79bSalidaTarea> tabla = new Aa79bTablaWebService<Aa79bSalidaTarea>();
		try {

			JQGridRequestDto jqGridRequestDto = null;
			if (aa79bTablaWebService != null && Utils.isValidAa79bTablaWebService(aa79bTablaWebService)) {
				jqGridRequestDto = new JQGridRequestDto(aa79bTablaWebService.getRows(), aa79bTablaWebService.getPage(),
						aa79bTablaWebService.getSidx(), aa79bTablaWebService.getSord());

			}

			final JQGridResponseDto<Aa79bSalidaTarea> jqGrid = this.albaranService.findTareasAlbaran(bean,
					jqGridRequestDto, false, true);
			tabla.setEstado(Constants.ESTADO_WS_OK);
			@SuppressWarnings(value = "unchecked")
			final Aa79bJaxbList<Aa79bSalidaTarea> lista = new Aa79bJaxbList<Aa79bSalidaTarea>(
					(List<Aa79bSalidaTarea>) jqGrid.getRows());
			tabla.setLista(lista);
			tabla.setRecordNum(Long.valueOf(jqGrid.getRecords()));

			FacturacionWSImpl.LOGGER.info("WS_METHOD consultaTareasAlbaranWS END\nreturn" + tabla);

		} catch (Exception e) {
			FacturacionWSImpl.LOGGER.error("[POST - subm]: consultaTareasAlbaranWS ", e);
			tabla.setSidx("-1");
			tabla.setSord(e.getMessage());
			tabla.setEstado(Constants.ESTADO_WS_ERROR);
			tabla.setDescripcion(this.appMessageSource.getMessage("mensaje error", null, locale));
		}

		FacturacionWSImpl.LOGGER.debug("consultaTareasAlbaranWS - FIN");
		return tabla;
	}

	@WebMethod(operationName = "crearAlbaranWS")
	public Aa79bSalidaTarea crearAlbaranWS(Aa79bEntradaTarea aa79bEntradaTarea) {
		FacturacionWSImpl.LOGGER.debug("crearAlbaranWS - INICIO");
		Aa79bAlbaran bean = aa79bEntradaTarea.getDatosPagoProveedores().getAlbaran();
		Aa79bSalidaTarea aa79bSalidaTarea = new Aa79bSalidaTarea();
		Albaran albaran = new Albaran();
		albaran.setRefAlbaran(bean.getRefAlbaran());
		Locale locale;
		if (StringUtils.isNotBlank(aa79bEntradaTarea.getIdioma())) {
			locale = new Locale(aa79bEntradaTarea.getIdioma());
		} else {
			locale = new Locale("eu");
		}
		Long refAlbaranCount = this.albaranService.existeReferencia(albaran);
		if (refAlbaranCount == 0) {
			albaran.setIdLote(bean.getIdLote());
			albaran.setEstado(EstadoAlbaranEnum.PENDIENTE_ENVIAR_IZO.getValue());
			this.albaranService.crearAlbaran(albaran);

			RegistroAcciones registroAcciones = new RegistroAcciones();
			registroAcciones.setIdPuntoMenu(PuntosMenuEnum.CONSULTA_ALBARANES_PROVEDOR.getValue());
			registroAcciones.setIdAccion(AccionesEnum.ALTA.getValue());
			registroAcciones.setObserv(this.appMessageSource.getMessage("label.regAcciones.crearAlbaran", null, locale)
					+ " " + bean.getRefAlbaran());
			registroAcciones.setUsuarioRegistro(aa79bEntradaTarea.getDni());
			this.registroAccionesService.addFromWS(registroAcciones);

			aa79bSalidaTarea.setEstado(Constants.ESTADO_WS_OK);
		} else {
			aa79bSalidaTarea.setEstado(Constants.ESTADO_WS_ERROR);
			aa79bSalidaTarea.setDescripcion(this.appMessageSource.getMessage(MENSAJE_REFERENCIA_EXISTE, null, locale));
		}
		FacturacionWSImpl.LOGGER.debug("WS_METHOD crearAlbaranWS - FIN: " + aa79bSalidaTarea);
		return aa79bSalidaTarea;
	}

	@WebMethod(operationName = "enviarAlbaranWS")
	public Aa79bSalidaTarea enviarAlbaranWS(Aa79bAlbaran bean) {
		FacturacionWSImpl.LOGGER.debug("enviarAlbaranWS - INICIO");
		Aa79bSalidaTarea aa79bSalidaTarea = new Aa79bSalidaTarea();
		Albaran albaran = new Albaran();
		albaran.setIdAlbaran(bean.getIdAlbaran());
		albaran.setEstado(bean.getEstado());
		albaran.setIdLote(bean.getIdLote());
		if (0 < this.albaranService.comprobarSiTareasAsociadas(albaran.getIdAlbaran())) {

			Albaran updatedAlbaran = this.albaranService.updateEstadoAlbaran(albaran);
			final List<String> listaDestinatarios = new ArrayList<String>();

			if (this.appConfiguration.getProperty(Constants.APP_ENTORNO) != null) {
				if (Constants.LOCAL.equals(this.appConfiguration.getProperty(Constants.APP_ENTORNO))) {
					listaDestinatarios.add(Constants.DESTINATARIO_EMAIL_LOCAL);
				} else {
					ConfigDireccionEmail configDireccionEmail = new ConfigDireccionEmail();
					configDireccionEmail.setId(Constants.CONFIG_DEFAULT_ID);
					ConfigDireccionEmail configMail = this.configDireccionEmailService.find(configDireccionEmail);
					listaDestinatarios.add(configMail.getDirEmail());
				}




				if (TareasServiceUtils.isNotEmptyLstDestinatarios(listaDestinatarios)) {

					bean = this.albaranService.find(bean);

					Lotes lote = new Lotes();
					lote.setIdLote(bean.getIdLote());
					lote = this.lotesService.find(lote);

					ParametrosEmail parametrosEmail = new ParametrosEmail();

					Locale localeEu = new Locale(Constants.LANG_EUSKERA);
					Map<String, String> infoEu = new LinkedHashMap<String, String>();
					Locale localeEs = new Locale(Constants.LANG_CASTELLANO);
					Map<String, String> infoEs = new LinkedHashMap<String, String>();

					infoEu.put(this.appMessageSource.getMessage("label.refPuntoAlbaran", null, localeEu), bean.getRefAlbaran());
					infoEs.put(this.appMessageSource.getMessage("label.refPuntoAlbaran", null, localeEs), bean.getRefAlbaran());

					infoEu.put(this.appMessageSource.getMessage("label.lote", null, localeEu), lote.getDescLoteEu());
					infoEs.put(this.appMessageSource.getMessage("label.lote", null, localeEs), lote.getDescLoteEs());

					parametrosEmail.setInfoEu(infoEu);
					parametrosEmail.setInfoEs(infoEs);


					this.mailService.sendMailWithParameters(TipoAvisoEnum.ENVIO_ALBARAN.getValue(), listaDestinatarios, parametrosEmail);
				}
			}

			aa79bSalidaTarea.setEstado(Constants.ESTADO_WS_OK);
			FacturacionWSImpl.LOGGER.debug("WS_METHOD enviarAlbaranWS - FIN: " + updatedAlbaran);
		} else {

			aa79bSalidaTarea.setEstado(Constants.ESTADO_WS_OK);
			aa79bSalidaTarea.setDescripcion(Constants.UNO.toString());
		}

		return aa79bSalidaTarea;
	}

	@WebMethod(operationName = "detalleTareaAlbaranWS")
	public Aa79bSalidaTarea detalleTareaAlbaranWS(Aa79bEntradaTarea bean) {
		FacturacionWSImpl.LOGGER.debug("detalleTareaAlbaranWS - INICIO");
		Aa79bSalidaTarea aa79bSalidaTarea = new Aa79bSalidaTarea();

		Aa79bEntradaEjecutarTarea aa79bEntradaEjecutarTarea = new Aa79bEntradaEjecutarTarea();
		aa79bEntradaEjecutarTarea.setDni(bean.getDni());
		aa79bEntradaEjecutarTarea.setIdTarea(bean.getIdTarea());

		Locale locale;
		if (StringUtils.isNotBlank(bean.getIdioma())) {
			locale = new Locale(bean.getIdioma());
		} else {
			locale = new Locale("eu");
		}

		try {
			Boolean accesoTarea = this.aa79bTareaWsService.getAccesoTarea(aa79bEntradaEjecutarTarea);
			if (accesoTarea) {
				aa79bSalidaTarea = this.albaranService.detalleTareaAlbaran(bean);
				aa79bSalidaTarea.setEstado(Constants.ESTADO_WS_OK);
			} else {
				aa79bSalidaTarea.setEstado(Constants.ESTADO_WS_ERROR);
				aa79bSalidaTarea.setDescripcion(
						this.appMessageSource.getMessage(MENSAJE_PROVEEDOR_SIN_ACCESO_A_TAREA, null, locale));
			}
		} catch (Exception e) {
			FacturacionWSImpl.LOGGER.error("WS_METHOD: detalleTareaAlbaranWS ", e);
			aa79bSalidaTarea.setEstado(Constants.ESTADO_WS_ERROR);
			aa79bSalidaTarea
					.setDescripcion(this.appMessageSource.getMessage(ERROR_OBTENIENDO_INFORMACION, null, locale));
		}

		FacturacionWSImpl.LOGGER.debug("WS_METHOD detalleTareaAlbaranWS - FIN: " + aa79bSalidaTarea);
		return aa79bSalidaTarea;
	}

	@WebMethod(operationName = "asociarAlbaranWS")
	public Aa79bSalidaTarea asociarAlbaranWS(Aa79bEntradaTarea bean) {
		FacturacionWSImpl.LOGGER.debug("asociarAlbaranWS - INICIO");
		Aa79bSalidaTarea aa79bSalidaTarea = new Aa79bSalidaTarea();

		bean.setTareasList(this.getTareasList(bean.getTareasListStr()));

		for (Aa79bTarea aa79bTarea : bean.getTareasList()) {
			Aa79bEntradaEjecutarTarea aa79bEntradaEjecutarTarea = new Aa79bEntradaEjecutarTarea();
			aa79bEntradaEjecutarTarea.setDni(bean.getDni());
			aa79bEntradaEjecutarTarea.setIdTarea(aa79bTarea.getIdTarea());

			Locale locale;
			if (StringUtils.isNotBlank(bean.getIdioma())) {
				locale = new Locale(bean.getIdioma());
			} else {
				locale = new Locale("eu");
			}

			try {
				Boolean accesoTarea = this.aa79bTareaWsService.getAccesoTarea(aa79bEntradaEjecutarTarea);
				if (accesoTarea) {
					DatosPagoProveedores datosPagoProveedores = cargarObjetoParaGestionAlbaran(aa79bTarea, bean);
					this.albaranService.asociarAlbaran(datosPagoProveedores);

					Expediente expedienteFilter = new Expediente();
					expedienteFilter.setAnyo(datosPagoProveedores.getAnyo());
					expedienteFilter.setNumExp(datosPagoProveedores.getNumExpediente());
					Expediente exp = this.expedienteService.find(expedienteFilter);

					RegistroAcciones registroAcciones = new RegistroAcciones();
					registroAcciones.setIdEstadoBitacora(exp.getBitacoraExpediente().getIdEstadoBitacora());
					registroAcciones.setAnyo(datosPagoProveedores.getAnyo());
					registroAcciones.setNumExp(datosPagoProveedores.getNumExpediente());
					registroAcciones.setIdPuntoMenu(PuntosMenuEnum.CONSULTA_ALBARANES_PROVEDOR.getValue());
					registroAcciones.setIdAccion(AccionesEnum.MODIFICACION.getValue());
					registroAcciones.setObserv(
							this.appMessageSource.getMessage("label.regAcciones.asociadoAlbaran", null, locale));
					registroAcciones.setUsuarioRegistro(bean.getDni());
					this.registroAccionesService.addFromWS(registroAcciones);
					aa79bSalidaTarea.setEstado(Constants.ESTADO_WS_OK);
				} else {
					aa79bSalidaTarea.setEstado(Constants.ESTADO_WS_ERROR);
					aa79bSalidaTarea.setDescripcion(
							this.appMessageSource.getMessage(MENSAJE_PROVEEDOR_SIN_ACCESO_A_TAREA, null, locale));
				}
			} catch (Exception e) {
				FacturacionWSImpl.LOGGER.error("WS_METHOD: asociarAlbaranWS ", e);
				aa79bSalidaTarea.setEstado(Constants.ESTADO_WS_ERROR);
				aa79bSalidaTarea
						.setDescripcion(this.appMessageSource.getMessage(ERROR_OBTENIENDO_INFORMACION, null, locale));
			}
		}

		FacturacionWSImpl.LOGGER.debug("WS_METHOD asociarAlbaranWS - FIN: " + aa79bSalidaTarea);
		return aa79bSalidaTarea;
	}

	@WebMethod(operationName = "desasociarAlbaranWS")
	public Aa79bSalidaTarea desasociarAlbaranWS(Aa79bEntradaTarea bean) {
		FacturacionWSImpl.LOGGER.debug("desasociarAlbaranWS - INICIO");
		Aa79bSalidaTarea aa79bSalidaTarea = new Aa79bSalidaTarea();

		bean.setTareasList(this.getTareasList(bean.getTareasListStr()));

		for (Aa79bTarea aa79bTarea : bean.getTareasList()) {
			Aa79bEntradaEjecutarTarea aa79bEntradaEjecutarTarea = new Aa79bEntradaEjecutarTarea();
			aa79bEntradaEjecutarTarea.setDni(bean.getDni());
			aa79bEntradaEjecutarTarea.setIdTarea(aa79bTarea.getIdTarea());

			Locale locale;
			if (StringUtils.isNotBlank(bean.getIdioma())) {
				locale = new Locale(bean.getIdioma());
			} else {
				locale = new Locale("eu");
			}

			try {
				DatosPagoProveedores datosPagoProveedores = cargarObjetoParaGestionAlbaran(aa79bTarea, bean);
				this.albaranService.desasociarAlbaran(datosPagoProveedores);

				Expediente expedienteFilter = new Expediente();
				expedienteFilter.setAnyo(datosPagoProveedores.getAnyo());
				expedienteFilter.setNumExp(datosPagoProveedores.getNumExpediente());
				Expediente exp = this.expedienteService.find(expedienteFilter);

				RegistroAcciones registroAcciones = new RegistroAcciones();
				registroAcciones.setIdEstadoBitacora(exp.getBitacoraExpediente().getIdEstadoBitacora());
				registroAcciones.setAnyo(datosPagoProveedores.getAnyo());
				registroAcciones.setNumExp(datosPagoProveedores.getNumExpediente());
				registroAcciones.setIdPuntoMenu(PuntosMenuEnum.CONSULTA_ALBARANES_PROVEDOR.getValue());
				registroAcciones.setIdAccion(AccionesEnum.MODIFICACION.getValue());
				registroAcciones.setObserv(
						this.appMessageSource.getMessage("label.regAcciones.desasociadoAlbaran", null, locale));
				registroAcciones.setUsuarioRegistro(bean.getDni());
				this.registroAccionesService.addFromWS(registroAcciones);
				aa79bSalidaTarea.setEstado(Constants.ESTADO_WS_OK);
			} catch (Exception e) {
				FacturacionWSImpl.LOGGER.error("WS_METHOD: desasociarAlbaranWS ", e);
				aa79bSalidaTarea.setEstado(Constants.ESTADO_WS_ERROR);
				aa79bSalidaTarea
						.setDescripcion(this.appMessageSource.getMessage(ERROR_OBTENIENDO_INFORMACION, null, locale));
			}
		}

		FacturacionWSImpl.LOGGER.debug("WS_METHOD desasociarAlbaranWS - FIN: " + aa79bSalidaTarea);
		return aa79bSalidaTarea;
	}

	@WebMethod(operationName = "comprobarAsociacionAlbaranWS")
	public Aa79bSalidaAsociacionAlbaran comprobarAsociacionAlbaranWS(Aa79bEntradaTareasAlbaran bean) {
		FacturacionWSImpl.LOGGER.debug("comprobarAsociacionAlbaranWS - INICIO");
		Aa79bSalidaAsociacionAlbaran aa79bSalidaAsociacionAlbaran = new Aa79bSalidaAsociacionAlbaran();
		EntradaAlbaranTareas entradaAlbaranTareas = new EntradaAlbaranTareas();
		Albaran albaran = new Albaran();
		albaran.setEstado(EstadoAlbaranEnum.PENDIENTE_ENVIAR_IZO.getValue());
		Tareas tareas = new Tareas();
		Long comprobarAsociacionAlbaran;

		bean.setTareasList(this.getTareasList(bean.getTareasListStr()));
		entradaAlbaranTareas.setAccionComprobarAsoc(bean.getAccionComprobarAsoc());
		for (Aa79bTarea tarea : bean.getTareasList()) {
			tareas.setIdTarea(tarea.getIdTarea());
			entradaAlbaranTareas.setTareas(tareas);
			entradaAlbaranTareas.setAlbaran(albaran);
			comprobarAsociacionAlbaran = this.albaranService.comprobarAsociacionAlbaranPorTarea(entradaAlbaranTareas);
			if (comprobarAsociacionAlbaran > 0) {
				aa79bSalidaAsociacionAlbaran.setEstadoCorrecto(false);
				aa79bSalidaAsociacionAlbaran.setEstado(Constants.ESTADO_WS_OK);
				return aa79bSalidaAsociacionAlbaran;
			}
		}
		aa79bSalidaAsociacionAlbaran.setEstadoCorrecto(true);
		aa79bSalidaAsociacionAlbaran.setEstado(Constants.ESTADO_WS_OK);
		FacturacionWSImpl.LOGGER.debug("WS_METHOD comprobarAsociacionAlbaranWS - FIN: " + bean);
		return aa79bSalidaAsociacionAlbaran;
	}

	@WebMethod(operationName = "informeXlsxTareasAlbaranWS")
	public FicheroWS informeXlsxTareasAlbaranWS(Aa79bInformeTareasAlbaran bean) {
		FacturacionWSImpl.LOGGER.debug("informeXlsxTareasAlbaranWS - INICIO");

		Locale locale;
		if (StringUtils.isNotBlank(bean.getIdioma())) {
			locale = new Locale(bean.getIdioma());
		} else {
			locale = new Locale("eu");
		}

		JQGridResponseDto<Aa79bSalidaTarea> respuesta = this.getDatosInforme(bean);

		final String expedienteLabel = "titulo.tarea";
		final String label = this.appMessageSource.getMessage(expedienteLabel, new Object[] {}, locale);

		ExcelModel excelModel = this.excelReportService.getExcelModelJson(bean.getCriterios(), bean.getColumns(),
				respuesta.getRows(), locale, expedienteLabel, label);
		byte[] bytesInforme = null;
		FicheroWS ficheroBean = new FicheroWS();
		try {
			bytesInforme = this.excelReportService.generarExcel(FormatoInformeEnum.EXCEL.getTemplate(), excelModel);
		} catch (Exception e1) {
			FacturacionWSImpl.LOGGER.info("ERROR informeXlsxTareasAlbaranWS, Error: " + e1);
			ficheroBean.setEstado(Constants.ESTADO_WS_ERROR);
			ficheroBean.setDescripcion("ERROR informeXlsxTareasAlbaranWS");
		}

		ficheroBean.setNombre(this.appMessageSource.getMessage("titulo.informeTareasAlbaran", null, locale));
		ficheroBean.setExtension(FormatoInformeEnum.EXCEL.getExtension());
		ficheroBean.setContentType(FormatoInformeEnum.EXCEL.getContentType());
		ficheroBean.setTamano((long) bytesInforme.length);

		try {
			ficheroBean.setRutaPif(this.pidService.subidaPif(ficheroBean.getNombre() + ficheroBean.getExtension(),
					bytesInforme, this.appConfiguration.getProperty("rutaPifAa06a"), false));
		} catch (UnsupportedEncodingException e) {
			FacturacionWSImpl.LOGGER.info("UnsupportedEncodingException - subidaPif: " + e);
			ficheroBean.setEstado(Constants.ESTADO_WS_ERROR);
			ficheroBean.setDescripcion("ERROR subidaPif");

		} catch (Exception e) {
			FacturacionWSImpl.LOGGER.error("pruebapid: JOSE - subidaFichero Error: ", e);
			ficheroBean.setEstado(Constants.ESTADO_WS_ERROR);
			ficheroBean.setDescripcion(this.appMessageSource.getMessage("mensaje.fichero.errorPIF", null, locale));
		}

		FacturacionWSImpl.LOGGER.debug("WS_METHOD informeXlsxTareasAlbaranWS - FIN: " + bean);
		return ficheroBean;
	}

	@WebMethod(operationName = "informePdfTareasAlbaranWS")
	public FicheroWS informePdfTareasAlbaranWS(Aa79bInformeTareasAlbaran bean) {
		FacturacionWSImpl.LOGGER.debug("informePdfTareasAlbaranWS - INICIO");

		Locale locale;
		if (StringUtils.isNotBlank(bean.getIdioma())) {
			locale = new Locale(bean.getIdioma());
		} else {
			locale = new Locale("eu");
		}

		JQGridResponseDto<Aa79bSalidaTarea> respuesta = this.getDatosInforme(bean);

		final String expedienteLabel = "titulo.tarea";
		final String label = this.appMessageSource.getMessage(expedienteLabel, new Object[] {}, locale);

		PdfModel pdfModel = this.pdfReportService.getPdfModelJson(bean.getCriterios(), bean.getColumns(),
				respuesta.getRows(), locale, label);
		byte[] bytesInforme = null;
		FicheroWS ficheroBean = new FicheroWS();
		try {
			bytesInforme = this.pdfReportService.generarPdf(FormatoInformeEnum.PDF.getTemplate(), pdfModel, locale);
		} catch (Exception e1) {
			FacturacionWSImpl.LOGGER.info("ERROR informePdfTareasAlbaranWS, Error: " + e1);
			ficheroBean.setEstado(Constants.ESTADO_WS_ERROR);
			ficheroBean.setDescripcion("ERROR informePdfTareasAlbaranWS");
		}

		ficheroBean.setNombre(this.appMessageSource.getMessage("titulo.informeTareasAlbaran", null, locale));
		ficheroBean.setExtension(FormatoInformeEnum.PDF.getExtension());
		ficheroBean.setContentType(FormatoInformeEnum.PDF.getContentType());
		ficheroBean.setTamano((long) bytesInforme.length);

		try {
			ficheroBean.setRutaPif(this.pidService.subidaPif(ficheroBean.getNombre() + ficheroBean.getExtension(),
					bytesInforme, this.appConfiguration.getProperty("rutaPifAa06a"), false));
		} catch (UnsupportedEncodingException e) {
			FacturacionWSImpl.LOGGER.info("UnsupportedEncodingException - subidaPif: " + e);
			ficheroBean.setEstado(Constants.ESTADO_WS_ERROR);
			ficheroBean.setDescripcion("ERROR subidaPif");

		} catch (Exception e) {
			FacturacionWSImpl.LOGGER.error("pruebapid: JOSE - subidaFichero Error: ", e);
			ficheroBean.setEstado(Constants.ESTADO_WS_ERROR);
			ficheroBean.setDescripcion(this.appMessageSource.getMessage("mensaje.fichero.errorPIF", null, locale));
		}

		FacturacionWSImpl.LOGGER.debug("WS_METHOD informePdfTareasAlbaranWS - FIN: " + bean);
		return ficheroBean;
	}

	private JQGridResponseDto<Aa79bSalidaTarea> getDatosInforme(Aa79bInformeTareasAlbaran bean) {
		Aa79bEntradaTarea aa79bEntradaTarea = bean.getAa79bEntradaTarea();
		JQGridRequestDto jqGridRequestDtoReport = new JQGridRequestDto(null, null, bean.getSidx(), bean.getSord());
		JQGridResponseDto<Aa79bSalidaTarea> respuesta = this.albaranService.findTareasAlbaran(aa79bEntradaTarea,
				jqGridRequestDtoReport, false, false);
		return respuesta;
	}

	private List<Aa79bTarea> getTareasList(String bean) {
		List<Aa79bTarea> idTareasList = new ArrayList<Aa79bTarea>();
		String[] idTareas = bean.split(",");

		for (String idTarea : idTareas) {
			Aa79bTarea aa79bTarea = new Aa79bTarea();
			aa79bTarea.setIdTarea(new BigDecimal(idTarea));
			idTareasList.add(aa79bTarea);
		}

		return idTareasList;
	}

	@WebMethod(operationName = "obtenerDatosConsultaFacturasWS")
	public Aa79bSalidaDatosConsulFact obtenerDatosConsultaFacturasWS(
			Aa79bEntradaDatosConsulFact aa79bEntradaDatosConsulFact) {
		FacturacionWSImpl.LOGGER.debug("WS_METHOD obtenerDatosConsultaFacturasWS - BEGIN");

		Aa79bSalidaDatosConsulFact aa79bSalidaDatosConsulFact = new Aa79bSalidaDatosConsulFact();

		Locale locale;
		if (StringUtils.isNotBlank(aa79bEntradaDatosConsulFact.getIdioma())) {
			locale = new Locale(aa79bEntradaDatosConsulFact.getIdioma());
		} else {
			locale = new Locale(Constants.LANG_EUSKERA);
		}

		try {

			List<String> titulosExpediente = this.facturasService
					.getTitulosExpedienteFacturados(aa79bEntradaDatosConsulFact.getDni());

			aa79bSalidaDatosConsulFact.setTitulosExpediente(titulosExpediente);

			aa79bSalidaDatosConsulFact.setEstado(Constants.ESTADO_WS_OK);

			FacturacionWSImpl.LOGGER
					.info("WS_METHOD obtenerDatosConsultaFacturasWS END\nreturn" + aa79bSalidaDatosConsulFact);

		} catch (Exception e) {
			FacturacionWSImpl.LOGGER.error("[POST - subm]: obtenerDatosConsultaFacturasWS ", e);
			aa79bSalidaDatosConsulFact.setEstado(Constants.ESTADO_WS_ERROR);
			aa79bSalidaDatosConsulFact.setDescripcion(
					this.appMessageSource.getMessage(ERROR_OBTENIENDO_INFORMACION_FACTURA, null, locale));
		}

		FacturacionWSImpl.LOGGER.debug("obtenerDatosConsultaFacturasWS - FIN");

		return aa79bSalidaDatosConsulFact;
	}

	@WebMethod(operationName = "consultaFacturasWS")
	public Aa79bTablaWebService<Aa79bSalidaConsultaFacturas> consultaFacturasWS(
			Aa79bEntradaConsultaFacturas aa79bEntradaConsultaFacturas,
			Aa79bTablaWebService<Aa79bSalidaConsultaFacturas> aa79bTablaWebService,
			Aa79bSalidaConsultaFacturas aa79bSalidaConsultaFacturas) {
		FacturacionWSImpl.LOGGER.debug("WS_METHOD consultaFacturasWS - BEGIN");

		Locale locale;
		if (StringUtils.isNotBlank(aa79bEntradaConsultaFacturas.getIdioma())) {
			locale = new Locale(aa79bEntradaConsultaFacturas.getIdioma());
		} else {
			locale = new Locale(Constants.LANG_EUSKERA);
		}

		Aa79bTablaWebService<Aa79bSalidaConsultaFacturas> tabla = new Aa79bTablaWebService<Aa79bSalidaConsultaFacturas>();

		try {

			JQGridRequestDto jqGridRequestDto = null;
			if (aa79bTablaWebService != null && Utils.isValidAa79bTablaWebService(aa79bTablaWebService)) {
				jqGridRequestDto = new JQGridRequestDto(aa79bTablaWebService.getRows(), aa79bTablaWebService.getPage(),
						aa79bTablaWebService.getSidx(), aa79bTablaWebService.getSord());

			}

			final JQGridResponseDto<Aa79bSalidaConsultaFacturas> jqGrid = this.facturasService
					.consultaFacturas(aa79bEntradaConsultaFacturas, jqGridRequestDto, false);
			tabla.setEstado(Constants.ESTADO_WS_OK);
			@SuppressWarnings(value = "unchecked")
			final Aa79bJaxbList<Aa79bSalidaConsultaFacturas> lista = new Aa79bJaxbList<Aa79bSalidaConsultaFacturas>(
					(List<Aa79bSalidaConsultaFacturas>) jqGrid.getRows());
			tabla.setLista(lista);
			tabla.setRecordNum(Long.valueOf(jqGrid.getRecords()));

			FacturacionWSImpl.LOGGER.info("WS_METHOD consultaFacturasWS END\nreturn" + tabla);

		} catch (Exception e) {
			FacturacionWSImpl.LOGGER.error("[POST - subm]: consultaFacturasWS ", e);
			tabla.setSidx("-1");
			tabla.setSord(e.getMessage());
			tabla.setEstado(Constants.ESTADO_WS_ERROR);
			tabla.setDescripcion(this.appMessageSource.getMessage(ERROR_OBTENIENDO_INFORMACION_FACTURA, null, locale));
		}

		FacturacionWSImpl.LOGGER.debug("consultaFacturasWS - FIN");

		return tabla;
	}

	@WebMethod(operationName = "detalleFacturaWS")
	public Aa79bSalidaDetalleFactura detalleFacturaWS(Aa79bEntradaFactura bean) {
		FacturacionWSImpl.LOGGER.debug("WS_METHOD detalleFacturaWS - BEGIN\nidTarea: " + bean.getIdFactura());

		Aa79bSalidaDetalleFactura aa79bSalidaDetalleFactura = new Aa79bSalidaDetalleFactura();

		Locale locale;
		if (StringUtils.isNotBlank(bean.getIdioma())) {
			locale = new Locale(bean.getIdioma());
		} else {
			locale = new Locale(Constants.LANG_EUSKERA);
		}

		try {

			// comprobar el acceso a la factura para el usuario conectado
			boolean accesoFactura = this.facturasService.getAccesoFactura(bean.getDni(), bean.getIdFactura());
			if (accesoFactura) {

				Facturas factura = this.facturasService.findDetalleFactura(bean.getIdFactura());

				aa79bSalidaDetalleFactura = WSUtils.parsearFacturaAFormatoWsAa79(factura);

				if (factura.getIdEntidadAsoc() != null && factura.getTipoEntidadAsoc() != null) {
					Entidad entidad = new Entidad();
					entidad.setCodigo(factura.getIdEntidadAsoc().intValue());
					entidad.setTipo(factura.getTipoEntidadAsoc());

					Entidad rstEntidad = this.entidadService.find(entidad);

					Aa79bEmpresasProveedoras aa79bEmpresasProveedoras = WSUtils
							.parsearDatosEmpresaProveedoraToWS(rstEntidad);

					aa79bSalidaDetalleFactura.setEntidad(aa79bEmpresasProveedoras);
				}

				if (factura.getDniContacto() != null) {
					DatosContacto datosContacto = new DatosContacto();
					datosContacto.setDni031(factura.getDniContacto());

					DatosContacto rstDatosContacto = this.datosContactoService.findDatosContacto(datosContacto);

					Aa79bDatosContacto aa79bDatosContacto = WSUtils.parsearDatosContactoToWS(rstDatosContacto);

					aa79bSalidaDetalleFactura.setDatosContacto(aa79bDatosContacto);
				}

				aa79bSalidaDetalleFactura.setEstado(Constants.ESTADO_WS_OK);

				FacturacionWSImpl.LOGGER.info("WS_METHOD detalleFacturaWS END\nreturn" + aa79bSalidaDetalleFactura);
			} else {
				aa79bSalidaDetalleFactura.setEstado(Constants.ESTADO_WS_ERROR);
				aa79bSalidaDetalleFactura.setDescripcion(
						this.appMessageSource.getMessage(MENSAJE_PROVEEDOR_SIN_ACCESO_A_FACTURA, null, locale));
			}

		} catch (Exception e) {
			FacturacionWSImpl.LOGGER.error("[POST - subm]: detalleFacturaWS ", e);
			aa79bSalidaDetalleFactura.setEstado(Constants.ESTADO_WS_ERROR);
			aa79bSalidaDetalleFactura.setDescripcion(
					this.appMessageSource.getMessage(ERROR_OBTENIENDO_INFORMACION_FACTURA, null, locale));
		}

		FacturacionWSImpl.LOGGER.debug("detalleFacturaWS - FIN");
		return aa79bSalidaDetalleFactura;
	}

	@WebMethod(operationName = "expedientesFacturaTraduccionWS")
	public Aa79bTablaWebService<Aa79bExpediente> expedientesFacturaTraduccionWS(Aa79bEntradaFactura bean,
			Aa79bTablaWebService<Aa79bExpediente> aa79bTablaWebService, Aa79bExpediente aa79bExpediente) {
		FacturacionWSImpl.LOGGER
				.debug("WS_METHOD expedientesFacturaTraduccionWS - BEGIN\nidTarea: " + bean.getIdFactura());

		Locale locale;
		if (StringUtils.isNotBlank(bean.getIdioma())) {
			locale = new Locale(bean.getIdioma());
		} else {
			locale = new Locale(Constants.LANG_EUSKERA);
		}

		Aa79bTablaWebService<Aa79bExpediente> tabla = new Aa79bTablaWebService<Aa79bExpediente>();

		try {

			JQGridRequestDto jqGridRequestDto = null;
			if (aa79bTablaWebService != null && Utils.isValidAa79bTablaWebService(aa79bTablaWebService)) {
				jqGridRequestDto = new JQGridRequestDto(aa79bTablaWebService.getRows(), aa79bTablaWebService.getPage(),
						aa79bTablaWebService.getSidx(), aa79bTablaWebService.getSord());

			}

			final JQGridResponseDto<Aa79bExpediente> jqGrid = this.facturasService
					.expedientesFacturaTraduccion(bean.getIdFactura(), jqGridRequestDto, false);
			tabla.setEstado(Constants.ESTADO_WS_OK);
			@SuppressWarnings(value = "unchecked")
			final Aa79bJaxbList<Aa79bExpediente> lista = new Aa79bJaxbList<Aa79bExpediente>(
					(List<Aa79bExpediente>) jqGrid.getRows());
			tabla.setLista(lista);
			tabla.setRecordNum(Long.valueOf(jqGrid.getRecords()));

			FacturacionWSImpl.LOGGER.info("WS_METHOD expedientesFacturaTraduccionWS END\nreturn" + tabla);

		} catch (Exception e) {
			FacturacionWSImpl.LOGGER.error("[POST - subm]: expedientesFacturaTraduccionWS ", e);
			tabla.setSidx("-1");
			tabla.setSord(e.getMessage());
			tabla.setEstado(Constants.ESTADO_WS_ERROR);
			tabla.setDescripcion(this.appMessageSource.getMessage(ERROR_OBTENIENDO_INFORMACION_FACTURA, null, locale));
		}

		FacturacionWSImpl.LOGGER.debug("expedientesFacturaTraduccionWS - FIN");

		return tabla;
	}

	@WebMethod(operationName = "expedientesFacturaInterpretacionWS")
	public Aa79bTablaWebService<Aa79bExpediente> expedientesFacturaInterpretacionWS(Aa79bEntradaFactura bean,
			Aa79bTablaWebService<Aa79bExpediente> aa79bTablaWebService, Aa79bExpediente aa79bExpediente) {
		FacturacionWSImpl.LOGGER
				.debug("WS_METHOD expedientesFacturaInterpretacionWS - BEGIN\nidTarea: " + bean.getIdFactura());

		Locale locale;
		if (StringUtils.isNotBlank(bean.getIdioma())) {
			locale = new Locale(bean.getIdioma());
		} else {
			locale = new Locale(Constants.LANG_EUSKERA);
		}

		Aa79bTablaWebService<Aa79bExpediente> tabla = new Aa79bTablaWebService<Aa79bExpediente>();

		try {

			JQGridRequestDto jqGridRequestDto = null;
			if (aa79bTablaWebService != null && Utils.isValidAa79bTablaWebService(aa79bTablaWebService)) {
				jqGridRequestDto = new JQGridRequestDto(aa79bTablaWebService.getRows(), aa79bTablaWebService.getPage(),
						aa79bTablaWebService.getSidx(), aa79bTablaWebService.getSord());

			}

			final JQGridResponseDto<Aa79bExpediente> jqGrid = this.facturasService
					.expedientesFacturaInterpretacion(bean.getIdFactura(), jqGridRequestDto, false);
			tabla.setEstado(Constants.ESTADO_WS_OK);
			@SuppressWarnings(value = "unchecked")
			final Aa79bJaxbList<Aa79bExpediente> lista = new Aa79bJaxbList<Aa79bExpediente>(
					(List<Aa79bExpediente>) jqGrid.getRows());
			tabla.setLista(lista);
			tabla.setRecordNum(Long.valueOf(jqGrid.getRecords()));

			FacturacionWSImpl.LOGGER.info("WS_METHOD expedientesFacturaInterpretacionWS END\nreturn" + tabla);

		} catch (Exception e) {
			FacturacionWSImpl.LOGGER.error("[POST - subm]: expedientesFacturaInterpretacionWS ", e);
			tabla.setSidx("-1");
			tabla.setSord(e.getMessage());
			tabla.setEstado(Constants.ESTADO_WS_ERROR);
			tabla.setDescripcion(this.appMessageSource.getMessage(ERROR_OBTENIENDO_INFORMACION_FACTURA, null, locale));
		}

		FacturacionWSImpl.LOGGER.debug("expedientesFacturaInterpretacionWS - FIN");

		return tabla;
	}

	@WebMethod(operationName = "documentoFacturaWS")
	public Aa79bSalidaDocumentoFactura documentoFacturaWS(Aa79bEntradaFactura bean) {
		FacturacionWSImpl.LOGGER.debug("WS_METHOD documentoFacturaWS - BEGIN\nidTarea: " + bean.getIdFactura());

		Aa79bSalidaDocumentoFactura aa79bSalidaDocumentoFactura = new Aa79bSalidaDocumentoFactura();

		Locale locale;
		if (StringUtils.isNotBlank(bean.getIdioma())) {
			locale = new Locale(bean.getIdioma());
		} else {
			locale = new Locale(Constants.LANG_EUSKERA);
		}

		try {

			// comprobar el acceso a la factura para el usuario conectado
			boolean accesoFactura = this.facturasService.getAccesoFactura(bean.getDni(), bean.getIdFactura());
			if (accesoFactura) {

				Fichero fichero = this.facturasService.findDocumentoFactura(bean.getIdFactura());

				aa79bSalidaDocumentoFactura.setFichero(WSUtils.parsearFicheroAFormatoWsAa79(fichero));
				aa79bSalidaDocumentoFactura.setEstado(Constants.ESTADO_WS_OK);

				FacturacionWSImpl.LOGGER.info("WS_METHOD documentoFacturaWS END\nreturn" + aa79bSalidaDocumentoFactura);
			} else {
				aa79bSalidaDocumentoFactura.setEstado(Constants.ESTADO_WS_ERROR);
				aa79bSalidaDocumentoFactura.setDescripcion(
						this.appMessageSource.getMessage(MENSAJE_PROVEEDOR_SIN_ACCESO_A_FACTURA, null, locale));
			}

		} catch (Exception e) {
			FacturacionWSImpl.LOGGER.error("[POST - subm]: documentoFacturaWS ", e);
			aa79bSalidaDocumentoFactura.setEstado(Constants.ESTADO_WS_ERROR);
			aa79bSalidaDocumentoFactura.setDescripcion(
					this.appMessageSource.getMessage(ERROR_OBTENIENDO_INFORMACION_FACTURA, null, locale));
		}

		FacturacionWSImpl.LOGGER.debug("documentoFacturaWS - FIN");
		return aa79bSalidaDocumentoFactura;
	}

	@WebMethod(operationName = "cartaPagoWS")
	public Aa79bSalidaDocumentoFactura cartaPagoWS(Aa79bEntradaFactura bean) {
		FacturacionWSImpl.LOGGER.debug("WS_METHOD cartaPagoWS - BEGIN\nidTarea: " + bean.getIdFactura());

		Aa79bSalidaDocumentoFactura aa79bSalidaDocumentoFactura = new Aa79bSalidaDocumentoFactura();

		Locale locale;
		if (StringUtils.isNotBlank(bean.getIdioma())) {
			locale = new Locale(bean.getIdioma());
		} else {
			locale = new Locale(Constants.LANG_EUSKERA);
		}

		try {

			// comprobar el acceso a la factura para el usuario conectado
			boolean accesoFactura = this.facturasService.getAccesoFactura(bean.getDni(), bean.getIdFactura());
			if (accesoFactura) {

				Fichero fichero = this.facturasService.findCartaPago(bean.getIdFactura());

				aa79bSalidaDocumentoFactura.setFichero(WSUtils.parsearFicheroAFormatoWsAa79(fichero));
				aa79bSalidaDocumentoFactura.setEstado(Constants.ESTADO_WS_OK);

				FacturacionWSImpl.LOGGER.info("WS_METHOD cartaPagoWS END\nreturn" + aa79bSalidaDocumentoFactura);
			} else {
				aa79bSalidaDocumentoFactura.setEstado(Constants.ESTADO_WS_ERROR);
				aa79bSalidaDocumentoFactura.setDescripcion(
						this.appMessageSource.getMessage(MENSAJE_PROVEEDOR_SIN_ACCESO_A_FACTURA, null, locale));
			}

		} catch (Exception e) {
			FacturacionWSImpl.LOGGER.error("[POST - subm]: cartaPagoWS ", e);
			aa79bSalidaDocumentoFactura.setEstado(Constants.ESTADO_WS_ERROR);
			aa79bSalidaDocumentoFactura.setDescripcion(
					this.appMessageSource.getMessage(ERROR_OBTENIENDO_INFORMACION_FACTURA, null, locale));
		}

		FacturacionWSImpl.LOGGER.debug("cartaPagoWS - FIN");
		return aa79bSalidaDocumentoFactura;
	}

	@WebMethod(operationName = "informeFacturasXlsxWS")
	public FicheroWS informeFacturasXlsxWS(Aa79bInformeFacturas aa79bInformeFacturas) {
		FacturacionWSImpl.LOGGER.debug("informeFacturasXlsxWS - INICIO");

		this.modificarCamposInformeFacturas(aa79bInformeFacturas);
		Locale locale;
		if (StringUtils.isNotBlank(aa79bInformeFacturas.getIdioma())) {
			locale = new Locale(aa79bInformeFacturas.getIdioma());
		} else {
			locale = new Locale(Constants.LANG_EUSKERA);
		}

		JQGridResponseDto<Aa79bSalidaConsultaFacturas> respuesta = this.getDatosInformeFacturas(aa79bInformeFacturas);

		final String reportTitle = "title.consultaFacturas";
		final String label = this.appMessageSource.getMessage(reportTitle, new Object[] {}, locale);

		ExcelModel excelModel = this.excelReportService.getExcelModelJson(aa79bInformeFacturas.getCriterios(),
				aa79bInformeFacturas.getColumns(), respuesta.getRows(), locale, reportTitle, label);
		byte[] bytesInforme = null;
		FicheroWS ficheroBean = new FicheroWS();
		try {
			bytesInforme = this.excelReportService.generarExcel(FormatoInformeEnum.EXCEL.getTemplate(), excelModel);
		} catch (Exception e1) {
			FacturacionWSImpl.LOGGER.info("ERROR generarExcel, Error: " + e1);
			ficheroBean.setEstado(Constants.ESTADO_WS_ERROR);
			ficheroBean.setDescripcion("ERROR generarExcel");
		}

		ficheroBean.setNombre(this.appMessageSource.getMessage("titulo.informeFacturas", null, locale));
		ficheroBean.setExtension(FormatoInformeEnum.EXCEL.getExtension());
		ficheroBean.setContentType(FormatoInformeEnum.EXCEL.getContentType());
		ficheroBean.setTamano((long) bytesInforme.length);

		try {
			ficheroBean.setRutaPif(this.pidService.subidaPif(ficheroBean.getNombre() + ficheroBean.getExtension(),
					bytesInforme, this.appConfiguration.getProperty("rutaPifAa06a"), false));
		} catch (UnsupportedEncodingException e) {
			FacturacionWSImpl.LOGGER.info("UnsupportedEncodingException - subidaPif: " + e);
			ficheroBean.setEstado(Constants.ESTADO_WS_ERROR);
			ficheroBean.setDescripcion("ERROR subidaPif");

		} catch (Exception e) {
			FacturacionWSImpl.LOGGER.error("pruebapid: subidaFichero Error: ", e);
			ficheroBean.setEstado(Constants.ESTADO_WS_ERROR);
			ficheroBean.setDescripcion(this.appMessageSource.getMessage("mensaje.fichero.errorPIF", null, locale));
		}

		FacturacionWSImpl.LOGGER.debug("WS_METHOD informeFacturasXlsxWS - FIN: " + aa79bInformeFacturas);
		return ficheroBean;
	}

	@WebMethod(operationName = "informeFacturasPdfWS")
	public FicheroWS informeFacturasPdfWS(Aa79bInformeFacturas aa79bInformeFacturas) {
		FacturacionWSImpl.LOGGER.debug("informeFacturasPdfWS - INICIO");

		this.modificarCamposInformeFacturas(aa79bInformeFacturas);
		Locale locale;
		if (StringUtils.isNotBlank(aa79bInformeFacturas.getIdioma())) {
			locale = new Locale(aa79bInformeFacturas.getIdioma());
		} else {
			locale = new Locale(Constants.LANG_EUSKERA);
		}

		JQGridResponseDto<Aa79bSalidaConsultaFacturas> respuesta = this.getDatosInformeFacturas(aa79bInformeFacturas);

		final String reportTitle = "title.consultaFacturas";
		final String label = this.appMessageSource.getMessage(reportTitle, new Object[] {}, locale);

		PdfModel pdfModel = this.pdfReportService.getPdfModelJson(aa79bInformeFacturas.getCriterios(),
				aa79bInformeFacturas.getColumns(), respuesta.getRows(), locale, label);
		byte[] bytesInforme = null;
		FicheroWS ficheroBean = new FicheroWS();
		ficheroBean.setEstado(Constants.ESTADO_WS_OK);
		try {
			bytesInforme = this.aa79bPdfReportService.generarPdf(FormatoInformeEnum.PDF.getTemplate(), pdfModel, locale);
		} catch (Exception e1) {
			FacturacionWSImpl.LOGGER.info("ERROR generarPdf, Error: " + e1);
			ficheroBean.setEstado(Constants.ESTADO_WS_ERROR);
			ficheroBean.setDescripcion("ERROR generarPdf");
		}

		ficheroBean.setNombre(this.appMessageSource.getMessage("titulo.informeFacturas", null, locale));
		ficheroBean.setExtension(FormatoInformeEnum.PDF.getExtension());
		ficheroBean.setContentType(FormatoInformeEnum.PDF.getContentType());
		ficheroBean.setTamano((long) bytesInforme.length);

		try {
			ficheroBean.setRutaPif(this.pidService.subidaPif(ficheroBean.getNombre() + ficheroBean.getExtension(),
					bytesInforme, this.appConfiguration.getProperty("rutaPifAa06a"), false));
		} catch (UnsupportedEncodingException e) {
			FacturacionWSImpl.LOGGER.info("UnsupportedEncodingException - subidaPif: " + e);
			ficheroBean.setEstado(Constants.ESTADO_WS_ERROR);
			ficheroBean.setDescripcion("ERROR subidaPif");

		} catch (Exception e) {
			FacturacionWSImpl.LOGGER.error("pruebapid: subidaFichero Error: ", e);
			ficheroBean.setEstado(Constants.ESTADO_WS_ERROR);
			ficheroBean.setDescripcion(this.appMessageSource.getMessage("mensaje.fichero.errorPIF", null, locale));
		}

		FacturacionWSImpl.LOGGER.debug("WS_METHOD informeFacturasXlsxWS - FIN: " + aa79bInformeFacturas);
		return ficheroBean;

	}

	/**
	 * Muestra campos adicionales en informe si encuentra campos especificos en
	 * columnas seleccionadas
	 *
	 * @param aa79bInformeTareasProveedor
	 *            Aa79bInformeTareasProveedor
	 */
	private void modificarCamposInformeFacturas(Aa79bInformeFacturas aa79bInformeFacturas) {
		String columnas = aa79bInformeFacturas.getColumns();
		if (columnas.indexOf(CAMPO_FECHA_EMISION) >= Constants.CERO) {
			if (StringUtils.isNotBlank(aa79bInformeFacturas.getIdioma())
					&& Constants.LANG_CASTELLANO.equals(aa79bInformeFacturas.getIdioma())) {
				columnas = columnas.replaceAll(CAMPO_FECHA_EMISION, CAMPO_FECHA_EMISION_INFORME_ES);
			} else {
				columnas = columnas.replaceAll(CAMPO_FECHA_EMISION, CAMPO_FECHA_EMISION_INFORME_EU);
			}
		}
		if (columnas.indexOf(CAMPO_ESTADO_FACTURA) >= Constants.CERO) {
			columnas = columnas.replaceAll(CAMPO_ESTADO_FACTURA, CAMPO_DESC_ESTADO_FACTURA);
		}
		aa79bInformeFacturas.setColumns(columnas);
	}

	private JQGridResponseDto<Aa79bSalidaConsultaFacturas> getDatosInformeFacturas(
			Aa79bInformeFacturas aa79bInformeFacturas) {

		JQGridRequestDto jqGridRequestDtoReport = new JQGridRequestDto(null, null, aa79bInformeFacturas.getSidx(),
				aa79bInformeFacturas.getSord());

		return this.facturasService.consultaFacturas(aa79bInformeFacturas.getFilterConsultaFacturas(),
				jqGridRequestDtoReport, false);
	}

	@WebMethod(operationName = "informeExpedientesFacturaInterpretacionXlsxWS")
	public FicheroWS informeExpedientesFacturaInterpretacionXlsxWS(
			Aa79bInformeExpedientesFactura aa79bInformeExpedientesFactura) {
		FacturacionWSImpl.LOGGER.debug("informeExpedientesFacturaInterpretacionXlsxWS - INICIO");

		this.modificarCamposInformeExpFactInter(aa79bInformeExpedientesFactura);
		Locale locale;
		if (StringUtils.isNotBlank(aa79bInformeExpedientesFactura.getIdioma())) {
			locale = new Locale(aa79bInformeExpedientesFactura.getIdioma());
		} else {
			locale = new Locale(Constants.LANG_EUSKERA);
		}

		JQGridResponseDto<Aa79bExpediente> respuesta = this.getDatosInformeExpFactInter(aa79bInformeExpedientesFactura);

		final String reportTitle = "title.expedientesFactura";
		final String label = this.appMessageSource.getMessage(reportTitle, new Object[] {}, locale);

		ExcelModel excelModel = this.excelReportService.getExcelModelJson(aa79bInformeExpedientesFactura.getCriterios(),
				aa79bInformeExpedientesFactura.getColumns(), respuesta.getRows(), locale, reportTitle, label);
		byte[] bytesInforme = null;
		FicheroWS ficheroBean = new FicheroWS();
		try {
			bytesInforme = this.excelReportService.generarExcel(FormatoInformeEnum.EXCEL.getTemplate(), excelModel);
		} catch (Exception e1) {
			FacturacionWSImpl.LOGGER.info("ERROR generarExcel, Error: " + e1);
			ficheroBean.setEstado(Constants.ESTADO_WS_ERROR);
			ficheroBean.setDescripcion("ERROR generarExcel");
		}

		ficheroBean.setNombre(this.appMessageSource.getMessage("titulo.informeExpedientesFactura", null, locale));
		ficheroBean.setExtension(FormatoInformeEnum.EXCEL.getExtension());
		ficheroBean.setContentType(FormatoInformeEnum.EXCEL.getContentType());
		ficheroBean.setTamano((long) bytesInforme.length);

		try {
			ficheroBean.setRutaPif(this.pidService.subidaPif(ficheroBean.getNombre() + ficheroBean.getExtension(),
					bytesInforme, this.appConfiguration.getProperty("rutaPifAa06a"), false));
		} catch (UnsupportedEncodingException e) {
			FacturacionWSImpl.LOGGER.info("UnsupportedEncodingException - subidaPif: " + e);
			ficheroBean.setEstado(Constants.ESTADO_WS_ERROR);
			ficheroBean.setDescripcion("ERROR subidaPif");

		} catch (Exception e) {
			FacturacionWSImpl.LOGGER.error("pruebapid: subidaFichero Error: ", e);
			ficheroBean.setEstado(Constants.ESTADO_WS_ERROR);
			ficheroBean.setDescripcion(this.appMessageSource.getMessage("mensaje.fichero.errorPIF", null, locale));
		}

		FacturacionWSImpl.LOGGER.debug(
				"WS_METHOD informeExpedientesFacturaInterpretacionXlsxWS - FIN: " + aa79bInformeExpedientesFactura);
		return ficheroBean;
	}

	@WebMethod(operationName = "informeExpedientesFacturaInterpretacionPdfWS")
	public FicheroWS informeExpedientesFacturaInterpretacionPdfWS(
			Aa79bInformeExpedientesFactura aa79bInformeExpedientesFactura) {
		FacturacionWSImpl.LOGGER.debug("informeExpedientesFacturaInterpretacionPdfWS - INICIO");

		this.modificarCamposInformeExpFactInter(aa79bInformeExpedientesFactura);
		Locale locale;
		if (StringUtils.isNotBlank(aa79bInformeExpedientesFactura.getIdioma())) {
			locale = new Locale(aa79bInformeExpedientesFactura.getIdioma());
		} else {
			locale = new Locale(Constants.LANG_EUSKERA);
		}

		JQGridResponseDto<Aa79bExpediente> respuesta = this.getDatosInformeExpFactInter(aa79bInformeExpedientesFactura);

		final String reportTitle = "title.expedientesFactura";
		final String label = this.appMessageSource.getMessage(reportTitle, new Object[] {}, locale);

		PdfModel pdfModel = this.pdfReportService.getPdfModelJson(aa79bInformeExpedientesFactura.getCriterios(),
				aa79bInformeExpedientesFactura.getColumns(), respuesta.getRows(), locale, label);
		byte[] bytesInforme = null;
		FicheroWS ficheroBean = new FicheroWS();
		ficheroBean.setEstado(Constants.ESTADO_WS_OK);
		try {
			bytesInforme = this.aa79bPdfReportService.generarPdf(FormatoInformeEnum.PDF.getTemplate(), pdfModel, locale);
		} catch (Exception e1) {
			FacturacionWSImpl.LOGGER.info("ERROR generarPdf, Error: " + e1);
			ficheroBean.setEstado(Constants.ESTADO_WS_ERROR);
			ficheroBean.setDescripcion("ERROR generarPdf");
		}

		ficheroBean.setNombre(this.appMessageSource.getMessage("titulo.informeExpedientesFactura", null, locale));
		ficheroBean.setExtension(FormatoInformeEnum.PDF.getExtension());
		ficheroBean.setContentType(FormatoInformeEnum.PDF.getContentType());
		ficheroBean.setTamano((long) bytesInforme.length);

		try {
			ficheroBean.setRutaPif(this.pidService.subidaPif(ficheroBean.getNombre() + ficheroBean.getExtension(),
					bytesInforme, this.appConfiguration.getProperty("rutaPifAa06a"), false));
		} catch (UnsupportedEncodingException e) {
			FacturacionWSImpl.LOGGER.info("UnsupportedEncodingException - subidaPif: " + e);
			ficheroBean.setEstado(Constants.ESTADO_WS_ERROR);
			ficheroBean.setDescripcion("ERROR subidaPif");

		} catch (Exception e) {
			FacturacionWSImpl.LOGGER.error("pruebapid: subidaFichero Error: ", e);
			ficheroBean.setEstado(Constants.ESTADO_WS_ERROR);
			ficheroBean.setDescripcion(this.appMessageSource.getMessage("mensaje.fichero.errorPIF", null, locale));
		}

		FacturacionWSImpl.LOGGER.debug(
				"WS_METHOD informeExpedientesFacturaInterpretacionPdfWS - FIN: " + aa79bInformeExpedientesFactura);
		return ficheroBean;

	}

	/**
	 * Muestra campos adicionales en informe si encuentra campos especificos en
	 * columnas seleccionadas
	 *
	 * @param aa79bInformeTareasProveedor
	 *            Aa79bInformeTareasProveedor
	 */
	private void modificarCamposInformeExpFactInter(Aa79bInformeExpedientesFactura aa79bInformeExpedientesFactura) {
		String columnas = aa79bInformeExpedientesFactura.getColumns();
		if (columnas.indexOf(CAMPO_FECHA_SOLICITUD) >= Constants.CERO) {
			if (StringUtils.isNotBlank(aa79bInformeExpedientesFactura.getIdioma())
					&& Constants.LANG_CASTELLANO.equals(aa79bInformeExpedientesFactura.getIdioma())) {
				columnas = columnas.replaceFirst(CAMPO_FECHA_SOLICITUD, CAMPO_FECHA_SOLICITUD_INFORME_ES);
			} else {
				columnas = columnas.replaceFirst(CAMPO_FECHA_SOLICITUD, CAMPO_FECHA_SOLICITUD_INFORME_EU);
			}
		}
		if (columnas.indexOf(CAMPO_TIPO_EXPEDIENTE) >= Constants.CERO) {
			if (StringUtils.isNotBlank(aa79bInformeExpedientesFactura.getIdioma())
					&& Constants.LANG_CASTELLANO.equals(aa79bInformeExpedientesFactura.getIdioma())) {
				columnas = columnas.replaceFirst(CAMPO_TIPO_EXPEDIENTE, CAMPO_TIPO_EXPEDIENTE_INFORME_ES);
			} else {
				columnas = columnas.replaceFirst(CAMPO_TIPO_EXPEDIENTE, CAMPO_TIPO_EXPEDIENTE_INFORME_EU);
			}
		}

		aa79bInformeExpedientesFactura.setColumns(columnas);

	}

	private JQGridResponseDto<Aa79bExpediente> getDatosInformeExpFactInter(
			Aa79bInformeExpedientesFactura aa79bInformeExpedientesFactura) {

		JQGridRequestDto jqGridRequestDtoReport = new JQGridRequestDto(null, null,
				aa79bInformeExpedientesFactura.getSidx(), aa79bInformeExpedientesFactura.getSord());

		return this.facturasService.expedientesFacturaInterpretacion(aa79bInformeExpedientesFactura.getIdFactura(),
				jqGridRequestDtoReport, false);
	}

	@WebMethod(operationName = "informeExpedientesFacturaTraduccionXlsxWS")
	public FicheroWS informeExpedientesFacturaTraduccionXlsxWS(
			Aa79bInformeExpedientesFactura aa79bInformeExpedientesFactura) {
		FacturacionWSImpl.LOGGER.debug("informeExpedientesFacturaTraduccionXlsxWS - INICIO");

		this.modificarCamposInformeExpFactTrad(aa79bInformeExpedientesFactura);
		Locale locale;
		if (StringUtils.isNotBlank(aa79bInformeExpedientesFactura.getIdioma())) {
			locale = new Locale(aa79bInformeExpedientesFactura.getIdioma());
		} else {
			locale = new Locale(Constants.LANG_EUSKERA);
		}

		JQGridResponseDto<Aa79bExpediente> respuesta = this.getDatosInformeExpFactTrad(aa79bInformeExpedientesFactura);

		final String reportTitle = "title.expedientesFactura";
		final String label = this.appMessageSource.getMessage(reportTitle, new Object[] {}, locale);

		ExcelModel excelModel = this.excelReportService.getExcelModelJson(aa79bInformeExpedientesFactura.getCriterios(),
				aa79bInformeExpedientesFactura.getColumns(), respuesta.getRows(), locale, reportTitle, label);
		byte[] bytesInforme = null;
		FicheroWS ficheroBean = new FicheroWS();
		try {
			bytesInforme = this.excelReportService.generarExcel(FormatoInformeEnum.EXCEL.getTemplate(), excelModel);
		} catch (Exception e1) {
			FacturacionWSImpl.LOGGER.info("ERROR generarExcel, Error: " + e1);
			ficheroBean.setEstado(Constants.ESTADO_WS_ERROR);
			ficheroBean.setDescripcion("ERROR generarExcel");
		}

		ficheroBean.setNombre(this.appMessageSource.getMessage("titulo.informeExpedientesFactura", null, locale));
		ficheroBean.setExtension(FormatoInformeEnum.EXCEL.getExtension());
		ficheroBean.setContentType(FormatoInformeEnum.EXCEL.getContentType());
		ficheroBean.setTamano((long) bytesInforme.length);

		try {
			ficheroBean.setRutaPif(this.pidService.subidaPif(ficheroBean.getNombre() + ficheroBean.getExtension(),
					bytesInforme, this.appConfiguration.getProperty("rutaPifAa06a"), false));
		} catch (UnsupportedEncodingException e) {
			FacturacionWSImpl.LOGGER.info("UnsupportedEncodingException - subidaPif: " + e);
			ficheroBean.setEstado(Constants.ESTADO_WS_ERROR);
			ficheroBean.setDescripcion("ERROR subidaPif");

		} catch (Exception e) {
			FacturacionWSImpl.LOGGER.error("pruebapid: subidaFichero Error: ", e);
			ficheroBean.setEstado(Constants.ESTADO_WS_ERROR);
			ficheroBean.setDescripcion(this.appMessageSource.getMessage("mensaje.fichero.errorPIF", null, locale));
		}

		FacturacionWSImpl.LOGGER
				.debug("WS_METHOD informeExpedientesFacturaTraduccionXlsxWS - FIN: " + aa79bInformeExpedientesFactura);
		return ficheroBean;
	}

	@WebMethod(operationName = "informeExpedientesFacturaTraduccionPdfWS")
	public FicheroWS informeExpedientesFacturaTraduccionPdfWS(
			Aa79bInformeExpedientesFactura aa79bInformeExpedientesFactura) {
		FacturacionWSImpl.LOGGER.debug("informeExpedientesFacturaTraduccionPdfWS - INICIO");

		this.modificarCamposInformeExpFactTrad(aa79bInformeExpedientesFactura);
		Locale locale;
		if (StringUtils.isNotBlank(aa79bInformeExpedientesFactura.getIdioma())) {
			locale = new Locale(aa79bInformeExpedientesFactura.getIdioma());
		} else {
			locale = new Locale(Constants.LANG_EUSKERA);
		}

		JQGridResponseDto<Aa79bExpediente> respuesta = this.getDatosInformeExpFactTrad(aa79bInformeExpedientesFactura);

		final String reportTitle = "title.expedientesFactura";
		final String label = this.appMessageSource.getMessage(reportTitle, new Object[] {}, locale);

		PdfModel pdfModel = this.pdfReportService.getPdfModelJson(aa79bInformeExpedientesFactura.getCriterios(),
				aa79bInformeExpedientesFactura.getColumns(), respuesta.getRows(), locale, label);
		byte[] bytesInforme = null;
		FicheroWS ficheroBean = new FicheroWS();
		ficheroBean.setEstado(Constants.ESTADO_WS_OK);
		try {
			bytesInforme = this.aa79bPdfReportService.generarPdf(FormatoInformeEnum.PDF.getTemplate(), pdfModel, locale);
		} catch (Exception e1) {
			FacturacionWSImpl.LOGGER.info("ERROR generarPdf, Error: " + e1);
			ficheroBean.setEstado(Constants.ESTADO_WS_ERROR);
			ficheroBean.setDescripcion("ERROR generarPdf");
		}

		ficheroBean.setNombre(this.appMessageSource.getMessage("titulo.informeExpedientesFactura", null, locale));
		ficheroBean.setExtension(FormatoInformeEnum.PDF.getExtension());
		ficheroBean.setContentType(FormatoInformeEnum.PDF.getContentType());
		ficheroBean.setTamano((long) bytesInforme.length);

		try {
			ficheroBean.setRutaPif(this.pidService.subidaPif(ficheroBean.getNombre() + ficheroBean.getExtension(),
					bytesInforme, this.appConfiguration.getProperty("rutaPifAa06a"), false));
		} catch (UnsupportedEncodingException e) {
			FacturacionWSImpl.LOGGER.info("UnsupportedEncodingException - subidaPif: " + e);
			ficheroBean.setEstado(Constants.ESTADO_WS_ERROR);
			ficheroBean.setDescripcion("ERROR subidaPif");

		} catch (Exception e) {
			FacturacionWSImpl.LOGGER.error("pruebapid: subidaFichero Error: ", e);
			ficheroBean.setEstado(Constants.ESTADO_WS_ERROR);
			ficheroBean.setDescripcion(this.appMessageSource.getMessage("mensaje.fichero.errorPIF", null, locale));
		}

		FacturacionWSImpl.LOGGER
				.debug("WS_METHOD informeExpedientesFacturaTraduccionPdfWS - FIN: " + aa79bInformeExpedientesFactura);
		return ficheroBean;

	}

	/**
	 * Muestra campos adicionales en informe si encuentra campos especificos en
	 * columnas seleccionadas
	 *
	 * @param aa79bInformeTareasProveedor
	 *            Aa79bInformeTareasProveedor
	 */
	private void modificarCamposInformeExpFactTrad(Aa79bInformeExpedientesFactura aa79bInformeExpedientesFactura) {
		String columnas = aa79bInformeExpedientesFactura.getColumns();
		if (columnas.indexOf(CAMPO_NUM_TOTAL_PAL_FACTURAR) >= Constants.CERO) {
			columnas = columnas.replaceAll(GeneralUtils.getRegexColumna(CAMPO_NUM_TOTAL_PAL_FACTURAR),
					CAMPOS_NUM_TOTAL_PAL_FACTURAR_INFORME);
		}
		if (columnas.indexOf(CAMPO_TIPO_EXPEDIENTE) >= Constants.CERO) {
			if (StringUtils.isNotBlank(aa79bInformeExpedientesFactura.getIdioma())
					&& Constants.LANG_CASTELLANO.equals(aa79bInformeExpedientesFactura.getIdioma())) {
				columnas = columnas.replaceAll(CAMPO_TIPO_EXPEDIENTE, CAMPO_TIPO_EXPEDIENTE_INFORME_ES);
			} else {
				columnas = columnas.replaceAll(CAMPO_TIPO_EXPEDIENTE, CAMPO_TIPO_EXPEDIENTE_INFORME_EU);
			}
		}

		aa79bInformeExpedientesFactura.setColumns(columnas);

	}

	private JQGridResponseDto<Aa79bExpediente> getDatosInformeExpFactTrad(
			Aa79bInformeExpedientesFactura aa79bInformeExpedientesFactura) {

		JQGridRequestDto jqGridRequestDtoReport = new JQGridRequestDto(null, null,
				aa79bInformeExpedientesFactura.getSidx(), aa79bInformeExpedientesFactura.getSord());

		return this.facturasService.expedientesFacturaTraduccion(aa79bInformeExpedientesFactura.getIdFactura(),
				jqGridRequestDtoReport, false);
	}

	private DatosPagoProveedores cargarObjetoParaGestionAlbaran(Aa79bTarea aa79bTarea, Aa79bEntradaTarea bean) {
		Tareas idTarea = new Tareas();
		idTarea.setIdTarea(aa79bTarea.getIdTarea());
		Tareas tarea = this.albaranService.obtenerIdTareaPagoProveedorRel(idTarea);

		DatosPagoProveedores datosPagoProveedores = new DatosPagoProveedores();
		datosPagoProveedores.setIdTarea(tarea.getIdTarea());
		datosPagoProveedores.setAnyo(tarea.getAnyo());
		datosPagoProveedores.setNumExpediente(tarea.getNumExp());
		datosPagoProveedores.setIdTarea(tarea.getIdTarea());
		Albaran albaran = new Albaran();
		albaran.setIdAlbaran(bean.getDatosPagoProveedores().getAlbaran().getIdAlbaran());
		albaran.setIdLote(bean.getDatosPagoProveedores().getAlbaran().getIdLote());
		datosPagoProveedores.setAlbaran(albaran);
		return datosPagoProveedores;
	}

}
