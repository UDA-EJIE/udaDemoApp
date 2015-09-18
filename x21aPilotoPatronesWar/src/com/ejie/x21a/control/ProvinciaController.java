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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejie.x21a.model.Provincia;
import com.ejie.x21a.service.ProvinciaService;
import com.ejie.x38.dto.JQGridJSONModel;
import com.ejie.x38.dto.Pagination;
import com.ejie.x38.util.ObjectConversionManager;
/**
 * ProvinciaController
 * @author UDA
 */
@Controller
@RequestMapping (value = "/provincia")

public class ProvinciaController  {

	private static final Logger logger = LoggerFactory.getLogger(ProvinciaController.class);

	@Autowired
	private ProvinciaService provinciaService;
	
	 /**
	 * Method 'getCreateForm'.
	 *
	 * @param model Model
	 * @return String
	 */
	@RequestMapping(value = "maint", method = RequestMethod.GET)
	public String getCreateForm(Model model) {
		return "provincia";
	}

	 /**
	 * Method 'getById'.
	 * @param  code BigDecimal
	 * @return String
	 */
	@RequestMapping(value = "/{code}", method = RequestMethod.GET)
	public @ResponseBody Provincia getById(@PathVariable BigDecimal code) {
        Provincia provincia = new Provincia();
		provincia.setCode(code);
        provincia = this.provinciaService.find(provincia);
        return provincia;
	}


	 /**
	 * Method 'getAll'.
	 * @param code BigDecimal
	 * @param descEs String
	 * @param descEu String
	 * @param css String
	 * @param request HttpServletRequest
	 * @return String
	 */
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Object getAll(
			@RequestParam(value = "code", required = false) BigDecimal code,
			@RequestParam(value = "descEs", required = false) String descEs,
			@RequestParam(value = "descEu", required = false) String descEu,
			@RequestParam(value = "css", required = false) String css,
			HttpServletRequest request) {
		Provincia filterProvincia = new Provincia(code, descEs, descEu, css);
        Pagination pagination = null;
	    if (request.getHeader("JQGridModel") != null &&  request.getHeader("JQGridModel").equals("true")) {
		    pagination = new Pagination();
		    pagination.setPage(Long.valueOf(request.getParameter("page")));
		    pagination.setRows(Long.valueOf(request.getParameter("rows")));
		    pagination.setSort(request.getParameter("sidx"));
		    pagination.setAscDsc(request.getParameter("sord"));
            List<Provincia> provincias =  this.provinciaService.findAll(filterProvincia, pagination);

	        Long total =  getAllCount(filterProvincia);
		    JQGridJSONModel data = new JQGridJSONModel();
		    data.setPage(request.getParameter("page"));
		    data.setRecords(total.intValue());
		    data.setTotal(total, pagination.getRows());
		    data.setRows(provincias);
		    return data;
		}else{
		   return this.provinciaService.findAll(filterProvincia, pagination);
		}
	}

	/**
	 * Method 'getAllCount'.
	 * @param filterProvincia Provincia 
	 * @return Long
	 */
	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public @ResponseBody Long getAllCount(
	@RequestParam(value = "provincia", required = false) Provincia  filterProvincia) {
		return provinciaService.findAllCount(filterProvincia != null ? filterProvincia: new Provincia ());
	}
	
	 /**
	 * Method 'edit'.
	 * @param provincia Provincia 
	 * @return Provincia
	 */
	@RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody Provincia edit(@RequestBody Provincia provincia) {		
        Provincia provinciaAux  = this.provinciaService.update(provincia);
		logger.info("Entity correctly updated!");
        return provinciaAux;
    }

	 /**
	 * Method 'add'.
	 * @param provincia Provincia 
	 * @return Provincia
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Provincia add(@RequestBody Provincia provincia) {		
        Provincia provinciaAux = this.provinciaService.add(provincia);
        logger.info("Entity correctly inserted!");
    	return provinciaAux;
	}

	 /**
	 * Method 'remove'.
	 * @param  code  BigDecimal
	 *
	 */
	@RequestMapping(value = "/{code}", method = RequestMethod.DELETE)
    public void remove(@PathVariable BigDecimal code) {
        Provincia provincia = new Provincia();
        provincia.setCode(code);
        this.provinciaService.remove(provincia);
        logger.info("Entity correctly deleted!");
    }
	
	 /**
	 * Method 'removeAll'.
	 * @param  provinciaIds  ArrayList
	 *
	 */	
	@RequestMapping(value = "/deleteAll", method = RequestMethod.POST)
	public void removeMultiple(@RequestBody ArrayList<ArrayList<String>> provinciaIds) {
        ArrayList<Provincia> provinciaList = new ArrayList<Provincia>();
        for (ArrayList<String> provinciaId:provinciaIds) {
		    Iterator<String> iterator = provinciaId.iterator();
		    Provincia provincia = new Provincia();
	        provincia.setCode(ObjectConversionManager.convert(iterator.next(), java.math.BigDecimal.class));
		    provinciaList.add(provincia);
	    }
        this.provinciaService.removeMultiple(provinciaList);
        logger.info("All entities correctly deleted!");
	}	
}