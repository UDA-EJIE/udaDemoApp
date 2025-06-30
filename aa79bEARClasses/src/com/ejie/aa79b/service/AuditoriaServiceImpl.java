package com.ejie.aa79b.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.dao.AuditoriaDao;
import com.ejie.aa79b.dao.AuditoriaDocumentosDao;
import com.ejie.aa79b.dao.DocumentosGeneralDao;
import com.ejie.aa79b.dao.GenericoDao;
import com.ejie.aa79b.dao.OidsAuxiliarDao;
import com.ejie.aa79b.dao.TiposTareaDao;
import com.ejie.aa79b.mail.MailService;
import com.ejie.aa79b.model.Auditoria;
import com.ejie.aa79b.model.AuditoriaCampoSeccionExpediente;
import com.ejie.aa79b.model.AuditoriaDocumentoSeccionExpediente;
import com.ejie.aa79b.model.AuditoriaSeccionExpediente;
import com.ejie.aa79b.model.DatosContacto;
import com.ejie.aa79b.model.DocumentoTareaAdjunto;
import com.ejie.aa79b.model.ParametrosEmail;
import com.ejie.aa79b.model.TiposTarea;
import com.ejie.aa79b.model.enums.ActivoEnum;
import com.ejie.aa79b.model.enums.AuditoriaTipoCampoEnum;
import com.ejie.aa79b.model.enums.AuditoriaTipoSeccionEnum;
import com.ejie.aa79b.model.enums.EstadoEnvioEmailEnum;
import com.ejie.aa79b.model.enums.TipoAvisoEnum;
import com.ejie.aa79b.model.enums.TipoSeccionAuditoriaEnum;
import com.ejie.aa79b.security.Usuario;
import com.ejie.aa79b.utils.TareasServiceUtils;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;

/**
 *
 * @author eaguirresarobe
 *
 */
@Service(value = "auditoriaService")
public class AuditoriaServiceImpl extends GenericoServiceImpl<Auditoria> implements AuditoriaService {

	@Autowired()
	private AuditoriaDao auditoriaDao;

	@Autowired()
	private AuditoriaDocumentosDao auditoriaDocumentosDao;

	@Autowired()
	private TiposTareaDao tiposTareasDao;

	@Autowired()
	private Properties appConfiguration;

	@Autowired()
	private DatosContactoService datosContactoService;

	@Autowired
	private DocumentosGeneralDao documentosGeneralDao;

	@Autowired()
	private OidsAuxiliarDao oidsAuxiliarDao;

	@Autowired()
	private MailService mailService;

	@Autowired()
	private ReloadableResourceBundleMessageSource messageSource;

	private static final Logger LOGGER = LoggerFactory.getLogger(AuditoriaServiceImpl.class);

	@Override
	protected GenericoDao<Auditoria> getDao() {
		return this.auditoriaDao;
	}

	@Override
	public JQGridResponseDto<Auditoria> filterAuditoria(Auditoria auditoriaFilter, JQGridRequestDto jqGridRequestDto,
			boolean startsWith) {

		List<Auditoria> listaT = this.auditoriaDao.filterAuditoria(auditoriaFilter, jqGridRequestDto, false, false);

		Long recordNum = this.auditoriaDao.filterAuditoriaCount(auditoriaFilter, false);

		return new JQGridResponseDto<Auditoria>(jqGridRequestDto, recordNum, listaT);
	}

