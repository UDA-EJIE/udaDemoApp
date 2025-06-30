package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.webservices.Aa79bDocumentoTarea;
import com.ejie.aa79b.model.webservices.Aa79bDocumentoTareaAdjunto;

public class Aa79bDocumentoTareaXliffRowMapper implements RowMapper<Aa79bDocumentoTarea> {

	@Override
	public Aa79bDocumentoTarea mapRow(ResultSet resultSet, int arg1) throws SQLException {

		Aa79bDocumentoTarea documentoTarea = new Aa79bDocumentoTarea();

		documentoTarea.setIdTarea(resultSet.getBigDecimal(DBConstants.IDTAREA));

		Aa79bDocumentoTareaAdjunto documentoTareaAdjunto = new Aa79bDocumentoTareaAdjunto();
		documentoTareaAdjunto.setIdFichero(resultSet.getBigDecimal(DBConstants.IDFICHEROXLIFF));
		documentoTareaAdjunto.setEncriptado(resultSet.getString(DBConstants.INDENCRIPTADO));
		documentoTareaAdjunto.setOid(resultSet.getString(DBConstants.OIDFICHERO));
		documentoTareaAdjunto.setTitulo(resultSet.getString(DBConstants.TITULO));
		documentoTarea.setDocumentoAdjunto(documentoTareaAdjunto);

		return documentoTarea;

	}

}
