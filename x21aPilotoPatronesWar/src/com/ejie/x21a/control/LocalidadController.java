/*
* Copyright 2011 E.J.I.E., S.A.
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

import com.ejie.x21a.model.Comarca;
import com.ejie.x21a.model.Localidad;
import com.ejie.x21a.service.LocalidadService;
import com.ejie.x38.dto.JQGridJSONModel;
import com.ejie.x38.dto.Pagination;
import com.ejie.x38.util.ObjectConversionManager;
/**
 * LocalidadController
* @author UDA
 */
@Controller
@RequestMapping (value = "/localidad")

public class LocalidadController  {

	private static final Logger logger = LoggerFactory.getLogger(LocalidadController.class);

	@Autowired
	private LocalidadService localidadService;
	
	 /**
	 * Method 'getCreateForm'.
	 *
	 * @param model Model
	 * @return String
	 */
	@RequestMapping(value = "maint", method = RequestMethod.GET)
	public String getCreateForm(Model model) {
		return "localidad";
	}

	 /**
	 * Method 'getById'.
	 * @param  code BigDecimal
	 * @return String
	 */
	@RequestMapping(value = "/{code}", method = RequestMethod.GET)
	public @ResponseBody Localidad getById(@PathVariable BigDecimal code) {
            Localidad localidad = new Localidad();
			localidad.setCode(code);
            localidad = this.localidadService.find(localidad);
            return localidad;
		}


	 /**
	 * Method 'getAll'.
	*@param	  code BigDecimal
	*@param	  comarcaCode BigDecimal
	*@param	  descEs String
	*@param	  descEu String
	*@param	  css String
	*@param request HttpServletRequest
	 * @return String
	 */
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Object getAll(
@RequestParam(value = "code", required = false) BigDecimal code,
@RequestParam(value = "comarcaCode", required = false) BigDecimal comarcaCode,
@RequestParam(value = "descEs", required = false) String descEs,
@RequestParam(value = "descEu", required = false) String descEu,
@RequestParam(value = "css", required = false) String css,
			HttpServletRequest request) {
				Localidad filterLocalidad = new Localidad(code, descEs, descEu, css, new Comarca(comarcaCode, null, null, null, null, null, null));
                Pagination pagination = null;
			    if (request.getHeader("JQGridModel") != null &&  request.getHeader("JQGridModel").equals("true")) {
				    pagination = new Pagination();
				    pagination.setPage(Long.valueOf(request.getParameter("page")));
				    pagination.setRows(Long.valueOf(request.getParameter("rows")));
				    pagination.setSort(request.getParameter("sidx"));
				    pagination.setAscDsc(request.getParameter("sord"));
                    List<Localidad> localidads =  this.localidadService.findAll(filterLocalidad, pagination);

	        Long total =  getAllCount(filterLocalidad);
				    JQGridJSONModel data = new JQGridJSONModel();
				    data.setPage(request.getParameter("page"));
				    data.setRecords(total.intValue());
				    data.setTotal(total, pagination.getRows());
				    data.setRows(localidads);
				    return data;
				}else{
		    return this.localidadService.findAll(filterLocalidad, pagination);
		            }
				}

	/**
	 * Method 'getAllCount'.
	 * @param filterLocalidad Localidad 
	 * @return Long
	 */
	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public @ResponseBody Long getAllCount(@RequestParam(value = "localidad", required = false) Localidad  filterLocalidad) {
		return localidadService.findAllCount(filterLocalidad != null ? filterLocalidad: new Localidad ());
		}
	
	 /**
	 * Method 'edit'.
	 * @param	 localidad Localidad 
	 * @return Localidad
	 */
	@RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody Localidad edit(@RequestBody Localidad localidad) {		
            Localidad localidadAux  = this.localidadService.update(localidad);
		logger.info("Entity correctly updated!");
            return localidadAux;
    }

	 /**
	 * Method 'add'.
	 * @param	 localidad Localidad 
	 * @return Localidad
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Localidad add(@RequestBody Localidad localidad) {		
            Localidad localidadAux = this.localidadService.add(localidad);
            logger.info( "Entity correctly inserted!");
        	return localidadAux;
	}

	 /**
	 * Method 'remove'.
	 * @param  code  BigDecimal
	 *
	 */
	@RequestMapping(value = "/{code}", method = RequestMethod.DELETE)
    public void remove(@PathVariable BigDecimal code) {
            Localidad localidad = new Localidad();
            localidad.setCode(code);
            this.localidadService.remove(localidad);
        logger.info("Entity correctly deleted!");
    }
	
	 /**
	 * Method 'removeAll'.
	 * @param  localidadIds  ArrayList
	 * @param response  HttpServletResponse
	 *
	 */	
	@RequestMapping(value = "/deleteAll", method = RequestMethod.POST)
	public void removeMultiple(@RequestBody ArrayList<ArrayList<String>> localidadIds) {
        ArrayList<Localidad> localidadList = new ArrayList<Localidad>();
            for (ArrayList<String> localidadId:localidadIds) {
			    Iterator<String> iterator = localidadId.iterator();
				    Localidad localidad = new Localidad();
			        localidad.setCode(ObjectConversionManager.convert(iterator.next(), java.math.BigDecimal.class));
				    localidadList.add(localidad);
		    }
            this.localidadService.removeMultiple(localidadList);
        logger.info("All entities correctly deleted!");
		}
	}	
