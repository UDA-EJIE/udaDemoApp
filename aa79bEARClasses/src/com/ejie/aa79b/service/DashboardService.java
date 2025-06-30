package com.ejie.aa79b.service;

import java.util.List;

import com.ejie.aa79b.model.ExpTareasResumen;
import com.ejie.aa79b.model.ResumenGraficas;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;

/**
 * DashboardService.
 * 
 * @author javarona
 */
public interface DashboardService {

	List<List<ResumenGraficas>> getEstudioExpedChartsData(String dni);

	List<List<ResumenGraficas>> getPlanificacionChartsData(String dni);

	List<List<ResumenGraficas>> getCargaChartsData(String dni);

	JQGridResponseDto<ExpTareasResumen> filterResumenCarga(Integer filtroDatosCarga, String dni,
			JQGridRequestDto jqGridRequestDto, Boolean startsWith);

}