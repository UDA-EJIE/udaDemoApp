package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.webservices.Aa79bAlbaran;

public class Aa79bAlbaranRowMapper implements RowMapper<Aa79bAlbaran> {
	@Override
	public Aa79bAlbaran mapRow(ResultSet resultSet, int rowNum) throws SQLException {

		Aa79bAlbaran aa79bAlbaran = new Aa79bAlbaran();
		aa79bAlbaran.setIdAlbaran(resultSet.getBigDecimal(DBConstants.IDALBARAN));
		aa79bAlbaran.setRefAlbaran(resultSet.getString(DBConstants.REFALBARAN));
		aa79bAlbaran.setIdLote(resultSet.getInt(DBConstants.IDLOTE));
		final Date fechaAlta = resultSet.getTimestamp(DBConstants.FECHAALTA);
		if (fechaAlta != null) {
			aa79bAlbaran.setFechaAlta(fechaAlta.getTime());
		}
		aa79bAlbaran.setEstado(resultSet.getInt(DBConstants.ESTADO));
		aa79bAlbaran.setRefFactura(resultSet.getString(DBConstants.REFFACTURA));
		aa79bAlbaran.setImporteFactura(resultSet.getInt(DBConstants.IMPORTEFACTURA));
		return aa79bAlbaran;
	}

}
