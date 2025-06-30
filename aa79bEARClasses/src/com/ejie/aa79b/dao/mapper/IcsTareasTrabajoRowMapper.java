/**
 *
 */
package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.OtrosTrabajos;
import com.ejie.aa79b.model.TareasTrabajo;
import com.ejie.aa79b.model.TiposTarea;

/**
 * @author eaguirresarobe
 *
 */
public class IcsTareasTrabajoRowMapper implements RowMapper<TareasTrabajo> {

	@Override
	public TareasTrabajo mapRow(ResultSet rs, int arg1) throws SQLException {
		TareasTrabajo tareas = new TareasTrabajo();
		tareas.setIdTarea(rs.getBigDecimal(DBConstants.IDTAREA));
		TiposTarea tiposTarea = new TiposTarea();
		tiposTarea.setId015(rs.getLong(DBConstants.IDTIPOTAREA));
		tiposTarea.setDescEs015(rs.getString(DBConstants.TIPOTAREADESCES));
		tiposTarea.setDescEu015(rs.getString(DBConstants.TIPOTAREADESCEU));
		tareas.setTipoTarea(tiposTarea);
		tareas.setFechaInicio(rs.getDate(DBConstants.FECHAINI));
		tareas.setHoraInicio(rs.getString(DBConstants.HORAINI));
		tareas.setFechaFin(rs.getDate(DBConstants.FECHAFIN));
		tareas.setHoraFin(rs.getString(DBConstants.HORAFIN));
		OtrosTrabajos trabajo = new OtrosTrabajos();
		trabajo.setIdTrabajo(rs.getBigDecimal("IDTRABAJO"));
		tareas.setTrabajo(trabajo);
		return tareas;
	}

}
