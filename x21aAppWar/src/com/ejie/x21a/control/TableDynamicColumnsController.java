/*
* Copyright 2019 E.J.I.E., S.A.
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
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
import org.springframework.web.servlet.ModelAndView;

import com.ejie.x21a.model.Usuario;
import com.ejie.x21a.service.TableUsuarioService;
import com.ejie.x21a.service.UsuarioService;
import com.ejie.x38.control.bind.annotation.RequestJsonBody;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;
import com.ejie.x38.dto.JerarquiaDto;
import com.ejie.x38.dto.TableRequestDto;
import com.ejie.x38.dto.TableResponseDto;
import com.ejie.x38.dto.TableRowDto;
import com.ejie.x38.reports.ReportData;
import com.ejie.x38.util.DateTimeManager;


@Controller
@RequestMapping (value = "/table/dynamicColumns")
public class TableDynamicColumnsController  {

	private static final Logger logger = LoggerFactory.getLogger(TableDynamicColumnsController.class);

	@Autowired
	private TableUsuarioService tableUsuarioService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Resource
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
	 * Operación CRUD Read. Devuelve el bean correspondiente al identificador
	 * indicado.
	 * 
	 * @param id
	 *            Identificador del objeto que se desea recuperar.
	 * @return Objeto correspondiente al identificador indicado.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Usuario get(@PathVariable String id) {
        Usuario usuario = new Usuario();
		usuario.setId(id);
        usuario = this.tableUsuarioService.find(usuario);
        
        return usuario;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String getFiltroSimple (Model model) {
		
		return "tableDynamicColumns";
	}
	
	/**
	 * Devuelve una lista de beans correspondientes a los valores de filtrados
	 * indicados en el objeto pasado como parámetro.
	 * 
	 * @param Usuario
	 *            Objeto que contiene los parámetros de filtrado utilizados en
	 *            la búsqueda.
	 * @return Lista de objetos correspondientes a la búsqueda realizada.
	 */
	@RequestMapping(value = "/all",method = RequestMethod.GET)
	public @ResponseBody List<Usuario> getAll(@ModelAttribute() Usuario usuarioFilter){
		TableDynamicColumnsController.logger.info("[GET - find_ALL] : Obtener Usuarios por filtro");
		return this.tableUsuarioService.findAllLike(usuarioFilter, null, false);
	}
	
	/**
	 * Operación CRUD Edit. Modificación del bean indicado.
	 * 
	 * @param Usuario
	 *            Bean que contiene la información a modificar.
	 * @return Bean resultante de la modificación.
	 */
	@RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody Usuario edit(@Validated @RequestBody Usuario usuario) {
		if (usuario.getEjie()==null){
			usuario.setEjie("0");
		}
        Usuario usuarioAux = this.tableUsuarioService.update(usuario);
		logger.info("Entity correctly updated!");
        return usuarioAux;
    }
	
	@RequestMapping(value = "/editar", method = RequestMethod.PUT, produces="application/json")
    public @ResponseBody Usuario editar(
    		@Validated @ModelAttribute Usuario usuario,
    		@RequestParam(value = "imagenAlumno", required = false) MultipartFile imagen,
    HttpServletRequest request, HttpServletResponse response){
		System.out.print("USUARIO::::"+usuario.getId()+" --- "+new Date()+"\n");
		if (usuario.getEjie()==null){
			usuario.setEjie("0");
		}
		if (imagen!=null){
			System.out.print("IMAGEN::::"+imagen);
        }
        Usuario usuarioAux = this.tableUsuarioService.update(usuario);
		logger.info("Entity correctly updated!");
        return usuarioAux;
    }

	/**
	 * Operación CRUD Create. Creación de un nuevo registro a partir del bean
	 * indicado.
	 * 
	 * @param Usuario
	 *            Bean que contiene la información con la que se va a crear el
	 *            nuevo registro.
	 * @return Bean resultante del proceso de creación.
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Usuario add(@Validated @RequestBody Usuario usuario) {		
		if (usuario.getEjie()==null){
			usuario.setEjie("0");
		}
        Usuario usuarioAux = this.tableUsuarioService.add(usuario);
        logger.info("Entity correctly inserted!");	
    	return usuarioAux;
	}
	

	/**
	 * Operación CRUD Delete. Borrado del registro correspondiente al
	 * identificador especificado.
	 * 
	 * @param id
	 *            Identificador del objeto que se desea eliminar.
	 * @return Bean eliminado.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value=HttpStatus.OK)
    public @ResponseBody Usuario remove(@PathVariable(value="id") String id, HttpServletResponse  response) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        this.tableUsuarioService.remove(usuario);
        logger.info("Entity correctly deleted!");
        return usuario;
    }
	
	
	/*
	 * METODOS COMPONENTE RUP_TABLE
	 * 
	 */
	
	/**
	 * Operación de filtrado del componente RUP_TABLE.
	 * 
	 * @param Usuario
	 *            Bean que contiene los parámetros de filtrado a emplear.
	 * @param JQGridRequestDto
	 *            Dto que contiene los parámtros de configuración propios del
	 *            RUP_TABLE a aplicar en el filtrado.
	 * @return Dto que contiene el resultado del filtrado realizado por el
	 *         componente RUP_TABLE.
	 * 
	 */
	//@Json(mixins={@JsonMixin(target=Usuario.class, mixin=UsuarioMixIn.class)})
	@RequestMapping(value = "/filter", method = RequestMethod.POST)
	public @ResponseBody TableResponseDto<Usuario> filter(
			@RequestJsonBody(param="filter") Usuario filterUsuario,
			@RequestJsonBody TableRequestDto tableRequestDto) {
		TableDynamicColumnsController.logger.info("[POST - jqGrid] : Obtener Usuarios");
		return tableUsuarioService.filter(filterUsuario, tableRequestDto, false);
	}
	
	/**
	 * Operación de búsqueda del componente RUP_TABLE.
	 * 
	 * @param filterUsuario
	 *            Bean que contiene los parámetros de filtrado a emplear.
	 * @param searchUsuario
	 *            Bean que contiene los parámetros de búsqueda a emplear.
	 * @param JQGridRequestDto
	 *            Dto que contiene los parámtros de configuración propios del
	 *            RUP_TABLE a aplicar en la búsqueda.
	 * @return Lista de lineas de la tabla que se corresponden con los registros
	 *         que se ajustan a los parámetros de búsqueda.
	 * 
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public @ResponseBody List<TableRowDto<Usuario>> search(
			@RequestJsonBody(param="filter") Usuario filterUsuario,
			@RequestJsonBody(param="search") Usuario searchUsuario,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto){
		TableDynamicColumnsController.logger.info("[POST - search] : Buscar Usuarios");
		return tableUsuarioService.search(filterUsuario, searchUsuario, jqGridRequestDto, false);
	}
	
	/**
	 * Borrado múltiple de registros
	 * 
	 * @param filterUsuario
	 *            Bean que contiene los parámetros de filtrado a emplear.
	 * @param JQGridRequestDto
	 *            Dto que contiene los parámtros de configuración propios del
	 *            RUP_TABLE a aplicar en la búsqueda.
	 * @return Lista de los identificadores de los registros eliminados.
	 */
	@RequestMapping(value = "/deleteAll", method = RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.OK)
	public @ResponseBody List<String> removeMultiple(
			@RequestJsonBody(param="filter") Usuario filterUsuario,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto) {
		TableDynamicColumnsController.logger.info("[POST - removeMultiple] : Eliminar multiples usuarios");
	    this.tableUsuarioService.removeMultiple(filterUsuario, jqGridRequestDto, false);
	    TableDynamicColumnsController.logger.info("All entities correctly deleted!");
	    
	    return jqGridRequestDto.getMultiselection().getSelectedIds();
	}
	
	
	/**
	 * EXPORTERS
	 */
	
	@RequestMapping(value = "/clipboardReport", method = RequestMethod.POST)
	protected @ResponseBody List<Usuario> getClipboardReport(
			@RequestJsonBody(param="filter") Usuario  filterUsuario ,
			@RequestJsonBody TableRequestDto  tableRequestDto) {
		//UsuarioController.logger.info("[POST - clipboardReport] : : Copiar multiples usuarios");
		return this.usuarioService.getMultiple(filterUsuario, tableRequestDto, false);
	}
	
	@RequestMapping(value = {"xlsReportViejo" , "xlsxReportViejo"}, method = RequestMethod.POST)
	protected ModelAndView getExcelPOIViejo(@ModelAttribute Usuario filterUsuario, @ModelAttribute JQGridRequestDto tableRequestDto,
			ModelMap modelMap,
			@RequestParam(value = "columns", required = false) String columns,
			HttpServletRequest request){
		System.out.print(" NUEVO \n");
		//Acceso a BD para recuperar datos
		/*List<Usuario> listUsuarioPage = this.tableUsuarioService.findAllLike(filterUsuario, jqGridRequestDto, false);
		jqGridRequestDto.setPage(null);
		jqGridRequestDto.setRows(null);
		List<Usuario> listUsuarioAll = this.tableUsuarioService.findAllLike(filterUsuario, jqGridRequestDto, false);*/
		List<Usuario> listUsuarioAll = null;//this.usuarioService.getMultiple(filterUsuario, tableRequestDto, false);
		//Nombre fichero
		modelMap.put("fileName", "datosExcel");
		
		//Datos
		List<Object> reportData = new ArrayList<Object>();
			//Hoja 1
			ReportData<Usuario> usuarioExcelDataAll = new ReportData<Usuario>();
				//nombre hoja
				usuarioExcelDataAll.setSheetName("Todos los usuarios");
				//cabeceras hoja
				if(columns != null){
					usuarioExcelDataAll.setHeaderNames(ReportData.parseColumns(columns));
				}
				//datos hoja
				usuarioExcelDataAll.setModelData(listUsuarioAll);
			reportData.add(usuarioExcelDataAll);
			//Hoja 2
			ReportData<Usuario> usuarioExcelDataPage = new ReportData<Usuario>();
				//nombre hoja
				usuarioExcelDataPage.setSheetName("Página 1 de usuarios");
				//cabeceras hoja
				usuarioExcelDataPage.setHeaderNames(ReportData.parseColumns(columns));
				//datos hoja
				usuarioExcelDataPage.setModelData(listUsuarioAll);
			reportData.add(usuarioExcelDataPage);
		modelMap.put("reportData", reportData);
		
		//Generación del XLS o XLSX

		if (request.getServletPath().indexOf("xlsReport")!=-1){
			return new ModelAndView("xlsReport", modelMap);
		}else{
			return new ModelAndView("xlsxReport", modelMap);
		}

		
	}
	
	@RequestMapping(value = "csvReport", method = RequestMethod.POST)
	protected ModelAndView getCSVReport(@ModelAttribute Usuario filterUsuario, @ModelAttribute JQGridRequestDto jqGridRequestDto,
			ModelMap modelMap,
			@RequestParam(value = "columns", required = false) String columns){
		
		//Acceso a BD para recuperar datos
		jqGridRequestDto.setPage(null);
		jqGridRequestDto.setRows(null);
		List<Usuario> filter = this.tableUsuarioService.findAllLike(filterUsuario, jqGridRequestDto, false);
		 
		//Nombre fichero
		modelMap.put("fileName", "datosCSV");
			
		//Datos
		ReportData<Usuario> reportData = new ReportData<Usuario>();
			//cabeceras hoja
			reportData.setHeaderNames(ReportData.parseColumns(columns));
			//datos hoja
			reportData.setModelData(filter);
		modelMap.put("reportData", reportData);
		
		//Generación del CVS
		return new ModelAndView("csvReport", modelMap);
	}
	
	@RequestMapping(value = {"xlsReport" , "xlsxReport"}, method = RequestMethod.POST)
	protected ModelAndView getExcelPOI(@ModelAttribute Usuario filterUsuario, 
			@ModelAttribute TableRequestDto tableRequestDto,
			ModelMap modelMap,
			@RequestParam(value = "columns", required = false) String columns,
			HttpServletRequest request){
		
		try{

		List<Usuario> listUsuarioAll = this.usuarioService.getMultiple(filterUsuario, tableRequestDto, false);
		
		//Nombre fichero
		modelMap.put("fileName", "datosExcel");
		
		//Datos
		List<Object> reportData = new ArrayList<Object>();
			//Hoja 1
			ReportData<Usuario> usuarioExcelDataAll = new ReportData<Usuario>();
				//nombre hoja
				usuarioExcelDataAll.setSheetName("Listado de usuarios");
				//cabeceras hoja
				usuarioExcelDataAll.setHeaderNames(ReportData.parseColumns(columns));
				//datos hoja
				usuarioExcelDataAll.setModelData(listUsuarioAll);
			reportData.add(usuarioExcelDataAll);
			//Hoja 2
		/*	ReportData<Usuario> usuarioExcelDataPage = new ReportData<Usuario>();
				//nombre hoja
				usuarioExcelDataPage.setSheetName("Página 1 de usuarios");
				//cabeceras hoja
				usuarioExcelDataPage.setHeaderNames(ReportData.parseColumns(columns));
				//datos hoja
				usuarioExcelDataPage.setModelData(listUsuarioAll);
			reportData.add(usuarioExcelDataPage);*/
		modelMap.put("reportData", reportData);
		}catch (Exception e) {
			// TODO: handle exception
		}
		//Generación del XLS o XLSX
		if (request.getServletPath().indexOf("xlsReport")!=-1){
			return new ModelAndView("xlsReport", modelMap);
		}else{
			return new ModelAndView("xlsxReport", modelMap);
		}
		
	}
	
	
	@RequestMapping(value = "odsReport", method = RequestMethod.POST)
	protected ModelAndView getODSReport(@ModelAttribute Usuario filterUsuario, @ModelAttribute JQGridRequestDto jqGridRequestDto,
			ModelMap modelMap,
			@RequestParam(value = "columns", required = false) String columns){
		
		//Acceso a BD para recuperar datos
		List<Usuario> listUsuarioPage = this.tableUsuarioService.findAllLike(filterUsuario, jqGridRequestDto, false);
		jqGridRequestDto.setPage(null);
		jqGridRequestDto.setRows(null);
		List<Usuario> listUsuarioAll = this.tableUsuarioService.findAllLike(filterUsuario, jqGridRequestDto, false);
		
		//Nombre fichero
		modelMap.put("fileName", "datosODS");
		
		//Datos
		List<Object> reportData = new ArrayList<Object>();
			//Hoja 1
			ReportData<Usuario> usuarioExcelDataAll = new ReportData<Usuario>();
				//nombre hoja
				usuarioExcelDataAll.setSheetName("Todos los usuarios");
				//cabeceras hoja
				usuarioExcelDataAll.setHeaderNames(ReportData.parseColumns(columns));
				//datos hoja
				usuarioExcelDataAll.setModelData(listUsuarioAll);
			reportData.add(usuarioExcelDataAll);
			//Hoja 2
			ReportData<Usuario> usuarioExcelDataPage = new ReportData<Usuario>();
				//nombre hoja
				usuarioExcelDataPage.setSheetName("Página 1 de usuarios");
				//cabeceras hoja
				usuarioExcelDataPage.setHeaderNames(ReportData.parseColumns(columns));
				//datos hoja
				usuarioExcelDataPage.setModelData(listUsuarioPage);
			reportData.add(usuarioExcelDataPage);
		modelMap.put("reportData", reportData);
		
		//Generación del ODS
		return new ModelAndView("odsReport", modelMap);
	}
	
	@RequestMapping(value="pdfReport")
	public ModelAndView generarPDFJasperReport(@ModelAttribute Usuario filterUsuario, @ModelAttribute JQGridRequestDto jqGridRequestDto,
			ModelMap modelMap,
			@RequestParam(value = "isInline", required = false) boolean isInline){
		
		//Acceso a BD para recuperar datos
		List<Usuario> usuarios = this.tableUsuarioService.findAll(new Usuario(), null);
		
		//Nombre fichero
		modelMap.put("fileName", "datosPDF");
		
		//En línea (no descarga fichero) ?
		modelMap.put("isInline", isInline);
		
		//Titulo y cabeceras (parameter)
		modelMap.put("TITULO", "Listado usuarios");
		modelMap.put("COL_NOMBRE", "Nombre");
		modelMap.put("COL_APE1", "Apellido 1");
		modelMap.put("COL_APE2", "Apellido 2");
		
		//Datos (field)
		modelMap.put("usuarios", usuarios);
		
		//Generación del PDF
		return new ModelAndView("pdfUsuario", modelMap);
    }
		
}