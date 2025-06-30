/**
 *
 */
package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.DocumentoTareaAdjunto;
import com.ejie.aa79b.model.DocumentosAlinear;

/**
 * @author eaguirresarobe
 *
 */
public class DocsAlinearRowMapper implements RowMapper<DocumentosAlinear> {

	@Override
	public DocumentosAlinear mapRow(ResultSet resultSet, int arg1) throws SQLException {
		DocumentoTareaAdjunto ficheroLerroko = new DocumentoTareaAdjunto();
		ficheroLerroko.setIdFichero(resultSet.getBigDecimal("IDFICHEROINFORME088"));
		ficheroLerroko.setTitulo(resultSet.getString("TITULOFICHEROINFORME088"));
		ficheroLerroko.setExtension(resultSet.getString("EXTENSIONFICHEROINFORME088"));
		ficheroLerroko.setContentType(resultSet.getString("CONTENTTYPEFICHEROINFORME088"));
		ficheroLerroko.setTamano(resultSet.getLong("TAMANOFICHEROINFORME088"));
		ficheroLerroko.setEncriptado(resultSet.getString("INDENCRIPTADOINFORME088"));
		ficheroLerroko.setOid(resultSet.getString("OIDFICHEROINFORME088"));
		ficheroLerroko.setNombre(resultSet.getString("NOMBREFICHEROINFORME088"));

		DocumentosAlinear documentoAlinear = new DocumentosAlinear();
		documentoAlinear.setIdTarea(resultSet.getBigDecimal("IDTAREA"));
		documentoAlinear.setIndCorreciones(resultSet.getString("INDCORRECCIONES0B6"));
		documentoAlinear.setFicheroLerroko(ficheroLerroko);
		documentoAlinear.setFechaAltaLerroko(resultSet.getDate("FECHAALTALERROKO086"));
		documentoAlinear.setIdFicheroLerroko(resultSet.getBigDecimal("IDFICHEROLERROKO0B6"));

		return documentoAlinear;
	}

}
