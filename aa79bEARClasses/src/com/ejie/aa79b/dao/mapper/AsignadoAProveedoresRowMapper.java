package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.TareasAsignado;
import com.ejie.aa79b.model.TiposTarea;

public class AsignadoAProveedoresRowMapper implements RowMapper<TareasAsignado> {
	@Override()
	public TareasAsignado mapRow(ResultSet rs, int rowNum) throws SQLException {
		TareasAsignado tareasAsignado = new TareasAsignado();
		TiposTarea tiposTarea = new TiposTarea();
		tiposTarea.setId015(rs.getLong("IDTIPOTAREA"));
		tiposTarea.setDescEs015(rs.getString("TAREA"));
		tiposTarea.setDescEu015(rs.getString("TAREA"));

		tareasAsignado.setIdTarea(rs.getInt("IDTAREA"));
		tareasAsignado.setNumExp(rs.getInt("NUMEXP"));
		tareasAsignado.setAnyo(rs.getLong("ANYO"));
		tareasAsignado.setTipoTarea(tiposTarea);
		tareasAsignado.setIdLote(rs.getInt("IDLOTE"));
		tareasAsignado.setNombreLote(rs.getString(DBConstants.NOMBRELOTE));
		tareasAsignado.setEstadoEjecucionDesc(rs.getString("ESTADOEJECUCIONDESC"));
		tareasAsignado.setNumPalIZO(rs.getLong("num_pal_izo"));
		tareasAsignado.setNumTotalPalIZO(rs.getLong("num_total_pal_izo"));
		tareasAsignado.setFechaFin(rs.getDate("FECHAFIN"));
		tareasAsignado.setHoraFin(rs.getString("HORAFIN"));
		tareasAsignado.setIdTareaRel(rs.getString("IDTAREAPROVEEDORES"));
		tareasAsignado.setIndAlbaran(rs.getString("INDALBARAN"));
		tareasAsignado.setOrden(rs.getInt("ORDEN"));
		return tareasAsignado;
	}
}
