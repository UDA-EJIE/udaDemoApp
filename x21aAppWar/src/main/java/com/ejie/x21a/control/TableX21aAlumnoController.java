package com.ejie.x21a.control;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ejie.x21a.model.X21aAlumno;
import com.ejie.x21a.service.TableX21aAlumnoService;
import com.ejie.x21a.util.Constants;
import com.ejie.x38.control.bind.annotation.RequestJsonBody;
import com.ejie.x38.dto.TableJerarquiaDto;
import com.ejie.x38.dto.TableRequestDto;
import com.ejie.x38.dto.TableResponseDto;
import com.ejie.x38.dto.TableRowDto;

/**
 * X21aAlumnoController generated by UDA, 15-nov-2018 9:05:37.
 * @author UDA
 */
 
@Controller
@RequestMapping (value = "/x21aalumno")

public class TableX21aAlumnoController  {

	private static final Logger logger = LoggerFactory.getLogger(TableX21aAlumnoController.class);

	@Autowired
	private TableX21aAlumnoService x21aAlumnoService;
	
	/*
	 * OPERACIONES CRUD (Create, Read, Update, Delete)
	 * 
	 */
	
	/**
	 * Operacion CRUD Read. Devuelve el bean correspondiente al identificador indicado.
	 * 
	 * @param id BigDecimal
	 * @return X21aAlumno 
	 *            Objeto correspondiente al identificador indicado.
	 */
	@GetMapping(value = "/{id}")
	public @ResponseBody X21aAlumno get(@PathVariable BigDecimal id) {
        X21aAlumno x21aAlumno = new X21aAlumno();
		x21aAlumno.setId(id);
        x21aAlumno = this.x21aAlumnoService.find(x21aAlumno);
        TableX21aAlumnoController.logger.info("[GET - findBy_PK] : Obtener X21aAlumno por PK");
        return x21aAlumno;
	}

	/**
	 * Devuelve una lista de beans correspondientes a los valores de filtrados
	 * indicados en el objeto pasado como parametro.
	 *
	 * @param filterX21aAlumno X21aAlumno
	 *            Objeto que contiene los parametros de filtrado utilizados en
	 *            la busqueda.
	 * @return List<X21aAlumno> 
	 *            Lista de objetos correspondientes a la busqueda realizada.
	 */
	@GetMapping(value = "/all")
	public @ResponseBody List<X21aAlumno> getAll(@ModelAttribute X21aAlumno filterX21aAlumno) {
		TableX21aAlumnoController.logger.info("[GET - find_ALL] : Obtener X21aAlumno por filtro");
	    return this.x21aAlumnoService.findAll(filterX21aAlumno, null);
	}

	/**
	 * Operacion CRUD Edit. Modificacion del bean indicado.
	 *
	 * @param x21aAlumno X21aAlumno 
	 *            Bean que contiene la informacion a modificar.
	 * @return X21aAlumno 
	 *            Bean resultante de la modificacion.
	 */
	@PutMapping(value = "/edit")
    public @ResponseBody X21aAlumno edit(@RequestBody X21aAlumno x21aAlumno) {		
        X21aAlumno x21aAlumnoAux = this.x21aAlumnoService.update(x21aAlumno);
		TableX21aAlumnoController.logger.info("[PUT] : X21aAlumno actualizado correctamente");
        return x21aAlumnoAux;
    }

	/**
	 * Operacion CRUD Create. Creacion de un nuevo registro a partir del bean
	 * indicado.
	 *
	 * @param x21aAlumno X21aAlumno 
	 *            Bean que contiene la informacion con la que se va a crear el
	 *            nuevo registro.
	 * @return X21aAlumno
	 *            Bean resultante del proceso de creacion.
	 */
	@PostMapping(value = "/add")
	public @ResponseBody X21aAlumno add(@RequestBody X21aAlumno x21aAlumno) {		
        X21aAlumno x21aAlumnoAux = this.x21aAlumnoService.add(x21aAlumno);
        TableX21aAlumnoController.logger.info("[POST] : X21aAlumno insertado correctamente");
        return x21aAlumnoAux;
	}

	/**
	 * Operacion CRUD Delete. Borrado del registro correspondiente al
	 * identificador especificado.
	 *
	 * @param id BigDecimal
	 *            Identificador del objeto que se desea eliminar.
	 * @return X21aAlumno
	 *            Bean eliminado.
	 */
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody X21aAlumno remove(@PathVariable BigDecimal id) {
        X21aAlumno x21aAlumno = new X21aAlumno();
        x21aAlumno.setId(id);
        this.x21aAlumnoService.remove(x21aAlumno);
       	TableX21aAlumnoController.logger.info("[DELETE] : X21aAlumno borrado correctamente");
       	return x21aAlumno;
    }
    
	
	/*
	 * METODOS COMPONENTE RUP_TABLE
	 * 
	 */

