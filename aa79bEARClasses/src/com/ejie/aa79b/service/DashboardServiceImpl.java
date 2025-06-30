package com.ejie.aa79b.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejie.aa79b.dao.DashboardDao;
import com.ejie.aa79b.model.ExpTareasResumen;
import com.ejie.aa79b.model.ResumenGraficas;
import com.ejie.aa79b.model.enums.DashboardCargaTareasEnum;
import com.ejie.aa79b.model.enums.DashboardEstudioExpedEnum;
import com.ejie.aa79b.model.enums.DashboardPlanifExpedientesEnum;
import com.ejie.aa79b.model.enums.DashboardPlanifTareasEnum;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;

/**
 * DashboardServiceImpl.
 * 
 * @author javarona
 */
@Service(value = "dashboardService")
public class DashboardServiceImpl implements DashboardService {

	@Autowired
	private DashboardDao dashboardDao;

	@Override
	public List<List<ResumenGraficas>> getEstudioExpedChartsData(String dni) {
		final List<List<ResumenGraficas>> lista = new ArrayList<List<ResumenGraficas>>();
		final List<ResumenGraficas> listaExpedientes = new ArrayList<ResumenGraficas>();

		// Se recupera el primer gráfico: Expedientes
		for (DashboardEstudioExpedEnum dashboardEstudioExpedEnum : DashboardEstudioExpedEnum.values()) {
			listaExpedientes.add(this.dashboardDao.getEstudioExpedChartsData(dni, dashboardEstudioExpedEnum.getId()));
		}
		lista.add(listaExpedientes);

		return lista;
	}

	@Override
	public List<List<ResumenGraficas>> getPlanificacionChartsData(String dni) {
		final List<List<ResumenGraficas>> lista = new ArrayList<List<ResumenGraficas>>();
		final List<ResumenGraficas> listaExpedientes = new ArrayList<ResumenGraficas>();
		final List<ResumenGraficas> listaTareas = new ArrayList<ResumenGraficas>();

		// Se recupera el primer gráfico: Expedientes
		for (DashboardPlanifExpedientesEnum planifExpedientesEnum : DashboardPlanifExpedientesEnum.values()) {
			listaExpedientes.add(this.dashboardDao.getPlanificacionExpedChartsData(dni, planifExpedientesEnum.getId()));
		}
		lista.add(listaExpedientes);

		// Se recupera el segundo gráfico: Tareas
		for (DashboardPlanifTareasEnum planifTareasEnum : DashboardPlanifTareasEnum.values()) {
			listaTareas.add(this.dashboardDao.getPlanificacionTareasChartsData(dni, planifTareasEnum.getId()));
		}
		lista.add(listaTareas);

		return lista;
	}

	@Override
	public List<List<ResumenGraficas>> getCargaChartsData(String dni) {
		final List<List<ResumenGraficas>> lista = new ArrayList<List<ResumenGraficas>>();
		final List<ResumenGraficas> listaTareas = new ArrayList<ResumenGraficas>();

		// Se recupera el segundo gráfico: Tareas
		for (DashboardCargaTareasEnum planifTareasEnum : DashboardCargaTareasEnum.values()) {
			listaTareas.add(this.dashboardDao.getCargaTareasChartsData(dni, planifTareasEnum.getId()));
		}
		lista.add(listaTareas);

		return lista;
	}

	@Override
	public JQGridResponseDto<ExpTareasResumen> filterResumenCarga(Integer filtroDatosCarga, String dni,
			JQGridRequestDto jqGridRequestDto, Boolean startsWith) {

		List<ExpTareasResumen> listaT = this.dashboardDao.findAllExpTareasResumenCarga(filtroDatosCarga, dni,
				jqGridRequestDto, false);
		Long recordNum = this.dashboardDao.findAllExpTareasResumenCargaCount(filtroDatosCarga, dni, false);

		return new JQGridResponseDto<ExpTareasResumen>(jqGridRequestDto, recordNum, listaT);
	}

}