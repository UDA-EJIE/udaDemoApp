package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.Leyenda;

public class ExpedienteRowMapperConfLeyenda implements RowMapper<Leyenda> {

	@Override
	public Leyenda mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Leyenda leyenda = new Leyenda();
		leyenda.setIdEstadosExp(resultSet.getLong(DBConstants.IDESTADOEXP));
		leyenda.setIdFaseExp(resultSet.getLong(DBConstants.IDFASEEXPEDIENTE));
		leyenda.setOrden(resultSet.getLong(DBConstants.ORDEN));
		leyenda.setNivel(resultSet.getLong(DBConstants.NIVEL));
		leyenda.setTexto(resultSet.getString(DBConstants.TEXTO));

		return leyenda;
	}

}
