package com.ejie.aa79b.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.dao.Aa79bSolicitudWsDao;
import com.ejie.aa79b.dao.BitacoraExpedienteDao;
import com.ejie.aa79b.dao.ConfigLibroRegistroDao;
import com.ejie.aa79b.dao.ContactoFacturacionExpedienteDao;
import com.ejie.aa79b.dao.DatosTareaTradosDao;
import com.ejie.aa79b.dao.DireccionNoraDao;
import com.ejie.aa79b.dao.DocumentosExpedienteDao;
import com.ejie.aa79b.dao.ExcepcionFacturacionDao;
import com.ejie.aa79b.dao.ExpedienteDao;
import com.ejie.aa79b.dao.ExpedienteInterpretacionDao;
import com.ejie.aa79b.dao.ExpedienteTradRevDao;
import com.ejie.aa79b.dao.ExpedientesRelacionadosDao;
import com.ejie.aa79b.dao.GestorExpedienteDao;
import com.ejie.aa79b.dao.IdiomaDestinoDao;
import com.ejie.aa79b.dao.LibroRegistroDao;
import com.ejie.aa79b.dao.ObservacionesExpedienteDao;
import com.ejie.aa79b.dao.OidsAuxiliarDao;
import com.ejie.aa79b.dao.RegistroAccionesDao;
import com.ejie.aa79b.dao.TareasDao;
import com.ejie.aa79b.model.BitacoraExpediente;
import com.ejie.aa79b.model.BitacoraSolicitante;
import com.ejie.aa79b.model.ConfigLibroRegistro;
import com.ejie.aa79b.model.ContactoFacturacionExpediente;
import com.ejie.aa79b.model.DatosContacto;
import com.ejie.aa79b.model.DatosTareaTrados;
import com.ejie.aa79b.model.DireccionNora;
import com.ejie.aa79b.model.DocumentosExpediente;
import com.ejie.aa79b.model.EstadosExpediente;
import com.ejie.aa79b.model.ExcepcionFacturacion;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.ExpedientesRelacionados;
import com.ejie.aa79b.model.FasesExpediente;
import com.ejie.aa79b.model.GestorExpediente;
import com.ejie.aa79b.model.LibroRegistro;
import com.ejie.aa79b.model.LibroRegistroModel;
import com.ejie.aa79b.model.ListaExpediente;
import com.ejie.aa79b.model.ObservacionesExpediente;
import com.ejie.aa79b.model.Persona;
import com.ejie.aa79b.model.RegistroAcciones;
import com.ejie.aa79b.model.Tareas;
import com.ejie.aa79b.model.enums.AccesoExpedienteEnum;
import com.ejie.aa79b.model.enums.AccionBitacoraEnum;
import com.ejie.aa79b.model.enums.AccionesEnum;
import com.ejie.aa79b.model.enums.ClasificacionDocumentosEnum;
import com.ejie.aa79b.model.enums.EstadoExpedienteEnum;
import com.ejie.aa79b.model.enums.FaseExpedienteEnum;
import com.ejie.aa79b.model.enums.PuntosMenuEnum;
import com.ejie.aa79b.model.enums.TipoExpedienteEnum;
import com.ejie.aa79b.security.Usuario;
import com.ejie.aa79b.utils.Utils;
import com.ejie.aa79b.webservices.srp.RegistroPresencialWebService;

/**
 * DatosGeneralesExpedienteServiceImpl Service para gestionar la pestaña de
 * datos generales del expediente
 *
 * @author mrodriguez
 */

@Service(value = "datosGeneralesExpedienteService")
public class DatosGeneralesExpedienteServiceImpl implements DatosGeneralesExpedienteService {

	@Autowired()
	private ExpedienteDao expedienteDao;

	@Autowired()
	private ExpedienteInterpretacionDao expedienteInterpretacionDao;

	@Autowired()
	private ExpedienteTradRevDao expedienteTradRevDao;

	@Autowired()
	private GestorExpedienteDao gestorExpedienteDao;

	@Autowired()
	private ObservacionesExpedienteDao observacionesExpedienteDao;

	@Autowired()
	private DocumentosExpedienteDao documentosExpedienteDao;

	@Autowired()
	private DireccionNoraDao direccionNoraDao;

	@Autowired()
	private BitacoraExpedienteDao bitacoraExpedienteDao;

	@Autowired()
	private DireccionNoraService direccionNoraService;

	@Autowired()
	private RegistroAccionesDao registroAccionesDao;

	@Autowired()
	private ExcepcionFacturacionDao excepcionFacturacionDao;

	@Autowired()
	private ContactoFacturacionExpedienteDao contactoFacturacionExpedienteDao;

	@Autowired()
	private LibroRegistroDao libroRegistroDao;

	@Autowired()
	private ConfigLibroRegistroDao configLibroRegistroDao;

	@Autowired()
	private ReloadableResourceBundleMessageSource msg;

	@Autowired()
	private RegistroPresencialWebService registroPresencialWebService;

	@Autowired()
	private ExpedienteTradRevService expedienteTradRevService;

	@Autowired()
	private ExpedienteService expedienteService;

	@Autowired()
	private TareasService tareasService;

	@Autowired()
	private DatosTareaTradosDao datosTareaTradosDao;

	@Autowired()
	private OidsAuxiliarDao oidsAuxiliarDao;

	@Autowired()
	private TareasDao tareasDao;

	@Autowired()
	private IdiomaDestinoDao idiomaDestinoDao;

	@Autowired()
	private ExpedientesRelacionadosService expedientesRelacionadosService;

	@Autowired
	private Aa79bSolicitudWsDao aa79bSolicitudWsDao;

