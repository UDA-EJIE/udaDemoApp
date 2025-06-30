package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.DatosPagoProveedores;

public class DatosPagoProveedoresConsultaInterRowMapper implements RowMapper<DatosPagoProveedores> {

	@Override
	public DatosPagoProveedores mapRow(ResultSet resultSet, int arg1) throws SQLException {
		DatosPagoProveedores datosPagoProveedoresConsultaInter = new DatosPagoProveedores();
		datosPagoProveedoresConsultaInter.setIdTarea(resultSet.getBigDecimal(DBConstants.IDTAREA));
		datosPagoProveedoresConsultaInter.setImporteBase(resultSet.getBigDecimal(DBConstants.IMPORTEBASE));
		datosPagoProveedoresConsultaInter.setPorIva(resultSet.getLong(DBConstants.PORIVA));
		datosPagoProveedoresConsultaInter.setImporteIva(resultSet.getBigDecimal(DBConstants.IMPORTEIVA));
		datosPagoProveedoresConsultaInter.setImporteTotal(resultSet.getBigDecimal(DBConstants.IMPORTETOTAL));
		datosPagoProveedoresConsultaInter.setIdTipoExpediente(resultSet.getString(DBConstants.IDTIPOEXPEDIENTE));
		datosPagoProveedoresConsultaInter.setTipoExpedienteDescEs(resultSet.getString(DBConstants.TIPOEXPEDIENTEDESCES));
		datosPagoProveedoresConsultaInter.setTipoExpedienteDescEu(resultSet.getString(DBConstants.TIPOEXPEDIENTEDESCEU));
		return datosPagoProveedoresConsultaInter;
	}
}
