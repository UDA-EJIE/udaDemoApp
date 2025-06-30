package com.ejie.aa79b.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.dao.Aa79bSolicitudWsDao;
import com.ejie.aa79b.dao.BitacoraExpedienteDao;
import com.ejie.aa79b.dao.ConfigDireccionEmailDao;
import com.ejie.aa79b.dao.DatosPersonaDao;
import com.ejie.aa79b.dao.DocumentosGeneralDao;
import com.ejie.aa79b.dao.ExpedienteDao;
import com.ejie.aa79b.dao.ExpedienteTradRevDao;
import com.ejie.aa79b.dao.ExpedientesRelacionadosDao;
import com.ejie.aa79b.dao.GenericoDao;
import com.ejie.aa79b.dao.TareasDao;
import com.ejie.aa79b.mail.MailService;
import com.ejie.aa79b.model.BitacoraExpediente;
import com.ejie.aa79b.model.BitacoraSolicitante;
import com.ejie.aa79b.model.ConfigDireccionEmail;
import com.ejie.aa79b.model.DocumentoTareaAdjunto;
import com.ejie.aa79b.model.EstadosExpediente;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.ExpedienteTradRev;
import com.ejie.aa79b.model.FasesExpediente;
import com.ejie.aa79b.model.GestorExpediente;
import com.ejie.aa79b.model.ParametrosEmail;
import com.ejie.aa79b.model.Persona;
import com.ejie.aa79b.model.Solicitante;
import com.ejie.aa79b.model.SubsanacionExpediente;
import com.ejie.aa79b.model.enums.Aa06aAccionSolicitudEnum;
import com.ejie.aa79b.model.enums.AccionBitacoraEnum;
import com.ejie.aa79b.model.enums.EstadoExpedienteEnum;
import com.ejie.aa79b.model.enums.FaseExpedienteEnum;
import com.ejie.aa79b.model.enums.TipoAvisoEnum;
import com.ejie.aa79b.model.enums.TipoExpedienteEnum;
import com.ejie.aa79b.model.webservices.Aa79bRegistroExpediente;
import com.ejie.aa79b.model.webservices.Aa79bSolicitud;
import com.ejie.aa79b.utils.SubsanacionUtils;
import com.ejie.aa79b.utils.Utils;

