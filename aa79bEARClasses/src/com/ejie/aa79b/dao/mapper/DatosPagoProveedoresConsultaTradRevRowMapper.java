package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.DatosPagoProveedores;
import com.ejie.aa79b.utils.QueryUtils;

public class DatosPagoProveedoresConsultaTradRevRowMapper implements RowMapper<DatosPagoProveedores> {

	@Override
	public DatosPagoProveedores mapRow(ResultSet resultSet, int arg1) throws SQLException {

		final QueryUtils queryUtils = new QueryUtils();
		return queryUtils.getDatosPagoProveedorConsultaTradRev(resultSet);
	}

}
