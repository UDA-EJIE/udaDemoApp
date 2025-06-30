/**
 * 
 */
package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.DocumentoTarea;
import com.ejie.aa79b.model.DocumentosExpediente;
import com.ejie.aa79b.model.FicheroDocExp;

/**
 * @author eaguirresarobe
 *
 */
public class DocumentoTareaCorreccionRowMapper implements RowMapper<DocumentoTarea> {

	@Override
	public DocumentoTarea mapRow(ResultSet resultSet, int arg1) throws SQLException {
		DocumentoTarea documentoTareaCorreccion = new DocumentoTarea();
		DocumentosExpediente documento = new DocumentosExpediente();
		FicheroDocExp fichero = new FicheroDocExp();
		documentoTareaCorreccion.setIdTarea(resultSet.getBigDecimal(DBConstants.IDTAREA));
		documento.setIdDoc(resultSet.getBigDecimal(DBConstants.IDFICHEROCORRECCION));
		documento.setTitulo(resultSet.getString(DBConstants.TITULO));
		fichero.setIdDocInsertado(resultSet.getBigDecimal(DBConstants.IDFICHEROCORRECCION));
		fichero.setExtension(resultSet.getString(DBConstants.EXTENSIONFICHERO));
		fichero.setContentType(resultSet.getString(DBConstants.CONTENTTYPE));
		fichero.setTamano(resultSet.getLong(DBConstants.TAMANOFICHERO));
		fichero.setEncriptado(resultSet.getString(DBConstants.INDENCRIPTADO));
		fichero.setRutaPif(resultSet.getString(DBConstants.RUTAPIFFICHERO));
		fichero.setOid(resultSet.getString(DBConstants.OIDFICHERO));
		fichero.setNombre(resultSet.getString(DBConstants.NOMBREFICHERO));
		List<FicheroDocExp> listaFicheros = new ArrayList<FicheroDocExp>();
		listaFicheros.add(fichero);
		documento.setFicheros(listaFicheros);
		documentoTareaCorreccion.setDocumentoOriginal(documento);
		return documentoTareaCorreccion;
	}

}
