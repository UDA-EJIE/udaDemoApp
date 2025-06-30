package com.ejie.aa79b.dao;

import java.util.List;

import com.ejie.aa79b.model.ExpTareasResumen;
import com.ejie.aa79b.model.ResumenGraficas;
import com.ejie.x38.dto.JQGridRequestDto;

/**
 * DashboardDao
 * 
 * @author javarona
 */

public interface DashboardDao {

	ResumenGraficas getEstudioExpedChartsData(String dni, int tipoConsulta);

	ResumenGraficas getPlanificacionExpedChartsData(String dni, int tipoConsulta);

	ResumenGraficas getPlanificacionTareasChartsData(String dni, int tipoConsulta);

	ResumenGraficas getCargaTareasChartsData(String dni, int tipoConsulta);

	List<ExpTareasResumen> findAllExpTareasResumenCarga(Integer filtroDatosCarga, String dni,
			JQGridRequestDto jqGridRequestDto, boolean startsWith);

	Long findAllExpTareasResumenCargaCount(Integer filtroDatosCarga, String dni, boolean b);

}