	@Autowired()
	private ExpedientesRelacionadosDao expedientesRelacionadosDao;

	private static final Logger LOGGER = LoggerFactory.getLogger(DatosGeneralesExpedienteServiceImpl.class);

	/**
	 * Inserts a single row in the DatosGeneralesExpediente table.
	 *
	 * @param bean Expediente
	 * @return Expediente
	 */
	@Override()
	@Transactional(rollbackFor = Throwable.class)
	public Expediente add(Expediente bean, HttpServletRequest request) {
		if (bean != null) {
			Expediente expediente = this.expedienteDao.add(bean);
			bean.setAnyo(expediente.getAnyo());
			bean.setNumExp(expediente.getNumExp());

			BitacoraExpediente bitacoraExpediente = new BitacoraExpediente();
			bitacoraExpediente.setAnyo(bean.getAnyo());
			bitacoraExpediente.setNumExp(bean.getNumExp());
			EstadosExpediente estadosExpediente = new EstadosExpediente();
			estadosExpediente.setId((long) EstadoExpedienteEnum.ALTA_EXPEDIENTE.getValue());
			bitacoraExpediente.setEstadoExp(estadosExpediente);
			FasesExpediente fasesExpediente = new FasesExpediente();
			fasesExpediente.setId((long) FaseExpedienteEnum.ALTA.getValue());
			bitacoraExpediente.setFaseExp(fasesExpediente);

			this.bitacoraExpedienteDao.add(bitacoraExpediente);

			Map<String, String> parametros = new LinkedHashMap<String, String>();
			parametros.put("anyo", "label.anyo");
			parametros.put("numExp", "label.numeroExpediente");
			parametros.put("idTipoExpediente", "label.tipoExpediente");
			parametros.put("tecnico.dni", "label.dniTecnico");

			this.registrarAcciones(bean, null, Constants.ACCION_ALTA, parametros, "mensaje.nuevoExpediente");

			bitacoraExpediente = new BitacoraExpediente();
			bitacoraExpediente.setAnyo(bean.getAnyo());
			bitacoraExpediente.setNumExp(bean.getNumExp());
			estadosExpediente = new EstadosExpediente();
			fasesExpediente = new FasesExpediente();

			if (TipoExpedienteEnum.INTERPRETACION.getValue().equals(expediente.getIdTipoExpediente())) {
				estadosExpediente.setId((long) EstadoExpedienteEnum.EN_CURSO.getValue());
				fasesExpediente.setId((long) FaseExpedienteEnum.PDTE_EJECT_TAREAS.getValue());
			} else {
				estadosExpediente.setId((long) EstadoExpedienteEnum.EN_ESTUDIO.getValue());
				fasesExpediente.setId((long) FaseExpedienteEnum.ESTUDIO_EXPEDIENTE.getValue());
			}

			bitacoraExpediente.setEstadoExp(estadosExpediente);
			bitacoraExpediente.setFaseExp(fasesExpediente);

			BitacoraExpediente bitacoraExp = this.bitacoraExpedienteDao.add(bitacoraExpediente);
			bean.setEstadoBitacora(bitacoraExp.getIdEstadoBitacora());

			this.expedienteDao.updateIdEstadoBitacora(bean);

			BitacoraSolicitante bitacoraSolicitante = new BitacoraSolicitante();
			bitacoraSolicitante.setAnyo(bean.getAnyo());
			bitacoraSolicitante.setNumExp(bean.getNumExp());
			bitacoraSolicitante.setUsuario(Constants.IZO);
			this.bitacoraExpedienteDao.addBitacoraSolicitante(bitacoraSolicitante,
					AccionBitacoraEnum.ALTA_EXP.getValue());

			if (TipoExpedienteEnum.INTERPRETACION.getValue().equals(expediente.getIdTipoExpediente())) {
				BitacoraSolicitante bitacoraSolicitanteFilter = new BitacoraSolicitante();
				bitacoraSolicitanteFilter.setAnyo(bean.getAnyo());
				bitacoraSolicitanteFilter.setNumExp(bean.getNumExp());
				bitacoraSolicitanteFilter.setUsuario(Constants.IZO);
				this.bitacoraExpedienteDao.addBitacoraSolicitante(bitacoraSolicitanteFilter,
						AccionBitacoraEnum.EXP_EN_CURSO.getValue());
			}

			this.addDatosGestorExpediente(bean, parametros);

			this.addExpedienteInterpretacion(bean);

			this.addExpedienteTradRev(bean);

			this.addDocumentosExpediente(bean, request);

			this.expedienteTradRevDao.actualizarNumPalSolicConIzo(bean.getAnyo(), bean.getNumExp());

			this.guardarTecnico(expediente);

			this.anotarLibroRegistro(bean);

			if(TipoExpedienteEnum.TRADUCCION.getValue().equals(expediente.getIdTipoExpediente())
					|| TipoExpedienteEnum.REVISION.getValue().equals(expediente.getIdTipoExpediente())) {
				if (StringUtils.isNotBlank(expediente.getExpedienteTradRev().getRefTramitagune())) {
					List<Expediente> listExp = this.aa79bSolicitudWsDao.getExpedientesRefAplic(expediente);
					if(listExp.size() > 0) {
						this.expedientesRelacionadosDao.addLista(listExp, expediente);
					}
				}
			}

		}

		return bean;
	}

	/**
	 * @param expediente
	 */
	private void guardarTecnico(Expediente expediente) {
		ListaExpediente listaExpedientes = new ListaExpediente();

		List<Expediente> expedientes = new ArrayList<Expediente>();
		expedientes.add(expediente);

		listaExpedientes.setListaExpediente(expedientes);

		this.expedienteService.asignarTecnicoAExpedientes(listaExpedientes);
	}

