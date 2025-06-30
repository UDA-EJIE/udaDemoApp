package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.DocumentoTarea;
import com.ejie.aa79b.model.DocumentoTareaAdjunto;
import com.ejie.aa79b.model.Tareas;

public class TareasCorreccionProvRowMapper implements RowMapper<Tareas> {

	@Override
	public Tareas mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Tareas tareas = new Tareas();
		DocumentoTarea documentoTarea = new DocumentoTarea();
		DocumentoTareaAdjunto documentoAdjunto = new DocumentoTareaAdjunto();
		documentoAdjunto.setIdFichero(resultSet.getBigDecimal(DBConstants.IDFICHERO));
		documentoAdjunto.setNombre(resultSet.getString(DBConstants.NOMBREFICHERO));
		documentoAdjunto.setEncriptado(resultSet.getString(DBConstants.INDENCRIPTADO));
		documentoTarea.setDocumentoAdjunto(documentoAdjunto);
		tareas.setDocumentoTarea(documentoTarea);
		tareas.setObservaciones(resultSet.getString(DBConstants.OBSERV));

		return tareas;
	}

}
