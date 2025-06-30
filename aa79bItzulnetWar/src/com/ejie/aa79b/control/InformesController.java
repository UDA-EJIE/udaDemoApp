package com.ejie.aa79b.control;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ejie.aa79b.model.Fichero;
import com.ejie.aa79b.model.Informe;
import com.ejie.aa79b.service.InformesService;

@Controller
@RequestMapping(value = "/informes")
public class InformesController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsultasController.class);

	@Autowired()
	private InformesService informesService;
	
	@RequestMapping(value = "/maint", method = RequestMethod.GET)
	public String getConsultaExpedientes() {
		InformesController.LOGGER.info("[GET - View] : getInformes");
		return "informes";
	}
	
	@RequestMapping(value = "/verInforme/{tipoInforme}/{anyo}/{mes}", method = RequestMethod.GET)
	public void getInforme(@PathVariable String tipoInforme,
			@PathVariable String anyo, @PathVariable String mes, HttpServletResponse response,
			Locale locale) throws Exception {
		InformesController.LOGGER.info("[GET - View]: verInforme");
		
		Cookie cookie = new Cookie("fileDownload", "false");
		cookie.setPath("/");
		response.addCookie(cookie);
		
		Informe bean = new Informe();
		bean.setTipoInforme(tipoInforme);
		bean.setAnyo(Long.valueOf(anyo));
		bean.setMes(mes);
		
		Fichero fichero = this.informesService.exportarResultadoCurso(bean, locale);
		
		if (fichero != null) {
			response.setContentType(fichero.getContentType());
			response.setHeader("Content-disposition", "attachment;filename="+fichero.getNombre() + ".xlsx");
			
			cookie = new Cookie("fileDownload", "true");
			cookie.setPath("/");
			response.addCookie(cookie);

			OutputStream output;
			try {
				output = response.getOutputStream();
				output.write(fichero.getBytes());
				output.close();
			} catch (IOException e) {
				LOGGER.error("[POST - subm]: verCertificado ", e);
				throw new RuntimeException(e);
			}
		}
	}
}
