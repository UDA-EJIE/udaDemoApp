package com.ejie.aa79b.dao;

import java.math.BigDecimal;
import java.util.List;

import com.ejie.aa79b.model.Albaran;
import com.ejie.aa79b.model.DatosPagoProveedores;
import com.ejie.aa79b.model.EntradaAlbaranTareas;
import com.ejie.aa79b.model.Tareas;
import com.ejie.aa79b.model.webservices.Aa79bAlbaran;
import com.ejie.aa79b.model.webservices.Aa79bEntradaTarea;
import com.ejie.aa79b.model.webservices.Aa79bSalidaTarea;
import com.ejie.x38.dto.JQGridRequestDto;

/**
 * @author mrodriguez
 *
 */
public interface Aa79bAlbaranDao extends GenericoDao<Aa79bAlbaran> {

	List<Aa79bAlbaran> obtenerBuscadorAlbaran(Albaran bean);

	Object findTareasAlbaran(Aa79bEntradaTarea bean, JQGridRequestDto jqGridRequestDto, Boolean startsWith,
			Boolean isCount, Boolean pagination);

	Long existeReferencia(Albaran bean);

	void crearAlbaran(Albaran bean);

	Albaran updateEstadoAlbaran(Albaran bean);

	Tareas obtenerIdTareaPagoProveedorRel(Tareas bean);

	DatosPagoProveedores asociarAlbaran(DatosPagoProveedores bean);

	DatosPagoProveedores desasociarAlbaran(DatosPagoProveedores bean);

	Aa79bSalidaTarea detalleTareaAlbaran(Aa79bEntradaTarea bean);

	Long comprobarAsociacionAlbaranPorTarea(EntradaAlbaranTareas bean);

	Long comprobarSiTareasAsociadas(BigDecimal idAlbaran);

}
