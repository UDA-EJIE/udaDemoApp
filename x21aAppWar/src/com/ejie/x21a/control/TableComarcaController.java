/*
* Copyright 2020 E.J.I.E., S.A.
*
* Licencia con arreglo a la EUPL, Versi�n 1.1 exclusivamente (la �Licencia�);
* Solo podr� usarse esta obra si se respeta la Licencia.
* Puede obtenerse una copia de la Licencia en
*
* http://ec.europa.eu/idabc/eupl.html
*
* Salvo cuando lo exija la legislaci�n aplicable o se acuerde por escrito,
* el programa distribuido con arreglo a la Licencia se distribuye �TAL CUAL�,
* SIN GARANT�AS NI CONDICIONES DE NING�N TIPO, ni expresas ni impl�citas.
* V�ase la Licencia en el idioma concreto que rige los permisos y limitaciones
* que establece la Licencia.
*/
package com.ejie.x21a.control;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ejie.x21a.model.Comarca;
import com.ejie.x21a.model.Localidad;
import com.ejie.x21a.model.Provincia;
import com.ejie.x21a.service.ComarcaService;
import com.ejie.x21a.service.ProvinciaService;
import com.ejie.x21a.util.ResourceUtils;
import com.ejie.x38.control.bind.annotation.RequestJsonBody;
import com.ejie.x38.dto.TableRequestDto;
import com.ejie.x38.dto.TableResourceResponseDto;
import com.ejie.x38.dto.TableRowDto;
import com.ejie.x38.hdiv.annotation.UDALink;
import com.ejie.x38.hdiv.annotation.UDALinkAllower;
import com.ejie.x38.rup.table.filter.model.Filter;
import com.ejie.x38.rup.table.filter.service.FilterService;
/**
 * ComarcaServiceImpl generated by UDA 1.0, 26-may-2011 13:46:35.
* @author UDA
 */
@Controller
@RequestMapping (value = "/tableComarca")
public class TableComarcaController {

	private static final Logger logger = LoggerFactory.getLogger(TableComarcaController.class);

	@Autowired 
	private ComarcaService comarcaService;
	
	@Autowired 
	private ProvinciaService provinciaService;
	
	@Autowired
	private FilterService filterService;
	
	@javax.annotation.Resource
	private ReloadableResourceBundleMessageSource messageSource;
	
	/*@UDALink(name = "getSimpleMasterDetail", linkTo = {
			@UDALinkAllower(name = "search"),
			@UDALinkAllower(name = "multifilterAdd"),
			@UDALinkAllower(name = "multifilterDelete"),
			@UDALinkAllower(name = "multifilterDefault"),
			@UDALinkAllower(name = "multifilterGetAll")})
	@RequestMapping(value = "masterDetail", method = RequestMethod.GET)
	public String getSimpleMasterDetail(Model model) {
		model.addAttribute("tituloPagina", messageSource.getMessage("tablaMasterDetail", null, LocaleContextHolder.getLocale()));
		model.addAttribute("comarca", new Comarca());
		Localidad localidad = new Localidad();
		localidad.setComarca(new Comarca());
		model.addAttribute("localidad", localidad);
		return "tableMasterDetail";
	}
	
	@UDALink(name = "getMasterDialog", linkTo = {
			@UDALinkAllower(name = "search"),
			@UDALinkAllower(name = "multifilterAdd"),
			@UDALinkAllower(name = "multifilterDelete"),
			@UDALinkAllower(name = "multifilterDefault"),
			@UDALinkAllower(name = "multifilterGetAll")})
	@RequestMapping(value = "masterDialog", method = RequestMethod.GET)
	public String getMasterDialog(Model model) {
		model.addAttribute("tituloPagina", messageSource.getMessage("tablaMasterDetail", null, LocaleContextHolder.getLocale()));
		model.addAttribute("comarca", new Comarca());
		model.addAttribute("localidad", new Localidad());
		return "tableDialogDetail";
	}*/
	
	/**
	 * Method 'getById'.
	 * @param  id String
	 * @return String
	 */
	@UDALink(name = "get", linkTo = { @UDALinkAllower(name = "edit"), @UDALinkAllower(name = "remove")})
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Resource<Comarca> getById(@PathVariable final BigDecimal id) {
        Comarca comarca = new Comarca();
        comarca.setCode(id);
        comarca = this.comarcaService.find(comarca);
        return new Resource<Comarca>(comarca);
	}
	
	/**
	 * Method 'edit'.
 	 * @param comarca Comarca 
 	 * @return Comarca
 	 */
	@UDALink(name = "edit")
	@RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody Resource<Comarca> edit(@Validated @RequestBody final Comarca comarca) {		
        final Comarca comarcaAux = this.comarcaService.update(comarca);
		logger.info("Entity correctly updated!");
		return new Resource<Comarca>(comarcaAux);
    }

