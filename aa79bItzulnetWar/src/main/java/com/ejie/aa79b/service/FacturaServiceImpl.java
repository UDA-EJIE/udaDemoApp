package com.ejie.aa79b.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.dao.ConfigLibroRegistroDao;
import com.ejie.aa79b.dao.DatosContactoDao;
import com.ejie.aa79b.dao.DireccionNoraDao;
import com.ejie.aa79b.dao.ExpedienteFacturacionDao;
import com.ejie.aa79b.dao.FacturaDao;
import com.ejie.aa79b.dao.FacturasExpedienteDao;
import com.ejie.aa79b.dao.GenericoDao;
import com.ejie.aa79b.dao.PlantillasDao;
import com.ejie.aa79b.model.ConfigLibroRegistro;
import com.ejie.aa79b.model.DatosContacto;
import com.ejie.aa79b.model.Direccion;
import com.ejie.aa79b.model.Entidad;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.ExpedienteFacturacion;
import com.ejie.aa79b.model.Factura;
import com.ejie.aa79b.model.FacturasExpediente;
import com.ejie.aa79b.model.Gestor;
import com.ejie.aa79b.model.LibroRegistroModel;
import com.ejie.aa79b.model.Plantillas;
import com.ejie.aa79b.model.Solicitante;
import com.ejie.aa79b.model.enums.TipoExpedienteEnum;
import com.ejie.aa79b.model.enums.TipoExpedienteGrupoEnum;
import com.ejie.aa79b.model.enums.TipoPlantillaEnum;
import com.ejie.aa79b.model.factura.CrearFactura;
import com.ejie.aa79b.model.factura.Tercero;
import com.ejie.aa79b.utils.DateUtils;
import com.ejie.aa79b.utils.GeneralUtils;
import com.ejie.aa79b.utils.RTFUtils;
import com.ejie.aa79b.webservices.PIDService;
import com.ejie.aa79b.webservices.srp.RegistroPresencialWebService;
import com.ejie.x38.dto.JQGridRequestDto;

@Service(value = "facturaService")
public class FacturaServiceImpl extends GenericoServiceImpl<Factura> implements FacturaService {

	/**
	 * JOSE - PRUEBAS GENERACIÓN TABLA DINÁMICA
	 */

	/**
	 * StringBuilder initilization value
	 */
	public static final int STRING_BUILDER_INIT = 4096;

	private static final String COLNEXP = "COLNEXP";

	private static final String INICIO_COLUMNA_CABECERA = "\\b{\\clbrdrt\\brdrs\\brdrw10 \\clbrdrl\\brdrs\\brdrw10 \\clbrdrb\\brdrs\\brdrw10 \\clbrdrr\\brdrs\\brdrw10";
	private static final String FIN_COLUMNA_CABECERA = "}\\cell\\b0";
	private static final String INICIO_CELDA = "\\b0{";
	private static final String FIN_CELDA = "}\\cell";
	private static final String FIN_FILA = "\\row}";
	private static final String FIN_TABLA = "}";

	private static final String WIDTH_COLUMN_1 = "\\cellx3000\\clvertalc\\qc";
	private static final String WIDTH_COLUMN_2 = "\\cellx4000\\clvertalc\\qc";
	private static final String WIDTH_COLUMN_3 = "\\cellx5000\\clvertalc\\qc";
	private static final String WIDTH_COLUMN_4 = "\\cellx6600\\clvertalc\\qc";
	private static final String WIDTH_COLUMN_5 = "\\cellx7800\\clvertalc\\qc";
	private static final String WIDTH_COLUMN_6 = "\\cellx9000\\clvertalc\\qc";
	private static final String WIDTH_COLUMN_7 = "\\cellx10200\\clvertalc\\qc";
	private static final String WIDTH_COLUMN_8 = "\\cellx12300\\clvertalc\\qc";
	private static final String WIDTH_COLUMN_9 = "\\cellx13300\\clvertalc\\qc";
	private static final String WIDTH_COLUMN_10 = "\\cellx15876\\clvertalc\\qc";

	private static final String WIDTH_COLUMN_ANCHO_TOTAL_HORIZONTAL = "\\cellx15876\\clvertalc\\ql";

	private static final String WIDTH_COLUMN_1_INTER = "\\cellx3000\\clvertalc\\qc";
	private static final String WIDTH_COLUMN_2_INTER = "\\cellx4000\\clvertalc\\qc";
	private static final String WIDTH_COLUMN_3_INTER = "\\cellx5000\\clvertalc\\qc";
	private static final String WIDTH_COLUMN_4_INTER = "\\cellx6600\\clvertalc\\qc";
	private static final String WIDTH_COLUMN_5_INTER = "\\cellx7800\\clvertalc\\qc";
	private static final String WIDTH_COLUMN_6_INTER = "\\cellx9000\\clvertalc\\qc";

	private static final String WIDTH_COLUMN_ANCHO_TOTAL_VERTICAL = "\\cellx9000\\clvertalc\\ql";
	// fs es el tamaño de la fuente x 2 ??
	// NOTA IMPORTANTE: 1cm. de la plantilla de word equivale a \\cellx567
	// \\cellx15876 equivale a 28cm

	// SALTO DE PÁGINA
	// \\pard \\insrsid \\page \\par