	/**
	 * @param bean
	 */
	@Override
	public void anotarLibroRegistro(Expediente bean) {
		ConfigLibroRegistro configLibroRegistro = new ConfigLibroRegistro();
		configLibroRegistro.setId(Constants.ID_DATOS_BASICOS);

		ConfigLibroRegistro configLibroRegistroAux = this.configLibroRegistroDao.find(configLibroRegistro);

		if (this.isAnotacionLibroRegistroActivo(configLibroRegistroAux)) {
			LibroRegistroModel libroRegistroEntrada = new LibroRegistroModel();
			Locale locale = new Locale(Constants.LANG_EUSKERA);
			StringBuilder strb = new StringBuilder();
			if (null != bean.getLblRegPresencial()) {
				strb.append(this.msg.getMessage(bean.getLblRegPresencial(), null, locale)).append(" ")
						.append(bean.getAnyoNumExpConcatenado());
			} else {
				strb.append(this.msg.getMessage("menu.altaExpediente", null, locale)).append(" ")
						.append(bean.getAnyoNumExpConcatenado());
			}

			libroRegistroEntrada.setMatter(strb.toString());

			try {
				LibroRegistroModel libroRegistroRst = this.registroPresencialWebService
						.altaRegistroEntrada(libroRegistroEntrada);

				if (libroRegistroRst != null) {
					LibroRegistro libroRegistro = new LibroRegistro();
					libroRegistro.setAnyo(bean.getAnyo());
					libroRegistro.setNumExp(bean.getNumExp());
					libroRegistro.setMatter(libroRegistroEntrada.getMatter());
					libroRegistro.setTelematico(Constants.NO);
					libroRegistro.setFechaRegistro(libroRegistroRst.getFechaRegistro());
					libroRegistro.setNumRegistro(libroRegistroRst.getNumRegistro());

					this.libroRegistroDao.add(libroRegistro);
				}

			} catch (Exception e) {
				DatosGeneralesExpedienteServiceImpl.LOGGER.info("Error al grabar en el libro de registro", e);
			}
		}
	}

	@Override
	public void anotarLibroRegistroSalida(Expediente bean) {
		ConfigLibroRegistro configLibroRegistro = new ConfigLibroRegistro();
		configLibroRegistro.setId(Constants.ID_DATOS_BASICOS);

		ConfigLibroRegistro configLibroRegistroAux = this.configLibroRegistroDao.find(configLibroRegistro);

		if (this.isAnotacionLibroRegistroActivo(configLibroRegistroAux)) {
			LibroRegistroModel libroRegistroEntrada = new LibroRegistroModel();
			Locale locale = new Locale(Constants.LANG_EUSKERA);
			StringBuilder strb = new StringBuilder();
			if (null != bean.getLblRegPresencial()) {
				strb.append(this.msg.getMessage(bean.getLblRegPresencial(), null, locale)).append(" ")
						.append(bean.getAnyoNumExpConcatenado());
			} else {
				strb.append(this.msg.getMessage("menu.altaExpediente", null, locale)).append(" ")
						.append(bean.getAnyoNumExpConcatenado());
			}

			libroRegistroEntrada.setMatter(strb.toString());

			try {
				LibroRegistroModel libroRegistroRst = this.registroPresencialWebService
						.altaRegistroSalida(libroRegistroEntrada);

				if (libroRegistroRst != null) {
					LibroRegistro libroRegistro = new LibroRegistro();
					libroRegistro.setAnyo(bean.getAnyo());
					libroRegistro.setNumExp(bean.getNumExp());
					libroRegistro.setIndSalida(Constants.SI);
					libroRegistro.setMatter(libroRegistroEntrada.getMatter());
					libroRegistro.setTelematico(Constants.NO);
					libroRegistro.setFechaRegistro(libroRegistroRst.getFechaRegistro());
					libroRegistro.setNumRegistro(libroRegistroRst.getNumRegistro());

					this.libroRegistroDao.add(libroRegistro);
				}

			} catch (Exception e) {
				DatosGeneralesExpedienteServiceImpl.LOGGER.info("Error al grabar en el libro de registro", e);
			}
		}
	}

	/**
	 * @param configLibroRegistroAux
	 * @return
	 */
	private boolean isAnotacionLibroRegistroActivo(ConfigLibroRegistro configLibroRegistroAux) {
		return configLibroRegistroAux != null && Constants.SI.equals(configLibroRegistroAux.getIndActivo());
	}

	/**
	 * @param bean Expediente
	 */
	private void addDocumentosExpediente(Expediente bean, HttpServletRequest request) {
		if (bean.getDocumentosExpediente() != null && !bean.getDocumentosExpediente().isEmpty()) {
			bean.getDocumentosExpediente().get(0).setAnyo(bean.getAnyo());
			bean.getDocumentosExpediente().get(0).setNumExp(bean.getNumExp());
			DocumentosExpediente beanDocExpediente = bean.getDocumentosExpediente().get(0);

			int x = 0;
			DocumentosExpediente documentosExpedienteAux = null;
			try {
				for (x = 0; x < beanDocExpediente.getFicheros().size()
						&& StringUtils.isNotBlank(beanDocExpediente.getFicheros().get(x).getContentType()); x++) {
					this.appendIdiomaDest(x, beanDocExpediente);

					documentosExpedienteAux = this.documentosExpedienteDao.add(beanDocExpediente, x);
					this.oidsAuxiliarDao.remove(beanDocExpediente.getFicheros().get(x).getOid());
				}
				// Actualiz datos T53
				this.updateDatosExpedienteTradRev(beanDocExpediente);
				beanDocExpediente.setTituloDifferentFromFileName();
			} catch (Exception e) {
				DatosGeneralesExpedienteServiceImpl.LOGGER.info("Excepción añadiendo documento a expediente nuevo. ",
						e);
				beanDocExpediente.getFicheros().get(x).setError(e.getLocalizedMessage());
				documentosExpedienteAux = beanDocExpediente;
			}
			List<DocumentosExpediente> nuevaListaDoc = new ArrayList<DocumentosExpediente>();
			nuevaListaDoc.add(documentosExpedienteAux);
			bean.setDocumentosExpediente(nuevaListaDoc);
		}
	}

