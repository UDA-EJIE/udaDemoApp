package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.DocumentoTarea;
import com.ejie.aa79b.model.DocumentoTareaAdjunto;
import com.ejie.aa79b.model.DocumentosExpediente;
import com.ejie.aa79b.model.FicheroDocExp;

public class DocumentoJustificanteRowMapper implements RowMapper<DocumentoTarea> {

	@Override
	public DocumentoTarea mapRow(ResultSet resultSet, int arg1) throws SQLException {
		DocumentoTarea documentoTarea = new DocumentoTarea();

		documentoTarea.setIdTarea(resultSet.getBigDecimal(DBConstants.IDTAREA83));

		DocumentosExpediente documentosExpediente = new DocumentosExpediente();
		documentosExpediente.setIdDoc(resultSet.getBigDecimal(DBConstants.IDDOC));
		documentosExpediente.setTitulo(resultSet.getString(DBConstants.TITULO));
		documentosExpediente.setClaseDocumento(resultSet.getLong(DBConstants.CLASEDOCUMENTO));

		List<FicheroDocExp> listaFicheros = new ArrayList<FicheroDocExp>();
		FicheroDocExp fichero = new FicheroDocExp();
		fichero.setOid(resultSet.getString(DBConstants.OIDFICHERO));
		fichero.setEncriptado(resultSet.getString(DBConstants.INDENCRIPTADO));
		listaFicheros.add(fichero);
		documentosExpediente.setFicheros(listaFicheros);
		documentoTarea.setDocumentoOriginal(documentosExpediente);

		DocumentoTareaAdjunto documentoTareaJustificante = new DocumentoTareaAdjunto();
		documentoTareaJustificante.setIdFichero(resultSet.getBigDecimal(DBConstants.IDFICHEROJUSTIFICANTE));
		documentoTareaJustificante.setEncriptado(resultSet.getString(DBConstants.INDENCRIPTADOREL));
		documentoTareaJustificante.setOid(resultSet.getString(DBConstants.OIDFICHEROJUST));
		documentoTarea.setDocumentoAdjunto(documentoTareaJustificante);

		return documentoTarea;
	}

}
