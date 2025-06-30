package com.ejie.aa79b.control;

import java.math.BigDecimal;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.ConsultaFacturacionExpediente;
import com.ejie.aa79b.model.DatosConfeccionarFactura;
import com.ejie.aa79b.model.DatosContacto;
import com.ejie.aa79b.model.DatosFacturacionExpediente;
import com.ejie.aa79b.model.DatosFacturacionExpedienteInterpretacion;
import com.ejie.aa79b.model.Direccion;
import com.ejie.aa79b.model.Entidad;
import com.ejie.aa79b.model.ExpedienteFacturacion;
import com.ejie.aa79b.model.Factura;
import com.ejie.aa79b.model.FacturasExpediente;
import com.ejie.aa79b.model.IvaPrecios;
import com.ejie.aa79b.model.RupTableMultiselectionModel;
import com.ejie.aa79b.model.Solicitante;
import com.ejie.aa79b.model.enums.TipoExpedienteGrupoEnum;
import com.ejie.aa79b.model.factura.CrearFactura;
import com.ejie.aa79b.model.factura.Iva;
import com.ejie.aa79b.model.factura.Tabla;
import com.ejie.aa79b.model.factura.Tercero;
import com.ejie.aa79b.service.DatosContactoService;
import com.ejie.aa79b.service.DireccionNoraService;
import com.ejie.aa79b.service.EmisionFacturasService;
import com.ejie.aa79b.service.EntidadService;
import com.ejie.aa79b.service.ExpedienteFacturacionService;
import com.ejie.aa79b.service.FacturaService;
import com.ejie.aa79b.service.IvaPreciosService;
import com.ejie.aa79b.utils.GeneralUtils;
import com.ejie.aa79b.webservices.EventosService;
import com.ejie.x38.control.bind.annotation.RequestJsonBody;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;

import p12f.exe.pasarelapagos.paymentrequest.PeticionPago;

@Controller
@RequestMapping(value = "/tramitacionexpedientes/facturacionypagos/emisiondefacturas")
public class FacturacionExpedientesController {

	private static final Logger LOGGER = LoggerFactory.getLogger(FacturacionExpedientesController.class);

	private static final String NOMBRE_TABLA = DBConstants.AA79BA4T00;
	private static final String CAMPO_TABLA = DBConstants.ID_LIQUIDACION_0A4;
	private static final String CAMPO_TABLA_PK = DBConstants.ID_FACTURA_0A4;
	private static final String CODIGO_CONCEPTO = String.valueOf(Constants.CODIGO_CONCEPTO);
	private static final String CODIGO_CONCEPTO_CON_IVA = String.valueOf(Constants.CODIGO_CONCEPTO_CON_IVA);
	private static final String TIPO_PARTIDA_COBRO = Constants.TIPO_PARTIDA_COBRO;

	@Autowired
	private EmisionFacturasService emisionFacturasService;
	@Autowired
	private EntidadService entidadService;
	@Autowired
	private DireccionNoraService direccionNoraService;
	@Autowired
	private ExpedienteFacturacionService expedienteFacturacionService;
	@Autowired()
	private FacturaService facturaService;
	@Autowired()
	private EventosService eventosService;
	@Autowired()
	private IvaPreciosService ivaPreciosService;
	@Autowired
	private DatosContactoService datosContactoService;

	/**
	 *
	 * @return String
	 */
	@RequestMapping(value = "/maint", method = RequestMethod.GET)
	public String getPestanasPlanificacion(Model model) {
		FacturacionExpedientesController.LOGGER.info("[GET - View] : getConsultaFacturacionExpedientes");
		return "consultafacturacionexpedientes";
	}


	@RequestMapping(value = "/entidadesSolicitantes", method = RequestMethod.GET)
	@ResponseBody()
	public List<Entidad> getConsultaFacturacionEntidadesSolicitantes(
			@RequestParam(value = "tipo", required = false) String tipoEntidad) {
		FacturacionExpedientesController.LOGGER.info("[GET - getConsultaFacturacionEntidadesSolicitantes] : getConsultaFacturacionEntidadesSolicitantes Obtener Entidad por filtro");
		final Entidad entidad = new Entidad();
		entidad.setTipo(tipoEntidad);
		return this.emisionFacturasService.getEntidadesSolicitantes(entidad);
	}

