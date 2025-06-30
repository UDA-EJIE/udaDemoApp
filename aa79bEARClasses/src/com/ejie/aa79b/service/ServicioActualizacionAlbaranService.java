package com.ejie.aa79b.service;

import java.util.List;
import java.util.Locale;

import com.ejie.aa79b.model.Albaran;
import com.ejie.aa79b.model.DatosPagoProveedores;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;

public interface ServicioActualizacionAlbaranService extends GenericoService<Albaran> {

	JQGridResponseDto<Albaran> filter(Albaran albaranFilter, JQGridRequestDto jqGridRequestDto, boolean startsWith);

	Albaran obtenerDatosDetalleAlbaran(Albaran albaran);

	JQGridResponseDto<DatosPagoProveedores> detalleTareasAlbaran(DatosPagoProveedores datosPagoProveedores,
			JQGridRequestDto jqGridRequestDto, Boolean startsWith);

	Integer comprobarEstadoAlbaranTareas(String[] refAlbaranListString);

	void borrarAlbaran(String[] refAlbaranListString, Locale locale);

	void desasociarAlbaran(String[] idTareasList, Locale locale);

	/**
	 * Operacion de obtencion de ids de elementos seleccionados de RUP_TABLE.
	 * 
	 * @param filterAlbaran
	 *            Albaran
	 * @param tableData
	 *            JQGridRequestDto
	 * @return List<Albaran>
	 */
	List<Albaran> filterGetSelectedIds(Albaran filterAlbaran, JQGridRequestDto tableData);
}
