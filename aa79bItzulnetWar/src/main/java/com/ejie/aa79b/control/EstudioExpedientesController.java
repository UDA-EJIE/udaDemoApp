package com.ejie.aa79b.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejie.aa79b.model.BitacoraExpediente;
import com.ejie.aa79b.model.EstadosExpediente;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.ListaExpediente;
import com.ejie.aa79b.model.enums.EstadoExpedienteEnum;
import com.ejie.aa79b.service.ExpedienteService;
import com.ejie.x38.control.bind.annotation.RequestJsonBody;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;

@Controller
@RequestMapping(value = "/tramitacionexpedientes/gestionexpedientes/estudioexpedientes")

public class EstudioExpedientesController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExpedienteController.class);

	@Autowired
	private ExpedienteService expedienteService;

	/**
	 * Funcion que asigna un tecnico a uno o varios expedientes
	 */
	@RequestMapping(value = "/asignarTecnicoAExpedientes", method = RequestMethod.POST)
	public @ResponseBody Integer asignarTecnicoAExpedientes(@RequestJsonBody ListaExpediente listaExpedientes) {

		EstudioExpedientesController.LOGGER.info("[POST] : Expedientes modificados correctamente");
		return this.expedienteService.asignarTecnicoAExpedientes(listaExpedientes);
	}

	/*
	 * METODOS COMPONENTE RUP_TABLE
	 * 
	 */

	/**
	 * Metodo de presentacion del RUP_TABLE.
	 * 
	 * @param model
	 *            Model
	 * @return String
	 */
	@RequestMapping(value = "/maint", method = RequestMethod.GET)
	public String getFormEdit(Model model) {
		EstudioExpedientesController.LOGGER.info("[GET - View] : expediente");
		return "estudioexpedientes";
	}

	/**
	 * Operacion de filtrado del componente RUP_TABLE.
	 * 
	 * @param filterExpediente
	 *            Expediente Bean que contiene los parametros de filtrado a
	 *            emplear.
	 * @param jqGridRequestDto
	 *            Dto que contiene los parametros de configuracion propios del
	 *            RUP_TABLE a aplicar en el filtrado.
	 * @return JQGridResponseDto<Expediente> Dto que contiene el resultado del
	 *         filtrado realizado por el componente RUP_TABLE.
	 */
	@RequestMapping(value = "/filter", method = RequestMethod.POST)
	public @ResponseBody JQGridResponseDto<Expediente> filter(
			@RequestJsonBody(param = "filter") Expediente filterExpediente,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto) {
		EstudioExpedientesController.LOGGER.info("[POST - filter] : Obtener Expedientes");
		if (filterExpediente.getBitacoraExpediente() != null
				&& filterExpediente.getBitacoraExpediente().getEstadoExp() != null) {
			filterExpediente.getBitacoraExpediente().getEstadoExp()
					.setId(Long.valueOf(EstadoExpedienteEnum.EN_ESTUDIO.getValue()));
		} else {
			BitacoraExpediente bitacoraExpediente = new BitacoraExpediente();
			EstadosExpediente estadoExpediente = new EstadosExpediente();
			estadoExpediente.setId(Long.valueOf(EstadoExpedienteEnum.EN_ESTUDIO.getValue()));
			bitacoraExpediente.setEstadoExp(estadoExpediente);
			filterExpediente.setBitacoraExpediente(bitacoraExpediente);

		}

		return this.expedienteService.filtroExpedienteEstadoEnEstudio(filterExpediente, jqGridRequestDto, false);
	}

	@RequestMapping(value = "/enPestana", method = RequestMethod.GET)
	public String getEstudioPestana(Model model) {
		EstudioExpedientesController.LOGGER.info("[GET - View] : getEstudioPestana");
		return "estudioexpedientespestana";
	}
}
