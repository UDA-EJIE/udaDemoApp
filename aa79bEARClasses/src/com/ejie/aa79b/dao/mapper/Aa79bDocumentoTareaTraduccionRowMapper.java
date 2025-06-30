package com.ejie.aa79b.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.ejie.aa79b.common.DBConstants;
import com.ejie.aa79b.model.webservices.Aa79bDocumentoTarea;
import com.ejie.aa79b.model.webservices.Aa79bDocumentoTareaAdjunto;
import com.ejie.aa79b.model.webservices.Aa79bDocumentosExpediente;
import com.ejie.aa79b.model.webservices.Aa79bFicheroDocExp;

public class Aa79bDocumentoTareaTraduccionRowMapper implements RowMapper<Aa79bDocumentoTarea> {

	@Override
	public Aa79bDocumentoTarea mapRow(ResultSet resultSet, int arg1) throws SQLException {

		Aa79bDocumentoTarea documentoTarea = new Aa79bDocumentoTarea();
		documentoTarea.setIdTarea(resultSet.getBigDecimal(DBConstants.IDTAREA));

		Aa79bDocumentosExpediente documentosExpediente = new Aa79bDocumentosExpediente();
		documentosExpediente.setIdDoc(resultSet.getBigDecimal(DBConstants.IDDOC));
		documentosExpediente.setTitulo(resultSet.getString(DBConstants.TITULO));
		documentosExpediente.setClaseDocumento(resultSet.getLong(DBConstants.CLASEDOCUMENTO));

		List<Aa79bFicheroDocExp> listaFicheros = new ArrayList<Aa79bFicheroDocExp>();
		Aa79bFicheroDocExp fichero = new Aa79bFicheroDocExp();
		fichero.setOid(resultSet.getString(DBConstants.OIDFICHERO));
		fichero.setEncriptado(resultSet.getString(DBConstants.INDENCRIPTADO));
		fichero.setExtension(resultSet.getString(DBConstants.EXTENSIONDOC));
		fichero.setNombre(resultSet.getString("NOMBREDOC"));
		listaFicheros.add(fichero);
		documentosExpediente.setFicheros(listaFicheros);
		documentoTarea.setDocumentoOriginal(documentosExpediente);

		Aa79bDocumentoTareaAdjunto documentoTareaAdjunto = new Aa79bDocumentoTareaAdjunto();
		documentoTareaAdjunto.setIdFichero(resultSet.getBigDecimal(DBConstants.IDFICHEROTRADUCIDO));
		documentoTareaAdjunto.setEncriptado(resultSet.getString(DBConstants.INDENCRIPTADODOCTRAD));
		documentoTareaAdjunto.setOid(resultSet.getString(DBConstants.OIDFICHERODOCTRAD));
		documentoTareaAdjunto.setTitulo(resultSet.getString(DBConstants.TITULODOCTRADUCIDO));
		documentoTareaAdjunto.setNombre(resultSet.getString("NOMBREDOCTRADUCIDO"));
		documentoTarea.setDocumentoAdjunto(documentoTareaAdjunto);

		return documentoTarea;
	}

}
