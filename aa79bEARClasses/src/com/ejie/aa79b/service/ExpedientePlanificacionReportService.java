package com.ejie.aa79b.service;

import com.ejie.aa79b.model.ExpedientePlanificacion;
import com.ejie.aa79b.model.excel.ExcelModel;
import com.ejie.x38.dto.JQGridRequestDto;

public interface ExpedientePlanificacionReportService {

	/**
	 * 
	 * @param filterExpediente ExpedientePlanificacion
	 * @param columns          String
	 * @return ExcelModel
	 */
	ExcelModel getInformePlanificacion(ExpedientePlanificacion filterExpediente, String columns,
			JQGridRequestDto jqGridRequestDto);

}
