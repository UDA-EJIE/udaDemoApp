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

import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import com.ejie.x21a.model.Alumno;
import com.ejie.x21a.model.NoraAutonomia;
import com.ejie.x21a.model.NoraPais;
import com.ejie.x21a.service.AlumnoService;
import com.ejie.x21a.service.NoraAutonomiaService;
import com.ejie.x21a.service.NoraPaisService;
import com.ejie.x21a.validation.group.AlumnoAddValidation;
import com.ejie.x21a.validation.group.AlumnoEditValidation;
import com.ejie.x38.dto.JQGridJSONModel;
import com.ejie.x38.dto.Pagination;
import com.ejie.x38.util.DateTimeManager;
import com.ejie.x38.util.ObjectConversionManager;
import com.ejie.x38.validation.ValidationManager;

/**
 * AlumnoController
 * @author UDA
 */
 
@Controller
@RequestMapping (value = "/administracion/alumno")
public class AlumnoController  {

	private static final Logger logger = LoggerFactory.getLogger(AlumnoController.class);

	@Autowired
	private AlumnoService alumnoService;
	
	@Autowired
	private NoraPaisService noraPaisService;
	
	@Autowired
	private NoraAutonomiaService noraAutonomiaService;
	
	@Autowired
	private ValidationManager validationManager;
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws ServletException {
		binder.registerCustomEditor(byte[].class,new ByteArrayMultipartFileEditor());
		binder.registerCustomEditor(Date.class, new CustomDateEditor(DateTimeManager.getDateTimeFormat(LocaleContextHolder.getLocale()), true));
		NumberFormat numberFormat = NumberFormat.getInstance(LocaleContextHolder.getLocale());
		binder.registerCustomEditor(BigDecimal.class, new CustomNumberEditor(BigDecimal.class, numberFormat, true));
	}
	
	/**
	 * Method 'getCreateForm'.
	 *
	 * @param model Model
	 * @return String
	 */
	@RequestMapping(value = "maint", method = RequestMethod.GET)
	public String getCreateForm(Model model) {
		
		List<NoraPais> paises = noraPaisService.findAll(null, null);
		model.addAttribute("paises",paises);
		
		List<NoraAutonomia> autonomias = noraAutonomiaService.findAll(null, null);
		model.addAttribute("autonomias",autonomias);
		
		Alumno alumno = new Alumno();
		
		NoraPais pais = new NoraPais();
		pais.setId("108");
		alumno.setPais(pais);
		
		model.addAttribute("alumno",alumno);
//		model.addAttribute("pais",pais);
		
		AlumnoController.logger.info("[GET - View] : alumno");
		return "alumno";
	}

	/**
	 * Method 'getById'.
	 *
	 * @param id BigDecimal
	 * @return alumno Alumno
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Alumno getById(final @PathVariable BigDecimal id) {
		Alumno alumno = new Alumno();
		alumno.setId(id);
        alumno = this.alumnoService.find(alumno);
        AlumnoController.logger.info("[GET - findBy_PK] : Obtener Alumno por PK");
        return alumno;
	}

	/**
	 * Method 'getAll'.
	 *
	 * @param filterAlumno Alumno
	 * @return List
	 */
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Alumno> getAll(@ModelAttribute Alumno filterAlumno) {
		AlumnoController.logger.info("[GET - find_ALL] : Obtener Alumnos por filtro");
	    return this.alumnoService.findAll(filterAlumno, null);
	}

	/**
	 * Method 'edit'.
	 * @param alumno Alumno 
	 * @return Alumno
	 * @throws IOException 
	 */
	@RequestMapping(method = RequestMethod.PUT, produces="application/json")
	public @ResponseBody Alumno edit(
			@Validated(AlumnoEditValidation.class) @ModelAttribute Alumno alumno,
			Errors errors,
			@RequestParam(value = "imagenAlumno", required = false) MultipartFile imagen,
			@RequestParam(value = "oldPassword", required = false) String oldPassword,
			HttpServletRequest request, HttpServletResponse response) throws IOException {	
		
		if (imagen!=null){
			alumno.setNombreImagen(imagen.getOriginalFilename());
			alumno.setImagen(imagen.getBytes());
        }
		
		Alumno alumnoAux  = this.alumnoService.update(alumno, oldPassword, errors);
		
		if (errors.hasErrors()){
			Map<String, List<String>> errorsMap = validationManager.getErrorsAsMap(errors);
			
			ServletOutputStream servletOutputStream = response.getOutputStream();
			
			response.setStatus(406);
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			servletOutputStream.print(validationManager.getMessageJSON(errorsMap, null, "error").toString());
			response.flushBuffer();
            
        	return null;
        }
		AlumnoController.logger.info("[PUT] : Alumno actualizado correctamente");
        return alumnoAux;
    }
	
// EJEMPLO ENVIO application/json
//	@RequestMapping(method = RequestMethod.PUT)
//	public @ResponseBody Alumno edit(
//			@RequestBody Alumno alumno,
//			@RequestParam(value = "oldPassword", required = false) String oldPassword,
//			HttpServletRequest request, HttpServletResponse response) throws IOException {	
//		
//		Errors errors = new BeanPropertyBindingResult(alumno, "alumno");
//		
//		Alumno alumnoAux  = this.alumnoService.update(alumno, oldPassword, errors);
//		validationManager.validate(errors, alumno, AlumnoEditValidation.class);
//		
//		if (errors.hasErrors()){
//			Map<String, List<String>> errorsMap = validationManager.getErrorsAsMap(errors);
//        	response.sendError(406, validationManager.getMessageJSON(errorsMap, null, "error").toString());
//        	return null;
//        }
//		AlumnoController.logger.info("[PUT] : Alumno actualizado correctamente");
//        return alumnoAux;
//    }
	

