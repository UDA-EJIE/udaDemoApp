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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * UploadController
 * 
 * @author UDA
 */
@Controller
@RequestMapping(value = "/calendar")
public class X21aCalendarController {
	private X21aCalendarController() {

	}

	private static final Logger logger = LoggerFactory.getLogger(X21aCalendarController.class);

	@RequestMapping(value = "page", method = RequestMethod.GET)
	public String getPage(Model model) {
		logger.info("Request /calendar/page view");
		return "calendar";
	}

	@RequestMapping(value = "noTemplate", method = RequestMethod.GET)
	public String getPageNoTemplate(Model model) {
		logger.info("Request /calendar/noTemplate view");
		return "calendarNoTemplate";
	}
}