	@Override
	public void getDatosDetalleAuditoria(Model model, Long anyo, Integer numExp, BigDecimal idTarea,
			BigDecimal idTareaAuditar) {
		Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication().getCredentials();
		model.addAttribute("usuarioConectado", credentials.getNif());
		Auditoria datosAuditoria = new Auditoria();
		datosAuditoria.setAnyo(anyo);
		datosAuditoria.setNumExp(numExp);
		datosAuditoria.setIdTarea(idTarea);
		datosAuditoria.setIdTareaAuditar(idTareaAuditar);
		// obtenemos el idAuditoria -- si no lo tiene, el PL lo crea
		Long idTablaC2 = this.llamarPLCrearEstructuraAuditoria(datosAuditoria);
		if (idTablaC2 != null && idTablaC2 > 0) {
			datosAuditoria.setIdAuditoria(idTablaC2);
			// obtenemos datos generales de auditoria
			datosAuditoria = this.getDatosGeneralesAuditoria(datosAuditoria);
			datosAuditoria.setIdTarea(idTarea);
			datosAuditoria.setIdTareaAuditar(idTareaAuditar);
			datosAuditoria.setAnyo(anyo);
			datosAuditoria.setNumExp(numExp);
			// obtenemos secciones de auditoria
			List<AuditoriaSeccionExpediente> lAuditoriaSeccionExpediente = this
					.getSeccionesExpedienteAuditoria(datosAuditoria);
			List<AuditoriaSeccionExpediente> lAuditoriaSeccionExpedienteMostrar = new ArrayList<AuditoriaSeccionExpediente>();
			if (lAuditoriaSeccionExpediente != null && !lAuditoriaSeccionExpediente.isEmpty()) {
				for (AuditoriaSeccionExpediente auditSecExp : lAuditoriaSeccionExpediente) {
					// obtenemos count de campos de seccion
					if (ActivoEnum.SI.getValue().equalsIgnoreCase(auditSecExp.getIndObservaciones())
							|| auditSecExp.getCamposSeccionCount() > 0
							|| auditSecExp.getTipoSeccion().equals(Integer.valueOf(TipoSeccionAuditoriaEnum.SECCION_DE_DOCUMENTOS.getValue()))) {
						// si la seccion tiene indObservaciones a 'S' o tiene campos, hay que mostrarlo
						// en pantalla
						lAuditoriaSeccionExpedienteMostrar.add(auditSecExp);
					}
				}
			}

			model.addAttribute("auditoriaSecciones", lAuditoriaSeccionExpedienteMostrar);
		}
		model.addAttribute("auditoriaDatos", datosAuditoria);

	}

	@Override
	public Auditoria getDatosBasicosAuditoria(BigDecimal idAuditoria) {
		Auditoria datosAuditoria = new Auditoria();
		datosAuditoria.setIdAuditoria(idAuditoria.longValue());
		// obtenemos datos generales de auditoria
		return this.getDatosGeneralesAuditoria(datosAuditoria);
	}

	@Override
	public JQGridResponseDto<AuditoriaCampoSeccionExpediente> filterCamposSeccion(
			AuditoriaSeccionExpediente seccionFilter, JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
		List<AuditoriaCampoSeccionExpediente> listaT = this.auditoriaDao.filterCamposSeccion(seccionFilter,
				jqGridRequestDto, false);

		Long recordNum = this.auditoriaDao.filterCamposSeccionCount(seccionFilter, false);

		return new JQGridResponseDto<AuditoriaCampoSeccionExpediente>(jqGridRequestDto, recordNum, listaT);
	}

	@Override
	public Long getCamposSeccionCount(AuditoriaSeccionExpediente auditSecExp) {
		return this.auditoriaDao.filterCamposSeccionCount(auditSecExp, false);
	}