	/**
	 * Method 'add'.
	 * @param alumno Alumno 
	 * @return Alumno
	 * @throws IOException 
	 */
	@RequestMapping(method = RequestMethod.POST, produces="application/json")
	public @ResponseBody Object add(@Validated(AlumnoAddValidation.class) 
			@ModelAttribute Alumno alumno, Errors errors, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="imagenAlumno", required=false)MultipartFile imagen) throws IOException {	
		
		if (imagen!=null){
			alumno.setNombreImagen(imagen.getOriginalFilename());
			alumno.setImagen(imagen.getBytes());
        }
		
        Alumno alumnoAux = this.alumnoService.add(alumno, errors);

        if (errors.hasErrors()){
			Map<String, List<String>> errorsMap = validationManager.getErrorsAsMap(errors);
            ServletOutputStream servletOutputStream = response.getOutputStream();
            response.setStatus(406);
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            servletOutputStream.print(validationManager.getMessageJSON(errorsMap, null, "error").toString());
            response.flushBuffer();       
        	return null;
        }
        AlumnoController.logger.info("[POST] : Alumno insertado correctamente");
    	return alumnoAux;
	}

	/**
	 * Method 'remove'.
	 *
	 * @param id BigDecimal
	 * @return alumno
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody Alumno remove(@PathVariable BigDecimal id) {
        Alumno alumno = new Alumno();
        alumno.setId(id);
        this.alumnoService.remove(alumno);
       	AlumnoController.logger.info("[DELETE] : Alumno borrado correctamente");
       	return alumno;
    }
	
	/**
	 * Method 'removeAll'.
	 *
	 * @param alumnoIds List
	 * @return alumnoList
	 */	
	@RequestMapping(value = "/deleteAll", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody List<List<String>> removeMultiple(@RequestBody List<List<String>> alumnoIds) {
        List<Alumno> alumnoList = new ArrayList<Alumno>();
        for (List<String> alumnoId:alumnoIds) {
		    Iterator<String> iterator = alumnoId.iterator();
		    Alumno alumno = new Alumno(); //NOPMD - Objeto nuevo en la lista (parametro del servicio)
	        alumno.setId(ObjectConversionManager.convert(iterator.next(), java.math.BigDecimal.class));
		    alumnoList.add(alumno);
	    }
        this.alumnoService.removeMultiple(alumnoList);
		AlumnoController.logger.info("[POST - DELETE_ALL] : Alumno borrados correctamente");
		return alumnoIds;
	}	
	
	/**
	 * Method 'getAllJQGrid'.
	 *
	 * @param filterAlumno Alumno
	 * @param pagination Pagination
	 * @return JQGridJSONModel
	 */
	@RequestMapping(method = RequestMethod.GET, headers={"JQGridModel=true"})
	public @ResponseBody JQGridJSONModel getAllJQGrid(@ModelAttribute Alumno filterAlumno, @ModelAttribute Pagination pagination) {
        List<Alumno> alumnos = this.alumnoService.findAll(filterAlumno, pagination);
        Long recordNum = this.alumnoService.findAllCount(filterAlumno);
        AlumnoController.logger.info("[GET - jqGrid] : Obtener Alumnos");
		return new JQGridJSONModel(pagination, recordNum, alumnos);
	}
	
	
	@RequestMapping(value = "/imagen/{id}", method = RequestMethod.GET)
	public void getImagenAlumno(@PathVariable BigDecimal id, HttpServletResponse response) throws IOException {
		Alumno alumno = new Alumno();
		alumno.setId(id);
		alumno = this.alumnoService.getImagen(alumno);
		response.setHeader("Content-Disposition","attachment; filename=\"" + alumno.getNombreImagen() +"\"");
        byte[] fileByteArray = alumno.getImagen();
        response.setContentLength(fileByteArray.length);
        FileCopyUtils.copy(fileByteArray, response.getOutputStream());
	}
	
}	
	
