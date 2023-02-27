package com.ejie.x21a.control;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hdiv.services.TrustAssertion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejie.x21a.model.Usuario;
import com.ejie.x21a.service.CacheService;
import com.ejie.x21a.util.Constants;
import com.ejie.x38.control.bind.annotation.RequestJsonBody;
import com.ejie.x38.dto.TableRequestDto;
import com.ejie.x38.dto.TableResourceResponseDto;
import com.ejie.x38.hdiv.annotation.UDALink;
import com.ejie.x38.hdiv.annotation.UDALinkAllower;
import com.ejie.x38.hdiv.util.IdentifiableModelWrapperFactory;
 
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
	@UDALink(name = "getCreateForm", linkTo = {
			@UDALinkAllower(name = "getTableInlineEdit"),
			@UDALinkAllower(name = "getApellidos", linkClass = TableUsuarioController.class),
			@UDALinkAllower(name = "getRoles", linkClass = TableUsuarioController.class) })
	@RequestMapping(value = "view", method = RequestMethod.GET)
	public String getCreateForm(Model model) {
		logger.info("[GET - View] : cache");
		return "cache";
	}
	
	@UDALink(name = "getTableInlineEdit", linkTo = {
			@UDALinkAllower(name = "get"),
			@UDALinkAllower(name = "edit"),
			@UDALinkAllower(name = "filter") })
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
			model.addAttribute(Constants.MODEL_PKVALUE, IdentifiableModelWrapperFactory.getInstance(new Usuario(pkValue)));
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
		
		logger.info("[POST - View] : tableInlineEditAuxForm");
		return "tableInlineEditAuxForm";
	}
	
	@UDALink(name = "get", linkTo = { @UDALinkAllower(name = "edit"), @UDALinkAllower(name = "filter") })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Resource<Usuario> get(@PathVariable @TrustAssertion(idFor = Usuario.class) String id) {
        Usuario usuario = new Usuario();
		usuario.setId(id);
        usuario = this.cacheService.find(usuario);
        logger.info("[GET - table] : Obtener Usuarios por PK");
        return new Resource<Usuario>(usuario);
	}
	
	@UDALink(name = "edit", linkTo = { @UDALinkAllower(name = "filter") })
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public @ResponseBody Resource<Usuario> edit(@Validated @RequestBody Usuario usuario) {
		Usuario usuarioAux = this.cacheService.update(usuario, Boolean.TRUE);
		logger.info("[PUT - table] : �Entidad correctamente actualizada!");
        return new Resource<Usuario>(usuarioAux);
    }
	
	@UDALink(name = "filter", linkTo = { @UDALinkAllower(name = "get"), @UDALinkAllower(name = "filter") })
	@RequestMapping(value = "/filter", method = RequestMethod.POST)
	public @ResponseBody TableResourceResponseDto<Usuario> filter(
			@RequestJsonBody(param="filter") Usuario filterUsuario,
			@RequestJsonBody TableRequestDto tableRequestDto) {
		logger.info("[POST - table] : Obtener Usuarios");		
		List<Usuario> listUser = cacheService.getAll(filterUsuario, null, Boolean.TRUE);
				
		int page = tableRequestDto.getPage().intValue();
		int rows = tableRequestDto.getRows().intValue();
		long recordNum = listUser.size();
		
		List<Usuario> paginatedList = listUser.subList((page - 1) * rows, ((page - 1) * rows) + rows);
		
		TableResourceResponseDto<Usuario> tableResourceResponseDto = new TableResourceResponseDto<Usuario>(tableRequestDto, recordNum, paginatedList);
		
		return tableResourceResponseDto;
	}
}	
