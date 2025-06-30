package com.ejie.aa79b.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.Reports;
import com.ejie.aa79b.common.exceptions.AppRuntimeException;
import com.ejie.aa79b.dao.Aa79bServicioItzulnetWsDao;
import com.ejie.aa79b.dao.ConfigDireccionEmailDao;
import com.ejie.aa79b.dao.DatosContactoDao;
import com.ejie.aa79b.dao.ExpedienteDao;
import com.ejie.aa79b.dao.ExpedientesRelacionadosDao;
import com.ejie.aa79b.dao.LibroRegistroDao;
import com.ejie.aa79b.dao.PropiedadDao;
import com.ejie.aa79b.mail.MailService;
import com.ejie.aa79b.model.Comunicaciones;
import com.ejie.aa79b.model.ConfigDireccionEmail;
import com.ejie.aa79b.model.ConfigLibroRegistro;
import com.ejie.aa79b.model.Contacto;
import com.ejie.aa79b.model.ContactoFacturacionExpediente;
import com.ejie.aa79b.model.DatosContacto;
import com.ejie.aa79b.model.DocumentosExpediente;
import com.ejie.aa79b.model.Entidad;
import com.ejie.aa79b.model.EntradaLibroRegistroSol;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.ExpedienteTradRev;
import com.ejie.aa79b.model.FicheroDocExp;
import com.ejie.aa79b.model.Gestor;
import com.ejie.aa79b.model.MotivosAnulacion;
import com.ejie.aa79b.model.ObservacionesExpediente;
import com.ejie.aa79b.model.ParametrosEmail;
import com.ejie.aa79b.model.Solicitante;
import com.ejie.aa79b.model.SrtLibroRegistro;
import com.ejie.aa79b.model.TiposDocumento;
import com.ejie.aa79b.model.enums.AccionBitacoraEnum;
import com.ejie.aa79b.model.enums.ClasificacionDocumentosEnum;
import com.ejie.aa79b.model.enums.EstadoExpedienteEnum;
import com.ejie.aa79b.model.enums.FaseExpedienteEnum;
import com.ejie.aa79b.model.enums.OrigenExpedienteEnum;
import com.ejie.aa79b.model.enums.RespuestaServicioWSEnum;
import com.ejie.aa79b.model.enums.TipoAvisoEnum;
import com.ejie.aa79b.model.enums.TipoExpedienteEnum;
import com.ejie.aa79b.model.webservices.Aa79bLibroRegistro;
import com.ejie.aa79b.model.webservices.servicios.AA79bDatosFacturacion;
import com.ejie.aa79b.model.webservices.servicios.AA79bDatosResponsable;
import com.ejie.aa79b.model.webservices.servicios.AA79bDatosSolicitante;
import com.ejie.aa79b.model.webservices.servicios.AA79bDatosSolicitanteSolicitud;
import com.ejie.aa79b.model.webservices.servicios.AA79bDocumento;
import com.ejie.aa79b.model.webservices.servicios.AA79bDocumentoApoyo;
import com.ejie.aa79b.model.webservices.servicios.AA79bDocumentoRevisar;
import com.ejie.aa79b.model.webservices.servicios.Aa79bNotasIZO;
import com.ejie.aa79b.model.webservices.servicios.Aa79bRespuesta;
import com.ejie.aa79b.model.webservices.servicios.Aa79bSolicitud;
import com.ejie.aa79b.model.webservices.servicios.Aa79bSolicitudComunicacion;
import com.ejie.aa79b.model.webservices.servicios.Aa79bSolicitudEliminar;
import com.ejie.aa79b.model.webservices.servicios.Aa79bSolicitudTramitagune;
import com.ejie.aa79b.utils.DateUtils;
import com.ejie.aa79b.utils.Utils;
import com.ejie.aa79b.utils.ValidationUtils;
import com.ejie.aa79b.webservices.LibroRegistroService;
import com.ejie.aa79b.webservices.PIDService;
import com.ejie.aa79b.webservices.X54JPermisosService;
import com.ejie.aa79b.webservices.x54j.X54JRespuestaAnadirRolWS;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.y31.vo.Y31AttachmentBean;