	@RequestMapping(value = "/contactosSolicitantes", method = RequestMethod.GET)
	@ResponseBody()
	public List<Solicitante> consultaFacturacionContactosSolicitantes(@RequestParam(value = "tipo", required = false) String tipo,
			@RequestParam(value = "codigo", required = false) Integer codigo) {
		FacturacionExpedientesController.LOGGER.info("[GET - consultaFacturacionContactosSolicitantes] : Solicitante combo");
		Solicitante contactoFilter = new Solicitante();
		Entidad entidad = new Entidad();
		entidad.setTipo(tipo);
		entidad.setCodigo(codigo);
		contactoFilter.setEntidad(entidad);
		return this.emisionFacturasService.getContactosSolicitantes(contactoFilter);
	}

	@RequestMapping(value = "/entidadesFacturar", method = RequestMethod.GET)
	@ResponseBody()
	public List<Entidad> getConsultaFacturacionEntidadesAFacturar(@RequestParam(value = "tipo", required = false) String tipoEntidad) {
		FacturacionExpedientesController.LOGGER.info("[GET - getConsultaFacturacionEntidadesAFacturar] : Obtener Entidad por filtro");
		final Entidad entidad = new Entidad();
		entidad.setTipo(tipoEntidad);
		entidad.setFacturable(Constants.SI);
		return this.emisionFacturasService.getEntidadesFacturar(entidad);
	}

	@RequestMapping(value = "/contactosFacturar", method = RequestMethod.GET)
	@ResponseBody()
	public List<Solicitante> consultaFacturacionContactosAFacturar(@RequestParam(value = "tipo", required = false) String tipo,
			@RequestParam(value = "codigo", required = false) Integer codigo) {
		FacturacionExpedientesController.LOGGER.info("[GET - consultaFacturacionContactosAFacturar] : Solicitante combo");
		Solicitante contactoFilter = new Solicitante();
		Entidad entidad = new Entidad();
		entidad.setTipo(tipo);
		entidad.setCodigo(codigo);
		entidad.setFacturable(Constants.SI);
		contactoFilter.setEntidad(entidad);
		return this.emisionFacturasService.getContactosFacturar(contactoFilter);
	}


	/**
	 *
	 * @return String
	 */
	@RequestMapping(value = "/confeccionarfactura/maint", method = RequestMethod.GET)
	public String getPestanaConfeccionarFactura() {
		FacturacionExpedientesController.LOGGER.info("[GET - View] : getPestanaConfeccionarFactura");
		return "confeccionarfactura";
	}

	/**
	 *
	 * @return String
	 */
	@RequestMapping(value = "/borradorfactura/maint", method = RequestMethod.GET)
	public String getBorradorFactura() {
		FacturacionExpedientesController.LOGGER.info("[GET - View] : getBorradorFactura");
		return "borradorfactura";
	}

	/**
	 *
	 * @return String
	 */
	@RequestMapping(value = "/calculoexpedientefacturacion/maint", method = RequestMethod.GET)
	public String getCalculoexpedientefacturacion() {
		FacturacionExpedientesController.LOGGER.info("[GET - View] : getCalculoexpedientefacturacion");
		return "calculoexpedientefacturacion";
	}

	/**
	 * Lista de objetos correspondientes a la busqueda realizada.
	 *
	 * @return List<Expediente>
	 */
	@RequestMapping(value = "/facturacionExpedientes/filter", method = RequestMethod.POST)
	public @ResponseBody JQGridResponseDto<ConsultaFacturacionExpediente> getConsultaFacturacionExpediente(
			@RequestJsonBody(param = "filter") ConsultaFacturacionExpediente filter,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto) {
		FacturacionExpedientesController.LOGGER.info("[POST - filter] : getConsultaFacturacionExpediente");
		return this.emisionFacturasService.filter(filter, jqGridRequestDto, false);
	}