	private static final String CABECERA = "{\\fs10\\trleftx\\trowd\\irow0\\irowband0\\ltrrow\\ts11\\trqc\\trgaph108\\trrh255\\trleft-108\\trkeep\\trbrdrt\\brdrs\\brdrw15 \\trbrdrl\\brdrs\\brdrw15 \\trbrdrb\\brdrs\\brdrw15 \\trbrdrr\\brdrs\\brdrw15 \\trbrdrh\\brdrs\\brdrw15 \\trbrdrv\\brdrs\\brdrw15\\clvertalc\\qc ";
	private static final String FILA = "{\\fs10\\trleftx20\\trowd\\irow0\\irowband0\\ltrrow\\ts11\\trqc\\trgaph108\\trrh255\\trleft-108\\trkeep\\trbrdrt\\brdrs\\brdrw15 \\trbrdrl\\brdrs\\brdrw15 \\trbrdrb\\brdrs\\brdrw15 \\trbrdrr\\brdrs\\brdrw15 \\trbrdrh\\brdrs\\brdrw15 \\trbrdrv\\brdrs\\brdrw15\\clvertalc\\qc ";
	private static final String LABEL_NUM_EXP = "label.numexpediente";
	private static final String LABEL_TOTAL = "label.total";
	private static final String BORDES_CELDA = "\\clbrdrt\\brdrs\\brdrw10 \\clbrdrl\\brdrs\\brdrw10 \\clbrdrb\\brdrs\\brdrw10 \\clbrdrr\\brdrs\\brdrw10";

	/**
	 * JOSE - PRUEBAS GENERACIÓN TABLA DINÁMICA ------------- FIN
	 */

	@Autowired()
	private FacturaDao facturaDao;

	@Autowired()
	private FacturasExpedienteDao facturasExpedienteDao;

	@Autowired()
	private DireccionNoraDao direccionNoraDao;

	@Autowired()
	private DatosContactoDao datosContactoDao;

	@Autowired()
	private PlantillasDao plantillasDao;

	@Autowired()
	private ExpedienteFacturacionDao expedienteFacturacionDao;

	@Autowired()
	private PIDService pidService;

	@Autowired()
	private Properties appConfiguration;

	@Autowired()
	private ReloadableResourceBundleMessageSource msg;

	@Autowired()
	private ConfigLibroRegistroDao configLibroRegistroDao;

	@Autowired()
	private RegistroPresencialWebService registroPresencialWebService;

	private static final Logger LOGGER = LoggerFactory.getLogger(FacturaServiceImpl.class);

	@Override
	protected GenericoDao<Factura> getDao() {
		return this.facturaDao;
	}

	@Override
	public Long getIdFacturaSgt() {
		return this.facturaDao.getIdFacturaSgt();
	}

	@Override
	public Long comprobarPartidaPresupuestaria(String concepto, String tipoPartida) {
		return this.facturaDao.comprobarPartidaPresupuestaria(concepto, tipoPartida);
	}

	@Override
	public void generarFactura(Factura factura, List<FacturasExpediente> listFacturasExp) {
		// Generamos nuevo registro en la tabla A4
		this.facturaDao.add(factura);
		this.facturasExpedienteDao.addExpedientes(listFacturasExp, factura.getIdFactura());
	}

	@Override
	public CrearFactura rellenarPlantilla(String tipoExpFactura, List<FacturasExpediente> listFacturasExp,
			CrearFactura crearFactura, Factura factura) throws Exception {

		Plantillas plantillas = new Plantillas();

		obtenerTipoPlantilla(tipoExpFactura, plantillas);

		Plantillas rstPlantillas = this.plantillasDao.find(plantillas);

		byte[] documento = this.pidService.getDocument(rstPlantillas.getOidFichero());

		Map<String, Object> datos = new HashMap<String, Object>();

		datos.put("ERAKUNDEA", crearFactura.getTercero().getRazonSocial());
		datos.put("IZENA", obtenerCentroOrganicoFacturacion(factura));
		datos.put("HELBIDEA", crearFactura.getTercero().getCalle());

		StringBuilder strDireccion = new StringBuilder();
		StringBuilder strDireccionEntidad = new StringBuilder();

		componerDirecciones(crearFactura, strDireccion, strDireccionEntidad);

		datos.put("HELBIDEA_2", strDireccion.toString());
		// Llamada al libro de registro de salida
		rellenarDatosLibroRegistro(factura, datos);
		datos.put("FEC_FACT_EUS", DateUtils.obtFechaFormateada(new Date()));

		datos.put("ENTIDAD_HELBIDEA", strDireccionEntidad.toString());
		datos.put("ENTIDAD_CIF", crearFactura.getTercero().getDniNif());
		datos.put("IMP_SIN_IVA", crearFactura.getImporteBase());
		datos.put("IMP_IVA", crearFactura.getIva().getImporteIva());
		datos.put("IMP_CON_IVA", crearFactura.getImporteTotal());

		// Listado de expedientes de
		final List<ExpedienteFacturacion> lstExpedientes = obtenerDatosExpedientesTabla(listFacturasExp, factura);

		// Rellenar plantilla
		if (TipoExpedienteEnum.INTERPRETACION.getValue().equals(tipoExpFactura)) {
			datos.put("IVA_PORCENTAJE", crearFactura.getIva().getTipoIva());
			// Listado de expedientes de interpretación
			datos.put(COLNEXP, this.generarListaExpInter(lstExpedientes, factura));
			// Rellenar tablas de expedientes con varios pagadores
			datos.put("TABLA", this.generarTablasInterVariasEntFacturacion(listFacturasExp, factura));

		} else {
			datos.put("FEC_RED_EUS", DateUtils.obtFechaFormateada(new Date()));
			// Modificación para generar la tabla dinámicamente
			datos.put(COLNEXP, this.generarListaExpTradRev(lstExpedientes, factura));

			// Anexo: Listado de expedientes de traducción / revisión
			datos.put("CAXN", this.generarListaAnexoExpTradRev(lstExpedientes, factura));

			// Rellenar tablas de expedientes con varios pagadores
			datos.put("TABLA", this.generarTablasTradRevVariasEntFacturacion(listFacturasExp, factura));
		}

		byte[] fichero = RTFUtils.getInstance().combinarDocumento(documento, datos);

		crearFactura.setPifFactura(this.pidService.subidaPif(
				this.msg.getMessage("label.factura", null, LocaleContextHolder.getLocale()) + "_"
						+ crearFactura.getIdFactura() + ".rtf",
				fichero, this.appConfiguration.getProperty("rutaPifFacturasW05u"), false));

		return crearFactura;
	}

