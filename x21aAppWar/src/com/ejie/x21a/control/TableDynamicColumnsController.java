/*
* Copyright 2019 E.J.I.E., S.A.
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

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

import com.ejie.x21a.model.Usuario;
import com.ejie.x21a.service.TableUsuarioService;
import com.ejie.x21a.util.ResourceUtils;
import com.ejie.x38.control.bind.annotation.RequestJsonBody;
import com.ejie.x38.dto.TableRequestDto;
import com.ejie.x38.dto.TableResourceResponseDto;
import com.ejie.x38.dto.TableRowDto;
import com.ejie.x38.hdiv.annotation.UDALink;
import com.ejie.x38.hdiv.annotation.UDALinkAllower;
import com.ejie.x38.util.DateTimeManager;


@Controller
@RequestMapping (value = "/table/dynamicColumns")
public class TableDynamicColumnsController  {
	private static final Logger logger = LoggerFactory.getLogger(TableDynamicColumnsController.class);

	@Autowired
	private TableUsuarioService tableUsuarioService;
	
	@javax.annotation.Resource
	private ReloadableResourceBundleMessageSource messageSource;
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws ServletException {
		binder.registerCustomEditor(byte[].class,new ByteArrayMultipartFileEditor());
		binder.registerCustomEditor(Date.class, new CustomDateEditor(DateTimeManager.getDateTimeFormat(LocaleContextHolder.getLocale()), true));
		NumberFormat numberFormat = NumberFormat.getInstance(LocaleContextHolder.getLocale());
		binder.registerCustomEditor(BigDecimal.class, new CustomNumberEditor(BigDecimal.class, numberFormat, true));
	}

	/*
	 * OPERACIONES CRUD
	 * 
	 * Metodos correspondientes a las operaciones CRUD (Create, Read, Update, Delete). 
	 * 
	 */
	
	/**
	 * OperaciÃƒÂ³n CRUD Read. Devuelve el bean correspondiente al identificador
	 * indicado.
	 * 
	 * @param id
	 *            Identificador del objeto que se desea recuperar.
	 * @return Objeto correspondiente al identificador indicado.
	 */
	@UDALink(name = "get", linkTo = { @UDALinkAllower(name = "edit"), @UDALinkAllower(name = "remove"), @UDALinkAllower(name = "filter")})
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Resource<Usuario> get(@PathVariable String id) {
        Usuario usuario = new Usuario();
		usuario.setId(id);
        usuario = this.tableUsuarioService.find(usuario);
		TableDynamicColumnsController.logger.info("[GET - findBy_PK] : Obtener Usuarios por PK");
        return new Resource<Usuario>(usuario);
	}
	
	@UDALink(name = "getFiltroSimple", linkTo = {
			@UDALinkAllower(name = "getTableInlineEdit"),
			@UDALinkAllower(name = "getApellidos", linkClass = TableUsuarioController.class),
			@UDALinkAllower(name = "getRoles", linkClass = TableUsuarioController.class),
			@UDALinkAllower(name = "excelReport"),
			@UDALinkAllower(name = "pdfReport"),
			@UDALinkAllower(name = "odsReport"),
			@UDALinkAllower(name = "csvReport")})
	@RequestMapping(method = RequestMethod.GET)
	public String getFiltroSimple (Model model) {
		model.addAttribute("usuario", new Usuario());
		
		return "tableDynamicColumns";
	}
	
	@UDALink(name = "getTableInlineEdit", linkTo = {
			@UDALinkAllower(name = "get"),
			@UDALinkAllower(name = "add"),
			@UDALinkAllower(name = "edit"),
			@UDALinkAllower(name = "filter")})
	@RequestMapping(value = "/inlineEdit", method = RequestMethod.POST)
	public String getTableInlineEdit (
			@RequestParam(required = true) String actionType,
			@RequestParam(required = true) String tableID,
			@RequestParam(required = false) String mapping,
			Model model) {
		model.addAttribute("entity", new Usuario());
		model.addAttribute("actionType", actionType);
		model.addAttribute("tableID", tableID);
		
		// Controlar que el mapping siempre se a�ada al modelo de la manera esperada
		if (mapping == null || mapping.isEmpty()) {
			mapping = "/table/dynamicColumns";
		} else if (mapping.endsWith("/")) {
			mapping = mapping.substring(0, mapping.length() - 1);
		}
		model.addAttribute("mapping", mapping);
		
		return "tableInlineEditAuxForm";
	}
	
	/**
	 * Devuelve una lista de beans correspondientes a los valores de filtrados
	 * indicados en el objeto pasado como parÃƒÂ¡metro.
	 * 
	 * @param Usuario
	 *            Objeto que contiene los parÃƒÂ¡metros de filtrado utilizados en
	 *            la bÃƒÂºsqueda.
	 * @return Lista de objetos correspondientes a la bÃƒÂºsqueda realizada.
	 */
	@UDALink(name = "getall", linkTo = { @UDALinkAllower(name = "edit" ), @UDALinkAllower(name = "remove" ), @UDALinkAllower(name = "get" )})
	@RequestMapping(value = "/all",method = RequestMethod.GET)
	public @ResponseBody List<Resource<Usuario>> getAll(@ModelAttribute() Usuario usuarioFilter){
		TableDynamicColumnsController.logger.info("[GET - find_ALL] : Obtener Usuarios por filtro");
		return ResourceUtils.fromListToResource(this.tableUsuarioService.findAllLike(usuarioFilter, null, false));
	}
	
	/**
	 * OperaciÃƒÂ³n CRUD Edit. ModificaciÃƒÂ³n del bean indicado.
	 * 
	 * @param Usuario
	 *            Bean que contiene la informaciÃƒÂ³n a modificar.
	 * @return Bean resultante de la modificaciÃƒÂ³n.
	 */
	@UDALink(name = "edit", linkTo = {@UDALinkAllower(name = "filter")})
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public @ResponseBody Resource<Usuario> edit(@Validated @RequestBody Usuario usuario) {
		Usuario usuarioAux = this.tableUsuarioService.update(usuario);
		logger.info("Entity correctly updated!");
        return new Resource<Usuario>(usuarioAux);
    }
	
	@UDALink(name = "editar")
	@RequestMapping(value = "/editar", method = RequestMethod.PUT, produces="application/json")
    public @ResponseBody Resource<Usuario> editar(
    		@Validated @ModelAttribute Usuario usuario,
    		@RequestParam(value = "imagenAlumno", required = false) MultipartFile imagen,
    HttpServletRequest request, HttpServletResponse response){
		System.out.print("USUARIO::::"+usuario.getId()+" --- "+new Date()+"\n");
		if (imagen!=null){
			System.out.print("IMAGEN::::"+imagen);
        }
        Usuario usuarioAux = this.tableUsuarioService.update(usuario);
		logger.info("Entity correctly updated!");
        return new Resource<Usuario>(usuarioAux);
    }

	/**
	 * OperaciÃƒÂ³n CRUD Create. CreaciÃƒÂ³n de un nuevo registro a partir del bean
	 * indicado.
	 * 
	 * @param Usuario
	 *            Bean que contiene la informaciÃƒÂ³n con la que se va a crear el
	 *            nuevo registro.
	 * @return Bean resultante del proceso de creaciÃƒÂ³n.
	 */
	@UDALink(name = "add", linkTo = {@UDALinkAllower(name = "filter")})
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody Resource<Usuario> add(@Validated @RequestBody Usuario usuario) {		
		Usuario usuarioAux = this.tableUsuarioService.add(usuario);
        logger.info("Entity correctly inserted!");	
    	return new Resource<Usuario>(usuarioAux);
	}
	

	/**
	 * OperaciÃƒÂ³n CRUD Delete. Borrado del registro correspondiente al
	 * identificador especificado.
	 * 
	 * @param id
	 *            Identificador del objeto que se desea eliminar.
	 * @return Bean eliminado.
	 */
	@UDALink(name = "remove")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value=HttpStatus.OK)
    public @ResponseBody Resource<Usuario> remove(@PathVariable(value="id") String id, HttpServletResponse  response) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        this.tableUsuarioService.remove(usuario);
        logger.info("Entity correctly deleted!");
        return new Resource<Usuario>(usuario);
    }
	
	
	/*
	 * METODOS COMPONENTE RUP_TABLE
	 * 
	 */
	
	/**
	 * OperaciÃƒÂ³n de filtrado del componente RUP_TABLE.
	 * 
	 * @param Usuario
	 *            Bean que contiene los parÃƒÂ¡metros de filtrado a emplear.
	 * @param TableRequestDto
	 *            Dto que contiene los parÃƒÂ¡mtros de configuraciÃƒÂ³n propios del
	 *            RUP_TABLE a aplicar en el filtrado.
	 * @return Dto que contiene el resultado del filtrado realizado por el
	 *         componente RUP_TABLE.
	 * 
	 */
	//@Json(mixins={@JsonMixin(target=Usuario.class, mixin=UsuarioMixIn.class)})
	@UDALink(name = "filter", linkTo = { @UDALinkAllower(name = "get"), @UDALinkAllower(name = "remove"), @UDALinkAllower(name = "filter"), @UDALinkAllower(name = "deleteAll")})
	@RequestMapping(value = "/filter", method = RequestMethod.POST)
	public @ResponseBody TableResourceResponseDto<Usuario> filter(
			@RequestJsonBody(param="filter") Usuario filterUsuario,
			@RequestJsonBody TableRequestDto tableRequestDto) {
		TableDynamicColumnsController.logger.info("[POST - table] : Obtener Usuarios");
		return tableUsuarioService.filter(filterUsuario, tableRequestDto, false);
	}
	
	/**
	 * OperaciÃƒÂ³n de bÃƒÂºsqueda del componente RUP_TABLE.
	 * 
	 * @param filterUsuario
	 *            Bean que contiene los parÃƒÂ¡metros de filtrado a emplear.
	 * @param searchUsuario
	 *            Bean que contiene los parÃƒÂ¡metros de bÃƒÂºsqueda a emplear.
	 * @param TableRequestDto
	 *            Dto que contiene los parÃƒÂ¡mtros de configuraciÃƒÂ³n propios del
	 *            RUP_TABLE a aplicar en la bÃƒÂºsqueda.
	 * @return Lista de lineas de la tabla que se corresponden con los registros
	 *         que se ajustan a los parÃƒÂ¡metros de bÃƒÂºsqueda.
	 * 
	 */
	@UDALink(name = "search", linkTo = { @UDALinkAllower(name = "filter")})
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public @ResponseBody List<TableRowDto<Usuario>> search(
			@RequestJsonBody(param="filter") Usuario filterUsuario,
			@RequestJsonBody(param="search") Usuario searchUsuario,
			@RequestJsonBody TableRequestDto tableRequestDto){
		TableDynamicColumnsController.logger.info("[POST - search] : Buscar Usuarios");
		return tableUsuarioService.search(filterUsuario, searchUsuario, tableRequestDto, false);
	}
	
	/**
	 * Borrado mÃƒÆ’Ã‚Âºltiple de registros
	 * 
	 * @param filterUsuario Usuario
	 *            Bean que contiene los parÃƒÆ’Ã‚Â¡metros de filtrado a emplear.
	 * @param TableRequestDto
	 *            Dto que contiene los parÃƒÆ’Ã‚Â¡mtros de configuraciÃƒÆ’Ã‚Â³n propios del
	 *            RUP_TABLE a aplicar en la bÃƒÆ’Ã‚Âºsqueda.
	 * @return Lista de los identificadores de los registros eliminados.
	 */
	@UDALink(name = "deleteAll")
	@RequestMapping(value = "/deleteAll", method = RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.OK)
	public @ResponseBody List<String> removeMultiple(
			@RequestJsonBody(param="filter") Usuario filterUsuario,
			@RequestJsonBody TableRequestDto tableRequestDto) {
		TableDynamicColumnsController.logger.info("[POST - removeMultiple] : Eliminar multiples usuarios");
	    this.tableUsuarioService.removeMultiple(filterUsuario, tableRequestDto, false);
	    TableDynamicColumnsController.logger.info("All entities correctly deleted!");
	    
	    return tableRequestDto.getMultiselection().getSelectedIds();
	}
	
	/*
	 * EXPORTACIONES DE DATOS
	 */
	
	/**
	 * Devuelve los datos exportados de la tabla.
	 *
	 * @param filterUsuario Usuario
	 * @param tableRequestDto TableRequestDto
	 */
	@UDALink(name = "clipboardReport")
	@RequestMapping(value = "/clipboardReport", method = RequestMethod.POST)
	public @ResponseBody List<Resource<Usuario>> getClipboardReport(
			@RequestJsonBody(param = "filter", required = false) Usuario filterUsuario,
			@RequestJsonBody(param = "columns", required = false) String[] columns, 
			@RequestJsonBody(param = "columnsName", required = false) String[] columnsName,
			@RequestJsonBody TableRequestDto tableRequestDto) {
		TableDynamicColumnsController.logger.info("[POST - clipboardReport] : Copiar multiples usuarios");
		return ResourceUtils.fromListToResource(this.tableUsuarioService.getDataForReports(filterUsuario, tableRequestDto));
	}
	
	/**
	 * Devuelve un fichero excel que contiene los datos exportados de la tabla.
	 *
	 * @param filterUsuario Usuario
	 * @param columns String[]
	 * @param columnsName String[]
	 * @param fileName String
	 * @param sheetTitle String
	 * @param reportsParams ArrayList<?>
	 * @param tableRequestDto TableRequestDto
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */	
	@UDALink(name = "excelReport")
	@RequestMapping(value = {"/xlsReport" , "/xlsxReport"}, method = RequestMethod.POST, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public @ResponseBody void generateExcelReport(
			@RequestJsonBody(param = "filter", required = false) Usuario filterUsuario, 
			@RequestJsonBody(param = "columns", required = false) String[] columns, 
			@RequestJsonBody(param = "columnsName", required = false) String[] columnsName, 
			@RequestJsonBody(param = "fileName", required = false) String fileName, 
			@RequestJsonBody(param = "sheetTitle", required = false) String sheetTitle,
			@RequestJsonBody(param = "reportsParams", required = false) ArrayList<?> reportsParams,
			@RequestJsonBody TableRequestDto tableRequestDto,
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException{
		TableDynamicColumnsController.logger.info("[POST - generateExcelReport] : Devuelve un fichero excel");
		//Idioma
        Locale locale = LocaleContextHolder.getLocale();
		this.tableUsuarioService.generateReport(filterUsuario, columns, columnsName, fileName, sheetTitle, reportsParams, tableRequestDto, locale, request, response);
    }
	
	/**
	 * Devuelve un fichero pdf que contiene los datos exportados de la tabla.
	 *
	 * @param filterUsuario Usuario
	 * @param columns String[]
	 * @param columnsName String[]
	 * @param fileName String
	 * @param sheetTitle String
	 * @param reportsParams ArrayList<?>
	 * @param tableRequestDto TableRequestDto
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	@UDALink(name = "pdfReport")
	@RequestMapping(value = "pdfReport", method = RequestMethod.POST, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public @ResponseBody void generatePDFReport(
			@RequestJsonBody(param = "filter", required = false) Usuario filterUsuario, 
			@RequestJsonBody(param = "columns", required = false) String[] columns, 
			@RequestJsonBody(param = "columnsName", required = false) String[] columnsName,
			@RequestJsonBody(param = "fileName", required = false) String fileName, 
			@RequestJsonBody(param = "sheetTitle", required = false) String sheetTitle,
			@RequestJsonBody(param = "reportsParams", required = false) ArrayList<?> reportsParams,
			@RequestJsonBody TableRequestDto tableRequestDto,
			HttpServletRequest request,
			HttpServletResponse response){
		TableDynamicColumnsController.logger.info("[POST - generatePDFReport] : Devuelve un fichero pdf");
		//Idioma
        Locale locale = LocaleContextHolder.getLocale();
		this.tableUsuarioService.generateReport(filterUsuario, columns, columnsName, fileName, sheetTitle, reportsParams, tableRequestDto, locale, request, response);
	}
	
	/**
	 * Devuelve un fichero ods que contiene los datos exportados de la tabla.
	 *
	 * @param filterUsuario Usuario
	 * @param columns String[]
	 * @param columnsName String[]
	 * @param fileName String
	 * @param sheetTitle String
	 * @param reportsParams ArrayList<?>
	 * @param tableRequestDto TableRequestDto
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	@UDALink(name = "odsReport")
	@RequestMapping(value = "odsReport", method = RequestMethod.POST, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public @ResponseBody void generateODSReport(
			@RequestJsonBody(param = "filter", required = false) Usuario filterUsuario, 
			@RequestJsonBody(param = "columns", required = false) String[] columns, 
			@RequestJsonBody(param = "columnsName", required = false) String[] columnsName,
			@RequestJsonBody(param = "fileName", required = false) String fileName, 
			@RequestJsonBody(param = "sheetTitle", required = false) String sheetTitle,
			@RequestJsonBody(param = "reportsParams", required = false) ArrayList<?> reportsParams,
			@RequestJsonBody TableRequestDto tableRequestDto,
			HttpServletRequest request,
			HttpServletResponse response){
		TableDynamicColumnsController.logger.info("[POST - generateODSReport] : Devuelve un fichero ods");
		//Idioma
        Locale locale = LocaleContextHolder.getLocale();
		this.tableUsuarioService.generateReport(filterUsuario, columns, columnsName, fileName, sheetTitle, reportsParams, tableRequestDto, locale, request, response);
	}
	
	/**
	 * Devuelve un fichero csv que contiene los datos exportados de la tabla.
	 *
	 * @param filterUsuario Usuario
	 * @param columns String[]
	 * @param columnsName String[]
	 * @param fileName String
	 * @param sheetTitle String
	 * @param reportsParams ArrayList<?>
	 * @param tableRequestDto TableRequestDto
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	@UDALink(name = "csvReport")
	@RequestMapping(value = "csvReport", method = RequestMethod.POST, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public @ResponseBody void generateCSVReport(
			@RequestJsonBody(param = "filter", required = false) Usuario filterUsuario, 
			@RequestJsonBody(param = "columns", required = false) String[] columns, 
			@RequestJsonBody(param = "columnsName", required = false) String[] columnsName,
			@RequestJsonBody(param = "fileName", required = false) String fileName, 
			@RequestJsonBody(param = "sheetTitle", required = false) String sheetTitle,
			@RequestJsonBody(param = "reportsParams", required = false) ArrayList<?> reportsParams,
			@RequestJsonBody TableRequestDto tableRequestDto,
			HttpServletRequest request,
			HttpServletResponse response){
		TableDynamicColumnsController.logger.info("[POST - generateCSVReport] : Devuelve un fichero csv");
		//Idioma
        Locale locale = LocaleContextHolder.getLocale();
		this.tableUsuarioService.generateReport(filterUsuario, columns, columnsName, fileName, sheetTitle, reportsParams, tableRequestDto, locale, request, response);
	}
}