	private void appendIdiomaDest(int x, DocumentosExpediente beanDocExpediente) {
		String idiomaDest = "";
		if (x == 1) {
			idiomaDest = this.idiomaDestinoDao.getCodeWithAnyoAndNumeExp(beanDocExpediente.getAnyo(),
					beanDocExpediente.getNumExp());
			String elNombre = beanDocExpediente.getFicheros().get(0).getNombre();
			elNombre = elNombre.substring(Constants.CERO, elNombre.lastIndexOf('.'));
			elNombre = elNombre + "_" + (idiomaDest.substring(0, 1).toUpperCase() + idiomaDest.substring(1));
			elNombre = elNombre + "." + beanDocExpediente.getFicheros().get(1).getExtension();
			beanDocExpediente.getFicheros().get(x).setTitulo(elNombre);
		}
	}

	/**
	 * @param beanDocExpediente DocumentosExpediente
	 */
	private void updateDatosExpedienteTradRev(DocumentosExpediente beanDocExpediente) {
		if (this.isClasificacionTradRev(beanDocExpediente)) {
			this.expedienteTradRevService.procRecalcularCamposDocumentacionPL(beanDocExpediente.getAnyo(),
					beanDocExpediente.getNumExp());
			beanDocExpediente.setLlamadaPL(true);
		} else {
			beanDocExpediente.setLlamadaPL(false);
		}
	}

	/**
	 * @param beanDocExpediente DocumentosExpediente
	 * @return boolean
	 */
	private boolean isClasificacionTradRev(DocumentosExpediente beanDocExpediente) {
		return ClasificacionDocumentosEnum.TRADUCCION.getValue() == beanDocExpediente.getClaseDocumento()
				|| (ClasificacionDocumentosEnum.REVISION.getValue() == beanDocExpediente.getClaseDocumento());
	}

	/**
	 * @param bean Expediente
	 */
	private void addExpedienteTradRev(Expediente bean) {
		if (bean.getExpedienteTradRev() != null) {
			bean.getExpedienteTradRev().setAnyo(bean.getAnyo());
			bean.getExpedienteTradRev().setNumExp(bean.getNumExp());
			bean.getExpedienteTradRev()
					.setIndObservaciones(this.obtenerIndObservaciones(bean.getExpedienteTradRev().getObservaciones()));
			this.expedienteTradRevDao.add(bean.getExpedienteTradRev());
			this.addObservaciones(bean.getExpedienteTradRev().getObservaciones(), bean);
		}
	}

	/**
	 * @param bean Expediente
	 */
	private void addExpedienteInterpretacion(Expediente bean) {
		if (bean.getExpedienteInterpretacion() != null) {
			DireccionNora direccionNora = new DireccionNora();
			if (bean.getExpedienteInterpretacion().getDirNora() != null) {
				direccionNora = this.direccionNoraDao.add(bean.getExpedienteInterpretacion().getDirNora());
			}
			bean.getExpedienteInterpretacion().setDirNora(direccionNora);
			bean.getExpedienteInterpretacion().setIndObservaciones(
					this.obtenerIndObservaciones(bean.getExpedienteInterpretacion().getObservaciones()));
			this.expedienteInterpretacionDao.add(bean);
			this.addObservaciones(bean.getExpedienteInterpretacion().getObservaciones(), bean);
		}
	}