	private void rellenarDatosLibroRegistro(Factura factura, Map<String, Object> datos) throws Exception {
		LibroRegistroModel libroRegistroModel = this.anotarLibroRegistroSalida(factura.getIdFactura());

		if (libroRegistroModel != null) {
			datos.put("NUM_REGISTRO", libroRegistroModel.getNumRegistro());
			datos.put("FEC_REGISTRO", DateUtils.obtFechaFormateada(libroRegistroModel.getFechaRegistro()));
		} else {
			datos.put("NUM_REGISTRO", "");
			datos.put("FEC_REGISTRO", "");
		}
	}

	private void componerDirecciones(CrearFactura crearFactura, StringBuilder strDireccion,
			StringBuilder strDireccionEntidad) {
		strDireccion.append(
				crearFactura.getTercero().getCodigoPostal() != null ? crearFactura.getTercero().getCodigoPostal() : "");

		strDireccionEntidad
				.append(crearFactura.getTercero().getCalle() != null ? crearFactura.getTercero().getCalle() : "");
		if (crearFactura.getTercero().getCodigoPostal() != null) {
			if (strDireccionEntidad.length() > 0) {
				strDireccionEntidad.append(" ");
			}
			strDireccionEntidad.append(crearFactura.getTercero().getCodigoPostal());
		}

		if (crearFactura.getTercero().getMunicipio() != null) {
			if (strDireccion.length() > 0) {
				strDireccion.append(" - ");
			}
			strDireccion.append(crearFactura.getTercero().getMunicipio());
			if (strDireccionEntidad.length() > 0) {
				strDireccionEntidad.append(" - ");
			}
			strDireccionEntidad.append(crearFactura.getTercero().getMunicipio());
		}
		if (crearFactura.getTercero().getTerritorio() != null) {
			if (strDireccion.length() > 0) {
				strDireccion.append(", ");
			}
			strDireccion.append(crearFactura.getTercero().getTerritorio());
			if (strDireccionEntidad.length() > 0) {
				strDireccionEntidad.append(", ");
			}
			strDireccionEntidad.append(crearFactura.getTercero().getTerritorio());
		}
	}

	private void obtenerTipoPlantilla(String tipoExpFactura, Plantillas plantillas) {
		// Rellenar plantilla
		if (TipoExpedienteEnum.INTERPRETACION.getValue().equals(tipoExpFactura)) {
			plantillas.setId0A7((long) TipoPlantillaEnum.FACTURA_INTERPRETACION.getValue());
		} else {
			plantillas.setId0A7((long) TipoPlantillaEnum.FACTURA_TRADUCCION.getValue());
		}
	}

	private String obtenerCentroOrganicoFacturacion(Factura factura) {
	     String centroOrganicoFacturacion = StringUtils.EMPTY;
	     if (factura.getDniContacto() != null) {
	          String centroOrganico = this.facturaDao.obtenerCentroOrganico(factura.getDniContacto());
	          if (centroOrganico != null && !centroOrganico.isEmpty()) {
	               centroOrganicoFacturacion = centroOrganico;
	          }
	     }
	     return centroOrganicoFacturacion;
	}

	/**
	 * @param expedienteFacturacion
	 * @return
	 */
	private String obtenerSolicitanteExp(ExpedienteFacturacion expedienteFacturacion) {
		return this.datosContactoDao.findSolicitanteExp(expedienteFacturacion);
	}

	public LibroRegistroModel anotarLibroRegistroSalida(Long idFactura) throws Exception {
		ConfigLibroRegistro configLibroRegistro = new ConfigLibroRegistro();
		configLibroRegistro.setId(Constants.ID_DATOS_BASICOS);

		ConfigLibroRegistro configLibroRegistroAux = this.configLibroRegistroDao.find(configLibroRegistro);

		LibroRegistroModel libroRegistroModel = null;

		if (configLibroRegistroAux != null && Constants.SI.equals(configLibroRegistroAux.getIndActivo())) {
			LibroRegistroModel libroRegistroEntrada = new LibroRegistroModel();
			Locale locale = new Locale(Constants.LANG_EUSKERA);
			StringBuilder strb = new StringBuilder();
			strb.append(this.msg.getMessage("menu.emisiondefacturas", null, locale)).append(Constants.ESPACIO)
					.append(idFactura);
			libroRegistroEntrada.setMatter(strb.toString());
			try {
				libroRegistroModel = this.registroPresencialWebService.altaRegistroSalida(libroRegistroEntrada);
			} catch (Exception e) {
				FacturaServiceImpl.LOGGER.info("Error al grabar en el libro de registro", e);
			}
		}

		return libroRegistroModel;
	}

	@Override()
	public Tercero generarDatosTercero(Entidad entidad, String dniContacto) {
		Tercero tercero = new Tercero();

		// Entidad de facturación

		asignarDatosEntidad(tercero, entidad);

		if (dniContacto != null && !"-1".equals(dniContacto)) {
			// Direccion de facturación del contacto al que se va a facturar (Si
			// lo hubiera)
			asignarDireccionContacto(dniContacto, tercero);
			asignarDatosContacto(dniContacto, tercero);
		} else if (entidad.getDireccion() != null) {
			tercero.setCalle(entidad.getDireccion().getTxtCalle());
			tercero.setCodigoPostal(entidad.getDireccion().getCodPostal());
			tercero.setMunicipio(entidad.getDireccion().getCodMunicipio());
			tercero.setTerritorio(entidad.getDireccion().getCodProvincia());
			tercero.setTelefono(StringUtils.EMPTY);
			tercero.setEmail(StringUtils.EMPTY);
		} else {
			tercero.setCalle(StringUtils.EMPTY);
			tercero.setCodigoPostal(StringUtils.EMPTY);
			tercero.setMunicipio(StringUtils.EMPTY);
			tercero.setTerritorio(StringUtils.EMPTY);
			tercero.setTelefono(StringUtils.EMPTY);
			tercero.setEmail(StringUtils.EMPTY);
		}
		tercero.setPais(StringUtils.EMPTY);

		return tercero;
	}