	 /**
	 * Metodo de presentacion del RUP_TABLE.
	 * 
	 * @param model Model
	 * @return String
	 */
	@GetMapping(value = "/maint")
	public String getFormEdit(Model model) {
		TableX21aAlumnoController.logger.info("[GET - View] : x21aalumno");
		return "x21aalumno";
	}
	
	@PostMapping(value = "/editForm")
	public String getTableEditForm (
			@RequestParam(required = true) String actionType,
			@RequestParam(required = false) BigDecimal pkValue,
			Model model) {
		model.addAttribute(Constants.MODEL_X21AALUMNO, new X21aAlumno());
		model.addAttribute(Constants.MODEL_ACTIONTYPE, actionType);
		model.addAttribute(Constants.MODEL_ENCTYPE, Constants.APPLICATION_URLENCODED);
		
		if (pkValue != null) {
			model.addAttribute("pkValue", pkValue);
		}
		
		if (actionType.equals("POST")) {
			model.addAttribute(Constants.MODEL_ENDPOINT, "add");
		} else {
			model.addAttribute(Constants.MODEL_ENDPOINT, "edit");
		}
		
		return "tableX21aAlumnoEditForm";
	}
	 
	 /**
	 * Operacion de filtrado del componente RUP_TABLE.
	 * 
	 * @param filterX21aAlumno X21aAlumno
	 *            Bean que contiene los parametros de filtrado a emplear.
	 * @param tableRequestDto
	 *            Dto que contiene los parametros de configuracion propios del
	 *            RUP_TABLE a aplicar en el filtrado.
	 * @return TableResponseDto<X21aAlumno>
	 *            Dto que contiene el resultado del filtrado realizado por el 
	 *            componente RUP_TABLE.
	 */
	@PostMapping(value = "/filter")
	public @ResponseBody TableResponseDto<X21aAlumno> filter(
			@RequestJsonBody(param="filter") X21aAlumno filterX21aAlumno,
			@RequestJsonBody TableRequestDto tableRequestDto) {
		TableX21aAlumnoController.logger.info("[POST - filter] : Obtener X21aAlumnos");
		return this.x21aAlumnoService.filter(filterX21aAlumno, tableRequestDto, false);
	}
	 
	/**
	 * Operacion de busqueda del componente RUP_TABLE.
	 * 
	 * @param filterX21aAlumno X21aAlumno
	 *            Bean que contiene los parametros de filtrado a emplear.
	 * @param searchX21aAlumno X21aAlumno
	 *            Bean que contiene los parametros de busqueda a emplear.
	 * @param tableRequestDto
	 *            Dto que contiene los parametros de configuracion propios del
	 *            RUP_TABLE a aplicar en la búsqueda.
	 * @return TableRowDto<X21aAlumno> 
	 *            Dto que contiene el resultado de la busqueda realizada por el
	 *            componente RUP_TABLE. 
	 */
	@PostMapping(value = "/search")
	public @ResponseBody List<TableRowDto<X21aAlumno>> search(
			@RequestJsonBody(param="filter") X21aAlumno filterX21aAlumno,
			@RequestJsonBody(param="search") X21aAlumno searchX21aAlumno,
			@RequestJsonBody TableRequestDto tableRequestDto) {
		TableX21aAlumnoController.logger.info("[POST - search] : Buscar X21aAlumnos");
		return this.x21aAlumnoService.search(filterX21aAlumno, searchX21aAlumno, tableRequestDto, false);
	}
	
	/**
	 * Borrado multiple de registros
	 * 
	 * @param filterX21aAlumno X21aAlumno
	 *            Bean que contiene los parametros de filtrado a emplear.
	 * @param tableRequestDto
	 *            Dto que contiene los parametros de configuracion propios del
	 *            RUP_TABLE a aplicar en la busqueda.
	 * @return List<String>
	 *            Lista de los identificadores de los registros eliminados.
	 * 
	 */
	@PostMapping(value = "/deleteAll")
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody List<String> removeMultiple(
			@RequestJsonBody(param="filter") X21aAlumno filterX21aAlumno,
			@RequestJsonBody TableRequestDto tableRequestDto) {
		TableX21aAlumnoController.logger.info("[POST - search] : [POST - removeMultiple] : Eliminar multiples X21aAlumnos");
		this.x21aAlumnoService.removeMultiple(filterX21aAlumno, tableRequestDto, false);
		TableX21aAlumnoController.logger.info("All entities correctly deleted!");
		
		return tableRequestDto.getMultiselection().getSelectedIds();
	}
	