	@Override
	public Auditoria guardarDatosAuditoria(Auditoria auditoria) {
		AuditoriaServiceImpl.LOGGER.info(
				"**************************************** GUARDAR AUDITORIA - INICIO ********************************************");
		auditoria = this.guardarDatosGeneralesAuditoria(auditoria);
		this.guardarDatosSeccionesYCampos(auditoria);
		if (ActivoEnum.SI.getValue().equalsIgnoreCase(auditoria.getIndEnviado())) {
			// obtenemos el tipo de tarea
			TiposTarea tarea = this.tiposTareasDao.getTipoTareaIdTarea(auditoria.getIdTareaAuditar());

			int rst = EstadoEnvioEmailEnum.OK.getValue();
			// enviamos aviso
			AuditoriaServiceImpl.LOGGER
					.info("************************** Proceso de envio de email - INICIO **************************");
			List<String> listaDestinatarios = new ArrayList<String>();

			if (this.appConfiguration.getProperty(Constants.APP_ENTORNO) != null) {
				// obtenemos lotes de tarea EMAIL_031 - 81 - 29
				List<DatosContacto> lDatosContacto = this.datosContactoService
						.getMailsProveedoresPorIdTarea(auditoria.getIdTareaAuditar());
				if (lDatosContacto != null && !lDatosContacto.isEmpty()) {
					for (final DatosContacto contacto : lDatosContacto) {
						listaDestinatarios.add(contacto.getEmail031());
					}
				}
				// cuerpo de mensaje numexp - titulo - tarea
				if (TareasServiceUtils.isNotEmptyLstDestinatarios(listaDestinatarios)) {
					AuditoriaServiceImpl.LOGGER
							.info("entorno: " + this.appConfiguration.getProperty(Constants.APP_ENTORNO));

					ParametrosEmail parametrosEmail = new ParametrosEmail();
					Locale localeEu = new Locale(Constants.LANG_EUSKERA);
					Map<String, String> infoEu = new LinkedHashMap<String, String>();
					Locale localeEs = new Locale(Constants.LANG_CASTELLANO);
					Map<String, String> infoEs = new LinkedHashMap<String, String>();
					parametrosEmail.setAnyoNumExpediente(auditoria.getAnyoNumExpConcatenado());
					infoEu.put(this.messageSource.getMessage(Constants.LABEL_NUM_EXP, null, localeEu),
							auditoria.getAnyoNumExpConcatenado() + Constants.GUION_CON_ESPACIOS
									+ this.messageSource.getMessage(Constants.LABEL_ID_TAREA, null, localeEu)
									+ Constants.DOS_PUNTOS + Constants.ESPACIO + auditoria.getIdTareaAuditar()
									+ Constants.GUION_CON_ESPACIOS + tarea.getDescEu015());
					infoEs.put(this.messageSource.getMessage(Constants.LABEL_NUM_EXP, null, localeEs),
							auditoria.getAnyoNumExpConcatenado() + Constants.GUION_CON_ESPACIOS
									+ this.messageSource.getMessage(Constants.LABEL_ID_TAREA, null, localeEs)
									+ Constants.DOS_PUNTOS + Constants.ESPACIO + auditoria.getIdTareaAuditar()
									+ Constants.GUION_CON_ESPACIOS + tarea.getDescEs015());
					parametrosEmail.setInfoEu(infoEu);
					parametrosEmail.setInfoEs(infoEs);
					try {
						this.mailService.sendMailWithParameters(TipoAvisoEnum.AUDITORIA_ENVIADA.getValue(),
								listaDestinatarios, parametrosEmail);
					} catch (Exception e) {
						AuditoriaServiceImpl.LOGGER.info("Error en el envío de email", e);
						rst = EstadoEnvioEmailEnum.ERROR.getValue();
					}
				} else {
					AuditoriaServiceImpl.LOGGER.info("No hay destinatarios", listaDestinatarios);
					rst = EstadoEnvioEmailEnum.SIN_DESTINATARIOS.getValue();
				}
			}
			AuditoriaServiceImpl.LOGGER
					.info("************************** Proceso de envio de email - FIN **************************");

			auditoria.setResulEmail(rst);
		}
		AuditoriaServiceImpl.LOGGER.info(
				"**************************************** GUARDAR AUDITORIA - FIN ********************************************");
		return auditoria;
	}

	@Override
	public Long llamarPLCrearEstructuraAuditoria(Auditoria auditoria) {
		return this.auditoriaDao.llamarPLCrearEstructuraAuditoria(auditoria);
	}


	@Override
	public JQGridResponseDto<AuditoriaDocumentoSeccionExpediente> filterDocumentosSeccion(
			AuditoriaDocumentoSeccionExpediente seccionFilter, JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
		List<AuditoriaDocumentoSeccionExpediente> listaT = this.auditoriaDocumentosDao.filterDocumentosSeccion(seccionFilter,
				jqGridRequestDto, false);

		Long recordNum = this.auditoriaDocumentosDao.filterDocumentosSeccionCount(seccionFilter, false);

		return new JQGridResponseDto<AuditoriaDocumentoSeccionExpediente>(jqGridRequestDto, recordNum, listaT);
	}