	/**
	 * @param dniContacto
	 * @param tercero
	 */
	private void asignarDatosContacto(String dniContacto, Tercero tercero) {
		DatosContacto datosContacto = obtenerDatosContacto(dniContacto);

		if (datosContacto != null) {
			tercero.setTelefono(StringUtils.EMPTY);
			tercero.setEmail(StringUtils.EMPTY);
		} else {
			tercero.setTelefono(StringUtils.EMPTY);
			tercero.setEmail(StringUtils.EMPTY);
		}
	}

	/**
	 * @param dniContacto
	 * @return
	 */
	private DatosContacto obtenerDatosContacto(String dniContacto) {
		DatosContacto contacto = new DatosContacto();
		contacto.setDni031(dniContacto);
		return this.datosContactoDao.findDatosContacto(contacto);
	}

	/**
	 * Obtiene el teléfono del contacto
	 *
	 * @param datosContacto DatosContacto
	 * @return String
	 */
	private String getTelefonoContacto(DatosContacto datosContacto) {
		String telefono = StringUtils.EMPTY;
		if (datosContacto.getTelmovil031() != null) {
			telefono = datosContacto.getTelmovil031();
		} else if (datosContacto.getTelfijo031() != null) {
			telefono = datosContacto.getTelfijo031();
		}
		return telefono;
	}

	/**
	 * @param dniContacto
	 * @param tercero
	 */
	private void asignarDireccionContacto(String dniContacto, Tercero tercero) {
		Direccion direccion = this.direccionNoraDao.obtenerDireccionConDni(dniContacto);

		if (direccion != null) {
			tercero.setCalle(direccion.getTxtCalle());
			tercero.setCodigoPostal(direccion.getCodPostal());
			tercero.setMunicipio(direccion.getCodMunicipio());
			tercero.setTerritorio(direccion.getCodProvincia());
		} else {
			tercero.setCalle(StringUtils.EMPTY);
			tercero.setCodigoPostal(StringUtils.EMPTY);
			tercero.setMunicipio(StringUtils.EMPTY);
			tercero.setTerritorio(StringUtils.EMPTY);
		}
	}

	/**
	 * @param tercero
	 * @param entidad
	 */
	private void asignarDatosEntidad(Tercero tercero, Entidad entidad) {
		if (entidad != null) {
			if (entidad.getCif() != null) {
				tercero.setDniNif(entidad.getCif());
			} else {
				tercero.setDniNif(StringUtils.EMPTY);
			}
			tercero.setRazonSocial(entidad.getDescEu());
		} else {
			tercero.setDniNif(StringUtils.EMPTY);
			tercero.setRazonSocial(StringUtils.EMPTY);
		}
		tercero.setTipoTercero(Constants.TIPO_CIF);
	}

	/**
	 *
	 * @param listFacturasExp
	 * @param factura
	 * @return
	 */
	private List<Map<String, String>> generarListaExpTradRev(List<ExpedienteFacturacion> lstExpedientes,
			Factura factura) {

		Map<String, String> datosFila;
		List<Map<String, String>> listaFila = new ArrayList<Map<String, String>>();

		for (ExpedienteFacturacion expedienteFacturacion : lstExpedientes) {
			datosFila = new HashMap<String, String>();
			datosFila.put(COLNEXP, expedienteFacturacion.getAnyoNumExpConcatenado());
			datosFila.put("COLSOLIC", this.obtenerSolicitanteExp(expedienteFacturacion));
			datosFila.put("COLFECHA", expedienteFacturacion.getFechaHoraAlta());
			datosFila.put("COLTIT", expedienteFacturacion.getTitulo());
			datosFila.put("COLTOTAL", format2DecimalsEuro(
					expedienteFacturacion.getExpedienteTradRev().getDatosFacturacionExpediente().getImporteTotal()));
			listaFila.add(datosFila);
		}

		return listaFila;
	}

	/**
	 * @param expedienteFacturacion
	 * @return String
	 */
	private String obtenerDescTipoExpediente(ExpedienteFacturacion expedienteFacturacion) {
		Locale locale = LocaleContextHolder.getLocale();
		String tipoExpediente;
		if (TipoExpedienteEnum.TRADUCCION.getValue().equals(expedienteFacturacion.getIdTipoExpediente())) {
			tipoExpediente = this.msg.getMessage(TipoExpedienteEnum.TRADUCCION.getLabel(), null, locale);
		} else if (TipoExpedienteEnum.REVISION.getValue().equals(expedienteFacturacion.getIdTipoExpediente())) {
			tipoExpediente = this.msg.getMessage(TipoExpedienteEnum.REVISION.getLabel(), null, locale);
		} else {
			tipoExpediente = this.msg.getMessage(TipoExpedienteEnum.INTERPRETACION.getLabel(), null, locale);
		}
		return tipoExpediente;
	}

