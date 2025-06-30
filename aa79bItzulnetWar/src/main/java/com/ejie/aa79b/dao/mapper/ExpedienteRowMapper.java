package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.utils.QueryUtils;

public class ExpedienteRowMapper implements RowMapper<Expediente> {

	@Override
	public Expediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		final QueryUtils queryUtils = new QueryUtils();
		return queryUtils.getExpedienteRwMp(resultSet, false);
	}

}
