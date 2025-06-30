/**
 * 
 */
package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.webservices.Aa79bEjecucionTareas;
import com.ejie.aa79b.model.webservices.Aa79bSalidaTarea;

/**
 * @author eaguirresarobe
 *
 */
public class Aa79bDatosTareaEjecutadaRowMapper implements RowMapper<Aa79bSalidaTarea> {

	@Override
	public Aa79bSalidaTarea mapRow(ResultSet resultSet, int arg1) throws SQLException {
		Aa79bSalidaTarea datosTareaEjecutada = new Aa79bSalidaTarea();
		datosTareaEjecutada.setObservaciones(resultSet.getString(DBConstants.OBSERVACIONES));
		Aa79bEjecucionTareas datosEjecucionTareaInterpretacion = new Aa79bEjecucionTareas();
		datosEjecucionTareaInterpretacion.setHorasTarea(resultSet.getString(DBConstants.HORASTAREA));
		datosEjecucionTareaInterpretacion.setPorUsoEuskera(resultSet.getLong(DBConstants.PORUSOEUSKERA));
		datosTareaEjecutada.setEjecucionTareas(datosEjecucionTareaInterpretacion);

		return datosTareaEjecutada;
	}

}
