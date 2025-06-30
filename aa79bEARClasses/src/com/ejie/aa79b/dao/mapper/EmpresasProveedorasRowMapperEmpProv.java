package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.EmpresasProveedoras;

public class EmpresasProveedorasRowMapperEmpProv implements RowMapper<EmpresasProveedoras> {

	@Override()
	public EmpresasProveedoras mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		EmpresasProveedoras bean = new EmpresasProveedoras();
		bean.setTipo(resultSet.getString(DBConstants.TIPO));
		bean.setCodigo(resultSet.getLong(DBConstants.CODIGO));
		bean.setDescAmpEs(resultSet.getString(DBConstants.DESCAMPES));
		bean.setDescAmpEu(resultSet.getString(DBConstants.DESCAMPEU));
		bean.setDescEs(resultSet.getString(DBConstants.DESCES));
		bean.setDescEu(resultSet.getString(DBConstants.DESCEU));
		bean.setCif(resultSet.getString(DBConstants.CIF));

		return bean;
	}

}
