package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.Direccion;
import com.ejie.aa79b.model.Entidad;

public class EntidadReceptorRowMapper implements RowMapper<Entidad>{
	
	@Override()
	public Entidad mapRow(ResultSet rs, int rowNum) throws SQLException {
		Entidad bean = new Entidad();
		bean.setTipo(rs.getString(DBConstants.TIPO));
		bean.setTipoDesc(rs.getString("TIPODESC"));
		bean.setCodigo(rs.getInt(DBConstants.CODIGO));
		bean.setDescAmpEs(rs.getString(DBConstants.DESCAMPES));
		bean.setDescAmpEu(rs.getString(DBConstants.DESCAMPEU));
		bean.setDescEs(rs.getString(DBConstants.DESCES));
		bean.setDescEu(rs.getString(DBConstants.DESCEU));
		bean.setCif(rs.getString(DBConstants.CIF));
		bean.setEstado(null);
		
		Direccion direccion = new Direccion();
		direccion.setDirNora(null);
		bean.setDireccion(direccion);
		return bean;
	}
}
