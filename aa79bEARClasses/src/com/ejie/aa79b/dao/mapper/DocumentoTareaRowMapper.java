package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.model.DocumentoTarea;
import com.ejie.aa79b.model.DocumentoTareaAdjunto;
import com.ejie.aa79b.model.DocumentosExpediente;
import com.ejie.aa79b.model.FicheroDocExp;

/**
 * @author javarona
 *
 */
public class DocumentoTareaRowMapper implements RowMapper<DocumentoTarea> {

	@Override()
	public DocumentoTarea mapRow(ResultSet resultSet, int rowNum) throws SQLException {

		DocumentoTarea documentoTarea = new DocumentoTarea();

		documentoTarea.setIdTarea(resultSet.getBigDecimal("ID_TAREA_083"));

		DocumentosExpediente documentosExpediente = new DocumentosExpediente();
		documentosExpediente.setIdDoc(resultSet.getBigDecimal("ID_DOC_056"));
		documentosExpediente.setTitulo(resultSet.getString("TITULO_056"));
		documentosExpediente.setClaseDocumento(resultSet.getLong("CLASE_DOCUMENTO_056"));
		documentosExpediente.setAnyo(resultSet.getLong("ANYO_056"));
		documentosExpediente.setNumExp(resultSet.getInt("NUM_EXP_056"));

		List<FicheroDocExp> listaFicheros = new ArrayList<FicheroDocExp>();
		FicheroDocExp fichero = new FicheroDocExp();
		fichero.setOid(resultSet.getString("OID_FICHERO_056"));
		fichero.setEncriptado(resultSet.getString("IND_ENCRIPTADO_056"));
		fichero.setExtension(resultSet.getString("EXTENSION_DOC_056"));
		fichero.setNombre(resultSet.getString("NOMBREDOCORIGINAL"));
		listaFicheros.add(fichero);
		documentosExpediente.setFicheros(listaFicheros);
		documentoTarea.setDocumentoOriginal(documentosExpediente);

		DocumentoTareaAdjunto documentoTareaAdjunto = new DocumentoTareaAdjunto();
		documentoTareaAdjunto.setIdFichero(resultSet.getBigDecimal("ID_FICHERO_FINAL_092"));
		documentoTareaAdjunto.setIndVisible(resultSet.getString("IND_VISIBLE_092"));
		documentoTareaAdjunto.setEncriptado(resultSet.getString("IND_ENCRIPTADO_088"));
		documentoTareaAdjunto.setOid(resultSet.getString("OID_FICHERO_088"));
		documentoTareaAdjunto.setTitulo(resultSet.getString("TITULO_FICHERO_088"));
		documentoTareaAdjunto.setNombre(resultSet.getString("NOMBREFICHEROFINAL"));
		documentoTarea.setDocumentoAdjunto(documentoTareaAdjunto);

		DocumentoTareaAdjunto documentoFinalOriginal = new DocumentoTareaAdjunto();
		documentoFinalOriginal.setIdFichero(resultSet.getBigDecimal("ID_DOC_FINAL_ORIG"));
		documentoFinalOriginal.setIndVisible(resultSet.getString("IND_VISIBLE_092"));
		documentoFinalOriginal.setEncriptado(resultSet.getString("ENCRIPTADO_ORIG_FINAL_088"));
		documentoFinalOriginal.setOid(resultSet.getString("OID_ORIG_FINAL_088"));
		documentoFinalOriginal.setTitulo(resultSet.getString("TITULO_ORIGINAL_FINAL_088"));
		documentoFinalOriginal.setNombre(resultSet.getString("NOMBREORIGFINAL"));
		documentoTarea.setDocumentoFinalOriginal(documentoFinalOriginal);

		return documentoTarea;

	}

}