	/**
	 *
	 * @param tipo
	 *            String
	 * @param codigo
	 *            Integer
	 * @param dni
	 *            String
	 * @return DatosConfeccionarFactura
	 */
	@RequestMapping(value = "/obtenerDatosConfeccionarFactura/{tipo}/{codigo}/{dni}", method = RequestMethod.GET)
	public @ResponseBody() DatosConfeccionarFactura obtenerDatosConfeccionarFactura(@PathVariable() String tipo,
			@PathVariable() Integer codigo, @PathVariable() String dni) {
		FacturacionExpedientesController.LOGGER.info("[GET - ] : obtenerDatosConfeccionarFactura");
		DatosConfeccionarFactura datosConfeccionarFactura = new DatosConfeccionarFactura();
		Entidad entidadDatos = new Entidad();
		entidadDatos.setCodigo(codigo);
		entidadDatos.setTipo(tipo);
		datosConfeccionarFactura.setDatosEntidad(entidadDatos);
		// datos de la entidad
		entidadDatos = this.entidadService.obtenerDatosConfeccionarFactura(entidadDatos);
		datosConfeccionarFactura.setDatosEntidad(entidadDatos);
		Direccion direccionFacturacContacto = new Direccion();
		// datos de facturacion del contacto al que se va a facturar (Si lo
		// hubiera)
		DatosContacto datosContacto = new DatosContacto();
		datosContacto.setDni031(dni);
		datosContacto = this.datosContactoService.findDatosContacto(datosContacto);
		datosConfeccionarFactura.setDatosContacto(datosContacto);

		direccionFacturacContacto = this.direccionNoraService.obtenerDireccionConDni(dni);
		datosConfeccionarFactura.setDirFacturacContacto(direccionFacturacContacto);
		datosConfeccionarFactura.setPorcentajeIva(obtenerTipoIvaOrdenPrecios());
		return datosConfeccionarFactura;
	}


	/**
	 * @return
	 */
	private Long obtenerTipoIvaOrdenPrecios() {
		IvaPrecios rstIvaPrecios = this.ivaPreciosService.findIvaOrdePreciosPublicos();
		Long porcentajeIva = 0L;
		if (rstIvaPrecios != null){
			porcentajeIva = rstIvaPrecios.getPorIva();
		}
		return porcentajeIva;
	}

	/**
	 * @return
	 */
	public Long obtenerTipoIva(BigDecimal importeIva) {
		Long porcentajeIva = 0L;
		if (importeIva.compareTo(Constants.MAGIC_NUMBER_0) != 0){
			porcentajeIva = obtenerTipoIvaOrdenPrecios();
		}
		return porcentajeIva;
	}

	/**
	 *
	 *
	 * @return List<Expediente> Lista de objetos correspondientes a la busqueda
	 *         realizada.
	 */
	@RequestMapping(value = "/confeccionarFactura/filter", method = RequestMethod.POST)
	public @ResponseBody JQGridResponseDto<ExpedienteFacturacion> getConsultaConfeccionarFactura(
			@RequestJsonBody(param = "filter") ExpedienteFacturacion filterContactoFacturacionExpediente,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto) {
		FacturacionExpedientesController.LOGGER.info("[POST - filter] : getConsultaConfeccionarFactura");
		return this.expedienteFacturacionService
				.confeccionarFacturaExpedientesFilter(filterContactoFacturacionExpediente, jqGridRequestDto, false);
	}

	/**
	 *
	 *
	 * @return DatosFacturacionExpediente obtiene la suma de los importes de los expedientes seleccionados
	 */
	@RequestMapping(value = "/obtenerImportesExpedientes/{tipoExpediente}", method = RequestMethod.POST)
	public @ResponseBody DatosFacturacionExpediente obtenerImportesExpedientes(
			@RequestBody RupTableMultiselectionModel rupTableMultiselectionModel, @PathVariable String tipoExpediente) {
		FacturacionExpedientesController.LOGGER.info("[POST] : obtenerImportesExpedientes");
		return this.expedienteFacturacionService.obtenerImportesExpedientes(rupTableMultiselectionModel,tipoExpediente);
	}

