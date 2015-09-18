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

import com.ejie.x21a.model.Comarca;
import com.ejie.x21a.model.Provincia;
import com.ejie.x21a.service.ComarcaService;
import com.ejie.x38.dto.JQGridJSONModel;
import com.ejie.x38.dto.Pagination;
import com.ejie.x38.util.ObjectConversionManager;
/**
 * ComarcaController
* @author UDA
 */
@Controller
@RequestMapping (value = "/comarca")

public class ComarcaController  {

	private static final Logger logger = LoggerFactory.getLogger(ComarcaController.class);

	@Autowired
	private ComarcaService comarcaService;
	
	 /**
	 * Method 'getCreateForm'.
	 *
	 * @param model Model
	 * @return String
	 */
	@RequestMapping(value = "maint", method = RequestMethod.GET)
	public String getCreateForm(Model model) {
		return "comarca";
	}

	 /**
	 * Method 'getById'.
	 * @param  code BigDecimal
	 * @return String
	 */
	@RequestMapping(value = "/{code}", method = RequestMethod.GET)
	public @ResponseBody Comarca getById(@PathVariable BigDecimal code) {
            Comarca comarca = new Comarca();
			comarca.setCode(code);
            comarca = this.comarcaService.find(comarca);
            return comarca;
		}


	 /**
	 * Method 'getAll'.
	*@param	  code BigDecimal
	*@param	  provinciaCode BigDecimal
	*@param	  descEs String
	*@param	  descEu String
	*@param	  css String
	*@param request HttpServletRequest
	 * @return String
	 */
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Object getAll(
@RequestParam(value = "code", required = false) BigDecimal code,
@RequestParam(value = "comarca.provincia.code", required = false) BigDecimal provinciaCode,
@RequestParam(value = "descEs", required = false) String descEs,
@RequestParam(value = "descEu", required = false) String descEu,
@RequestParam(value = "css", required = false) String css,
			HttpServletRequest request) {
				Comarca filterComarca = new Comarca(code, descEs, descEu, css, new Provincia(provinciaCode, null, null, null, null, null));
                Pagination pagination = null;
			    if (request.getHeader("JQGridModel") != null &&  request.getHeader("JQGridModel").equals("true")) {
				    pagination = new Pagination();
				    pagination.setPage(Long.valueOf(request.getParameter("page")));
				    pagination.setRows(Long.valueOf(request.getParameter("rows")));
				    pagination.setSort(request.getParameter("sidx"));
				    pagination.setAscDsc(request.getParameter("sord"));
                    List<Comarca> comarcas =  this.comarcaService.findAll(filterComarca, pagination);

	        Long total =  getAllCount(filterComarca);
				    JQGridJSONModel data = new JQGridJSONModel();
				    data.setPage(request.getParameter("page"));
				    data.setRecords(total.intValue());
				    data.setTotal(total, pagination.getRows());
				    data.setRows(comarcas);
				    return data;
				}else{
		    return this.comarcaService.findAll(filterComarca, pagination);
		            }
	}

	/**
	 * Method 'getAllCount'.
	 * @param filterComarca Comarca 
	 * @return Long
	 */
	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public @ResponseBody Long getAllCount(
	@RequestParam(value = "comarca", required = false) Comarca  filterComarca) {
		return comarcaService.findAllCount(filterComarca != null ? filterComarca: new Comarca ());
		}
	
	 /**
	 * Method 'edit'.
	 * @param	 comarca Comarca 
	 * @return Comarca
	 */
	@RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody Comarca edit(@RequestBody Comarca comarca) {		
            Comarca comarcaAux  = this.comarcaService.update(comarca);
		logger.info("Entity correctly updated!");
            return comarcaAux;
    }

	 /**
	 * Method 'add'.
	 * @param	 comarca Comarca 
	 * @return Comarca
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Comarca add(@RequestBody Comarca comarca) {		
            Comarca comarcaAux = this.comarcaService.add(comarca);
            logger.info( "Entity correctly inserted!");
        	return comarcaAux;
		}

	 /**
	 * Method 'remove'.
	 * @param  code  BigDecimal
	 *
	 */
	@RequestMapping(value = "/{code}", method = RequestMethod.DELETE)
    public void remove(@PathVariable BigDecimal code) {
            Comarca comarca = new Comarca();
            comarca.setCode(code);
            this.comarcaService.remove(comarca);
        logger.info("Entity correctly deleted!");
    	}
	
	 /**
	 * Method 'removeAll'.
	 * @param  comarcaIds  ArrayList
	 *
	 */	
	@RequestMapping(value = "/deleteAll", method = RequestMethod.POST)
	public void removeMultiple(@RequestBody ArrayList<ArrayList<String>> comarcaIds) {
        ArrayList<Comarca> comarcaList = new ArrayList<Comarca>();
            for (ArrayList<String> comarcaId:comarcaIds) {
			    Iterator<String> iterator = comarcaId.iterator();
				    Comarca comarca = new Comarca();
			        comarca.setCode(ObjectConversionManager.convert(iterator.next(), java.math.BigDecimal.class));
				    comarcaList.add(comarca);
		    }
            this.comarcaService.removeMultiple(comarcaList);
            logger.info("All entities correctly deleted!");
    	}	
    }	