@Service(value = "aa79bServicioItzulnetWsService")
public class Aa79bServicioItzulnetWsServiceImpl implements Aa79bServicioItzulnetWsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(Aa79bServicioItzulnetWsServiceImpl.class);

	@Autowired()
	private ReloadableResourceBundleMessageSource msg;
	@Autowired
	private Aa79bServicioItzulnetWsDao aa79bSolicitudWsDao;
	@Autowired()
	private TiposDocumentoService tiposDocumentoService;
	@Autowired()
	private PropiedadService propiedadService;
	@Autowired()
	private SolicitanteService solicitanteService;
	@Autowired()
	private EntidadService entidadService;
	@Autowired()
	private X54JPermisosService x54JPermisosService;
	@Autowired()
	private ExpedienteTradRevService expedienteTradRevService;
	@Autowired()
	private PIDService pidService;
	@Autowired()
	private LibroRegistroService libroRegistroService;
	@Autowired
	private LibroRegistroDao libroRegistroDao;
	@Autowired
	private ConfigLibroRegistroService configLibroRegistroService;
	@Autowired
	private ExpedientesRelacionadosDao expedientesRelacionadosDao;
	@Autowired()
	private ReloadableResourceBundleMessageSource appMessageSource;
	@Autowired()
	private MailService mailService;
	@Autowired
	private DatosContactoDao datosContactoDao;
	@Autowired
	private ConfigDireccionEmailDao configDireccionEmailDao;
	@Autowired
	private ExpedienteDao expedienteDao;
	@Autowired
	private PropiedadDao propiedadDao;

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public Aa79bRespuesta altaSolicitud(Aa79bSolicitud bean) {
		Aa79bRespuesta respuesta = this.validarBeanAlta(bean, 0);
		if (respuesta.getCodigo() != 0) {
			return respuesta;
		}
		Expediente expediente = new Expediente();
		respuesta = this.parsearBean(bean, expediente, 0);
		String fechaHoraEntrega = "";
		if (respuesta.getCodigo() != 0) {
			return respuesta;
		} else {
			// recogemos una posible fecha de entrega calculada
			fechaHoraEntrega = respuesta.getFechaHoraEntrega();
		}
		this.aa79bSolicitudWsDao.addExpediente(expediente);
		this.aa79bSolicitudWsDao.addBitacoraExpediente(expediente, EstadoExpedienteEnum.ALTA_EXPEDIENTE,
				FaseExpedienteEnum.ALTA);
		this.aa79bSolicitudWsDao.addBitacoraExpediente(expediente, EstadoExpedienteEnum.EN_ESTUDIO,
				FaseExpedienteEnum.PENDIENTE_ESTUDIO);
		this.aa79bSolicitudWsDao.addExpedienteTradRev(expediente);
		this.aa79bSolicitudWsDao.addObservacionesExpediente(expediente);
		this.aa79bSolicitudWsDao.addGestorExpediente(expediente);
		this.aa79bSolicitudWsDao.addContactoFacturacionExpediente(expediente);
		this.aa79bSolicitudWsDao.addDocumentosExpediente(expediente);

		Map<String, String> parametros = new LinkedHashMap<String, String>();
		parametros.put("anyo", "label.anyo");
		parametros.put("numExp", "label.numeroExpediente");
		parametros.put("idTipoExpediente", "label.tipoExpediente");
		parametros.put("gestorExpediente.solicitante.dniCompleto", "label.dniGestor");
		parametros.put("gestorExpediente.entidad.codigo", "label.codigoEntidad");
		parametros.put("gestorExpediente.entidad.tipo", "label.tipoEntidad");

		this.aa79bSolicitudWsDao.generarSolicitud(expediente);
		// anyadir bitacora de solicitud a BBDD Tabla 79
		this.aa79bSolicitudWsDao.addBitacoraSolicitud(expediente, AccionBitacoraEnum.ALTA_EXP);

		final Solicitante solicitante = expediente.getGestorExpediente().getSolicitante();
		List<String> dnis = new ArrayList<String>();
		dnis.add(solicitante.getDni());
		List<ContactoFacturacionExpediente> contactosFacturacion = expediente.getContactosFacturacion();
		if (contactosFacturacion != null) {
			for (ContactoFacturacionExpediente contacto : contactosFacturacion) {
				String dni = contacto.getContacto().getDni();
				if (StringUtils.isNotBlank(dni)) {
					dnis.add(dni);
				}
			}
		}
		this.aa79bSolicitudWsDao.guardarTecnico(dnis);

		final String anioNumExp = Utils.getAnyoNumExpConcatenado(expediente.getAnyo(), expediente.getNumExp());
		final Long anioExp =  expediente.getAnyo();
		final Integer numExp = expediente.getNumExp();

		final String descripcionEus = this.msg.getMessage("registro.altaExpediente", new String[] { anioNumExp },
				new Locale(Constants.LANG_EUSKERA));
		final String descripcionCas = this.msg.getMessage("registro.altaExpediente", new String[] { anioNumExp },
				new Locale(Constants.LANG_CASTELLANO));

		// anyadir a la tabla 43
		this.aa79bSolicitudWsDao.addRegistroAcciones(expediente, null, Constants.ACCION_ALTA, parametros,
				"mensaje.nuevaSolicitud");

		if(StringUtils.isNotBlank(bean.getReferenciaExpediente())) {
			List<Expediente> listExp = this.aa79bSolicitudWsDao.getExpedientesRefAplic(expediente);
			if(listExp.size() > 0) {
				this.expedientesRelacionadosDao.addLista(listExp, expediente);
			}
		}



		class LibroRegistroLauncher implements Runnable {
	    	LibroRegistroService libroRegistroService;
	    	LibroRegistroDao libroRegistroDao;
	    	ConfigLibroRegistroService configLibroRegistroService;
	        LibroRegistroLauncher(LibroRegistroService libroRegistroService, LibroRegistroDao libroRegistroDao, ConfigLibroRegistroService configLibroRegistroService) {
	        	this.libroRegistroService = libroRegistroService;
	        	this.libroRegistroDao = libroRegistroDao;
	        	this.configLibroRegistroService = configLibroRegistroService;
	        }
	        @Override
			public void run() {
	        	try {
	        		ConfigLibroRegistro configLibroRegistro = new ConfigLibroRegistro();
	        		configLibroRegistro.setId(Constants.ID_DATOS_BASICOS);
	        		ConfigLibroRegistro configLibroRegistroAux = this.configLibroRegistroService.find(configLibroRegistro);
	        		if (configLibroRegistroAux != null && Constants.SI.equals(configLibroRegistroAux.getIndActivo())) {
		    			String senderId = solicitante.getDniCompleto();
		    			String senderName = solicitante.getNombreCompleto();
		    			SrtLibroRegistro libroRegistro = this.libroRegistroService.registrarEntrada(descripcionEus, descripcionCas,
		    					senderId, senderName);
		    			EntradaLibroRegistroSol entrada = new EntradaLibroRegistroSol();
		    			entrada.setAnyo(anioExp);
		    			entrada.setNumExp(numExp);
		    			Aa79bLibroRegistro libro = new Aa79bLibroRegistro();
		    			libro.setFechaRegistro(libroRegistro.getRegistryTimestamp().getTime());
		    			libro.setNumRegistro(libroRegistro.getRegistryNumber());
		    			libro.setMatter(descripcionEus);
		    			libro.setTelematico(Constants.SI);
		    			entrada.setLibroRegistro(libro);
		    			this.libroRegistroDao.setlibroRegistroSol(entrada);
	        		}
	    		} catch (Exception e) {
	    			LOGGER.info("Error llamando al registro telemático", e);
	    		}
	        }
	    }
	    Thread t = new Thread(new LibroRegistroLauncher(this.libroRegistroService, this.libroRegistroDao, this.configLibroRegistroService));
	    t.start();


		respuesta = new Aa79bRespuesta();
		respuesta.setCodigo(RespuestaServicioWSEnum.OK.getCodigo());
		respuesta.setNumeroExpedienteIZO(anioNumExp);
		respuesta.setReferenciaExpediente(bean.getReferenciaExpediente());
		respuesta.setFechaHoraEntrega(fechaHoraEntrega);

		return respuesta;
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public Aa79bRespuesta altaSolicitudTramitagune(Aa79bSolicitudTramitagune bean) {
		Aa79bSolicitud solicitud = this.cargarSolicitud(bean);
		Aa79bRespuesta respuesta = this.validarBeanAlta(solicitud, 1);
		if (respuesta.getCodigo() != 0) {
			return respuesta;
		}
		Expediente expediente = new Expediente();
		respuesta = this.parsearBean(solicitud, expediente, 1);
		String fechaHoraEntrega = "";
		if (respuesta.getCodigo() != 0) {
			return respuesta;
		} else {
			// recogemos una posible fecha de entrega calculada
			fechaHoraEntrega = respuesta.getFechaHoraEntrega();
		}
		this.aa79bSolicitudWsDao.addExpediente(expediente);
		this.aa79bSolicitudWsDao.addBitacoraExpediente(expediente, EstadoExpedienteEnum.ALTA_EXPEDIENTE,
				FaseExpedienteEnum.ALTA);
		this.aa79bSolicitudWsDao.addBitacoraExpediente(expediente, EstadoExpedienteEnum.EN_ESTUDIO,
				FaseExpedienteEnum.PENDIENTE_ESTUDIO);
		this.aa79bSolicitudWsDao.addExpedienteTradRev(expediente);
		this.aa79bSolicitudWsDao.addObservacionesExpediente(expediente);
		this.aa79bSolicitudWsDao.addGestorExpediente(expediente);
		this.aa79bSolicitudWsDao.addContactoFacturacionExpediente(expediente);
		this.aa79bSolicitudWsDao.addDocumentosExpediente(expediente);

		Map<String, String> parametros = new LinkedHashMap<String, String>();
		parametros.put("anyo", "label.anyo");
		parametros.put("numExp", "label.numeroExpediente");
		parametros.put("idTipoExpediente", "label.tipoExpediente");
		parametros.put("gestorExpediente.solicitante.dniCompleto", "label.dniGestor");
		parametros.put("gestorExpediente.entidad.codigo", "label.codigoEntidad");
		parametros.put("gestorExpediente.entidad.tipo", "label.tipoEntidad");

		this.aa79bSolicitudWsDao.generarSolicitud(expediente);
		// anyadir bitacora de solicitud a BBDD Tabla 79
		this.aa79bSolicitudWsDao.addBitacoraSolicitud(expediente, AccionBitacoraEnum.ALTA_EXP);

		final Solicitante solicitante = expediente.getGestorExpediente().getSolicitante();
		List<String> dnis = new ArrayList<String>();
		dnis.add(solicitante.getDni());
		List<ContactoFacturacionExpediente> contactosFacturacion = expediente.getContactosFacturacion();
		if (contactosFacturacion != null) {
			for (ContactoFacturacionExpediente contacto : contactosFacturacion) {
				String dni = contacto.getContacto().getDni();
				if (StringUtils.isNotBlank(dni)) {
					dnis.add(dni);
				}
			}
		}
		this.aa79bSolicitudWsDao.guardarTecnico(dnis);

		final String anioNumExp = Utils.getAnyoNumExpConcatenado(expediente.getAnyo(), expediente.getNumExp());
		final Long anioExp =  expediente.getAnyo();
		final Integer numExp = expediente.getNumExp();

		final String descripcionEus = this.msg.getMessage("registro.altaExpediente", new String[] { anioNumExp },
				new Locale(Constants.LANG_EUSKERA));
		final String descripcionCas = this.msg.getMessage("registro.altaExpediente", new String[] { anioNumExp },
				new Locale(Constants.LANG_CASTELLANO));

		// anyadir a la tabla 43
		this.aa79bSolicitudWsDao.addRegistroAcciones(expediente, null, Constants.ACCION_ALTA, parametros,
				"mensaje.nuevaSolicitud");

		if(StringUtils.isNotBlank(bean.getReferenciaExpediente())) {
			List<Expediente> listExp = this.aa79bSolicitudWsDao.getExpedientesRefAplic(expediente);
			if(listExp.size() > 0) {
				this.expedientesRelacionadosDao.addLista(listExp, expediente);
			}
		}



		class LibroRegistroLauncher implements Runnable {
			LibroRegistroService libroRegistroService;
			LibroRegistroDao libroRegistroDao;
			ConfigLibroRegistroService configLibroRegistroService;
			LibroRegistroLauncher(LibroRegistroService libroRegistroService, LibroRegistroDao libroRegistroDao, ConfigLibroRegistroService configLibroRegistroService) {
				this.libroRegistroService = libroRegistroService;
				this.libroRegistroDao = libroRegistroDao;
				this.configLibroRegistroService = configLibroRegistroService;
			}
			@Override
			public void run() {
				try {
					ConfigLibroRegistro configLibroRegistro = new ConfigLibroRegistro();
					configLibroRegistro.setId(Constants.ID_DATOS_BASICOS);
					ConfigLibroRegistro configLibroRegistroAux = this.configLibroRegistroService.find(configLibroRegistro);
					if (configLibroRegistroAux != null && Constants.SI.equals(configLibroRegistroAux.getIndActivo())) {
						String senderId = solicitante.getDniCompleto();
						String senderName = solicitante.getNombreCompleto();
						SrtLibroRegistro libroRegistro = this.libroRegistroService.registrarEntrada(descripcionEus, descripcionCas,
								senderId, senderName);
						EntradaLibroRegistroSol entrada = new EntradaLibroRegistroSol();
						entrada.setAnyo(anioExp);
						entrada.setNumExp(numExp);
						Aa79bLibroRegistro libro = new Aa79bLibroRegistro();
						libro.setFechaRegistro(libroRegistro.getRegistryTimestamp().getTime());
						libro.setNumRegistro(libroRegistro.getRegistryNumber());
						libro.setMatter(descripcionEus);
						libro.setTelematico(Constants.SI);
						entrada.setLibroRegistro(libro);
						this.libroRegistroDao.setlibroRegistroSol(entrada);
					}
				} catch (Exception e) {
					LOGGER.info("Error llamando al registro telemático", e);
				}
			}
		}
		Thread t = new Thread(new LibroRegistroLauncher(this.libroRegistroService, this.libroRegistroDao, this.configLibroRegistroService));
		t.start();


		respuesta = new Aa79bRespuesta();
		respuesta.setCodigo(RespuestaServicioWSEnum.OK.getCodigo());
		respuesta.setNumeroExpedienteIZO(anioNumExp);
		respuesta.setReferenciaExpediente(bean.getReferenciaExpediente());
		respuesta.setFechaHoraEntrega(fechaHoraEntrega);

		return respuesta;
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public Aa79bRespuesta eliminarExpediente(Aa79bSolicitudEliminar bean) {
		Aa79bRespuesta respuesta = this.validarBeanEliminacion(bean);
		if (respuesta.getCodigo() != 0) {
			return respuesta;
		}
		Expediente expediente = new Expediente();
		respuesta = this.parsearBeanEliminacion(bean, expediente);
		if (respuesta.getCodigo() != 0) {
			return respuesta;
		}
		String validarSolicitud = this.aa79bSolicitudWsDao.validarSolicitud(expediente);
		if (validarSolicitud.equals(Constants.BAJA)) {
			respuesta = this.parsearError(RespuestaServicioWSEnum.ELIMINADO);
		} else {
			Integer validarEliminacion = this.aa79bSolicitudWsDao.validarEliminacion(expediente);
			if (validarEliminacion.equals(Constants.UNO) || validarEliminacion.equals(Constants.DOS)) {
				this.aa79bSolicitudWsDao.eliminarSolicitud(expediente);
				respuesta = this.parsearError(RespuestaServicioWSEnum.ELIMINADO);
			}else if(validarEliminacion.equals(Constants.MINUS_UNO)) {
				respuesta = this.parsearError(RespuestaServicioWSEnum.ELIMINACION_NO_PERMITIDA);
			}else {
				enviarCorreo(expediente);
				respuesta = this.parsearError(RespuestaServicioWSEnum.NO_ELIMNADO);
			}
		}
		return respuesta;
	}

	private Aa79bSolicitud cargarSolicitud(Aa79bSolicitudTramitagune bean) {
		Aa79bSolicitud sol = new Aa79bSolicitud();
		sol.setCodigoAplicacion(bean.getCodigoAplicacion());
		sol.setReferenciaExpediente(bean.getReferenciaExpediente());
		if(bean.getDatosSolicitante() != null) {
			AA79bDatosSolicitanteSolicitud datosSol = new AA79bDatosSolicitanteSolicitud();
			datosSol.setNifSolicitante(bean.getDatosSolicitante().getNifSolicitante());
			datosSol.setTipoDocumento(bean.getDatosSolicitante().getTipoDocumento());
			datosSol.setPrefijoDocumento(bean.getDatosSolicitante().getPrefijoDocumento());
			datosSol.setSufijoDocumento(bean.getDatosSolicitante().getSufijoDocumento());
			sol.setDatosSolicitante(datosSol);
		}
		if(bean.getDatosFacturacion() != null) {
			AA79bDatosFacturacion datosFact = new AA79bDatosFacturacion();
			datosFact.setTipoDocumento(bean.getDatosFacturacion().getTipoDocumento());
			datosFact.setPrefijoDocumento(bean.getDatosFacturacion().getPrefijoDocumento());
			datosFact.setNifContacto(bean.getDatosFacturacion().getNifContacto());
			datosFact.setSufijoDocumento(bean.getDatosFacturacion().getSufijoDocumento());
			datosFact.setObservaciones(bean.getDatosFacturacion().getObservaciones());
			sol.setDatosFacturacion(datosFact);
		}
		sol.setTipoExpediente(bean.getTipoExpediente());
		sol.setTitulo(bean.getTitulo());
		sol.setPublicarBOPV(bean.getPublicarBOPV());
		sol.setIdioma(bean.getIdioma());
		sol.setDocumento(bean.getDocumento());
		sol.setDocumentoRevisar(bean.getDocumentoRevisar());
		sol.setDocumentoApoyo(bean.getDocumentoApoyo());
		sol.setFechaHoraEntregaSolicitada(bean.getFechaHoraEntregaSolicitada());
		sol.setNotasParaIZO(bean.getNotasParaIZO());
		sol.setAceptaLOPD(bean.getAceptaLOPD());
		return sol;
	}

	private Aa79bRespuesta validarBeanAlta(Aa79bSolicitud bean, int tipo) {
		Aa79bRespuesta respuesta = new Aa79bRespuesta();
		respuesta.setCodigo(RespuestaServicioWSEnum.OK.getCodigo());
		Boolean esBOPV = this.propiedadService.esAplicacionBOPV(bean.getCodigoAplicacion());
		List<String> errores = new ArrayList<String>();
		ValidationUtils.validarObligatorio(errores, bean, "codigoAplicacion");
		ValidationUtils.validarObligatorio(errores, bean, "referenciaExpediente");
		if (tipo != 1) {
			ValidationUtils.validarObligatorio(errores, bean, "datosSolicitante.tipoEntidad");
			ValidationUtils.validarObligatorio(errores, bean, "datosSolicitante.codigoEntidad");
		}
		ValidationUtils.validarObligatorio(errores, bean, "datosSolicitante.tipoDocumento");
		ValidationUtils.validarObligatorioIf(errores, bean, "datosSolicitante.prefijoDocumento",
				"datosSolicitante.tipoDocumento", "2");
		ValidationUtils.validarObligatorio(errores, bean, "datosSolicitante.nifSolicitante");
		ValidationUtils.validarObligatorioIf(errores, bean, "datosSolicitante.sufijoDocumento",
				"datosSolicitante.tipoDocumento", "1", "2");
		if (ValidationUtils.comprobarVacios(bean, "datosFacturacion.observaciones")) {
			if (tipo != 1) {
				ValidationUtils.validarObligatorio(errores, bean, "datosFacturacion.tipoEntidad");
				ValidationUtils.validarObligatorio(errores, bean, "datosFacturacion.codigoEntidad");
			}
			ValidationUtils.validarObligatorio(errores, bean, "datosFacturacion.tipoDocumento");
			ValidationUtils.validarObligatorioIf(errores, bean, "datosFacturacion.prefijoDocumento",
					"datosFacturacion.tipoDocumento", "2");
			ValidationUtils.validarObligatorio(errores, bean, "datosFacturacion.nifContacto");
			ValidationUtils.validarObligatorioIf(errores, bean, "datosFacturacion.sufijoDocumento",
					"datosFacturacion.tipoDocumento", "1", "2");
			if (ValidationUtils.comprobarVacios(bean, "datosFacturacion.tipoEntidad", "datosFacturacion.codigoEntidad",
					"datosFacturacion.tipoDocumento", "datosFacturacion.prefijoDocumento",
					"datosFacturacion.nifContacto", "datosFacturacion.sufijoDocumento")) {
				ValidationUtils.validarObligatorio(errores, bean, "datosFacturacion.observaciones");
			}
		}
		ValidationUtils.validarObligatorio(errores, bean, "tipoExpediente");
		ValidationUtils.validarObligatorio(errores, bean, "titulo");
		ValidationUtils.validarObligatorio(errores, bean, "publicarBOPV");
		ValidationUtils.validarObligatorio(errores, bean, "idioma");
		ValidationUtils.validarObligatorio(errores, bean, "documento.titulo");
		ValidationUtils.validarObligatorio(errores, bean, "documento.nombreFichero");
		ValidationUtils.validarObligatorio(errores, bean, "documento.rutaPIF");
		if (tipo == 1) {
			ValidationUtils.validarObligatorio(errores, bean, "documento.tipo");
			ValidationUtils.validarObligatorio(errores, bean, "documento.oidDokusi");
		}
		ValidationUtils.validarObligatorio(errores, bean, "documento.numeroTotalPalabras");
		if (tipo == 1) {
			ValidationUtils.validarObligatorioIf(errores, bean, "documentoRevisar",
					"tipoExpediente", "R");
			ValidationUtils.validarObligatorioIf(errores, bean, "documentoRevisar.nombreFichero",
					"tipoExpediente", "R");
			ValidationUtils.validarObligatorioIf(errores, bean, "documentoRevisar.rutaPIF",
					"tipoExpediente", "R");
			ValidationUtils.validarObligatorioIf(errores, bean, "documentoRevisar.oidDokusi",
					"tipoExpediente", "R");
		}
		// permitir a las aplicaciones de boletin enviar la peticion al servicio web sin fecha hora de entrega solicitada
		if (!esBOPV && tipo != 1) {
			ValidationUtils.validarObligatorio(errores, bean, "fechaHoraEntregaSolicitada");
		}
		ValidationUtils.validarObligatorio(errores, bean, "aceptaLOPD");

		if (!errores.isEmpty()) {
			respuesta = this.parsearError(RespuestaServicioWSEnum.ERROR_OBLIGATORIOS);
			respuesta.setInformacionAdicional(Utils.concatenarLista(errores, ", "));
		} else {
			respuesta = this.validarTipos(bean, tipo);
		}

		return respuesta;
	}

	private Aa79bRespuesta validarTipos(Aa79bSolicitud bean, int tipo) {
		Aa79bRespuesta respuesta = new Aa79bRespuesta();
		respuesta.setCodigo(RespuestaServicioWSEnum.OK.getCodigo());
		Boolean esBOPV = this.propiedadService.esAplicacionBOPV(bean.getCodigoAplicacion());
		List<String> errores = new ArrayList<String>();
		ValidationUtils.validarString(errores, bean, "codigoAplicacion", 5);
		ValidationUtils.validarString(errores, bean, "referenciaExpediente", 50);
		if(tipo != 1) {
			ValidationUtils.validarString(errores, bean, "datosSolicitante.tipoEntidad", 1);
			ValidationUtils.validarInteger(errores, bean, "datosSolicitante.codigoEntidad", 8);
		}
		ValidationUtils.validarInteger(errores, bean, "datosSolicitante.tipoDocumento", 1);
		ValidationUtils.validarString(errores, bean, "datosSolicitante.prefijoDocumento", 1);
		Boolean nifSolicitanteValido = ValidationUtils.validarString(errores, bean, "datosSolicitante.nifSolicitante",
				10);
		if (nifSolicitanteValido) {
			ValidationUtils.validarDni(errores, bean, "datosSolicitante.tipoDocumento",
					"datosSolicitante.prefijoDocumento", "datosSolicitante.nifSolicitante",
					"datosSolicitante.sufijoDocumento");
		}
		ValidationUtils.validarString(errores, bean, "datosSolicitante.sufijoDocumento", 1);
		if (tipo != 1) {
			ValidationUtils.validarString(errores, bean, "datosFacturacion.tipoEntidad", 1);
			ValidationUtils.validarInteger(errores, bean, "datosFacturacion.codigoEntidad", 8);
		}
		ValidationUtils.validarInteger(errores, bean, "datosFacturacion.tipoDocumento", 1);
		ValidationUtils.validarString(errores, bean, "datosFacturacion.prefijoDocumento", 1);
		Boolean nifContactoValido = ValidationUtils.validarString(errores, bean, "datosFacturacion.nifContacto", 10);
		if (nifContactoValido) {
			ValidationUtils.validarDni(errores, bean, "datosFacturacion.tipoDocumento",
					"datosFacturacion.prefijoDocumento", "datosFacturacion.nifContacto",
					"datosFacturacion.sufijoDocumento");
		}
		ValidationUtils.validarString(errores, bean, "datosFacturacion.sufijoDocumento", 1);
		ValidationUtils.validarString(errores, bean, "datosFacturacion.observaciones", 4000);
		Boolean tipoExpedienteValido = ValidationUtils.validarString(errores, bean, "tipoExpediente", 1);
		if (tipoExpedienteValido) {
			String tipoExpediente = bean.getTipoExpediente();
			if (!TipoExpedienteEnum.TRADUCCION.getValue().equals(tipoExpediente)
					&& !TipoExpedienteEnum.REVISION.getValue().equals(tipoExpediente)) {
				errores.add("tipoExpediente");
			}
		}
		ValidationUtils.validarString(errores, bean, "titulo", 150);
		ValidationUtils.validarString(errores, bean, "publicarBOPV", 1);
		ValidationUtils.validarInteger(errores, bean, "idioma", 2);
		ValidationUtils.validarString(errores, bean, "documento.titulo", 256);
		Boolean tipoValido = ValidationUtils.validarInteger(errores, bean, "documento.tipo", 2);
		if (tipoValido) {
			String documentoTipo = (String) Reports.retrieveObjectValue(bean, "documento.tipo");
			if (StringUtils.isNotBlank(documentoTipo)) {
				TiposDocumento tipoDocumento = new TiposDocumento();
				tipoDocumento.setId(Long.parseLong(documentoTipo));
				tipoDocumento.setEstado(Constants.ALTA);
				JQGridRequestDto jqGridRequestDto = new JQGridRequestDto();
				if ("ES".equalsIgnoreCase(LocaleContextHolder.getLocale().getLanguage())) {
					jqGridRequestDto.setSidx("ORDEN_042 ASC, DESC_ES_042");
				} else {
					jqGridRequestDto.setSidx("ORDEN_042 ASC, DESC_EU_042");
				}
				jqGridRequestDto.setSord("ASC");
				List<TiposDocumento> datos = this.tiposDocumentoService.findAll(tipoDocumento, jqGridRequestDto);
				if (datos.isEmpty()) {
					errores.add("documento.tipo");
				}
			}
		}
		ValidationUtils.validarString(errores, bean, "documento.nombreFichero", 250);
		if (tipo == 0) {
			ValidationUtils.validarString(errores, bean, "documento.rutaPIF", 500);
		} else if (tipo == 1) {
			ValidationUtils.validarRutaPIF(errores, bean, "documento.rutaPIF", 500);
		}
		ValidationUtils.validarString(errores, bean, "documento.datosResponsable.nombreApellidos", 150);
		ValidationUtils.validarEmail(errores, bean, "documento.datosResponsable.email", 256);
		ValidationUtils.validarInteger(errores, bean, "documento.datosResponsable.telefono", 15);
		ValidationUtils.validarString(errores, bean, "documento.datosResponsable.entidad", 150);
		ValidationUtils.validarString(errores, bean, "documento.datosResponsable.direccion", 256);
		ValidationUtils.validarString(errores, bean, "documento.numeroTotalPalabras", 6);
		ValidationUtils.validarString(errores, bean, "documentoRevisar.nombreFichero", 250);
		ValidationUtils.validarString(errores, bean, "documentoRevisar.rutaPIF", 500);
		ValidationUtils.validarString(errores, bean, "documentoRevisar.datosResponsable.nombreApellidos", 150);
		ValidationUtils.validarEmail(errores, bean, "documentoRevisar.datosResponsable.email", 256);
		ValidationUtils.validarInteger(errores, bean, "documentoRevisar.datosResponsable.telefono", 15);
		ValidationUtils.validarString(errores, bean, "documentoRevisar.datosResponsable.entidad", 150);
		ValidationUtils.validarString(errores, bean, "documentoRevisar.datosResponsable.direccion", 256);
		ValidationUtils.validarString(errores, bean, "documentoApoyo.titulo", 256);
		ValidationUtils.validarString(errores, bean, "documentoApoyo.nombreFichero", 250);
		ValidationUtils.validarString(errores, bean, "documentoApoyo.rutaPIF", 500);
		// permitir a las aplicaciones de boletin enviar la peticion al servicio web sin fecha hora de entrega solicitada
		if (!esBOPV && tipo != 1) {
			ValidationUtils.validarFechaHora(errores, bean, "fechaHoraEntregaSolicitada");
		}
		ValidationUtils.validarString(errores, bean, "notasParaIZO.notas", 4000);
		ValidationUtils.validarString(errores, bean, "notasParaIZO.nombreFichero", 250);
		ValidationUtils.validarString(errores, bean, "notasParaIZO.rutaPIF", 500);
		ValidationUtils.validarString(errores, bean, "aceptaLOPD", 1);

		if (!errores.isEmpty()) {
			respuesta = this.parsearError(RespuestaServicioWSEnum.ERROR_FORMATO);
			respuesta.setInformacionAdicional(Utils.concatenarLista(errores, ", "));
		}

		return respuesta;
	}

	private Aa79bRespuesta parsearBean(Aa79bSolicitud bean, Expediente expediente, int tipo) {
		expediente.setAplicacionOrigen(bean.getCodigoAplicacion());
		Aa79bRespuesta respuesta = new Aa79bRespuesta();
		respuesta.setCodigo(0);
		if ((tipo == 0 && bean.getCodigoAplicacion().equalsIgnoreCase(Constants.CONSTANTE_APLICACION_TRAMITAGUNE)) ||
			(tipo == 1 && !bean.getCodigoAplicacion().equalsIgnoreCase(Constants.CONSTANTE_APLICACION_TRAMITAGUNE))) {
			return this.parsearError(RespuestaServicioWSEnum.APLICACION_NO_PERMITIDA);
		}
		if (tipo == 1) {
			int numPersonas = this.aa79bSolicitudWsDao.comprobacionPersona(bean.getDatosSolicitante());
			if(numPersonas == 1) {
				if (!this.aa79bSolicitudWsDao.comprobacionSolicitante(bean.getDatosSolicitante())) {
					try {
						AA79bDatosSolicitante datosEntidadAux = this.aa79bSolicitudWsDao.getDatosEntidad(bean.getDatosSolicitante());
						bean.getDatosSolicitante().setCodigoEntidad(datosEntidadAux.getCodigoEntidad());
						bean.getDatosSolicitante().setTipoEntidad(datosEntidadAux.getTipoEntidad());
						X54JRespuestaAnadirRolWS x54JRespuestaAnadirRolWS = this.x54JPermisosService.anadirRol(bean);
						if ( null == x54JRespuestaAnadirRolWS ||  !x54JRespuestaAnadirRolWS.getCodigo().contentEquals( Constants.STRING_CERO)) {
							Aa79bServicioItzulnetWsServiceImpl.LOGGER.info("ERROR CONTROLADO AL ANADIR ROL");
							return this.parsearError(RespuestaServicioWSEnum.ERROR_ALTA_USUARIO_PERFIL_NUEVO);
						}
					} catch (Exception e) {
						Aa79bServicioItzulnetWsServiceImpl.LOGGER.info("ERROR NO CONTROLADO AL ANADIR ROL");
						return this.parsearError(RespuestaServicioWSEnum.ERROR_ALTA_USUARIO_PERFIL_NUEVO);
					}
				}
			}else if (numPersonas == 0){
				return this.parsearError(RespuestaServicioWSEnum.SOLICITANTE_NO_EXISTE);
			}else {
				return this.parsearError(RespuestaServicioWSEnum.ENTIDAD_NO_ENCONTRADA);
			}
		}
		Boolean esBOPV = this.propiedadService.esAplicacionBOPV(bean.getCodigoAplicacion());
		Solicitante solicitante = new Solicitante();
		solicitante.setDni(bean.getDatosSolicitante().getNifSolicitante());
		solicitante = this.solicitanteService.find(solicitante);
		if (tipo == 0) {
			if (solicitante == null) {
				if (esBOPV) {
					return this.parsearError(RespuestaServicioWSEnum.NO_ES_GESTOR_APLIC_BOPV);
				} else {
					return this.parsearError(RespuestaServicioWSEnum.NO_ES_GESTOR);
				}
			}
			if ( null != bean.getDatosSolicitante().getEsGrupoBoletin() && Constants.SI.contentEquals( bean.getDatosSolicitante().getEsGrupoBoletin() )){
				solicitante.setGrupoBoletin( bean.getDatosSolicitante().getEsGrupoBoletin() );
			}

			AA79bDatosSolicitanteSolicitud datosSolicitante = bean.getDatosSolicitante();
			Entidad entidad = solicitante.getEntidad();
			if (!(datosSolicitante.getTipoEntidad().equals(entidad.getTipo())
					&& datosSolicitante.getCodigoEntidad().equals(String.valueOf(entidad.getCodigo())))) {
				return this.parsearError(RespuestaServicioWSEnum.NO_PERTENECE_ENTIDAD);
			}

			if (esBOPV) {
				if (Constants.NO.equals(solicitante.getGestorExpedientesBOPV())) {
					this.x54JPermisosService.anadirRolBOPV(solicitante.getTipIden(), solicitante.getPreDni(),
							solicitante.getDni(), solicitante.getSufDni());
				}
			} else {
				Boolean publicarBOPV = Constants.SI.contentEquals(bean.getPublicarBOPV());
				if (publicarBOPV && Constants.NO.equals(solicitante.getGestorExpedientesBOPV())) {
					return this.parsearError(RespuestaServicioWSEnum.NO_ES_GESTOR_BOPV);
				} else if (!publicarBOPV && Constants.NO.equals(solicitante.getGestorExpedientes())
						&& Constants.NO.equals(solicitante.getGestorExpedientesVIP())) {
					return this.parsearError(RespuestaServicioWSEnum.NO_ES_GESTOR);
				}
			}
		}

		if (tipo == 1 && (bean.getDocumento().getTipo() == null || (!bean.getDocumento().getTipo().equalsIgnoreCase("1")
				&& !bean.getDocumento().getTipo().equalsIgnoreCase("2")))) {
			return this.parsearError(RespuestaServicioWSEnum.TIPO_DOCUMENTAL_NO_PERMITIDO);
		}
		expediente.setGestorExpediente(this.parsearBeanGestor(solicitante));

		String tipoExp = bean.getTipoExpediente();
		AA79bDocumento documento = bean.getDocumento();
		Integer numPalIzo = StringUtils.isNotBlank(documento.getNumeroTotalPalabras())
				? Integer.parseInt(documento.getNumeroTotalPalabras())
				: null;
		Date fechaEntregaSolicitada = DateUtils.formatearFechaWS(bean.getFechaHoraEntregaSolicitada());
		String indPresupuesto = Constants.NO;
		String indVip = solicitante.getGestorExpedientesVIP();
		Date fechaEntregaIZO = this.expedienteTradRevService.calcularFechaPropuesta(tipoExp, numPalIzo, indPresupuesto,
				indVip);

		if (esBOPV || tipo == 1) {
			if (fechaEntregaSolicitada == null) {
				respuesta.setFechaHoraEntrega(DateUtils.obtFechaFormateadaWS(fechaEntregaIZO));
			} else {
				respuesta.setFechaHoraEntrega(DateUtils.obtFechaFormateadaWS(fechaEntregaSolicitada));
				fechaEntregaIZO = fechaEntregaSolicitada;
			}
		} else {
			if (fechaEntregaSolicitada == null || fechaEntregaSolicitada.before(fechaEntregaIZO)) {
				respuesta.setFechaHoraEntrega(DateUtils.obtFechaFormateadaWS(fechaEntregaIZO));
			} else {
				respuesta.setFechaHoraEntrega(DateUtils.obtFechaFormateadaWS(fechaEntregaSolicitada));
				fechaEntregaIZO = fechaEntregaSolicitada;
			}
		}

		List<String> rutasErroneas = new ArrayList<String>();
		Y31AttachmentBean ficheroDocumento = descargarPIF(rutasErroneas, documento.getNombreFichero(),
				documento.getRutaPIF());
		AA79bDocumentoRevisar documentoRevisar = bean.getDocumentoRevisar();
		if (documentoRevisar == null) {
			documentoRevisar = new AA79bDocumentoRevisar();
		}
		Y31AttachmentBean ficheroDocumentoRevisar = descargarPIF(rutasErroneas, documentoRevisar.getNombreFichero(),
				documentoRevisar.getRutaPIF());
		AA79bDocumentoApoyo documentoApoyo = bean.getDocumentoApoyo();
		if (documentoApoyo == null) {
			documentoApoyo = new AA79bDocumentoApoyo();
		}
		Y31AttachmentBean ficheroDocumentoApoyo = descargarPIF(rutasErroneas, documentoApoyo.getNombreFichero(),
				documentoApoyo.getRutaPIF());
		Aa79bNotasIZO notasParaIZO = bean.getNotasParaIZO();
		if (notasParaIZO == null) {
			notasParaIZO = new Aa79bNotasIZO();
		}
		Y31AttachmentBean ficheroNotas = descargarPIF(rutasErroneas, notasParaIZO.getNombreFichero(),
				notasParaIZO.getRutaPIF());
		if (!rutasErroneas.isEmpty()) {
			respuesta = this.parsearError(RespuestaServicioWSEnum.ERROR_PIF);
			respuesta.setInformacionAdicional(Utils.concatenarLista(rutasErroneas, ", "));
			return respuesta;
		}

		expediente.setOrigen(OrigenExpedienteEnum.WEB_SERVICE.getValue());
		expediente.setFechaAlta(new Date());
		expediente.setContactosFacturacion(this.parsearContactosFacturacion(bean, expediente, tipo));
		expediente.setObsvFacturacion(bean.getDatosFacturacion().getObservaciones());
		expediente.setIdTipoExpediente(bean.getTipoExpediente());
		expediente.setTitulo(bean.getTitulo());
		expediente.setDocumentosExpediente(
				this.parsearDocumentos(bean, ficheroDocumento, ficheroDocumentoRevisar, ficheroDocumentoApoyo));
		expediente.setFechaEntregaFormateada(bean.getFechaHoraEntregaSolicitada());

		ExpedienteTradRev expedienteTradRev = new ExpedienteTradRev();
		expedienteTradRev.setNumTotalPalSolic(numPalIzo);
		expedienteTradRev.setFechaFinalSolic(fechaEntregaIZO);
		expedienteTradRev.setFechaFinalIzo(fechaEntregaIZO);
		expedienteTradRev.setFechaFinalProp(fechaEntregaIZO);
		expedienteTradRev.setRefTramitagune(bean.getReferenciaExpediente());
		expedienteTradRev.setIndPublicarBopv(bean.getPublicarBOPV());
		expedienteTradRev
				.setIdIdioma(StringUtils.isNotBlank(bean.getIdioma()) ? Long.parseLong(bean.getIdioma()) : null);
		ObservacionesExpediente observaciones = this.parsearObservaciones(bean, ficheroNotas);
		expedienteTradRev.setObservaciones(observaciones);
		if (StringUtils.isNotBlank(observaciones.getObservaciones())
				|| StringUtils.isNotBlank(observaciones.getRutaPif())) {
			expedienteTradRev.setIndObservaciones(Constants.SI);
		}
		expediente.setExpedienteTradRev(expedienteTradRev);
		return respuesta;
	}

	private Y31AttachmentBean descargarPIF(List<String> rutasErroneas, String nombre, String rutaPIF) {
		if (StringUtils.isNotBlank(rutaPIF)) {
			try {
				return this.pidService.prepararDocumentoPid(nombre, rutaPIF);
			} catch (Exception e) {
				rutasErroneas.add(rutaPIF);
				return null;
			}
		}
		return null;
	}

	private Aa79bRespuesta parsearError(RespuestaServicioWSEnum respuesta) {
		Aa79bRespuesta bean = new Aa79bRespuesta();
		bean.setCodigo(respuesta.getCodigo());
		bean.setDetalleCastellano(
				this.msg.getMessage(respuesta.getMensaje(), null, new Locale(Constants.LANG_CASTELLANO)));
		bean.setDetalleEuskera(this.msg.getMessage(respuesta.getMensaje(), null, new Locale(Constants.LANG_EUSKERA)));
		return bean;
	}

	private ObservacionesExpediente parsearObservaciones(Aa79bSolicitud bean, Y31AttachmentBean ficheroNotas) {
		Aa79bNotasIZO notas = bean.getNotasParaIZO();
		ObservacionesExpediente observaciones = new ObservacionesExpediente();
		if(notas!=null){
			observaciones.setObservaciones(notas.getNotas());
			if (StringUtils.isNotBlank(notas.getRutaPIF())) {
				observaciones.setNombre(notas.getNombreFichero());
				String oid;
				try {
					oid = this.pidService.addDocument(notas.getNombreFichero(), ficheroNotas.getFilePath());
				} catch (Exception e) {
					throw new AppRuntimeException(e);
				}
				observaciones.setOidFichero(oid);
				observaciones.setContentType(ficheroNotas.getContentType());
				observaciones.setExtension(Utils.getExtension(notas.getNombreFichero()));
				observaciones.setTamano(ficheroNotas.getSize());
			}
		}
		return observaciones;
	}

	private Gestor parsearBeanGestor(Solicitante solicitante) {
		Gestor gestorExpediente = new Gestor();
		gestorExpediente.setEntidad(solicitante.getEntidad());
		gestorExpediente.setSolicitante(solicitante);
		return gestorExpediente;
	}

	private List<ContactoFacturacionExpediente> parsearContactosFacturacion(Aa79bSolicitud bean,
			Expediente expediente, int tipo) {
		List<ContactoFacturacionExpediente> contactosFacturacion = new ArrayList<ContactoFacturacionExpediente>();
		ContactoFacturacionExpediente contactoFacturacion = new ContactoFacturacionExpediente();
		AA79bDatosFacturacion datosFacturacion = bean.getDatosFacturacion();
		Boolean encontrado = false;
		if (StringUtils.isNotBlank(datosFacturacion.getNifContacto())) {
			Solicitante contacto = new Solicitante();
			contacto.setDni(datosFacturacion.getNifContacto());
			contacto = this.solicitanteService.find(contacto);
			if (tipo == 0 && contacto != null) {
				Entidad entidad = contacto.getEntidad();
				if (datosFacturacion.getTipoEntidad().equals(entidad.getTipo())
						&& datosFacturacion.getCodigoEntidad().equals(String.valueOf(entidad.getCodigo()))) {
					contactoFacturacion.setEntidadSolicitante(entidad);
					contactoFacturacion.setContacto(contacto);
					encontrado = true;
				}
			}
		} else if (StringUtils.isNotBlank(datosFacturacion.getTipoEntidad())
				&& StringUtils.isNotBlank(datosFacturacion.getCodigoEntidad())) {
			Entidad entidad = new Entidad();
			entidad.setTipo(datosFacturacion.getTipoEntidad());
			entidad.setCodigo(Integer.parseInt(datosFacturacion.getCodigoEntidad()));
			entidad = this.entidadService.find(entidad);
			if (entidad != null) {
				contactoFacturacion.setEntidadSolicitante(entidad);
				contactoFacturacion.setContacto(new Solicitante());
				encontrado = true;
			}
		}
		if (!encontrado) {
			Gestor gestor = expediente.getGestorExpediente();
			contactoFacturacion.setEntidadSolicitante(gestor.getEntidad());
			contactoFacturacion.setContacto(gestor.getSolicitante());
		}
		contactoFacturacion.setPorFactura058(100L);
		contactosFacturacion.add(contactoFacturacion);
		return contactosFacturacion;
	}

	private List<DocumentosExpediente> parsearDocumentos(Aa79bSolicitud bean, Y31AttachmentBean ficheroDocumento,
			Y31AttachmentBean ficheroDocumentoRevisar, Y31AttachmentBean ficheroDocumentoApoyo) {
		List<DocumentosExpediente> documentosExpediente = new ArrayList<DocumentosExpediente>();
		AA79bDocumento doc = bean.getDocumento();
		DocumentosExpediente documento = new DocumentosExpediente();
		documento.setTitulo(doc.getTitulo());
		documento.setTipoDocumento(StringUtils.isNotBlank(doc.getTipo()) ? Long.parseLong(doc.getTipo()) : null);
		documento.setNumPalSolic(
				StringUtils.isNotBlank(doc.getNumeroTotalPalabras()) ? Integer.parseInt(doc.getNumeroTotalPalabras())
						: null);
		documento.setOidDokusi(doc.getOidDokusi());
		Boolean traduccion = TipoExpedienteEnum.TRADUCCION.getValue().equals(bean.getTipoExpediente());
		documento.setClaseDocumento(traduccion ? ClasificacionDocumentosEnum.TRADUCCION.getValue()
				: ClasificacionDocumentosEnum.REVISION.getValue());
		FicheroDocExp fichero = this.generarFichero(doc.getNombreFichero(), ficheroDocumento,
				doc.getDatosResponsable());
		documento.getFicheros().add(fichero);
		documentosExpediente.add(documento);

		AA79bDocumentoRevisar documentoRevisar = bean.getDocumentoRevisar();
		if (documentoRevisar != null && StringUtils.isNotBlank(documentoRevisar.getRutaPIF())) {
			FicheroDocExp fichero2 = this.generarFichero(documentoRevisar.getNombreFichero(), ficheroDocumentoRevisar,
					documentoRevisar.getDatosResponsable());
			documento.getFicheros().add(fichero2);

		}

		AA79bDocumentoApoyo documentoApoyo = bean.getDocumentoApoyo();
		if (documentoApoyo != null && StringUtils.isNotBlank(documentoApoyo.getRutaPIF())) {
			DocumentosExpediente documento2 = new DocumentosExpediente();
			documento2.setTitulo(documentoApoyo.getTitulo());
			documento2.setClaseDocumento(ClasificacionDocumentosEnum.APOYO.getValue());
			FicheroDocExp fichero2 = this.generarFichero(documentoApoyo.getNombreFichero(), ficheroDocumentoApoyo,
					null);
			documento2.getFicheros().add(fichero2);
			documentosExpediente.add(documento2);
		}

		return documentosExpediente;
	}

	private FicheroDocExp generarFichero(String nombre, Y31AttachmentBean ficheroDocumento,	AA79bDatosResponsable datosResponsable) {
		FicheroDocExp fichero = new FicheroDocExp();
		fichero.setNombre(nombre);
		String oidDocumento;
		try {
			oidDocumento = this.pidService.addDocument(nombre, ficheroDocumento.getFilePath());
		} catch (Exception e) {
			throw new AppRuntimeException(e);
		}
		fichero.setContentType(ficheroDocumento.getContentType());
		fichero.setExtension(Utils.getExtension(nombre));
		fichero.setTamano(ficheroDocumento.getSize());
		if (datosResponsable != null) {
			fichero.setContacto(this.parsearContacto(datosResponsable));
		}
		fichero.setOid(oidDocumento);
		return fichero;
	}

	private Contacto parsearContacto(AA79bDatosResponsable datosResponsable) {
		Contacto contacto = new Contacto();
		contacto.setPersona(datosResponsable.getNombreApellidos());
		contacto.setEmail(datosResponsable.getEmail());
		contacto.setTelefono(datosResponsable.getTelefono());
		contacto.setEntidad(datosResponsable.getEntidad());
		contacto.setDireccion(datosResponsable.getDireccion());
		return contacto;
	}

	private Aa79bRespuesta validarBeanEliminacion(Aa79bSolicitudEliminar bean) {
		Aa79bRespuesta respuesta = new Aa79bRespuesta();
		respuesta.setCodigo(RespuestaServicioWSEnum.OK.getCodigo());
		List<String> errores = new ArrayList<String>();
		ValidationUtils.validarObligatorio(errores, bean, "codigoAplicacion");
		ValidationUtils.validarObligatorio(errores, bean, "referenciaExpediente");
		ValidationUtils.validarObligatorio(errores, bean, "datosSolicitante.tipoDocumento");
		ValidationUtils.validarObligatorioIf(errores, bean, "datosSolicitante.prefijoDocumento",
				"datosSolicitante.tipoDocumento", "2");
		ValidationUtils.validarObligatorio(errores, bean, "datosSolicitante.nifSolicitante");
		ValidationUtils.validarObligatorioIf(errores, bean, "datosSolicitante.sufijoDocumento",
				"datosSolicitante.tipoDocumento", "1", "2");
		ValidationUtils.validarObligatorio(errores, bean, "numeroExpedienteIZO");
		ValidationUtils.validarObligatorio(errores, bean, "motivos");

		if (!errores.isEmpty()) {
			respuesta = this.parsearError(RespuestaServicioWSEnum.ERROR_OBLIGATORIOS);
			respuesta.setInformacionAdicional(Utils.concatenarLista(errores, ", "));
		} else {
			respuesta = this.validarTiposEliminacion(bean);
		}
		return respuesta;
	}

	private Aa79bRespuesta validarTiposEliminacion(Aa79bSolicitudEliminar bean) {
		Aa79bRespuesta respuesta = new Aa79bRespuesta();
		respuesta.setCodigo(RespuestaServicioWSEnum.OK.getCodigo());
		List<String> errores = new ArrayList<String>();
		ValidationUtils.validarString(errores, bean, "codigoAplicacion", 5);
		ValidationUtils.validarString(errores, bean, "referenciaExpediente", 50);
		ValidationUtils.validarInteger(errores, bean, "datosSolicitante.tipoDocumento", 1);
		ValidationUtils.validarString(errores, bean, "datosSolicitante.prefijoDocumento", 1);
		Boolean nifSolicitanteValido = ValidationUtils.validarString(errores, bean, "datosSolicitante.nifSolicitante",
				10);
		if (nifSolicitanteValido) {
			ValidationUtils.validarDni(errores, bean, "datosSolicitante.tipoDocumento",
					"datosSolicitante.prefijoDocumento", "datosSolicitante.nifSolicitante",
					"datosSolicitante.sufijoDocumento");
		}
		ValidationUtils.validarString(errores, bean, "datosSolicitante.sufijoDocumento", 1);
		ValidationUtils.validarString(errores, bean, "numeroExpedienteIZO", 10);
		ValidationUtils.validarString(errores, bean, "motivos", 4000);

		if (!errores.isEmpty()) {
			respuesta = this.parsearError(RespuestaServicioWSEnum.ERROR_FORMATO);
			respuesta.setInformacionAdicional(Utils.concatenarLista(errores, ", "));
		}

		return respuesta;
	}

	private Aa79bRespuesta parsearBeanEliminacion(Aa79bSolicitudEliminar bean, Expediente expediente) {
		Aa79bRespuesta respuesta = new Aa79bRespuesta();
		respuesta.setCodigo(0);

		expediente.setAplicacionOrigen(bean.getCodigoAplicacion());
		ExpedienteTradRev expedienteTradRev = new ExpedienteTradRev();
		expedienteTradRev.setRefTramitagune(bean.getReferenciaExpediente());
		expediente.setExpedienteTradRev(expedienteTradRev);
		Solicitante solicitante = new Solicitante();
		solicitante.setTipIden(Integer.parseInt(bean.getDatosSolicitante().getTipoDocumento()));
		solicitante.setPreDni(bean.getDatosSolicitante().getPrefijoDocumento());
		solicitante.setDni(bean.getDatosSolicitante().getNifSolicitante());
		solicitante.setSufDni(bean.getDatosSolicitante().getSufijoDocumento());
		expediente.setGestorExpediente(this.parsearBeanGestor(solicitante));
		respuesta = this.setAnyoNumExp(bean.getNumeroExpedienteIZO(), expediente);
//		expediente.setAnyoNumExpConcatenado(bean.getNumeroExpedienteIZO());
		MotivosAnulacion motivos = new MotivosAnulacion();
		motivos.setDescEs012(bean.getMotivos());
		expediente.setMotivosAnulacion(motivos);

		return respuesta;
	}

	private Aa79bRespuesta setAnyoNumExp(String anyoNumExp, Expediente expediente) {
		Aa79bRespuesta respuesta = new Aa79bRespuesta();
		respuesta.setCodigo(0);
		String[] aux = StringUtils.split(anyoNumExp, "/");
		if (aux.length == 2) {
			Integer i = 0;
			expediente.setAnyo(Long.parseLong("20" + aux[i++]));
			expediente.setNumExp(Integer.parseInt(aux[i++]));
		}else {
			respuesta = this.parsearError(RespuestaServicioWSEnum.ERROR_OBLIGATORIOS);
		}
		return respuesta;
	}

	private void enviarCorreo(Expediente expediente) {
		Aa79bServicioItzulnetWsServiceImpl.LOGGER.info("************************** Proceso de envio de email - INICIO **************************");
   		List<String> listaDestinatarios = new ArrayList<String>();
   		DatosContacto datosContacto = new DatosContacto();
    	datosContacto.setDni031(expediente.getGestorExpediente().getSolicitante().getDni());
    	datosContacto = this.datosContactoDao.findDatosContacto(datosContacto);
    	if (datosContacto != null && StringUtils.isNotBlank(datosContacto.getEmail031())) {
    		listaDestinatarios.add(datosContacto.getEmail031());
    	}else {
    		listaDestinatarios.add(
    				this.configDireccionEmailDao.find(new ConfigDireccionEmail(Constants.EMAIL_GENERAL_IZO)).getDirEmail());
    	}
    	ParametrosEmail parametrosEmail = new ParametrosEmail();
    	Locale localeEu = new Locale(Constants.LANG_EUSKERA);
    	Map<String, String> infoEu = new LinkedHashMap<String, String>();
    	Locale localeEs = new Locale(Constants.LANG_CASTELLANO);
    	Map<String, String> infoEs = new LinkedHashMap<String, String>();
    	parametrosEmail.setAnyoNumExpediente(expediente.getAnyoNumExpConcatenado());
    	infoEu.put(this.appMessageSource.getMessage(Constants.LABEL_NUM_EXP, null, localeEu),
    			Constants.ESPACIO + expediente.getAnyoNumExpConcatenado() + Constants.ESPACIO);
    	infoEu.put(this.appMessageSource.getMessage(Constants.LABEL_COD_APLIC, null, localeEu),
    			Constants.ESPACIO + expediente.getAplicacionOrigen());
    	infoEu.put(this.appMessageSource.getMessage(Constants.LABEL_OBSERVACIONES, null, localeEu),
    			Constants.ESPACIO + expediente.getMotivosAnulacion().getDescEs012());

    	infoEs.put(Constants.LABEL_NUM_EXP,
    			expediente.getAnyoNumExpConcatenado());
    	infoEs.put(Constants.LABEL_COD_APLIC,
    			expediente.getAplicacionOrigen());
    	infoEs.put(Constants.LABEL_OBSERVACIONES,
    			expediente.getMotivosAnulacion().getDescEs012());
    	parametrosEmail.setInfoEu(infoEu);
    	parametrosEmail.setInfoEs(infoEs);
    	try {
    		this.mailService.sendMailWithParametersEliminacion(TipoAvisoEnum.PETICION_ANULACION_EXPEDIENTE.getValue(),
    			listaDestinatarios, parametrosEmail);
    	} catch (Exception e) {
    		Aa79bServicioItzulnetWsServiceImpl.LOGGER.info("Error en el envío de email", e);
    	}
    	Aa79bServicioItzulnetWsServiceImpl.LOGGER.info("************************** Proceso de envio de email - FIN **************************");
	}



	@Override
	@Transactional(rollbackFor = Throwable.class)
	public Aa79bRespuesta altaComunicacion(Aa79bSolicitudComunicacion bean) {
		Aa79bRespuesta respuesta = this.validarBeanSolicitudComunicacion(bean);
		if (respuesta.getCodigo() != 0) {
			return respuesta;
		}
		Comunicaciones comunicacion = new Comunicaciones();
		Expediente expediente = new Expediente();
		respuesta = this.setAnyoNumExp(bean.getNumeroExpedienteIZO(), expediente);
		if (respuesta.getCodigo() != 0) {
			return respuesta;
		}
		expediente = this.expedienteDao.find(expediente);

		respuesta = this.parsearBeanSolicitudComunicacion(bean, comunicacion, expediente);
		if (respuesta.getCodigo() != 0) {
			return respuesta;
		}

		respuesta = this.validarComunicacion( bean, expediente );
		if (respuesta.getCodigo() != 0) {
			return respuesta;
		}

		this.aa79bSolicitudWsDao.addComunicacion(comunicacion);
		enviarCorreoAltaComunicacion( expediente );
		return respuesta;
	}

	private Aa79bRespuesta validarBeanSolicitudComunicacion(Aa79bSolicitudComunicacion bean) {
		Aa79bRespuesta respuesta = new Aa79bRespuesta();
		respuesta.setCodigo(RespuestaServicioWSEnum.OK.getCodigo());
		List<String> errores = new ArrayList<String>();
		ValidationUtils.validarObligatorio(errores, bean, "codigoAplicacion");
		ValidationUtils.validarObligatorio(errores, bean, "referenciaExpediente");
		ValidationUtils.validarObligatorio(errores, bean, "datosSolicitanteComunicacion.tipoDocumento");
		ValidationUtils.validarObligatorioIf(errores, bean, "datosSolicitanteComunicacion.prefijoDocumento","datosSolicitanteComunicacion.tipoDocumento", "2");
		ValidationUtils.validarObligatorio(errores, bean, "datosSolicitanteComunicacion.nifSolicitante");
		ValidationUtils.validarObligatorioIf(errores, bean, "datosSolicitanteComunicacion.sufijoDocumento",	"datosSolicitanteComunicacion.tipoDocumento", "1", "2");
		ValidationUtils.validarObligatorio(errores, bean, "numeroExpedienteIZO");
		ValidationUtils.validarObligatorio(errores, bean, "datosComunicacion.asunto");
		ValidationUtils.validarObligatorio(errores, bean, "datosComunicacion.texto");

		if (!errores.isEmpty()) {
			respuesta = this.parsearError(RespuestaServicioWSEnum.ERROR_OBLIGATORIOS);
			respuesta.setInformacionAdicional(Utils.concatenarLista(errores, ", "));
		} else {
			respuesta = this.validarTiposSolicitudComunicacion(bean);
		}
		return respuesta;
	}

	private Aa79bRespuesta validarTiposSolicitudComunicacion(Aa79bSolicitudComunicacion bean) {
		Aa79bRespuesta respuesta = new Aa79bRespuesta();
		respuesta.setCodigo(RespuestaServicioWSEnum.OK.getCodigo());
		List<String> errores = new ArrayList<String>();
		ValidationUtils.validarString(errores, bean, "codigoAplicacion", 5);
		ValidationUtils.validarString(errores, bean, "referenciaExpediente", 50);
		ValidationUtils.validarInteger(errores, bean, "datosSolicitanteComunicacion.tipoDocumento", 1);
		ValidationUtils.validarString(errores, bean, "datosSolicitanteComunicacion.prefijoDocumento", 1);
		Boolean nifSolicitanteValido = ValidationUtils.validarString(errores, bean, "datosSolicitanteComunicacion.nifSolicitante",10);
		if (nifSolicitanteValido) {
			ValidationUtils.validarDni(errores, bean, "datosSolicitanteComunicacion.tipoDocumento",
					"datosSolicitanteComunicacion.prefijoDocumento", "datosSolicitanteComunicacion.nifSolicitante",
					"datosSolicitanteComunicacion.sufijoDocumento");
		}
		ValidationUtils.validarString(errores, bean, "datosSolicitanteComunicacion.sufijoDocumento", 1);
		ValidationUtils.validarString(errores, bean, "numeroExpedienteIZO", 10);
		ValidationUtils.validarString(errores, bean, "datosComunicacion.asunto", 500);
		ValidationUtils.validarString(errores, bean, "datosComunicacion.texto", 4000);

		if (!errores.isEmpty()) {
			respuesta = this.parsearError(RespuestaServicioWSEnum.ERROR_FORMATO);
			respuesta.setInformacionAdicional(Utils.concatenarLista(errores, ", "));
		}
		return respuesta;
	}


	private Aa79bRespuesta parsearBeanSolicitudComunicacion(Aa79bSolicitudComunicacion bean, Comunicaciones comunicacion, Expediente expediente ) {
		Aa79bRespuesta respuesta = new Aa79bRespuesta();
		respuesta.setCodigo(0);

		comunicacion.setAnyo( expediente.getAnyo() );
		comunicacion.setNumExp( expediente.getNumExp() );
		comunicacion.setRefTramitagune( bean.getReferenciaExpediente() );
		comunicacion.setTipo(Constants.COMUNICACION_RECIBIDA);
		if ( null !=expediente.getGestorExpediente() && null != expediente.getGestorExpediente().getSolicitante()
				&& StringUtils.isNotEmpty( expediente.getGestorExpediente().getSolicitante().getNombreCompleto()) ) {
			comunicacion.setRemitente( expediente.getGestorExpediente().getSolicitante().getNombreCompleto() );
		}else {
			comunicacion.setRemitente( bean.getDatosSolicitanteComunicacion().getNifSolicitante() + bean.getDatosSolicitanteComunicacion().getSufijoDocumento() );
		}
		comunicacion.setAsunto( bean.getDatosComunicacion().getAsunto() );
		comunicacion.setMensaje( bean.getDatosComunicacion().getTexto() );

		if ( StringUtils.isNotEmpty( bean.getDatosComunicacion().getFichero() )) {
			//Hay fichero adjunto a la comunicacion

			int pos = bean.getDatosComunicacion().getFichero().lastIndexOf('/');
			String rutaPif =  bean.getDatosComunicacion().getFichero().substring(0, pos+1);
			String nombreFich =  bean.getDatosComunicacion().getFichero().substring(pos+1);

			List<String> rutasErroneas = new ArrayList<String>();
			Y31AttachmentBean ficheroDocumento = descargarPIF(rutasErroneas, nombreFich,rutaPif+nombreFich);

			FicheroDocExp fichero = this.generarFichero( nombreFich, ficheroDocumento,	null );

			comunicacion.setTitulo( FilenameUtils.removeExtension(fichero.getNombre()) );
			comunicacion.setNombre( fichero.getNombre() );
			comunicacion.setOidFichero( fichero.getOid() );
			comunicacion.setContentType( fichero.getContentType() );
			comunicacion.setExtension( fichero.getExtension() );
			comunicacion.setTamano( fichero.getTamano() );
			comunicacion.setEncriptado( Constants.NO );
		}

		return respuesta;
	}

	private Aa79bRespuesta validarComunicacion( Aa79bSolicitudComunicacion bean,  Expediente expediente) {

		Aa79bRespuesta respuesta = new Aa79bRespuesta();
		respuesta.setCodigo(0);

//		1-El expediente corresponde a la aplicacion y al solicitante.
//		2-Que la aplicacion esté entre las que tienen activada las notificaciones (ver punto 1).
//		Cuando no cumpla estos casos, se devolvera el error 110

		if ( !expediente.getAplicacionOrigen().contentEquals( bean.getCodigoAplicacion() )
				|| !expediente.getGestorExpediente().getSolicitante().getDni().equals( bean.getDatosSolicitanteComunicacion().getNifSolicitante())
				||  Boolean.FALSE.equals(this.propiedadDao.esAplicacionComunicaciones( expediente.getAplicacionOrigen() ))) {

			respuesta = this.parsearError(RespuestaServicioWSEnum.ERROR_VALIDACION_COMUNICACION);
		}
		return respuesta;
	}

	private void enviarCorreoAltaComunicacion(Expediente expediente) {
		Aa79bServicioItzulnetWsServiceImpl.LOGGER.info("************************** Proceso de envio de email Alta Comunicacion- INICIO **************************");
   		List<String> listaDestinatarios = new ArrayList<String>();
    	listaDestinatarios.add(	this.configDireccionEmailDao.find(new ConfigDireccionEmail(Constants.EMAIL_GENERAL_IZO)).getDirEmail());

    	ParametrosEmail parametrosEmail = new ParametrosEmail();
    	parametrosEmail.setAnyoNumExpediente(expediente.getAnyoNumExpConcatenado());
    	try {
    		this.mailService.sendMailWithParametersTipoAviso(TipoAvisoEnum.NUEVA_COMUNICACION_RECIBIDA.getValue(), listaDestinatarios, parametrosEmail);
    	} catch (Exception e) {
    		Aa79bServicioItzulnetWsServiceImpl.LOGGER.info("Error en el envío de email de Alta Comunicacion", e);
    	}
    	Aa79bServicioItzulnetWsServiceImpl.LOGGER.info("************************** Proceso de envio de email Alta Comunicacion - FIN **************************");
	}

}