	/**
	 * Inserta los datos del gestor en base de datos
	 *
	 * @param bean       Expediente
	 * @param parametros Map<String, String>
	 */
	private void addDatosGestorExpediente(Expediente bean, Map<String, String> parametros) {
		if (bean.getGestorExpediente() != null) {
			this.gestorExpedienteDao.add(bean);

			// Información de la facturación
			ExcepcionFacturacion excepcionFacturacion = new ExcepcionFacturacion();
			excepcionFacturacion.setIdEntidad036(bean.getGestorExpediente().getEntidad().getCodigo());
			excepcionFacturacion.setTipoEntidad036(bean.getGestorExpediente().getEntidad().getTipo());

			List<ExcepcionFacturacion> excepcionesFacturacion = this.excepcionFacturacionDao.findExcepcionesSolicitante(
					excepcionFacturacion, bean.getGestorExpediente().getSolicitante().getDni());

			if (excepcionesFacturacion.isEmpty()) {
				excepcionFacturacion.setTipoExcepcion036(Constants.CERO);
				excepcionesFacturacion = this.excepcionFacturacionDao.findExcepcionesFacturacion(excepcionFacturacion);
			}

			ContactoFacturacionExpediente contactoFacturacionExpediente;

			if (!excepcionesFacturacion.isEmpty()) {

				for (ExcepcionFacturacion excepcionFacturacionAux : excepcionesFacturacion) {

					contactoFacturacionExpediente = new ContactoFacturacionExpediente();
					contactoFacturacionExpediente.setAnyo(bean.getAnyo());
					contactoFacturacionExpediente.setNumExp(bean.getNumExp());
					contactoFacturacionExpediente.setIdEntidadAsoc058(excepcionFacturacionAux.getIdEntidadAsoc036());
					contactoFacturacionExpediente
							.setTipoEntidadAsoc058(excepcionFacturacionAux.getTipoEntidadAsoc036());
					contactoFacturacionExpediente.setDniContacto058(excepcionFacturacionAux.getDniContacto036());
					contactoFacturacionExpediente.setPorFactura058(excepcionFacturacionAux.getPorFactura036());

					this.contactoFacturacionExpedienteDao.add(contactoFacturacionExpediente);

				}

			} else {
				contactoFacturacionExpediente = new ContactoFacturacionExpediente();
				contactoFacturacionExpediente.setAnyo(bean.getAnyo());
				contactoFacturacionExpediente.setNumExp(bean.getNumExp());
				contactoFacturacionExpediente.setIdEntidadAsoc058(bean.getGestorExpediente().getEntidad().getCodigo());
				contactoFacturacionExpediente.setTipoEntidadAsoc058(bean.getGestorExpediente().getEntidad().getTipo());
				contactoFacturacionExpediente.setDniContacto058(bean.getGestorExpediente().getSolicitante().getDni());
				contactoFacturacionExpediente.setPorFactura058((long) 100);

				this.contactoFacturacionExpedienteDao.add(contactoFacturacionExpediente);
			}

			parametros.put("gestorExpediente.solicitante.dni", "label.dniGestor");
			parametros.put("gestorExpediente.entidad.codigo", "label.codigoEntidad");
			parametros.put("gestorExpediente.entidad.tipo", "label.tipoEntidad");
		}
	}

	/**
	 * @param bean       ObservacionesExpediente
	 * @param expediente Expediente
	 */
	private void addObservaciones(ObservacionesExpediente observaciones, Expediente expediente) {
		if (observaciones != null) {
			this.observacionesExpedienteDao.observacionesAdd(expediente);
			this.oidsAuxiliarDao.remove(observaciones.getOidFichero());
		}
	}

	/**
	 * @param bean       ObservacionesExpediente
	 * @param expediente Expediente
	 */

	private void updateObservaciones(ObservacionesExpediente observaciones, Expediente expediente) {
		ObservacionesExpediente observacionesExpediente = this.observacionesExpedienteDao.observacionesFind(expediente);
		if (observaciones != null) {

			if (observacionesExpediente != null) {
				this.observacionesExpedienteDao.observacionesUpdate(expediente);

				if (observacionesExpediente.getOidFichero() != null
						&& StringUtils.isNotEmpty(observacionesExpediente.getOidFichero())
						&& !observacionesExpediente.getOidFichero().contentEquals(observaciones.getOidFichero())) {
					this.eliminarFichObservAnteriores(observacionesExpediente);
					this.oidsAuxiliarDao.remove(observaciones.getOidFichero());
				}
			} else {
				this.addObservaciones(observaciones, expediente);
			}

		} else if (observacionesExpediente != null) {
			this.observacionesExpedienteDao.remove(expediente);
			this.eliminarFichObservAnteriores(observacionesExpediente);
		}
	}

	/**
	 * @param observacionesNuevas
	 * @param observacionesAnteriores
	 */
	private void eliminarFichObservAnteriores(ObservacionesExpediente observacionesAnteriores) {
		// Añado el oid a la tabla auxiliar para que sea eliminado por el
		// proceso Batch de limpieza del PID
		if (observacionesAnteriores.getOidFichero() != null
				&& StringUtils.isNotBlank(observacionesAnteriores.getOidFichero())) {
			this.oidsAuxiliarDao.add(observacionesAnteriores.getOidFichero());
		}
	}

	@Override()
	public Expediente update(Expediente bean, String origen) {
		if (bean != null) {
			Expediente original = this.find(bean, origen);
			this.expedienteDao.update(bean);
			// Asignamos al bean el estado de la bitacora del bean original
			// para grabarlo en el registro de acciones
			bean.setEstadoBitacora(original.getEstadoBitacora());

			Map<String, String> parametros = new LinkedHashMap<String, String>();
			parametros.put("anyo", "label.anyo");
			parametros.put("numExp", "label.numeroExpediente");
			parametros.put("idTipoExpediente", "label.tipoExpediente");
			parametros.put("fechaAlta", "label.fechaAlta");
			parametros.put("horaAlta", "label.horaAlta");
			parametros.put("titulo", "label.titulo");
			parametros.put("tecnico.dni", "label.dniTecnico");

			this.updateDatosGestorExpediente(bean, parametros);

			this.updateExpedienteInterpretacion(bean, parametros, origen);

			this.updateExpedienteTradRev(bean, parametros, origen, original);

			this.registrarAcciones(bean, original, Constants.ACCION_MODIFICACION, parametros,
					"mensaje.expedienteModificado");
		}

		return bean;
	}

