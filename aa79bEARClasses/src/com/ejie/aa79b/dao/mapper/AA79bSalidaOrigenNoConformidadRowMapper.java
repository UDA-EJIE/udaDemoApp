package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.webservices.Aa79bSalidaOrigenNoConformidad;

public class AA79bSalidaOrigenNoConformidadRowMapper implements RowMapper<Aa79bSalidaOrigenNoConformidad> {

	@Override
	public Aa79bSalidaOrigenNoConformidad mapRow(ResultSet resultSet, int arg1) throws SQLException {
		Aa79bSalidaOrigenNoConformidad aa79bSalidaOrigenNoConformidad = new Aa79bSalidaOrigenNoConformidad();
		aa79bSalidaOrigenNoConformidad.setTipoTarea(resultSet.getInt(DBConstants.IDTIPOTAREA));
		aa79bSalidaOrigenNoConformidad.setIdTareaRelacionada(resultSet.getBigDecimal(DBConstants.IDTAREA));
		return aa79bSalidaOrigenNoConformidad;
	}

}
