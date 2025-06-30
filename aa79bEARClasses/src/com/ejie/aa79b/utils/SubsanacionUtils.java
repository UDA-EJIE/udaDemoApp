package com.ejie.aa79b.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.dao.DatosContactoDao;
import com.ejie.aa79b.mail.MailService;
import com.ejie.aa79b.model.DatosContacto;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.GestorExpediente;
import com.ejie.aa79b.model.ParametrosEmail;
import com.ejie.aa79b.model.SubsanacionExpediente;
import com.ejie.aa79b.model.enums.EstadoExpedienteEnum;
import com.ejie.aa79b.model.enums.TipoAvisoEnum;
import com.ejie.aa79b.model.enums.TipoRequerimientoEnum;
import com.ejie.aa79b.service.ExpedienteService;

public class SubsanacionUtils extends SpringBeanAutowiringSupport {

	private static final Logger LOGGER = LoggerFactory.getLogger(SubsanacionUtils.class);

	protected static final String ACTIVO_ENUM = "ActivoEnum";
	protected static final String TO_CHAR_DATE_TYPE = "'HH24:MI'";

	@Autowired()
	private Properties appConfiguration;
	@Autowired
	private ExpedienteService expedienteService;
	@Autowired()
	private ReloadableResourceBundleMessageSource appMessageSource;
	@Autowired()
	private MailService mailService;
	@Autowired()
	private DatosContactoDao datosContactoDao;

	public SubsanacionUtils() {
		// Constructor
	}

	/**
	 * @param expedientes
	 * @param subsanacionExpediente
	 * @param gestor
	 * @param listaDestinatarios
	 */
	public void enviarEmailRequerirSubsanacion(Expediente expedientes, SubsanacionExpediente subsanacionExpediente,
			GestorExpediente gestor) {

		final List<String> listaDestinatarios = new ArrayList<String>();
		if (this.appConfiguration.getProperty(Constants.APP_ENTORNO) != null) {
			if (Constants.LOCAL.equals(this.appConfiguration.getProperty(Constants.APP_ENTORNO))) {
				listaDestinatarios.add(Constants.DESTINATARIO_EMAIL_LOCAL);
			} else {
				listaDestinatarios.add(gestor.getSolicitante().getDatosContacto().getEmail031());
			}

			Expediente expAux = new Expediente();
			expAux = this.expedienteService.find(expedientes);
			ParametrosEmail parametrosEmail = new ParametrosEmail();

			Locale localeEu = new Locale(Constants.LANG_EUSKERA);
			Map<String, String> infoEu = new LinkedHashMap<String, String>();
			Locale localeEs = new Locale(Constants.LANG_CASTELLANO);
			Map<String, String> infoEs = new LinkedHashMap<String, String>();

			infoEu.put(this.appMessageSource.getMessage(Constants.LABEL_NUM_EXP, null, localeEu),
					expedientes.getAnyoNumExpConcatenado() + Constants.GUION_CON_ESPACIOS + expAux.getTitulo());
			infoEs.put(this.appMessageSource.getMessage(Constants.LABEL_NUM_EXP, null, localeEs),
					expedientes.getAnyoNumExpConcatenado() + Constants.GUION_CON_ESPACIOS + expAux.getTitulo());

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			infoEu.put(this.appMessageSource.getMessage("label.fechaHoraLimiteSubsanacion", null, localeEu),
					subsanacionExpediente.getFechaHoraLimite());
			infoEs.put(this.appMessageSource.getMessage("label.fechaHoraLimiteSubsanacion", null, localeEs),
					dateFormat.format(subsanacionExpediente.getFechaLimite()) + Constants.ESPACIO
							+ subsanacionExpediente.getHoraLimite());

			infoEu.put(this.appMessageSource.getMessage("label.detalleSub", null, localeEu),
					subsanacionExpediente.getDetalle());
			infoEs.put(this.appMessageSource.getMessage("label.detalleSub", null, localeEs),
					subsanacionExpediente.getDetalle());

			parametrosEmail.setInfoEu(infoEu);
			parametrosEmail.setInfoEs(infoEs);

			parametrosEmail.setAnyoNumExpediente(Utils.getNumExpedienteParameter(expAux));

			if (!listaDestinatarios.isEmpty()) {
				this.mailService.sendMailWithParameters(TipoAvisoEnum.REQ_SUBSANACION.getValue(), listaDestinatarios,
						parametrosEmail);
			}
		}
	}