@Service(value = "aa79bSolicitudWsService")
public class Aa79bSolicitudWsServiceImpl extends GenericoServiceImpl<Aa79bSolicitud>
		implements Aa79bSolicitudWsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(Aa79bSolicitudWsServiceImpl.class);

	@Autowired
	private Aa79bSolicitudWsDao aa79bSolicitudWsDao;
	@Autowired()
	private ExpedienteService expedienteService;
	@Autowired()
	private ExpedienteDao expedienteDao;
	@Autowired()
	private DatosPersonaDao datosPersonaDao;
	@Autowired()
	private SolicitanteService solicitanteService;
	@Autowired()
	private BitacoraExpedienteDao bitacoraExpedienteDao;
	@Autowired()
	private ReloadableResourceBundleMessageSource msg;
	@Autowired()
	private TareasDao tareasDao;
	@Autowired()
	private DocumentosGeneralDao documentosGeneralDao;
	@Autowired()
	private ExpedienteTradRevDao expedienteTradRevDao;
	@Autowired()
	private ExpedientesRelacionadosDao expedientesRelacionadosDao;
	@Autowired
	private ConfigDireccionEmailDao configDireccionEmailDao;
	@Autowired()
	private MailService mailService;

	@Override
	protected GenericoDao<Aa79bSolicitud> getDao() {
		return this.aa79bSolicitudWsDao;
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public Aa79bRegistroExpediente altaSolicitud(Aa79bSolicitud solicitud) {
		Aa79bRegistroExpediente registroExpediente = new Aa79bRegistroExpediente();
		if (solicitud != null) {
			// add gestor a 77 si no esta
			if (solicitud.getGestor() != null) {
				boolean esta = this.datosPersonaDao.comprobarPersona(solicitud.getGestor().getDniGestor());
				if (!esta) {
					Solicitante solicitante = new Solicitante();
					solicitante.setDni(solicitud.getGestor().getDniGestor());
					Solicitante solicitanteAInsertar = this.solicitanteService.find(solicitante);

					this.aa79bSolicitudWsDao.guardarTecnico(solicitanteAInsertar);
				}
			}
			// add en tabla 51
			Aa79bSolicitud solicitudCreada = this.aa79bSolicitudWsDao.add(solicitud);
			solicitud.setAnyo(solicitudCreada.getAnyo());
			solicitud.setNumExp(solicitudCreada.getNumExp());
			// add en tabla 69
			this.aa79bSolicitudWsDao.addSolicitud69(solicitud);

			BitacoraExpediente bitacoraExpediente = new BitacoraExpediente();
			bitacoraExpediente.setAnyo(solicitudCreada.getAnyo());
			bitacoraExpediente.setNumExp(solicitudCreada.getNumExp());
			EstadosExpediente estadoExp = new EstadosExpediente();
			estadoExp.setId((long) EstadoExpedienteEnum.ALTA_EXPEDIENTE.getValue());
			FasesExpediente faseExp = new FasesExpediente();
			faseExp.setId((long) FaseExpedienteEnum.ALTA.getValue());
			bitacoraExpediente.setEstadoExp(estadoExp);
			bitacoraExpediente.setFaseExp(faseExp);
			// bitacora inicial 1 59
			long idEstadoBitacora = this.aa79bSolicitudWsDao.addBitacoraExpediente(bitacoraExpediente);
			solicitud.setIdEstadoBitacora(idEstadoBitacora);

			Map<String, String> parametros = new LinkedHashMap<String, String>();
			parametros.put("anyo", "label.anyo");
			parametros.put("numExp", "label.numeroExpediente");
			parametros.put("tipoExpediente.codigo", "label.tipoExpediente");

			// anyadir gestor de expediente 54
			this.aa79bSolicitudWsDao.addGestorExpediente(solicitud, parametros);

			// anyadir a la tabla 43
			this.aa79bSolicitudWsDao.addRegistroAcciones(solicitud, null, Constants.ACCION_ALTA, parametros,
					"mensaje.nuevaSolicitud");

			if (TipoExpedienteEnum.TRADUCCION.getValue().equals(solicitud.getTipoExpediente().getCodigo())
					|| TipoExpedienteEnum.REVISION.getValue().equals(solicitud.getTipoExpediente().getCodigo())) {
				estadoExp.setId((long) EstadoExpedienteEnum.EN_ESTUDIO.getValue());
				faseExp.setId((long) FaseExpedienteEnum.PENDIENTE_ESTUDIO.getValue());
			} else if (TipoExpedienteEnum.INTERPRETACION.getValue().equals(solicitud.getTipoExpediente().getCodigo())) {
				estadoExp.setId((long) EstadoExpedienteEnum.EN_CURSO.getValue());
				faseExp.setId((long) FaseExpedienteEnum.PDTE_EJECT_TAREAS.getValue());
			}
			// bitacora inicial 2 59
			long idEstadoBitacoraExpediente = this.aa79bSolicitudWsDao.addBitacoraExpediente(bitacoraExpediente);

			// actualizar estado bitacora de expediente 51
			solicitud.setIdEstadoBitacora(idEstadoBitacoraExpediente);
			this.aa79bSolicitudWsDao.updateIdEstadoBitacora(solicitud);

			// anyadir bitacora de solicitud a BBDD Tabla 79
			this.aa79bSolicitudWsDao.addBitacoraSolicitud(solicitud, AccionBitacoraEnum.ALTA_EXP.getValue());

			// anyadir datos de expediente de traduccion/revision o
			// interpretacion
			if (TipoExpedienteEnum.TRADUCCION.getValue().equals(solicitud.getTipoExpediente().getCodigo())
					|| TipoExpedienteEnum.REVISION.getValue().equals(solicitud.getTipoExpediente().getCodigo())) {
				// anyadir a tabla 53 (y observaciones a 55 y a 72)
				this.aa79bSolicitudWsDao.addExpedienteTradRev(solicitud);
				// anyadir a la tabla 71
				this.aa79bSolicitudWsDao.addExpedienteTradRev71(solicitud);

				if(StringUtils.isNotBlank(solicitud.getDatosTradRev().getRefTramitagune())) {
					Expediente expediente = new Expediente();
					expediente.setAnyo(solicitud.getAnyo());
					expediente.setNumExp(solicitud.getNumExp());
					ExpedienteTradRev expTradRev = new ExpedienteTradRev();
					expTradRev.setRefTramitagune(solicitud.getDatosTradRev().getRefTramitagune());
					expediente.setExpedienteTradRev(expTradRev);
					Solicitante solicitante = new Solicitante();
					solicitante.setDni(solicitud.getSolicitante().getDni());
					GestorExpediente gestorExpediente = new GestorExpediente();
					gestorExpediente.setSolicitante(solicitante);
					expediente.setGestorExpediente(gestorExpediente);
					Persona tecnico = new Persona();
					expediente.setTecnico(tecnico);
					List<Expediente> listExp = this.aa79bSolicitudWsDao.getExpedientesRefAplic(expediente);
					if(listExp.size() > 0) {
						this.expedientesRelacionadosDao.addLista(listExp, expediente);
					}
				}

			} else if (TipoExpedienteEnum.INTERPRETACION.getValue().equals(solicitud.getTipoExpediente().getCodigo())) {
				// anyadir a tabla 52 (y observaciones a 55 y a 72)
				this.aa79bSolicitudWsDao.addExpedienteInterpretacion(solicitud);
				// anyadir a la tabla 70
				this.aa79bSolicitudWsDao.addExpedienteInterpretacion70(solicitud);

				BitacoraSolicitante bitacoraSolicitanteFilter = new BitacoraSolicitante();
				bitacoraSolicitanteFilter.setAnyo(solicitud.getAnyo());
				bitacoraSolicitanteFilter.setNumExp(solicitud.getNumExp());
				bitacoraSolicitanteFilter.setUsuario(Constants.IZO);
				this.bitacoraExpedienteDao.addBitacoraSolicitante(bitacoraSolicitanteFilter,
						AccionBitacoraEnum.EXP_EN_CURSO.getValue());
				//Envio de email de aviso de nuevo expediente de interpretacion
				enviarCorreoNuevoExpInterpretacion(solicitud);

			}

			// anyadir expedientes relacionados 57
			this.aa79bSolicitudWsDao.relacionarExpedientes(solicitud, DBConstants.TABLA_057);
			// anyadir a la tabla 74
			this.aa79bSolicitudWsDao.relacionarExpedientes(solicitud, DBConstants.TABLA_074);

			// anyadir documentos 56
			this.aa79bSolicitudWsDao.addDocumentosExpediente(solicitud);
			// anyadir a la tabla 73
			this.aa79bSolicitudWsDao.addDocumentosExpediente73(solicitud);

			registroExpediente.setAnyo(solicitud.getAnyo());
			registroExpediente.setNumExp(solicitud.getNumExp());
			registroExpediente.setEstado(Constants.ESTADO_WS_OK);
		}

		return registroExpediente;
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public Aa79bRegistroExpediente modificarSolicitud(Aa79bSolicitud solicitud) {
		Aa79bRegistroExpediente registroExpediente = new Aa79bRegistroExpediente();

		boolean subsanar = Utils.comprobarAccion(solicitud.getAccionSolicitud(),
				Aa06aAccionSolicitudEnum.SUBSANAR.getLabel());
		if (solicitud != null) {
			Map<String, String> parametros = new LinkedHashMap<String, String>();
			parametros.put("anyo", "label.anyo");
			parametros.put("numExp", "label.numeroExpediente");
			parametros.put("tipoExpediente.codigo", "label.tipoExpediente");
			parametros.put("solicitante.dni", "label.dniTecnico");

			if (Utils.comprobarAccion(solicitud.getAccionSolicitud(),
					Aa06aAccionSolicitudEnum.DOCUMENTOS_CON_VERSION.getLabel())) {
				// documentos con version
				this.aa79bSolicitudWsDao.updateDocumentosExpediente(solicitud, false);
				this.aa79bSolicitudWsDao.updateExpedienteTradRevNumTotalPalSolic(solicitud);
			} else {
				// modificar o subsanar
				// add en tabla 51
				this.aa79bSolicitudWsDao.update(solicitud);
				// add en tabla 69
				this.aa79bSolicitudWsDao.updateSolicitud69(solicitud, subsanar);

				// anyadir bitacora de solicitud a BBDD Tabla 79
				this.aa79bSolicitudWsDao.addBitacoraSolicitud(solicitud,
						subsanar ? AccionBitacoraEnum.SUBS_APORT_CLIENTE.getValue()
								: AccionBitacoraEnum.MODIFICAR_EXP.getValue());

				// anyadir datos de expediente de traduccion/revision o
				// interpretacion
				if (TipoExpedienteEnum.TRADUCCION.getValue().equals(solicitud.getTipoExpediente().getCodigo())
						|| TipoExpedienteEnum.REVISION.getValue().equals(solicitud.getTipoExpediente().getCodigo())) {
					// anyadir a tabla 53 (y observaciones a 55 y a 72)
					this.aa79bSolicitudWsDao.updateExpedienteTradRev(solicitud, subsanar);
					// anyadir a la tabla 71
					this.aa79bSolicitudWsDao.updateExpedienteTradRev71(solicitud, subsanar);

				} else if (TipoExpedienteEnum.INTERPRETACION.getValue()
						.equals(solicitud.getTipoExpediente().getCodigo())) {
					// anyadir a tabla 52 (y observaciones a 55 y a 72)
					this.aa79bSolicitudWsDao.updateExpedienteInterpretacion(solicitud, subsanar);
					// anyadir a la tabla 70
					this.aa79bSolicitudWsDao.updateExpedienteInterpretacion70(solicitud, subsanar);
				}

				// anyadir expedientes relacionados 57
				this.aa79bSolicitudWsDao.updateExpedientesRelacionados(solicitud, DBConstants.TABLA_057, false);
				// anyadir a la tabla 74
				this.aa79bSolicitudWsDao.updateExpedientesRelacionados(solicitud, DBConstants.TABLA_074, subsanar);

				// anyadir documentos 56 y anyadir a la tabla 73
				List<BigDecimal> listaIdDocsNuevosSubsanacion = this.aa79bSolicitudWsDao
						.updateDocumentosExpediente(solicitud, subsanar);

				// Actualiz datos T53
				if (!subsanar) {
					this.expedienteTradRevDao.procRecalcularCamposDocumentacionPL(solicitud.getAnyo(),
							solicitud.getNumExp());
				}

				// si subsanacion anyadir 064
				if (subsanar) {
					this.accionesSubsanar(solicitud, listaIdDocsNuevosSubsanacion);
				}
			}

			// anyadir a la tabla 43
			this.aa79bSolicitudWsDao.addRegistroAcciones(solicitud, null, Constants.ACCION_MODIFICACION, parametros,
					"mensaje.solicitudModificada");

			registroExpediente.setAnyo(solicitud.getAnyo());
			registroExpediente.setNumExp(solicitud.getNumExp());
			registroExpediente.setEstado(Constants.ESTADO_WS_OK);

		}
		return registroExpediente;
	}

	private void accionesSubsanar(Aa79bSolicitud solicitud, List<BigDecimal> listaIdDocsNuevosSubsanacion) {
		Expediente expediente = new Expediente();
		expediente.setAnyo(solicitud.getAnyo());
		expediente.setNumExp(solicitud.getNumExp());
		Expediente newExpediente = this.expedienteService.find(expediente);
		SubsanacionExpediente detalleSub = this.expedienteService.getDetalleSubsanacion(newExpediente);
		this.expedienteService.updateIndSubsanacion(detalleSub);

		// Añadir a la T83 los nuevos documentos aportados en la subsamnación
		if (!listaIdDocsNuevosSubsanacion.isEmpty()) {
			BigDecimal idTareaSubsanacion = this.tareasDao.findIdTareaPorRequerimiento(detalleSub.getId());
			if (idTareaSubsanacion.compareTo(new BigDecimal(0)) > 0) {
				DocumentoTareaAdjunto documentoTareaAdjunto = new DocumentoTareaAdjunto();
				documentoTareaAdjunto.setIdTarea(idTareaSubsanacion);
				for (BigDecimal unIdDoc : listaIdDocsNuevosSubsanacion) {
					documentoTareaAdjunto.setIdDocOriginal(unIdDoc);
					this.documentosGeneralDao.add83(documentoTareaAdjunto);
				}
			}
		}

		// obtenemos el estado y fase del expediente para anyadir una entrada en
		// la bitacora con el mismo estado y fase
		BitacoraExpediente bitacoraExpediente = new BitacoraExpediente();
		bitacoraExpediente = this.expedienteDao.findEstadoFaseExpediente(solicitud.getAnyo(), solicitud.getNumExp());

		BitacoraExpediente bExp = new BitacoraExpediente();

		bExp.setAnyo(newExpediente.getAnyo());
		bExp.setNumExp(newExpediente.getNumExp());

		FasesExpediente fases = new FasesExpediente();
		fases.setId(bitacoraExpediente.getFaseExp().getId());
		bExp.setFaseExp(fases);

		EstadosExpediente estado = new EstadosExpediente();
		estado.setId(bitacoraExpediente.getEstadoExp().getId());
		bExp.setEstadoExp(estado);

		Locale locale = new Locale(Constants.LANG_EUSKERA);
		bExp.setDatoAdic(this.msg.getMessage("label.subRealizada", null, locale));
		bExp.setIdMotivoRechazo(null);

		SubsanacionExpediente subExp = new SubsanacionExpediente();
		subExp.setId(newExpediente.getBitacoraExpediente().getSubsanacionExp().getId());
		bExp.setSubsanacionExp(subExp);
		// anyadimos la bitacora y obtenemos el idestadobitacora
		bExp = this.bitacoraExpedienteDao.add(bExp);

		newExpediente.setBitacoraExpediente(bExp);

		this.expedienteDao.modificarEstado(newExpediente);

		SubsanacionUtils subsanacionUtils = new SubsanacionUtils();
		subsanacionUtils.enviarEmailSubsanacionRealizada(newExpediente, detalleSub);
	}

	@Override
	public int addBitacoraSolicitante(BitacoraSolicitante bitacoraSolicitante) {
		return this.aa79bSolicitudWsDao.addBitacoraSolicitante(bitacoraSolicitante);
	}

	private void enviarCorreoNuevoExpInterpretacion(Aa79bSolicitud solicitud) {
		Aa79bSolicitudWsServiceImpl.LOGGER.info("************************** Proceso de envio de email nuevo expediente interpretacion WS- INICIO **************************");
   		List<String> listaDestinatarios = new ArrayList<String>();

   		//Recupero y proceso los destinatarios
   		String emails = this.configDireccionEmailDao.find(new ConfigDireccionEmail(Constants.EMAIL_RESPONSABLES_INTERPRETACION)).getDirEmail();
   		String[] aEmails = emails.split(Constants.COMA);
   		for (String sEmail : aEmails) {
   			listaDestinatarios.add( sEmail.trim() );
   		}
    	ParametrosEmail parametrosEmail = new ParametrosEmail();
    	parametrosEmail.setAnyoNumExpediente( Utils.getAnyoNumExpConcatenado(solicitud.getAnyo(), solicitud.getNumExp()) );

    	try {
    		this.mailService.sendMailWithParametersTipoAviso(TipoAvisoEnum.NUEVO_EXPEDIENTE_INTERPRETACION.getValue(), listaDestinatarios, parametrosEmail);
    	} catch (Exception e) {
    		Aa79bSolicitudWsServiceImpl.LOGGER.info("Error en el envío de email nuevo expediente interpretacion WS", e);
    	}
    	Aa79bSolicitudWsServiceImpl.LOGGER.info("************************** Proceso de envio de email nuevo expediente interpretacion WS - FIN **************************");
	}

}
