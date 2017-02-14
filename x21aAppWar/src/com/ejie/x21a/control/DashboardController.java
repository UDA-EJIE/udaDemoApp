/*
* Copyright 2012 E.J.I.E., S.A.
*
* Licencia con arreglo a la EUPL, VersiÃ³n 1.1 exclusivamente (la Â«LicenciaÂ»);
* Solo podrÃ¡ usarse esta obra si se respeta la Licencia.
* Puede obtenerse una copia de la Licencia en
*
* http://ec.europa.eu/idabc/eupl.html
*
* Salvo cuando lo exija la legislaciÃ³n aplicable o se acuerde por escrito,
* el programa distribuido con arreglo a la Licencia se distribuye Â«TAL CUALÂ»,
* SIN GARANTÃ�AS NI CONDICIONES DE NINGÃšN TIPO, ni expresas ni implÃ­citas.
* VÃ©ase la Licencia en el idioma concreto que rige los permisos y limitaciones
* que establece la Licencia.
*/
package com.ejie.x21a.control;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import com.ejie.x21a.model.Dashboard;
import com.ejie.x21a.service.DashboardService;

/**
 * PatronesController
 * 
 * @author UDA
 */
@Controller
@RequestMapping(value = "/dashboard")
public class DashboardController {

	private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);
	
	@Autowired
	private DashboardService dashboardService;
	
	
	@Resource
	private ReloadableResourceBundleMessageSource messageSource;
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws ServletException {
		binder.registerCustomEditor(byte[].class,new ByteArrayMultipartFileEditor());
	}
	
	
	//dashboardSimple
	@RequestMapping(value = "dashboardSimple", method = RequestMethod.GET)
	public String getDashboardSimple(Model model) {
		return "dashboardSimple";
	}
	
	
	@RequestMapping(value = "getAll", method = RequestMethod.GET)
	public @ResponseBody List<Dashboard> getAll(Model model) {
		return dashboardService.getAll();
	}
	
	@RequestMapping(value = "get/{id}", method = RequestMethod.GET)
	public @ResponseBody Dashboard get(@PathVariable String id) {
		return dashboardService.get(new Dashboard(id));
	}
	
	@RequestMapping(value = "put", method = RequestMethod.PUT)
	public @ResponseBody Dashboard get(@RequestBody Dashboard dashboard) {
		return dashboardService.put(dashboard);
	}
	
	
}
