package com.ejie.x21a.control;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejie.x21a.model.Usuario;
import com.ejie.x21a.service.TableUsuarioService;
import com.ejie.x21a.util.ResourceUtils;
import com.ejie.x38.control.bind.annotation.RequestJsonBody;
import com.ejie.x38.dto.TableRequestDto;
import com.ejie.x38.dto.TableResourceResponseDto;
import com.ejie.x38.hdiv.annotation.UDALink;
import com.ejie.x38.hdiv.annotation.UDALinkAllower;
import com.ejie.x38.rup.table.filter.model.Filter;
import com.ejie.x38.rup.table.filter.service.FilterService;

@Controller
@RequestMapping(value = "/patrones/lista")
public class ListaController {
    private static final Logger logger = LoggerFactory.getLogger(ListaController.class);

    @Autowired
    private TableUsuarioService tableUsuarioService;
    
    @Autowired
	private FilterService filterService;
    
    @UDALink(name = "getListaConfigurableView", linkTo = {@UDALinkAllower(name = "multifilterAdd"),
			@UDALinkAllower(name = "multifilterDelete"),
			@UDALinkAllower(name = "multifilterDefault"),
			@UDALinkAllower(name = "multifilterGetAll")})
    @GetMapping (value = "/configurable")
    public String getListaConfigurableView(Model model) {
    	model.addAttribute("usuario", new Usuario());
		
		Map<String,String> comboRol = new LinkedHashMap<String,String>();
		comboRol.put("", "---");
		comboRol.put("administrador", "Administrador");
		comboRol.put("desarrollador", "Desarrollador");
		comboRol.put("espectador", "Espectador");
		comboRol.put("informador", "Informador");
		comboRol.put("manager", "Manager");
		model.addAttribute("comboRol", comboRol);
    	
    	Map<String,String> comboEjie = new LinkedHashMap<String,String>();
		comboEjie.put("", "---");
		comboEjie.put("0", "No");
		comboEjie.put("1", "Sí");
		model.addAttribute("comboEjie", comboEjie);
    	
    	return "listaConfigurable";
    }
    
    @UDALink(name = "getListaDobleView", linkTo = {@UDALinkAllower(name = "multifilterAdd"),
			@UDALinkAllower(name = "multifilterDelete"),
			@UDALinkAllower(name = "multifilterDefault"),
			@UDALinkAllower(name = "multifilterGetAll")})
    @GetMapping (value = "/doble")
    public String getListaDobleView(Model model) {
    	model.addAttribute("usuario", new Usuario());
		
		Map<String,String> comboRol = new LinkedHashMap<String,String>();
		comboRol.put("", "---");
		comboRol.put("administrador", "Administrador");
		comboRol.put("desarrollador", "Desarrollador");
		comboRol.put("espectador", "Espectador");
		comboRol.put("informador", "Informador");
		comboRol.put("manager", "Manager");
		model.addAttribute("comboRol", comboRol);
    	
    	Map<String,String> comboEjie = new LinkedHashMap<String,String>();
		comboEjie.put("", "---");
		comboEjie.put("0", "No");
		comboEjie.put("1", "Sí");
		model.addAttribute("comboEjie", comboEjie);
    	
    	return "listaDoble";
    }
    
    @UDALink(name = "getListaNoTemplateView", linkTo = {@UDALinkAllower(name = "multifilterAdd"),
			@UDALinkAllower(name = "multifilterDelete"),
			@UDALinkAllower(name = "multifilterDefault"),
			@UDALinkAllower(name = "multifilterGetAll")})
    @GetMapping (value = "/noTemplate")
    public String getListaNoTemplateView(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "listaNoTemplate";
    }
    
    @UDALink(name = "filter")
    @PostMapping(value = "/filter")
    public @ResponseBody
    TableResourceResponseDto<Usuario> filter(
            @RequestJsonBody(param="filter") Usuario filterUsuario,
            @RequestJsonBody TableRequestDto tableRequestDto) {
        ListaController.logger.info("[POST - table] : Obtener Usuarios");
        return tableUsuarioService.filter(filterUsuario, tableRequestDto, false);
    }
	
    @UDALink(name = "multifilterAdd")
	@PostMapping(value = "/filter/multiFilter/add")
	public @ResponseBody Resource<Filter> filterAdd(@RequestJsonBody(param="filtro") Filter filtro){
		ListaController.logger.info("[POST - table] : add filter");
		return new Resource<Filter>(filterService.insert(filtro));
	}
	
    @UDALink(name = "multifilterDelete")
	@PostMapping(value = "/filter/multiFilter/delete")
	public @ResponseBody Resource<Filter> filterDelete(
			@RequestJsonBody(param="filtro") Filter filtro) {
		ListaController.logger.info("[POST - table] : delete filter");
		return new Resource<Filter>(filterService.delete(filtro));
	}
	
    @UDALink(name = "multifilterDefault")
	@GetMapping(value = "/filter/multiFilter/getDefault")
	public @ResponseBody Resource<Filter> filterGetDefault(
		@RequestParam(value = "filterSelector", required = true) String filterSelector,
		@RequestParam(value = "user", required = true) String filterUser) {
		ListaController.logger.info("[get - table] : getDefault filter");
		return ResourceUtils.toResource(filterService.getDefault(filterSelector, filterUser));
	}
	
    @UDALink(name = "multifilterGetAll")
	@GetMapping(value = "/filter/multiFilter/getAll")
	public @ResponseBody List<Resource<Filter>> filterGetAll(
		@RequestParam(value = "filterSelector", required = true) String filterSelector,
		@RequestParam(value = "user", required = true) String filterUser) {
		ListaController.logger.info("[get - table] : GetAll filter");
		return ResourceUtils.fromListToResource(filterService.getAllFilters(filterSelector, filterUser));
	}
}