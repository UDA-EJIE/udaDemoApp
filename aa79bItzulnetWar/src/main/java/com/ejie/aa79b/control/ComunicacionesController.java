package com.ejie.aa79b.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.model.Comunicaciones;
import com.ejie.aa79b.service.ComunicacionesService;
import com.ejie.x38.control.bind.annotation.RequestJsonBody;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;

@Controller
@RequestMapping(value = "/comunicaciones")

public class ComunicacionesController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComunicacionesController.class);

	@Autowired
	private ComunicacionesService comunicacionesService;


	@RequestMapping(value = "/{id0D4}", method = RequestMethod.GET)
	public @ResponseBody Comunicaciones get(@PathVariable Long id0D4) {
		Comunicaciones comunicaciones = new Comunicaciones();
		comunicaciones.setId0D4(id0D4);
		comunicaciones = this.comunicacionesService.find(comunicaciones);
		ComunicacionesController.LOGGER.info("[GET - findBy_PK] : Obtener Comunicaciones por PK");
		return comunicaciones;
	}




    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Comunicaciones add(@RequestBody Comunicaciones comunicacion) {
    	comunicacion.setTipo( Constants.COMUNICACION_ENVIADA);
    	Comunicaciones comunicacionAux = this.comunicacionesService.createNewComunicacion(comunicacion);
        ComunicacionesController.LOGGER.info("[POST] : comunicacion insertada correctamente");
        return comunicacionAux;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody Comunicaciones edit(@RequestBody Comunicaciones comunicacion) {
    	Comunicaciones comunicacionAux = this.comunicacionesService.updateComunicacion(comunicacion);
        ComunicacionesController.LOGGER.info("[PUT] : comunicacion actualizada correctamente");
        return comunicacionAux;
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
		ComunicacionesController.LOGGER.info("[GET - View] : comunicaciones");
		return "comunicaciones";
	}

	/**
	 * Operacion de filtrado del componente RUP_TABLE.
	 *
	 * @param filterComunicaciones
	 *            Comunicaciones Bean que contiene los parametros de filtrado a
	 *            emplear.
	 * @param jqGridRequestDto
	 *            Dto que contiene los parametros de configuracion propios del
	 *            RUP_TABLE a aplicar en el filtrado.
	 * @return JQGridResponseDto<Comunicaciones> Dto que contiene el resultado del
	 *         filtrado realizado por el componente RUP_TABLE.
	 */
	@RequestMapping(value = "/filter", method = RequestMethod.POST)
	public @ResponseBody JQGridResponseDto<Comunicaciones> filter(
			@RequestJsonBody(param = "filter") Comunicaciones filterComunicaciones,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto) {
		ComunicacionesController.LOGGER.info("[POST - filter] : Obtener Comunicaciones");
		return this.comunicacionesService.filter(filterComunicaciones, jqGridRequestDto, false);
	}
}
