package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.TareaIntPagoProveed;

public class TareasRowMapperTareaIntPagoProveed implements RowMapper<TareaIntPagoProveed> {

	@Override
	public TareaIntPagoProveed mapRow(ResultSet rs, int arg1) throws SQLException {
		TareaIntPagoProveed tarea = new TareaIntPagoProveed();
		tarea.setFechaIni(rs.getDate("FECHAINI"));
		tarea.setFechaFin(rs.getDate("FECHAFIN"));
		tarea.setHoraIni(rs.getString("HORAINI"));
		tarea.setHoraFin(rs.getString("HORAFIN"));
		tarea.setImporteBase(rs.getBigDecimal("IMPTBASE"));
		tarea.setImporteIVA(rs.getBigDecimal("IMPTIVA"));
		tarea.setPorcentIVA(rs.getLong("PORCIVA"));
		tarea.setTotal(rs.getBigDecimal("IMPRTOTAL"));
		tarea.setHorasPrevistas(rs.getString("HORASPREVISTAS"));
		tarea.setHorasRealesAsignadas(rs.getString("HORASREALESTAREA"));
		tarea.setNomPersonAsignado(rs.getString("NOMBRE") + " " + rs.getString("APE1") + " " + rs.getString("APE2"));
		return tarea;
	}

}