	/**
	 * @param bean       Expediente
	 * @param origen     String
	 * @param parametros Map<String, String>
	 */
	private void updateExpedienteTradRev(Expediente bean, Map<String, String> parametros, String origen,
			Expediente original) {
		if (bean.getExpedienteTradRev() != null) {

			this.actualizarIndFacturable(bean, original);

			bean.getExpedienteTradRev().setAnyo(bean.getAnyo());
			bean.getExpedienteTradRev().setNumExp(bean.getNumExp());

			if (AccesoExpedienteEnum.ALTA.getValue().equals(origen)) {
				this.updateObservaciones(bean.getExpedienteTradRev().getObservaciones(), bean);
				bean.getExpedienteTradRev().setIndObservaciones(
						this.obtenerIndObservaciones(bean.getExpedienteTradRev().getObservaciones()));
			}
			this.expedienteTradRevDao.update(bean.getExpedienteTradRev());

			if (TipoExpedienteEnum.REVISION.getValue().equals(bean.getIdTipoExpediente())
					&& !original.getExpedienteTradRev().getIndPublicarBopv()
							.equals(bean.getExpedienteTradRev().getIndPublicarBopv())
			// &&
			// Constants.NO.equals(bean.getExpedienteTradRev().getIndPublicarBopv())
			// Antes sólo querían que se recalculara en el caso de pasar BOPV de
			// SI a NO.
			) {
				this.expedienteTradRevService.procRecalcularCamposDocumentacionPL(bean.getAnyo(), bean.getNumExp());
			}

			parametros.put("expedienteTradRev.indPublicarBopv", "label.bopv");
			parametros.put("expedienteTradRev.indPrevistoBopv", "label.previstoBopv");
			parametros.put("expedienteTradRev.idIdioma", "label.idiomaDestino");
			parametros.put("expedienteTradRev.indConfidencial", "label.indConfidencial");
			parametros.put("expedienteTradRev.indCorredaccion", "label.indCorredaccion");
			parametros.put("expedienteTradRev.refTramitagune", "label.referenciaTramitagune");
		}
	}

	/**
	 * @param bean
	 * @param original
	 */
	private void actualizarIndFacturable(Expediente bean, Expediente original) {
		if (TipoExpedienteEnum.REVISION.getValue().equals(bean.getIdTipoExpediente()) && !original
				.getExpedienteTradRev().getIndPublicarBopv().equals(bean.getExpedienteTradRev().getIndPublicarBopv())) {

			if (Constants.SI.equals(bean.getExpedienteTradRev().getIndPublicarBopv())) {
				bean.getExpedienteTradRev().setIndFacturable(Constants.NO);
			} else {
				bean.getExpedienteTradRev().setIndPublicarBopv(Constants.NO);
			}
		}
	}

	/**
	 * @param bean       Expediente
	 * @param parametros Map<String, String>
	 */
	private void updateExpedienteInterpretacion(Expediente bean, Map<String, String> parametros, String origen) {
		if (bean.getExpedienteInterpretacion() != null) {
			DireccionNora direccionNora = new DireccionNora();
			if (bean.getExpedienteInterpretacion().getDirNora() != null) {
				direccionNora = this.direccionNoraDao.update(bean.getExpedienteInterpretacion().getDirNora());

				parametros.put("expedienteInterpretacion.dirNora.dirNora", "label.lugarInterpretacion");
			}
			bean.getExpedienteInterpretacion().setDirNora(direccionNora);

			if (AccesoExpedienteEnum.ALTA.getValue().equals(origen)) {
				this.updateObservaciones(bean.getExpedienteInterpretacion().getObservaciones(), bean);
				bean.getExpedienteInterpretacion().setIndObservaciones(
						this.obtenerIndObservaciones(bean.getExpedienteInterpretacion().getObservaciones()));
			}
			this.expedienteInterpretacionDao.update(bean);

			parametros.put("expedienteInterpretacion.modoInterpretacion", "label.modoInterpretacion");
			parametros.put("expedienteInterpretacion.tipoActo", "label.tipoActo");
			parametros.put("expedienteInterpretacion.tipoPeticionario", "label.tipoPeticionario");
			parametros.put("expedienteInterpretacion.indProgramada", "label.interpretacionProgramada");
			parametros.put("expedienteInterpretacion.fechaIni", "label.fechaIni");
			parametros.put("expedienteInterpretacion.horaIni", "label.horaIni");
			parametros.put("expedienteInterpretacion.fechaFin", "label.fechaFin");
			parametros.put("expedienteInterpretacion.horaFin", "label.horaFin");
			parametros.put("expedienteInterpretacion.indPresupuesto", "label.presupuesto");
			parametros.put("expedienteInterpretacion.personaContacto", "label.nombreApellidosContacto");
			parametros.put("expedienteInterpretacion.emailContacto", "label.emailContacto");
			parametros.put("expedienteInterpretacion.telefonoContacto", "label.telefonoContacto");

		}
	}

	/**
	 * @param bean       Expediente
	 * @param parametros Map<String, String>
	 */
	private void updateDatosGestorExpediente(Expediente bean, Map<String, String> parametros) {
		if (bean.getGestorExpediente() != null) {
			this.gestorExpedienteDao.update(bean);

			parametros.put("gestorExpediente.solicitante.dni", "label.dniGestor");
			parametros.put("gestorExpediente.entidad.codigo", "label.codigoEntidad");
			parametros.put("gestorExpediente.entidad.tipo", "label.tipoEntidad");
		}
	}

	/**
	 * @param bean       Expediente
	 * @param original   Expediente
	 * @param accion     Long
	 * @param parametros Map<String, String>
	 * @param mensaje    String
	 */
	public void registrarAcciones(Expediente bean, Expediente original, Long accion, Map<String, String> parametros,
			String mensaje) {
		RegistroAcciones reg = new RegistroAcciones();
		reg.setIdPuntoMenu(Constants.PUNTO_MENU_TRAMITACION_EXPEDIENTES);
		reg.setIdAccion(accion);

		Locale locale = new Locale(Constants.LANG_EUSKERA);
		String aux = Utils.observacionesRegistro(bean, original, parametros, this.msg, locale);
		if (StringUtils.isNotBlank(aux)) {
			StringBuilder observ = new StringBuilder();
			observ.append(this.msg.getMessage(mensaje, null, locale)).append(" \n");
			observ.append(aux);
			observ.append(this.obtenerMensajeObservaciones(bean, original, locale));
			reg.setAnyo(bean.getAnyo());
			reg.setNumExp(bean.getNumExp());
			reg.setIdEstadoBitacora(bean.getEstadoBitacora());
			reg.setObserv(observ.toString());
			this.registroAccionesDao.add(reg);
		}
	}

