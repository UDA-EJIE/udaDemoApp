package com.ejie.aa79b.control;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejie.aa79b.model.ExpTareasResumen;
import com.ejie.aa79b.model.ResumenGraficas;
import com.ejie.aa79b.security.Usuario;
import com.ejie.aa79b.service.DashboardService;
import com.ejie.x38.control.bind.annotation.RequestJsonBody;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;

/**
 * DashboardController.
 * 
 * @author javarona
 */

@Controller
@RequestMapping(value = "/dashboard")
public class DashboardController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DashboardController.class);

	@Autowired()
	private DashboardService dashboardService;

	/**
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/maint", method = RequestMethod.GET)
	public String getInicio() {
		DashboardController.LOGGER.info("getInicio");
		return "dashboard";
	}
	/**
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/estudio/maint", method = RequestMethod.GET)
	public String getInicioEstudio() {
		DashboardController.LOGGER.info("getInicioEstudio");
		return "dashboardpestestudio";
	}

	/**
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/estudio/getExpedientesChartsData", method = RequestMethod.GET)
	public @ResponseBody List<List<ResumenGraficas>> getChartsData() {
		DashboardController.LOGGER.info("[GET] :/estudio/getExpedientesChartsData - getChartsData");
		// Se recupera el dni del usuario
		final Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication().getCredentials();
		return this.dashboardService.getEstudioExpedChartsData(credentials.getNif());
	}

	/*
	 * Gráficas de planificación * / /**
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/planificacion/maint", method = RequestMethod.GET)
	public String getInicioPlanificacion() {
		DashboardController.LOGGER.info("[GET - View] : getEstudio");
		return "dashboardpestplanificacion";
	}

	/**
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/planificacion/getChartsData", method = RequestMethod.GET)
	public @ResponseBody List<List<ResumenGraficas>> getPlanificacionChartsData() {
		DashboardController.LOGGER.info("[GET] : getChartsData");
		// Se recupera el dni del usuario
		final Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication().getCredentials();
		return this.dashboardService.getPlanificacionChartsData(credentials.getNif());
	}

	@RequestMapping(value = "/planificacion/datos/maint", method = RequestMethod.GET)
	public String getTablaPlanificacion() {
		DashboardController.LOGGER.info("[GET - View] : getTablaPlanificacion");
		return "dashboardpestplanificaciondatos";
	}

	/* grafica de carga de trabajo --> Tareas */

	/**
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/cargatrabajo/maint", method = RequestMethod.GET)
	public String getInicioCarga() {
		DashboardController.LOGGER.info("[GET - View] : getEstudio");
		return "dashboardpestcarga";
	}

	/**
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/cargatrabajo/getChartsData", method = RequestMethod.GET)
	public @ResponseBody List<List<ResumenGraficas>> getCargaChartsData() {
		DashboardController.LOGGER.info("[GET] : getChartsData");
		// Se recupera el dni del usuario
		final Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication().getCredentials();
		return this.dashboardService.getCargaChartsData(credentials.getNif());
	}

	@RequestMapping(value = "/cargatrabajo/datos/maint", method = RequestMethod.GET)
	public String getTablaCarga() {
		DashboardController.LOGGER.info("[GET - View] : getTablaPlanificacion");
		return "dashboardpestcargadatos";
	}

	@RequestMapping(value = "/datos/dashboardCarga/filter", method = RequestMethod.POST)
	public @ResponseBody JQGridResponseDto<ExpTareasResumen> getExpedientesTareas(
			@RequestJsonBody(param = "filter") ExpTareasResumen filter,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto) {
		DashboardController.LOGGER.info("[POST - filter] : Obtener Expedientes");
		final String sidx = jqGridRequestDto.getSidx();
		jqGridRequestDto.setSidx(sidx.replaceAll("anyoNumExpConcatenado asc,", ""));

		final Usuario credentials = (Usuario) SecurityContextHolder.getContext().getAuthentication().getCredentials();

		int tipoFiltro = Integer.parseInt(filter.getIdsGrupoTrabajo());

		return this.dashboardService.filterResumenCarga(tipoFiltro, credentials.getNif(), jqGridRequestDto, false);
	}

	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/welcome/maint", method = RequestMethod.GET)
	public String getWelcome() {
		DashboardController.LOGGER.info("getWelcome");
		return "welcome";
	}
}