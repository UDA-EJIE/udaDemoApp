package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.DocumentoTarea;
import com.ejie.aa79b.model.DocumentoTareaAdjunto;

public class DocsXliffTareaOrigenNoConformidadTraduccionRowMapper implements RowMapper<DocumentoTarea> {

	@Override
	public DocumentoTarea mapRow(ResultSet resultSet, int arg1) throws SQLException {
		DocumentoTarea documentoTarea = new DocumentoTarea();

		documentoTarea.setIdTarea(resultSet.getBigDecimal("IDTAREA"));

		DocumentoTareaAdjunto documentoTareaAdjunto = new DocumentoTareaAdjunto();
		documentoTareaAdjunto.setIdFichero(resultSet.getBigDecimal("IDFICHEROXLIFF"));
		documentoTareaAdjunto.setEncriptado(resultSet.getString("INDENCRIPTADO"));
		documentoTareaAdjunto.setOid(resultSet.getString("OIDFICHERO"));
		documentoTareaAdjunto.setTitulo(resultSet.getString("TITULO"));
		documentoTarea.setDocumentoAdjunto(documentoTareaAdjunto);

		return documentoTarea;
	}

}