	/**
	 *
	 * @param listFacturasExp
	 * @param factura
	 * @return
	 */
	private List<Map<String, String>> generarListaExpInter(List<ExpedienteFacturacion> lstExpedientes,
			Factura factura) {

		Map<String, String> datosFila;
		List<Map<String, String>> listaFila = new ArrayList<Map<String, String>>();
		final Locale locale = LocaleContextHolder.getLocale();

		for (ExpedienteFacturacion expedienteFacturacion : lstExpedientes) {
			datosFila = new HashMap<String, String>();
			datosFila.put("COLNEXP", expedienteFacturacion.getAnyoNumExpConcatenado()
					+ (Constants.CIEN != expedienteFacturacion.getPorFactura() ? " \\b\\cf6*\\cf0\\b0" : ""));
			datosFila.put("COLSOLIC", this.obtenerSolicitanteExp(expedienteFacturacion));
			datosFila.put("COLFECHA", expedienteFacturacion.getFechaHoraAlta());
			datosFila.put("COLHOR", expedienteFacturacion.getExpedienteInterpretacion().getFechaHoraIni() + " - "
					+ expedienteFacturacion.getExpedienteInterpretacion().getFechaHoraFin());

			datosFila.put("COLDESC", expedienteFacturacion.getTitulo());
			datosFila.put("COLNINT",
					expedienteFacturacion.getDatosFacturacionInterpretacion().getNumInterpretes().toString());

			datosFila.put("COLTOT",
					format2DecimalsEuro(expedienteFacturacion.getDatosFacturacionInterpretacion().getImporteTotal()));

			listaFila.add(datosFila);
		}

		return listaFila;
	}

	/**
	 * @param listFacturasExp List<FacturasExpediente>
	 * @param factura         Factura
	 * @return List<ExpedienteFacturacion>
	 */
	private List<ExpedienteFacturacion> obtenerDatosExpedientesTabla(List<FacturasExpediente> listFacturasExp,
			Factura factura) {
		ExpedienteFacturacion filterExpFacturacion = new ExpedienteFacturacion();
		Gestor entidadContactoFactura = new Gestor();
		Entidad entidad = new Entidad();
		entidad.setCodigo(factura.getIdEntidadAsoc());
		entidad.setTipo(factura.getTipoEntidadAsoc());
		entidadContactoFactura.setEntidad(entidad);
		Solicitante solicitante = new Solicitante();
		solicitante.setDni(factura.getDniContacto());
		entidadContactoFactura.setSolicitante(solicitante);
		filterExpFacturacion.setEntidadContactoFactura(entidadContactoFactura);

		String idTipoExpediente;
		if (TipoExpedienteEnum.INTERPRETACION.getValue().equals(factura.getTipoExpFactura())) {
			idTipoExpediente = String.valueOf(TipoExpedienteGrupoEnum.INTERPRETACION.getValue());
		} else {
			idTipoExpediente = String.valueOf(TipoExpedienteGrupoEnum.TRADUCCION_REVISION.getValue());
		}
		filterExpFacturacion.setIdTipoExpediente(idTipoExpediente);

		List<Expediente> expedientesList = new ArrayList<Expediente>();

		for (FacturasExpediente facturasExpediente : listFacturasExp) {
			Expediente exp = new Expediente();
			exp.setAnyo(facturasExpediente.getAnyo());
			exp.setNumExp(facturasExpediente.getNumExp());
			expedientesList.add(exp);
		}

		JQGridRequestDto jqGridRequestDto = new JQGridRequestDto(null, null, DBConstants.ANYONUMEXPCONCATENADO, "ASC");
		return this.expedienteFacturacionDao.borradorFacturaTablaFilter(filterExpFacturacion, jqGridRequestDto,
				expedientesList, false);
	}

	/**
	 *
	 * @param listFacturasExp
	 * @param factura
	 * @return
	 */
	private List<Map<String, String>> generarListaAnexoExpTradRev(List<ExpedienteFacturacion> lstExpedientes,
			Factura factura) {

		final Locale locale = LocaleContextHolder.getLocale();
		Map<String, String> datosFila;
		List<Map<String, String>> listaFila = new ArrayList<Map<String, String>>();

		for (ExpedienteFacturacion expedienteFacturacion : lstExpedientes) {
			datosFila = new HashMap<String, String>();
			datosFila.put("CAXN", expedienteFacturacion.getAnyoNumExpConcatenado()
					+ (Constants.CIEN != expedienteFacturacion.getPorFactura() ? " \\b\\cf6*\\cf0\\b0" : ""));
			datosFila.put("CAXF", expedienteFacturacion.getFechaHoraAlta());
			datosFila.put("CAXTIP", obtenerDescTipoExpediente(expedienteFacturacion));

			if (locale.getLanguage().equals(Constants.LANG_EUSKERA)) {
				datosFila.put("CAXRLV", expedienteFacturacion.getExpedienteTradRev().getRelevanciaDescEu());
				datosFila.put("CAXIDI", expedienteFacturacion.getExpedienteTradRev().getIdIdiomaDescEu());
			} else {
				datosFila.put("CAXRLV", expedienteFacturacion.getExpedienteTradRev().getRelevanciaDescEs());
				datosFila.put("CAXIDI", expedienteFacturacion.getExpedienteTradRev().getIdIdiomaDescEs());
			}
			datosFila.put("CAXTAR", format4DecimalsEuro(
					expedienteFacturacion.getExpedienteTradRev().getDatosFacturacionExpediente().getTarifaPal()));
			datosFila.put("CAXNP", format2Decimals(expedienteFacturacion.getExpedienteTradRev()
					.getDatosFacturacionExpediente().getNumTotalPalFacturar()));

			datosFila.put("CAXC1", format2Decimals(
					expedienteFacturacion.getExpedienteTradRev().getDatosFacturacionExpediente().getNumPalConcor084()));
			datosFila.put("CAXC2", format2Decimals(expedienteFacturacion.getExpedienteTradRev()
					.getDatosFacturacionExpediente().getNumPalConcor8594()));
			datosFila.put("CAXC3", format2Decimals(expedienteFacturacion.getExpedienteTradRev()
					.getDatosFacturacionExpediente().getNumPalConcor95100()));

			// preg
			datosFila.put("CAXUR", literalSiNo(expedienteFacturacion.getExpedienteTradRev().getIndUrgente()));
			datosFila.put("CAXDF", literalSiNo(expedienteFacturacion.getExpedienteTradRev().getIndDificil()));
			datosFila.put("CAXIB", format2DecimalsEuro(
					expedienteFacturacion.getExpedienteTradRev().getDatosFacturacionExpediente().getImporteBase()));
			datosFila.put("CAXII", format2DecimalsEuro(
					expedienteFacturacion.getExpedienteTradRev().getDatosFacturacionExpediente().getImporteIva()));
			datosFila.put("CAXIT", format2DecimalsEuro(
					expedienteFacturacion.getExpedienteTradRev().getDatosFacturacionExpediente().getImporteTotal()));
			listaFila.add(datosFila);

		}
		return listaFila;
	}