	/**
	 *
	 *
	 * @return DatosFacturacionExpediente obtiene la suma de los importes de los expedientes seleccionados
	 */
	@RequestMapping(value = "/obtenerExpedientesSeleccionados/{tipoExpediente}", method = RequestMethod.POST)
	public @ResponseBody List<DatosFacturacionExpediente> obtenerExpedientesSeleccionados(
			@RequestBody RupTableMultiselectionModel rupTableMultiselectionModel, @PathVariable String tipoExpediente) {
		FacturacionExpedientesController.LOGGER.info("[POST] : obtenerImportesExpedientes");
		return this.expedienteFacturacionService.obtenerExpedientesSeleccionados(rupTableMultiselectionModel,tipoExpediente);
	}



	/**
	 *
	 *
	 * @return List<Expediente> Lista de objetos correspondientes a la busqueda
	 *         realizada.
	 */
	@RequestMapping(value = "/borradorFacturaTabla/{listaExpedientes}/filter", method = RequestMethod.POST)
	public @ResponseBody JQGridResponseDto<ExpedienteFacturacion> getBorradorFacturaTabla(
			@RequestJsonBody(param = "filter") ExpedienteFacturacion filterContactoFacturacionExpediente,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto, @PathVariable String listaExpedientes) {
		FacturacionExpedientesController.LOGGER.info("[POST - filter] : getBorradorFacturaTabla");
		try {
			return this.expedienteFacturacionService.borradorFacturaTablaFilter(filterContactoFacturacionExpediente,
					jqGridRequestDto, listaExpedientes, false);
		} catch (Exception e) {
			FacturacionExpedientesController.LOGGER.info("[error - " + e);
		}
		return new JQGridResponseDto<ExpedienteFacturacion>();
	}

	/**
	 *
	 * @param tipo
	 *            String
	 * @param codigo
	 *            Integer
	 * @param dni
	 *            String
	 * @return DatosConfeccionarFactura
	 */
	@RequestMapping(value = "/realizarCalculoInterpretacion/{anyo}/{numExp}", method = RequestMethod.POST)
	public @ResponseBody void realizarCalculoInterpretacion(@PathVariable() Long anyo, @PathVariable() Integer numExp) {
		FacturacionExpedientesController.LOGGER.info("[GET - ] : realizarCalculoInterpretacion");
		ExpedienteFacturacion expedienteFacturacion = new ExpedienteFacturacion();
		expedienteFacturacion.setAnyo(anyo);
		expedienteFacturacion.setNumExp(numExp);
		this.expedienteFacturacionService.realizarCalculoInterpretacion(expedienteFacturacion);
	}

	/**
	 *
	 * @param tipo
	 *            String
	 * @param codigo
	 *            Integer
	 * @param dni
	 *            String
	 * @return ExpedienteFacturacion
	 */
	@RequestMapping(value = "/obtenerDatosCalculoExpedienteFacturacion/{anyo}/{numExp}/{tipoExpediente}", method = RequestMethod.GET)
	public @ResponseBody ExpedienteFacturacion obtenerDatosCalculoExpedienteFacturacion(@PathVariable() Long anyo,
			@PathVariable() Integer numExp, @PathVariable() Integer tipoExpediente) {
		FacturacionExpedientesController.LOGGER.info("[GET - ] : obtenerDatosCalculoExpedienteFacturacion");
		ExpedienteFacturacion expedienteFacturacionFilter = new ExpedienteFacturacion();
		expedienteFacturacionFilter.setAnyo(anyo);
		expedienteFacturacionFilter.setNumExp(numExp);
		if (TipoExpedienteGrupoEnum.TRADUCCION_REVISION.getValue() == tipoExpediente) {
			expedienteFacturacionFilter.setIdTipoExpediente(TipoExpedienteGrupoEnum.TRADUCCION_REVISION.getCode());
		} else {
			expedienteFacturacionFilter.setIdTipoExpediente(TipoExpedienteGrupoEnum.INTERPRETACION.getCode());
		}

		return this.expedienteFacturacionService.obtenerDatosCalculoExpedienteFacturacion(expedienteFacturacionFilter,
				true);
	}

