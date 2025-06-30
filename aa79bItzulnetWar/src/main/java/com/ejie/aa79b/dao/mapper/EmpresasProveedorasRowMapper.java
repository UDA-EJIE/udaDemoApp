package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.Direccion;
import com.ejie.aa79b.model.EmpresasProveedoras;

/**
 * EmpresasProveedorasRowMapper, 24-ene-2018 09:11:29.
 */

public class EmpresasProveedorasRowMapper implements RowMapper<EmpresasProveedoras> {

	@Override()
	public EmpresasProveedoras mapRow(ResultSet rs, int rowNum) throws SQLException {
		EmpresasProveedoras bean = new EmpresasProveedoras();
		bean.setTipo(rs.getString(DBConstants.TIPO));
		bean.setCodigo(rs.getLong(DBConstants.CODIGO));
		bean.setDescAmpEs(rs.getString(DBConstants.DESCAMPES));
		bean.setDescAmpEu(rs.getString(DBConstants.DESCAMPEU));
		bean.setDescEs(rs.getString(DBConstants.DESCES));
		bean.setDescEu(rs.getString(DBConstants.DESCEU));
		bean.setCif(rs.getString(DBConstants.CIF));
		bean.setEstado(rs.getString(DBConstants.ESTADO));
		bean.setEstadoDesc(rs.getString(DBConstants.ESTADODESC));
		bean.setFacturable(rs.getString(DBConstants.FACTURABLE));
		bean.setIva(rs.getString(DBConstants.IVA));

		Direccion direccion = new Direccion();
		direccion.setDirNora(rs.getInt(DBConstants.CDIRNORA));
		bean.setDireccion(direccion);

		bean.setLotesVigentes(rs.getString("LOTESVIGENTES"));
		bean.setLotesVigentesDesc(rs.getString("LOTESVIGENTESDESC"));

		return bean;
	}

}
