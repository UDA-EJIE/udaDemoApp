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
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hdiv.services.TrustAssertion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.Resource;
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
import com.ejie.x21a.service.TableAlumnoService;
import com.ejie.x21a.service.NoraAutonomiaService;
import com.ejie.x21a.service.NoraPaisService;
import com.ejie.x21a.validation.group.AlumnoAddValidation;
import com.ejie.x21a.validation.group.AlumnoEditValidation;
import com.ejie.x38.control.bind.annotation.RequestJsonBody;
import com.ejie.x38.dto.TableRequestDto;
import com.ejie.x38.dto.TableResourceResponseDto;
import com.ejie.x38.dto.TableRowDto;
import com.ejie.x38.hdiv.annotation.UDALink;
import com.ejie.x38.hdiv.annotation.UDALinkAllower;
import com.ejie.x38.util.DateTimeManager;
import com.ejie.x38.util.ResourceUtils;
import com.ejie.x38.validation.ValidationManager;

/**
 * AlumnoController
 * @author UDA
 */
 
@Controller
@RequestMapping (value = "/administracion/alumno")
public class TableAlumnoController  {

	private static final Logger logger = LoggerFactory.getLogger(TableAlumnoController.class);

	@Autowired
	private TableAlumnoService alumnoService;
	
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
	@UDALink(name = "getCreateForm")
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
		
