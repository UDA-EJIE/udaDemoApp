package com.ejie.aa79b.control;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejie.aa79b.model.Clonacion;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.service.ExpedienteService;
import com.ejie.aa79b.service.ServicioClonacionExpedientesService;
import com.ejie.x38.control.bind.annotation.RequestJsonBody;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;

/**
 * 
 * @author javarona
 */

@Controller
@RequestMapping(value = "/servicios/clonacionexpedientes")

public class ServicioClonacionExpedientesController {

	@Autowired()
	private ExpedienteService expedienteService;
	@Autowired()
	private ServicioClonacionExpedientesService servicioClonacionExpedientesService;

	private static final Logger LOGGER = LoggerFactory.getLogger(ServicioClonacionExpedientesController.class);

	/**
	 * Metodo de presentacion del RUP_TABLE.
	 * 
	 * @param model
	 *            Model
	 * @return String
	 */
	@RequestMapping(value = "/maint", method = RequestMethod.GET)
	public String getClonacion() {
		ServicioClonacionExpedientesController.LOGGER.info("[GET - View] : expedientealta");
		return "servicioClonacion";
	}

	@RequestMapping(value = "/filter", method = RequestMethod.POST)
	public @ResponseBody JQGridResponseDto<Expediente> getFilterClonacion(
			@RequestJsonBody(param = "filter") Expediente expedienteFilter,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto) {
		ServicioClonacionExpedientesController.LOGGER.info("[POST - filter] : Obtener expedientes");

		return this.expedienteService.filtroExpedienteEstadoEnEstudio(expedienteFilter, jqGridRequestDto, false);
	}

	@RequestMapping(value = "/clonarExpediente", method = RequestMethod.POST)
	public @ResponseBody Clonacion clonarExpediente(
			@RequestJsonBody(required = false, param = "clonacion") Clonacion clonacion, HttpServletRequest request) {

		ServicioClonacionExpedientesController.LOGGER.info("[POST ] : clonarExpediente");

		this.servicioClonacionExpedientesService.clonarExpediente(clonacion, request);

		return clonacion;
	}

	/* PROCESOS DE CLONACIÓN */

	/**
	 * Metodo de presentacion del RUP_TABLE.
	 * 
	 * @param model
	 *            Model
	 * @return String
	 */
	@RequestMapping(value = "/procesos/maint", method = RequestMethod.GET)
	public String getPantallaProcesos() {
		ServicioClonacionExpedientesController.LOGGER.info("[GET - View] : getPantallaProcesos");
		return "procesosClonacion";
	}

	@RequestMapping(value = "/procesos/filter", method = RequestMethod.POST)
	public @ResponseBody JQGridResponseDto<Clonacion> getFilterProcesos(
			@RequestJsonBody(param = "filter") Clonacion clonacionFilter,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto) {
		ServicioClonacionExpedientesController.LOGGER.info("[POST - filter] : getFilterProcesos");

		return this.servicioClonacionExpedientesService.filter(clonacionFilter, jqGridRequestDto, false);
	}
}
