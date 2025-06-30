package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.Fichero;

public class FacturasRowMapperDocumento implements RowMapper<Fichero> {

	@Override
	public Fichero mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Fichero fichero = new Fichero();
		String nombreFich = resultSet.getString(DBConstants.NOMBREFICHERO);
		nombreFich = nombreFich.replace(".", "-" + resultSet.getString("IDFACTURA") + ".");
		fichero.setOid(resultSet.getString(DBConstants.OIDFICHERO));
		fichero.setNombre(nombreFich);
		fichero.setContentType(resultSet.getString(DBConstants.CONTENTTYPE));

		return fichero;
	}

}