	/**
	 *
	 * @param tipo
	 *            String
	 * @param codigo
	 *            Integer
	 * @param dni
	 *            String
	 * @return ExpedienteFacturacion
	 */
	@RequestMapping(value = "/obtenerDatosCalculoExpFactInter/{anyo}/{numExp}", method = RequestMethod.GET)
	public @ResponseBody ExpedienteFacturacion obtenerDatosCalculoExpFactInter(@PathVariable() Long anyo,
			@PathVariable() Integer numExp) {
		FacturacionExpedientesController.LOGGER.info("[GET] : obtenerDatosCalculoExpFactInter");
		ExpedienteFacturacion expedienteFacturacionFilter = new ExpedienteFacturacion();
		expedienteFacturacionFilter.setAnyo(anyo);
		expedienteFacturacionFilter.setNumExp(numExp);
		expedienteFacturacionFilter.setIdTipoExpediente(TipoExpedienteGrupoEnum.INTERPRETACION.getCode());

		return this.expedienteFacturacionService.obtenerDatosCalculoExpedienteFacturacion(expedienteFacturacionFilter,
				false);
	}

	/**
	 * Comprueba si el expediente tiene datos de facturación asociados
	 *
	 * @param anyo
	 *            Long
	 * @param numExp
	 *            Integer
	 * @return long, Devuelve (0/n): 0 - Si el expediente no contiene datos de
	 *         facturación asociados; n - En caso contrario
	 */
	@RequestMapping(value = "/comprobarDatosFactExpInter/{anyo}/{numExp}", method = RequestMethod.GET)
	public @ResponseBody() long comprobarDatosFactExpInter(@PathVariable() Long anyo, @PathVariable() Integer numExp) {
		FacturacionExpedientesController.LOGGER.info("[GET] : comprobarDatosFactExpInter");
		return this.expedienteFacturacionService.comprobarDatosFactExpInter(anyo, numExp);
	}

	/**
	 *
	 *
	 * @return List<Expediente> Lista de objetos correspondientes a la busqueda
	 *         realizada.
	 */
	@RequestMapping(value = "/calculoExpedienteFacturacionTabla/{anyo}/{numExp}/{tipoExpediente}/filter", method = RequestMethod.POST)
	public @ResponseBody JQGridResponseDto<ExpedienteFacturacion> getBorradorFacturaTabla(
			@RequestJsonBody(param = "filter") ExpedienteFacturacion filterContactoFacturacionExpediente,
			@PathVariable Long anyo, @PathVariable Integer numExp, @PathVariable Integer tipoExpediente,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto) {
		FacturacionExpedientesController.LOGGER.info("[POST - filter] : calculoExpedienteFacturacionTabla");
		ExpedienteFacturacion expedienteFacturacionFilter = new ExpedienteFacturacion();
		expedienteFacturacionFilter.setAnyo(anyo);
		expedienteFacturacionFilter.setNumExp(numExp);
		if (TipoExpedienteGrupoEnum.TRADUCCION_REVISION.getValue() == tipoExpediente) {
			expedienteFacturacionFilter.setIdTipoExpediente(TipoExpedienteGrupoEnum.TRADUCCION_REVISION.getCode());
		} else {
			expedienteFacturacionFilter.setIdTipoExpediente(TipoExpedienteGrupoEnum.INTERPRETACION.getCode());
		}
		try {
			return this.expedienteFacturacionService
					.calculoExpedienteFacturacionTablaFilter(expedienteFacturacionFilter, jqGridRequestDto, false);
		} catch (Exception e) {
			FacturacionExpedientesController.LOGGER.info("[error - " + e);
		}
		return new JQGridResponseDto<ExpedienteFacturacion>();
	}