	/*
	 * METODOS COMPONENTE RUP_TABLE - JERARQUIA
	 */
	
	/**
	 * Operacion de filtrado del componente RUP_TABLE para presentar los
	 * registros mediante visualizacion jerarquica.
	 * 
	 * @param filterX21aAlumno X21aAlumno
	 *            Bean que contiene los parametros de filtrado a emplear.
	 * @param tableRequestDto
	 *            Dto que contiene los parametros de configuracion propios del
	 *            RUP_TABLE a aplicar en el filtrado.
	 * @return TableResponseDto<TableJerarquiaDto<X21aAlumno>>
	 *            Dto que contiene el resultado del filtrado realizado por el
	 *            componente RUP_TABLE. 
	 */
	@PostMapping(value = "/jerarquia/filter")
	public @ResponseBody TableResponseDto<TableJerarquiaDto<X21aAlumno>> jerarquia(
			@RequestJsonBody(param="filter") X21aAlumno filterX21aAlumno,
			@RequestJsonBody TableRequestDto tableRequestDto) {
		TableX21aAlumnoController.logger.info("[POST - jerarquia] : Obtener X21aAlumnos jerarquia");
		return this.x21aAlumnoService.jerarquia(filterX21aAlumno, tableRequestDto, false);
	}
	
	/**
	 * Recupera los hijos de los registros desplegados en la visualizacion jerarquica.
	 * 
	 * @param filterX21aAlumno X21aAlumno
	 *            Bean que contiene los parametros de filtrado a emplear.
	 * @param tableRequestDto
	 *            Dto que contiene los parametros de configuracion propios del
	 *            RUP_TABLE a aplicar en el filtrado.
	 * @return TableResponseDto<TableJerarquiaDto<X21aAlumno>>
	 *            Dto que contiene el resultado del filtrado realizado por el
	 *            componente RUP_TABLE. 
	 */
	@PostMapping(value = "/jerarquiaChildren")
	public @ResponseBody TableResponseDto<TableJerarquiaDto<X21aAlumno>> jerarquiaChildren(
			@RequestJsonBody(param="filter") X21aAlumno  filterX21aAlumno ,
			@RequestJsonBody TableRequestDto  tableRequestDto) {
		TableX21aAlumnoController.logger.info("[POST - jerarquia] : Obtener X21aAlumnos jerarquia - Hijos");
		return this.x21aAlumnoService.jerarquiaChildren(filterX21aAlumno, tableRequestDto);
	}
	
	/*
	 * EXPORTACIONES DE DATOS
	 */
	
	/**
	 * Devuelve los datos exportados de la tabla.
	 *
	 * @param filterX21aAlumno X21aAlumno
	 * @param tableRequestDto TableRequestDto
	 */
	@PostMapping(value = "/clipboardReport")
	public @ResponseBody List<X21aAlumno> getClipboardReport(
			@RequestJsonBody(param = "filter", required = false) X21aAlumno filterX21aAlumno,
			@RequestParam(required = false) String[] columns, 
			@RequestParam(required = false) String[] columnsName,
			@RequestParam(required = false) ArrayList<?> reportsParams,
			@RequestJsonBody TableRequestDto tableRequestDto) {
		TableX21aAlumnoController.logger.info("[POST - clipboardReport] : Copiar multiples X21aAlumnos");
		return this.x21aAlumnoService.getDataForReports(filterX21aAlumno, tableRequestDto);
	}
	