	@Override()
	public Expediente find(Expediente bean, String origen) {
		Expediente expediente = null;
		if (bean != null) {
			expediente = this.expedienteDao.find(bean);
			GestorExpediente gestorExpediente = this.gestorExpedienteDao.getDatosContacto(expediente);

			DatosContacto datosContacto = this.getDatosContactoGestor(gestorExpediente);
			expediente.getGestorExpediente().getSolicitante().setDatosContacto(datosContacto);

			List<ExpedientesRelacionados> listExp = this.expedientesRelacionadosService.selectExpRelacionadosList(expediente);
			expediente.setListaExpedientesRelacionados(listExp);

			if (TipoExpedienteEnum.INTERPRETACION.getValue().equals(expediente.getIdTipoExpediente())
					&& expediente.getExpedienteInterpretacion() != null
					&& expediente.getExpedienteInterpretacion().getDirNora() != null) {
				DireccionNora direccion = new DireccionNora();
				direccion.setDirNora(expediente.getExpedienteInterpretacion().getDirNora().getDirNora());
				direccion = this.direccionNoraService.find(direccion);
				expediente.getExpedienteInterpretacion().setDirNora(Utils.obtenerDireccion(direccion));
			}

			if (AccesoExpedienteEnum.ALTA.getValue().equals(origen) || bean.isObservacionesVisibles()) {
				this.getObservacionesExp(expediente);
			}

			asignarTecnico(origen, expediente);

			// indVisiblePpto
			calcularIndPresupuestoVisibleParaUsuario(expediente);

			expTieneTareasEjecutadas(expediente);

		}

		return expediente;
	}

	@Override()
	public void procCambioTipoExpedientePL(final Long anyo, final Integer numExp, final String tipoExpediente) {
		this.expedienteDao.procCambioTipoExpedientePL(anyo, numExp, tipoExpediente);
	}

	/**
	 * @param expediente
	 */
	private void expTieneTareasEjecutadas(Expediente expediente) {
		if (TipoExpedienteEnum.INTERPRETACION.getValue().equals(expediente.getIdTipoExpediente())
				|| TipoExpedienteEnum.TRADUCCION.getValue().equals(expediente.getIdTipoExpediente())) {
			// Tiene tareas ejecutadas
			Tareas tareas = new Tareas();
			tareas.setAnyo(expediente.getAnyo());
			tareas.setNumExp(expediente.getNumExp());

			long numTareaEjec = this.tareasDao.findAllCountTareasEjecutadas(tareas);

			if (numTareaEjec > 0) {
				expediente.setTieneTareasEjecutadas(true);
			} else {
				expediente.setTieneTareasEjecutadas(false);
			}

		}
	}

	/**
	 * @param origen
	 * @param expediente
	 */
	private void asignarTecnico(String origen, Expediente expediente) {
		if (!TipoExpedienteEnum.INTERPRETACION.getValue().equals(expediente.getIdTipoExpediente())
				&& AccesoExpedienteEnum.ESTUDIO.getValue().equals(origen)
				&& (expediente.getTecnico() == null || StringUtils.isBlank(expediente.getTecnico().getDni()))) {
			// Se actualiza el técnico
			final Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication()
					.getCredentials();
			final Persona tecnico = new Persona();
			tecnico.setDni(credentials.getNif());
			tecnico.setNombre(credentials.getName());
			tecnico.setApellido1(credentials.getSurname());
			expediente.setTecnico(tecnico);

			this.expedienteDao.update(expediente);

			// Se actualiza la bitácora
			final BitacoraExpediente bExp = new BitacoraExpediente();
			bExp.setAnyo(expediente.getAnyo());
			bExp.setNumExp(expediente.getNumExp());

			final FasesExpediente fases = new FasesExpediente();
			fases.setId(Long.valueOf(FaseExpedienteEnum.ESTUDIO_EXPEDIENTE.getValue()));
			bExp.setFaseExp(fases);

			final EstadosExpediente estado = new EstadosExpediente();
			estado.setId(Long.valueOf(EstadoExpedienteEnum.EN_ESTUDIO.getValue()));
			bExp.setEstadoExp(estado);

			bExp.setIdMotivoRechazo(null);
			this.bitacoraExpedienteDao.add(bExp);

			// Se actualiza el estado del expediente
			expediente.setBitacoraExpediente(bExp);
			this.expedienteDao.modificarEstado(expediente);

			// Registrar accion
			final RegistroAcciones registroAcciones = new RegistroAcciones();
			registroAcciones.setIdPuntoMenu(PuntosMenuEnum.TRAMITACION_EXPEDIENTES.getValue());
			registroAcciones.setIdAccion(AccionesEnum.MODIFICACION.getValue());
			registroAcciones.setAnyo(expediente.getAnyo());
			registroAcciones.setNumExp(expediente.getNumExp());
			registroAcciones.setIdEstadoBitacora(expediente.getBitacoraExpediente().getIdEstadoBitacora());
			String[] tec = { credentials.getNif() };
			registroAcciones
					.setObserv(this.msg.getMessage("label.asignacionTecnico", tec, LocaleContextHolder.getLocale()));
			this.registroAccionesDao.add(registroAcciones);
		}
	}

