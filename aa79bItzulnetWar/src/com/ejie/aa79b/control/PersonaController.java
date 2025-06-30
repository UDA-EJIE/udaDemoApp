package com.ejie.aa79b.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.model.Solicitante;
import com.ejie.aa79b.service.SolicitanteService;
import com.ejie.x38.control.bind.annotation.RequestJsonBody;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;

@Controller
@RequestMapping (value = "/persona")

public class PersonaController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PersonaController.class);

	@Autowired()
	private SolicitanteService solicitanteService;

	@RequestMapping(value = "/solicitantes/filter", method = RequestMethod.POST)
	public @ResponseBody JQGridResponseDto<Solicitante> filter(
			@RequestJsonBody(param = "filter") Solicitante filterSolicitante,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto) {
		PersonaController.LOGGER.info("[POST - filter] : Obtener Solicitantes");
		return this.solicitanteService.filter(filterSolicitante, jqGridRequestDto, false);
	}
	
	@RequestMapping(value = "/{destino}", method = RequestMethod.POST)
	public @ResponseBody JQGridResponseDto<Solicitante> get(@PathVariable String destino, 
			@RequestJsonBody JQGridRequestDto jqGridRequestDto) {
		PersonaController.LOGGER.info("[POST - filter] : Obtener Solicitantes");
		if(destino!=null){
			if(Constants.ROL_SOLICITANTE.equals(destino)){
				return this.solicitanteService.filter(new Solicitante(), jqGridRequestDto, false);
			}else if(Constants.ROL_RECEPTOR_AUTORIZADO.equals(destino)){
//				return this.
			}
		}
		return null;
		
	}
	
}
