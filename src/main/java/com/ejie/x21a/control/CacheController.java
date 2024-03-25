package com.ejie.x21a.control;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejie.x21a.model.Usuario;
import com.ejie.x21a.service.CacheService;
import com.ejie.x21a.util.Constants;
import com.ejie.x38.control.bind.annotation.RequestJsonBody;
import com.ejie.x38.dto.TableRequestDto;
import com.ejie.x38.dto.TableResponseDto;
 
@Controller
@RequestMapping (value = "/integracion/cache")
public class CacheController {

	private static final Logger logger = LoggerFactory.getLogger(CacheController.class);

	@Autowired
	private CacheService cacheService;
	
	/**
	 * Method 'getCreateForm'.
	 *
	 * @param model Model
	 * @return String
	 */
	@GetMapping(value = "view")
	public String getCreateForm(Model model) {
		logger.info("[GET - View] : cache");
		return "cache";
	}
	
	@PostMapping(value = "/inlineEdit")
	public String getTableInlineEdit (
			@RequestParam(required = true) String actionType,
			@RequestParam(required = true) boolean isMultipart,
			@RequestParam(required = false) String pkValue,
			Model model) {
		model.addAttribute(Constants.MODEL_USUARIO, new Usuario());
		model.addAttribute(Constants.MODEL_ACTIONTYPE, isMultipart ? "POST" : actionType);
		model.addAttribute(Constants.MODEL_ISMULTIPART, isMultipart);
		model.addAttribute(Constants.MODEL_ENCTYPE, isMultipart ? Constants.MULTIPART_FORMDATA : Constants.APPLICATION_URLENCODED);
		
		if (pkValue != null) {
			model.addAttribute(Constants.MODEL_PKVALUE, pkValue);
		}
		
		if (actionType.equals("POST")) {
			if (isMultipart) {
				model.addAttribute(Constants.MODEL_ENDPOINT, "/integracion/cache/addMultipart");
			} else {
				model.addAttribute(Constants.MODEL_ENDPOINT, "/integracion/cache/add");
			}
		} else {
			if (isMultipart) {
				model.addAttribute(Constants.MODEL_ENDPOINT, "/integracion/cache/editMultipart");
			} else {
				model.addAttribute(Constants.MODEL_ENDPOINT, "/integracion/cache/edit");
			}
		}
		
		Map<String,String> comboRol = new LinkedHashMap<String,String>();
		comboRol.put("", "---");
		comboRol.put("Administrador", "Administrador");
		comboRol.put("Desarrollador", "Desarrollador");
		comboRol.put("Espectador", "Espectador");
		comboRol.put("Informador", "Informador");
		comboRol.put("Manager", "Manager");
		model.addAttribute("comboRol", comboRol);
		
		List<String> radioEjie = new ArrayList<String>();
		radioEjie.add("0");
		radioEjie.add("1");
		model.addAttribute("radioEjie", radioEjie);
		
		logger.info("[POST - View] : tableInlineEditAuxForm");
		return "tableInlineEditAuxForm";
	}
	
	@GetMapping(value = "/{id}")
	public @ResponseBody Usuario get(@PathVariable String id) {
        Usuario usuario = new Usuario();
		usuario.setId(id);
        usuario = this.cacheService.find(usuario);
        logger.info("[GET - table] : Obtener Usuarios por PK");
        return usuario;
	}
	
	@PutMapping(value = "/edit")
    public @ResponseBody Usuario edit(@Validated @RequestBody Usuario usuario) {
		Usuario usuarioAux = this.cacheService.update(usuario, Boolean.TRUE);
		logger.info("[PUT - table] : �Entidad correctamente actualizada!");
        return usuarioAux;
    }
	
	@PostMapping(value = "/filter")
	public @ResponseBody TableResponseDto<Usuario> filter(
			@RequestJsonBody(param="filter") Usuario filterUsuario,
			@RequestJsonBody TableRequestDto tableRequestDto) {
		logger.info("[POST - table] : Obtener Usuarios");		
		List<Usuario> listUser = cacheService.getAll(filterUsuario, null, Boolean.TRUE);
				
		int page = tableRequestDto.getPage().intValue();
		int rows = tableRequestDto.getRows().intValue();
		long recordNum = listUser.size();
		
		List<Usuario> paginatedList = listUser.subList((page - 1) * rows, ((page - 1) * rows) + rows);
		
		TableResponseDto<Usuario> TableResponseDto = new TableResponseDto<Usuario>(tableRequestDto, recordNum, paginatedList);
		
		return TableResponseDto;
	}
}	
