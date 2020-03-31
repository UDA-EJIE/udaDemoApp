package com.ejie.x21a.control;

import com.ejie.x21a.model.Usuario;
import com.ejie.x21a.service.TableUsuarioService;
import com.ejie.x38.control.bind.annotation.RequestJsonBody;
import com.ejie.x38.dto.TableRequestDto;
import com.ejie.x38.dto.TableResponseDto;

import com.ejie.x38.rup.table.filter.model.Filter;
import com.ejie.x38.rup.table.filter.service.FilterService;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    	return "listaConfigurable";
    }
    
    @GetMapping (value = "/doble")
    public String getListaDobleView(Model model) {
    	model.addAttribute("usuario", new Usuario());
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
        ListaController.logger.info("[POST - jqGrid] : Obtener Usuarios");
        return tableUsuarioService.filter(filterUsuario, tableRequestDto, false);
    }
	
	@GetMapping(value = "/filter/multiFilter/getAll")
	public @ResponseBody List<Filter> filterGetAll(
		@RequestParam(value = "filterSelector", required = true) String filterSelector,
		@RequestParam(value = "user", required = true) String filterUser) {
		ListaController.logger.info("[get - jqGrid] : GetAll filter");
		return filterService.getAllFilters(filterSelector,filterUser);
	}
	
	@GetMapping(value = "/filter/multiFilter/getDefault")
	public @ResponseBody Filter filterGetDefault(
		@RequestParam(value = "filterSelector", required = true) String filterSelector,
		@RequestParam(value = "user", required = true) String filterUser) {
		ListaController.logger.info("[get - jqGrid] : getDefault filter");
		 return filterService.getDefault(filterSelector, filterUser);
	}
	
	@PostMapping(value = "/filter/multiFilter/add")
	public @ResponseBody Filter filterAdd(@RequestJsonBody(param="filtro") Filter filtro){
		ListaController.logger.info("[POST - jqGrid] : add filter");
		 return filterService.insert(filtro);
	}
	
	@PostMapping(value = "/filter/multiFilter/delete")
	public @ResponseBody Filter  filterDelete(
			@RequestJsonBody(param="filtro") Filter filtro) {
		ListaController.logger.info("[POST - jqGrid] : delete filter");
		return  filterService.delete(filtro);
	}
}