	/**
	 * Devuelve un fichero excel que contiene los datos exportados de la tabla.
	 *
	 * @param filterX21aAlumno X21aAlumno
	 * @param columns String[]
	 * @param fileName String
	 * @param sheetTitle String
	 * @param reportsParams ArrayList<?>
	 * @param tableRequestDto TableRequestDto
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	@PostMapping(value = {"/xlsReport" , "/xlsxReport"}, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public @ResponseBody void generateExcelReport(
			@RequestJsonBody(param = "filter", required = false) X21aAlumno filterX21aAlumno, 
			@RequestJsonBody(param = "columns", required = false) String[] columns, 
			@RequestJsonBody(param = "fileName", required = false) String fileName, 
			@RequestJsonBody(param = "sheetTitle", required = false) String sheetTitle,
			@RequestJsonBody(param = "reportsParams", required = false) ArrayList<?> reportsParams,
			@RequestJsonBody TableRequestDto tableRequestDto,
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException{
		TableX21aAlumnoController.logger.info("[POST - generateExcelReport] : Devuelve un fichero excel");
		//Idioma
        Locale locale = LocaleContextHolder.getLocale();
		this.x21aAlumnoService.generateReport(filterX21aAlumno, columns, fileName, sheetTitle, reportsParams, tableRequestDto, locale, request, response);
    }
	
	/**
	 * Devuelve un fichero pdf que contiene los datos exportados de la tabla.
	 *
	 * @param filterX21aAlumno X21aAlumno
	 * @param columns String[]
	 * @param fileName String
	 * @param sheetTitle String
	 * @param reportsParams ArrayList<?>
	 * @param tableRequestDto TableRequestDto
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	@PostMapping(value = "pdfReport", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public @ResponseBody void generatePDFReport(
			@RequestJsonBody(param = "filter", required = false) X21aAlumno filterX21aAlumno, 
			@RequestJsonBody(param = "columns", required = false) String[] columns, 
			@RequestJsonBody(param = "fileName", required = false) String fileName, 
			@RequestJsonBody(param = "sheetTitle", required = false) String sheetTitle,
			@RequestJsonBody(param = "reportsParams", required = false) ArrayList<?> reportsParams,
			@RequestJsonBody TableRequestDto tableRequestDto,
			HttpServletRequest request,
			HttpServletResponse response){
		TableX21aAlumnoController.logger.info("[POST - generatePDFReport] : Devuelve un fichero pdf");
		//Idioma
        Locale locale = LocaleContextHolder.getLocale();
		this.x21aAlumnoService.generateReport(filterX21aAlumno, columns, fileName, sheetTitle, reportsParams, tableRequestDto, locale, request, response);
	}
	
	/**
	 * Devuelve un fichero ods que contiene los datos exportados de la tabla.
	 *
	 * @param filterX21aAlumno X21aAlumno
	 * @param columns String[]
	 * @param fileName String
	 * @param sheetTitle String
	 * @param reportsParams ArrayList<?>
	 * @param tableRequestDto TableRequestDto
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	@PostMapping(value = "odsReport", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public @ResponseBody void generateODSReport(
			@RequestJsonBody(param = "filter", required = false) X21aAlumno filterX21aAlumno, 
			@RequestJsonBody(param = "columns", required = false) String[] columns, 
			@RequestJsonBody(param = "fileName", required = false) String fileName, 
			@RequestJsonBody(param = "sheetTitle", required = false) String sheetTitle,
			@RequestJsonBody(param = "reportsParams", required = false) ArrayList<?> reportsParams,
			@RequestJsonBody TableRequestDto tableRequestDto,
			HttpServletRequest request,
			HttpServletResponse response){
		TableX21aAlumnoController.logger.info("[POST - generateODSReport] : Devuelve un fichero ods");
		//Idioma
        Locale locale = LocaleContextHolder.getLocale();
		this.x21aAlumnoService.generateReport(filterX21aAlumno, columns, fileName, sheetTitle, reportsParams, tableRequestDto, locale, request, response);
	}
	
	/**
	 * Devuelve un fichero csv que contiene los datos exportados de la tabla.
	 *
	 * @param filterX21aAlumno X21aAlumno
	 * @param columns String[]
	 * @param fileName String
	 * @param sheetTitle String
	 * @param reportsParams ArrayList<?>
	 * @param tableRequestDto TableRequestDto
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	@PostMapping(value = "csvReport", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public @ResponseBody void generateCSVReport(
			@RequestJsonBody(param = "filter", required = false) X21aAlumno filterX21aAlumno, 
			@RequestJsonBody(param = "columns", required = false) String[] columns, 
			@RequestJsonBody(param = "fileName", required = false) String fileName, 
			@RequestJsonBody(param = "sheetTitle", required = false) String sheetTitle,
			@RequestJsonBody(param = "reportsParams", required = false) ArrayList<?> reportsParams,
			@RequestJsonBody TableRequestDto tableRequestDto,
			HttpServletRequest request,
			HttpServletResponse response){
		TableX21aAlumnoController.logger.info("[POST - generateCSVReport] : Devuelve un fichero csv");
		//Idioma
        Locale locale = LocaleContextHolder.getLocale();
		this.x21aAlumnoService.generateReport(filterX21aAlumno, columns, fileName, sheetTitle, reportsParams, tableRequestDto, locale, request, response);
	}
}	