/*
* Copyright 2012 E.J.I.E., S.A.
*
* Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la «Licencia»);
* Solo podrá usarse esta obra si se respeta la Licencia.
* Puede obtenerse una copia de la Licencia en
*
* http://ec.europa.eu/idabc/eupl.html
*
* Salvo cuando lo exija la legislación aplicable o se acuerde por escrito,
* el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL»,
* SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
* Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
* que establece la Licencia.
*/
package com.ejie.x21a.control;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import com.ejie.x21a.model.RandomForm;

/**
 * PatronesController
 * 
 * @author UDA
 */
@Controller
@RequestMapping(value = "/bootstrap")
public class BootstrapController {

	private static final Logger logger = LoggerFactory.getLogger(BootstrapController.class);
	
	

	
	@Resource
	private ReloadableResourceBundleMessageSource messageSource;
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws ServletException {
		binder.registerCustomEditor(byte[].class,new ByteArrayMultipartFileEditor());
	}
	
	
	@RequestMapping(value = "stackedHorizontal", method = RequestMethod.GET)
	public String getStackedHorizontal(Model model) {
		return "stackedHorizontal";
	}
	
	@RequestMapping(value = "mobileDesktop", method = RequestMethod.GET)
	public String getMobileDesktop(Model model) {
		return "mobileDesktop";
	}
	
	@RequestMapping(value = "mobileTabletDesktop", method = RequestMethod.GET)
	public String getMobileTabletDesktop(Model model) {
		return "mobileTabletDesktop";
	}
	
	@RequestMapping(value = "exampleForm", method = RequestMethod.GET)
	public String getExampleForm(Model model) {
		model.addAttribute("randomForm", new RandomForm());
		return "exampleForm";
	}
	
	
}