	/**
	 * @param expediente
	 */
	private void calcularIndPresupuestoVisibleParaUsuario(Expediente expediente) {
		if (EstadoExpedienteEnum.EN_ESTUDIO.getValue() != expediente.getBitacoraExpediente().getEstadoExp().getId()) {
			if (TipoExpedienteEnum.INTERPRETACION.getValue().equals(expediente.getIdTipoExpediente())) {
				if (Constants.SI.equalsIgnoreCase(expediente.getExpedienteInterpretacion().getIndPresupuesto())) {
					expediente.setPresupuestoVisibleUsuario(this.tareasService.isVisiblePptoInterpretacion(expediente));
				} else {
					expediente.setPresupuestoVisibleUsuario(false);
				}
			} else {
				DatosTareaTrados datosTareaTrados = this.datosTareaTradosDao.findDatosTareaTrados(expediente);
				if (Constants.SI.equalsIgnoreCase(expediente.getExpedienteTradRev().getIndPresupuesto())
						&& datosTareaTrados != null
						&& Constants.SI.equalsIgnoreCase(datosTareaTrados.getIndVisible())) {
					expediente.setPresupuestoVisibleUsuario(true);
				} else {
					expediente.setPresupuestoVisibleUsuario(false);
				}
				if (datosTareaTrados != null && datosTareaTrados.getIdRequerimiento() != null) {
					expediente.setTradosConRequerimiento(true);
				} else {
					expediente.setTradosConRequerimiento(false);
				}
			}
		}
	}

	/**
	 * @param gestor
	 */
	private DatosContacto getDatosContactoGestor(GestorExpediente gestorExpediente) {
		DatosContacto datosContacto = null;
		if (gestorExpediente != null && gestorExpediente.getSolicitante() != null
				&& gestorExpediente.getSolicitante().getDatosContacto() != null) {
			datosContacto = new DatosContacto();
			datosContacto.setTelfijo031(gestorExpediente.getSolicitante().getDatosContacto().getTelfijo031());
			datosContacto.setTelmovil031(gestorExpediente.getSolicitante().getDatosContacto().getTelmovil031());
			datosContacto.setEmail031(gestorExpediente.getSolicitante().getDatosContacto().getEmail031());
		}

		return datosContacto;
	}

	/**
	 * @param expediente Expediente
	 */
	private void getObservacionesExp(Expediente expediente) {
		ObservacionesExpediente observacionesExpediente = this.observacionesExpedienteDao.observacionesFind(expediente);

		if (TipoExpedienteEnum.INTERPRETACION.getValue().equals(expediente.getIdTipoExpediente())
				&& expediente.getExpedienteInterpretacion() != null) {

			expediente.getExpedienteInterpretacion().setObservaciones(observacionesExpediente);

		} else if ((TipoExpedienteEnum.TRADUCCION.getValue().equals(expediente.getIdTipoExpediente())
				|| TipoExpedienteEnum.REVISION.getValue().equals(expediente.getIdTipoExpediente()))
				&& expediente.getExpedienteTradRev() != null) {

			expediente.getExpedienteTradRev().setObservaciones(observacionesExpediente);

		}
	}

	/**
	 * @param expediente Expediente
	 * @return ObservacionesExpediente
	 */

	@Override
	public ObservacionesExpediente observacionesFind(Expediente expediente) {
		ObservacionesExpediente observacionesExpediente = null;
		if (expediente != null) {
			observacionesExpediente = this.observacionesExpedienteDao.observacionesFind(expediente);
		}

		return observacionesExpediente;
	}

	/**
	 * @param bean     Expediente
	 * @param original Expediente
	 * @param locale   Locale
	 * @param String
	 */
	private String obtenerMensajeObservaciones(Expediente bean, Expediente original, Locale locale) {
		StringBuilder strObservaciones = new StringBuilder();
		if (original != null) {
			String strActual = this.obtenerObservaciones(bean);
			String strOriginal = this.obtenerObservaciones(original);
			if (!StringUtils.equals(strOriginal, strActual)) {
				strObservaciones.append(this.msg.getMessage(Constants.LABEL_OBSERVACIONES_MODIFICADAS, null, locale));
			}
		}

		return strObservaciones.toString();
	}

	/**
	 * @param expediente Expediente
	 * @return String
	 */
	private String obtenerObservaciones(Expediente expediente) {
		String observaciones = "";
		if (expediente != null) {
			if (TipoExpedienteEnum.INTERPRETACION.getValue().equals(expediente.getIdTipoExpediente())
					&& expediente.getExpedienteInterpretacion() != null
					&& expediente.getExpedienteInterpretacion().getObservaciones() != null) {
				observaciones = expediente.getExpedienteInterpretacion().getObservaciones().getObservaciones();
			} else if ((TipoExpedienteEnum.TRADUCCION.getValue().equals(expediente.getIdTipoExpediente())
					|| TipoExpedienteEnum.REVISION.getValue().equals(expediente.getIdTipoExpediente()))
					&& expediente.getExpedienteTradRev() != null
					&& expediente.getExpedienteTradRev().getObservaciones() != null) {
				observaciones = expediente.getExpedienteTradRev().getObservaciones().getObservaciones();
			}
		}
		return observaciones;
	}

	/**
	 * Obtiene el valor del indicador de las observaciones
	 *
	 * @param observaciones
	 * @return String con valor S/N
	 */
	private String obtenerIndObservaciones(ObservacionesExpediente observaciones) {
		String indObservaciones = Constants.NO;
		if (observaciones != null) {
			indObservaciones = Constants.SI;
		}

		return indObservaciones;
	}

}
