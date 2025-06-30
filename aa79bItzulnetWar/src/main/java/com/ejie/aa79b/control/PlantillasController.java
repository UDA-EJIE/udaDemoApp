package com.ejie.aa79b.control;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejie.aa79b.model.Plantillas;
import com.ejie.aa79b.service.PlantillasService;
import com.ejie.x38.control.bind.annotation.RequestJsonBody;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;
import com.ejie.x38.dto.TableRowDto;

@Controller
@RequestMapping(value = "/administracion/datosmaestros/plantillas")

public class PlantillasController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PlantillasController.class);

	@Autowired
	private PlantillasService plantillasService;

	/**
	 * Operacion CRUD Read. Devuelve el bean correspondiente al identificador
	 * indicado.
	 * 
	 * @param id0A7
	 *            Long
	 * @return Plantillas Objeto correspondiente al identificador indicado.
	 */
	@RequestMapping(value = "/{id0A7}", method = RequestMethod.GET)
	public @ResponseBody Plantillas get(@PathVariable Long id0A7) {
		Plantillas plantillas = new Plantillas();
		plantillas.setId0A7(id0A7);
		plantillas = this.plantillasService.find(plantillas);
		PlantillasController.LOGGER.info("[GET - findBy_PK] : Obtener Plantillas por PK");
		return plantillas;
	}

	/**
	 * Devuelve una lista de beans correspondientes a los valores de filtrados
	 * indicados en el objeto pasado como parametro.
	 *
	 * @param filterPlantillas
	 *            Plantillas Objeto que contiene los parametros de filtrado
	 *            utilizados en la busqueda.
	 * @return List<Plantillas> Lista de objetos correspondientes a la busqueda
	 *         realizada.
	 */
	@RequestMapping(value = "/mostrarPlantillas", method = RequestMethod.GET)
	public @ResponseBody() List<Plantillas> getAllMotivos(@ModelAttribute() Plantillas filterPlantillas,
			Locale locale) {
		PlantillasController.LOGGER.info("[GET - find_ALL] : Obtener Plantillas");
		JQGridRequestDto jqGridRequestDto = new JQGridRequestDto();

		jqGridRequestDto.setSord("ASC");

		return this.plantillasService.findAllLike(filterPlantillas, jqGridRequestDto, false);

	}

	@RequestMapping(value = "/crearActualizarPlantilla", method = RequestMethod.POST)
	public @ResponseBody Plantillas actualizarPlantilla(@RequestBody Plantillas plantilla) {
		Plantillas plantillaAux;
		if(plantilla.getId0A7() != null) {
			// editar
			plantillaAux = this.plantillasService.updatePlantilla(plantilla);
		}else {
			// nuevo
			plantillaAux = this.plantillasService.createNewPlantilla(plantilla);
		}
		PlantillasController.LOGGER.info("[PUT] : Plantillas actualizado correctamente");
		return plantillaAux;
	}

	/**
	 * Metodo de presentacion del RUP_TABLE.
	 * 
	 * @param model
	 *            Model
	 * @return String
	 */
	@RequestMapping(value = "/maint", method = RequestMethod.GET)
	public String getFormEdit(Model model) {
		PlantillasController.LOGGER.info("[GET - View] : plantillas");
		return "plantillas";
	}

	/**
	 * Operacion de filtrado del componente RUP_TABLE.
	 * 
	 * @param filterPlantillas
	 *            Plantillas Bean que contiene los parametros de filtrado a
	 *            emplear.
	 * @param jqGridRequestDto
	 *            Dto que contiene los parametros de configuracion propios del
	 *            RUP_TABLE a aplicar en el filtrado.
	 * @return JQGridResponseDto<Plantillas> Dto que contiene el resultado del
	 *         filtrado realizado por el componente RUP_TABLE.
	 */
	@RequestMapping(value = "/filter", method = RequestMethod.POST)
	public @ResponseBody JQGridResponseDto<Plantillas> filter(
			@RequestJsonBody(param = "filter") Plantillas filterPlantillas,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto) {
		PlantillasController.LOGGER.info("[POST - filter] : Obtener Plantillas");
		return this.plantillasService.filter(filterPlantillas, jqGridRequestDto, false);
	}

	/**
	 * Operacion de busqueda del componente RUP_TABLE.
	 * 
	 * @param filterPlantillas
	 *            Plantillas Bean que contiene los parametros de filtrado a
	 *            emplear.
	 * @param searchPlantillas
	 *            Plantillas Bean que contiene los parametros de busqueda a
	 *            emplear.
	 * @param jqGridRequestDto
	 *            Dto que contiene los parametros de configuracion propios del
	 *            RUP_TABLE a aplicar en la búsqueda.
	 * @return TableRowDto<Plantillas> Dto que contiene el resultado de la
	 *         busqueda realizada por el componente RUP_TABLE.
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public @ResponseBody List<TableRowDto<Plantillas>> search(
			@RequestJsonBody(param = "filter") Plantillas filterPlantillas,
			@RequestJsonBody(param = "search") Plantillas searchPlantillas,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto) {
		PlantillasController.LOGGER.info("[POST - search] : Buscar Plantillas");
		return this.plantillasService.search(filterPlantillas, searchPlantillas, jqGridRequestDto, false);
	}
}