	public static String format2DecimalsEuro(BigDecimal valor) {
		String valorStr = StringUtils.EMPTY;
		if (valor != null) {
			valorStr = GeneralUtils.format2Decimals(valor).concat(Constants.ESPACIO).concat("\\'80");
		}
		return valorStr;
	}

	public static String format2Decimals(BigDecimal valor) {
		String valorStr = StringUtils.EMPTY;
		if (valor != null) {
			DecimalFormatSymbols symbols = new DecimalFormatSymbols();
			symbols.setDecimalSeparator(',');
			DecimalFormat formatter = new DecimalFormat("0.##", symbols);

			valorStr = formatter.format(valor);
		}
		return valorStr;
	}

	public static String literalSiNo(String valor) {
		if (Constants.SI.equals(valor)) {
			return "Bai";
		} else {
			return "Ez";
		}
	}

	public static String format4DecimalsEuro(BigDecimal valor) {
		String valorStr = StringUtils.EMPTY;
		if (valor != null) {
			valorStr = GeneralUtils.format4Decimals(valor).concat(Constants.ESPACIO).concat("\\'80");
		}
		return valorStr;
	}

	// METODOS PARA LA GENERACION DE LA TABLA DINAMICA
	/**
	 * The method generarTablaExpTradRev.
	 *
	 * @param listFacturasExp List<FacturasExpediente>
	 * @param factura         Factura
	 * @return String
	 */

	private String generarTablasTradRevVariasEntFacturacion(List<FacturasExpediente> lstExpedientes, Factura factura) {
		final Locale locale = LocaleContextHolder.getLocale();
		// Las etiquetas de las columnas de cabecera
		String importeTotalLabel = INICIO_COLUMNA_CABECERA + this.msg.getMessage(LABEL_TOTAL, null, locale)
				+ FIN_COLUMNA_CABECERA;
		String entidadLabel = INICIO_COLUMNA_CABECERA + this.msg.getMessage("label.tipoEntidad.entidad", null, locale)
				+ FIN_COLUMNA_CABECERA;
		String factPorcLabel = INICIO_COLUMNA_CABECERA + this.msg.getMessage("label.porFact", null, locale)
				+ FIN_COLUMNA_CABECERA;
		String ivaLabel = INICIO_COLUMNA_CABECERA + this.msg.getMessage("label.iva", null, locale)
				+ FIN_COLUMNA_CABECERA;
		String numTotalPalLabel = INICIO_COLUMNA_CABECERA + this.msg.getMessage("aa79b.tabla.numTotalPal", null, locale)
				+ FIN_COLUMNA_CABECERA;
		String entre0y84Label = INICIO_COLUMNA_CABECERA + this.msg.getMessage("label.entre084", null, locale)
				+ FIN_COLUMNA_CABECERA;
		String entre85y94Label = INICIO_COLUMNA_CABECERA + this.msg.getMessage("label.entre8594", null, locale)
				+ FIN_COLUMNA_CABECERA;
		String entre95y100Label = INICIO_COLUMNA_CABECERA + this.msg.getMessage("label.entre95100", null, locale)
				+ FIN_COLUMNA_CABECERA;
		String impBaseLabel = INICIO_COLUMNA_CABECERA + this.msg.getMessage("label.impBase", null, locale)
				+ FIN_COLUMNA_CABECERA;

		StringBuilder tabla = new StringBuilder(FacturaServiceImpl.STRING_BUILDER_INIT);

		final List<ExpedienteFacturacion> lstExpedientesVariosPagadores = this.expedienteFacturacionDao
				.listadoExpedientesFacturaVariosPagadores(lstExpedientes, TipoExpedienteEnum.TRADUCCION.getValue());
		String expAnteriorTmp = "";

		if (!lstExpedientesVariosPagadores.isEmpty()) {
			tabla.append("\\b\\cf6*\\cf0   "
					+ this.msg.getMessage("label.facturacionExpediente.facturasVariosPagadores", null, locale)
					+ " \\b0\\par\\par ");

			for (ExpedienteFacturacion lineaPagador : lstExpedientesVariosPagadores) {

				if (!expAnteriorTmp.equals(lineaPagador.getAnyoNumExpConcatenado())) {
					cierraTablaExpedienteVariosPagadores(tabla, expAnteriorTmp);
					// dibujar la cabecera del expediente
					tabla.append(this.definirCabeceraNumExpHorizontal(
							INICIO_COLUMNA_CABECERA + this.msg.getMessage(LABEL_NUM_EXP, null, locale) + ": "
									+ lineaPagador.getAnyoNumExpConcatenado() + FIN_COLUMNA_CABECERA)
							+ FIN_FILA);
					tabla.append(this.definirCabeceraTradRevVariasEntFacturacion());
					tabla.append(this.definirCabeceraTradRevVariasEntFacturacion()).append(entidadLabel)
							.append(factPorcLabel).append(ivaLabel).append(numTotalPalLabel).append(entre0y84Label)
							.append(entre85y94Label).append(entre95y100Label).append(impBaseLabel).append(ivaLabel)
							.append(importeTotalLabel).append(FIN_FILA);

					expAnteriorTmp = lineaPagador.getAnyoNumExpConcatenado();
				}

				// dibujo el contenido de una fila

				tabla.append(this.definirFilaTradRev());
				tabla.append(
						INICIO_CELDA + lineaPagador.getEntidadContactoFactura().getEntidad().getDescEu() + FIN_CELDA);
				tabla.append(INICIO_CELDA
						+ lineaPagador.getExpedienteTradRev().getDatosFacturacionExpediente().getPorcentajeFacturacion()
						+ FIN_CELDA);
				tabla.append(INICIO_CELDA + literalSiNo(lineaPagador.getIndIva()) + FIN_CELDA);
				tabla.append(INICIO_CELDA + format2Decimals(
						lineaPagador.getExpedienteTradRev().getDatosFacturacionExpediente().getNumTotalPalFacturar())
						+ FIN_CELDA);
				tabla.append(INICIO_CELDA + format2Decimals(
						lineaPagador.getExpedienteTradRev().getDatosFacturacionExpediente().getNumPalConcor084())
						+ FIN_CELDA);
				tabla.append(INICIO_CELDA + format2Decimals(
						lineaPagador.getExpedienteTradRev().getDatosFacturacionExpediente().getNumPalConcor8594())
						+ FIN_CELDA);
				tabla.append(INICIO_CELDA + format2Decimals(
						lineaPagador.getExpedienteTradRev().getDatosFacturacionExpediente().getNumPalConcor95100())
						+ FIN_CELDA);
				tabla.append(INICIO_CELDA
						+ format2DecimalsEuro(
								lineaPagador.getExpedienteTradRev().getDatosFacturacionExpediente().getImporteBase())
						+ FIN_CELDA);
				tabla.append(INICIO_CELDA
						+ format2DecimalsEuro(
								lineaPagador.getExpedienteTradRev().getDatosFacturacionExpediente().getImporteIva())
						+ FIN_CELDA);
				tabla.append(INICIO_CELDA
						+ format2DecimalsEuro(
								lineaPagador.getExpedienteTradRev().getDatosFacturacionExpediente().getImporteTotal())
						+ FIN_CELDA);
				tabla.append(FIN_FILA);

			}
			cierraTablaExpedienteVariosPagadores(tabla, expAnteriorTmp);
		}
		return tabla.toString();

	}

