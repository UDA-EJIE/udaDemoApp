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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import com.ejie.x21a.model.Comarca;
import com.ejie.x21a.model.Localidad;
import com.ejie.x21a.model.PerfilUsuario;
import com.ejie.x21a.model.Provincia;
import com.ejie.x21a.model.Usuario;
import com.ejie.x21a.service.PerfilUsuarioService;
import com.ejie.x38.dto.JQGridJSONModel;
import com.ejie.x38.dto.Pagination;
import com.ejie.x38.util.DateTimeManager;
import com.ejie.x38.util.ObjectConversionManager;

/**
 * PerfilUsuarioController
 * @author UDA
 */
 
@Controller
@RequestMapping (value = "/perfilusuario")

public class PerfilUsuarioController  {

	private static final Logger logger = LoggerFactory.getLogger(PerfilUsuarioController.class);

	@Autowired
	private PerfilUsuarioService perfilUsuarioService;
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws ServletException {
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
		binder.registerCustomEditor(Date.class, new CustomDateEditor(DateTimeManager.getDateTimeFormat(LocaleContextHolder.getLocale()), true));
	}
	
	/**
	 * Method 'getCreateForm'.
	 *
	 * @param model Model
	 * @return String
	 */
	@RequestMapping(value = "maint", method = RequestMethod.GET)
	public String getCreateForm(Model model) {
		PerfilUsuario perfilUsuario = new PerfilUsuario();
		perfilUsuario.setId(new BigDecimal(4));
		model.addAttribute("perfilUsuario", perfilUsuario);
		return "perfilusuario";
	}

	/**
	 * Method 'getById'.
	 * @param  id BigDecimal
	 * @return String
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody PerfilUsuario getById(@PathVariable BigDecimal id) {
        PerfilUsuario perfilUsuario = new PerfilUsuario();
		perfilUsuario.setId(id);
        perfilUsuario = this.perfilUsuarioService.find(perfilUsuario);
        return perfilUsuario;
	}


	/**
	 * Method 'getAll'.
	 *@param	  id BigDecimal
	 *@param	  usuarioId String
	 *@param	  localidadCode BigDecimal
	 *@param	  comarcaCode BigDecimal
	 *@param	  provinciaCode BigDecimal
	 *@param	  userId String
	 *@param	  pass String
	 *@param	  activo String
	 *@param	  tipo String
	 *@param	  fechaAlta Timestamp
	 *@param	  fechaBaja Date
	 *@param	  horaEntrada Timestamp
	 * @param request HttpServletRequest
	 * @return String
	 */
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Object getAll(
	@RequestParam(value = "id", required = false) BigDecimal id,
	@RequestParam(value = "usuarioId", required = false) String usuarioId,
	@RequestParam(value = "localidadCode", required = false) BigDecimal localidadCode,
	@RequestParam(value = "comarcaCode", required = false) BigDecimal comarcaCode,
	@RequestParam(value = "provinciaCode", required = false) BigDecimal provinciaCode,
	@RequestParam(value = "userId", required = false) String userId,
	@RequestParam(value = "pass", required = false) String pass,
	@RequestParam(value = "activo", required = false) String activo,
	@RequestParam(value = "tipo", required = false) String tipo,
	@RequestParam(value = "fechaAlta", required = false) Timestamp fechaAlta,
	@RequestParam(value = "fechaBaja", required = false) Date fechaBaja,
	@RequestParam(value = "horaEntrada", required = false) Timestamp horaEntrada,
			HttpServletRequest request) {
	
			PerfilUsuario filterPerfilUsuario = new PerfilUsuario(id, userId, pass, activo, tipo, null, fechaAlta, fechaBaja, horaEntrada, new Usuario(usuarioId, null, null, null, null, null, null, null), new Localidad(localidadCode, null, null, null, null), new Comarca(comarcaCode, null, null, null, null, null, null), new Provincia(provinciaCode, null, null, null, null, null));
            Pagination pagination = null;
	    if (request.getHeader("JQGridModel") != null &&  request.getHeader("JQGridModel").equals("true")) {
		    pagination = new Pagination();
		    pagination.setPage(Long.valueOf(request.getParameter("page")));
		    pagination.setRows(Long.valueOf(request.getParameter("rows")));
		    pagination.setSort(request.getParameter("sidx"));
		    pagination.setAscDsc(request.getParameter("sord"));
            List<PerfilUsuario> perfilUsuarios =  this.perfilUsuarioService.findAll(filterPerfilUsuario, pagination);

	        Long total = getAllCount(filterPerfilUsuario);
		    JQGridJSONModel data = new JQGridJSONModel();
		    data.setPage(request.getParameter("page"));
		    data.setRecords(total.intValue());
		    data.setTotal(total, pagination.getRows());
		    data.setRows(perfilUsuarios);
		    return data;
		}else{
		    return this.perfilUsuarioService.findAll(filterPerfilUsuario, pagination);
        }
	}

