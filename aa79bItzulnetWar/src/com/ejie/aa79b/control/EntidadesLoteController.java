/**
 *
 */
package com.ejie.aa79b.control;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ejie.aa79b.model.EntidadesLote;
import com.ejie.aa79b.service.EntidadesLoteService;
import com.ejie.x38.control.bind.annotation.RequestJsonBody;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;

/**
 * @author eaguirresarobe
 *
 */

@Controller
@RequestMapping (value = "/administracion/entidadeslote")
public class EntidadesLoteController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EntidadesLoteController.class);

	@Autowired
	private EntidadesLoteService entidadesLoteService;


	/*
	 * OPERACIONES CRUD (Create, Read, Update, Delete)
	 *
	 */

	/**
	 * Operacion CRUD Read. Devuelve el bean correspondiente al identificador indicado.
	 *
	 * @param idLote Long
	 * @return EntidadesLote
	 */
	@RequestMapping(value = "/{idLote}", method = RequestMethod.GET)
	public @ResponseBody EntidadesLote get(@PathVariable Long idLote) {
		EntidadesLote entidadesLote = new EntidadesLote();
		entidadesLote.setIdLote(idLote);
        entidadesLote = this.entidadesLoteService.find(entidadesLote);
        EntidadesLoteController.LOGGER.info("[GET - findBy_PK] : Obtener EntidadesLote por PK");
        return entidadesLote;
	}

	/**
	 * Devuelve una lista de beans correspondientes a los valores de filtrados
	 * indicados en el objeto pasado como parametro.
	 *
	 * @param filterEntidadesLote EntidadesLote
	 *            Objeto que contiene los parametros de filtrado utilizados en
	 *            la busqueda.
	 * @return List<EntidadesLote>
	 *            Lista de objetos correspondientes a la busqueda realizada.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<EntidadesLote> getAll(@ModelAttribute EntidadesLote filterEntidadesLote) {
		EntidadesLoteController.LOGGER.info("[GET - find_ALL] : Obtener EntidadesLote por filtro");
	    return this.entidadesLoteService.findAll(filterEntidadesLote, null);
	}

	/**
	 * Operacion CRUD Edit. Modificacion del bean indicado.
	 *
	 * @param entidadesLote EntidadesLote
	 *            Bean que contiene la informacion a modificar.
	 * @return EntidadesLote
	 *            Bean resultante de la modificacion.
	 */
	@RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody EntidadesLote edit(@RequestBody EntidadesLote entidadesLote) {
		EntidadesLote entidadesLoteAux = this.entidadesLoteService.update(entidadesLote);
		EntidadesLoteController.LOGGER.info("[PUT] : EntidadesLote actualizado correctamente");
        return entidadesLoteAux;
    }

	/**
	 * Operacion CRUD Create. Creacion de un nuevo registro a partir del bean
	 * indicado.
	 *
	 * @param entidadesLote EntidadesLote
	 *            Bean que contiene la informacion con la que se va a crear el
	 *            nuevo registro.
	 * @return EntidadesLote
	 *            Bean resultante del proceso de creacion.
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody EntidadesLote add(@RequestBody EntidadesLote entidadesLote) {
		EntidadesLote entidadesLoteAux = this.entidadesLoteService.add(entidadesLote);
		EntidadesLoteController.LOGGER.info("[POST] : EntidadesLote insertado correctamente");
    	return entidadesLoteAux;
	}

	/**
	 *
	 * Operacion CRUD Delete. Borrado del registro correspondiente al
	 * identificador especificado.
	 *
	 * @param idLote Long
	 * @param tipo String
	 * @param codigo Integer
	 * @return EntidadesLote
	 */
	@RequestMapping(value = "/{idLote}_{tipo}_{codigo}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody EntidadesLote remove(@PathVariable() Long idLote, @PathVariable() String tipo, @PathVariable() Integer codigo) {
		EntidadesLote entidadesLote = new EntidadesLote();
		entidadesLote.setIdLote(idLote);
		entidadesLote.setTipo(tipo);
		entidadesLote.setCodigo(codigo);
        this.entidadesLoteService.remove(entidadesLote);
        EntidadesLoteController.LOGGER.info("[DELETE] : EntidadesLote borrado correctamente");
       	return entidadesLote;
    }

	/**
	 *
	 * @param idLote Long
	 * @return EntidadesLote
	 */
	@RequestMapping(value = "/removeWhereID/{idGrupo}", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody EntidadesLote removeWhereID(@PathVariable() Long idLote) {
		EntidadesLote entidadesLote = new EntidadesLote();
		entidadesLote.setIdLote(idLote);
        this.entidadesLoteService.removeWhereID(entidadesLote);
        EntidadesLoteController.LOGGER.info("[DELETE] : EntidadesLote borrado correctamente");
       	return entidadesLote;
    }

	@RequestMapping(value = "/filter", method = RequestMethod.POST)
	public @ResponseBody JQGridResponseDto<EntidadesLote> filter(
			@RequestJsonBody(param="filter") EntidadesLote filterEntidadesLote,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto) {
		EntidadesLoteController.LOGGER.info("[POST - filter] : Obtener EntidadesLote");
		return this.entidadesLoteService.filter(filterEntidadesLote, jqGridRequestDto, false);
	}

	/**
	 *
	 * @param model model
	 * @param bean EntidadesLote
	 * @return Boolean
	 */
	@RequestMapping(value = "/esEntidadAsociada", method = RequestMethod.POST)
	public @ResponseBody Boolean esEntidadAsociada(Model model, @RequestJsonBody() EntidadesLote bean) {
		return this.entidadesLoteService.findAllCount(bean) > 0;
	}

	/**
	 *
	 * @param model model
	 * @param bean EntidadesLote
	 * @return EntidadesLote
	 */
	@RequestMapping(value = "/obtenerComentarioEntidad", method = RequestMethod.POST)
	public @ResponseBody EntidadesLote obtenerComentarioEntidad(Model model, @RequestJsonBody() EntidadesLote bean) {
		return this.entidadesLoteService.obtenerComentarioEntidad(bean);
	}

	/**
	 *
	 * @param model model
	 * @param bean EntidadesLote
	 * @return Boolean
	 */
	@RequestMapping(value = "/anyadirComentarioEntidad", method = RequestMethod.POST)
	public @ResponseBody Integer anyadirComentarioEntidad(Model model, @RequestJsonBody() EntidadesLote bean) {
		return this.entidadesLoteService.anyadirComentarioEntidad(bean);
	}

}