	/**
	 * Generación de la factura en W05
	 *
	 * @param factura
	 *            Factura @param expedientesSelect String @return int, Devuelve
	 *            (0/1): 0 - Si existe la partida presupuestaria; 1 - en caso
	 *            contrario. @throws IOException @throws
	 *            JsonMappingException @throws
	 * @throws Exception
	 */
	@RequestMapping(value = "/generarFactura", method = RequestMethod.POST)
	public @ResponseBody() int generarFactura(@RequestJsonBody(required = false, param = "factura") Factura factura,
			@RequestJsonBody(required = false, param = "listFacturasExp") String listFacturasExpStr,
			@RequestJsonBody(required = false, param = "datosFacturacion") DatosFacturacionExpedienteInterpretacion datosFacturacion)
			throws Exception {
		FacturacionExpedientesController.LOGGER.info("[POST] : generarFactura");
		int rst = 0;

		Entidad entidad = new Entidad();
		entidad.setCodigo(factura.getIdEntidadAsoc());
		entidad.setTipo(factura.getTipoEntidadAsoc());
		entidad = this.entidadService.obtenerDatosConfeccionarFactura(entidad);

		String codigoConcepto = CODIGO_CONCEPTO_CON_IVA;
		String sinIVA = Constants.SI;
		String fueraDeUE = Constants.NO;
		if (Constants.SI.equals(entidad.getIva())){
			// si la entidad tiene iva, marcamos el sinIVA a no
			sinIVA = Constants.NO;
		}
		String tipoPartida = TIPO_PARTIDA_COBRO;//tipo partida = cobro

		Long conceptoCount = this.facturaService.comprobarPartidaPresupuestaria(codigoConcepto, tipoPartida);

		if (conceptoCount > 0) {
			Long idFactura = this.facturaService.getIdFacturaSgt();
			factura.setIdFactura(idFactura);

			ObjectMapper mapper = new ObjectMapper();
			List<FacturasExpediente> listFacturasExp = mapper.readValue(listFacturasExpStr,
					new TypeReference<List<FacturasExpediente>>() {
					});

			CrearFactura crearFactura = this.asignarDatosCrearFactura(entidad, factura, listFacturasExp.size(),
					datosFacturacion, codigoConcepto, sinIVA, fueraDeUE);

			// Rellenar plantilla
			crearFactura = this.facturaService.rellenarPlantilla(factura.getTipoExpFactura(), listFacturasExp,
					crearFactura, factura);


			if ("-1".equals(factura.getDniContacto())){
				factura.setDniContacto(null);
			}
			// Registra los datos asociados a la generación de la factura en
			// las tablas A4 y A5
			this.facturaService.generarFactura(factura, listFacturasExp);

			// Crear la factura
			this.crearFactura(factura.getIdFactura(), crearFactura);
		} else {
			rst = 1;
		}

		return rst;
	}

	private CrearFactura asignarDatosCrearFactura(Entidad entidad, Factura factura, int numUnidades,
			DatosFacturacionExpedienteInterpretacion datosFacturacion, String codigoConcepto, String sinIVA, String fueraDeUE) {
		Long idFactura = factura.getIdFactura();
		CrearFactura crearFactura = new CrearFactura();

		Tercero tercero = this.facturaService.generarDatosTercero(entidad,
				factura.getDniContacto());
		crearFactura.setTercero(tercero);


		crearFactura.setIdFactura(String.valueOf(idFactura));
		crearFactura.setAplicacion(Constants.CONSTANTE_APLICACION_FACTURACION);

		crearFactura.setConcepto(codigoConcepto);
		crearFactura.setSinIVA(sinIVA);
		crearFactura.setFueraDeUE(fueraDeUE);

		crearFactura.setImporteBase(GeneralUtils.format2Decimals(datosFacturacion.getImporteBase()));
		crearFactura.setImporteTotal(GeneralUtils.format2Decimals(datosFacturacion.getImporteTotal()));
		crearFactura.setDevengo(PeticionPago.DEVENGO_EMISION);
		Iva iva = new Iva();
		iva.setImporteIva(GeneralUtils.format2Decimals(datosFacturacion.getImporteIva()));
		iva.setTipoIva(String.valueOf(obtenerTipoIva(datosFacturacion.getImporteIva())));
		crearFactura.setIva(iva);
		// Nº de expedientes a facturar
		crearFactura.setUnidades(String.valueOf(numUnidades));

		Tabla tabla = new Tabla();
		tabla.setNombreTabla(NOMBRE_TABLA);
		tabla.setCampoTabla(CAMPO_TABLA);
		tabla.setCampoTablaPk(CAMPO_TABLA_PK);
		crearFactura.setTabla(tabla);
		crearFactura.setTagFactura("N_FACT");

		return crearFactura;
	}

	public void crearFactura(Long idFactura, CrearFactura crearFactura) {
		this.eventosService.createInvoice(String.valueOf(idFactura), crearFactura);
	}

}
