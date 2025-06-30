package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.webservices.Aa79bAlbaran;
import com.ejie.aa79b.model.webservices.Aa79bDatosPagoProveedores;
import com.ejie.aa79b.model.webservices.Aa79bLotes;
import com.ejie.aa79b.model.webservices.Aa79bSalidaTarea;
import com.ejie.aa79b.model.webservices.Aa79bTiposTarea;

public class AA79bFindTareasAlbaranRowMapper implements RowMapper<Aa79bSalidaTarea> {
	@Override
	public Aa79bSalidaTarea mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Aa79bSalidaTarea aa79bSalidaTarea = new Aa79bSalidaTarea();
		aa79bSalidaTarea.setIdTarea(resultSet.getBigDecimal("IDTAREAFACT"));
		aa79bSalidaTarea.setAnyo(resultSet.getLong(DBConstants.ANYO));
		aa79bSalidaTarea.setNumExp(resultSet.getInt(DBConstants.NUMEXP));

		Aa79bTiposTarea tiposTarea = new Aa79bTiposTarea();
		tiposTarea.setDescEs(resultSet.getString(DBConstants.TIPOTAREADESCES));
		tiposTarea.setDescEu(resultSet.getString(DBConstants.TIPOTAREADESCES));
		aa79bSalidaTarea.setTipoTarea(tiposTarea);

		Aa79bDatosPagoProveedores datosPagoProveedores = new Aa79bDatosPagoProveedores();
		datosPagoProveedores.setNumTotalPal(resultSet.getInt(DBConstants.NUMTOTALPAL));
		datosPagoProveedores.setNumPalConcor084(resultSet.getInt(DBConstants.NUMPALCONCOR084));
		datosPagoProveedores.setNumPalConcor8594(resultSet.getInt(DBConstants.NUMPALCONCOR8594));
		datosPagoProveedores.setNumPalConcor95100(resultSet.getInt(DBConstants.NUMPALCONCOR95100));
		datosPagoProveedores.setImporteTotal(resultSet.getBigDecimal(DBConstants.IMPORTETOTAL));

		Aa79bAlbaran albaran = new Aa79bAlbaran();
		albaran.setRefAlbaran(resultSet.getString(DBConstants.REFALBARAN));
		albaran.setEstado(resultSet.getInt(DBConstants.ESTADO));
		albaran.setDescEstadoEs(resultSet.getString(DBConstants.ESTADOALBARANDESCES));
		albaran.setDescEstadoEu(resultSet.getString(DBConstants.ESTADOALBARANDESCEU));
		datosPagoProveedores.setAlbaran(albaran);

		Aa79bLotes lote = new Aa79bLotes();
		lote.setIdLote(resultSet.getInt("IDLOTE"));
		lote.setDescLoteEu(resultSet.getString("DESCLOTEEU"));
		lote.setDescLoteEs(resultSet.getString("DESCLOTEES"));

		aa79bSalidaTarea.setLotes(lote);

		aa79bSalidaTarea.setDatosPagoProveedor(datosPagoProveedores);

		aa79bSalidaTarea.setIdTareaRel(resultSet.getBigDecimal("IDTAREAREVPAGO"));

		return aa79bSalidaTarea;
	}

}
