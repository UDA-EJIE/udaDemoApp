package com.ejie.x21a.control;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejie.x21a.model.Usuario;
import com.ejie.x21a.service.TableUsuarioService;
import com.ejie.x38.control.bind.annotation.RequestJsonBody;
import com.ejie.x38.dto.TableRequestDto;
import com.ejie.x38.dto.TableResponseDto;
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
		comboEjie.put("1", "S�");
		model.addAttribute("comboEjie", comboEjie);
    	
    	return "listaConfigurable";
    }
    
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
		comboEjie.put("1", "S�");
		model.addAttribute("comboEjie", comboEjie);
    	
    	return "listaDoble";
    }
    
    @GetMapping (value = "/noTemplate")
    public String getListaNoTemplateView(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "listaNoTemplate";
    }
    
    @PostMapping(value = "/filter")
    public @ResponseBody
    TableResponseDto<Usuario> filter(
            @RequestJsonBody(param="filter") Usuario filterUsuario,
            @RequestJsonBody TableRequestDto tableRequestDto) {
        ListaController.logger.info("[POST - table] : Obtener Usuarios");
        return tableUsuarioService.filter(filterUsuario, tableRequestDto, false);
    }
	
	@PostMapping(value = "/filter/multiFilter/add")
	public @ResponseBody Filter filterAdd(@RequestJsonBody(param="filtro") Filter filtro){
		ListaController.logger.info("[POST - table] : add filter");
		return filterService.insert(filtro);
	}
	
	@DeleteMapping(value = "/filter/multiFilter/delete")
	public @ResponseBody Filter filterDelete(
			@RequestJsonBody(param="filtro") Filter filtro) {
		ListaController.logger.info("[DELETE - table] : delete filter");
		return filterService.delete(filtro);
	}
	
	@GetMapping(value = "/filter/multiFilter/getDefault")
	public @ResponseBody Filter filterGetDefault(
		@RequestParam(value = "selector", required = true) String selector,
		@RequestParam(value = "user", required = true) String user) {
		ListaController.logger.info("[get - table] : getDefault filter");
		return filterService.getDefault(selector, user);
	}
	
	@GetMapping(value = "/filter/multiFilter/getAll")
	public @ResponseBody List<Filter> filterGetAll(
		@RequestParam(value = "selector", required = true) String selector,
		@RequestParam(value = "user", required = true) String user) {
		ListaController.logger.info("[get - table] : GetAll filter");
		return filterService.getAllFilters(selector, user);
	}
}