	private String generarTablasInterVariasEntFacturacion(List<FacturasExpediente> lstExpedientes, Factura factura) {
		final Locale locale = LocaleContextHolder.getLocale();
		// Las etiquetas de las columnas de cabecera
		String importeTotalLabel = INICIO_COLUMNA_CABECERA + this.msg.getMessage(LABEL_TOTAL, null, locale)
				+ FIN_COLUMNA_CABECERA;
		String entidadLabel = INICIO_COLUMNA_CABECERA + this.msg.getMessage("label.tipoEntidad.entidad", null, locale)
				+ FIN_COLUMNA_CABECERA;
		String factPorcLabel = INICIO_COLUMNA_CABECERA + this.msg.getMessage("label.porFact", null, locale)
				+ FIN_COLUMNA_CABECERA;
		String ivaLabel = INICIO_COLUMNA_CABECERA + this.msg.getMessage("label.iva", null, locale)
				+ FIN_COLUMNA_CABECERA;
		String impBaseLabel = INICIO_COLUMNA_CABECERA + this.msg.getMessage("label.impBase", null, locale)
				+ FIN_COLUMNA_CABECERA;

		StringBuilder tabla = new StringBuilder(FacturaServiceImpl.STRING_BUILDER_INIT);

		final List<ExpedienteFacturacion> lstExpedientesVariosPagadores = this.expedienteFacturacionDao
				.listadoExpedientesFacturaVariosPagadores(lstExpedientes, TipoExpedienteEnum.INTERPRETACION.getValue());
		String expAnteriorTmp = "";

		if (!lstExpedientesVariosPagadores.isEmpty()) {
			// Salto de página
			tabla.append("\\pard \\insrsid \\page \\par");
			tabla.append("\\b\\cf6*\\cf0   "
					+ this.msg.getMessage("label.facturacionExpediente.facturasVariosPagadores", null, locale)
					+ " \\b0\\par\\par ");

			for (ExpedienteFacturacion lineaPagador : lstExpedientesVariosPagadores) {

				if (!expAnteriorTmp.equals(lineaPagador.getAnyoNumExpConcatenado())) {
					cierraTablaExpedienteVariosPagadores(tabla, expAnteriorTmp);
					// dibujar la cabecera del expediente
					tabla.append(this.definirCabeceraNumExpVertical(
							INICIO_COLUMNA_CABECERA + this.msg.getMessage(LABEL_NUM_EXP, null, locale) + ": "
									+ lineaPagador.getAnyoNumExpConcatenado() + FIN_COLUMNA_CABECERA)
							+ FIN_FILA);
					tabla.append(this.definirCabeceraInterVariasEntFacturacion());
					tabla.append(this.definirCabeceraInterVariasEntFacturacion()).append(entidadLabel)
							.append(factPorcLabel).append(ivaLabel).append(impBaseLabel).append(ivaLabel)
							.append(importeTotalLabel).append(FIN_FILA);

					expAnteriorTmp = lineaPagador.getAnyoNumExpConcatenado();
				}

				// dibujo el contenido de una fila

				tabla.append(this.definirFilaInter());
				tabla.append(
						INICIO_CELDA + lineaPagador.getEntidadContactoFactura().getEntidad().getDescEu() + FIN_CELDA);
				tabla.append(INICIO_CELDA + lineaPagador.getDatosFacturacionInterpretacion().getPorcentajeFacturacion()
						+ FIN_CELDA);
				tabla.append(INICIO_CELDA + literalSiNo(lineaPagador.getIndIva()) + FIN_CELDA);
				tabla.append(INICIO_CELDA
						+ format2DecimalsEuro(lineaPagador.getDatosFacturacionInterpretacion().getImporteBase())
						+ FIN_CELDA);
				tabla.append(INICIO_CELDA
						+ format2DecimalsEuro(lineaPagador.getDatosFacturacionInterpretacion().getImporteIva())
						+ FIN_CELDA);
				tabla.append(INICIO_CELDA
						+ format2DecimalsEuro(lineaPagador.getDatosFacturacionInterpretacion().getImporteTotal())
						+ FIN_CELDA);
				tabla.append(FIN_FILA);

			}
			cierraTablaExpedienteVariosPagadores(tabla, expAnteriorTmp);
		}
		return tabla.toString();

	}

