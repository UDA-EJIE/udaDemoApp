package com.ejie.aa79b.control;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.model.SubidaTradosResponse;
import com.ejie.aa79b.model.TareaTrados;
import com.ejie.aa79b.service.DatosTareaTradosService;
import com.ejie.aa79b.service.TareasGeneralService;

@Controller()
@RequestMapping(value = "/xmlTrados")
public class XmlTradosController {

	private static final Logger LOGGER = LoggerFactory.getLogger(XmlTradosController.class);

	@Autowired()
	private DatosTareaTradosService datosTareaTradosService;

	@Autowired
	private TareasGeneralService tareasGeneralService;

	@Autowired()
	private ReloadableResourceBundleMessageSource messageSource;

	/**
	 * PIF / PID
	 *
	 * @param fichero
	 *            MultipartFile.
	 * @param request
	 *            HttpServletRequest.
	 * @param idioma
	 *            Locale.
	 * @return Fichero Bean Fichero.
	 */
	@RequestMapping(value = "/subidaFichero", method = RequestMethod.POST)
	public @ResponseBody() TareaTrados subidaFicheroXml(@RequestParam() MultipartFile fichero,
			@RequestParam() BigDecimal idTarea, @RequestParam() String indPresupuesto, @RequestParam() Long anyo,
			@RequestParam() Integer numExp) {
		XmlTradosController.LOGGER.info("DocumentosGeneral : subidaFicheroXml");
		Locale locale = LocaleContextHolder.getLocale();

		TareaTrados tareaTrados = new TareaTrados();

		BigDecimal limit = new BigDecimal(Constants.TAMANO_FICHERO_MAX_2MB);
		final DecimalFormat df = new DecimalFormat("#,###.##", new java.text.DecimalFormatSymbols(new Locale("es")));
		Object[] paramT = { ""
				+ (df.format(limit.divide(new BigDecimal(Constants.TAMANO_1MB_EN_BYTES), 2, RoundingMode.CEILING))) };

		String nombreFichero = fichero.getOriginalFilename();
		int elPunto = nombreFichero.lastIndexOf('.');
		String laExtension = nombreFichero.substring(elPunto + 1, nombreFichero.length());

		if (!Constants.FILE_XML.equalsIgnoreCase(laExtension)) {
			tareaTrados.setErrorMsg(this.messageSource.getMessage("error.trados.noxml", paramT, locale));
		} else if (new BigDecimal(fichero.getSize()).compareTo(limit) == 1) {
			tareaTrados.setErrorMsg(this.messageSource.getMessage("mensaje.fichero.tamanoFich", paramT, locale));
		} else {

			SubidaTradosResponse resul = this.datosTareaTradosService.subirTrados(idTarea, fichero, indPresupuesto,
					anyo, numExp);

			if (resul.getPresupuesto().compareTo(BigDecimal.ZERO) >= 0) {
				tareaTrados = this.tareasGeneralService.cargaInicialTareaTrados(anyo, numExp, idTarea);

				if (tareaTrados.getDatosTareaTrados() != null) {
					tareaTrados.getDatosTareaTrados().setPresupuesto(resul.getPresupuesto());
				}
			} else {
				tareaTrados.setErrorMsg(resul.getErrorMsg());

			}
		}
		return tareaTrados;
	}
}
