package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.Pagamentos;

/**
 * @author javarona
 *
 */
public class PagamentosRowMapper implements RowMapper<Pagamentos> {

	@Override()
	public Pagamentos mapRow(ResultSet resultSet, int rowNum) throws SQLException {

		Pagamentos pag = new Pagamentos(resultSet.getInt("IDALBARAN"));

		pag.setCodEntidad(resultSet.getInt("CODENTIDAD"));
		pag.setIdLote(resultSet.getInt("IDLOTE"));
		pag.setIdAlbaran(resultSet.getInt("IDALBARAN"));
		pag.setRefAlbaran(resultSet.getString("REFALBARAN"));
		pag.setFechaAlta(resultSet.getTimestamp("FECHAALTA"));
		pag.setHoraAlta(resultSet.getString(DBConstants.HORAALTA));
		pag.setNtareas(resultSet.getBigDecimal("NTAREAS"));
		pag.setNexpedientes(resultSet.getBigDecimal("NEXPEDIENTES"));
		pag.setImporteTotal(resultSet.getBigDecimal("IMPORTETOTAL"));
		pag.setEstadoAlbaran(resultSet.getInt("ESTADOALBARAN"));
		pag.setDescEstadoAlbaran(resultSet.getString("DESCESTADOALBARAN"));
		pag.setImporteFactura(resultSet.getBigDecimal("IMPORTEFACTURA"));
		pag.setNombreLote(resultSet.getString("NOMBRE_LOTE"));
		pag.setImporteAssignado(resultSet.getBigDecimal("IMPORTE_ASIGNADO"));
		pag.setImporteConsumido(resultSet.getBigDecimal("IMPORTE_CONSUMIDO"));
		return pag;

	}

}