	@Override
	public Integer eliminarDocAuditoria(AuditoriaDocumentoSeccionExpediente docAuditoria) {
		docAuditoria = this.auditoriaDocumentosDao.obtenerDatosDocumentoAuditoria(docAuditoria);
		// si no fichero, devolvemos -1
		if (docAuditoria != null && StringUtils.isNotBlank(docAuditoria.getOid())) {
			// anyadimos oid a tabla temporal de eliminacion
			this.oidsAuxiliarDao.add(docAuditoria.getOid());
			// eliminamos registro de tabla C5
			this.auditoriaDocumentosDao.removeC5(docAuditoria.getIdFicheroInterno());
			// Borro registro de la 88 por IdFichero
			this.documentosGeneralDao.remove(docAuditoria.getIdFicheroInterno());

			return 1;
		}
		return -1;
	}

	@Override
	public AuditoriaDocumentoSeccionExpediente anyadirDocAuditoria(
			AuditoriaDocumentoSeccionExpediente documentoAuditoria) {
		DocumentoTareaAdjunto docTarea = new DocumentoTareaAdjunto();
		docTarea.setNombre(documentoAuditoria.getNombre());
		docTarea.setTitulo(documentoAuditoria.getTituloFichero());
		docTarea.setExtension(documentoAuditoria.getExtension());
		docTarea.setContentType(documentoAuditoria.getContentType());
		docTarea.setTamano(documentoAuditoria.getTamano());
		docTarea.setEncriptado(documentoAuditoria.getEncriptado());
		docTarea.setOid(documentoAuditoria.getOid());
		docTarea = this.documentosGeneralDao.add(docTarea);
		documentoAuditoria.setIdFicheroInterno(docTarea.getIdFichero());
		return this.auditoriaDocumentosDao.anyadirDocAuditoria(documentoAuditoria);
	}

	/**
	 *
	 * @param auditoria Auditoria
	 * @return Auditoria
	 */
	private Auditoria getDatosGeneralesAuditoria(Auditoria auditoria) {
		return this.auditoriaDao.getDatosGeneralesAuditoria(auditoria);
	}

	/**
	 *
	 * @param auditoria Auditoria
	 * @return List<AuditoriaSeccionExpediente>
	 */
	private List<AuditoriaSeccionExpediente> getSeccionesExpedienteAuditoria(Auditoria auditoria) {
		return this.auditoriaDao.getSeccionesExpedienteAuditoria(auditoria);
	}

	/**
	 *
	 * @param auditoria Auditoria
	 * @return Auditoria
	 */
	private Auditoria guardarDatosGeneralesAuditoria(Auditoria auditoria) {
		return this.auditoriaDao.guardarDatosGeneralesAuditoria(auditoria);
	}

	/**
	 *
	 * @param auditoria Auditoria
	 * @return Auditoria
	 */
	private Auditoria guardarDatosSeccionesYCampos(Auditoria auditoria) {
		for (AuditoriaSeccionExpediente seccion : auditoria.getlAuditoriaSeccionExpediente()) {
			if (AuditoriaTipoSeccionEnum.VALORACION.getValue().equals(seccion.getTipoSeccion())
					|| ActivoEnum.SI.getValue().equalsIgnoreCase(seccion.getIndObservaciones())) {
				this.auditoriaDao.guardarDatosSeccion(seccion);
			}
			for (AuditoriaCampoSeccionExpediente campo : seccion.getlCamposSeccion()) {
				if (AuditoriaTipoCampoEnum.VALORACION.getValue().equals(campo.getTipoCampo())
						|| AuditoriaTipoCampoEnum.CONDICION.getValue().equals(campo.getTipoCampo())) {
					this.auditoriaDao.guardarDatosCampo(campo);
				}
			}
		}
		return auditoria;
	}
}
