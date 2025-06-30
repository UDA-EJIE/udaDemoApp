package com.ejie.aa79b.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ejie.aa79b.model.ListaReceptoresAutorizados;
import com.ejie.aa79b.model.ReceptorAutorizado;
import com.ejie.aa79b.service.ReceptorAutorizadoService;
import com.ejie.x38.control.bind.annotation.RequestJsonBody;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;

@Controller
@RequestMapping (value = "/receptoresautorizados")
public class ReceptoresAutorizadosController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReceptoresAutorizadosController.class);
	
	@Autowired ReceptorAutorizadoService receptorAutorizadoService;

	
	/**
	 * Devuelve una lista de beans correspondientes a los valores de filtrados
	 * indicados en el objeto pasado como parametro.
	 *
	 * @param filterReceptorAutorizado ReceptorAutorizado
	 *            Objeto que contiene los parametros de filtrado utilizados en
	 *            la busqueda.
	 * @return List<ReceptorAutorizado> 
	 *            Lista de objetos correspondientes a la busqueda realizada.
	 */
	@RequestMapping(value = "/filter", method = RequestMethod.POST)
	public @ResponseBody JQGridResponseDto<ReceptorAutorizado> getAll(@RequestJsonBody(param="filter") ReceptorAutorizado filterReceptorAutorizado,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto) {
		ReceptoresAutorizadosController.LOGGER.info("[GET - find_ALL] : Obtener ReceptorAutorizado por filtro");
	    return this.receptorAutorizadoService.filter(filterReceptorAutorizado, jqGridRequestDto, false);
	}
	
	/**
	 * Operacion CRUD Read. Devuelve el bean correspondiente al identificador indicado.
	 * 
	 * @param dni String
	 * @return DatosPersona 
	 *            Objeto correspondiente al identificador indicado.
	 */
	@RequestMapping(value = "/comprobarYGuardar", method = RequestMethod.POST)
	public @ResponseBody ListaReceptoresAutorizados comprobarYGuardar(@RequestJsonBody ListaReceptoresAutorizados listaReceptoresAutorizados) {
		ReceptoresAutorizadosController.LOGGER.info("[GET - findBy_PK] : Obtener DatosPersona por dni");
		return this.receptorAutorizadoService.comprobarYGuardar(listaReceptoresAutorizados);
	}
	
	/**
	 * Operacion CRUD Read. Devuelve el bean correspondiente al identificador indicado.
	 * 
	 * @param dni String
	 * @return DatosPersona 
	 *            Objeto correspondiente al identificador indicado.
	 */
	@RequestMapping(value = "/eliminarReceptorAutorizado", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void eliminarReceptorAutorizado(@RequestJsonBody ReceptorAutorizado receptorAutorizado) {
		ReceptoresAutorizadosController.LOGGER.info("[GET - findBy_PK] : Eliminar DatosPersona por dni");
		this.receptorAutorizadoService.eliminarReceptorAutorizado(receptorAutorizado);
	}
	
}