	/**
	 * @param expedientes
	 * @param subsanacionExpediente
	 * @param gestor
	 * @param listaDestinatarios
	 */
	public void enviarEmailSubsanacionRealizada(Expediente expedientes, SubsanacionExpediente subsanacionExpediente) {

		final List<String> listaDestinatarios = new ArrayList<String>();
		if (this.appConfiguration.getProperty(Constants.APP_ENTORNO) != null) {
			DatosContacto datosContacto = new DatosContacto();

			if (Constants.LOCAL.equals(this.appConfiguration.getProperty(Constants.APP_ENTORNO))) {
				listaDestinatarios.add(Constants.DESTINATARIO_EMAIL_LOCAL);
			} else {
				if (EstadoExpedienteEnum.EN_ESTUDIO.getValue() == expedientes.getBitacoraExpediente().getEstadoExp()
						.getId()) {
					// envio al técnico
					datosContacto.setDni031(expedientes.getTecnico().getDni());
				} else {
					// envio al asignador
					datosContacto.setDni031(expedientes.getAsignador().getDni());
				}
				datosContacto = this.datosContactoDao.findDatosContacto(datosContacto);
				if (datosContacto != null) {
					listaDestinatarios.add(datosContacto.getEmail031());
				}
			}

			if (TareasServiceUtils.isNotEmptyLstDestinatarios(listaDestinatarios)) {
				ParametrosEmail parametrosEmail = new ParametrosEmail();

				Locale localeEu = new Locale(Constants.LANG_EUSKERA);
				Map<String, String> infoEu = new LinkedHashMap<String, String>();
				Locale localeEs = new Locale(Constants.LANG_CASTELLANO);
				Map<String, String> infoEs = new LinkedHashMap<String, String>();

				infoEu.put(this.appMessageSource.getMessage(Constants.LABEL_NUM_EXP, null, localeEu),
						expedientes.getAnyoNumExpConcatenado() + Constants.GUION_CON_ESPACIOS
								+ expedientes.getTitulo());
				infoEs.put(this.appMessageSource.getMessage(Constants.LABEL_NUM_EXP, null, localeEs),
						expedientes.getAnyoNumExpConcatenado() + Constants.GUION_CON_ESPACIOS
								+ expedientes.getTitulo());

				infoEu.put(this.appMessageSource.getMessage("label.tipoRequerimiento", null, localeEu),
						this.appMessageSource.getMessage(TipoRequerimientoEnum.SUBSANACION.getLabel(), null, localeEu));
				infoEs.put(this.appMessageSource.getMessage("label.tipoRequerimiento", null, localeEs),
						this.appMessageSource.getMessage(TipoRequerimientoEnum.SUBSANACION.getLabel(), null, localeEs));

				parametrosEmail.setInfoEu(infoEu);
				parametrosEmail.setInfoEs(infoEs);

				parametrosEmail.setAnyoNumExpediente(Utils.getNumExpedienteParameter(expedientes));

				try {
					this.mailService.sendMailWithParameters(TipoAvisoEnum.REQUERIMIENTO_REALIZADO.getValue(),
							listaDestinatarios, parametrosEmail);
				} catch (Exception e) {
					SubsanacionUtils.LOGGER.info("Error en el envío de email", e);
				}
			} else {
				SubsanacionUtils.LOGGER.info("No sera informado via email al carecer de correo electronico.");
			}
		}
	}

	public void enviarEmailNegociarFecha(Expediente expFilter, SubsanacionExpediente subsanacionExpediente,
			GestorExpediente gestor) {
		final List<String> listaDestinatarios = new ArrayList<String>();
		if (this.appConfiguration.getProperty(Constants.APP_ENTORNO) != null) {
			if (Constants.LOCAL.equals(this.appConfiguration.getProperty(Constants.APP_ENTORNO))) {
				listaDestinatarios.add(Constants.DESTINATARIO_EMAIL_LOCAL);
			} else {
				listaDestinatarios.add(gestor.getSolicitante().getDatosContacto().getEmail031());
			}

			Expediente expAux = new Expediente();
			expAux = this.expedienteService.find(expFilter);
			ParametrosEmail parametrosEmail = new ParametrosEmail();

			Locale localeEu = new Locale(Constants.LANG_EUSKERA);
			Map<String, String> infoEu = new LinkedHashMap<String, String>();
			Locale localeEs = new Locale(Constants.LANG_CASTELLANO);
			Map<String, String> infoEs = new LinkedHashMap<String, String>();

			infoEu.put(this.appMessageSource.getMessage(Constants.LABEL_NUM_EXP, null, localeEu),
					expFilter.getAnyoNumExpConcatenado() + Constants.GUION_CON_ESPACIOS + expAux.getTitulo());
			infoEs.put(this.appMessageSource.getMessage(Constants.LABEL_NUM_EXP, null, localeEs),
					expFilter.getAnyoNumExpConcatenado() + Constants.GUION_CON_ESPACIOS + expAux.getTitulo());

			infoEu.put(this.appMessageSource.getMessage("label.tipoRequerimiento", null, localeEu),
					this.appMessageSource.getMessage("label.negociarNuevaFechaEntrega", null, localeEu));
			infoEs.put(this.appMessageSource.getMessage("label.tipoRequerimiento", null, localeEs),
					this.appMessageSource.getMessage("label.negociarNuevaFechaEntrega", null, localeEs));

			infoEu.put(this.appMessageSource.getMessage("label.nuevaFechaHoraEntrega", null, localeEu),
					DateUtils.obtFechaHoraFormateada(subsanacionExpediente.getFechaEntrega(),
							subsanacionExpediente.getHoraEntrega(), localeEu));
			infoEs.put(this.appMessageSource.getMessage("label.nuevaFechaHoraEntrega", null, localeEs),
					DateUtils.obtFechaHoraFormateada(subsanacionExpediente.getFechaEntrega(),
							subsanacionExpediente.getHoraEntrega(), localeEs));

			parametrosEmail.setInfoEu(infoEu);
			parametrosEmail.setInfoEs(infoEs);

			parametrosEmail.setAnyoNumExpediente(Utils.getNumExpedienteParameter(expAux));

			if (!listaDestinatarios.isEmpty()) {
				this.mailService.sendMailWithParameters(TipoAvisoEnum.REQ_FECHA_ENTREGA.getValue(), listaDestinatarios,
						parametrosEmail);
			}
		}

	}

}