	/**
	 * Method 'getAllCount'.
	 * @param filterPerfilUsuario PerfilUsuario 
	 * @return Long
	 */
	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public @ResponseBody Long getAllCount(
	@RequestParam(value = "perfilUsuario", required = false) PerfilUsuario  filterPerfilUsuario) {
		return perfilUsuarioService.findAllCount(filterPerfilUsuario != null ? filterPerfilUsuario : new PerfilUsuario ());
	}
	
	/**
	 * Method 'edit'.
	 * @param perfilUsuario PerfilUsuario 
	 * @return PerfilUsuario
	 */
	@RequestMapping(method = RequestMethod.POST, headers={"rup_maint_mode=edit", "Content-Type=application/x-www-form-urlencoded"} )
    public @ResponseBody PerfilUsuario edit(@ModelAttribute @Valid PerfilUsuario perfilUsuario, BindingResult bindingResult) {		
			bindingResult.hasErrors();
            PerfilUsuario perfilUsuarioAux  = this.perfilUsuarioService.update(perfilUsuario);
		logger.info("Entity correctly updated!");
            return perfilUsuarioAux;
    }
	
	@RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody PerfilUsuario editPut(
    		 @RequestBody PerfilUsuario perfilUsuario
    		) {		
            PerfilUsuario perfilUsuarioAux  = this.perfilUsuarioService.update(perfilUsuario);
			logger.info("Entity correctly inserted!");
            return perfilUsuarioAux;
    }

	/**
	 * Method 'add'.
	 * @param perfilUsuario PerfilUsuario 
	 * @return PerfilUsuario
	 */
	@RequestMapping(method = RequestMethod.POST, headers="rup_maint_mode=add")
	public @ResponseBody PerfilUsuario add(@ModelAttribute @Valid PerfilUsuario perfilUsuario, BindingResult bindingResult) {		
            PerfilUsuario perfilUsuarioAux = this.perfilUsuarioService.add(perfilUsuario);
            logger.info("Entity correctly inserted!");
        	return perfilUsuarioAux;
	}

	/**
	 * Method 'remove'.
	 * @param  id  BigDecimal
	 * @param response  HttpServletResponse
	 * @return 
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable BigDecimal id) {
            PerfilUsuario perfilUsuario = new PerfilUsuario();
            perfilUsuario.setId(id);
            this.perfilUsuarioService.remove(perfilUsuario);
        logger.info("Entity correctly deleted!");
    }
	
	/**
	 * Method 'removeAll'.
	 * @param  perfilUsuarioIds  ArrayList
	 * @param response  HttpServletResponse
	 * @return
	 */	
	@RequestMapping(value = "/deleteAll", method = RequestMethod.POST)
	public void removeMultiple(@RequestBody ArrayList<ArrayList<String>> perfilUsuarioIds) {
        ArrayList<PerfilUsuario> perfilUsuarioList = new ArrayList<PerfilUsuario>();
            for (ArrayList<String> perfilUsuarioId:perfilUsuarioIds) {
			    Iterator<String> iterator = perfilUsuarioId.iterator();
				    PerfilUsuario perfilUsuario = new PerfilUsuario();
			        perfilUsuario.setId(ObjectConversionManager.convert(iterator.next(), java.math.BigDecimal.class));
				    perfilUsuarioList.add(perfilUsuario);
		    }
            this.perfilUsuarioService.removeMultiple(perfilUsuarioList);
        logger.info("All entities correctly deleted!");
	}

	}