		TableAlumnoController.logger.info("[GET - View] : alumno");
		return "alumno";
	}

	
	/*
	 * OPERACIONES CRUD
	 * 
	 * Metodos correspondientes a las operaciones CRUD (Create, Read, Update, Delete). 
	 * 
	 */
	
	/**
	 * Method 'get'.
	 *
	 * @param id BigDecimal
	 * @return alumno Alumno
	 */
	@UDALink(name = "get", linkTo = { 
			@UDALinkAllower(name = "edit"),
			@UDALinkAllower(name = "remove"), 
			@UDALinkAllower(name = "filter") })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Resource<Alumno> get(final @PathVariable @TrustAssertion(idFor = Alumno.class) BigDecimal id) {
		Alumno alumno = new Alumno();
		alumno.setId(id);
        alumno = this.alumnoService.find(alumno);
        TableAlumnoController.logger.info("[GET - findBy_PK] : Obtener Alumno por PK");
        return new Resource<Alumno>(alumno);
	}

	/**
	 * Method 'getAll'.
	 *
	 * @param filterAlumno Alumno
	 * @return List
	 */
	@UDALink(name = "getall", linkTo = { 
			@UDALinkAllower(name = "edit"), 
			@UDALinkAllower(name = "remove"),
			@UDALinkAllower(name = "get") })
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Resource<Alumno>> getAll(@ModelAttribute Alumno filterAlumno) {
		TableAlumnoController.logger.info("[GET - find_ALL] : Obtener Alumnos por filtro");
	    return ResourceUtils.fromListToResource(this.alumnoService.findAll(filterAlumno, null));
	}

	/**
	 * Method 'add'.
	 * @param alumno Alumno 
	 * @return Alumno
	 * @throws IOException 
	 */
	@UDALink(name = "add", linkTo = { @UDALinkAllower(name = "filter") })
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces="application/json")
	public @ResponseBody Resource<Object> add(@Validated(AlumnoAddValidation.class) 
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
        TableAlumnoController.logger.info("[POST] : Alumno insertado correctamente");
    	return new Resource<Object>(alumnoAux);
	}
	
	/**
	 * Method 'edit'.
	 * @param alumno Alumno 
	 * @return Alumno
	 * @throws IOException 
	 */
	@UDALink(name = "edit", linkTo = { @UDALinkAllower(name = "filter") })
	@RequestMapping(value = "/edit", method = RequestMethod.PUT, produces="application/json")
	public @ResponseBody Resource<Alumno> edit(
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
		TableAlumnoController.logger.info("[PUT] : Alumno actualizado correctamente");
        return new Resource<Alumno>(alumnoAux);
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
	 * Method 'remove'.
	 *
	 * @param id BigDecimal
	 * @return alumno
	 */
	@UDALink(name = "remove")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody Resource<Alumno> remove(@PathVariable @TrustAssertion(idFor = Alumno.class) BigDecimal id) {
        Alumno alumno = new Alumno();
        alumno.setId(id);
        this.alumnoService.remove(alumno);
       	TableAlumnoController.logger.info("[DELETE] : Alumno borrado correctamente");
       	return new Resource<Alumno>(alumno);
    }
		
	/*
	 * METODOS COMPONENTE RUP_TABLE
	 * 
	 */
	
	/**
	 * OperaciÃƒÂ³n de filtrado del componente RUP_TABLE.
	 * 
	 * @param Alumno
	 *            Bean que contiene los parÃƒÂ¡metros de filtrado a emplear.
	 * @param TableRequestDto
	 *            Dto que contiene los parÃƒÂ¡mtros de configuraciÃƒÂ³n propios del
	 *            RUP_TABLE a aplicar en el filtrado.
	 * @return Dto que contiene el resultado del filtrado realizado por el
	 *         componente RUP_TABLE.
	 * 
	 */
	//@Json(mixins={@JsonMixin(target=Usuario.class, mixin=UsuarioMixIn.class)})
	@UDALink(name = "filter", linkTo = { 
			@UDALinkAllower(name = "get"), 
			@UDALinkAllower(name = "getImagenAlumno"),
			@UDALinkAllower(name = "remove"), 
			@UDALinkAllower(name = "filter"),
			@UDALinkAllower(name = "deleteAll") })
	@RequestMapping(value = "/filter", method = RequestMethod.POST)
	public @ResponseBody TableResourceResponseDto<Alumno> filter(
			@RequestJsonBody(param="filter") Alumno filterAlumno,
			@RequestJsonBody TableRequestDto tableRequestDto) {
		
		TableAlumnoController.logger.info("[POST - table] : Obtener Alumnos");
		return this.alumnoService.filter(filterAlumno, tableRequestDto, false);
	}
	
	/**
	 * OperaciÃƒÂ³n de bÃƒÂºsqueda del componente RUP_TABLE.
	 * 
	 * @param filterAlumno
	 *            Bean que contiene los parÃƒÂ¡metros de filtrado a emplear.
	 * @param searchAlumno
	 *            Bean que contiene los parÃƒÂ¡metros de bÃƒÂºsqueda a emplear.
	 * @param TableRequestDto
	 *            Dto que contiene los parÃƒÂ¡mtros de configuraciÃƒÂ³n propios del
	 *            RUP_TABLE a aplicar en la bÃƒÂºsqueda.
	 * @return Lista de lineas de la tabla que se corresponden con los registros
	 *         que se ajustan a los parÃƒÂ¡metros de bÃƒÂºsqueda.
	 * 
	 */
	@UDALink(name = "search", linkTo = { @UDALinkAllower(name = "filter") })
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public @ResponseBody List<TableRowDto<Alumno>> search(
			@RequestJsonBody(param="filter") Alumno filterAlumno,
			@RequestJsonBody(param="search") Alumno searchAlumno,
			@RequestJsonBody TableRequestDto tableRequestDto){
		
		TableAlumnoController.logger.info("[POST - search] : Buscar Alumnos");
		return this.alumnoService.search(filterAlumno, searchAlumno, tableRequestDto, false);
	}
	
	/**
	 * Method 'removeAll'.
	 *
	 * @param alumnoIds List
	 * @return alumnoList
	 */	
	@UDALink(name = "deleteAll")
	@RequestMapping(value = "/deleteAll", method = RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.OK)
	public @ResponseBody List<String> removeMultiple(
			@RequestJsonBody(param="filter") Alumno filterAlumno,
			@RequestJsonBody TableRequestDto tableRequestDto) {
		TableAlumnoController.logger.info("[POST - removeMultiple] : Eliminar multiples usuarios");
	    this.alumnoService.removeMultiple(filterAlumno, tableRequestDto, false);
	    TableAlumnoController.logger.info("All entities correctly deleted!");
	    
	    return tableRequestDto.getMultiselection().getSelectedIds();
	}	
	
	@UDALink(name = "getImagenAlumno", linkTo = { 
			@UDALinkAllower(name = "edit"),
			@UDALinkAllower(name = "remove"), 
			@UDALinkAllower(name = "filter") })
	@RequestMapping(value = "/imagen/{id}", method = RequestMethod.GET)
	public void getImagenAlumno(@PathVariable @TrustAssertion(idFor = Alumno.class) BigDecimal id, HttpServletResponse response) throws IOException {
		Alumno alumno = new Alumno();
		alumno.setId(id);
		alumno = this.alumnoService.getImagen(alumno);
		response.setHeader("Content-Disposition","attachment; filename=\"" + alumno.getNombreImagen() +"\"");
        byte[] fileByteArray = alumno.getImagen();
        response.setContentLength(fileByteArray.length);
        FileCopyUtils.copy(fileByteArray, response.getOutputStream());
	}	
}	
