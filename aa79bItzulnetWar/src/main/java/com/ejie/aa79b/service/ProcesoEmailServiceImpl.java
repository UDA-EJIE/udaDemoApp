package com.ejie.aa79b.service;

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

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.dao.GenericoDao;
import com.ejie.aa79b.dao.ProcesoEmailDao;
import com.ejie.aa79b.mail.MailService;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.ParametrosEmail;
import com.ejie.aa79b.model.ProcesoEmail;
import com.ejie.aa79b.utils.Utils;




@Service(value = "procesoEmailService")
public class ProcesoEmailServiceImpl extends GenericoServiceImpl<ProcesoEmail> implements ProcesoEmailService {

	private static final String LABEL_TAREA = "label.tarea";
	private static final Logger LOGGER = LoggerFactory.getLogger(ProcesoEmailServiceImpl.class);
	private static final String ERROR_ENVIO_EMAIL = "Error en el envío de email";

	@Autowired()
	private ProcesoEmailDao procesoEmailDao;

	@Autowired()
	private MailService mailService;

	@Override
	protected GenericoDao<ProcesoEmail> getDao() {
		return this.procesoEmailDao;
	}

	@Autowired()
	private ReloadableResourceBundleMessageSource msg;

	@Override
	public void enviarMail(ProcesoEmail bean) {

		List<ProcesoEmail> listaProcesos = this.procesoEmailDao.findProcesoEmail(bean);

		String emailProcesando = "";

		Locale localeEu = new Locale(Constants.LANG_EUSKERA);
		Map<String, String> infoEu = null;
		Locale localeEs = new Locale(Constants.LANG_CASTELLANO);
		Map<String, String> infoEs = null;
		Expediente expediente = null;

		StringBuilder descTareasEs = null;
		StringBuilder descTareasEu = null;

		ParametrosEmail parametrosEmail = null;

		for (ProcesoEmail proceso : listaProcesos) {
			String emailAux = proceso.getAnyo().toString() + proceso.getNumExp() + "_" + proceso.getIdTipoAviso() + "_"
					+ proceso.getDniDestinatario();

			if (emailAux.equals(emailProcesando)) {
				descTareasEu.append(Constants.SALTO_DE_LINEA);
				descTareasEs.append(Constants.SALTO_DE_LINEA);

				descTareasEu.append(this.msg.getMessage(LABEL_TAREA, null, localeEu)).append(" ");
				descTareasEu.append(Constants.ESPACIO);
				descTareasEu.append(proceso.getIdTarea());
				descTareasEu.append(Constants.GUION_CON_ESPACIOS);
				descTareasEu.append(proceso.getDescTipoTareaEu());

				// Castellano
				descTareasEs.append(this.msg.getMessage(LABEL_TAREA, null, localeEs)).append(" ");
				descTareasEs.append(Constants.ESPACIO);
				descTareasEs.append(proceso.getIdTarea());
				descTareasEs.append(Constants.GUION_CON_ESPACIOS);
				descTareasEs.append(proceso.getDescTipoTareaEs());
				// acumulo
			} else {
				// enviar anterior

				if (emailProcesando != "") {

					infoEu.put("", descTareasEu.toString());
					infoEs.put("", descTareasEs.toString());

					parametrosEmail.setInfoEu(infoEu);
					parametrosEmail.setInfoEs(infoEs);

					parametrosEmail.setAnyoNumExpediente(Utils.getNumExpedienteParameter(expediente));

					try {
						this.mailService.sendMailWithParameters(parametrosEmail.getTipoAviso(),
								parametrosEmail.getListaDestinatarios(), parametrosEmail);
					} catch (Exception e) {
						ProcesoEmailServiceImpl.LOGGER.info(ERROR_ENVIO_EMAIL, e);
					}
				}
				// empezamos no nuevo
				emailProcesando = emailAux;
				infoEu = new LinkedHashMap<String, String>();
				infoEs = new LinkedHashMap<String, String>();
				parametrosEmail = new ParametrosEmail();
				expediente = new Expediente();

				parametrosEmail.setTipoAviso(proceso.getIdTipoAviso());

				List<String> listaDestinatarios = new ArrayList<String>();
				if (!StringUtils.isEmpty(proceso.getEmailDestinatario())) {
					listaDestinatarios.add(proceso.getEmailDestinatario());
				}

				parametrosEmail.setListaDestinatarios(listaDestinatarios);

				descTareasEs = new StringBuilder();
				descTareasEu = new StringBuilder();

				expediente.setAnyo(proceso.getAnyo());
				expediente.setNumExp(proceso.getNumExp());

				infoEu.put(this.msg.getMessage(Constants.LABEL_NUM_EXP, null, localeEu),
						proceso.getAnyoNumExpConcatenado() + " - " + proceso.getTituloExp());
				infoEs.put(this.msg.getMessage(Constants.LABEL_NUM_EXP, null, localeEs),
						proceso.getAnyoNumExpConcatenado() + " - " + proceso.getTituloExp());

				infoEu.put(this.msg.getMessage("label.tareas", null, localeEu), "");
				infoEs.put(this.msg.getMessage("label.tareas", null, localeEs), "");

				// Euskera
				descTareasEu.append(this.msg.getMessage(LABEL_TAREA, null, localeEu)).append(" ");
				descTareasEu.append(Constants.ESPACIO);
				descTareasEu.append(proceso.getIdTarea());
				descTareasEu.append(Constants.GUION_CON_ESPACIOS);
				descTareasEu.append(proceso.getDescTipoTareaEu());

				// Castellano
				descTareasEs.append(this.msg.getMessage(LABEL_TAREA, null, localeEs)).append(" ");
				descTareasEs.append(Constants.ESPACIO);
				descTareasEs.append(proceso.getIdTarea());
				descTareasEs.append(Constants.GUION_CON_ESPACIOS);
				descTareasEs.append(proceso.getDescTipoTareaEs());

			}

		}

		if (emailProcesando != "") {

			infoEu.put("", descTareasEu.toString());
			infoEs.put("", descTareasEs.toString());

			parametrosEmail.setInfoEu(infoEu);
			parametrosEmail.setInfoEs(infoEs);
			parametrosEmail.setAnyoNumExpediente(Utils.getNumExpedienteParameter(expediente));

			try {
				this.mailService.sendMailWithParameters(parametrosEmail.getTipoAviso(),
						parametrosEmail.getListaDestinatarios(), parametrosEmail);
			} catch (Exception e) {
				ProcesoEmailServiceImpl.LOGGER.info(ERROR_ENVIO_EMAIL, e);
			}
		}

	}
}
