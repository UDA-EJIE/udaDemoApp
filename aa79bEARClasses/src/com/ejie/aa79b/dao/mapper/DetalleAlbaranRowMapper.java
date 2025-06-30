package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.Albaran;
import com.ejie.aa79b.model.Lotes;

public class DetalleAlbaranRowMapper implements RowMapper<Albaran> {
	@Override
	public Albaran mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Albaran albaran = new Albaran();
		albaran.setIdAlbaran(resultSet.getBigDecimal(DBConstants.IDALBARAN));
		albaran.setRefAlbaran(resultSet.getString(DBConstants.REFALBARAN));
		albaran.setFechaAlta(resultSet.getDate(DBConstants.FECHAALTA));
		albaran.setHoraAlta(resultSet.getString(DBConstants.HORAALTA));
		albaran.setEstado(resultSet.getInt(DBConstants.ESTADO));
		albaran.setDescEstadoEs(resultSet.getString(DBConstants.DESCES));
		albaran.setDescEstadoEu(resultSet.getString(DBConstants.DESCEU));
		albaran.setRefFactura(resultSet.getString(DBConstants.REFFACTURA));
		albaran.setImporteFactura(resultSet.getBigDecimal(DBConstants.IMPORTEFACTURA));
		albaran.setImportePrevisto(resultSet.getBigDecimal("IMPORTEPREVISTO"));
		albaran.setNumTareasAsoc(resultSet.getInt("NUMTAREASASOC"));
		albaran.setNumExpAsoc(resultSet.getInt("NUMEXPASOC"));
		Lotes lotes = new Lotes();
		lotes.setIdLote(resultSet.getInt(DBConstants.IDLOTE));
		lotes.setNombreLote(resultSet.getString(DBConstants.NOMBRELOTE));
		lotes.setImporteAsignado(resultSet.getBigDecimal(DBConstants.IMPORTEASIGNADO));
		lotes.setImporteConsumido(resultSet.getBigDecimal(DBConstants.IMPORTECONSUMIDO));
		lotes.setImportePrevisto(resultSet.getBigDecimal("IMPORTEPREVISTOLOTE"));
		lotes.setImporteDisponible(resultSet.getBigDecimal("IMPORTEDISPONIBLE"));
		albaran.setLotes(lotes);
		return albaran;
	}
}