	/**
	 * @param tabla
	 * @param expAnteriorTmp
	 */
	private void cierraTablaExpedienteVariosPagadores(StringBuilder tabla, String expAnteriorTmp) {
		if (StringUtils.isNotBlank(expAnteriorTmp)) {
			// El cierre de la tabla anterior si la hay
			tabla.append(FIN_TABLA);
			tabla.append("\\par");
		}
	}

	private String definirCabeceraTradRevVariasEntFacturacion() {
		String cabecera = CABECERA;
		StringBuilder titulo = new StringBuilder(cabecera);
		titulo.append(BORDES_CELDA).append(WIDTH_COLUMN_1).append(BORDES_CELDA).append(WIDTH_COLUMN_2)
				.append(BORDES_CELDA).append(WIDTH_COLUMN_3).append(BORDES_CELDA).append(WIDTH_COLUMN_4)
				.append(BORDES_CELDA).append(WIDTH_COLUMN_5).append(BORDES_CELDA).append(WIDTH_COLUMN_6)
				.append(BORDES_CELDA).append(WIDTH_COLUMN_7).append(BORDES_CELDA).append(WIDTH_COLUMN_8)
				.append(BORDES_CELDA).append(WIDTH_COLUMN_9).append(BORDES_CELDA).append(WIDTH_COLUMN_10)
				.append(BORDES_CELDA);
		return titulo.toString();
	}

	private String definirCabeceraInterVariasEntFacturacion() {
		String cabecera = CABECERA;
		StringBuilder titulo = new StringBuilder(cabecera);
		titulo.append(BORDES_CELDA).append(WIDTH_COLUMN_1_INTER).append(BORDES_CELDA).append(WIDTH_COLUMN_2_INTER)
				.append(BORDES_CELDA).append(WIDTH_COLUMN_3_INTER).append(BORDES_CELDA).append(WIDTH_COLUMN_4_INTER)
				.append(BORDES_CELDA).append(WIDTH_COLUMN_5_INTER).append(BORDES_CELDA).append(WIDTH_COLUMN_6_INTER)
				.append(BORDES_CELDA);
		return titulo.toString();
	}

	private String definirCabeceraNumExpHorizontal(String contenidoCabNumExp) {
		String cabecera = CABECERA;
		StringBuilder titulo = new StringBuilder("\\par\\par" + cabecera);
		titulo.append(BORDES_CELDA).append(WIDTH_COLUMN_ANCHO_TOTAL_HORIZONTAL).append(BORDES_CELDA)
				.append(contenidoCabNumExp);
		return titulo.toString();
	}

	private String definirCabeceraNumExpVertical(String contenidoCabNumExp) {
		String cabecera = CABECERA;
		StringBuilder titulo = new StringBuilder("\\par\\par" + cabecera);
		titulo.append(BORDES_CELDA).append(WIDTH_COLUMN_ANCHO_TOTAL_VERTICAL).append(BORDES_CELDA)
				.append(contenidoCabNumExp);
		return titulo.toString();
	}

	/**
	 * Metodo que devuelve la cabecera de celdas
	 *
	 * @return String
	 */
	private String definirFilaTradRev() {
		String cabecera = FILA;
		StringBuilder titulo = new StringBuilder(cabecera);
		titulo.append(BORDES_CELDA).append(WIDTH_COLUMN_1).append(BORDES_CELDA).append(WIDTH_COLUMN_2)
				.append(BORDES_CELDA).append(WIDTH_COLUMN_3).append(BORDES_CELDA).append(WIDTH_COLUMN_4)
				.append(BORDES_CELDA).append(WIDTH_COLUMN_5).append(BORDES_CELDA).append(WIDTH_COLUMN_6)
				.append(BORDES_CELDA).append(WIDTH_COLUMN_7).append(BORDES_CELDA).append(WIDTH_COLUMN_8)
				.append(BORDES_CELDA).append(WIDTH_COLUMN_9).append(BORDES_CELDA).append(WIDTH_COLUMN_10)
				.append(BORDES_CELDA);
		return titulo.toString();
	}

	/**
	 *
	 * @return
	 */
	private String definirFilaInter() {
		String cabecera = FILA;
		StringBuilder titulo = new StringBuilder(cabecera);
		titulo.append(BORDES_CELDA).append(WIDTH_COLUMN_1).append(BORDES_CELDA).append(WIDTH_COLUMN_2)
				.append(BORDES_CELDA).append(WIDTH_COLUMN_3).append(BORDES_CELDA).append(WIDTH_COLUMN_4)
				.append(BORDES_CELDA).append(WIDTH_COLUMN_5).append(BORDES_CELDA).append(WIDTH_COLUMN_6)
				.append(BORDES_CELDA);
		return titulo.toString();
	}

}
