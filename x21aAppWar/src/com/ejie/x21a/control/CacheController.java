package com.ejie.x21a.control;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejie.x21a.model.Usuario;
import com.ejie.x21a.service.CacheService;
import com.ejie.x38.control.bind.annotation.RequestJsonBody;
import com.ejie.x38.dto.TableRequestDto;
import com.ejie.x38.dto.TableResourceResponseDto;
import com.ejie.x38.hdiv.annotation.UDALink;
import com.ejie.x38.hdiv.annotation.UDALinkAllower;
 
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
	@RequestMapping(value = "/inlineEdit", method = RequestMethod.POST)
	public String getTableInlineEdit (
			@RequestParam(required = true) String actionType,
			@RequestParam(required = true) String tableID,
			@RequestParam(required = false) String mapping,
			Model model) {
		model.addAttribute("entity", new Usuario());
		model.addAttribute("actionType", actionType);
		model.addAttribute("tableID", tableID);
		
		// Controlar que el mapping siempre se añada al modelo de la manera esperada
		if (mapping == null || mapping.isEmpty()) {
			mapping = "/integracion/cache";
		} else if (mapping.endsWith("/")) {
			mapping = mapping.substring(0, mapping.length() - 1);
		}
		model.addAttribute("mapping", mapping);
		
		logger.info("[POST - View] : tableInlineEditAuxForm");
		return "tableInlineEditAuxForm";
	}
	
	@UDALink(name = "get", linkTo = { @UDALinkAllower(name = "edit"), @UDALinkAllower(name = "filter") })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Resource<Usuario> get(@PathVariable String id) {
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
		logger.info("[PUT - table] : ¡Entidad correctamente actualizada!");
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