	/**
	 * Method 'add'.
	 * @param comarca Comarca 
	 * @return Comarca
	 */
	@UDALink(name = "add", linkTo = { @UDALinkAllower(name = "edit" ), @UDALinkAllower(name = "remove" ), @UDALinkAllower(name = "get" )})
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Resource<Comarca> add(@Validated @RequestBody final Comarca comarca) {		
		final Comarca comarcaAux = this.comarcaService.add(comarca);
		logger.info("Entity correctly inserted!");
		return new Resource<Comarca>(comarcaAux);
	}

	/**
	 * Method 'remove'.
	 * @param id String
	 *
	 */
	@UDALink(name = "remove")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value=HttpStatus.OK)
	public @ResponseBody Resource<Comarca> remove(@PathVariable(value="id") final BigDecimal id, final HttpServletResponse  response) {
		final Comarca comarca = new Comarca();
		comarca.setCode(id);
		this.comarcaService.remove(comarca);
		logger.info("Entity correctly deleted!");
		return new Resource<Comarca>(comarca);
	}
		
	/**
	 * RUP_TABLE
	 */
	@UDALink(name = "filter", linkTo = { @UDALinkAllower(name = "get"), @UDALinkAllower(name = "remove")})
	@RequestMapping(value = "/filter", method = RequestMethod.POST)
	public @ResponseBody TableResourceResponseDto<Comarca> filter(
			@RequestJsonBody(param="filter") final Comarca comarca,
			@RequestJsonBody final TableRequestDto tableRequestDto) {
		TableComarcaController.logger.info("[GET - table] : Obtener Comarcas");	
		return comarcaService.filter(comarca, tableRequestDto, false);
	}
	
	@UDALink(name = "multifilterAdd")
	@RequestMapping(value = "/multiFilter/add", method = RequestMethod.POST)
	public @ResponseBody Resource<Filter> filterAdd(@RequestJsonBody(param="filtro") final Filter filtro){
		TableComarcaController.logger.info("[POST - table] : add filter");
		return new Resource<Filter>(filterService.insert(filtro));
	}	
	
	@UDALink(name = "multifilterDelete")
	@RequestMapping(value = "/multiFilter/delete", method = RequestMethod.POST)
	public @ResponseBody Resource<Filter>  filterDelete(
			@RequestJsonBody(param="filtro") final Filter filtro) {
		TableComarcaController.logger.info("[POST - table] : delete filter");
		return new Resource<Filter>(filterService.delete(filtro));
	}
	
	@UDALink(name = "multifilterDefault")
	@RequestMapping(value = "/multiFilter/getDefault", method = RequestMethod.GET)
	public @ResponseBody Resource<Filter> filterGetDefault(
		@RequestParam(value = "filterSelector", required = true) final String filterSelector,
		@RequestParam(value = "comarca", required = true) final String filterComarca) {
		TableComarcaController.logger.info("[get - table] : getDefault filter");
		return ResourceUtils.toResource(filterService.getDefault(filterSelector, filterComarca));
	}
	
	@UDALink(name = "multifilterGetAll")
	@RequestMapping(value = "/multiFilter/getAll", method = RequestMethod.GET)
	public @ResponseBody List<Resource<Filter>> filterGetAll(
		@RequestParam(value = "filterSelector", required = true) final String filterSelector,
		@RequestParam(value = "comarca", required = true) final String filterComarca) {
		TableComarcaController.logger.info("[get - table] : GetAll filter");
		return ResourceUtils.fromListToResource(filterService.getAllFilters(filterSelector, filterComarca));
	}
	
	@UDALink(name = "search")	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public @ResponseBody List<TableRowDto<Comarca>> search(
			@RequestJsonBody(param="filter") final Comarca comarcaFilter,
			@RequestJsonBody(param="search") final Comarca comarcaSearch,
			@RequestJsonBody final TableRequestDto tableRequestDto){	
		TableComarcaController.logger.info("[GET - search] : Obtener Comarcas por filtro");
		return comarcaService.search(comarcaFilter, comarcaSearch, tableRequestDto, true);
	}
		
	/*
	 * MAPPING PARA EL COMBO DE PROVINCIAS
	 */
	@UDALink(name = "getProvincias")
	@RequestMapping(value = "/provincia", method=RequestMethod.GET)
	public @ResponseBody List<Resource<Provincia>> getProvincias() {
		final Provincia filtroProvincia = new Provincia();
		final List<Provincia> findAll = provinciaService.findAll(filtroProvincia, null);
		return ResourceUtils.fromListToResource(findAll);
	}
	
	/*
	 * MAPPING PARA EL COMBO DE COMARCAS
	 */
	@UDALink(name = "getComarcas")
	@RequestMapping(value = "/comarca", method=RequestMethod.GET)
	public @ResponseBody List<Resource<Comarca>> getComarcas() {
		final Comarca filtroComarca = new Comarca();
		final List<Comarca> findAll = comarcaService.findAll(filtroComarca, null);
		return ResourceUtils.fromListToResource(findAll);
	}
}