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

public class DocumentoTareasTraduccionRowMapper implements RowMapper<DocumentoTarea> {

	@Override
	public DocumentoTarea mapRow(ResultSet resultSet, int arg1) throws SQLException {
		DocumentoTarea documentoTarea = new DocumentoTarea();

		documentoTarea.setIdTarea(resultSet.getBigDecimal("ID_TAREA_083"));

		DocumentosExpediente documentosExpediente = new DocumentosExpediente();
		documentosExpediente.setIdDoc(resultSet.getBigDecimal("ID_DOC_056"));
		documentosExpediente.setTitulo(resultSet.getString("TITULO_056"));
		documentosExpediente.setClaseDocumento(resultSet.getLong("CLASE_DOCUMENTO_056"));

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
		documentoTareaAdjunto.setIdFichero(resultSet.getBigDecimal("ID_FICHERO_TRADUCIDO_087"));
		documentoTareaAdjunto.setEncriptado(resultSet.getString("IND_ENCRIPTADO_088"));
		documentoTareaAdjunto.setOid(resultSet.getString("OID_FICHERO_088"));
		documentoTareaAdjunto.setNombre(resultSet.getString("NOMBREFICHEROTAREA"));
		documentoTarea.setDocumentoAdjunto(documentoTareaAdjunto);

		DocumentoTareaAdjunto documentoFinalFirmado = new DocumentoTareaAdjunto();
		documentoFinalFirmado.setIdFichero(resultSet.getBigDecimal("IDDOCFINALFIRMADO"));
		documentoFinalFirmado.setEstadoFirma(resultSet.getInt("ESTADOFIRMA"));
		// Se utiliza el objeto documento adjunto para volcar los datos
		// asociados al documento final
		documentoTarea.setDocumentoFinalFirmado(documentoFinalFirmado);

		DocumentoTareaAdjunto documentoOriginalFirmado = new DocumentoTareaAdjunto();
		documentoOriginalFirmado.setIdFichero(resultSet.getBigDecimal("IDDOCORIGFIRMADO"));
		documentoOriginalFirmado.setEstadoFirma(resultSet.getInt("ESTADOFIRMA"));
		// Se utiliza el objeto documento adjunto para volcar los datos
		// asociados al documento final
		documentoTarea.setDocumentoOriginalFirmado(documentoOriginalFirmado);

		DocumentoTareaAdjunto documentoFinalOriginal = new DocumentoTareaAdjunto();
		documentoFinalOriginal.setIdFichero(resultSet.getBigDecimal("IDDOCFINALORIG"));
		documentoTarea.setDocumentoFinalOriginal(documentoFinalOriginal);

		return documentoTarea;
	}

}
